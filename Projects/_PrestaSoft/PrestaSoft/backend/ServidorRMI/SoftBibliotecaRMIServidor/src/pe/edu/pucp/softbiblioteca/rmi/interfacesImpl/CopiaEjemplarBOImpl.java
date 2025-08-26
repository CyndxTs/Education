
package pe.edu.pucp.softbiblioteca.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.CopiaEjemplar;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.CopiaEjemplarBO;

public class CopiaEjemplarBOImpl extends UnicastRemoteObject implements CopiaEjemplarBO{

    private pe.edu.pucp.softbiblioteca.publicaciones.bo.CopiaEjemplarBO copiaejemplarBO;
    
    public CopiaEjemplarBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.copiaejemplarBO = new pe.edu.pucp.softbiblioteca.publicaciones.bo.CopiaEjemplarBO();
    }
    
    @Override
    public ArrayList<CopiaEjemplar> ObtenerCopiasEjemplaresDeLibro(Integer idLibro) throws RemoteException {
        return this.copiaejemplarBO.ObtenerCopiasEjemplaresDeLibro(idLibro);
    }

    @Override
    public ArrayList<CopiaEjemplar> ObtenerCopiasEjemplaresDeTesis(Integer idTesis) throws RemoteException {
        return this.copiaejemplarBO.ObtenerCopiasEjemplaresDeTesis(idTesis);
    }

    @Override
    public CopiaEjemplar obtenerPorId(Integer idCopiaEjemplar) throws RemoteException {
        return this.copiaejemplarBO.obtenerPorId(idCopiaEjemplar);
    }
    
}
