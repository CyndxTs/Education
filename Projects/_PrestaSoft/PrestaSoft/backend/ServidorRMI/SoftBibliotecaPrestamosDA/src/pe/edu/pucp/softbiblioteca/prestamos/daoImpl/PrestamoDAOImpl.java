
/* [/]
 >> Project:    SoftBibliotecaPrestamosDA
 >> File:       PrestamoDAOImpl.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.prestamos.daoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbiblioteca.config.DAOImpl;
import pe.edu.pucp.softbiblioteca.prestamos.dao.PrestamoDAO;
import pe.edu.pucp.softbiblioteca.prestamos.model.Prestamo;
import pe.edu.pucp.softbiblioteca.prestamos.model.TipoEstadoPrestamo;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.CopiaEjemplarDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.CopiaEjemplarDAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.model.CopiaEjemplar;
import pe.edu.pucp.softbiblioteca.usuarios.dao.UsuarioDAO;
import pe.edu.pucp.softbiblioteca.usuarios.daoImpl.UsuarioDAOImpl;
import pe.edu.pucp.softbiblioteca.usuarios.model.Usuario;

public class PrestamoDAOImpl extends DAOImpl implements PrestamoDAO{
    private Prestamo prestamo;
    private CopiaEjemplarDAO copiaEjemplarDAO;
    private UsuarioDAO usuarioDAO;
    
    public PrestamoDAOImpl() {
        super("Prestamo");
        this.prestamo = null;
        this.copiaEjemplarDAO = null;
        this.usuarioDAO = null;
    }

    @Override
    public Integer insertar(Prestamo prestamo) {
        this.prestamo = prestamo;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }
    
    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "INSTANTE_PRESTAMO,FECHA_DEVOLUCION_ESPERADA, FECHA_DEVOLUCION_REAL, ID_COPIA_EJEMPLAR,TIPO_ESTADO_PRESTAMO,HUBO_RESERVA_PREVIA,ID_USUARIO";
    }
    
    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?, ?, ?, ?, ?, ?, ?";
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException{
        this.incluirParametroLocalDateTime(1, this.prestamo.getInstantePrestamo());
        this.incluirParametroDate(2, this.prestamo.getFechaDevolucionEsperada());
        if(this.prestamo.getFechaDevolucionReal() != null) {
            this.incluirParametroDate(3, this.prestamo.getFechaDevolucionReal());
        } else this.incluirParametroDate(3, null);
        this.incluirParametroInt(4, this.prestamo.getEjemplarAsociado().getIdCopiaEjemplar());
        this.incluirParametroString(5, this.prestamo.getTipoEstado().toString());
        this.incluirParametroBoolean(6, this.prestamo.isHuboReservaPrevia());
        this.incluirParametroInt(7, this.prestamo.getUsuarioAsociado().getIdUsuario());
    }
 
    @Override
    public Integer modificar(Prestamo prestamo) {
        this.prestamo = prestamo;
        return super.modificar();
    }
    
    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "INSTANTE_PRESTAMO=?,FECHA_DEVOLUCION_ESPERADA=?, FECHA_DEVOLUCION_REAL=?,ID_COPIA_EJEMPLAR=?, TIPO_ESTADO_PRESTAMO=?, HUBO_RESERVA_PREVIA=?, ID_USUARIO=?";
    }
    
    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "ID_PRESTAMO=?";
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroLocalDateTime(1, this.prestamo.getInstantePrestamo());
        this.prestamo.setStrInstantePrestamo(this.prestamo.getInstantePrestamo().toString());
        this.incluirParametroDate(2, this.prestamo.getFechaDevolucionEsperada());
        if(this.prestamo.getFechaDevolucionEsperada()!= null) {
            this.incluirParametroDate(3, this.prestamo.getFechaDevolucionEsperada());
        } else this.incluirParametroDate(3, null);
        this.incluirParametroInt(4, this.prestamo.getEjemplarAsociado().getIdCopiaEjemplar());
        this.incluirParametroString(5, this.prestamo.getTipoEstado().toString());
        this.incluirParametroBoolean(6, this.prestamo.isHuboReservaPrevia());
        this.incluirParametroInt(7, this.prestamo.getUsuarioAsociado().getIdUsuario());
        this.incluirParametroInt(8, this.prestamo.getIdPrestamo());
    }

    @Override
    public Integer eliminar(Prestamo prestamo) {
        this.prestamo = prestamo;
        return super.eliminar();
    }
    
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.prestamo.getIdPrestamo());
    }
    
    @Override
    public ArrayList<Prestamo> listarTodos(Prestamo prestamo, Integer limiteListado, Integer limiteProfundidad) {
        this.prestamo = prestamo;
        this.limiteProfundidad = limiteProfundidad;
        return (ArrayList<Prestamo>)super.listarTodos(limiteListado);
    }
    
    @Override
    protected String obtenerProyeccionDeColumnasParaSelect() {
        return "ID_PRESTAMO, INSTANTE_PRESTAMO, FECHA_DEVOLUCION_ESPERADA, FECHA_DEVOLUCION_REAL" +
               ", ID_COPIA_EJEMPLAR, TIPO_ESTADO_PRESTAMO, HUBO_RESERVA_PREVIA, ID_USUARIO";
    }
    
    @Override
    protected void cargarFiltroParaSelect_Listar() {
        if(this.prestamo == null) return;
        if(this.prestamo.getInstantePrestamo()!= null) {
            super.filtroParaSelect.add("INSTANTE_PRESTAMO >= " +
                                        super.vkf.toSqlString(this.prestamo.getInstantePrestamo()));
        }
        if(this.prestamo.getFechaDevolucionEsperada()!= null) {
            super.filtroParaSelect.add("(FECHA_DEVOLUCION_ESPERADA IS NULL OR FECHA_DEVOLUCION_ESPERADA >= " +
                                       super.vkf.toSqlString(this.prestamo.getFechaDevolucionEsperada())+ ")");
        }
        if(this.prestamo.getFechaDevolucionEsperada()!= null) {
            super.filtroParaSelect.add("(FECHA_DEVOLUCION_REAL IS NULL OR FECHA_DEVOLUCION_REAL >= " +
                                       super.vkf.toSqlString(this.prestamo.getFechaDevolucionEsperada())+ ")");
        }
        if(this.prestamo.getEjemplarAsociado()!= null &&
           super.vkf.esFiltroValido(this.prestamo.getEjemplarAsociado().getIdCopiaEjemplar())) {
            super.filtroParaSelect.add("ID_COPIA_EJEMPLAR= " +
                                       this.prestamo.getEjemplarAsociado().getIdCopiaEjemplar());
        }
        if(this.prestamo.getTipoEstado() != null) {
            super.filtroParaSelect.add("TIPO_ESTADO_PRESTAMO = " +
                                       super.vkf.toSqlString(this.prestamo.getTipoEstado()));
        }
        if(this.prestamo.isHuboReservaPrevia()!= null) {
            super.filtroParaSelect.add("HUBO_RESERVA_PREVIA = " +
                                       super.vkf.toSqlString(this.prestamo.isHuboReservaPrevia()));
        }
        if(this.prestamo.getUsuarioAsociado()!= null &&
           super.vkf.esFiltroValido(this.prestamo.getUsuarioAsociado().getIdUsuario())) {
            super.filtroParaSelect.add("ID_USUARIO = " +
                                       this.prestamo.getUsuarioAsociado().getIdUsuario());
        }
    }
    
    @Override
    protected void cargarOrdenamientoParaSelect() {
        super.ordenamientoParaSelect.add("INSTANTE_PRESTAMO DESC");
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        instanciarObjetoDelResultSet();        
        lista.add(this.prestamo);
    }
    
    @Override
    public Prestamo obtenerPorId(Integer idPrestamo) {
        this.prestamo = new Prestamo();
        this.prestamo.setIdPrestamo(idPrestamo);
        super.obtenerPorId();
        return this.prestamo;
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.prestamo.getIdPrestamo());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.prestamo = new Prestamo();
        this.prestamo.setIdPrestamo(resultSet.getInt("ID_PRESTAMO"));        
        this.prestamo.setInstantePrestamo(resultSet.getTimestamp("INSTANTE_PRESTAMO").toLocalDateTime());
        this.prestamo.setStrInstantePrestamo(super.vkf.toValidString(this.prestamo.getInstantePrestamo()));
        this.prestamo.setFechaDevolucionEsperada(resultSet.getDate("FECHA_DEVOLUCION_ESPERADA"));
        this.prestamo.setFechaDevolucionReal(resultSet.getDate("FECHA_DEVOLUCION_REAL"));
        Integer idEjemplarAsociado = this.resultSet.getInt("ID_COPIA_EJEMPLAR");
        this.prestamo.setTipoEstado(TipoEstadoPrestamo.valueOf(resultSet.getString("TIPO_ESTADO_PRESTAMO")));
        this.prestamo.setHuboReservaPrevia(resultSet.getBoolean("HUBO_RESERVA_PREVIA"));
        Integer idUsuarioAsociado = this.resultSet.getInt("ID_USUARIO");
        CopiaEjemplar ejemplarAsociado;
        Usuario usuarioAsociado;
        if(!super.vkf.esFiltroValido(this.limiteProfundidad) ||
           (super.vkf.esFiltroValido(this.profundidadInstanciacion) &&
            this.profundidadInstanciacion < this.limiteProfundidad)){
            this.copiaEjemplarDAO = new CopiaEjemplarDAOImpl();
            ejemplarAsociado = this.copiaEjemplarDAO.obtenerPorId(idEjemplarAsociado);
            this.usuarioDAO = new UsuarioDAOImpl();
            usuarioAsociado = this.usuarioDAO.obtenerPorId(idUsuarioAsociado);
        } else {
            ejemplarAsociado = new CopiaEjemplar();
            ejemplarAsociado.setIdCopiaEjemplar(idEjemplarAsociado);
            usuarioAsociado = new Usuario();
            usuarioAsociado.setIdUsuario(idUsuarioAsociado);
        }
        this.prestamo.setEjemplarAsociado(ejemplarAsociado);
        this.prestamo.setUsuarioAsociado(usuarioAsociado);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.prestamo = null;
        this.copiaEjemplarDAO = null;
        this.usuarioDAO = null;
    }
}
