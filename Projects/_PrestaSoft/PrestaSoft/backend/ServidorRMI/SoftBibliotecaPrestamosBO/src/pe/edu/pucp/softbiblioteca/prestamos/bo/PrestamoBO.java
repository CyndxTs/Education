
/* [/]
 >> Project:    SoftBibliotecaPrestamosBO
 >> File:       PrestamoBO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.prestamos.bo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softbiblioteca.localidad.dao.BibliotecaDAO;
import pe.edu.pucp.softbiblioteca.localidad.dao.UniversidadDAO;
import pe.edu.pucp.softbiblioteca.localidad.daoImpl.BibliotecaDAOImpl;
import pe.edu.pucp.softbiblioteca.localidad.daoImpl.UniversidadDAOImpl;
import pe.edu.pucp.softbiblioteca.politicas.model.PoliticaPrestamo;
import pe.edu.pucp.softbiblioteca.prestamos.dao.PenalizacionDAO;
import pe.edu.pucp.softbiblioteca.prestamos.dao.PrestamoDAO;
import pe.edu.pucp.softbiblioteca.prestamos.daoImpl.PenalizacionDAOImpl;
import pe.edu.pucp.softbiblioteca.prestamos.daoImpl.PrestamoDAOImpl;
import pe.edu.pucp.softbiblioteca.prestamos.model.Penalizacion;
import pe.edu.pucp.softbiblioteca.prestamos.model.Prestamo;
import pe.edu.pucp.softbiblioteca.prestamos.model.TipoEstadoPrestamo;
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
import pe.edu.pucp.softbiblioteca.usuarios.dao.UsuarioDAO;
import pe.edu.pucp.softbiblioteca.usuarios.daoImpl.UsuarioDAOImpl;
import pe.edu.pucp.softbiblioteca.usuarios.model.TipoUsuario;
import pe.edu.pucp.softbiblioteca.usuarios.model.Usuario;
import pe.edu.pucp.softbiblioteca.util.VKF_Formatter;
import pe.edu.pucp.sotbiblioteca.politicas.dao.PoliticaDAO;
import pe.edu.pucp.sotbiblioteca.politicas.dao.PoliticaPrestamoDAO;
import pe.edu.pucp.sotbiblioteca.politicas.daoimpl.PoliticaDAOImpl;
import pe.edu.pucp.sotbiblioteca.politicas.daoimpl.PoliticaPrestamoDAOImpl;

public class PrestamoBO {
    private PrestamoDAO prestamoDAO;
    private UsuarioDAO usuarioDAO;
    private PoliticaDAO politicaDAO;
    private PoliticaPrestamoDAO politicaprestamoDAO;
    private UniversidadDAO universidadDAO;
    private BibliotecaDAO bibliotecaDAO;
    private CopiaEjemplarDAO copiaejemplarDAO;
    private VKF_Formatter vkf;
    private ReservaDAO reservaDAO;
    private PenalizacionDAO penalizacionDAO;
    
    private LibroDAO libroDAO;
    private TesisDAO tesisDAO;
    
    
    public PrestamoBO(){
        prestamoDAO = new PrestamoDAOImpl();
        usuarioDAO=new UsuarioDAOImpl();
        politicaDAO = new PoliticaDAOImpl();
        politicaprestamoDAO = new PoliticaPrestamoDAOImpl();
        universidadDAO = new UniversidadDAOImpl();
        bibliotecaDAO = new BibliotecaDAOImpl();
        copiaejemplarDAO = new CopiaEjemplarDAOImpl();
        this.vkf = new VKF_Formatter();
        reservaDAO = new ReservaDAOImpl();
        penalizacionDAO = new PenalizacionDAOImpl();
       
        this.libroDAO = new LibroDAOImpl();
        this.tesisDAO = new TesisDAOImpl();
    }
    
    public Integer insertar(String instantePrestamo,String fechaDevolucionEsperada, String fechaDevolucionReal,
                            String tipoEstado,Integer huboReservaPrevia,Integer idEjemplarAsociado,
                            Integer idUsuarioAsociado){
        LocalDateTime ldt_instantePrestamo = vkf.toValidLocalDateTime(instantePrestamo);
        Date d_fechaDevolucionEsperada = vkf.toValidDate(fechaDevolucionEsperada);
        Date d_fechaDevolucionReal = vkf.toValidDate(fechaDevolucionReal);
        TipoEstadoPrestamo tep_tipoEstado = vkf.toValidEnum(tipoEstado,TipoEstadoPrestamo.class);
        Boolean b_huboReservaPrevia = vkf.toValidBoolean(huboReservaPrevia);
        CopiaEjemplar ejemplarAsociado = new CopiaEjemplar();
        ejemplarAsociado.setIdCopiaEjemplar(idEjemplarAsociado);
        Usuario usuarioAsociado = new Usuario();
        usuarioAsociado.setIdUsuario(idUsuarioAsociado);
        Prestamo prestamo = new Prestamo(null,ldt_instantePrestamo,d_fechaDevolucionEsperada,d_fechaDevolucionReal,
                                         ejemplarAsociado,tep_tipoEstado,b_huboReservaPrevia,
                                         usuarioAsociado);
        return prestamoDAO.insertar(prestamo);
    }
    
    public Integer modificar(Integer idPrestamo,String instantePrestamo,String fechaDevolucionEsperada, String fechaDevolucionReal,
                            String tipoEstado,Integer huboReservaPrevia,Integer idEjemplarAsociado,
                            Integer idUsuarioAsociado){
        LocalDateTime ldt_instantePrestamo = vkf.toValidLocalDateTime(instantePrestamo);
        Date d_fechaDevolucionEsperada = vkf.toValidDate(fechaDevolucionEsperada);
        Date d_fechaDevolucionReal = vkf.toValidDate(fechaDevolucionReal);
        TipoEstadoPrestamo tep_tipoEstado = vkf.toValidEnum(tipoEstado,TipoEstadoPrestamo.class);
        Boolean b_huboReservaPrevia = vkf.toValidBoolean(huboReservaPrevia);
        CopiaEjemplar ejemplarAsociado = new CopiaEjemplar();
        ejemplarAsociado.setIdCopiaEjemplar(idEjemplarAsociado);
        Usuario usuarioAsociado = new Usuario();
        usuarioAsociado.setIdUsuario(idUsuarioAsociado);
        Prestamo prestamo = new Prestamo(idPrestamo,ldt_instantePrestamo,d_fechaDevolucionEsperada,d_fechaDevolucionReal,
                                         ejemplarAsociado,tep_tipoEstado,b_huboReservaPrevia,
                                         usuarioAsociado);
        return prestamoDAO.modificar(prestamo);
    }
    
    public Integer eliminar(Integer idPrestamo){
        Prestamo prestamo = new Prestamo();
        prestamo.setIdPrestamo(idPrestamo);
        return prestamoDAO.eliminar(prestamo);
    }
    
    public ArrayList<Prestamo> listarTodos(String instantePrestamo,String fechaDevolucionEsperada, String fechaDevolucionReal,String tipoEstado,
                                           Integer huboReservaPrevia,Integer idEjemplarAsociado,Integer idUsuarioAsociado, Integer limite) {
        LocalDateTime ldt_instantePrestamo = vkf.toValidLocalDateTime(instantePrestamo);
        Date d_fechaDevolucionEsperada = vkf.toValidDate(fechaDevolucionEsperada);
        Date d_fechaDevolucionReal = vkf.toValidDate(fechaDevolucionReal);
        TipoEstadoPrestamo tep_tipoEstado = vkf.toValidEnum(tipoEstado,TipoEstadoPrestamo.class);
        Boolean b_huboReservaPrevia = vkf.toValidBoolean(huboReservaPrevia);
        CopiaEjemplar ejemplarAsociado = new CopiaEjemplar();
        ejemplarAsociado.setIdCopiaEjemplar(idEjemplarAsociado);
        Usuario usuarioAsociado = new Usuario();
        usuarioAsociado.setIdUsuario(idUsuarioAsociado);
        Prestamo prestamo = new Prestamo(null,ldt_instantePrestamo,d_fechaDevolucionEsperada,d_fechaDevolucionReal,
                                         ejemplarAsociado,tep_tipoEstado,b_huboReservaPrevia,
                                         usuarioAsociado);
        return prestamoDAO.listarTodos(prestamo, limite, 1); //!!
    }
    
    public Prestamo obtenerPorId(Integer idPrestamo){
        return prestamoDAO.obtenerPorId(idPrestamo);
    }
    
    //--------------------------------------Desde aqui están en el ws--------------------------------------
       
    public ArrayList<Prestamo> mostrarPrestamosEnCurso(Integer idUsuario, Integer limite){
        Usuario usu = new Usuario();
        usu.setIdUsuario(idUsuario);
        Prestamo prestamo=new Prestamo();
        prestamo.setUsuarioAsociado(usu);
        prestamo.setTipoEstado(TipoEstadoPrestamo.PORDEVOLVER);
        ArrayList<Prestamo> prestamosUsuario = prestamoDAO.listarTodos(prestamo,limite,3); //HASTA LA FOTO DE LA PUBLICACIÓN!
        for(Prestamo prestamoPorIntanciarPublicaicon:prestamosUsuario){
            CopiaEjemplar copiaIntanciada = this.copiaejemplarDAO.obtenerPorId(prestamoPorIntanciarPublicaicon.getEjemplarAsociado().getIdCopiaEjemplar());
            prestamoPorIntanciarPublicaicon.setEjemplarAsociado(copiaIntanciada);
        }
        
        
        return prestamosUsuario;
    }
    
    public ArrayList<Prestamo> mostrarUltimosPrestamos(Integer limite){
        Prestamo prestamo = new Prestamo();
//        LocalDateTime fechaActual = LocalDateTime.now(); // Descomentar cuando hayan prestamos recientes de verdad en los dml
//        LocalDateTime fechaModificada = fechaActual.minusDays(7); //resta 7 días
//        prestamo.setInstantePrestamo(fechaModificada);
        ArrayList<Prestamo>prestamos = prestamoDAO.listarTodos(prestamo,limite,3); //USUARIO Y PUBLICACIÓN!
        return prestamos;
    }
    
    public Integer solicitarAmpliacion(Integer idPrestamo){
        Prestamo prestamo = this.prestamoDAO.obtenerPorId(idPrestamo);        
        CopiaEjemplar copia = prestamo.getEjemplarAsociado();
        PoliticaPrestamo politicaprestamo = copia.getPublicacionAsociada().getBibliotecaAsociada().getUniversidadAsociada().getPoliticaRegular().getPoliticasPorPrestamo();
        
        ArrayList<Prestamo> prestamosAtrasados = this.listarTodos(null, null, null,TipoEstadoPrestamo.ATRASADO.toString(), -1,null,prestamo.getUsuarioAsociado().getIdUsuario(),null);
        ArrayList<Prestamo> prestamosPerdidos = this.listarTodos(null, null, null,TipoEstadoPrestamo.CANCELADOPORPERDIDA.toString(), -1,null,prestamo.getUsuarioAsociado().getIdUsuario(),null);
        ArrayList<Prestamo> prestamosPorDevolver = this.listarTodos(null, null, null,TipoEstadoPrestamo.PORDEVOLVER.toString(), -1,null,prestamo.getUsuarioAsociado().getIdUsuario(),null);
        Integer cantPrestamosAtrasados=0,cantPrestamosPerdidos=0,cantPrestamosPorDevolver=0;
        
        if(!prestamosAtrasados.isEmpty()){
            cantPrestamosAtrasados = prestamosAtrasados.size();
        }
        if(!prestamosPerdidos.isEmpty()){
            cantPrestamosPerdidos = prestamosPerdidos.size();
        }
        if(!prestamosPorDevolver.isEmpty()){
            cantPrestamosPorDevolver = prestamosPorDevolver.size();
        }
        Integer cantDiasPrestamoRegular = politicaprestamo.getCantDiasPrestamoRegular();
        
        //COMPARACION DE FECHAS
        Date d_fechaDevolucionEsperada = prestamo.getFechaDevolucionEsperada();
        
        if (prestamo.getFechaDevolucionEsperada() instanceof java.sql.Date) {
            d_fechaDevolucionEsperada = new Date(prestamo.getFechaDevolucionEsperada().getTime()); // Convertir a java.util.Date
        }
        
        
        LocalDate localFechaDevolucionEsperada = d_fechaDevolucionEsperada.toInstant()
                .atZone(ZoneId.systemDefault()) // Zona horaria del sistema
                .toLocalDate();
        
        
        LocalDate localFechaInstantePrestamo = prestamo.getInstantePrestamo().toLocalDate();
        
        Integer cantDiasDePrestamo = (int)ChronoUnit.DAYS.between(localFechaInstantePrestamo, localFechaDevolucionEsperada);
        
        cantDiasDePrestamo = cantDiasDePrestamo - cantDiasPrestamoRegular;
        //SI NO TUVO NINGUNA AMPLIACION SERÁ 0
        Integer cantDeAmpliaciones = 0;
        Integer cantDiasAmpliacionRegular = politicaprestamo.getCantDiasDeAmpliacionRegular();
        //SI CANTIDAD DE DIAS ES MAYOR A CERO ENTONCES TUVO AMPLIACIONES
        if(cantDiasDePrestamo>0 && cantDiasAmpliacionRegular>0){
            cantDeAmpliaciones = cantDiasDePrestamo / cantDiasAmpliacionRegular;
        }
        
        Boolean cumpleMaxAmpliaciones = false;
        
        if(politicaprestamo.getCantMaxAmpliacionesPermitidasPorPrestamo()>cantDeAmpliaciones){
            cumpleMaxAmpliaciones=true;
        }
        
        if(politicaprestamo.getCantMaxAtrasosPorCiclo()>=cantPrestamosAtrasados &&
                politicaprestamo.getCantMaxCopiasPorDevolverPorUsuario()>=cantPrestamosPorDevolver &&
                politicaprestamo.getCantMaxPerdidasPorCiclo()>=cantPrestamosPerdidos &&
                cumpleMaxAmpliaciones){
            
            
            if (prestamo.getFechaDevolucionEsperada() instanceof java.sql.Date) {
                d_fechaDevolucionEsperada = new Date(prestamo.getFechaDevolucionEsperada().getTime()); // Convertir a java.util.Date
            }
            
            //CONVERTIMOS FECHAS A LOCAL DATE
            LocalDate localDate = d_fechaDevolucionEsperada.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

            LocalDate ld_fechaDevolucionEsperadaActualizada = localDate.plusDays(politicaprestamo.getCantDiasDeAmpliacionRegular());
            
            Date d_fechaDevolucionEsperadaActualizada = Date.from(ld_fechaDevolucionEsperadaActualizada.atStartOfDay(ZoneId.systemDefault()).toInstant());
                 
            //ACTULIZO PRESTAMO
            prestamo.setFechaDevolucionEsperada(d_fechaDevolucionEsperadaActualizada);
            
            return prestamoDAO.modificar(prestamo);
        }else{
            if(politicaprestamo.getCantMaxAtrasosPorCiclo()<cantPrestamosAtrasados){
                return -1;//EXCEDE MAX ATRASADOS;
            }else if(politicaprestamo.getCantMaxCopiasPorDevolverPorUsuario()<cantPrestamosPorDevolver){
                return -2;//EXCEDE MAX POR DEVOLVER;
            }else if(politicaprestamo.getCantMaxPerdidasPorCiclo()<cantPrestamosPerdidos){
                return -3;//EXCEDE MAX PERDIDAS;
            }else{
                return -4;//EXCEDE MAX AMPLIACIONES POR EJEMPLAR;
            }
        }
    }
    
    public Integer confirmarAmpliacion(Integer idPrestamo){
        Prestamo prestamo = prestamoDAO.obtenerPorId(idPrestamo);

        // Obtener la fecha de devolución esperada
        java.sql.Date d_fechaDevolucionEsperada = (java.sql.Date) prestamo.getFechaDevolucionEsperada();

        // Convertir a LocalDate directamente sin usar Instant
        LocalDate localDate = d_fechaDevolucionEsperada.toLocalDate();

        // Sumar los días de ampliación
        LocalDate ld_fechaDevolucionEsperadaActualizada = localDate.plusDays(
            prestamo.getEjemplarAsociado()
                   .getPublicacionAsociada()
                   .getBibliotecaAsociada()
                   .getUniversidadAsociada()
                   .getPoliticaRegular()
                   .getPoliticasPorPrestamo()
                   .getCantDiasDeAmpliacionRegular()
        );

        // Convertir de vuelta a java.sql.Date
        java.sql.Date d_fechaDevolucionEsperadaActualizada = java.sql.Date.valueOf(ld_fechaDevolucionEsperadaActualizada);

        // Actualizar la fecha en el objeto préstamo
        prestamo.setFechaDevolucionEsperada(d_fechaDevolucionEsperadaActualizada);

        // Guardar los cambios
        return prestamoDAO.modificar(prestamo);
    }
    public ArrayList<String> consultarCalificacionPrestamo(Integer idUsuario, Integer idCopiaEjemplar  ) {
        ArrayList<String>motivos = new ArrayList<String>();
        Usuario usuario = usuarioDAO.obtenerPorId(idUsuario);
        CopiaEjemplar copia = copiaejemplarDAO.obtenerPorId(idCopiaEjemplar);
        int cantMaxPrestamosEnUniversidad = copia.getPublicacionAsociada().getBibliotecaAsociada().getUniversidadAsociada().getPoliticaRegular().getPoliticasPorPrestamo().getCantMaxCopiasPorDevolverPorUsuario();
        int idDeLaUnivesidad = copia.getPublicacionAsociada().getBibliotecaAsociada().getUniversidadAsociada().getIdUniversidad();
        Prestamo prestamo = new Prestamo();
        Usuario usu = new Usuario();
        usu.setIdUsuario(idUsuario);
        prestamo.setUsuarioAsociado(usu);
        ArrayList<Prestamo> prestamosDelUsuario = prestamoDAO.listarTodos(prestamo, null, 5); //null = instanciara hasta el fondo  //XDDDDDDDDDDDDDDDDDDD
        int contadorDePrestamosPendientesEnLaUniversidadDeLaPublicacion = 0;
        if(usuario.getTipoUsuario().equals(TipoUsuario.SANCIONADO) ){
            for(Prestamo p: prestamosDelUsuario){
                Penalizacion pena = new Penalizacion();
                pena.setPrestamoAsociado(p);
                pena.setEstaActivo(Boolean.TRUE);
                ArrayList<Penalizacion>penalizacionesDelMiembroActivas = penalizacionDAO.listarTodos(pena , null, 2);
                for(Penalizacion penalizacion: penalizacionesDelMiembroActivas){
                    String descripcion = penalizacion.getDescripcion();
                    if (!motivos.contains(descripcion)) {
                        motivos.add(descripcion);
                    }
                }
                //Prestamo prestamito = prestamoDAO.obtenerPorId(p.getIdPrestamo());//ya no es necesario
                if( p.getEjemplarAsociado().getPublicacionAsociada().getBibliotecaAsociada().getUniversidadAsociada().getIdUniversidad() == idDeLaUnivesidad
                        && ( p.getTipoEstado().toString().equals("PORDEVOLVER") || p.getTipoEstado().toString().equals("ATRASADO") )
                        ){
                    contadorDePrestamosPendientesEnLaUniversidadDeLaPublicacion++;
                }
            }
        }
        if(contadorDePrestamosPendientesEnLaUniversidadDeLaPublicacion > cantMaxPrestamosEnUniversidad ){
            motivos.add("Máximo de préstamos en curso según política de universidad de publicación");
        }

        return motivos; //SI DEVUELVE VACIO ES PORQUE ESTÁ APTO
    }

       public Integer registrarPrestamo(Integer idUsuario, Integer idCopiaEjemplar){
        CopiaEjemplar copia = this.copiaejemplarDAO.obtenerPorId(idCopiaEjemplar);
        int plazo = copia.getPublicacionAsociada().getBibliotecaAsociada().getUniversidadAsociada().getPoliticaRegular().getPoliticasPorPrestamo().getCantDiasPrestamoRegular();
        LocalDateTime instanteActual = LocalDateTime.now();
        LocalDateTime instanteMaximo = instanteActual.plusDays(plazo);
        Date fechaMaxima = Date.from(instanteMaximo.atZone(ZoneId.systemDefault()).toInstant());
        
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        
        Reserva reserva = new Reserva();
        reserva.setEjemplarAsociado(copia);
        reserva.setUsuarioAsociado(usuario);
        reserva.setTipoEstado(TipoEstadoReserva.AGENDADA);
        
        
        ArrayList<Reserva> reservas = this.reservaDAO.listarTodos(reserva, null, 2);
        Boolean huboReservaPrevia = true;
        if(reservas.isEmpty())huboReservaPrevia = false;
        else{            
            Reserva reservaDelPrestamo = reservaDAO.obtenerPorId(reservas.get(0).getIdReserva());
            reservaDelPrestamo.setTipoEstado(TipoEstadoReserva.RECOGIDA);
            this.reservaDAO.modificar(reservaDelPrestamo);
        }
        Prestamo prestamoRegistro = new Prestamo(null,instanteActual,fechaMaxima,null,copia,TipoEstadoPrestamo.PORDEVOLVER,huboReservaPrevia,usuario);
        
        copia.setEstado(EstadoEjemplar.NODISPONIBLE);
        this.copiaejemplarDAO.modificar(copia); //!!!!!!!!!!!!
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
        
        return this.prestamoDAO.insertar(prestamoRegistro);
    }        

    public Prestamo consultarPrestamoQueSePrestoCopiaEjemplar(Integer idCopiaEjemplar) {
        Prestamo prestamo = new Prestamo();
        CopiaEjemplar ejemplarAsociado = new CopiaEjemplar();
        ejemplarAsociado.setIdCopiaEjemplar(idCopiaEjemplar);
        prestamo.setEjemplarAsociado(ejemplarAsociado);
        ArrayList<Prestamo> prestamos = prestamoDAO.listarTodos(prestamo, null, 3); //me da la publicación :D
        for (Prestamo prestamo1 : prestamos) {
            //pueden salir préstamos antiguos que ya no son en curso
            if(prestamo1.getTipoEstado()==TipoEstadoPrestamo.ATRASADO || prestamo1.getTipoEstado()==TipoEstadoPrestamo.PORDEVOLVER ){ //las canceladas por perdida tienen otro manejo
                return prestamo1;
            }
        }   
        return null;
    }
    //----------------------------------------
    //alguien pruebe la función! [PROBADA]!!!!
    public int confirmarDevolucion(Integer idPrestamo,Integer  idCopiaEjemplar, Integer estaEnMalEstado) {

        Prestamo p = prestamoDAO.obtenerPorId(idPrestamo);
        p.setFechaDevolucionReal(new Date());
        p.setTipoEstado(TipoEstadoPrestamo.DEVUELTO);
        CopiaEjemplar c = copiaejemplarDAO.obtenerPorId(idCopiaEjemplar);
        if(estaEnMalEstado<=0){
                    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            if(c.getPublicacionAsociada() instanceof Tesis){
                Tesis tesisAsociada = (Tesis) c.getPublicacionAsociada();
                tesisAsociada.setCopiasDisponibles( tesisAsociada.getCopiasDisponibles() +1 );
                tesisDAO.modificar(tesisAsociada);
            }else{
                Libro libro = (Libro) c.getPublicacionAsociada();
                libro.setCopiasDisponibles( libro.getCopiasDisponibles() +1 );
                libroDAO.modificar(libro);
            } //se aumenta a la publicación su copia optima que ahora es parte de las disponibles nuevamente
            c.setEstado(EstadoEjemplar.OPTIMO);
        }
        else{
            //ESTADO DE USUARIO Y PENALIZACION A PRESTAMO
            Usuario usu = p.getUsuarioAsociado();
            usu.setTipoUsuario(TipoUsuario.SANCIONADO);
            UsuarioDAO usuDAO = new UsuarioDAOImpl();
            usuDAO.modificar(usu);
            
            Penalizacion penalizacion = new Penalizacion();
            //ahora que lo pienso... Tal vez el administrador pueda poner como SANCIONADO al usuario también... Aunque ya puede ponerlo ccomo inactivo, hay cosas que ocurren fuera del sistema
            penalizacion.setDescripcion("Mal estado de ejemplar entregado");
            penalizacion.setEstaActivo(true);
            penalizacion.setFechaImposicion(new Date());
            //recordar que la penalizacion por atraso y pérdida se pone automáticamente en la BD
            penalizacion.setMonto(c.getPublicacionAsociada().getBibliotecaAsociada().getUniversidadAsociada().getPoliticaRegular().getPoliticasPorPrestamo().getCargoPorMalEstado() );
            penalizacion.setPrestamoAsociado(p);
            penalizacionDAO.insertar(penalizacion); //o usar BO????
            
            c.setEstado(EstadoEjemplar.DANIADO); //la publicación seguirá sin su copia disponible, no pasa nada con el número de copias disponibles
        }
        
        prestamoDAO.modificar(p);
        return copiaejemplarDAO.modificar(c);
    }
}
