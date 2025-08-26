
/* [/]
 >> Project:    SoftBibliotecaPrestamosDA
 >> File:       PenalizacionDAO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.prestamos.dao;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.prestamos.model.Penalizacion;

public interface PenalizacionDAO {
     public Integer insertar(Penalizacion penalizacion);
    
    public Integer modificar(Penalizacion penalizacion);
    
    public Integer eliminar(Penalizacion penalizacion);
    
    public ArrayList<Penalizacion> listarTodos(Penalizacion penalizacion, Integer limiteListado, Integer limiteProfundidad);
    
    public Penalizacion obtenerPorId(Integer idPenalizaciion);
}
