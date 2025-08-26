
/* [/]
 >> Project:    SoftBibliotecaUsuariosDA
 >> File:       UsuarioDAO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.usuarios.dao;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.usuarios.model.Usuario;

public interface UsuarioDAO {
    public Integer insertar(Usuario usuario);
    
    public Integer modificar(Usuario usuario);
    
    public Integer eliminar(Usuario usuario);
    
    public ArrayList<Usuario> listarTodos(Usuario usuario, Integer limiteListado);
    
    public Usuario obtenerPorId(Integer idUsuario);
    
    public Usuario obtenerPorAtributosUnicos(Usuario usuario);
}
