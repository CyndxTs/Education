
/* [/]
 >> @Project:    SoftBibliotecaUsuariosModel
 >> @File:       TipoUsuario.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.usuarios.model;

import java.io.Serializable;

public enum TipoUsuario implements Serializable{
    MIEMBRO,
    BIBLIOTECARIO,
    PROGRAMADOR,
    ADMINISTRADOR,
    SANCIONADO,
    INACTIVO
}
