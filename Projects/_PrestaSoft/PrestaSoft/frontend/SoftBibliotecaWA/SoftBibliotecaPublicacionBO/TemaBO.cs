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
    public class TemaBO : BaseBO
    {
        /*
       public int insertar(string titulo)
       {
           return this.WsCliente.tema_insertar(titulo);
       }

       public int modificar(int idTema, string titulo)
       {
           return this.WsCliente.tema_modificar(idTema,titulo);
       }

       public int eliminar(int idTema)
       {
           return this.WsCliente.tema_eliminar(idTema);
       }

       public BindingList<tema> listarTodos()
       {
           try
           {
               tema[] temas = this.WsCliente.tema_listarTodos();
               if (temas == null) return new BindingList<tema>();
               return new BindingList<tema>(temas);
           }
           catch (Exception ex)
           {
               return new BindingList<tema>();
           }

       }

       public BindingList<tema> listarTodos(string titulo)
       {
           try
           {
               tema[] temas = this.WsCliente.tema_listarTodos_porParametros(titulo);
               if (temas == null) return new BindingList<tema>();
               return new BindingList<tema>(temas);
           }
           catch (Exception ex)
           {
               return new BindingList<tema>();
           }
       }

       public tema obtenerPorId(int idTema)
       {
           return this.WsCliente.tema_obtenerPorId(idTema);
       }
       */


        public BindingList<tema> buscarTemaPorNombre(string titulo, int lim)
        {
            try
            {
                tema[] temas = this.WsCliente.buscarTemaPorNombre(titulo,lim);
                if (temas == null) return new BindingList<tema>();
                return new BindingList<tema>(temas);
            }
            catch (Exception ex)
            {
                return new BindingList<tema>();
            }
        }
        public int insertarTema(string titulo)
        {
            return this.WsCliente.insertarTema(titulo);
        }

        public BindingList<tema> listarTodos_TemasDeTesisPorID(int idTesis)
        {
            try
            {
                tema[] temas = this.WsCliente.listarTodos_TemasDeTesisPorID(idTesis);
                if (temas == null) return new BindingList<tema>();
                return new BindingList<tema>(temas);
            }
            catch (Exception ex)
            {
                return new BindingList<tema>();
            }
        }

        public BindingList<tema> listarTodos_TemasDeTesis(int idTesis, string ts_titulo, string fechaPublicacion,
                                                        string descripcion, int hayFormatoFisico, int hayFormatoVirtual,
                                                        int idBibliotecaAsociada, string gradoAcademico, string tm_titulo)
        {
            try
            {
                tema[] temas = this.WsCliente.listarTodos_TemasDeTesis(idTesis, ts_titulo, fechaPublicacion,
                                                        descripcion, hayFormatoFisico, hayFormatoVirtual,
                                                        idBibliotecaAsociada, gradoAcademico, tm_titulo);
                if (temas == null) return new BindingList<tema>();
                return new BindingList<tema>(temas);
            }
            catch (Exception ex)
            {
                return new BindingList<tema>();
            }
        }
    }
}
