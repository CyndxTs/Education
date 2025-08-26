using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftBibliotecaBO.ServicioWeb;
using SoftBibliotecaUniversidadBO;

namespace SoftBibliotecaWA
{
    public partial class WebForm1 : System.Web.UI.Page
    {
        private UniversidadBO universidadBO;
        private usuario orig;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                universidadBO = new UniversidadBO();
                BindingList<universidad> universidades = universidadBO.listarTodos(-1, -1, -1);
                ddlUniversidad.Items.Clear();
                ddlUniversidad.Items.Add(new ListItem("Seleccione una universidad", "0")); // opción por defecto

                // Agregar las universidades al DropDownList
                foreach (var universidad in universidades)
                {
                    ddlUniversidad.Items.Add(new ListItem(universidad.nombre, universidad.idUniversidad.ToString()));
                }

            }

        }
        protected void btnDescargarInforme_Click(object sender, EventArgs e)
        {
            if (txtFechaDel.Value == null || txtFechaDel.Value == "" || txtFechaHasta.Value == null || txtFechaHasta.Value == "")
            {
                string scriptCorrecto = "alert('Ingrese fechas válidas por favor.');";
                ClientScript.RegisterStartupScript(this.GetType(), "Ingresar fechas correctas", scriptCorrecto, true);
                return;
            }
            if ( (txtFechaDel.Value).CompareTo( (txtFechaHasta.Value) ) > 0  )
            {
                string scriptCorrecto = "alert('Ingrese fechas válidas por favor.');";
                ClientScript.RegisterStartupScript(this.GetType(), "Ingresar fechas correctas", scriptCorrecto, true);
                return;
            }
            usuario usuarioQueQuiereReporte = ((usuario)Session["Usuario"]);
            int idUsuario = usuarioQueQuiereReporte.idUsuario;
            string fechaMin = txtFechaDel.Value; // Suponiendo que txtFechaMin es un control de tipo Date
            string fechaMax = txtFechaHasta.Value; // Suponiendo que txtFechaMax es un control de tipo Date
            string estadoDePrestamos = ddlEstadoPrestamos.SelectedValue; // Suponiendo que ddlEstadoPrestamos es un DropDownList
            int idUniversidad = int.Parse(ddlUniversidad.SelectedValue);

            int mostrarONoReservas = chkMostrarReservas.Checked ? 1 : 0; // Suponiendo que chkMostrarReservas es un CheckBox
            UniversidadBO universidadBO = new UniversidadBO();
            universidad universidadParaReporte = new universidad();
            if (idUniversidad > 0) 
                universidadParaReporte = universidadBO.obtenerPorId(idUniversidad);

            string descripcion = "de " + usuarioQueQuiereReporte.nombres + " " + usuarioQueQuiereReporte.apellidos + " de ";
            descripcion += idUniversidad == 0 ? " ninguna universidad en particular." : ("la universidad " + universidadParaReporte.nombre + ".");

            // Almacenar los valores en la sesión (si se requiere)
            Session["IdUsuario"] = idUsuario;
            Session["FechaMin"] = fechaMin;
            Session["FechaMax"] = fechaMax;
            Session["EstadoDePrestamos"] = estadoDePrestamos;
            Session["IdUniversidad"] = idUniversidad;
            Session["MostrarONoReservas"] = mostrarONoReservas;
            Session["Descripcion"] = descripcion;

            // Redirigir a la página del reporte
            Response.Redirect("ReporteSolicitudes.aspx");


        }
    }
}