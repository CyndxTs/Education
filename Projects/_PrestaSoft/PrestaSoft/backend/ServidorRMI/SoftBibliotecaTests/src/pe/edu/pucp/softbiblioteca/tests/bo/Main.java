
/* [/]
 >> @Project:    SoftBibliotecaTestBO
 >> @File:       Main.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.tests.bo;
import java.text.ParseException;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.usuarios.bo.UsuarioBO;
import pe.edu.pucp.softbiblioteca.usuarios.dao.UsuarioDAO;
import pe.edu.pucp.softbiblioteca.usuarios.daoImpl.UsuarioDAOImpl;
import pe.edu.pucp.softbiblioteca.usuarios.model.Usuario;
import pe.edu.pucp.softbiblioteca.util.Cifrado;

public class Main {
    
    public static void main(String[] args) throws ParseException {
        Tester_BO tester = new Tester_BO();
//        tester.testBusquedaAvanzada();
        
//        Integer modoDeBusqueda = 0, hayFormatoFisico = -1,hayFormatoVirtual = 0,
//                idBibliotecaAsociada = null,idEditorial = null,volumen = null,tomo = null;
//        String titulo = null, fechaPublicacion = "12",descripcion = null,
//                tipoLibro = null ,materia = null, genero = null,gradoAcademico = null;
//        tester.testearListadoSimplePorParametros_Publicacion(modoDeBusqueda, titulo, fechaPublicacion,
//                                                             descripcion, hayFormatoFisico, hayFormatoVirtual,
//                                                             idBibliotecaAsociada, idEditorial, tipoLibro,
//                                                             volumen, materia, genero, tomo, gradoAcademico);

//        //
//        tester.testearListadoPorIntermediarios_UsuariosDeUniversidad();
//        tester.testearListadoPorIntermediarios_UniversidadesDeUsuario();
//        //
//        tester.testearListadoPorIntermediarios_PublicacionesDeAutor();
//        tester.testearListadoPorIntermediarios_AutoresDePublicacion();
//        //
//        tester.testearListadoPorIntermediarios_TesisesDeTema();
//        tester.testearListadoPorIntermediarios_TemasDeTesis();

        //
//        tester.testearExtraccionCompleta_Penalizacion();

        //PRUEBAS DE LOS BO
//        tester.testPublicacionesBO();
   //    tester.testPrestamoYReservaBO();
//        tester.testLibroYTesisBO();
        tester.testUsuariosBO();
//        tester.testRegistroPrestamos();
        
//        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
//        UsuarioBO usuarioBO = new UsuarioBO();
//        ArrayList<Usuario> usuarios = usuarioBO.listarTodos(null, null, null, null, null, null);
//        for(Usuario us : usuarios){
//            String contrasenia = us.getContrasenia();
//            us.setContrasenia(Cifrado.cifrarMD5(contrasenia));
//            usuarioDAO.modificar(us);
//        }
          //  tester.testUniversidades();
        
    }
}
