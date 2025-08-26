
/* [/]
 >> Project:    SoftBibliotecaTestDBManager
 >> File:       Main.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.tests.dbmanager;
import pe.edu.pucp.softbiblioteca.db.DBManager;

public class Main {
    public static void main(String[] args) {
        DBManager dbManager = DBManager.getInstance();
        System.out.println(dbManager.getConnection());
    }
}
