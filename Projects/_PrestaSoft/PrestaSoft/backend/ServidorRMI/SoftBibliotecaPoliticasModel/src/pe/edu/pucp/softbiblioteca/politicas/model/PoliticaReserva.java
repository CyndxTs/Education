
/* [/]
 >> @Project:    SoftBibliotecaPoliticasEspecificasModel
 >> @File:       PoliticaReserva.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.politicas.model;

import java.io.Serializable;

public class PoliticaReserva implements Serializable{
    private Integer idPoliticaReserva;
    private Integer cantMaxHorasDeRecojo;
    private Integer cantMaxReservasAgendadasPorUsuario;

    public PoliticaReserva() {
        this.idPoliticaReserva = null;
        this.cantMaxHorasDeRecojo = null;
        this.cantMaxReservasAgendadasPorUsuario = null;
    }
        
    public PoliticaReserva(Integer idPoliticaReserva,Integer cantMaxHorasDeRecojo,
                           Integer cantMaxReservasAgendadasPorUsuario) {
        this.idPoliticaReserva = idPoliticaReserva;
        this.cantMaxHorasDeRecojo = cantMaxHorasDeRecojo;
        this.cantMaxReservasAgendadasPorUsuario = cantMaxReservasAgendadasPorUsuario;
    }

    public String consultarDatos() {
        String str = "Plazo maximo de recojo: " + getCantMaxHorasDeRecojo() + " horas";
        return str;
    }

    public Integer getIdPoliticaReserva() {
        return idPoliticaReserva;
    }

    public void setIdPoliticaReserva(Integer idPoliticaReserva) {
        this.idPoliticaReserva = idPoliticaReserva;
    }

    public Integer getCantMaxHorasDeRecojo() {
        return cantMaxHorasDeRecojo;
    }

    public void setCantMaxHorasDeRecojo(Integer cantMaxHorasDeRecojo) {
        this.cantMaxHorasDeRecojo = cantMaxHorasDeRecojo;
    }

    public Integer getCantMaxReservasAgendadasPorUsuario() {
        return cantMaxReservasAgendadasPorUsuario;
    }

    public void setCantMaxReservasAgendadasPorUsuario(Integer cantMaxReservasAgendadasPorUsuario) {
        this.cantMaxReservasAgendadasPorUsuario = cantMaxReservasAgendadasPorUsuario;
    }
}
