
/* [/]
 >> Project:    SoftBiblioecaPublicacionesDA
 >> File:       TemaDAO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.dao;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tema;

public interface TemaDAO {
    public Integer insertar(Tema tema);
    
    public Integer modificar(Tema tema);
    
    public Integer eliminar(Tema tema);
    
    public ArrayList<Tema> listarTodos(Tema tema, Integer limiteListado);
    
    public Tema obtenerPorId(Integer idTema);
    
    public Tema obtenerPorAtributosUnicos(Tema tema);
}
