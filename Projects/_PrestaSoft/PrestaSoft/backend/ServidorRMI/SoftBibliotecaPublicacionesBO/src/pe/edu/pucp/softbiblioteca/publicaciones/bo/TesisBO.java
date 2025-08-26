
/* [/]
 >> Project:    SoftBiblioecaPublicacionesBO
 >> File:       TesisBO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.bo;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softbiblioteca.localidad.model.Biblioteca;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.CopiaEjemplarDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.TesisDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.Tesis_AutorDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.Tesis_TemaDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.CopiaEjemplarDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.TesisDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.Tesis_AutorDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.Tesis_TemaDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Autor;
import pe.edu.pucp.softbiblioteca.publicaciones.model.CopiaEjemplar;
import pe.edu.pucp.softbiblioteca.publicaciones.model.EstadoEjemplar;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tema;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tesis;
import pe.edu.pucp.softbiblioteca.publicaciones.model.TipoGrado;
import pe.edu.pucp.softbiblioteca.util.VKF_Formatter;

public class TesisBO {
    private final TesisDAO tesisDAO;
    private AutorBO autorBO;
    private Tesis_AutorDAO tesisautorDAO;
    private TemaBO temaBO;
    private Tesis_TemaDAO tesistemaDAO;
    private CopiaEjemplarDAO copiaejemplarDAO;
    private final VKF_Formatter vkf;
    
    public TesisBO(){
        this.tesisDAO = new TesisDAOImpl();
        this.tesisautorDAO = new Tesis_AutorDAOImpl();
        this.temaBO = new TemaBO();
        this.autorBO = new AutorBO();
        this.tesistemaDAO = new Tesis_TemaDAOImpl();
        this.copiaejemplarDAO = new CopiaEjemplarDAOImpl();
        this.vkf = new VKF_Formatter();
    }
    
    public Integer insertar(String titulo, String fechaPublicacion, String descripcion,
                            Integer copiasDisponibles, Integer hayFormatoFisico,Integer hayFormatoVirtual, String url, byte[] portada,
                            Integer idBibliotecaAsociada,String gradoAcademico) { 
        Date d_fechaPublicacion = vkf.toValidDate(fechaPublicacion);
        Boolean b_hayFormatoFisico = vkf.toValidBoolean(hayFormatoFisico);
        Boolean b_hayFormatoVirtual = vkf.toValidBoolean(hayFormatoVirtual);
        Biblioteca bibliotecaAsociada = new Biblioteca();
        bibliotecaAsociada.setIdBiblioteca(idBibliotecaAsociada);
        TipoGrado tg_gradoAcademico = vkf.toValidEnum(gradoAcademico, TipoGrado.class);
        Tesis tesis = new Tesis(null, titulo, d_fechaPublicacion,descripcion, copiasDisponibles,
                                b_hayFormatoFisico, b_hayFormatoVirtual, url, portada,
                                bibliotecaAsociada, tg_gradoAcademico);
        return this.tesisDAO.insertar(tesis);    
    }
    
    public Integer modificar(Integer idTesis,String titulo, String fechaPublicacion, String descripcion,
                            Integer copiasDisponibles, Integer hayFormatoFisico,Integer hayFormatoVirtual, String url, byte[] portada,
                            Integer idBibliotecaAsociada,String gradoAcademico) { 
        Date d_fechaPublicacion = vkf.toValidDate(fechaPublicacion);
        Boolean b_hayFormatoFisico = vkf.toValidBoolean(hayFormatoFisico);
        Boolean b_hayFormatoVirtual = vkf.toValidBoolean(hayFormatoVirtual);
        Biblioteca bibliotecaAsociada = new Biblioteca();
        bibliotecaAsociada.setIdBiblioteca(idBibliotecaAsociada);
        TipoGrado tg_gradoAcademico = vkf.toValidEnum(gradoAcademico, TipoGrado.class);
        Tesis tesis = new Tesis(idTesis, titulo, d_fechaPublicacion, descripcion,
                                copiasDisponibles, b_hayFormatoFisico, b_hayFormatoVirtual,
                                url, portada, bibliotecaAsociada, tg_gradoAcademico);
        return this.tesisDAO.modificar(tesis);    
    }
    
    public Integer eliminar(Integer idTesis) {        
        Tesis tesis = new Tesis();
        tesis.setIdTesis(idTesis);
        return this.tesisDAO.eliminar(tesis);
    }
    
    public ArrayList<Tesis> listarTodos(Integer limite){
        return this.tesisDAO.listarTodos(null,limite,2);
    }
    
    //NO BORRAR ESTE PORQUE ES REUTILIZADO (PUBLICACIONES BO, SIN EMBARGO NO SE AGREGA A WS)
    public ArrayList<Tesis> listarTodos(String titulo, String fechaPublicacion, String descripcion,
                                        Integer hayFormatoFisico,Integer hayFormatoVirtual,Integer idBibliotecaAsociada,
                                        String gradoAcademico, Integer limite) {
        Date d_fechaPublicacion = vkf.toValidDate(fechaPublicacion);
        Boolean b_hayFormatoFisico = vkf.toValidBoolean(hayFormatoFisico);
        Boolean b_hayFormatoVirtual = vkf.toValidBoolean(hayFormatoVirtual);
        Biblioteca bibliotecaAsociada = new Biblioteca();
        bibliotecaAsociada.setIdBiblioteca(idBibliotecaAsociada);
        TipoGrado tg_gradoAcademico = null;
        if(gradoAcademico != null) tg_gradoAcademico = vkf.toValidEnum(gradoAcademico, TipoGrado.class);
        Tesis tesis = new Tesis(null, titulo, d_fechaPublicacion, descripcion,null,
                                b_hayFormatoFisico,b_hayFormatoVirtual, null, null,
                                bibliotecaAsociada,tg_gradoAcademico);
        return this.tesisDAO.listarTodos(tesis,limite,2);
    }
    

    
    //APARTIR DE AQUI AGREGO-----------------------------------------------------------------------------------------------
    
    public Tesis verDetallePublicacioTesis(Integer idTesis){
        return this.tesisDAO.obtenerPorId(idTesis);
    }
    
    public Integer nuevaPublicacionTesis(String titulo, String fechaPublicacion, String descripcion,
                            Integer copiasDisponibles, Integer hayFormatoFisico,Integer hayFormatoVirtual, String url, byte[] portada,
                            Integer idBibliotecaAsociada,String gradoAcademico, ArrayList<String> direccioneslocal,
                            ArrayList<Integer> autores,ArrayList<Integer> temas){
        Integer idTesis = this.insertar(titulo, fechaPublicacion, descripcion, copiasDisponibles, hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada, gradoAcademico);
        if(idTesis>0){
            //RECUPERAMOS EL LIBRO 
            Tesis tesisPorRegistrar = new Tesis();
            tesisPorRegistrar.setIdTesis(idTesis);

            //PARA LOS AUTORES, SI ES NULL NO HACE CAMBIOS
            if(autores != null){
                for(Integer idAutor : autores){                    
                    Autor autorporregistrar = new Autor();
                    autorporregistrar.setIdAutor(idAutor);
                    tesisautorDAO.insertar(tesisPorRegistrar, autorporregistrar);
                }
            }

            
            //PARA LOS TEMAS, SI ES NULL NO HACE CAMBIOS
            if(temas != null){
                for(Integer idTema : temas){                    
                    Tema temaporregistar = new Tema();
                    temaporregistar.setIdTema(idTema);
                    tesistemaDAO.insertar(tesisPorRegistrar, temaporregistar);
                }
            }
            
            
            //AL SER UN LIBRO NUEVO TODAS LAS COPIAS SE ENCUENTRAN EN BUEN ESTADO
            
            for(Integer i = 0;i<copiasDisponibles;i++){
                CopiaEjemplar copiaejemplar = new CopiaEjemplar();
                String direccion = null;
                if(direccioneslocal != null && i < direccioneslocal.size()){
                    direccion = direccioneslocal.get(i);
                }
                copiaejemplar.setDireccionLocal(direccion);
                copiaejemplar.setEstado(EstadoEjemplar.OPTIMO);
                copiaejemplar.setPublicacionAsociada(tesisPorRegistrar);

                copiaejemplarDAO.insertar(copiaejemplar);
            }
            return idTesis;
        }else{
            return -1;//FALLO AL INSERTAR TESIS
        }
    }
    
    public Integer modificarPublicacionTesis(Integer idTesis,String titulo, String fechaPublicacion, String descripcion,
                            Integer copiasDisponibles, Integer hayFormatoFisico,Integer hayFormatoVirtual, String url, byte[] portada,
                            Integer idBibliotecaAsociada,String gradoAcademico,
                            ArrayList<Integer> idsCopiasEjemplares, ArrayList<String> estadosCopiasEjemplares,
                            ArrayList<Integer> autores,ArrayList<Integer> temas,Integer cantCopiasPorInsertar){
        
        System.out.println("Iniciando modificarPublicacionTesis");
        System.out.println("ID Tesis: " + idTesis);
        System.out.println("Título: " + titulo);
        System.out.println("Autores recibidos: " + autores.toString());
        System.out.println("Temas recibidos: " + temas.toString());
        System.out.println("IDs Copias Ejemplares recibidos: " + idsCopiasEjemplares.toString());
        System.out.println("Estados Copias Ejemplares recibidos: " + estadosCopiasEjemplares.getFirst());
        //PARA PROBLEMAS BYTE
        if(portada == null){
            Tesis tesistemporal = this.tesisDAO.obtenerPorId(idTesis);
            portada = tesistemporal.getPortada();
        }
        
        Integer verificaModifica = this.modificar(idTesis, titulo, fechaPublicacion, descripcion, copiasDisponibles, hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada, gradoAcademico);
        CopiaEjemplar copiaejemplar;
        if(verificaModifica>=0){
            //RECUPERAMOS EJEMPLAR
            int cantDisponibles=0;
            if(idsCopiasEjemplares!=null){
               Integer index = 0;

                for(Integer idCopiaEjemplar: idsCopiasEjemplares){
                    copiaejemplar = this.copiaejemplarDAO.obtenerPorId(idCopiaEjemplar);
                    String str_nuevoestadoejemplar = estadosCopiasEjemplares.get(index);
                    EstadoEjemplar ee_nuevoestadoejemplar = EstadoEjemplar.valueOf(str_nuevoestadoejemplar);
                    copiaejemplar.setEstado(ee_nuevoestadoejemplar);
                    copiaejemplarDAO.modificar(copiaejemplar);
                    if(copiaejemplar.getEstado().toString() == "OPTIMO")
                        cantDisponibles++;
                    index++;
                } 
            }


            if(cantCopiasPorInsertar!= null && cantCopiasPorInsertar>0){
                Tesis tesistemporal=new Tesis(); //= this.tesisDAO.obtenerPorId(idTesis);
                tesistemporal.setIdTesis(idTesis);
                for(int i=0;i<cantCopiasPorInsertar;i++){
                    CopiaEjemplar copiaejemplarporregistrar = new CopiaEjemplar();
                    copiaejemplarporregistrar.setEstado(EstadoEjemplar.OPTIMO);
                    copiaejemplarporregistrar.setPublicacionAsociada(tesistemporal);
                    this.copiaejemplarDAO.insertar(copiaejemplarporregistrar);
                }
            }

            if(autores!=null){
                //PARA LOS AUTORES ELIMINAMOS LOS DE LA TABLA INTERMEDIA
                ArrayList<Autor> autoressinmodificar = autorBO.listarTodos_AutoresDeTesisPorID(idTesis);
                for(Autor autor:autoressinmodificar){
                    Tesis tesisporeliminar = new Tesis();
                    tesisporeliminar.setIdTesis(idTesis);
                    Autor autorporeliminar = new Autor();
                    autorporeliminar.setIdAutor(autor.getIdAutor());
                    tesisautorDAO.eliminar(tesisporeliminar, autorporeliminar);
                }
                //VUELVO A INSERTAR CON AUTORES NUEVOS
                for(Integer idautorporinsertar : autores){
                    Tesis tesisporregistrar = new Tesis();
                    tesisporregistrar.setIdTesis(idTesis);
                    Autor autorporregistrar = new Autor();
                    autorporregistrar.setIdAutor(idautorporinsertar);

                    tesisautorDAO.insertar(tesisporregistrar, autorporregistrar);
                }
            }
            
            if(temas!=null){
                //PARA LOS AUTORES ELIMINAMOS LOS DE LA TABLA INTERMEDIA
                ArrayList<Tema> temassinmodificar = temaBO.listarTodos_TemasDeTesisPorID(idTesis);
                for(Tema tema:temassinmodificar){
                    Tesis tesisporeliminar = new Tesis();
                    tesisporeliminar.setIdTesis(idTesis);
                    Tema temaporeliminar = new Tema();
                    temaporeliminar.setIdTema(tema.getIdTema());
                    tesistemaDAO.eliminar(tesisporeliminar,temaporeliminar);
                }
                //VUELVO A INSERTAR CON AUTORES NUEVOS
                for(Integer idtemaporinsertar : temas){
                    Tesis tesisporregistrar = new Tesis();
                    tesisporregistrar.setIdTesis(idTesis);
                    Tema temaporregistrar = new Tema();
                    temaporregistrar.setIdTema(idtemaporinsertar);
                    tesistemaDAO.insertar(tesisporregistrar, temaporregistrar);
                }
            }
            
            if(cantDisponibles + cantCopiasPorInsertar != copiasDisponibles){
                Integer ver = this.modificar(idTesis, titulo, fechaPublicacion, descripcion, cantDisponibles+cantCopiasPorInsertar, hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada, gradoAcademico);
                if(ver==1) return idTesis;
                else return -1;
            }
            else
                return idTesis;
        }else{
            return -1;//FALLO AL MODIFICAR TESIS;
        }  
    }
    
    public ArrayList<Tesis> listarTodos_TesisDeAutorPorID(Integer idAutor){
        Autor autor = new Autor();
        autor.setIdAutor(idAutor);
       return this.tesisautorDAO.listarTodos_TesisDeAutor(null,autor,null,2);
    }
     
    public ArrayList<Tesis> listarTodos_TesisDeAutor(String titulo, String fechaPublicacion,
                                                     String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual,
                                                     Integer idBibliotecaAsociada, String gradoAcademico, Integer idAutor,
                                                     String nombreCompleto) {
        Date d_fechaPublicacion = vkf.toValidDate(fechaPublicacion);
        Boolean b_hayFormatoFisico = vkf.toValidBoolean(hayFormatoFisico);
        Boolean b_hayFormatoVirtual = vkf.toValidBoolean(hayFormatoVirtual);
        Biblioteca bibliotecaAsociada = new Biblioteca();
        bibliotecaAsociada.setIdBiblioteca(idBibliotecaAsociada);
        TipoGrado tg_gradoAcademico = vkf.toValidEnum(gradoAcademico, TipoGrado.class);
        Tesis tesis = new Tesis(null, titulo, d_fechaPublicacion, descripcion,null,
                                b_hayFormatoFisico,b_hayFormatoVirtual, null, null,
                                bibliotecaAsociada,tg_gradoAcademico);
        Autor autor = new Autor(idAutor,nombreCompleto);
       return this.tesisautorDAO.listarTodos_TesisDeAutor(tesis,autor,null,2);
    }
    
    public ArrayList<Tesis> listarTodos_TesisDeTemaPorID(Integer idTema){
        Tema tema = new Tema();
        tema.setIdTema(idTema);
        return this.tesistemaDAO.listarTodos_TesisDeTema(null, tema,null,2);
    }
    
    public ArrayList<Tesis> listarTodos_TesisDeTema(String ts_titulo, String fechaPublicacion,
                                                    String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual,
                                                    Integer idBibliotecaAsociada, String gradoAcademico, Integer idTema,
                                                    String tm_titulo) {
        Date d_fechaPublicacion = vkf.toValidDate(fechaPublicacion);
        Boolean b_hayFormatoFisico = vkf.toValidBoolean(hayFormatoFisico);
        Boolean b_hayFormatoVirtual = vkf.toValidBoolean(hayFormatoVirtual);
        Biblioteca bibliotecaAsociada = new Biblioteca();
        bibliotecaAsociada.setIdBiblioteca(idBibliotecaAsociada);
        TipoGrado tg_gradoAcademico = vkf.toValidEnum(gradoAcademico, TipoGrado.class);
        Tesis tesis = new Tesis(null,ts_titulo, d_fechaPublicacion, descripcion,null,
                                b_hayFormatoFisico,b_hayFormatoVirtual, null, null,
                                bibliotecaAsociada,tg_gradoAcademico);
        Tema tema = new Tema(idTema,tm_titulo);
        return this.tesistemaDAO.listarTodos_TesisDeTema(tesis, tema,null,2);
    }
}
