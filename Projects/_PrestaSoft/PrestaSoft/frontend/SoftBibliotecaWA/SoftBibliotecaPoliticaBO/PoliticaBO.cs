using SoftBibliotecaBO;
using SoftBibliotecaBO.ServicioWeb;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftBibliotecaPolitica
{
    public class PoliticaBO : BaseBO
    {
        public int insertar(string etiqueta, int idPoliticaPrestamo, int idPoliticaReserva)
        {
            return this.WsCliente.politica_insertar(etiqueta, idPoliticaPrestamo, idPoliticaReserva);
        }

        public int modificar(int idPolitica, string etiqueta, int idPoliticaReserva, int idPoliticaPrestamo)
        {
            return this.WsCliente.politica_modificar(idPolitica, etiqueta, idPoliticaReserva, idPoliticaPrestamo);
        }

        public int eliminar(int idPolitica)
        {
            return this.WsCliente.politica_eliminar(idPolitica);
        }

        public BindingList<politica> listarTodos(int idPoliticaPrestamo, int idPoliticaReserva, int limite)
        {
            try
            {
                politica[] politicas = this.WsCliente.politica_listarTodos(idPoliticaPrestamo, idPoliticaReserva, limite);
                if (politicas == null) return new BindingList<politica>();
                return new BindingList<politica>(politicas);
            }
            catch (Exception ex)
            {
                return new BindingList<politica>();
            }
        }

        public politica obtenerPorId(int idPolitica)
        {
            return this.WsCliente.politica_obtenerPorId(idPolitica);
        }
    }
}
