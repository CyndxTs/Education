
package pe.edu.pucp.softbiblioteca.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.politicas.model.PoliticaReserva;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.PoliticaReservaBO;

public class PoliticaReservaBOImpl extends UnicastRemoteObject implements PoliticaReservaBO{

    private pe.edu.pucp.softbiblioteca.politicas.bo.PoliticaReservaBO politicareservaBO;
    
    public PoliticaReservaBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.politicareservaBO = new pe.edu.pucp.softbiblioteca.politicas.bo.PoliticaReservaBO();
    }
    
    @Override
    public Integer insertar(Integer cantMaxHorasDeRecojo, Integer cantMaxReservasAgendadasPorUsuario) throws RemoteException {
        return this.politicareservaBO.insertar(cantMaxHorasDeRecojo, cantMaxReservasAgendadasPorUsuario);
    }

    @Override
    public Integer modificar(Integer idPoliticaReserva, Integer cantMaxHorasDeRecojo, Integer cantMaxReservasAgendadasPorUsuario) throws RemoteException {
        return this.politicareservaBO.modificar(idPoliticaReserva, cantMaxHorasDeRecojo, cantMaxReservasAgendadasPorUsuario);
    }

    @Override
    public Integer eliminar(Integer idPoliticaReserva) throws RemoteException {
        return this.politicareservaBO.eliminar(idPoliticaReserva);
    }

    @Override
    public ArrayList<PoliticaReserva> listarTodos(Integer limite) throws RemoteException {
        return this.politicareservaBO.listarTodos(limite);
    }

    @Override
    public PoliticaReserva obtenerPorId(Integer idPoliticaReserva) throws RemoteException {
        return this.politicareservaBO.obtenerPorId(idPoliticaReserva);
    }
    
}
