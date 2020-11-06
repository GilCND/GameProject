package pirates;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;


public class Panel extends javax.swing.JPanel implements ActionListener
{
	player1 player1;
	//image to player 
	Random rand = new Random();
	
	//points 
	int score;
	static int savedScore;
	int life =3;
	int counter;
	
	
	//Walls
	//Array list to store all walls
	ArrayList<Wall> walls = new ArrayList<>();
	Timer gTimer;
	int offset;
	int camerax;
	
	//buttons
	Rectangle Reset;
	Rectangle Smenu;
	Rectangle Save;
	Rectangle Load;
	
	//font 
	Font font = new Font("Arial", Font.ITALIC, 30);
	
	private Menu menu;
	
	public static enum STATE
	{
		MENU,
		GAME
	};
	private STATE State = STATE.MENU;
	
	//Constructor for a Panel
	public Panel ()
	{ 	
		//positioning Buttons on screen
		Reset = new Rectangle(550,25,50,50);
		Smenu = new Rectangle(600,25,50,50);
		
		//Player1 position
		player1 = new player1 (400, 300, this);
		restart(); 
		menu = new Menu();
		
		
		gTimer = new Timer();
		gTimer.schedule(new TimerTask(){

			@Override
			public void run() 
			{	
				//Check if we need to respown new walls
				
				if(walls.get(walls.size()-1).x < 800)
				{
					offset += 700; //in order to not print wall one top of another. 700 create a mew screem
					makeWalls(offset);
				}
				
				//update the player's position 
				player1.set();
				for(Wall wall: walls) //for int i=0; i<walls; i++
				{
					wall.set(camerax);
				}
				
				//Delete old wall in order to not use unecessary resources
				//Delete Walls
				for (int i =0; i<walls.size(); i++)
				{
					if (walls.get(i).x < -100)
					{
						score = score +1;
						walls.remove(i);
					}
				}
				
				repaint();
			
			}
			
		},0, 17);//how long it takes to start the timer (0 seconds), How many miliseconds between frames 1000/60 = 16.6
	}
	

	//when player dies

	public void reset()
	{
		//restart player position
	
			player1.x = 200;
			player1.y = 0;
			player1.xvelocity = 0;
			player1.yvelocity = 0;
			//clear walls and rebuild scenario
			walls.clear();
			offset = -150;
			makeWalls(offset);
			camerax = 150;
			counter = 0;
			life = 3;
			score = 0;
			savedScore =0;
			counter ++;
		}

	
	public void restart()
	{
		//restart player position

		player1.x = 200;
		player1.y = 0;
		player1.xvelocity = 0;
		player1.yvelocity = 0;
		//clear walls and rebuild scenario
		walls.clear();
		offset = -150;
		makeWalls(offset);
		camerax = 150;	
		if (counter ==0)
		{
			score = 0;
			savedScore =0;
		}
		else
		{
			if (life >0)
			{
				save();	
				load();
				life --;
				
			}
			else
			{
				SaveData.updateScore(savedScore);
				score = 0;	
				savedScore =0;
				State = STATE.MENU;
			}		

		}
		counter ++;
			
	}
	
	public void save ()
	{
		savedScore = score;
		
	}
	public void load ()
	{
		score = savedScore;	
		
	}
	
	public void makeWalls(int offset)
	{
		
		
		int size = 50;
		int map = rand.nextInt(6); //ammount of maps
		//floor
		
		if(map == 0)
		{
			
			for(int i =0; i<14; i++)
			{
				walls.add(new Wall(Color.ORANGE, offset + i*50, 600, size, size));
			}

		}
		else if (map == 1)
		{
			
			for (int i=0; i<5; i++)
			{
				walls.add(new Wall(Color.red, offset + i*50, 600, size, size));
			}
			walls.add(new Wall(Color.red,offset + 500, 600, size, size));
			walls.add(new Wall(Color.red,offset + 550, 600, size, size));
			walls.add(new Wall(Color.red,offset + 600, 600, size, size));
			walls.add(new Wall(Color.red,offset + 650, 600, size, size));
			walls.add(new Wall(Color.red,offset + 700, 600, size, size));
			walls.add(new Wall(Color.red,offset + 750, 600, size, size));		

		}

		else if (map == 2)
		{
			
			for (int i=0; i<5; i++)
			{
				walls.add(new Wall(Color.ORANGE, offset + i*50, 600, size, size));
			}
			for (int i=0; i<5; i++)
			{
				walls.add(new Wall(Color.ORANGE, offset + 450 + i*50, 600, size, size));
			}
			walls.add(new Wall(Color.ORANGE, offset + 150 + 550, 600, size, size));
			walls.add(new Wall(Color.ORANGE, offset + 200 + 550, 600, size, size));
			walls.add(new Wall(Color.ORANGE, offset + 200 + 500, 600, size, size));
			walls.add(new Wall(Color.ORANGE, offset + 200 + 450, 600, size, size));
			walls.add(new Wall(Color.ORANGE, offset + 500 + 500, 600, size, size));
			walls.add(new Wall(Color.ORANGE, offset + 450 + 550, 600, size, size));
			walls.add(new Wall(Color.ORANGE, offset + 450 + 500, 600, size, size));
			walls.add(new Wall(Color.ORANGE, offset + 450 + 450, 600, size, size));

		}

		else if (map ==3)
		{
			
			for (int i=0; i<5; i++)
			{
				walls.add(new Wall(Color.BLUE, offset + i*50, 600, size, size));
			}
			for (int i=0; i<4; i++)
			{
				walls.add(new Wall(Color.BLUE, offset + 50 + i*50, 550, size, size));
			}
			for (int i=0; i<3; i++)
			{
				walls.add(new Wall(Color.BLUE, offset + 100 + i*50, 500, size, size));
			}
			for (int i=0; i<3; i++)
			{
				walls.add(new Wall(Color.BLUE, offset + 150 + i*50, 450, size, size));
			}
			for (int i=0; i<3; i++)
			{
				walls.add(new Wall(Color.BLUE, offset + 500 + i*50, 600, size, size));
			}

		}	

		else if (map == 4)
		{
			
			for (int i= 0; i<5; i++)
			{
				walls.add(new Wall(Color.YELLOW, offset + i*50, 600, size, size));
			}
			for (int i= 0; i<3; i++)
			{
				walls.add(new Wall(Color.YELLOW, offset + 100 + i*50, 550, size, size));
			}
			for (int i= 0; i<2; i++)
			{
				walls.add(new Wall(Color.YELLOW, offset + 150 + i*50, 500, size, size));
			}
			for (int l= 0; l<2; l++)
			{
				for (int i=0; i<4; i++)
				{
					walls.add(new Wall(Color.YELLOW, offset + 350 + i*50, 450 + 50+l, size, size));
				}		
			}
		}

			else if (map == 5)
			{
				for (int i= 0; i<5; i++)
				{
					walls.add(new Wall(Color.pink, offset + i*150, 600, size, size));
				}
			}	
		
	}	
	//Send Graphics to this method when its called
	public void paint(Graphics g)
	{

		super.paint(g);
		//Cast the graphics to 2D
		Graphics2D g2d = (Graphics2D) g;
		if (State == STATE.GAME)
		{
		//Draw a player
		player1.draw(g2d);
		
		//Draw the wall
		for(Wall wall: walls) wall.draw(g2d);
		
		//Draw Buttons
		g2d.setColor(Color.BLACK);
		//r
		g2d.drawRect(550, 25, 50, 50);
		//m
		g2d.drawRect(600, 25, 50, 50);
		//bar
		g2d.drawRect(25, 25, 500, 50);

	
		g2d.setColor(Color.WHITE);
		//r
		g2d.fillRect(551, 26, 48, 48);
		//m
		g2d.fillRect(601, 26, 48, 48);
		//score
		g2d.fillRect(25, 26, 500, 48);


		g2d.setColor(Color.BLACK);
		g2d.setFont(font);
		g2d.drawString("R", 564, 60);
		g2d.drawString("M", 614, 60);
		
		g2d.drawString("Life", 240,60);
		g2d.drawString("Score", 26,60);
		String scoreString = Integer.toString(score);
		String lifeString = Integer.toString(life);			
		g2d.drawString(scoreString, 115,60);
		g2d.drawString(lifeString, 320,60);
		
		}
		else if (State == STATE.MENU)
		{
			menu.render(g2d);
		}
			
	}
	

	@Override
	public void actionPerformed(ActionEvent e) 
	{			
	}

	//Methods
	
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyChar() == 'a' || e.getKeyChar() ==  'A') 
		{
			player1.kLeft = true; 
			
		}
		if(e.getKeyChar() == 'd'||  e.getKeyChar() ==  'D') 
		{
			player1.kRight = true; 
			
		}
		if(e.getKeyChar() == 'w' || e.getKeyChar() ==  'W') 
		{
			player1.kUp = true; 
		}
		if(e.getKeyChar() == 's' || e.getKeyChar() ==  'S') 
		{
			player1.kDown = true; 
		}
		if (e.getKeyChar() == 'r' || e.getKeyChar() ==  'R')
		{
			restart();
		}
		if (e.getKeyChar() == 'm' || e.getKeyChar() ==  'M')
		{
			
		}


		
	}

	public void keyReleased(KeyEvent e) 
	{
		if(e.getKeyChar() == 'a' || e.getKeyChar() ==  'A') 
		{
			player1.kLeft = false; 
		}
		if(e.getKeyChar() == 'd'||  e.getKeyChar() ==  'D') 
		{
			player1.kRight = false; 
		}
		if(e.getKeyChar() == 'w' || e.getKeyChar() ==  'W') 
		{
			player1.kUp = false; 
		}
		if(e.getKeyChar() == 's' || e.getKeyChar() ==  'S') 
		{
			player1.kDown = false; 
		}
		if (e.getKeyChar() == 'r' || e.getKeyChar() ==  'R')
		{
			reset();
		}
		if (e.getKeyChar() == 'm' || e.getKeyChar() ==  'M')
		{
			changeGameState();
		}

			
	}

	public void changeGameState()
	{
		if (State == STATE.GAME) 
		{
			State = STATE.MENU;
		}
		else
		State = STATE.GAME;		
	}
	
	public void mouseClicked(MouseEvent e) {
		System.out.println("Click");
		if (Reset.contains(new Point(e.getPoint().x, e.getPoint().y-27))) reset();	
		if (Smenu.contains(new Point(e.getPoint().x, e.getPoint().y-27))) changeGameState();
        Point location = MouseInfo.getPointerInfo().getLocation();
        double x = location.getX();
        double y = location.getY();
        
        String userName = SelectRecords.getName();
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("State" +State);
        System.out.println("User Name" + userName);
        
        if (Menu.play.contains(new Point(e.getPoint().x, e.getPoint().y-27))) changeGameState();		
        if (Menu.help.contains(new Point(e.getPoint().x, e.getPoint().y-27))) changeGameState();
        if (Menu.quit.contains(new Point(e.getPoint().x, e.getPoint().y-27))) System.exit(0);;     
		
	}
}
