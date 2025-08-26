
/* [/]
 >> @Project:    SoftBibliotecaLocalidadModel
 >> @File:       Consorcio.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.localidad.model;

import java.io.Serializable;

public class Consorcio implements Serializable{
    private Integer idConsorcio;
    private String CIF;
    
    public Consorcio() {
        this.idConsorcio = null;
        this.CIF = null;
    }
    
    public Consorcio(Integer idConsorcio,String CIF) {
        this.idConsorcio = idConsorcio;
        this.CIF = CIF;
    }
    
    public String consultarDatos() {
        return "<" + getCIF() + ">";
    }

    public Integer getIdConsorcio() {
        return idConsorcio;
    }

    public void setIdConsorcio(Integer idConsorcio) {
        this.idConsorcio = idConsorcio;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }
}
