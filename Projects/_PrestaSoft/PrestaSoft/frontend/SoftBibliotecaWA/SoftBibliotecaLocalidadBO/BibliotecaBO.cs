using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftBibliotecaBO;
using SoftBibliotecaBO.ServicioWeb;

namespace SoftBibliotecaPublicacionBO
{
    public class BibliotecaBO : BaseBO
    {
        public int? insertar(string nombre, string ubicacion, int idUniversidadAsoc)
        {
            return this.WsCliente.biblioteca_insertar(nombre, ubicacion, idUniversidadAsoc);
        }

        public int? modificar(int idBiblioteca, string nombre, string ubicacion, int idUniversidadAsoc)
        {
            return this.WsCliente.biblioteca_modificar(idBiblioteca, nombre, ubicacion, idUniversidadAsoc);
        }

        public BindingList<biblioteca> listarTodos()
        {
            biblioteca[] listBib = this.WsCliente.biblioteca_listarTodos();
            return new BindingList<biblioteca>(listBib);
        }

        public BindingList<biblioteca> listarTodos(int idUniversidadAsociada)
        {
            biblioteca[] listBib = this.WsCliente.biblioteca_listarTodos_porParametros(idUniversidadAsociada);
            return new BindingList<biblioteca>(listBib);
        }

        public biblioteca obtenerPorId(int idBiblioteca)
        {
            return this.WsCliente.biblioteca_obtenerPorId(idBiblioteca);
        }
    }
}
