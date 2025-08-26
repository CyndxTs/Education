
/* [/]
 >> Project:    SoftBibliotecaReservasDA
 >> File:       ReservaDAO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.reservas.dao;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.reservas.model.Reserva;

public interface ReservaDAO{
     public Integer insertar(Reserva reserva);
    
    public Integer modificar(Reserva reserva);
    
    public Integer eliminar(Reserva reserva);
    
    public ArrayList<Reserva> listarTodos(Reserva reserva, Integer limiteListado, Integer limiteProfundidad);
    
    public Reserva obtenerPorId(Integer idReserva);
}
