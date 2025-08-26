
/* [/]
 >> Project:    SoftBiblioecaPublicacionesDA
 >> File:       Tesis_AutorDAO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.dao;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Autor;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tesis;

public interface Tesis_AutorDAO {
    public Integer insertar(Tesis tesis,Autor autor);

    public Integer eliminar(Tesis tesis,Autor autor);
    
    public ArrayList<Tesis> listarTodos_TesisDeAutor(Tesis tesis,Autor autor, Integer limiteListado, Integer limiteProfundidad);
    
    public ArrayList<Autor> listarTodos_AutoresDeTesis(Tesis tesis,Autor autor, Integer limiteListado);
}
