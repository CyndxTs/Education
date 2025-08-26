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
    public class PublicacionBO : BaseBO
    {
        /*
        public BindingList<publicacion> listarTodos(int modoDeBusqueda) {
            try
            {
                publicacion[] publicaciones = this.WsCliente.publicacion_listarTodos(modoDeBusqueda);
                if (publicaciones == null) return new BindingList<publicacion>();
                return new BindingList<publicacion>(publicaciones);
            }
            catch (Exception ex)
            {
                return new BindingList<publicacion>();
            }
        }

        public BindingList<publicacion> listarTodos(int modoDeBusqueda,string titulo, string fechaPublicacion, string descripcion,
                                                    int hayFormatoFisico, int hayFormatoVirtual, int idBibliotecaAsociada,
                                                    int idEditorial, string tipo, int volumen,
                                                    string materia, string genero, int tomo, string gradoAcademico)
        {
            try
            {
                publicacion[] publicaciones = this.WsCliente.publicacion_listarTodos_porParametros(modoDeBusqueda, titulo, fechaPublicacion, descripcion,
                                                                                               hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada,
                                                                                               idEditorial, tipo, volumen,
                                                                                               materia, genero, tomo, gradoAcademico);
                if (publicaciones == null) return new BindingList<publicacion>();
                return new BindingList<publicacion>(publicaciones);
            }
            catch (Exception ex)
            {
                return new BindingList<publicacion>();
            }
            
        }
        */

        public BindingList<publicacion> verCatalogo(int modoBusqueda, int idBiblioteca, string tipo, string titulo, int limite)
        {
            try
            {
                publicacion[] publicaciones = this.WsCliente.verCatalogo(modoBusqueda, idBiblioteca, tipo, titulo, limite);
                if (publicaciones == null) return new BindingList<publicacion>();
                return new BindingList<publicacion>(publicaciones);

            }
            catch (Exception ex)
            {
                return new BindingList<publicacion>();
            }
        }
        public BindingList<publicacion> busquedaAvanzada(int modoDeBusqueda, string titulo,
                                            string descripcion, int hayFormatoFisico, int hayFormatoVirtual,
                                             int idBibliotecaAsociada, int nombreautor)
        {
            try
            {
                publicacion[] publicaciones = this.WsCliente.busquedaAvanzada(modoDeBusqueda, titulo, descripcion, hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada, nombreautor);
                if (publicaciones == null) return new BindingList<publicacion>();
                return new BindingList<publicacion>(publicaciones);

            }
            catch (Exception ex)
            {
                return new BindingList<publicacion>();
            }

        }
        public BindingList<publicacion> publicacionesPorAutor(int id)
        {
            try
            {
                publicacion[] publicaciones = this.WsCliente.publicacionesPorAutor(id);
                if (publicaciones == null) return new BindingList<publicacion>();
                return new BindingList<publicacion>(publicaciones);

            }
            catch (Exception ex)
            {
                return new BindingList<publicacion>();
            }
        }
        public copiaEjemplar consultarCopiaEjemplar(int id)
        {
            copiaEjemplar copiaEjemplar = this.WsCliente.consultarCopiaEjemplar(id);
            return copiaEjemplar;
        }

        public byte[] reportePublicaciones(int cantPrestamosMin, int cantPrestamosMax, int incluirVirtuales, int idUniversidad, int limite, string descripcion)
        {
            return this.WsReporte.reportePublicaciones(cantPrestamosMin, cantPrestamosMax, incluirVirtuales, idUniversidad, limite, descripcion);

        }

    }
}
