
package pe.edu.pucp.softbiblioteca.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Libro;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.LibroBO;

public class LibroBOImpl extends UnicastRemoteObject implements LibroBO{

    private pe.edu.pucp.softbiblioteca.publicaciones.bo.LibroBO libroBO;
    
    public LibroBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.libroBO = new pe.edu.pucp.softbiblioteca.publicaciones.bo.LibroBO();
    }
    
    @Override
    public Integer insertar(String titulo, String fechaPublicacion, String descripcion, Integer copiasDisponibles, Integer hayFormatoFisico, Integer hayFormatoVirtual, String url, byte[] portada, Integer idBibliotecaAsociada, Integer ISBN, Integer idEditorial, String tipo, Integer volumen, String materia, String genero, Integer tomo) throws RemoteException {
        return this.libroBO.insertar(titulo, fechaPublicacion, descripcion, copiasDisponibles, hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada, ISBN, idEditorial, tipo, volumen, materia, genero, tomo);
    }

    @Override
    public Integer modificar(Integer idLibro, String titulo, String fechaPublicacion, String descripcion, Integer copiasDisponibles, Integer hayFormatoFisico, Integer hayFormatoVirtual, String url, byte[] portada, Integer idBibliotecaAsociada, Integer ISBN, Integer idEditorial, String tipo, Integer volumen, String materia, String genero, Integer tomo) throws RemoteException {
        return this.libroBO.modificar(idLibro, titulo, fechaPublicacion, descripcion, copiasDisponibles, hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada, ISBN, idEditorial, tipo, volumen, materia, genero, tomo);
    }

    @Override
    public Integer eliminar(Integer idLibro) throws RemoteException {
        return this.libroBO.eliminar(idLibro);
    }

    @Override
    public ArrayList<Libro> listarTodos(String titulo, String fechaPublicacion, String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual, Integer idBibliotecaAsociada, Integer idEditorial, String tipo, Integer volumen, String materia, String genero, Integer tomo, Integer limite) throws RemoteException {
        return this.libroBO.listarTodos(titulo, fechaPublicacion, descripcion, hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada, idEditorial, tipo, volumen, materia, genero, tomo, limite);
    }

    @Override
    public Libro verDetallePublicacionLibro(Integer idLibro) throws RemoteException {
        return this.libroBO.verDetallePublicacionLibro(idLibro);
    }

    @Override
    public Integer nuevaPublicacionLibro(String titulo, String fechaPublicacion, String descripcion, Integer copiasDisponibles, Integer hayFormatoFisico, Integer hayFormatoVirtual, String url, byte[] portada, Integer idBibliotecaAsociada, Integer ISBN, Integer idEditorial, String tipo, Integer volumen, String materia, String genero, Integer tomo, ArrayList<String> direccioneslocal, ArrayList<Integer> autores) throws RemoteException {
        return this.libroBO.nuevaPublicacionLibro(titulo, fechaPublicacion, descripcion, copiasDisponibles, hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada, ISBN, idEditorial, tipo, volumen, materia, genero, tomo, direccioneslocal, autores);
    }

    @Override
    public Integer modificarPublicacionLibro(Integer idLibro, String titulo, String fechaPublicacion, String descripcion, Integer copiasDisponibles, Integer hayFormatoFisico, Integer hayFormatoVirtual, String url, byte[] portada, Integer idBibliotecaAsociada, Integer ISBN, Integer idEditorial, String tipo, Integer volumen, String materia, String genero, Integer tomo, ArrayList<Integer> idsCopiasEjemplares, ArrayList<String> estadosCopiasEjemplares, ArrayList<Integer> autores, Integer cantCopiasPorInsertar) throws RemoteException {
        return this.libroBO.modificarPublicacionLibro(idLibro, titulo, fechaPublicacion, descripcion, copiasDisponibles, hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada, ISBN, idEditorial, tipo, volumen, materia, genero, tomo, idsCopiasEjemplares, estadosCopiasEjemplares, autores, cantCopiasPorInsertar);
    }

    @Override
    public ArrayList<Libro> listarTodos_LibrosDeAutorPorID(Integer idAutor) throws RemoteException {
        return this.libroBO.listarTodos_LibrosDeAutorPorID(idAutor);
    }

    @Override
    public ArrayList<Libro> listarTodos_LibrosDeAutor(String titulo, String fechaPublicacion, String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual, Integer idBibliotecaAsociada, Integer idEditorial, String tipo, Integer volumen, String materia, String genero, Integer tomo, Integer idAutor, String nombreCompleto) throws RemoteException {
        return this.libroBO.listarTodos_LibrosDeAutor(titulo, fechaPublicacion, descripcion, hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada, idEditorial, tipo, volumen, materia, genero, tomo, idAutor, nombreCompleto);
    }
    
}
