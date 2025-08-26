
package pe.edu.pucp.softbiblioteca.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.prestamos.model.Penalizacion;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.PenalizacionBO;

public class PenalizacionBOImpl extends UnicastRemoteObject implements PenalizacionBO{

    private pe.edu.pucp.softbiblioteca.prestamos.bo.PenalizacionBO penalizacionBO;
    
    public PenalizacionBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.penalizacionBO = new pe.edu.pucp.softbiblioteca.prestamos.bo.PenalizacionBO();
    }
    
    @Override
    public Integer insertar(String descripcion, Float monto, Integer idPrestamoAsociado, String fechaImposicion, Integer estaActivo) throws RemoteException {
        return this.penalizacionBO.insertar(descripcion, monto, idPrestamoAsociado, fechaImposicion, estaActivo);
    }

    @Override
    public Integer modificar(Integer idPenalizacion, String descripcion, Float monto, Integer idPrestamoAsociado, String fechaImposicion, Integer estaActivo) throws RemoteException {
        return this.penalizacionBO.modificar(idPenalizacion, descripcion, monto, idPrestamoAsociado, fechaImposicion, estaActivo);
    }

    @Override
    public Integer eliminar(Integer idPenalizacion) throws RemoteException {
        return this.penalizacionBO.eliminar(idPenalizacion);
    }

    @Override
    public ArrayList<Penalizacion> listarTodos(Integer idPrestamoAsociado, String fechaImposicion, Integer estaActivo, Integer limite) throws RemoteException {
        return this.penalizacionBO.listarTodos(idPrestamoAsociado, fechaImposicion, estaActivo, limite);
    }

    @Override
    public Penalizacion obtenerPorId(Integer idPenalizacion) throws RemoteException {
        return this.penalizacionBO.obtenerPorId(idPenalizacion);
    }

    @Override
    public ArrayList<Penalizacion> verPenalizacionesPrestamo(Integer idPrestamoAsociado) throws RemoteException {
        return this.penalizacionBO.verPenalizacionesPrestamo(idPrestamoAsociado);
    }
    
}
