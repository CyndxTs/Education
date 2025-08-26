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
    public partial class ReportesBibliotecario : System.Web.UI.Page
    {
        private UniversidadBO universidadBO;
        private usuario orig;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                universidadBO = new UniversidadBO();
                BindingList<universidad> universidades = universidadBO.listarTodos(-1, -1, -1);
            
                ddlUniversidad.DataTextField = "nombre";
                ddlUniversidad.DataValueField = "idUniversidad";
                ddlUniversidad.DataSource = universidades;
                ddlUniversidad.DataBind();
                ddlUniversidad.Items.Insert(0, new ListItem("Seleccione una universidad", "0"));

                ddlUniversidadPublicaciones.DataTextField = "nombre";
                ddlUniversidadPublicaciones.DataValueField = "idUniversidad";
                ddlUniversidadPublicaciones.DataSource = universidades;
                ddlUniversidadPublicaciones.DataBind();
                ddlUniversidadPublicaciones.Items.Insert(0, new ListItem("Seleccione una universidad", "0"));



            }



        }



        protected void btnDescargarReporteMiembros_Click(object sender, EventArgs e)
        {
            // Capturar los valores del formulario
            if (txtCantidadSancionesMin.Text == null || txtCantidadSancionesMin.Text == "" || txtCantidadSancionesMax.Text == null || txtCantidadSancionesMax.Text == "")
            {
                string scriptCorrecto = "alert('Ingrese valores válidos por favor.');";
                ClientScript.RegisterStartupScript(this.GetType(), "Ingresar valores correctos", scriptCorrecto, true);
                return;
            }
            if (txtFechaRegistroDesde.Text == null || txtFechaRegistroDesde.Text == "" || txtFechaRegistroHasta.Text == null || txtFechaRegistroHasta.Text == "")
            {
                string scriptCorrecto = "alert('Ingrese fechas válidas por favor.');";
                ClientScript.RegisterStartupScript(this.GetType(), "Ingresar fechas correctas", scriptCorrecto, true);
                return;
            }
            if ((txtFechaRegistroDesde.Text).CompareTo((txtFechaRegistroHasta.Text)) >= 0)
            {
                string scriptCorrecto = "alert('Ingrese fechas válidas por favor.');";
                ClientScript.RegisterStartupScript(this.GetType(), "Ingresar fechas correctas", scriptCorrecto, true);
                return;
            }

            int cantidadSancionesMin = string.IsNullOrEmpty(txtCantidadSancionesMin.Text) ? 0 : int.Parse(txtCantidadSancionesMin.Text);
            int cantidadSancionesMax = string.IsNullOrEmpty(txtCantidadSancionesMax.Text) ? 0 : int.Parse(txtCantidadSancionesMax.Text);
            string fechaRegistroDesde = txtFechaRegistroDesde.Text;  // Fecha desde
            string fechaRegistroHasta = txtFechaRegistroHasta.Text;  // Fecha hasta
            int idUniversidad = int.Parse(ddlUniversidad.SelectedValue);
            UniversidadBO universidadBO = new UniversidadBO();
            universidad universidadParaReporte = new universidad();
            if (idUniversidad > 0)
                universidadParaReporte = universidadBO.obtenerPorId(idUniversidad);
            string descripcion = idUniversidad == 0 ? "de ninguna universidad en particular." : ("de la universidad " + universidadParaReporte.nombre + ".");

            // Guardar los valores en la sesión para ser utilizados en la página siguiente
            Session["CantidadSancionesMin"] = cantidadSancionesMin;
            Session["CantidadSancionesMax"] = cantidadSancionesMax;
            Session["FechaRegistroDesde"] = fechaRegistroDesde;
            Session["FechaRegistroHasta"] = fechaRegistroHasta;
            Session["IdUniversidad"] = idUniversidad;
            Session["Descripcion"] = descripcion;

            // Redirigir a la página de reporte
            Response.Redirect("ReporteMiembros.aspx");


        }

        protected void btnDescargarReportePublicaciones_Click(object sender, EventArgs e)
        {
            if(txtNumberMin.Text == null || txtNumberMin.Text == "" )
            {
                string scriptCorrecto = "alert('Ingrese valores válidos por favor.');";
                ClientScript.RegisterStartupScript(this.GetType(), "Ingresar valores correctos", scriptCorrecto, true);
                return;
            }
            
            int cantidadMin = int.Parse(txtNumberMin.Text);
            int cantidadMax = int.Parse(txtNumberMax.Text);
            bool incluirDigitales = ! CheckBox1.Checked;
            int idUniversidad = int.Parse(ddlUniversidad.SelectedValue);
            UniversidadBO universidadBO = new UniversidadBO();
            universidad universidadParaReporte = new universidad();
            if (idUniversidad > 0)
                universidadParaReporte = universidadBO.obtenerPorId(idUniversidad);
            string descripcion = idUniversidad == 0 ? "de ninguna universidad en particular." : ("de la universidad " + universidadParaReporte.nombre + ".");

            // Almacenar los valores en la sesión
            Session["CantidadMin"] = cantidadMin;
            Session["CantidadMax"] = cantidadMax;
            Session["incluirDigitales"] = incluirDigitales;
            Session["idUniversidad"] = idUniversidad;
            Session["Descripcion"] = descripcion;

            // Opcional: redirigir a otra página para mostrar el reporte o procesar los datos
            Response.Redirect("ReportePublicaciones.aspx");



        }
    }
}