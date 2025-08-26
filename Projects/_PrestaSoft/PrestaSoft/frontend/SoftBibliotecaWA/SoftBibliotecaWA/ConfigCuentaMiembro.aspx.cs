using System;
using SoftBibliotecaBO.ServicioWeb;
using SoftBibliotecaUsuarioBO;

namespace SoftBibliotecaWA
{
    public partial class ConfigCuenta : System.Web.UI.Page
    {
        private UsuarioBO usuarioBO;
        private usuario usuarioOriginal;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                usuarioBO = new UsuarioBO();
                usuarioOriginal = (usuario)Session["Usuario"];
                Session["UsuarioOriginal"] = usuarioOriginal;

                if (usuarioOriginal != null)
                {
                    txtNombreUsuario.Text = usuarioOriginal.nombreUsuario;
                    txtNombres.Text = usuarioOriginal.nombres;
                    txtApellidos.Text = usuarioOriginal.apellidos;
                    txtCorreo.Text = usuarioOriginal.correoInstitucional;
                    txtContrasena.Text = "▪▪▪▪▪▪▪▪";//MASCARA INICIAL

                    SetReadOnly(true);
                }
            }
        }

        protected void btnModificar_Click(object sender, EventArgs e)
        {
            SetReadOnly(false);
            usuario usuario = (usuario)Session["UsuarioOriginal"];
            txtContrasena.Text = usuario.contrasenia;
            btnMostrar.Text = "Ocultar";
            btnMostrar.Enabled = false;
            btnGuardarCambios.Visible = true;
            btnCancelar.Visible = true;
            btnModificar.Visible = false;
        }

        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            SetReadOnly(true);

            //RESTAURA A VALORES SIN CAMBIOS
            usuarioOriginal = (usuario)Session["UsuarioOriginal"];
            txtNombreUsuario.Text = usuarioOriginal.nombreUsuario;
            txtNombres.Text = usuarioOriginal.nombres;
            txtApellidos.Text = usuarioOriginal.apellidos;
            txtCorreo.Text = usuarioOriginal.correoInstitucional;
            txtContrasena.Text = usuarioOriginal.contrasenia;
            btnMostrar.Enabled = true;
            btnGuardarCambios.Visible = false;
            btnCancelar.Visible = false;
            btnModificar.Visible = true;
        }

        protected void btnGuardarCambios_Click(object sender, EventArgs e)
        {
            usuarioBO = new UsuarioBO();
            usuario usuarioConCambios = (usuario)Session["Usuario"];

            if (usuarioConCambios != null)
            {
                int verifica = usuarioBO.modificar(
                    usuarioConCambios.idUsuario,
                    txtNombres.Text,
                    txtApellidos.Text,
                    txtCorreo.Text,
                    txtContrasena.Text,
                    txtNombreUsuario.Text,
                    usuarioConCambios.fechaRegistro.ToString(),
                    usuarioConCambios.tipoUsuario.ToString()
                );

                if (verifica > 0)
                {
                    usuarioConCambios.nombres = txtNombres.Text;
                    usuarioConCambios.apellidos = txtApellidos.Text;
                    usuarioConCambios.correoInstitucional = txtCorreo.Text;
                    usuarioConCambios.contrasenia = txtContrasena.Text;
                    usuarioConCambios.nombreUsuario = txtNombreUsuario.Text;
                    Session["Usuario"] = usuarioConCambios;
                    Session["UsuarioOriginal"] = usuarioConCambios;

                    (this.Master as MaestroMiembro).CambiarNombre(usuarioConCambios.nombreUsuario);

                    string scriptExito = "alert('Los cambios se guardaron correctamente.');";
                    ClientScript.RegisterStartupScript(this.GetType(), "Exito", scriptExito, true);
                }
                else
                {
                    string scriptError = "alert('Error al guardar los cambios. Por favor, inténtelo de nuevo.');";
                    ClientScript.RegisterStartupScript(this.GetType(), "ErrorModificar", scriptError, true);
                }

                SetReadOnly(true);
                btnMostrar.Enabled = true;
                btnGuardarCambios.Visible = false;
                btnCancelar.Visible = false;
                btnModificar.Visible = true;
            }
        }

        private void SetReadOnly(bool isReadOnly)
        {
            txtNombreUsuario.ReadOnly = isReadOnly;
            txtNombres.ReadOnly = isReadOnly;
            txtApellidos.ReadOnly = isReadOnly;
            txtCorreo.ReadOnly = isReadOnly;
            txtContrasena.ReadOnly = isReadOnly;
        }

        protected void btnMostrar_Click(object sender, EventArgs e)
        {
            usuario usuario = (usuario)Session["UsuarioOriginal"];
            if (txtContrasena.Text == "▪▪▪▪▪▪▪▪")
            {
                btnMostrar.Text = "Ocultar";
                txtContrasena.Text = usuario.contrasenia;
            }
            else
            {
                btnMostrar.Text = "Mostrar";
                txtContrasena.Text = "▪▪▪▪▪▪▪▪";
            }

        }
    }
}
