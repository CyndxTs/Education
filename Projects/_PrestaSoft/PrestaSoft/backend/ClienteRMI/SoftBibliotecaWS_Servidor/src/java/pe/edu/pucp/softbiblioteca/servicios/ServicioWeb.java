
/* [/]
 >> Project:    SoftBibliotecaWS_Servidor
 >> File:       ServicioWeb.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.servicios;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Properties;
import pe.edu.pucp.softbiblioteca.politicas.model.Mora;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.AutorBO;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.BibliotecaBO;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.ConsorcioBO;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.CopiaEjemplarBO;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.EditorialBO;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.LibroBO;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.MoraBO;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.PenalizacionBO;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.PoliticaBO;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.PoliticaPrestamoBO;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.PoliticaReservaBO;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.PrestamoBO;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.PublicacionBO;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.ReservaBO;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.TemaBO;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.TesisBO;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.UniversidadBO;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.UsuarioBO;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softbiblioteca.localidad.model.Biblioteca;
import pe.edu.pucp.softbiblioteca.localidad.model.Consorcio;
import pe.edu.pucp.softbiblioteca.localidad.model.Universidad;
import pe.edu.pucp.softbiblioteca.politicas.model.Politica;
import pe.edu.pucp.softbiblioteca.politicas.model.PoliticaPrestamo;
import pe.edu.pucp.softbiblioteca.politicas.model.PoliticaReserva;
import pe.edu.pucp.softbiblioteca.prestamos.model.Penalizacion;
import pe.edu.pucp.softbiblioteca.prestamos.model.Prestamo;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Autor;
import pe.edu.pucp.softbiblioteca.publicaciones.model.CopiaEjemplar;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Editorial;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Libro;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Publicacion;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tema;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tesis;
import pe.edu.pucp.softbiblioteca.reservas.model.Reserva;
import pe.edu.pucp.softbiblioteca.usuarios.model.Usuario;

@WebService(serviceName = "ServicioWeb")
public class ServicioWeb {
   
    private static final String ARCHIVO_CONFIGURACION = "rmi.properties";
    private String ip;
    private Integer puerto;
    private AutorBO autorBO;
    private BibliotecaBO bibliotecaBO;
    private ConsorcioBO consorcioBO;
    private CopiaEjemplarBO copiaEjemplarBO;
    private EditorialBO editorialBO;
    private LibroBO libroBO;
    private MoraBO moraBO;
    private PenalizacionBO penalizacionBO;
    private PoliticaBO politicaBO;
    private PoliticaPrestamoBO politicaPrestamoBO;
    private PoliticaReservaBO politicaReservaBO;
    private PrestamoBO prestamoBO;
    private PublicacionBO publicacionBO;
    private ReservaBO reservaBO;
    private TemaBO temaBO;
    private TesisBO tesisBO;
    private UniversidadBO universidadBO;
    private UsuarioBO usuarioBO;
    
    public ServicioWeb() {
        try {
            cargar_configuracion_de_servidor();
            String nombreDelServidor = retornarNombreDelSevicio("autorBO");
            this.autorBO = (AutorBO) Naming.lookup(nombreDelServidor);
            
            nombreDelServidor = retornarNombreDelSevicio("bibliotecaBO");
            this.bibliotecaBO = (BibliotecaBO) Naming.lookup(nombreDelServidor);
            
            nombreDelServidor = retornarNombreDelSevicio("consorcioBO");
            this.consorcioBO = (ConsorcioBO) Naming.lookup(nombreDelServidor);
            
            nombreDelServidor = retornarNombreDelSevicio("copiaejemplarBO");
            this.copiaEjemplarBO = (CopiaEjemplarBO) Naming.lookup(nombreDelServidor);
            
            nombreDelServidor = retornarNombreDelSevicio("editorialBO");
            this.editorialBO = (EditorialBO) Naming.lookup(nombreDelServidor);
            
            nombreDelServidor = retornarNombreDelSevicio("libroBO");
            this.libroBO = (LibroBO) Naming.lookup(nombreDelServidor);
            
            nombreDelServidor = retornarNombreDelSevicio("moraBO");
            this.moraBO = (MoraBO) Naming.lookup(nombreDelServidor);
            
            nombreDelServidor = retornarNombreDelSevicio("penalizacionBO");
            this.penalizacionBO = (PenalizacionBO) Naming.lookup(nombreDelServidor);
            
            nombreDelServidor = retornarNombreDelSevicio("politicaBO");
            this.politicaBO = (PoliticaBO) Naming.lookup(nombreDelServidor);
            
            nombreDelServidor = retornarNombreDelSevicio("politicaprestamoBO");
            this.politicaPrestamoBO = (PoliticaPrestamoBO) Naming.lookup(nombreDelServidor);
            
            nombreDelServidor = retornarNombreDelSevicio("politicareservaBO");
            this.politicaReservaBO = (PoliticaReservaBO) Naming.lookup(nombreDelServidor);
            
            nombreDelServidor = retornarNombreDelSevicio("prestamoBO");
            this.prestamoBO = (PrestamoBO) Naming.lookup(nombreDelServidor);
            
            nombreDelServidor = retornarNombreDelSevicio("publicacionBO");
            this.publicacionBO = (PublicacionBO) Naming.lookup(nombreDelServidor);
            
            nombreDelServidor = retornarNombreDelSevicio("reservaBO");
            this.reservaBO = (ReservaBO) Naming.lookup(nombreDelServidor);
            
            nombreDelServidor = retornarNombreDelSevicio("temaBO");
            this.temaBO = (TemaBO) Naming.lookup(nombreDelServidor);
            
            nombreDelServidor = retornarNombreDelSevicio("tesisBO");
            this.tesisBO = (TesisBO) Naming.lookup(nombreDelServidor);
            
            nombreDelServidor = retornarNombreDelSevicio("universidadBO");
            this.universidadBO = (UniversidadBO) Naming.lookup(nombreDelServidor);
            
            nombreDelServidor = retornarNombreDelSevicio("usuarioBO");
            this.usuarioBO = (UsuarioBO) Naming.lookup(nombreDelServidor);
            
            
            
        }catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


                /*  - / > [ - PAQUETE - POLITICAS - ] < / -  */
    
    @WebMethod(operationName = "mora_insertar")
    public Integer mora_insertar(@WebParam(name = "monto") Float monto) {
        
        try {
            return moraBO.insertar(monto);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "mora_modificar")
    public Integer mora_modificar(@WebParam(name = "idMora") Integer idMora,
                                   @WebParam(name = "monto") Float monto) {
        try {
            return moraBO.modificar(idMora, monto);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "mora_eliminar")
    public Integer mora_eliminar(@WebParam(name = "idMora") Integer idMora) {
        try {
            return moraBO.eliminar(idMora);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "mora_listarTodos")
    public ArrayList<Mora> mora_listarTodos(Integer limite) {
        try {
            return moraBO.listarTodos(limite);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "mora_obtenerPorId")
    public Mora mora_obtenerPorId(@WebParam(name = "idMora") Integer idMora) {
        try {
            return moraBO.obtenerPorId(idMora);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "politicaprestamo_insertar")
    public Integer politicaprestamo_insertar(@WebParam(name = "idMoraAsociada") Integer idMoraAsociada,
                                              @WebParam(name = "cantDiasPrestamoRegular") Integer cantDiasPrestamoRegular,
                                              @WebParam(name = "cantMaxCopiasPorDevolverPorUsuario") Integer cantMaxCopiasPorDevolverPorUsuario,
                                              @WebParam(name = "cantDiasDeAmpliacionRegular") Integer cantDiasDeAmpliacionRegular,
                                              @WebParam(name = "cantMaxAmpliacionesPermitidasPorPrestamo") Integer cantMaxAmpliacionesPermitidasPorPrestamo,
                                              @WebParam(name = "cantDiasParaDarPorPerdidaUnaCopia") Integer cantDiasParaDarPorPerdidaUnaCopia,
                                              @WebParam(name = "cantDiasSinPrestamoPorAtraso") Integer cantDiasSinPrestamoPorAtraso,
                                              @WebParam(name = "cantMaxAtrasosPorCiclo") Integer cantMaxAtrasosPorCiclo,
                                              @WebParam(name = "cantDiasSinPrestamoPorMalEstado") Integer cantDiasSinPrestamoPorMalEstado,
                                              @WebParam(name = "cargoPorMalEstado") Float cargoPorMalEstado,
                                              @WebParam(name = "cantMaxMalEstadoPorCiclo") Integer cantMaxMalEstadoPorCiclo,
                                              @WebParam(name = "cantDiasSinPrestamoPorPerdido") Integer cantDiasSinPrestamoPorPerdido,
                                              @WebParam(name = "cargoPorPerdido") Float cargoPorPerdido,
                                              @WebParam(name = "cantMaxPerdidasPorCiclo") Integer cantMaxPerdidasPorCiclo) {
        try {
            return politicaPrestamoBO.insertar(idMoraAsociada, cantDiasPrestamoRegular, cantMaxCopiasPorDevolverPorUsuario,
                    cantDiasDeAmpliacionRegular, cantMaxAmpliacionesPermitidasPorPrestamo,
                    cantDiasParaDarPorPerdidaUnaCopia, cantDiasSinPrestamoPorAtraso, cantMaxAtrasosPorCiclo,
                    cantDiasSinPrestamoPorMalEstado, cargoPorMalEstado, cantMaxMalEstadoPorCiclo,
                    cantDiasSinPrestamoPorPerdido, cargoPorPerdido, cantMaxPerdidasPorCiclo);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "politicaprestamo_modificar")
    public Integer politicaprestamo_modificar(@WebParam(name = "idPoliticaPrestamo") Integer idPoliticaPrestamo,
                                               @WebParam(name = "idMoraAsociada") Integer idMoraAsociada,
                                               @WebParam(name = "cantDiasPrestamoRegular") Integer cantDiasPrestamoRegular,
                                               @WebParam(name = "cantMaxCopiasPorDevolverPorUsuario") Integer cantMaxCopiasPorDevolverPorUsuario,
                                               @WebParam(name = "cantDiasDeAmpliacionRegular") Integer cantDiasDeAmpliacionRegular,
                                               @WebParam(name = "cantMaxAmpliacionesPermitidasPorPrestamo") Integer cantMaxAmpliacionesPermitidasPorPrestamo,
                                               @WebParam(name = "cantDiasParaDarPorPerdidaUnaCopia") Integer cantDiasParaDarPorPerdidaUnaCopia,
                                               @WebParam(name = "cantDiasSinPrestamoPorAtraso") Integer cantDiasSinPrestamoPorAtraso,
                                               @WebParam(name = "cantMaxAtrasosPorCiclo") Integer cantMaxAtrasosPorCiclo,
                                               @WebParam(name = "cantDiasSinPrestamoPorMalEstado") Integer cantDiasSinPrestamoPorMalEstado,
                                               @WebParam(name = "cargoPorMalEstado") Float cargoPorMalEstado,
                                               @WebParam(name = "cantMaxMalEstadoPorCiclo") Integer cantMaxMalEstadoPorCiclo,
                                               @WebParam(name = "cantDiasSinPrestamoPorPerdido") Integer cantDiasSinPrestamoPorPerdido,
                                               @WebParam(name = "cargoPorPerdido") Float cargoPorPerdido,
                                               @WebParam(name = "cantMaxPerdidasPorCiclo") Integer cantMaxPerdidasPorCiclo) {
        try {
            return politicaPrestamoBO.modificar(idPoliticaPrestamo, idMoraAsociada, cantDiasPrestamoRegular, cantMaxCopiasPorDevolverPorUsuario,
                    cantDiasDeAmpliacionRegular, cantMaxAmpliacionesPermitidasPorPrestamo, cantDiasParaDarPorPerdidaUnaCopia,
                    cantDiasSinPrestamoPorAtraso, cantMaxAtrasosPorCiclo, cantDiasSinPrestamoPorMalEstado, cargoPorMalEstado,
                    cantMaxMalEstadoPorCiclo, cantDiasSinPrestamoPorPerdido, cargoPorPerdido, cantMaxPerdidasPorCiclo);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "politicaprestamo_eliminar")
    public Integer politicaprestamo_eliminar(@WebParam(name = "idPoliticaPrestamo") Integer idPoliticaPrestamo) {
        try {
            return politicaPrestamoBO.eliminar(idPoliticaPrestamo);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "politicaprestamo_listarTodos")
    public ArrayList<PoliticaPrestamo> politicaprestamo_listarTodos(@WebParam(name = "idMoraAsociada")Integer idMoraAsociada,
                                                                    @WebParam(name = "limite")Integer limite) {
        try {
            return politicaPrestamoBO.listarTodos(idMoraAsociada,limite);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "politicaprestamo_obtenerPorId")
    public PoliticaPrestamo politicaprestamo_obtenerPorId(@WebParam(name = "idPoliticaPrestamo") Integer idPoliticaPrestamo) {
        try {
            return politicaPrestamoBO.obtenerPorId(idPoliticaPrestamo);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "politicareserva_insertar")
    public Integer politicareserva_insertar(@WebParam(name = "cantMaxHorasDeRecojo") Integer cantMaxHorasDeRecojo,
                                            @WebParam(name = "cantMaxReservasAgendadasPorUsuario") Integer cantMaxReservasAgendadasPorUsuario) {
        try {
            return politicaReservaBO.insertar(cantMaxHorasDeRecojo, cantMaxReservasAgendadasPorUsuario);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "politicareserva_modificar")
    public Integer politicareserva_modificar(@WebParam(name = "idPoliticaReserva") Integer idPoliticaReserva,
                                              @WebParam(name = "cantMaxHorasDeRecojo") Integer cantMaxHorasDeRecojo,
                                              @WebParam(name = "cantMaxReservasAgendadasPorUsuario") Integer cantMaxReservasAgendadasPorUsuario) {
        try {
            return politicaReservaBO.modificar(idPoliticaReserva, cantMaxHorasDeRecojo, cantMaxReservasAgendadasPorUsuario);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "politicareserva_eliminar")
    public Integer politicareserva_eliminar(@WebParam(name = "idPoliticaReserva") Integer idPoliticaReserva) {
        try {
            return politicaReservaBO.eliminar(idPoliticaReserva);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "politicareserva_listarTodos")
    public ArrayList<PoliticaReserva> politicareserva_listarTodos(Integer limite) {
        try {
            return politicaReservaBO.listarTodos(limite);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "politicareserva_obtenerPorId")
    public PoliticaReserva politicareserva_obtenerPorId(@WebParam(name = "idPoliticaReserva") Integer idPoliticaReserva) {
        try {
            return politicaReservaBO.obtenerPorId(idPoliticaReserva);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "politica_insertar")
    public Integer politica_insertar(@WebParam(name = "etiqueta") String etiqueta,
                                     @WebParam(name = "idPoliticaPrestamo") Integer idPoliticaPrestamo,
                                     @WebParam(name = "idPoliticaReserva") Integer idPoliticaReserva) {
        try {
            return politicaBO.insertar(etiqueta, idPoliticaPrestamo, idPoliticaReserva);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "politica_modificar")
    public Integer politica_modificar(@WebParam(name = "idPolitica") Integer idPolitica,
                                       @WebParam(name = "etiqueta") String etiqueta,
                                       @WebParam(name = "idPoliticaPrestamo") Integer idPoliticaPrestamo,
                                       @WebParam(name = "idPoliticaReserva") Integer idPoliticaReserva) {
        try {
            return politicaBO.modificar(idPolitica, etiqueta, idPoliticaPrestamo, idPoliticaReserva);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "politica_eliminar")
    public Integer politica_eliminar(@WebParam(name = "idPolitica") Integer idPolitica) {
        try {
            return politicaBO.eliminar(idPolitica);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "politica_listarTodos")
    public ArrayList<Politica> politica_listarTodos(@WebParam(name = "idPoliticaPrestamo")Integer idPoliticaPrestamo,
                                                    @WebParam(name = "idPoliticaReserva")Integer idPoliticaReserva, 
                                                    @WebParam(name = "limite")Integer limite) {
        try {
            return politicaBO.listarTodos(idPoliticaPrestamo,idPoliticaReserva,limite);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "politica_obtenerPorId")
    public Politica politica_obtenerPorId(@WebParam(name = "idPolitica") Integer idPolitica) {
        try {
            return politicaBO.obtenerPorId(idPolitica);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    
    
    
                /*  - / > [ - PAQUETE - LOCALIDAD - ] < / -  */

    
    @WebMethod(operationName = "consorcio_insertar")
    public Integer consorcio_insertar(@WebParam(name = "CIF") String CIF) {
        try {
            return consorcioBO.insertar(CIF);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "consorcio_modificar")
    public Integer consorcio_modificar(@WebParam(name = "idConsorcio") Integer idConsorcio,
                                        @WebParam(name = "CIF") String CIF) {
        try {
            return consorcioBO.modificar(idConsorcio, CIF);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "consorcio_eliminar")
    public Integer consorcio_eliminar(@WebParam(name = "idConsorcio") Integer idConsorcio) {
        try {
            return consorcioBO.eliminar(idConsorcio);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "consorcio_listarTodos")
    public ArrayList<Consorcio> consorcio_listarTodos(@WebParam(name = "limite")Integer limite) {
        try {
            return consorcioBO.listarTodos(limite);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "consorcio_obtenerPorId")
    public Consorcio consorcio_obtenerPorId(@WebParam(name = "idConsorcio") Integer idConsorcio) {
        try {
            return consorcioBO.obtenerPorId(idConsorcio);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "universidad_insertar")
    public Integer universidad_insertar(@WebParam(name = "nombre") String nombre,
                                         @WebParam(name = "direccion") String direccion,
                                         @WebParam(name = "correoPersonalAdministrativo") String correoPersonalAdministrativo,
                                         @WebParam(name = "extensionDominioCorreo") String extensionDominioCorreo,
                                         @WebParam(name = "idPoliticaRegular") Integer idPoliticaRegular,
                                         @WebParam(name = "idConsorcioPerteneciente") Integer idConsorcioPerteneciente) {
        try {
            return universidadBO.insertar(nombre, direccion, correoPersonalAdministrativo, extensionDominioCorreo,
                    idPoliticaRegular, idConsorcioPerteneciente);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "universidad_modificar")
    public Integer universidad_modificar(@WebParam(name = "idUniversidad") Integer idUniversidad,
                                          @WebParam(name = "nombre") String nombre,
                                          @WebParam(name = "direccion") String direccion,
                                          @WebParam(name = "correoPersonalAdministrativo") String correoPersonalAdministrativo,
                                          @WebParam(name = "extensionDominioCorreo") String extensionDominioCorreo,
                                          @WebParam(name = "idPoliticaRegular") Integer idPoliticaRegular,
                                          @WebParam(name = "idConsorcioPerteneciente") Integer idConsorcioPerteneciente) {
        try {
            return universidadBO.modificar(idUniversidad, nombre, direccion, correoPersonalAdministrativo,
                    extensionDominioCorreo, idPoliticaRegular, idConsorcioPerteneciente);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "universidad_eliminar")
    public Integer universidad_eliminar(@WebParam(name = "idUniversidad") Integer idUniversidad) {
        try {
            return universidadBO.eliminar(idUniversidad);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "universidad_listarTodos")
    public ArrayList<Universidad> universidad_listarTodos(@WebParam(name = "idPoliticaRegular")Integer idPoliticaRegular,
                                                          @WebParam(name = "idConsorcioPerteneciente")Integer idConsorcioPerteneciente, 
                                                          @WebParam(name = "limite")Integer limite) {
        try {
            return universidadBO.listarTodos(idPoliticaRegular,idConsorcioPerteneciente,limite);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "universidad_obtenerPorId")
    public Universidad universidad_obtenerPorId(@WebParam(name = "idUniversidad") Integer idUniversidad) {
        try {
            return universidadBO.obtenerPorId(idUniversidad);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "listarTodos_UniversidadesDeUsuarioPorID")
    public ArrayList<Universidad> listarTodos_UniversidadesDeUsuarioPorID(@WebParam(name = "idUsuario")Integer idUsuario){
        try {
            return this.universidadBO.listarTodos_UniversidadesDeUsuarioPorID(idUsuario);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "listarTodos_UniversidadesDeUsuario")
    public ArrayList<Universidad> listarTodos_UniversidadesDeUsuario(@WebParam(name = "idUsuario")Integer idUsuario,
                                                                     @WebParam(name = "nombres")String nombres, 
                                                                     @WebParam(name = "apellidos")String apellidos,
                                                                     @WebParam(name = "correoInstitucional")String correoInstitucional,
                                                                     @WebParam(name = "nombre_usuario")String nombre_usuario,
                                                                     @WebParam(name = "fechaRegistro")String fecharegistrarPrestamo,
                                                                     @WebParam(name = "tipoUsuario")String tipoUsuario,
                                                                     @WebParam(name = "idPoliticaRegular")Integer idPoliticaRegular,
                                                                     @WebParam(name = "idConsorcioPerteneciente")Integer idConsorcioPerteneciente){
        try {
            return this.universidadBO.listarTodos_UniversidadesDeUsuario(idUsuario, nombres, apellidos, correoInstitucional, nombre_usuario, fecharegistrarPrestamo, tipoUsuario, idPoliticaRegular, idConsorcioPerteneciente);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }        
    //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    @WebMethod(operationName = "eliminarUniversidadesDeUsuario")
    public Integer eliminarUniversidadesDeUsuario(  @WebParam(name = "idUsuario")Integer idUsuario ){
        try {
            return this.universidadBO.eliminarUniversidadesDeUsuario(idUsuario);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }        
    
    @WebMethod(operationName = "biblioteca_insertar")
    public Integer biblioteca_insertar(@WebParam(name = "nombre") String nombre,
                                        @WebParam(name = "ubicacion") String ubicacion,
                                        @WebParam(name = "idUniversidadAsociada") Integer idUniversidadAsociada) {
        try {
            return bibliotecaBO.insertar(nombre, ubicacion, idUniversidadAsociada);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "biblioteca_modificar")
    public Integer biblioteca_modificar(@WebParam(name = "idBiblioteca") Integer idBiblioteca,
                                         @WebParam(name = "nombre") String nombre,
                                         @WebParam(name = "ubicacion") String ubicacion,
                                         @WebParam(name = "idUniversidadAsociada") Integer idUniversidadAsociada) {
        try {
            return bibliotecaBO.modificar(idBiblioteca, nombre, ubicacion, idUniversidadAsociada);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "biblioteca_eliminar")
    public Integer biblioteca_eliminar(@WebParam(name = "idBiblioteca") Integer idBiblioteca) {
        try {
            return bibliotecaBO.eliminar(idBiblioteca);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "biblioteca_listarTodos")
    public ArrayList<Biblioteca> biblioteca_listarTodos(@WebParam(name = "idUniversidadAsociada")Integer idUniversidadAsociada, 
                                                        @WebParam(name = "limite")Integer limite) {
        try {
            return bibliotecaBO.listarTodos(idUniversidadAsociada,limite);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @WebMethod(operationName = "biblioteca_obtenerPorId")
    public Biblioteca biblioteca_obtenerPorId(@WebParam(name = "idBiblioteca") Integer idBiblioteca) {
        try {
            return bibliotecaBO.obtenerPorId(idBiblioteca);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "mostrarBibliotecasDeUniversidadesDelProgramador")
    public ArrayList<Biblioteca> mostrarBibliotecasDeUniversidadesDelProgramador(@WebParam(name = "idUsuario")Integer idUsuario, 
                                    @WebParam(name = "idUniversidadesAsociadasDelBibliotecario")ArrayList<Integer>idUniversidadesAsociadasDelBibliotecario){
        try {
            return this.bibliotecaBO.mostrarBibliotecasDeUniversidadesDelProgramador(idUsuario, idUniversidadesAsociadasDelBibliotecario);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    
    
    
                /*  - / > [ - PAQUETE - USUARIOS - ] < / -  */
    
    
    @WebMethod(operationName = "modificarUsuario")
    public Integer usuario_modificar(@WebParam(name = "idUsuario") Integer idUsuario,@WebParam(name = "nombres") String nombres,
            @WebParam(name = "apellidos") String apellidos,@WebParam(name = "correo")String correo,
            @WebParam(name = "contrasenia") String contrasenia, @WebParam(name = "nombreUsuario") String nombreUsuario,
            @WebParam(name = "fechaRegistro") String fechaRegistro,@WebParam(name = "tipoUsuario") String tipoUsuario) throws ParseException{
        try {
            return this.usuarioBO.modificar(idUsuario, nombres, apellidos, correo, contrasenia, nombreUsuario, fechaRegistro, tipoUsuario);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "iniciarSesion")
    public Usuario usuario_iniciarSesion(@WebParam(name = "correo_nombreusuario")String correo_nombreusuario, 
                                 @WebParam(name = "contrasenia")String contrasenia) {
        try {
            return usuarioBO.iniciarSesion(correo_nombreusuario, contrasenia);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @WebMethod(operationName = "registrarMiembro")
    public Integer registrarMiembro(@WebParam(name = "nombre")String nombre,
                                    @WebParam(name = "apellidos")String apellidos,
                                    @WebParam(name = "correo")String correo,
                                    @WebParam(name = "contrasenia")String contrasenia,
                                    @WebParam(name = "contraseniaconfirmacion")String contraseniaconfirmacion,
                                    @WebParam(name = "nombreusuario")String nombreusuario,
                                    @WebParam(name = "strIdUniversidad")String strIdUniversidad){
        try {
            return this.usuarioBO.registrarMiembro(nombre, apellidos, correo, contrasenia, contraseniaconfirmacion, nombreusuario, strIdUniversidad);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "consultarMiembroID")
    public Usuario consultarMiembroID(@WebParam(name = "idUsuario")Integer idUsuario) {
        try {
            return this.usuarioBO.consultarMiembroID(idUsuario);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "obtenerPorAtributosUnicos")
    public Usuario obtenerPorAtributosUnicos(@WebParam(name = "correoInstitucional")String correoInstitucional,
                                             @WebParam(name = "contrasenia")String contrasenia,
                                             @WebParam(name = "nombre_usuario")String nombre_usuario) {
        try {
            return this.usuarioBO.obtenerPorAtributosUnicos(correoInstitucional, contrasenia, nombre_usuario);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "usuario_listarTodos")
    public ArrayList<Usuario> usuario_listarTodos(@WebParam(name = "nombres")String nombres, 
                                          @WebParam(name = "apellidos")String apellidos,
                                          @WebParam(name = "correoInstitucional")String correoInstitucional,
                                          @WebParam(name = "nombre_usuario")String nombre_usuario,
                                          @WebParam(name = "tipoUsuario")String tipoUsuario, 
                                          @WebParam(name = "limite")Integer limite) {
        try {
            return this.usuarioBO.listarTodos(nombres, apellidos, correoInstitucional, nombre_usuario, tipoUsuario, limite);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "listarTodos_UsuariosDeUniversidadPorID")
    public ArrayList<Usuario> listarTodos_UsuariosDeUniversidadPorID(@WebParam(name = "idUniversidad")Integer idUniversidad){
        try {
            return this.usuarioBO.listarTodos_UsuariosDeUniversidadPorID(idUniversidad);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "listarTodos_UsuariosDeUniversidad")
    public ArrayList<Usuario> listarTodos_UsuariosDeUniversidad(@WebParam(name = "nombres")String nombres, 
                                                                @WebParam(name = "apellidos")String apellidos,
                                                                @WebParam(name = "correoInstitucional")String correoInstitucional,
                                                                @WebParam(name = "nombre_usuario")String nombre_usuario,
                                                                @WebParam(name = "fechaRegistro")String fechaRegistro,
                                                                @WebParam(name = "tipoUsuario")String tipoUsuario, 
                                                                @WebParam(name = "idUniversidad")Integer idUniversidad,
                                                                @WebParam(name = "idPoliticaRegular")Integer idPoliticaRegular,
                                                                @WebParam(name = "idConsorcioPerteneciente")Integer idConsorcioPerteneciente){
        try {
            return this.usuarioBO.listarTodos(nombres, apellidos, correoInstitucional, nombre_usuario, tipoUsuario, idUniversidad);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "modificarUniversidadesAsociadasDeUsuario")
    public Integer modificarUniversidadesAsociadasDeUsuario(@WebParam(name = "nombres")Integer idUsuario,
                                                            @WebParam(name = "idUniversidadesActuales")ArrayList<Integer> idUniversidadesActuales,
                                                            @WebParam(name = "idUniversidadesNuevas")ArrayList<Integer> idUniversidadesNuevas){
        try {
            return this.usuarioBO.modificarUniversidadesAsociadasDeUsuario(idUsuario, idUniversidadesActuales, idUniversidadesNuevas);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }



    
    
    
              /*  - / > [ - PAQUETE - PUBLICACIONES - ] < / -  */
    
    
    @WebMethod(operationName = "buscarAutorPorNombre")
    public ArrayList<Autor> buscarAutorPorNombre( @WebParam(name = "nombre")String nombre,
                                                  @WebParam(name = "limite")Integer limite){
        try {
            return this.autorBO.buscarAutorPorNombre(nombre, limite);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "insertarAutor")
    public Integer insertarAutor(@WebParam(name = "nombre")String nombre){
        try {
            return this.autorBO.insertarAutor(nombre);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "listarTodos_AutoresDeLibroPorID")
    public ArrayList<Autor> listarTodos_AutoresDeLibroPorID(@WebParam(name = "idLibro")Integer idLibro){
        try {
            return this.autorBO.listarTodos_AutoresDeLibroPorID(idLibro);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "listarTodos_AutoresDeLibro")
    public ArrayList<Autor> listarTodos_AutoresDeLibro(@WebParam(name = "idLibro")Integer idLibro, 
                                                       @WebParam(name = "titulo")String titulo, 
                                                       @WebParam(name = "fechaPublicacion")String fechaPublicacion,
                                                       @WebParam(name = "descripcion")String descripcion, 
                                                       @WebParam(name = "hayFormatoFisico")Integer hayFormatoFisico, 
                                                       @WebParam(name = "hayFormatoVirtual")Integer hayFormatoVirtual,
                                                       @WebParam(name = "idBibliotecaAsociada")Integer idBibliotecaAsociada, 
                                                       @WebParam(name = "idEditorial")Integer idEditorial, 
                                                       @WebParam(name = "tipo")String tipo, 
                                                       @WebParam(name = "volumen")Integer volumen,
                                                       @WebParam(name = "materia")String materia, 
                                                       @WebParam(name = "genero")String genero, 
                                                       @WebParam(name = "tomo")Integer tomo, 
                                                       @WebParam(name = "nombreCompleto")String nombreCompleto){
        try {
            return this.autorBO.listarTodos_AutoresDeLibro(idLibro, titulo, fechaPublicacion, descripcion, hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada, idEditorial, tipo, volumen, materia, genero, tomo, nombreCompleto);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "ObtenerCopiasEjemplaresDeLibro")
    public ArrayList<CopiaEjemplar>ObtenerCopiasEjemplaresDeLibro(@WebParam(name = "idLibro")Integer idLibro){
        try {
            return this.copiaEjemplarBO.ObtenerCopiasEjemplaresDeLibro(idLibro);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "ObtenerCopiasEjemplaresDeTesis")
    public ArrayList<CopiaEjemplar>ObtenerCopiasEjemplaresDeTesis(@WebParam(name = "idTesis")Integer idTesis){
        try {
            return this.copiaEjemplarBO.ObtenerCopiasEjemplaresDeTesis(idTesis);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "buscarEditorialPorNombre")
    public ArrayList<Editorial> buscarEditorialPorNombre(@WebParam(name = "nombre")String nombre){
        try {
            return this.editorialBO.buscarEditorialPorNombre(nombre);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "verDetallePublicacionLibro")
    public Libro verDetallePublicacionLibro(@WebParam(name = "idLibro")Integer idLibro){
        try {
            return this.libroBO.verDetallePublicacionLibro(idLibro);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "nuevaPublicacionLibro")
    public Integer nuevaPublicacionLibro(@WebParam(name = "titulo")String titulo,
                                         @WebParam(name = "fechaPublicacion")String fechaPublicacion, 
                                         @WebParam(name = "descripcion")String descripcion,
                                         @WebParam(name = "copiasDisponibles")Integer copiasDisponibles, 
                                         @WebParam(name = "hayFormatoFisico")Integer hayFormatoFisico,
                                         @WebParam(name = "hayFormatoVirtual")Integer hayFormatoVirtual, 
                                         @WebParam(name = "url")String url, 
                                         @WebParam(name = "portada")byte[] portada,
                                         @WebParam(name = "idBibliotecaAsociada")Integer idBibliotecaAsociada,
                                         @WebParam(name = "ISBN")Integer ISBN,
                                         @WebParam(name = "idEditorial")Integer idEditorial,
                                         @WebParam(name = "tipo")String tipo,
                                         @WebParam(name = "volumen")Integer volumen, 
                                         @WebParam(name = "materia")String materia, 
                                         @WebParam(name = "genero")String genero, 
                                         @WebParam(name = "tomo")Integer tomo, 
                                         @WebParam(name = "direccioneslocal")ArrayList<String> direccioneslocal,
                                         @WebParam(name = "autores")ArrayList<Integer> autores){
        try {
            return this.libroBO.nuevaPublicacionLibro(titulo, fechaPublicacion, descripcion, copiasDisponibles, hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada, ISBN, idEditorial, tipo, volumen, materia, genero, tomo, direccioneslocal, autores);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "modificarPublicacionLibro")
    public Integer modificarPublicacionLibro(@WebParam(name = "idLibro")Integer idLibro,
                                         @WebParam(name = "titulo")String titulo,
                                         @WebParam(name = "fechaPublicacion")String fechaPublicacion, 
                                         @WebParam(name = "descripcion")String descripcion,
                                         @WebParam(name = "copiasDisponibles")Integer copiasDisponibles, 
                                         @WebParam(name = "hayFormatoFisico")Integer hayFormatoFisico,
                                         @WebParam(name = "hayFormatoVirtual")Integer hayFormatoVirtual, 
                                         @WebParam(name = "url")String url, 
                                         @WebParam(name = "portada")byte[] portada,
                                         @WebParam(name = "idBibliotecaAsociada")Integer idBibliotecaAsociada,
                                         @WebParam(name = "ISBN")Integer ISBN,
                                         @WebParam(name = "idEditorial")Integer idEditorial,
                                         @WebParam(name = "tipo")String tipo,
                                         @WebParam(name = "volumen")Integer volumen, 
                                         @WebParam(name = "materia")String materia, 
                                         @WebParam(name = "genero")String genero, 
                                         @WebParam(name = "tomo")Integer tomo,                                         
                                         @WebParam(name = "idsCopiasEjemplares")ArrayList<Integer> idsCopiasEjemplares,
                                         @WebParam(name = "estadosCopiasEjemplares")ArrayList<String> estadosCopiasEjemplares,                                                                              
                                         @WebParam(name = "autores")ArrayList<Integer> autores,
                                         @WebParam(name = "cantCopiasPorInsertar")Integer cantCopiasPorInsertar){
        try {
            return this.libroBO.modificarPublicacionLibro(idLibro, titulo, fechaPublicacion, descripcion, copiasDisponibles, hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada, ISBN, idEditorial, tipo, volumen, materia, genero, tomo, idsCopiasEjemplares, estadosCopiasEjemplares, autores, cantCopiasPorInsertar);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "listarTodos_LibrosDeAutorPorID")
    public ArrayList<Libro> listarTodos_LibrosDeAutorPorID(@WebParam(name = "idAutor")Integer idAutor){
        try {
            return this.libroBO.listarTodos_LibrosDeAutorPorID(idAutor);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "listarTodos_LibrosDeAutor")
    public ArrayList<Libro> listarTodos_LibrosDeAutor(@WebParam(name = "titulo")String titulo, 
                                                      @WebParam(name = "fechaPublicacion")String fechaPublicacion,
                                                      @WebParam(name = "descripcion")String descripcion, 
                                                      @WebParam(name = "hayFormatoFisico")Integer hayFormatoFisico, 
                                                      @WebParam(name = "hayFormatoVirtual")Integer hayFormatoVirtual,
                                                      @WebParam(name = "idBibliotecaAsociada")Integer idBibliotecaAsociada, 
                                                      @WebParam(name = "idEditorial")Integer idEditorial, 
                                                      @WebParam(name = "tipo")String tipo, 
                                                      @WebParam(name = "volumen")Integer volumen,
                                                      @WebParam(name = "materia")String materia, 
                                                      @WebParam(name = "genero")String genero, 
                                                      @WebParam(name = "tomo")Integer tomo, 
                                                      @WebParam(name = "idAutor")Integer idAutor, 
                                                      @WebParam(name = "nombreCompleto")String nombreCompleto){
        try {
            return this.libroBO.listarTodos_LibrosDeAutor(titulo, fechaPublicacion, descripcion, hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada, idEditorial, tipo, volumen, materia, genero, tomo, idAutor, nombreCompleto);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "listarTodos_AutoresDeTesisPorID")
    public ArrayList<Autor> listarTodos_AutoresDeTesisPorID(@WebParam(name = "idTesis")Integer idTesis){
        try {
            return this.autorBO.listarTodos_AutoresDeTesisPorID(idTesis);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "listarTodos_AutoresDeTesis")
    public ArrayList<Autor> listarTodos_AutoresDeTesis(@WebParam(name = "idTesis")Integer idTesis,
                                                       @WebParam(name = "titulo")String titulo, 
                                                       @WebParam(name = "fechaPublicacion")String fechaPublicacion,
                                                       @WebParam(name = "descripcion")String descripcion, 
                                                       @WebParam(name = "hayFormatoFisico")Integer hayFormatoFisico, 
                                                       @WebParam(name = "hayFormatoVirtual")Integer hayFormatoVirtual,
                                                       @WebParam(name = "idBibliotecaAsociada")Integer idBibliotecaAsociada, 
                                                       @WebParam(name = "gradoAcademico")String gradoAcademico, 
                                                       @WebParam(name = "nombreCompleto")String nombreCompleto){
        try {
            return this.autorBO.listarTodos_AutoresDeTesis(idTesis, titulo, fechaPublicacion, descripcion, hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada, gradoAcademico, nombreCompleto);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    @WebMethod(operationName = "verCatalogo")
    public ArrayList<Publicacion> verCatalogo(@WebParam(name = "modoDeBusqueda")Integer modoDeBusqueda,
                                              @WebParam(name = "idBibliotecaAsociada")Integer idBibliotecaAsociada,
                                              @WebParam(name = "tipo")String tipo,
                                              @WebParam(name = "titulo")String titulo, 
                                              @WebParam(name = "limite")Integer limite){
        try {
            return this.publicacionBO.verCatalogo(modoDeBusqueda, idBibliotecaAsociada, tipo, titulo, limite);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "busquedaAvanzada")
    public ArrayList<Publicacion> busquedaAvanzada(@WebParam(name = "modoDeBusqueda")Integer modoDeBusqueda,
                                                   @WebParam(name = "titulo")String titulo,
                                                   @WebParam(name = "descripcion")String descripcion, 
                                                   @WebParam(name = "hayFormatoFisico")Integer hayFormatoFisico, 
                                                   @WebParam(name = "hayFormatoVirtual")Integer hayFormatoVirtual,
                                                   @WebParam(name = "idBibliotecaAsociada")Integer idBibliotecaAsociada, 
                                                   @WebParam(name = "idAutor")Integer idAutor) {
        try {
            return this.publicacionBO.busquedaAvanzada(modoDeBusqueda, titulo, descripcion, hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada, idAutor);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "publicacionesPorAutor")
    public ArrayList<Publicacion> publicacionesPorAutor(@WebParam(name = "id")Integer id){
        try {
            return this.publicacionBO.publicacionesPorAutor(id);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "consultarCopiaEjemplar")
    public CopiaEjemplar consultarCopiaEjemplar(@WebParam(name = "id")Integer id){
        try {
            return this.publicacionBO.consultarCopiaEjemplar(id);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }  
    
    @WebMethod(operationName = "buscarTemaPorNombre")
    public ArrayList<Tema> buscarTemaPorNombre(@WebParam(name = "titulo")String titulo,
                                               @WebParam(name = "limite")Integer limite){
        try {
            return this.temaBO.buscarTemaPorNombre(titulo,limite);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "insertarTema")
    public Integer insertarTema(@WebParam(name = "titulo")String titulo){
        try {
            return this.temaBO.insertar(titulo);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "listarTodos_TemasDeTesisPorID")
    public ArrayList<Tema> listarTodos_TemasDeTesisPorID(@WebParam(name = "idTesis")Integer idTesis){
        try {
            return this.temaBO.listarTodos_TemasDeTesisPorID(idTesis);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "listarTodos_TemasDeTesis")
    public ArrayList<Tema> listarTodos_TemasDeTesis(@WebParam(name = "idTesis")Integer idTesis, 
                                                    @WebParam(name = "ts_titulo")String ts_titulo, 
                                                    @WebParam(name = "fechaPublicacion")String fechaPublicacion,
                                                    @WebParam(name = "descripcion")String descripcion, 
                                                    @WebParam(name = "hayFormatoFisico")Integer hayFormatoFisico, 
                                                    @WebParam(name = "hayFormatoVirtual")Integer hayFormatoVirtual,
                                                    @WebParam(name = "idBibliotecaAsociada")Integer idBibliotecaAsociada, 
                                                    @WebParam(name = "gradoAcademico")String gradoAcademico, 
                                                    @WebParam(name = "tm_titulo")String tm_titulo){
        try {
            return this.temaBO.listarTodos_TemasDeTesis(idTesis, ts_titulo, fechaPublicacion, descripcion, hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada, gradoAcademico, tm_titulo);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    @WebMethod(operationName = "verDetallePublicacioTesis")
    public Tesis verDetallePublicacioTesis(@WebParam(name = "idTesis")Integer idTesis){
        try {
            return this.tesisBO.verDetallePublicacioTesis(idTesis);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    @WebMethod(operationName = "nuevaPublicacionTesis")
    public Integer nuevaPublicacionTesis(@WebParam(name = "titulo")String titulo, 
                                         @WebParam(name = "fechaPublicacion")String fechaPublicacion, 
                                         @WebParam(name = "descripcion")String descripcion,
                                         @WebParam(name = "copiasDisponibles")Integer copiasDisponibles, 
                                         @WebParam(name = "hayFormatoFisico")Integer hayFormatoFisico,
                                         @WebParam(name = "hayFormatoVirtual")Integer hayFormatoVirtual, 
                                         @WebParam(name = "url")String url, 
                                         @WebParam(name = "portada")byte[] portada,
                                         @WebParam(name = "idBibliotecaAsociada")Integer idBibliotecaAsociada,
                                         @WebParam(name = "gradoAcademico")String gradoAcademico, 
                                         @WebParam(name = "direccioneslocal")ArrayList<String> direccioneslocal,
                                         @WebParam(name = "autores")ArrayList<Integer> autores,
                                         @WebParam(name = "temas")ArrayList<Integer> temas){
        try {
            return this.tesisBO.nuevaPublicacionTesis(titulo, fechaPublicacion, descripcion, copiasDisponibles, hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada, gradoAcademico, direccioneslocal, autores, temas);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    @WebMethod(operationName = "modificarPublicacionTesis")
    public Integer modificarPublicacionTesis(@WebParam(name = "idTesis")Integer idTesis,
                                             @WebParam(name = "titulo")String titulo, 
                                             @WebParam(name = "fechaPublicacion")String fechaPublicacion, 
                                             @WebParam(name = "descripcion")String descripcion,
                                             @WebParam(name = "copiasDisponibles")Integer copiasDisponibles, 
                                             @WebParam(name = "hayFormatoFisico")Integer hayFormatoFisico,
                                             @WebParam(name = "hayFormatoVirtual")Integer hayFormatoVirtual, 
                                             @WebParam(name = "url")String url, 
                                             @WebParam(name = "portada")byte[] portada,
                                             @WebParam(name = "idBibliotecaAsociada")Integer idBibliotecaAsociada,
                                             @WebParam(name = "gradoAcademico")String gradoAcademico,
                                             @WebParam(name = "idsCopiasEjemplares")ArrayList<Integer> idsCopiasEjemplares, 
                                             @WebParam(name = "estadosCopiasEjemplares")ArrayList<String> estadosCopiasEjemplares,
                                             @WebParam(name = "autores")ArrayList<Integer> autores,
                                             @WebParam(name = "temas")ArrayList<Integer> temas,
                                             @WebParam(name = "cantCopiasPorInsertar")Integer cantCopiasPorInsertar){
        try {
            return this.tesisBO.modificar(idTesis, titulo, fechaPublicacion, descripcion, copiasDisponibles, hayFormatoFisico, hayFormatoVirtual, url, portada, idBibliotecaAsociada, gradoAcademico);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "listarTodos_TesisDeAutorPorID")
    public ArrayList<Tesis> listarTodos_TesisDeAutorPorID(@WebParam(name = "idAutor")Integer idAutor){
        try {
            return this.tesisBO.listarTodos_TesisDeAutorPorID(idAutor);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "listarTodos_TesisDeAutor")
    public ArrayList<Tesis> listarTodos_TesisDeAutor(@WebParam(name = "titulo")String titulo, 
                                                     @WebParam(name = "fechaPublicacion")String fechaPublicacion,
                                                     @WebParam(name = "descripcion")String descripcion, 
                                                     @WebParam(name = "hayFormatoFisico")Integer hayFormatoFisico, 
                                                     @WebParam(name = "hayFormatoVirtual")Integer hayFormatoVirtual,
                                                     @WebParam(name = "idBibliotecaAsociada")Integer idBibliotecaAsociada, 
                                                     @WebParam(name = "gradoAcademico")String gradoAcademico, 
                                                     @WebParam(name = "idAutor")Integer idAutor,
                                                     @WebParam(name = "nombreCompleto")String nombreCompleto){
        try {
            return this.tesisBO.listarTodos_TesisDeAutor(titulo, fechaPublicacion, descripcion, hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada, gradoAcademico, idAutor, nombreCompleto);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @WebMethod(operationName = "listarTodos_TesisDeTemaPorID")
    public ArrayList<Tesis> listarTodos_TesisDeTemaPorID(@WebParam(name = "idTema")Integer idTema){
        try {
            return this.tesisBO.listarTodos_TesisDeTemaPorID(idTema);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "listarTodos_TesisDeTema")
    public ArrayList<Tesis> listarTodos_TesisDeTema(@WebParam(name = "ts_titulo")String ts_titulo, 
                                                    @WebParam(name = "fechaPublicacion")String fechaPublicacion,
                                                    @WebParam(name = "descripcion")String descripcion, 
                                                    @WebParam(name = "hayFormatoFisico")Integer hayFormatoFisico, 
                                                    @WebParam(name = "hayFormatoVirtual")Integer hayFormatoVirtual,
                                                    @WebParam(name = "idBibliotecaAsociada")Integer idBibliotecaAsociada, 
                                                    @WebParam(name = "gradoAcademico")String gradoAcademico, 
                                                    @WebParam(name = "idTema")Integer idTema,
                                                    @WebParam(name = "tm_titulo")String tm_titulo){
        try {
            return this.tesisBO.listarTodos_TesisDeTema(ts_titulo, fechaPublicacion, descripcion, hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada, gradoAcademico, idTema, tm_titulo);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    
//                 /*  - / > [ - PAQUETE - PRESTAMO - ] < / -  */
    
    
    
    
    @WebMethod(operationName = "verPenalizacionesPrestamo")
    public ArrayList<Penalizacion> verPenalizacionesPrestamo(@WebParam(name = "idPrestamoAsociado")Integer idPrestamoAsociado){
        try {
            return this.penalizacionBO.verPenalizacionesPrestamo(idPrestamoAsociado);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "mostrarPrestamosEnCurso")
    public ArrayList<Prestamo> mostrarPrestamosEnCurso(@WebParam(name = "idUsuario")Integer idUsuario, 
                                                       @WebParam(name = "limite")Integer limite){
        try {
            return this.prestamoBO.mostrarPrestamosEnCurso(idUsuario,limite);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "mostrarUltimosPrestamos")
    public ArrayList<Prestamo> mostrarUltimosPrestamos(@WebParam(name = "limite")Integer limite){
        try {
            return this.prestamoBO.mostrarUltimosPrestamos(limite);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "solicitarAmpliacion")
    public Integer solicitarAmpliacion(@WebParam(name = "idPrestamo")Integer idPrestamo){
        try {
            return this.prestamoBO.solicitarAmpliacion(idPrestamo);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "confirmarAmpliacion")
    public Integer confirmarAmpliacion(@WebParam(name = "idPrestamo")Integer idPrestamo){
        try {
            return this.prestamoBO.confirmarAmpliacion(idPrestamo);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "consultarCalificacionPrestamo")
    public ArrayList<String> consultarCalificacionPrestamo(@WebParam(name = "idUsuario")Integer idUsuario, 
                                                           @WebParam(name = "idCopiaEjemplar")Integer idCopiaEjemplar){
        try {
            return this.prestamoBO.consultarCalificacionPrestamo(idUsuario, idCopiaEjemplar);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "registrarPrestamo")
    public Integer registrarPrestamo(@WebParam(name = "idUsuario")Integer idUsuario, 
                                     @WebParam(name = "idCopiaEjemplar")Integer idCopiaEjemplar){
        try {
            return this.prestamoBO.registrarPrestamo(idUsuario, idCopiaEjemplar);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "consultarPrestamoQueSePrestoCopiaEjemplar")
    public Prestamo consultarPrestamoQueSePrestoCopiaEjemplar(@WebParam(name = "idCopiaEjemplar")Integer idCopiaEjemplar){
        try {
            return this.prestamoBO.consultarPrestamoQueSePrestoCopiaEjemplar(idCopiaEjemplar);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "confirmarDevolucion")
    public Integer confirmarDevolucion(@WebParam(name = "idPrestamo")Integer idPrestamo,
                                   @WebParam(name = "idCopiaEjemplar")Integer idCopiaEjemplar, 
                                   @WebParam(name = "estaEnMalEstado")Integer estaEnMalEstado){
        try {
            return this.prestamoBO.confirmarDevolucion(idPrestamo, idCopiaEjemplar, estaEnMalEstado);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    

    //                 /*  - / > [ - PAQUETE - RESERVA - ] < / -  */
    
    @WebMethod(operationName = "mostrarReservasEnCurso")
    public ArrayList<Reserva> mostrarReservasEnCurso(@WebParam(name = "idUsuario")Integer idUsuario){
        try {
            return this.reservaBO.mostrarReservasEnCurso(idUsuario);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "cancelarReserva")
    public Integer cancelarReserva(@WebParam(name = "idReserva")Integer idReserva){
        try {
            return this.reservaBO.cancelarReserva(idReserva);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "solicitarReserva")
    public Reserva solicitarReserva(@WebParam(name = "idUsuario")Integer idUsuario, 
                                    @WebParam(name = "idPublicacion")Integer idPublicacion,
                                    @WebParam(name = "tipoPublicacion")String tipoPublicacion){
        try {
            return this.reservaBO.solicitarReserva(idUsuario, idPublicacion, tipoPublicacion);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @WebMethod(operationName = "confirmarReserva")
    public Integer confirmarReserva(@WebParam(name = "idUsuario")Integer idUsuario, 
                                    @WebParam(name = "idEjemplarAsociado")Integer idEjemplarAsociado, 
                                    @WebParam(name = "instanteRecojoMax")String instanteRecojoMax){
        try {
            return this.reservaBO.confirmarReserva(idUsuario, idEjemplarAsociado, instanteRecojoMax);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "insertarEditorial")
    public Integer insertar(@WebParam(name = "nombre")String nombre) {        
        try {
            return this.editorialBO.insertar(nombre);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    @WebMethod(operationName = "obtenerPorId_copia_ejemplar")
    public CopiaEjemplar obtenerPorId(@WebParam(name = "idCopiaEjemplar")Integer idCopiaEjemplar){
        try {
            return this.copiaEjemplarBO.obtenerPorId(idCopiaEjemplar);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "obtenerPorId_Prestamo")
    public Prestamo obtenerPorId_Prestamo(@WebParam(name = "idPrestamo")Integer idPrestamo){
        try {
            return prestamoBO.obtenerPorId(idPrestamo);
        } catch (RemoteException ex) {
            Logger.getLogger(ServicioWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    private void cargar_configuracion_de_servidor() {
        Properties properties = new Properties();
        try {
            String nmArchivoConf = "c:"+"\\" + ARCHIVO_CONFIGURACION;
            System.out.print("Accediendo al directorio '" + nmArchivoConf + "'.. ");
            properties.load(new FileInputStream(new File(nmArchivoConf)));
            this.ip = properties.getProperty("ip");
            String str_puerto = properties.getProperty("puerto");
            this.puerto = Integer.parseInt(str_puerto);
        } catch (FileNotFoundException ex) {
            System.out.println("[FAILED]");
            System.err.println("ERROR: No se encontro el archivo de propiedades en la ruta especificada.\n" + ex);
        } catch (IOException ex) {
            System.out.println("[FAILED]");
            System.err.println("ERROR: Datos faltantes en el archivo de propiedades.\n" + ex);
        }
    }
    
    public String retornarNombreDelSevicio(String nombreDelObjetoRemoto) {
        return "//"+ip+":"+puerto+"/"+nombreDelObjetoRemoto;
    }
    
}