
/* [/]
 >> Project:    SoftBiblioecaPublicacionesDA
 >> File:       Usuario_UniversidadDAOImpl.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.usuarios.daoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbiblioteca.config.DAOImpl;
import pe.edu.pucp.softbiblioteca.localidad.dao.ConsorcioDAO;
import pe.edu.pucp.softbiblioteca.localidad.daoImpl.ConsorcioDAOImpl;

import pe.edu.pucp.softbiblioteca.localidad.model.Consorcio;
import pe.edu.pucp.softbiblioteca.localidad.model.Universidad;
import pe.edu.pucp.softbiblioteca.politicas.model.Politica;
import pe.edu.pucp.softbiblioteca.usuarios.dao.Usuario_UniversidadDAO;
import pe.edu.pucp.softbiblioteca.usuarios.model.TipoUsuario;
import pe.edu.pucp.softbiblioteca.usuarios.model.Usuario;
import pe.edu.pucp.sotbiblioteca.politicas.dao.PoliticaDAO;
import pe.edu.pucp.sotbiblioteca.politicas.daoimpl.PoliticaDAOImpl;


public class Usuario_UniversidadDAOImpl extends DAOImpl implements Usuario_UniversidadDAO{
    private String salida;
    private Usuario usuario;
    private Universidad universidad;
    private PoliticaDAO politicaDAO;
    private ConsorcioDAO consorcioDAO;
    
    public Usuario_UniversidadDAOImpl() {
        super("Usuario_Universidad");
        this.usuario = null;
        this.universidad = null;
        this.politicaDAO = null;
        this.consorcioDAO = null;
        super.filtrarDuplicados = true;
    }

    @Override
    public Integer insertar(Usuario usuario, Universidad universidad) {
        this.usuario = usuario;
        this.universidad = universidad;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "ID_USUARIO, ID_UNIVERSIDAD";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroInt(1,this.usuario.getIdUsuario());
        this.incluirParametroInt(2,this.universidad.getIdUniversidad());
    }

    @Override
    public Integer eliminar(Usuario usuario, Universidad universidad) {
        this.usuario = usuario;
        this.universidad = universidad;
        return super.eliminar();
    }    
        
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1,this.usuario.getIdUsuario());
        this.incluirParametroInt(2,this.universidad.getIdUniversidad());
    }
    
    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "ID_USUARIO=? AND ID_UNIVERSIDAD=?";
    }
   
    @Override
    public ArrayList<Usuario> listarTodos_UsuariosDeUniversidad(Usuario usuario, Universidad universidad, Integer limiteListado) {
        this.usuario = usuario;
        this.universidad = universidad;
        this.salida = "US";
        return (ArrayList<Usuario>) super.listarTodosPorIntermediario(limiteListado);
    }
    
    @Override
    public ArrayList<Universidad> listarTodos_UniversidadesDeUsuario(Usuario usuario, Universidad universidad, Integer limiteListado, Integer limiteProfundidad) {
        this.usuario = usuario;
        this.universidad = universidad;
        this.salida = "UN";
        this.limiteProfundidad = limiteProfundidad;
        return (ArrayList<Universidad>) super.listarTodosPorIntermediario(limiteListado);
    }
    
    @Override
    protected String obtenerProyeccionDeColumnasParaSelect() {
        if(this.salida.compareTo("US") == 0) {
            return "US.ID_USUARIO, US.NOMBRES, US.APELLIDOS, US.CORREO_INSTITUCIONAL" +
                   ", US.CONTRASENIA, US.NOMBRE_USUARIO, US.FECHA_REGISTRO, US.TIPO_USUARIO";
        } else {
            return "UN.ID_UNIVERSIDAD, UN.NOMBRE, UN.DIRECCION, UN.CORREO_PERSONAL_ADMINISTRATIVO, " +
                   "UN.EXTENSION_DOMINIO_CORREO, UN.ID_POLITICA_REGULAR, UN.ID_CONSORCIO";
        }
    }
    
    @Override
    protected String obtenerProyeccionDeTablasParaSelect() {
        if(this.salida.compareTo("US") == 0) {
            return "Universidad UN";
        } else {
            return "Usuario US";
        }
    }
    
    @Override
    protected void cargarCombinacionParaSelect() {
        if(this.salida.compareTo("US") == 0) {
            super.combinacionParaSelect.add("Usuario_Universidad UU ON UN.ID_UNIVERSIDAD = UU.ID_UNIVERSIDAD");
            super.combinacionParaSelect.add("Usuario US ON UU.ID_USUARIO = US.ID_USUARIO");
        } else {
            super.combinacionParaSelect.add("Usuario_Universidad UU ON US.ID_USUARIO = UU.ID_USUARIO");
            super.combinacionParaSelect.add("Universidad UN ON UU.ID_UNIVERSIDAD = UN.ID_UNIVERSIDAD");
        }
    }
    
    @Override
    protected void cargarFiltroParaSelect_Listar() {
        if(this.usuario!= null) {
            if(super.vkf.esFiltroValido(this.usuario.getIdUsuario())) {
                 super.filtroParaSelect.add("US.ID_USUARIO = " +
                                            this.usuario.getIdUsuario());
            } else {
                if(super.vkf.esFiltroValido(this.usuario.getNombres())) {
                    super.filtroParaSelect.add("US.NOMBRES LIKE '" +
                                               this.usuario.getNombres() + "%'");
                }
                if(super.vkf.esFiltroValido(this.usuario.getApellidos())) {
                    super.filtroParaSelect.add("US.APELLIDOS LIKE '" +
                                               this.usuario.getApellidos()+ "%'");
                }
                if(super.vkf.esFiltroValido(this.usuario.getCorreoInstitucional())) {
                    super.filtroParaSelect.add("US.CORREO_INSTITUCIONAL LIKE '%" +
                                               this.usuario.getCorreoInstitucional()+ "%'");
                }
                if(super.vkf.esFiltroValido(this.usuario.getNombreUsuario())) {
                    super.filtroParaSelect.add("US.NOMBRE_USUARIO LIKE '%" +
                                               this.usuario.getNombreUsuario()+ "%'");
                }
                if(this.usuario.getFechaRegistro() != null) {
                    super.filtroParaSelect.add("(US.FECHA_REGISTRO IS NULL OR US.FECHA_REGISTRO >= " +
                                               super.vkf.toSqlString(this.usuario.getFechaRegistro()));
                }
                if(this.usuario.getTipoUsuario() != null) {
                    super.filtroParaSelect.add("US.TIPO_USUARIO = " +
                                               super.vkf.toSqlString(this.usuario.getTipoUsuario()));
                }
            }
        }
        if(this.universidad!= null) {
            if(super.vkf.esFiltroValido(this.universidad.getIdUniversidad())) {
                 super.filtroParaSelect.add("UN.ID_UNIVERSIDAD = " +
                                            this.universidad.getIdUniversidad());
             } else {
                if(super.vkf.esFiltroValido(this.universidad.getPoliticaRegular().getIdPolitica())) {
                    super.filtroParaSelect.add("UN.ID_POLITICA =" +
                                               this.universidad.getPoliticaRegular().getIdPolitica());
                }
                if(super.vkf.esFiltroValido(this.universidad.getConsorcioPerteneciente().getIdConsorcio())) {
                    super.filtroParaSelect.add("UN.ID_CONSORCIO =" +
                                               this.universidad.getConsorcioPerteneciente().getIdConsorcio());
                }
            }
        }
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        if(this.salida.compareTo("US") == 0) lista.add(this.usuario);
        else lista.add(this.universidad);
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {   
        if(this.salida.compareTo("US") == 0) {
            this.usuario = new Usuario();
            this.usuario.setIdUsuario(this.resultSet.getInt("ID_USUARIO"));
            this.usuario.setNombres(this.resultSet.getString("NOMBRES"));
            this.usuario.setApellidos(this.resultSet.getString("APELLIDOS"));
            this.usuario.setCorreoInstitucional(this.resultSet.getString("CORREO_INSTITUCIONAL"));
            this.usuario.setContrasenia(this.resultSet.getString("CONTRASENIA"));
            this.usuario.setNombreUsuario(this.resultSet.getString("NOMBRE_USUARIO"));
            this.usuario.setFechaRegistro(this.resultSet.getDate("FECHA_REGISTRO"));
            this.usuario.setTipoUsuario(TipoUsuario.valueOf(this.resultSet.getString("TIPO_USUARIO")));
        } else {
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
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.usuario = null;
        this.universidad = null;
        this.politicaDAO = null;
        this.consorcioDAO = null;
    }
}
