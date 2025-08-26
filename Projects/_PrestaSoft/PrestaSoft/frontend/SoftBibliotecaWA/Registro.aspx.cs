using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftBibliotecaWA
{
    public partial class Registro : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            Response.Redirect("Inicio.aspx"); 
        }
        protected void btnAgregarUniversidad_Click(object sender, EventArgs e)
        {
            // Hacer visible el contenedor de la segunda universidad
            universidad2Container.Visible = true;
        }


    }
}