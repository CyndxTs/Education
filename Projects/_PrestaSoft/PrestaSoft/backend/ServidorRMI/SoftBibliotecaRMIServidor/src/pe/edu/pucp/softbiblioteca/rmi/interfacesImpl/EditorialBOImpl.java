
package pe.edu.pucp.softbiblioteca.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Editorial;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.EditorialBO;

public class EditorialBOImpl extends UnicastRemoteObject implements EditorialBO{

    private pe.edu.pucp.softbiblioteca.publicaciones.bo.EditorialBO editorialBO;
    
    public EditorialBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.editorialBO = new pe.edu.pucp.softbiblioteca.publicaciones.bo.EditorialBO();
    }
    
    @Override
    public Integer insertar(String nombre) throws RemoteException {
        return this.editorialBO.insertar(nombre);
    }

    @Override
    public Integer modificar(Integer idEditorial, String nombre) throws RemoteException {
        return this.editorialBO.modificar(idEditorial, nombre);
    }

    @Override
    public Integer eliminar(Integer idEditorial) throws RemoteException {
        return this.editorialBO.eliminar(idEditorial);
    }

    @Override
    public ArrayList<Editorial> listarTodos(String nombre, Integer limite) throws RemoteException {
        return this.editorialBO.listarTodos(nombre, limite);
    }

    @Override
    public Editorial obtenerPorId(Integer idEditorial) throws RemoteException {
        return this.editorialBO.obtenerPorId(idEditorial);
    }

    @Override
    public ArrayList<Editorial> buscarEditorialPorNombre(String nombre) throws RemoteException {
        return this.editorialBO.buscarEditorialPorNombre(nombre);
    }
    
}
