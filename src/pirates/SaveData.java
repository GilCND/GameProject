package pirates;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;  
   
public class SaveData {  
   
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

    public void insert(int id, String inpname, int score) {
        String sql = "INSERT INTO players(id, name, score) VALUES(?,?,?)";

        System.out.println(inpname); 
        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, inpname);
            pstmt.setInt(3, score);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    } 

    public static void updateScore(int score) {
        String sql = "UPDATE players SET score  = ? WHERE id = 1";

         
        try{
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, score);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    } 

    public static void updateName(String name) {
        String sql = "UPDATE players SET name  = ? WHERE id = 1";

         
        try{
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    } 
   
}  