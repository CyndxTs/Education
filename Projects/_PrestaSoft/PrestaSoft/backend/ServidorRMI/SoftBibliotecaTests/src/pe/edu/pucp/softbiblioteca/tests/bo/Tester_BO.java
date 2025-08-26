
/* [/]
 >> @Project:    SoftBibliotecaTestBO
 >> @File:       Tester_BO.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.tests.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.localidad.bo.BibliotecaBO;
import pe.edu.pucp.softbiblioteca.localidad.bo.ConsorcioBO;
import pe.edu.pucp.softbiblioteca.localidad.bo.UniversidadBO;
import pe.edu.pucp.softbiblioteca.localidad.model.Biblioteca;
import pe.edu.pucp.softbiblioteca.localidad.model.Consorcio;
import pe.edu.pucp.softbiblioteca.localidad.model.Universidad;
import pe.edu.pucp.softbiblioteca.politicas.bo.MoraBO;
import pe.edu.pucp.softbiblioteca.politicas.bo.PoliticaBO;
import pe.edu.pucp.softbiblioteca.politicas.bo.PoliticaPrestamoBO;
import pe.edu.pucp.softbiblioteca.politicas.bo.PoliticaReservaBO;
import pe.edu.pucp.softbiblioteca.politicas.model.Mora;
import pe.edu.pucp.softbiblioteca.politicas.model.Politica;
import pe.edu.pucp.softbiblioteca.politicas.model.PoliticaPrestamo;
import pe.edu.pucp.softbiblioteca.politicas.model.PoliticaReserva;
import pe.edu.pucp.softbiblioteca.prestamos.bo.PenalizacionBO;
import pe.edu.pucp.softbiblioteca.prestamos.bo.PrestamoBO;
import pe.edu.pucp.softbiblioteca.prestamos.model.Penalizacion;
import pe.edu.pucp.softbiblioteca.prestamos.model.Prestamo;
import pe.edu.pucp.softbiblioteca.publicaciones.bo.AutorBO;
import pe.edu.pucp.softbiblioteca.publicaciones.bo.CopiaEjemplarBO;
import pe.edu.pucp.softbiblioteca.publicaciones.bo.EditorialBO;
import pe.edu.pucp.softbiblioteca.publicaciones.bo.LibroBO;
import pe.edu.pucp.softbiblioteca.publicaciones.bo.PublicacionBO;
import pe.edu.pucp.softbiblioteca.publicaciones.bo.TemaBO;
import pe.edu.pucp.softbiblioteca.publicaciones.bo.TesisBO;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Autor;
import pe.edu.pucp.softbiblioteca.publicaciones.model.CopiaEjemplar;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Editorial;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Libro;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Publicacion;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tema;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tesis;
import pe.edu.pucp.softbiblioteca.reservas.bo.ReservaBO;
import pe.edu.pucp.softbiblioteca.reservas.model.Reserva;
import pe.edu.pucp.softbiblioteca.usuarios.bo.UsuarioBO;
import pe.edu.pucp.softbiblioteca.usuarios.model.Usuario;

public class Tester_BO {
    //     POLITICAS
    private final MoraBO moraBO;
    private final PoliticaPrestamoBO politicaPrestamoBO;
    private final PoliticaReservaBO politicaReservaBO;
    private final PoliticaBO politicaBO;
    //     LOCALIDAD
    private final ConsorcioBO consorcioBO;
    private final UniversidadBO universidadBO;
    private final BibliotecaBO bibliotecaBO;
    //     USUARIOS
    private final UsuarioBO usuarioBO;
    //     PUBLCACIONES
    private final EditorialBO editorialBO;
    private final TemaBO temaBO;
    private final AutorBO autorBO;
    private final LibroBO libroBO;
    private final TesisBO tesisBO;
    private final PublicacionBO publicacionBO;
    private final CopiaEjemplarBO copiaEjemplarBO;
    //     PRESTAMOS
    private final PenalizacionBO penalizacionBO;
    private final PrestamoBO prestamoBO;
    //     RESERVAS
    private final ReservaBO reservaBO;

    public Tester_BO() {
        //         POLITICAS
        this.moraBO = new MoraBO();
        this.politicaPrestamoBO = new PoliticaPrestamoBO();
        this.politicaReservaBO = new PoliticaReservaBO();
        this.politicaBO = new PoliticaBO();
        //         LOCALIDAD
        this.consorcioBO = new ConsorcioBO();
        this.universidadBO = new UniversidadBO();
        this.bibliotecaBO = new BibliotecaBO();
        //         USUARIOS
        this.usuarioBO = new UsuarioBO();
        //         PUBLICACIONES
        this.editorialBO = new EditorialBO();
        this.temaBO = new TemaBO();
        this.autorBO = new AutorBO();
        this.libroBO = new LibroBO();
        this.tesisBO = new TesisBO();
        this.publicacionBO = new PublicacionBO();
        this.copiaEjemplarBO = new CopiaEjemplarBO();
        //         PRESTAMOS
        this.prestamoBO = new PrestamoBO();
        this.penalizacionBO = new PenalizacionBO();
        //         RESERVAS
        this.reservaBO = new ReservaBO();
    }
    
    public void testBusquedaAvanzada() {
       ArrayList<Publicacion> publicaciones = this.publicacionBO.busquedaAvanzada(0,"Tik",null,null,null,null,null);
       for(Publicacion pub : publicaciones) {
           System.out.println(" >> " + pub.getTitulo());
       }
    }
  
    public void testPublicacionesBO(){
        PublicacionBO publicacionBO = new PublicacionBO();
        TesisBO tesisBO = new TesisBO();
        LibroBO libroBO = new LibroBO();
        
//      FUNCIONA
        ArrayList<Publicacion> publicaciones = publicacionBO.verCatalogo(0, 0, null, null,null);
        for(Publicacion p : publicaciones){
            System.out.println(p.consultarDatos());
        }
        /*
//      FUNCIONA
        ArrayList<Publicacion> pubAvanzada = publicacionBO.busquedaAvanzada(0, "Intro", null,-1 ,-1, null, "Ju");
        for(Publicacion p : pubAvanzada){
            System.out.println(p.consultarDatos());
        }
*/
//        FUNCIONA
        Tesis t = tesisBO.verDetallePublicacioTesis(18);
        System.out.println(t.consultarDatos());

//        FUNCIONA    
        Libro l = libroBO.verDetallePublicacionLibro(14);
        System.out.println(l.consultarDatos());
        
//       FUNCIONA
        ArrayList<Publicacion> pubs = publicacionBO.publicacionesPorAutor(3);
        for(Publicacion p : pubs){
            System.out.println(p.consultarDatos());
//        }

        CopiaEjemplar copia = publicacionBO.consultarCopiaEjemplar(1);
        System.out.println(copia.consultarDatos());
        ReservaBO reservaBO = new ReservaBO();
        ArrayList<Reserva> reservas = reservaBO.mostrarReservasEnCurso(13);
            for(Reserva res : reservas){
                System.out.println(res.getEjemplarAsociado().getPublicacionAsociada().getTitulo());
            }
        }
    }
    
    public void testPrestamoYReservaBO(){

        PrestamoBO prestamoBO = new PrestamoBO();
        ReservaBO reservaBO = new ReservaBO();
        PenalizacionBO penalizacionBO = new PenalizacionBO();
        CopiaEjemplarBO copiaEjemplarBO = new CopiaEjemplarBO();
        AutorBO autorBO = new AutorBO();
        EditorialBO editorialBO = new EditorialBO();
////        FUNCIONA
//        ArrayList<Reserva> reservas = reservaBO.mostrarReservasEnCurso(2);
//        for(Reserva r : reservas){
//            System.out.println(r.consultarDatos());
//        }
//
////      FUNCIONA
//        ArrayList<Prestamo> prestamos = prestamoBO.mostrarPrestamosEnCurso(17,null);
//        for(Prestamo p : prestamos){
//            System.out.println(p.consultarDatos());
//        }
////    FUNCIONA
//        ArrayList<Penalizacion> penalizaciones = penalizacionBO.verPenalizacionesPrestamo(13);
//        for(Penalizacion p : penalizaciones){
//            System.out.println(p.consultarDatos());
//        }
//
////        FUNCIONA (verificado en la bd)
//        reservaBO.cancelarReserva(14);
    
//       FUNCIONA
        ArrayList<Prestamo> ultimosPrestamos = prestamoBO.mostrarUltimosPrestamos(5);
        for(Prestamo p : ultimosPrestamos){
            System.out.println( p.getIdPrestamo() + " us: " + p.getUsuarioAsociado().getApellidos() + " pub: " + p.getEjemplarAsociado().getPublicacionAsociada().getTitulo());
        }
        
////        //FUNCIONA
////       Publicacion p = publicacionBO.consultarCopiaEjemplar(2);
////       System.out.println(p.consultarDatos());
//
//        //FUNCIONA
//        ArrayList<Autor> autores = autorBO.buscarAutorPorNombre("Gabriel Garcia Marquez",5);
//        ArrayList<Integer> idAutores = new ArrayList<>();
//        for(Autor autor : autores){
//            idAutores.add(autores.get(0).getIdAutor());
//            System.out.println(idAutores);
//        }
//        
//        
//        //FUNCIONA
//        autorBO.insertarAutor("Juan Tiburcio");
//        
//       //FUNCIONA
//       ArrayList<Editorial> editoriales = editorialBO.buscarEditorialPorNombre("Editorial");
//       for(Editorial e : editoriales){
//           System.out.println(e.consultarDatos());
//       }
//
////        FUNCIONA
//        ArrayList<CopiaEjemplar> copiasLibro = copiaEjemplarBO.ObtenerCopiasEjemplaresDeLibro(1);
//        for(CopiaEjemplar cp : copiasLibro){
//            System.out.println(cp.consultarDatos());
//        }
//        
//        
//        //FUNCIONA
//        ArrayList<CopiaEjemplar> copiasTesis = copiaEjemplarBO.ObtenerCopiasEjemplaresDeLibro(1);
//        for(CopiaEjemplar cp : copiasTesis){
//            System.out.println(cp.consultarDatos());
//        }
////        FUNCIONA
//        reservaBO.solicitarReserva(13,1,"libro");
//        
//        //FUNCIONA
//        prestamoBO.solicitarAmpliacion(11);
//        
//        
    }
   
    public void testLibroYTesisBO(){
        LibroBO libroBO = new LibroBO();
        TesisBO tesisBO = new TesisBO();
        CopiaEjemplarBO copiaejemplarBO = new CopiaEjemplarBO();
        
//        PARA LIBRO
//        FUNCIONA NUEVA PUBLICACION LIBRO
        
        /*
        Libro libro = libroBO.verDetallePublicacionLibro(20);
        
        ArrayList<String> direcciones = new ArrayList<>();
        direcciones.add("PRU-123");
        direcciones.add("PRU-124");
        direcciones.add("PRU-125");
        direcciones.add("PRU-126");
        direcciones.add("PRU-127");
        
        ArrayList<String> autores = new ArrayList<>();
        autores.add("Diego Lavado");
        autores.add("Juan Tiburcio");

        ArrayList<Integer> autores = new ArrayList<>();
        autores.add(36);
        libroBO.nuevaPublicacionLibro("libroPrueba",libro.getFechaPublicacion().toString() , libro.getDescripcion(), libro.getCopiasDisponibles(),1,
                1, libro.getUrl(), libro.getPortada(), libro.getBibliotecaAsociada().getIdBiblioteca(), libro.getISBN(), libro.getEditorial().getIdEditorial(),
                libro.getTipo().toString(), libro.getVolumen(), libro.getMateria(), libro.getGenero(), libro.getTomo(),null,autores);
        
     
        */

//        MODIFICAR FUNCIONA
        
        /*
        Libro libro = libroBO.verDetallePublicacionLibro(28);
        
        ArrayList<CopiaEjemplar> copias = copiaejemplarBO.ObtenerCopiasEjemplaresDeLibro(libro.getIdLibro());
        
        ArrayList<Integer> idsCopiasEjemplares = new ArrayList<>();
        ArrayList<String> estadosCopiasEjemplares = new ArrayList<>();
        
        for(CopiaEjemplar copia : copias){
            idsCopiasEjemplares.add(copia.getIdCopiaEjemplar());
            estadosCopiasEjemplares.add(copia.getEstado().toString());
        }
        estadosCopiasEjemplares.set(2,"DANIADO");
        libroBO.modificarPublicacionLibro(28,libro.getTitulo(),libro.getFechaPublicacion().toString() , libro.getDescripcion(), libro.getCopiasDisponibles(),1,
                1, libro.getUrl(), libro.getPortada(), libro.getBibliotecaAsociada().getIdBiblioteca(), libro.getISBN(), libro.getEditorial().getIdEditorial(),
                libro.getTipo().toString(), libro.getVolumen(), libro.getMateria(), libro.getGenero(), libro.getTomo(),idsCopiasEjemplares, estadosCopiasEjemplares,null,0);
        */
        
//        PARA TESIS
        
        /*
        String titulo, String fechaPublicacion, String descripcion,
                            Integer copiasDisponibles, Integer hayFormatoFisico,Integer hayFormatoVirtual, String url, byte[] portada,
                            Integer idBibliotecaAsociada,String gradoAcademico, ArrayList<String> direccioneslocal,
                            ArrayList<String> autores,ArrayList<String> temas
        */
        /*
        Tesis tesis = tesisBO.verDetallePublicacioTesis(20);
        
        
        ArrayList<String> autores = new ArrayList<>();
        autores.add("Diego Lavado");
        autores.add("Juan Tiburcio");
        
        ArrayList<String> temas = new ArrayList<>();
        temas.add("Diego Lavado");
        temas.add("Juan Tiburcio");
        
        tesisBO.nuevaPublicacionTesis(tesis.getTitulo(),tesis.getFechaPublicacion().toString(), tesis.getDescripcion(), 
                tesis.getCopiasDisponibles(), 1, 1, tesis.getUrl(), tesis.getPortada(), tesis.getBibliotecaAsociada().getIdBiblioteca(), 
                tesis.getGradoAcademico().toString(), null, autores, temas);
        */
        /*
        nteger idTesis,String titulo, String fechaPublicacion, String descripcion,
                            Integer copiasDisponibles, Integer hayFormatoFisico,Integer hayFormatoVirtual, String url, byte[] portada,
                            Integer idBibliotecaAsociada,String gradoAcademico,
                            ArrayList<Integer> idsCopiasEjemplares, ArrayList<String> estadosCopiasEjemplares,
                            ArrayList<Integer> autores,ArrayList<Integer> temas,Integer cantCopiasPorInsertar
        */
        /*
        Tesis tesis = tesisBO.verDetallePublicacioTesis(24);
        
        Tesis_AutorBO tesisautorBO = new Tesis_AutorBO();
        ArrayList<Autor> autores = tesisautorBO.listarTodos_AutoresDeTesis(24);
        
        ArrayList<Integer> int_autores = new ArrayList<>();
        for(Autor aut:autores){
            int_autores.add(aut.getIdAutor());
        }
        int_autores.set(0, 3);
        
        tesisBO.modificarPublicacionTesis(tesis.getIdTesis(),tesis.getTitulo(),tesis.getFechaPublicacion().toString(), tesis.getDescripcion(), 
                tesis.getCopiasDisponibles(), 1, 1, tesis.getUrl(), tesis.getPortada(), tesis.getBibliotecaAsociada().getIdBiblioteca(), 
                tesis.getGradoAcademico().toString(), null,null, int_autores, null,0);
*/
    }
    
    public void testUsuariosBO(){
        UsuarioBO usuarioBO = new UsuarioBO();
        
//        FUNCIONA
        //Usuario us = usuarioBO.iniciarSesion("rodrigoxd330", "123");
        Usuario us2 = usuarioBO.iniciarSesion("diegolavadoc@pucp.edu.pe", "123");
        System.out.println(us2.consultarDatos());

        //FUNCIONA
        //Integer registro = usuarioBO.registrarMiembro("nomprueba", "apelprueba", "prueba@pucp.edu.pe", "1234567", "1234567", "rodrigoxd330", "1");
        //System.out.println(registro);
    }
    public void testRegistroPrestamos(){
        PrestamoBO prestamoBO = new PrestamoBO();
        
        prestamoBO.registrarPrestamo(1,146);

        //FUNCIONA
        Integer registro = usuarioBO.registrarMiembro("nomprueba", "apelprueba", "prueba@pucp.edu.pe", "1234567", "1234567", "rodrigoxd330", "1");
        System.out.println(registro);
    }
    public void testUniversidades(){
        UniversidadBO universidadBO = new UniversidadBO();
        
        ArrayList<Universidad>us= universidadBO.listarTodos(-1, -1, -1);
        for (Universidad u : us) {
            System.out.println(u.consultarDatos());
        }
        
    }
}
