using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftBibliotecaBO.ServicioWeb;

namespace SoftBibliotecaWA
{
    public partial class MaestroBibliotecario : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        public void CambiarNombre(string nombre)
        {
            NombreUsuarioBibliotecario.Text = nombre;
        }

        protected void btnInicio_Click(object sender, EventArgs e)
        {
            // Código para el evento de registro de préstamo.
            usuario us = (usuario)Session["Usuario"];
            Session.Clear();
            Session["Usuario"] = us;
            Response.Redirect("CatalogoBibliotecario.aspx");
        }

        protected void btnReporteMiembro_Click(object sender, EventArgs e)
        {
            Response.Redirect("ReporteMiembroBibliotecario.aspx");
        }

        protected void btnReportePubli_Click(object sender, EventArgs e)
        {
            // Código para el evento de gestión de publicaciones.
        }

        protected void btnConfiguracion_Click(object sender, EventArgs e)
        {
            // Código para el evento de búsqueda avanzada.
        }

        protected void btnCerrarSesion_Click(object sender, EventArgs e)
        {
            Session.Clear();
            Response.Redirect("InicioSesion.aspx");
        }
        protected void btnRegistrarPrestamo_Click(object sender, EventArgs e)
        {
            Session.Clear();
            Response.Redirect("RegistrarPrestamoBibliotecario.aspx");
        }

        protected void btnRegistrarDevolucion_Click(object sender, EventArgs e)
        {
            // Código para el evento de registro de préstamo.
        }

    }
}