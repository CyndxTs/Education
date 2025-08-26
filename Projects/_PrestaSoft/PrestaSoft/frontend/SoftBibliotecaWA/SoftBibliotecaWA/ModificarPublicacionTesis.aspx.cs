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
    public partial class ModificarPublicacionTesis : System.Web.UI.Page
    {
        private BibliotecaBO bibliotecaBO;
        private AutorBO autorBO;
        private EditorialBO editorialBO;
        private LibroBO libroBO;
        private TemaBO temaBO;
        private TesisBO tesisBO;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {   
                // Inicializa los campos como invisibles por defecto
                divNumCopias.Visible = false;
                divCopias.Visible = false;
                divURL.Visible = false;
                //tblAutoresAgregados.Visible = false;
                //BindingList<autor> autores = new BindingList<autor>();
                //Session["listaAutores"] = autores;
                CargarBibliotecas();
                CargarCopiasExistentes();
                CargarDatosTesis();


            }
            else
            {
                CargarCopiasExistentes();
            }
        }
        private void CargarCopiasExistentes()
        {
            tesis _tesis = (tesis)Session["tesis"];
            if (_tesis != null)
            {
                // Cargar las copias del libro y almacenarlas en la sesión
                CopiaEjemplarBO copiaBO = new CopiaEjemplarBO();
                List<copiaEjemplar> copiasExistentes = copiaBO.ObtenerCopiasEjemplaresDeTesis(_tesis.idTesis).ToList();

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
        private void CargarAutoresAsociadosDeTesis(int idPublicacion)
        {
            AutorBO autorBO = new AutorBO();
            BindingList<autor> autoresObtenidos = autorBO.listarTodos_AutoresDeTesisPorID(idPublicacion);
            BindingList<autor> autores = new BindingList<autor>();
            foreach (var autor in autoresObtenidos)
            {
                
                autores.Add(autor);
            }
            Session["listaAutores"] = autores;
            rptAutores.DataSource = autores;
            rptAutores.DataBind();
        }
        private void CargarTemasAsociadosDeTesis(int idPublicacion)
        {
            TemaBO temaBO = new TemaBO();
            BindingList<tema> temasObtenidos = temaBO.listarTodos_TemasDeTesisPorID(idPublicacion);
            BindingList<tema> temas = new BindingList<tema>();
            foreach (var tema in temasObtenidos)
            {
                temas.Add(tema);
            }
            Session["listaTemas"] = temas;
            rptTemas.DataSource = temas;
            rptTemas.DataBind();
        }
        private void CargarDatosTesis()
        {
            tesis _tesis = (tesis)Session["tesis"]; //recuperado de gestión de publicación
            if (_tesis != null)
            {
                // Llenar los campos con los datos del libro
                txtTitulo.Value = _tesis.titulo;
                txtFecha.Value = _tesis.fechaPublicacion.ToString("yyyy-MM-dd"); //!!!
                

                txtDescripcion.Value = _tesis.descripcion;
                ddlBibliotecas.SelectedValue = _tesis.bibliotecaAsociada.idBiblioteca.ToString();
                previsualizarImagen(_tesis.portada);
                // Mostrar u ocultar controles según el formato

                // Manejar la visibilidad y carga de copias y URL
                if (_tesis.hayFormatoFisico == true && _tesis.hayFormatoVirtual == false)
                {
                    divCopias.Visible = true;
                    divURL.Visible = false;
                }
                else if (_tesis.hayFormatoFisico == false && _tesis.hayFormatoVirtual == true)
                {
                    divCopias.Visible = false;
                    divURL.Visible = true;
                    txtURL.Value = _tesis.url;
                }
                else if (_tesis.hayFormatoFisico == true && _tesis.hayFormatoVirtual == true)
                {
                    divCopias.Visible = true;
                    divURL.Visible = true;
                    txtURL.Value = _tesis.url;

                    /*
                    CopiaEjemplarBO copiaBO = new CopiaEjemplarBO();
                    BindingList<copiaEjemplar> copias = copiaBO.ObtenerCopiasEjemplaresDeLibro(librito.idLibro);
                    gvCopias.DataSource = copias;
                    gvCopias.DataBind();
                    */
                }


                // Llenar otros campos específicos del libro
                ddlGradoTesis.SelectedValue = _tesis.gradoAcademico.ToString();
                

                // Cargar autores asociados
                CargarAutoresAsociadosDeTesis(_tesis.idTesis);
                //cargar temas
                CargarTemasAsociadosDeTesis(_tesis.idTesis);
            }
            else
            {
                // Manejar el caso donde la tesis no se encuentra
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

                string rutaImagenPorDefecto = Server.MapPath("~/Images/imgTesis.png");
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
            int idTesis = ((tesis)Session["tesis"] ).idTesis;
            Response.Redirect("GestionPublicacion.aspx?id=" + idTesis + "&tipo=" + "tesis"); // Cambia "PaginaAnterior.aspx" según corresponda
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
                tesis _tesis = (tesis)Session["tesis"];
                if (_tesis == null)
                {
                    Response.Write("<script>alert('La publicación no existe.');</script>");
                    Response.Redirect("GestionPublicacion.aspx");
                    return;
                }
                int idTesis = _tesis.idTesis;

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
                
                string gradoAcademico = ddlGradoTesis.SelectedValue;

                // Obtener los autores seleccionados
                BindingList<autor> listaAutores = (BindingList<autor>)Session["listaAutores"];
                if (listaAutores == null || listaAutores.Count == 0)
                {
                    Response.Write("<script>alert('Debe seleccionar al menos un autor.');</script>");
                    return;
                }
                int[] idsAutores = listaAutores.Select(a => a.idAutor).ToArray();

                // Obtener los temas seleccionados
                BindingList<tema> listaTemas = (BindingList<tema>)Session["listaTemas"];
                if (listaTemas == null || listaTemas.Count == 0)
                {
                    Response.Write("<script>alert('Debe seleccionar al menos un tema.');</script>");
                    return;
                }
                int[] idsTemas = listaTemas.Select(t => t.idTema).ToArray();


                // Obtener la imagen de la portada
                byte[] portada = (byte[])Session["portadaPublicacion"];
                if (portada == null)
                {
                    // Si no se ha cargado una nueva imagen, usar la existente
                    portada = _tesis.portada;
                }

                // Obtener datos de formato (físico, digital)
                bool hayFormatoFisico = _tesis.hayFormatoFisico;
                bool hayFormatoVirtual = _tesis.hayFormatoVirtual;
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
                int copiasDisponibles = _tesis.copiasDisponibles + cantcopisinsertar;



                // Llamar a la función modificarPublicacionLibro
                tesisBO = new TesisBO();
                int resultado = tesisBO.modificarPublicacionTesis(
                    idTesis, titulo, fechaPublicacion, descripcion,
                    copiasDisponibles, hayFormatoFisico ? 1 : 0,
                    hayFormatoVirtual ? 1 : 0, url, portada,
                    idBibliotecaAsociada, gradoAcademico,
                    idsCopiasEjemplares, estadosCopiasEjemplares,
                    idsAutores, idsTemas,  cantcopisinsertar
                );

                if (resultado >= 0)
                {
                    // Modificación exitosa
                    Session["copiasExistentes"] = null;
                    Response.Write("<script>alert('La publicación ha sido modificada exitosamente.');</script>");
                    Response.Redirect("GestionPublicacion.aspx?id=" + idTesis + "&tipo=" + "tesis");
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
            int idTesis =( (tesis)Session["tesis"]).idTesis;
            Response.Redirect("GestionPublicacion.aspx?id=" + idTesis + "&tipo=" + "tesis");
        }

    //TEMAS -----------------------------------------------------------


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
            BindingList<tema> temasEncontrados = temaBO.buscarTemaPorNombre(temaBuscar, 5);
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


        protected void rptTemas_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            if (e.CommandName == "Eliminar")
            {
                int idTema = Convert.ToInt32(e.CommandArgument);

                BindingList<tema> temas = (BindingList<tema>)Session["listaTemas"];
                if (temas != null)
                {
                    for (int i = 0; i < temas.Count; i++)
                    {
                        if (temas[i].idTema == idTema)
                        {
                            temas.RemoveAt(i);
                            break;
                        }
                    }

                    Session["listaTemas"] = temas;
                    rptTemas.DataSource = temas;
                    rptTemas.DataBind();
                }
            }
        }

    }
}