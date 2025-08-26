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
    public class LibroBO : BaseBO
    {
        /*
       public int insertar(string titulo, string fechaPublicacion, string descripcion,
                               int copiasDisponibles, int hayFormatoFisico,
                               int hayFormatoVirtual, string url, byte[] portada,
                               int idBibliotecaAsociada, int ISBN, int idEditorial, string tipo,
                               int volumen, string materia, string genero, int tomo)
       {
           return this.WsCliente.libro_insertar(titulo, fechaPublicacion, descripcion, copiasDisponibles,
                                                hayFormatoFisico, hayFormatoVirtual, url, portada,
                                                idBibliotecaAsociada,ISBN, idEditorial,tipo, volumen,
                                                materia, genero, tomo);
       }

       public int modificar(int idLibro,string titulo, string fechaPublicacion, string descripcion,
                               int copiasDisponibles, int hayFormatoFisico,
                               int hayFormatoVirtual, string url, byte[] portada,
                               int idBibliotecaAsociada, int ISBN, int idEditorial, string tipo,
                               int volumen, string materia, string genero, int tomo)
       {
           return this.WsCliente.libro_modificar(idLibro,titulo, fechaPublicacion, descripcion, copiasDisponibles,
                                                hayFormatoFisico, hayFormatoVirtual, url, portada,
                                                idBibliotecaAsociada, ISBN, idEditorial, tipo, volumen,
                                                materia, genero, tomo);
       }

       public int eliminar(int idLibro)
       {
           return this.WsCliente.libro_eliminar(idLibro);
       }

       public BindingList<libro> listarTodos()
       {
           try
           {
               libro[] libros = this.WsCliente.libro_listarTodos();
               if (libros == null) return new BindingList<libro>();
               return new BindingList<libro>(libros);
           }
           catch (Exception e)
           {
               return new BindingList<libro>();
           }

       }

       public BindingList<libro> listarTodos(string titulo, string fechaPublicacion, string descripcion,
                                             int hayFormatoFisico, int hayFormatoVirtual, int idBibliotecaAsociada,
                                             int idEditorial, string tipo, int volumen,
                                             string materia, string genero, int tomo)
       {

           try
           {
               libro[] libros = this.WsCliente.libro_listarTodos_porParametros(titulo, fechaPublicacion, descripcion, hayFormatoFisico,
                                                                           hayFormatoVirtual, idBibliotecaAsociada, idEditorial, tipo,
                                                                           volumen, materia, genero, tomo);
               if (libros == null) return new BindingList<libro>();
               return new BindingList<libro>(libros);
           }
           catch (Exception e)
           {
               return new BindingList<libro>();
           }
       }


       public libro obtenerPorId(int idLibro)
       {
           return this.WsCliente.libro_obtenerPorId(idLibro);
       }

       public libro obtenerPorAtributosUnicos(int ISBN)
       {
           return this.WsCliente.libro_obtenerPorAtributosUnicos(ISBN);
       }
       */
        public libro verDetallePublicacionLibro(int idLibro)
        {

            try
            {
                libro unLibro = this.WsCliente.verDetallePublicacionLibro(idLibro);
                if (unLibro == null) unLibro = new libro();
                return unLibro;
            }
            catch (Exception ex)
            {
                return new libro();
            }

        }
        public int nuevaPublicacionLibro(string titulo, string fechaPublicacion, string descripcion,
                            int copiasDisponibles, int hayFormatoFisico,
                            int hayFormatoVirtual, string url, byte[] portada,
                            int idBibliotecaAsociada, int ISBN, int idEditorial, string tipo,
                            int volumen, string materia, string genero, int tomo, string[] direccioneslocal,
                            int[] autores)
        {

            return this.WsCliente.nuevaPublicacionLibro(titulo, fechaPublicacion, descripcion, copiasDisponibles,
                                                hayFormatoFisico, hayFormatoVirtual, url, portada,
                                                 idBibliotecaAsociada, ISBN, idEditorial, tipo, volumen,
                                                 materia, genero, tomo, direccioneslocal, autores);
        }
        public int modificarPublicacionLibro(int id, string titulo, string fechaPublicacion, string descripcion,
                            int copiasDisponibles, int hayFormatoFisico,
                            int hayFormatoVirtual, string url, byte[] portada,
                            int idBibliotecaAsociada, int ISBN, int idEditorial, string tipo,
                            int volumen, string materia, string genero, int tomo, int[] idsCopiasEjemplares, string[] estadosCopiasEjemplares,
                            int[] autores, int cantcopisinsertar)
        {
            return this.WsCliente.modificarPublicacionLibro(id, titulo, fechaPublicacion, descripcion, copiasDisponibles,
                                                hayFormatoFisico, hayFormatoVirtual, url, portada,
                                                 idBibliotecaAsociada, ISBN, idEditorial, tipo, volumen,
                                                 materia, genero, tomo, idsCopiasEjemplares, estadosCopiasEjemplares, autores, cantcopisinsertar);
        }

        public BindingList<libro> listarTodos_LibrosDeAutorPorID(int id)
        {
            try
            {
                libro[] libros = this.WsCliente.listarTodos_LibrosDeAutorPorID(id);
                if (libros == null) return new BindingList<libro>();
                return new BindingList<libro>(libros);
            }
            catch (Exception e)
            {
                return new BindingList<libro>();
            }
        }
        public BindingList<libro> listarTodos_LibrosDeAutor(string titulo, string fechaPublicacion, string descripcion,
                            int hayFormatoFisico, int hayFormatoVirtual, int idBibliotecaAsociada, int idEditorial, string tipo,
                            int volumen, string materia, string genero, int tomo, int idAutor, string nombrecompleto)
        {
            try
            {
                libro[] libros = this.WsCliente.listarTodos_LibrosDeAutor(titulo, fechaPublicacion, descripcion, hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada, idEditorial, tipo, volumen, materia, genero, tomo, idAutor, nombrecompleto);
                if (libros == null) return new BindingList<libro>();
                return new BindingList<libro>(libros);
            }
            catch (Exception e)
            {
                return new BindingList<libro>();
            }
        }
    }
}
