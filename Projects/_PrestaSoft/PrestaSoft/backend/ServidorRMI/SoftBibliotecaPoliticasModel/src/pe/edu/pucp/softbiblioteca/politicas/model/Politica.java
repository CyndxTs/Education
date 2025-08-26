
/* [/]
 >> Project:    SoftBibliotecaLocalidadModel
 >> File:       Politica.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.politicas.model;

import java.io.Serializable;

public class Politica implements Serializable{
    private Integer idPolitica;
    private String etiqueta;
    private PoliticaPrestamo politicasPorPrestamo;
    private PoliticaReserva politicasPorReserva;
    
    public Politica() {
        this.idPolitica = null;
        this.etiqueta = null;
        this.politicasPorPrestamo = null;
        this.politicasPorReserva = null;
    }
    
    public Politica(Integer idPolitica,String etiqueta,PoliticaPrestamo politicasPorPrestamo,
                    PoliticaReserva politicasPorReserva) {
        this.idPolitica = idPolitica;
        this.etiqueta = etiqueta;
        this.politicasPorPrestamo = politicasPorPrestamo;
        this.politicasPorReserva = politicasPorReserva;
    }

    public String consultarDatos() {
        String str = "POLITICA '" + this.getEtiqueta() +"'";
        return str;
    }

    public Integer getIdPolitica() {
        return idPolitica;
    }

    public void setIdPolitica(Integer idPolitica) {
        this.idPolitica = idPolitica;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public PoliticaPrestamo getPoliticasPorPrestamo() {
        return politicasPorPrestamo;
    }

    public void setPoliticasPorPrestamo(PoliticaPrestamo politicasPorPrestamo) {
        this.politicasPorPrestamo = politicasPorPrestamo;
    }

    public PoliticaReserva getPoliticasPorReserva() {
        return politicasPorReserva;
    }

    public void setPoliticasPorReserva(PoliticaReserva politicasPorReserva) {
        this.politicasPorReserva = politicasPorReserva;
    }
}
