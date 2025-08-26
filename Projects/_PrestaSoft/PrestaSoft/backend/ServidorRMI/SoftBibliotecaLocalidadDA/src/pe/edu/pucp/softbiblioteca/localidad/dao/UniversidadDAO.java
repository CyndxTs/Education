
/* [/]
 >> Project:    SoftBibliotecaLocalidadModelDA
 >> File:       UniversidadDAO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.localidad.dao;
import pe.edu.pucp.softbiblioteca.localidad.model.Universidad;
import java.util.ArrayList;

public interface UniversidadDAO {
    public Integer insertar(Universidad universidad);
    
    public Integer modificar(Universidad universidad);
    
    public Integer eliminar(Universidad universidad);
    
    public ArrayList<Universidad> listarTodos(Universidad universidad, Integer limiteListado, Integer limiteProfundidad);
    
    public Universidad obtenerPorId(Integer idUniversidad);
}
