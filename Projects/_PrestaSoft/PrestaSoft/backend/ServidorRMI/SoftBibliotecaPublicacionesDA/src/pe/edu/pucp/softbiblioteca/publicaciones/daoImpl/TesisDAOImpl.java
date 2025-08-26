
/* [/]
 >> Project:    SoftBiblioecaPublicacionesDA
 >> File:       TesisDAOImpl.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.daoImpl;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softbiblioteca.config.DAOImpl;
import pe.edu.pucp.softbiblioteca.localidad.dao.BibliotecaDAO;
import pe.edu.pucp.softbiblioteca.localidad.daoImpl.BibliotecaDAOImpl;
import pe.edu.pucp.softbiblioteca.localidad.model.Biblioteca;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.TesisDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Autor;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tema;
import pe.edu.pucp.softbiblioteca.publicaciones.model.TipoGrado;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tesis;

public class TesisDAOImpl extends DAOImpl implements TesisDAO{
    private Tesis tesis;
    private BibliotecaDAO bibliotecaDAO;
    public TesisDAOImpl() {
        super("Tesis");
        this.tesis = null; 
        this.bibliotecaDAO = null;
    }

    @Override
    public Integer insertar(Tesis tesis) {
        this.tesis = tesis;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "TITULO, FECHA_PUBLICACION, DESCRIPCION, COPIAS_DISPONIBLES, HAY_FORMATO_FISICO" +
               ", HAY_FORMATO_VIRTUAL, URL, PORTADA, ID_BIBLIOTECA, GRADO_ACADEMICO";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroString(1, this.tesis.getTitulo());
        this.incluirParametroDate(2, this.tesis.getFechaPublicacion());
        this.incluirParametroString(3, this.tesis.getDescripcion());
        this.incluirParametroInt(4, this.tesis.getCopiasDisponibles());
        this.incluirParametroBoolean(5, this.tesis.getHayFormatoFisico());
        this.incluirParametroBoolean(6, this.tesis.getHayFormatoVirtual());
        this.incluirParametroString(7, this.tesis.getUrl());
        this.incluirParametroBytes(8, this.tesis.getPortada());
        this.incluirParametroInt(9, this.tesis.getBibliotecaAsociada().getIdBiblioteca());
        this.incluirParametroString(10, this.tesis.getGradoAcademico().toString());
    }

    @Override
    public Integer modificar(Tesis tesis) {
        this.tesis = tesis;
        return super.modificar();
    }    
    
    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "ID_TESIS=?, TITULO=?, FECHA_PUBLICACION=?, DESCRIPCION=?, COPIAS_DISPONIBLES=?, HAY_FORMATO_FISICO=?" +
               ", HAY_FORMATO_VIRTUAL=?, URL=?, PORTADA=?, ID_BIBLIOTECA=?, GRADO_ACADEMICO=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroInt(1, this.tesis.getIdTesis());
        this.incluirParametroString(2, this.tesis.getTitulo());
        this.incluirParametroDate(3, this.tesis.getFechaPublicacion());
        this.incluirParametroString(4, this.tesis.getDescripcion());
        this.incluirParametroInt(5, this.tesis.getCopiasDisponibles());
        this.incluirParametroBoolean(6, this.tesis.getHayFormatoFisico());
        this.incluirParametroBoolean(7, this.tesis.getHayFormatoVirtual());
        this.incluirParametroString(8, this.tesis.getUrl());
        this.incluirParametroBytes(9, this.tesis.getPortada());
        this.incluirParametroInt(10, this.tesis.getBibliotecaAsociada().getIdBiblioteca());
        this.incluirParametroString(11, this.tesis.getGradoAcademico().toString());
        this.incluirParametroInt(12, this.tesis.getIdTesis());
    }

    @Override
    public Integer eliminar(Tesis tesis) {
        this.tesis = tesis;
        return super.eliminar();
    }    
        
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.tesis.getIdTesis());
    }
    
    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "ID_TESIS=?";
    }
   
    @Override
    public ArrayList<Tesis> listarTodos(Tesis tesis, Integer limiteListado, Integer limiteProfundidad) {
        this.tesis = tesis;
        this.limiteProfundidad = limiteProfundidad;
        return (ArrayList<Tesis>) super.listarTodos(limiteListado);
    }
    
    @Override
    protected String obtenerProyeccionDeColumnasParaSelect() {
        return "ID_TESIS, TITULO, FECHA_PUBLICACION, DESCRIPCION, COPIAS_DISPONIBLES, HAY_FORMATO_FISICO" +
               ", HAY_FORMATO_VIRTUAL, URL, PORTADA, ID_BIBLIOTECA, GRADO_ACADEMICO";
    }
    
    @Override
    protected void cargarFiltroParaSelect_Listar() {
        if(this.tesis == null) return;
        if(super.vkf.esFiltroValido(this.tesis.getTitulo())) {
            super.filtroParaSelect.add("TITULO LIKE '%" +
                                       this.tesis.getTitulo()+ "%'");
        }
        if(this.tesis.getFechaPublicacion() != null) {
            super.filtroParaSelect.add("FECHA_PUBLICACION >= " +
                                       super.vkf.toSqlString(this.tesis.getFechaPublicacion()));
        }
        if(super.vkf.esFiltroValido(this.tesis.getDescripcion())) {
            super.filtroParaSelect.add("DESCRIPCION LIKE '%" +
                                       this.tesis.getDescripcion()+ "%'");
        }
        if(this.tesis.getHayFormatoFisico() != null) {
            super.filtroParaSelect.add("HAY_FORMATO_FISICO = " +
                                       super.vkf.toSqlString(this.tesis.getHayFormatoFisico()));
        }
        if(this.tesis.getHayFormatoVirtual() != null) {
            super.filtroParaSelect.add("HAY_FORMATO_VIRTUAL = " +
                                       super.vkf.toSqlString(this.tesis.getHayFormatoVirtual()));
        }
        if(this.tesis.getBibliotecaAsociada() != null &&
           super.vkf.esFiltroValido(this.tesis.getBibliotecaAsociada().getIdBiblioteca())) {
            super.filtroParaSelect.add("ID_BIBLIOTECA = " +
                                       this.tesis.getBibliotecaAsociada().getIdBiblioteca());
        }
        if(this.tesis.getGradoAcademico() != null) {
            super.filtroParaSelect.add("GRADO_ACADEMICO =" +
                                       super.vkf.toSqlString(this.tesis.getGradoAcademico()));
        }
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.tesis);
    }
    
    @Override
    public Tesis obtenerPorId(Integer idTesis) {
        this.tesis = new Tesis();
        this.tesis.setIdTesis(idTesis);
        super.obtenerPorId();
        return this.tesis;
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.tesis.getIdTesis());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.tesis = new Tesis();
        this.tesis.setIdTesis(this.resultSet.getInt("ID_TESIS"));
        this.tesis.setTitulo(this.resultSet.getString("TITULO"));
        this.tesis.setFechaPublicacion(this.resultSet.getTimestamp("FECHA_PUBLICACION"));
        this.tesis.setDescripcion(this.resultSet.getString("DESCRIPCION"));
        this.tesis.setCopiasDisponibles(this.resultSet.getInt("COPIAS_DISPONIBLES"));
        this.tesis.setHayFormatoFisico(this.resultSet.getBoolean("HAY_FORMATO_FISICO"));
        this.tesis.setHayFormatoVirtual(this.resultSet.getBoolean("HAY_FORMATO_VIRTUAL"));
        this.tesis.setUrl(this.resultSet.getString("URL"));
        this.tesis.setPortada(this.resultSet.getBytes("PORTADA"));
        Integer idBibliotecaAsociada = this.resultSet.getInt("ID_BIBLIOTECA");
        this.tesis.setGradoAcademico(super.vkf.toValidEnum(this.resultSet.getString("GRADO_ACADEMICO"),TipoGrado.class));
        Biblioteca bibliotecaAsociada;
        if(!super.vkf.esFiltroValido(this.limiteProfundidad) ||
           (super.vkf.esFiltroValido(this.profundidadInstanciacion) &&
            this.profundidadInstanciacion < this.limiteProfundidad)){
            this.bibliotecaDAO = new BibliotecaDAOImpl();
            bibliotecaAsociada = this.bibliotecaDAO.obtenerPorId(idBibliotecaAsociada);
        } else {
            bibliotecaAsociada = new Biblioteca();
            bibliotecaAsociada.setIdBiblioteca(idBibliotecaAsociada);
        }
        this.tesis.setBibliotecaAsociada(bibliotecaAsociada);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.tesis = null;
    }
}
