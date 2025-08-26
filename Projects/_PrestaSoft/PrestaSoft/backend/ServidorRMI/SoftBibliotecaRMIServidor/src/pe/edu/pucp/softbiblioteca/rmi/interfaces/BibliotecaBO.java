package pe.edu.pucp.softbiblioteca.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.localidad.model.Biblioteca;

public interface BibliotecaBO extends Remote{
    
    public Integer insertar(String nombre,String ubicacion,Integer idUniversidadAsociada) throws RemoteException;

    public Integer modificar(Integer idBiblioteca,String nombre,String ubicacion,
                             Integer idUniversidadAsociada) throws RemoteException;

    public Integer eliminar(Integer idBiblioteca) throws RemoteException;

    public ArrayList<Biblioteca> listarTodos(Integer idUniversidadAsociada, Integer limite) throws RemoteException;

    public Biblioteca obtenerPorId(Integer idBiblioteca) throws RemoteException;
    
    public ArrayList<Biblioteca> mostrarBibliotecasDeUniversidadesDelProgramador(Integer idUsuario, 
            ArrayList<Integer>idUniversidadesAsociadasDelBibliotecario) throws RemoteException;
    
}
