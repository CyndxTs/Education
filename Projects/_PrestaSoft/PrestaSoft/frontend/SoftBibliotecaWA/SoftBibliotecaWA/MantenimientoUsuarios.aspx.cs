using SoftBibliotecaBO.ServicioWeb;
using SoftBibliotecaUsuarioBO;
using SoftBibliotecaUniversidadBO;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Runtime.Remoting.Channels;
using System.Configuration;

namespace SoftBibliotecaWA
{
    public partial class MantenimientoUsuarios : System.Web.UI.Page
    {
        private UsuarioBO usuarioBO = new UsuarioBO();
        private UniversidadBO universidadBO = new UniversidadBO();
        private const int LIMITE_USUARIOS = 50;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarUsuarios();
                CargarUniversidades();
            }
        }

        private void CargarUsuarios()
        {
            string nombreBusqueda = ViewState["NombreBusqueda"] != null ? ViewState["NombreBusqueda"].ToString() : string.Empty;
            string apellidoBusqueda = ViewState["ApellidoBusqueda"] != null ? ViewState["ApellidoBusqueda"].ToString() : string.Empty;
            string usernameBusqueda = ViewState["UsernameBusqueda"] != null ? ViewState["UsernameBusqueda"].ToString() : string.Empty;

            // Obtener la lista completa de usuarios hasta el límite definido
            BindingList<usuario> usuarios = usuarioBO.listarTodos(nombreBusqueda, apellidoBusqueda, null, usernameBusqueda, null, LIMITE_USUARIOS);

            // Filtrar los usuarios excluyendo a los ADMINISTRADOR
            List<usuario> usuariosFiltrados = usuarios
                .Where(u => u.tipoUsuario != tipoUsuario.ADMINISTRADOR)
                .ToList();

            gvUsuarios.DataSource = usuariosFiltrados;
            gvUsuarios.DataBind();

            // Actualizar el Label con la cantidad de usuarios mostrados
            lblCantidadUsuarios.Text = $"Mostrando {usuariosFiltrados.Count} usuarios.";
        }

        private void CargarUniversidades()
        {

            //ARREGLAR

            BindingList<universidad> universidades = universidadBO.listarTodos(-1, -1, -1);
            cblUniversidades.DataSource = universidades;
            cblUniversidades.DataTextField = "nombre";
            cblUniversidades.DataValueField = "idUniversidad";
            cblUniversidades.DataBind();

        }

        protected void gvUsuarios_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvUsuarios.PageIndex = e.NewPageIndex;
            CargarUsuarios(); //!!!
        }

        protected void gvUsuarios_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            //ARREGALR
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                usuario usuario = (usuario)e.Row.DataItem;

                tipoUsuario tipoUsuario = usuario.tipoUsuario;

                if (tipoUsuario == tipoUsuario.ADMINISTRADOR)
                {
                    // Ocultar los botones Editar y Eliminar
                    LinkButton lbEditar = (LinkButton)e.Row.FindControl("lbEditarUsuario");
                    LinkButton lbEliminar = (LinkButton)e.Row.FindControl("lbEliminarUsuario");

                    if (lbEditar != null)
                        lbEditar.Visible = false;

                    if (lbEliminar != null)
                        lbEliminar.Visible = false;
                }

                Label lblUniversidades = (Label)e.Row.FindControl("lblUniversidades");

                // Obtener las universidades asociadas al usuario
                BindingList<universidad> universidadesAsociadas = universidadBO.listarTodos_UniversidadesDeUsuarioPorID(usuario.idUsuario);
                Session["universidadesAsociadas"] = universidadesAsociadas;
                if (universidadesAsociadas != null && universidadesAsociadas.Count > 0)
                {
                    // Concatenar los nombres de las universidades
                    string nombresUniversidades = string.Join(",\n ", universidadesAsociadas.Select(a => a.nombre));
                    lblUniversidades.Text = nombresUniversidades;
                }
                else
                {
                    lblUniversidades.Text = "Sin universidades asociadas";
                }
            }

        }

        protected void lbEditarUsuario_Click(object sender, EventArgs e)
        {
            //ARREGLAR
            // Obtenemos el ID del usuario seleccionado a través del CommandArgument
            int idUsuario = int.Parse(((LinkButton)sender).CommandArgument);

            // Cargamos el usuario desde la base de datos
            usuario usuario = usuarioBO.consultarMiembroID(idUsuario);

            // Asignamos los valores a los controles del modal
            txtNombreUsuario.Text = usuario.nombres;
            txtApellidosUsuario.Text = usuario.apellidos;
            txtCorreoUsuario.Text = usuario.correoInstitucional;
            txtUsername.Text = usuario.nombreUsuario;

            // Aseguramos que el valor del tipo de usuario coincide exactamente con los valores del DropDownList
            ddlTipoUsuario.SelectedValue = usuario.tipoUsuario.ToString();

            // Guardamos datos necesarios en sesión
            Session["Usuario"] = usuario;

            // Cargar y preseleccionar las universidades asociadas
            BindingList<universidad> universidadesAsociacionesDelUsuario = universidadBO.listarTodos_UniversidadesDeUsuarioPorID(idUsuario);
            Session["universidadesAsociadas"] = universidadesAsociacionesDelUsuario;

            // Deseleccionar todas las opciones primero
            foreach (ListItem item in cblUniversidades.Items)
            {
                item.Selected = false;
            }

            // Preseleccionamos las universidades asociadas
            foreach (ListItem item in cblUniversidades.Items)
            {
                int idUniversidad = int.Parse(item.Value);
                if (universidadesAsociacionesDelUsuario.Any(a => a.idUniversidad == idUniversidad))
                {
                    item.Selected = true;
                }
            }

            // Abrimos el modal desde el servidor
            string script = "window.onload = function() { showModalUsuario(); };";
            ClientScript.RegisterStartupScript(this.GetType(), "ShowModalScript", script, true);

        }

        protected void btnGuardarUsuario_Click(object sender, EventArgs e)
        {
            //ARREGLAR


            // Recuperamos el tipo de usuario seleccionado
            string tipoUsuario = ddlTipoUsuario.SelectedValue;

            // Obtener las universidades seleccionadas
            List<int> universidadesSeleccionadas = cblUniversidades.Items.Cast<ListItem>()
                .Where(li => li.Selected)
                .Select(li => int.Parse(li.Value))
                .ToList();
            int[] universidadesSeleccionadasArray = universidadesSeleccionadas.ToArray();
            // Recuperar las asociaciones actuales desde la sesión
            //BindingList<usuarioUniversidad> asociaciones = (BindingList<usuarioUniversidad>)Session["asociaciones"];
            BindingList<universidad> univs = (BindingList<universidad>)Session["universidadesAsociadas"];
            int[] universidadesActuales = new int[0]; // Inicializar el arreglo vacío

            if (univs != null && univs.Any())
            {
                universidadesActuales = univs.Select(u => u.idUniversidad).ToArray();
            }
            if (Session["Usuario"] != null)
            {
                // Estamos editando un usuario
                // int idUsuario = (int)Session["idUsuario"];

                // Obtener el usuario actual para conservar datos inmutables
                usuario usuarioActual = (usuario)Session["Usuario"]; //!!!!!!!!!!!!

                // Actualizamos solo el tipo de usuario
                usuarioBO.modificar(usuarioActual.idUsuario, usuarioActual.nombres, usuarioActual.apellidos, usuarioActual.correoInstitucional,
                    usuarioActual.contrasenia, usuarioActual.nombreUsuario, usuarioActual.fechaRegistro.ToString(), tipoUsuario);

                // Actualizar asociaciones de universidades
                usuarioBO.modificarUniversidadesAsociadasDeUsuario(usuarioActual.idUsuario, universidadesActuales, universidadesSeleccionadasArray);

                // Limpiamos variables de sesión
                Session.Remove("Usuario");
                Session.Remove("asociaciones");
            }

            // Recargamos la lista de usuarios
            CargarUsuarios();

            // Cerramos el modal desde el servidor
            string script = "window.onload = function() { hideModalUsuario(); };";
            ClientScript.RegisterStartupScript(this.GetType(), "HideModalScript", script, true);

        }

        protected void btnCancelarUsuario_Click(object sender, EventArgs e)
        {
            // Limpiamos variables de sesión
            Session.Remove("Usuario");
            Session.Remove("asociaciones");
            //limpiar campos
            txtNombreUsuario.Text = string.Empty;
            txtApellidosUsuario.Text = string.Empty;
            txtCorreoUsuario.Text = string.Empty;
            txtUsername.Text = string.Empty;
            ddlTipoUsuario.SelectedIndex = 0;

            // Cerramos el modal desde el servidor
            string script = "window.onload = function() { hideModalUsuario(); };";
            ClientScript.RegisterStartupScript(this.GetType(), "HideModalScript", script, true);
        }

        protected void lbEliminarUsuario_Click(object sender, EventArgs e)
        {
            //ARREGALR
            // Obtener el ID del usuario a eliminar desde el CommandArgument
            int idUsuario = int.Parse(((LinkButton)sender).CommandArgument);
            usuarioBO = new UsuarioBO();//
            try
            {
                // Eliminar las asociaciones del usuario con las universidades
                //usuarioUniversidadBO.eliminarPorUsuario(idUsuario);
                //OJO, NO SE BORRA UN USUARIO SI TIENE ELEMENTOS RELACIONADOS PORQUE SALE DE NUESTRO ALCANCE, BORRAR EN CASCADA ESTOS ELEMENTOS RELACIONADOS PODRÍA SALIRSE DE CONTROL,
                //DEBIDO A QUE NO SOLO SON UNIVERSIDADES, SINO PRÉSTAMOS Y RESERVAS

                usuario usuAModificar = usuarioBO.consultarMiembroID(idUsuario);
                usuAModificar.tipoUsuario = tipoUsuario.INACTIVO;
                int resultao = usuarioBO.modificar(idUsuario, usuAModificar.nombres, usuAModificar.apellidos, usuAModificar.correoInstitucional, usuAModificar.contrasenia, usuAModificar.nombreUsuario, usuAModificar.fechaRegistro.ToString(), usuAModificar.tipoUsuario.ToString());
                if (resultao < 0)
                {
                    string scriptError = "alert('Error al eliminar el usuario');";
                    ClientScript.RegisterStartupScript(this.GetType(), "ErrorEliminacion", scriptError, true);
                    //CargarUsuarios();
                }
                else
                {
                    int resultado = universidadBO.eliminarUniversidadesDeUsuario(idUsuario);
                    // Mostrar un mensaje de éxito al usuario
                    if (resultado >= 0)
                    {
                        string scriptSuccess = "alert('Usuario eliminado (desactivado) correctamente.');";
                        ClientScript.RegisterStartupScript(this.GetType(), "EliminacionExitosa", scriptSuccess, true);
                    }
                    else
                    {
                        string scriptSuccess = "alert('Usuario eliminado pero hubo problema con universidades asociadas');";
                        ClientScript.RegisterStartupScript(this.GetType(), "Eliminacion", scriptSuccess, true);
                    }

                }
                // Recargar la lista de usuarios para reflejar los cambios
                CargarUsuarios();
            }
            catch (Exception ex)
            {
                // Mostrar un mensaje de error al usuario
                string errorMessage = ex.Message.Replace("'", "\\'");
                string scriptError = "alert('Error al eliminar el usuario : {errorMessage}');";
                ClientScript.RegisterStartupScript(this.GetType(), "ErrorEliminacion", scriptError, true);

                CargarUsuarios();
            }

        }
        protected void btnBuscarUsuarios_Click(object sender, EventArgs e)
        {
            // Obtener los valores de los campos de búsqueda
            string nombreBusqueda = txtBuscarPorNombre.Text.Trim();
            string apellidoBusqueda = txtBuscarPorApellido.Text.Trim();
            string usernameBusqueda = txtBuscarPorUsername.Text.Trim();

            // Guardar los criterios de búsqueda en ViewState
            ViewState["NombreBusqueda"] = nombreBusqueda;
            ViewState["ApellidoBusqueda"] = apellidoBusqueda;
            ViewState["UsernameBusqueda"] = usernameBusqueda;

            // Cargar los usuarios con los criterios de búsqueda
            CargarUsuarios();
        }
        protected void btnLimpiarBusqueda_Click(object sender, EventArgs e)
        {
            // Limpiar los campos de búsqueda
            txtBuscarPorNombre.Text = string.Empty;
            txtBuscarPorApellido.Text = string.Empty;
            txtBuscarPorUsername.Text = string.Empty;

            // Limpiar los criterios de búsqueda en ViewState
            ViewState["NombreBusqueda"] = string.Empty;
            ViewState["ApellidoBusqueda"] = string.Empty;
            ViewState["UsernameBusqueda"] = string.Empty;

            // Recargar todos los usuarios
            CargarUsuarios();
        }
        protected void buscarPorNombre(object sender, EventArgs e)
        {
            string textoBusqueda = txtBuscarPorNombre.Text;
            ViewState["TextoBusqueda"] = textoBusqueda; // Guardar el criterio de búsqueda
            if (!string.IsNullOrEmpty(textoBusqueda))
            {
                // Obtener los datos originales (por ejemplo, desde la base de datos)
                BindingList<usuario> usuarios = usuarioBO.listarTodos(textoBusqueda, null, null, null, null, -1);
                List<usuario> usuariosFiltrados = usuarios
                .Where(u => u.tipoUsuario != tipoUsuario.ADMINISTRADOR).ToList();
                // Crear una nueva tabla con los datos filtrados
                gvUsuarios.DataSource = usuariosFiltrados;
                gvUsuarios.PageIndex = 0; // Reiniciar a la primera página
                gvUsuarios.DataBind();

                lblCantidadUsuarios.Text = $"Mostrando {usuariosFiltrados.Count} usuarios de la búsqueda.";
            }
            else
            {
                CargarUsuarios();
            }


        }

    }
}
