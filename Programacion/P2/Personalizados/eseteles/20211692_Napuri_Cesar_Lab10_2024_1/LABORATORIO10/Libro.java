
/*/
 >> @Alumno: Cesar Augusto Napuri de la Cruz
 >> @Codigo: 20211692
/*/

import java.util.Scanner;
import java.util.ArrayList;

public class Libro {
	private String codigo;
	private String titulo;
	private String autor;
	private double precio;
	private ArrayList<Venta> ventas;
	private int suma;
	private int ranking;
	private double totalVendido;
	
	public Libro() {
		ventas = new ArrayList<Venta>();
		suma = 0;
		ranking = 0;
		totalVendido = 0.0;
	}
	
	public void SetCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String GetCodigo() {
		return codigo;
	}
	
	public void SetTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String GetTitulo() {
		return titulo;
	}
	
	public void SetAutor(String autor) {
		this.autor = autor;
	}
	
	public String GetAutor() {
		return autor;
	}
	
	public void SetPrecio(double precio) {
		this.precio = precio;
	}
	
	public double GetPrecio() {
		return precio;
	}
	
	public void SetVentas(ArrayList<Venta> ventas) {
		this.ventas = ventas;
	}
	
	public ArrayList<Venta> GetVentas() {
		return ventas;
	}
	
	public void SetSuma(int suma) {
		this.suma = suma;
	}
	
	public int GetSuma() {
		return suma;
	}
	
	public void SetRanking(int ranking) {
		this.ranking = ranking;
	}
	
	public int GetRanking() {
		return ranking;
	}
	
	public void SetTotalVendido(double totalVendido) {
		this.totalVendido = totalVendido;
	}
	
	public double GetTotalVendido() {
		return totalVendido;
	}
	
	public boolean leer(Scanner arch) {
		codigo = arch.next();
		if(codigo.compareTo("FIN") == 0) return false;
		titulo = arch.next();
		autor = arch.next();
		precio = arch.nextDouble();
		return true;
	}
	
	public void agregarVenta(Venta venta) {
		ventas.add(venta);
		suma += venta.GetCalificacion();
		totalVendido++;
	}
	
	public void actualizarRanking() {
		double promedio = suma/totalVendido;
		if(promedio > 0) {
			if(promedio < 25.0) ranking = 1;
			else if(promedio < 50.0) ranking = 2;
			else if(promedio < 75.0) ranking = 3;
			else ranking = 4;
		}
	}
	
	public void imprimir(ArrayList<Cliente> clientes) {
		int i = 0;
		System.out.printf("CODIGO: %-11s    TITULO: %-70s",codigo,titulo);
		System.out.printf("    AUTOR: %-40s    PRECIO: %5.2f",autor,precio);
		System.out.println("");
		System.out.printf("LIBROS VENDIDOS: %2d    RANKING: %d",(int)totalVendido,ranking);
		System.out.println("");
		System.out.println("");
		System.out.println("VENTAS REALIZADAS:");
		if(totalVendido > 0){
			for(Venta ventAux :ventas){
				for(Cliente cliAux : clientes){
					if(cliAux.GetDni() == ventAux.GetDni()) {
						System.out.printf("No. %2d    ",i+1);
						ventAux.imprimir(cliAux);
						i++;
					}
				}
			}
		} else System.out.println("                  > NO SE REALIZARON VENTAS <");
	}
}