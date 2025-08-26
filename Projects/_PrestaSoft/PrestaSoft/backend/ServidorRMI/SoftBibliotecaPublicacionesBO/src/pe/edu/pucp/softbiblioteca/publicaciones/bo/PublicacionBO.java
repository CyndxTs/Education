
/* [/]
 >> Project:    SoftBiblioecaPublicacionesBO
 >> File:       PublicacionBO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.bo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import pe.edu.pucp.softbiblioteca.localidad.model.Biblioteca;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.CopiaEjemplarDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.Libro_AutorDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.Tesis_AutorDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.CopiaEjemplarDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.Libro_AutorDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.Tesis_AutorDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Autor;
import pe.edu.pucp.softbiblioteca.publicaciones.model.CopiaEjemplar;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Libro;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Publicacion;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tesis;
import pe.edu.pucp.softbiblioteca.util.VKF_Formatter;

public class PublicacionBO {
    private final LibroBO libroBO;
    private final TesisBO tesisBO;
    private Libro_AutorDAO libro_autordao;
    private Tesis_AutorDAO tesis_autordao;
    private CopiaEjemplarDAO copiaejemplarDAO;
    private VKF_Formatter vkf;
    
    public PublicacionBO() {
        this.libroBO = new LibroBO();
        this.tesisBO = new TesisBO();
        this.libro_autordao=new Libro_AutorDAOImpl();
        this.tesis_autordao=new Tesis_AutorDAOImpl();
        this.copiaejemplarDAO=new CopiaEjemplarDAOImpl();
    }
    
    //--------------------------------TODOS VAN AL WS-------------------------------------------
    
public ArrayList<Publicacion> verCatalogo(Integer modoDeBusqueda, Integer idBibliotecaAsociada,
                                          String tipo, String titulo, Integer limite) {
    if (limite != null && limite > 1) limite /= 2;
    System.out.println("CATALOGO QUE VOY A MANDAR CON LIMITES " + limite);
    
    // Usamos un HashSet para eliminar duplicados
    Set<Publicacion> publicaciones = new HashSet<>();
    
    if (modoDeBusqueda >= 0) {
        publicaciones.addAll(libroBO.listarTodos(titulo, null, null,
                                                 -1, -1, idBibliotecaAsociada,
                                                 null, tipo, null,
                                                 null, null, null, limite));
    }
    if (modoDeBusqueda <= 0) {
        publicaciones.addAll(tesisBO.listarTodos(titulo, null, null,
                                                 -1, -1, idBibliotecaAsociada,
                                                 null, limite));
    }

    
    // Convertimos el Set a ArrayList para el retorno
    return new ArrayList<>(publicaciones);
}


    
public ArrayList<Publicacion> busquedaAvanzada(Integer modoDeBusqueda, String titulo,
                                               String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual,
                                               Integer idBibliotecaAsociada, Integer idAutor) {
    this.vkf = new VKF_Formatter();
    Autor autor = new Autor();
    autor.setIdAutor(idAutor);
    Biblioteca bibliotecaAsociada = new Biblioteca();
    bibliotecaAsociada.setIdBiblioteca(idBibliotecaAsociada);
    
    // Usamos un HashSet para evitar duplicados
    Set<Publicacion> publicaciones = new HashSet<>();

    if (modoDeBusqueda >= 0) {
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setBibliotecaAsociada(bibliotecaAsociada);
        libro.setDescripcion(descripcion);
        libro.setHayFormatoFisico(vkf.toValidBoolean(hayFormatoFisico));
        libro.setHayFormatoVirtual(vkf.toValidBoolean(hayFormatoVirtual));
        publicaciones.addAll(libro_autordao.listarTodos_LibrosDeAutor(libro, autor, null, 2));
    }

    if (modoDeBusqueda <= 0) {
        Tesis tesis = new Tesis();
        tesis.setTitulo(titulo);
        tesis.setBibliotecaAsociada(bibliotecaAsociada);
        tesis.setDescripcion(descripcion);
        tesis.setHayFormatoFisico(vkf.toValidBoolean(hayFormatoFisico));
        tesis.setHayFormatoVirtual(vkf.toValidBoolean(hayFormatoVirtual));
        publicaciones.addAll(tesis_autordao.listarTodos_TesisDeAutor(tesis, autor, null, 2));
    }

    // Convertimos el Set a ArrayList antes de retornar
    return new ArrayList<>(publicaciones);
}

    
    public ArrayList<Publicacion> publicacionesPorAutor(Integer id){
        ArrayList<Publicacion> publicaciones=new ArrayList<Publicacion>();
        Autor autor=new Autor();
        autor.setIdAutor(id);
        ArrayList<Libro>librosdeautor=this.libro_autordao.listarTodos_LibrosDeAutor(null, autor,null,2);
        for(Libro libros:librosdeautor){
            publicaciones.add(libros);
        }
        ArrayList<Tesis>librosdetesis=this.tesis_autordao.listarTodos_TesisDeAutor(null, autor,null,2);
        for(Tesis tesis:librosdetesis){
            publicaciones.add(tesis);
        }
        return publicaciones;
    }
    
    
    public CopiaEjemplar consultarCopiaEjemplar(Integer id){        
        return this.copiaejemplarDAO.obtenerPorId(id);        
    }
    
}