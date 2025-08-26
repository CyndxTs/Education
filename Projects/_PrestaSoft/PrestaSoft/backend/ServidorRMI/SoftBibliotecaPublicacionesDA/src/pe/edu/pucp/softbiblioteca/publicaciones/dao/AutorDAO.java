
/* [/]
 >> Project:    SoftBiblioecaPublicacionesDA
 >> File:       AutorDAO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.dao;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Autor;

public interface AutorDAO {
    public Integer insertar(Autor autor);
    
    public Integer modificar(Autor autor);
    
    public Integer eliminar(Autor autor);
    
    public ArrayList<Autor> listarTodos(Autor autor, Integer limiteListado);
    
    public Autor obtenerPorId(Integer idAutor);
    
    public Autor obtenerPorAtributosUnicos(Autor autor);
}
