
/* [/]
 >> Project:    SoftBiblioecaPublicacionesDA
 >> File:       CopiaEjemplarDAOImpl.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.daoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbiblioteca.config.DAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.CopiaEjemplarDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.LibroDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.TesisDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.model.CopiaEjemplar;
import pe.edu.pucp.softbiblioteca.publicaciones.model.EstadoEjemplar;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Libro;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Publicacion;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Tesis;

public class CopiaEjemplarDAOImpl extends DAOImpl implements CopiaEjemplarDAO{
    private CopiaEjemplar copiaEjemplar;
    private LibroDAO libroDAO;
    private TesisDAO tesisDAO;
    
    public CopiaEjemplarDAOImpl() {
        super("CopiaEjemplar");
        this.copiaEjemplar = null;
        this.libroDAO = null;
        this.tesisDAO = null;  
    }

    @Override
    public Integer insertar(CopiaEjemplar copiaEjemplar) {
        this.copiaEjemplar = copiaEjemplar;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "DIRECCION_LOCAL,ESTADO_EJEMPLAR,ID_LIBRO,ID_TESIS";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?, ?, ?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroString(1, this.copiaEjemplar.getDireccionLocal());
        this.incluirParametroString(2, this.copiaEjemplar.getEstado().toString());
        if(this.copiaEjemplar.getPublicacionAsociada() instanceof Libro) {
            this.incluirParametroInt(3,this.copiaEjemplar.getPublicacionAsociada().getIdPublicacion());
            this.incluirParametroInt(4,null);
        } else {
            this.incluirParametroInt(3,null);
            this.incluirParametroInt(4,this.copiaEjemplar.getPublicacionAsociada().getIdPublicacion());
        }
    }

    @Override
    public Integer modificar(CopiaEjemplar copiaEjemplar) {
        this.copiaEjemplar = copiaEjemplar;
        return super.modificar();
    }    
    
    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "DIRECCION_LOCAL=?, ESTADO_EJEMPLAR=?, ID_LIBRO=?, ID_TESIS=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroString(1, this.copiaEjemplar.getDireccionLocal());
        this.incluirParametroString(2, this.copiaEjemplar.getEstado().toString());
        if(this.copiaEjemplar.getPublicacionAsociada() instanceof Libro) {
            this.incluirParametroInt(3,this.copiaEjemplar.getPublicacionAsociada().getIdPublicacion());
            this.incluirParametroInt(4,null);
        } else {
            this.incluirParametroInt(3,null);
            this.incluirParametroInt(4,this.copiaEjemplar.getPublicacionAsociada().getIdPublicacion());
        }
        this.incluirParametroInt(5, this.copiaEjemplar.getIdCopiaEjemplar());
    }

    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "ID_COPIA_EJEMPLAR=?";
    }

    @Override
    public Integer eliminar(CopiaEjemplar copiaEjemplar) {
        this.copiaEjemplar=copiaEjemplar;
        return super.eliminar();
    }    
        
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.copiaEjemplar.getIdCopiaEjemplar());
    }
   
    @Override
    public ArrayList<CopiaEjemplar> listarTodos(CopiaEjemplar copiaEjemplar, Integer limiteListado, Integer limiteProfundidad) {
        this.copiaEjemplar = copiaEjemplar;
        this.limiteProfundidad = limiteProfundidad;
        return (ArrayList<CopiaEjemplar>) super.listarTodos(limiteListado);
    }
    
    @Override
    protected String obtenerProyeccionDeColumnasParaSelect() {
        return "ID_COPIA_EJEMPLAR, DIRECCION_LOCAL,ESTADO_EJEMPLAR,ID_LIBRO,ID_TESIS";
    }
    
    @Override
    protected void cargarFiltroParaSelect_Listar() {
        if(this.copiaEjemplar == null) return;
        if(super.vkf.esFiltroValido(this.copiaEjemplar.getDireccionLocal())) {
            super.filtroParaSelect.add("DIRECCION_LOCAL LIKE '%" +
                                       this.copiaEjemplar.getDireccionLocal() + "%'");
        }
        if(this.copiaEjemplar.getEstado() != null) {
            super.filtroParaSelect.add("ESTADO_EJEMPLAR = " +
                                        super.vkf.toSqlString(this.copiaEjemplar.getEstado()));
        }
        if(this.copiaEjemplar.getPublicacionAsociada() != null &&
           super.vkf.esFiltroValido(this.copiaEjemplar.getPublicacionAsociada().getIdPublicacion())) {
            if(this.copiaEjemplar.getPublicacionAsociada() instanceof Libro) {
                super.filtroParaSelect.add("ID_LIBRO = " +
                                           this.copiaEjemplar.getPublicacionAsociada().getIdPublicacion());
            } else {
                super.filtroParaSelect.add("ID_TESIS = " +
                                           this.copiaEjemplar.getPublicacionAsociada().getIdPublicacion());
            }
        }
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.copiaEjemplar);
    }
    
    @Override
    public CopiaEjemplar obtenerPorId(Integer idCopiaEjemplar) {
        this.copiaEjemplar = new CopiaEjemplar();
        this.copiaEjemplar.setIdCopiaEjemplar(idCopiaEjemplar);
        super.obtenerPorId();
        return this.copiaEjemplar;
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.copiaEjemplar.getIdCopiaEjemplar());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException { 
        this.copiaEjemplar = new CopiaEjemplar();
        this.copiaEjemplar.setIdCopiaEjemplar(this.resultSet.getInt("ID_COPIA_EJEMPLAR"));
        this.copiaEjemplar.setDireccionLocal(this.resultSet.getString("DIRECCION_LOCAL"));
        this.copiaEjemplar.setEstado(EstadoEjemplar.valueOf(this.resultSet.getString("ESTADO_EJEMPLAR")));
        Integer idPublicacion = this.resultSet.getInt("ID_LIBRO");
        Publicacion publicacionAsociada;
        if(!super.vkf.esFiltroValido(this.limiteProfundidad) ||
           (super.vkf.esFiltroValido(this.profundidadInstanciacion) &&
            this.profundidadInstanciacion < this.limiteProfundidad)){
            if(!super.vkf.esFiltroValido(idPublicacion)) {
                idPublicacion = this.resultSet.getInt("ID_TESIS");
                this.tesisDAO = new TesisDAOImpl();
                publicacionAsociada = this.tesisDAO.obtenerPorId(idPublicacion);
            }else{
                this.libroDAO = new LibroDAOImpl();
                publicacionAsociada = this.libroDAO.obtenerPorId(idPublicacion);
            }                 
        }else{
            if(!super.vkf.esFiltroValido(idPublicacion)){
                publicacionAsociada = new Tesis();                
            }else{
                publicacionAsociada = new Libro();
            }
            publicacionAsociada.setIdPublicacion(idPublicacion);           
        }
        this.copiaEjemplar.setPublicacionAsociada(publicacionAsociada);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.copiaEjemplar = null;
        this.libroDAO = null;
        this.tesisDAO = null;
    }
}
