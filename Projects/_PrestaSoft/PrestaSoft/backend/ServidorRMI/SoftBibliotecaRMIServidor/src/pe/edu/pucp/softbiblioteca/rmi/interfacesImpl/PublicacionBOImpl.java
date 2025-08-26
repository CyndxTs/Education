
package pe.edu.pucp.softbiblioteca.rmi.interfacesImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import pe.edu.pucp.softbiblioteca.publicaciones.model.CopiaEjemplar;
import pe.edu.pucp.softbiblioteca.publicaciones.model.Publicacion;
import pe.edu.pucp.softbiblioteca.rmi.interfaces.PublicacionBO;

public class PublicacionBOImpl extends UnicastRemoteObject implements PublicacionBO{

    private pe.edu.pucp.softbiblioteca.publicaciones.bo.PublicacionBO publicacionBO;
    
    public PublicacionBOImpl(Integer puerto) throws RemoteException{
        super(puerto);
        this.publicacionBO = new pe.edu.pucp.softbiblioteca.publicaciones.bo.PublicacionBO();
    }
    
    @Override
    public ArrayList<Publicacion> verCatalogo(Integer modoDeBusqueda, Integer idBibliotecaAsociada, String tipo, String titulo, Integer limite) throws RemoteException {
        return this.publicacionBO.verCatalogo(modoDeBusqueda, idBibliotecaAsociada, tipo, titulo, limite);
    }

    

    @Override
    public ArrayList<Publicacion> publicacionesPorAutor(Integer id) throws RemoteException {
        return this.publicacionBO.publicacionesPorAutor(id);
    }

    @Override
    public CopiaEjemplar consultarCopiaEjemplar(Integer id) throws RemoteException {
        return this.publicacionBO.consultarCopiaEjemplar(id);
    }

    @Override
    public ArrayList<Publicacion> busquedaAvanzada(Integer modoDeBusqueda, String titulo, String descripcion, Integer hayFormatoFisico, Integer hayFormatoVirtual, Integer idBibliotecaAsociada, Integer idAutor) throws RemoteException {
        return this.publicacionBO.busquedaAvanzada(modoDeBusqueda, titulo, descripcion, hayFormatoFisico, hayFormatoVirtual, idBibliotecaAsociada, idAutor);
    }
    
}
