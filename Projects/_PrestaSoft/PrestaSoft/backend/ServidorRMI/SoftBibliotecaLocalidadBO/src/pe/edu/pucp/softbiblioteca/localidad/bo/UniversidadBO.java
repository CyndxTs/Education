
/* [/]
 >> @Project:    SoftBibliotecaLocalidadBO
 >> @File:       UniversidadBO.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.localidad.bo;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softbiblioteca.localidad.dao.UniversidadDAO;
import pe.edu.pucp.softbiblioteca.localidad.daoImpl.UniversidadDAOImpl;
import pe.edu.pucp.softbiblioteca.localidad.model.Consorcio;
import pe.edu.pucp.softbiblioteca.localidad.model.Universidad;
import pe.edu.pucp.softbiblioteca.politicas.model.Politica;
import pe.edu.pucp.softbiblioteca.usuarios.dao.Usuario_UniversidadDAO;
import pe.edu.pucp.softbiblioteca.usuarios.daoImpl.Usuario_UniversidadDAOImpl;
import pe.edu.pucp.softbiblioteca.usuarios.model.TipoUsuario;
import pe.edu.pucp.softbiblioteca.usuarios.model.Usuario;
import pe.edu.pucp.softbiblioteca.util.VKF_Formatter;

public class UniversidadBO {
    private UniversidadDAO universidadDAO;
    private Usuario_UniversidadDAO usuario_universidadDAO;
    private VKF_Formatter vkf;
    
    public UniversidadBO(){
        this.universidadDAO = new UniversidadDAOImpl();
        this.usuario_universidadDAO = new Usuario_UniversidadDAOImpl();
        this.vkf = new VKF_Formatter();
    }
    
    //TODO EN EL WS
    
    public Integer insertar(String nombre,String direccion,String correoPersonalAdministrativo,
                            String extensionDominioCorreo,Integer idPoliticaRegular,Integer idConsorcioPerteneciente) {
        Consorcio consorcioPerteneciente = new Consorcio();
        consorcioPerteneciente.setIdConsorcio(idConsorcioPerteneciente);
        Politica politicaRegular = new Politica();
        politicaRegular.setIdPolitica(idPoliticaRegular);
        Universidad universidad = new Universidad(null,nombre,direccion,correoPersonalAdministrativo,
                                                  extensionDominioCorreo,politicaRegular,consorcioPerteneciente);
        return this.universidadDAO.insertar(universidad);
    }
    
    public Integer modificar(Integer idUniversidad,String nombre,String direccion,
                             String correoPersonalAdministrativo,String extensionDominioCorreo,Integer idPoliticaRegular,
                             Integer idConsorcioPerteneciente) {
        Consorcio consorcioPerteneciente = new Consorcio();
        consorcioPerteneciente.setIdConsorcio(idConsorcioPerteneciente);
        Politica politicaRegular = new Politica();
        politicaRegular.setIdPolitica(idPoliticaRegular);
        Universidad universidad = new Universidad(idUniversidad,nombre,direccion,correoPersonalAdministrativo,
                                                  extensionDominioCorreo,politicaRegular,consorcioPerteneciente);
        return this.universidadDAO.modificar(universidad);
    }
    
    public Integer eliminar(int idUniversidad) {
        Universidad universidad = new Universidad();
        universidad.setIdUniversidad(idUniversidad);
        return this.universidadDAO.eliminar(universidad);
    }
    
    public ArrayList<Universidad> listarTodos(Integer idPoliticaRegular,Integer idConsorcioPerteneciente, Integer limite) {
        Politica politicaRegular = new Politica();
        politicaRegular.setIdPolitica(idPoliticaRegular);
        Consorcio consorcioPerteneciente = new Consorcio();
        consorcioPerteneciente.setIdConsorcio(idConsorcioPerteneciente);
        Universidad universidad = new Universidad();
        universidad.setPoliticaRegular(politicaRegular);
        universidad.setConsorcioPerteneciente(consorcioPerteneciente);
        return this.universidadDAO.listarTodos(universidad, limite, 2); //NECESITO MOSTRAR CIF DE CONSORCIO, MOSTRAR ETIQUETA POLITICA
    }
    
    public Universidad obtenerPorId(int idUniversidad) {
        return this.universidadDAO.obtenerPorId(idUniversidad);
    }
    
    
    public ArrayList<Universidad> listarTodos_UniversidadesDeUsuarioPorID(Integer idUsuario) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        return usuario_universidadDAO.listarTodos_UniversidadesDeUsuario(usuario,null,null,2);//NECESITO MOSTRAR CIF DE CONSORCIO, MOSTRAR ETIQUETA POLITICA
    }
    
    public ArrayList<Universidad> listarTodos_UniversidadesDeUsuario(Integer idUsuario,String nombres, String apellidos,
                                                                      String correoInstitucional,String nombre_usuario,String fechaRegistro,
                                                                      String tipoUsuario,Integer idPoliticaRegular,Integer idConsorcioPerteneciente) {
        Date d_fechaRegistro = vkf.toValidDate(fechaRegistro);
        TipoUsuario tu_tipoUsuario = vkf.toValidEnum(tipoUsuario, TipoUsuario.class);
        Usuario usuario = new Usuario(idUsuario,nombres,apellidos,correoInstitucional,null,
                                      nombre_usuario,d_fechaRegistro,tu_tipoUsuario);
        Universidad universidad = new Universidad();
        Politica politicaRegular = new Politica();
        politicaRegular.setIdPolitica(idPoliticaRegular);
        universidad.setPoliticaRegular(politicaRegular);
        Consorcio consorcioPerteneciente = new Consorcio();
        consorcioPerteneciente.setIdConsorcio(idConsorcioPerteneciente);
        universidad.setConsorcioPerteneciente(consorcioPerteneciente);
        return usuario_universidadDAO.listarTodos_UniversidadesDeUsuario(usuario,universidad,null,2);//NECESITO MOSTRAR CIF DE CONSORCIO, MOSTRAR ETIQUETA POLITICA
    }
    //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA 
    public Integer eliminarUniversidadesDeUsuario(Integer idUsuario){
        try{
            Usuario usuTarget = new Usuario();
            usuTarget.setIdUsuario(idUsuario);
            ArrayList<Universidad>universidadesDeUsuario = listarTodos_UniversidadesDeUsuarioPorID(idUsuario);
            int resultado;
            for (Universidad universidad : universidadesDeUsuario) {
                resultado = usuario_universidadDAO.eliminar(usuTarget, universidad);
                if(resultado < 0)
                    return resultado;
            }
            return 1;
        }catch(Exception e){
            return -1;
        }
    }
}
