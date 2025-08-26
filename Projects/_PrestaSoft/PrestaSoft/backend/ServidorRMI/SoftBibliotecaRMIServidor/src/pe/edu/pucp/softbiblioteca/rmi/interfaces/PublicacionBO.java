
package pe.edu.pucp.softbiblioteca.rmi.interfaces;

import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.CopiaEjemplar;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Publicacion;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PublicacionBO extends Remote{
    
    public ArrayList<Publicacion> verCatalogo(Integer modoDeBusqueda,Integer idBibliotecaAsociada,
                                              String tipo,String titulo, Integer limite) throws RemoteException;
    
    public ArrayList<Publicacion> busquedaAvanzada(Integer modoDeBusqueda,String titulo,
                                              String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual,
                                              Integer idBibliotecaAsociada, Integer idAutor)  throws RemoteException;
     
    public ArrayList<Publicacion> publicacionesPorAutor(Integer id) throws RemoteException;
    
    public CopiaEjemplar consultarCopiaEjemplar(Integer id) throws RemoteException;
    
}
