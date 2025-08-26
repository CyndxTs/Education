package pe.edu.pucp.softbiblioteca.rmi.interfaces;

import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tesis;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TesisBO extends Remote{
    
    public Integer insertar(String titulo, String fechaPublicacion, String descripcion,
                            Integer copiasDisponibles, Integer hayFormatoFisico,Integer hayFormatoVirtual, String url, byte[] portada,
                            Integer idBibliotecaAsociada,String gradoAcademico) throws RemoteException;
    
    public Integer modificar(Integer idTesis,String titulo, String fechaPublicacion, String descripcion,
                            Integer copiasDisponibles, Integer hayFormatoFisico,Integer hayFormatoVirtual, String url, byte[] portada,
                            Integer idBibliotecaAsociada,String gradoAcademico) throws RemoteException;
    
    public Integer eliminar(Integer idTesis) throws RemoteException;
    
    public ArrayList<Tesis> listarTodos(Integer limite) throws RemoteException;
    
    public ArrayList<Tesis> listarTodos(String titulo, String fechaPublicacion, String descripcion,
                                        Integer hayFormatoFisico,Integer hayFormatoVirtual,Integer idBibliotecaAsociada,
                                        String gradoAcademico, Integer limite) throws RemoteException;
    
    public Tesis verDetallePublicacioTesis(Integer idTesis) throws RemoteException;
    
    public Integer nuevaPublicacionTesis(String titulo, String fechaPublicacion, String descripcion,
                            Integer copiasDisponibles, Integer hayFormatoFisico,Integer hayFormatoVirtual, String url, byte[] portada,
                            Integer idBibliotecaAsociada,String gradoAcademico, ArrayList<String> direccioneslocal,
                            ArrayList<Integer> autores,ArrayList<Integer> temas) throws RemoteException;
    
    public Integer modificarPublicacionTesis(Integer idTesis,String titulo, String fechaPublicacion, String descripcion,
                            Integer copiasDisponibles, Integer hayFormatoFisico,Integer hayFormatoVirtual, String url, byte[] portada,
                            Integer idBibliotecaAsociada,String gradoAcademico,
                            ArrayList<Integer> idsCopiasEjemplares, ArrayList<String> estadosCopiasEjemplares,
                            ArrayList<Integer> autores,ArrayList<Integer> temas,Integer cantCopiasPorInsertar) throws RemoteException;
    
    public ArrayList<Tesis> listarTodos_TesisDeAutorPorID(Integer idAutor) throws RemoteException;
    
    public ArrayList<Tesis> listarTodos_TesisDeAutor(String titulo, String fechaPublicacion,
                                                     String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual,
                                                     Integer idBibliotecaAsociada, String gradoAcademico, Integer idAutor,
                                                     String nombreCompleto) throws RemoteException;
    
    public ArrayList<Tesis> listarTodos_TesisDeTemaPorID(Integer idTema) throws RemoteException;
    
    public ArrayList<Tesis> listarTodos_TesisDeTema(String ts_titulo, String fechaPublicacion,
                                                    String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual,
                                                    Integer idBibliotecaAsociada, String gradoAcademico, Integer idTema,
                                                    String tm_titulo) throws RemoteException;
    
}
