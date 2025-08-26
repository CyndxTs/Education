using SoftBibliotecaPrestamosBO;
using SoftBibliotecaPublicacionBO;
using SoftBibliotecaUsuarioBO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftBibliotecaWA
{
    public partial class ReportePublicaciones : System.Web.UI.Page
    {
        private PublicacionBO publicacionBO;
        protected void Page_Load(object sender, EventArgs e)
        {
            this.publicacionBO = new PublicacionBO();

            // Recuperar los valores almacenados en la sesión
            int cantidadMin = (int)Session["CantidadMin"];
            int cantidadMax = (int)Session["CantidadMax"];
            bool incluirDigitales = (bool)Session["IncluirDigitales"];
            int idUniversidad = (int)Session["IdUniversidad"];
            string descripcion = (string)Session["Descripcion"];

            // Llamar al método que genera el reporte, pasando los parámetros recuperados de la sesión
            byte[] reporte = publicacionBO.reportePublicaciones(cantidadMin, cantidadMax, incluirDigitales==true?1:0, idUniversidad, 30, descripcion);

            // Abrir el reporte en el navegador
            this.publicacionBO.abrirReporte(Response, "ReportePublicaciones.pdf", reporte);
            Session["CantidadMin"] = null;
            Session["CantidadMax"] = null;
            Session["incluirDigitales"] = null;
            Session["idUniversidad"] = null;
            Session["Descripcion"] = null;
        }
    }
}