using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftBibliotecaBO.ServicioWeb;
using SoftBibliotecaPublicacionBO;

namespace SoftBibliotecaWA
{
    public partial class GestionPublicacion : System.Web.UI.Page
    {
        private string idPublicacion;
        private string tipoPublicacion;
        private LibroBO libroBO;
        private TesisBO tesisBO;
        private TemaBO temaBO;
        private AutorBO autorBO;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                idPublicacion = Request.QueryString["id"];
                tipoPublicacion = Request.QueryString["tipo"];

                if (!string.IsNullOrEmpty(idPublicacion) && !string.IsNullOrEmpty(tipoPublicacion))
                {
                    if (tipoPublicacion.Equals("libro", StringComparison.OrdinalIgnoreCase))
                    {
                        CargarDetallesLibro(idPublicacion);
                    }
                    else if (tipoPublicacion.Equals("tesis", StringComparison.OrdinalIgnoreCase))
                    {
                        CargarDetallesTesis(idPublicacion);
                    }
                }
                else
                {
                    Response.Redirect("CatalogoBibliotecario.aspx");
                }
            }
            else //debemos recargar esto porque es´información dinámica
            {
                libro librito = (libro)Session["librito"];
                tesis tesis = (tesis)Session["tesis"];
                if (librito != null)
                    cargarDatosEspecificosLibro(librito);
                else
                    cargarDatosEspecificosTesis(tesis);
            }
        }
        private string ObtenerTipoAcceso(bool hayFormatoFisico, bool hayFormatoVirtual, string url)
        {
            bool accesoFisico = hayFormatoFisico;
            bool accesoDigital = hayFormatoVirtual && !string.IsNullOrEmpty(url);

            if (accesoFisico && accesoDigital)
            {
                return "Acceso físico y digital";
            }
            else if (accesoFisico)
            {
                return "Acceso físico";
            }
            else if (accesoDigital)
            {
                return "Acceso digital";
            }
            else
            {
                return "Sin acceso disponible";
            }
        }
        private void cargarDatosGeneralesPublicacion(publicacion publi)
        {
            lblTitulo.Text = publi.titulo;
            lblDescripcion.Text = publi.descripcion;
            lblBiblioteca.Text = publi.bibliotecaAsociada.nombre;
            lblNumCopias.Text = publi.copiasDisponibles.ToString();
            hlURL.NavigateUrl = publi.url;
            if (publi.portada != null)
            {
                string base64String = Convert.ToBase64String(publi.portada);
                imgPortada.ImageUrl = "data:image/jpeg;base64," + base64String;
            }
            else
                imgPortada.ImageUrl = "~/Images/imgLibro.png";
        }
        private void cargarAutoresDePublicacion(BindingList<autor> autores)
        {
            lblAutores.Text = "";
            foreach (autor a in autores)
            {
                lblAutores.Text += "\n" + a.nombreCompleto;
            }
        }
        private void cargarDatosEspecificosLibro(libro librito)
        {
            autorBO = new AutorBO();
            BindingList<autor> autores = autorBO.listarTodos_AutoresDeLibroPorID(librito.idLibro);
            cargarAutoresDePublicacion(autores);

            Label lblTipoLibroLabel = new Label(); //lblTipoLibroLabel.CssClass = "fw-bold"; //para su estilo si tuviera
            Label lblTipoLibro = new Label();
            lblTipoLibroLabel.Text = "Tipo de libro: ";
            lblTipoLibro.Text = librito.tipo.ToString().Equals("") ? " -" : librito.tipo.ToString();
            lblTipoLibroLabel.CssClass = "fw-bold"; // Aplicar negrita

            Label lblISBNLabel = new Label();
            Label lblISBN = new Label();
            lblISBN.Text = librito.ISBN.Equals("") ? " -" : librito.ISBN.ToString();
            lblISBNLabel.Text = "ISBN: ";
            lblISBNLabel.CssClass = "fw-bold"; // Aplicar negrita

            Label lblGeneroLabel = new Label();
            Label lblGenero = new Label();
            lblGenero.Text = librito.genero == null || librito.genero.Equals("") ? " -" : librito.genero;
            lblGeneroLabel.Text = "Género: ";
            lblGeneroLabel.CssClass = "fw-bold"; // Aplicar negrita

            Label lblMateriaLabel = new Label();
            Label lblMateria = new Label();
            lblMateria.Text = librito.materia==null || librito.materia.Equals("")? " -" : librito.materia;
            lblMateriaLabel.Text = "Materia: ";
            lblMateriaLabel.CssClass = "fw-bold"; // Aplicar negrita

            Label lblVolumenLabel = new Label();
            Label lblVolumen = new Label();
            lblVolumen.Text = librito.volumen == 0 ? " -" : librito.volumen.ToString();
            lblVolumenLabel.Text = "Volumen: ";
            lblVolumenLabel.CssClass = "fw-bold"; // Aplicar negrita

            Label lblTomoLabel = new Label();
            Label lblTomo = new Label();
            lblTomo.Text = librito.tomo == 0 ? " -" : librito.tomo.ToString();
            lblTomoLabel.Text = "Tomo: ";
            lblTomoLabel.CssClass = "fw-bold"; // Aplicar negrita

            Label lblEditorialLabel = new Label();
            Label lblEditorial = new Label();
            lblEditorial.Text = librito.editorial.nombre;
            lblEditorialLabel.Text = "Editorial: ";
            lblEditorialLabel.CssClass = "fw-bold"; // Aplicar negrita

            string tipoAcceso = ObtenerTipoAcceso(librito.hayFormatoFisico, librito.hayFormatoVirtual, librito.url);
            lblAcceso.Text = tipoAcceso;
            // Agregar controles al PlaceHolder
            phCamposPersonalizados.Controls.Add(lblTipoLibroLabel);
            phCamposPersonalizados.Controls.Add(lblTipoLibro);
            phCamposPersonalizados.Controls.Add(new LiteralControl("<br />")); // Salto de línea
            phCamposPersonalizados.Controls.Add(lblISBNLabel);
            phCamposPersonalizados.Controls.Add(lblISBN);
            phCamposPersonalizados.Controls.Add(new LiteralControl("<br />")); // Salto de línea
            phCamposPersonalizados.Controls.Add(lblGeneroLabel);
            phCamposPersonalizados.Controls.Add(lblGenero);
            phCamposPersonalizados.Controls.Add(new LiteralControl("<br />")); // Salto de línea
            phCamposPersonalizados.Controls.Add(lblMateriaLabel);
            phCamposPersonalizados.Controls.Add(lblMateria);
            phCamposPersonalizados.Controls.Add(new LiteralControl("<br />")); // Salto de línea
            phCamposPersonalizados.Controls.Add(lblVolumenLabel);
            phCamposPersonalizados.Controls.Add(lblVolumen);
            phCamposPersonalizados.Controls.Add(new LiteralControl("<br />")); // Salto de línea
            phCamposPersonalizados.Controls.Add(lblTomoLabel);
            phCamposPersonalizados.Controls.Add(lblTomo);
            phCamposPersonalizados.Controls.Add(new LiteralControl("<br />")); // Salto de línea
            phCamposPersonalizados.Controls.Add(lblEditorialLabel);
            phCamposPersonalizados.Controls.Add(lblEditorial);
            phCamposPersonalizados.Controls.Add(new LiteralControl("<br />")); // Salto de línea

        }
        private void cargarDatosEspecificosTesis(tesis tesisActual)
        {
            // Inicializar el objeto AutorBO para obtener los autores de la tesis
            temaBO = new TemaBO();
            autorBO = new AutorBO();
            BindingList<autor> autores = autorBO.listarTodos_AutoresDeTesisPorID(tesisActual.idTesis);
            cargarAutoresDePublicacion(autores);

            // Crear etiquetas para el Grado
            Label lblGradoLabel = new Label();
            lblGradoLabel.Text = "Grado: ";
            lblGradoLabel.CssClass = "fw-bold"; // Aplicar negrita
            Label lblGrado = new Label();
            lblGrado.Text = tesisActual.gradoAcademico.ToString(); // Convertir el enum a string

            // Crear etiquetas para los Temas
            Label lblTemasLabel = new Label();
            lblTemasLabel.Text = "Temas: ";
            lblTemasLabel.CssClass = "fw-bold"; // Aplicar negrita
            Label lblTemas = new Label();

            // Obtener la lista de temas asociados a la tesis
            BindingList<tema> temas = temaBO.listarTodos_TemasDeTesisPorID(tesisActual.idTesis);

            // Concatenar los nombres de los temas en una sola cadena, separados por comas
            string temasTexto = string.Join(", ", temas.Select(t => t.titulo));
            lblTemas.Text = temasTexto.Equals(", ") || temasTexto == null ? " -" : temasTexto;

            string tipoAcceso = ObtenerTipoAcceso(tesisActual.hayFormatoFisico, tesisActual.hayFormatoVirtual, tesisActual.url);
            lblAcceso.Text = tipoAcceso;

            phCamposPersonalizados.Controls.Add(lblGradoLabel);
            phCamposPersonalizados.Controls.Add(lblGrado);
            phCamposPersonalizados.Controls.Add(new LiteralControl("<br />")); // Salto de línea

            phCamposPersonalizados.Controls.Add(lblTemasLabel);
            phCamposPersonalizados.Controls.Add(lblTemas);
            phCamposPersonalizados.Controls.Add(new LiteralControl("<br />")); // Salto de línea
        }

        private void CargarDetallesLibro(string id)
        {
            //ARREGLAR 
            if (string.IsNullOrEmpty(id))
            {
                lblTitulo.Text = "ID no válido.";
                return;
            }

            if (int.TryParse(id, out int libroId))
            {
                libroBO = new LibroBO();
                libro librito = libroBO.verDetallePublicacionLibro(libroId);
                Session["librito"] = librito; //para no perderlo dinámicamente cuando se abra un modal
                if (librito != null)
                {
                    cargarDatosGeneralesPublicacion(librito);
                    cargarDatosEspecificosLibro(librito);
                }
                else
                {
                    lblTitulo.Text = "Libro no encontrado.";
                }
            }
        }

        private void CargarDetallesTesis(string id)
        {
            //ARREGLAR

            if (string.IsNullOrEmpty(id))
            {
                lblTitulo.Text = "ID no válido.";
                return;
            }

            if (int.TryParse(id, out int tesisId))
            {
                tesisBO = new TesisBO();
                tesis tesis = tesisBO.verDetallePublicacioTesis(tesisId);
                Session["tesis"] = tesis;
                if (tesis != null)
                {
                    cargarDatosGeneralesPublicacion(tesis); //para no repetir código
                    cargarDatosEspecificosTesis(tesis);
                }
                else
                {
                    lblTitulo.Text = "Tesis no encontrada.";
                }
            }

        }

        protected void btnModificar_Click(object sender, EventArgs e)
        {
            if ( (libro) Session["librito"] != null)
                Response.Redirect("ModificarPublicacionLibro.aspx");
            else
            {
                if((tesis)Session["tesis"] != null)
                {
                    Response.Redirect("ModificarPublicacionTesis.aspx");
                }
                else
                {
                    Response.Redirect("CatalogoBibliotecario.aspx");
                }
            }
        }


        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Session["Publicaciones"] = null;
            Session["copiasExistentes"] = null;
            Session["librito"] = null;
            Session["tesis"] = null;
            Response.Redirect("CatalogoBibliotecario.aspx");
        }
    }
}
