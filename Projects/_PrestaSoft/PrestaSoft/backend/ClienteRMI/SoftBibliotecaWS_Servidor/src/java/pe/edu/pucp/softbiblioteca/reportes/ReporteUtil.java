
package pe.edu.pucp.softbiblioteca.reportes;

import jakarta.servlet.http.HttpServletResponse;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import pe.edu.pucp.softbiblioteca.db.DBManager;
import pe.edu.pucp.softbiblioteca.servlet.ReporteSolicitudesServlet;

public class ReporteUtil {

    private static byte[] invocarReporte(HttpServletResponse response, String nombreReporte, HashMap parametros) {
        byte[] reporte = null;
        Connection conexion = DBManager.getInstance().getConnection();
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(ReporteUtil.class.getResource("/pe/edu/pucp/softbiblioteca/reportes/" + nombreReporte + ".jasper"));
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, conexion);
            if (response != null) {
                JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());
            } else {
                reporte = JasperExportManager.exportReportToPdf(jp);
            }
        } catch (JRException | IOException ex) {
            Logger.getLogger(ReporteUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) { //deccia ReporteSeccion xd
                Logger.getLogger(ReporteUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return reporte;
    }
    /*
    public static byte[] reporteSeccion(HttpServletResponse response) {
        return invocarReporte(response, "ReporteSeccion", null);
    }
*/
    public static byte[] reporteSolicitudes(HttpServletResponse response, Integer idUsuario, String fechaMin,String fechaMax, String estadoDePrestamos, Integer idUniversidad, Integer mostrarONoReservas, String descripcion ) { //!!!
        
        Date min = null ;
        Date max =null;
        if(fechaMax!=null)
            max = java.sql.Date.valueOf(fechaMax);
        if(fechaMin!=null)
            min = java.sql.Date.valueOf(fechaMin);
        
        HashMap parametros = new HashMap();
        parametros.put("descripcion", descripcion ); //la descripción se arma en el frontend con los objetos instanciados!!
        parametros.put("idUsuario", idUsuario ); //no aguanta nulo o sale mal resultado
        parametros.put("fechaMin", min ); //creo que sí aguantaba nulos el procedimiento almacenado CREO
        parametros.put("fechaMax",   max ); // aguanta nulo
        parametros.put("estadoDePrestamos",estadoDePrestamos ); //aguanta nulo
        parametros.put("idUniversidad",idUniversidad); //debe ser numerico, 0 o id
        parametros.put("mostrarONoReservas", mostrarONoReservas>0); // debe ser numerito, no nulo
        String subreportDir = ReporteUtil.class.getResource("/pe/edu/pucp/softbiblioteca/reportes/").getPath(); //GPT sugirió esto!
        parametros.put("SUBREPORT_DIR", subreportDir);
                
        return invocarReporte(response, "reporteSolicitudes", parametros);
        
       
    }
    
    public static byte[] reporteMiembros(HttpServletResponse response,String fechaRegMin,String fechaRegMax, Integer cantSancionesMin, Integer cantSancionesMax, Integer idUniversidad, Integer limiteUsuarios, String descripcion  ){
        // Parámetros para el reporte
        Date min = null ;
        Date max =null;
        if(fechaRegMin!=null)
            min = java.sql.Date.valueOf(fechaRegMin);
        if(fechaRegMax!=null)
            max = java.sql.Date.valueOf(fechaRegMax);
        
        HashMap parametros = new HashMap();
        parametros.put("descripcion", descripcion); // aguanta nulo, la descripción se arma en el frontend con los objetos instanciados!!
        parametros.put("fechaRegistroMinima", min); // aguanta nulo
        parametros.put("fechaRegistroMaxima", max); // aguanta nulo
        parametros.put("cantidadSancionesMinima", cantSancionesMin); // aguanta nulo
        parametros.put("cantidadSancionesMaxima", cantSancionesMax); // aguanta nulo
        parametros.put("idUniversidad",idUniversidad); // puede ser 0 para que no sean de una univ en particular
        parametros.put("limiteUsuarios", limiteUsuarios); 

        // Ruta del directorio de subreportes
        String subreportDir = ReporteUtil.class.getResource("/pe/edu/pucp/softbiblioteca/reportes/").getPath(); //JasperReports usa Java para resolver esa ruta, que estará basada en el valor de user.dir (el directorio de trabajo actual).
        parametros.put("SUBREPORT_DIR", subreportDir);

        return invocarReporte(response, "reporteMiembros", parametros);
    }
    
    public static byte[] reportePublicaciones(HttpServletResponse response,Integer cantPrestamosMin, Integer cantPrestamosMax, Integer incluirVirtuales,Integer idUniversidad, Integer limite, String descripcion  ){
        // Parámetros para el reporte
       
        HashMap parametros = new HashMap();
        parametros.put("descripcion", descripcion); // Título o descripción del reporte NULABLE
        parametros.put("idUniversidad", idUniversidad); // Filtrar por ID de universidad, debe ser 0 no nulo o id
        parametros.put("cantidadDePrestamosMinima", cantPrestamosMin); // Mínimo de préstamos NULABLE pero no deja
        parametros.put("cantidadDePrestamosMaxima", cantPrestamosMax); // Máximo de préstamos NULABLE pero no deja el java
        parametros.put("incluirVirtuales", incluirVirtuales>0); // Incluir publicaciones virtuales  debe ser 0 no nulo o id
        parametros.put("limite", limite); // Límite de publicaciones a mostrar nulable

            // Ruta del directorio de subreportes
        String subreportDir = ReporteUtil.class.getResource("/pe/edu/pucp/softbiblioteca/reportes/").getPath(); //JasperReports usa Java para resolver esa ruta, que estará basada en el valor de user.dir (el directorio de trabajo actual).
        parametros.put("SUBREPORT_DIR", subreportDir);

        return invocarReporte(response, "reportePublicaciones", parametros);
    }

}