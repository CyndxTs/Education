
/* [/]
 >> Project:    SoftBiblioecaPublicacionesModel
 >> File:       Libro.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.publicaciones.model;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import pe.edu.pucp.softbiblioteca.localidad.model.Biblioteca;

public class Libro extends Publicacion implements Serializable{
    private Integer idLibro;
    private Integer ISBN;
    private Editorial editorial;
    private TipoLibro tipo;
    private Integer volumen;
    private String materia;
    private String genero;
    private Integer tomo;

    public Libro(Integer idLibro,String titulo, Date fechaPublicacion, String descripcion,
                 Integer copiasDisponibles, Boolean hayFormatoFisico,Boolean hayFormatoVirtual,
                 String url, byte[] portada,Biblioteca bibliotecaAsociada,Integer ISBN,
                 Editorial editorial, TipoLibro tipo,Integer volumen, String materia,
                 String genero, Integer tomo) {
        super(titulo, fechaPublicacion, descripcion, copiasDisponibles,
              hayFormatoFisico,hayFormatoVirtual,url,portada,bibliotecaAsociada);
        this.idLibro = idLibro;
        this.ISBN = ISBN;
        this.editorial = editorial;
        this.tipo = tipo;
        this.volumen = volumen;
        this.materia = materia;
        this.genero = genero;
        this.tomo = tomo;
    }

    public Libro(){
        super();
        this.idLibro = null;
        this.ISBN = null;
        this.editorial = null;
        this.tipo = null;
        this.volumen = null;
        this.materia = null;
        this.genero = null;
        this.tomo = null;
    }
    
    @Override
    public String consultarDatos(){
        String str = "{" + new SimpleDateFormat("dd/MM/yyyy").format(super.getFechaPublicacion());
        str += "}[LIBRO - " + this.tipo+ "] " + super.getTitulo();
        return str;
    }
    
    @Override
    public void setIdPublicacion(Integer idLibro) {
        this.setIdLibro(idLibro);
    }
    
    @Override
    public Integer getIdPublicacion() {
        return this.getIdLibro();
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public Integer getISBN() {
        return ISBN;
    }

    public void setISBN(Integer ISBN) {
        this.ISBN = ISBN;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public TipoLibro getTipo() {
        return tipo;
    }

    public void setTipo(TipoLibro tipo) {
        this.tipo = tipo;
    }

    public Integer getVolumen() {
        return volumen;
    }

    public void setVolumen(Integer volumen) {
        this.volumen = volumen;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getTomo() {
        return tomo;
    }

    public void setTomo(Integer tomo) {
        this.tomo = tomo;
    }
}
