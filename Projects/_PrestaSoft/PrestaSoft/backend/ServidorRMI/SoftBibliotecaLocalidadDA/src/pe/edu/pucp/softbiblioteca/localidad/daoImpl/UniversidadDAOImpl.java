
/* [/]
 >> Project:    SoftBibliotecaLocalidadModelDA
 >> File:       UniversidadDAOImpl.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.localidad.daoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbiblioteca.config.DAOImpl;
import pe.edu.pucp.softbiblioteca.localidad.dao.ConsorcioDAO;
import pe.edu.pucp.softbiblioteca.localidad.dao.UniversidadDAO;
import pe.edu.pucp.softbiblioteca.localidad.model.Consorcio;
import pe.edu.pucp.softbiblioteca.localidad.model.Universidad;
import pe.edu.pucp.softbiblioteca.politicas.model.Politica;
import pe.edu.pucp.sotbiblioteca.politicas.dao.PoliticaDAO;
import pe.edu.pucp.sotbiblioteca.politicas.daoimpl.PoliticaDAOImpl;

public class UniversidadDAOImpl extends DAOImpl implements UniversidadDAO {
    private Universidad universidad;
    private ConsorcioDAO consorcioDAO;
    private PoliticaDAO politicaDAO;
    
    public UniversidadDAOImpl() {
        super("Universidad");
        this.universidad = null;
        this.politicaDAO = null;
        this.consorcioDAO = null;
    }

    @Override
    public Integer insertar(Universidad universidad) {
        this.universidad = universidad;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "NOMBRE, DIRECCION, CORREO_PERSONAL_ADMINISTRATIVO, EXTENSION_DOMINIO_CORREO" +
               ", ID_POLITICA_REGULAR, ID_CONSORCIO";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?, ?, ?, ?, ?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroString(1, this.universidad.getNombre());
        this.incluirParametroString(2, this.universidad.getDireccion());
        this.incluirParametroString(3, this.universidad.getCorreoPersonalAdministrativo());
        this.incluirParametroString(4, this.universidad.getExtensionDominioCorreo());
        if(this.universidad.getPoliticaRegular()!= null &&
           super.vkf.esFiltroValido(this.universidad.getPoliticaRegular().getIdPolitica())) {
            this.incluirParametroInt(5,this.universidad.getPoliticaRegular().getIdPolitica());
        } else this.incluirParametroInt(5,null);
        if(this.universidad.getConsorcioPerteneciente() != null &&
           super.vkf.esFiltroValido(this.universidad.getConsorcioPerteneciente().getIdConsorcio())) {
            this.incluirParametroInt(6, this.universidad.getConsorcioPerteneciente().getIdConsorcio());
        } else this.incluirParametroInt(6,null);
    }

    @Override
    public Integer modificar(Universidad universidad) {
        this.universidad = universidad;
        return super.modificar();
    }    
    
    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "NOMBRE=?, DIRECCION=?, CORREO_PERSONAL_ADMINISTRATIVO=?" +
               ", EXTENSION_DOMINIO_CORREO=?, ID_POLITICA_REGULAR=?, ID_CONSORCIO=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroString(1, this.universidad.getNombre());
        this.incluirParametroString(2, this.universidad.getDireccion());
        this.incluirParametroString(3, this.universidad.getCorreoPersonalAdministrativo());
        this.incluirParametroString(4, this.universidad.getExtensionDominioCorreo());
        if(this.universidad.getPoliticaRegular()!= null &&
           super.vkf.esFiltroValido(this.universidad.getPoliticaRegular().getIdPolitica())) {
            this.incluirParametroInt(5,this.universidad.getPoliticaRegular().getIdPolitica());
        } else this.incluirParametroInt(5,null);
        if(this.universidad.getConsorcioPerteneciente() != null &&
           super.vkf.esFiltroValido(this.universidad.getConsorcioPerteneciente().getIdConsorcio())) {
            this.incluirParametroInt(6, this.universidad.getConsorcioPerteneciente().getIdConsorcio());
        } else this.incluirParametroInt(6,null);
        this.incluirParametroInt(7,this.universidad.getIdUniversidad());
    }

    @Override
    public Integer eliminar(Universidad universidad) {
        this.universidad = universidad;
        return super.eliminar();
    }    
        
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.universidad.getIdUniversidad());
    }
    
    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "ID_UNIVERSIDAD=?";
    }
   
    @Override
    public ArrayList<Universidad> listarTodos(Universidad universidad, Integer limiteListado, Integer limiteProfundidad) {
        this.universidad = universidad;
        this.limiteProfundidad = limiteProfundidad;
        return (ArrayList<Universidad>) super.listarTodos(limiteListado);
    }
    
    @Override
    protected String obtenerProyeccionDeColumnasParaSelect() {
        return "ID_UNIVERSIDAD, NOMBRE, DIRECCION, CORREO_PERSONAL_ADMINISTRATIVO" +
               ", EXTENSION_DOMINIO_CORREO, ID_POLITICA_REGULAR, ID_CONSORCIO";
    }
    
    @Override
    protected void cargarFiltroParaSelect_Listar() {
        if(this.universidad == null) return;
        if(this.universidad.getPoliticaRegular() != null &&
           super.vkf.esFiltroValido(this.universidad.getPoliticaRegular().getIdPolitica())) {
            super.filtroParaSelect.add("ID_POLITICA = " +
                                       this.universidad.getPoliticaRegular().getIdPolitica());
        }
        if(this.universidad.getConsorcioPerteneciente() != null &&
           super.vkf.esFiltroValido(this.universidad.getConsorcioPerteneciente().getIdConsorcio())) {
            super.filtroParaSelect.add("ID_CONSORCIO = " +
                                       this.universidad.getConsorcioPerteneciente().getIdConsorcio());
        }
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.universidad);
    }

    @Override
    public Universidad obtenerPorId(Integer idUniversidad) {
        this.universidad = new Universidad();
        this.universidad.setIdUniversidad(idUniversidad);
        super.obtenerPorId();
        return this.universidad;
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.universidad.getIdUniversidad());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException { 
        this.universidad = new Universidad();
        this.universidad.setIdUniversidad(this.resultSet.getInt("ID_UNIVERSIDAD"));
        this.universidad.setNombre(this.resultSet.getString("NOMBRE"));
        this.universidad.setDireccion(this.resultSet.getString("DIRECCION"));
        this.universidad.setCorreoPersonalAdministrativo(this.resultSet.getString("CORREO_PERSONAL_ADMINISTRATIVO"));
        this.universidad.setExtensionDominioCorreo(this.resultSet.getString("EXTENSION_DOMINIO_CORREO"));
        Integer idPoliticaRegular = this.resultSet.getInt("ID_POLITICA_REGULAR");
        Integer idConsorcio = this.resultSet.getInt("ID_CONSORCIO");
        Politica politicaRegular;
        Consorcio consorcioPerteneciente;
        if(!super.vkf.esFiltroValido(this.limiteProfundidad) ||
           (super.vkf.esFiltroValido(this.profundidadInstanciacion) &&
            this.profundidadInstanciacion < this.limiteProfundidad)){
            if(super.vkf.esFiltroValido(idPoliticaRegular)) {
                this.politicaDAO = new PoliticaDAOImpl();
                politicaRegular = this.politicaDAO.obtenerPorId(idPoliticaRegular);
                this.universidad.setPoliticaRegular(politicaRegular);
            }
            if(super.vkf.esFiltroValido(idConsorcio)) {
                this.consorcioDAO = new ConsorcioDAOImpl();
                consorcioPerteneciente = this.consorcioDAO.obtenerPorId(idConsorcio);
                this.universidad.setConsorcioPerteneciente(consorcioPerteneciente);
            }
        } else {
            if(super.vkf.esFiltroValido(idPoliticaRegular)) {
                politicaRegular = new Politica();
                politicaRegular.setIdPolitica(idPoliticaRegular);
                this.universidad.setPoliticaRegular(politicaRegular);
            }
            if(super.vkf.esFiltroValido(idConsorcio)) {
                consorcioPerteneciente = new Consorcio();
                consorcioPerteneciente.setIdConsorcio(idConsorcio);
                this.universidad.setConsorcioPerteneciente(consorcioPerteneciente);
            }
        }
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.universidad = null;
        this.politicaDAO = null;
        this.consorcioDAO = null;
    }
}
