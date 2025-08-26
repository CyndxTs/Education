
/* [/]
 >> @Project:    SoftBibliotecaPoliticasDA
 >> @File:       PoliticaDAO.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.sotbiblioteca.politicas.dao;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.politicas.model.Politica;

public interface PoliticaDAO {
    public Integer insertar(Politica politica);
    
    public Integer modificar(Politica politica);
    
    public Integer eliminar(Politica politica);
    
    public ArrayList<Politica> listarTodos(Politica politica, Integer limiteListado, Integer limiteProfundidad);
    
    public Politica obtenerPorId(Integer idPolitica);
}
