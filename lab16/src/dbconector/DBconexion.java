package dbconector;

import java.sql.Connection;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.sql.DriverManager;
//import java.sql.SQLException;

public class DBconexion {
    
    Connection conn = null;
    
    public static Connection conDB(){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Matricula", "root", "Reyes562");
            return con;
        } catch (Exception ex) {
            System.err.println("DBconexion: "+ex.getMessage());
            return null;
        }
        
    
    }
    
}
