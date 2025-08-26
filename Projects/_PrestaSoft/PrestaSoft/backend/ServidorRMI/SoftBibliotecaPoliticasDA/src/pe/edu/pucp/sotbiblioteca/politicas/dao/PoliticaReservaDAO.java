
/* [/]
 >> @Project:    SoftBibliotecaPoliticasDA
 >> @File:       PoliticaReservaDAO.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.sotbiblioteca.politicas.dao;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.politicas.model.PoliticaReserva;

public interface PoliticaReservaDAO {
    public Integer insertar(PoliticaReserva politicaReserva);
    
    public Integer modificar(PoliticaReserva politicaReserva);
    
    public Integer eliminar(PoliticaReserva politicaReserva);
    
    public ArrayList<PoliticaReserva> listarTodos(PoliticaReserva politicaReserva, Integer limiteListado);
    
    public PoliticaReserva obtenerPorId(Integer idPoliticaReserva);
}
