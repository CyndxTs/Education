
package pe.edu.pucp.softbiblioteca.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.localidad.model.Consorcio;


public interface ConsorcioBO extends Remote{
    
    public Integer insertar(String CIF) throws RemoteException;

    public Integer modificar(Integer idConsorcio,String CIF) throws RemoteException;

    public Integer eliminar(Integer idConsorcio) throws RemoteException;

    public ArrayList<Consorcio> listarTodos(Integer limiteListado) throws RemoteException;

    public Consorcio obtenerPorId(Integer idConsorcio) throws RemoteException;
    
}
