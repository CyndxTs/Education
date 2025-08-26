
/* [/]
 >> Project:    SoftBibliotecaUsuariosDA
 >> File:       Usuario_UniversidadDAO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.usuarios.dao;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.localidad.model.Universidad;
import pe.edu.pucp.softbiblioteca.usuarios.model.Usuario;

public interface Usuario_UniversidadDAO {
    public Integer insertar(Usuario usuario, Universidad universidad);
    
    public Integer eliminar(Usuario usuario, Universidad universidad);
    
    public ArrayList<Usuario> listarTodos_UsuariosDeUniversidad(Usuario usuario, Universidad universidad, Integer limiteListado);
    
    public ArrayList<Universidad> listarTodos_UniversidadesDeUsuario(Usuario usuario, Universidad universidad, Integer limiteListado, Integer limiteProfundidad);
}
