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
    public partial class AgregarPublicacion : System.Web.UI.Page
    {
        private BibliotecaBO bibliotecaBO;
        private AutorBO autorBO;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // Inicializa los campos como invisibles por defecto
                divNumCopias.Visible = false;
                divURL.Visible = false;
                //tblAutoresAgregados.Visible = false;
                BindingList<autor> autores = new BindingList<autor>();
                Session["listaAutores"] = autores;
                CargarBibliotecas();
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



        protected void ddlTipoLibro_SelectedIndexChanged(object sender, EventArgs e)
        {
            // Obtiene el valor seleccionado del DropDownList
            string tipoSeleccionado = ddlTipoLibro.SelectedValue;

            // Lógica para mostrar/ocultar campos según el tipo de libro seleccionado
            switch (tipoSeleccionado)
            {
                case "Físico":
                    divNumCopias.Visible = true;  // Mostrar Número de Copias
                    divURL.Visible = false;       // Ocultar URL
                    break;

                case "Digital":
                    divNumCopias.Visible = false; // Ocultar Número de Copias
                    divURL.Visible = true;        // Mostrar URL
                    break;

                case "Ambos":
                    divNumCopias.Visible = true;  // Mostrar Número de Copias
                    divURL.Visible = true;        // Mostrar URL
                    break;

                default:
                    divNumCopias.Visible = false; // Ocultar ambos por defecto
                    divURL.Visible = false;
                    break;
            }
        }

        protected void btnVolver_Click(object sender, EventArgs e)
        {
            // Acción para el botón "Volver"
            // Ejemplo: Redirigir a la página anterior o cerrar un pop-up
            Response.Redirect("CatalogoBibliotecario.aspx"); // Cambia "PaginaAnterior.aspx" según corresponda
        }

        protected void btnSiguiente_Click(object sender, EventArgs e)
        {
            

            int[] idsAutores = new int[0];
            BindingList<autor> autores = (BindingList<autor>)Session["listaAutores"];
            if(autores != null && autores.Count > 0)
            {
                idsAutores = autores.Select(a => a.idAutor).ToArray();
            }
            else
            {
                string scriptAutores = "alert('El campo de autores es obligatorio');";
                ClientScript.RegisterStartupScript(this.GetType(), "Autores", scriptAutores, true);
                return;
            }


            autorBO = new AutorBO();

            string titulo = txtTitulo.Value;
            string fechaPublicacion = txtFecha.Value;
            string descripcion = txtDescripcion.Value;
            string tipoLibro = ddlTipoLibro.SelectedValue;
            int idBiblioteca;
            try
            {
                idBiblioteca = int.Parse(ddlBibliotecas.SelectedValue);
            }
            catch
            {
                string scriptAutores = "alert('Debe ingresar un biblioteca');";
                ClientScript.RegisterStartupScript(this.GetType(), "Biblioteca", scriptAutores, true);
                return;
            }
            

            string numCopias = "0";
            string url = "";
            if (tipoLibro == "Físico")
            {
                Session["HayFF"] = 1;
                Session["HayFV"] = 0;
                if (!string.IsNullOrEmpty(Number1.Value)) 
                {
                    numCopias = Number1.Value;
                }
                else
                {
                    string scriptAutores = "alert('Si la publicacion tiene disponibilidad físico debe ingresar el número de copias');";
                    ClientScript.RegisterStartupScript(this.GetType(), "Autores", scriptAutores, true);
                    return;
                }                                
            }
            else if (tipoLibro == "Digital")
            {
                Session["HayFF"] = 0;
                Session["HayFV"] = 1;
                url = txtURL.Value;
            }
            else if (tipoLibro == "Ambos")
            {
                Session["HayFF"] = 1;
                Session["HayFV"] = 1;
                if (!string.IsNullOrEmpty(Number1.Value))
                {
                    numCopias = Number1.Value;
                }
                else
                {
                    string scriptAutores = "alert('Si la publicacion tiene disponibilidad físico debe ingresar el número de copias');";
                    ClientScript.RegisterStartupScript(this.GetType(), "Autores", scriptAutores, true);
                    return;
                }
                numCopias = Number1.Value;
            }
            else
            {
                string scriptAutores = "alert('Seleccione la disponibilidad de la publicacion');";
                ClientScript.RegisterStartupScript(this.GetType(), "Autores", scriptAutores, true);
                return;
            }

            if(titulo == "" || descripcion == "" || fechaPublicacion == "")
            {
                string scriptAutores = "alert('Porfavor, rellene todos los campos');";
                ClientScript.RegisterStartupScript(this.GetType(), "Autores", scriptAutores, true);
                return;
            }

            if(descripcion.Length > 275) 
            {
                string scriptAutores = "alert('La descripción no puede superar los 275 caracteres');";
                ClientScript.RegisterStartupScript(this.GetType(), "Autores", scriptAutores, true);
                return;
            }

            Session["tituloPub"] = titulo;           
            Session["listarIdAutores"] = idsAutores;
            Session["fechaPublicacion"] = fechaPublicacion;
            Session["descripcionPublicacion"] = descripcion;
            Session["idBiblioteca"] = idBiblioteca;
            Session["urlPub"] = url;
            Session["numCopiasPub"] = numCopias;          
            Response.Redirect("AgregarPublicacionSegundaParte.aspx");
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
                for(int i = 0; i < autores.Count; i++)
                {
                    if (autores[i].idAutor == idAutor)
                    {
                        autores.RemoveAt(i);
                    }
                }
                Session["listaAutores"] = autores;
                rptAutores.DataSource = autores;
                rptAutores.DataBind();
                if (autores.Count == 0) rptAutores.Visible = false;
            }
        }
    }


}