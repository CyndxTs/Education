
/* [/]
 >> Project:    SoftBibliotecaUsuariosModel
 >> File:       Usuario.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.usuarios.model;
import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable{
    private Integer idUsuario;
    private String nombres;
    private String apellidos;
    private String correoInstitucional;
    private String contrasenia;
    private String nombreUsuario;
    private Date fechaRegistro;
    private TipoUsuario tipoUsuario;    
    
    public Usuario() {
        this.idUsuario = null;
        this.nombres = null;
        this.apellidos = null;
        this.correoInstitucional = null;
        this.contrasenia = null;
        this.nombreUsuario = null;
        this.fechaRegistro = null;
        this.tipoUsuario = null;
    }
    
    public Usuario(Integer idUsuario,String nombres,String apellidos,
                   String correoInstitucional,String contrasenia,
                   String nombreUsuario,Date fechaRegistro,
                   TipoUsuario tipoUsuario) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoInstitucional = correoInstitucional;
        this.contrasenia = contrasenia;
        this.nombreUsuario = nombreUsuario;
        this.fechaRegistro = fechaRegistro;
        this.tipoUsuario = tipoUsuario;
    }

    public String consultarDatos() {
        String str = "[" + tipoUsuario.toString() + "] ";
        str += this.getApellidos() + ", " + this.getNombres();
        str += " <" + this.getNombreUsuario() + ">";
        return str;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
