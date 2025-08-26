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
    public class CopiaEjemplarBO : BaseBO
    {
        /*
        public int insertar(string direccionLocal, int idPublicacionAsociada, string estado,
                            char tipoPublicaicon)
        {
            return this.WsCliente.copiaejemplar_insertar(direccionLocal,idPublicacionAsociada,estado,tipoPublicaicon);
        }
        public int modificar(int idCopiaEjemplar, string direccionLocal, int idPublicacionAsociada, string estado,
                                 char tipoPublicaicon)
        {
           return this.WsCliente.copiaejemplar_modificar(idCopiaEjemplar,direccionLocal,idPublicacionAsociada,estado,tipoPublicaicon);
        }

        public int eliminar(int idCopiaEjemplar)
        {
            return this.WsCliente.copiaejemplar_eliminar(idCopiaEjemplar);
        }

        public BindingList<copiaEjemplar> listarTodos()
        {
            try
            {
                copiaEjemplar[] copiasEjemplares = this.WsCliente.copiaejemplar_listarTodos();
                if (copiasEjemplares == null) return new BindingList<copiaEjemplar>();
                return new BindingList<copiaEjemplar>(copiasEjemplares);
            }
            catch (Exception e)
            {
                return new BindingList<copiaEjemplar>();
            }
            
        }

        public BindingList<copiaEjemplar> listarTodos(string direccionLocal,int idPublicacionAsociada,string estado,char tipoPublicacion)
        {
            try
            {
                copiaEjemplar[] copiasEjemplares = this.WsCliente.copiaejemplar_listarTodos_porParametros(direccionLocal, idPublicacionAsociada, estado, tipoPublicacion);
                if (copiasEjemplares == null) return new BindingList<copiaEjemplar>();
                return new BindingList<copiaEjemplar>(copiasEjemplares);
            }
            catch (Exception e)
            {
                return new BindingList<copiaEjemplar>();
            }
        }
        */
        public copiaEjemplar obtenerPorId(int idCopiaEjemplar)
        {
            copiaEjemplar cp = this.WsCliente.obtenerPorId_copia_ejemplar(idCopiaEjemplar);
          /*  if (cp != null) 
            {
                if (cp.publicacionAsociada is libro) 
                {
                    cp.publicacionAsociada = this.WsCliente.libro_obtenerPorId(cp.publicacionAsociada.idPublicacion);
                }
                else
                {
                    cp.publicacionAsociada = this.WsCliente.tesis_obtenerPorId(cp.publicacionAsociada.idPublicacion);
                }
                
            }*/
            return cp;
        }

        


        public BindingList<copiaEjemplar> ObtenerCopiasEjemplaresDeLibro(int id)
        {
            try
            {
                copiaEjemplar[] copiasEjemplares = this.WsCliente.ObtenerCopiasEjemplaresDeLibro(id);
                if (copiasEjemplares == null) return new BindingList<copiaEjemplar>();
                return new BindingList<copiaEjemplar>(copiasEjemplares);
            }
            catch (Exception e)
            {
                return new BindingList<copiaEjemplar>();
            }
        }

        public BindingList<copiaEjemplar> ObtenerCopiasEjemplaresDeTesis(int id)
        {
            try
            {
                copiaEjemplar[] copiasEjemplares = this.WsCliente.ObtenerCopiasEjemplaresDeTesis(id);
                if (copiasEjemplares == null) return new BindingList<copiaEjemplar>();
                return new BindingList<copiaEjemplar>(copiasEjemplares);
            }
            catch (Exception e)
            {
                return new BindingList<copiaEjemplar>();
            }
        }
    }
}
