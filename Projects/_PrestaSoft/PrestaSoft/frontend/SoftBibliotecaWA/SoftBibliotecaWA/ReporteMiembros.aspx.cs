using SoftBibliotecaBO.ServicioWeb;
using SoftBibliotecaPrestamosBO;
using SoftBibliotecaUsuarioBO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftBibliotecaWA
{
    public partial class ReporteMiembros : System.Web.UI.Page
    {
        private UsuarioBO usuarioBO;
        protected void Page_Load(object sender, EventArgs e)
        {
            usuarioBO = new UsuarioBO();
            int cantidadSancionesMin = (int)Session["CantidadSancionesMin"];
            int cantidadSancionesMax = (int)Session["CantidadSancionesMax"];
            string fechaRegistroDesde = (string)Session["FechaRegistroDesde"];
            string fechaRegistroHasta = (string)Session["FechaRegistroHasta"];
            int idUniversidad = (int)Session["IdUniversidad"];
            string descripcion = (string)Session["Descripcion"];

            // Usar estos valores para generar el reporte
            byte[] reporte = usuarioBO.reporteMiembros(fechaRegistroDesde, fechaRegistroHasta, cantidadSancionesMin, cantidadSancionesMax, idUniversidad, 50, descripcion);
            
            this.usuarioBO.abrirReporte(Response, "ReporteMiembros.pdf", reporte);

            Session["CantidadSancionesMin"] = null;
            Session["CantidadSancionesMax"] = null;
            Session["FechaRegistroDesde"] = null;
            Session["FechaRegistroHasta"] = null;
            Session["IdUniversidad"] = null;
            Session["Descripcion"] = null;
        }
    }
}