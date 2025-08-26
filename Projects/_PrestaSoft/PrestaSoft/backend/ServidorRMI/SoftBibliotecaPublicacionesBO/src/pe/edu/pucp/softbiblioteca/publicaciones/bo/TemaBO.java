
/* [/]
 >> Project:    SoftBiblioecaPublicacionesBO
 >> File:       TemaBO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.bo;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softbiblioteca.localidad.model.Biblioteca;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.TemaDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.Tesis_TemaDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.TemaDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.Tesis_TemaDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tema;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tesis;
import pe.edu.pucp.softbiblioteca.publicaciones.model.TipoGrado;
import pe.edu.pucp.softbiblioteca.util.VKF_Formatter;

public class TemaBO {
    private final TemaDAO temaDAO;
    private final VKF_Formatter vkf;
    private final Tesis_TemaDAO tesis_temaDAO;
    
    public TemaBO(){
        this.temaDAO = new TemaDAOImpl();
        this.tesis_temaDAO = new Tesis_TemaDAOImpl();
        this.vkf = new VKF_Formatter();
    }
    
    public Integer insertar(String titulo) {        
        Tema tema = new Tema(null, titulo);
        return this.temaDAO.insertar(tema);
    }
    
    public Integer modificar(Integer idTema, String titulo) {        
        Tema tema = new Tema(idTema, titulo);
        return this.temaDAO.modificar(tema);
    }
    
    public Integer eliminar(Integer idTema) {        
        Tema tema = new Tema();
        tema.setIdTema(idTema);
        return this.temaDAO.eliminar(tema);
    }
    
    public ArrayList<Tema> listarTodos(String titulo, Integer limite){
        Tema tema = new Tema();
        tema.setTitulo(titulo);
        return this.temaDAO.listarTodos(tema, limite);
    }
    
    public Tema obtenerPorId(Integer idTema){
        return this.temaDAO.obtenerPorId(idTema);
    }
    
    
    //-------------------APARTIR DE AQUI AGREGO
    
    public ArrayList<Tema> buscarTemaPorNombre(String titulo, Integer limite){
        Tema tema = new Tema();
        tema.setTitulo(titulo);
        return this.temaDAO.listarTodos(tema,limite);
    }
    public Integer insertarTema(String titulo){
        Tema temaPorRegistrar = new Tema(null,titulo);
        Tema temaVerificacion = new Tema();
        temaVerificacion.setTitulo(titulo);
        Tema resultadoVerif = this.temaDAO.obtenerPorAtributosUnicos(temaVerificacion);
        if(resultadoVerif != null){
            return resultadoVerif.getIdTema();
        }else{
            return this.temaDAO.insertar(temaPorRegistrar);
        }        
    }
    
    public ArrayList<Tema> listarTodos_TemasDeTesisPorID(Integer idTesis){
        Tesis tesis = new Tesis();
        tesis.setIdTesis(idTesis);
        return this.tesis_temaDAO.listarTodos_TemasDeTesis(tesis,null, null);
    }
    
    public ArrayList<Tema> listarTodos_TemasDeTesis(Integer idTesis, String ts_titulo, String fechaPublicacion,
                                                    String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual,
                                                    Integer idBibliotecaAsociada, String gradoAcademico, String tm_titulo) {
        Date d_fechaPublicacion = vkf.toValidDate(fechaPublicacion);
        Boolean b_hayFormatoFisico = vkf.toValidBoolean(hayFormatoFisico);
        Boolean b_hayFormatoVirtual = vkf.toValidBoolean(hayFormatoVirtual);
        Biblioteca bibliotecaAsociada = new Biblioteca();
        bibliotecaAsociada.setIdBiblioteca(idBibliotecaAsociada);
        TipoGrado tg_gradoAcademico = vkf.toValidEnum(gradoAcademico, TipoGrado.class);
        Tesis tesis = new Tesis(idTesis,ts_titulo, d_fechaPublicacion, descripcion,null,
                                b_hayFormatoFisico,b_hayFormatoVirtual, null, null,
                                bibliotecaAsociada,tg_gradoAcademico);
        Tema tema = new Tema(null,tm_titulo);
        return this.tesis_temaDAO.listarTodos_TemasDeTesis(tesis, tema,null);
    }
    
}
