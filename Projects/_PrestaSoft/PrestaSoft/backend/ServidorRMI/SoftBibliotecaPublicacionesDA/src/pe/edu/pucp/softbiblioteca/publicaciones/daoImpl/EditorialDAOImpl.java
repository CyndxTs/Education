
/* [/]
 >> Project:    SoftBiblioecaPublicacionesDA
 >> File:       EditorialDAOImpl.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.daoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbiblioteca.config.DAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.EditorialDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Editorial;


public class EditorialDAOImpl extends DAOImpl implements EditorialDAO{
    private Editorial editorial;

    public EditorialDAOImpl() {
        super("Editorial");
        this.editorial = null;
    }
    
    @Override
    public Integer insertar(Editorial editorial) {
        this.editorial = editorial;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }
    
    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "NOMBRE";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroString(1, this.editorial.getNombre());
    }
    
    @Override
    public Integer modificar(Editorial editorial) {
        this.editorial = editorial;
        return super.modificar();
    }
    
    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "NOMBRE=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroString(1, this.editorial.getNombre());
        this.incluirParametroInt(2, this.editorial.getIdEditorial());
    }
    
    @Override
    public Integer eliminar(Editorial editorial) {
        this.editorial = editorial;
        return super.eliminar();
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.editorial.getIdEditorial());
    }
    
    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "ID_EDITORIAL=?";
    }
    
    @Override
    public ArrayList<Editorial> listarTodos(Editorial editorial, Integer limiteListado) {
        this.editorial = editorial;
        return (ArrayList<Editorial>) super.listarTodos(limiteListado);
    }
    
    @Override
    protected String obtenerProyeccionDeColumnasParaSelect() {
        return "ID_EDITORIAL, NOMBRE";
    }
    
    @Override
    protected void cargarFiltroParaSelect_Listar() {
        if(this.editorial == null) return;
        if(super.vkf.esFiltroValido(this.editorial.getNombre())) {
            super.filtroParaSelect.add("NOMBRE LIKE '%" +
                                       this.editorial.getNombre()+ "%'");
        }
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.editorial);
    }

    @Override
    public Editorial obtenerPorId(Integer idEditorial) {
        this.editorial = new Editorial();
        this.editorial.setIdEditorial(idEditorial);
        super.obtenerPorId();        
        return this.editorial;
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.editorial.getIdEditorial());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.editorial = new Editorial();
        this.editorial.setIdEditorial(this.resultSet.getInt("ID_EDITORIAL"));
        this.editorial.setNombre(this.resultSet.getString("NOMBRE"));
    }    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.editorial = null;
    }
}
