using System;
using System.Data.SqlClient;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftBibliotecaBO.ServicioWeb;
using SoftBibliotecaUsuarioBO;

//ARREGLADO

namespace SoftBibliotecaWA
{
    public partial class InicioSesion : System.Web.UI.Page
    {
        private UsuarioBO usuarioBO;

        public InicioSesion()
        {
            this.usuarioBO = new UsuarioBO();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
        }

        protected void btnIniciarSesion_Click(object sender, EventArgs e)
        {
            string correo = txtEmail.Text;
            string contrasenia = txtPassword.Text;

            usuario usu = usuarioBO.iniciarSesion(correo, contrasenia);

            if (usu != null)
            {
                Session["NombreUsuario"] = usu.nombreUsuario;
                Session["Usuario"] = usu;
                Session["idUsuario"] = usu.idUsuario;
                string redirectPage = string.Empty;
                string tipo = usu.tipoUsuario.ToString().ToUpper();
                switch (tipo)
                {
                    case "MIEMBRO":
                        redirectPage = "CatalogoMiembro.aspx";
                        break;
                    case "PROGRAMADOR":
                        redirectPage = "MenuProgramador.aspx"; //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        break;
                    case "ADMINISTRADOR":
                        redirectPage = "MenuAdministrador.aspx";
                        break;
                    case "BIBLIOTECARIO":
                        redirectPage = "BibliotecarioPrestamos.aspx";
                        break;
                    case "SANCIONADO":
                        redirectPage = "CatalogoMiembro.aspx";
                        break;
                    case "INACTIVO":
                        redirectPage = "InicioSesion.aspx";
                        break;

                        /*
                    default:
                        lblError.Text = "Tipo de usuario no válido.";
                        return;
                        */
                }
                Response.Redirect(redirectPage);
            }
            else
            {
                string scriptUsuarioExistente = "alert('Correo o contraseña incorrecta.');";
                ClientScript.RegisterStartupScript(this.GetType(), "Usuario Existente", scriptUsuarioExistente, true);
                return;
            }
        }
    }
}
