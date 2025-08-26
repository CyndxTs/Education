
/* [/]
 >> @Project:    SoftBibliotecaLocalidadBO
 >> @File:       ConsorcioBO.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.localidad.bo;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.localidad.dao.ConsorcioDAO;
import pe.edu.pucp.softbiblioteca.localidad.daoImpl.ConsorcioDAOImpl;
import pe.edu.pucp.softbiblioteca.localidad.model.Consorcio;

public class ConsorcioBO {
    private ConsorcioDAO consorcioDAO;
    
    public ConsorcioBO(){
        this.consorcioDAO = new ConsorcioDAOImpl();
    }
    
    public Integer insertar(String CIF) {
        Consorcio consorcio = new Consorcio(null,CIF);
        return this.consorcioDAO.insertar(consorcio);
    }

    public Integer modificar(Integer idConsorcio,String CIF) {
        Consorcio consorcio = new Consorcio(idConsorcio,CIF);
        return this.consorcioDAO.modificar(consorcio);
    }

    public Integer eliminar(Integer idConsorcio) {
        Consorcio consorcio = new Consorcio();
        consorcio.setIdConsorcio(idConsorcio);
        return this.consorcioDAO.eliminar(consorcio);
    }

    public ArrayList<Consorcio> listarTodos(Integer limiteListado) {
        return this.consorcioDAO.listarTodos(null,limiteListado);
    }

    public Consorcio obtenerPorId(Integer idConsorcio) {
        return this.consorcioDAO.obtenerPorId(idConsorcio);
    }
}
