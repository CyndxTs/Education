
package pe.edu.pucp.softbiblioteca.rmi.servidor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.rmi.registry.LocateRegistry;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import pe.edu.pucp.softbiblioteca.rmi.interfacesImpl.AutorBOImpl;
import pe.edu.pucp.softbiblioteca.rmi.interfacesImpl.BibliotecaBOImpl;
import pe.edu.pucp.softbiblioteca.rmi.interfacesImpl.ConsorcioBOImpl;
import pe.edu.pucp.softbiblioteca.rmi.interfacesImpl.CopiaEjemplarBOImpl;
import pe.edu.pucp.softbiblioteca.rmi.interfacesImpl.EditorialBOImpl;
import pe.edu.pucp.softbiblioteca.rmi.interfacesImpl.LibroBOImpl;
import pe.edu.pucp.softbiblioteca.rmi.interfacesImpl.MoraBOImpl;
import pe.edu.pucp.softbiblioteca.rmi.interfacesImpl.PenalizacionBOImpl;
import pe.edu.pucp.softbiblioteca.rmi.interfacesImpl.PoliticaBOImpl;
import pe.edu.pucp.softbiblioteca.rmi.interfacesImpl.PoliticaPrestamoBOImpl;
import pe.edu.pucp.softbiblioteca.rmi.interfacesImpl.PoliticaReservaBOImpl;
import pe.edu.pucp.softbiblioteca.rmi.interfacesImpl.PrestamoBOImpl;
import pe.edu.pucp.softbiblioteca.rmi.interfacesImpl.PublicacionBOImpl;
import pe.edu.pucp.softbiblioteca.rmi.interfacesImpl.ReservaBOImpl;
import pe.edu.pucp.softbiblioteca.rmi.interfacesImpl.TemaBOImpl;
import pe.edu.pucp.softbiblioteca.rmi.interfacesImpl.TesisBOImpl;
import pe.edu.pucp.softbiblioteca.rmi.interfacesImpl.UniversidadBOImpl;
import pe.edu.pucp.softbiblioteca.rmi.interfacesImpl.UsuarioBOImpl;
import java.rmi.Naming;

public class ServidorRMI {
    
    private static final String ARCHIVO_CONFIGURACION = "rmi.properties";
    private String ip;
    private Integer puerto;
    
    public ServidorRMI(){};
    
    public void iniciarServidor(){
        createServer();
    }
    
    private void createServer(){
        try {
            cargar_configuracion_de_servidor();
            LocateRegistry.createRegistry(puerto);
            AutorBO autorBO = new AutorBOImpl(puerto);
            String nombreServicio = retornarNombreDelSevicio("autorBO");
            Naming.rebind(nombreServicio,autorBO);
            
            BibliotecaBO bibliotecaBO = new BibliotecaBOImpl(puerto);
            nombreServicio = retornarNombreDelSevicio("bibliotecaBO");
            Naming.rebind(nombreServicio,bibliotecaBO);
            
            ConsorcioBO consorcioBO = new ConsorcioBOImpl(puerto);
            nombreServicio = retornarNombreDelSevicio("consorcioBO");
            Naming.rebind(nombreServicio,consorcioBO);
            
            CopiaEjemplarBO copiaejemplarBO = new CopiaEjemplarBOImpl(puerto);
            nombreServicio = retornarNombreDelSevicio("copiaejemplarBO");
            Naming.rebind(nombreServicio,copiaejemplarBO);
            
            EditorialBO editorialBO = new EditorialBOImpl(puerto);
            nombreServicio = retornarNombreDelSevicio("editorialBO");
            Naming.rebind(nombreServicio,editorialBO);
            
            LibroBO libroBO = new LibroBOImpl(puerto);
            nombreServicio = retornarNombreDelSevicio("libroBO");
            Naming.rebind(nombreServicio,libroBO);
            
            MoraBO moraBO = new MoraBOImpl(puerto);
            nombreServicio = retornarNombreDelSevicio("moraBO");
            Naming.rebind(nombreServicio,moraBO);
            
            PenalizacionBO penalizacionBO = new PenalizacionBOImpl(puerto);
            nombreServicio = retornarNombreDelSevicio("penalizacionBO");
            Naming.rebind(nombreServicio,penalizacionBO);
            
            PoliticaBO politicaBO = new PoliticaBOImpl(puerto);
            nombreServicio = retornarNombreDelSevicio("politicaBO");
            Naming.rebind(nombreServicio,politicaBO);
            
            PoliticaPrestamoBO politicaprestamoBO = new PoliticaPrestamoBOImpl(puerto);
            nombreServicio = retornarNombreDelSevicio("politicaprestamoBO");
            Naming.rebind(nombreServicio,politicaprestamoBO);
            
            PoliticaReservaBO politicareservaBO = new PoliticaReservaBOImpl(puerto);
            nombreServicio = retornarNombreDelSevicio("politicareservaBO");
            Naming.rebind(nombreServicio,politicareservaBO);
            
            PrestamoBO prestamoBO = new PrestamoBOImpl(puerto);
            nombreServicio = retornarNombreDelSevicio("prestamoBO");
            Naming.rebind(nombreServicio,prestamoBO);
            
            PublicacionBO publicacionBO = new PublicacionBOImpl(puerto);
            nombreServicio = retornarNombreDelSevicio("publicacionBO");
            Naming.rebind(nombreServicio,publicacionBO);
            
            ReservaBO reservaBO = new ReservaBOImpl(puerto);
            nombreServicio = retornarNombreDelSevicio("reservaBO");
            Naming.rebind(nombreServicio,reservaBO);
            
            TemaBO temaBO = new TemaBOImpl(puerto);
            nombreServicio = retornarNombreDelSevicio("temaBO");
            Naming.rebind(nombreServicio,temaBO);
            
            TesisBO tesisBO = new TesisBOImpl(puerto);
            nombreServicio = retornarNombreDelSevicio("tesisBO");
            Naming.rebind(nombreServicio,tesisBO);
            
            UniversidadBO universidadBO = new UniversidadBOImpl(puerto);
            nombreServicio = retornarNombreDelSevicio("universidadBO");
            Naming.rebind(nombreServicio,universidadBO);
            
            UsuarioBO usuarioBO = new UsuarioBOImpl(puerto);
            nombreServicio = retornarNombreDelSevicio("usuarioBO");
            Naming.rebind(nombreServicio,usuarioBO);
            
            
            System.out.println("[SUCCESS]\nServidor RMI registrado correctamente.");
            
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(ServidorRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void cargar_configuracion_de_servidor() {
        Properties properties = new Properties();
        try {
            // String nmArchivoConf = "c:"+"\\" + ARCHIVO_CONFIGURACION;
            Path directorioActual = Paths.get(System.getProperty("user.dir"));
            Path rutaConfig = directorioActual.resolve("../../../../").resolve(ARCHIVO_CONFIGURACION).normalize();
            String nmArchivoConf = rutaConfig.toString();
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
