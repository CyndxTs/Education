
/* [/]
 >> Project:    SoftBiblioecaPublicacionesModel
 >> File:       Tesis.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.model;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import pe.edu.pucp.softbiblioteca.localidad.model.Biblioteca;

public class Tesis extends Publicacion implements Serializable{
    private Integer idTesis;
    private TipoGrado gradoAcademico;
    
    public Tesis(Integer idTesis, String titulo, Date fechaPublicacion, String descripcion,
                 Integer copiasDisponibles, Boolean hayFormatoFisico, Boolean hayFormatoVirtual,
                 String url, byte[] portada, Biblioteca bibliotecaAsociada, TipoGrado gradoAcademico) {
        super(titulo, fechaPublicacion, descripcion, copiasDisponibles, hayFormatoFisico,
              hayFormatoVirtual, url, portada, bibliotecaAsociada);
        this.idTesis = idTesis;
        this.gradoAcademico = gradoAcademico;
    }
    
    public Tesis() {
        super();
        this.idTesis = null;
        this.gradoAcademico = null;
    }
    
    @Override
    public String consultarDatos(){
        String str = "{" + new SimpleDateFormat("dd/MM/yyyy").format(super.getFechaPublicacion());
        str += "}[TESIS - " + this.getGradoAcademico() + "] " + super.getTitulo();
        return str;
    }
    
    @Override
    public Integer getIdPublicacion() {
        return this.getIdTesis();
    }
    
    @Override
    public void setIdPublicacion(Integer idPublicacion) {
        this.setIdTesis(idPublicacion);
    }

    public Integer getIdTesis() {
        return idTesis;
    }

    public void setIdTesis(Integer idTesis) {
        this.idTesis = idTesis;
    }

    public TipoGrado getGradoAcademico() {
        return gradoAcademico;
    }

    public void setGradoAcademico(TipoGrado gradoAcademico) {
        this.gradoAcademico = gradoAcademico;
    }
}
