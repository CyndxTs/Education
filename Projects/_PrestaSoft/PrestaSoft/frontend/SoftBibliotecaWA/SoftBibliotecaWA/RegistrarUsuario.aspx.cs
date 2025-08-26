using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Xml.Linq;
using SoftBibliotecaBO.ServicioWeb;
using SoftBibliotecaPolitica;
using SoftBibliotecaUniversidadBO;
using SoftBibliotecaUsuarioBO;

//ARREGLADO

namespace SoftBibliotecaWA
{
    public partial class RegistrarUsuario : System.Web.UI.Page
    {
        private UsuarioBO usuarioBO;

        public RegistrarUsuario()
        {
            this.usuarioBO = new UsuarioBO();
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                cargarUniversidades();
            }
        }

        private void cargarUniversidades()
        {
            UniversidadBO univerisdadBO = new UniversidadBO();
            BindingList<universidad> universidades = univerisdadBO.listarTodos(-1, -1, -1);
            ddlCorreoParte2.DataSource = universidades;
            ddlCorreoParte2.DataTextField = "extensionDominioCorreo";
            ddlCorreoParte2.DataValueField = "idUniversidad";
            ddlCorreoParte2.DataBind();
            ddlCorreoParte2.Items.Insert(0, new ListItem("Seleccione una extensión institucional..", ""));

        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("InicioSesion.aspx");
        }

        protected void btnRegistrar_Click(object sender, EventArgs e)
        {
            BindingList<int> bindingList = new BindingList<int>();
            string apepaterno = txtApePaterno.Text;
            string apematerno = txtApeMaterno.Text;
            string apellidos = apepaterno + " " + apematerno;
            string contrasenia = txtContrasenia.Text;
            string contraseniaconfirmacion = txtContraseniaConfirmacion.Text;
            string nombre = txtNombre.Text;
            string correo = txtCorreoParte1.Text + ddlCorreoParte2.SelectedItem.Text;
            string nombreusuario = txtNombreUsuario.Text;
            string universidad = ddlCorreoParte2.Text;

            if (apepaterno != "" && apematerno != "" && contrasenia != "" && contraseniaconfirmacion != "" && nombre != "" && correo != "" && nombreusuario != "" && universidad != "")
            {
                int verifica = usuarioBO.registrarMiembro(nombre, apellidos, correo, contrasenia, contraseniaconfirmacion, nombreusuario, universidad);
                if (verifica > 0)
                {
                    string scriptCorrecto = "alert('Registro completado con éxito.');";
                    ClientScript.RegisterStartupScript(this.GetType(), "Registro Exitoso", scriptCorrecto, true);
                    Response.Redirect("InicioSesion.aspx");
                }
                else
                {
                    if (verifica == -1)
                    {
                        string scriptUsuarioExistente = "alert('El nombre de usuario ya existe. Por favor, elija otro.');";
                        ClientScript.RegisterStartupScript(this.GetType(), "Usuario Existente", scriptUsuarioExistente, true);
                        return;
                    }
                    else if (verifica == -2)
                    {
                        string scriptContraseniasDistintas = "alert('Las contraseñas no coinciden. Por favor, verifique e intente nuevamente.');";
                        ClientScript.RegisterStartupScript(this.GetType(), "Contraseñas Distintas", scriptContraseniasDistintas, true);
                        return;
                    }
                    else if (verifica == -3)
                    {
                        string scriptContraseniaCorta = "alert('La contraseña debe tener al menos 6 caracteres.');";
                        ClientScript.RegisterStartupScript(this.GetType(), "Contraseña Corta", scriptContraseniaCorta, true);
                        return;
                    }
                    else if (verifica == -4)
                    {
                        string scriptEmailExiste = "alert('El correo ya está registrado. Intente con otro.');";
                        ClientScript.RegisterStartupScript(this.GetType(), "Correo Existente", scriptEmailExiste, true);
                        return;
                    }
                    else
                    {
                        string scriptUsernameExiste = "alert('El nombre de usuario ya está en uso. Por favor, elija uno diferente.');";
                        ClientScript.RegisterStartupScript(this.GetType(), "Nombre Usuario Existente", scriptUsernameExiste, true);
                        return;
                    }
                }
            }
            else
            {
                string scriptUsernameExiste = "alert('Por favor complete todos los campos solicitados.');";
                ClientScript.RegisterStartupScript(this.GetType(), "Nombre Usuario Existente", scriptUsernameExiste, true);
                return;
            }




        }
    }
}