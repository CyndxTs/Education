
/* [/]
 >> Project:    SoftBiblioecaPublicacionesBO
 >> File:       LibroBO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.bo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softbiblioteca.localidad.model.Biblioteca;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.CopiaEjemplarDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.LibroDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.Libro_AutorDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.CopiaEjemplarDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.LibroDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.Libro_AutorDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Autor;
import pe.edu.pucp.softbiblioteca.publicaciones.model.CopiaEjemplar;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Editorial;
import pe.edu.pucp.softbiblioteca.publicaciones.model.EstadoEjemplar;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Libro;
import pe.edu.pucp.softbiblioteca.publicaciones.model.TipoLibro;
import pe.edu.pucp.softbiblioteca.util.VKF_Formatter;

public class LibroBO {
    private final LibroDAO libroDAO;
    private AutorBO autorBO;
    private Libro_AutorDAO libroautorDAO;
    private CopiaEjemplarDAO copiaejemplarDAO;
    private final VKF_Formatter vkf;
    private final Libro_AutorDAO libro_autorDAO;
    
    
    public LibroBO(){
        this.libroDAO = new LibroDAOImpl();
        this.autorBO = new AutorBO();
        this.libroautorDAO = new Libro_AutorDAOImpl();
        this.copiaejemplarDAO = new CopiaEjemplarDAOImpl();
        this.vkf = new VKF_Formatter();
        this.libro_autorDAO = new Libro_AutorDAOImpl();
    }
    
    public Integer insertar(String titulo,String fechaPublicacion, String descripcion,
                            Integer copiasDisponibles, Integer hayFormatoFisico,
                            Integer hayFormatoVirtual, String url, byte[] portada,
                            Integer idBibliotecaAsociada,Integer ISBN,Integer idEditorial,String tipo,
                            Integer volumen, String materia, String genero, Integer tomo) {
        Boolean b_hayFormatoFisico = vkf.toValidBoolean(hayFormatoFisico);
        Boolean b_hayFormatoVirtual = vkf.toValidBoolean(hayFormatoVirtual);
        Date d_fechaPublicacion = vkf.toValidDate(fechaPublicacion);
        Biblioteca bibliotecaAsociada = new Biblioteca();
        bibliotecaAsociada.setIdBiblioteca(idBibliotecaAsociada);
        Editorial editorial = new Editorial();
        editorial.setIdEditorial(idEditorial);
        TipoLibro tl_tipo = vkf.toValidEnum(tipo, TipoLibro.class);
        Libro libro = new Libro(null, titulo,d_fechaPublicacion, descripcion,
                                copiasDisponibles, b_hayFormatoFisico, b_hayFormatoVirtual,
                                url, portada, bibliotecaAsociada, ISBN, editorial,
                                tl_tipo,volumen, materia, genero,
                                tomo);
        return this.libroDAO.insertar(libro);
    }
    
    public Integer modificar(Integer idLibro,String titulo, String fechaPublicacion, String descripcion,
                            Integer copiasDisponibles, Integer hayFormatoFisico,
                            Integer hayFormatoVirtual, String url, byte[] portada,
                            Integer idBibliotecaAsociada,Integer ISBN,Integer idEditorial,String tipo,
                            Integer volumen, String materia, String genero, Integer tomo) {
        Boolean b_hayFormatoFisico = vkf.toValidBoolean(hayFormatoFisico);
        Boolean b_hayFormatoVirtual = vkf.toValidBoolean(hayFormatoVirtual);
        Date d_fechaPublicacion = vkf.toValidDate(fechaPublicacion);
        Biblioteca bibliotecaAsociada = new Biblioteca();
        bibliotecaAsociada.setIdBiblioteca(idBibliotecaAsociada);
        Editorial editorial = new Editorial();
        editorial.setIdEditorial(idEditorial);
        TipoLibro tl_tipo = vkf.toValidEnum(tipo, TipoLibro.class);
        Libro libro = new Libro(idLibro, titulo,d_fechaPublicacion, descripcion,
                                copiasDisponibles, b_hayFormatoFisico, b_hayFormatoVirtual,
                                url, portada, bibliotecaAsociada, ISBN, editorial,
                                tl_tipo, volumen, materia, genero,
                                tomo);
        return this.libroDAO.modificar(libro);
    }
    
    public Integer eliminar(Integer idLibro) {        
        Libro libro = new Libro();
        libro.setIdLibro(idLibro);
        return this.libroDAO.eliminar(libro);
    }
    
    public ArrayList<Libro> listarTodos(String titulo, String fechaPublicacion, String descripcion,
                                        Integer hayFormatoFisico,Integer hayFormatoVirtual,Integer idBibliotecaAsociada,
                                        Integer idEditorial,String tipo,Integer volumen,
                                        String materia, String genero, Integer tomo, Integer limite) {
        Date d_fechaPublicacion = vkf.toValidDate(fechaPublicacion);
        Boolean b_hayFormatoFisico = vkf.toValidBoolean(hayFormatoFisico);
        Boolean b_hayFormatoVirtual = vkf.toValidBoolean(hayFormatoVirtual);
        Biblioteca bibliotecaAsociada = new Biblioteca();
        bibliotecaAsociada.setIdBiblioteca(idBibliotecaAsociada);
        Editorial editorial = new Editorial();
        editorial.setIdEditorial(idEditorial);
        TipoLibro tl_tipo = null;
        if(tipo != null)tl_tipo = vkf.toValidEnum(tipo, TipoLibro.class);
        Libro libro = new Libro(null, titulo, d_fechaPublicacion, descripcion, null,
                                b_hayFormatoFisico, b_hayFormatoVirtual, null, null,
                                bibliotecaAsociada, null, editorial, tl_tipo, volumen,
                                materia, genero, tomo);
        return this.libroDAO.listarTodos(libro, limite, 2);
    }
    
    public Libro obtenerPorAtributosUnicos(Integer ISBN) {
        Libro libro = new Libro();
        libro.setISBN(ISBN);
        return this.libroDAO.obtenerPorAtributosUnicos(libro);
    }
    
    
   //-----------------------------LOS DE ABAJO ESTAN EN EL WS----------------------------
    public Libro verDetallePublicacionLibro(Integer idLibro){
        return this.libroDAO.obtenerPorId(idLibro);
    }
    
    public Integer nuevaPublicacionLibro(String titulo,String fechaPublicacion, String descripcion,
                            Integer copiasDisponibles, Integer hayFormatoFisico,
                            Integer hayFormatoVirtual, String url, byte[] portada,
                            Integer idBibliotecaAsociada,Integer ISBN,Integer idEditorial,String tipo,
                            Integer volumen, String materia, String genero, Integer tomo, ArrayList<String> direccioneslocal,
                            ArrayList<Integer> autores){
        Integer idLibro = this.insertar(titulo, fechaPublicacion, descripcion, copiasDisponibles, hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada, ISBN, idEditorial, tipo, volumen, materia, genero, tomo);
        if(idLibro > 0){
            Libro libroporregistrar = new Libro();
            libroporregistrar.setIdLibro(idLibro);
            //PARA LOS AUTORES, SI ES NULL NO HACE CAMBIOS
            if(autores != null){
                for(Integer idAutor : autores){                    
                    Autor autorporregistrar = new Autor();
                    autorporregistrar.setIdAutor(idAutor);
                    libroautorDAO.insertar(libroporregistrar, autorporregistrar);
                }
            }
            //AL SER UN LIBRO NUEVO TODAS LAS COPIAS SE ENCUENTRAN EN BUEN ESTADO
            for(Integer i = 0; i < copiasDisponibles;i++){
                CopiaEjemplar copiaejemplar = new CopiaEjemplar();
                String direccion = null;
                if(direccioneslocal != null && i < direccioneslocal.size()){
                    direccion = direccioneslocal.get(i);
                }
                
                copiaejemplar.setDireccionLocal(direccion);
                copiaejemplar.setEstado(EstadoEjemplar.OPTIMO);
                copiaejemplar.setPublicacionAsociada(libroporregistrar);

                copiaejemplarDAO.insertar(copiaejemplar);
            }

            return idLibro;
        }else{
            return -1;//FALLO AL INSERTAR LIBRO;
        }
    }
    
    public Integer modificarPublicacionLibro(Integer idLibro,String titulo, String fechaPublicacion, String descripcion,
                            Integer copiasDisponibles, Integer hayFormatoFisico,
                            Integer hayFormatoVirtual, String url, byte[] portada,
                            Integer idBibliotecaAsociada,Integer ISBN,Integer idEditorial,String tipo,
                            Integer volumen, String materia, String genero, Integer tomo,
                            ArrayList<Integer> idsCopiasEjemplares, ArrayList<String> estadosCopiasEjemplares,
                            ArrayList<Integer> autores,Integer cantCopiasPorInsertar){
        //PARA PROBLEMAS BYTE
        if(portada == null){
            Libro librotemporal = this.libroDAO.obtenerPorId(idLibro);
            portada = librotemporal.getPortada();
        }
        
        
        Integer verificaFunciono = this.modificar(idLibro, titulo, fechaPublicacion, descripcion, copiasDisponibles, hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada, ISBN, idEditorial, tipo, volumen, materia, genero, tomo);
        int cantDisponibles=0;
        if(verificaFunciono>=0){
            //RECUPERAMOS EJEMPLAR
            Integer index = 0;
            for(Integer idCopiaEjemplar: idsCopiasEjemplares){
                //SI QUISIEROS DIRECCION LOCAL SE AGREGA EL PARAMETRO, NO ES NECESARIO OBTENER POR ID
                CopiaEjemplar copiaejemplartemporal = new CopiaEjemplar();
                String str_nuevoestadoejemplar = estadosCopiasEjemplares.get(index);
                EstadoEjemplar ee_nuevoestadoejemplar = EstadoEjemplar.valueOf(str_nuevoestadoejemplar);
                copiaejemplartemporal.setEstado(ee_nuevoestadoejemplar);
                copiaejemplartemporal.setIdCopiaEjemplar(idCopiaEjemplar);
                Libro librotemp = new Libro();
                librotemp.setIdPublicacion(idLibro);
                librotemp.setIdLibro(idLibro);
                copiaejemplartemporal.setPublicacionAsociada(librotemp);
                copiaejemplarDAO.modificar(copiaejemplartemporal);
                if(copiaejemplartemporal.getEstado().toString() == "OPTIMO")
                    cantDisponibles++;
                index++;
            }
            
            if(cantCopiasPorInsertar>0){
                Libro librotemporal = new Libro();
                librotemporal.setIdLibro(idLibro);
                librotemporal.setIdPublicacion(idLibro);//PORSIACA
                for(int i=0;i<cantCopiasPorInsertar;i++){
                    CopiaEjemplar copiaejemplarporregistrar = new CopiaEjemplar();
                    copiaejemplarporregistrar.setEstado(EstadoEjemplar.OPTIMO);
                    copiaejemplarporregistrar.setPublicacionAsociada(librotemporal);
                    this.copiaejemplarDAO.insertar(copiaejemplarporregistrar);
                }
            }
            
            if(autores != null){
                //PARA LOS AUTORES ELIMINAMOS LOS DE LA TABLA INTERMEDIA
                ArrayList<Autor> autoressinmodificar = this.autorBO.listarTodos_AutoresDeLibroPorID(idLibro);
                for(Autor autor:autoressinmodificar){
                    Libro libroporeliminar = new Libro();
                    libroporeliminar.setIdLibro(idLibro);
                    Autor autorporeliminar = new Autor();
                    autorporeliminar.setIdAutor(autor.getIdAutor());
                    libroautorDAO.eliminar(libroporeliminar, autorporeliminar);
                }
                //VUELVO A INSERTAR CON AUTORES NUEVOS
                for(Integer idautorporinsertar : autores){
                    Libro libroporregistrar = new Libro();
                    libroporregistrar.setIdLibro(idLibro);
                    Autor autorporregistrar = new Autor();
                    autorporregistrar.setIdAutor(idautorporinsertar);
                    libroautorDAO.insertar(libroporregistrar, autorporregistrar);
                }
            }
            
            
            if(cantDisponibles + cantCopiasPorInsertar != copiasDisponibles){
                Integer ver = this.modificar(idLibro, titulo, fechaPublicacion, descripcion, cantDisponibles+cantCopiasPorInsertar, hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada, ISBN, idEditorial, tipo, volumen, materia, genero, tomo);
                if(ver==1) return idLibro;
                else return -1;
            }
            else
                return idLibro;
        }else{
            return -1;//FALLO AL MODIFICAR LIBRO;
        }  
    }
    
    
    public ArrayList<Libro> listarTodos_LibrosDeAutorPorID(Integer idAutor) {
        Autor autor = new Autor();
        autor.setIdAutor(idAutor);
        return this.libro_autorDAO.listarTodos_LibrosDeAutor(null,autor,null,2);
    }
    
    public ArrayList<Libro> listarTodos_LibrosDeAutor(String titulo, String fechaPublicacion,
                                                      String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual,
                                                      Integer idBibliotecaAsociada, Integer idEditorial, String tipo, Integer volumen,
                                                      String materia, String genero, Integer tomo, Integer idAutor, String nombreCompleto) {
        Date d_fechaPublicacion = vkf.toValidDate(fechaPublicacion);
        Boolean b_hayFormatoFisico = vkf.toValidBoolean(hayFormatoFisico);
        Boolean b_hayFormatoVirtual = vkf.toValidBoolean(hayFormatoVirtual);
        Biblioteca bibliotecaAsociada = new Biblioteca();
        bibliotecaAsociada.setIdBiblioteca(idBibliotecaAsociada);
        Editorial editorial = new Editorial();
        editorial.setIdEditorial(idEditorial);
        TipoLibro tl_tipo = vkf.toValidEnum(tipo, TipoLibro.class);
        Libro libro = new Libro(null, titulo, d_fechaPublicacion, descripcion, null,
                                b_hayFormatoFisico, b_hayFormatoVirtual, null, null,
                                bibliotecaAsociada, null, editorial, tl_tipo, volumen,
                                materia, genero, tomo);
        Autor autor = new Autor(idAutor,nombreCompleto);
        return this.libro_autorDAO.listarTodos_LibrosDeAutor(libro,autor,null,2);
    }
    
    
    
    
    
}
