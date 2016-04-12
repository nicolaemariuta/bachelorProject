package claseUtile;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import mainPack.PoartaLogica;

public class DesenareButoane extends JButton

{
	String type = null;
	
	public DesenareButoane(String type)
	{
		this.type = type;
		
//		this.setBackground(Color.LIGHT_GRAY);
	}
	
	public void paintComponent(Graphics g)
	{
		if(type == "line")
		{
		Graphics2D g2d = (Graphics2D) g;
		
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect(0, 0, 58, 49);
		g2d.setColor(Color.black);
		g2d.drawLine(17, 20, 17, 58);
		g2d.drawLine(17, 20, 58, 20);
		
		super.paintComponents(g);
		}
		
		if(type == "non")
		{
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(0, 0, 58, 49);
			g2d.setColor(Color.black);
			DesenarePorti.desenareNON(new PoartaLogica(new Coordonate(26, 23), "dreapta",20),g);
			super.paintComponents(g);
			
		}
		
		if(type == "on")
		{
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(0, 0, 58, 49);
			g2d.setColor(Color.black);
			DesenarePorti.desenareAMPL(new PoartaLogica(new Coordonate(26, 23), "dreapta",20),g);
			super.paintComponents(g);
			
		}
		
		if(type == "and")
		{
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(0, 0, 58, 49);
			g2d.setColor(Color.black);
			DesenarePorti.desenareAND(new PoartaLogica(new Coordonate(28, 23), "dreapta",20),g);
			int size = 20;
			int x = 28;
			int y = 23;
			
			g2d.drawLine(x-size*3/4, y+size*3/4, x-size*5/4, y+size*3/4);
			g2d.drawLine(x-size*3/4, y-size*3/4, x-size*5/4, y-size*3/4);
			super.paintComponents(g);
		}
		
		if(type == "nand")
		{
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(0, 0, 58, 49);
			g2d.setColor(Color.black);
			DesenarePorti.desenareNAND(new PoartaLogica(new Coordonate(28, 23), "dreapta",20),g);
			int size = 20;
			int x = 28;
			int y = 23;
			
			g2d.drawLine(x-size*3/4, y+size*3/4, x-size*5/4, y+size*3/4);
			g2d.drawLine(x-size*3/4, y-size*3/4, x-size*5/4, y-size*3/4);
			super.paintComponents(g);
		}
		
		
		if(type == "or")
		{
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(0, 0, 58, 49);
			g2d.setColor(Color.black);
			DesenarePorti.desenareOR(new PoartaLogica(new Coordonate(38, 23), "dreapta",20),g);
			int size = 20;
			int x = 38;
			int y = 23;
			
			g.drawLine(x-size*35/20, y-size/2, x-size*24/20, y-size/2);
			g.drawLine(x-size*35/20, y+size/2, x-size*24/20, y+size/2);
			super.paintComponents(g);
		}
		
		if(type == "nor")
		{
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(0, 0, 58, 49);
			g2d.setColor(Color.black);
			DesenarePorti.desenareNOR(new PoartaLogica(new Coordonate(36, 23), "dreapta",20),g);
			int size = 20;
			int x = 36;
			int y = 23;
			
			g.drawLine(x-size*35/20, y-size/2, x-size*24/20, y-size/2);
			g.drawLine(x-size*35/20, y+size/2, x-size*24/20, y+size/2);
			super.paintComponents(g);
		}
		
		if(type == "xor")
		{
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(0, 0, 58, 49);
			g2d.setColor(Color.black);
			DesenarePorti.desenareXOR(new PoartaLogica(new Coordonate(38, 23), "dreapta",20),g);
			int size = 20;
			int x = 38;
			int y = 23;
			
			g.drawLine(x-size*35/20, y-size/2, x-size*24/20, y-size/2);
			g.drawLine(x-size*35/20, y+size/2, x-size*24/20, y+size/2);
			super.paintComponents(g);
		}
		
		
		if(type == "nxor")
		{
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(0, 0, 58, 49);
			g2d.setColor(Color.black);
			DesenarePorti.desenareNXOR(new PoartaLogica(new Coordonate(38, 23), "dreapta",19),g);
			int size = 20;
			int x = 38;
			int y = 23;
			
			g.drawLine(x-size*35/20, y-size/2, x-size*24/20, y-size/2);
			g.drawLine(x-size*35/20, y+size/2, x-size*24/20, y+size/2);
			super.paintComponents(g);
		}
		
		
		if(type == "in")
		{
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(0, 0, 58, 49);
			g2d.setColor(Color.black);
			DesenarePorti.desenareAMPL(new PoartaLogica(new Coordonate(26, 23), "dreapta",20, Color.red),g);
			super.paintComponents(g);
			
		}
		
		
		if(type == "out")
		{
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(0, 0, 58, 49);
			g2d.setColor(Color.black);
			DesenarePorti.desenareAMPL(new PoartaLogica(new Coordonate(26, 23), "dreapta",20, new Color(61,169,58)),g);
			super.paintComponents(g);
			
		}
		
		if(type == "nod")
		{
			Graphics2D g2d = (Graphics2D) g;
			
			Stroke drawingStroke = new BasicStroke(2);
			g2d.setStroke(drawingStroke);
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(0, 0, 58, 49);
			g2d.setColor(Color.black);
			g2d.drawLine(17, 20, 17, 58);
			g2d.drawLine(17, 20, 58, 20);
			
			g2d.fillOval(26, 16, 8, 8);
			
			super.paintComponents(g);
		}
		
	}

}
