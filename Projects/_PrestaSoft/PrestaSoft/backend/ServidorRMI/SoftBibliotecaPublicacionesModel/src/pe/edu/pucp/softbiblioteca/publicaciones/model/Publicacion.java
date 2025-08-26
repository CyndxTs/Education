    
/* [/]
 >> Project:    SoftBiblioecaPublicacionesModel
 >> File:       Publicacion.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.model;
import java.io.Serializable;
import java.util.Date;
import pe.edu.pucp.softbiblioteca.localidad.model.Biblioteca;

public abstract class Publicacion implements Serializable{
    private String titulo;
    private Date fechaPublicacion;
    private String descripcion;
    private Integer copiasDisponibles;
    private Boolean hayFormatoFisico;
    private Boolean hayFormatoVirtual;
    private String url;
    private byte[] portada;  
    private Biblioteca bibliotecaAsociada;

    public Publicacion(String titulo, Date fechaPublicacion, String descripcion,
                       Integer copiasDisponibles, Boolean hayFormatoFisico, Boolean hayFormatoVirtual,
                       String url, byte[] portada, Biblioteca bibliotecaAsociada) {
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;
        this.descripcion = descripcion;
        this.copiasDisponibles = copiasDisponibles;
        this.hayFormatoFisico = hayFormatoFisico;
        this.hayFormatoVirtual = hayFormatoVirtual;
        this.url = url;
        this.portada = portada;
        this.bibliotecaAsociada = bibliotecaAsociada;
    }
    
    public Publicacion() {
        this.titulo = null;
        this.fechaPublicacion = null;
        this.descripcion = null;
        this.copiasDisponibles = null;
        this.hayFormatoFisico = null;
        this.hayFormatoVirtual = null;
        this.url = null;
        this.portada = null;
        this.bibliotecaAsociada = null;
    }
    
    public abstract String consultarDatos();
    
    public abstract Integer getIdPublicacion();

    public abstract void setIdPublicacion(Integer idPublicacion);

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCopiasDisponibles() {
        return copiasDisponibles;
    }

    public void setCopiasDisponibles(Integer copiasDisponibles) {
        this.copiasDisponibles = copiasDisponibles;
    }

    public Boolean getHayFormatoFisico() {
        return hayFormatoFisico;
    }

    public void setHayFormatoFisico(Boolean hayFormatoFisico) {
        this.hayFormatoFisico = hayFormatoFisico;
    }

    public Boolean getHayFormatoVirtual() {
        return hayFormatoVirtual;
    }

    public void setHayFormatoVirtual(Boolean hayFormatoVirtual) {
        this.hayFormatoVirtual = hayFormatoVirtual;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getPortada() {
        return portada;
    }

    public void setPortada(byte[] portada) {
        this.portada = portada;
    }

    public Biblioteca getBibliotecaAsociada() {
        return bibliotecaAsociada;
    }

    public void setBibliotecaAsociada(Biblioteca bibliotecaAsociada) {
        this.bibliotecaAsociada = bibliotecaAsociada;
    }
}
