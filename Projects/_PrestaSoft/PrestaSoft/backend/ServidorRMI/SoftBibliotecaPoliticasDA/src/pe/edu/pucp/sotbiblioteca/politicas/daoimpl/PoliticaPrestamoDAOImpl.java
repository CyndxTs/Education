
/* [/]
 >> @Project:    SoftBibliotecaPoliticasDA
 >> @File:       PoliticaPrestamoDAOImpl.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.sotbiblioteca.politicas.daoimpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbiblioteca.config.DAOImpl;
import pe.edu.pucp.softbiblioteca.politicas.model.Mora;
import pe.edu.pucp.sotbiblioteca.politicas.dao.PoliticaPrestamoDAO;
import pe.edu.pucp.softbiblioteca.politicas.model.PoliticaPrestamo;

public class PoliticaPrestamoDAOImpl extends DAOImpl implements PoliticaPrestamoDAO {
    PoliticaPrestamo politicaPrestamo;
    MoraDAOImpl moraDAOImpl;
    
    public PoliticaPrestamoDAOImpl() {
        super("PoliticaPrestamo");
        this.politicaPrestamo = null;
        this.moraDAOImpl =  null;
    }

    @Override
    public Integer insertar(PoliticaPrestamo politicaPrestamo) {
        this.politicaPrestamo = politicaPrestamo;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "ID_MORA,CANT_DIAS_PRESTAMO_REGULAR" +
               ",CANT_MAX_COPIAS_POR_DEVOLVER_POR_USUARIO,CANT_DIAS_AMPLIACION_REGULAR,CANT_MAX_AMPLIACIONES_PERMITIDAS_POR_PRESTAMO" +
               ",CANT_DIAS_PARA_DAR_POR_PERDIDA_UNA_COPIA,CANT_DIAS_SIN_PRESTAMO_POR_ATRASO,CANT_MAX_ATRASOS_POR_CICLO" +
               ",CANT_DIAS_SIN_PRESTAMO_POR_MAL_ESTADO,CARGO_POR_MAL_ESTADO,CANT_MAX_MAL_ESTADO_POR_CICLO" +
               ",CANT_DIAS_SIN_PRESTAMO_POR_PERDIDO,CARGO_POR_PERDIDO,CANT_MAX_PERDIDAS_POR_CICLO";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        if(this.politicaPrestamo.getMoraAsociada() != null &&
           super.vkf.esFiltroValido(this.politicaPrestamo.getMoraAsociada().getIdMora())) {
            this.incluirParametroInt(1,this.politicaPrestamo.getMoraAsociada().getIdMora());
        } else this.incluirParametroInt(1,null);
        this.incluirParametroInt(2,this.politicaPrestamo.getCantDiasPrestamoRegular());
        this.incluirParametroInt(3,this.politicaPrestamo.getCantMaxCopiasPorDevolverPorUsuario());
        this.incluirParametroInt(4,this.politicaPrestamo.getCantDiasDeAmpliacionRegular());
        this.incluirParametroInt(5,this.politicaPrestamo.getCantMaxAmpliacionesPermitidasPorPrestamo());
        this.incluirParametroInt(6,this.politicaPrestamo.getCantDiasParaDarPorPerdidaUnaCopia());
        this.incluirParametroInt(7,this.politicaPrestamo.getCantDiasSinPrestamoPorAtraso());
        this.incluirParametroInt(8,this.politicaPrestamo.getCantMaxAtrasosPorCiclo());
        this.incluirParametroInt(9,this.politicaPrestamo.getCantDiasSinPrestamoPorMalEstado());
        this.incluirParametroDouble(10,(double)this.politicaPrestamo.getCargoPorMalEstado());
        this.incluirParametroInt(11,this.politicaPrestamo.getCantMaxMalEstadoPorCiclo());
        this.incluirParametroInt(12,this.politicaPrestamo.getCantDiasSinPrestamoPorPerdido());
        this.incluirParametroDouble(13,(double)this.politicaPrestamo.getCargoPorPerdido());
        this.incluirParametroInt(14,this.politicaPrestamo.getCantMaxPerdidasPorCiclo());
    }

    @Override
    public Integer modificar(PoliticaPrestamo politicaPrestamo) {
        this.politicaPrestamo=politicaPrestamo;
        return super.modificar();
    }    
    
    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "ID_MORA=?,CANT_DIAS_PRESTAMO_REGULAR=?" +
               ",CANT_MAX_COPIAS_POR_DEVOLVER_POR_USUARIO=?,CANT_DIAS_AMPLIACION_REGULAR=?,CANT_MAX_AMPLIACIONES_PERMITIDAS_POR_PRESTAMO=?" +
               ",CANT_DIAS_PARA_DAR_POR_PERDIDA_UNA_COPIA=?,CANT_DIAS_SIN_PRESTAMO_POR_ATRASO=?,CANT_MAX_ATRASOS_POR_CICLO=?" +
               ",CANT_DIAS_SIN_PRESTAMO_POR_MAL_ESTADO=?,CARGO_POR_MAL_ESTADO=?,CANT_MAX_MAL_ESTADO_POR_CICLO=?" +
               ",CANT_DIAS_SIN_PRESTAMO_POR_PERDIDO=?,CARGO_POR_PERDIDO=?,CANT_MAX_PERDIDAS_POR_CICLO=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        if(this.politicaPrestamo.getMoraAsociada() != null &&
           super.vkf.esFiltroValido(this.politicaPrestamo.getMoraAsociada().getIdMora())) {
            this.incluirParametroInt(1,this.politicaPrestamo.getMoraAsociada().getIdMora());
        } else this.incluirParametroInt(1,null);
        this.incluirParametroInt(2,this.politicaPrestamo.getCantDiasPrestamoRegular());
        this.incluirParametroInt(3,this.politicaPrestamo.getCantMaxCopiasPorDevolverPorUsuario());
        this.incluirParametroInt(4,this.politicaPrestamo.getCantDiasDeAmpliacionRegular());
        this.incluirParametroInt(5,this.politicaPrestamo.getCantMaxAmpliacionesPermitidasPorPrestamo());
        this.incluirParametroInt(6,this.politicaPrestamo.getCantDiasParaDarPorPerdidaUnaCopia());
        this.incluirParametroInt(7,this.politicaPrestamo.getCantDiasSinPrestamoPorAtraso());
        this.incluirParametroInt(8,this.politicaPrestamo.getCantMaxAtrasosPorCiclo());
        this.incluirParametroInt(9,this.politicaPrestamo.getCantDiasSinPrestamoPorMalEstado());
        this.incluirParametroDouble(10,(double)this.politicaPrestamo.getCargoPorMalEstado());
        this.incluirParametroInt(11,this.politicaPrestamo.getCantMaxMalEstadoPorCiclo());
        this.incluirParametroInt(12,this.politicaPrestamo.getCantDiasSinPrestamoPorPerdido());
        this.incluirParametroDouble(13,(double)this.politicaPrestamo.getCargoPorPerdido());
        this.incluirParametroInt(14,this.politicaPrestamo.getCantMaxPerdidasPorCiclo());
        this.incluirParametroInt(15,this.politicaPrestamo.getIdPoliticaPrestamo());
    }

    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "ID_POLITICA_PRESTAMO=?";
    }

    @Override
    public Integer eliminar(PoliticaPrestamo politicaPrestamo) {
        this.politicaPrestamo=politicaPrestamo;
        return super.eliminar();
    }    
        
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.politicaPrestamo.getIdPoliticaPrestamo());
    }
   
    @Override
    public ArrayList<PoliticaPrestamo> listarTodos(PoliticaPrestamo politicaPrestamo, Integer limiteListado, Integer limiteProfundidad) {
        this.politicaPrestamo = politicaPrestamo;
        this.limiteProfundidad = limiteProfundidad;
        return (ArrayList<PoliticaPrestamo>) super.listarTodos(limiteListado);
    }
    
    @Override
    protected String obtenerProyeccionDeColumnasParaSelect() {
        return "ID_POLITICA_PRESTAMO,ID_MORA,CANT_DIAS_PRESTAMO_REGULAR" +
               ",CANT_MAX_COPIAS_POR_DEVOLVER_POR_USUARIO,CANT_DIAS_AMPLIACION_REGULAR,CANT_MAX_AMPLIACIONES_PERMITIDAS_POR_PRESTAMO" +
               ",CANT_DIAS_PARA_DAR_POR_PERDIDA_UNA_COPIA,CANT_DIAS_SIN_PRESTAMO_POR_ATRASO,CANT_MAX_ATRASOS_POR_CICLO" +
               ",CANT_DIAS_SIN_PRESTAMO_POR_MAL_ESTADO,CARGO_POR_MAL_ESTADO,CANT_MAX_MAL_ESTADO_POR_CICLO" +
               ",CANT_DIAS_SIN_PRESTAMO_POR_PERDIDO,CARGO_POR_PERDIDO,CANT_MAX_PERDIDAS_POR_CICLO";
    }
    
    @Override
    protected void cargarFiltroParaSelect_Listar() {
        if(this.politicaPrestamo == null) return;
        if(this.politicaPrestamo.getMoraAsociada() != null &&
           super.vkf.esFiltroValido(this.politicaPrestamo.getMoraAsociada().getIdMora())) {
            super.filtroParaSelect.add("ID_MORA = " +
                                       this.politicaPrestamo.getMoraAsociada().getIdMora());
        }
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.politicaPrestamo);
    }

    @Override
    public PoliticaPrestamo obtenerPorId(Integer idPoliticaPrestamo) {
        this.politicaPrestamo = new PoliticaPrestamo();
        this.politicaPrestamo.setIdPoliticaPrestamo(idPoliticaPrestamo);
        super.obtenerPorId();
        return this.politicaPrestamo;
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.politicaPrestamo.getIdPoliticaPrestamo());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException { 
        this.politicaPrestamo = new PoliticaPrestamo();
        this.politicaPrestamo.setIdPoliticaPrestamo(this.resultSet.getInt("ID_POLITICA_PRESTAMO"));
        Integer idMoraAsociada = this.resultSet.getInt("ID_MORA");
        this.politicaPrestamo.setCantDiasPrestamoRegular(this.resultSet.getInt("CANT_DIAS_PRESTAMO_REGULAR"));
        this.politicaPrestamo.setCantMaxCopiasPorDevolverPorUsuario(this.resultSet.getInt("CANT_MAX_COPIAS_POR_DEVOLVER_POR_USUARIO"));
        this.politicaPrestamo.setCantDiasDeAmpliacionRegular(this.resultSet.getInt("CANT_DIAS_AMPLIACION_REGULAR"));
        this.politicaPrestamo.setCantMaxAmpliacionesPermitidasPorPrestamo(this.resultSet.getInt("CANT_MAX_AMPLIACIONES_PERMITIDAS_POR_PRESTAMO"));
        this.politicaPrestamo.setCantDiasParaDarPorPerdidaUnaCopia(this.resultSet.getInt("CANT_DIAS_PARA_DAR_POR_PERDIDA_UNA_COPIA"));
        this.politicaPrestamo.setCantDiasSinPrestamoPorAtraso(this.resultSet.getInt("CANT_DIAS_SIN_PRESTAMO_POR_ATRASO"));
        this.politicaPrestamo.setCantMaxAtrasosPorCiclo(this.resultSet.getInt("CANT_MAX_ATRASOS_POR_CICLO"));
        this.politicaPrestamo.setCantDiasSinPrestamoPorMalEstado(this.resultSet.getInt("CANT_DIAS_SIN_PRESTAMO_POR_MAL_ESTADO"));
        this.politicaPrestamo.setCargoPorMalEstado(this.resultSet.getFloat("CARGO_POR_MAL_ESTADO"));
        this.politicaPrestamo.setCantMaxMalEstadoPorCiclo(this.resultSet.getInt("CANT_MAX_MAL_ESTADO_POR_CICLO"));
        this.politicaPrestamo.setCantDiasSinPrestamoPorPerdido(this.resultSet.getInt("CANT_DIAS_SIN_PRESTAMO_POR_PERDIDO"));
        this.politicaPrestamo.setCargoPorPerdido(this.resultSet.getFloat("CARGO_POR_PERDIDO"));
        this.politicaPrestamo.setCantMaxPerdidasPorCiclo(this.resultSet.getInt("CANT_MAX_PERDIDAS_POR_CICLO"));
        Mora moraAsociada;
        if(!super.vkf.esFiltroValido(this.limiteProfundidad) ||
           (super.vkf.esFiltroValido(this.profundidadInstanciacion) &&
            this.profundidadInstanciacion < this.limiteProfundidad)){
            if(super.vkf.esFiltroValido(idMoraAsociada)) {
                this.moraDAOImpl = new MoraDAOImpl();
                moraAsociada = this.moraDAOImpl.obtenerPorId(idMoraAsociada);
                this.politicaPrestamo.setMoraAsociada(moraAsociada);
            }
        } else {
            if(super.vkf.esFiltroValido(idMoraAsociada)) {
                moraAsociada = new Mora();
                moraAsociada.setIdMora(idMoraAsociada);
                this.politicaPrestamo.setMoraAsociada(moraAsociada);
            }
        }
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.politicaPrestamo = null;
        this.moraDAOImpl = null;
    }
}
