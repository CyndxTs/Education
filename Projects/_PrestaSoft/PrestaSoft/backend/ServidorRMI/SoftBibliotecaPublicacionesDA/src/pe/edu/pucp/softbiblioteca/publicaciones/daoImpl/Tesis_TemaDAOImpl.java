
/* [/]
 >> Project:    SoftBiblioecaPublicacionesDA
 >> File:       Tesis_TemaDAOImpl.java
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
import pe.edu.pucp.softbiblioteca.publicaciones.dao.Tesis_TemaDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tema;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tesis;
import pe.edu.pucp.softbiblioteca.publicaciones.model.TipoGrado;

public class Tesis_TemaDAOImpl extends DAOImpl implements Tesis_TemaDAO{
    private String salida;
    private Tesis tesis;
    private Tema tema;
    private BibliotecaDAO bibliotecaDAO;
    
    public Tesis_TemaDAOImpl() {
        super("Tesis_Tema");
        this.tesis = null;
        this.tema = null;
        super.filtrarDuplicados = true;
    }

    @Override
    public Integer insertar(Tesis tesis, Tema tema) {
        this.tesis = tesis;
        this.tema = tema;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "ID_TESIS, ID_TEMA";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroInt(1, this.tesis.getIdTesis());
        this.incluirParametroInt(2, this.tema.getIdTema());
    }

    @Override
    public Integer eliminar(Tesis tesis, Tema tema) {
        this.tesis = tesis;
        this.tema = tema;
        return super.eliminar();
    }    
        
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.tesis.getIdTesis());
        this.incluirParametroInt(2, this.tema.getIdTema());
    }
    
    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "ID_TESIS=? AND ID_TEMA=?";
    }
   
    @Override
    public ArrayList<Tesis> listarTodos_TesisDeTema(Tesis tesis, Tema tema, Integer limiteListado, Integer limiteProfundidad) {
        this.tesis = tesis;
        this.tema = tema;
        this.salida = "TS";
        this.limiteProfundidad = limiteProfundidad;
        return (ArrayList<Tesis>) super.listarTodosPorIntermediario(limiteListado);
    }
    
    @Override
    public ArrayList<Tema> listarTodos_TemasDeTesis(Tesis tesis, Tema tema, Integer limiteListado) {
        this.tesis = tesis;
        this.tema = tema;
        this.salida = "TM";
        return (ArrayList<Tema>) super.listarTodosPorIntermediario(limiteListado);
    }
    
    @Override
    protected String obtenerProyeccionDeColumnasParaSelect() {
        if(this.salida.compareTo("TS") == 0) {
            return "TS.ID_TESIS, TS.TITULO, TS.FECHA_PUBLICACION, TS.DESCRIPCION, TS.COPIAS_DISPONIBLES, TS.HAY_FORMATO_FISICO" +
                   ", TS.HAY_FORMATO_VIRTUAL, TS.URL, TS.PORTADA, TS.ID_BIBLIOTECA, TS.GRADO_ACADEMICO";
        } else {
            return "TM.ID_TEMA, TM.TITULO";
        }
    }

    @Override
    protected String obtenerProyeccionDeTablasParaSelect() {
        if(this.salida.compareTo("TS") == 0) {
            return "Tema TM";
        } else {
            return "Tesis TS";
        }
    }
    
    @Override
    protected void cargarCombinacionParaSelect() {
        if(this.salida.compareTo("TS") == 0) {
            super.combinacionParaSelect.add("Tesis_Tema TT ON TM.ID_TEMA = TT.ID_TEMA");
            super.combinacionParaSelect.add("Tesis TS ON TT.ID_TESIS = TS.ID_TESIS");
        } else {
            super.combinacionParaSelect.add("Tesis_Tema TT ON TS.ID_TESIS = TT.ID_TESIS");
            super.combinacionParaSelect.add("Tema TM ON TT.ID_TEMA = TM.ID_TEMA");
        }
    }
    
    @Override
    protected void cargarFiltroParaSelect_Listar(){
        if(this.tesis!= null) {
            if(super.vkf.esFiltroValido(this.tesis.getIdTesis())) {
                 super.filtroParaSelect.add("TS.ID_TESIS = " +this.tesis.getIdTesis());
            } else {
                if(super.vkf.esFiltroValido(this.tesis.getTitulo())) {
                    super.filtroParaSelect.add("TS.TITULO LIKE '%" +
                                               this.tesis.getTitulo()+ "%'");
                }
                if(this.tesis.getFechaPublicacion() != null) {
                    super.filtroParaSelect.add("TS.FECHA_PUBLICACION >= " +
                                               super.vkf.toSqlString(this.tesis.getFechaPublicacion()));
                }
                if(super.vkf.esFiltroValido(this.tesis.getDescripcion())) {
                    super.filtroParaSelect.add("TS.DESCRIPCION LIKE '%" +
                                               this.tesis.getDescripcion()+ "%'");
                }
                if(this.tesis.getHayFormatoFisico() != null) {
                    super.filtroParaSelect.add("TS.HAY_FORMATO_FISICO = " +
                                               super.vkf.toSqlString(this.tesis.getHayFormatoFisico()));
                }
                if(this.tesis.getHayFormatoVirtual() != null) {
                    super.filtroParaSelect.add("TS.HAY_FORMATO_VIRTUAL = " +
                                               super.vkf.toSqlString(this.tesis.getHayFormatoVirtual()));
                }
                if(this.tesis.getBibliotecaAsociada() != null &&
                   super.vkf.esFiltroValido(this.tesis.getBibliotecaAsociada().getIdBiblioteca())) {
                    super.filtroParaSelect.add("TS.ID_BIBLIOTECA = " +
                                               this.tesis.getBibliotecaAsociada().getIdBiblioteca());
                }
                if(this.tesis.getGradoAcademico() != null) {
                    super.filtroParaSelect.add("TS.GRADO_ACADEMICO = " +
                                               super.vkf.toSqlString(this.tesis.getGradoAcademico()));
                }
            }
        }
        if(this.tema!= null) {
            if(super.vkf.esFiltroValido(this.tema.getIdTema())) {
                 super.filtroParaSelect.add("TM.ID_TEMA = " +
                                            this.tema.getIdTema());
             } else {
                if(super.vkf.esFiltroValido(this.tema.getTitulo())) {
                    super.filtroParaSelect.add("TM.TITULO LIKE '%" +
                                               this.tema.getTitulo() + "%'");
                }
            }
        }
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        if(this.salida.compareTo("TS") == 0) lista.add(this.tesis);
        else lista.add(this.tema);
        
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {   
        if(this.salida.compareTo("TS") == 0) {
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
            this.tema = new Tema();
            this.tema.setIdTema(this.resultSet.getInt("ID_TEMA"));
            this.tema.setTitulo(this.resultSet.getString("TITULO"));
        }
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.tesis = null;
        this.tema = null;
        this.bibliotecaDAO = null;
    }
}
