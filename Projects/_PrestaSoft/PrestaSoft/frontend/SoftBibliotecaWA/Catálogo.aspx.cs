using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftBibliotecaWA
{
    public partial class Catalogo : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        protected void btnLogin_Click(object sender, EventArgs e)
        {
            //// Aquí validas las credenciales del usuario (nombre de usuario y contraseña)
            //string username = txtUsername.Text;
            //string password = txtPassword.Text;

            //// Supongamos que validas las credenciales con algún método
            //if (ValidarCredenciales(username, password)) // Implementa tu lógica de validación
            //{
            //    // Inicia sesión (puedes usar FormsAuthentication si es necesario)
            //    Session["Usuario"] = username; // Guarda el usuario en la sesión
            //    Response.Redirect("Catálogo.aspx"); // Redirige al catálogo
            //}
            //else
            //{
            //    // Maneja el caso de error (credenciales incorrectas)
            //    lblError.Text = "Nombre de usuario o contraseña incorrectos.";
            //}

            Response.Redirect("Catálogo.aspx");
        }
    }
}