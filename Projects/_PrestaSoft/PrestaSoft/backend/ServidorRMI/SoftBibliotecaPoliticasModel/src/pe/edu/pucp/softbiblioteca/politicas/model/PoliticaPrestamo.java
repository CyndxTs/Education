
/* [/]
 >> @Project:    SoftBibliotecaPoliticasEspecificasModel
 >> @File:       PoliticaPrestamo.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.politicas.model;

import java.io.Serializable;

public class PoliticaPrestamo implements Serializable{
    private Integer idPoliticaPrestamo;
    private Mora moraAsociada;
    private Integer cantDiasPrestamoRegular;
    private Integer cantMaxCopiasPorDevolverPorUsuario;
    private Integer cantDiasDeAmpliacionRegular;
    private Integer cantMaxAmpliacionesPermitidasPorPrestamo;
    private Integer cantDiasParaDarPorPerdidaUnaCopia;
    private Integer cantDiasSinPrestamoPorAtraso;
    private Integer cantMaxAtrasosPorCiclo;
    private Integer cantDiasSinPrestamoPorMalEstado;
    private Float cargoPorMalEstado;
    private Integer cantMaxMalEstadoPorCiclo;
    private Integer cantDiasSinPrestamoPorPerdido;
    private Float cargoPorPerdido;
    private Integer cantMaxPerdidasPorCiclo;

    public PoliticaPrestamo() {
        this.idPoliticaPrestamo = null;
        this.moraAsociada = null;
        this.cantDiasPrestamoRegular = null;
        this.cantMaxCopiasPorDevolverPorUsuario = null;
        this.cantDiasDeAmpliacionRegular = null;
        this.cantMaxAmpliacionesPermitidasPorPrestamo = null;
        this.cantDiasParaDarPorPerdidaUnaCopia = null;
        this.cantDiasSinPrestamoPorAtraso = null;
        this.cantMaxAtrasosPorCiclo = null;
        this.cantDiasSinPrestamoPorMalEstado = null;
        this.cargoPorMalEstado = null;
        this.cantMaxMalEstadoPorCiclo = null;
        this.cantDiasSinPrestamoPorPerdido = null;
        this.cargoPorPerdido = null;
        this.cantMaxPerdidasPorCiclo = null;
    }

    public PoliticaPrestamo(Integer idPoliticaPrestamo,Mora moraAsociada,Integer cantDiasPrestamoRegular,
                            Integer cantMaxCopiasPorDevolverPorUsuario,Integer cantDiasDeAmpliacionRegular,Integer cantMaxAmpliacionesPermitidasPorPrestamo,
                            Integer cantDiasParaDarPorPerdidaUnaCopia,Integer cantDiasSinPrestamoPorAtraso,Integer cantMaxAtrasosPorCiclo,
                            Integer cantDiasSinPrestamoPorMalEstado,Float cargoPorMalEstado,Integer cantMaxMalEstadoPorCiclo,
                            Integer cantDiasSinPrestamoPorPerdido,Float cargoPorPerdido,Integer cantMaxPerdidasPorCiclo) {
        this.idPoliticaPrestamo = idPoliticaPrestamo;
        this.moraAsociada = moraAsociada;
        this.cantDiasPrestamoRegular = cantDiasPrestamoRegular;
        this.cantMaxCopiasPorDevolverPorUsuario = cantMaxCopiasPorDevolverPorUsuario;
        this.cantDiasDeAmpliacionRegular = cantDiasDeAmpliacionRegular;
        this.cantMaxAmpliacionesPermitidasPorPrestamo = cantMaxAmpliacionesPermitidasPorPrestamo;
        this.cantDiasParaDarPorPerdidaUnaCopia = cantDiasParaDarPorPerdidaUnaCopia;
        this.cantDiasSinPrestamoPorAtraso = cantDiasSinPrestamoPorAtraso;
        this.cantMaxAtrasosPorCiclo = cantMaxAtrasosPorCiclo;
        this.cantDiasSinPrestamoPorMalEstado = cantDiasSinPrestamoPorMalEstado;
        this.cargoPorMalEstado = cargoPorMalEstado;
        this.cantMaxMalEstadoPorCiclo = cantMaxMalEstadoPorCiclo;
        this.cantDiasSinPrestamoPorPerdido = cantDiasSinPrestamoPorPerdido;
        this.cargoPorPerdido = cargoPorPerdido;
        this.cantMaxPerdidasPorCiclo = cantMaxPerdidasPorCiclo;
    }

    public String consultarDatos() {
        String str = "Plazo de Prestamo Regular: " + getCantDiasPrestamoRegular() + " dias";
        // ...
        return str;
    }

    public Integer getIdPoliticaPrestamo() {
        return idPoliticaPrestamo;
    }

    public void setIdPoliticaPrestamo(Integer idPoliticaPrestamo) {
        this.idPoliticaPrestamo = idPoliticaPrestamo;
    }

    public Mora getMoraAsociada() {
        return moraAsociada;
    }

    public void setMoraAsociada(Mora moraAsociada) {
        this.moraAsociada = moraAsociada;
    }

    public Integer getCantDiasPrestamoRegular() {
        return cantDiasPrestamoRegular;
    }

    public void setCantDiasPrestamoRegular(Integer cantDiasPrestamoRegular) {
        this.cantDiasPrestamoRegular = cantDiasPrestamoRegular;
    }

    public Integer getCantMaxCopiasPorDevolverPorUsuario() {
        return cantMaxCopiasPorDevolverPorUsuario;
    }

    public void setCantMaxCopiasPorDevolverPorUsuario(Integer cantMaxCopiasPorDevolverPorUsuario) {
        this.cantMaxCopiasPorDevolverPorUsuario = cantMaxCopiasPorDevolverPorUsuario;
    }

    public Integer getCantDiasDeAmpliacionRegular() {
        return cantDiasDeAmpliacionRegular;
    }

    public void setCantDiasDeAmpliacionRegular(Integer cantDiasDeAmpliacionRegular) {
        this.cantDiasDeAmpliacionRegular = cantDiasDeAmpliacionRegular;
    }

    public Integer getCantMaxAmpliacionesPermitidasPorPrestamo() {
        return cantMaxAmpliacionesPermitidasPorPrestamo;
    }

    public void setCantMaxAmpliacionesPermitidasPorPrestamo(Integer cantMaxAmpliacionesPermitidasPorPrestamo) {
        this.cantMaxAmpliacionesPermitidasPorPrestamo = cantMaxAmpliacionesPermitidasPorPrestamo;
    }

    public Integer getCantDiasParaDarPorPerdidaUnaCopia() {
        return cantDiasParaDarPorPerdidaUnaCopia;
    }

    public void setCantDiasParaDarPorPerdidaUnaCopia(Integer cantDiasParaDarPorPerdidaUnaCopia) {
        this.cantDiasParaDarPorPerdidaUnaCopia = cantDiasParaDarPorPerdidaUnaCopia;
    }

    public Integer getCantDiasSinPrestamoPorAtraso() {
        return cantDiasSinPrestamoPorAtraso;
    }

    public void setCantDiasSinPrestamoPorAtraso(Integer cantDiasSinPrestamoPorAtraso) {
        this.cantDiasSinPrestamoPorAtraso = cantDiasSinPrestamoPorAtraso;
    }

    public Integer getCantMaxAtrasosPorCiclo() {
        return cantMaxAtrasosPorCiclo;
    }

    public void setCantMaxAtrasosPorCiclo(Integer cantMaxAtrasosPorCiclo) {
        this.cantMaxAtrasosPorCiclo = cantMaxAtrasosPorCiclo;
    }

    public Integer getCantDiasSinPrestamoPorMalEstado() {
        return cantDiasSinPrestamoPorMalEstado;
    }

    public void setCantDiasSinPrestamoPorMalEstado(Integer cantDiasSinPrestamoPorMalEstado) {
        this.cantDiasSinPrestamoPorMalEstado = cantDiasSinPrestamoPorMalEstado;
    }

    public Float getCargoPorMalEstado() {
        return cargoPorMalEstado;
    }

    public void setCargoPorMalEstado(Float cargoPorMalEstado) {
        this.cargoPorMalEstado = cargoPorMalEstado;
    }

    public Integer getCantMaxMalEstadoPorCiclo() {
        return cantMaxMalEstadoPorCiclo;
    }

    public void setCantMaxMalEstadoPorCiclo(Integer cantMaxMalEstadoPorCiclo) {
        this.cantMaxMalEstadoPorCiclo = cantMaxMalEstadoPorCiclo;
    }

    public Integer getCantDiasSinPrestamoPorPerdido() {
        return cantDiasSinPrestamoPorPerdido;
    }

    public void setCantDiasSinPrestamoPorPerdido(Integer cantDiasSinPrestamoPorPerdido) {
        this.cantDiasSinPrestamoPorPerdido = cantDiasSinPrestamoPorPerdido;
    }

    public Float getCargoPorPerdido() {
        return cargoPorPerdido;
    }

    public void setCargoPorPerdido(Float cargoPorPerdido) {
        this.cargoPorPerdido = cargoPorPerdido;
    }

    public Integer getCantMaxPerdidasPorCiclo() {
        return cantMaxPerdidasPorCiclo;
    }

    public void setCantMaxPerdidasPorCiclo(Integer cantMaxPerdidasPorCiclo) {
        this.cantMaxPerdidasPorCiclo = cantMaxPerdidasPorCiclo;
    }
}
