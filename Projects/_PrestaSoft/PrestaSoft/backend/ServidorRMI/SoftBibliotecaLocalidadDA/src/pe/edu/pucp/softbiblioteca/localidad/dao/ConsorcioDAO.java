
/* [/]
 >> @Project:    SoftBibliotecaLocalidadDA
 >> @File:       ConsorcioDAO.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.localidad.dao;
import pe.edu.pucp.softbiblioteca.localidad.model.Consorcio;
import java.util.ArrayList;

public interface ConsorcioDAO {
    public Integer insertar(Consorcio consorcio);
    
    public Integer modificar(Consorcio consorcio);
    
    public Integer eliminar(Consorcio consorcio);
    
    public ArrayList<Consorcio> listarTodos(Consorcio consorcio, Integer limiteListado);
    
    public Consorcio obtenerPorId(Integer idConsorcio);
}
