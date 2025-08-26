
package pe.edu.pucp.softbiblioteca.rmi.interfaces;

import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.usuarios.model.Usuario;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UsuarioBO extends Remote{
    
    public Integer insertar(String nombres,String apellidos,String correo,
                            String contrasenia,String nombreUsuario,String fechaRegistro,
                            String tipoUsuario) throws RemoteException;

    public Integer eliminar(Integer idUsuario) throws RemoteException;
    
    public Integer modificar(Integer idUsuario,String nombres,String apellidos,String correo,
                            String contrasenia,String nombreUsuario,String fechaRegistro,
                            String tipoUsuario) throws RemoteException;
    
    public Usuario iniciarSesion(String correo_nombreusuario, String contrasenia) throws RemoteException;
    
    public Integer registrarMiembro(String nombre,String apellidos,String correo,String contrasenia,
                                        String contraseniaconfirmacion,String nombreusuario,String strIdUniversidad) throws RemoteException;
    
    public Usuario consultarMiembroID(Integer idUsuario) throws RemoteException;
        
    public Usuario obtenerPorAtributosUnicos(String correoInstitucional,String contrasenia,String nombre_usuario) throws RemoteException;
    
    public ArrayList<Usuario> listarTodos(String nombres, String apellidos,String correoInstitucional,
                                          String nombre_usuario,String tipoUsuario, Integer limite) throws RemoteException;
    
    public ArrayList<Usuario> listarTodos_UsuariosDeUniversidadPorID(Integer idUniversidad) throws RemoteException;
    
    public ArrayList<Usuario> listarTodos_UsuariosDeUniversidad(String nombres, String apellidos,
                                                                String correoInstitucional,String nombre_usuario,String fechaRegistro,
                                                                String tipoUsuario, Integer idUniversidad,Integer idPoliticaRegular,
                                                                Integer idConsorcioPerteneciente) throws RemoteException;
    
    public Integer modificarUniversidadesAsociadasDeUsuario(Integer idUsuario,ArrayList<Integer> idUniversidadesActuales,ArrayList<Integer> idUniversidadesNuevas) throws RemoteException;
    
}
