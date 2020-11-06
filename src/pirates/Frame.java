package pirates;

import java.awt.Color;
import java.awt.Graphics;

public class Frame extends javax.swing.JFrame
{
	//Constructor
	public Frame ()
	{
		//Menu configuration
		Panel panel1 = new Panel();
		panel1.setLocation(0,0);
		panel1.setSize(this.getSize());
		panel1.setBackground(Color.GRAY);
		panel1.setVisible(true);
		this.add(panel1);
		
		addKeyListener(new Control(panel1));
		addMouseListener(new Mouse(panel1));		
	}
}
