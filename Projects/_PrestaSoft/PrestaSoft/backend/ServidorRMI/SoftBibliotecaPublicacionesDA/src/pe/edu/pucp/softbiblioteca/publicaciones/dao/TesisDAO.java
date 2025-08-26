
/* [/]
 >> Project:    SoftBiblioecaPublicacionesDA
 >> File:       TesisDAO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.dao;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tesis;

public interface TesisDAO {
    public Integer insertar(Tesis tesis);
    
    public Integer modificar(Tesis tesis);
    
    public Integer eliminar(Tesis tesis);
    
    public ArrayList<Tesis> listarTodos(Tesis tesis, Integer limiteListado, Integer limiteProfundidad);
    
    public Tesis obtenerPorId(Integer idTesis);
}
