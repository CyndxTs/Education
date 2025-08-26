using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Security.Policy;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftBibliotecaBO.ServicioWeb;
using SoftBibliotecaPublicacionBO;

namespace SoftBibliotecaWA
{
    public partial class AgregarPublicacionSegundaParte : System.Web.UI.Page
    {
        private LibroBO libroBO;
        private TesisBO tesisBO;
        private EditorialBO editorialBO;
        private TemaBO temaBO;
        protected void Page_Load(object sender, EventArgs e)
        {
            if(!IsPostBack)
            {
                BindingList<tema> temas = new BindingList<tema>();
                Session["listaTemas"] = temas;
            }
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            // Obtener el valor del HiddenField que indica si se seleccionó "libro" o "tesis"
            string tipoSeleccionado = hfTipoSeleccionado.Value;

            string titulo = (string)Session["tituloPub"];
            int[] listaAutores = (int[])Session["listarIdAutores"];
            string fechaPublicacoin = (string)Session["fechaPublicacion"];
            string descripcion = (string)Session["descripcionPublicacion"];
            string url = (string)Session["urlPub"];
            string numCopias = (string)Session["numCopiasPub"];
            int hayFormatoFisico = (int)Session["HayFF"];
            int hayFormatoVirtual = (int)Session["HayFV"];
            byte[] portada = (byte[])Session["portadaPublicacion"];
            int numCopiasPub = 0;
            if (url == "") url = null;
            if (numCopias != "0") numCopiasPub = int.Parse(numCopias);
            int idBibliotecaAsociada = (int)Session["idBiblioteca"];
            if (tipoSeleccionado == "libro")
            {
                // Lógica para manejar el libro
                // Puedes acceder a los valores de los controles relacionados con el libro aquí
                string tipoLibro = ddlTipoLibro.SelectedValue;
                if (tipoLibro == "") 
                {
                    string script = "alert('Debe ingresar el tipo de libro');";
                    ClientScript.RegisterStartupScript(this.GetType(), "Tesis", script, true);
                    return;
                }

                //OPCIONALES (NULL)
                string genero = txtGenero.Text;
                string materia = txtMateria.Text;
                int volumen = int.Parse(txtVolumen.Text);
                int tomo = int.Parse(txtTomo.Text);
                int ISBN = int.Parse(txtISBN.Text);               

                int idEditorial = (int)Session["Editorial"];


                libroBO = new LibroBO();
                libroBO.nuevaPublicacionLibro(titulo, fechaPublicacoin, descripcion, numCopiasPub, hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada, ISBN, idEditorial, tipoLibro, volumen, materia, genero, tomo, null, listaAutores);
            }
            else if (tipoSeleccionado == "tesis")
            {

                // Lógica para manejar la tesis
                // Puedes acceder a los valores de los controles relacionados con la tesis aquí
                string tipoTesis = ddlTipoTesis.SelectedValue;
                if(tipoTesis == "")
                {
                    string script = "alert('Debe seleccionar el tipo de tesis');";
                    ClientScript.RegisterStartupScript(this.GetType(), "Tesis", script, true);
                    return;
                }

                int[] idsTemas = new int[0];
                BindingList<tema> temas = (BindingList<tema>)Session["listaTemas"];
                if (temas != null && temas.Count > 0) 
                {
                    idsTemas = temas.Select(a => a.idTema).ToArray();
                }
                else
                {
                    string script = "alert('Debe ingresar al menos un tema');";
                    ClientScript.RegisterStartupScript(this.GetType(), "Tesis", script, true);
                    return;
                }
                
                tesisBO = new TesisBO();
                tesisBO.nuevaPublicacionTesis(titulo, fechaPublicacoin, descripcion, numCopiasPub, hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada, tipoTesis, null, listaAutores, idsTemas);
            }
            else
            {
                // Si no se ha seleccionado ninguna opción (libro o tesis)
                // Mostrar un mensaje o tomar alguna acción
                string scriptAutores = "alert('Especifique un tipo de publicacion por favor');";
                ClientScript.RegisterStartupScript(this.GetType(), "Publicacion", scriptAutores, true);
                return;
            }
        }


        protected void btnAnterior_Click(object sender, EventArgs e)
        {
            Response.Redirect("AgregarPublicacion.aspx");
        }

        protected void btnRegistrarTema_Click(object sender, EventArgs e)
        {
            string nombreTema = txtNuevoTema.Text;
            temaBO = new TemaBO();
            int error = temaBO.insertarTema(nombreTema);
        }

        protected void btnBuscarTema_Click(object sender, EventArgs e)
        {
            string temaBuscar = txtBuscarTema.Text;
            temaBO = new TemaBO();
            BindingList<tema> temasEncontrados = temaBO.buscarTemaPorNombre(temaBuscar,5);
            ModalTemas_gvTemas.DataSource = temasEncontrados;
            ModalTemas_gvTemas.DataBind();            
        }

        protected void modalSeleccionarTema_Click(object sender, EventArgs e)
        {
            LinkButton btn = (LinkButton)sender;
            string commandArgument = btn.CommandArgument;

            string[] arguments = commandArgument.Split(',');

            // Obtener cada valor
            int idTema = Int32.Parse(arguments[0]);
            string titulo = arguments[1];

            tema temaAgregado = new tema();
            temaAgregado.idTema = idTema;
            temaAgregado.titulo = titulo;

            BindingList<tema> temas = (BindingList<tema>)Session["listaTemas"];
            temas.Add(temaAgregado);
            Session["listaTemas"] = temas;

            rptTemas.DataSource = temas;
            rptTemas.DataBind();
            if (!rptTemas.Visible) rptTemas.Visible = true;
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
        }

        protected void btnRegistrarEditorial_Click1(object sender, EventArgs e)
        {
            string nombreEditorial = txtNombreEditorial.Value;
            editorialBO = new EditorialBO();
            int error = editorialBO.insertar(nombreEditorial);
        }

        protected void btnBuscarEditorial_Click(object sender, EventArgs e)
        {
            string temaBuscar = txtBuscarEditorial.Text;
            editorialBO = new EditorialBO();
            BindingList<editorial> editoriales = editorialBO.buscarEditorialPorNombre(temaBuscar);
            gdvBuscarEditorialModal.DataSource = editoriales;
            gdvBuscarEditorialModal.DataBind();
        }

        protected void lbtnSeleccionarEditorialModel_Click(object sender, EventArgs e)
        {
            LinkButton btn = (LinkButton)sender;
            string commandArgument = btn.CommandArgument;

            string[] arguments = commandArgument.Split(',');

            // Obtener cada valor
            int idEditorial = Int32.Parse(arguments[0]);
            string nombreEditorial = arguments[1];

            Session["Editorial"] = idEditorial;
            txtEditorial.Text = nombreEditorial;


            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
        }

        protected void rptTemas_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            if (e.CommandName == "Eliminar")
            {
                int idTema = Convert.ToInt32(e.CommandArgument);

                BindingList<tema> temas = (BindingList<tema>)Session["listaTemas"];
                int cont = temas.Count;
                for (int i = 0; i < cont; i++)
                {
                    if (temas[i].idTema == idTema)
                    {
                        temas.RemoveAt(i);
                    }
                }
                Session["listaTemas"] = temas;
                rptTemas.DataSource = temas;
                rptTemas.DataBind();
                if (temas.Count == 0) rptTemas.Visible = false;
            }
        }
    }
}