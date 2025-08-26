
package pe.edu.pucp.softbiblioteca.rmi.interfacesImpl;

import java.rmi.server.UnicastRemoteObject;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.AutorBO;
import java.rmi.RemoteException;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Autor;

public class AutorBOImpl extends UnicastRemoteObject implements AutorBO{
    
    private pe.edu.pucp.softbiblioteca.publicaciones.bo.AutorBO autorBO;
    
    public AutorBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.autorBO = new pe.edu.pucp.softbiblioteca.publicaciones.bo.AutorBO();
    }

    @Override
    public ArrayList<Autor> buscarAutorPorNombre(String nombre, Integer limite) throws RemoteException {
        return this.autorBO.buscarAutorPorNombre(nombre, limite);
    }

    @Override
    public Integer insertarAutor(String nombre) throws RemoteException {
        return this.autorBO.insertarAutor(nombre);
    }

    @Override
    public ArrayList<Autor> listarTodos_AutoresDeLibroPorID(Integer idLibro) throws RemoteException {
        return this.autorBO.listarTodos_AutoresDeLibroPorID(idLibro);
    }

    @Override
    public ArrayList<Autor> listarTodos_AutoresDeLibro(Integer idLibro, String titulo, String fechaPublicacion, String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual, Integer idBibliotecaAsociada, Integer idEditorial, String tipo, Integer volumen, String materia, String genero, Integer tomo, String nombreCompleto) throws RemoteException {
        return this.autorBO.listarTodos_AutoresDeLibro(idLibro, titulo, fechaPublicacion, descripcion, hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada, idEditorial, tipo, volumen, materia, genero, tomo, nombreCompleto);
    }

    @Override
    public ArrayList<Autor> listarTodos_AutoresDeTesisPorID(Integer idTesis) throws RemoteException {
        return this.autorBO.listarTodos_AutoresDeTesisPorID(idTesis);
    }

    @Override
    public ArrayList<Autor> listarTodos_AutoresDeTesis(Integer idTesis, String titulo, String fechaPublicacion, String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual, Integer idBibliotecaAsociada, String gradoAcademico, String nombreCompleto) throws RemoteException {
        return this.autorBO.listarTodos_AutoresDeTesis(idTesis, titulo, fechaPublicacion, descripcion, hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada, gradoAcademico, nombreCompleto);
    }

    @Override
    public Integer insertar(String nombreCompleto) throws RemoteException {
        return this.autorBO.insertar(nombreCompleto);
    }

    @Override
    public Integer modificar(Integer idAutor, String nombreCompleto) throws RemoteException {
        return this.autorBO.modificar(idAutor, nombreCompleto);
    }

    @Override
    public Integer eliminar(Integer idAutor) throws RemoteException {
        return this.autorBO.eliminar(idAutor);
    }

    @Override
    public Autor obtenerPorId(Integer idAutor) throws RemoteException {
        return this.autorBO.obtenerPorId(idAutor);
    }
    
}
