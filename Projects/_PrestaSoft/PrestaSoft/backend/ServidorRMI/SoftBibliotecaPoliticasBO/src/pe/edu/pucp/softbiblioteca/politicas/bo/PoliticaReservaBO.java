
/* [/]
 >> @Project:    SoftBibliotecaPoliticasBO
 >> @File:       PoliticaReservaBO.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.politicas.bo;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.politicas.model.PoliticaReserva;
import pe.edu.pucp.sotbiblioteca.politicas.dao.PoliticaReservaDAO;
import pe.edu.pucp.sotbiblioteca.politicas.daoimpl.PoliticaReservaDAOImpl;

public class PoliticaReservaBO {
    private PoliticaReservaDAO politicaReservaDAO;
    
    public PoliticaReservaBO(){
        this.politicaReservaDAO = new PoliticaReservaDAOImpl();
    }
    
        //DE ACÁ VAN TODOS LOS CRUDS
    
    public Integer insertar(Integer cantMaxHorasDeRecojo,Integer cantMaxReservasAgendadasPorUsuario) {
        PoliticaReserva politicaReserva = new PoliticaReserva(null,cantMaxHorasDeRecojo,cantMaxReservasAgendadasPorUsuario);
        return this.politicaReservaDAO.insertar(politicaReserva);
    }

    public Integer modificar(Integer idPoliticaReserva,Integer cantMaxHorasDeRecojo,Integer cantMaxReservasAgendadasPorUsuario) {
        PoliticaReserva politicaReserva = new PoliticaReserva(idPoliticaReserva,cantMaxHorasDeRecojo,cantMaxReservasAgendadasPorUsuario);
        return this.politicaReservaDAO.modificar(politicaReserva);
    }

    public Integer eliminar(Integer idPoliticaReserva) {
        PoliticaReserva politicaReserva = new PoliticaReserva();
        politicaReserva.setIdPoliticaReserva(idPoliticaReserva);
        return this.politicaReservaDAO.eliminar(politicaReserva);
    }

    public ArrayList<PoliticaReserva> listarTodos(Integer limite) {
        return this.politicaReservaDAO.listarTodos(null,limite);
    }

    public PoliticaReserva obtenerPorId(Integer idPoliticaReserva) {
        return this.politicaReservaDAO.obtenerPorId(idPoliticaReserva);
    }
}
