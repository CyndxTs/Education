
/* [/]
 >> Project:    SoftBibliotecaReservasDA
 >> File:       ReservaDAOImpl.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.reservas.daoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbiblioteca.config.DAOImpl;
import pe.edu.pucp.softbiblioteca.publicaciones.dao.CopiaEjemplarDAO;
import pe.edu.pucp.softbiblioteca.publicaciones.daoImpl.CopiaEjemplarDAOImpl;

import pe.edu.pucp.softbiblioteca.reservas.dao.ReservaDAO;
import pe.edu.pucp.softbiblioteca.reservas.model.Reserva;
import pe.edu.pucp.softbiblioteca.publicaciones.model.CopiaEjemplar;
import pe.edu.pucp.softbiblioteca.reservas.model.TipoEstadoReserva;
import pe.edu.pucp.softbiblioteca.usuarios.dao.UsuarioDAO;
import pe.edu.pucp.softbiblioteca.usuarios.daoImpl.UsuarioDAOImpl;

import pe.edu.pucp.softbiblioteca.usuarios.model.Usuario;

public class ReservaDAOImpl extends DAOImpl implements ReservaDAO {
    private Reserva reserva;
    private CopiaEjemplarDAO copiaEjemplarDAO;
    private UsuarioDAO usuarioDAO;
    
    public ReservaDAOImpl(){
        super("Reserva");
        this.reserva = null;
        this.copiaEjemplarDAO = null;
        this.usuarioDAO = null;
    }

    @Override
    public Integer insertar(Reserva reserva) {
        this.reserva = reserva;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
       return "INSTANTE_RESERVA,INSTANTE_RECOJO,TIPO_ESTADO_RESERVA,ID_COPIA_EJEMPLAR,ID_USUARIO";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?,?,?,?,?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroLocalDateTime(1, this.reserva.getInstanteReserva());
        this.incluirParametroLocalDateTime(2, this.reserva.getInstanteRecojo());
        this.incluirParametroString(3,this.reserva.getTipoEstado().toString());
        this.incluirParametroInt(4,this.reserva.getEjemplarAsociado().getIdCopiaEjemplar());
        this.incluirParametroInt(5,this.reserva.getUsuarioAsociado().getIdUsuario());
    }

    @Override
    public Integer modificar(Reserva reserva) {
        this.reserva = reserva;
        return super.modificar();
    }    
    
    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "INSTANTE_RESERVA=?, INSTANTE_RECOJO=?, TIPO_ESTADO_RESERVA=?, ID_COPIA_EJEMPLAR=?, ID_USUARIO=?";
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException{
        this.incluirParametroLocalDateTime(1,this.reserva.getInstanteReserva());
        this.incluirParametroLocalDateTime(2,this.reserva.getInstanteRecojo());
        this.incluirParametroString(3, this.reserva.getTipoEstado().toString());
        this.incluirParametroInt(4, this.reserva.getEjemplarAsociado().getIdCopiaEjemplar());
        this.incluirParametroInt(5, this.reserva.getUsuarioAsociado().getIdUsuario());
        this.incluirParametroInt(6, this.reserva.getIdReserva());
    }
    
    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "ID_RESERVA=?";
    }

    @Override
    public Integer eliminar(Reserva reserva) {
        this.reserva = reserva;
        return super.eliminar();
    }    
        
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.reserva.getIdReserva());
    }
   
    @Override
    public ArrayList<Reserva> listarTodos(Reserva reserva, Integer limiteListado, Integer limiteProfundidad) {
        this.reserva = reserva;
        this.limiteProfundidad = limiteProfundidad;
        return (ArrayList<Reserva>) super.listarTodos(limiteListado);
    }
    
    @Override
    protected String obtenerProyeccionDeColumnasParaSelect() {
        return "ID_RESERVA, INSTANTE_RESERVA, INSTANTE_RECOJO, TIPO_ESTADO_RESERVA, ID_COPIA_EJEMPLAR, ID_USUARIO";
    }
    
    @Override
    protected void cargarFiltroParaSelect_Listar() {
        if(this.reserva == null) return;
        if(this.reserva.getInstanteReserva() != null) {
            super.filtroParaSelect.add("INSTANTE_RESERVA >= " +
                                       super.vkf.toSqlString(this.reserva.getInstanteReserva()));
        }
        if(this.reserva.getInstanteRecojo() != null) {
            super.filtroParaSelect.add("(INSTANTE_RECOJO IS NULL OR INSTANTE_RECOJO >= " +
                                       super.vkf.toSqlString(this.reserva.getInstanteRecojo())+ ")");
        }
        if(this.reserva.getTipoEstado() != null) {
            super.filtroParaSelect.add("TIPO_ESTADO_RESERVA = " +
                                       super.vkf.toSqlString(this.reserva.getTipoEstado()));
        }
        if(this.reserva.getEjemplarAsociado() != null &&
           super.vkf.esFiltroValido(this.reserva.getEjemplarAsociado().getIdCopiaEjemplar())) {
            super.filtroParaSelect.add("ID_COPIA_EJEMPLAR =" +
                                       this.reserva.getEjemplarAsociado().getIdCopiaEjemplar());
        }
        if(this.reserva.getUsuarioAsociado()!= null &&
           super.vkf.esFiltroValido(this.reserva.getUsuarioAsociado().getIdUsuario())) {
            super.filtroParaSelect.add("ID_USUARIO =" +
                                       this.reserva.getUsuarioAsociado().getIdUsuario());
        }
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.reserva);
    }

    @Override
    public Reserva obtenerPorId(Integer idReserva) {
        this.reserva = new Reserva();
        this.reserva.setIdReserva(idReserva);
        super.obtenerPorId();
        return this.reserva;
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1,this.reserva.getIdReserva());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.reserva = new Reserva();
        this.reserva.setIdReserva(resultSet.getInt("ID_RESERVA"));
        LocalDateTime instanteReserva = resultSet.getTimestamp("INSTANTE_RESERVA").toLocalDateTime();
        this.reserva.setInstanteReserva(instanteReserva);
        this.reserva.setStr_instanteReserva(super.vkf.toValidString(instanteReserva));
        java.sql.Timestamp ts = resultSet.getTimestamp("INSTANTE_RECOJO");
        if(ts == null) {
            this.reserva.setInstanteRecojo(null);
            this.reserva.setStr_instanteRecojo(null);
        } else {
            LocalDateTime instanteRecojo = ts.toLocalDateTime();
            this.reserva.setInstanteRecojo(instanteRecojo);
            this.reserva.setStr_instanteRecojo(super.vkf.toValidString(instanteRecojo));
        }
        this.reserva.setTipoEstado(TipoEstadoReserva.valueOf(resultSet.getString("TIPO_ESTADO_RESERVA")));
        Integer idEjemplarAsociado = resultSet.getInt("ID_COPIA_EJEMPLAR");
        Integer idUsuarioAsociado = resultSet.getInt("ID_USUARIO");
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
        this.reserva.setEjemplarAsociado(ejemplarAsociado);
        this.reserva.setUsuarioAsociado(usuarioAsociado);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.reserva = null;
        this.copiaEjemplarDAO = null;
        this.usuarioDAO = null;
    }
}
