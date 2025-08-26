
/* [/]
 >> Project:    SoftBibliotecaPrestamosDA
 >> File:       PenalizacionDAOImpl.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.prestamos.daoImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbiblioteca.config.DAOImpl;
import pe.edu.pucp.softbiblioteca.prestamos.dao.PenalizacionDAO;
import pe.edu.pucp.softbiblioteca.prestamos.dao.PrestamoDAO;
import pe.edu.pucp.softbiblioteca.prestamos.model.Penalizacion;
import pe.edu.pucp.softbiblioteca.prestamos.model.Prestamo;

public class PenalizacionDAOImpl extends DAOImpl implements PenalizacionDAO {
    private Penalizacion penalizacion;
    private PrestamoDAO prestamoDAO;
    public PenalizacionDAOImpl() {
        super("Penalizacion");
        this.penalizacion = null;
        this.prestamoDAO = null;
    }

    @Override
    public Integer insertar(Penalizacion penalizacion) {
        this.penalizacion = penalizacion;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }
    
    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "DESCRIPCION,MONTO,ID_PRESTAMO, FECHA_IMPOSICION, ESTA_ACTIVA";
    }
    
    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?,?,?,?,?";
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException{
        this.incluirParametroString(1,penalizacion.getDescripcion());
        this.incluirParametroDouble(2,(double)penalizacion.getMonto());
        this.incluirParametroInt(3, penalizacion.getPrestamoAsociado().getIdPrestamo());
        this.incluirParametroDate(4, penalizacion.getFechaImposicion());
        this.incluirParametroBoolean(5, penalizacion.getEstaActivo());
    }
    
    @Override
    public Integer modificar(Penalizacion penalizacion) {
        this.penalizacion = penalizacion;
        return modificar();
    }
    
    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "DESCRIPCION=?, MONTO=?, ID_PRESTAMO=?, FECHA_IMPOSICION=?, ESTA_ACTIVA=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException{
        this.incluirParametroString(1, this.penalizacion.getDescripcion());
        this.incluirParametroDouble(2,(double)this.penalizacion.getMonto());
        this.incluirParametroInt(3, this.penalizacion.getPrestamoAsociado().getIdPrestamo());
        this.incluirParametroDate(4, penalizacion.getFechaImposicion());
        this.incluirParametroBoolean(5, penalizacion.getEstaActivo());
        this.incluirParametroInt(6,this.penalizacion.getIdPenalizacion());
    }

    @Override
    public Integer eliminar(Penalizacion penalizacion) {
        this.penalizacion = penalizacion;
        return super.eliminar();
    }
    
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.penalizacion.getIdPenalizacion());
    }
    
    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "ID_PENALIZACION=?";
    }
   
    @Override
    public ArrayList<Penalizacion> listarTodos(Penalizacion penalizacion, Integer limiteListado, Integer limiteProfundidad){
        this.penalizacion = penalizacion;
        this.limiteProfundidad = limiteProfundidad;
        return (ArrayList<Penalizacion>) super.listarTodos(limiteListado);
    }
    
    @Override
    protected String obtenerProyeccionDeColumnasParaSelect() {
        return "ID_PENALIZACION,DESCRIPCION,MONTO,ID_PRESTAMO, FECHA_IMPOSICION, ESTA_ACTIVA";
    }
    
    @Override
    protected void cargarFiltroParaSelect_Listar() {
        if(this.penalizacion == null) return;
        if(this.penalizacion.getPrestamoAsociado() != null &&
           super.vkf.esFiltroValido(this.penalizacion.getPrestamoAsociado().getIdPrestamo())) {
           super.filtroParaSelect.add("ID_PRESTAMO = " +
                                       this.penalizacion.getPrestamoAsociado().getIdPrestamo());
        }
        if(this.penalizacion.getFechaImposicion() != null) {
            super.filtroParaSelect.add("(FECHA_IMPOSICION IS NULL OR FECHA_IMPOSICION >= " +
                                       super.vkf.toSqlString(this.penalizacion.getFechaImposicion()) + ")");
        }
        if(this.penalizacion.getEstaActivo() != null) {
            super.filtroParaSelect.add("ESTA_ACTIVA = " +
                                       super.vkf.toSqlString(this.penalizacion.getEstaActivo()));
        }
    }
    
    @Override
    protected void cargarOrdenamientoParaSelect() {
        super.ordenamientoParaSelect.add("ESTA_ACTIVA DESC");
        super.ordenamientoParaSelect.add("FECHA_IMPOSICION DESC");
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.penalizacion);
    }
    
    @Override
    public Penalizacion obtenerPorId(Integer idPenalizacion){
        this.penalizacion = new Penalizacion();
        this.penalizacion.setIdPenalizacion(idPenalizacion);
        super.obtenerPorId();
        return this.penalizacion;
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.penalizacion.getIdPenalizacion());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.penalizacion = new Penalizacion();
        this.penalizacion.setIdPenalizacion(resultSet.getInt("ID_PENALIZACION"));
        this.penalizacion.setDescripcion(resultSet.getString("DESCRIPCION"));
        this.penalizacion.setMonto(resultSet.getFloat("MONTO"));
        Integer idPrestamoAsociado = resultSet.getInt("ID_PRESTAMO");
        this.penalizacion.setFechaImposicion(resultSet.getDate("FECHA_IMPOSICION"));
        this.penalizacion.setEstaActivo(resultSet.getBoolean("ESTA_ACTIVA"));
        Prestamo prestamoAsociado;
        if(!super.vkf.esFiltroValido(this.limiteProfundidad) ||
           (super.vkf.esFiltroValido(this.profundidadInstanciacion) &&
            this.profundidadInstanciacion < this.limiteProfundidad)){
            this.prestamoDAO = new PrestamoDAOImpl();
            prestamoAsociado = this.prestamoDAO.obtenerPorId(idPrestamoAsociado);
        } else {
            prestamoAsociado = new Prestamo();
            prestamoAsociado.setIdPrestamo(idPrestamoAsociado);
        }
        this.penalizacion.setPrestamoAsociado(prestamoAsociado);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.penalizacion = null;
        this.prestamoDAO = null;
    }
}
