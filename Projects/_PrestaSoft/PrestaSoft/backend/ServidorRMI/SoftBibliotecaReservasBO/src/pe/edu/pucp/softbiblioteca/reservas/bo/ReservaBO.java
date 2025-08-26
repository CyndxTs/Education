

/* [/]
 >> Project:    SoftBibliotecaReservasBO
 >> File:       ReservaBO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.reservas.bo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.localidad.dao.BibliotecaDAO;
import pe.edu.pucp.softbiblioteca.localidad.dao.UniversidadDAO;
import pe.edu.pucp.softbiblioteca.localidad.daoImpl.BibliotecaDAOImpl;
import pe.edu.pucp.softbiblioteca.localidad.daoImpl.UniversidadDAOImpl;
import pe.edu.pucp.softbiblioteca.localidad.model.Biblioteca;
import pe.edu.pucp.softbiblioteca.localidad.model.Universidad;
import pe.edu.pucp.softbiblioteca.politicas.model.Politica;
import pe.edu.pucp.softbiblioteca.politicas.model.PoliticaReserva;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.CopiaEjemplarDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.LibroDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.TesisDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.CopiaEjemplarDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.LibroDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.TesisDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.model.CopiaEjemplar;
import pe.edu.pucp.softbiblioteca.publicaciones.model.EstadoEjemplar;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Libro;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Publicacion;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tesis;
import pe.edu.pucp.softbiblioteca.reservas.dao.ReservaDAO;
import pe.edu.pucp.softbiblioteca.reservas.daoImpl.ReservaDAOImpl;
import pe.edu.pucp.softbiblioteca.reservas.model.Reserva;
import pe.edu.pucp.softbiblioteca.reservas.model.TipoEstadoReserva;
import pe.edu.pucp.softbiblioteca.usuarios.model.Usuario;
import pe.edu.pucp.softbiblioteca.util.VKF_Formatter;
import pe.edu.pucp.sotbiblioteca.politicas.dao.PoliticaDAO;
import pe.edu.pucp.sotbiblioteca.politicas.dao.PoliticaReservaDAO;
import pe.edu.pucp.sotbiblioteca.politicas.daoimpl.PoliticaDAOImpl;
import pe.edu.pucp.sotbiblioteca.politicas.daoimpl.PoliticaReservaDAOImpl;


public class ReservaBO{
    private ReservaDAO reservaDAO;
    private VKF_Formatter vkf;
    private LibroDAO libroDAO;
    private TesisDAO tesisDAO;
    private CopiaEjemplarDAO copiaejemplarDAO;
    private BibliotecaDAO bibliotecaDAO;
    private UniversidadDAO universidadDAO;
    private PoliticaDAO politicaDAO;
    private PoliticaReservaDAO politicaReservaDAO;
    
    public ReservaBO(){
        this.reservaDAO = new ReservaDAOImpl();
        this.libroDAO = new LibroDAOImpl();
        this.tesisDAO = new TesisDAOImpl();
        this.copiaejemplarDAO = new CopiaEjemplarDAOImpl();
        this.bibliotecaDAO = new BibliotecaDAOImpl();
        this.politicaDAO = new PoliticaDAOImpl();
        this.politicaReservaDAO = new PoliticaReservaDAOImpl();
        this.universidadDAO = new UniversidadDAOImpl();
        this.vkf = new VKF_Formatter();
    }
    
    public Integer insertar(String instanteReserva,String instanteRecojo,String tipoEstado, 
                            Integer idEjemplarAsociado,Integer idUsuarioAsociado){
        LocalDateTime ldt_instanteReserva = vkf.toValidLocalDateTime(instanteReserva);
        LocalDateTime ldt_instanteRecojo = vkf.toValidLocalDateTime(instanteRecojo);
        TipoEstadoReserva ter_tipoEstado = vkf.toValidEnum(tipoEstado,TipoEstadoReserva.class);
        CopiaEjemplar ejemplarAsociado = new CopiaEjemplar();
        ejemplarAsociado.setIdCopiaEjemplar(idEjemplarAsociado);
        Usuario usuarioAsociado = new Usuario();
        usuarioAsociado.setIdUsuario(idUsuarioAsociado);
        Reserva reserva = new Reserva(null,ldt_instanteReserva,ldt_instanteRecojo,ter_tipoEstado,
                                      ejemplarAsociado,usuarioAsociado);
        return reservaDAO.insertar(reserva);
    }
    
    public Integer modificar(Integer idReserva,String instanteReserva,String instanteRecojo,String tipoEstado, 
                            Integer idEjemplarAsociado,Integer idUsuarioAsociado){
        LocalDateTime ldt_instanteReserva = vkf.toValidLocalDateTime(instanteReserva);
        LocalDateTime ldt_instanteRecojo = vkf.toValidLocalDateTime(instanteRecojo);
        TipoEstadoReserva ter_tipoEstado = vkf.toValidEnum(tipoEstado,TipoEstadoReserva.class);
        CopiaEjemplar ejemplarAsociado = new CopiaEjemplar();
        ejemplarAsociado.setIdCopiaEjemplar(idEjemplarAsociado);
        Usuario usuarioAsociado = new Usuario();
        usuarioAsociado.setIdUsuario(idUsuarioAsociado);
        Reserva reserva = new Reserva(idReserva,ldt_instanteReserva,ldt_instanteRecojo,ter_tipoEstado,
                                      ejemplarAsociado,usuarioAsociado);
        return reservaDAO.modificar(reserva);
    }
    
    public Integer eliminar(Integer idReserva){
        Reserva reserva = new Reserva();
        reserva.setIdReserva(idReserva);
        return reservaDAO.eliminar(reserva);
    }
    
    public ArrayList<Reserva> listarTodos(String instanteReserva,String instanteRecojo,
                                          String tipoEstado,Integer idEjemplarAsociado,
                                          Integer idUsuarioAsociado, Integer limite){
        LocalDateTime ldt_instanteReserva = vkf.toValidLocalDateTime(instanteReserva);
        LocalDateTime ldt_instanteRecojo = vkf.toValidLocalDateTime(instanteRecojo);
        TipoEstadoReserva ter_tipoEstado = vkf.toValidEnum(tipoEstado,TipoEstadoReserva.class);
        CopiaEjemplar ejemplarAsociado = new CopiaEjemplar();
        ejemplarAsociado.setIdCopiaEjemplar(idEjemplarAsociado);
        Usuario usuarioAsociado = new Usuario();
        usuarioAsociado.setIdUsuario(idUsuarioAsociado);
        Reserva reserva = new Reserva(null,ldt_instanteReserva,ldt_instanteRecojo,
                                      ter_tipoEstado,ejemplarAsociado,usuarioAsociado);
        return reservaDAO.listarTodos(reserva,limite,2);
    }
    
    public Reserva obtenerPorId(Integer idReserva){
        return reservaDAO.obtenerPorId(idReserva);
    }
    
    //---------------------desde aqui el ws-----------------------------------------
    
    public ArrayList<Reserva> mostrarReservasEnCurso(Integer idUsuario){
        Usuario usu= new Usuario();
        usu.setIdUsuario(idUsuario);
        Reserva reserva=new Reserva();
        reserva.setUsuarioAsociado(usu);
        reserva.setTipoEstado(TipoEstadoReserva.AGENDADA);
        ArrayList<Reserva> reservaUsuario=reservaDAO.listarTodos(reserva,null,4); //sería 3 pero quiero que sea hasta la biblioteca xddd
        //MEJORAR
        for(Reserva reservaPorInstancarPublicacion:reservaUsuario){
            CopiaEjemplar copiaEjemplarInstanciada = this.copiaejemplarDAO.obtenerPorId(reservaPorInstancarPublicacion.getEjemplarAsociado().getIdCopiaEjemplar());
            reservaPorInstancarPublicacion.setEjemplarAsociado(copiaEjemplarInstanciada);
        }   
        return reservaUsuario;
    }
    
    public Integer cancelarReserva(Integer idReserva){
        Reserva reserva=this.reservaDAO.obtenerPorId(idReserva);
        reserva.setTipoEstado(TipoEstadoReserva.CANCELADA);
        return this.reservaDAO.modificar(reserva);
    }
    //estoy cambiando desde aquí GIANO
    public Reserva solicitarReserva(Integer idUsuario, Integer idPublicacion,String tipoPublicacion){
        Reserva reservaHipotetica = new Reserva();
         Boolean validaCantReservasEnCurso = false,validaCantCopiasDisponible = false;
              
        //HALLAR CANTIDAD DE RESERVAS DE USUARIO
        ArrayList<Reserva> reservas = this.listarTodos(null, null, null, null, idUsuario, null);
        Integer cantReservasEnCurso = 0;
        for(Reserva reserva : reservas){
            if (reserva.getTipoEstado().equals(TipoEstadoReserva.AGENDADA) || reserva.getTipoEstado().equals(TipoEstadoReserva.RECOGIDA)){
                cantReservasEnCurso++;
            }
        }
        //BUSCO LAS POLITICAS DE LA PUBLICACION
        Publicacion publicacionAsociada;
        if(tipoPublicacion.equals("libro")){
            publicacionAsociada = libroDAO.obtenerPorId(idPublicacion);               
        }else{
            publicacionAsociada = tesisDAO.obtenerPorId(idPublicacion);   
        }
        publicacionAsociada.setIdPublicacion(idPublicacion);
        PoliticaReserva politicaReserva = publicacionAsociada.getBibliotecaAsociada().getUniversidadAsociada().getPoliticaRegular().getPoliticasPorReserva();
        
        if(politicaReserva.getCantMaxReservasAgendadasPorUsuario()>cantReservasEnCurso)validaCantReservasEnCurso=true;
        
        //OBTENEMOS COPIA EJEMPLAR
        
        CopiaEjemplar copia = new CopiaEjemplar();
        copia.setPublicacionAsociada(publicacionAsociada);
        ArrayList<CopiaEjemplar> copias = this.copiaejemplarDAO.listarTodos(copia,null,2);//LISTO SOLO COPIAS DE LA PUBLICACION
        Integer idEjemplarAsociado = null;
        if(!copias.isEmpty()){
            validaCantCopiasDisponible = true;
            for(CopiaEjemplar candidato: copias){
                if(candidato.getEstado().toString().equals("OPTIMO")){
                    idEjemplarAsociado = candidato.getIdCopiaEjemplar();
                    copia.setIdCopiaEjemplar(idEjemplarAsociado);
                    break;
                }
            }
        }
        //LÓGICA PARA OBTENER FECHA Y HORA MÁXIMA DE RECOJO PARA LA RESERVA
        int plazoEnHoras = politicaReserva.getCantMaxHorasDeRecojo();
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime fechaHoraCalculada = ahora.plusHours(plazoEnHoras);
        int minuto = fechaHoraCalculada.getMinute();
        if (minuto > 0 && minuto <= 30) {
            fechaHoraCalculada = fechaHoraCalculada.truncatedTo(ChronoUnit.HOURS).plusMinutes(30);
        } else if (minuto > 30) {
            fechaHoraCalculada = fechaHoraCalculada.truncatedTo(ChronoUnit.HOURS).plusHours(1);
        }
        // Ajustar al horario permitido (8 AM a 10 PM)
        int hora = fechaHoraCalculada.getHour();
        if (hora < 8) {
            fechaHoraCalculada = fechaHoraCalculada.withHour(8).truncatedTo(ChronoUnit.HOURS);
        } else if (hora > 22) {
            fechaHoraCalculada = fechaHoraCalculada.plusDays(1).withHour(8).truncatedTo(ChronoUnit.HOURS);
        }
        DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        //los tenemos!!

        if(validaCantCopiasDisponible && validaCantReservasEnCurso && idEjemplarAsociado!=null && idUsuario!=null){
            reservaHipotetica.setEjemplarAsociado(copia);
            reservaHipotetica.setInstanteRecojo(fechaHoraCalculada);
            reservaHipotetica.setStr_instanteRecojo(fechaHoraCalculada.format(formatoFechaHora));
        }
        return reservaHipotetica;
    }
    
    public Integer confirmarReserva(Integer idUsuario, Integer idEjemplarAsociado, String instanteRecojoMax){

        //SI SE CUMPLEN LAS VALIDACIONES INSERTA
        String formato = "yyyy-MM-dd HH:mm:ss";
        
        String instanteReserva = LocalDateTime.now().format(DateTimeFormatter.ofPattern(formato));
        instanteReserva = instanteReserva.trim();
        Integer resultado = 0;
        CopiaEjemplar copia = copiaejemplarDAO.obtenerPorId(idEjemplarAsociado);
        copia.setEstado(EstadoEjemplar.NODISPONIBLE);
        copiaejemplarDAO.modificar(copia);
        
        instanteRecojoMax = instanteRecojoMax.replace("/", "-") + ":00";
        System.out.println("Instante reserva: " + instanteReserva + " Instante Rec: " + instanteRecojoMax);
        
        
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if(copia.getPublicacionAsociada() instanceof Tesis){
            Tesis tesisAsociada = (Tesis) copia.getPublicacionAsociada();
            tesisAsociada.setCopiasDisponibles( tesisAsociada.getCopiasDisponibles() -1 );
            tesisDAO.modificar(tesisAsociada);
        }else{
            Libro libro = (Libro) copia.getPublicacionAsociada();
            libro.setCopiasDisponibles( libro.getCopiasDisponibles() -1 );
            libroDAO.modificar(libro);
        }
        
        
        
        resultado = this.insertar(instanteReserva, instanteRecojoMax.trim(),TipoEstadoReserva.AGENDADA.toString(),idEjemplarAsociado, idUsuario);
        
        return resultado;
    }
    
}
