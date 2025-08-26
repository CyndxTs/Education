
/* [/]
 >> Project:    SoftBiblioecaPublicacionesDA
 >> File:       Libro_AutorDAO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.dao;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Autor;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Libro;

public interface Libro_AutorDAO {
    public Integer insertar(Libro libro,Autor autor);

    public Integer eliminar(Libro libro,Autor autor);
        
    public ArrayList<Libro> listarTodos_LibrosDeAutor(Libro libro,Autor autor, Integer limiteListado, Integer limiteProfundidad);
    
    public ArrayList<Autor> listarTodos_AutoresDeLibro(Libro libro,Autor autor, Integer limiteListado);
}
