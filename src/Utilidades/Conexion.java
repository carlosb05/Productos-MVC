
package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
   Connection con;
   String driver = "com.mysql.jc.jdbc.Driver";
   String dbName= "virtualshop";
   String url = "jdbc:mysql://localhost:3306/"+dbName+"?useSSL=false&serverTimeZone=UTC";
   String usuario = "root";
   String clave = "";
   
   public Connection Conectar(){
       try {
           con = DriverManager.getConnection(url,usuario,clave);
           System.out.println("Conexion exitosa");
       } catch (SQLException e) {
           System.out.println("Error en la conexion "+ e);
       }
       return con;
   }
   
   }


