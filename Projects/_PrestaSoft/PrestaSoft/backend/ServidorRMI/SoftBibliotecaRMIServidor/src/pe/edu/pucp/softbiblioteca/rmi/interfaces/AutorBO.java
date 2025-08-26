
package pe.edu.pucp.softbiblioteca.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Autor;

public interface AutorBO extends Remote{
    
    public ArrayList<Autor> buscarAutorPorNombre(String nombre, Integer limite) throws RemoteException;
    
    public Integer insertarAutor(String nombre) throws RemoteException;
    
    public ArrayList<Autor> listarTodos_AutoresDeLibroPorID(Integer idLibro) throws RemoteException;
    
    public ArrayList<Autor> listarTodos_AutoresDeLibro(Integer idLibro, String titulo, String fechaPublicacion,
                                                       String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual,
                                                       Integer idBibliotecaAsociada, Integer idEditorial, String tipo, Integer volumen,
                                                       String materia, String genero, Integer tomo, String nombreCompleto) throws RemoteException;
    
    public ArrayList<Autor> listarTodos_AutoresDeTesisPorID(Integer idTesis) throws RemoteException;
    
    public ArrayList<Autor> listarTodos_AutoresDeTesis(Integer idTesis,String titulo, String fechaPublicacion,
                                                       String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual,
                                                       Integer idBibliotecaAsociada, String gradoAcademico, String nombreCompleto) throws RemoteException;
    
    public Integer insertar(String nombreCompleto) throws RemoteException;
    
    public Integer modificar(Integer idAutor,String nombreCompleto) throws RemoteException;
    
    public Integer eliminar(Integer idAutor) throws RemoteException;
    
    public Autor obtenerPorId(Integer idAutor) throws RemoteException;
    
}
