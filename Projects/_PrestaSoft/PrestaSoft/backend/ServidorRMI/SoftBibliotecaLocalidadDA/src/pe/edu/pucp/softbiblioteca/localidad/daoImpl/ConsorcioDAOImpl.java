
/* [/]
 >> @Project:    SoftBibliotecaLocalidadModelDA
 >> @File:       ConsorcioDAOImpl.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.localidad.daoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbiblioteca.config.DAOImpl;
import pe.edu.pucp.softbiblioteca.localidad.dao.ConsorcioDAO;
import pe.edu.pucp.softbiblioteca.localidad.model.Consorcio;

public class ConsorcioDAOImpl extends DAOImpl implements ConsorcioDAO {
    private Consorcio consorcio;

    public ConsorcioDAOImpl() {
        super("Consorcio");
        this.consorcio = null;
    }

    @Override
    public Integer insertar(Consorcio consorcio) {
        this.consorcio = consorcio;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "CIF";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroString(1, this.consorcio.getCIF());
    }

    @Override
    public Integer modificar(Consorcio consorcio) {
        this.consorcio = consorcio;
        return super.modificar();
    }    
    
    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "CIF=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroString(1, this.consorcio.getCIF());
        this.incluirParametroInt(2, this.consorcio.getIdConsorcio());
    }

    @Override
    public Integer eliminar(Consorcio consorcio) {
        this.consorcio = consorcio;
        return super.eliminar();
    }    
        
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.consorcio.getIdConsorcio());
    }
    
    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "ID_CONSORCIO=?";
    }

    @Override
    public ArrayList<Consorcio> listarTodos(Consorcio consorcio,Integer limiteListado) {
        this.consorcio = consorcio;
        return (ArrayList<Consorcio>) super.listarTodos(limiteListado);
    }
    
    @Override
    protected String obtenerProyeccionDeColumnasParaSelect() {
        return "ID_CONSORCIO, CIF";
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.consorcio);
    }

    @Override
    public Consorcio obtenerPorId(Integer idConsorcio) {
        this.consorcio = new Consorcio();
        this.consorcio.setIdConsorcio(idConsorcio);
        super.obtenerPorId();
        return this.consorcio;
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.consorcio.getIdConsorcio());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException { 
        this.consorcio = new Consorcio();
        this.consorcio.setIdConsorcio(this.resultSet.getInt("ID_CONSORCIO"));
        this.consorcio.setCIF(this.resultSet.getString("CIF"));
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.consorcio = null;
    }
}
