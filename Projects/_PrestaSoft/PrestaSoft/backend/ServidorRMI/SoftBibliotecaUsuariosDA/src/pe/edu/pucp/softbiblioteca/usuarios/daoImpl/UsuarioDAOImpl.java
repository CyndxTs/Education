
/* [/]
 >> Project:    SoftBibliotecaUsuariosDA
 >> File:       UsuarioDAOImpl.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.usuarios.daoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbiblioteca.config.DAOImpl;
import pe.edu.pucp.softbiblioteca.usuarios.dao.UsuarioDAO;
import pe.edu.pucp.softbiblioteca.usuarios.model.TipoUsuario;
import pe.edu.pucp.softbiblioteca.usuarios.model.Usuario;

public class UsuarioDAOImpl extends DAOImpl implements UsuarioDAO {
    private Usuario usuario;
    
    public UsuarioDAOImpl() {
        super("Usuario");
        this.usuario = null;
    }

    @Override
    public Integer insertar(Usuario usuario) {
        this.usuario = usuario;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "NOMBRES,APELLIDOS,CORREO_INSTITUCIONAL,CONTRASENIA,NOMBRE_USUARIO,FECHA_REGISTRO,TIPO_USUARIO";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return " ?, ?, ?, ?, ?, ?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroString(1,this.usuario.getNombres());
        this.incluirParametroString(2,this.usuario.getApellidos());
        this.incluirParametroString(3,this.usuario.getCorreoInstitucional());
        this.incluirParametroString(4,this.usuario.getContrasenia());
        this.incluirParametroString(5,this.usuario.getNombreUsuario());
        this.incluirParametroDate(6,this.usuario.getFechaRegistro());
        this.incluirParametroString(7,this.usuario.getTipoUsuario().toString());
    }

    @Override
    public Integer modificar(Usuario usuario) {
        this.usuario = usuario;
        return super.modificar();
    }    
    
    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return " NOMBRES=?,APELLIDOS=?,CORREO_INSTITUCIONAL=?,CONTRASENIA=?,NOMBRE_USUARIO=?,FECHA_REGISTRO=?,TIPO_USUARIO=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroString(1,this.usuario.getNombres());
        this.incluirParametroString(2,this.usuario.getApellidos());
        this.incluirParametroString(3,this.usuario.getCorreoInstitucional());
        this.incluirParametroString(4,this.usuario.getContrasenia());
        this.incluirParametroString(5,this.usuario.getNombreUsuario());
        this.incluirParametroDate(6,this.usuario.getFechaRegistro());
        this.incluirParametroString(7,this.usuario.getTipoUsuario().toString());
        this.incluirParametroInt(8,this.usuario.getIdUsuario());
    }

    @Override
    public Integer eliminar(Usuario usuario) {
        this.usuario = usuario;
        return super.eliminar();
    }    

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1,this.usuario.getIdUsuario());
    }
    
    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "ID_USUARIO=?";
    }
    
    @Override
    public ArrayList<Usuario> listarTodos(Usuario usuario, Integer limiteListado) {
        this.usuario = usuario;
        return (ArrayList<Usuario>) super.listarTodos(limiteListado);
    }
    
    @Override
    protected String obtenerProyeccionDeColumnasParaSelect() {
        return "ID_USUARIO,NOMBRES,APELLIDOS,CORREO_INSTITUCIONAL,CONTRASENIA,NOMBRE_USUARIO,FECHA_REGISTRO,TIPO_USUARIO";
    }
    
    @Override
    protected void cargarFiltroParaSelect_Listar() {
        if(this.usuario == null) return;
        if(super.vkf.esFiltroValido(this.usuario.getNombres())) {
            super.filtroParaSelect.add("NOMBRES LIKE '" +
                                       this.usuario.getNombres() + "%'");
        }
        if(super.vkf.esFiltroValido(this.usuario.getApellidos())) {
            super.filtroParaSelect.add("APELLIDOS LIKE '" +
                                       this.usuario.getApellidos()+ "%'");
        }
        if(super.vkf.esFiltroValido(this.usuario.getCorreoInstitucional())) {
            super.filtroParaSelect.add("CORREO_INSTITUCIONAL LIKE '" +
                                       this.usuario.getCorreoInstitucional()+ "%'");
        }
        if(super.vkf.esFiltroValido(this.usuario.getNombreUsuario())) {
            super.filtroParaSelect.add("NOMBRE_USUARIO LIKE '%" +
                                       this.usuario.getNombreUsuario()+ "%'");
        }
        if(this.usuario.getFechaRegistro() != null) {
            super.filtroParaSelect.add("(FECHA_REGISTRO IS NULL OR FECHA_REGISTRO >= " +
                                       super.vkf.toSqlString(this.usuario.getFechaRegistro()));
        }
        if(this.usuario.getTipoUsuario() != null) {
            super.filtroParaSelect.add("TIPO_USUARIO = " +
                                       super.vkf.toSqlString(this.usuario.getTipoUsuario()));
        }
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.usuario);
    }

    @Override
    public Usuario obtenerPorId(Integer idUsuario) {
        this.usuario = new Usuario();
        this.usuario.setIdUsuario(idUsuario);
        super.obtenerPorId();
        return this.usuario;
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.usuario.getIdUsuario());
    }
    
    @Override
    public Usuario obtenerPorAtributosUnicos(Usuario usuario){
        this.usuario = usuario;
        super.obtenerPorAtributosUnicos();
        return this.usuario;
    }
    
    @Override
    protected void cargarFiltroParaSelect_Obtener() {
        if(this.usuario == null) return;
        if(this.usuario.getCorreoInstitucional() != null && !this.usuario.getCorreoInstitucional().isBlank()) {
            super.filtroParaSelect.add(" CORREO_INSTITUCIONAL='" + this.usuario.getCorreoInstitucional() + "'");
        }
        if(this.usuario.getContrasenia()!=null && !this.usuario.getContrasenia().isBlank()) {
            super.filtroParaSelect.add(" CONTRASENIA='" + this.usuario.getContrasenia() + "'");
        }
        if(this.usuario.getNombreUsuario()!=null && !this.usuario.getNombreUsuario().isBlank()) {
            super.filtroParaSelect.add(" NOMBRE_USUARIO='" + this.usuario.getNombreUsuario() + "'");
        }
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException { 
        this.usuario = new Usuario();
        this.usuario.setIdUsuario(this.resultSet.getInt("ID_USUARIO"));
        this.usuario.setNombres(this.resultSet.getString("NOMBRES"));
        this.usuario.setApellidos(this.resultSet.getString("APELLIDOS"));
        this.usuario.setCorreoInstitucional(this.resultSet.getString("CORREO_INSTITUCIONAL"));
        this.usuario.setContrasenia(this.resultSet.getString("CONTRASENIA"));
        this.usuario.setNombreUsuario(this.resultSet.getString("NOMBRE_USUARIO"));
        this.usuario.setFechaRegistro(this.resultSet.getDate("FECHA_REGISTRO"));
        TipoUsuario tipo = super.vkf.toValidEnum(this.resultSet.getString("TIPO_USUARIO"), TipoUsuario.class);
        if(tipo!=null)
            this.usuario.setTipoUsuario(tipo);
        //!!!
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.usuario = null;
    }
}
