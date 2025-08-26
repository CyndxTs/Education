
package pe.edu.pucp.softbiblioteca.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.localidad.model.Universidad;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.UniversidadBO;

public class UniversidadBOImpl extends UnicastRemoteObject implements UniversidadBO{

    private pe.edu.pucp.softbiblioteca.localidad.bo.UniversidadBO universidadBO;
    
    public UniversidadBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.universidadBO = new pe.edu.pucp.softbiblioteca.localidad.bo.UniversidadBO();
    }
    
    @Override
    public Integer insertar(String nombre, String direccion, String correoPersonalAdministrativo, String extensionDominioCorreo, Integer idPoliticaRegular, Integer idConsorcioPerteneciente) throws RemoteException {
        return this.universidadBO.insertar(nombre, direccion, correoPersonalAdministrativo, extensionDominioCorreo, idPoliticaRegular, idConsorcioPerteneciente);
    }

    @Override
    public Integer modificar(Integer idUniversidad, String nombre, String direccion, String correoPersonalAdministrativo, String extensionDominioCorreo, Integer idPoliticaRegular, Integer idConsorcioPerteneciente) throws RemoteException {
        return this.universidadBO.modificar(idUniversidad, nombre, direccion, correoPersonalAdministrativo, extensionDominioCorreo, idPoliticaRegular, idConsorcioPerteneciente);
    }

    @Override
    public Integer eliminar(int idUniversidad) throws RemoteException {
        return this.universidadBO.eliminar(idUniversidad);
    }

    @Override
    public ArrayList<Universidad> listarTodos(Integer idPoliticaRegular, Integer idConsorcioPerteneciente, Integer limite) throws RemoteException {
        return this.universidadBO.listarTodos(idPoliticaRegular, idConsorcioPerteneciente, limite);
    }

    @Override
    public Universidad obtenerPorId(int idUniversidad) throws RemoteException {
        return this.universidadBO.obtenerPorId(idUniversidad);
    }

    @Override
    public ArrayList<Universidad> listarTodos_UniversidadesDeUsuarioPorID(Integer idUsuario) throws RemoteException {
        return this.universidadBO.listarTodos_UniversidadesDeUsuarioPorID(idUsuario);
    }

    @Override
    public ArrayList<Universidad> listarTodos_UniversidadesDeUsuario(Integer idUsuario, String nombres, String apellidos, String correoInstitucional, String nombre_usuario, String fechaRegistro, String tipoUsuario, Integer idPoliticaRegular, Integer idConsorcioPerteneciente) throws RemoteException {
        return this.universidadBO.listarTodos_UniversidadesDeUsuario(idUsuario, nombres, apellidos, correoInstitucional, nombre_usuario, fechaRegistro, tipoUsuario, idPoliticaRegular, idConsorcioPerteneciente);
    }

    @Override
    public Integer eliminarUniversidadesDeUsuario(Integer idUsuario) throws RemoteException {
        return this.universidadBO.eliminarUniversidadesDeUsuario(idUsuario);
    }
    
}
