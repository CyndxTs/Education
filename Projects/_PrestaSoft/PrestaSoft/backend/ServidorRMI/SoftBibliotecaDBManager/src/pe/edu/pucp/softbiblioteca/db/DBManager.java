
/* [/]
 >> Project:    SoftBibliotecaDBManager
 >> File:       DBManager.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import pe.edu.pucp.softbiblioteca.util.Cifrado;

public class DBManager {

    private static final String ARCHIVO_CONFIGURACION = "jdbc.properties";
    private Connection conexion;
    private String driver;
    private String tipo_de_driver;
    private String base_de_datos;
    private String nombre_de_host;
    private String puerto;
    private String usuario;
    private String contraseña;
    private static DBManager dbManager;
    
    private DBManager(){}

    public static DBManager getInstance() {
        if (DBManager.dbManager == null) {
            createInstance();
        }
        return DBManager.dbManager;
    }

    private static void createInstance() {
        if (DBManager.dbManager == null) {
            DBManager.dbManager = new DBManager();
        }
    }

    public Connection getConnection() {
        try {
            leer_archivo_de_propiedades();
            System.out.print("[SUCCESS]\nIniciando driver '" + this.driver + "'.. ");
            Class.forName(this.driver);
            System.out.println("[SUCCESS]");
            System.out.print("Conectando con la base de datos '" + this.base_de_datos + "'.. ");
            this.conexion = DriverManager.getConnection(getURL(),this.usuario,Cifrado.descifrarMD5(this.contraseña));
        } catch (ClassNotFoundException ex) {
            System.out.println("[FAILED]");
            System.err.println("ERROR: La conexion con el driver ha fallado..\n" + ex);
        } catch (SQLException ex) {
            System.out.println("[FAILED]");
            System.err.println("ERROR: La conexión con la base de datos ha fallado..\n" + ex);
        }
        System.out.println("[SUCCESS]\nConexion Exitosa!");
        return conexion;
    }
    
    private String getURL() {
        String url = tipo_de_driver.concat("://");
        url = url.concat(this.nombre_de_host);
        url = url.concat(":");
        url = url.concat(this.puerto);
        url = url.concat("/");
        url = url.concat(this.base_de_datos);  
        url = url.concat("?useSSL=false");
        return url;
    }

    private void leer_archivo_de_propiedades() {
        Properties properties = new Properties();
        try {
            // String nmArchivoConf = "c:"+"\\" + ARCHIVO_CONFIGURACION;
            Path directorioActual = Paths.get(System.getProperty("user.dir"));
            Path rutaConfig = directorioActual.resolve("../../../../").resolve(ARCHIVO_CONFIGURACION).normalize();
            String nmArchivoConf = rutaConfig.toString();
            System.out.print("Accediendo al directorio '" + nmArchivoConf + "'.. ");
            properties.load(new FileInputStream(new File(nmArchivoConf)));
            this.driver = properties.getProperty("driver");
            this.tipo_de_driver = properties.getProperty("tipo_de_driver");
            this.base_de_datos = properties.getProperty("base_de_datos");
            this.nombre_de_host = properties.getProperty("nombre_de_host");
            this.puerto = properties.getProperty("puerto");
            this.usuario = properties.getProperty("usuario");
            this.contraseña = properties.getProperty("contrasenha");
        } catch (FileNotFoundException ex) {
            System.out.println("[FAILED]");
            System.err.println("ERROR: No se encontro el archivo de propiedades en la ruta especificada.\n" + ex);
        } catch (IOException ex) {
            System.out.println("[FAILED]");
            System.err.println("ERROR: Datos faltantes en el archivo de propiedades.\n" + ex);
        }
    }
}
