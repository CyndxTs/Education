
/* [/]
 >> Project:    SoftBiblioecaPublicacionesModel
 >> File:       CopiaEjemplar.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.model;

import java.io.Serializable;

public class CopiaEjemplar implements Serializable{
    private Integer idCopiaEjemplar;
    private String direccionLocal;
    private Publicacion publicacionAsociada;
    private EstadoEjemplar estado;

    public CopiaEjemplar(Integer idCopiaEjemplar,String direccionLocal,Publicacion publicacionAsociada,
                         EstadoEjemplar estado) {
        this.idCopiaEjemplar = idCopiaEjemplar;
        this.direccionLocal = direccionLocal;
        this.publicacionAsociada = publicacionAsociada;
        this.estado = estado;
    }
    
    public CopiaEjemplar() {
        this.idCopiaEjemplar = null;
        this.direccionLocal = null;
        this.publicacionAsociada = null;
        this.estado = null;
    }
    
    public String consultarDatos(){
        String str = "(" + this.estado.toString() + ")";
        str += "[" + this.direccionLocal + "] ";
        str +=  this.publicacionAsociada.getTitulo();
        return str;
    }

    public Integer getIdCopiaEjemplar() {
        return idCopiaEjemplar;
    }

    public void setIdCopiaEjemplar(Integer idCopiaEjemplar) {
        this.idCopiaEjemplar = idCopiaEjemplar;
    }

    public String getDireccionLocal() {
        return direccionLocal;
    }

    public void setDireccionLocal(String direccionLocal) {
        this.direccionLocal = direccionLocal;
    }

    public Publicacion getPublicacionAsociada() {
        return publicacionAsociada;
    }

    public void setPublicacionAsociada(Publicacion publicacionAsociada) {
        this.publicacionAsociada = publicacionAsociada;
    }

    public EstadoEjemplar getEstado() {
        return estado;
    }

    public void setEstado(EstadoEjemplar estado) {
        this.estado = estado;
    }
}
