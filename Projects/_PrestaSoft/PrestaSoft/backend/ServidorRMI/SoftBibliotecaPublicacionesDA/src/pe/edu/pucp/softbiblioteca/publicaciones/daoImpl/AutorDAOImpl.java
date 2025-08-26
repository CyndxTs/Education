
/* [/]
 >> Project:    SoftBiblioecaPublicacionesDA
 >> File:       AutorDAOImpl.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.daoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbiblioteca.config.DAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.AutorDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Autor;

public class AutorDAOImpl extends DAOImpl implements AutorDAO{
    private Autor autor;
    
    public AutorDAOImpl() {
        super("Autor");
        this.autor = null;
    }

    @Override
    public Integer insertar(Autor autor) {
        this.autor = autor;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "NOMBRE_COMPLETO";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroString(1,this.autor.getNombreCompleto());
    }

    @Override
    public Integer modificar(Autor autor) {
        this.autor = autor;
        return super.modificar();
    }    
    
    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "NOMBRE_COMPLETO=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroString(1, this.autor.getNombreCompleto());
        this.incluirParametroInt(2, this.autor.getIdAutor());
    }

    @Override
    public Integer eliminar(Autor autor) {
        this.autor = autor;
        return super.eliminar();
    }    
        
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.autor.getIdAutor());
    }
   
    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "ID_AUTOR=?";
    }
    
    @Override
    public ArrayList<Autor> listarTodos(Autor autor, Integer limiteListado) {
        this.autor = autor;
        return (ArrayList<Autor>) super.listarTodos(limiteListado);
    }
    
    @Override
    protected String obtenerProyeccionDeColumnasParaSelect() {
        return "ID_AUTOR, NOMBRE_COMPLETO";
    }
    
    @Override
    public Autor obtenerPorAtributosUnicos(Autor autor){
        this.autor = autor;
        super.obtenerPorAtributosUnicos();
        return this.autor;
    }
    
    @Override
    protected void cargarFiltroParaSelect_Listar() {
        if(this.autor == null) return;
        if(super.vkf.esFiltroValido(this.autor.getNombreCompleto())) {
            super.filtroParaSelect.add("NOMBRE_COMPLETO LIKE '%" +
                                       this.autor.getNombreCompleto() + "%'");
        }
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.autor);
    }

    @Override
    public Autor obtenerPorId(Integer idAutor) {
        this.autor = new Autor();
        this.autor.setIdAutor(idAutor);
        super.obtenerPorId();
        return this.autor;
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.autor.getIdAutor());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException { 
        this.autor = new Autor();
        this.autor.setIdAutor(this.resultSet.getInt("ID_AUTOR"));
        this.autor.setNombreCompleto(this.resultSet.getString("NOMBRE_COMPLETO"));
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.autor = null;
    }
   
    
    @Override
    protected void cargarFiltroParaSelect_Obtener() {
        if(this.autor == null) return;
        if(this.autor.getNombreCompleto() != null || this.autor.getNombreCompleto().isBlank()) {
            super.filtroParaSelect.add("NOMBRE_COMPLETO='" + this.autor.getNombreCompleto()+ "'");
        }
    }
    
}
