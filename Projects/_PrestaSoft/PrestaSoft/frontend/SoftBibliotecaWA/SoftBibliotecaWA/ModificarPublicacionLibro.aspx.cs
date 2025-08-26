using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftBibliotecaBO.ServicioWeb;
using SoftBibliotecaPublicacionBO;

namespace SoftBibliotecaWA
{
    public partial class ModificarPublicacionLibro : System.Web.UI.Page
    {
        private BibliotecaBO bibliotecaBO;
        private AutorBO autorBO;
        private EditorialBO editorialBO;
        private LibroBO libroBO;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {   
                // Inicializa los campos como invisibles por defecto
                divNumCopias.Visible = false;
                divURL.Visible = false;
                //tblAutoresAgregados.Visible = false;
                //BindingList<autor> autores = new BindingList<autor>();
                //Session["listaAutores"] = autores;
                CargarBibliotecas();
                CargarCopiasExistentes();   
                CargarDatosLibro();
                
            }
        }
        private void CargarCopiasExistentes()
        {
            libro librito = (libro)Session["librito"];
            if (librito != null)
            {
                // Cargar las copias del libro y almacenarlas en la sesión
                CopiaEjemplarBO copiaBO = new CopiaEjemplarBO();
                List<copiaEjemplar> copiasExistentes = copiaBO.ObtenerCopiasEjemplaresDeLibro(librito.idLibro).ToList();

                Session["copiasExistentes"] = copiasExistentes;

                // Inicializar la lista de nuevas copias
                Session["nuevasCopias"] = new List<int>();

                // Enlazar los GridViews
                BindGridViewCopias();
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
        private void CargarAutoresAsociadosDeLibro(int idPublicacion)
        {
            AutorBO autorBO = new AutorBO();
            BindingList<autor> autoresObtenidos = autorBO.listarTodos_AutoresDeLibroPorID(idPublicacion);
            BindingList<autor> autores = new BindingList<autor>();
            foreach (var autor in autoresObtenidos)
            {
                autores.Add(autor);
            }
            Session["listaAutores"] = autores;
            rptAutores.DataSource = autores;
            rptAutores.DataBind();
        }
        private void CargarDatosLibro()
        {
            libro librito = (libro)Session["librito"]; //recuperado de gestión de publicación
            if (librito != null)
            {
                // Llenar los campos con los datos del libro
                txtTitulo.Value = librito.titulo;
                txtFecha.Value = librito.fechaPublicacion.ToString("yyyy-MM-dd"); //!!!
                txtISBN.Text = librito.ISBN.ToString();

                txtDescripcion.Value = librito.descripcion;
                ddlBibliotecas.SelectedValue = librito.bibliotecaAsociada.idBiblioteca.ToString();
                previsualizarImagen(librito.portada);
                // Mostrar u ocultar controles según el formato
                
                if (librito.hayFormatoFisico == true && librito.hayFormatoVirtual == false)
                {
                    divCopias.Visible = true;
                    divURL.Visible = false;
                }
                else if (librito.hayFormatoFisico == false && librito.hayFormatoVirtual == true)
                {
                    divCopias.Visible = false;
                    divURL.Visible = true;
                    txtURL.Value = librito.url;
                }
                else if (librito.hayFormatoFisico == true && librito.hayFormatoVirtual == true)
                {
                    divCopias.Visible = true;
                    divURL.Visible = true;
                    txtURL.Value = librito.url;
                    /*
                    // Cargar las copias del libro y enlazarlas al GridView
                    CopiaEjemplarBO copiaBO = new CopiaEjemplarBO();
                    BindingList<copiaEjemplar> copias = copiaBO.ObtenerCopiasEjemplaresDeLibro(librito.idLibro);
                    gvCopias.DataSource = copias;
                    gvCopias.DataBind();
                    */
                }

                // Llenar otros campos específicos del libro
                ddlTipoLibro.SelectedValue = librito.tipo.ToString();
                txtGenero.Text = librito.genero;
                txtMateria.Text = librito.materia;
                txtVolumen.Text = librito.volumen == 0 ? "" : librito.volumen.ToString();
                txtTomo.Text = librito.tomo == 0 ? "" : librito.tomo.ToString();
                txtEditorial.Text = librito.editorial.nombre;
                Session["Editorial"] = librito.editorial.idEditorial;
                // Cargar autores asociados
                CargarAutoresAsociadosDeLibro(librito.idLibro);
            }
            else
            {
                // Manejar el caso donde el libro no se encuentra
                Response.Write("<script>alert('La publicación no existe.');</script>");
                Response.Redirect("CatalogoBibliotecario.aspx");
            }

        }
        private void previsualizarImagen(byte[] portada)
        {
            string base64String;
            if (portada != null)
            {
                base64String = Convert.ToBase64String(portada);
                
            }
            else
            {

                string rutaImagenPorDefecto = Server.MapPath("~/Images/imgLibro.png");
                byte[] imagenPorDefecto = System.IO.File.ReadAllBytes(rutaImagenPorDefecto);
                base64String = Convert.ToBase64String(imagenPorDefecto);
            }
            imgPrevisualizar.ImageUrl = "data:image;base64," + base64String;
            imgPrevisualizar.Visible = true;
        }

        protected void lbEditarCopia_Click(object sender, EventArgs e)
        {
            int idCopia = int.Parse(((LinkButton)sender).CommandArgument);
            // Lógica para editar la copia
        }


        protected void btnVolver_Click(object sender, EventArgs e)
        {
            // Acción para el botón "Volver"
            // Ejemplo: Redirigir a la página anterior o cerrar un pop-up
            int idLibro = ((libro)Session["librito"]).idLibro;
            Response.Redirect("GestionPublicacion.aspx?id=" + idLibro + "&tipo=" + "libro"); // Cambia "PaginaAnterior.aspx" según corresponda
        }

        protected void btnSubirImagen_Click(object sender, EventArgs e)
        {
            if (fuImagen.HasFile)
            {
                try
                {
                    // Validar el tipo de archivo
                    string extension = System.IO.Path.GetExtension(fuImagen.FileName).ToLower();
                    string[] extensionesPermitidas = { ".jpg", ".jpeg", ".png", ".gif" };

                    if (!extensionesPermitidas.Contains(extension))
                    {
                        lblImagenCargada.Text = "Por favor, selecciona una imagen válida (.jpg, .jpeg, .png, .gif).";
                        lblImagenCargada.CssClass = "text-danger";
                        lblImagenCargada.Visible = true;
                        return;
                    }

                    // Convertir la imagen a un arreglo de bytes
                    byte[] imagenEnBytes;

                    using (System.IO.BinaryReader reader = new System.IO.BinaryReader(fuImagen.PostedFile.InputStream))
                    {
                        imagenEnBytes = reader.ReadBytes(fuImagen.PostedFile.ContentLength);
                    }

                    // Mostrar mensaje de éxito
                    lblImagenCargada.Text = "¡Imagen cargada exitosamente y convertida a bytes!";
                    lblImagenCargada.CssClass = "text-success";
                    lblImagenCargada.Visible = true;

                    // Previsualizar la imagen cargada desde los bytes (opcional)
                    string base64String = Convert.ToBase64String(imagenEnBytes);
                    imgPrevisualizar.ImageUrl = "data:image;base64," + base64String;
                    imgPrevisualizar.Visible = true;

                    Session["portadaPublicacion"] = imagenEnBytes; //guarda en variable de sesion
                }
                catch (Exception ex)
                {
                    lblImagenCargada.Text = "Ocurrió un error al subir la imagen: " + ex.Message;
                    lblImagenCargada.CssClass = "text-danger";
                    lblImagenCargada.Visible = true;
                }
            }
            else
            {
                lblImagenCargada.Text = "Por favor, selecciona una imagen para cargar.";
                lblImagenCargada.CssClass = "text-danger";
                lblImagenCargada.Visible = true;
            }
        }

        protected void btnRegistrarAutor_Click(object sender, EventArgs e)
        {
            string nombreAutor = txtNombreAutor.Value;

            if (!string.IsNullOrEmpty(nombreAutor))
            {
                this.autorBO = new AutorBO();
                autorBO.insertarAutor(nombreAutor);
            }
        }

        protected void btnBuscarAutorModal_Click(object sender, EventArgs e)
        {
            string autorBuscar = txtBuscarAutorModal.Text;
            autorBO = new AutorBO();    
            BindingList<autor> autoresEncontrados = autorBO.buscarAutorPorNombre(autorBuscar,5);
            gdvAutorModal.DataSource = autoresEncontrados;
            gdvAutorModal.DataBind();
        }

        protected void lbtnSeleccionarAutorModal_Click(object sender, EventArgs e)
        {
            LinkButton btn = (LinkButton)sender;
            string commandArgument = btn.CommandArgument;

            string[] arguments = commandArgument.Split(',');

            // Obtener cada valor
            int idAutor = Int32.Parse(arguments[0]);
            string nombreAutor = arguments[1];

            autor autorAgregado = new autor();
            autorAgregado.idAutor = idAutor;
            autorAgregado.nombreCompleto = nombreAutor;

            BindingList<autor> autores = (BindingList<autor>)Session["listaAutores"];
            autores.Add(autorAgregado); 

            Session["listaAutores"] = autores;
            rptAutores.DataSource = autores;
            rptAutores.DataBind();
            if(!rptAutores.Visible)rptAutores.Visible = true;
            ScriptManager.RegisterStartupScript(this, GetType(), "", "__doPostBack('','');", true);
        }

        protected void rptAutores_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            if(e.CommandName == "Eliminar")
            {
                int idAutor = Convert.ToInt32(e.CommandArgument);

                BindingList<autor> autores = (BindingList<autor>)Session["listaAutores"];
                if (autores != null)
                {
                    // Encontrar y eliminar el autor de la lista
                    
                    for (int i = 0; i < autores.Count; i++)
                    {
                        if (autores[i].idAutor == idAutor)
                        {
                            autores.RemoveAt(i);
                            break;
                        }
                    }

                    // Actualizar la sesión y el Repeater
                    Session["listaAutores"] = autores;
                    rptAutores.DataSource = autores;
                    rptAutores.DataBind();
                    if (autores.Count == 0) rptAutores.Visible = false;
                }
            }
        }
        //AQUÍ EMPIEZA LO DE AGREGAR PUBLICACIÓN SEGUNDA PARTE, FILTRAR !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        protected string MapearEstadoEjemplar(string estado)
        {
            switch (estado)
            {
                case "OPTIMO":
                    return "Óptimo";
                case "DANIADO":
                    return "Dañado";
                case "PERDIDO":
                    return "Perdido";
                case "NODISPONIBLE":
                    return "No disponible";
                default:
                    return "Desconocido";
            }
        }

        protected void gvCopias_RowEditing(object sender, GridViewEditEventArgs e)
        {
            gvCopias.EditIndex = e.NewEditIndex;
            BindGridViewCopias();
        }
        protected void gvCopias_RowUpdating(object sender, GridViewUpdateEventArgs e)
        {
            // Obtener la lista de copias existentes desde la sesión
            List<copiaEjemplar> copiasExistentes = Session["copiasExistentes"] as List<copiaEjemplar>;

            // Obtener el índice de la fila que se está actualizando
            int index = e.RowIndex;

            // Obtener el ID de la copia
            int idCopia = Convert.ToInt32(gvCopias.DataKeys[index].Value);

            // Obtener el nuevo estado desde el DropDownList
            GridViewRow row = gvCopias.Rows[index];
            DropDownList ddlEstado = row.FindControl("ddlEstado") as DropDownList;
            string nuevoEstado = ddlEstado.SelectedValue;

            // Actualizar el estado en la lista de copias existentes
            copiaEjemplar copia = copiasExistentes.FirstOrDefault(c => c.idCopiaEjemplar == idCopia);
            if (copia != null)
            {
                switch (nuevoEstado){
                    case "OPTIMO":
                        copia.estado = estadoEjemplar.OPTIMO; break;
                    case "NODISPONIBLE":
                        copia.estado = estadoEjemplar.NODISPONIBLE; break;
                    case "PERDIDO":
                        copia.estado = estadoEjemplar.PERDIDO; break;
                    case "DANIADO":
                        copia.estado = estadoEjemplar.DANIADO; break;
                }
                 
            }

            // Actualizar la sesión y el GridView
            Session["copiasExistentes"] = copiasExistentes;
            gvCopias.EditIndex = -1;
            BindGridViewCopias();
        }

        protected void gvCopias_RowCancelingEdit(object sender, GridViewCancelEditEventArgs e)
        {
            gvCopias.EditIndex = -1;
            BindGridViewCopias();
        }
        private void BindGridViewCopias()
        {
            List<copiaEjemplar> copiasExistentes = (List < copiaEjemplar > )Session["copiasExistentes"]  ;
            if (copiasExistentes != null)
            {
                gvCopias.DataSource = copiasExistentes;
                gvCopias.DataBind();
            }

            List<int> nuevasCopias = Session["nuevasCopias"] as List<int>;
            if (nuevasCopias != null)
            {
                gvCopiasNuevas.DataSource = nuevasCopias;
                gvCopiasNuevas.DataBind();
            }
        }
        protected void btnAgregarCopia_Click(object sender, EventArgs e)
        {
            List<int> nuevasCopias = Session["nuevasCopias"] as List<int>;
            if (nuevasCopias == null)
            {
                nuevasCopias = new List<int>();
            }

            // Agregar una nueva copia (podemos usar un contador para llevar el control)
            nuevasCopias.Add(1); // El valor no importa, solo necesitamos el conteo

            Session["nuevasCopias"] = nuevasCopias;
            BindGridViewCopias();
        }
        protected void gvCopiasNuevas_RowDeleting(object sender, GridViewDeleteEventArgs e)
        {
            int index = e.RowIndex;
            List<int> nuevasCopias = Session["nuevasCopias"] as List<int>;

            if (nuevasCopias != null && index >= 0 && index < nuevasCopias.Count)
            {
                nuevasCopias.RemoveAt(index);
                Session["nuevasCopias"] = nuevasCopias;
                BindGridViewCopias();
            }
        }
    
        protected void btnModificar_Click(object sender, EventArgs e)
        {
            try
            {
                // Obtener el ID del libro desde la sesión o query string
                libro librito = (libro)Session["librito"];
                if (librito == null)
                {
                    Response.Write("<script>alert('La publicación no existe.');</script>");
                    Response.Redirect("CatalogoBibliotecario.aspx");
                    return;
                }
                int idLibro = librito.idLibro;

                // Capturar los datos del formulario
                string titulo = txtTitulo.Value.Trim();
                string fechaPublicacionInput = txtFecha.Value;
                DateTime fechaPublicacionDate;
                string fechaPublicacion;
                if (DateTime.TryParse(fechaPublicacionInput, out fechaPublicacionDate))
                {
                    // Formatear la fecha al formato esperado por modificarPublicacionLibro
                     fechaPublicacion = fechaPublicacionDate.ToString("yyyy-MM-dd");
                }
                else
                {
                    // Manejar el error si la fecha no es válida
                    Response.Write("<script>alert('La fecha de publicación no es válida.');</script>");
                    return;
                }
                string descripcion = txtDescripcion.Value;
                int idBibliotecaAsociada = int.Parse(ddlBibliotecas.SelectedValue);

                // Opcionales (pueden ser nulos)
                string genero = txtGenero.Text.Trim();
                string materia = txtMateria.Text.Trim();
                int volumen = string.IsNullOrEmpty(txtVolumen.Text) ? 0 : int.Parse(txtVolumen.Text);
                int tomo = string.IsNullOrEmpty(txtTomo.Text) ? 0 : int.Parse(txtTomo.Text);
                string tipoLibro = ddlTipoLibro.SelectedValue;

                int ISBN = 0;
                if (!string.IsNullOrEmpty(txtISBN.Text))
                {
                    if (!int.TryParse(txtISBN.Text.Trim(), out ISBN))
                    {
                        Response.Write("<script>alert('El ISBN debe ser un número entero válido.');</script>");
                        return;
                    }
                }

                // Obtener el ID de la editorial
                if (Session["Editorial"] == null)
                {
                    // Manejar el caso donde no se ha seleccionado una editorial
                    Response.Write("<script>alert('Debe seleccionar una editorial.');</script>");
                    return;
                }
                int idEditorial = (int)Session["Editorial"];

                // Obtener los autores seleccionados
                BindingList<autor> listaAutores = (BindingList<autor>)Session["listaAutores"];
                if (listaAutores == null || listaAutores.Count == 0)
                {
                    Response.Write("<script>alert('Debe seleccionar al menos un autor.');</script>");
                    return;
                }
                int[] idsAutores = listaAutores.Select(a => a.idAutor).ToArray();

                // Obtener la imagen de la portada
                byte[] portada = (byte[])Session["portadaPublicacion"];
                if (portada == null)
                {
                    // Si no se ha cargado una nueva imagen, usar la existente
                    portada = librito.portada;
                }

                // Obtener datos de formato (físico, digital)
                bool hayFormatoFisico = librito.hayFormatoFisico;
                bool hayFormatoVirtual = librito.hayFormatoVirtual;
                string url = null;
                

                // Procesar copias existentes
                List<copiaEjemplar> copiasExistentes = Session["copiasExistentes"] as List<copiaEjemplar>;
                int[] idsCopiasEjemplares = copiasExistentes.Select(c => c.idCopiaEjemplar).ToArray();
                string[] estadosCopiasEjemplares = new string[copiasExistentes.Count];
                for(int i=0; i< copiasExistentes.Count;i++ )
                {
                    estadosCopiasEjemplares[i] = copiasExistentes[i].estado.ToString();
                }

                //fechaPublicacion = librito.fechaPublicacion.ToString();
                // Obtener el número de nuevas copias a insertar
                List<int> nuevasCopias = Session["nuevasCopias"] as List<int>;
                int cantcopisinsertar = nuevasCopias != null ? nuevasCopias.Count : 0;
                int copiasDisponibles = librito.copiasDisponibles + cantcopisinsertar;
                if(tomo<0  || volumen<0){
                    Response.Write("<script>alert('Ingrese números válidos a tomo o volumen');</script>");
                    return;
                }
                if (tomo == 0) tomo = 0;
                if (volumen == 0) volumen = 0;

                // Llamar a la función modificarPublicacionLibro
                libroBO = new LibroBO();
                int resultado = libroBO.modificarPublicacionLibro(
                    idLibro, titulo, fechaPublicacion, descripcion,
                    copiasDisponibles, hayFormatoFisico ? 1 : 0,
                    hayFormatoVirtual ? 1 : 0, url, portada,
                    idBibliotecaAsociada, ISBN, idEditorial, tipoLibro,
                    volumen, materia, genero, tomo, idsCopiasEjemplares, estadosCopiasEjemplares,
                    idsAutores, cantcopisinsertar
                );

                if (resultado > 0)
                {
                    // Modificación exitosa
                    Response.Write("<script>alert('La publicación ha sido modificada exitosamente.');</script>");
                    Response.Redirect("GestionPublicacion.aspx?id=" + idLibro + "&tipo=" + "libro");
                }
                else
                {
                    // Manejar errores
                    Response.Write("<script>alert('Ocurrió un error al modificar la publicación. Código de error: " + resultado + "');</script>");
                }
            }
            catch (Exception ex)
            {
                // Manejar excepciones
                Response.Write("<script>alert('Error: " + ex.Message + "');</script>");
            }
        }



        protected void btnAnterior_Click(object sender, EventArgs e)
        {
            Response.Redirect("CatalogoBibliotecario.aspx");
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
    }
}