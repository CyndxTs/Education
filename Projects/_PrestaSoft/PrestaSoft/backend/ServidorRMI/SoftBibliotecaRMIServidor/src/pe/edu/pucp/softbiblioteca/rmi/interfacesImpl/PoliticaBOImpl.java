
package pe.edu.pucp.softbiblioteca.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.politicas.model.Politica;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.PoliticaBO;

public class PoliticaBOImpl extends UnicastRemoteObject implements PoliticaBO{

    private pe.edu.pucp.softbiblioteca.politicas.bo.PoliticaBO politicaBO;
    
    public PoliticaBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.politicaBO = new pe.edu.pucp.softbiblioteca.politicas.bo.PoliticaBO();
    }
    
    @Override
    public Integer insertar(String etiqueta, Integer idPoliticaPrestamo, Integer idPoliticaReserva) throws RemoteException {
        return this.politicaBO.insertar(etiqueta, idPoliticaPrestamo, idPoliticaReserva);
    }

    @Override
    public Integer modificar(Integer idPolitica, String etiqueta, Integer idPoliticaPrestamo, Integer idPoliticaReserva) throws RemoteException {
        return this.politicaBO.modificar(idPolitica, etiqueta, idPoliticaPrestamo, idPoliticaReserva);
    }

    @Override
    public Integer eliminar(Integer idPolitica) throws RemoteException {
        return this.politicaBO.eliminar(idPolitica);
    }

    @Override
    public ArrayList<Politica> listarTodos(Integer idPoliticaPrestamo, Integer idPoliticaReserva, Integer limite) throws RemoteException {
        return this.politicaBO.listarTodos(idPoliticaPrestamo, idPoliticaReserva, limite);
    }

    @Override
    public Politica obtenerPorId(Integer idPolitica) throws RemoteException {
        return this.politicaBO.obtenerPorId(idPolitica);
    }
    
}
