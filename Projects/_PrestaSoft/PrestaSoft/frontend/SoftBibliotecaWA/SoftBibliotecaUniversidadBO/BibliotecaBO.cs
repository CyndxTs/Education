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
        public int insertar(string nombre, string ubicacion, int idUniversidadAsociada)
        {
            return this.WsCliente.biblioteca_insertar(nombre, ubicacion, idUniversidadAsociada);
        }

        public int modificar(int idBiblioteca, string nombre, string ubicacion,
                                 int idUniversidadAsociada)
        {
            return this.WsCliente.biblioteca_modificar(idBiblioteca, nombre, ubicacion, idUniversidadAsociada);
        }

        public int eliminar(int idBiblioteca)
        {
            return this.WsCliente.biblioteca_eliminar(idBiblioteca);
        }

        public BindingList<biblioteca> listarTodos(int idUniversidadAsociada, int limite)
        {
            try
            {
                biblioteca[] bibliotecas = this.WsCliente.biblioteca_listarTodos(idUniversidadAsociada, limite);
                if (bibliotecas == null) return new BindingList<biblioteca>();
                return new BindingList<biblioteca>(bibliotecas);
            }
            catch (Exception ex)
            {
                return new BindingList<biblioteca>();
            }
        }

        public biblioteca obtenerPorId(int idBiblioteca)
        {
            return this.WsCliente.biblioteca_obtenerPorId(idBiblioteca);
        }

        public BindingList<biblioteca> mostrarBibliotecasDeUniversidadesDelProgramador(int idUsuario, int[] idUniversidadesAsociadasDelBibliotecario)
        {
            try
            {
                biblioteca[] bibliotecas = this.WsCliente.mostrarBibliotecasDeUniversidadesDelProgramador(idUsuario, idUniversidadesAsociadasDelBibliotecario);
                if (bibliotecas == null) return new BindingList<biblioteca>();
                return new BindingList<biblioteca>(bibliotecas);
            }
            catch (Exception ex)
            {
                return new BindingList<biblioteca>();
            }
        }
    }
}
