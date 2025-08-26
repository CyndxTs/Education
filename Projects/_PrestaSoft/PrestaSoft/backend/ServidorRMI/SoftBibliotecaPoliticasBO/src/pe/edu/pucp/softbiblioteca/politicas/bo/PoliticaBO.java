
/* [/]
 >> @Project:    SoftBibliotecaPoliticasBO
 >> @File:       PoliticaBO.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.politicas.bo;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.politicas.model.Politica;
import pe.edu.pucp.softbiblioteca.politicas.model.PoliticaPrestamo;
import pe.edu.pucp.softbiblioteca.politicas.model.PoliticaReserva;
import pe.edu.pucp.sotbiblioteca.politicas.dao.PoliticaDAO;
import pe.edu.pucp.sotbiblioteca.politicas.daoimpl.PoliticaDAOImpl;

public class PoliticaBO {
    private PoliticaDAO politicaDAO;
    
    public PoliticaBO(){
        this.politicaDAO = new PoliticaDAOImpl();
    }
    
        //DE ACÁ VAN TODOS LOS CRUDS
    
    public Integer insertar(String etiqueta,Integer idPoliticaPrestamo,Integer idPoliticaReserva) {
        PoliticaPrestamo politicaPrestamo = new PoliticaPrestamo(); 
        politicaPrestamo.setIdPoliticaPrestamo(idPoliticaPrestamo);
        PoliticaReserva politicaReserva = new PoliticaReserva();
        politicaReserva.setIdPoliticaReserva(idPoliticaReserva);
        Politica politica = new Politica(null,etiqueta,politicaPrestamo,politicaReserva);
        return this.politicaDAO.insertar(politica);
    }

    public Integer modificar(Integer idPolitica,String etiqueta,Integer idPoliticaPrestamo,
                             Integer idPoliticaReserva) {
        PoliticaPrestamo politicaPrestamo = new PoliticaPrestamo(); 
        politicaPrestamo.setIdPoliticaPrestamo(idPoliticaPrestamo);
        PoliticaReserva politicaReserva = new PoliticaReserva();
        politicaReserva.setIdPoliticaReserva(idPoliticaReserva);
        Politica politica = new Politica(idPolitica,etiqueta,politicaPrestamo,politicaReserva);
        return this.politicaDAO.modificar(politica);
    }

    public Integer eliminar(Integer idPolitica) {
        Politica politica = new Politica();
        politica.setIdPolitica(idPolitica);
        return this.politicaDAO.eliminar(politica);
    }
    
    public ArrayList<Politica> listarTodos(Integer idPoliticaPrestamo,Integer idPoliticaReserva, Integer limite) {
        PoliticaPrestamo politicaPrestamo = new PoliticaPrestamo(); 
        politicaPrestamo.setIdPoliticaPrestamo(idPoliticaPrestamo);
        PoliticaReserva politicaReserva = new PoliticaReserva();
        politicaReserva.setIdPoliticaReserva(idPoliticaReserva);
        Politica politica = new Politica();
        politica.setPoliticasPorPrestamo(politicaPrestamo);
        politica.setPoliticasPorReserva(politicaReserva);
        return this.politicaDAO.listarTodos(politica,limite,1); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!POSIBLE FALLO XD
    }

    public Politica obtenerPorId(Integer idPolitica) {
        return this.politicaDAO.obtenerPorId(idPolitica);
    }
}
