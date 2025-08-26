using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftBibliotecaWA
{
    public partial class BusquedaAvanzada : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            Response.Redirect("Catálogo.aspx");
        }
        protected void btnAgregarAutor_Click(object sender, EventArgs e)
        {
            
        }

        protected void btnTema_Click(object sender, EventArgs e)
        {

        }
    }
}