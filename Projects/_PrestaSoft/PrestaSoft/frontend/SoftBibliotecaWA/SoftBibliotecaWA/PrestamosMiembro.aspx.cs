using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftBibliotecaBO.ServicioWeb;
using SoftBibliotecaPrestamosBO;

namespace SoftBibliotecaWA
{
    public partial class PrestamoMiembro : System.Web.UI.Page
    {
        private PrestamoBO prestamoBO;
        private usuario usuarioSesion;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                this.usuarioSesion = (usuario)Session["Usuario"];
                CargaPrestamos();
            }

        }

        private void CargaPrestamos()
        {
            //ARREGLAR

            prestamoBO = new PrestamoBO();

            BindingList<prestamo> prestamos = prestamoBO.mostrarPrestamosEnCurso(usuarioSesion.idUsuario, 0);
            gvPrestamo.DataSource = prestamos;
            gvPrestamo.DataBind();
        }

        protected void ExtenderPrestamo(object sender, EventArgs e)
        {
            //solo abre el modal y calcular la fecha limite
            Button btnExtender = (Button)sender;
            
            int idPrestamo = Convert.ToInt32(btnExtender.CommandArgument);

            prestamoBO = new PrestamoBO();
            prestamo prestamoSeleccionado = prestamoBO.obtenerPorId(idPrestamo);
            int cantDiasAmpliacion = prestamoSeleccionado.ejemplarAsociado.publicacionAsociada.bibliotecaAsociada.universidadAsociada.politicaRegular.politicasPorPrestamo.cantDiasDeAmpliacionRegular;
            Session["idPrestamo"] = prestamoSeleccionado.idPrestamo;
            txtAmpliacion.Text = "Su ampliación va a ser de " + cantDiasAmpliacion + " dias";
            string script = "window.onload = function() { showModalUniversidad(); };";
            ClientScript.RegisterStartupScript(this.GetType(), "ShowModalScript", script, true);
        }
        protected void ConfirmarAmpliacion_Click(object sender, EventArgs e)
        {

            int id = (int)Session["idPrestamo"];
            prestamoBO = new PrestamoBO();
            int resultado = prestamoBO.confirmarAmpliacion(id);
            if (resultado > 0)
            {
                string scriptCorrecto = "alert('Ampliación solicitada con éxito.');";
                ClientScript.RegisterStartupScript(this.GetType(), "Ampliación Exitosa", scriptCorrecto, true);
                Response.Redirect("PrestamosMiembro.aspx");
            }
            else
            {
                string scriptError = "alert('No cumple con los requerimientos para solicitar una ampliación.');";
                ClientScript.RegisterStartupScript(this.GetType(), "Error", scriptError, true);
                Response.Redirect("PrestamosMiembros.aspx");
            }
        }

        protected void BtnReservas_Click(object sender, EventArgs e)
        {
            Response.Redirect("SolicitudesMiembro.aspx");
        }

        protected void BtnInformeSolicitudes_Click(object sender, EventArgs e)
        {
            Response.Redirect("InformeSolicitudes.aspx");
        }

    }
}