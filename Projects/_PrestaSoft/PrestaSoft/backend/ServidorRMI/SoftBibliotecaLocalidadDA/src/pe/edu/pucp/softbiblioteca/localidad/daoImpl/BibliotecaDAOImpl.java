
/* [/]
 >> @Project:    SoftBibliotecaLocalidadDA
 >> @File:       BibliotecaDAOImpl.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.localidad.daoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbiblioteca.config.DAOImpl;
import pe.edu.pucp.softbiblioteca.localidad.dao.BibliotecaDAO;
import pe.edu.pucp.softbiblioteca.localidad.dao.UniversidadDAO;
import pe.edu.pucp.softbiblioteca.localidad.model.Biblioteca;
import pe.edu.pucp.softbiblioteca.localidad.model.Universidad;

public class BibliotecaDAOImpl extends DAOImpl implements BibliotecaDAO {
    private Biblioteca biblioteca;
    private UniversidadDAO universidadDAO;
    
    public BibliotecaDAOImpl() {
        super("Biblioteca");
        this.biblioteca = null;
        this.universidadDAO = null;
    }

    @Override
    public Integer insertar(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "NOMBRE, UBICACION, ID_UNIVERSIDAD";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?, ?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroString(1,this.biblioteca.getNombre());
        this.incluirParametroString(2,this.biblioteca.getUbicacion());
        this.incluirParametroInt(3,this.biblioteca.getUniversidadAsociada().getIdUniversidad());
    }
    
    @Override
    public Integer modificar(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        return super.modificar();
    }    
    
    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "NOMBRE=?, UBICACION=?, ID_UNIVERSIDAD=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroString(1, this.biblioteca.getNombre());
        this.incluirParametroString(2, this.biblioteca.getUbicacion());
        this.incluirParametroInt(3, this.biblioteca.getUniversidadAsociada().getIdUniversidad());
        this.incluirParametroInt(4, this.biblioteca.getIdBiblioteca());
    }

    @Override
    public Integer eliminar(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        return super.eliminar();
    }
        
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.biblioteca.getIdBiblioteca());
    }
    
    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "ID_BIBLIOTECA=?";
    }
   
    @Override
    public ArrayList<Biblioteca> listarTodos(Biblioteca biblioteca,Integer limiteListado,Integer limiteProfundidad) {
        this.biblioteca = biblioteca;
        this.limiteProfundidad = limiteProfundidad;
        return (ArrayList<Biblioteca>) super.listarTodos(limiteListado);
    }
    
    @Override
    protected String obtenerProyeccionDeColumnasParaSelect() {
        return "ID_BIBLIOTECA, NOMBRE, UBICACION, ID_UNIVERSIDAD";
    }
    
    @Override
    protected void cargarFiltroParaSelect_Listar() {
        if(this.biblioteca == null) return;
        if(this.biblioteca.getUniversidadAsociada() != null &&
           super.vkf.esFiltroValido(this.biblioteca.getUniversidadAsociada().getIdUniversidad())) {
            super.filtroParaSelect.add("ID_UNIVERSIDAD = " +
                                       this.biblioteca.getUniversidadAsociada().getIdUniversidad());
        }
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.biblioteca);
    }
    
    @Override
    public Biblioteca obtenerPorId(Integer idBiblioteca) {
        this.biblioteca = new Biblioteca();
        this.biblioteca.setIdBiblioteca(idBiblioteca);
        super.obtenerPorId();
        return this.biblioteca;
    }
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.biblioteca.getIdBiblioteca());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException { 
        this.biblioteca = new Biblioteca();
        this.biblioteca.setIdBiblioteca(this.resultSet.getInt("ID_BIBLIOTECA"));
        this.biblioteca.setNombre(this.resultSet.getString("NOMBRE"));
        this.biblioteca.setUbicacion(this.resultSet.getString("UBICACION"));
        Integer idUniversidadAsociada = this.resultSet.getInt("ID_UNIVERSIDAD");
        Universidad universidadAsociada;
        if(!super.vkf.esFiltroValido(this.limiteProfundidad) ||
           (super.vkf.esFiltroValido(this.profundidadInstanciacion) &&
            this.profundidadInstanciacion < this.limiteProfundidad)){
            this.universidadDAO = new UniversidadDAOImpl();
            universidadAsociada = this.universidadDAO.obtenerPorId(idUniversidadAsociada);
        }else{
            universidadAsociada = new Universidad();
            universidadAsociada.setIdUniversidad(idUniversidadAsociada);
        }
        this.biblioteca.setUniversidadAsociada(universidadAsociada);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.biblioteca = null;
        this.universidadDAO = null;
    }
}
