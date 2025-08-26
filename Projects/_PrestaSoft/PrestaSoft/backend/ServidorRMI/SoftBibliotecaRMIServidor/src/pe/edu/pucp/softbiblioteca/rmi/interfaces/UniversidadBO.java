package pe.edu.pucp.softbiblioteca.rmi.interfaces;

import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.localidad.model.Universidad;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UniversidadBO extends Remote{
    
    public Integer insertar(String nombre,String direccion,String correoPersonalAdministrativo,
                            String extensionDominioCorreo,Integer idPoliticaRegular,Integer idConsorcioPerteneciente) throws RemoteException;
    
    public Integer modificar(Integer idUniversidad,String nombre,String direccion,
                             String correoPersonalAdministrativo,String extensionDominioCorreo,Integer idPoliticaRegular,
                             Integer idConsorcioPerteneciente) throws RemoteException;
    
    public Integer eliminar(int idUniversidad) throws RemoteException;
    
    public ArrayList<Universidad> listarTodos(Integer idPoliticaRegular,Integer idConsorcioPerteneciente, Integer limite) throws RemoteException;
    
    public Universidad obtenerPorId(int idUniversidad) throws RemoteException;
    
    public ArrayList<Universidad> listarTodos_UniversidadesDeUsuarioPorID(Integer idUsuario) throws RemoteException;
    
    public ArrayList<Universidad> listarTodos_UniversidadesDeUsuario(Integer idUsuario,String nombres, String apellidos,
                                                                      String correoInstitucional,String nombre_usuario,String fechaRegistro,
                                                                      String tipoUsuario,Integer idPoliticaRegular,Integer idConsorcioPerteneciente) throws RemoteException;
    
    public Integer eliminarUniversidadesDeUsuario(Integer idUsuario) throws RemoteException;
    
}
