
/* [/]
 >> Project:    SoftBibliotecaPrestamosDA
 >> File:       PrestamoDAO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.prestamos.dao;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.prestamos.model.Prestamo;

public interface PrestamoDAO {
     public Integer insertar(Prestamo prestamo);
    
    public Integer modificar(Prestamo prestamo);
    
    public Integer eliminar(Prestamo prestamo);
    
    public ArrayList<Prestamo> listarTodos(Prestamo prestamo, Integer limiteListado, Integer limiteProfundidad);
    
    public Prestamo obtenerPorId(Integer idPrestamo);
}
