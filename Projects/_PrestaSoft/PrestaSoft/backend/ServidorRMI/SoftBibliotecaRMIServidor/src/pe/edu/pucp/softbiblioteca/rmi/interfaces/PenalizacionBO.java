package pe.edu.pucp.softbiblioteca.rmi.interfaces;

import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.prestamos.model.Penalizacion;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PenalizacionBO extends Remote{
    
    public Integer insertar(String descripcion,Float monto,Integer idPrestamoAsociado,String fechaImposicion, Integer estaActivo) throws RemoteException;
    
    public Integer modificar(Integer idPenalizacion,String descripcion,Float monto,Integer idPrestamoAsociado, String fechaImposicion, Integer estaActivo) throws RemoteException;
    
    public Integer eliminar(Integer idPenalizacion) throws RemoteException;
    
    public ArrayList<Penalizacion> listarTodos(Integer idPrestamoAsociado,String fechaImposicion, Integer estaActivo, Integer limite) throws RemoteException;
    
    public Penalizacion obtenerPorId(Integer idPenalizacion) throws RemoteException;
    
    public ArrayList<Penalizacion> verPenalizacionesPrestamo(Integer idPrestamoAsociado) throws RemoteException;
    
}
