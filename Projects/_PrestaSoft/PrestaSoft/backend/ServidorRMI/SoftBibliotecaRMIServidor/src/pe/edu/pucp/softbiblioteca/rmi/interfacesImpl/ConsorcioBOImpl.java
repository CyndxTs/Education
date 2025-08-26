
package pe.edu.pucp.softbiblioteca.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.localidad.model.Consorcio;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.ConsorcioBO;

public class ConsorcioBOImpl extends UnicastRemoteObject implements ConsorcioBO{

    private pe.edu.pucp.softbiblioteca.localidad.bo.ConsorcioBO consorcioBO;
    
    public ConsorcioBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.consorcioBO = new pe.edu.pucp.softbiblioteca.localidad.bo.ConsorcioBO();
    }
    
    @Override
    public Integer insertar(String CIF) throws RemoteException {
        return this.consorcioBO.insertar(CIF);
    }

    @Override
    public Integer modificar(Integer idConsorcio, String CIF) throws RemoteException {
        return this.consorcioBO.modificar(idConsorcio, CIF);
    }

    @Override
    public Integer eliminar(Integer idConsorcio) throws RemoteException {
        return this.consorcioBO.eliminar(idConsorcio);
    }

    @Override
    public ArrayList<Consorcio> listarTodos(Integer limiteListado) throws RemoteException {
        return this.consorcioBO.listarTodos(limiteListado);
    }

    @Override
    public Consorcio obtenerPorId(Integer idConsorcio) throws RemoteException {
        return this.consorcioBO.obtenerPorId(idConsorcio);
    }
    
}
