using System;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftBibliotecaBO.ServicioWeb;
using SoftBibliotecaPublicacionBO;
using System.ComponentModel;
using System.Linq;
using System.IO;
using SoftBibliotecaPolitica;
using SoftBibliotecaPoliticaReservaBO;
using SoftBibliotecaUniversidadBO;
using SoftBibliotecaUsuarioBO;
using System.Collections.Generic;
using System.EnterpriseServices.Internal;

namespace SoftBibliotecaWA
{
    public partial class DetallePublicacion : System.Web.UI.Page
    {
        private int ID;
        private LibroBO libroBO;
        private TesisBO tesisBO;
        private TemaBO temaBO;
        private AutorBO autorBO;
        private BibliotecaBO bibliotecaBO;
        private string tipoPublicacion;
        private string idPublicacion;
        private usuario Usuario;
        private UniversidadBO universidadBO;
        private UsuarioBO usuarioBO;
        private PoliticaBO politicaBO;
        private PoliticaReservaBO politicareservabo;
        private ReservaBO reservaBO;
        private CopiaEjemplarBO copiaEjemplarBO;
        private politicaReserva politicaReserva;

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
                        CargarLibrosRecomendados(idPublicacion);
                    }
                    else if (tipoPublicacion.Equals("tesis", StringComparison.OrdinalIgnoreCase))
                    {
                        CargarDetallesTesis(idPublicacion);
                        CargarTesisRecomendadas(idPublicacion);
                    }

                }
            }
            else
            { //debemos recargar esto porque es´información dinámica
                libro librito = (libro)Session["librito"];
                tesis tesis = (tesis)Session["tesis"];
                if (librito != null)
                    cargarDatosEspecificosLibro(librito);
                else
                    cargarDatosEspecificosTesis(tesis);
            }
        }
        private void CargarLibrosRecomendados(string idPublicacionActual)
        {
            if (string.IsNullOrEmpty(idPublicacionActual))
            {
                string scriptUsuarioExistente = "alert('Error: Libro no existe');";
                ClientScript.RegisterStartupScript(this.GetType(), "Usuario Existente", scriptUsuarioExistente, true);
                return;
            }

            if (int.TryParse(idPublicacionActual, out int publiId))
            {
                libroBO = new LibroBO();

                // Lista para almacenar libros recomendados sin duplicados
                List<libro> librosRecomendados = new List<libro>();

                // Obtener la lista de autores desde la sesión
                BindingList<autor> autores = Session["autores"] as BindingList<autor>;

                if (autores != null && autores.Count > 0)
                {
                    // HashSet para rastrear los IDs de publicaciones ya agregadas y evitar duplicados
                    HashSet<int> addedPublicacionIDs = new HashSet<int>();

                    foreach (autor a in autores)
                    {
                        // Obtener libros por autor
                        BindingList<libro> librosPorAutor = libroBO.listarTodos_LibrosDeAutorPorID(a.idAutor);

                        foreach (libro l in librosPorAutor)
                        {
                            // Verificar si el libro ya ha sido agregado
                            if (l.idPublicacion != publiId && !addedPublicacionIDs.Contains(l.idPublicacion))
                            {
                                librosRecomendados.Add(l);
                                addedPublicacionIDs.Add(l.idPublicacion);

                                // Limitar a 6 libros recomendados
                                if (librosRecomendados.Count >= 6)
                                {
                                    break;
                                }
                            }
                        }

                        // Salir del bucle si ya se han agregado 6 libros
                        if (librosRecomendados.Count >= 6)
                        {
                            break;
                        }
                    }
                }

                // Preparar una lista para el Repeater con las URLs de portada
                var librosRecomendadosVista = librosRecomendados.Select(l => new
                {
                    l.idPublicacion,
                    l.titulo,
                    l.descripcion,
                    PortadaUrl = (l.portada != null && l.portada.Length > 0) ?
                                 "data:image/png;base64," + Convert.ToBase64String(l.portada) :
                                 "~/Images/imgLibro.png",
                    TipoPublicacion = "libro"
                }).ToList();

                // Enlazar los libros recomendados al Repeater
                rptLibrosRecomendados.DataSource = librosRecomendadosVista;
                rptLibrosRecomendados.DataBind();

            }

        }
        private void CargarTesisRecomendadas(string idPublicacionActual)
        {
            if (string.IsNullOrEmpty(idPublicacionActual))
            {
                string scriptUsuarioExistente = "alert('Error: Tesis no existe');";
                ClientScript.RegisterStartupScript(this.GetType(), "Usuario Existente", scriptUsuarioExistente, true);
                return;
            }

            if (int.TryParse(idPublicacionActual, out int tesisId))
            {
                // Inicializar LibroBO
                tesisBO = new TesisBO();

                // Lista para almacenar libros recomendados sin duplicados
                List<tesis> tesisRecomendadas = new List<tesis>();

                // Obtener la lista de autores desde la sesión
                BindingList<autor> autores = Session["autores"] as BindingList<autor>;

                if (autores != null && autores.Count > 0)
                {
                    // HashSet para rastrear los IDs de publicaciones ya agregadas y evitar duplicados
                    HashSet<int> addedPublicacionIDs = new HashSet<int>();

                    foreach (autor a in autores)
                    {
                        // Obtener libros por autor
                        BindingList<tesis> tesisPorAutor = tesisBO.listarTodos_TesisDeAutorPorID(a.idAutor);

                        foreach (tesis t in tesisPorAutor)
                        {
                            // Verificar si el libro ya ha sido agregado
                            if (t.idPublicacion != tesisId && !addedPublicacionIDs.Contains(t.idPublicacion))
                            {
                                tesisRecomendadas.Add(t);
                                addedPublicacionIDs.Add(t.idPublicacion);

                                // Limitar a 6 libros recomendados
                                if (tesisPorAutor.Count >= 6)
                                {
                                    break;
                                }
                            }
                        }

                        // Salir del bucle si ya se han agregado 6 libros
                        if (tesisRecomendadas.Count >= 6)
                        {
                            break;
                        }
                    }
                }

                // Preparar una lista para el Repeater con las URLs de portada
                var tesisRecomendadasVista = tesisRecomendadas.Select(l => new
                {
                    l.idPublicacion,
                    l.titulo,
                    l.descripcion,
                    PortadaUrl = (l.portada != null && l.portada.Length > 0) ?
                                 "data:image/png;base64," + Convert.ToBase64String(l.portada) :
                                 "~/Images/imgTesis.png",
                    TipoPublicacion = "tesis"
                }).ToList();

                // Enlazar los libros recomendados al Repeater
                rptLibrosRecomendados.DataSource = tesisRecomendadasVista;
                rptLibrosRecomendados.DataBind();

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

        private void cargarDatosGeneralesPublicacion(publicacion publi, char tipo)
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
                if (tipo == 't')
                imgPortada.ImageUrl = "~/Images/imgTesis.png";
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
            Session["autores"] = autores;
            cargarAutoresDePublicacion(autores);

            Label lblTipoLibroLabel = new Label(); //lblTipoLibroLabel.CssClass = "fw-bold"; //para su estilo si tuviera
            Label lblTipoLibro = new Label();
            lblTipoLibroLabel.Text = "Tipo de libro: ";
            lblTipoLibro.Text = librito.tipo.ToString().Equals("") ? " - " : librito.tipo.ToString();
            lblTipoLibroLabel.CssClass = "fw-bold"; // Aplicar negrita

            Label lblGeneroLabel = new Label();
            Label lblGenero = new Label();
            lblGenero.Text = librito.genero == null || librito.genero.Equals("") ? " - " : librito.genero;
            lblGeneroLabel.Text = "Género: ";
            lblGeneroLabel.CssClass = "fw-bold"; // Aplicar negrita

            Label lblMateriaLabel = new Label();
            Label lblMateria = new Label();
            lblMateria.Text = librito.materia == null || librito.materia.Equals("") ? " - " : librito.materia;
            lblMateriaLabel.Text = "Materia: ";
            lblMateriaLabel.CssClass = "fw-bold"; // Aplicar negrita

            Label lblVolumenLabel = new Label();
            Label lblVolumen = new Label();
            lblVolumen.Text = librito.volumen == null || librito.volumen == 0 ? " - " : librito.volumen.ToString();
            lblVolumenLabel.Text = "Volumen: ";
            lblVolumenLabel.CssClass = "fw-bold"; // Aplicar negrita

            Label lblTomoLabel = new Label();
            Label lblTomo = new Label();
            lblTomo.Text = librito.tomo == null || librito.tomo == 0 ? " -" : librito.tomo.ToString();
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
            Session["autores"] = autores;
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


            if (string.IsNullOrEmpty(id))
            {
                string scriptUsuarioExistente = "alert('Error: Libro no existe');";
                ClientScript.RegisterStartupScript(this.GetType(), "Usuario Existente", scriptUsuarioExistente, true);
                return;
            }

            if (int.TryParse(id, out int libroId))
            {
                libroBO = new LibroBO();
                libro librito = libroBO.verDetallePublicacionLibro(libroId);
                Session["librito"] = librito; //para no perderlo dinámicamente cuando se abra un modal
                if (librito != null)
                {
                    cargarDatosGeneralesPublicacion(librito, 'l');
                    cargarDatosEspecificosLibro(librito);
                }
                else
                {
                    lblTitulo.Text = "Libro no encontrado.";
                }
            }
            else
            {
                string scriptUsuarioExistente = "alert('Error: Libro no encontrado');";
                ClientScript.RegisterStartupScript(this.GetType(), "Usuario Existente", scriptUsuarioExistente, true);
                return;
            }

        }

        private void CargarDetallesTesis(string id)
        {
            if (string.IsNullOrEmpty(id))
            {
                string scriptUsuarioExistente = "alert('Error: Tesis no existe');";
                ClientScript.RegisterStartupScript(this.GetType(), "Usuario Existente", scriptUsuarioExistente, true);
                return;
            }

            if (int.TryParse(id, out int tesisId))
            {
                tesisBO = new TesisBO();
                autorBO = new AutorBO();

                tesis tesis = tesisBO.verDetallePublicacioTesis(tesisId);
                Session["tesis"] = tesis;
                if (tesis != null)
                {
                    cargarDatosGeneralesPublicacion(tesis, 't'); //para no repetir código
                    cargarDatosEspecificosTesis(tesis);
                }
                else
                {
                    string scriptUsuarioExistente = "alert('Error: Tesis no encontrada');";
                    ClientScript.RegisterStartupScript(this.GetType(), "Usuario Existente", scriptUsuarioExistente, true);
                    return;
                }
            }
            else
            {
                string scriptUsuarioExistente = "alert('Error: Tesis no encontrada');";
                ClientScript.RegisterStartupScript(this.GetType(), "Usuario Existente", scriptUsuarioExistente, true);
                return;
            }

        }

        protected void btnSolicitarReserva_Click(object sender, EventArgs e)
        {
            idPublicacion = Request.QueryString["id"];
            tipoPublicacion = Request.QueryString["tipo"];
            usuario usu = (usuario)Session["Usuario"];
            int idUsuario = usu.idUsuario;

            lblTituloModal.Text = lblTitulo.Text;
            lblDescripcionModal.Text = lblDescripcion.Text;
            lblAutoresModal.Text = lblAutores.Text.Substring(1).Replace("\n", ", ");
            lblBibliotecaModal.Text = lblBiblioteca.Text;
            lblNumCopiasModal.Text = lblNumCopias.Text;

            reservaBO = new ReservaBO();
            reserva reservaHipotetica = reservaBO.solicitarReserva(idUsuario, int.Parse(idPublicacion), tipoPublicacion); //&////////////////////7
            if (reservaHipotetica.ejemplarAsociado != null) //basta con que cualquiera de sus campos sea nulo
            {
                Session["reservaHipotetica"] = reservaHipotetica;
                string[] partes = reservaHipotetica.str_instanteRecojo.Split(' ');
                string fecha = partes[0].Replace("/", "/");
                string hora = partes[1]; // Hora exacta
                lblCodigoCopia.Text = reservaHipotetica.ejemplarAsociado.idCopiaEjemplar.ToString();
                lblFechaRecojo.Text = fecha;
                lblHoraMaxRecojo.Text = hora;
                ScriptManager.RegisterStartupScript(this, this.GetType(), "showModal",
                "var modal = new bootstrap.Modal(document.getElementById('reservaModal')); modal.show();", true);
            }
            else
            {
                Session["str_instanteRecojoMax"] = null;
                string script = @"var noCalificaModal = new bootstrap.Modal(document.getElementById('noCalificaModal'));
                    noCalificaModal.show();";
                ScriptManager.RegisterStartupScript(this, this.GetType(), "showNoCalificaModal", script, true);
            }

        }
        protected void btnConfirmarReserva_Click(object sender, EventArgs e)
        {

            idPublicacion = Request.QueryString["id"];
            tipoPublicacion = Request.QueryString["tipo"];
            usuario usu = (usuario)Session["Usuario"];
            int idUsuario = usu.idUsuario;
            reserva reservaHipotetica = (reserva)Session["reservaHipotetica"];
            reservaBO = new ReservaBO();
            if (reservaHipotetica != null)
            {
                int idCopiaEjemplarReserva = reservaHipotetica.ejemplarAsociado.idCopiaEjemplar;
                string fechaHoraMaxRecojo = reservaHipotetica.str_instanteRecojo;
                int verifica = reservaBO.confirmarReserva(idUsuario, idCopiaEjemplarReserva, fechaHoraMaxRecojo); //////////////
                if (verifica > 0)
                {
                    string scriptUsuarioExistente = "alert('Reserva confirmada con éxito.');";
                    ClientScript.RegisterStartupScript(this.GetType(), "Usuario Existente", scriptUsuarioExistente, true);
                }
                else
                {
                    string scriptUsuarioExistente = "alert('No se pudo confirmar la reserva');";
                    ClientScript.RegisterStartupScript(this.GetType(), "Usuario Existente", scriptUsuarioExistente, true);
                }
            }
            else
            {
                string scriptUsuarioExistente = "alert('No se pudo confirmar la reserva');";
                ClientScript.RegisterStartupScript(this.GetType(), "Usuario Existente", scriptUsuarioExistente, true);
            }

        }

        protected void btnRegresarCatalogo_Click(object sender, EventArgs e)
        {            
            Session["Publicaciones"] = null;
            Session["str_instanteRecojoMax"] = null;
            Session["autores"] = null;
            Session["librito"] = null;
            Session["tesis"] = null;
            Response.Redirect("CatalogoMiembro.aspx");
        }
    }
}