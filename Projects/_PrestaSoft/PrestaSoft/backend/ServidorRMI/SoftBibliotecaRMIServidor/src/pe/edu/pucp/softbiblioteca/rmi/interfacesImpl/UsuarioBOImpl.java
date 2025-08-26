
package pe.edu.pucp.softbiblioteca.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.UsuarioBO;
import pe.edu.pucp.softbiblioteca.usuarios.model.Usuario;

public class UsuarioBOImpl extends UnicastRemoteObject implements UsuarioBO{

    private pe.edu.pucp.softbiblioteca.usuarios.bo.UsuarioBO usuarioBO;
    
    public UsuarioBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.usuarioBO = new pe.edu.pucp.softbiblioteca.usuarios.bo.UsuarioBO();
    }
    
    @Override
    public Integer insertar(String nombres, String apellidos, String correo, String contrasenia, String nombreUsuario, String fechaRegistro, String tipoUsuario) throws RemoteException {
        try {
            return this.usuarioBO.insertar(nombres, apellidos, correo, contrasenia, nombreUsuario, fechaRegistro, tipoUsuario);
        } catch (ParseException ex) {
            Logger.getLogger(UsuarioBOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public Integer eliminar(Integer idUsuario) throws RemoteException {
        return this.usuarioBO.eliminar(idUsuario);
    }

    @Override
    public Integer modificar(Integer idUsuario, String nombres, String apellidos, String correo, String contrasenia, String nombreUsuario, String fechaRegistro, String tipoUsuario) throws RemoteException {
        try {
            return this.usuarioBO.modificar(idUsuario, nombres, apellidos, correo, contrasenia, nombreUsuario, fechaRegistro, tipoUsuario);
        } catch (ParseException ex) {
            Logger.getLogger(UsuarioBOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public Usuario iniciarSesion(String correo_nombreusuario, String contrasenia) throws RemoteException {
        return this.usuarioBO.iniciarSesion(correo_nombreusuario, contrasenia);
    }

    @Override
    public Integer registrarMiembro(String nombre, String apellidos, String correo, String contrasenia, String contraseniaconfirmacion, String nombreusuario, String strIdUniversidad) throws RemoteException {
        return this.usuarioBO.registrarMiembro(nombre, apellidos, correo, contrasenia, contraseniaconfirmacion, nombreusuario, strIdUniversidad);
    }

    @Override
    public Usuario consultarMiembroID(Integer idUsuario) throws RemoteException {
        return this.usuarioBO.consultarMiembroID(idUsuario);
    }

    @Override
    public Usuario obtenerPorAtributosUnicos(String correoInstitucional, String contrasenia, String nombre_usuario) throws RemoteException {
        return this.usuarioBO.obtenerPorAtributosUnicos(correoInstitucional, contrasenia, nombre_usuario);
    }

    @Override
    public ArrayList<Usuario> listarTodos(String nombres, String apellidos, String correoInstitucional, String nombre_usuario, String tipoUsuario, Integer limite) throws RemoteException {
        return this.usuarioBO.listarTodos(nombres, apellidos, correoInstitucional, nombre_usuario, tipoUsuario, limite);
    }

    @Override
    public ArrayList<Usuario> listarTodos_UsuariosDeUniversidadPorID(Integer idUniversidad) throws RemoteException {
        return this.usuarioBO.listarTodos_UsuariosDeUniversidadPorID(idUniversidad);
    }

    @Override
    public ArrayList<Usuario> listarTodos_UsuariosDeUniversidad(String nombres, String apellidos, String correoInstitucional, String nombre_usuario, String fechaRegistro, String tipoUsuario, Integer idUniversidad, Integer idPoliticaRegular, Integer idConsorcioPerteneciente) throws RemoteException {
        return this.usuarioBO.listarTodos_UsuariosDeUniversidad(nombres, apellidos, correoInstitucional, nombre_usuario, fechaRegistro, tipoUsuario, idUniversidad, idPoliticaRegular, idConsorcioPerteneciente);
    }

    @Override
    public Integer modificarUniversidadesAsociadasDeUsuario(Integer idUsuario, ArrayList<Integer> idUniversidadesActuales, ArrayList<Integer> idUniversidadesNuevas) throws RemoteException {
        return this.usuarioBO.modificarUniversidadesAsociadasDeUsuario(idUsuario, idUniversidadesActuales, idUniversidadesNuevas);
    }
    
}
