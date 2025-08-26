
package pe.edu.pucp.softbiblioteca.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.reservas.model.Reserva;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.ReservaBO;

public class ReservaBOImpl extends UnicastRemoteObject implements ReservaBO{

    private pe.edu.pucp.softbiblioteca.reservas.bo.ReservaBO reservaBO;
    
    public ReservaBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.reservaBO = new pe.edu.pucp.softbiblioteca.reservas.bo.ReservaBO();
    }
    
    @Override
    public Integer insertar(String instanteReserva, String instanteRecojo, String tipoEstado, Integer idEjemplarAsociado, Integer idUsuarioAsociado) throws RemoteException {
        return this.reservaBO.insertar(instanteReserva, instanteRecojo, tipoEstado, idEjemplarAsociado, idUsuarioAsociado);
    }

    @Override
    public Integer modificar(Integer idReserva, String instanteReserva, String instanteRecojo, String tipoEstado, Integer idEjemplarAsociado, Integer idUsuarioAsociado) throws RemoteException {
        return this.reservaBO.modificar(idReserva, instanteReserva, instanteRecojo, tipoEstado, idEjemplarAsociado, idUsuarioAsociado);
    }

    @Override
    public Integer eliminar(Integer idReserva) throws RemoteException {
        return this.reservaBO.eliminar(idReserva);
    }

    @Override
    public ArrayList<Reserva> listarTodos(String instanteReserva, String instanteRecojo, String tipoEstado, Integer idEjemplarAsociado, Integer idUsuarioAsociado, Integer limite) throws RemoteException {
        return this.reservaBO.listarTodos(instanteReserva, instanteRecojo, tipoEstado, idEjemplarAsociado, idUsuarioAsociado, limite);
    }

    @Override
    public Reserva obtenerPorId(Integer idReserva) throws RemoteException {
        return this.reservaBO.obtenerPorId(idReserva);
    }

    @Override
    public ArrayList<Reserva> mostrarReservasEnCurso(Integer idUsuario) throws RemoteException {
        return this.reservaBO.mostrarReservasEnCurso(idUsuario);
    }

    @Override
    public Integer cancelarReserva(Integer idReserva) throws RemoteException {
        return this.reservaBO.cancelarReserva(idReserva);
    }

    @Override
    public Reserva solicitarReserva(Integer idUsuario, Integer idPublicacion, String tipoPublicacion) throws RemoteException {
        return this.reservaBO.solicitarReserva(idUsuario, idPublicacion, tipoPublicacion);
    }

    @Override
    public Integer confirmarReserva(Integer idUsuario, Integer idEjemplarAsociado, String instanteRecojoMax) throws RemoteException {
        return this.reservaBO.confirmarReserva(idUsuario, idEjemplarAsociado, instanteRecojoMax);
    }
    
}
