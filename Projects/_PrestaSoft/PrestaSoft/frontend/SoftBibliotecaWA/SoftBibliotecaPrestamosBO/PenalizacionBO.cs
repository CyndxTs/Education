using SoftBibliotecaBO;
using SoftBibliotecaBO.ServicioWeb;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftBibliotecaPrestamosBO
{
    public class PenalizacionBO : BaseBO
    {
        /*
        public int insertar(string descripcion, float monto, int idPrestamoAsociado)
        {
            return this.WsCliente.penalizacion_insertar(descripcion, monto, idPrestamoAsociado);
        }

        public int modificar(int idPenalizacion, string descripcion, float monto, int idPrestamoAsociado)
        {
            return this.WsCliente.penalizacion_modificar(idPenalizacion, descripcion, monto, idPrestamoAsociado);
        }

        public int eliminar(int idPenalizacion)
        {
            return this.WsCliente.penalizacion_eliminar(idPenalizacion);
        }

        public BindingList<penalizacion> listarTodos()
        {
            try
            {
                penalizacion[] penalizaciones = this.WsCliente.penalizacion_listarTodos();
                if (penalizaciones == null) return new BindingList<penalizacion>();
                return new BindingList<penalizacion>(penalizaciones);
            }
            catch (Exception ex)
            {
                return new BindingList<penalizacion>();
            }
        }

        public BindingList<penalizacion> listarTodos(int idPrestamoAsociado)
        {
            try
            {
                penalizacion[] penalizaciones = this.WsCliente.penalizacion_listarTodos_porParametros(idPrestamoAsociado);
                if (penalizaciones == null) return new BindingList<penalizacion>();
                return new BindingList<penalizacion>(penalizaciones);
            }
            catch (Exception ex)
            {
                return new BindingList<penalizacion>();
            }
        }

        public penalizacion obtenerPorId(int idPenalizacion)
        {
            return this.WsCliente.penalizacion_obtenerPorId(idPenalizacion);
        }
        */

        public BindingList<penalizacion> verPenalizacionesPrestamo(int idPrestamoAsociado)
        {
            try
            {
                penalizacion[] penalizaciones = this.WsCliente.verPenalizacionesPrestamo(idPrestamoAsociado);
                if (penalizaciones == null) return new BindingList<penalizacion>();
                return new BindingList<penalizacion>(penalizaciones);
            }
            catch (Exception ex)
            {
                return new BindingList<penalizacion>();
            }
        }
    }
}
