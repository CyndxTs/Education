
/* [/]
 >> Project:    SoftBibliotecaLocalidadModel
 >> File:       Universidad.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.localidad.model;
import java.io.Serializable;
import pe.edu.pucp.softbiblioteca.politicas.model.Politica;

public class Universidad implements Serializable{
    private Integer idUniversidad;
    private String nombre;
    private String direccion;
    private String correoPersonalAdministrativo;
    private String extensionDominioCorreo;
    private Politica politicaRegular;
    private Consorcio consorcioPerteneciente;
    
    public Universidad() {
        this.idUniversidad = null;
        this.nombre = null;
        this.direccion = null;
        this.correoPersonalAdministrativo = null;
        this.extensionDominioCorreo = null;
        this.politicaRegular = null;
        this.consorcioPerteneciente = null;
    }
    
    public Universidad(Integer idUniversidad,String nombre,String direccion,
                       String correoPersonalAdministrativo,String extensionDominioCorreo,Politica politicaRegular,
                       Consorcio consorcioPerteneciente) {
        this.idUniversidad = idUniversidad;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correoPersonalAdministrativo = correoPersonalAdministrativo;
        this.extensionDominioCorreo = extensionDominioCorreo;
        this.politicaRegular = politicaRegular;
        this.consorcioPerteneciente = consorcioPerteneciente;
    }
    
    public String consultarDatos() {
        String str = getNombre() + " [" + getDireccion() + "]";
        return str;
    }

    public Integer getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(Integer idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoPersonalAdministrativo() {
        return correoPersonalAdministrativo;
    }

    public void setCorreoPersonalAdministrativo(String correoPersonalAdministrativo) {
        this.correoPersonalAdministrativo = correoPersonalAdministrativo;
    }

    public String getExtensionDominioCorreo() {
        return extensionDominioCorreo;
    }

    public void setExtensionDominioCorreo(String extensionDominioCorreo) {
        this.extensionDominioCorreo = extensionDominioCorreo;
    }

    public Politica getPoliticaRegular() {
        return politicaRegular;
    }

    public void setPoliticaRegular(Politica politicaRegular) {
        this.politicaRegular = politicaRegular;
    }

    public Consorcio getConsorcioPerteneciente() {
        return consorcioPerteneciente;
    }

    public void setConsorcioPerteneciente(Consorcio consorcioPerteneciente) {
        this.consorcioPerteneciente = consorcioPerteneciente;
    }
}
