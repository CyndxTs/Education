using NodaTime;
using SoftBibliotecaBO.ServicioWeb;
using SoftBibliotecaPrestamosBO;
using SoftBibliotecaPublicacionBO;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftBibliotecaWA
{
    public partial class BibliotecarioPrestamos : System.Web.UI.Page
    {
        private PrestamoBO prestamosbo;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                cargarPrestamos();
            }
        }

        protected string FormatearFecha(object fecha)
        {
            if (fecha == null || fecha == DBNull.Value)
                return string.Empty;
            if (fecha is DateTime)
            {
                return ((DateTime)fecha).ToString("dd/MM/yyyy HH:mm");
            }

            if (fecha is LocalDateTime)
            {
                var fechaLocal = (LocalDateTime)fecha;
                return fechaLocal.ToDateTimeUnspecified().ToString("dd/MM/yyyy HH:mm");
            }

            return fecha.ToString();
        }

        protected void cargarPrestamos()
        {

            prestamosbo = new PrestamoBO();
            BindingList<prestamo> prestamos;
            prestamos = prestamosbo.mostrarUltimosPrestamos(10);
            rptPrestamos.DataSource = prestamos;
            rptPrestamos.DataBind();
        }

        protected void rptPrestamos_ItemDataBound(object sender, RepeaterItemEventArgs e)
        {
            if (e.Item.ItemType == ListItemType.Item || e.Item.ItemType == ListItemType.AlternatingItem)
            {
                var prestamo = (prestamo)e.Item.DataItem;
                var lblNombre = (Label)e.Item.FindControl("lblNombre");

                if (lblNombre != null)
                {
                    //lblNombre.Text = prestamo.Nombre;
                }
            }
        }
    }
}