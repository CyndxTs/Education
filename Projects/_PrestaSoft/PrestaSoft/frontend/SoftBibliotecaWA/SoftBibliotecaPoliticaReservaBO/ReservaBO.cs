using SoftBibliotecaBO;
using SoftBibliotecaBO.ServicioWeb;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.ServiceModel.Channels;
using System.Text;
using System.Threading.Tasks;

namespace SoftBibliotecaReservaBO
{
    public class ReservaBO:BaseBO
    {
        
        public BindingList<reserva> listarPorParametros(string instanteReserva, string instanteRecojo, string estado, int idCopiaEjemplar, int idUsuario)
        {
            try
            {
                reserva[] listaReserva = this.WsCliente.reserva_listarTodos_porParametros(instanteReserva,instanteRecojo,estado,idCopiaEjemplar,idUsuario);
                if (listaReserva == null) return new BindingList<reserva>();
                return new BindingList<reserva>(listaReserva);
            }
            catch (Exception ex)
            {
                return new BindingList<reserva>(); 
            }
        }

        public int insertar(string instanteReserva,string instanteRecojo,string estado, 
                            int plazoDeRecojo,int idCopiaEjemplar, int idUsuario)
        {
            return this.WsCliente.reserva_insertar(instanteReserva, instanteRecojo, estado, idCopiaEjemplar, idUsuario);
        }


        /*
        public int modificar(int idReserva, localDateTime instanteReserva, int plazoDeRecojo,
                                 localDateTime instanteRecojo, string estado,
                                 copiaEjemplar copiaEjemplar, usuario usuario)
        {
            return this.WsCliente.reserva_modificar(idReserva, instanteReserva, plazoDeRecojo, instanteRecojo, estado, copiaEjemplar, usuario);
        }
        
        public int eliminar(int idReserva)
        {
            return this.WsCliente.reserva_eliminar(idReserva);
        }

        public BindingList<reserva> listarTodos()
        {
            reserva[] reservas = this.WsCliente.reserva_listarTodos();
            return new BindingList<reserva>(reservas);
        }

        public reserva obtenerPorId(int idReserva)
        {
            return this.WsCliente.reserva_obtenerPorId(idReserva);   
        }
        */
    }
}
