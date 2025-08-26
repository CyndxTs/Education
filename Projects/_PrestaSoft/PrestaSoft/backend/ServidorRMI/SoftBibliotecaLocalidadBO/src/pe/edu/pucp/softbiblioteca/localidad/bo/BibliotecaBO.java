
/* [/]
 >> @Project:    SoftBibliotecaLocalidadBO
 >> @File:       BibliotecaBO.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.localidad.bo;
import pe.edu.pucp.softbiblioteca.localidad.dao.BibliotecaDAO;
import pe.edu.pucp.softbiblioteca.localidad.daoImpl.BibliotecaDAOImpl;
import pe.edu.pucp.softbiblioteca.localidad.model.Biblioteca;
import pe.edu.pucp.softbiblioteca.localidad.model.Universidad;
import java.util.ArrayList;

public class BibliotecaBO {
    private BibliotecaDAO bibliotecaDAO;
    
    public BibliotecaBO(){
        this.bibliotecaDAO = new BibliotecaDAOImpl();
    }
    
    public Integer insertar(String nombre,String ubicacion,Integer idUniversidadAsociada) {
        Universidad universidadAsociada = new Universidad();
        universidadAsociada.setIdUniversidad(idUniversidadAsociada);
        Biblioteca biblioteca = new Biblioteca(null,nombre,ubicacion,universidadAsociada);
        return this.bibliotecaDAO.insertar(biblioteca);
    }

    public Integer modificar(Integer idBiblioteca,String nombre,String ubicacion,
                             Integer idUniversidadAsociada) {
        Universidad universidadAsociada = new Universidad();
        universidadAsociada.setIdUniversidad(idUniversidadAsociada);
        Biblioteca biblioteca = new Biblioteca(idBiblioteca,nombre,ubicacion,universidadAsociada);
        biblioteca.setUniversidadAsociada(universidadAsociada);
        return this.bibliotecaDAO.modificar(biblioteca);
    }

    public Integer eliminar(Integer idBiblioteca) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setIdBiblioteca(idBiblioteca);
        return this.bibliotecaDAO.eliminar(biblioteca);
    }

    public ArrayList<Biblioteca> listarTodos(Integer idUniversidadAsociada, Integer limite) {
        Universidad universidadAsociada = new Universidad();
        universidadAsociada.setIdUniversidad(idUniversidadAsociada);
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setUniversidadAsociada(universidadAsociada);
        return this.bibliotecaDAO.listarTodos(biblioteca,limite,2); //LO USO EN ADMIN la profundidad 2
    }

    public Biblioteca obtenerPorId(Integer idBiblioteca) {
        return this.bibliotecaDAO.obtenerPorId(idBiblioteca);
    }
    
    
    public ArrayList<Biblioteca> mostrarBibliotecasDeUniversidadesDelProgramador(Integer idUsuario, ArrayList<Integer>idUniversidadesAsociadasDelBibliotecario){
        ArrayList<Biblioteca>bibliotecasDelProgramador = new ArrayList<Biblioteca>();
        for(int idUniv:idUniversidadesAsociadasDelBibliotecario ){
            Universidad univ= new Universidad();
            univ.setIdUniversidad(idUniv);
            Biblioteca biblioBusqueda = new Biblioteca();
            biblioBusqueda.setUniversidadAsociada(univ);
            ArrayList<Biblioteca>bibliotecasDeLaUniv = bibliotecaDAO.listarTodos(biblioBusqueda,null,2);
            bibliotecasDelProgramador.addAll(bibliotecasDeLaUniv);
        }
        return bibliotecasDelProgramador;
    }
}
