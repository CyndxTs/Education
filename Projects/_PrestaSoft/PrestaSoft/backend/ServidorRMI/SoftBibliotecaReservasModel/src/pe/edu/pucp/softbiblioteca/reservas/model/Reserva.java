
/* [/]
 >> Project:    SoftBiblioecaReservasModel
 >> File:       Reserva.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.reservas.model;
import java.io.Serializable;
import pe.edu.pucp.softbiblioteca.usuarios.model.Usuario;
import java.time.LocalDateTime;
import pe.edu.pucp.softbiblioteca.publicaciones.model.CopiaEjemplar;

public class Reserva implements Serializable{
    private Integer idReserva;
    private LocalDateTime instanteReserva;
    private LocalDateTime instanteRecojo;
    private TipoEstadoReserva tipoEstado; 
    private CopiaEjemplar ejemplarAsociado;
    private Usuario usuarioAsociado;
    private String str_instanteReserva;
    private String str_instanteRecojo;
    
    public Reserva() {
        this.idReserva = null;
        this.instanteReserva = null;
        this.instanteRecojo = null;
        this.tipoEstado = null;
        this.ejemplarAsociado = null;
        this.usuarioAsociado = null;
        this.str_instanteReserva = null;
        this.str_instanteRecojo = null;
    }
    
    public Reserva(Integer idReserva, LocalDateTime instanteReserva,LocalDateTime instanteRecojo,
                   TipoEstadoReserva tipoEstado,CopiaEjemplar ejemplarAsociado,Usuario usuarioAsociado) {
        this.idReserva = idReserva;
        this.instanteReserva = instanteReserva;
        this.instanteRecojo = instanteRecojo;
        this.tipoEstado = tipoEstado;
        this.ejemplarAsociado = ejemplarAsociado;
        this.usuarioAsociado = usuarioAsociado;
    }
    
    public String consultarDatos() {
        String str = "Reservado [" + instanteReserva.toString() + "]";
        str += "{" + tipoEstado.toString() + "}\n";
        return str;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public LocalDateTime getInstanteReserva() {
        return instanteReserva;
    }

    public void setInstanteReserva(LocalDateTime instanteReserva) {
        this.instanteReserva = instanteReserva;
    }

    public LocalDateTime getInstanteRecojo() {
        return instanteRecojo;
    }

    public void setInstanteRecojo(LocalDateTime instanteRecojo) {
        this.instanteRecojo = instanteRecojo;
    }

    public TipoEstadoReserva getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(TipoEstadoReserva tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    public CopiaEjemplar getEjemplarAsociado() {
        return ejemplarAsociado;
    }

    public void setEjemplarAsociado(CopiaEjemplar ejemplarAsociado) {
        this.ejemplarAsociado = ejemplarAsociado;
    }

    public Usuario getUsuarioAsociado() {
        return usuarioAsociado;
    }

    public void setUsuarioAsociado(Usuario usuarioAsociado) {
        this.usuarioAsociado = usuarioAsociado;
    }

    public String getStr_instanteReserva() {
        return str_instanteReserva;
    }

    public void setStr_instanteReserva(String str_instanteReserva) {
        this.str_instanteReserva = str_instanteReserva;
    }

    public String getStr_instanteRecojo() {
        return str_instanteRecojo;
    }

    public void setStr_instanteRecojo(String str_instanteRecojo) {
        this.str_instanteRecojo = str_instanteRecojo;
    }
}
