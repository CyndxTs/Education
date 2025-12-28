
/*/
 >> @Alumno: Cesar Augusto Napuri de la Cruz
 >> @Codigo: 20211692
/*/

import java.util.Scanner;

public class Cliente {
	private int dni;
	private String nombre;
	private double totalGastado;
	
	public Cliente() {
		totalGastado = 0.0;
	}
	
	public void SetDni(int dni) {
		this.dni = dni;
	}
	
	public int GetDni() {
		return dni;
	}
	
	public void SetNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String GetNombre() {
		return nombre;
	}
	
	public void SetTotalGastado(double totalGastado) {
		this.totalGastado = totalGastado;
	}
	
	public double GetTotalGastado() {
		return totalGastado;
	}
	
	public boolean leer(Scanner arch){
		dni = arch.nextInt();
		if(dni == 0) return false;
		nombre = arch.next();
		return true;
	}
	
	public void agregarVenta(Libro libro){
		totalGastado += libro.GetPrecio();
	}
	
	public void imprimir() {
		System.out.printf("DNI: %12d    NOMBRE: %-40s",dni,nombre);
	}
}
