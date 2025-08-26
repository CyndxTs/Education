
/*/
 >> @Alumno: Cesar Augusto Napuri de la Cruz
 >> @Codigo: 20211692
 >> Para Compilar:   javac *.java
 >> Para Runear:     java PRINCIPAL < VentaDeLibros.txt > Reporte.txt
/*/

import java.util.Scanner;
import java.util.ArrayList;

public class PRINCIPAL {
	
	public static void main(String[] args){
		// Inicializacion de Variables
		Libreria lib = new Libreria();
		Scanner arch = new Scanner(System.in);
		// Lectura Generalizada de Datos (Los metodos de lectura independiente estan definidos dentro de la clase igualmente , solo añadi esto porque hay nombre y direccion en el archivo)
		lib.leerDatos(arch);
		// Actualizacion de Ranking
		lib.actualizarRanking();
		// Impresion de Datos (Los metodos de Impresion independiente estan definidos dentro de la clase igualmente , solo añadi esto porque hay nombre y direccion en el Reporte)
		lib.imprimirDatos();
	}
}
