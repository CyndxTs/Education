using SoftBibliotecaBO;
using SoftBibliotecaBO.ServicioWeb;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftBibliotecaConsorcioBO
{
    public class ConsorcioBO : BaseBO
    {
        public BindingList<consorcio> listarTodos(int limite)
        {
            try
            {
                consorcio[] consorcios = this.WsCliente.consorcio_listarTodos(limite);
                if (consorcios == null) return new BindingList<consorcio>();
                return new BindingList<consorcio>(consorcios);
            }
            catch (Exception ex)
            {
                return new BindingList<consorcio>();
            }
        }


        public int insertar(string CIF)
        {
            return this.WsCliente.consorcio_insertar(CIF);
        }

        public int modificar(int idConsorcio, string CIF)
        {
            return this.WsCliente.consorcio_modificar(idConsorcio, CIF);
        }

        public int eliminar(int idConsorcio)
        {
            return this.WsCliente.consorcio_eliminar(idConsorcio);
        }

        public consorcio obtenerPorId(int idConsorcio)
        {
            return this.WsCliente.consorcio_obtenerPorId(idConsorcio);
        }
    }
}
