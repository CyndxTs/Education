using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data.SqlClient;
using System.Diagnostics;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftBibliotecaBO.ServicioWeb;
using SoftBibliotecaPublicacionBO;

//PARCIALMENTE SOLUCIONADO FALTA BUSQUEDA AVANZADA

namespace SoftBibliotecaWA
{
    public partial class CatalogoMiembro : System.Web.UI.Page
    {
        private bool estaEnBusquedaAvnazada = false;
        private int PageSize = 6;
        private int CurrentPage
        {
            get { return (int)(ViewState["CurrentPage"] ?? 0); }
            set { ViewState["CurrentPage"] = value; }
        }
        private PublicacionBO publicacionBO;
        private TesisBO tesisBO;
        private BibliotecaBO bibliotecaBO;
        private AutorBO autorBO;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                Session["Publicaciones"] = null; //quitar si funciona busqueda avanzada
                CargarPublicaciones(0, null, null, 1, 1, 0, 0);
                CargarBibliotecas();
                CargarBibliotecasPopUp();
                btnEliminarAutorModal.Visible = false;
            }

        }


        private void BindBooksToRepeater(bool reiniciarPaginacion)
        {
            // Reiniciar la paginación si se solicita
            if (reiniciarPaginacion)
            {
                CurrentPage = 0;
            }
            BindingList<publicacion> publicacionesBDList = (BindingList<publicacion>)Session["Publicaciones"];

            if (publicacionesBDList != null && publicacionesBDList.Count > 0)
            {
                List<publicacion> publicaciones = publicacionesBDList
                    .Skip(CurrentPage * PageSize)
                    .Take(PageSize)
                    .ToList();

                lblCantidadPublicaciones.Text = "Se han encontrado " + publicacionesBDList.Count.ToString() + " publicaciones.";

                rptPublicaciones.DataSource = publicaciones;
                rptPublicaciones.DataBind();

                btnPrevious.Visible = CurrentPage > 0;
                btnNext.Visible = (CurrentPage + 1) * PageSize < publicacionesBDList.Count;

            }
            else
            {
                rptPublicaciones.DataSource = null;
                rptPublicaciones.DataBind();
                lblCantidadPublicaciones.Text = "No se han encontrado publicaciones.";
                btnPrevious.Visible = false;
                btnNext.Visible = false;
            }
        }



        protected void btnPrevious_Click(object sender, EventArgs e)
        {
            CurrentPage--;
            BindBooksToRepeater(false);
        }

        protected void btnNext_Click(object sender, EventArgs e)
        {
            CurrentPage++;
            BindBooksToRepeater(false);
        }


        private void CargarPublicaciones(int tipoBusqueda, string titulo, string descripcion, int hayFormatoFisico, int hayFormatoVirtual, int idBiblioteca, int autor)
        {
            // Siempre reiniciamos la paginación al cargar nuevas publicaciones
            CurrentPage = 0;  // Reinicia la página a la primera

            bool reiniciarPaginacion = false;
            publicacionBO = new PublicacionBO();

            // Verificar si los datos están en la sesión
            if (Session["Publicaciones"] == null)
            {
                BindingList<publicacion> publicaciones = null;

                if (!this.estaEnBusquedaAvnazada)
                {
                    // Búsqueda normal
                    publicaciones = publicacionBO.verCatalogo(tipoBusqueda, idBiblioteca, null, titulo, 50);
                }
                else
                {
                    publicaciones = publicacionBO.busquedaAvanzada(tipoBusqueda, titulo, descripcion, hayFormatoFisico, hayFormatoVirtual, idBiblioteca, autor);
                    this.estaEnBusquedaAvnazada = false;
                }
                Session["Publicaciones"] = publicaciones;
            }
            else
            {
                reiniciarPaginacion = true;
            }

            // Enlazar las publicaciones al Repeater (con reinicio de paginación si es necesario)
            BindBooksToRepeater(reiniciarPaginacion);
        }



        protected void btnBuscar_Click(object sender, EventArgs e)
        {

            string titulo = txtBuscar.Text.Trim();
            string tipoPub = ddlTipoPublicacion.SelectedValue;
            string strIdBib = ddlBibliotecas.SelectedValue;
            int idBiblioteca = int.Parse(strIdBib);

            rptPublicaciones.DataSource = null;
            rptPublicaciones.DataBind();

            int tipoBusqueda = 0;
            if (tipoPub == "Libro")
            {
                tipoBusqueda = 1;
            }
            else if (tipoPub == "Tesis")
            {
                tipoBusqueda = -1;
            }
            this.estaEnBusquedaAvnazada = false;
            Session["Publicaciones"] = null;
            CargarPublicaciones(tipoBusqueda, titulo, null, 1, 1, idBiblioteca, 0);
        }

        private void CargarBibliotecasPopUp()
        {
            if (Session["Bibliotecas"] != null)
            {
                ddlBibliotecaModel.DataSource = (BindingList<biblioteca>)Session["Bibliotecas"];
                ddlBibliotecaModel.DataTextField = "nombre";
                ddlBibliotecaModel.DataValueField = "idBiblioteca";
                ddlBibliotecaModel.DataBind();
                ddlBibliotecaModel.Items.Insert(0, new ListItem("Seleccione una biblioteca", "0"));
            }
        }


        private void CargarBibliotecas()
        {
            if (Session["Bibliotecas"] != null)
            {
                ddlBibliotecas.DataSource = (BindingList<biblioteca>)Session["Bibliotecas"];
            }
            else
            {
                bibliotecaBO = new BibliotecaBO();
                BindingList<biblioteca> bibliotecas = bibliotecaBO.listarTodos(-1, -1);
                Session["Bibliotecas"] = bibliotecas;
                ddlBibliotecas.DataSource = bibliotecas;
            }
            ddlBibliotecas.DataTextField = "nombre";
            ddlBibliotecas.DataValueField = "idBiblioteca";
            ddlBibliotecas.DataBind();
            ddlBibliotecas.Items.Insert(0, new ListItem("Seleccione una biblioteca", "0"));

        }


        protected void rptPublicaciones_ItemDataBound(object sender, RepeaterItemEventArgs e)
        {
            if (e.Item.ItemType == ListItemType.Item || e.Item.ItemType == ListItemType.AlternatingItem)
            {
                var publicacion = (publicacion)e.Item.DataItem;
                Image imgPortada = (Image)e.Item.FindControl("imgPortada");
                if (publicacion.portada != null)
                {
                    string base64String = Convert.ToBase64String(publicacion.portada);
                    imgPortada.ImageUrl = "data:image/jpeg;base64," + base64String;
                }
                else
                    if (publicacion is libro)
                {
                    imgPortada.ImageUrl = "~/Images/imgLibro.png";
                }
                else if (publicacion is tesis)
                {
                    imgPortada.ImageUrl = "~/Images/imgTesis.png";
                }
            }
        }

        protected void btnBuscarModal_Click(object sender, EventArgs e)
        {
            string titulo = txtTitulo.Text;
            string descripcion = txtDescripcion.Text.Trim();

            int idAutor = 0;
            if (Session["BA_autorSeleccionado"] != null)
            {
                autor autor = (autor)Session["BA_autorSeleccionado"];
                idAutor = autor.idAutor;
            }

            int tieneFormatoDigital = -1, tieneFormatoFisico = -1;
            if (ckcBoxHayFD.Checked == true) tieneFormatoDigital = 1;
            if (ckcBoxHayFF.Checked == true) tieneFormatoFisico = 1;


            string strIdBib = ddlBibliotecaModel.SelectedValue;
            int idBiblioteca = int.Parse(strIdBib); //Si no escogíó da 0   

            int tipoBusqueda = 0;
            if (tipoLibro.Checked) tipoBusqueda++;
            if (tipoTesis.Checked) tipoBusqueda--;

            rptPublicaciones.DataSource = null;
            rptPublicaciones.DataBind();

            this.estaEnBusquedaAvnazada = true;
            Session["Publicaciones"] = null;
            CargarPublicaciones(tipoBusqueda, titulo, descripcion, tieneFormatoFisico, tieneFormatoDigital, idBiblioteca, idAutor);

            ddlBibliotecas.SelectedValue = default;
            ddlTipoPublicacion.SelectedValue = default;
            txtBuscar.Text = default;
            txtTitulo.Text = default;
            txtDescripcion.Text = default;
            ckcBoxHayFF.Checked = default;
            ckcBoxHayFD.Checked = default;
            tipoTesis.Checked = default;
            tipoLibro.Checked = default;
            ddlBibliotecaModel.SelectedValue = default;
            //liberamos para próximas búsquedas (?)
            
        }

        protected void btnEliminarAutorModal_Click(object sender, EventArgs e)
        {
            if (btnEliminarAutorModal.Visible)
            {
                Session["BA_autorSeleccionado"] = null;
                txtBuscarAutor.Text = "";
                if (gvAutores.Visible) gvAutores.Visible = false;
                btnEliminarAutorModal.Visible = false;
            }
        }

        protected void btnBuscarAutor_Click(object sender, EventArgs e)
        {
            string autorBuscar = txtBuscarAutor.Text;
            autorBO = new AutorBO();
            BindingList<autor> autoresEncontrados = autorBO.buscarAutorPorNombre(autorBuscar, 5);
            gvAutores.DataSource = autoresEncontrados;
            gvAutores.DataBind();
            if (!gvAutores.Visible) gvAutores.Visible = true;
        }

        protected void btnSeleccionarAutorModa_Click1(object sender, EventArgs e)
        {
            LinkButton btn = (LinkButton)sender;
            string commandArgument = btn.CommandArgument;

            string[] arguments = commandArgument.Split(',');
            int idAutor = Int32.Parse(arguments[0]);
            string nombreAutor = arguments[1];

            autor autorBA = new autor();
            autorBA.nombreCompleto = nombreAutor;
            autorBA.idAutor = idAutor;

            Session["BA_autorSeleccionado"] = autorBA;
            gvAutores.Visible = false;
            txtBuscarAutor.Text = nombreAutor;
            btnEliminarAutorModal.Visible = true;
        }
    }
}