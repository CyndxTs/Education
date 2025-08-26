
/* [/]
 >> @Project:    SoftBibliotecaPoliticasDA
 >> @File:       PoliticaDAOImpl.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.sotbiblioteca.politicas.daoimpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbiblioteca.config.DAOImpl;
import pe.edu.pucp.sotbiblioteca.politicas.dao.PoliticaDAO;
import pe.edu.pucp.softbiblioteca.politicas.model.Politica;
import pe.edu.pucp.softbiblioteca.politicas.model.PoliticaPrestamo;
import pe.edu.pucp.softbiblioteca.politicas.model.PoliticaReserva;
import pe.edu.pucp.sotbiblioteca.politicas.dao.PoliticaPrestamoDAO;
import pe.edu.pucp.sotbiblioteca.politicas.dao.PoliticaReservaDAO;

public class PoliticaDAOImpl extends DAOImpl implements PoliticaDAO {
    private Politica politica;
    private PoliticaPrestamoDAO politicaPrestamoDAO;
    private PoliticaReservaDAO politicaReservaDAO;
    
    public PoliticaDAOImpl() {
        super("Politica");
        this.politica = null;
        this.politicaPrestamoDAO = null;
        this.politicaReservaDAO = null;
    }

    @Override
    public Integer insertar(Politica politica) {
        this.politica = politica;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "ETIQUETA, ID_POLITICA_PRESTAMO, ID_POLITICA_RESERVA";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?, ?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroString(1,this.politica.getEtiqueta());
        if(this.politica.getPoliticasPorPrestamo() != null &&
           super.vkf.esFiltroValido(this.politica.getPoliticasPorPrestamo().getIdPoliticaPrestamo())) {
            this.incluirParametroInt(2,this.politica.getPoliticasPorPrestamo().getIdPoliticaPrestamo());
        } else this.incluirParametroInt(2,null);
        if(this.politica.getPoliticasPorReserva() != null &&
           super.vkf.esFiltroValido(this.politica.getPoliticasPorReserva().getIdPoliticaReserva())) {
            this.incluirParametroInt(3,this.politica.getPoliticasPorReserva().getIdPoliticaReserva());
        } else this.incluirParametroInt(3,null);
    }

    @Override
    public Integer modificar(Politica politica) {
        this.politica = politica;
        return super.modificar();
    }    
    
    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "ETIQUETA=?, ID_POLITICA_PRESTAMO=?, ID_POLITICA_RESERVA=? ";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroString(1,this.politica.getEtiqueta());
        if(this.politica.getPoliticasPorPrestamo() != null &&
           super.vkf.esFiltroValido(this.politica.getPoliticasPorPrestamo().getIdPoliticaPrestamo())) {
            this.incluirParametroInt(2,this.politica.getPoliticasPorPrestamo().getIdPoliticaPrestamo());
        } else this.incluirParametroInt(2,null);
        if(this.politica.getPoliticasPorReserva() != null &&
           super.vkf.esFiltroValido(this.politica.getPoliticasPorReserva().getIdPoliticaReserva())) {
            this.incluirParametroInt(3,this.politica.getPoliticasPorReserva().getIdPoliticaReserva());
        } else this.incluirParametroInt(3,null);
        this.incluirParametroInt(4,this.politica.getIdPolitica());
    }

    @Override
    public Integer eliminar(Politica politica) {
        this.politica = politica;
        return super.eliminar();
    }    
        
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.politica.getIdPolitica());
    }
    
    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "ID_POLITICA=?";
    }
   
    @Override
    public ArrayList<Politica> listarTodos(Politica politica, Integer limiteListado, Integer limiteProfundidad) {
        this.politica = politica;
        this.limiteProfundidad = limiteProfundidad;
        return (ArrayList<Politica>) super.listarTodos(limiteListado);
    }
    
    @Override
    protected String obtenerProyeccionDeColumnasParaSelect() {
        return "ID_POLITICA, ETIQUETA, ID_POLITICA_PRESTAMO, ID_POLITICA_RESERVA";
    }
    
    @Override
    protected void cargarFiltroParaSelect_Listar() {
        if(this.politica == null) return;
        if(this.politica.getPoliticasPorPrestamo() != null && 
           super.vkf.esFiltroValido(this.politica.getPoliticasPorPrestamo().getIdPoliticaPrestamo())) {
            super.filtroParaSelect.add("ID_POLITICA_PRESTAMO=" + this.politica.getPoliticasPorPrestamo().getIdPoliticaPrestamo());
        }
        if(this.politica.getPoliticasPorReserva()!= null && 
           super.vkf.esFiltroValido(this.politica.getPoliticasPorReserva().getIdPoliticaReserva())) {
            super.filtroParaSelect.add("ID_POLITICA_RESERVA=" + this.politica.getPoliticasPorReserva().getIdPoliticaReserva());
        }
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.politica);
    }

    @Override
    public Politica obtenerPorId(Integer idPolitica) {
        this.politica = new Politica();
        this.politica.setIdPolitica(idPolitica);
        super.obtenerPorId();
        return this.politica;
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.politica.getIdPolitica());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException { 
        this.politica = new Politica();
        this.politica.setIdPolitica(this.resultSet.getInt("ID_POLITICA"));
        this.politica.setEtiqueta(this.resultSet.getString("ETIQUETA"));
        Integer idPoliticaPrestamo = this.resultSet.getInt("ID_POLITICA_PRESTAMO");
        Integer idPoliticaReserva = this.resultSet.getInt("ID_POLITICA_RESERVA");
        PoliticaPrestamo politicasPorPrestamo;
        PoliticaReserva politicasPorReserva;
        if(!super.vkf.esFiltroValido(this.limiteProfundidad) ||
           (super.vkf.esFiltroValido(this.profundidadInstanciacion) &&
            this.profundidadInstanciacion < this.limiteProfundidad)){
            this.politicaPrestamoDAO = new PoliticaPrestamoDAOImpl();
            politicasPorPrestamo = this.politicaPrestamoDAO.obtenerPorId(idPoliticaPrestamo);
            this.politicaReservaDAO = new PoliticaReservaDAOImpl();
            politicasPorReserva = this.politicaReservaDAO.obtenerPorId(idPoliticaReserva);
        }else{
            politicasPorPrestamo = new PoliticaPrestamo();
            politicasPorPrestamo.setIdPoliticaPrestamo(idPoliticaPrestamo);
            politicasPorReserva = new PoliticaReserva();
            politicasPorReserva.setIdPoliticaReserva(idPoliticaReserva);
        }
        this.politica.setPoliticasPorPrestamo(politicasPorPrestamo);
        this.politica.setPoliticasPorReserva(politicasPorReserva);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.politica = null;
        this.politicaPrestamoDAO = null;
        this.politicaReservaDAO = null;
    }
}
