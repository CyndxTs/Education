using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftBibliotecaBO.ServicioWeb;
using SoftBibliotecaPublicacionBO;

namespace SoftBibliotecaWA
{
    public partial class MaestroMiembro : System.Web.UI.MasterPage
    {

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Session["Usuario"] != null)
                {
                    usuario us = (usuario)Session["Usuario"];
                    lblUsuarioNombre.Text = us.nombreUsuario;
                }

            }
            else
            {

            }
        }

        public void CambiarNombre(string nombre)
        {
            lblUsuarioNombre.Text = nombre;
        }

        protected void btnCatalogo_Click(object sender, EventArgs e)
        {
            usuario us = (usuario)Session["Usuario"];
            Session.Clear();
            Session["Usuario"] = us;
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
            Session.Clear();
            Response.Redirect("InicioSesion.aspx");
        }

        protected void btnBuscar_ClickMaster(object sender, EventArgs e)
        {

        }
    }
}