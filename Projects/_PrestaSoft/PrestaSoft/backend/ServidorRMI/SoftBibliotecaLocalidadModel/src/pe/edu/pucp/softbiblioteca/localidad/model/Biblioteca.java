
/* [/]
 >> Project:    SoftBibliotecaLocalidadModel
 >> File:       Biblioteca.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.localidad.model;

import java.io.Serializable;

public class Biblioteca implements Serializable{
    private Integer idBiblioteca;
    private String nombre;
    private String ubicacion;
    private Universidad universidadAsociada;
    
    public Biblioteca() {
        this.idBiblioteca = null;
        this.nombre = null;
        this.ubicacion = null;
        this.universidadAsociada = null;
    }
    
    public Biblioteca(Integer idBiblioteca,String nombre,String ubicacion,
                      Universidad universidadAsociada) {
        this.idBiblioteca = idBiblioteca;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.universidadAsociada = universidadAsociada;
    }

    public String consultarDatos() {
        String str = getNombre() + " [" + getUbicacion() + "]";
        return str;
    }

    public Integer getIdBiblioteca() {
        return idBiblioteca;
    }

    public void setIdBiblioteca(Integer idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Universidad getUniversidadAsociada() {
        return universidadAsociada;
    }

    public void setUniversidadAsociada(Universidad universidadAsociada) {
        this.universidadAsociada = universidadAsociada;
    }
}
