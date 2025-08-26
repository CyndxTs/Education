using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Configuration;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftBibliotecaWA
{
    public partial class MasterPageAdmin : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }



        protected void btnConfUs_Click(object sender, EventArgs e)
        {
            //meter configuracion de usuario
            //Response.Redirect("InicioSesion.aspx");
        }

        protected void btnCerrarSesion_Click(object sender, EventArgs e)
        {
            Response.Redirect("InicioSesion.aspx");
        }
    }
}