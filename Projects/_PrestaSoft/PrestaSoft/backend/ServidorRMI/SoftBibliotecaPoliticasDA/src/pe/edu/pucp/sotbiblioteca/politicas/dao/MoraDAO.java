
/* [/]
 >> @Project:    SoftBibliotecaPoliticasDA
 >> @File:       MoraDAO.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.sotbiblioteca.politicas.dao;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.politicas.model.Mora;

public interface MoraDAO {
    public Integer insertar(Mora mora);
    
    public Integer modificar(Mora mora);
    
    public Integer eliminar(Mora mora);
    
    public ArrayList<Mora> listarTodos(Mora mora, Integer limiteListado);
    
    public Mora obtenerPorId(Integer idMora);
}
