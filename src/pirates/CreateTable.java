package pirates;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
import java.sql.Statement;  
   
public class CreateTable {  
   
    public static void createNewTable() {  
        // SQLite connection string  
    	String url = "jdbc:sqlite:"+Settings.GAME_PATH+"/game.db";  
          
        // SQL statement for creating a new table  
        
        String sql = "CREATE TABLE IF NOT EXISTS players (\n"
                + " id int PRIMARY KEY,\n"
                + " name text, "
                + " score int NOT NULL \n"
                + ");";
          
        try{  
            Connection conn = DriverManager.getConnection(url);  
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
   

   
}  