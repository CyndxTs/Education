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
    public class EditorialBO : BaseBO
    {
        
       public int insertar(string nombre)
       {
           return this.WsCliente.insertarEditorial(nombre);
       }
        /*
       public int modificar(int idEditorial, string nombre)
       {
           return this.WsCliente.editorial_modificar(idEditorial,nombre);
       }

       public int eliminar(int idEditorial)
       {
           return this.WsCliente.editorial_eliminar(idEditorial);
       }

       public BindingList<editorial> listarTodos()
       {
           try
           {
               editorial[] editoriales = this.WsCliente.editorial_listarTodos();
               if (editoriales == null) return new BindingList<editorial>();
               return new BindingList<editorial>(editoriales);
           }
           catch (Exception e)
           {
               return new BindingList<editorial>();
           }

       }

       public BindingList<editorial> listarTodos(string nombre)
       {
           try
           {
               editorial[] editoriales = this.WsCliente.editorial_listarTodos_porParametros(nombre);
               if (editoriales == null) return new BindingList<editorial>();
               return new BindingList<editorial>(editoriales);
           }
           catch (Exception e)
           {
               return new BindingList<editorial>();
           }
       }

       public editorial obtenerPorId(int idEditorial)
       {
           return this.WsCliente.editorial_obtenerPorId(idEditorial);
       }

       */


        public BindingList<editorial> buscarEditorialPorNombre(string nombre)
        {
            try
            {
                editorial[] editoriales = this.WsCliente.buscarEditorialPorNombre(nombre);
                if (editoriales == null) return new BindingList<editorial>();
                return new BindingList<editorial>(editoriales);
            }
            catch (Exception e)
            {
                return new BindingList<editorial>();
            }
        }
    }
}
