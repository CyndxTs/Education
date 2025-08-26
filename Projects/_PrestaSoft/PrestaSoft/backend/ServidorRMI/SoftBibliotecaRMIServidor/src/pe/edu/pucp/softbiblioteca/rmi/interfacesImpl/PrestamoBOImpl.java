
package pe.edu.pucp.softbiblioteca.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.prestamos.model.Prestamo;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.PrestamoBO;

public class PrestamoBOImpl extends UnicastRemoteObject implements PrestamoBO{

    private pe.edu.pucp.softbiblioteca.prestamos.bo.PrestamoBO prestamoBO;
    
    public PrestamoBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.prestamoBO = new pe.edu.pucp.softbiblioteca.prestamos.bo.PrestamoBO();
    }
    
    @Override
    public Integer insertar(String instantePrestamo, String fechaDevolucionEsperada, String fechaDevolucionReal, String tipoEstado, Integer huboReservaPrevia, Integer idEjemplarAsociado, Integer idUsuarioAsociado) throws RemoteException {
        return this.prestamoBO.insertar(instantePrestamo, fechaDevolucionEsperada, fechaDevolucionReal, tipoEstado, huboReservaPrevia, idEjemplarAsociado, idUsuarioAsociado);
    }

    @Override
    public Integer modificar(Integer idPrestamo, String instantePrestamo, String fechaDevolucionEsperada, String fechaDevolucionReal, String tipoEstado, Integer huboReservaPrevia, Integer idEjemplarAsociado, Integer idUsuarioAsociado) throws RemoteException {
        return this.prestamoBO.modificar(idPrestamo, instantePrestamo, fechaDevolucionEsperada, fechaDevolucionReal, tipoEstado, huboReservaPrevia, idEjemplarAsociado, idUsuarioAsociado);
    }

    @Override
    public Integer eliminar(Integer idPrestamo) throws RemoteException {
        return this.prestamoBO.eliminar(idPrestamo);
    }

    @Override
    public ArrayList<Prestamo> listarTodos(String instantePrestamo, String fechaDevolucionEsperada, String fechaDevolucionReal, String tipoEstado, Integer huboReservaPrevia, Integer idEjemplarAsociado, Integer idUsuarioAsociado, Integer limite) throws RemoteException {
        return this.prestamoBO.listarTodos(instantePrestamo, fechaDevolucionEsperada, fechaDevolucionReal, tipoEstado, huboReservaPrevia, idEjemplarAsociado, idUsuarioAsociado, limite);
    }

    @Override
    public Prestamo obtenerPorId(Integer idPrestamo) throws RemoteException {
        return this.prestamoBO.obtenerPorId(idPrestamo);
    }

    @Override
    public ArrayList<Prestamo> mostrarPrestamosEnCurso(Integer idUsuario, Integer limite) throws RemoteException {
        return this.prestamoBO.mostrarPrestamosEnCurso(idUsuario, limite);
    }

    @Override
    public ArrayList<Prestamo> mostrarUltimosPrestamos(Integer limite) throws RemoteException {
        return this.prestamoBO.mostrarUltimosPrestamos(limite);
    }

    @Override
    public Integer solicitarAmpliacion(Integer idPrestamo) throws RemoteException {
        return this.prestamoBO.solicitarAmpliacion(idPrestamo);
    }

    @Override
    public Integer confirmarAmpliacion(Integer idPrestamo) throws RemoteException {
        return this.prestamoBO.confirmarAmpliacion(idPrestamo);
    }

    @Override
    public ArrayList<String> consultarCalificacionPrestamo(Integer idUsuario, Integer idCopiaEjemplar) throws RemoteException {
        return this.prestamoBO.consultarCalificacionPrestamo(idUsuario, idCopiaEjemplar);
    }

    @Override
    public Integer registrarPrestamo(Integer idUsuario, Integer idCopiaEjemplar) throws RemoteException {
        return this.prestamoBO.registrarPrestamo(idUsuario, idCopiaEjemplar);
    }

    @Override
    public Prestamo consultarPrestamoQueSePrestoCopiaEjemplar(Integer idCopiaEjemplar) throws RemoteException {
        return this.prestamoBO.consultarPrestamoQueSePrestoCopiaEjemplar(idCopiaEjemplar);
    }

    @Override
    public int confirmarDevolucion(Integer idPrestamo, Integer idCopiaEjemplar, Integer estaEnMalEstado) throws RemoteException {
        return this.prestamoBO.confirmarDevolucion(idPrestamo, idCopiaEjemplar, estaEnMalEstado);
    }
    
}
