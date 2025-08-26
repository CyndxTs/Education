
/* [/]
 >> Project:    SoftBibliotecaDBManager
 >> File:       DAOImpl.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.config;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Types;
import java.time.LocalDateTime;
import pe.edu.pucp.softbiblioteca.db.DBManager;
import pe.edu.pucp.softbiblioteca.util.VKF_Formatter;

public abstract class DAOImpl {
    protected String nombre_tabla;
    protected static Connection conexion = null;
    protected CallableStatement statement;
    protected ResultSet resultSet;
    protected Boolean mostrarSentenciaSQL;
    protected Boolean retornarLlavePrimaria;
    protected Boolean usarTransaccion;
    protected Tipo_Operacion tipo_Operacion;
    protected Boolean filtrarDuplicados;
    protected ArrayList<String> combinacionParaSelect;          // Join
    protected ArrayList<String> filtroParaSelect;               // where
    protected ArrayList<String> ordenamientoParaSelect;         // order by
    protected Integer limiteParaSelect;                         // limit
    protected VKF_Formatter vkf;                                // Formato para fechas y filtros
    protected static String iniciador = null;                   // Marca de la tabla inicial de una trasaccion de busqueda
    protected static Integer profundidadInstanciacion = null;   // Nivel de profundidad de instanciacion de las composiciones en un listar todos
    protected static Integer limiteProfundidad = null;          // Limite de profundidad
    
    public DAOImpl(String nombre_tabla) {
        this.nombre_tabla = nombre_tabla;
        this.mostrarSentenciaSQL = true;
        this.retornarLlavePrimaria = false;
        this.usarTransaccion = true;
        this.tipo_Operacion = null;
        this.filtrarDuplicados = false;
        this.filtroParaSelect = null;
        this.combinacionParaSelect = null;
        this.ordenamientoParaSelect = null;
        this.vkf = new VKF_Formatter();
    }

    /*  FUNCIONES DE TRANSACCION SQL */
    
    protected void iniciarTransaccion() throws SQLException {
        this.abrirConexion();
        this.conexion.setAutoCommit(false);
    }

    protected void comitarTransaccion() throws SQLException {
        this.conexion.commit();
    }

    protected void rollbackTransaccion() throws SQLException {
        if (this.conexion != null) {
            this.conexion.rollback();
        }
    }

    protected void abrirConexion() {
        this.conexion = DBManager.getInstance().getConnection();
    }

    protected void cerrarConexion() throws SQLException {
        if (this.conexion != null) {
            this.conexion.close();
            this.conexion = null;
        }
    }

    protected void colocarSQLenStatement(String sql) throws SQLException {
        if (this.mostrarSentenciaSQL) {
            Logger.getLogger(DAOImpl.class.getName()).log(Level.INFO, "\u001B[32m" + sql + "\u001B[0m");
        }
        this.statement = this.conexion.prepareCall(sql);
    }

    protected Integer ejecutarModificacionEnBD(String sql) throws SQLException {
        return this.statement.executeUpdate();
    }

    protected void ejecutarConsultaEnBD(String sql) throws SQLException {
        this.resultSet = this.statement.executeQuery();
    }
    
    /* FUNCION DE EJECUCION DML [INSERTAR - MODIFICAR - ELIMINAR] */
    
    private Integer ejecuta_DML(Tipo_Operacion tipo_Operacion) {
        Integer resultado = -1;
        this.tipo_Operacion = tipo_Operacion;
        try {
            if (this.usarTransaccion) {
                System.out.println("Iniciando apertura de conexion.. ");
                this.iniciarTransaccion();
            }
            String sql = null;
            switch (this.tipo_Operacion) {
                case Tipo_Operacion.INSERTAR ->
                    sql = this.generarSQLParaInsercion();
                case Tipo_Operacion.MODIFICAR ->
                    sql = this.generarSQLParaModificacion();
                case Tipo_Operacion.ELIMINAR ->
                    sql = this.generarSQLParaEliminacion();
            }
            this.colocarSQLenStatement(sql);
            switch (this.tipo_Operacion) {
                case Tipo_Operacion.INSERTAR ->
                    this.incluirValorDeParametrosParaInsercion();
                case Tipo_Operacion.MODIFICAR ->
                    this.incluirValorDeParametrosParaModificacion();
                case Tipo_Operacion.ELIMINAR ->
                    this.incluirValorDeParametrosParaEliminacion();
            }
            resultado = this.ejecutarModificacionEnBD(sql);
            if (this.retornarLlavePrimaria) {
                Integer id = this.retornarUltimoAutoGenerado();
                resultado = id;
            }
            if (this.usarTransaccion) {
                this.comitarTransaccion();
            }
        } catch (SQLException ex) {
            resultado = -1;
            System.err.println("ERROR: EL DML '" + this.tipo_Operacion + "' ha fallado.\n" + ex);
            if (this.usarTransaccion) {
                try {
                    this.rollbackTransaccion();
                } catch (SQLException ex1) {
                    System.err.println("ERROR: El rollback ha fallado.\n" + ex);
                }
            }
        } finally {
            try {
                if (this.usarTransaccion) {
                    System.out.print("Cerrando la Conexion.. ");
                    this.cerrarConexion();
                    System.out.println("[SUCCESS]\n\t> CONEXION CERRADA <");
                }
            } catch (SQLException ex) {
                System.err.println("[FAILED]");
                System.err.println("ERROR: El cierre de conexión ha fallado.\n" + ex);
            }

        }
        this.tipo_Operacion = null;
        return resultado;
    }

    /* FUNCIONES PARA DML  - PRIMER ORDEN */
    
    protected Integer insertar() {
        return this.ejecuta_DML(Tipo_Operacion.INSERTAR);
    }
    
    protected Integer modificar() {
        return this.ejecuta_DML(Tipo_Operacion.MODIFICAR);
    }
    
    protected Integer eliminar() {
        return this.ejecuta_DML(Tipo_Operacion.ELIMINAR);
    }

    /* FUNCIONES PARA DML  - SEGUNDO ORDEN */
    
    protected String generarSQLParaInsercion() {
        String sql = "INSERT INTO ";
        sql = sql.concat(this.nombre_tabla);
        sql = sql.concat(" (");
        sql = sql.concat(this.obtenerListaDeAtributosParaInsercion());
        sql = sql.concat(") VALUES (");
        sql = sql.concat(this.incluirListaDeParametrosParaInsercion());
        sql = sql.concat(")");
        return sql;
    }
    
    protected String generarSQLParaModificacion() {
        String sql = "UPDATE ";
        sql = sql.concat(this.nombre_tabla);
        sql = sql.concat(" SET ");
        sql = sql.concat(this.obtenerListaDeValoresYAtributosParaModificacion());
        sql = sql.concat(" ");
        sql = sql.concat("WHERE ");
        sql = sql.concat(this.obtenerPredicadoParaLlavePrimaria());
        return sql;
    }
    
    private String generarSQLParaEliminacion() {
        String sql = "DELETE FROM ".concat(this.nombre_tabla);
        sql = sql.concat(" WHERE ");
        sql = sql.concat(this.obtenerPredicadoParaLlavePrimaria());
        return sql;
    }
    
    public Integer retornarUltimoAutoGenerado() {
        Integer resultado = null;
        try {
            String sql = "SELECT @@last_insert_id AS id";
            this.colocarSQLenStatement(sql);
            this.ejecutarConsultaEnBD(sql);
            if (this.resultSet.next()) {
                resultado = this.resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            System.err.println("ERROR: El retorno del ultimo autogenerado ha fallado..\n" + ex);
        }
        return resultado;
    }
    
    /* FUNCIONES PARA DML  - TERCER ORDEN */

    protected abstract String obtenerListaDeAtributosParaInsercion();

    protected abstract String incluirListaDeParametrosParaInsercion();

    protected abstract void incluirValorDeParametrosParaInsercion() throws SQLException;
    
    protected String obtenerListaDeValoresYAtributosParaModificacion() { return ""; };
    
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {};
    
    protected abstract void incluirValorDeParametrosParaEliminacion() throws SQLException;
    
    protected abstract String obtenerPredicadoParaLlavePrimaria();
    
    /* FUNCION DE EJECUCION LISTADO [LISTARTODOS - LISTARTODOS_PORINTERMEDIARIO]  */

    private List ejecuta_Listado(Tipo_Operacion tipo_operacion) {
        List lista = new ArrayList<>();
        this.tipo_Operacion = tipo_operacion;
        try {
            System.out.println("Iniciando apertura de conexion.. ");
            if(this.conexion == null) this.abrirConexion();
            else System.out.println("\t> CONEXION MANTENIDA <");
            if(this.iniciador == null) {
                this.iniciador = this.nombre_tabla;
                profundidadInstanciacion = 1;
            }
            String sql = this.generarSQLParaListado();
            this.colocarSQLenStatement(sql);
            this.incluirValorDeParametrosParaListado();
            this.ejecutarConsultaEnBD(sql);
            while (this.resultSet.next()) {
                agregarObjetoALaLista(lista,this.resultSet);
            }
        } catch (SQLException ex) {
            System.err.println("ERROR: '" + tipo_Operacion.toString() +"' ha fallado.\n" + ex);
        } finally {
            try {
                if(this.iniciador != null && this.iniciador.compareTo(this.nombre_tabla) == 0) {
                    this.iniciador = null;
                    this.profundidadInstanciacion = null;
                    this.limiteProfundidad = null;
                    System.out.print("Cerrando la conexion.. ");
                    this.cerrarConexion();
                    System.out.println("[SUCCESS]\n\t> CONEXION CERRADA <");                    
                }
            } catch (SQLException ex) {
                System.err.println("[FAILED]");
                System.err.println("ERROR: El cierre de conexión ha fallado.\n" + ex);
            }
        }
        return lista;
    }
    
    /* FUNCIONES DE LISTADO  - PRIMER ORDEN */
    
    public List listarTodos(Integer limite) {
        this.limiteParaSelect = limite;
        return this.ejecuta_Listado(Tipo_Operacion.LISTARTODOS);
    }
    
    public List listarTodosPorIntermediario(Integer limite) {
        if(this.vkf.esFiltroValido(limite)) this.limiteParaSelect = limite;
        return this.ejecuta_Listado(Tipo_Operacion.LISTARTODOS_PORINTERMEDIARIO);
    }
    
    /* FUNCIONES DE LISTADO  - SEGUNDO ORDEN */
    
    protected String generarSQLParaListado() {
        String sql = "SELECT ";
        if(this.filtrarDuplicados) sql = sql.concat(" DISTINCT ");
        sql = sql.concat(this.obtenerProyeccionDeColumnasParaSelect()).concat(" FROM ");
        switch (this.tipo_Operacion) {
            case Tipo_Operacion.LISTARTODOS -> {
                sql = sql.concat(this.nombre_tabla);
            }
            case Tipo_Operacion.LISTARTODOS_PORINTERMEDIARIO -> {
                sql = sql.concat(this.obtenerProyeccionDeTablasParaSelect());
                this.combinacionParaSelect = new ArrayList<String>();
                cargarCombinacionParaSelect();
                sql = sql.concat(obtenerCombinacionParaSelect());
                this.combinacionParaSelect = null;
            }
        }
        this.filtroParaSelect = new ArrayList<String>();
        cargarFiltroParaSelect_Listar();
        sql = sql.concat(obtenerFiltroParaSelect());
        this.filtroParaSelect = null;
        this.ordenamientoParaSelect = new ArrayList<String>();
        cargarOrdenamientoParaSelect();
        sql = sql.concat(obtenerOrdenamientoParaSelect());
        this.ordenamientoParaSelect = null;
        sql = sql.concat(obtenerLimiteParaSelect());
        return sql;
    }

    protected void incluirValorDeParametrosParaListado() throws SQLException {}
        
    protected abstract void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException;
    
    /* FUNCIONES DE LISTADO  - TERCER ORDEN */
        
    protected void cargarFiltroParaSelect_Listar() {}
    
    /* FUNCION DE EJECUCION EXTRACCION [OBTENERPORID - OBTENERPORATRIBUTOSUNICOS]  */
    
    public void ejecuta_Extraccion(Tipo_Operacion tipo_operacion) {
        this.tipo_Operacion = tipo_operacion;
        try {
            System.out.println("Iniciando apertura de conexion.. ");
            if(this.conexion == null) this.abrirConexion();
            else System.out.println("\t> CONEXION MANTENIDA <");
            if(this.iniciador == null) this.iniciador = this.nombre_tabla;
            if(this.profundidadInstanciacion != null) this.profundidadInstanciacion++;
            String sql = this.generarSQLParaExtraccion();
            this.colocarSQLenStatement(sql);
            switch(this.tipo_Operacion){
                case Tipo_Operacion.OBTENER_PORID -> {
                     incluirValorDeParametrosParaObtenerPorId();
                }
                case Tipo_Operacion.OBTENER_PORATRIBUTOSUNICOS -> {
                     incluirValorDeParametrosParaObtenerPorAtribtosUnicos();
                }
            }
            this.ejecutarConsultaEnBD(sql);
            if (this.resultSet.next()) {
                instanciarObjetoDelResultSet();
            } else {
                limpiarObjetoDelResultSet();
            }
        } catch (SQLException ex) {
            limpiarObjetoDelResultSet();
            System.err.println("ERROR: '" + tipo_operacion.toString() + "' ha fallado.\n" + ex);
        } finally {
            try {
                if(this.profundidadInstanciacion != null) this.profundidadInstanciacion--;
                if(this.iniciador != null && this.iniciador.compareTo(this.nombre_tabla) == 0) {
                    this.iniciador = null;
                    this.profundidadInstanciacion = null;
                    this.limiteProfundidad = null;
                    System.out.print("Cerrando la Conexion.. ");
                    this.cerrarConexion();
                    System.out.println("[SUCCESS]\n\t> CONEXION CERRADA <"); 
                }
            } catch (SQLException ex) {
                System.out.println("[FAILED]");
                System.err.println("ERROR: El cierre de conexión ha fallado.\n" + ex);
            }
        }
    }
    
    /* FUNCIONES DE BUSQUEDA  - PRIMER ORDEN */
    
    public void obtenerPorId() {
        this.ejecuta_Extraccion(Tipo_Operacion.OBTENER_PORID);
    }
    
    public void obtenerPorAtributosUnicos() {
        this.ejecuta_Extraccion(Tipo_Operacion.OBTENER_PORATRIBUTOSUNICOS);
    }
    
    /* FUNCIONES DE BUSQUEDA  - SEGUNDO ORDEN */
    
    protected String generarSQLParaExtraccion() {
        String sql = "SELECT ";
        sql = sql.concat(this.obtenerProyeccionDeColumnasParaSelect());
        sql = sql.concat(" FROM ").concat(this.nombre_tabla);
        switch (this.tipo_Operacion) {
            case Tipo_Operacion.OBTENER_PORID:
                sql = sql.concat(" WHERE ").concat(this.obtenerPredicadoParaLlavePrimaria());
                break;
            case Tipo_Operacion.OBTENER_PORATRIBUTOSUNICOS:
                this.filtroParaSelect = new ArrayList<String>();
                cargarFiltroParaSelect_Obtener();
                sql = sql.concat(obtenerFiltroParaSelect());
                this.filtroParaSelect = null;
                break;
        }
        return sql;
    }
    
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {};
    
    protected void incluirValorDeParametrosParaObtenerPorAtribtosUnicos() throws SQLException {}

    /* FUNCIONES DE BUSQUEDA  - TERCER ORDEN */
 
    protected void cargarFiltroParaSelect_Obtener() {}
    
    /* FUNCIONES INDEPENDIENTES ENTRE LISTADO Y BUSQUEDA */
    
    protected abstract String obtenerProyeccionDeColumnasParaSelect();
    
    protected String obtenerProyeccionDeTablasParaSelect() {
        return "";
    }
    
    protected void cargarCombinacionParaSelect() {}
    
    protected String obtenerCombinacionParaSelect() {
        String sql = "";
        if(!combinacionParaSelect.isEmpty()) {
            for(int i = 0;i < combinacionParaSelect.size();i++) {
                sql = sql.concat(" JOIN ").concat(combinacionParaSelect.get(i));
            }
        }
        return sql;
    }
    
    protected String obtenerFiltroParaSelect() {
        String sql = "";
        if(!filtroParaSelect.isEmpty()) {
            sql = sql.concat(" WHERE ").concat(filtroParaSelect.get(0));
            for(int i = 1;i < filtroParaSelect.size();i++){
                sql = sql.concat(" AND ").concat(filtroParaSelect.get(i));
            }
        }
        return sql;
    }
    
    protected void cargarOrdenamientoParaSelect() {}
    
    protected String obtenerOrdenamientoParaSelect() {
        String sql = "";
        if(!ordenamientoParaSelect.isEmpty()) {
            sql = sql.concat(" ORDER BY ").concat(ordenamientoParaSelect.get(0));
            for(int i = 1;i < ordenamientoParaSelect.size();i++) {
                sql = sql.concat(", ").concat(ordenamientoParaSelect.get(i));
            }
        }
        return sql;
    }

    protected String obtenerLimiteParaSelect() {
        String sql = "";
        if (this.limiteParaSelect != null && this.limiteParaSelect > 0) {
            sql = sql.concat(" LIMIT ").concat(this.limiteParaSelect.toString());
        }
        return sql;
    }
    
    protected abstract void instanciarObjetoDelResultSet() throws SQLException;

    protected abstract void limpiarObjetoDelResultSet();
    
    /* Los siguientes métodos:
        - ObtenerFechaParaSQL
        - ObtenerStringParaSQL
        - ObtenerIntegerParaSQL
        - ObtenerBooleanParaSQL
        fueron usados en la versión de la clase en donde se trabajaba con un statement
        razón por la cual era necesario que se manejase la nulidad en el comando SQL.
        La actual versión de esta clase implementa el manejo de parámetros a través de los
        métodos:
        - incluirParametroString
        - incluirParametroInt
        - incluirParametroDate
        - incluirParametroBoolean
     */
    protected String ObtenerFechaParaSQL(Date fecha) {
        String sql = "NULL";
        if (fecha != null) {
            sql = "STR_TO_DATE";
            sql = sql.concat("(");
            sql = sql.concat("'");

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            Integer anho = calendar.get(Calendar.YEAR);
            Integer mes = calendar.get(Calendar.MONTH) + 1;
            Integer dia = calendar.get(Calendar.DAY_OF_MONTH);
            Integer hora = calendar.get(Calendar.HOUR_OF_DAY);
            Integer minuto = calendar.get(Calendar.MINUTE);
            Integer segundo = calendar.get(Calendar.SECOND);

            sql = sql.concat(anho.toString());
            sql = sql.concat("/");
            sql = sql.concat(String.format("%02d", mes));
            sql = sql.concat("/");
            sql = sql.concat(String.format("%02d", dia));
            sql = sql.concat(" ");
            sql = sql.concat(String.format("%02d", hora));
            sql = sql.concat(":");
            sql = sql.concat(String.format("%02d", minuto));
            sql = sql.concat(":");
            sql = sql.concat(String.format("%02d", segundo));

            sql = sql.concat("', ");
            sql = sql.concat("'");
            sql = sql.concat("%Y/%m/%d %H:%i:%S");
            sql = sql.concat("'");
            sql = sql.concat(")");
        }
        return sql;
    }

    protected String ObtenerStringParaSQL(String valor) {
        String sql = "NULL";
        if (valor != null) {
            sql = "'";
            sql = sql.concat(valor);
            sql = sql.concat("'");
        }
        return sql;
    }

    protected String ObtenerIntegerParaSQL(Integer valor) {
        String sql = "NULL";
        if (valor != null) {
            sql = valor.toString();
        }
        return sql;
    }

    protected String ObtenerBooleanParaSQL(Boolean valor) {
        String sql = "NULL";
        if (valor != null) {
            if (valor) {
                sql = "1";
            } else {
                sql = "0";
            }
        }
        return sql;
    }

    

    protected void incluirParametroString(Integer numeroParametro, String valor) throws SQLException {
        if (valor == null) {
            this.statement.setNull(numeroParametro, Types.VARCHAR);
        } else {
            this.statement.setString(numeroParametro, valor);
        }
    }

    protected void incluirParametroInt(Integer numeroParametro, Integer valor) throws SQLException {
        if (valor == null) {
            this.statement.setNull(numeroParametro, Types.INTEGER);
        } else {
            this.statement.setInt(numeroParametro, valor);
        }
    }

    protected void incluirParametroDate(Integer numeroParametro, Date valor) throws SQLException {
        if (valor == null) {
            this.statement.setNull(numeroParametro, Types.DATE);
        } else {
             java.sql.Date sqlDate = new java.sql.Date(valor.getTime());
            this.statement.setDate(numeroParametro, sqlDate);
        }
    }
    
    protected void incluirParametroLocalDateTime(Integer numeroParametro, LocalDateTime valor) throws SQLException {
        if (valor == null) {
            this.statement.setNull(numeroParametro, Types.TIMESTAMP);
        } else {
            java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf(valor);
            this.statement.setTimestamp(numeroParametro, sqlTimestamp);
        }
    }

    protected void incluirParametroBoolean(Integer numeroParametro, Boolean valor) throws SQLException {
        if (valor == null) {
            this.statement.setNull(numeroParametro, Types.BOOLEAN);
        } else {
            this.statement.setBoolean(numeroParametro, valor);
        }
    }

    protected void incluirParametroString(String nombreParametro, String valor) throws SQLException {
        if (valor == null) {
            this.statement.setNull(nombreParametro, Types.VARCHAR);
        } else {
            this.statement.setString(nombreParametro, valor);
        }
    }

    protected void incluirParametroInt(String nombreParametro, Integer valor) throws SQLException {
        if (valor == null) {
            this.statement.setNull(nombreParametro, Types.DATE);
        } else {
            this.statement.setInt(nombreParametro, valor);
        }
    }
    
    protected void incluirParametroDouble(Integer numeroParametro, Double valor) throws SQLException{
        if(valor == null){
            this.statement.setNull(numeroParametro, Types.DOUBLE);
        }else{
            this.statement.setDouble(numeroParametro, valor);
        }
    }

    protected void incluirParametroDate(String nombreParametro, Date valor) throws SQLException {
        java.sql.Date sqlDate = new java.sql.Date(valor.getTime());
        if (valor == null) {
            this.statement.setNull(nombreParametro, Types.DATE);
        } else {
            this.statement.setDate(nombreParametro, sqlDate);
        }
    }

    protected void incluirParametroBoolean(String nombreParametro, Boolean valor) throws SQLException {
        if (valor == null) {
            this.statement.setNull(nombreParametro, Types.BOOLEAN);
        } else {
            this.statement.setBoolean(nombreParametro, valor);
        }
    }
    
    protected void incluirParametroBytes(Integer numeroParametro, byte[] valor) throws SQLException{
        if(valor == null){
            this.statement.setNull(numeroParametro, Types.BINARY);
        }else{
            this.statement.setBytes(numeroParametro, valor);
        }
    }
}
