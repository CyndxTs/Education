
package pe.edu.pucp.softbiblioteca.rmi.interfaces;

import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Libro;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LibroBO extends Remote{
    
    public Integer insertar(String titulo,String fechaPublicacion, String descripcion,
                            Integer copiasDisponibles, Integer hayFormatoFisico,
                            Integer hayFormatoVirtual, String url, byte[] portada,
                            Integer idBibliotecaAsociada,Integer ISBN,Integer idEditorial,String tipo,
                            Integer volumen, String materia, String genero, Integer tomo) throws RemoteException;
    
    public Integer modificar(Integer idLibro,String titulo, String fechaPublicacion, String descripcion,
                            Integer copiasDisponibles, Integer hayFormatoFisico,
                            Integer hayFormatoVirtual, String url, byte[] portada,
                            Integer idBibliotecaAsociada,Integer ISBN,Integer idEditorial,String tipo,
                            Integer volumen, String materia, String genero, Integer tomo) throws RemoteException;
    
    public Integer eliminar(Integer idLibro) throws RemoteException;
    
    public ArrayList<Libro> listarTodos(String titulo, String fechaPublicacion, String descripcion,
                                        Integer hayFormatoFisico,Integer hayFormatoVirtual,Integer idBibliotecaAsociada,
                                        Integer idEditorial,String tipo,Integer volumen,
                                        String materia, String genero, Integer tomo, Integer limite) throws RemoteException;
    
    public Libro verDetallePublicacionLibro(Integer idLibro) throws RemoteException;
    
    public Integer nuevaPublicacionLibro(String titulo,String fechaPublicacion, String descripcion,
                            Integer copiasDisponibles, Integer hayFormatoFisico,
                            Integer hayFormatoVirtual, String url, byte[] portada,
                            Integer idBibliotecaAsociada,Integer ISBN,Integer idEditorial,String tipo,
                            Integer volumen, String materia, String genero, Integer tomo, ArrayList<String> direccioneslocal,
                            ArrayList<Integer> autores) throws RemoteException;
    
    public Integer modificarPublicacionLibro(Integer idLibro,String titulo, String fechaPublicacion, String descripcion,
                            Integer copiasDisponibles, Integer hayFormatoFisico,
                            Integer hayFormatoVirtual, String url, byte[] portada,
                            Integer idBibliotecaAsociada,Integer ISBN,Integer idEditorial,String tipo,
                            Integer volumen, String materia, String genero, Integer tomo,
                            ArrayList<Integer> idsCopiasEjemplares, ArrayList<String> estadosCopiasEjemplares,
                            ArrayList<Integer> autores,Integer cantCopiasPorInsertar) throws RemoteException;
    
    public ArrayList<Libro> listarTodos_LibrosDeAutorPorID(Integer idAutor) throws RemoteException;
    
    public ArrayList<Libro> listarTodos_LibrosDeAutor(String titulo, String fechaPublicacion,
                                                      String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual,
                                                      Integer idBibliotecaAsociada, Integer idEditorial, String tipo, Integer volumen,
                                                      String materia, String genero, Integer tomo, Integer idAutor, String nombreCompleto) throws RemoteException;
    
    
}
