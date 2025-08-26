using SoftBibliotecaBO;
using SoftBibliotecaBO.ServicioWeb;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftBibliotecaUniversidadBO
{
    public class UniversidadBO : BaseBO
    {
        public int insertar(string nombre, string direccion, string correoPersonalAdministrativo, string extensionDominioCorreo, int idPoliticaRegular, int idConsorcioPerteneciente)
        {
            return this.WsCliente.universidad_insertar(nombre, direccion, correoPersonalAdministrativo, extensionDominioCorreo, idPoliticaRegular, idConsorcioPerteneciente);
        }

        public int modificar(int idUniversidad, string nombre, string direccion, string correoPersonalAdministrativo, string extensionDominioCorreo, int idPoliticaRegular, int idConsorcioPerteneciente)
        {
            return this.WsCliente.universidad_modificar(idUniversidad, nombre, direccion, correoPersonalAdministrativo, extensionDominioCorreo, idPoliticaRegular, idConsorcioPerteneciente);
        }

        public int eliminar(int idUniversidad)
        {
            return this.WsCliente.universidad_eliminar(idUniversidad);
        }


        public BindingList<universidad> listarTodos(int idPoliticaRegular, int idConsorcioPerteneciente, int limite)
        {
            try
            {
                universidad[] universidades = this.WsCliente.universidad_listarTodos(idPoliticaRegular, idConsorcioPerteneciente, limite);
                if (universidades == null) return new BindingList<universidad>();
                return new BindingList<universidad>(universidades);
            }
            catch (Exception e)
            {
                return new BindingList<universidad>();
            }
        }

        public universidad obtenerPorId(int idUniversidad)
        {
            return this.WsCliente.universidad_obtenerPorId(idUniversidad);
        }

        public BindingList<universidad> listarTodos_UniversidadesDeUsuarioPorID(int idUsuario)
        {
            try
            {
                universidad[] universidades = this.WsCliente.listarTodos_UniversidadesDeUsuarioPorID(idUsuario);
                if (universidades == null) return new BindingList<universidad>();
                return new BindingList<universidad>(universidades);
            }
            catch (Exception e)
            {
                return new BindingList<universidad>();
            }
        }

        public BindingList<universidad> listarTodos_UniversidadesDeUsuario(int idUsuario, string nombres, string apellidos,
                                                                          string correoInstitucional, string nombre_usuario, string fechaRegistro,
                                                                          string tipoUsuario, int idPoliticaRegular, int idConsorcioPerteneciente)
        {
            try
            {
                universidad[] universidades = this.WsCliente.listarTodos_UniversidadesDeUsuario(idUsuario, nombres, apellidos, correoInstitucional, nombre_usuario, fechaRegistro, tipoUsuario, idPoliticaRegular, idConsorcioPerteneciente);
                if (universidades == null) return new BindingList<universidad>();
                return new BindingList<universidad>(universidades);
            }
            catch (Exception e)
            {
                return new BindingList<universidad>();
            }
        }
        public int eliminarUniversidadesDeUsuario(int idUsuario)
        {
            return this.WsCliente.eliminarUniversidadesDeUsuario(idUsuario);
        }


    }
}
