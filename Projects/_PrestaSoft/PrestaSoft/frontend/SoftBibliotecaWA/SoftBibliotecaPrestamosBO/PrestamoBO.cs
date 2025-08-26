using SoftBibliotecaBO;
using SoftBibliotecaBO.ServicioWeb;

using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftBibliotecaPrestamosBO
{
    public class PrestamoBO : BaseBO
    {
        public prestamo obtenerPorId(int idPrestamo)
        {
            return this.WsCliente.obtenerPorId_Prestamo(idPrestamo);
        }
        

        public BindingList<prestamo> mostrarPrestamosEnCurso(int idUsuario, int limite)
        {
            try
            {
                prestamo[] prestamos = this.WsCliente.mostrarPrestamosEnCurso(idUsuario, limite);
                if (prestamos == null) return new BindingList<prestamo>();
                return new BindingList<prestamo>(prestamos);
            }
            catch (Exception ex)
            {
                return new BindingList<prestamo>();
            }
        }

        public BindingList<prestamo> mostrarUltimosPrestamos(int limite)
        {
            try
            {
                prestamo[] prestamos = this.WsCliente.mostrarUltimosPrestamos(limite);
                if (prestamos == null) return new BindingList<prestamo>();
                return new BindingList<prestamo>(prestamos);
            }
            catch (Exception ex)
            {
                return new BindingList<prestamo>();
            }
        }

        public int solicitarAmpliacion(int idPrestamo)
        {
            return this.WsCliente.solicitarAmpliacion(idPrestamo);
        }

        public int confirmarAmpliacion(int idPrestamo)
        {
            return this.WsCliente.confirmarAmpliacion(idPrestamo);
        }

        public BindingList<string> consultarCalificacionPrestamo(int idUsuario, int idCopiaEjemplar)
        {
            try
            {
                string[] prestamos = this.WsCliente.consultarCalificacionPrestamo(idUsuario, idCopiaEjemplar);
                if (prestamos == null) return new BindingList<string>();
                return new BindingList<string>(prestamos);
            }
            catch (Exception ex)
            {
                return new BindingList<string>();
            }
        }

        public int registrarPrestamo(int idUsuario, int idCopiaEjemplar)
        {
            return this.WsCliente.registrarPrestamo(idUsuario, idCopiaEjemplar);
        }


        public prestamo consultarPrestamoQueSePrestoCopiaEjemplar(int idCopiaEjemplar)
        {
            return this.WsCliente.consultarPrestamoQueSePrestoCopiaEjemplar(idCopiaEjemplar);
        }

        public int confirmarDevolucion(int idPrestamo, int idCopiaEjemplar, int estaEnMalEstado)
        {
            return this.WsCliente.confirmarDevolucion(idPrestamo, idCopiaEjemplar, estaEnMalEstado);
        }
        //int idUsuario, string fechaMin, string fechaMax, string estadoDePrestamos, int idUniversidad, int mostrarONoReservas, string descripcion
        public byte[] reporteSolicitudes(int idUsuario, string fechaMin, string fechaMax, string estadoDePrestamos, int idUniversidad, int mostrarONoReservas, string descripcion)
        {
            return this.WsReporte.reporteSolicitudes(idUsuario,  fechaMin, fechaMax, estadoDePrestamos, idUniversidad, mostrarONoReservas, descripcion); 
        }

    }
}
