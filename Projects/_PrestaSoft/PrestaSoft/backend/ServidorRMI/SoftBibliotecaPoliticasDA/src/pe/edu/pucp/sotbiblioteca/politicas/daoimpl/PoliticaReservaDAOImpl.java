
/* [/]
 >> @Project:    SoftBibliotecaPoliticasDA
 >> @File:       PoliticaReservaDAOImpl.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.sotbiblioteca.politicas.daoimpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbiblioteca.config.DAOImpl;
import pe.edu.pucp.sotbiblioteca.politicas.dao.PoliticaReservaDAO;
import pe.edu.pucp.softbiblioteca.politicas.model.PoliticaReserva;

public class PoliticaReservaDAOImpl extends DAOImpl implements PoliticaReservaDAO {
    PoliticaReserva politicaReserva;
    
    public PoliticaReservaDAOImpl() {
        super("PoliticaReserva");
        this.politicaReserva = null;
    }

    @Override
    public Integer insertar(PoliticaReserva politicaReserva) {
        this.politicaReserva = politicaReserva;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "CANT_MAX_HORAS_RECOJO, CANT_MAX_RESERVAS_AGENDADAS_POR_USUARIO";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return " ?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroInt(1,this.politicaReserva.getCantMaxHorasDeRecojo());
        this.incluirParametroInt(2,this.politicaReserva.getCantMaxReservasAgendadasPorUsuario());
    }

    @Override
    public Integer modificar(PoliticaReserva politicaReserva) {
        this.politicaReserva=politicaReserva;
        return super.modificar();
    }    
    
    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "CANT_MAX_HORAS_RECOJO=?, CANT_MAX_RESERVAS_AGENDADAS_POR_USUARIO=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroInt(1,this.politicaReserva.getCantMaxHorasDeRecojo());
        this.incluirParametroInt(2,this.politicaReserva.getCantMaxReservasAgendadasPorUsuario());
        this.incluirParametroInt(3,this.politicaReserva.getIdPoliticaReserva());
    }

    @Override
    public Integer eliminar(PoliticaReserva politicaReserva) {
        this.politicaReserva = politicaReserva;
        return super.eliminar();
    }    
        
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.politicaReserva.getIdPoliticaReserva());
    }
    
    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "ID_POLITICA_RESERVA=?";
    }
   
    @Override
    public ArrayList<PoliticaReserva> listarTodos(PoliticaReserva politicaReserva, Integer limiteListado) {
        this.politicaReserva = politicaReserva;
        return (ArrayList<PoliticaReserva>) super.listarTodos(limiteListado);
    }
    
    @Override
    protected String obtenerProyeccionDeColumnasParaSelect() {
        return "ID_POLITICA_RESERVA, CANT_MAX_HORAS_RECOJO, CANT_MAX_RESERVAS_AGENDADAS_POR_USUARIO";
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.politicaReserva);
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException { 
        this.politicaReserva = new PoliticaReserva();
        this.politicaReserva.setIdPoliticaReserva(this.resultSet.getInt("ID_POLITICA_RESERVA"));
        this.politicaReserva.setCantMaxHorasDeRecojo(this.resultSet.getInt("CANT_MAX_HORAS_RECOJO"));
        this.politicaReserva.setCantMaxReservasAgendadasPorUsuario(this.resultSet.getInt("CANT_MAX_RESERVAS_AGENDADAS_POR_USUARIO"));
    }

    @Override
    public PoliticaReserva obtenerPorId(Integer idPoliticaReserva) {
        this.politicaReserva = new PoliticaReserva();
        this.politicaReserva.setIdPoliticaReserva(idPoliticaReserva);
        super.obtenerPorId();
        return this.politicaReserva;
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.politicaReserva = null;
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.politicaReserva.getIdPoliticaReserva());
    }
}
