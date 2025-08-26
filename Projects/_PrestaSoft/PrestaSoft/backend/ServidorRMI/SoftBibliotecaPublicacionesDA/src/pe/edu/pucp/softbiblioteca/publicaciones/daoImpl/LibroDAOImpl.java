
/* [/]
 >> Project:    SoftBiblioecaPublicacionesDA
 >> File:       LibroDAOImpl.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.daoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbiblioteca.config.DAOImpl;
import pe.edu.pucp.softbiblioteca.localidad.dao.BibliotecaDAO;
import pe.edu.pucp.softbiblioteca.localidad.daoImpl.BibliotecaDAOImpl;
import pe.edu.pucp.softbiblioteca.localidad.model.Biblioteca;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.EditorialDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.LibroDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Editorial;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Libro;
import pe.edu.pucp.softbiblioteca.publicaciones.model.TipoLibro;

public class LibroDAOImpl extends DAOImpl implements LibroDAO{
    private Libro libro;
    private BibliotecaDAO bibliotecaDAO;
    private EditorialDAO editorialDAO;
    public LibroDAOImpl() {
        super("Libro");
        this.libro = null;
        this.bibliotecaDAO = null;
        this.editorialDAO = null;
    }

    @Override
    public Integer insertar(Libro libro) {
        this.libro = libro;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "TITULO, FECHA_PUBLICACION, DESCRIPCION, COPIAS_DISPONIBLES, HAY_FORMATO_FISICO" +
               ", HAY_FORMATO_VIRTUAL, URL, PORTADA, ID_BIBLIOTECA, ISBN, ID_EDITORIAL" +
               ", TIPO, VOLUMEN, MATERIA, GENERO, TOMO";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroString(1,this.libro.getTitulo());
        this.incluirParametroDate(2,this.libro.getFechaPublicacion());
        this.incluirParametroString(3,this.libro.getDescripcion());
        this.incluirParametroInt(4,this.libro.getCopiasDisponibles());
        this.incluirParametroBoolean(5,this.libro.getHayFormatoFisico());
        this.incluirParametroBoolean(6,this.libro.getHayFormatoVirtual());
        this.incluirParametroString(7,this.libro.getUrl());
        this.incluirParametroBytes(8,this.libro.getPortada());
        this.incluirParametroInt(9,this.libro.getBibliotecaAsociada().getIdBiblioteca());
        this.incluirParametroInt(10,this.libro.getISBN());
        this.incluirParametroInt(11,this.libro.getEditorial().getIdEditorial());
        this.incluirParametroString(12,this.libro.getTipo().toString());
        this.incluirParametroInt(13,this.libro.getVolumen());
        this.incluirParametroString(14,this.libro.getMateria());
        this.incluirParametroString(15,this.libro.getGenero());
        this.incluirParametroInt(16,this.libro.getTomo());
    }

    @Override
    public Integer modificar(Libro libro) {
        this.libro = libro;
        return super.modificar();
    }    
    
    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "TITULO=?, FECHA_PUBLICACION=?, DESCRIPCION=?, COPIAS_DISPONIBLES=?" +
               ", HAY_FORMATO_FISICO=?, HAY_FORMATO_VIRTUAL=?, URL=?, PORTADA=?" +
               ", ID_BIBLIOTECA=?, ISBN=?, ID_EDITORIAL=?, TIPO=?, VOLUMEN=?, MATERIA=?" +
               ", GENERO=?, TOMO=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroString(1,this.libro.getTitulo());
        this.incluirParametroDate(2,this.libro.getFechaPublicacion());
        this.incluirParametroString(3,this.libro.getDescripcion());
        this.incluirParametroInt(4,this.libro.getCopiasDisponibles());
        this.incluirParametroBoolean(5,this.libro.getHayFormatoFisico());
        this.incluirParametroBoolean(6,this.libro.getHayFormatoVirtual());
        this.incluirParametroString(7,this.libro.getUrl());
        this.incluirParametroBytes(8,this.libro.getPortada());
        this.incluirParametroInt(9,this.libro.getBibliotecaAsociada().getIdBiblioteca());
        this.incluirParametroInt(10,this.libro.getISBN());
        this.incluirParametroInt(11,this.libro.getEditorial().getIdEditorial());
        this.incluirParametroString(12,this.libro.getTipo().toString());
        this.incluirParametroInt(13,this.libro.getVolumen());
        this.incluirParametroString(14,this.libro.getMateria());
        this.incluirParametroString(15,this.libro.getGenero());
        this.incluirParametroInt(16,this.libro.getTomo());
        this.incluirParametroInt(17, this.libro.getIdLibro());
    }

    @Override
    public Integer eliminar(Libro libro) {
        this.libro = libro;
        return super.eliminar();
    }    
        
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.libro.getIdLibro());
    }
    
    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "ID_LIBRO=?";
    }
   
    @Override
    public ArrayList<Libro> listarTodos(Libro libro, Integer limiteListado, Integer limiteProfundidad) {
        this.libro = libro;
        this.limiteProfundidad = limiteProfundidad;
        return (ArrayList<Libro>) super.listarTodos(limiteListado);
    }
    
    @Override
    protected String obtenerProyeccionDeColumnasParaSelect() {
        return "ID_LIBRO, TITULO, FECHA_PUBLICACION, DESCRIPCION, COPIAS_DISPONIBLES, HAY_FORMATO_FISICO" +
               ", HAY_FORMATO_VIRTUAL, URL, PORTADA, ID_BIBLIOTECA, ISBN, ID_EDITORIAL" +
               ", TIPO, VOLUMEN, MATERIA, GENERO, TOMO";
    }
    
    @Override
    protected void cargarFiltroParaSelect_Listar() {
        if(this.libro == null) return;
        if(super.vkf.esFiltroValido(this.libro.getTitulo())) {
            super.filtroParaSelect.add("TITULO LIKE '%" +
                                       this.libro.getTitulo()+ "%'");
        }
        if(this.libro.getFechaPublicacion() != null) {
            super.filtroParaSelect.add("FECHA_PUBLICACION >= " +
                                       super.vkf.toSqlString(this.libro.getFechaPublicacion()));
        }
        if(super.vkf.esFiltroValido(this.libro.getDescripcion())) {
            super.filtroParaSelect.add("DESCRIPCION LIKE '%" +
                                       this.libro.getDescripcion()+ "%'");
        }
        if(this.libro.getHayFormatoFisico() != null) {
            super.filtroParaSelect.add("HAY_FORMATO_FISICO = " +
                                       super.vkf.toSqlString(this.libro.getHayFormatoFisico()));
        }
        if(this.libro.getHayFormatoVirtual() != null) {
            super.filtroParaSelect.add("HAY_FORMATO_VIRTUAL = " +
                                       super.vkf.toSqlString(this.libro.getHayFormatoVirtual()));
        }
        if(this.libro.getBibliotecaAsociada() != null &&
           super.vkf.esFiltroValido(this.libro.getBibliotecaAsociada().getIdBiblioteca())) {
            super.filtroParaSelect.add("ID_BIBLIOTECA = " +
                                       this.libro.getBibliotecaAsociada().getIdBiblioteca());
        }
        if(this.libro.getEditorial() != null &&
           super.vkf.esFiltroValido(this.libro.getEditorial().getIdEditorial())) {
            super.filtroParaSelect.add("ID_EDITORIAL = " +
                                       this.libro.getEditorial().getIdEditorial());
        }
        if(this.libro.getTipo()!= null) {
            super.filtroParaSelect.add("TIPO = " +
                                       super.vkf.toSqlString(this.libro.getTipo()));
        }
        if(vkf.esFiltroValido(this.libro.getVolumen())) {
            super.filtroParaSelect.add("VOLUMEN = " +
                                       this.libro.getVolumen());
        }
        if(vkf.esFiltroValido(this.libro.getMateria())) {
            super.filtroParaSelect.add("MATERIA LIKE '%" +
                                       this.libro.getMateria() + "%'");
        }
        if(vkf.esFiltroValido(this.libro.getGenero())) {
            super.filtroParaSelect.add("GENERO LIKE '%" +
                                       this.libro.getGenero() + "%'");
        }
        if(vkf.esFiltroValido(this.libro.getTomo())) {
            super.filtroParaSelect.add("TOMO = " + this.libro.getTomo());
        }
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.libro);
    }

    @Override
    public Libro obtenerPorId(Integer idLibro) {
        this.libro = new Libro();
        this.libro.setIdLibro(idLibro);
        
        super.obtenerPorId();
        return this.libro;
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.libro.getIdLibro());
    }
    
    @Override
    public Libro obtenerPorAtributosUnicos(Libro libro) {
        this.libro = libro;
        super.obtenerPorAtributosUnicos();
        return this.libro;
    }
    
    @Override
    protected void cargarFiltroParaSelect_Obtener() {
        if(this.libro == null) return;
        if(this.libro.getISBN() != null && this.libro.getISBN() > 0) {
            super.filtroParaSelect.add("ISBN='" + this.libro.getISBN()+ "'");
        }
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {        
        this.libro = new Libro();
        this.libro.setIdLibro(this.resultSet.getInt("ID_LIBRO"));
        this.libro.setTitulo(this.resultSet.getString("TITULO"));
        this.libro.setFechaPublicacion(this.resultSet.getTimestamp("FECHA_PUBLICACION"));
        this.libro.setDescripcion(this.resultSet.getString("DESCRIPCION"));
        this.libro.setCopiasDisponibles(this.resultSet.getInt("COPIAS_DISPONIBLES"));
        this.libro.setHayFormatoFisico(this.resultSet.getBoolean("HAY_FORMATO_FISICO"));
        this.libro.setHayFormatoVirtual(this.resultSet.getBoolean("HAY_FORMATO_VIRTUAL"));
        this.libro.setUrl(this.resultSet.getString("URL"));
        this.libro.setPortada(this.resultSet.getBytes("PORTADA"));
        Integer idBibliotecaAsociada = this.resultSet.getInt("ID_BIBLIOTECA");
        this.libro.setISBN(this.resultSet.getInt("ISBN"));
        Integer idEditorial = this.resultSet.getInt("ID_EDITORIAL");
        this.libro.setTipo(super.vkf.toValidEnum(this.resultSet.getString("TIPO"),TipoLibro.class));
        this.libro.setVolumen(this.resultSet.getInt("VOLUMEN"));
        this.libro.setMateria(this.resultSet.getString("MATERIA"));
        this.libro.setGenero(this.resultSet.getString("GENERO"));
        this.libro.setTomo(this.resultSet.getInt("TOMO"));
        Biblioteca bibliotecaAsociada;
        Editorial editorial;
        if(!super.vkf.esFiltroValido(this.limiteProfundidad) ||
           (super.vkf.esFiltroValido(this.profundidadInstanciacion) &&
            this.profundidadInstanciacion < this.limiteProfundidad)){
            this.bibliotecaDAO = new BibliotecaDAOImpl();
            bibliotecaAsociada = this.bibliotecaDAO.obtenerPorId(idBibliotecaAsociada);
            this.editorialDAO = new EditorialDAOImpl();
            editorial = this.editorialDAO.obtenerPorId(idEditorial);
        } else {
            bibliotecaAsociada = new Biblioteca();
            bibliotecaAsociada.setIdBiblioteca(idBibliotecaAsociada);
            editorial = new Editorial();
            editorial.setIdEditorial(idEditorial);
        }
        this.libro.setBibliotecaAsociada(bibliotecaAsociada);
        this.libro.setEditorial(editorial);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.libro = null;
        this.editorialDAO = null;
        this.bibliotecaDAO = null;
    }
}
