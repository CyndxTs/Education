/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.softbiblioteca.rmi.interfaces;

import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Editorial;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EditorialBO extends Remote{
    
    public Integer insertar(String nombre) throws RemoteException;
    
    public Integer modificar(Integer idEditorial, String nombre) throws RemoteException;
    
    public Integer eliminar(Integer idEditorial) throws RemoteException;
   
    public ArrayList<Editorial> listarTodos(String nombre, Integer limite) throws RemoteException;
    
    public Editorial obtenerPorId(Integer idEditorial) throws RemoteException;
    
    public ArrayList<Editorial> buscarEditorialPorNombre(String nombre) throws RemoteException;
    
}
