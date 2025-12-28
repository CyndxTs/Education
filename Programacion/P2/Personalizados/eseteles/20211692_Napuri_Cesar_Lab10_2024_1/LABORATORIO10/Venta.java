
/*/
 >> @Alumno: Cesar Augusto Napuri de la Cruz
 >> @Codigo: 20211692
/*/

import java.util.Scanner;

public class Venta {
	private int dni;
	private int fecha;
	private int calificacion;
	
	public Venta(){
		dni = 0;
		fecha = 0;
		calificacion = 0;
	}
	
	public void SetDni(int dni) {
		this.dni = dni;
	}
	
	public int GetDni() {
		return dni;
	}
	
	public void SetFecha(int fecha) {
		this.fecha = fecha;
	}
	
	public int GetFecha() {
		return fecha;
	}
	
	public void SetCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
	public int GetCalificacion() {
		return calificacion;
	}
	
	public void leer(Scanner arch) {
		int dd,mm,aa;
		dni = arch.nextInt();
		dd = arch.nextInt();
		mm = arch.nextInt();
		aa = arch.nextInt();
		fecha = dd + 100*mm + 10000*aa;
		calificacion = arch.nextInt();
	}
	
	public void imprimir(Cliente cliente) {
		int aa = fecha/10000;
		int mm = (fecha - 10000*aa)/100;
		int dd = fecha - 10000*aa - 100*mm;
		cliente.imprimir();
		System.out.printf("CALIFICACION: %3d    FECHA: ",calificacion);
		System.out.printf("%2d/%2d/%4d",dd,mm,aa);
		System.out.println("");
	}
}
