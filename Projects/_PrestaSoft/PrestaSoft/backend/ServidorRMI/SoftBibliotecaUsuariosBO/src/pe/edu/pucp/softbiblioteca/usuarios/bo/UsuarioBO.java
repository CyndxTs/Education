    /* [/]
 >> @Project:    SoftBibliotecaUsuariosBO
 >> @File:       UsuarioBO.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.usuarios.bo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softbiblioteca.localidad.model.Consorcio;
import pe.edu.pucp.softbiblioteca.localidad.model.Universidad;
import pe.edu.pucp.softbiblioteca.politicas.model.Politica;
import pe.edu.pucp.softbiblioteca.usuarios.dao.UsuarioDAO;
import pe.edu.pucp.softbiblioteca.usuarios.dao.Usuario_UniversidadDAO;
import pe.edu.pucp.softbiblioteca.usuarios.daoImpl.UsuarioDAOImpl;
import pe.edu.pucp.softbiblioteca.usuarios.daoImpl.Usuario_UniversidadDAOImpl;
import pe.edu.pucp.softbiblioteca.usuarios.model.TipoUsuario;
import pe.edu.pucp.softbiblioteca.usuarios.model.Usuario;
import pe.edu.pucp.softbiblioteca.util.Cifrado;
import pe.edu.pucp.softbiblioteca.util.VKF_Formatter;

public class UsuarioBO {
    private UsuarioDAO usuarioDAO;
    private Usuario_UniversidadDAO usuario_universidadDAO;
    private SimpleDateFormat sdf;
    private VKF_Formatter vkf;
    private Cifrado cifrado;
    public UsuarioBO(){
        this.usuarioDAO = new UsuarioDAOImpl();
        this.usuario_universidadDAO = new Usuario_UniversidadDAOImpl();
        this.sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.vkf = new VKF_Formatter();
        this.cifrado = new Cifrado();
    }
    
    public Integer insertar(String nombres,String apellidos,String correo,
                            String contrasenia,String nombreUsuario,String fechaRegistro,
                            String tipoUsuario) throws ParseException {
        Date d_fechaRegistro = this.sdf.parse(fechaRegistro);
        Usuario usuario = new Usuario(null,nombres,apellidos,correo,contrasenia,
                                      nombreUsuario,d_fechaRegistro,TipoUsuario.valueOf(tipoUsuario));
        return this.usuarioDAO.insertar(usuario);
    }
    


    public Integer eliminar(Integer idUsuario) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        return usuarioDAO.eliminar(usuario);
    }
    
    //-------------------------------------------------------LOS DE ABAJO VAN EN SW----------------------------------
    
    //YA TIENE CIFRADO***********************************************
    public Integer modificar(Integer idUsuario,String nombres,String apellidos,String correo,
                            String contrasenia,String nombreUsuario,String fechaRegistro,
                            String tipoUsuario) throws ParseException  {
        Date d_fechaRegistro = this.sdf.parse(fechaRegistro);
        contrasenia = Cifrado.cifrarMD5(contrasenia);
        Usuario usuario = new Usuario(idUsuario,nombres,apellidos,correo,contrasenia,
                                      nombreUsuario,d_fechaRegistro,TipoUsuario.valueOf(tipoUsuario));
        return this.usuarioDAO.modificar(usuario);
    }
    
    //YA TIENE CIFRADO***********************************************
    public Usuario iniciarSesion(String correo_nombreusuario, String contrasenia){
        Usuario usuarioABuscar = new Usuario();
        Usuario usuarioRecibido = new Usuario();
        //CIFRAMOS CONTRASENIA PARA BUSCAR EN BD
        contrasenia = Cifrado.cifrarMD5(contrasenia);
        if(correo_nombreusuario.contains("@")){
            usuarioABuscar.setCorreoInstitucional(correo_nombreusuario);
            usuarioABuscar.setContrasenia(contrasenia);
        }else{
            usuarioABuscar.setNombreUsuario(correo_nombreusuario);
            usuarioABuscar.setContrasenia(contrasenia);            
        }
        usuarioRecibido = usuarioDAO.obtenerPorAtributosUnicos(usuarioABuscar);
        if(usuarioRecibido!=null){
            usuarioRecibido.setContrasenia(Cifrado.descifrarMD5(contrasenia));
            if(usuarioRecibido.getTipoUsuario() == TipoUsuario.INACTIVO)return null;
            
        }
        
        
        //RETORNAMOS SU CONTRASENIA SIN CIFRAR
           
        return usuarioRecibido;
    }
    
    //YA TIENE CIFRADO***********************************************
    public Integer registrarMiembro(String nombre,String apellidos,String correo,String contrasenia,
                                        String contraseniaconfirmacion,String nombreusuario,String strIdUniversidad){
        Usuario usuarioPorRegistrar = new Usuario();
        Usuario usuarioVerificacion = new Usuario();
        
        Integer idUniveridad = Integer.parseInt(strIdUniversidad); 
        usuarioPorRegistrar.setApellidos(apellidos);
        
        String contraseniaSinCifrar = contrasenia;
        String contraseniaConfirmacionSinCifrar = contraseniaconfirmacion;
        //ANTES DE CIFRAR CONTRASENIA VALIDAMOS QUE SEA IGUAL A SU CONFIRMACION Y QUE SEA MAYOR A 6 CARACTERES
        Boolean contraseniavalida = false;
        if(contrasenia.length()>6 && contrasenia.equals(contraseniaconfirmacion))contraseniavalida=true;
        
        //CIFRO LA CONTRASENIA PARA QUE SE SUBA CIFRADA
        contrasenia = Cifrado.cifrarMD5(contrasenia);
        contraseniaconfirmacion = Cifrado.cifrarMD5(contraseniaconfirmacion);
        
        usuarioPorRegistrar.setContrasenia(contrasenia);
        usuarioPorRegistrar.setCorreoInstitucional(correo);
        usuarioPorRegistrar.setFechaRegistro(new Date());//INSERTA CON LA FECHA EN LA QUE SE ACCIONA EL CLICK
        usuarioPorRegistrar.setNombres(nombre);
        usuarioPorRegistrar.setTipoUsuario(TipoUsuario.MIEMBRO);
        usuarioPorRegistrar.setNombreUsuario(nombreusuario);
        
        //DEMAS VALIDACIONES
        //VERIFICAR SI YA SE ENCUENTRA BUSCAR POR CORREO Y NOMBRE DE USUARIO
        Usuario usuarioVerifCorreo = this.iniciarSesion(usuarioPorRegistrar.getCorreoInstitucional(),usuarioPorRegistrar.getContrasenia());
        Usuario usuarioVerifNombreUsuario = this.iniciarSesion(usuarioPorRegistrar.getNombreUsuario(),usuarioPorRegistrar.getContrasenia());
        
        //VERIFICAR QUE LOS DATOS POR INGRESAR SEAN UNICOS
        Usuario usuarioVerifNombreUsuarioUnico = this.obtenerPorAtributosUnicos(null, null, usuarioPorRegistrar.getNombreUsuario());
        Usuario usuarioVerifCorreoUnico = this.obtenerPorAtributosUnicos(usuarioPorRegistrar.getCorreoInstitucional(), null, null);
        
        
        if(usuarioVerifCorreo == null && usuarioVerifNombreUsuario==null &&
                contraseniavalida && usuarioVerifNombreUsuarioUnico==null && usuarioVerifCorreoUnico==null){
            //INSERTA
            Integer idUsuarioRegistrado = usuarioDAO.insertar(usuarioPorRegistrar);
            if(idUsuarioRegistrado != -1){
                usuarioPorRegistrar.setIdUsuario(idUsuarioRegistrado);
                Universidad universidad = new Universidad();
                universidad.setIdUniversidad(idUniveridad);              
                usuario_universidadDAO.insertar(usuarioPorRegistrar,universidad);
            }
            return idUsuarioRegistrado;
        }else{
            if(usuarioVerifCorreo != null || usuarioVerifNombreUsuario != null){
                return -1;//YA EXISTE USUARIO
            }else if(contraseniaSinCifrar.equals(contraseniaConfirmacionSinCifrar)==false){
                return -2;//CONTRASEÑAS DISTINTAS
            }else if(contraseniaSinCifrar.length()<=6){
                return -3;//ERROR DE TAMAÑO DE CONTRASEÑA
            }else if(usuarioVerifNombreUsuarioUnico != null){
                return -4;//NOMBRE DE USUARIO YA EXISTE
            }else{
                return -5;
            }
        }
    }
    
    public Usuario consultarMiembroID(Integer idUsuario) {
        Usuario usuario = usuarioDAO.obtenerPorId(idUsuario);
        usuario.setContrasenia(Cifrado.cifrarMD5(usuario.getContrasenia()));
        return usuario;
    }
        
        //PORSIACA
    public Usuario obtenerPorAtributosUnicos(String correoInstitucional,String contrasenia,String nombre_usuario) {
        Usuario usuario = new Usuario();
        usuario.setCorreoInstitucional(correoInstitucional);
        usuario.setContrasenia(contrasenia);
        usuario.setNombreUsuario(nombre_usuario);
        return usuarioDAO.obtenerPorAtributosUnicos(usuario);
    }
    
    public ArrayList<Usuario> listarTodos(String nombres, String apellidos,String correoInstitucional,
                                          String nombre_usuario,String tipoUsuario, Integer limite) {
        TipoUsuario tu_tipoUsuario = null;
        if(tipoUsuario != null && !tipoUsuario.isBlank())
            tu_tipoUsuario = TipoUsuario.valueOf(tipoUsuario);
        Usuario usuario = new Usuario();
        usuario.setNombres(nombres);
        usuario.setApellidos(apellidos);
        usuario.setCorreoInstitucional(correoInstitucional);
        usuario.setNombreUsuario(nombre_usuario);
        usuario.setTipoUsuario(tu_tipoUsuario);
        return usuarioDAO.listarTodos(usuario, limite);
    }
    
    public ArrayList<Usuario> listarTodos_UsuariosDeUniversidadPorID(Integer idUniversidad) {
        Universidad universidad = new Universidad();
        universidad.setIdUniversidad(idUniversidad);
        return usuario_universidadDAO.listarTodos_UsuariosDeUniversidad(null,universidad,null);
    }
    
    public ArrayList<Usuario> listarTodos_UsuariosDeUniversidad(String nombres, String apellidos,
                                                                String correoInstitucional,String nombre_usuario,String fechaRegistro,
                                                                String tipoUsuario, Integer idUniversidad,Integer idPoliticaRegular,
                                                                Integer idConsorcioPerteneciente) {
        Date d_fechaRegistro = vkf.toValidDate(fechaRegistro);
        TipoUsuario tu_tipoUsuario = vkf.toValidEnum(tipoUsuario, TipoUsuario.class);
        Usuario usuario = new Usuario(null,nombres,apellidos,correoInstitucional,null,
                                      nombre_usuario,d_fechaRegistro,tu_tipoUsuario);
        Universidad universidad = new Universidad();
        universidad.setIdUniversidad(idUniversidad);
        Politica politicaRegular = new Politica();
        politicaRegular.setIdPolitica(idPoliticaRegular);
        universidad.setPoliticaRegular(politicaRegular);
        Consorcio consorcioPerteneciente = new Consorcio();
        consorcioPerteneciente.setIdConsorcio(idConsorcioPerteneciente);
        universidad.setConsorcioPerteneciente(consorcioPerteneciente);
        return usuario_universidadDAO.listarTodos_UsuariosDeUniversidad(usuario,universidad,null);
    }
    
    public Integer modificarUniversidadesAsociadasDeUsuario(Integer idUsuario,ArrayList<Integer> idUniversidadesActuales,ArrayList<Integer> idUniversidadesNuevas){
        try{
            Usuario usuTemp = new Usuario();
            usuTemp.setIdUsuario(idUsuario);
            for (int idUnivActual : idUniversidadesActuales)
            {
                Universidad univ = new Universidad();
                univ.setIdUniversidad(idUnivActual);
                usuario_universidadDAO.eliminar(usuTemp, univ);
            }
            for (int idUnivNueva : idUniversidadesNuevas)
            {
                Universidad univ = new Universidad();
                univ.setIdUniversidad(idUnivNueva);
                usuario_universidadDAO.insertar(usuTemp, univ);
            }

        }catch (Exception e){
            return -1;
        }
            return 0;
    }

}