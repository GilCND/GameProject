package pirates;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class noCoinstoPirates {


	public static String userName;
	
	public static void main(String[] args) 
	{
		
		//Check if the DB exists
		File file = new File (Settings.GAME_PATH + "/game.db");
		if (file.exists())
		{
			//Database already exists retrieve variables
		}
		
		
		else
		{
			//Database does not exist, create database
			Createdb.createNewDatabase(Settings.GAME_PATH + "/game.db");
	
			//Create table
			CreateTable.createNewTable();

			SaveData app = new SaveData();
			//Insert default blank player
			app.insert(1,null,0);
		}
		

		
		if(userName == null) {
			userName = JOptionPane.showInputDialog("Please enter your name: ");
			SaveData.updateName(userName);
		}
		
		
		
		//Screen Frame
		Frame frame = new Frame();
		frame.setSize(700,700);
		
		//Dimension of the window
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((int)(screenSize.getWidth()/2 - frame.getSize().getWidth()/2), 
		(int)(screenSize.getHeight()/2 - frame.getSize().getHeight()/2));
		
		//Frame title and basic conf
		frame.setResizable(false);
		frame.setTitle("No Coins to Pirates");
		frame.setVisible(true);
		
		//close
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		

		
	}
	
	
	
}
