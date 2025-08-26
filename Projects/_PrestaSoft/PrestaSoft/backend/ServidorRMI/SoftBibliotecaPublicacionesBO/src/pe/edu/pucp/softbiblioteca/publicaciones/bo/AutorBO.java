
/* [/]
 >> Project:    SoftBiblioecaPublicacionesBO
 >> File:       AutorBO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.bo;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softbiblioteca.localidad.model.Biblioteca;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.AutorDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.Libro_AutorDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.Tesis_AutorDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.AutorDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.Libro_AutorDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.Tesis_AutorDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Autor;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Editorial;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Libro;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tema;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tesis;
import pe.edu.pucp.softbiblioteca.publicaciones.model.TipoGrado;
import pe.edu.pucp.softbiblioteca.publicaciones.model.TipoLibro;
import pe.edu.pucp.softbiblioteca.util.VKF_Formatter;

public class AutorBO {
    private final AutorDAO autorDAO;
    private final Libro_AutorDAO libro_autorDAO;
    private final VKF_Formatter vkf;
    private final Tesis_AutorDAO tesis_autorDAO;
    
    
    public AutorBO(){
        this.autorDAO = new AutorDAOImpl();
        this.libro_autorDAO = new Libro_AutorDAOImpl();
        this.vkf = new VKF_Formatter();
        this.tesis_autorDAO = new Tesis_AutorDAOImpl();
    }
    
    public Integer insertar(String nombreCompleto) {        
        Autor autor = new Autor(null,nombreCompleto);
        return this.autorDAO.insertar(autor);
    }
    
    public Integer modificar(Integer idAutor,String nombreCompleto) {        
        Autor autor = new Autor(idAutor,nombreCompleto);
        return this.autorDAO.modificar(autor);
    }
    
    public Integer eliminar(Integer idAutor) {        
        Autor autor = new Autor();
        autor.setIdAutor(idAutor);
        return this.autorDAO.eliminar(autor);
    }
    
//    public ArrayList<Autor> listarTodos(String nombreCompleto){
//        Autor autor = new Autor();
//        autor.setNombreCompleto(nombreCompleto);
//        return this.autorDAO.listarTodos(autor);
//    }
    
//    public ArrayList<Autor> listarTodos(Integer l){
//        return this.autorDAO.listarTodos(null);
//    }
//    
    public Autor obtenerPorId(Integer idAutor){
        return this.autorDAO.obtenerPorId(idAutor);
    }
    
    
    //-----------------------------LOS DE ABAJO ESTAN EN EL WS---------------------------------
    public ArrayList<Autor> buscarAutorPorNombre(String nombre, Integer limite){
        Autor autor = new Autor();
        autor.setNombreCompleto(nombre);
        return this.autorDAO.listarTodos(autor,limite);
    }
    
    public Integer insertarAutor(String nombre){
        Autor autorPorRegistrar = new Autor(null,nombre);
        Autor autorVerificacion = new Autor();
        autorVerificacion.setNombreCompleto(nombre);
        Autor resultadoVerif = this.autorDAO.obtenerPorAtributosUnicos(autorVerificacion);
        if(resultadoVerif != null){
            return resultadoVerif.getIdAutor();
        }else{
            return this.autorDAO.insertar(autorPorRegistrar);
        }
    }
    
    public ArrayList<Autor> listarTodos_AutoresDeLibroPorID(Integer idLibro) {
        Libro libro = new Libro();
        libro.setIdLibro(idLibro);
        return this.libro_autorDAO.listarTodos_AutoresDeLibro(libro,null,null);
    }
    
    public ArrayList<Autor> listarTodos_AutoresDeLibro(Integer idLibro, String titulo, String fechaPublicacion,
                                                       String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual,
                                                       Integer idBibliotecaAsociada, Integer idEditorial, String tipo, Integer volumen,
                                                       String materia, String genero, Integer tomo, String nombreCompleto) {
        Date d_fechaPublicacion = vkf.toValidDate(fechaPublicacion);
        Boolean b_hayFormatoFisico = vkf.toValidBoolean(hayFormatoFisico);
        Boolean b_hayFormatoVirtual = vkf.toValidBoolean(hayFormatoVirtual);
        Biblioteca bibliotecaAsociada = new Biblioteca();
        bibliotecaAsociada.setIdBiblioteca(idBibliotecaAsociada);
        Editorial editorial = new Editorial();
        editorial.setIdEditorial(idEditorial);
        TipoLibro tl_tipo = vkf.toValidEnum(tipo, TipoLibro.class);
        Libro libro = new Libro(idLibro, titulo, d_fechaPublicacion, descripcion, null,
                                b_hayFormatoFisico, b_hayFormatoVirtual, null, null,
                                bibliotecaAsociada, null, editorial, tl_tipo, volumen,
                                materia, genero, tomo);
        Autor autor = new Autor(null,nombreCompleto);
        return this.libro_autorDAO.listarTodos_AutoresDeLibro(libro,autor,null);
    }
    
    
    public ArrayList<Autor> listarTodos_AutoresDeTesisPorID(Integer idTesis){
        Tesis tesis = new Tesis();
        tesis.setIdTesis(idTesis);
       return this.tesis_autorDAO.listarTodos_AutoresDeTesis(tesis,null,null);
    }
     
    public ArrayList<Autor> listarTodos_AutoresDeTesis(Integer idTesis,String titulo, String fechaPublicacion,
                                                       String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual,
                                                       Integer idBibliotecaAsociada, String gradoAcademico, String nombreCompleto) {
        Date d_fechaPublicacion = vkf.toValidDate(fechaPublicacion);
        Boolean b_hayFormatoFisico = vkf.toValidBoolean(hayFormatoFisico);
        Boolean b_hayFormatoVirtual = vkf.toValidBoolean(hayFormatoVirtual);
        Biblioteca bibliotecaAsociada = new Biblioteca();
        bibliotecaAsociada.setIdBiblioteca(idBibliotecaAsociada);
        TipoGrado tg_gradoAcademico = vkf.toValidEnum(gradoAcademico, TipoGrado.class);
        Tesis tesis = new Tesis(idTesis, titulo, d_fechaPublicacion, descripcion,null,
                                b_hayFormatoFisico,b_hayFormatoVirtual, null, null,
                                bibliotecaAsociada,tg_gradoAcademico);
        Autor autor = new Autor(null,nombreCompleto);
       return this.tesis_autorDAO.listarTodos_AutoresDeTesis(tesis,autor,null);
    }
}

