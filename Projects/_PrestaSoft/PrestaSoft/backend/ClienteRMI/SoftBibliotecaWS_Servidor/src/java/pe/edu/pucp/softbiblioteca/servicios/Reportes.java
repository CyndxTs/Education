package pe.edu.pucp.softbiblioteca.servicios;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.sql.Date; //SQL O UTIL??????? JASPER RECIBE SQL QUE YO SEPA D:
import pe.edu.pucp.softbiblioteca.reportes.ReporteUtil;

@WebService(serviceName="Reportes")
public class Reportes {
/*
    @WebMethod(operationName="reporteSeccion")
    public byte[] reporteSeccion() {
        return ReporteUtil.reporteSeccion(null);        
    }
  */  
    @WebMethod(operationName="reporteSolicitudes")
    public byte[] reporteSolicitudes(@WebParam(name="idUsuario") Integer idUsuario, 
                                        @WebParam(name="fechaMin") String fechaMin,
                                        @WebParam(name="fechaMax") String fechaMax, 
                                        @WebParam(name="estadoDePrestamos") String estadoDePrestamos, 
                                        @WebParam(name="idUniversidad") Integer idUniversidad, 
                                        @WebParam(name="mostrarONoReservas") Integer mostrarONoReservas, 
                                        @WebParam(name="descripcion") String descripcion ) {
        //ESTE REPORTE PUEDE RECIBIR FECHAS NULAS Y LAS PROCESA, PERO NO PUEDE RECICBIR IDUNIVERSIDAD NULA, TIENE QUE SER 0. 
        //NI BOOLEANO NULO, DEBE SER 1 O 0, DESCRIPCIÓN SÍ PUEDE SER NULA
        //IDUSUARIO OBLIGADO
        return ReporteUtil.reporteSolicitudes(null, idUsuario,fechaMin,fechaMax,estadoDePrestamos,idUniversidad,mostrarONoReservas,descripcion);        
    }
    
    @WebMethod(operationName="reporteMiembros")
    public byte[] reporteMiembros(
        @WebParam(name="fechaRegMin") String fechaRegMin,
        @WebParam(name="fechaRegMax") String fechaRegMax,
        @WebParam(name="cantSancionesMin") Integer cantSancionesMin,
        @WebParam(name="cantSancionesMax") Integer cantSancionesMax,
        @WebParam(name="idUniversidad") Integer idUniversidad,
        @WebParam(name="limiteUsuarios") Integer limiteUsuarios,
        @WebParam(name="descripcion") String descripcion ) {
        
        return ReporteUtil.reporteMiembros(null,fechaRegMin,fechaRegMax,cantSancionesMin,cantSancionesMax,idUniversidad,limiteUsuarios,descripcion);        
    }
    
    @WebMethod(operationName="reportePublicaciones")
   public byte[] reportePublicaciones(
        @WebParam(name="cantPrestamosMin") Integer cantPrestamosMin,
        @WebParam(name="cantPrestamosMax") Integer cantPrestamosMax,
        @WebParam(name="incluirVirtuales") Integer incluirVirtuales,
        @WebParam(name="idUniversidad") Integer idUniversidad,
        @WebParam(name="limite") Integer limite,
        @WebParam(name="descripcion") String descripcion
    ) {
        
        return ReporteUtil.reportePublicaciones(null, cantPrestamosMin,            cantPrestamosMax,   incluirVirtuales,   idUniversidad,  limite, descripcion);        
    }
}


