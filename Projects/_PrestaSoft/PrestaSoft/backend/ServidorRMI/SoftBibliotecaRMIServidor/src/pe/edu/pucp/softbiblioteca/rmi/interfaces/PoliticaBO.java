
package pe.edu.pucp.softbiblioteca.rmi.interfaces;

import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.politicas.model.Politica;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface PoliticaBO extends Remote{
    
    public Integer insertar(String etiqueta,Integer idPoliticaPrestamo,Integer idPoliticaReserva) throws RemoteException;

    public Integer modificar(Integer idPolitica,String etiqueta,Integer idPoliticaPrestamo,
                             Integer idPoliticaReserva) throws RemoteException;

    public Integer eliminar(Integer idPolitica) throws RemoteException;
    
    public ArrayList<Politica> listarTodos(Integer idPoliticaPrestamo,Integer idPoliticaReserva, Integer limite) throws RemoteException;

    public Politica obtenerPorId(Integer idPolitica) throws RemoteException;
    
}
