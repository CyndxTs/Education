using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftBibliotecaBO.ServicioWeb;
using SoftBibliotecaPrestamosBO;
using SoftBibliotecaUsuarioBO;

namespace SoftBibliotecaWA
{
    public partial class SolicitudesMiembro : System.Web.UI.Page
    {
        private ReservaBO reservaBO;
        private usuario usuarioSesion;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                this.usuarioSesion = (usuario)Session["Usuario"];
                CargaReservas();
            }
        }

        private void CargaReservas()
        {
            //ARREGLAR

            reservaBO = new ReservaBO();

            BindingList<reserva> reservas = reservaBO.mostrarReservasEnCurso(usuarioSesion.idUsuario);



            gvReservas.DataSource = reservas;
            gvReservas.DataBind();

        }



        protected void CancelarReserva(object sender, EventArgs e)
        {
            //ARREGLAR

            Button btnCancelar = (Button)sender;
            int idReserva = Convert.ToInt32(btnCancelar.CommandArgument);
            reservaBO = new ReservaBO();
            reservaBO.cancelarReserva(idReserva);
            Response.Redirect("SolicitudesMiembro.aspx");
        }


        protected void BtnPrestamos_Click(object sender, EventArgs e)
        {
            Response.Redirect("PrestamosMiembro.aspx");
        }

        protected void BtnInformeSolicitudes_Click(object sender, EventArgs e)
        {
            Response.Redirect("InformeSolicitudes.aspx");
        }


    }
}