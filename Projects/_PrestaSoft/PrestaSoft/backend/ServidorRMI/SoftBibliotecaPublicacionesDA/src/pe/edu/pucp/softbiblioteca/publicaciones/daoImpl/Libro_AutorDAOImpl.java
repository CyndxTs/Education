
/* [/]
 >> Project:    SoftBiblioecaPublicacionesDA
 >> File:       Libro_AutorDAOImpl.java
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
import pe.edu.pucp.softbiblioteca.publicaciones.dao.Libro_AutorDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Autor;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Editorial;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Libro;
import pe.edu.pucp.softbiblioteca.publicaciones.model.TipoLibro;

public class Libro_AutorDAOImpl extends DAOImpl implements Libro_AutorDAO{
    private char salida;
    private Libro libro;
    private Autor autor;
    private BibliotecaDAO bibliotecaDAO;
    private EditorialDAO editorialDAO;
    
    public Libro_AutorDAOImpl() {
        super("Libro_Autor");
        this.libro = null;
        this.autor = null;
        this.bibliotecaDAO = null;
        this.editorialDAO = null;
        super.filtrarDuplicados = true;
    }

    @Override
    public Integer insertar(Libro libro,Autor autor) {
        this.libro = libro;
        this.autor = autor;
        this.retornarLlavePrimaria = false;
        Integer id = super.insertar();
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "ID_LIBRO, ID_AUTOR";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroInt(1, this.libro.getIdLibro());
        this.incluirParametroInt(2, this.autor.getIdAutor());
    }
    
    @Override
    public Integer eliminar(Libro libro,Autor autor) {
        this.libro = libro;
        this.autor = autor;
        return super.eliminar();
    }    
        
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.libro.getIdLibro());
        this.incluirParametroInt(2, this.autor.getIdAutor());
    }
    
    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "ID_LIBRO=? AND ID_AUTOR=?";
    }
    
    @Override
    public ArrayList<Libro> listarTodos_LibrosDeAutor(Libro libro,Autor autor, Integer limiteListado, Integer limiteProfundidad) {
        this.libro = libro;
        this.autor = autor;
        this.salida = 'L';
        this.limiteProfundidad = limiteProfundidad;
        return (ArrayList<Libro>) super.listarTodosPorIntermediario(limiteListado);
    }
   
    @Override
    public ArrayList<Autor> listarTodos_AutoresDeLibro(Libro libro,Autor autor, Integer limiteListado) {
        this.libro = libro;
        this.autor = autor;
        this.salida = 'A';
        return (ArrayList<Autor>) super.listarTodosPorIntermediario(limiteListado);
    }
    
    @Override
    protected String obtenerProyeccionDeColumnasParaSelect() {
        if(this.salida == 'L') {
            return "L.ID_LIBRO, L.TITULO, L.FECHA_PUBLICACION, L.DESCRIPCION, L.COPIAS_DISPONIBLES, L.HAY_FORMATO_FISICO" +
                   ", L.HAY_FORMATO_VIRTUAL, L.URL, L.PORTADA, L.ID_BIBLIOTECA, L.ISBN, L.ID_EDITORIAL" +
                   ", L.TIPO, L.VOLUMEN, L.MATERIA, L.GENERO, L.TOMO";
        } else {
            return "A.ID_AUTOR, A.NOMBRE_COMPLETO";
        }
    }
    
    @Override
    protected String obtenerProyeccionDeTablasParaSelect() {
        if(this.salida == 'L') {
            return "Autor A";
        } else {
            return "Libro L";
        }
    }
    
    @Override
    protected void cargarCombinacionParaSelect() {
        if(this.salida == 'L') {
            super.combinacionParaSelect.add("Libro_Autor LA ON A.ID_AUTOR = LA.ID_AUTOR");
            super.combinacionParaSelect.add("Libro L ON LA.ID_LIBRO = L.ID_LIBRO");
        } else {
            super.combinacionParaSelect.add("Libro_Autor LA ON L.ID_LIBRO = LA.ID_LIBRO");
            super.combinacionParaSelect.add("Autor A ON LA.ID_AUTOR = A.ID_AUTOR");
        }
    }
    
    @Override
    protected void cargarFiltroParaSelect_Listar(){
        if(this.libro != null) {
            if(super.vkf.esFiltroValido(this.libro.getIdLibro())) {
                 super.filtroParaSelect.add("L.ID_LIBRO = " +this.libro.getIdLibro());
            } else {
                if(super.vkf.esFiltroValido(this.libro.getTitulo())) {
                    super.filtroParaSelect.add("L.TITULO LIKE '%" +
                                               this.libro.getTitulo()+ "%'");
                }
                if(this.libro.getFechaPublicacion() != null) {
                    super.filtroParaSelect.add("L.FECHA_PUBLICACION >= " +
                                               super.vkf.toSqlString(this.libro.getFechaPublicacion()));
                }
                if(super.vkf.esFiltroValido(this.libro.getDescripcion())) {
                    super.filtroParaSelect.add("L.DESCRIPCION LIKE '%" +
                                               this.libro.getDescripcion()+ "%'");
                }
                if(this.libro.getHayFormatoFisico() != null) {
                    super.filtroParaSelect.add("L.HAY_FORMATO_FISICO = " +
                                               super.vkf.toSqlString(this.libro.getHayFormatoFisico()));
                }
                if(this.libro.getHayFormatoVirtual() != null) {
                    super.filtroParaSelect.add("L.HAY_FORMATO_VIRTUAL = " +
                                               super.vkf.toSqlString(this.libro.getHayFormatoVirtual()));
                }
                if(this.libro.getBibliotecaAsociada() != null &&
                   super.vkf.esFiltroValido(this.libro.getBibliotecaAsociada().getIdBiblioteca())) {
                    super.filtroParaSelect.add("L.ID_BIBLIOTECA = " +
                                               this.libro.getBibliotecaAsociada().getIdBiblioteca());
                }
                if(this.libro.getEditorial() != null &&
                   super.vkf.esFiltroValido(this.libro.getEditorial().getIdEditorial())) {
                    super.filtroParaSelect.add("L.ID_EDITORIAL = " +
                                               this.libro.getEditorial().getIdEditorial());
                }
                if(this.libro.getTipo()!= null) {
                    super.filtroParaSelect.add("L.TIPO = " +
                                               super.vkf.toSqlString(this.libro.getTipo()));
                }
                if(super.vkf.esFiltroValido(this.libro.getVolumen())) {
                    super.filtroParaSelect.add("L.VOLUMEN = " +
                                               this.libro.getVolumen());
                }
                if(super.vkf.esFiltroValido(this.libro.getMateria())) {
                    super.filtroParaSelect.add("L.MATERIA LIKE '%" +
                                               this.libro.getMateria() + "%'");
                }
                if(super.vkf.esFiltroValido(this.libro.getGenero())) {
                    super.filtroParaSelect.add("L.GENERO LIKE '%" +
                                               this.libro.getGenero() + "%'");
                }
                if(super.vkf.esFiltroValido(this.libro.getTomo())) {
                    super.filtroParaSelect.add("L.TOMO = " +
                                               this.libro.getTomo());
                }
            }
        }
        if(this.autor != null) {
            if(super.vkf.esFiltroValido(this.autor.getIdAutor())) {
                 super.filtroParaSelect.add("A.ID_AUTOR = " +
                                            this.autor.getIdAutor());
            } else {
                if(super.vkf.esFiltroValido(this.autor.getNombreCompleto())) {
                    super.filtroParaSelect.add("A.NOMBRE_COMPLETO LIKE '%" +
                                               this.autor.getNombreCompleto() + "%'");
                }
            }
        }
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        if(this.salida == 'L') lista.add(this.libro);
        else lista.add(this.autor);
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {   
        if(salida == 'L') {
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
        } else {
            this.autor = new Autor();
            this.autor.setIdAutor(this.resultSet.getInt("ID_AUTOR"));
            this.autor.setNombreCompleto(this.resultSet.getString("NOMBRE_COMPLETO"));
        }
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.libro = null;
        this.autor = null;
        this.bibliotecaDAO = null;
        this.editorialDAO = null;
    }
}
