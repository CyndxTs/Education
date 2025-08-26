
/* [/]
 >> Project:    SoftBiblioecaPublicacionesDA
 >> File:       Tesis_AutorDAOImpl.java
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
import pe.edu.pucp.softbiblioteca.publicaciones.dao.Tesis_AutorDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Autor;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tesis;
import pe.edu.pucp.softbiblioteca.publicaciones.model.TipoGrado;

public class Tesis_AutorDAOImpl extends DAOImpl implements Tesis_AutorDAO{
    private char salida;
    private Tesis tesis;
    private Autor autor;
    private BibliotecaDAO bibliotecaDAO;
    
    public Tesis_AutorDAOImpl() {
        super("Tesis_Autor");
        this.tesis = null;
        this.autor = null;
        this.bibliotecaDAO = null;
        super.filtrarDuplicados = true;
    }

    @Override
    public Integer insertar(Tesis tesis, Autor autor) {
        this.tesis = tesis;
        this.autor = autor;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "ID_TESIS, ID_AUTOR";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroInt(1, this.tesis.getIdTesis());
        this.incluirParametroInt(2, this.autor.getIdAutor());
    }

    @Override
    public Integer eliminar(Tesis tesis, Autor autor) {
        this.tesis = tesis;
        this.autor = autor;
        return super.eliminar();
    }    
        
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.tesis.getIdTesis());
        this.incluirParametroInt(2, this.autor.getIdAutor());
    }
    
    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "ID_TESIS=? AND ID_AUTOR=?";
    }
   
    @Override
    public ArrayList<Tesis> listarTodos_TesisDeAutor(Tesis tesis, Autor autor, Integer limiteListado, Integer limiteProfundidad) {
        this.tesis = tesis;
        this.autor = autor;
        this.salida = 'T';
        this.limiteProfundidad = limiteProfundidad;
        return (ArrayList<Tesis>) super.listarTodosPorIntermediario(limiteListado);
    }
    
    @Override
    public ArrayList<Autor> listarTodos_AutoresDeTesis(Tesis tesis, Autor autor, Integer limiteListado) {
        this.tesis = tesis;
        this.autor = autor;
        this.salida = 'A';
        return (ArrayList<Autor>) super.listarTodosPorIntermediario(limiteListado);
    }
    
    @Override
    protected String obtenerProyeccionDeColumnasParaSelect() {
        if(this.salida == 'T') {
            return "T.ID_TESIS, T.TITULO, T.FECHA_PUBLICACION, T.DESCRIPCION, T.COPIAS_DISPONIBLES, T.HAY_FORMATO_FISICO" +
                   ", T.HAY_FORMATO_VIRTUAL, T.URL, T.PORTADA, T.ID_BIBLIOTECA, T.GRADO_ACADEMICO";
        } else {
            return "A.ID_AUTOR, A.NOMBRE_COMPLETO";
        }
    }

    @Override
    protected String obtenerProyeccionDeTablasParaSelect() {
        if(this.salida == 'T') {
            return "Autor A";
        } else {
            return "Tesis T";
        }
    }

    @Override
    protected void cargarCombinacionParaSelect() {
        if(this.salida == 'T') {
            super.combinacionParaSelect.add("Tesis_Autor TA ON A.ID_AUTOR = TA.ID_AUTOR");
            super.combinacionParaSelect.add("Tesis T ON TA.ID_TESIS = T.ID_TESIS");
        } else {
            super.combinacionParaSelect.add("Tesis_Autor TA ON T.ID_TESIS = TA.ID_TESIS");
            super.combinacionParaSelect.add("Autor A ON TA.ID_AUTOR = A.ID_AUTOR");
        }
    }
    
    @Override
    protected void cargarFiltroParaSelect_Listar(){
        if(this.tesis!= null) {
            if(super.vkf.esFiltroValido(this.tesis.getIdTesis())) {
                 super.filtroParaSelect.add("T.ID_TESIS = " +this.tesis.getIdTesis());
            } else {
                if(super.vkf.esFiltroValido(this.tesis.getTitulo())) {
                    super.filtroParaSelect.add("T.TITULO LIKE '%" +
                                               this.tesis.getTitulo()+ "%'");
                }
                if(this.tesis.getFechaPublicacion() != null) {
                    super.filtroParaSelect.add("T.FECHA_PUBLICACION >= " +
                                               super.vkf.toSqlString(this.tesis.getFechaPublicacion()));
                }
                if(super.vkf.esFiltroValido(this.tesis.getDescripcion())) {
                    super.filtroParaSelect.add("T.DESCRIPCION LIKE '%" +
                                               this.tesis.getDescripcion()+ "%'");
                }
                if(this.tesis.getHayFormatoFisico() != null) {
                    super.filtroParaSelect.add("T.HAY_FORMATO_FISICO = " +
                                               super.vkf.toSqlString(this.tesis.getHayFormatoFisico()));
                }
                if(this.tesis.getHayFormatoVirtual() != null) {
                    super.filtroParaSelect.add("T.HAY_FORMATO_VIRTUAL = " +
                                               super.vkf.toSqlString(this.tesis.getHayFormatoVirtual()));
                }
                if(this.tesis.getBibliotecaAsociada() != null &&
                   super.vkf.esFiltroValido(this.tesis.getBibliotecaAsociada().getIdBiblioteca())) {
                    super.filtroParaSelect.add("T.ID_BIBLIOTECA = " +
                                               this.tesis.getBibliotecaAsociada().getIdBiblioteca());
                }
                if(this.tesis.getGradoAcademico() != null) {
                    super.filtroParaSelect.add("T.GRADO_ACADEMICO = " +
                                               super.vkf.toSqlString(this.tesis.getGradoAcademico()));
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
        if(this.salida == 'T') lista.add(this.tesis);
        else lista.add(this.autor);
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {   
        if(this.salida == 'T') {
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
        } else {
            this.autor = new Autor();
            this.autor.setIdAutor(this.resultSet.getInt("ID_AUTOR"));
            this.autor.setNombreCompleto(this.resultSet.getString("NOMBRE_COMPLETO"));
        }
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.tesis = null;
        this.autor = null;
        this.bibliotecaDAO = null;
    }
}
