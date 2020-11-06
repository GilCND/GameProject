package pirates;

import java.sql.DriverManager;  
import java.sql.Connection;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;  
   
public class SelectRecords {  
   
    public static Connection connect() {  
        // SQLite connection string  
    	String url = "jdbc:sqlite:"+Settings.GAME_PATH+"/game.db"; 
        Connection conn = null;  
        try {  
            conn = DriverManager.getConnection(url);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
        return conn;  
    }  

    
    public void selectAll(){  
        String sql = "SELECT * FROM player";  
          
        try {  
            Connection conn = this.connect();  
            Statement stmt  = conn.createStatement();  
            ResultSet rs    = stmt.executeQuery(sql);  
              
            // loop through the result set  
            while (rs.next()) {  
                System.out.println(rs.getInt("id") +  "\t" +   
                                   rs.getString("name") + "\t" +  
                                   rs.getDouble("score"));  
            }  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
   

    public static String getName(){
        String sql = "SELECT id, name FROM players WHERE id = 1";
        String name = "";

        try {
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);


            // loop through the result set
            while (rs.next()) {

                                 name = rs.getString("name");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return name;
    }
   
   
}  
