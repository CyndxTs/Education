using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftBibliotecaWA
{
    public partial class Site1 : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        protected void btnCatalogo_Click1(object sender, EventArgs e)
        {
            Response.Redirect("CatalogoMiembro.aspx");
        }

        protected void btnSolicitudes_Click(object sender, EventArgs e)
        {

        }

        protected void btnUsuario_Click(object sender, EventArgs e)
        {

        }

        protected void btnCerrarSesion_Click(object sender, EventArgs e)
        {
            Response.Redirect("InicioSesion.aspx");
        }

    }
}