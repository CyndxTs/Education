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
        public int? insertar(string nombre, string direccion, string correoPersonalAdministrativo, string extensionDominioCorreo, int idPoliticaRegular, int idConsorcioPerteneciente)
        {
            return this.WsCliente.universidad_insertar(nombre, direccion, correoPersonalAdministrativo, extensionDominioCorreo, idPoliticaRegular, idConsorcioPerteneciente);
        }

        public int? modificar(int idUniversidad, string nombre, string direccion, string correoPersonalAdministrativo, string extensionDominioCorreo, int idPoliticaRegular, int idConsorcioPerteneciente)
        {
            return this.WsCliente.universidad_modificar(idUniversidad, nombre, direccion, correoPersonalAdministrativo, extensionDominioCorreo, idPoliticaRegular, idConsorcioPerteneciente);
        }

        public int? eliminar(int idUniversidad)
        {
            return this.WsCliente.usuario_eliminar(idUniversidad);
        }

        public BindingList<universidad> listarTodos()
        {
            universidad[] universidades = this.WsCliente.universidad_listarTodos();
            return new BindingList<universidad>(universidades);
        }

        public BindingList<universidad> listarTodos(int idPoliticaRegular, int idConsorcioPerteneciente)
        {
            universidad[] universidades = this.WsCliente.universidad_listarTodos_porParametros(idPoliticaRegular, idConsorcioPerteneciente);
            return new BindingList<universidad>(universidades);
        }

        public universidad obtenerPorId(int idUniversidad)
        {
            return this.WsCliente.universidad_obtenerPorId(idUniversidad);
        }
    }
}
