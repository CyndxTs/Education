using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftBibliotecaBO;
using SoftBibliotecaBO.ServicioWeb;

namespace SoftBibliotecaUsuarioBO
{
    public class ReservaBO : BaseBO
    {

        /*
        public int insertar(string instanteReserva, string instanteRecojo, string tipoEstado,
                                int idEjemplarAsociado, int idUsuarioAsociado)
        {
            return this.WsCliente.reserva_insertar(instanteReserva, instanteRecojo, tipoEstado,idEjemplarAsociado,idUsuarioAsociado);
        }

        public int modificar(int idReserva,string instanteReserva, string instanteRecojo, string tipoEstado,
                                int idEjemplarAsociado, int idUsuarioAsociado)
        {
            return this.WsCliente.reserva_modificar(idReserva,instanteReserva, instanteRecojo, tipoEstado, idEjemplarAsociado, idUsuarioAsociado);
        }

        public int eliminar(int idReserva)
        {
            return this.WsCliente.reserva_eliminar(idReserva);
        }

        public BindingList<reserva> listarTodos()
        {
            try
            {
                reserva[] reservas = this.WsCliente.reserva_listarTodos();
                if (reservas == null) return new BindingList<reserva>();
                return new BindingList<reserva>(reservas);
            }
            catch (Exception e)
            {
                return new BindingList<reserva>();
            }
        }

        public BindingList<reserva> listarTodos(string instanteReserva, string instanteRecojo,
                                                string tipoEstado, int idEjemplarAsociado, int idUsuarioAsociado)
        {
            try
            {
                reserva[] reservas = this.WsCliente.reserva_listarTodos_porParametros(instanteReserva, instanteRecojo, tipoEstado, idEjemplarAsociado, idUsuarioAsociado);
                if (reservas == null) return new BindingList<reserva>();
                return new BindingList<reserva>(reservas);
            }
            catch (Exception e)
            {
                return new BindingList<reserva>();
            }
        }

        public reserva obtenerPorId(int idReserva)
        {
            return this.WsCliente.reserva_obtenerPorId(idReserva);
        }
        */


        public BindingList<reserva> mostrarReservasEnCurso(int idUsuario)
        {
            try
            {
                reserva[] reservas = this.WsCliente.mostrarReservasEnCurso(idUsuario);
                if (reservas == null) return new BindingList<reserva>();
                return new BindingList<reserva>(reservas);
            }
            catch (Exception e)
            {
                return new BindingList<reserva>();
            }
        }

        public int cancelarReserva(int idReserva)
        {
            return this.WsCliente.cancelarReserva(idReserva);
        }
        //cambios giano reservaBO
        public reserva solicitarReserva(int idUsuario, int idPublicacion, string tipoPublicacion)
        {
            return this.WsCliente.solicitarReserva(idUsuario, idPublicacion, tipoPublicacion);

        }
        public int confirmarReserva(int idUsuario, int idEjemplarAsociado, string instanteRecojoMax)
        {
            return this.WsCliente.confirmarReserva(idUsuario, idEjemplarAsociado, instanteRecojoMax);

        }
        //cambios fin
    }
}
