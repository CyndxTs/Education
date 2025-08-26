package pe.edu.pucp.softbiblioteca.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.CopiaEjemplar;


public interface CopiaEjemplarBO extends Remote{
 
    public ArrayList<CopiaEjemplar>ObtenerCopiasEjemplaresDeLibro(Integer idLibro) throws RemoteException;
    
    public ArrayList<CopiaEjemplar>ObtenerCopiasEjemplaresDeTesis(Integer idTesis) throws RemoteException;
    
    public CopiaEjemplar obtenerPorId(Integer idCopiaEjemplar) throws RemoteException;
    
}
