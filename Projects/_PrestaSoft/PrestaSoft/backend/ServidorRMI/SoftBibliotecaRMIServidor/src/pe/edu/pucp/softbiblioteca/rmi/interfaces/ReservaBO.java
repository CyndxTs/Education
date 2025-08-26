
package pe.edu.pucp.softbiblioteca.rmi.interfaces;

import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.reservas.model.Reserva;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ReservaBO extends Remote{
    
    public Integer insertar(String instanteReserva,String instanteRecojo,String tipoEstado, 
                            Integer idEjemplarAsociado,Integer idUsuarioAsociado) throws RemoteException;
    
    public Integer modificar(Integer idReserva,String instanteReserva,String instanteRecojo,String tipoEstado, 
                            Integer idEjemplarAsociado,Integer idUsuarioAsociado) throws RemoteException;
    
    public Integer eliminar(Integer idReserva) throws RemoteException;
    
    public ArrayList<Reserva> listarTodos(String instanteReserva,String instanteRecojo,
                                          String tipoEstado,Integer idEjemplarAsociado,
                                          Integer idUsuarioAsociado, Integer limite) throws RemoteException;
    
    public Reserva obtenerPorId(Integer idReserva) throws RemoteException;
    
    public ArrayList<Reserva> mostrarReservasEnCurso(Integer idUsuario) throws RemoteException;
    
    public Integer cancelarReserva(Integer idReserva) throws RemoteException;

    public Reserva solicitarReserva(Integer idUsuario, Integer idPublicacion,String tipoPublicacion) throws RemoteException;
    
    public Integer confirmarReserva(Integer idUsuario, Integer idEjemplarAsociado, String instanteRecojoMax) throws RemoteException;
    
}
