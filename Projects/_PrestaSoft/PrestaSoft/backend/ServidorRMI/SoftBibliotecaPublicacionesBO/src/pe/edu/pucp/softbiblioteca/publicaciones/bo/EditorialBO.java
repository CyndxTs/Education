
/* [/]
 >> Project:    SoftBiblioecaPublicacionesBO
 >> File:       EditorialBO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.bo;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.EditorialDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.EditorialDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Editorial;

public class EditorialBO {
    private final EditorialDAO editorialDAO;
    
    public EditorialBO(){
        this.editorialDAO = new EditorialDAOImpl();
    }
    
    public Integer insertar(String nombre) {        
        Editorial editorial = new Editorial(null, nombre);
        return this.editorialDAO.insertar(editorial);
    }
    
    public Integer modificar(Integer idEditorial, String nombre) {        
        Editorial editorial = new Editorial(idEditorial, nombre);
        return this.editorialDAO.modificar(editorial);
    }
    
    public Integer eliminar(Integer idEditorial) {        
        Editorial editorial = new Editorial();
        editorial.setIdEditorial(idEditorial);
        return this.editorialDAO.eliminar(editorial);
    }
   
    
    public ArrayList<Editorial> listarTodos(String nombre, Integer limite){
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        return this.editorialDAO.listarTodos(editorial, limite);
    }
    
    public Editorial obtenerPorId(Integer idEditorial){
        return this.editorialDAO.obtenerPorId(idEditorial);
    }
    
    //nuevos bo
    public ArrayList<Editorial> buscarEditorialPorNombre(String nombre){
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        return this.editorialDAO.listarTodos(editorial,null);
    }
}
