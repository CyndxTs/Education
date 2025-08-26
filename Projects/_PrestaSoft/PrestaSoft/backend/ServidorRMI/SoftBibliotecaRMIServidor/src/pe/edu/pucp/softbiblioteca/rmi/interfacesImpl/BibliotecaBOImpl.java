
package pe.edu.pucp.softbiblioteca.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.localidad.model.Biblioteca;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.BibliotecaBO;

public class BibliotecaBOImpl extends UnicastRemoteObject implements BibliotecaBO{

    private pe.edu.pucp.softbiblioteca.localidad.bo.BibliotecaBO bibliotecaBO;
    
    public BibliotecaBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.bibliotecaBO = new pe.edu.pucp.softbiblioteca.localidad.bo.BibliotecaBO();
    }
    
    @Override
    public Integer insertar(String nombre, String ubicacion, Integer idUniversidadAsociada) throws RemoteException {
        return this.bibliotecaBO.insertar(nombre, ubicacion, idUniversidadAsociada);
    }

    @Override
    public Integer modificar(Integer idBiblioteca, String nombre, String ubicacion, Integer idUniversidadAsociada) throws RemoteException {
        return this.bibliotecaBO.modificar(idBiblioteca, nombre, ubicacion, idUniversidadAsociada);
    }

    @Override
    public Integer eliminar(Integer idBiblioteca) throws RemoteException {
        return this.bibliotecaBO.eliminar(idBiblioteca);
    }

    @Override
    public ArrayList<Biblioteca> listarTodos(Integer idUniversidadAsociada, Integer limite) throws RemoteException {
        return this.bibliotecaBO.listarTodos(idUniversidadAsociada, limite);
    }

    @Override
    public Biblioteca obtenerPorId(Integer idBiblioteca) throws RemoteException {
        return this.bibliotecaBO.obtenerPorId(idBiblioteca);
    }

    @Override
    public ArrayList<Biblioteca> mostrarBibliotecasDeUniversidadesDelProgramador(Integer idUsuario, ArrayList<Integer> idUniversidadesAsociadasDelBibliotecario) throws RemoteException {
        return this.bibliotecaBO.mostrarBibliotecasDeUniversidadesDelProgramador(idUsuario, idUniversidadesAsociadasDelBibliotecario);
    }
    
}
