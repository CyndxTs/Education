
/* [/]
 >> Project:    SoftBiblioecaPublicacionesBO
 >> File:       CopiaEjemplarBO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.bo;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.CopiaEjemplarDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.CopiaEjemplarDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.model.CopiaEjemplar;
import pe.edu.pucp.softbiblioteca.publicaciones.model.EstadoEjemplar;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Libro;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Publicacion;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tesis;
import pe.edu.pucp.softbiblioteca.util.VKF_Formatter;

public class CopiaEjemplarBO {
    private final CopiaEjemplarDAO copia_ejemplarDAO;
    private final VKF_Formatter vkf;
    
    public CopiaEjemplarBO() {
        this.copia_ejemplarDAO = new CopiaEjemplarDAOImpl();
        this.vkf = new VKF_Formatter();
    }
    
    public Integer insertar(String direccionLocal,Integer idPublicacionAsociada,String estado,
                            char tipoPublicacion){
        Publicacion publicacionAsociada = null;
        if(tipoPublicacion == 'L') publicacionAsociada = new Libro();
        else publicacionAsociada = new Tesis();
        publicacionAsociada.setIdPublicacion(idPublicacionAsociada);
        EstadoEjemplar ee_estado = vkf.toValidEnum(estado, EstadoEjemplar.class);
        CopiaEjemplar copiaEjemplar = new CopiaEjemplar(null, direccionLocal, publicacionAsociada,
                                                        ee_estado);
        return this.copia_ejemplarDAO.insertar(copiaEjemplar);
    }
    
    public Integer modificar(Integer idCopiaEjemplar,String direccionLocal,Integer idPublicacionAsociada,String estado,
                             char tipoPublicacion) {  
        Publicacion publicacionAsociada = null;
        if(tipoPublicacion == 'L') publicacionAsociada = new Libro();
        else publicacionAsociada = new Tesis();
        publicacionAsociada.setIdPublicacion(idPublicacionAsociada);
        EstadoEjemplar ee_estado = vkf.toValidEnum(estado, EstadoEjemplar.class);
        CopiaEjemplar copiaEjemplar = new CopiaEjemplar(idCopiaEjemplar, direccionLocal, publicacionAsociada,
                                                        ee_estado);
        return this.copia_ejemplarDAO.modificar(copiaEjemplar);
    }
    
    public Integer eliminar(Integer idCopiaEjemplar) {        
        CopiaEjemplar copiaEjemplar = new CopiaEjemplar();
        copiaEjemplar.setIdCopiaEjemplar(idCopiaEjemplar);
        return this.copia_ejemplarDAO.eliminar(copiaEjemplar);
    }
    
    public ArrayList<CopiaEjemplar> listarTodos(){
        return this.copia_ejemplarDAO.listarTodos(null,null,null);
    }
    
    public ArrayList<CopiaEjemplar> listarTodos(String direccionLocal, Integer idPublicacionAsociada,
                                                String estado, char tipoPublicacion){
        Publicacion publicacionAsociada = null;
        if(tipoPublicacion == 'L') publicacionAsociada = new Libro();
        else publicacionAsociada = new Tesis();
        publicacionAsociada.setIdPublicacion(idPublicacionAsociada);
        EstadoEjemplar ee_estado = vkf.toValidEnum(estado, EstadoEjemplar.class);
        CopiaEjemplar copiaEjemplar = new CopiaEjemplar(null, direccionLocal, null,
                                                        ee_estado);
        return this.copia_ejemplarDAO.listarTodos(copiaEjemplar,null,null);
    }
    
    public CopiaEjemplar obtenerPorId(Integer idCopiaEjemplar){
        return this.copia_ejemplarDAO.obtenerPorId(idCopiaEjemplar);
    }
    
    //-------------------------APARTIR DE AQUI ESTAN EN EL WS----------------------------
    public ArrayList<CopiaEjemplar>ObtenerCopiasEjemplaresDeLibro(Integer idLibro){
        ArrayList<CopiaEjemplar>copias=new ArrayList<CopiaEjemplar>();
        Libro libro= new Libro();
        libro.setIdLibro(idLibro);
        CopiaEjemplar copia=new CopiaEjemplar();
        copia.setPublicacionAsociada(libro);
        copias = this.copia_ejemplarDAO.listarTodos(copia,null,2);
        return copias;
    }
    
    public ArrayList<CopiaEjemplar>ObtenerCopiasEjemplaresDeTesis(Integer idTesis){
        ArrayList<CopiaEjemplar>copias=new ArrayList<CopiaEjemplar>();
        Tesis tesis= new Tesis();
        tesis.setIdTesis(idTesis); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        CopiaEjemplar copia=new CopiaEjemplar();
        copia.setPublicacionAsociada(tesis);
        copias=this.copia_ejemplarDAO.listarTodos(copia,null,2);
        return copias;
    }
    

}
