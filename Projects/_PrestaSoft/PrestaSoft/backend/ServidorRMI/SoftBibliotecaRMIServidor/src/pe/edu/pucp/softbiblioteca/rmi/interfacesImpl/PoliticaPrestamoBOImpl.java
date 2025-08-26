
package pe.edu.pucp.softbiblioteca.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.politicas.model.PoliticaPrestamo;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.PoliticaPrestamoBO;

public class PoliticaPrestamoBOImpl extends UnicastRemoteObject implements PoliticaPrestamoBO{
    
    private pe.edu.pucp.softbiblioteca.politicas.bo.PoliticaPrestamoBO politicaprestamoBO;
    
    public PoliticaPrestamoBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.politicaprestamoBO = new pe.edu.pucp.softbiblioteca.politicas.bo.PoliticaPrestamoBO();
    }
    
    @Override
    public Integer insertar(Integer idMoraAsociada, Integer cantDiasPrestamoRegular, Integer cantMaxCopiasPorDevolverPorUsuario, Integer cantDiasDeAmpliacionRegular, Integer cantMaxAmpliacionesPermitidasPorPrestamo, Integer cantDiasParaDarPorPerdidaUnaCopia, Integer cantDiasSinPrestamoPorAtraso, Integer cantMaxAtrasosPorCiclo, Integer cantDiasSinPrestamoPorMalEstado, Float cargoPorMalEstado, Integer cantMaxMalEstadoPorCiclo, Integer cantDiasSinPrestamoPorPerdido, Float cargoPorPerdido, Integer cantMaxPerdidasPorCiclo) throws RemoteException {
        return this.politicaprestamoBO.insertar(idMoraAsociada, cantDiasPrestamoRegular, cantMaxCopiasPorDevolverPorUsuario, cantDiasDeAmpliacionRegular, cantMaxAmpliacionesPermitidasPorPrestamo, cantDiasParaDarPorPerdidaUnaCopia, cantDiasSinPrestamoPorAtraso, cantMaxAtrasosPorCiclo, cantDiasSinPrestamoPorMalEstado, cargoPorMalEstado, cantMaxMalEstadoPorCiclo, cantDiasSinPrestamoPorPerdido, cargoPorPerdido, cantMaxPerdidasPorCiclo);
    }

    @Override
    public Integer modificar(Integer idPoliticaPrestamo, Integer idMoraAsociada, Integer cantDiasPrestamoRegular, Integer cantMaxCopiasPorDevolverPorUsuario, Integer cantDiasDeAmpliacionRegular, Integer cantMaxAmpliacionesPermitidasPorPrestamo, Integer cantDiasParaDarPorPerdidaUnaCopia, Integer cantDiasSinPrestamoPorAtraso, Integer cantMaxAtrasosPorCiclo, Integer cantDiasSinPrestamoPorMalEstado, Float cargoPorMalEstado, Integer cantMaxMalEstadoPorCiclo, Integer cantDiasSinPrestamoPorPerdido, Float cargoPorPerdido, Integer cantMaxPerdidasPorCiclo) throws RemoteException {
        return this.politicaprestamoBO.modificar(idPoliticaPrestamo, idMoraAsociada, cantDiasPrestamoRegular, cantMaxCopiasPorDevolverPorUsuario, cantDiasDeAmpliacionRegular, cantMaxAmpliacionesPermitidasPorPrestamo, cantDiasParaDarPorPerdidaUnaCopia, cantDiasSinPrestamoPorAtraso, cantMaxAtrasosPorCiclo, cantDiasSinPrestamoPorMalEstado, cargoPorMalEstado, cantMaxMalEstadoPorCiclo, cantDiasSinPrestamoPorPerdido, cargoPorPerdido, cantMaxPerdidasPorCiclo);
    }

    @Override
    public Integer eliminar(Integer idPoliticaPrestamo) throws RemoteException {
        return this.politicaprestamoBO.eliminar(idPoliticaPrestamo);
    }

    @Override
    public ArrayList<PoliticaPrestamo> listarTodos(Integer idMoraAsociada, Integer limite) throws RemoteException {
        return this.politicaprestamoBO.listarTodos(idMoraAsociada, limite);
    }

    @Override
    public PoliticaPrestamo obtenerPorId(Integer idPoliticaPrestamo) throws RemoteException {
        return this.politicaprestamoBO.obtenerPorId(idPoliticaPrestamo);
    }
    
}
