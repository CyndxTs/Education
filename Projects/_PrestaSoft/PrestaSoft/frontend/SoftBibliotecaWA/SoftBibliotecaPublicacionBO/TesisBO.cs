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
    public class TesisBO:BaseBO
    {
        /*
        public int insertar(string titulo, string fechaPublicacion, string descripcion,
                            int copiasDisponibles, int hayFormatoFisico,
                            int hayFormatoVirtual, string url, byte[] portada,
                            int idBibliotecaAsociada, string gradoAcademico)
        {
            return this.WsCliente.tesis_insertar(titulo, fechaPublicacion, descripcion, copiasDisponibles,
                                                 hayFormatoFisico, hayFormatoVirtual, url, portada,
                                                 idBibliotecaAsociada, gradoAcademico);
        }

        public int modificar(int idTesis,string titulo, string fechaPublicacion, string descripcion,
                            int copiasDisponibles, int hayFormatoFisico,
                            int hayFormatoVirtual, string url, byte[] portada,
                            int idBibliotecaAsociada, string gradoAcademico)
        {
            return this.WsCliente.tesis_modificar(idTesis,titulo, fechaPublicacion, descripcion, copiasDisponibles,
                                                  hayFormatoFisico, hayFormatoVirtual, url, portada,
                                                  idBibliotecaAsociada, gradoAcademico);
        }

        public int eliminar(int idTesis)
        {
            return this.WsCliente.tesis_eliminar(idTesis);
        }

        public BindingList<tesis> listarTodos()
        {
            try
            {
                tesis[] libros = this.WsCliente.tesis_listarTodos();
                if (libros == null) return new BindingList<tesis>();
                return new BindingList<tesis>(libros);
            }
            catch (Exception ex)
            {
                return new BindingList<tesis>();
            }
        }

        public BindingList<tesis> listarTodos(string titulo, string fechaPublicacion, string descripcion,
                                              int hayFormatoFisico, int hayFormatoVirtual, int idBibliotecaAsociada,string gradoAcademico)
        {
            try
            {
                tesis[] libros = this.WsCliente.tesis_listarTodos_porParametros(titulo, fechaPublicacion, descripcion, hayFormatoFisico,
                                                                            hayFormatoVirtual, idBibliotecaAsociada, gradoAcademico);
                if (libros == null) return new BindingList<tesis>();
                return new BindingList<tesis>(libros);
            }
            catch (Exception ex)
            {
                return new BindingList<tesis>();
            }
        }


        public tesis obtenerPorId(int idTesis)
        {
            return this.WsCliente.tesis_obtenerPorId(idTesis);
        }
        */
        public tesis verDetallePublicacioTesis(int idTesis)
        {
            try
            {
                tesis unaTesis = this.WsCliente.verDetallePublicacioTesis(idTesis);
                if (unaTesis == null) unaTesis = new tesis();
                return unaTesis;
            }
            catch (Exception ex)
            {
                return new tesis();
            }
        }

        public int nuevaPublicacionTesis(string titulo, string fechaPublicacion, string descripcion,
                        int copiasDisponibles, int hayFormatoFisico, int hayFormatoVirtual, string url, byte[] portada,
                        int idBibliotecaAsociada, string gradoAcademico, string[] direccioneslocal,
                        int[] autores, int[] temas)
        {
            return this.WsCliente.nuevaPublicacionTesis(titulo, fechaPublicacion, descripcion, copiasDisponibles,
                                                        hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada,
                                                        gradoAcademico, direccioneslocal, autores, temas);
        }

        public int modificarPublicacionTesis(int idTesis, string titulo, string fechaPublicacion, string descripcion,
                        int copiasDisponibles, int hayFormatoFisico, int hayFormatoVirtual, string url, byte[] portada,
                        int idBibliotecaAsociada, string gradoAcademico,
                        int[] idsCopiasEjemplares, string[] estadosCopiasEjemplares,
                        int[] autores, int[] temas, int cantCopiasPorInsertar)
        {
            return this.WsCliente.modificarPublicacionTesis(idTesis, titulo, fechaPublicacion, descripcion, copiasDisponibles,
                                                            hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada, gradoAcademico,
                                                            idsCopiasEjemplares, estadosCopiasEjemplares, autores, temas, cantCopiasPorInsertar);
        }

        public BindingList<tesis> listarTodos_TesisDeAutorPorID(int idAutor)
        {
            try
            {
                tesis[] ltesis = this.WsCliente.listarTodos_TesisDeAutorPorID(idAutor);
                if (ltesis == null) return new BindingList<tesis>();
                return new BindingList<tesis>(ltesis);
            }
            catch (Exception ex)
            {
                return new BindingList<tesis>();
            }
        }

        public BindingList<tesis> listarTodos_TesisDeAutor(string titulo, string fechaPublicacion,
                                                 string descripcion, int hayFormatoFisico, int hayFormatoVirtual,
                                                 int idBibliotecaAsociada, string gradoAcademico, int idAutor,
                                                 string nombreCompleto)
        {
            try
            {
                tesis[] ltesis = this.WsCliente.listarTodos_TesisDeAutor(titulo, fechaPublicacion, descripcion, hayFormatoFisico, hayFormatoVirtual,
                                                                         idBibliotecaAsociada, gradoAcademico, idAutor, nombreCompleto);
                if (ltesis == null) return new BindingList<tesis>();
                return new BindingList<tesis>(ltesis);
            }
            catch (Exception ex)
            {
                return new BindingList<tesis>();
            }
        }

        public BindingList<tesis> listarTodos_TesisDeTemaPorID(int idTema)
        {
            try
            {
                tesis[] ltesis = this.WsCliente.listarTodos_TesisDeTemaPorID(idTema);
                if (ltesis == null) return new BindingList<tesis>();
                return new BindingList<tesis>(ltesis);
            }
            catch (Exception ex)
            {
                return new BindingList<tesis>();
            }
        }

        public BindingList<tesis> listarTodos_TesisDeTema(string ts_titulo, string fechaPublicacion,
                                                string descripcion, int hayFormatoFisico, int hayFormatoVirtual,
                                                int idBibliotecaAsociada, string gradoAcademico, int idTema,
                                                string tm_titulo)
        {
            try
            {
                tesis[] ltesis = this.WsCliente.listarTodos_TesisDeTema(ts_titulo, fechaPublicacion, descripcion, hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada, gradoAcademico, idTema, tm_titulo);
                if (ltesis == null) return new BindingList<tesis>();
                return new BindingList<tesis>(ltesis);
            }
            catch (Exception ex)
            {
                return new BindingList<tesis>();
            }
        }

    }
}
