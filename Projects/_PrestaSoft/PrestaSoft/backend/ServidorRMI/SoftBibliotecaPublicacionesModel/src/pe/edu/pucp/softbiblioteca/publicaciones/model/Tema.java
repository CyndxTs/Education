
/* [/]
 >> Project:    SoftBiblioecaPublicacionesModel
 >> File:       Tema.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.model;

import java.io.Serializable;

public class Tema implements Serializable{
    private Integer idTema;
    private String titulo;
    
    public Tema(Integer idTema,String titulo) {
        this.idTema = idTema;
        this.titulo = titulo;
    }
    
    public Tema() {
        this.idTema = null;
        this.titulo = null;
    }
    
    public String consultarDatos(){
        return this.titulo;
    }

    public Integer getIdTema() {
        return idTema;
    }

    public void setIdTema(Integer idTema) {
        this.idTema = idTema;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
