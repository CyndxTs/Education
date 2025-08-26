using SoftBibliotecaBO;
using SoftBibliotecaBO.ServicioWeb;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftBibliotecaPoliticaReservaBO
{
    public class PoliticaReservaBO : BaseBO
    {
        public int insertar(int cantMaxHorasDeRecojo, int cantMaxReservasAgendadasPorUsuario)
        {
            return this.WsCliente.politicareserva_insertar(cantMaxHorasDeRecojo, cantMaxReservasAgendadasPorUsuario);
        }

        public int modificar(int idPoliticaReserva, int cantMaxHorasDeRecojo, int cantMaxReservasAgendadasPorUsuario)
        {
            return this.WsCliente.politicareserva_modificar(idPoliticaReserva, cantMaxHorasDeRecojo, cantMaxReservasAgendadasPorUsuario);
        }

        public int eliminar(int idPoliticaReserva)
        {
            return this.WsCliente.politicareserva_eliminar(idPoliticaReserva);
        }

        public BindingList<politicaReserva> listarTodos(int limite)
        {
            try
            {
                politicaReserva[] politicasReserva = this.WsCliente.politicareserva_listarTodos(limite);
                if (politicasReserva == null) return new BindingList<politicaReserva>();

                return new BindingList<politicaReserva>(politicasReserva);
            }
            catch (Exception e)
            {
                return new BindingList<politicaReserva>();
            }
        }

        public politicaReserva obtenerPorId(int idPoliticaReserva)
        {
            return this.WsCliente.politicareserva_obtenerPorId(idPoliticaReserva);
        }
    }
}
