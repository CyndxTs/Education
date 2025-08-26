package pe.edu.pucp.softbiblioteca.rmi.interfaces;

import pe.edu.pucp.softbiblioteca.politicas.model.PoliticaPrestamo;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PoliticaPrestamoBO extends Remote{
    
    public Integer insertar(Integer idMoraAsociada,Integer cantDiasPrestamoRegular,
                            Integer cantMaxCopiasPorDevolverPorUsuario,Integer cantDiasDeAmpliacionRegular,Integer cantMaxAmpliacionesPermitidasPorPrestamo,
                            Integer cantDiasParaDarPorPerdidaUnaCopia,Integer cantDiasSinPrestamoPorAtraso,Integer cantMaxAtrasosPorCiclo,
                            Integer cantDiasSinPrestamoPorMalEstado,Float cargoPorMalEstado,Integer cantMaxMalEstadoPorCiclo,
                            Integer cantDiasSinPrestamoPorPerdido,Float cargoPorPerdido,Integer cantMaxPerdidasPorCiclo) throws RemoteException;
    
    public Integer modificar(Integer idPoliticaPrestamo,Integer idMoraAsociada,Integer cantDiasPrestamoRegular,
                            Integer cantMaxCopiasPorDevolverPorUsuario,Integer cantDiasDeAmpliacionRegular,Integer cantMaxAmpliacionesPermitidasPorPrestamo,
                            Integer cantDiasParaDarPorPerdidaUnaCopia,Integer cantDiasSinPrestamoPorAtraso,Integer cantMaxAtrasosPorCiclo,
                            Integer cantDiasSinPrestamoPorMalEstado,Float cargoPorMalEstado,Integer cantMaxMalEstadoPorCiclo,
                            Integer cantDiasSinPrestamoPorPerdido,Float cargoPorPerdido,Integer cantMaxPerdidasPorCiclo) throws RemoteException;

    public Integer eliminar(Integer idPoliticaPrestamo) throws RemoteException;
    
    public ArrayList<PoliticaPrestamo> listarTodos(Integer idMoraAsociada, Integer limite) throws RemoteException;

    public PoliticaPrestamo obtenerPorId(Integer idPoliticaPrestamo) throws RemoteException;
    
}
