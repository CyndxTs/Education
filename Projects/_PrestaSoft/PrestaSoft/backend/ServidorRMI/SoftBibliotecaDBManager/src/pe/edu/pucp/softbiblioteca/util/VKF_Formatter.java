
/* [/]
 >> Project:    SoftBibliotecaDBManager
 >> File:       VKF_Formatter.java
 >> Author:     Seguidores de VK
[/] */

package pe.edu.pucp.softbiblioteca.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class VKF_Formatter {
    private static String ANSI_RESET = "\u001B[0m";
    private static String ANSI_VKF = "\u001B[36m";
    
    public String toSqlString(Date d) {
        String sqlStr_Date = "NULL";
        if (d != null) {
            sqlStr_Date = "'" + new SimpleDateFormat("yyyy-MM-dd").format(d) + "'";
        }
        return sqlStr_Date;
    }
    
    public String toSqlString(LocalDateTime ldt) {
        String sqlStr_DateTime = "NULL";
        if (ldt != null) {
            sqlStr_DateTime = "'" + ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "'";
        }
        return sqlStr_DateTime;
    }
    
    public String toSqlString(Boolean b) {
       String sqlStr_TinyInt = "NULL";
       if(b != null) {
           if(b) sqlStr_TinyInt = "True";
           else sqlStr_TinyInt = "False";
       }
       return sqlStr_TinyInt;
   }

    public <T extends Enum<T>> String toSqlString(T enumValue) {
        return "'" + enumValue.name().toUpperCase() + "'";
    }
    
    public LocalDateTime toValidLocalDateTime(String str_ldt) {
        LocalDateTime ldt = null;
        if(str_ldt != null && !str_ldt.isBlank()) {
            try {
                ldt = LocalDateTime.parse(str_ldt,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            } catch (DateTimeParseException e) {
                //System.err.println(ANSI_VKF + "VKF: La cadena '" + str_ldt + "' no es del formato correcto para el Parse hacia 'LocalDateTime'..");
                //System.err.println("     Retornando valor 'null' como default.." + ANSI_RESET);
                ldt = null;
            }
        }
        return ldt;
    }
    
    public Date toValidDate(String str_d) {
        Date d = null;
        if(str_d != null && !str_d.isBlank()) {
            try {
                d = (new SimpleDateFormat("yyyy-MM-dd")).parse(str_d);
            } catch (ParseException e) {
                //System.err.println(ANSI_VKF + "VKF: La cadena '" + str_d + "' no es del formato correcto para el Parse hacia 'Date'..");
                //System.err.println("     Retornando valor 'null' como default.." + ANSI_RESET);
                d = null;
            }
        }
        return d;
    }
    
   public Boolean toValidBoolean(Integer itg_b) {
       Boolean b = null;
       if(itg_b != null) {
           if(itg_b > 0) b = true;
           else if(itg_b == 0) b = false;
       }
       return b;
   }
   
   public String toValidString(LocalDateTime ldt) {
       String str_DateTime = "";
        if (ldt != null) {
            str_DateTime = ldt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        }
        return str_DateTime;
   }
   
   public String toValidString(Date d) {
       String str_Date = "";
        if (d != null) {
            str_Date = new SimpleDateFormat("dd/MM/yyyy").format(d);
        }
        return str_Date;
   }
   
   public <T extends Enum<T>> T toValidEnum(String str,Class<T> enumType) {
        try {
            return Enum.valueOf(enumType, str.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            //System.err.println(ANSI_VKF + "VKF: La cadena '" + str + "' no es del formato correcto para el Parse hacia '" + enumType.getSimpleName()+ "'..");
            //System.err.println("     Retornando valor 'null' como default.." + ANSI_RESET);
            return null;
        }
    }
   
   public boolean esFiltroValido(String str) {
       if(str != null && !str.isBlank()) return true;
       return false;
   }

   public boolean esFiltroValido(Integer itg) {
       if(itg != null && itg > 0) return true;
       return false;
   }
}
