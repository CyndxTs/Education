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
        public int? insertar(string cif)
        {
            return this.WsCliente.consorcio_insertar(cif);
        }

        public int? modificar(int idConsorcio, string CIF)
        {
            return this.WsCliente.consorcio_modificar(idConsorcio, CIF);
        }

        public int? eliminar(int idConsorcio)
        {
            return this.WsCliente.consorcio_eliminar(idConsorcio);
        }

        public BindingList<consorcio> listarTodos()
        {
            consorcio[] consorcios = this.WsCliente.consorcio_listarTodos();
            return new BindingList<consorcio>(consorcios);
        }

        public consorcio obtenerPorId(int idConsorcio)
        {
            return this.WsCliente.consorcio_obtenerPorId(idConsorcio);
        }
    }
}
