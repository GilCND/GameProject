package pirates;

import java.awt.*;

import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Menu {
	
	public static Rectangle play = new Rectangle(290, 150,100,50);
	public static Rectangle help = new Rectangle(290, 250,100,50);
	public static Rectangle quit = new Rectangle(290, 350,100,50);
	public static Rectangle chart = new Rectangle(200, 450,300,200);
	
	public static void render(Graphics g)
	{


		Graphics2D g3d = (Graphics2D) g;
		Font fnt0 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("NO COINS TO PIRATES", 175,100);
		
		Font fnt1 = new Font("arial", Font.BOLD,30);
		g.setFont(fnt1);
		g.drawString("Play", play.x+20, play.y+35);
		g3d.draw(play);
		
		g.drawString("Help", help.x+20, help.y+35);
		g3d.draw(help);
		g.drawString("Quit", quit.x+20, quit.y+35);
		g3d.draw(quit);
		String name = SelectRecords.getName();
		String score = SelectRecords.getScore();
		String id = SelectRecords.getId();
		g.drawString("High SCORE", chart.x+60, chart.y+35);
		g.drawString(id, chart.x+40, chart.y+75);
		g.drawString(name, chart.x+80, chart.y+75);
		g.drawString(score, chart.x+200, chart.y+75);
		g3d.draw(chart);
	
	}
	
	
}
