
/* [/]
 >> Project:    SoftBiblioecaPublicacionesModel
 >> File:       Autor.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.model;

import java.io.Serializable;

public class Autor implements Serializable{
    private Integer idAutor;
    private String nombreCompleto;
    
    public Autor() {
        this.idAutor = null;
        this.nombreCompleto = null;
    }
    
    public Autor(Integer idAutor,String nombreCompleto) {
        this.idAutor = idAutor;
        this.nombreCompleto = nombreCompleto;
    }

    public String consultarDatos(){
        return this.nombreCompleto;
    }

    public Integer getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Integer idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
