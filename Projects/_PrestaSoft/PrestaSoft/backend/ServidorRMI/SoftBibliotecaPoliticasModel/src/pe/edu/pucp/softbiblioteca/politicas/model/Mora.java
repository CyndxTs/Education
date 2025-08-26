
/* [/]
 >> @Project:    SoftBibliotecaLocalidadModel
 >> @File:       Mora.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.politicas.model;

import java.io.Serializable;

public class Mora implements Serializable{
    private Integer idMora;
    private Float monto;
    
    public Mora() {
        this.idMora = null;
        this.monto = null;
    }
    
    public Mora(Integer idMora,Float monto) {
        this.idMora = idMora;
        this.monto = monto;
    }
    
    public String consultarDatos() {
        String str = "<S/." + monto + ">";
        return str;
    }

    public Integer getIdMora() {
        return idMora;
    }

    public void setIdMora(Integer idMora) {
        this.idMora = idMora;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }
}
