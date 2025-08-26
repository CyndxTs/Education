
/* [/]
 >> Project:    SoftBibliotecaPrestamosModel
 >> File:       Prestamo.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.prestamos.model;     
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import pe.edu.pucp.softbiblioteca.publicaciones.model.CopiaEjemplar;
import pe.edu.pucp.softbiblioteca.usuarios.model.Usuario;

public class Prestamo implements Serializable{
    private Integer idPrestamo;
    private LocalDateTime instantePrestamo;
    private Date fechaDevolucionEsperada;
    private Date fechaRealDevolucionReal;
    private CopiaEjemplar ejemplarAsociado;
    private TipoEstadoPrestamo tipoEstado;
    private Boolean huboReservaPrevia;
    private Usuario usuarioAsociado;
     
    private String strInstantePrestamo;
    
    public Prestamo() {
        this.idPrestamo = null;
        this.instantePrestamo = null;
        this.fechaDevolucionEsperada = null;
        this.fechaRealDevolucionReal = null;
        this.ejemplarAsociado = null;
        this.tipoEstado = null;
        this.huboReservaPrevia = null;
        this.usuarioAsociado = null;
        this.strInstantePrestamo = null;
    }
    
    public Prestamo(Integer idPrestamo,LocalDateTime instantePrestamo,Date fechaMaximaDevolucion,
                    Date fechaRealDevolucion, CopiaEjemplar ejemplarAsociado,TipoEstadoPrestamo tipoEstado,
                    Boolean huboReservaPrevia,Usuario usuarioAsociado) {
        this.idPrestamo = idPrestamo;
        this.instantePrestamo = instantePrestamo;
        this.fechaDevolucionEsperada = fechaMaximaDevolucion;
        this.fechaRealDevolucionReal = fechaRealDevolucion;
        this.ejemplarAsociado = ejemplarAsociado;
        this.tipoEstado = tipoEstado;
        this.huboReservaPrevia = huboReservaPrevia;
        this.usuarioAsociado = usuarioAsociado;
    }
    

    public String consultarDatos() {
        String str = "idPrestamo: "+ this.getIdPrestamo() + " Usuario <" + this.getUsuarioAsociado().getIdUsuario();
        str += "> - has -> Ejemplar <" + this.getEjemplarAsociado().getIdCopiaEjemplar()+">\n";
        return str;
    }
    
    
    
    public String getStrInstantePrestamo(){
        return this.strInstantePrestamo;
    }
    
    public void setStrInstantePrestamo(String instantePrestamo){
        this.strInstantePrestamo = instantePrestamo;
    }
    

    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public LocalDateTime getInstantePrestamo() {
        return instantePrestamo;
    }

    public void setInstantePrestamo(LocalDateTime instantePrestamo) {
        this.instantePrestamo = instantePrestamo;
    }

    public Date getFechaDevolucionEsperada() {
        return fechaDevolucionEsperada;
    }

    public void setFechaDevolucionEsperada(Date fechaDevolucionEsperada) {
        this.fechaDevolucionEsperada = fechaDevolucionEsperada;
    }
    
    public void setFechaDevolucionReal(Date fechaRealDevolucion){
        this.fechaRealDevolucionReal = fechaRealDevolucion;
    }
    
    public Date getFechaDevolucionReal(){
        return this.fechaRealDevolucionReal;
    }

    public CopiaEjemplar getEjemplarAsociado() {
        return ejemplarAsociado;
    }

    public void setEjemplarAsociado(CopiaEjemplar ejemplarAsociado) {
        this.ejemplarAsociado = ejemplarAsociado;
    }

    public TipoEstadoPrestamo getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(TipoEstadoPrestamo tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    public Boolean isHuboReservaPrevia() {
        return huboReservaPrevia;
    }

    public void setHuboReservaPrevia(Boolean huboReservaPrevia) {
        this.huboReservaPrevia = huboReservaPrevia;
    }

    public Usuario getUsuarioAsociado() {
        return usuarioAsociado;
    }

    public void setUsuarioAsociado(Usuario usuarioAsociado) {
        this.usuarioAsociado = usuarioAsociado;
    }
    
}
