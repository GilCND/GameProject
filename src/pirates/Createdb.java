package pirates;

	import java.sql.Connection;  
	import java.sql.DatabaseMetaData;  
	import java.sql.DriverManager;  
	import java.sql.SQLException;  
	   
	public class Createdb {  
	  
	    public static void createNewDatabase(String fileName) {  
	   
	    	String url = "jdbc:sqlite:"+Settings.GAME_PATH+"/game.db"; 
	   
	        try {  
	            Connection conn = DriverManager.getConnection(url);  
	            if (conn != null) {  
	                DatabaseMetaData meta = conn.getMetaData();  
	                System.out.println("A new database has been created.");  
	            }  
	   
	        } catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        }  
	    }  
	  
	    public static void main(String[] args) {  
	        createNewDatabase("game.db");  
	    }  
	}  
