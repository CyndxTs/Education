
package pe.edu.pucp.softbiblioteca.rmi.interfaces;

import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tema;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TemaBO extends Remote{
    
    public ArrayList<Tema> buscarTemaPorNombre(String titulo, Integer limite) throws RemoteException;
    
    public Integer insertarTema(String titulo) throws RemoteException;
    
    public ArrayList<Tema> listarTodos_TemasDeTesisPorID(Integer idTesis) throws RemoteException;
    
    public ArrayList<Tema> listarTodos_TemasDeTesis(Integer idTesis, String ts_titulo, String fechaPublicacion,
                                                    String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual,
                                                    Integer idBibliotecaAsociada, String gradoAcademico, String tm_titulo) throws RemoteException;
    
    public Integer insertar(String titulo)throws RemoteException;
    
}
