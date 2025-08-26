using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftBibliotecaBO;
using SoftBibliotecaBO.ServicioWeb;

namespace SoftBibliotecaUsuarioBO
{
    public class UsuarioBO : BaseBO
    {
        /*
         public int insertar(string nombres, string apellidos, string correo, string contrasenia,
                             string nombreUsuario, string fechaRegistro, string tipoUsuario)
         {
             return this.WsCliente.usuario_insertar(nombres, apellidos, correo, contrasenia,
                                                    nombreUsuario, fechaRegistro, tipoUsuario);
         }
        */

        public int modificar(int idUsuario, string nombres, string apellidos, string correo,
                            string contrasenia, string nombreUsuario, string fechaRegistro, string tipoUsuario)
        {
            return this.WsCliente.modificarUsuario(idUsuario, nombres, apellidos, correo, contrasenia,
                                                    nombreUsuario, fechaRegistro, tipoUsuario);
        }


        /*
         public int eliminar(int idUsuario)
         {
             return this.WsCliente.usuario_eliminar(idUsuario);
         }

         public BindingList<usuario> listarTodos()
         {
             try
             {
                 usuario[] usuarios = this.WsCliente.usuario_listarTodos();
                 if (usuarios == null) return new BindingList<usuario>();
                 return new BindingList<usuario>(usuarios);
             }
             catch (Exception e)
             {
                 return new BindingList<usuario>();
             }
         }

         public BindingList<usuario> listarTodos(string nombres, string apellidos, string correoInstitucional, string nombresUsuario, string tipoUsario)
         {
             try
             {
                 usuario[] usuarios = this.WsCliente.usuario_listarTodos_porParametros(nombres, apellidos, correoInstitucional, nombresUsuario,null ,tipoUsario);
                 if (usuarios == null) return new BindingList<usuario>();
                 return new BindingList<usuario>(usuarios);
             }
             catch (Exception e)
             {
                 return new BindingList<usuario>();
             }
         }

         public usuario obtenerPorId(int idUsuario)
         {
             return this.WsCliente.usuario_obtenerPorId(idUsuario);
         }

         public usuario obtenerPorAtributosUnicos(string correoInstitucional, string contrasenia, string nombreUsuario)
         {
             return this.WsCliente.usuario_obtenerPorAtributosUnicos(correoInstitucional, contrasenia, nombreUsuario);
         }

         public usuario iniciarSesion(string correo_nombreusuario, string contrasenia)
         {
             return this.WsCliente.iniciarSesion(correo_nombreusuario, contrasenia);
         }
         public int registrarMiembro(string nombre, string apellidos, string correo, string contrasenia, string contraseniaconfirmacion, string nombreusuario, string strIdUniversidad)
         {
             return this.WsCliente.registrarMiembro(nombre, apellidos, correo, contrasenia, contraseniaconfirmacion, nombreusuario, strIdUniversidad);
         }
         */


        public usuario iniciarSesion(string correo_nombreusuario, string contrasenia)
        {
            return this.WsCliente.iniciarSesion(correo_nombreusuario, contrasenia);
        }

        public int registrarMiembro(string nombre, string apellidos, string correo, string contrasenia, string contraseniaconfirmacion, string nombreusuario, string strIdUniversidad)
        {
            return this.WsCliente.registrarMiembro(nombre, apellidos, correo, contrasenia, contraseniaconfirmacion, nombreusuario, strIdUniversidad);
        }

        public usuario consultarMiembroID(int idUsuario)
        {
            return this.WsCliente.consultarMiembroID(idUsuario);
        }

        public usuario obtenerPorAtributosUnicos(string correoInstitucional, string contrasenia, string nombre_usuario)
        {
            return this.WsCliente.obtenerPorAtributosUnicos(correoInstitucional, contrasenia, nombre_usuario);
        }

        public BindingList<usuario> listarTodos(string nombres, string apellidos, string correoInstitucional,
                                                string nombre_usuario, string tipoUsuario, int limite)
        {
            try
            {
                usuario[] usuarios = this.WsCliente.usuario_listarTodos(nombres, apellidos, correoInstitucional, nombre_usuario, tipoUsuario, limite);
                if (usuarios == null) return new BindingList<usuario>();
                return new BindingList<usuario>(usuarios);
            }
            catch (Exception e)
            {
                return new BindingList<usuario>();
            }
        }

        public BindingList<usuario> listarTodos_UsuariosDeUniversidadPorID(int idUniversidad)
        {
            try
            {
                usuario[] usuarios = this.WsCliente.listarTodos_UsuariosDeUniversidadPorID(idUniversidad);
                if (usuarios == null) return new BindingList<usuario>();
                return new BindingList<usuario>(usuarios);
            }
            catch (Exception e)
            {
                return new BindingList<usuario>();
            }
        }

        public BindingList<usuario> listarTodos_UsuariosDeUniversidad(string nombres, string apellidos,
                                                            string correoInstitucional, string nombre_usuario, string fechaRegistro,
                                                            string tipoUsuario, int idUniversidad, int idPoliticaRegular,
                                                            int idConsorcioPerteneciente)
        {
            try
            {
                usuario[] usuarios = this.WsCliente.listarTodos_UsuariosDeUniversidad(nombres, apellidos, correoInstitucional, nombre_usuario,
                                                                                      fechaRegistro, tipoUsuario, idUniversidad, idPoliticaRegular,
                                                                                      idConsorcioPerteneciente);
                if (usuarios == null) return new BindingList<usuario>();
                return new BindingList<usuario>(usuarios);
            }
            catch (Exception e)
            {
                return new BindingList<usuario>();
            }
        }

        public int modificarUniversidadesAsociadasDeUsuario(int idUsuario, int[] idUniversidadesActuales, int[] idUniversidadesNuevas)
        {
            return this.WsCliente.modificarUniversidadesAsociadasDeUsuario(idUsuario, idUniversidadesActuales, idUniversidadesNuevas);
        }
        
        
        public byte[] reporteMiembros(string fechaRegMin, string fechaRegMax, int cantSancionesMin, int cantSancionesMax, int idUniversidad, int limiteUsuarios, string descripcion)
        {
            return this.WsReporte.reporteMiembros(fechaRegMin, fechaRegMax, cantSancionesMin, cantSancionesMax, idUniversidad, limiteUsuarios, descripcion);
        }

    }
}
