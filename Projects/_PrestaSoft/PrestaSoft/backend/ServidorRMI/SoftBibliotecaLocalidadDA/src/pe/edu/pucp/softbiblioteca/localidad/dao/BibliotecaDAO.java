
/* [/]
 >> @Project:    SoftBibliotecaLocalidadDA
 >> @File:       BibliotecaDAO.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.localidad.dao;
import pe.edu.pucp.softbiblioteca.localidad.model.Biblioteca;
import java.util.ArrayList;

public interface BibliotecaDAO {
    public Integer insertar(Biblioteca biblioteca);
    
    public Integer modificar(Biblioteca biblioteca);
    
    public Integer eliminar(Biblioteca biblioteca);
    
    public ArrayList<Biblioteca> listarTodos(Biblioteca biblioteca, Integer limiteListado, Integer limiteProfundidad);
    
    public Biblioteca obtenerPorId(Integer idBiblioteca);
}
