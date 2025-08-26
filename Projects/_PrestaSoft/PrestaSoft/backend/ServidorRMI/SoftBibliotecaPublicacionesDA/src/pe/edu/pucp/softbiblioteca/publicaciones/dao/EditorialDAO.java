
/* [/]
 >> Project:    SoftBiblioecaPublicacionesDA
 >> File:       EditorialDAO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.dao;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Editorial;

public interface EditorialDAO {
    public Integer insertar(Editorial editorial);
    
    public Integer modificar(Editorial editorial);
    
    public Integer eliminar(Editorial editorial);
    
    public ArrayList<Editorial> listarTodos(Editorial editorial, Integer limiteListado);
    
    public Editorial obtenerPorId(Integer idEditorial);
}
