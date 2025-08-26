
/*/
 >> @Alumno: Cesar Augusto Napuri de la Cruz
 >> @Codigo: 20211692
/*/

import java.util.Scanner;
import java.util.ArrayList;

public class Libreria {
	private String nombre;
	private String direccion;
	private ArrayList<Libro> libros;
	private ArrayList<Cliente> clientes;
	private int cantidadVendida;
	private double totalVendido;
	
	public Libreria() {
		libros = new ArrayList<Libro>();
		clientes = new ArrayList<Cliente>();
		cantidadVendida = 0;
		totalVendido = 0.0;
	}
	
	public void SetNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String GetNombre() {
		return nombre;
	}
	
	public void SetDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String GetDireccion() {
		return direccion;
	}
	
	public void SetLibros(ArrayList<Libro> libros) {
		this.libros = libros;
	}
	
	public ArrayList<Libro> GetLibros() {
		return libros;
	}
	
	public void SetClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public ArrayList<Cliente> GetClientes() {
		return clientes;
	}
	
	public void SetCantidadVendida(int cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}
	
	public int GetCantidadVendida() {
		return cantidadVendida;
	}
	
	public void SetTotalVendido(double totalVendido) {
		this.totalVendido = totalVendido;
	}
	
	public double GetTotalVendido() {
		return totalVendido;
	}
	
	public void leerDatos(Scanner arch) {
		nombre = arch.next();
		direccion = arch.next();
		leer_Libros(arch);
		leer_Clientes(arch);
		leer_Ventas(arch);
	}
	
	public void leer_Libros(Scanner arch) {
		while(true){
			Libro libAux = new Libro();
			if(!libAux.leer(arch)) break;
			libros.add(libAux);
		}
	}
	
	public void leer_Clientes(Scanner arch) {
		while(true){
			Cliente cliAux = new Cliente();
			if(!cliAux.leer(arch)) break;
			clientes.add(cliAux);
		}
	}
	
	public void leer_Ventas(Scanner arch) {
		while(arch.hasNext()){
			String cadAux = arch.next();
			Venta ventAux = new Venta();
			ventAux.leer(arch);
			for(Libro libAux : libros){
				if(libAux.GetCodigo().compareTo(cadAux) == 0){
					libAux.agregarVenta(ventAux); // Aqui se actualizan los campos correspondientes por la venta de un libro
					for(Cliente cliAux : clientes)
						if(cliAux.GetDni() == ventAux.GetDni())
							cliAux.agregarVenta(libAux); // Aqui simplemente se actualiza el total gastado por el cliente
				}
			}
		}
	}
	
	public void actualizarRanking() {
		for(Libro libAux : libros) libAux.actualizarRanking();
	}
	
	public void imprimirDatos() {
		int i = 0;
		System.out.println(nombre);
		System.out.println(direccion);
		System.out.println("RESULTADO DE LAS VENTAS REALIZADAS:");
		System.out.println("");
		for(Libro libAux : libros) {
			System.out.printf("LIBRO NO.%3d",i+1);
			System.out.println("");
			libAux.imprimir(clientes);
			System.out.println("");
			i++;
		}
	}
}
