
/* [/]
 >> Project:    SoftBibliotecaPrestamosModel
 >> File:       Penalizacion.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.prestamos.model;
import java.io.Serializable;
import java.util.Date;

public class Penalizacion implements Serializable{
    private Integer idPenalizacion;
    private String descripcion;
    private Float monto;
    private Prestamo prestamoAsociado;
    private Date fechaImposicion;
    private Boolean estaActivo;
    
    public Penalizacion() {
        this.idPenalizacion = null;
        this.descripcion = null;
        this.monto = null;
        this.prestamoAsociado = null;
        this.fechaImposicion = null;
        this.estaActivo = null;
    }

    public Penalizacion(Integer idPenalizacion, String descripcion, Float monto,
                        Prestamo prestamoAsociado, Date fechaImposicion, Boolean estaActivo) {
        this.idPenalizacion = idPenalizacion;
        this.descripcion = descripcion;
        this.monto = monto;
        this.prestamoAsociado = prestamoAsociado;
        this.fechaImposicion = fechaImposicion;
        this.estaActivo = estaActivo;
    }
    
    public String consultarDatos() {
        String str = "Prestamo <" + this.getPrestamoAsociado().getIdPrestamo();
        str += "> - with -> Penalizacion <" + this.getIdPenalizacion() + ">";
        return str;
    }

    public Integer getIdPenalizacion() {
        return idPenalizacion;
    }

    public void setIdPenalizacion(Integer idPenalizacion) {
        this.idPenalizacion = idPenalizacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public Prestamo getPrestamoAsociado() {
        return prestamoAsociado;
    }

    public void setPrestamoAsociado(Prestamo prestamoAsociado) {
        this.prestamoAsociado = prestamoAsociado;
    }

    public Date getFechaImposicion() {
        return fechaImposicion;
    }

    public void setFechaImposicion(Date fechaImposicion) {
        this.fechaImposicion = fechaImposicion;
    }

    public Boolean getEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(Boolean estaActivo) {
        this.estaActivo = estaActivo;
    }
}
