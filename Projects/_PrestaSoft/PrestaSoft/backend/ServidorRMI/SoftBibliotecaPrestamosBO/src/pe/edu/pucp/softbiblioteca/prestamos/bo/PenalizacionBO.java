
/* [/]
 >> Project:    SoftBibliotecaPrestamosBO
 >> File:       PenalizacionBO.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.prestamos.bo;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softbiblioteca.prestamos.dao.PenalizacionDAO;
import pe.edu.pucp.softbiblioteca.prestamos.daoImpl.PenalizacionDAOImpl;
import pe.edu.pucp.softbiblioteca.prestamos.model.Penalizacion;
import pe.edu.pucp.softbiblioteca.prestamos.model.Prestamo;
import pe.edu.pucp.softbiblioteca.util.VKF_Formatter;

public class PenalizacionBO {
    private PenalizacionDAO penalizacionDAO;
    private VKF_Formatter vkf;
    
    public PenalizacionBO(){
        this.penalizacionDAO = new PenalizacionDAOImpl();
        this.vkf = new VKF_Formatter();
    }
    
    public Integer insertar(String descripcion,Float monto,Integer idPrestamoAsociado,String fechaImposicion, Integer estaActivo){
        Prestamo prestamoAsociado = new Prestamo();
        prestamoAsociado.setIdPrestamo(idPrestamoAsociado);
        Date d_fechaImposicion = this.vkf.toValidDate(fechaImposicion);
        Boolean b_estaActivo = this.vkf.toValidBoolean(estaActivo);
        Penalizacion penalizacion = new Penalizacion(null,descripcion, monto,prestamoAsociado,d_fechaImposicion,b_estaActivo);
        return penalizacionDAO.insertar(penalizacion);
    }
    
    public Integer modificar(Integer idPenalizacion,String descripcion,Float monto,Integer idPrestamoAsociado, String fechaImposicion, Integer estaActivo){
        Prestamo prestamoAsociado = new Prestamo();
        prestamoAsociado.setIdPrestamo(idPrestamoAsociado);
        Date d_fechaImposicion = this.vkf.toValidDate(fechaImposicion);
        Boolean b_estaActivo = this.vkf.toValidBoolean(estaActivo);
        Penalizacion penalizacion = new Penalizacion(idPenalizacion,descripcion, monto,prestamoAsociado,d_fechaImposicion,b_estaActivo);
        return penalizacionDAO.modificar(penalizacion);
    }
    
    public Integer eliminar(Integer idPenalizacion){
        Penalizacion penalizacion = new Penalizacion();
        penalizacion.setIdPenalizacion(idPenalizacion);
        return penalizacionDAO.eliminar(penalizacion);
    }
    
    public ArrayList<Penalizacion> listarTodos(Integer idPrestamoAsociado,String fechaImposicion, Integer estaActivo, Integer limite) {
        Penalizacion penalizacion = new Penalizacion();
        penalizacion.setIdPenalizacion(idPrestamoAsociado);
        Date d_fechaImposicion = this.vkf.toValidDate(fechaImposicion);
        penalizacion.setFechaImposicion(d_fechaImposicion);
        Boolean b_estaActivo = this.vkf.toValidBoolean(estaActivo);
        penalizacion.setEstaActivo(b_estaActivo);
        return penalizacionDAO.listarTodos(penalizacion, limite,1); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!PUEDE QUE SEA 2 O 3 POR ALGUNA FUNCION EN OTRO LADO
    }
    
    public Penalizacion obtenerPorId(Integer idPenalizacion){
        return penalizacionDAO.obtenerPorId(idPenalizacion);
    }
    
    public ArrayList<Penalizacion> verPenalizacionesPrestamo(Integer idPrestamoAsociado){
        Penalizacion penalizacion = new Penalizacion();
        Prestamo prestamo = new Prestamo();
        prestamo.setIdPrestamo(idPrestamoAsociado);
        penalizacion.setPrestamoAsociado(prestamo);
        return penalizacionDAO.listarTodos(penalizacion,null,2); 
    }
}
