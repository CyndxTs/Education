using System;
using System.ComponentModel;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftBibliotecaBO.ServicioWeb;
using SoftBibliotecaPolitica;
using SoftBibliotecaPoliticaPrestamos;
using SoftBibliotecaPrestamosBO;
using SoftBibliotecaPublicacionBO;
using SoftBibliotecaUniversidadBO;
using SoftBibliotecaUsuarioBO;

namespace SoftBibliotecaWA
{
    public partial class RegistrarPrestamoBibliotecario : System.Web.UI.Page
    {
        private UsuarioBO usuarioBO;
        private CopiaEjemplarBO copiaEjemplarBO;
        private PrestamoBO prestamoBO;
        private ReservaBO reservaBO;
        private UniversidadBO universiadadBO;
        private BibliotecaBO bibliotecaBO;
        private PoliticaBO politicaBO;
        private PoliticaPrestamoBO politicaPrestamoBO;
        private PublicacionBO publicacionBO;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                nombreUsuario.InnerText = "";
                nombreUsuario.Visible = false;
                Session["calificado"] = null;
                Session["usuarioConsulta"] = null;
                Session["copiaEjemplar"] = null;
                lblMensajeRegistro.Text = "";
                btnEliminarMiembro.Visible = false;
                btnNuevoPrestamo.Visible = false;
            }
            else
            {
                //btnEliminarMiembro_Click(null, null);
            }
        }

        protected void btnConsultarCopia_Click(object sender, EventArgs e)
        {
            string codigoCopia = txtCodigoCopia.Text;
            publicacionBO = new PublicacionBO();

            if (string.IsNullOrEmpty(codigoCopia)) return;

            copiaEjemplar copia = publicacionBO.consultarCopiaEjemplar(int.Parse(codigoCopia));
            if (copia.estado.ToString() != "OPTIMO")
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "alert", "alert('Esta copia no debería prestarse, no está en estado OPTIMO');", true);
                lblMotivos.Text = "";
                Session["usuarioConsulta"] = null;
                nombreUsuario.InnerText = "";
                nombreUsuario.Visible = false;
                btnEliminarMiembro.Visible = false;
                gvMiembros.Visible = false;

                return;
            }
            Session["copiaEjemplar"] = copia;

            if (copia != null)
            {
                if (copia.publicacionAsociada is libro)
                {
                    lblIdPublicacion.Text = "Código del libro: " + copia.publicacionAsociada.idPublicacion;
                    libro libro = (libro)copia.publicacionAsociada;
                    lblTituloPublicacion.Text = "Título: " + libro.titulo;
                    Session["idBiblioteca"] = libro.bibliotecaAsociada.idBiblioteca;

                    if (copia.publicacionAsociada.portada != null)
                    {
                        string base64String = Convert.ToBase64String(copia.publicacionAsociada.portada);
                        imgPortada.ImageUrl = "data:image/png;base64," + base64String;
                    }
                    else
                    {
                        imgPortada.ImageUrl = "~/Images/imgLibro.png";
                    }
                }
                else if (copia.publicacionAsociada is tesis)
                {
                    lblIdPublicacion.Text = "Código de la tesis: " + copia.publicacionAsociada.idPublicacion;
                    tesis tesis = (tesis)copia.publicacionAsociada;
                    lblTituloPublicacion.Text = "Título: " + tesis.titulo;

                    if (copia.publicacionAsociada.portada != null)
                    {
                        string base64String = Convert.ToBase64String(copia.publicacionAsociada.portada);
                        imgPortada.ImageUrl = "data:image/png;base64," + base64String;
                    }
                    else
                    {
                        imgPortada.ImageUrl = "~/Images/imgTesis.png";
                    }
                }

                lblIdPublicacion.Visible = true;
                lblTituloPublicacion.Visible = true;
                imgPortada.Visible = true;

                lblMotivos.Text = "";
                Session["usuarioConsulta"] = null;

                btnEliminarMiembro.Visible = false;
                gvMiembros.Visible = false;
            }
        }

        protected void btnBuscarMiembro_Click(object sender, EventArgs e)
        {

            string nombre = txtBuscarNombreMiembro.Text.Trim();
            string apellido = txtBuscarApellidoMiembro.Text.Trim();
            if (string.IsNullOrEmpty(nombre) && string.IsNullOrEmpty(apellido))
            {
                string scriptAutores = "alert('Ingrese el nombre de un miembro porfavor');";
                ClientScript.RegisterStartupScript(this.GetType(), "", scriptAutores, true);
                return;
            }
            else
            {
                usuarioBO = new UsuarioBO();
                BindingList<usuario> usuarios = usuarioBO.listarTodos(nombre, apellido, null, null, null, 10);
                usuarios = new BindingList<usuario>(usuarios.Where(u => u.tipoUsuario.ToString() == "MIEMBRO" || u.tipoUsuario.ToString() == "SANCIONADO").ToList());
                gvMiembros.DataSource = usuarios;
                gvMiembros.DataBind();
                gvMiembros.Visible = true;
            }
        }

        protected void btnSeleccionarMiembro_Click(object sender, EventArgs e)
        {
            copiaEjemplar copiaActual = (copiaEjemplar)Session["copiaEjemplar"];
            if (Session["copiaEjemplar"] == null)
            {
                // Si no se ha ingresado la copia, mostramos un alert
                ScriptManager.RegisterStartupScript(this, this.GetType(), "alert", "alert('Por favor, ingrese primero el código de la copia.');", true);
                return;
            }

            LinkButton btn = (LinkButton)sender;
            string commandArgument = btn.CommandArgument;
            string[] arguments = commandArgument.Split(',');
            int idMiembro = Int32.Parse(arguments[0]);
            string nombreMiembro = arguments[1];
            string apellidoMiembro = arguments[2];

            txtBuscarNombreMiembro.Text = nombreMiembro;
            txtBuscarApellidoMiembro.Text = apellidoMiembro;

            PrestamoBO prestamoBO = new PrestamoBO();

            usuarioBO = new UsuarioBO();
            usuario usu = usuarioBO.consultarMiembroID(idMiembro);

            Session["usuarioConsulta"] = usu;
            nombreUsuario.Visible = true;
            btnEliminarMiembro.Visible = true;
            gvMiembros.Visible = false;

            nombreUsuario.InnerText = "Usuario seleccionado: " + usu.nombres + " " + usu.apellidos;
            BindingList<string> motivos = prestamoBO.consultarCalificacionPrestamo(usu.idUsuario, copiaActual.idCopiaEjemplar);

            if (motivos.Count == 0)
            {
                // Usuario calificado para el préstamo
                lblMotivos.Text = "Este usuario está calificado para el préstamo.";
                Session["calificado"] = 1;
            }
            else
            {
                // Mostrar motivos de no calificación
                lblMotivos.Text = "Motivos (penalizaciones activas) por los cuales el usuario no está calificado:";
                foreach (var motivo in motivos)
                {
                    lblMotivos.Text += "<br/>- " + motivo;
                }
                Session["calificado"] = 0;
            }

            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
        }

        protected void btnRegistrar_Click(object sender, EventArgs e)
        {
            prestamoBO = new PrestamoBO();
            usuario usuario = (usuario)Session["usuarioConsulta"];
            copiaEjemplar copiaEjemplar = (copiaEjemplar)Session["copiaEjemplar"];

            if (usuario != null && copiaEjemplar != null)
            {
                try
                {
                    int? esCalificado = (int?)Session["calificado"];
                    if (esCalificado != null && esCalificado > 0)
                    {
                        prestamoBO.registrarPrestamo(usuario.idUsuario, copiaEjemplar.idCopiaEjemplar);
                        lblMensajeRegistro.Text = "Préstamo registrado correctamente";
                        btnNuevoPrestamo.Visible = true;
                        btnPrestamo.Visible = false;
                    }
                    else
                    {
                        string scriptCalif = "alert('Este usuario no está calificado para el préstamo, por favor, indíquele los motivos');";
                        ClientScript.RegisterStartupScript(this.GetType(), "", scriptCalif, true);
                    }
                }
                catch (Exception ex)
                {
                    lblMensajeRegistro.Text = "Error: " + ex.Message;
                }
            }
            else
            {
                string scriptAutores = "alert('Todos los campos deben estar llenos para el registro');";
                ClientScript.RegisterStartupScript(this.GetType(), "", scriptAutores, true);
            }
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            //se libera algun session aqui?
            Session["calificado"] = null;
            Session["usuarioConsulta"] = null;
            Session["copiaEjemplar"] = null;

            Response.Redirect("ListadoPrestamos.aspx");
        }

        protected void btnEliminarMiembro_Click(object sender, EventArgs e)
        {
            if (nombreUsuario.Visible)
            {

                nombreUsuario.InnerText = "";
                nombreUsuario.Visible = false;
                txtBuscarNombreMiembro.Text = "";
                txtBuscarApellidoMiembro.Text = "";
                Session["usuarioConsulta"] = null;
                btnEliminarMiembro.Visible = false;

                lblMotivos.Text = "";
            }
        }

        protected void btnNuevoPrestamo_Click(object sender, EventArgs e)
        {
            nombreUsuario.InnerText = "";
            nombreUsuario.Visible = false;

            lblMensajeRegistro.Text = "";
            Session["calificado"] = null;
            Session["usuarioConsulta"] = null;
            Session["copiaEjemplar"] = null;
            Response.Redirect(Request.RawUrl); // Redirige a la misma página
        }
    }
}
