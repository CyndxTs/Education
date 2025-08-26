
/* [/]
 >> @Project:    SoftBibliotecaTestModel
 >> @File:       Main.java
 >> @Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.tests.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softbiblioteca.politicas.model.*;
import pe.edu.pucp.softbiblioteca.publicaciones.model.*;
import pe.edu.pucp.softbiblioteca.localidad.model.*;
import pe.edu.pucp.softbiblioteca.prestamos.model.*;
import pe.edu.pucp.softbiblioteca.reservas.model.*;
import pe.edu.pucp.softbiblioteca.usuarios.model.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("---- <> Localidad <> ----");
        // Universidades
        Universidad pucp = new Universidad(1,"Pontificia Universidad Catolica del Peru","Av. Universitaria 1801, San Miguel 15088","pucp_jajas@hotmail.com","@pucp.edu.pe",null,null);
        Universidad cayetano = new Universidad(2,"Universidad Cayetano Heredia","Av. Honorio Delgado 430, San Martín de Porres 15102","calladitos@gmail.com","@cayetano.edu.pe",null,null);
        Universidad ulima = new Universidad(3,"Universidad de Lima","Av. Javier Prado Este 4600, Santiago de Surco 15023","manyas_mano@outlook.com","@ulimenios.edu.pe",null,null);
        Universidad[] universidades = {pucp,cayetano,ulima};
        // Bibliotecas
        Biblioteca cia = new Biblioteca(1,"Centro de Innovacion Academica","Al costado de faci",pucp);
        Biblioteca central = new Biblioteca(2,"Biblioteca Central","Como yendote pal Z",pucp);
        Biblioteca otraBib = new Biblioteca(3,"OtraBibliotecaPeroDeLaCayetano","En la cayetano, 'creo' :thumb-up:",cayetano);
        Biblioteca[] bibliotecas = {cia,central,otraBib};
        // Resultados
        for(Universidad uni : universidades) {
            System.out.print("# " + uni.consultarDatos());
            System.out.println(">> BIBLIOTECAS:");
            int i = 0;
            for(Biblioteca bib : bibliotecas) {
                if(bib.getUniversidadAsociada().getIdUniversidad() == uni.getIdUniversidad()) {
                    i++;
                    System.out.print(" - " + bib.consultarDatos());
                }
            }
            if(i == 0) System.out.println("\t > No se registraron bibliotecas. <");
        }
        System.out.println("---- <> Usuarios <> ----");
        // Usuarios
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios.add(new Usuario(1,"Ioni Hypes","Orihuela Goddess","elDiosdeKto@pucp.edu.pe","onepiece","jonasbroder",new Date(2012,12,12),TipoUsuario.ADMINISTRADOR));
        usuarios.add(new Usuario(2,"Giano Ruby","Casanova PadreTuyo","gianoHypes@pucp.edu.pe","nerfSisop","Channel",new Date(2012,10,5),TipoUsuario.MIEMBRO));
        // Resultados
        for(Usuario usu : usuarios) System.out.print("# " + usu.consultarDatos());
    }
}
