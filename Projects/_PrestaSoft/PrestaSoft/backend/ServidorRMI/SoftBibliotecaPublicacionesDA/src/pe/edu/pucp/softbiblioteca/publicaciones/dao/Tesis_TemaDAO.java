
/* [/]
 >> Project:    SoftBiblioecaPublicacionesDA
 >> File:       Tesis_TemaDAO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.dao;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tema;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tesis;

public interface Tesis_TemaDAO {
    public Integer insertar(Tesis tesis,Tema tema);

    public Integer eliminar(Tesis tesis,Tema tema);
    
    public ArrayList<Tesis> listarTodos_TesisDeTema(Tesis tesis,Tema tema, Integer limiteListado, Integer limiteProfundidad);

    public ArrayList<Tema> listarTodos_TemasDeTesis(Tesis tesis,Tema tema, Integer limiteListado);
}
