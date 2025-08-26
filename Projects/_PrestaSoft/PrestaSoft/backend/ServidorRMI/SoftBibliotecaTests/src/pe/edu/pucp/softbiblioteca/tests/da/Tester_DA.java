
/* [/]
 >> @Project:    
 >> @File:       Tester_DA.java
 >> @Author:     
[/] */

package pe.edu.pucp.softbiblioteca.tests.da;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.localidad.dao.BibliotecaDAO;
import pe.edu.pucp.softbiblioteca.localidad.dao.ConsorcioDAO;
import pe.edu.pucp.softbiblioteca.localidad.dao.UniversidadDAO;
import pe.edu.pucp.softbiblioteca.localidad.daoImpl.BibliotecaDAOImpl;
import pe.edu.pucp.softbiblioteca.localidad.daoImpl.ConsorcioDAOImpl;
import pe.edu.pucp.softbiblioteca.localidad.daoImpl.UniversidadDAOImpl;
import pe.edu.pucp.softbiblioteca.prestamos.dao.PenalizacionDAO;
import pe.edu.pucp.softbiblioteca.prestamos.dao.PrestamoDAO;
import pe.edu.pucp.softbiblioteca.prestamos.daoImpl.PenalizacionDAOImpl;
import pe.edu.pucp.softbiblioteca.prestamos.daoImpl.PrestamoDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.AutorDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.CopiaEjemplarDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.EditorialDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.LibroDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.Libro_AutorDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.TemaDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.TesisDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.Tesis_AutorDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.Tesis_TemaDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.AutorDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.CopiaEjemplarDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.EditorialDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.LibroDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.Libro_AutorDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.TemaDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.TesisDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.Tesis_AutorDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.Tesis_TemaDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Autor;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Libro;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tesis;
import pe.edu.pucp.softbiblioteca.reservas.dao.ReservaDAO;
import pe.edu.pucp.softbiblioteca.reservas.daoImpl.ReservaDAOImpl;
import pe.edu.pucp.softbiblioteca.usuarios.dao.UsuarioDAO;
import pe.edu.pucp.softbiblioteca.usuarios.dao.Usuario_UniversidadDAO;
import pe.edu.pucp.softbiblioteca.usuarios.daoImpl.UsuarioDAOImpl;
import pe.edu.pucp.softbiblioteca.usuarios.daoImpl.Usuario_UniversidadDAOImpl;
import pe.edu.pucp.sotbiblioteca.politicas.dao.MoraDAO;
import pe.edu.pucp.sotbiblioteca.politicas.dao.PoliticaDAO;
import pe.edu.pucp.sotbiblioteca.politicas.dao.PoliticaPrestamoDAO;
import pe.edu.pucp.sotbiblioteca.politicas.dao.PoliticaReservaDAO;
import pe.edu.pucp.sotbiblioteca.politicas.daoimpl.MoraDAOImpl;
import pe.edu.pucp.sotbiblioteca.politicas.daoimpl.PoliticaDAOImpl;
import pe.edu.pucp.sotbiblioteca.politicas.daoimpl.PoliticaPrestamoDAOImpl;
import pe.edu.pucp.sotbiblioteca.politicas.daoimpl.PoliticaReservaDAOImpl;

public class Tester_DA {
    //     POLITICAS
    private final MoraDAO moraDAO;
    private final PoliticaPrestamoDAO politicaPrestamoDAO;
    private final PoliticaReservaDAO politicaReservaDAO;
    private final PoliticaDAO politicaDAO;
    //     LOCALIDAD
    private final ConsorcioDAO consorcioDAO;
    private final UniversidadDAO universidadDAO;
    private final BibliotecaDAO bibliotecaDAO;
    //     USUARIOS
    private final UsuarioDAO usuarioDAO;
    private final Usuario_UniversidadDAO usuario_universidadDAO;
    //     PUBLCACIONES
    private final EditorialDAO editorialDAO;
    private final TemaDAO temaDAO;
    private final AutorDAO autorDAO;
    private final LibroDAO libroDAO;
    private final Libro_AutorDAO libro_autorDAO;
    private final TesisDAO tesisDAO;
    private final Tesis_AutorDAO tesis_autorDAO;
    private final Tesis_TemaDAO tesis_temaDAO;
    private final CopiaEjemplarDAO copiaEjemplarDAO;
    //     PRESTAMOS
    private final PenalizacionDAO penalizacionDAO;
    private final PrestamoDAO prestamoDAO;
    //     RESERVAS
    private final ReservaDAO reservaDAO;

    public Tester_DA() {
        //         POLITICAS
        this.moraDAO = new MoraDAOImpl();
        this.politicaPrestamoDAO = new PoliticaPrestamoDAOImpl();
        this.politicaReservaDAO = new PoliticaReservaDAOImpl();
        this.politicaDAO = new PoliticaDAOImpl();
        //         LOCALIDAD
        this.consorcioDAO = new ConsorcioDAOImpl();
        this.universidadDAO = new UniversidadDAOImpl();
        this.bibliotecaDAO = new BibliotecaDAOImpl();
        //         USUARIOS
        this.usuarioDAO = new UsuarioDAOImpl();
        this.usuario_universidadDAO = new Usuario_UniversidadDAOImpl();
        //         PUBLICACIONES
        this.editorialDAO = new EditorialDAOImpl();
        this.temaDAO = new TemaDAOImpl();
        this.autorDAO = new AutorDAOImpl();
        this.libroDAO = new LibroDAOImpl();
        this.libro_autorDAO = new Libro_AutorDAOImpl();
        this.tesisDAO = new TesisDAOImpl();
        this.tesis_autorDAO = new Tesis_AutorDAOImpl();
        this.tesis_temaDAO = new Tesis_TemaDAOImpl();
        this.copiaEjemplarDAO = new CopiaEjemplarDAOImpl();
        //         PRESTAMOS
        this.prestamoDAO = new PrestamoDAOImpl();
        this.penalizacionDAO = new PenalizacionDAOImpl();
        //         RESERVAS
        this.reservaDAO = new ReservaDAOImpl();
    }
    
    public void listarTodos_LibrosDeAutor() {
        Libro libro = new Libro();
        Autor autor = new Autor();
        // autor.setIdAutor(9); // Autor(9) == Fiódor Dostroyevski
        autor.setNombreCompleto("Dostoy");  // Se buscaran libros de autores con 'Dostoy' en su nombre
        ArrayList<Libro> libros = this.libro_autorDAO.listarTodos_LibrosDeAutor(libro, autor, null, 2);
        for(Libro lib : libros) {
            System.out.println(" >> " + lib.getTitulo());
        }
    }
    
    public void listarTodos_LibrosFiltradosDeAutor() {
        Libro libro = new Libro();
        libro.setTitulo("Heart"); // De los libros buscados, se filtraran los que tengan 'Heart' en su titulo
        Autor autor = new Autor();
        // autor.setIdAutor(9); // Autor(9) == Fiódor Dostroyevski
        autor.setNombreCompleto("Dostoy");  // Se buscaran libros de autores con 'Dostoy' en su nombre
        ArrayList<Libro> libros = this.libro_autorDAO.listarTodos_LibrosDeAutor(libro, autor, null, 2);
        for(Libro lib : libros) {
            System.out.println(" >> " + lib.getTitulo());
        }
    }
    
    // Basicamente es lo lismo que hacer un listar_todos_libro , pero con la diferencia que en caso se necesite, se pueden agregar los otros filtros
    public void listarTodos_LibrosFiltradosDeAutorGeneral() {
        Libro libro = new Libro();
        libro.setTitulo("H"); // De los libros buscados, se filtraran los que tengan 'H' en su titulo
        Autor autor = new Autor(); 
        ArrayList<Libro> libros = this.libro_autorDAO.listarTodos_LibrosDeAutor(libro, autor, null, 2);
        for(Libro lib : libros) {
            System.out.println(" >> " + lib.getTitulo());
        }
    }
    
    public void listarTodos_AutoresDeLibro() {
        Libro libro = new Libro();
        // libro.setIdLibro(2); // Libro(2) == The Sandman Universe
        libro.setTitulo("Sand"); // Se buscaran autores de libros con 'Sand' en su titulo
        Autor autor = new Autor();
        ArrayList<Autor> autores = this.libro_autorDAO.listarTodos_AutoresDeLibro(libro, autor,null);
        for(Autor aut : autores) {
            System.out.println(" >> " + aut.getNombreCompleto());
        }
    }
    
    public void listarTodos_AutoresFiltradosDeLibro() {
        Libro libro = new Libro();
        // libro.setIdLibro(2); // Libro(2) == The Sandman Universe
        libro.setTitulo("Sand"); // Se buscaran autores de libros con 'Sand' en su titulo
        Autor autor = new Autor();
        autor.setNombreCompleto("Neil"); // De los autores buscados, se filtraran solos los que tenga 'Neil' en su nombre
        ArrayList<Autor> autores = this.libro_autorDAO.listarTodos_AutoresDeLibro(libro, autor,null);
        for(Autor aut : autores) {
            System.out.println(" >> " + aut.getNombreCompleto());
        }
    }
    
    // Basicamente es lo lismo que hacer un listar_todos_autor ero con la diferencia que en caso se necesite, se pueden agregar los otros filtros (Y QUE SOLO SE OBTIENE AUTORES DE LA TABLA LIBROS)
    public void listarTodos_AutoresFiltradosDeLibroGeneral() {
        Libro libro = new Libro();
        Autor autor = new Autor();
        autor.setNombreCompleto("Z"); // De los autores buscados, se filtraran solos los que tenga 'Z' en su nombre
        ArrayList<Autor> autores = this.libro_autorDAO.listarTodos_AutoresDeLibro(libro, autor,null);
        for(Autor aut : autores) {
            System.out.println(" >> " + aut.getNombreCompleto());
        }
    }
    
    public void listarTodos_TesisDeAutor() {
        Tesis tesis = new Tesis();
        Autor autor = new Autor();
        // autor.setIdAutor(9); // Autor(9) == Fiódor Dostroyevski
        autor.setNombreCompleto("Dostoy");  // Se buscaran libros de autores con 'Dostoy' en su nombre
        ArrayList<Tesis> tesises = this.tesis_autorDAO.listarTodos_TesisDeAutor(tesis, autor, null, 2);
        for(Tesis tes : tesises) {
            System.out.println(" >> " + tes.getTitulo());
        }
    }
    
    public void listarTodos_TesisFiltradosDeAutor() {
        Tesis tesis = new Tesis();
        tesis.setTitulo("Heart"); // De los libros buscados, se filtraran los que tengan 'Heart' en su titulo
        Autor autor = new Autor();
        // autor.setIdAutor(9); // Autor(9) == Fiódor Dostroyevski
        autor.setNombreCompleto("Dostoy");  // Se buscaran libros de autores con 'Dostoy' en su nombre
        ArrayList<Tesis> tesises = this.tesis_autorDAO.listarTodos_TesisDeAutor(tesis, autor, null, 2);
        for(Tesis tes : tesises) {
            System.out.println(" >> " + tes.getTitulo());
        }
    }
    
    // Basicamente es lo lismo que hacer un listar_todos_libro , pero con la diferencia que en caso se necesite, se pueden agregar los otros filtros
    public void listarTodos_TesisFiltradosDeAutorGeneral() {
        Tesis tesis = new Tesis();
        tesis.setTitulo("H"); // De los libros buscados, se filtraran los que tengan 'H' en su titulo
        Autor autor = new Autor(); 
        ArrayList<Tesis> tesises = this.tesis_autorDAO.listarTodos_TesisDeAutor(tesis, autor, null, 2);
        for(Tesis tes : tesises) {
            System.out.println(" >> " + tes.getTitulo());
        }
    }
    
    public void listarTodos_AutoresDeTesis() {
        Tesis tesis = new Tesis();
        tesis.setTitulo("Sand"); // Se buscaran autores de libros con 'Sand' en su titulo
        Autor autor = new Autor();
        ArrayList<Autor> autores = this.tesis_autorDAO.listarTodos_AutoresDeTesis(tesis, autor,null);
        for(Autor aut : autores) {
            System.out.println(" >> " + aut.getNombreCompleto());
        }
    }
    
    public void listarTodos_AutoresFiltradosDeTesis() {
        Tesis tesis = new Tesis();
        // libro.setIdLibro(2); // Libro(2) == The Sandman Universe
        tesis.setTitulo("Sand"); // Se buscaran autores de libros con 'Sand' en su titulo
        Autor autor = new Autor();
        autor.setNombreCompleto("Neil"); // De los autores buscados, se filtraran solos los que tenga 'Neil' en su nombre
        ArrayList<Autor> autores = this.tesis_autorDAO.listarTodos_AutoresDeTesis(tesis, autor,null);
        for(Autor aut : autores) {
            System.out.println(" >> " + aut.getNombreCompleto());
        }
    }
    
    // Basicamente es lo lismo que hacer un listar_todos_autor ero con la diferencia que en caso se necesite, se pueden agregar los otros filtros (Y QUE SOLO SE ESTAN OBTENIEDO AUTORES DE LA TABLA TESIS)
    public void listarTodos_AutoresFiltradosDeTesisGeneral() {
        Tesis tesis = new Tesis();
        Autor autor = new Autor();
        autor.setNombreCompleto("Z"); // De los autores buscados, se filtraran solos los que tenga 'Z' en su nombre
        ArrayList<Autor> autores = this.tesis_autorDAO.listarTodos_AutoresDeTesis(tesis, autor,null);
        for(Autor aut : autores) {
            System.out.println(" >> " + aut.getNombreCompleto());
        }
    }
}
