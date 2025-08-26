
package pe.edu.pucp.softbiblioteca.rmi.interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.prestamos.model.Prestamo;

public interface PrestamoBO extends Remote{
    
    public Integer insertar(String instantePrestamo,String fechaDevolucionEsperada, String fechaDevolucionReal,
                            String tipoEstado,Integer huboReservaPrevia,Integer idEjemplarAsociado,
                            Integer idUsuarioAsociado) throws RemoteException;
    
    public Integer modificar(Integer idPrestamo,String instantePrestamo,String fechaDevolucionEsperada, String fechaDevolucionReal,
                            String tipoEstado,Integer huboReservaPrevia,Integer idEjemplarAsociado,
                            Integer idUsuarioAsociado) throws RemoteException;
    
    public Integer eliminar(Integer idPrestamo) throws RemoteException;
    
    public ArrayList<Prestamo> listarTodos(String instantePrestamo,String fechaDevolucionEsperada, String fechaDevolucionReal,String tipoEstado,
                                           Integer huboReservaPrevia,Integer idEjemplarAsociado,Integer idUsuarioAsociado, Integer limite) throws RemoteException;
    
    public Prestamo obtenerPorId(Integer idPrestamo) throws RemoteException;
       
    public ArrayList<Prestamo> mostrarPrestamosEnCurso(Integer idUsuario, Integer limite) throws RemoteException;
    
    public ArrayList<Prestamo> mostrarUltimosPrestamos(Integer limite) throws RemoteException;
    
    public Integer solicitarAmpliacion(Integer idPrestamo) throws RemoteException;
    
    public Integer confirmarAmpliacion(Integer idPrestamo) throws RemoteException;
    
    public ArrayList<String> consultarCalificacionPrestamo(Integer idUsuario, Integer idCopiaEjemplar) throws RemoteException;

    public Integer registrarPrestamo(Integer idUsuario, Integer idCopiaEjemplar) throws RemoteException;

    public Prestamo consultarPrestamoQueSePrestoCopiaEjemplar(Integer idCopiaEjemplar) throws RemoteException;
    
    public int confirmarDevolucion(Integer idPrestamo,Integer  idCopiaEjemplar, Integer estaEnMalEstado) throws RemoteException;
    
}
