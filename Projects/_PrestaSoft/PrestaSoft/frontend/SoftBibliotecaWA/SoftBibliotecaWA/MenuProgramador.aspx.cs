using SoftBibliotecaBO.ServicioWeb;
using SoftBibliotecaConsorcioBO;
using SoftBibliotecaMoraBO;
using SoftBibliotecaPolitica;
using SoftBibliotecaPoliticaPrestamos;
using SoftBibliotecaPoliticaReservaBO;
using SoftBibliotecaPublicacionBO;
using SoftBibliotecaUniversidadBO;
using SoftBibliotecaUsuarioBO;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftBibliotecaWA
{
    public partial class MenuProgramador : System.Web.UI.Page
    {
        private MoraBO moraBO;
        private PoliticaReservaBO politicaReservaBO;
        private PoliticaPrestamoBO politicaPrestamoBO;
        private PoliticaBO politicaBO;

        private UniversidadBO universidadBO;
        private BibliotecaBO bibliotecaBO;
        private UsuarioBO usuarioBO;
        private ConsorcioBO consorcioBO;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack) // Carga Inicial de elementos
            {
                cargarMoras();
                cargarPoliticasPrestamo();
                cargarPoliticasReserva();
                cargarPoliticas();
                cargarConsorcio();
                cargarUniversidades();
                cargarBibliotecas();
                MostrarUniversidad(null, null);
            }
        }

        protected void MostrarUniversidad(object sender, EventArgs e)
        {
            TablaUniversidades.Visible = true;
            TablaBiblioteca.Visible = false;
            
            TablaMoras.Visible = false;
            TablaPoliticas.Visible = false;
            TablaPoliticaPrestamo.Visible = false;
            TablaPoliticaReservas.Visible = false;
            //cargarUniversidades();
            //BindUniversidadesDropDown();
        }

        protected void MostrarBibliotecas(object sender, EventArgs e)
        {
            TablaUniversidades.Visible = false;
            TablaBiblioteca.Visible = true;
            
            TablaMoras.Visible = false;
            TablaPoliticas.Visible = false;
            TablaPoliticaPrestamo.Visible = false;
            TablaPoliticaReservas.Visible = false;
            //cargarBibliotecas();
        }
        /*
        protected void MostrarConsorcios(object sender, EventArgs e)
        {
            TablaUniversidades.Visible = false;
            TablaBiblioteca.Visible = false;
            
            TablaMoras.Visible = false;
            TablaPoliticas.Visible = false;
            TablaPoliticaPrestamo.Visible = false;
            TablaPoliticaReservas.Visible = false;
            //cargarConsorcio();
        }
        */
        protected void MostrarMoras(object sender, EventArgs e)
        {
            TablaUniversidades.Visible = false;
            TablaBiblioteca.Visible = false;
            
            TablaMoras.Visible = true;
            TablaPoliticas.Visible = false;
            TablaPoliticaPrestamo.Visible = false;
            TablaPoliticaReservas.Visible = false;
            //cargarMoras();
            //BindMoraAsociadaDropDown();
        }

        protected void MostrarPoliticas(object sender, EventArgs e)
        {
            TablaUniversidades.Visible = false;
            TablaBiblioteca.Visible = false;
            
            TablaMoras.Visible = false;
            TablaPoliticas.Visible = true;
            TablaPoliticaPrestamo.Visible = false;
            TablaPoliticaReservas.Visible = false;
            cargarPoliticas();
            //BindPoliticasDropDown();
        }

        protected void MostrarReservas(object sender, EventArgs e)
        {
            TablaUniversidades.Visible = false;
            TablaBiblioteca.Visible = false;
            
            TablaMoras.Visible = false;
            TablaPoliticas.Visible = false;
            TablaPoliticaPrestamo.Visible = false;
            TablaPoliticaReservas.Visible = true;
            //cargarPoliticasReserva();
            //BindPoliticasReservaDropDown();
        }

        protected void MostrarPrestamos(object sender, EventArgs e)
        {
            TablaUniversidades.Visible = false;
            TablaBiblioteca.Visible = false;
            
            TablaMoras.Visible = false;
            TablaPoliticas.Visible = false;
            TablaPoliticaPrestamo.Visible = true;
            TablaPoliticaReservas.Visible = false;
            //cargarPoliticasPrestamo();
            //BindPoliticasPrestamoDropDown();
        }

        //CARGAS PRINCIPALES
        private void cargarDDL<T>(DropDownList ddl, BindingList<T> dataSource, string dataTextField, string DataValueField, string placeholder)
        {
            ddl.DataSource = dataSource;
            ddl.DataTextField = dataTextField;
            ddl.DataValueField = DataValueField;
            ddl.DataBind();
            ddl.Items.Insert(0, new ListItem(placeholder, ""));
        }
        private void cargarConsorcio()
        {
            consorcioBO = new ConsorcioBO();
            BindingList<consorcio> consorcios = consorcioBO.listarTodos(-1);
            cargarDDL<consorcio>(ddlUniversidadConsorcios, consorcios, "CIF", "idConsorcio", "Seleccione un consorcio..");

        }
        private void cargarMoras()
        {
            //ARREGLAR



            moraBO = new MoraBO();
            BindingList<mora> moras = moraBO.listarTodos(-1);
            cargarDDL<mora>(ddlMoraAsociada, moras, "monto", "idMora", "Seleccione una Mora Asociada según monto..");
            GV_Moras.DataSource = moras;
            GV_Moras.DataBind();


        }

        private void cargarPoliticasReserva()
        {

            //ARREGLAR


            politicaReservaBO = new PoliticaReservaBO();
            BindingList<politicaReserva> politicasReserva = politicaReservaBO.listarTodos(-1);
            cargarDDL<politicaReserva>(ddlPoliticasReserva, politicasReserva, "idPoliticaReserva", "idPoliticaReserva", "Seleccione una política por reserva..");
            GV_PoliticasReserva.DataSource = politicasReserva;
            GV_PoliticasReserva.DataBind();

        }

        private void cargarPoliticasPrestamo()
        {
            //ARREGLAR
            //cargarMoras();
            politicaPrestamoBO = new PoliticaPrestamoBO();
            BindingList<politicaPrestamo> politicasPrestamo = politicaPrestamoBO.listarTodos(-1, -1);
            cargarDDL<politicaPrestamo>(ddlPoliticasPrestamo, politicasPrestamo, "idPoliticaPrestamo", "idPoliticaPrestamo", "Seleccione una política por préstamo..");
            GV_PoliticasPrestamo.DataSource = politicasPrestamo;
            GV_PoliticasPrestamo.DataBind();

        }

        private void cargarPoliticas()
        {
            //ARREGLAR

            politicaBO = new PoliticaBO();
            BindingList<politica> politicas = politicaBO.listarTodos(-1, -1, -1);
            cargarDDL<politica>(ddlUniversidadPoliticas, politicas, "etiqueta", "idPolitica", "Seleccione una política..");
            GV_Politicas.DataSource = politicas;
            GV_Politicas.DataBind();
            // DropDownLists se manejan de forma independiente ahora

        }
        private void cargarUniversidades()
        {
            //ARREGLAR
            //cargarConsorcio();
            //cargarPoliticas();
            usuario programador = (usuario)Session["Usuario"];
            if (programador == null)
            {
                // Manejar el caso donde el usuario no está en la sesión
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorUsuarioNoEncontrado", "alert('Error: Usuario no encontrado en la sesión.');", true);
                return;
            }
            universidadBO = new UniversidadBO();
            BindingList<universidad> universidades = universidadBO.listarTodos_UniversidadesDeUsuarioPorID(programador.idUsuario);
            Session["idUniversidadesAsociadasDelProgramador"] = universidades.Select(u => u.idUniversidad).ToArray();
            politica politicaDummy = new politica { etiqueta = "Política sin asignar" };
            consorcio consorcioDummy = new consorcio { CIF = "Sin consorcio" };

            // Iterar y asignar objetos dummy donde sea necesario
            foreach (var u in universidades)
            {
                if (u.politicaRegular == null)
                {
                    u.politicaRegular = politicaDummy;
                }

                if (u.consorcioPerteneciente == null)
                {
                    u.consorcioPerteneciente = consorcioDummy;
                }
            }
            cargarDDL<universidad>(ddlBibliotecaUniversidad, universidades, "nombre", "idUniversidad", "Seleccione una universidad..");
            GV_Universidades.DataSource = universidades;
            GV_Universidades.DataBind();
            // DropDownLists se manejan de forma independiente ahora

        }

        private void cargarBibliotecas()
        {
            //ARREGLAR
            usuario programador = (usuario)Session["Usuario"];
            
            bibliotecaBO = new BibliotecaBO();
            int[] idUniversidadesDelProgramador = (int[])Session["idUniversidadesAsociadasDelProgramador"];
            BindingList<biblioteca> bibliotecas = bibliotecaBO.mostrarBibliotecasDeUniversidadesDelProgramador(programador.idUsuario, idUniversidadesDelProgramador);

            universidad univDummie = new universidad { nombre = "Universidad Desconocida" };
            foreach (var b in bibliotecas)
            {
                if (b.universidadAsociada == null)
                    b.universidadAsociada = univDummie;

            }

            GV_Bibliotecas.DataSource = bibliotecas;
            GV_Bibliotecas.DataBind();

        }



        //SECCIONES ESPECÍFICAS
        /* UNIVERSIDADES */

        protected void btnAbrirModalUniversidad_Click(object sender, EventArgs e)
        {
            LimpiarCamposUniversidad();
            Session["accionUniversidad"] = "Insertar";
            Session.Remove("idUniversidad");
            modalUniversidadLabel.InnerText = "Ingresar Universidad";

            // Abrir el modal utilizando jQuery y Bootstrap 4
            string script = "window.onload = function() { showModalUniversidad(); };";
            ClientScript.RegisterStartupScript(this.GetType(), "ShowModalScript", script, true);
        }

        protected void lbEditarUniversidad_Click(object sender, EventArgs e)
        {
            // Obtener el ID de la universidad de forma segura
            int idUniversidad = int.Parse(((LinkButton)sender).CommandArgument);


            // Cargar los datos de la universidad en los controles del modal
            CargarDatosUniversidad(idUniversidad);
            Session["accionUniversidad"] = "Editar";
            Session["idUniversidad"] = idUniversidad;
            // Cambiar el título del modal
            modalUniversidadLabel.InnerText = "Editar Universidad";


            string script = "window.onload = function() { showModalUniversidad(); };";
            ClientScript.RegisterStartupScript(this.GetType(), "ShowModalScript", script, true);
        }

        private void CargarDatosUniversidad(int idUniversidad)
        {
            universidadBO = new UniversidadBO();
            universidad universidad = universidadBO.obtenerPorId(idUniversidad);

            if (universidad != null)
            {
                txtUniversidadNombre.Text = universidad.nombre;
                txtUniversidadDireccion.Text = universidad.direccion;
                txtUniversidadCorreo.Text = universidad.correoPersonalAdministrativo;
                txtUniversidadExtension.Text = universidad.extensionDominioCorreo;

                // Vincular los DropDownList específicos
                //BindPoliticasDropDown();
                //BindConsorciosDropDown();

                // Asignar el SelectedValue de forma segura
                if (universidad.politicaRegular.idPolitica > 0)
                {
                    string politicaValue = universidad.politicaRegular.idPolitica.ToString();
                    if (ddlUniversidadPoliticas.Items.FindByValue(politicaValue) != null)
                    {
                        ddlUniversidadPoliticas.SelectedValue = politicaValue;
                    }
                    else
                    {
                        ddlUniversidadPoliticas.SelectedIndex = 0; // Seleccionar el placeholder si no existe el valor
                    }
                }
                else
                {
                    ddlUniversidadPoliticas.SelectedIndex = 0;
                }

                if (universidad.consorcioPerteneciente != null && universidad.consorcioPerteneciente.idConsorcio > 0)
                {
                    string consorcioValue = universidad.consorcioPerteneciente.idConsorcio.ToString();
                    if (ddlUniversidadConsorcios.Items.FindByValue(consorcioValue) != null)
                    {
                        ddlUniversidadConsorcios.SelectedValue = consorcioValue;
                    }
                    else
                    {
                        ddlUniversidadConsorcios.SelectedIndex = 0; // Seleccionar el placeholder si no existe el valo                  
                    }
                }
                else
                {
                    ddlUniversidadConsorcios.SelectedIndex = 0;
                }
            }
            else
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorCargaDatos", "alert('Error: No se pudieron cargar los datos de la universidad.');", true);
            }
        }

        protected void btnGuardarUniversidad_Click(object sender, EventArgs e)
        {
            string accion = Session["accionUniversidad"] as string;

            string nombre = txtUniversidadNombre.Text;
            string direccion = txtUniversidadDireccion.Text;
            string correo = txtUniversidadCorreo.Text;
            string extensionDominio = txtUniversidadExtension.Text;
            // Obtener valores de los dropdowns
            int idPolitica = -1;
            int idConsorcio = -1;

            bool isValidPolitica = int.TryParse(ddlUniversidadPoliticas.SelectedValue, out idPolitica);
            bool isValidConsorcio = int.TryParse(ddlUniversidadConsorcios.SelectedValue, out idConsorcio);

            universidadBO = new UniversidadBO();
            if (!isValidPolitica) idPolitica = -1;
            if (!isValidConsorcio) idConsorcio = -1;
            try
            {
                if (accion == "Insertar")
                {
                    universidadBO.insertar(nombre, direccion, correo, extensionDominio, idPolitica, idConsorcio);
                    //ScriptManager.RegisterStartupScript(this, this.GetType(), "SuccessInsertar", "alert('Universidad registrada correctamente.');", true);
                }
                else if (accion == "Editar")
                {
                    if (Session["idUniversidad"] != null)
                    {
                        int idUniversidad = (int)Session["idUniversidad"];
                        universidadBO.modificar(idUniversidad, nombre, direccion, correo, extensionDominio, idPolitica, idConsorcio);
                        ScriptManager.RegisterStartupScript(this, this.GetType(), "SuccessEditar", "alert('Universidad actualizada correctamente.');", true);
                    }
                    else
                    {
                        // Manejar el caso en que el ID no esté disponible
                        ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorID", "alert('Error: No se pudo obtener el ID de la universidad de la sesión.');", true);
                        return;
                    }
                }

                // Limpiar variables de sesión
                Session.Remove("accionUniversidad");
                Session.Remove("idUniversidad");

                // Recargar la lista de universidades
                cargarUniversidades();

                // Cerrar el modal utilizando jQuery y Bootstrap 4
                string script = "window.onload = function() { hideModalUniversidad(); };";
                ClientScript.RegisterStartupScript(this.GetType(), "HideModalScript", script, true);
            }
            catch (Exception ex)
            {
                // Manejar errores durante la operación
                string errorMessage = ex.Message.Replace("'", "\\'");
                string scriptError = $"alert('Error al guardar la universidad: {errorMessage}');";
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorGuardarUniversidad", scriptError, true);
            }
        }

        protected void btnCancelarUniversidad_Click(object sender, EventArgs e)
        {
            // Limpiar variables de sesión y campos
            Session.Remove("accionUniversidad");
            Session.Remove("idUniversidad");
            LimpiarCamposUniversidad();
            // Cerrar el modal utilizando jQuery y Bootstrap 4
            string script = "window.onload = function() { hideModalUniversidad(); };";
            ClientScript.RegisterStartupScript(this.GetType(), "HideModalScript", script, true);
        }

        private void LimpiarCamposUniversidad()
        {
            txtUniversidadNombre.Text = "";
            txtUniversidadDireccion.Text = "";
            txtUniversidadCorreo.Text = "";
            txtUniversidadExtension.Text = "";
            ddlUniversidadPoliticas.SelectedIndex = 0;
            ddlUniversidadConsorcios.SelectedIndex = 0;
        }

        protected void lbEliminarUniversidad_Click(object sender, EventArgs e)
        {
            // Obtener el ID de la universidad a eliminar de forma segura
            int idUniversidad = int.Parse(((LinkButton)sender).CommandArgument);
            universidadBO = new UniversidadBO();
            try
            {

                int resultado = universidadBO.eliminar(idUniversidad);

                if (resultado < 0)
                {
                    // Mostrar mensaje de error
                    string scriptError = "alert('Error al eliminar la universidad, hay bibliotecas o usuarios relacionados a ella.');";
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorEliminacion", scriptError, true);
                }
                else
                {
                    // Mostrar mensaje de éxito
                    string scriptSuccess = "alert('Universidad eliminada correctamente.');";
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "EliminacionExitosa", scriptSuccess, true);
                }

                // Recargar la lista de universidades
                cargarUniversidades();
            }
            catch (Exception ex)
            {
                // Mostrar mensaje de error con interpolación correcta
                string errorMessage = ex.Message.Replace("'", "\\'");
                string scriptError = $"alert('Error al eliminar la universidad: {errorMessage}');";
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorEliminacion", scriptError, true);

                // Recargar la lista de universidades
                cargarUniversidades();
            }
        }
        /*
        private void BindMoraAsociadaDropDown()
        {
            //ARREGLAR

            moraBO = new MoraBO();
            BindingList<mora> moras = moraBO.listarTodos(-1);
            cargarDDL<mora>(ddlMoraAsociada, moras, "monto", "idMora", "Seleccione una Mora Asociada según monto..");

        }
        private void BindPoliticasPrestamoDropDown()
        {
            //ARREGLAR

            politicaPrestamoBO = new PoliticaPrestamoBO();
            BindingList<politicaPrestamo> politicasPrestamo = politicaPrestamoBO.listarTodos(-1, -1);
            cargarDDL<politicaPrestamo>(ddlPoliticasPrestamo, politicasPrestamo, "idPoliticaPrestamo", "idPoliticaPrestamo", "Seleccione una política por préstamo..");

        }

        private void BindPoliticasReservaDropDown()
        {
            //ARREGLAR

            politicaReservaBO = new PoliticaReservaBO();
            BindingList<politicaReserva> politicasReserva = politicaReservaBO.listarTodos(-1);
            cargarDDL<politicaReserva>(ddlPoliticasReserva, politicasReserva, "idPoliticaReserva", "idPoliticaReserva", "Seleccione una política por reserva..");

        }
        private void BindPoliticasDropDown()
        {
            //ARREGLAR

            politicaBO = new PoliticaBO();
            BindingList<politica> politicas = politicaBO.listarTodos(-1, -1, -1);
            cargarDDL<politica>(ddlUniversidadPoliticas, politicas, "etiqueta", "idPolitica", "Seleccione una política..");

        }

        private void BindConsorciosDropDown()
        {
            //ARREGLAR

            consorcioBO = new ConsorcioBO();
            BindingList<consorcio> consorcios = consorcioBO.listarTodos(-1);
            cargarDDL<consorcio>(ddlUniversidadConsorcios, consorcios, "CIF", "idConsorcio", "Seleccione un consorcio..");

        }
        private void BindUniversidadesDropDown()
        {
            //ARREGLAR

            universidadBO = new UniversidadBO();
            var universidades = universidadBO.listarTodos(-1, -1, -1);
            cargarDDL<universidad>(ddlBibliotecaUniversidad, universidades, "nombre", "idUniversidad", "Seleccione una universidad..");

        }
        */
        //BIBLIOTECAS  -------------------------------------------------------------------------
        protected void btnAbrirModalBiblioteca_Click(object sender, EventArgs e)
        {
            LimpiarCamposBiblioteca();
            Session["accionBiblioteca"] = "Insertar";
            Session.Remove("idBiblioteca");
            modalBibliotecaLabel.InnerText = "Ingresar Biblioteca";
            string script = "window.onload = function() { showModalBiblioteca(); };";
            //BindUniversidadesDropDown();
            ClientScript.RegisterStartupScript(this.GetType(), "ShowModalScript", script, true);
        }
        protected void lbEditarBiblioteca_Click(object sender, EventArgs e)
        {
            if (!int.TryParse(((LinkButton)sender).CommandArgument, out int idBiblioteca))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorFormatoIDEditar", "alert('El ID de la biblioteca no tiene un formato válido.');", true);
                return;
            }
            CargarDatosBiblioteca(idBiblioteca);
            Session["accionBiblioteca"] = "Editar";
            Session["idBiblioteca"] = idBiblioteca;


            modalBibliotecaLabel.InnerText = "Editar Biblioteca";

            string script = "window.onload = function() { showModalBiblioteca(); };";
            ClientScript.RegisterStartupScript(this.GetType(), "ShowModalScript", script, true);
        }
        private void CargarDatosBiblioteca(int idBiblioteca)
        {
            bibliotecaBO = new BibliotecaBO();
            var biblioteca = bibliotecaBO.obtenerPorId(idBiblioteca);

            if (biblioteca != null)
            {
                txtBibliotecaNombre.Text = biblioteca.nombre;
                txtBibliotecaUbicacion.Text = biblioteca.ubicacion;

                //BindUniversidadesDropDown(); !!!!
                // Asignar el SelectedValue de forma segura
                if (biblioteca.universidadAsociada != null)
                {
                    string universidadValue = biblioteca.universidadAsociada.idUniversidad.ToString();
                    if (ddlBibliotecaUniversidad.Items.FindByValue(universidadValue) != null)
                    {
                        ddlBibliotecaUniversidad.SelectedValue = universidadValue;
                    }
                    else
                    {
                        ddlBibliotecaUniversidad.SelectedIndex = 0;
                        ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorSeleccionUniversidad", $"alert('La universidad con ID {universidadValue} no existe en la lista.');", true);
                    }
                }
                else
                {
                    ddlBibliotecaUniversidad.SelectedIndex = 0;
                }
            }
            else
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorCargaDatos", "alert('Error: No se pudieron cargar los datos de la biblioteca.');", true);
            }
        }
        protected void btnGuardarBiblioteca_Click(object sender, EventArgs e)
        {
            string accion = Session["accionBiblioteca"] as string;

            string nombre = txtBibliotecaNombre.Text;
            string ubicacion = txtBibliotecaUbicacion.Text;

            // Validar que los campos no estén vacíos
            if (string.IsNullOrWhiteSpace(nombre) || string.IsNullOrWhiteSpace(ubicacion))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorCampos", "alert('Por favor, completa todos los campos.');", true);
                return;
            }

            // Validar que el dropdown tenga un valor seleccionado
            if (ddlBibliotecaUniversidad.SelectedIndex == 0)
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorDropdown", "alert('Por favor, selecciona una universidad.');", true);
                return;
            }

            // Obtener el valor seleccionado del dropdown
            if (!int.TryParse(ddlBibliotecaUniversidad.SelectedValue, out int idUniversidad))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorDropdownFormat", "alert('Error al obtener el valor seleccionado de la universidad.');", true);
                return;
            }

            bibliotecaBO = new BibliotecaBO();

            try
            {
                if (accion == "Insertar")
                {
                    // Insertar nueva biblioteca
                    bibliotecaBO.insertar(nombre, ubicacion, idUniversidad);
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "SuccessInsertar", "alert('Biblioteca registrada correctamente.');", true);
                }
                else if (accion == "Editar")
                {
                    if (Session["idBiblioteca"] != null && int.TryParse(Session["idBiblioteca"].ToString(), out int idBiblioteca))
                    {
                        bibliotecaBO.modificar(idBiblioteca, nombre, ubicacion, idUniversidad);
                        ScriptManager.RegisterStartupScript(this, this.GetType(), "SuccessEditar", "alert('Biblioteca actualizada correctamente.');", true);
                    }
                    else
                    {
                        // Manejar el caso en que el ID no esté disponible
                        string script1 = "window.onload = function() { hideModalBiblioteca(); };";
                        ClientScript.RegisterStartupScript(this.GetType(), "HideModalScript", script1, true);
                        return;
                    }
                }

                // Limpiar variables de sesión
                Session.Remove("accionBiblioteca");
                Session.Remove("idBiblioteca");

                // Recargar la lista de bibliotecas
                cargarBibliotecas();

                string script = "window.onload = function() { hideModalBiblioteca(); };";
                ClientScript.RegisterStartupScript(this.GetType(), "HideModalScript", script, true);
            }
            catch (Exception ex)
            {
                // Manejar errores durante la operación
                string errorMessage = ex.Message.Replace("'", "\\'");
                string scriptError = $"alert('Error al guardar la biblioteca: {errorMessage}');";
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorGuardarBiblioteca", scriptError, true);
            }
        }
        protected void btnCancelarBiblioteca_Click(object sender, EventArgs e)
        {
            // Limpiar variables de sesión y campos
            Session.Remove("accionBiblioteca");
            Session.Remove("idBiblioteca");
            LimpiarCamposBiblioteca();

            // Cerrar el modal utilizando Bootstrap 5
            string script = "window.onload = function() { hideModalBiblioteca(); };";
            ClientScript.RegisterStartupScript(this.GetType(), "HideModalScript", script, true);
        }

        private void LimpiarCamposBiblioteca()
        {
            txtBibliotecaNombre.Text = "";
            txtBibliotecaUbicacion.Text = "";
            ddlBibliotecaUniversidad.SelectedIndex = 0;
        }
        protected void lbEliminarBiblioteca_Click(object sender, EventArgs e)
        {
            if (!int.TryParse(((LinkButton)sender).CommandArgument, out int idBiblioteca))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorFormatoIDEliminar", "alert('El ID de la biblioteca no tiene un formato válido.');", true);
                return;
            }

            bibliotecaBO = new BibliotecaBO();

            try
            {
                int resultado = bibliotecaBO.eliminar(idBiblioteca);

                if (resultado < 0)
                {
                    // Mostrar mensaje de error
                    string scriptError = "alert('Error al eliminar la biblioteca, hay publicaciones relacionadas a ella.');";
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorEliminacion", scriptError, true);
                }
                else
                {
                    // Mostrar mensaje de éxito
                    string scriptSuccess = "alert('Biblioteca eliminada correctamente.');";
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "EliminacionExitosa", scriptSuccess, true);
                }

                // Recargar la lista de bibliotecas
                cargarBibliotecas();
            }
            catch (Exception ex)
            {
                // Mostrar mensaje de error con interpolación correcta
                string errorMessage = ex.Message.Replace("'", "\\'");
                string scriptError = $"alert('Error al eliminar la biblioteca: {errorMessage}');";
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorEliminacion", scriptError, true);

                // Recargar la lista de bibliotecas
                cargarBibliotecas();
            }
        }

       


        //SECCIÓN MORA
        protected void btnAbrirModalMora_Click(object sender, EventArgs e)
        {
            LimpiarCamposMora();
            Session["accionMora"] = "Insertar";
            Session.Remove("idMora");
            modalMoraLabel.InnerText = "Ingresar Mora";

            // Abrir el modal utilizando ClientScript
            string script1 = "window.onload = function() { showModalMora(); };";
            ClientScript.RegisterStartupScript(this.GetType(), "ShowModalScript", script1, true);
        }
        protected void lbEditarMora_Click(object sender, EventArgs e)
        {
            if (!int.TryParse(((LinkButton)sender).CommandArgument, out int idMora))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorFormatoIDEditar", "alert('El ID de la mora no tiene un formato válido.');", true);
                return;
            }

            Session["accionMora"] = "Editar";
            Session["idMora"] = idMora;

            CargarDatosMora(idMora);
            modalMoraLabel.InnerText = "Editar Mora";

            // Abrir el modal utilizando ClientScript
            string script = "window.onload = function() { showModalMora(); };";
            ClientScript.RegisterStartupScript(this.GetType(), "ShowModalScript", script, true);
        }
        private void CargarDatosMora(int idMora)
        {
            moraBO = new MoraBO();
            mora mora = moraBO.obtenerPorId(idMora);

            if (mora != null)
            {
                txtMontoMora.Text = mora.monto.ToString("F2");
            }
            else
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorCargaDatos", "alert('Error: No se pudieron cargar los datos de la mora.');", true);
            }
        }
        protected void btnGuardarMora_Click(object sender, EventArgs e)
        {
            string accion = Session["accionMora"] as string;

            // Obtener y validar los valores ingresados
            if (!decimal.TryParse(txtMontoMora.Text.Trim(), out decimal monto))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorValidacionMonto", "alert('Por favor, ingresa un valor válido para Monto.');", true);
                return;
            }

            moraBO = new MoraBO();

            try
            {
                if (accion == "Insertar")
                {
                    // Insertar nueva mora
                    moraBO.insertar((float)monto);
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "SuccessInsertar", "alert('Mora registrada correctamente.');", true);
                }
                else if (accion == "Editar")
                {
                    if (Session["idMora"] != null && int.TryParse(Session["idMora"].ToString(), out int idMora))
                    {
                        moraBO.modificar(idMora, (float)monto);
                        ScriptManager.RegisterStartupScript(this, this.GetType(), "SuccessEditar", "alert('Mora actualizada correctamente.');", true);
                    }
                    else
                    {
                        // Manejar el caso en que el ID no esté disponible
                        ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorID", "alert('Error: No se pudo obtener el ID de la mora de la sesión.');", true);
                        return;
                    }
                }

                // Limpiar variables de sesión
                Session.Remove("accionMora");
                Session.Remove("idMora");

                // Recargar la lista de moras
                cargarMoras();

                // Cerrar el modal utilizando ClientScript
                string script = "window.onload = function() { hideModalMora(); };";
                ClientScript.RegisterStartupScript(this.GetType(), "HideModalScript", script, true);
            }
            catch (Exception ex)
            {
                // Manejar errores durante la operación
                string errorMessage = ex.Message.Replace("'", "\\'");
                string scriptError = $"alert('Error al guardar la mora: {errorMessage}');";
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorGuardarMora", scriptError, true);
            }
        }
        protected void btnCancelarMora_Click(object sender, EventArgs e)
        {
            // Limpiar variables de sesión y campos
            Session.Remove("accionMora");
            Session.Remove("idMora");
            LimpiarCamposMora();

            // Cerrar el modal utilizando ClientScript
            string script = "window.onload = function() { hideModalMora(); };";
            ClientScript.RegisterStartupScript(this.GetType(), "HideModalScript", script, true);
        }

        private void LimpiarCamposMora()
        {
            txtMontoMora.Text = "";
        }
        protected void lbEliminarMora_Click(object sender, EventArgs e)
        {
            if (!int.TryParse(((LinkButton)sender).CommandArgument, out int idMora))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorFormatoIDEliminar", "alert('El ID de la mora no tiene un formato válido.');", true);
                return;
            }

            moraBO = new MoraBO();

            try
            {
                int resultado = moraBO.eliminar(idMora);

                if (resultado < 0)
                {
                    // Mostrar mensaje de error
                    string scriptError = "alert('Error al eliminar la mora, hay políticas de préstamo que la usan posiblemente.');";
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorEliminacion", scriptError, true);
                }
                else
                {
                    // Mostrar mensaje de éxito
                    string scriptSuccess = "alert('Mora eliminada correctamente.');";
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "EliminacionExitosa", scriptSuccess, true);
                }

                // Recargar la lista de moras
                cargarMoras();
            }
            catch (Exception ex)
            {
                // Mostrar mensaje de error con interpolación correcta
                string errorMessage = ex.Message.Replace("'", "\\'");
                string scriptError = $"alert('Error al eliminar la mora: {errorMessage}');";
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorEliminacion", scriptError, true);

                // Recargar la lista de moras
                cargarMoras();
            }
        }




        /*SECCIÓN POLÍTICAS */

        protected void btnAbrirModalPolitica_Click(object sender, EventArgs e)
        {
            LimpiarCamposPolitica();
            Session["accionPolitica"] = "Insertar";
            Session.Remove("idPolitica");
            modalPoliticaLabel.InnerText = "Ingresar Política";
            //BindPoliticasPrestamoDropDown();
            // BindPoliticasReservaDropDown(); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

            string script = "window.onload = function() { showModalPolitica(); };";
            ClientScript.RegisterStartupScript(this.GetType(), "ShowModalScript", script, true);
        }
        protected void lbEditarPolitica_Click(object sender, EventArgs e)
        {
            if (!int.TryParse(((LinkButton)sender).CommandArgument, out int idPolitica))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorFormatoIDEditar", "alert('El ID de la política no tiene un formato válido.');", true);
                return;
            }

            Session["accionPolitica"] = "Editar";
            Session["idPolitica"] = idPolitica;
            //BindPoliticasPrestamoDropDown();
            //BindPoliticasReservaDropDown();
            CargarDatosPolitica(idPolitica);
            modalPoliticaLabel.InnerText = "Editar Política";

            string script = "window.onload = function() { showModalPolitica(); };";
            ClientScript.RegisterStartupScript(this.GetType(), "ShowModalScript", script, true);
        }
        private void CargarDatosPolitica(int idPolitica)
        {
            politicaBO = new PoliticaBO();
            var politica = politicaBO.obtenerPorId(idPolitica);

            if (politica != null)
            {
                txtPoliticaEtiqueta.Text = politica.etiqueta;

                // Asignar el SelectedValue de forma segura para Política por Préstamo
                if (politica.politicasPorPrestamo != null)
                {
                    string prestamoValue = politica.politicasPorPrestamo.idPoliticaPrestamo.ToString();
                    if (ddlPoliticasPrestamo.Items.FindByValue(prestamoValue) != null)
                    {
                        ddlPoliticasPrestamo.SelectedValue = prestamoValue;
                    }
                    else
                    {
                        ddlPoliticasPrestamo.SelectedIndex = 0;
                        ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorSeleccionPrestamo", $"alert('La política por préstamo con ID {prestamoValue} no existe en la lista.');", true);
                    }
                }
                else
                {
                    ddlPoliticasPrestamo.SelectedIndex = 0;
                }

                // Asignar el SelectedValue de forma segura para Política por Reserva
                if (politica.politicasPorReserva != null)
                {
                    string reservaValue = politica.politicasPorReserva.idPoliticaReserva.ToString();
                    if (ddlPoliticasReserva.Items.FindByValue(reservaValue) != null)
                    {
                        ddlPoliticasReserva.SelectedValue = reservaValue;
                    }
                    else
                    {
                        ddlPoliticasReserva.SelectedIndex = 0;
                        ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorSeleccionReserva", $"alert('La política por reserva con ID {reservaValue} no existe en la lista.');", true);
                    }
                }
                else
                {
                    ddlPoliticasReserva.SelectedIndex = 0;
                }
            }
            else
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorCargaDatos", "alert('Error: No se pudieron cargar los datos de la política.');", true);
            }
        }
        protected void btnGuardarPolitica_Click(object sender, EventArgs e)
        {
            string accion = Session["accionPolitica"] as string;

            string etiqueta = txtPoliticaEtiqueta.Text.Trim();

            // Validar que los campos no estén vacíos
            if (string.IsNullOrWhiteSpace(etiqueta))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorCampos", "alert('Por favor, completa todos los campos.');", true);
                return;
            }

            // Validar que los dropdowns tengan valores seleccionados
            if (ddlPoliticasPrestamo.SelectedIndex == 0 || ddlPoliticasReserva.SelectedIndex == 0)
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorDropdowns", "alert('Por favor, selecciona una política por préstamo y una política por reserva.');", true);
                return;
            }

            // Obtener valores de los dropdowns
            if (!int.TryParse(ddlPoliticasPrestamo.SelectedValue, out int idPoliticaPrestamo) ||
                !int.TryParse(ddlPoliticasReserva.SelectedValue, out int idPoliticaReserva))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorDropdownsFormat", "alert('Error al obtener los valores seleccionados.');", true);
                return;
            }

            politicaBO = new PoliticaBO();

            try
            {
                if (accion == "Insertar")
                {
                    // Insertar nueva política
                    politicaBO.insertar(etiqueta, idPoliticaPrestamo, idPoliticaReserva);
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "SuccessInsertar", "alert('Política registrada correctamente.');", true);
                }
                else if (accion == "Editar")
                {
                    if (Session["idPolitica"] != null && int.TryParse(Session["idPolitica"].ToString(), out int idPolitica))
                    {
                        politicaBO.modificar(idPolitica, etiqueta, idPoliticaPrestamo, idPoliticaReserva);
                        ScriptManager.RegisterStartupScript(this, this.GetType(), "SuccessEditar", "alert('Política actualizada correctamente.');", true);
                    }
                    else
                    {
                        // Manejar el caso en que el ID no esté disponible
                        ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorID", "alert('Error: No se pudo obtener el ID de la política de la sesión.');", true);
                        return;
                    }
                }

                // Limpiar variables de sesión
                Session.Remove("accionPolitica");
                Session.Remove("idPolitica");

                // Recargar la lista de políticas
                cargarPoliticas();

                string script = "window.onload = function() { hideModalPolitica(); };";
                ClientScript.RegisterStartupScript(this.GetType(), "HideModalScript", script, true);
            }
            catch (Exception ex)
            {
                // Manejar errores durante la operación
                string errorMessage = ex.Message.Replace("'", "\\'");
                string scriptError = $"alert('Error al guardar la política: {errorMessage}');";
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorGuardarPolitica", scriptError, true);
            }
        }
        protected void btnCancelarPolitica_Click(object sender, EventArgs e)
        {
            // Limpiar variables de sesión y campos
            Session.Remove("accionPolitica");
            Session.Remove("idPolitica");
            LimpiarCamposPolitica();

            string script = "window.onload = function() { hideModalPolitica(); };";
            ClientScript.RegisterStartupScript(this.GetType(), "HideModalScript", script, true);
        }

        private void LimpiarCamposPolitica()
        {
            txtPoliticaEtiqueta.Text = "";
            ddlPoliticasPrestamo.SelectedIndex = 0;
            ddlPoliticasReserva.SelectedIndex = 0;
        }
        protected void lbEliminarPolitica_Click(object sender, EventArgs e)
        {
            if (!int.TryParse(((LinkButton)sender).CommandArgument, out int idPolitica))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorFormatoIDEliminar", "alert('El ID de la política no tiene un formato válido.');", true);
                return;
            }

            politicaBO = new PoliticaBO();

            try
            {
                int resultado = politicaBO.eliminar(idPolitica);

                if (resultado < 0)
                {
                    // Mostrar mensaje de error
                    string scriptError = "alert('Error al eliminar la política, es posible que una universidad la tenga en uso.');";
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorEliminacion", scriptError, true);
                }
                else
                {
                    // Mostrar mensaje de éxito
                    string scriptSuccess = "alert('Política eliminada correctamente.');";
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "EliminacionExitosa", scriptSuccess, true);
                }

                // Recargar la lista de políticas
                cargarPoliticas();
            }
            catch (Exception ex)
            {
                // Mostrar mensaje de error con interpolación correcta
                string errorMessage = ex.Message.Replace("'", "\\'");
                string scriptError = $"alert('Error al eliminar la política: {errorMessage}');";
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorEliminacion", scriptError, true);

                // Recargar la lista de políticas
                cargarPoliticas();
            }
        }




        //SECCIÓN POLITICA reservas
        protected void btnAbrirModalPoliticaReserva_Click(object sender, EventArgs e)
        {
            LimpiarCamposPoliticaReserva();
            Session["accionPoliticaReserva"] = "Insertar";
            Session.Remove("idPoliticaReserva");
            modalPoliticaReservaLabel.InnerText = "Ingresar Política de Reserva";

            // Abrir el modal utilizando ClientScript
            string script = "window.onload = function() { showModalPoliticaReserva(); };";
            ClientScript.RegisterStartupScript(this.GetType(), "ShowModalScript", script, true);
        }
        protected void lbEditarPoliticaReserva_Click(object sender, EventArgs e)
        {
            if (!int.TryParse(((LinkButton)sender).CommandArgument, out int idPoliticaReserva))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorFormatoIDEditar", "alert('El ID de la política de reserva no tiene un formato válido.');", true);
                return;
            }

            Session["accionPoliticaReserva"] = "Editar";
            Session["idPoliticaReserva"] = idPoliticaReserva;

            CargarDatosPoliticaReserva(idPoliticaReserva);
            modalPoliticaReservaLabel.InnerText = "Editar Política de Reserva";

            // Abrir el modal utilizando ClientScript
            string script = "window.onload = function() { showModalPoliticaReserva(); };";
            ClientScript.RegisterStartupScript(this.GetType(), "ShowModalScript", script, true);
        }
        private void CargarDatosPoliticaReserva(int idPoliticaReserva)
        {
            politicaReservaBO = new PoliticaReservaBO();
            politicaReserva politicaReserva = politicaReservaBO.obtenerPorId(idPoliticaReserva);

            if (politicaReserva != null)
            {
                txtCantMaxHorasDeRecojo.Text = politicaReserva.cantMaxHorasDeRecojo.ToString();
                txtCantMaxReservasAgendadasPorUsuario.Text = politicaReserva.cantMaxReservasAgendadasPorUsuario.ToString();
            }
            else
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorCargaDatos", "alert('Error: No se pudieron cargar los datos de la política de reserva.');", true);
            }
        }
        protected void btnGuardarPoliticaReserva_Click(object sender, EventArgs e)
        {
            string accion = Session["accionPoliticaReserva"] as string;

            // Obtener y validar los valores ingresados
            if (!int.TryParse(txtCantMaxHorasDeRecojo.Text, out int cantMaxHorasDeRecojo))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorValidacionHoras", "alert('Por favor, ingresa un valor válido para Máximo Horas de Recogida.');", true);
                return;
            }

            if (!int.TryParse(txtCantMaxReservasAgendadasPorUsuario.Text, out int cantMaxReservasAgendadasPorUsuario))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorValidacionReservas", "alert('Por favor, ingresa un valor válido para Máximo Reservas Agendadas por Usuario.');", true);
                return;
            }

            politicaReservaBO = new PoliticaReservaBO();

            try
            {
                if (accion == "Insertar")
                {
                    // Insertar nueva política de reserva
                    politicaReservaBO.insertar(cantMaxHorasDeRecojo, cantMaxReservasAgendadasPorUsuario);
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "SuccessInsertar", "alert('Política de Reserva registrada correctamente.');", true);
                }
                else if (accion == "Editar")
                {
                    if (Session["idPoliticaReserva"] != null && int.TryParse(Session["idPoliticaReserva"].ToString(), out int idPoliticaReserva))
                    {
                        politicaReservaBO.modificar(idPoliticaReserva, cantMaxHorasDeRecojo, cantMaxReservasAgendadasPorUsuario);
                        ScriptManager.RegisterStartupScript(this, this.GetType(), "SuccessEditar", "alert('Política de Reserva actualizada correctamente.');", true);
                    }
                    else
                    {
                        // Manejar el caso en que el ID no esté disponible
                        ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorID", "alert('Error: No se pudo obtener el ID de la política de reserva de la sesión.');", true);
                        return;
                    }
                }

                // Limpiar variables de sesión
                Session.Remove("accionPoliticaReserva");
                Session.Remove("idPoliticaReserva");

                // Recargar la lista de políticas de reserva
                cargarPoliticasReserva();

                // Cerrar el modal utilizando ClientScript
                ClientScript.RegisterStartupScript(this.GetType(), "HideModalScript", "hideModalPoliticaReserva();", true);
            }
            catch (Exception ex)
            {
                // Manejar errores durante la operación
                string errorMessage = ex.Message.Replace("'", "\\'");
                string scriptError = $"alert('Error al guardar la política de reserva: {errorMessage}');";
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorGuardarPoliticaReserva", scriptError, true);
            }
        }
        protected void btnCancelarPoliticaReserva_Click(object sender, EventArgs e)
        {
            // Limpiar variables de sesión y campos
            Session.Remove("accionPoliticaReserva");
            Session.Remove("idPoliticaReserva");
            LimpiarCamposPoliticaReserva();

            // Cerrar el modal utilizando ClientScript
            ClientScript.RegisterStartupScript(this.GetType(), "HideModalScript", "hideModalPoliticaReserva();", true);
        }

        private void LimpiarCamposPoliticaReserva()
        {
            txtCantMaxHorasDeRecojo.Text = "";
            txtCantMaxReservasAgendadasPorUsuario.Text = "";
        }
        protected void lbEliminarPoliticaReserva_Click(object sender, EventArgs e)
        {
            if (!int.TryParse(((LinkButton)sender).CommandArgument, out int idPoliticaReserva))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorFormatoIDEliminar", "alert('El ID de la política de reserva no tiene un formato válido.');", true);
                return;
            }

            politicaReservaBO = new PoliticaReservaBO();

            try
            {
                int resultado = politicaReservaBO.eliminar(idPoliticaReserva);

                if (resultado < 0)
                {
                    // Mostrar mensaje de error
                    string scriptError = "alert('Error al eliminar la política de reserva (Es posible que esté en uso).');";
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorEliminacion", scriptError, true);
                }
                else
                {
                    // Mostrar mensaje de éxito
                    string scriptSuccess = "alert('Política de Reserva eliminada correctamente.');";
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "EliminacionExitosa", scriptSuccess, true);
                }

                // Recargar la lista de políticas de reserva
                cargarPoliticasReserva();
            }
            catch (Exception ex)
            {
                // Mostrar mensaje de error con interpolación correcta
                string errorMessage = ex.Message.Replace("'", "\\'");
                string scriptError = $"alert('Error al eliminar la política de reserva: {errorMessage}');";
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorEliminacion", scriptError, true);

                // Recargar la lista de políticas de reserva
                cargarPoliticasReserva();
            }
        }
        //POLITICA PRESTAMOSSSS


        protected void btnAbrirModalPoliticaPrestamo_Click(object sender, EventArgs e)
        {
            LimpiarCamposPoliticaPrestamo();
            Session["accionPoliticaPrestamo"] = "Insertar";
            Session.Remove("idPoliticaPrestamo");
            modalPoliticaPrestamoLabel.InnerText = "Ingresar Política de Préstamo";

            // Abrir el modal utilizando ClientScript
            string script = "window.onload = function() { showModalPoliticaPrestamo(); };";
            ClientScript.RegisterStartupScript(this.GetType(), "ShowModalScript", script, true);
        }

        protected void lbEditarPoliticaPrestamo_Click(object sender, EventArgs e)
        {
            if (!int.TryParse(((LinkButton)sender).CommandArgument, out int idPoliticaPrestamo))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorFormatoIDEditar", "alert('El ID de la política de préstamo no tiene un formato válido.');", true);
                return;
            }

            Session["accionPoliticaPrestamo"] = "Editar";
            Session["idPoliticaPrestamo"] = idPoliticaPrestamo;
            //BindMoraAsociadaDropDown();
            CargarDatosPoliticaPrestamo(idPoliticaPrestamo);
            modalPoliticaPrestamoLabel.InnerText = "Editar Política de Préstamo";

            // Abrir el modal utilizando ClientScript
            string script = "window.onload = function() { showModalPoliticaPrestamo(); };";
            ClientScript.RegisterStartupScript(this.GetType(), "ShowModalScript", script, true);
        }
        private void CargarDatosPoliticaPrestamo(int idPoliticaPrestamo)
        {
            politicaPrestamoBO = new PoliticaPrestamoBO();
            politicaPrestamo politicaPrestamo = politicaPrestamoBO.obtenerPorId(idPoliticaPrestamo);

            if (politicaPrestamo != null)
            {
                txtCantDiasDeAmpliacionRegular.Text = politicaPrestamo.cantDiasDeAmpliacionRegular.ToString();
                txtCantDiasParaDarPorPerdidaUnaCopia.Text = politicaPrestamo.cantDiasParaDarPorPerdidaUnaCopia.ToString();
                txtCantDiasPrestamoRegular.Text = politicaPrestamo.cantDiasPrestamoRegular.ToString();
                txtCantDiasSinPrestamoPorAtraso.Text = politicaPrestamo.cantDiasSinPrestamoPorAtraso.ToString();
                txtCantDiasSinPrestamoPorMalEstado.Text = politicaPrestamo.cantDiasSinPrestamoPorMalEstado.ToString();
                txtCantDiasSinPrestamoPorPerdido.Text = politicaPrestamo.cantDiasSinPrestamoPorPerdido.ToString();
                txtCantMaxAmpliacionesPermitidasPorPrestamo.Text = politicaPrestamo.cantMaxAmpliacionesPermitidasPorPrestamo.ToString();
                txtCantMaxAtrasosPorCiclo.Text = politicaPrestamo.cantMaxAtrasosPorCiclo.ToString();
                txtCantMaxCopiasPorDevolverPorUsuario.Text = politicaPrestamo.cantMaxCopiasPorDevolverPorUsuario.ToString();
                txtCantMaxMalEstadoPorCiclo.Text = politicaPrestamo.cantMaxMalEstadoPorCiclo.ToString();
                txtCantMaxPerdidasPorCiclo.Text = politicaPrestamo.cantMaxPerdidasPorCiclo.ToString();
                txtCargoPorMalEstado.Text = politicaPrestamo.cargoPorMalEstado.ToString("F2");
                txtCargoPorPerdido.Text = politicaPrestamo.cargoPorPerdido.ToString("F2");

                // Asignar el SelectedValue de forma segura para Mora Asociada
                if (politicaPrestamo.moraAsociada != null)
                {
                    string moraValue = politicaPrestamo.moraAsociada.idMora.ToString();
                    if (ddlMoraAsociada.Items.FindByValue(moraValue) != null)
                    {
                        ddlMoraAsociada.SelectedValue = moraValue;
                    }
                    else
                    {
                        ddlMoraAsociada.SelectedIndex = 0;
                        ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorSeleccionMora", $"alert('La mora con ID {moraValue} no existe en la lista.');", true);
                    }
                }
                else
                {
                    ddlMoraAsociada.SelectedIndex = 0;
                }
            }
            else
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorCargaDatos", "alert('Error: No se pudieron cargar los datos de la política de préstamo.');", true);
            }
        }
        protected void btnGuardarPoliticaPrestamo_Click(object sender, EventArgs e)
        {
            string accion = Session["accionPoliticaPrestamo"] as string;

            // Obtener y validar los valores ingresados
            if (!int.TryParse(txtCantDiasDeAmpliacionRegular.Text, out int cantDiasDeAmpliacionRegular))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorValidacionDiasAmpliacion", "alert('Por favor, ingresa un valor válido para Días de Ampliación Regular.');", true);
                return;
            }

            if (!int.TryParse(txtCantDiasParaDarPorPerdidaUnaCopia.Text, out int cantDiasParaDarPorPerdidaUnaCopia))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorValidacionDiasPerdida", "alert('Por favor, ingresa un valor válido para Días para Dar por Pérdida una Copia.');", true);
                return;
            }

            if (!int.TryParse(txtCantDiasPrestamoRegular.Text, out int cantDiasPrestamoRegular))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorValidacionDiasPrestamo", "alert('Por favor, ingresa un valor válido para Días de Préstamo Regular.');", true);
                return;
            }

            if (!int.TryParse(txtCantDiasSinPrestamoPorAtraso.Text, out int cantDiasSinPrestamoPorAtraso))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorValidacionDiasSinAtraso", "alert('Por favor, ingresa un valor válido para Días Sin Préstamo por Atraso.');", true);
                return;
            }

            if (!int.TryParse(txtCantDiasSinPrestamoPorMalEstado.Text, out int cantDiasSinPrestamoPorMalEstado))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorValidacionDiasSinMalEstado", "alert('Por favor, ingresa un valor válido para Días Sin Préstamo por Mal Estado.');", true);
                return;
            }

            if (!int.TryParse(txtCantDiasSinPrestamoPorPerdido.Text, out int cantDiasSinPrestamoPorPerdido))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorValidacionDiasSinPerdido", "alert('Por favor, ingresa un valor válido para Días Sin Préstamo por Perdido.');", true);
                return;
            }

            if (!int.TryParse(txtCantMaxAmpliacionesPermitidasPorPrestamo.Text, out int cantMaxAmpliacionesPermitidasPorPrestamo))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorValidacionMaxAmpliaciones", "alert('Por favor, ingresa un valor válido para Máx. Ampliaciones Permitidas por Préstamo.');", true);
                return;
            }

            if (!int.TryParse(txtCantMaxAtrasosPorCiclo.Text, out int cantMaxAtrasosPorCiclo))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorValidacionMaxAtrasos", "alert('Por favor, ingresa un valor válido para Máx. Atrasos por Ciclo.');", true);
                return;
            }

            if (!int.TryParse(txtCantMaxCopiasPorDevolverPorUsuario.Text, out int cantMaxCopiasPorDevolverPorUsuario))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorValidacionMaxCopias", "alert('Por favor, ingresa un valor válido para Máx. Copias por Devolver por Usuario.');", true);
                return;
            }

            if (!int.TryParse(txtCantMaxMalEstadoPorCiclo.Text, out int cantMaxMalEstadoPorCiclo))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorValidacionMaxMalEstado", "alert('Por favor, ingresa un valor válido para Máx. Mal Estado por Ciclo.');", true);
                return;
            }

            if (!int.TryParse(txtCantMaxPerdidasPorCiclo.Text, out int cantMaxPerdidasPorCiclo))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorValidacionMaxPerdidas", "alert('Por favor, ingresa un valor válido para Máx. Pérdidas por Ciclo.');", true);
                return;
            }

            if (!float.TryParse(txtCargoPorMalEstado.Text, out float cargoPorMalEstado))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorValidacionCargoMalEstado", "alert('Por favor, ingresa un valor válido para Cargo por Mal Estado.');", true);
                return;
            }

            if (!float.TryParse(txtCargoPorPerdido.Text, out float cargoPorPerdido))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorValidacionCargoPerdido", "alert('Por favor, ingresa un valor válido para Cargo por Perdido.');", true);
                return;
            }

            // Validar selección de Mora Asociada
            if (ddlMoraAsociada.SelectedIndex == 0)
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorSeleccionMora", "alert('Por favor, selecciona una Mora Asociada.');", true);
                return;
            }

            if (!int.TryParse(ddlMoraAsociada.SelectedValue, out int idMoraAsociada))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorSeleccionMoraFormato", "alert('Error al obtener el valor seleccionado de la Mora Asociada.');", true);
                return;
            }

            politicaPrestamoBO = new PoliticaPrestamoBO();

            try
            {
                if (accion == "Insertar")
                {
                    // Insertar nueva política de préstamo
                    politicaPrestamoBO.insertar(
                            idMoraAsociada,
                            cantDiasPrestamoRegular,
                            cantMaxCopiasPorDevolverPorUsuario,
                            cantDiasDeAmpliacionRegular,
                            cantMaxAmpliacionesPermitidasPorPrestamo,
                            cantDiasParaDarPorPerdidaUnaCopia,
                            cantDiasSinPrestamoPorAtraso,
                            cantMaxAtrasosPorCiclo,
                            cantDiasSinPrestamoPorMalEstado,
                            cargoPorMalEstado,
                            cantMaxMalEstadoPorCiclo,
                            cantDiasSinPrestamoPorPerdido,
                            cargoPorPerdido,
                            cantMaxPerdidasPorCiclo
                        );
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "SuccessInsertar", "alert('Política de Préstamo registrada correctamente.');", true);
                }
                else if (accion == "Editar")
                {
                    if (Session["idPoliticaPrestamo"] != null && int.TryParse(Session["idPoliticaPrestamo"].ToString(), out int idPoliticaPrestamo))
                    {
                        politicaPrestamoBO.modificar(
                            idPoliticaPrestamo,
                            idMoraAsociada,
                            cantDiasPrestamoRegular,
                            cantMaxCopiasPorDevolverPorUsuario,
                            cantDiasDeAmpliacionRegular,
                            cantMaxAmpliacionesPermitidasPorPrestamo,
                            cantDiasParaDarPorPerdidaUnaCopia,
                            cantDiasSinPrestamoPorAtraso,
                            cantMaxAtrasosPorCiclo,
                            cantDiasSinPrestamoPorMalEstado,
                            cargoPorMalEstado,
                            cantMaxMalEstadoPorCiclo,
                            cantDiasSinPrestamoPorPerdido,
                            cargoPorPerdido,
                            cantMaxPerdidasPorCiclo
                        );
                        ScriptManager.RegisterStartupScript(this, this.GetType(), "SuccessEditar", "alert('Política de Préstamo actualizada correctamente.');", true);
                    }
                    else
                    {
                        // Manejar el caso en que el ID no esté disponible
                        ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorID", "alert('Error: No se pudo obtener el ID de la política de préstamo de la sesión.');", true);
                        return;
                    }
                }

                // Limpiar variables de sesión
                Session.Remove("accionPoliticaPrestamo");
                Session.Remove("idPoliticaPrestamo");

                // Recargar la lista de políticas de préstamo
                cargarPoliticasPrestamo();

                // Cerrar el modal utilizando ClientScript
                string script = "window.onload = function() { hideModalPoliticaPrestamo(); };";
                ClientScript.RegisterStartupScript(this.GetType(), "HideModalScript", script, true);
            }
            catch (Exception ex)
            {
                // Manejar errores durante la operación
                string errorMessage = ex.Message.Replace("'", "\\'");
                string scriptError = $"alert('Error al guardar la política de préstamo: {errorMessage}');";
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorGuardarPoliticaPrestamo", scriptError, true);
            }
        }
        protected void btnCancelarPoliticaPrestamo_Click(object sender, EventArgs e)
        {
            // Limpiar variables de sesión y campos
            Session.Remove("accionPoliticaPrestamo");
            Session.Remove("idPoliticaPrestamo");
            LimpiarCamposPoliticaPrestamo();

            // Cerrar el modal utilizando ClientScript
            string script = "window.onload = function() { hideModalPoliticaPrestamo(); };";
            ClientScript.RegisterStartupScript(this.GetType(), "HideModalScript", script, true);
        }

        private void LimpiarCamposPoliticaPrestamo()
        {
            txtCantDiasDeAmpliacionRegular.Text = "";
            txtCantDiasParaDarPorPerdidaUnaCopia.Text = "";
            txtCantDiasPrestamoRegular.Text = "";
            txtCantDiasSinPrestamoPorAtraso.Text = "";
            txtCantDiasSinPrestamoPorMalEstado.Text = "";
            txtCantDiasSinPrestamoPorPerdido.Text = "";
            txtCantMaxAmpliacionesPermitidasPorPrestamo.Text = "";
            txtCantMaxAtrasosPorCiclo.Text = "";
            txtCantMaxCopiasPorDevolverPorUsuario.Text = "";
            txtCantMaxMalEstadoPorCiclo.Text = "";
            txtCantMaxPerdidasPorCiclo.Text = "";
            txtCargoPorMalEstado.Text = "";
            txtCargoPorPerdido.Text = "";
            ddlMoraAsociada.SelectedIndex = 0;
        }
        protected void lbEliminarPoliticaPrestamo_Click(object sender, EventArgs e)
        {
            if (!int.TryParse(((LinkButton)sender).CommandArgument, out int idPoliticaPrestamo))
            {
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorFormatoIDEliminar", "alert('El ID de la política de préstamo no tiene un formato válido.');", true);
                return;
            }

            politicaPrestamoBO = new PoliticaPrestamoBO();

            try
            {
                int resultado = politicaPrestamoBO.eliminar(idPoliticaPrestamo);

                if (resultado < 0)
                {
                    // Mostrar mensaje de error
                    string scriptError = "alert('Error al eliminar la política de préstamo (Es posible que esté en uso).');";
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorEliminacion", scriptError, true);
                }
                else
                {
                    // Mostrar mensaje de éxito
                    string scriptSuccess = "alert('Política de Préstamo eliminada correctamente.');";
                    ScriptManager.RegisterStartupScript(this, this.GetType(), "EliminacionExitosa", scriptSuccess, true);
                }

                // Recargar la lista de políticas de préstamo
                cargarPoliticasPrestamo();
            }
            catch (Exception ex)
            {
                // Mostrar mensaje de error con interpolación correcta
                string errorMessage = ex.Message.Replace("'", "\\'");
                string scriptError = $"alert('Error al eliminar la política de préstamo: {errorMessage}');";
                ScriptManager.RegisterStartupScript(this, this.GetType(), "ErrorEliminacion", scriptError, true);

                // Recargar la lista de políticas de préstamo
                cargarPoliticasPrestamo();
            }
        }



    }
}