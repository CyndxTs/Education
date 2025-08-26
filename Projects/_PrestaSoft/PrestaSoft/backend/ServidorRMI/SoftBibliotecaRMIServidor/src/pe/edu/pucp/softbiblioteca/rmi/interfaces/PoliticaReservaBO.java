
package pe.edu.pucp.softbiblioteca.rmi.interfaces;

import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.politicas.model.PoliticaReserva;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PoliticaReservaBO extends Remote{
    
    public Integer insertar(Integer cantMaxHorasDeRecojo,Integer cantMaxReservasAgendadasPorUsuario) throws RemoteException;

    public Integer modificar(Integer idPoliticaReserva,Integer cantMaxHorasDeRecojo,Integer cantMaxReservasAgendadasPorUsuario) throws RemoteException;

    public Integer eliminar(Integer idPoliticaReserva) throws RemoteException;

    public ArrayList<PoliticaReserva> listarTodos(Integer limite) throws RemoteException;

    public PoliticaReserva obtenerPorId(Integer idPoliticaReserva) throws RemoteException;
    
}
