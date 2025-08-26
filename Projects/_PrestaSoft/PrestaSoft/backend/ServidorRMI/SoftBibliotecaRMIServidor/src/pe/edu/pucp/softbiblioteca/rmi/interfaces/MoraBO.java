package pe.edu.pucp.softbiblioteca.rmi.interfaces;

import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.politicas.model.Mora;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MoraBO extends Remote{
    
    public Integer insertar(Float monto) throws RemoteException;
    
    public Integer modificar(Integer idMora, Float monto) throws RemoteException;

    public Integer eliminar(Integer idMora) throws RemoteException;

    public ArrayList<Mora> listarTodos(Integer limite) throws RemoteException;

    public Mora obtenerPorId(Integer idMora) throws RemoteException;
    
}
