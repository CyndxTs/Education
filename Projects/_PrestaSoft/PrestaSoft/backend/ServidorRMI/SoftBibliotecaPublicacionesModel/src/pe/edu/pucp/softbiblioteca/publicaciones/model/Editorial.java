
/* [/]
 >> Project:    SoftBiblioecaPublicacionesModel
 >> File:       Editorial.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.model;

import java.io.Serializable;

public class Editorial implements Serializable{
    private Integer idEditorial;
    private String nombre;
    
    public Editorial(Integer idEditorial,String nombre) {
        this.idEditorial = idEditorial;
        this.nombre = nombre;
    }
    
    public Editorial() {
        this.idEditorial = null;
        this.nombre = null;
    }
    
    public String consultarDatos() {
        return this.nombre;
    }

    public Integer getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(Integer idEditorial) {
        this.idEditorial = idEditorial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
