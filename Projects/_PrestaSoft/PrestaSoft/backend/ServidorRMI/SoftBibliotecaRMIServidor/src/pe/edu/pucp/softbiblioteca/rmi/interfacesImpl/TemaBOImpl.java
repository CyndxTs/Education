
package pe.edu.pucp.softbiblioteca.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tema;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.TemaBO;

public class TemaBOImpl extends UnicastRemoteObject implements TemaBO{

    private pe.edu.pucp.softbiblioteca.publicaciones.bo.TemaBO temaBO;
    
    public TemaBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.temaBO = new pe.edu.pucp.softbiblioteca.publicaciones.bo.TemaBO();
    }
    
    @Override
    public ArrayList<Tema> buscarTemaPorNombre(String titulo, Integer limite) throws RemoteException {
        return this.temaBO.buscarTemaPorNombre(titulo, limite);
    }

    @Override
    public Integer insertarTema(String titulo) throws RemoteException {
        return this.temaBO.insertarTema(titulo);
    }

    @Override
    public ArrayList<Tema> listarTodos_TemasDeTesisPorID(Integer idTesis) throws RemoteException {
        return this.temaBO.listarTodos_TemasDeTesisPorID(idTesis);
    }

    @Override
    public ArrayList<Tema> listarTodos_TemasDeTesis(Integer idTesis, String ts_titulo, String fechaPublicacion, String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual, Integer idBibliotecaAsociada, String gradoAcademico, String tm_titulo) throws RemoteException {
        return this.temaBO.listarTodos_TemasDeTesis(idTesis, ts_titulo, fechaPublicacion, descripcion, hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada, gradoAcademico, tm_titulo);
    }

    @Override
    public Integer insertar(String titulo) throws RemoteException {
        return this.temaBO.insertar(titulo);
    }
    
}
