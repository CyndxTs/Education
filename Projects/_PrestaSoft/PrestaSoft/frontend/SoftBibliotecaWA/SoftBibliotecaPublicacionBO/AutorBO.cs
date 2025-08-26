using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;
using SoftBibliotecaBO;
using SoftBibliotecaBO.ServicioWeb;

namespace SoftBibliotecaPublicacionBO
{
    public class AutorBO : BaseBO
    {
        /*
        public int insertar(string nombreCompleto)
        {
            return this.WsCliente.autor_insertar(nombreCompleto);
        }

        public int modificar(int idAutor, string nombreCompleto)
        {
            return this.WsCliente.autor_modificar(idAutor,nombreCompleto);
        }

        public int eliminar(int idAutor)
        {
            return this.WsCliente.autor_eliminar(idAutor);
        }

        public BindingList<autor> listarTodos()
        {
            try
            {
                autor[] autores = this.WsCliente.autor_listarTodos();
                if(autores == null) return new BindingList<autor>();
                return new BindingList<autor>(autores);
            }
            catch (Exception e)
            {
                return new BindingList<autor>();
            }
        }

        public BindingList<autor> listarTodos(string nombreCompleto)
        {
            try
            {
                autor[] autores = this.WsCliente.autor_listarTodos_porParametros(nombreCompleto);
                if (autores == null) return new BindingList<autor>();
                return new BindingList<autor>(autores);
            }
            catch (Exception e)
            {
                return new BindingList<autor>();
            }
        }

        public autor obtenerPorId(int idAutor)
        {
            return this.WsCliente.autor_obtenerPorId(idAutor);
        }

        */
        public BindingList<autor> buscarAutorPorNombre(string nombre, int limite)
        {

            try
            {
                autor[] autores = this.WsCliente.buscarAutorPorNombre(nombre,limite);
                if (autores == null) return new BindingList<autor>();
                return new BindingList<autor>(autores);

            }
            catch (Exception ex)
            {
                return new BindingList<autor>();
            }
        }
        public int insertarAutor(String nombre)
        {
            return this.WsCliente.insertarAutor(nombre);
        }
        public BindingList<autor> listarTodos_AutoresDeLibroPorID(int id)
        {
            try
            {
                autor[] autores = this.WsCliente.listarTodos_AutoresDeLibroPorID(id);
                if (autores == null) return new BindingList<autor>();
                return new BindingList<autor>(autores);

            }
            catch (Exception ex)
            {
                return new BindingList<autor>();
            }
        }
        public BindingList<autor> listarTodos_AutoresDeLibro(int idLibro, string titulo, string fechaPublicacion,
                                                       string descripcion, int hayFormatoFisico, int hayFormatoVirtual,
                                                       int idBibliotecaAsociada, int idEditorial, string tipo, int volumen,
                                                       string materia, string genero, int tomo, string nombreCompleto)
        {
            try
            {
                autor[] autores = this.WsCliente.listarTodos_AutoresDeLibro(idLibro, titulo, fechaPublicacion, descripcion, hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada, idEditorial, tipo, volumen, materia, genero, tomo, nombreCompleto);
                if (autores == null) return new BindingList<autor>();
                return new BindingList<autor>(autores);

            }
            catch (Exception ex)
            {
                return new BindingList<autor>();
            }

        }
        public BindingList<autor> listarTodos_AutoresDeTesisPorID(int id)
        {
            try
            {
                autor[] autores = this.WsCliente.listarTodos_AutoresDeTesisPorID(id);
                if (autores == null) return new BindingList<autor>();
                return new BindingList<autor>(autores);

            }
            catch (Exception ex)
            {
                return new BindingList<autor>();
            }
        }
        public BindingList<autor> listarTodos_AutoresDeTesis(int idTesis, string titulo, string fechaPublicacion,
                                                      string descripcion, int hayFormatoFisico, int hayFormatoVirtual,
                                                      int idBibliotecaAsociada, string gradoAcademico, string nombreCompleto)
        {
            try
            {
                autor[] autores = this.WsCliente.listarTodos_AutoresDeTesis(idTesis, titulo, fechaPublicacion, descripcion, hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada, gradoAcademico, nombreCompleto);
                if (autores == null) return new BindingList<autor>();
                return new BindingList<autor>(autores);

            }
            catch (Exception ex)
            {
                return new BindingList<autor>();
            }

        }
    }
}
