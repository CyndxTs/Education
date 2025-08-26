
/* [/]
 >> @Project:    SoftBibliotecaPoliticasBO
 >> @File:       MoraBO.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.politicas.bo;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.politicas.model.Mora;
import pe.edu.pucp.sotbiblioteca.politicas.dao.MoraDAO;
import pe.edu.pucp.sotbiblioteca.politicas.daoimpl.MoraDAOImpl;

public class MoraBO {
    private MoraDAO moraDAO;
    
    public MoraBO(){
        this.moraDAO = new MoraDAOImpl();
    }
    
    
    //DE ACÁ VAN TODOS LOS CRUDS
    
    public Integer insertar(Float monto) {
        Mora mora = new Mora(null,monto);
        return this.moraDAO.insertar(mora);
    }

    public Integer modificar(Integer idMora, Float monto) {
        Mora mora = new Mora(idMora,monto);
        return this.moraDAO.modificar(mora);
    }

    public Integer eliminar(Integer idMora) {
        Mora mora = new Mora();
        mora.setIdMora(idMora);
        return this.moraDAO.eliminar(mora);
    }

    public ArrayList<Mora> listarTodos(Integer limite) {
        return this.moraDAO.listarTodos(null,limite);
    }

    public Mora obtenerPorId(Integer idMora) {
        return this.moraDAO.obtenerPorId(idMora);
    }
}
