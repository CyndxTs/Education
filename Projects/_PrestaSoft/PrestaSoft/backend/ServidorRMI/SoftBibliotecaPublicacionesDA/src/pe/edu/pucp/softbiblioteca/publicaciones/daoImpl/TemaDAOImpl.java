
/* [/]
 >> Project:    SoftBiblioecaPublicacionesDA
 >> File:       TemaDAOImpl.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.daoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbiblioteca.config.DAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.TemaDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tema;

public class TemaDAOImpl extends DAOImpl implements TemaDAO{
    private Tema tema;

    public TemaDAOImpl() {
        super("Tema");
        this.tema = null;
    }

    @Override
    public Integer insertar(Tema tema) {
        this.tema = tema;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "TITULO";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroString(1, this.tema.getTitulo());
    }

    @Override
    public Integer modificar(Tema tema) {
        this.tema = tema;
        return super.modificar();
    }
    
    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "TITULO=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroString(1, this.tema.getTitulo());
        this.incluirParametroInt(2, this.tema.getIdTema());
    }

    @Override
    public Integer eliminar(Tema tema) {
        this.tema = tema;
        return super.eliminar();
    }    
        
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.tema.getIdTema());
    }
    
    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "ID_TEMA=?";
    }
   
    @Override
    public ArrayList<Tema> listarTodos(Tema tema, Integer limiteListado) {
        this.tema = tema;
        return (ArrayList<Tema>) super.listarTodos(limiteListado);
    }
    
    @Override
    protected String obtenerProyeccionDeColumnasParaSelect() {
        return "ID_TEMA, TITULO";
    }
    
    @Override
    protected void cargarFiltroParaSelect_Listar(){
        if(this.tema == null) return;
        if(super.vkf.esFiltroValido(this.tema.getTitulo())) {
            super.filtroParaSelect.add("TITULO LIKE '%" +
                                       this.tema.getTitulo() + "%'");
        }
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.tema);
    }

    @Override
    public Tema obtenerPorId(Integer idTema) {
        this.tema = new Tema();
        this.tema.setIdTema(idTema);
        super.obtenerPorId();
        return this.tema;
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.tema.getIdTema());
    }
    
    @Override
    public Tema obtenerPorAtributosUnicos(Tema tema) {
        this.tema = tema;
        super.obtenerPorAtributosUnicos();
        return this.tema;
    }
    
    @Override
    protected void cargarFiltroParaSelect_Obtener(){
        if(this.tema == null) return;
        if(this.tema.getTitulo() != null && !this.tema.getTitulo().isBlank()) {
            super.filtroParaSelect.add("TITULO='" +this.tema.getTitulo() + "'");
        }
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {   
        this.tema = new Tema();
        this.tema.setIdTema(this.resultSet.getInt("ID_TEMA"));
        this.tema.setTitulo(this.resultSet.getString("TITULO"));
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.tema = null;
    }
    @Override
    protected void incluirValorDeParametrosParaObtenerPorAtribtosUnicos() throws SQLException{
        this.incluirParametroString(1, this.tema.getTitulo());
    }
    
   
    
}
