
/* [/]
 >> Project:    SoftBiblioecaPublicacionesDA
 >> File:       LibroDAO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.dao;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Libro;

public interface LibroDAO {
    public Integer insertar(Libro libro);
    
    public Integer modificar(Libro libro);
    
    public Integer eliminar(Libro libro);
    
    public ArrayList<Libro> listarTodos(Libro libro, Integer limiteListado, Integer limiteProfundidad);
    
    public Libro obtenerPorId(Integer idLibro);
    
    public Libro obtenerPorAtributosUnicos(Libro libro);
}
