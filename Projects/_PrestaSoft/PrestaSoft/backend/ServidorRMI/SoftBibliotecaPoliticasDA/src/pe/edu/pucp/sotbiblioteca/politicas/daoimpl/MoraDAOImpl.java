
/* [/]
 >> @Project:    SoftBibliotecaPoliticasDA
 >> @File:       MoraDAOImpl.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.sotbiblioteca.politicas.daoimpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbiblioteca.config.DAOImpl;
import pe.edu.pucp.sotbiblioteca.politicas.dao.MoraDAO;
import pe.edu.pucp.softbiblioteca.politicas.model.Mora;

public class MoraDAOImpl extends DAOImpl implements MoraDAO {
    private Mora mora;

    public MoraDAOImpl() {
        super("Mora");
        this.mora = null;
    }

    @Override
    public Integer insertar(Mora mora) {
        this.mora = mora;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "MONTO";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroDouble(1,(double) this.mora.getMonto());
    }

    @Override
    public Integer modificar(Mora mora) {
        this.mora = mora;
        return super.modificar();
    }    
    
    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "MONTO=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroDouble(1,(double) this.mora.getMonto());
        this.incluirParametroInt(2,this.mora.getIdMora());
    }

    @Override
    public Integer eliminar(Mora mora) {
        this.mora = mora;
        return super.eliminar();
    }    
        
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.mora.getIdMora());
    }
    
    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "ID_MORA=?";
    }
   
    @Override
    public ArrayList<Mora> listarTodos(Mora mora, Integer limiteListado) {
        this.mora = mora;
        return (ArrayList<Mora>) super.listarTodos(limiteListado);
    }
    
    @Override
    protected String obtenerProyeccionDeColumnasParaSelect() {
        return "ID_MORA, MONTO";
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.mora);
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException { 
        this.mora = new Mora();
        this.mora.setIdMora(this.resultSet.getInt("ID_MORA"));
        this.mora.setMonto(this.resultSet.getFloat("MONTO"));
    }

    @Override
    public Mora obtenerPorId(Integer idMora) {
        this.mora = new Mora();
        this.mora.setIdMora(idMora);
        super.obtenerPorId();
        return this.mora;
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.mora = null;
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1,this.mora.getIdMora());
    }
}
