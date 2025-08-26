
/* [/]
 >> Project:    SoftBibliotecaPrestamosModel
 >> File:       TipoEstadoPrestamo.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.prestamos.model;

import java.io.Serializable;

public enum TipoEstadoPrestamo implements Serializable{
    PORDEVOLVER,
    DEVUELTO,
    ATRASADO,
    CANCELADOPORPERDIDA
}
