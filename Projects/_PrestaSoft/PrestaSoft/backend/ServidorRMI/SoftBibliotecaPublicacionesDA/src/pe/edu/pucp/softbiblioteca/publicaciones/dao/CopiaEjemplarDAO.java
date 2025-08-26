
/* [/]
 >> Project:    SoftBiblioecaPublicacionesDA
 >> File:       CopiaEjemplarDAO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.dao;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.CopiaEjemplar;

public interface CopiaEjemplarDAO {
    public Integer insertar(CopiaEjemplar copiaEjemplar);
    
    public Integer modificar(CopiaEjemplar copiaEjemplar);
    
    public Integer eliminar(CopiaEjemplar copiaEjemplar);
    
    public ArrayList<CopiaEjemplar> listarTodos(CopiaEjemplar copiaEjemplar, Integer limiteListado, Integer limiteProfundidad);
    
    public CopiaEjemplar obtenerPorId(Integer idCopiaEjemplar);
}
