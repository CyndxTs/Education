using SoftBibliotecaBO.ServicioWeb;
using SoftBibliotecaPrestamosBO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftBibliotecaWA
{
    public partial class ReporteSolicitudes : System.Web.UI.Page
    {
        private PrestamoBO prestamoBO;
        protected void Page_Load(object sender, EventArgs e)
        {
            this.prestamoBO = new PrestamoBO();

            int idUsuario = (int)Session["idUsuario"];
            string fechaMin = (string)Session["FechaMin"] ;
            string fechaMax = (string)Session["FechaMax"];
            string estadoDePrestamos = (string)Session["EstadoDePrestamos"];
            int idUniversidad = (int)Session["IdUniversidad"];
            int mostrarONoReservas= (int)Session["MostrarONoReservas"];
            string descripcion = (string)Session["Descripcion"];

            byte[] reporte = prestamoBO.reporteSolicitudes(idUsuario, fechaMin, fechaMax, estadoDePrestamos, idUniversidad, mostrarONoReservas, descripcion);

            this.prestamoBO.abrirReporte(Response, "ReporteSolicitudes.pdf", reporte);
            Session["IdUsuario"] = null;
            Session["FechaMin"] = null;
            Session["FechaMax"] = null;
            Session["EstadoDePrestamos"] = null;
            Session["IdUniversidad"] = null;
            Session["MostrarONoReservas"] = null;
            Session["Descripcion"] = null;

        }
    }
}