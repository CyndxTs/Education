using SoftBibliotecaBO;
using SoftBibliotecaBO.ServicioWeb;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftBibliotecaMoraBO
{
    public class MoraBO : BaseBO
    {
        public int insertar(float monto)
        {
            return this.WsCliente.mora_insertar(monto);
        }

        public int modificar(int idMora, float monto)
        {
            return this.WsCliente.mora_modificar(idMora, monto);
        }

        public int eliminar(int idMora)
        {
            return this.WsCliente.mora_eliminar(idMora);
        }

        public BindingList<mora> listarTodos(int limite)
        {
            try
            {
                mora[] moras = this.WsCliente.mora_listarTodos(limite);
                if (moras == null) return new BindingList<mora>();
                return new BindingList<mora>(moras);
            }
            catch (Exception ex)
            {
                return new BindingList<mora>();
            }
        }

        public mora obtenerPorId(int idMora)
        {
            return this.WsCliente.mora_obtenerPorId(idMora);
        }
    }
}
