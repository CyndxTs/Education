
package pe.edu.pucp.softbiblioteca.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.politicas.model.Mora;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.MoraBO;

public class MoraBOImpl extends UnicastRemoteObject implements MoraBO{
    
    private pe.edu.pucp.softbiblioteca.politicas.bo.MoraBO moraBO;
    
    public MoraBOImpl(Integer puerto) throws RemoteException {
        super(puerto);
        this.moraBO = new pe.edu.pucp.softbiblioteca.politicas.bo.MoraBO();
    }
    
    @Override
    public Integer insertar(Float monto) throws RemoteException {
        return this.moraBO.insertar(monto);
    }

    @Override
    public Integer modificar(Integer idMora, Float monto) throws RemoteException {
        return this.moraBO.modificar(idMora, monto);
    }

    @Override
    public Integer eliminar(Integer idMora) throws RemoteException {
        return this.moraBO.eliminar(idMora);
    }

    @Override
    public ArrayList<Mora> listarTodos(Integer limite) throws RemoteException {
        return this.moraBO.listarTodos(limite);
    }

    @Override
    public Mora obtenerPorId(Integer idMora) throws RemoteException {
        return this.moraBO.obtenerPorId(idMora);
    }
    
}
