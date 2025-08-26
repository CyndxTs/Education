
package pe.edu.pucp.softbiblioteca.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tesis;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.TesisBO;

public class TesisBOImpl extends UnicastRemoteObject implements TesisBO{

    private pe.edu.pucp.softbiblioteca.publicaciones.bo.TesisBO tesisBO;
    
    public TesisBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.tesisBO = new pe.edu.pucp.softbiblioteca.publicaciones.bo.TesisBO();
    }
    
    @Override
    public Integer insertar(String titulo, String fechaPublicacion, String descripcion, Integer copiasDisponibles, Integer hayFormatoFisico, Integer hayFormatoVirtual, String url, byte[] portada, Integer idBibliotecaAsociada, String gradoAcademico) throws RemoteException {
        return this.tesisBO.insertar(titulo, fechaPublicacion, descripcion, copiasDisponibles, hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada, gradoAcademico);
    }

    @Override
    public Integer modificar(Integer idTesis, String titulo, String fechaPublicacion, String descripcion, Integer copiasDisponibles, Integer hayFormatoFisico, Integer hayFormatoVirtual, String url, byte[] portada, Integer idBibliotecaAsociada, String gradoAcademico) throws RemoteException {
        return this.tesisBO.modificar(idTesis, titulo, fechaPublicacion, descripcion, copiasDisponibles, hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada, gradoAcademico);
    }

    @Override
    public Integer eliminar(Integer idTesis) throws RemoteException {
        return this.tesisBO.eliminar(idTesis);
    }

    @Override
    public ArrayList<Tesis> listarTodos(Integer limite) throws RemoteException {
        return this.tesisBO.listarTodos(limite);
    }

    @Override
    public ArrayList<Tesis> listarTodos(String titulo, String fechaPublicacion, String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual, Integer idBibliotecaAsociada, String gradoAcademico, Integer limite) throws RemoteException {
        return this.tesisBO.listarTodos(titulo, fechaPublicacion, descripcion, hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada, gradoAcademico, limite);
    }

    @Override
    public Tesis verDetallePublicacioTesis(Integer idTesis) throws RemoteException {
        return this.tesisBO.verDetallePublicacioTesis(idTesis);
    }

    @Override
    public Integer nuevaPublicacionTesis(String titulo, String fechaPublicacion, String descripcion, Integer copiasDisponibles, Integer hayFormatoFisico, Integer hayFormatoVirtual, String url, byte[] portada, Integer idBibliotecaAsociada, String gradoAcademico, ArrayList<String> direccioneslocal, ArrayList<Integer> autores, ArrayList<Integer> temas) throws RemoteException {
        return this.tesisBO.nuevaPublicacionTesis(titulo, fechaPublicacion, descripcion, copiasDisponibles, hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada, gradoAcademico, direccioneslocal, autores, temas);
    }

    @Override
    public Integer modificarPublicacionTesis(Integer idTesis, String titulo, String fechaPublicacion, String descripcion, Integer copiasDisponibles, Integer hayFormatoFisico, Integer hayFormatoVirtual, String url, byte[] portada, Integer idBibliotecaAsociada, String gradoAcademico, ArrayList<Integer> idsCopiasEjemplares, ArrayList<String> estadosCopiasEjemplares, ArrayList<Integer> autores, ArrayList<Integer> temas, Integer cantCopiasPorInsertar) throws RemoteException {
        return this.tesisBO.modificarPublicacionTesis(idTesis, titulo, fechaPublicacion, descripcion, copiasDisponibles, hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada, gradoAcademico, idsCopiasEjemplares, estadosCopiasEjemplares, autores, temas, cantCopiasPorInsertar);
    }

    @Override
    public ArrayList<Tesis> listarTodos_TesisDeAutorPorID(Integer idAutor) throws RemoteException {
        return this.tesisBO.listarTodos_TesisDeAutorPorID(idAutor);
    }

    @Override
    public ArrayList<Tesis> listarTodos_TesisDeAutor(String titulo, String fechaPublicacion, String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual, Integer idBibliotecaAsociada, String gradoAcademico, Integer idAutor, String nombreCompleto) throws RemoteException {
        return this.tesisBO.listarTodos_TesisDeAutor(titulo, fechaPublicacion, descripcion, hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada, gradoAcademico, idAutor, nombreCompleto);
    }

    @Override
    public ArrayList<Tesis> listarTodos_TesisDeTemaPorID(Integer idTema) throws RemoteException {
        return this.tesisBO.listarTodos_TesisDeTemaPorID(idTema);
    }

    @Override
    public ArrayList<Tesis> listarTodos_TesisDeTema(String ts_titulo, String fechaPublicacion, String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual, Integer idBibliotecaAsociada, String gradoAcademico, Integer idTema, String tm_titulo) throws RemoteException {
        return this.tesisBO.listarTodos_TesisDeTema(ts_titulo, fechaPublicacion, descripcion, hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada, gradoAcademico, idTema, tm_titulo);
    }
    
}
