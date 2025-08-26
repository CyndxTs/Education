
/* [/]
 >> @Project:    SoftBibliotecaPoliticasDA
 >> @File:       PoliticaPrestamoDAO.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.sotbiblioteca.politicas.dao;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.politicas.model.PoliticaPrestamo;

public interface PoliticaPrestamoDAO {
    public Integer insertar(PoliticaPrestamo politicaPrestamo);
    
    public Integer modificar(PoliticaPrestamo politicaPrestamo);
    
    public Integer eliminar(PoliticaPrestamo politicaPrestamo);
    
    public ArrayList<PoliticaPrestamo> listarTodos(PoliticaPrestamo politicaPrestamo, Integer limiteListado, Integer limiteProfundidad);
    
    public PoliticaPrestamo obtenerPorId(Integer idPoliticaPrestamo);
}
