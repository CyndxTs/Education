
/* [/]
 >> @Project:    SoftBibliotecaPoliticasBO
 >> @File:       PoliticaPrestamoBO.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.politicas.bo;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.politicas.model.Mora;
import pe.edu.pucp.softbiblioteca.politicas.model.PoliticaPrestamo;
import pe.edu.pucp.sotbiblioteca.politicas.dao.PoliticaPrestamoDAO;
import pe.edu.pucp.sotbiblioteca.politicas.daoimpl.PoliticaPrestamoDAOImpl;

public class PoliticaPrestamoBO {
    private PoliticaPrestamoDAO politicaPrestamoDAO;
    
    public PoliticaPrestamoBO(){
        this.politicaPrestamoDAO = new PoliticaPrestamoDAOImpl();
    }
    
        //DE ACÁ VAN TODOS LOS CRUDS
    
    public Integer insertar(Integer idMoraAsociada,Integer cantDiasPrestamoRegular,
                            Integer cantMaxCopiasPorDevolverPorUsuario,Integer cantDiasDeAmpliacionRegular,Integer cantMaxAmpliacionesPermitidasPorPrestamo,
                            Integer cantDiasParaDarPorPerdidaUnaCopia,Integer cantDiasSinPrestamoPorAtraso,Integer cantMaxAtrasosPorCiclo,
                            Integer cantDiasSinPrestamoPorMalEstado,Float cargoPorMalEstado,Integer cantMaxMalEstadoPorCiclo,
                            Integer cantDiasSinPrestamoPorPerdido,Float cargoPorPerdido,Integer cantMaxPerdidasPorCiclo) {
        Mora moraAsociada = new Mora();
        moraAsociada.setIdMora(idMoraAsociada);
        PoliticaPrestamo politicaPrestamo = new PoliticaPrestamo(null,moraAsociada,cantDiasPrestamoRegular,cantMaxCopiasPorDevolverPorUsuario,
                                                                 cantDiasDeAmpliacionRegular,cantMaxAmpliacionesPermitidasPorPrestamo,cantDiasParaDarPorPerdidaUnaCopia,
                                                                 cantDiasSinPrestamoPorAtraso,cantMaxAtrasosPorCiclo,cantDiasSinPrestamoPorMalEstado,cargoPorMalEstado,
                                                                 cantMaxMalEstadoPorCiclo,cantDiasSinPrestamoPorPerdido,cargoPorPerdido,cantMaxPerdidasPorCiclo);
        return this.politicaPrestamoDAO.insertar(politicaPrestamo);
    }
    
    public Integer modificar(Integer idPoliticaPrestamo,Integer idMoraAsociada,Integer cantDiasPrestamoRegular,
                            Integer cantMaxCopiasPorDevolverPorUsuario,Integer cantDiasDeAmpliacionRegular,Integer cantMaxAmpliacionesPermitidasPorPrestamo,
                            Integer cantDiasParaDarPorPerdidaUnaCopia,Integer cantDiasSinPrestamoPorAtraso,Integer cantMaxAtrasosPorCiclo,
                            Integer cantDiasSinPrestamoPorMalEstado,Float cargoPorMalEstado,Integer cantMaxMalEstadoPorCiclo,
                            Integer cantDiasSinPrestamoPorPerdido,Float cargoPorPerdido,Integer cantMaxPerdidasPorCiclo) {
        Mora moraAsociada = new Mora();
        moraAsociada.setIdMora(idMoraAsociada);
        PoliticaPrestamo politicaPrestamo = new PoliticaPrestamo(idPoliticaPrestamo,moraAsociada,cantDiasPrestamoRegular,cantMaxCopiasPorDevolverPorUsuario,
                                                                 cantDiasDeAmpliacionRegular,cantMaxAmpliacionesPermitidasPorPrestamo,cantDiasParaDarPorPerdidaUnaCopia,
                                                                 cantDiasSinPrestamoPorAtraso,cantMaxAtrasosPorCiclo,cantDiasSinPrestamoPorMalEstado,cargoPorMalEstado,
                                                                 cantMaxMalEstadoPorCiclo,cantDiasSinPrestamoPorPerdido,cargoPorPerdido,cantMaxPerdidasPorCiclo);
        return this.politicaPrestamoDAO.modificar(politicaPrestamo);
    }

    public Integer eliminar(Integer idPoliticaPrestamo) {
        PoliticaPrestamo politicaPrestamo = new PoliticaPrestamo();
        politicaPrestamo.setIdPoliticaPrestamo(idPoliticaPrestamo);
        return this.politicaPrestamoDAO.eliminar(politicaPrestamo);
    }
    
    public ArrayList<PoliticaPrestamo> listarTodos(Integer idMoraAsociada, Integer limite) {
        Mora moraAsociada = new Mora();
        moraAsociada.setIdMora(idMoraAsociada);
        PoliticaPrestamo politicaPrestamo = new PoliticaPrestamo();
        politicaPrestamo.setMoraAsociada(moraAsociada);
        return this.politicaPrestamoDAO.listarTodos(politicaPrestamo,limite,2); //para obtener monto de la mora
    }

    public PoliticaPrestamo obtenerPorId(Integer idPoliticaPrestamo) {
        return this.politicaPrestamoDAO.obtenerPorId(idPoliticaPrestamo);
    }
}
