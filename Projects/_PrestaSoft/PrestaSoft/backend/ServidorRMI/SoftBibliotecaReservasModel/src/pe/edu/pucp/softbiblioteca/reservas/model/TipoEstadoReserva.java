
/* [/]
 >> Project:    SoftBibliotecaReservasModel
 >> File:       TipoEstadoReserva.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.reservas.model;

import java.io.Serializable;

public enum TipoEstadoReserva implements Serializable{
    AGENDADA,
    CANCELADA,
    RECOGIDA,
    NORECOGIDA
}
