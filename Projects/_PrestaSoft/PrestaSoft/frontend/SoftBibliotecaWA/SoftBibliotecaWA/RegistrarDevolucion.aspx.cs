using System;
using System.Collections.Generic;
using System.Data.SqlTypes;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftBibliotecaBO.ServicioWeb;
using SoftBibliotecaPrestamosBO;
using SoftBibliotecaPublicacionBO;
using SoftBibliotecaUsuarioBO;

namespace SoftBibliotecaWA
{
    public partial class RegistrarDevolucion : System.Web.UI.Page
    {
        private UsuarioBO usuarioBO;
        private CopiaEjemplarBO copiaEjemplarBO;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // Asegurarse de que los campos estén vacíos al cargar la página
                nombre.Text = string.Empty;
                formOpciones.Visible = false; // El formulario desplegable permanece oculto inicialmente
            }
        }

        protected void btnConfirmarDevolucion_Click(object sender, EventArgs e)
        {
            string hola = opciones.SelectedValue;
            if (int.Parse(hola) == 2) //si marco mal estado
            {
                Session["booleano"] = 1;
            }
            else
            {
                Session["booleano"] = 0;
            }


            PrestamoBO prestamoBO = new PrestamoBO();

            int idC = (int)Session["idCopiaEjemplarDevolucion"];
            int idP = (int)Session["idPrestamoDevolucion"];
            int b = (int)Session["booleano"];
            int res = prestamoBO.confirmarDevolucion(idP, idC, b);
            if (res > 0)
            {
                Session["copiaEjemplar"] = null;
                Session["idPrestamoDevolucion"] = null;
                Session["booleano"] = null;
                lblIdPublicacion.Visible = false;
                lblTituloPublicacion.Visible = false;
                imgPortada.Visible = false;
                formOpciones.Visible = false;
                nombre.Text = "";

                string scriptError = "alert('Préstamo devuelto correctamente');";
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorEliminacion", scriptError, true);
            }
            else
            {
                string scriptError = "alert('Error en devolución.');";
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorEliminacion", scriptError, true);
            }

        }

        protected void btnConsultar_Click(object sender, EventArgs e)
        {
            //ARREGLAR
            copiaEjemplarBO = new CopiaEjemplarBO();

            // Leer el texto ingresado en el input
            string codigoCopia = txtCodigoCopia.Text;

            // Validar si el usuario ingresó algo
            if (!string.IsNullOrEmpty(codigoCopia))
            {
                copiaEjemplarBO = new CopiaEjemplarBO();
                PrestamoBO prestamoBO = new PrestamoBO();
                prestamo prestamoDeLaCopia = prestamoBO.consultarPrestamoQueSePrestoCopiaEjemplar(int.Parse(codigoCopia));
                if (prestamoDeLaCopia != null)
                {
                    copiaEjemplar copia = copiaEjemplarBO.obtenerPorId(int.Parse(codigoCopia));

                    Session["copiaEjemplar"] = copia;
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

                        lblIdPublicacion.Visible = true;
                        lblTituloPublicacion.Visible = true;
                        imgPortada.Visible = true;
                    }
                    else if (copia.publicacionAsociada is tesis)
                    {
                        lblIdPublicacion.Text = "Código de la tesis: " + copia.publicacionAsociada.idPublicacion;
                        tesis tesis = (tesis)copia.publicacionAsociada;
                        Session["idBiblioteca"] = tesis.bibliotecaAsociada.idBiblioteca;
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

                        lblIdPublicacion.Visible = true;
                        lblTituloPublicacion.Visible = true;
                        imgPortada.Visible = true;
                    }

                    //prestamo prestamoInstanciadazo = prestamoBO.obtenerPorId(prestamoDeLaCopia.idPrestamo);
                    nombre.Text = prestamoDeLaCopia.usuarioAsociado.nombres + " " + prestamoDeLaCopia.usuarioAsociado.apellidos; // Resultado NO simulado
                    Session["usuarioDeLaDevolucion"] = prestamoDeLaCopia.usuarioAsociado;
                    Session["idCopiaEjemplarDevolucion"] = prestamoDeLaCopia.ejemplarAsociado.idCopiaEjemplar;
                    Session["idPrestamoDevolucion"] = prestamoDeLaCopia.idPrestamo;

                    /*
                    string hola = opciones.SelectedValue;
                    if (int.Parse(hola) == 2) //si marco mal estado
                    {
                        Session["booleano"] = 1;
                    }
                    else
                    {
                        Session["booleano"] = 0;
                    }
                    */
                    formOpciones.Visible = true;
                }
                else
                {
                    string scriptError = "alert('La copia no tiene un préstamo en curso');";
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorEliminacion", scriptError, true);
                }
            }

        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Session["copiaEjemplar"] = null;
            Session["idPrestamoDevolucion"] = null;
            Session["booleano"] = null;

            lblIdPublicacion.Visible = false;
            lblTituloPublicacion.Visible = false;
            imgPortada.Visible = false;
            formOpciones.Visible = false;
            nombre.Text = "";

            Response.Redirect("BibliotecarioPrestamos.aspx");
        }

    }
}