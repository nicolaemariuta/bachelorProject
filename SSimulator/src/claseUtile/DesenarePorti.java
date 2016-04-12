package claseUtile;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.Stack;

import mainPack.PoartaLogica;
import mainPack.SuprafataDesenare;

public class DesenarePorti

{
	
	
	public static void desenare(PoartaLogica poarta, Graphics g)
	{
		
		for(int i = 0; i< poarta.in.length; i++)
		{
			poarta.in[i] = null;
		}
		
		if(poarta.getType().equals("AMPL"))
		{
			
			desenareAMPL(poarta,g);
		}
		
		if(poarta.getType().equals("NON"))
		{
			desenareNON(poarta,g);
		}
		
		
		if(poarta.getType().equals("AND"))
		{
			desenareAND(poarta,g);
		}
		
		if(poarta.getType().equals("NAND"))
		{
			desenareNAND(poarta,g);
		}
		
		
		if(poarta.getType().equals("OR"))
		{
			desenareOR(poarta,g);
		}
		
		if(poarta.getType().equals("NOR"))
		{
			desenareNOR(poarta,g);
		}
		
		if(poarta.getType().equals("XOR"))
		{
			desenareXOR(poarta,g);
		}
		
		if(poarta.getType().equals("NXOR"))
		{
			desenareNXOR(poarta,g);
		}
		
		if(poarta.getType().equals("OUT"))
		{
		
			desenareAMPL(poarta,g);
		}
		
		if(poarta.getType().equals("IN"))
		{
			desenareAMPL(poarta,g);
		}
		
		
	}
	
	
	public static void desenareAND(PoartaLogica poarta, Graphics g)
	
	{
		if(poarta.getOrientare().equals("dreapta") )
		{
			
			
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);
		
		Coordonate c = poarta.getCoord();
		int size = poarta.getSize();

		
		int x = c.getX();
		int y = c.getY();
		
		
		g2d.setColor(poarta.getCuloare());
		
		g2d.drawLine(x-size*3/4, y+size, x-size*3/4, y-size);
		g2d.drawLine(x+size*2/4, y, x+size*3/2 , y);
		poarta.setOut(new Coordonate(x+size*3/2 , y));

		
		
		
		GeneralPath path = new GeneralPath();
		path.moveTo(x-size*3/4, y+size);
		path.curveTo(x-size*3/4, y+size, x+size*8/20, y+size*18/20, x+size/2, y);
		g2d.draw(path);
		
		
		path.moveTo(x-size*3/4, y-size);
		path.curveTo(x-size*3/4, y-size, x+size*8/20, y-size*18/20, x+size/2, y);
		g2d.draw(path);
		
		try
		{
		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
			g2d.drawString(nume, x+size*12/20, y-size*4/12);
			
		}
		} catch(NullPointerException e) {}
		
		
		if(poarta.getNrIn()  == 2)
		{
			g2d.drawLine(x-size*3/4, y+size*3/4, x-size*5/4, y+size*3/4);
			g2d.drawLine(x-size*3/4, y-size*3/4, x-size*5/4, y-size*3/4);
			
			poarta.in[0] = new Coordonate(x-size*5/4, y+size*3/4);
			poarta.in[1] = new Coordonate(x-size*5/4, y-size*3/4);
		}
		
		
		if(poarta.getNrIn()  == 3)
		{
			
			g2d.drawLine(x-size*3/4, y+size*3/4, x-size*5/4, y+size*3/4);
			g2d.drawLine(x-size*3/4, y-size*3/4, x-size*5/4, y-size*3/4);
			g2d.drawLine(x-size*3/4, y, x-size*5/4, y);
			
			poarta.in[0] = new Coordonate(x-size*5/4, y+size*3/4);
			poarta.in[2] = new Coordonate(x-size*5/4, y-size*3/4);
			poarta.in[1] = new Coordonate(x-size*5/4, y);
			

		}
		
		if(poarta.getNrIn()  == 4)
		{
			
			g.drawLine(x-size*3/4, y+size*4/5, x-size*5/4, y+size*4/5);
			g.drawLine(x-size*3/4, y-size*4/5, x-size*5/4, y-size*4/5);
			
			poarta.in[0] = new Coordonate(x-size*5/4, y+size*4/5);
			poarta.in[1] = new Coordonate(x-size*5/4, y-size*4/5);
			
			g.drawLine(x-size*3/4, y+size*2/5, x-size*5/4, y+size*2/5);
			g.drawLine(x-size*3/4, y-size*2/5, x-size*5/4, y-size*2/5);
			
			poarta.in[2] = new Coordonate(x-size*5/4, y+size*2/5);
			poarta.in[3] = new Coordonate(x-size*5/4, y-size*2/5);
		
		}
		
		if(poarta.getNrIn()  == 5)
		{
			
			g.drawLine(x-size*3/4, y+size*4/5, x-size*5/4, y+size*4/5);
			g.drawLine(x-size*3/4, y-size*4/5, x-size*5/4, y-size*4/5);
			
			poarta.in[0] = new Coordonate(x-size*5/4, y+size*4/5);
			poarta.in[1] = new Coordonate(x-size*5/4, y-size*4/5);
			
			g.drawLine(x-size*3/4, y, x-size*5/4, y);
			
			poarta.in[2] = new Coordonate(x-size*5/4, y);
			
			
			g.drawLine(x-size*3/4, y+size*2/5, x-size*5/4, y+size*2/5);
			g.drawLine(x-size*3/4, y-size*2/5, x-size*5/4, y-size*2/5);
			
			poarta.in[3] = new Coordonate(x-size*5/4, y+size*2/5);
			poarta.in[4] = new Coordonate(x-size*5/4, y-size*2/5);
		
		}
		
		
		}
		
		
		if(poarta.getOrientare().equals("stanga"))
		{
			
			
			Graphics2D g2d = (Graphics2D) g;
			Stroke drawingStroke = new BasicStroke(2);
			g2d.setStroke(drawingStroke);
			
			
			Coordonate c = poarta.getCoord();
			int size = - poarta.getSize();
		
			
			int x = c.getX();
			int y = c.getY();
			
			
			
			
			g2d.setColor(poarta.getCuloare());
			
			g2d.drawLine(x-size*3/4, y+size, x-size*3/4, y-size);   
			g2d.drawLine(x+size*2/4, y, x+size*3/2 , y);
			poarta.setOut(new Coordonate(x+size*3/2 , y));
			
		

		
			
			GeneralPath path = new GeneralPath();
			path.moveTo(x-size*3/4, y+size);
			path.curveTo(x-size*3/4, y+size, x+size*8/20, y+size*18/20, x+size/2, y);
			g2d.draw(path);
			
			
			path.moveTo(x-size*3/4, y-size);
			path.curveTo(x-size*3/4, y-size, x+size*8/20, y-size*18/20, x+size/2, y);
			g2d.draw(path);
			
			try
			{
			if(poarta.getNume()!=null)
			{
				String nume = poarta.getNume();
				if(poarta.getValue()==0 || poarta.getValue() == 1)
				{
					nume = nume+ "="+String.valueOf(poarta.getValue());
				}
				
				g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
				
				g2d.drawString(nume, x+size*15/20, y-size*4/12);
				
				
			}} catch (Exception e){}
			
			if(poarta.getNrIn()  == 2)
			{
				g.drawLine(x-size*3/4, y+size*3/4, x-size*5/4, y+size*3/4);
				g.drawLine(x-size*3/4, y-size*3/4, x-size*5/4, y-size*3/4);
				
				poarta.in[0] = new Coordonate(x-size*5/4, y+size*3/4);
				poarta.in[1] = new Coordonate(x-size*5/4, y-size*3/4);
			}
			
			
			if(poarta.getNrIn()  == 3)
			{
				
				g.drawLine(x-size*3/4, y+size*3/4, x-size*5/4, y+size*3/4);
				g.drawLine(x-size*3/4, y-size*3/4, x-size*5/4, y-size*3/4);
				
				poarta.in[0] = new Coordonate(x-size*5/4, y+size*3/4);
				poarta.in[2] = new Coordonate(x-size*5/4, y-size*3/4);
				
				g.drawLine(x-size*3/4, y, x-size*5/4, y);
				
				poarta.in[1] = new Coordonate(x-size*5/4, y);
			}
			
			if(poarta.getNrIn()  == 4)
			{
				
				g.drawLine(x-size*3/4, y+size*4/5, x-size*5/4, y+size*4/5);
				g.drawLine(x-size*3/4, y-size*4/5, x-size*5/4, y-size*4/5);
				
				poarta.in[0] = new Coordonate(x-size*5/4, y+size*4/5);
				poarta.in[1] = new Coordonate(x-size*5/4, y-size*4/5);
				
				g.drawLine(x-size*3/4, y+size*2/5, x-size*5/4, y+size*2/5);
				g.drawLine(x-size*3/4, y-size*2/5, x-size*5/4, y-size*2/5);
				
				poarta.in[2] = new Coordonate(x-size*5/4, y+size*2/5);
				poarta.in[3] = new Coordonate(x-size*5/4, y-size*2/5);
			
			}
			
			if(poarta.getNrIn()  == 5)
			{
				
				g.drawLine(x-size*3/4, y+size*4/5, x-size*5/4, y+size*4/5);
				g.drawLine(x-size*3/4, y-size*4/5, x-size*5/4, y-size*4/5);
				
				poarta.in[0] = new Coordonate(x-size*5/4, y+size*4/5);
				poarta.in[1] = new Coordonate(x-size*5/4, y-size*4/5);
				
				g.drawLine(x-size*3/4, y, x-size*5/4, y);
				
				poarta.in[2] = new Coordonate(x-size*5/4, y);
				
				g.drawLine(x-size*3/4, y+size*2/5, x-size*5/4, y+size*2/5);
				g.drawLine(x-size*3/4, y-size*2/5, x-size*5/4, y-size*2/5);
				
				poarta.in[3] = new Coordonate(x-size*5/4, y+size*2/5);
				poarta.in[4] = new Coordonate(x-size*5/4, y-size*2/5);
			
			}
			
		
			
		}
		
		
		if(poarta.getOrientare().equals("jos"))
		{
			Coordonate c = poarta.getCoord();
			int size = poarta.getSize();
			
			Graphics2D g2d = (Graphics2D) g;
			Stroke drawingStroke = new BasicStroke(2);
			g2d.setStroke(drawingStroke);
			
			int x = c.getX();
			int y = c.getY();
			
			
			g2d.setColor(poarta.getCuloare());
			
			g2d.drawLine(x+size, y-size*3/4, x-size, y-size*3/4);     
			g2d.drawLine(x, y+size*2/4, x , y+size*3/2); 
			poarta.setOut(new Coordonate(x , y+size*3/2));
			
			
		
			
			GeneralPath path = new GeneralPath();
			path.moveTo(x+size, y-size*3/4);             			
			path.curveTo(x+size, y-size*3/4, x+size*18/20, y+size*8/20, x, y+size/2);
			g2d.draw(path);
			
			
			path.moveTo(x-size, y-size*3/4);               
			path.curveTo(x-size, y-size*3/4, x-size*18/20, y+size*8/20, x, y+size/2);
		
			g2d.draw(path);
			
			try{
			if(poarta.getNume()!=null)
			{
				String nume = poarta.getNume();
				if(poarta.getValue()==0 || poarta.getValue() == 1)
				{
					nume = nume+ "="+String.valueOf(poarta.getValue());
				}
				
				g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
				Font font = g2d.getFont();
				AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(90));
				g2d.setFont(font.deriveFont(at));
				g2d.drawString(nume, x+size*10/12, y+size*5/20);
				
				
			}} catch(Exception e){}

			if(poarta.getNrIn()  == 2)
			{
				g.drawLine(x+size*3/4, y-size*3/4, x+size*3/4, y-size*5/4);
				g.drawLine(x-size*3/4, y-size*3/4, x-size*3/4, y-size*5/4);
				
				poarta.in[0] = new Coordonate(x+size*3/4, y-size*5/4);
				poarta.in[1] = new Coordonate(x-size*3/4, y-size*5/4);
			}
			
			
			if(poarta.getNrIn()  == 3)
			{
				
				g.drawLine(x+size*3/4, y-size*3/4, x+size*3/4, y-size*5/4);
				g.drawLine(x-size*3/4, y-size*3/4, x-size*3/4, y-size*5/4);
				
				poarta.in[0] = new Coordonate(x+size*3/4, y-size*5/4);
				poarta.in[2] = new Coordonate(x-size*3/4, y-size*5/4);
				
				g.drawLine(x, y-size*3/4, x, y-size*5/4);
				
				poarta.in[1] = new Coordonate(x, y-size*5/4);
			}
			
			if(poarta.getNrIn()  == 4)
			{
				
				g.drawLine(x+size*4/5, y-size*3/4, x+size*4/5, y-size*5/4);
				g.drawLine(x-size*4/5, y-size*3/4, x-size*4/5, y-size*5/4);
				
				poarta.in[0] = new Coordonate(x+size*4/5, y-size*5/4);
				poarta.in[1] = new Coordonate(x-size*4/5, y-size*5/4);
				
			
				
				g.drawLine(x+size*2/5, y-size*3/4, x+size*2/5, y-size*5/4);
				g.drawLine(x-size*2/5, y-size*3/4, x-size*2/5, y-size*5/4);
				
				poarta.in[2] = new Coordonate(x+size*2/5, y-size*5/4);
				poarta.in[3] = new Coordonate(x-size*2/5, y-size*5/4);
				
			
			}
			
			if(poarta.getNrIn()  == 5)
			{
				
				g.drawLine(x+size*4/5, y-size*3/4, x+size*4/5, y-size*5/4);
				g.drawLine(x-size*4/5, y-size*3/4, x-size*4/5, y-size*5/4);
				
				poarta.in[0] = new Coordonate(x+size*4/5, y-size*5/4);
				poarta.in[1] = new Coordonate(x-size*4/5, y-size*5/4);
				
				g.drawLine(x, y-size*3/4, x, y-size*5/4);
				poarta.in[2] = new Coordonate(x, y-size*5/4);
				
				g.drawLine(x+size*2/5, y-size*3/4, x+size*2/5, y-size*5/4);
				g.drawLine(x-size*2/5, y-size*3/4, x-size*2/5, y-size*5/4);
				
				poarta.in[3] = new Coordonate(x+size*2/5, y-size*5/4);
				poarta.in[4] = new Coordonate(x-size*2/5, y-size*5/4);
			
			}
			
		}
		
		
		if(poarta.getOrientare().equals("sus"))
		{
			Coordonate c = poarta.getCoord();
			int size = - poarta.getSize();
			
			Graphics2D g2d = (Graphics2D) g;
			Stroke drawingStroke = new BasicStroke(2);
			g2d.setStroke(drawingStroke);
			
			int x = c.getX();
			int y = c.getY();
			
			
			g2d.setColor(poarta.getCuloare());
			
			g2d.drawLine(x+size, y-size*3/4, x-size, y-size*3/4);     
			g2d.drawLine(x, y+size*2/4, x , y+size*3/2);  
			poarta.setOut(new Coordonate(x , y+size*3/2));
			
			
			
			GeneralPath path = new GeneralPath();
			path.moveTo(x+size, y-size*3/4);             			
			path.curveTo(x+size, y-size*3/4, x+size*18/20, y+size*8/20, x, y+size/2);
			g2d.draw(path);
			
			
			path.moveTo(x-size, y-size*3/4);               
			path.curveTo(x-size, y-size*3/4, x-size*18/20, y+size*8/20, x, y+size/2);
		
			g2d.draw(path);
			
			try{
			if(poarta.getNume()!=null)
			{
				String nume = poarta.getNume();
				if(poarta.getValue()==0 || poarta.getValue() == 1)
				{
					nume = nume+ "="+String.valueOf(poarta.getValue());
				}
				
				g2d.setFont(new Font("Arial", Font.BOLD, -size*10/12));
				Font font = g2d.getFont();
				AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(90));
				g2d.setFont(font.deriveFont(at));
				g2d.drawString(nume, x+size*12/20, y+size*18/12);
				
				
			}} catch(Exception e){}

			if(poarta.getNrIn()  == 2)
			{
				g.drawLine(x+size*3/4, y-size*3/4, x+size*3/4, y-size*5/4);
				g.drawLine(x-size*3/4, y-size*3/4, x-size*3/4, y-size*5/4);
				
				poarta.in[0] = new Coordonate(x+size*3/4, y-size*5/4);
				poarta.in[1] = new Coordonate(x-size*3/4, y-size*5/4);
			}
			
			
			if(poarta.getNrIn()  == 3)
			{
				
				g.drawLine(x+size*3/4, y-size*3/4, x+size*3/4, y-size*5/4);
				g.drawLine(x-size*3/4, y-size*3/4, x-size*3/4, y-size*5/4);
				
				poarta.in[0] = new Coordonate(x+size*3/4, y-size*5/4);
				poarta.in[2] = new Coordonate(x-size*3/4, y-size*5/4);
				
				g.drawLine(x, y-size*3/4, x, y-size*5/4);
				
				poarta.in[1] = new Coordonate(x, y-size*5/4);
			}
			
			if(poarta.getNrIn()  == 4)
			{
				
				g.drawLine(x+size*4/5, y-size*3/4, x+size*4/5, y-size*5/4);
				g.drawLine(x-size*4/5, y-size*3/4, x-size*4/5, y-size*5/4);
				
				poarta.in[0] = new Coordonate(x+size*4/5, y-size*5/4);
				poarta.in[1] = new Coordonate(x-size*4/5, y-size*5/4);
				
			
				
				g.drawLine(x+size*2/5, y-size*3/4, x+size*2/5, y-size*5/4);
				g.drawLine(x-size*2/5, y-size*3/4, x-size*2/5, y-size*5/4);
				
				poarta.in[2] = new Coordonate(x+size*2/5, y-size*5/4);
				poarta.in[3] = new Coordonate(x-size*2/5, y-size*5/4);
				
			
			}
			
			if(poarta.getNrIn()  == 5)
			{
				
				g.drawLine(x+size*4/5, y-size*3/4, x+size*4/5, y-size*5/4);
				g.drawLine(x-size*4/5, y-size*3/4, x-size*4/5, y-size*5/4);
				
				poarta.in[0] = new Coordonate(x+size*4/5, y-size*5/4);
				poarta.in[1] = new Coordonate(x-size*4/5, y-size*5/4);
				
				g.drawLine(x, y-size*3/4, x, y-size*5/4);
				poarta.in[2] = new Coordonate(x, y-size*5/4);
				
				g.drawLine(x+size*2/5, y-size*3/4, x+size*2/5, y-size*5/4);
				g.drawLine(x-size*2/5, y-size*3/4, x-size*2/5, y-size*5/4);
				
				poarta.in[3] = new Coordonate(x+size*2/5, y-size*5/4);
				poarta.in[4] = new Coordonate(x-size*2/5, y-size*5/4);
			
			}
		}
	
		
	}
	
	
	
	
	public static void desenareNAND(PoartaLogica poarta, Graphics g)
	{
		
		if(poarta.getOrientare().equals("dreapta") )
		{
		Coordonate c = poarta.getCoord();
		int size = poarta.getSize();
		
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);
		
		g2d.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		g2d.drawLine(x-size*3/4, y+size, x-size*3/4, y-size);
		g.drawOval(x+size*2/4, y-size*1/8, size*2/8, size*2/8);
		
		g.drawLine(x+size*3/4, y, x+size*3/2 , y);
		poarta.setOut(new Coordonate(x+size*3/2 , y));
		
		
		
	
		GeneralPath path = new GeneralPath();
		path.moveTo(x-size*3/4, y+size);
		path.curveTo(x-size*3/4, y+size, x+size*8/20, y+size*18/20, x+size/2, y);
		g2d.draw(path);
		
		
		path.moveTo(x-size*3/4, y-size);
		path.curveTo(x-size*3/4, y-size, x+size*8/20, y-size*18/20, x+size/2, y);
		g2d.draw(path);
		
		try{
		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
			g2d.drawString(nume, x+size*12/20, y-size*4/12);
			
		}} catch(Exception e){}
		
		
		if(poarta.getNrIn()  == 2)
		{
			g.drawLine(x-size*3/4, y+size*3/4, x-size*5/4, y+size*3/4);
			g.drawLine(x-size*3/4, y-size*3/4, x-size*5/4, y-size*3/4);
			
			poarta.in[0] = new Coordonate(x-size*5/4, y+size*3/4);
			poarta.in[1] = new Coordonate(x-size*5/4, y-size*3/4);
		}
		
		
		if(poarta.getNrIn()  == 3)
		{
			
			g.drawLine(x-size*3/4, y+size*3/4, x-size*5/4, y+size*3/4);
			g.drawLine(x-size*3/4, y-size*3/4, x-size*5/4, y-size*3/4);
			
			poarta.in[0] = new Coordonate(x-size*5/4, y+size*3/4);
			poarta.in[2] = new Coordonate(x-size*5/4, y-size*3/4);
			
			g.drawLine(x-size*3/4, y, x-size*5/4, y);
			
			poarta.in[1] = new Coordonate(x-size*5/4, y);
		}
		
		if(poarta.getNrIn()  == 4)
		{
			
			g.drawLine(x-size*3/4, y+size*4/5, x-size*5/4, y+size*4/5);
			g.drawLine(x-size*3/4, y-size*4/5, x-size*5/4, y-size*4/5);
			
			poarta.in[0] = new Coordonate(x-size*5/4, y+size*4/5);
			poarta.in[1] = new Coordonate(x-size*5/4, y-size*4/5);
			
			g.drawLine(x-size*3/4, y+size*2/5, x-size*5/4, y+size*2/5);
			g.drawLine(x-size*3/4, y-size*2/5, x-size*5/4, y-size*2/5);
			
			poarta.in[2] = new Coordonate(x-size*5/4, y+size*2/5);
			poarta.in[3] = new Coordonate(x-size*5/4, y-size*2/5);
		
		}
		
		if(poarta.getNrIn()  == 5)
		{
			
			g.drawLine(x-size*3/4, y+size*4/5, x-size*5/4, y+size*4/5);
			g.drawLine(x-size*3/4, y-size*4/5, x-size*5/4, y-size*4/5);
			
			poarta.in[0] = new Coordonate(x-size*5/4, y+size*4/5);
			poarta.in[1] = new Coordonate(x-size*5/4, y-size*4/5);
			
			g.drawLine(x-size*3/4, y, x-size*5/4, y);
			
			poarta.in[2] = new Coordonate(x-size*5/4, y);
			
			g.drawLine(x-size*3/4, y+size*2/5, x-size*5/4, y+size*2/5);
			g.drawLine(x-size*3/4, y-size*2/5, x-size*5/4, y-size*2/5);
			
			poarta.in[3] = new Coordonate(x-size*5/4, y+size*2/5);
			poarta.in[4] = new Coordonate(x-size*5/4, y-size*2/5);
		
		}
		
		}
		
		if(poarta.getOrientare().equals("stanga") )
		{
			Coordonate c = poarta.getCoord();
			int size = - poarta.getSize();
		
			Graphics2D g2d = (Graphics2D) g;
			Stroke drawingStroke = new BasicStroke(2);
			g2d.setStroke(drawingStroke);
			
			g2d.setColor(poarta.getCuloare());
			int x = c.getX();
			int y = c.getY();
			
			g2d.drawLine(x-size*3/4, y+size, x-size*3/4, y-size);
			g.drawOval(x+size*3/4, y+size*1/8, -size*2/8, -size*2/8);
			
			g.drawLine(x+size*3/4, y, x+size*3/2 , y);
			poarta.setOut(new Coordonate(x+size*3/2 , y));
			
			
			
			
		
			GeneralPath path = new GeneralPath();
			path.moveTo(x-size*3/4, y+size);
			path.curveTo(x-size*3/4, y+size, x+size*8/20, y+size*18/20, x+size/2, y);
			g2d.draw(path);
			
			
			path.moveTo(x-size*3/4, y-size);
			path.curveTo(x-size*3/4, y-size, x+size*8/20, y-size*18/20, x+size/2, y);
			g2d.draw(path);
			
			String nume = poarta.getNume();
			
			try{
			if(poarta.getNume()!=null)
			{
			
				if(poarta.getValue()==0 || poarta.getValue() == 1)
				{
					nume = nume+ "="+String.valueOf(poarta.getValue());
				}
				
				
			}
			} catch (Exception e) {}
			g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
			g2d.drawString(nume, x+size*15/20, y-size*4/12);
			
			
			if(poarta.getNrIn()  == 2)
			{
				g.drawLine(x-size*3/4, y+size*3/4, x-size*5/4, y+size*3/4);
				g.drawLine(x-size*3/4, y-size*3/4, x-size*5/4, y-size*3/4);
				
				poarta.in[0] = new Coordonate(x-size*5/4, y+size*3/4);
				poarta.in[1] = new Coordonate(x-size*5/4, y-size*3/4);
			}
			
			
			if(poarta.getNrIn()  == 3)
			{
				
				g.drawLine(x-size*3/4, y+size*3/4, x-size*5/4, y+size*3/4);
				g.drawLine(x-size*3/4, y-size*3/4, x-size*5/4, y-size*3/4);
				
				poarta.in[0] = new Coordonate(x-size*5/4, y+size*3/4);
				poarta.in[2] = new Coordonate(x-size*5/4, y-size*3/4);
				
				g.drawLine(x-size*3/4, y, x-size*5/4, y);
				
				poarta.in[1] = new Coordonate(x-size*5/4, y);
			}
			
			if(poarta.getNrIn()  == 4)
			{
				
				g.drawLine(x-size*3/4, y+size*4/5, x-size*5/4, y+size*4/5);
				g.drawLine(x-size*3/4, y-size*4/5, x-size*5/4, y-size*4/5);
				
				poarta.in[0] = new Coordonate(x-size*5/4, y+size*4/5);
				poarta.in[1] = new Coordonate(x-size*5/4, y-size*4/5);
				
				g.drawLine(x-size*3/4, y+size*2/5, x-size*5/4, y+size*2/5);
				g.drawLine(x-size*3/4, y-size*2/5, x-size*5/4, y-size*2/5);
				
				poarta.in[2] = new Coordonate(x-size*5/4, y+size*2/5);
				poarta.in[3] = new Coordonate(x-size*5/4, y-size*2/5);
			
			}
			
			if(poarta.getNrIn()  == 5)
			{
				
				g.drawLine(x-size*3/4, y+size*4/5, x-size*5/4, y+size*4/5);
				g.drawLine(x-size*3/4, y-size*4/5, x-size*5/4, y-size*4/5);
				
				poarta.in[0] = new Coordonate(x-size*5/4, y+size*4/5);
				poarta.in[1] = new Coordonate(x-size*5/4, y-size*4/5);
				
				g.drawLine(x-size*3/4, y, x-size*5/4, y);
				
				poarta.in[2] = new Coordonate(x-size*5/4, y);
				
				g.drawLine(x-size*3/4, y+size*2/5, x-size*5/4, y+size*2/5);
				g.drawLine(x-size*3/4, y-size*2/5, x-size*5/4, y-size*2/5);
				
				poarta.in[3] = new Coordonate(x-size*5/4, y+size*2/5);
				poarta.in[4] = new Coordonate(x-size*5/4, y-size*2/5);
			
			}
			
		}
		
		
		
		
		if(poarta.getOrientare().equals("jos"))
		{
		Coordonate c = poarta.getCoord();
		int size = poarta.getSize();

		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);
		
		g2d.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		g2d.drawLine(x+size, y-size*3/4, x-size, y-size*3/4);
	
	
	
		g2d.drawOval(x-size*1/8, y+size*2/4, size*2/8, size*2/8);
		g2d.drawLine(x, y+size*3/4, x , y+size*3/2);
		poarta.setOut(new Coordonate(x , y+size*3/2));
		
		
		try
		{
		if(poarta.getNume()!=null)
			{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
				
			g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
			Font font = g2d.getFont();
			AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(90));
			g2d.setFont(font.deriveFont(at));
			g2d.drawString(nume, x+size*10/12, y+size*5/20);
				
				
			} } catch(Exception e){}

		
		
	
		GeneralPath path = new GeneralPath();
		path.moveTo(x+size, y-size*3/4);
		path.curveTo(x+size, y-size*3/4, x+size*18/20, y+size*8/20, x, y+size/2);
		g2d.draw(path);
		
		
		path.moveTo(x-size, y-size*3/4);
		path.curveTo(x-size, y-size*3/4, x-size*18/20, y+size*8/20, x, y+size/2);
		g2d.draw(path);
		
		if(poarta.getNrIn()  == 2)
		{
			g.drawLine(x+size*3/4, y-size*3/4, x+size*3/4, y-size*5/4);
			g.drawLine(x-size*3/4, y-size*3/4, x-size*3/4, y-size*5/4);
			
			poarta.in[0] = new Coordonate(x+size*3/4, y-size*5/4);
			poarta.in[1] = new Coordonate(x-size*3/4, y-size*5/4);
			
			
		}
		
		
		if(poarta.getNrIn()  == 3)
		{
			
			g.drawLine(x+size*3/4, y-size*3/4, x+size*3/4, y-size*5/4);
			g.drawLine(x-size*3/4, y-size*3/4, x-size*3/4, y-size*5/4);
			
			poarta.in[0] = new Coordonate(x+size*3/4, y-size*5/4);
			poarta.in[2] = new Coordonate(x-size*3/4, y-size*5/4);
			
			g.drawLine(x, y-size*3/4, x, y-size*5/4);
			
			poarta.in[1] = new Coordonate(x, y-size*5/4);
		}
		
		if(poarta.getNrIn()  == 4)
		{
			
			g.drawLine(x+size*4/5, y-size*3/4, x+size*4/5, y-size*5/4);
			g.drawLine(x-size*4/5, y-size*3/4, x-size*4/5, y-size*5/4);
			
			poarta.in[0] = new Coordonate(x+size*4/5, y-size*5/4);
			poarta.in[1] = new Coordonate(x-size*4/5, y-size*5/4);
			
			g.drawLine(x+size*2/5, y-size*3/4, x+size*2/5, y-size*5/4);
			g.drawLine(x-size*2/5, y-size*3/4, x-size*2/5, y-size*5/4);
			
			poarta.in[2] = new Coordonate(x+size*2/5, y-size*5/4);
			poarta.in[3] = new Coordonate(x-size*2/5, y-size*5/4);
			
		
		}
		
		if(poarta.getNrIn()  == 5)
		{
			
			g.drawLine(x+size*4/5, y-size*3/4, x+size*4/5, y-size*5/4);
			g.drawLine(x-size*4/5, y-size*3/4, x-size*4/5, y-size*5/4);
			
			poarta.in[0] = new Coordonate(x+size*4/5, y-size*5/4);
			poarta.in[1] = new Coordonate(x-size*4/5, y-size*5/4);
			
			g.drawLine(x, y-size*3/4, x, y-size*5/4);
			
			poarta.in[2] = new Coordonate(x, y-size*5/4);
			
			g.drawLine(x+size*2/5, y-size*3/4, x+size*2/5, y-size*5/4);
			g.drawLine(x-size*2/5, y-size*3/4, x-size*2/5, y-size*5/4);
			
			poarta.in[3] = new Coordonate(x+size*2/5, y-size*5/4);
			poarta.in[4] = new Coordonate(x-size*2/5, y-size*5/4);
		
		}
		
		}
		
		
		
		if(poarta.getOrientare().equals("sus") )
		{
		Coordonate c = poarta.getCoord();
		int size = - poarta.getSize();
		

		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);
		
	
		g2d.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		g2d.drawLine(x+size, y-size*3/4, x-size, y-size*3/4);
	
	
	
		g2d.drawOval(x+size*1/8, y+size*3/4, -size*2/8, -size*2/8);
		g2d.drawLine(x, y+size*3/4, x , y+size*3/2);
		poarta.setOut(new Coordonate(x , y+size*3/2));
		
		
		
		
		
	
		GeneralPath path = new GeneralPath();
		path.moveTo(x+size, y-size*3/4);
		path.curveTo(x+size, y-size*3/4, x+size*18/20, y+size*8/20, x, y+size/2);
		g2d.draw(path);
		
		
		path.moveTo(x-size, y-size*3/4);
		path.curveTo(x-size, y-size*3/4, x-size*18/20, y+size*8/20, x, y+size/2);
		g2d.draw(path);
		
		try{
		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			
			g2d.setFont(new Font("Arial", Font.BOLD, -size*10/12));
			Font font = g2d.getFont();
			AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(90));
			g2d.setFont(font.deriveFont(at));
			g2d.drawString(nume, x+size*12/20, y+size*18/12);
			
		} } catch (Exception e){}
		

		if(poarta.getNrIn()  == 2)
		{
			g.drawLine(x+size*3/4, y-size*3/4, x+size*3/4, y-size*5/4);
			g.drawLine(x-size*3/4, y-size*3/4, x-size*3/4, y-size*5/4);
			
			poarta.in[0] = new Coordonate(x+size*3/4, y-size*5/4);
			poarta.in[1] = new Coordonate(x-size*3/4, y-size*5/4);
			
			
		}
		
		
		if(poarta.getNrIn()  == 3)
		{
			
			g.drawLine(x+size*3/4, y-size*3/4, x+size*3/4, y-size*5/4);
			g.drawLine(x-size*3/4, y-size*3/4, x-size*3/4, y-size*5/4);
			
			poarta.in[0] = new Coordonate(x+size*3/4, y-size*5/4);
			poarta.in[2] = new Coordonate(x-size*3/4, y-size*5/4);
			
			g.drawLine(x, y-size*3/4, x, y-size*5/4);
			
			poarta.in[1] = new Coordonate(x, y-size*5/4);
		}
		
		if(poarta.getNrIn()  == 4)
		{
			
			g.drawLine(x+size*4/5, y-size*3/4, x+size*4/5, y-size*5/4);
			g.drawLine(x-size*4/5, y-size*3/4, x-size*4/5, y-size*5/4);
			
			poarta.in[0] = new Coordonate(x+size*4/5, y-size*5/4);
			poarta.in[1] = new Coordonate(x-size*4/5, y-size*5/4);
			
			g.drawLine(x+size*2/5, y-size*3/4, x+size*2/5, y-size*5/4);
			g.drawLine(x-size*2/5, y-size*3/4, x-size*2/5, y-size*5/4);
			
			poarta.in[2] = new Coordonate(x+size*2/5, y-size*5/4);
			poarta.in[3] = new Coordonate(x-size*2/5, y-size*5/4);
			
		
		}
		
		if(poarta.getNrIn()  == 5)
		{
			
			g.drawLine(x+size*4/5, y-size*3/4, x+size*4/5, y-size*5/4);
			g.drawLine(x-size*4/5, y-size*3/4, x-size*4/5, y-size*5/4);
			
			poarta.in[0] = new Coordonate(x+size*4/5, y-size*5/4);
			poarta.in[1] = new Coordonate(x-size*4/5, y-size*5/4);
			
			g.drawLine(x, y-size*3/4, x, y-size*5/4);
			
			poarta.in[2] = new Coordonate(x, y-size*5/4);
			
			g.drawLine(x+size*2/5, y-size*3/4, x+size*2/5, y-size*5/4);
			g.drawLine(x-size*2/5, y-size*3/4, x-size*2/5, y-size*5/4);
			
			poarta.in[3] = new Coordonate(x+size*2/5, y-size*5/4);
			poarta.in[4] = new Coordonate(x-size*2/5, y-size*5/4);
		
		}
		}
		
		
		
	}
	
	
	public static void desenareAMPL(PoartaLogica poarta, Graphics g)
	{
		
		if(poarta.getOrientare().equals("dreapta") )
		{
			
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);
		
		Coordonate c = poarta.getCoord();
		int size = poarta.getSize();

		
		g2d.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		g2d.drawLine(x-size*3/4, y+size, x-size*3/4, y-size);
		g2d.drawLine(x-size*3/4, y+size, x+size*5/4, y);
		g2d.drawLine(x+size*5/4, y, x-size*3/4, y-size);
		
		g.drawLine(x+size*5/4, y, x+size*2 , y);
		poarta.setOut(new Coordonate(x+size*2 , y));
		
		
		g.drawLine(x-size*3/4, y, x-size*5/4, y);
		poarta.in[0] = new Coordonate(x-size*5/4, y);
		
		try{
		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
			g2d.drawString(nume, x+size, y-size*2/12);
			
		} } catch(Exception e) {}
		
		}
		
		if(poarta.getOrientare().equals("stanga") )
		{
			
		
		Coordonate c = poarta.getCoord();
		int size = - poarta.getSize();
		
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);
	
		
		g2d.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		g2d.drawLine(x-size*3/4, y+size, x-size*3/4, y-size);
		g2d.drawLine(x-size*3/4, y+size, x+size*5/4, y);
		g2d.drawLine(x+size*5/4, y, x-size*3/4, y-size);
		
		g.drawLine(x+size*5/4, y, x+size*2 , y);
		poarta.setOut(new Coordonate(x+size*2 , y));
		
		g.drawLine(x-size*3/4, y, x-size*5/4, y);
		poarta.in[0] = new Coordonate(x-size*5/4, y);
		
		try{
		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			
			g.setFont(new Font("Arial", Font.BOLD, size*10/12));
			
			g.drawString(nume, x+size*15/20, y-size*4/12);
			
			
		}
		} catch(Exception e){}
		}
		
		if(poarta.getOrientare().equals("jos"))
		{
			
		
		Coordonate c = poarta.getCoord();
		int size = poarta.getSize();
		
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);
	
		
		g2d.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		g2d.drawLine(x+size, y-size*3/4, x-size, y-size*3/4);
		g2d.drawLine(x+size, y-size*3/4, x, y+size*5/4);
		g2d.drawLine(x, y+size*5/4, x-size, y-size*3/4);
		
		g.drawLine(x, y+size*5/4, x , y+size*2);
		poarta.setOut(new Coordonate(x , y+size*2));
		
		
		g.drawLine(x, y-size*3/4, x, y-size*5/4);
		poarta.in[0] = new Coordonate(x, y-size*5/4);
		
		try{
		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			
			g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
			Font font = g2d.getFont();
			AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(90));
			g2d.setFont(font.deriveFont(at));
			g2d.drawString(nume, x+size*10/12, y+size*5/20);
			
			
		}} catch(Exception e){}
		
		
		}
		
		
		if(poarta.getOrientare().equals("sus") )
		{
			
		
		Coordonate c = poarta.getCoord();
		int size = - poarta.getSize();
		
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);

		
		g2d.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		g2d.drawLine(x+size, y-size*3/4, x-size, y-size*3/4);
		g.drawLine(x+size, y-size*3/4, x, y+size*5/4);
		g.drawLine(x, y+size*5/4, x-size, y-size*3/4);
		
		
		g.drawLine(x, y+size*5/4, x , y+size*2);
		poarta.setOut(new Coordonate(x , y+size*2));
		
		g.drawLine(x, y-size*3/4, x, y-size*5/4);
		poarta.in[0] = new Coordonate(x, y-size*5/4);
		
		try{
		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			
			g2d.setFont(new Font("Arial", Font.BOLD, -size*10/12));
			Font font = g2d.getFont();
			AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(90));
			g2d.setFont(font.deriveFont(at));
			g2d.drawString(nume, x+size*12/20, y+size*28/12);
		}}catch(Exception e) {}

		}
		
	
	}
	
	
	
	public static void desenareNON(PoartaLogica poarta, Graphics g)
	{
		if(poarta.getOrientare().equals("dreapta") )
		{
		Coordonate c = poarta.getCoord();
		int size = poarta.getSize();
		
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);

		
		g2d.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
	
		
		g2d.drawLine(x-size*3/4, y+size, x-size*3/4, y-size);
		g2d.drawLine(x-size*3/4, y+size, x+size, y);
		g2d.drawLine(x+size, y, x-size*3/4, y-size);
		g2d.drawOval(x+size, y-size*3/20, size*3/10, size*3/10);
		
		
		g.drawLine(x+size*13/10, y, x+size*20/10 , y);
		poarta.setOut(new Coordonate(x+size*20/10 , y));
		
		g.drawLine(x-size*3/4, y, x-size*5/4, y);
		poarta.in[0] = new Coordonate(x-size*5/4, y);
		
		try{
		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
			g2d.drawString(nume, x+size, y-size*2/12);
			
		}}catch(Exception e){}
		
		}
		
		if(poarta.getOrientare().equals("stanga") )
		{
		Coordonate c = poarta.getCoord();
		int size = - poarta.getSize();

		
		g.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);
		
		
		
		g2d.drawLine(x-size*3/4, y+size, x-size*3/4, y-size);
		g2d.drawLine(x-size*3/4, y+size, x+size, y);
		g2d.drawLine(x+size, y, x-size*3/4, y-size);
		g2d.drawOval(x+size*26/20, y+size*3/20, -size*3/10, -size*3/10);
		
		
		g.drawLine(x+size*13/10, y, x+size*20/10 , y);
		poarta.setOut(new Coordonate(x+size*20/10 , y));
		
		g.drawLine(x-size*3/4, y, x-size*5/4, y);
		poarta.in[0] = new Coordonate(x-size*5/4, y);
		
		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			
			g.setFont(new Font("Arial", Font.BOLD, size*10/12));
			
			g.drawString(nume, x+size*15/20, y-size*4/12);
			
			
		}
		
		}
		
		
		
		
		if(poarta.getOrientare().equals("jos") )
		{
		Coordonate c = poarta.getCoord();
		int size = poarta.getSize();
		
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);

		
		g2d.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		
		
		g2d.drawLine(x+size, y-size*3/4, x-size, y-size*3/4);
		g2d.drawLine(x+size, y-size*3/4, x, y+size);
		g2d.drawLine(x, y+size, x-size, y-size*3/4);
		g2d.drawOval(x-size*3/20, y+size, size*3/10, size*3/10);
		
		g.drawLine(x, y+size*13/10, x, y+size*20/10 );
		poarta.setOut(new Coordonate(x, y+size*20/10));
		
		g.drawLine(x, y-size*3/4, x, y-size*5/4);
		poarta.in[0] = new Coordonate(x, y-size*5/4);
		
		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			
			g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
			Font font = g2d.getFont();
			AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(90));
			g2d.setFont(font.deriveFont(at));
			g2d.drawString(nume, x+size*10/12, y+size*5/20);
			
			
		}
		
		}
		
		if(poarta.getOrientare().equals("sus"))
		{
		Coordonate c = poarta.getCoord();
		int size = -poarta.getSize();
	
		
		g.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);
		
		
		g2d.drawLine(x+size, y-size*3/4, x-size, y-size*3/4);
		g2d.drawLine(x+size, y-size*3/4, x, y+size);
		g2d.drawLine(x, y+size, x-size, y-size*3/4);
		g2d.drawOval(x+size*3/20, y+size*26/20, -size*3/10, -size*3/10);
		
		g.drawLine(x, y+size*13/10, x, y+size*20/10 );
		poarta.setOut(new Coordonate(x, y+size*20/10));
		
		g.drawLine(x, y-size*3/4, x, y-size*5/4);
		poarta.in[0] = new Coordonate(x, y-size*5/4);
		
		
		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			
			g2d.setFont(new Font("Arial", Font.BOLD, -size*10/12));
			Font font = g2d.getFont();
			AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(90));
			g2d.setFont(font.deriveFont(at));
			g2d.drawString(nume, x+size*12/20, y+size*28/12);
			
			
		}
		
		}
		
		
		
		
		
		
	}
	
	
	
	public static void desenareOR(PoartaLogica poarta, Graphics g)
	{
		
		if(poarta.getOrientare().equals("dreapta"))
		{
		
		Coordonate c = poarta.getCoord();
		int size = poarta.getSize();
		
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);

		
		g2d.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		g2d.drawLine(x+size/2, y, x+size*16/20, y);
		poarta.setOut(new Coordonate(x+size*16/20, y));
		
		
  
		
		GeneralPath path = new GeneralPath();
		path.moveTo(x-size*28/20, y-size);
		path.curveTo(x-size*28/20, y-size, x-size*3/4, y, x-size*28/20, y+size);
		g2d.draw(path);
		
		path.moveTo(x-size*28/20, y-size);
		path.curveTo(x-size*28/20, y-size, x+size*8/20, y-size*18/20, x+size/2, y);
		g2d.draw(path);
		
		
		path.moveTo(x-size*28/20, y+size);
		path.curveTo(x-size*28/20, y+size, x+size*8/20, y+size*18/20, x+size/2, y);
		g2d.draw(path);
		
		
		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
			g2d.drawString(nume, x+size*12/20, y-size*4/12);
			
		}
		
		if(poarta.getNrIn() == 2)
		{
		g.drawLine(x-size*35/20, y-size/2, x-size*24/20, y-size/2);
		g.drawLine(x-size*35/20, y+size/2, x-size*24/20, y+size/2);
		
		poarta.in[0] = new Coordonate(x-size*35/20, y-size/2);
		poarta.in[1] = new Coordonate(x-size*35/20, y+size/2);
		
		
		}
		
		if(poarta.getNrIn() == 3)
		{
		g.drawLine(x-size*35/20, y-size/2, x-size*24/20, y-size/2);
		g.drawLine(x-size*35/20, y+size/2, x-size*24/20, y+size/2);
		
		poarta.in[0] = new Coordonate(x-size*35/20, y-size/2);
		poarta.in[2] = new Coordonate(x-size*35/20, y+size/2);
		
		g.drawLine(x-size*23/20, y, x-size*35/20, y);
		
		poarta.in[1] = new Coordonate(x-size*35/20, y);
		
		}
		
		if(poarta.getNrIn() == 4)
		{
		g.drawLine(x-size*35/20, y-size*4/5, x-size*26/20, y-size*4/5);
		g.drawLine(x-size*35/20, y+size*4/5, x-size*26/20, y+size*4/5);
		
		poarta.in[0] = new Coordonate(x-size*35/20, y-size*4/5);
		poarta.in[1] = new Coordonate(x-size*35/20, y+size*4/5);
		
		g.drawLine(x-size*35/20, y-size*2/5, x-size*23/20, y-size*2/5);
		g.drawLine(x-size*35/20, y+size*2/5, x-size*23/20, y+size*2/5);
		
		poarta.in[2] = new Coordonate(x-size*35/20, y-size*2/5);
		poarta.in[3] = new Coordonate(x-size*35/20, y+size*2/5);
		}
		
		
		if(poarta.getNrIn() == 5)
		{
		g.drawLine(x-size*35/20, y-size*4/5, x-size*26/20, y-size*4/5);
		g.drawLine(x-size*35/20, y+size*4/5, x-size*26/20, y+size*4/5);
		
		poarta.in[0] = new Coordonate(x-size*35/20, y-size*4/5);
		poarta.in[1] = new Coordonate(x-size*35/20, y+size*4/5);
		
		g.drawLine(x-size*23/20, y, x-size*35/20, y);
		
		poarta.in[2] = new Coordonate(x-size*35/20, y);
		
		g.drawLine(x-size*35/20, y-size*2/5, x-size*23/20, y-size*2/5);
		g.drawLine(x-size*35/20, y+size*2/5, x-size*23/20, y+size*2/5);
		
		poarta.in[3] = new Coordonate(x-size*35/20, y-size*2/5);
		poarta.in[4] = new Coordonate(x-size*35/20, y+size*2/5);
		}
		
		
		}
		
		
		if(poarta.getOrientare().equals("stanga"))
		{
		
		Coordonate c = poarta.getCoord();
		int size = - poarta.getSize();
		
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);
	
		
		g2d.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		g2d.drawLine(x+size/2, y, x+size*16/20, y);
		poarta.setOut(new Coordonate(x+size*16/20, y));
		
	
		
		GeneralPath path = new GeneralPath();
		path.moveTo(x-size*28/20, y-size);
		path.curveTo(x-size*28/20, y-size, x-size*3/4, y, x-size*28/20, y+size);
		g2d.draw(path);
		
		path.moveTo(x-size*28/20, y-size);
		path.curveTo(x-size*28/20, y-size, x+size*8/20, y-size*18/20, x+size/2, y);
		g2d.draw(path);
		
		
		path.moveTo(x-size*28/20, y+size);
		path.curveTo(x-size*28/20, y+size, x+size*8/20, y+size*18/20, x+size/2, y);
		g2d.draw(path);
		
		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			
			g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
			
			g2d.drawString(nume, x+size*15/20, y-size*4/12);
			
			
		}

		
		
		if(poarta.getNrIn() == 2)
		{
		g.drawLine(x-size*35/20, y-size/2, x-size*24/20, y-size/2);
		g.drawLine(x-size*35/20, y+size/2, x-size*24/20, y+size/2);
		
		poarta.in[0] = new Coordonate(x-size*35/20, y-size/2);
		poarta.in[1] = new Coordonate(x-size*35/20, y+size/2);
		
		
		}
		
		if(poarta.getNrIn() == 3)
		{
		g.drawLine(x-size*35/20, y-size/2, x-size*24/20, y-size/2);
		g.drawLine(x-size*35/20, y+size/2, x-size*24/20, y+size/2);
		
		poarta.in[0] = new Coordonate(x-size*35/20, y-size/2);
		poarta.in[2] = new Coordonate(x-size*35/20, y+size/2);
		
		g.drawLine(x-size*23/20, y, x-size*35/20, y);
		
		poarta.in[1] = new Coordonate(x-size*35/20, y);
		
		}
		
		if(poarta.getNrIn() == 4)
		{
		g.drawLine(x-size*35/20, y-size*4/5, x-size*26/20, y-size*4/5);
		g.drawLine(x-size*35/20, y+size*4/5, x-size*26/20, y+size*4/5);
		
		poarta.in[0] = new Coordonate(x-size*35/20, y-size*4/5);
		poarta.in[1] = new Coordonate(x-size*35/20, y+size*4/5);
		
		g.drawLine(x-size*35/20, y-size*2/5, x-size*23/20, y-size*2/5);
		g.drawLine(x-size*35/20, y+size*2/5, x-size*23/20, y+size*2/5);
		
		poarta.in[2] = new Coordonate(x-size*35/20, y-size*2/5);
		poarta.in[3] = new Coordonate(x-size*35/20, y+size*2/5);
		}
		
		
		if(poarta.getNrIn() == 5)
		{
		g.drawLine(x-size*35/20, y-size*4/5, x-size*26/20, y-size*4/5);
		g.drawLine(x-size*35/20, y+size*4/5, x-size*26/20, y+size*4/5);
		
		poarta.in[0] = new Coordonate(x-size*35/20, y-size*4/5);
		poarta.in[1] = new Coordonate(x-size*35/20, y+size*4/5);
		
		g.drawLine(x-size*23/20, y, x-size*35/20, y);
		
		poarta.in[2] = new Coordonate(x-size*35/20, y);
		
		g.drawLine(x-size*35/20, y-size*2/5, x-size*23/20, y-size*2/5);
		g.drawLine(x-size*35/20, y+size*2/5, x-size*23/20, y+size*2/5);
		
		poarta.in[3] = new Coordonate(x-size*35/20, y-size*2/5);
		poarta.in[4] = new Coordonate(x-size*35/20, y+size*2/5);
		}
		
		
		}
		
		
		if(poarta.getOrientare().equals("jos"))
		{
		
		Coordonate c = poarta.getCoord();
		int size = poarta.getSize();
		
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);

		
		g2d.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		g2d.drawLine(x, y+size/2, x, y+size*16/20);
		poarta.setOut(new Coordonate(x, y+size*16/20));
		
		
    	
		
		GeneralPath path = new GeneralPath();
		path.moveTo(x-size, y-size*28/20);
		path.curveTo(x-size, y-size*28/20, x, y-size*3/4, x+size, y-size*28/20);
		g2d.draw(path);
		
		path.moveTo(x-size, y-size*28/20);
		path.curveTo(x-size, y-size*28/20, x-size*18/20, y+size*8/20, x, y+size/2);
		g2d.draw(path);
		
		
		path.moveTo(x+size, y-size*28/20);
		path.curveTo(x+size, y-size*28/20, x+size*18/20, y+size*8/20, x, y+size/2);
		g2d.draw(path);
		
		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			
			g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
			Font font = g2d.getFont();
			AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(90));
			g2d.setFont(font.deriveFont(at));
			g2d.drawString(nume, x+size*10/12, y+size*5/20);
			
			
		}
		
		if(poarta.getNrIn() == 2)
		{
		g.drawLine(x-size/2, y-size*35/20, x-size/2, y-size*24/20);
		g.drawLine(x+size/2, y-size*35/20, x+size/2, y-size*24/20);
		
		poarta.in[0] = new Coordonate(x-size/2, y-size*35/20);
		poarta.in[1] = new Coordonate(x+size/2, y-size*35/20);
		
		}
		
		if(poarta.getNrIn() == 3)
		{
		g.drawLine(x-size/2, y-size*35/20, x-size/2, y-size*24/20);
		g.drawLine(x+size/2, y-size*35/20, x+size/2, y-size*24/20);
		
		poarta.in[0] = new Coordonate(x-size/2, y-size*35/20);
		poarta.in[2] = new Coordonate(x+size/2, y-size*35/20);
		
		g.drawLine(x, y-size*23/20, x, y-size*35/20);
		
		poarta.in[1] = new Coordonate(x, y-size*35/20);
		}
		
		if(poarta.getNrIn() == 4)
		{
		g.drawLine(x-size*4/5, y-size*35/20, x-size*4/5, y-size*26/20);
		g.drawLine(x+size*4/5, y-size*35/20, x+size*4/5, y-size*26/20);
		
		poarta.in[0] = new Coordonate(x-size*4/5, y-size*35/20);
		poarta.in[1] = new Coordonate(x+size*4/5, y-size*35/20);
		
		g.drawLine(x-size*2/5, y-size*35/20, x-size*2/5, y-size*23/20);
		g.drawLine(x+size*2/5, y-size*35/20, x+size*2/5, y-size*23/20);
		
		poarta.in[2] = new Coordonate(x-size*2/5, y-size*35/20);
		poarta.in[3] = new Coordonate(x+size*2/5, y-size*35/20);
		
		
		}
		
		
		if(poarta.getNrIn() == 5)
		{
		g.drawLine(x-size*4/5, y-size*35/20, x-size*4/5, y-size*26/20);
		g.drawLine(x+size*4/5, y-size*35/20, x+size*4/5, y-size*26/20);
		
		poarta.in[0] = new Coordonate(x-size*4/5, y-size*35/20);
		poarta.in[1] = new Coordonate(x+size*4/5, y-size*35/20);
		
		g.drawLine(x, y-size*23/20, x, y-size*35/20);
		
		poarta.in[2] = new Coordonate(x, y-size*35/20);
		
		g.drawLine(x-size*2/5, y-size*35/20, x-size*2/5, y-size*23/20);
		g.drawLine(x+size*2/5, y-size*35/20, x+size*2/5, y-size*23/20);
		
		poarta.in[3] = new Coordonate(x-size*2/5, y-size*35/20);
		poarta.in[4] = new Coordonate(x+size*2/5, y-size*35/20);
		}
		
		}
		
		if(poarta.getOrientare().equals("sus"))
		{
		
		Coordonate c = poarta.getCoord();
		int size = - poarta.getSize();
		
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);
	
		
		g2d.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		g2d.drawLine(x, y+size/2, x, y+size*16/20);
		poarta.setOut(new Coordonate(x, y+size*16/20));
		
  
		
		GeneralPath path = new GeneralPath();
		path.moveTo(x-size, y-size*28/20);
		path.curveTo(x-size, y-size*28/20, x, y-size*3/4, x+size, y-size*28/20);
		g2d.draw(path);
		
		path.moveTo(x-size, y-size*28/20);
		path.curveTo(x-size, y-size*28/20, x-size*18/20, y+size*8/20, x, y+size/2);
		g2d.draw(path);
		
		
		path.moveTo(x+size, y-size*28/20);
		path.curveTo(x+size, y-size*28/20, x+size*18/20, y+size*8/20, x, y+size/2);
		g2d.draw(path);
		
		

		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			
			g2d.setFont(new Font("Arial", Font.BOLD, -size*10/12));
			Font font = g2d.getFont();
			AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(90));
			g2d.setFont(font.deriveFont(at));
			g2d.drawString(nume, x+size*12/20, y+size*18/12);
			
			
		}


		
		if(poarta.getNrIn() == 2)
		{
		g.drawLine(x-size/2, y-size*35/20, x-size/2, y-size*24/20);
		g.drawLine(x+size/2, y-size*35/20, x+size/2, y-size*24/20);
		
		poarta.in[0] = new Coordonate(x-size/2, y-size*35/20);
		poarta.in[1] = new Coordonate(x+size/2, y-size*35/20);
		
		}
		
		if(poarta.getNrIn() == 3)
		{
		g.drawLine(x-size/2, y-size*35/20, x-size/2, y-size*24/20);
		g.drawLine(x+size/2, y-size*35/20, x+size/2, y-size*24/20);
		
		poarta.in[0] = new Coordonate(x-size/2, y-size*35/20);
		poarta.in[2] = new Coordonate(x+size/2, y-size*35/20);
		
		g.drawLine(x, y-size*23/20, x, y-size*35/20);
		
		poarta.in[1] = new Coordonate(x, y-size*35/20);
		}
		
		if(poarta.getNrIn() == 4)
		{
		g.drawLine(x-size*4/5, y-size*35/20, x-size*4/5, y-size*26/20);
		g.drawLine(x+size*4/5, y-size*35/20, x+size*4/5, y-size*26/20);
		
		poarta.in[0] = new Coordonate(x-size*4/5, y-size*35/20);
		poarta.in[1] = new Coordonate(x+size*4/5, y-size*35/20);
		
		g.drawLine(x-size*2/5, y-size*35/20, x-size*2/5, y-size*23/20);
		g.drawLine(x+size*2/5, y-size*35/20, x+size*2/5, y-size*23/20);
		
		poarta.in[2] = new Coordonate(x-size*2/5, y-size*35/20);
		poarta.in[3] = new Coordonate(x+size*2/5, y-size*35/20);
		
		
		}
		
		
		if(poarta.getNrIn() == 5)
		{
		g.drawLine(x-size*4/5, y-size*35/20, x-size*4/5, y-size*26/20);
		g.drawLine(x+size*4/5, y-size*35/20, x+size*4/5, y-size*26/20);
		
		poarta.in[0] = new Coordonate(x-size*4/5, y-size*35/20);
		poarta.in[1] = new Coordonate(x+size*4/5, y-size*35/20);
		
		g.drawLine(x, y-size*23/20, x, y-size*35/20);
		
		poarta.in[2] = new Coordonate(x, y-size*35/20);
		
		g.drawLine(x-size*2/5, y-size*35/20, x-size*2/5, y-size*23/20);
		g.drawLine(x+size*2/5, y-size*35/20, x+size*2/5, y-size*23/20);
		
		poarta.in[3] = new Coordonate(x-size*2/5, y-size*35/20);
		poarta.in[4] = new Coordonate(x+size*2/5, y-size*35/20);
		}
		
		}
		
	}
	
	public static void desenareNOR(PoartaLogica poarta, Graphics g)
	{
		
		if(poarta.getOrientare().equals("dreapta") )
		{
			
		Coordonate c = poarta.getCoord();
		int size = poarta.getSize();
		
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);

		
		g2d.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		
		g2d.drawOval(x+size/2, y-size*3/20, size*6/20,  size*6/20);
		g2d.drawLine(x+size*16/20, y, x+size*22/20, y);
		poarta.setOut(new Coordonate(x+size*22/20, y));
		
	
		
		GeneralPath path = new GeneralPath();
		path.moveTo(x-size*28/20, y-size);
		path.curveTo(x-size*28/20, y-size, x-size*15/20, y, x-size*28/20, y+size);
		g2d.draw(path);
		
		
		
		path.moveTo(x-size*28/20, y-size);
		path.curveTo(x-size*28/20, y-size, x+size*8/20, y-size*18/20, x+size/2, y);
		g2d.draw(path);
		
		
		path.moveTo(x-size*28/20, y+size);
		path.curveTo(x-size*28/20, y+size, x+size*8/20, y+size*18/20, x+size/2, y);
		g2d.draw(path);
		
		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
			g2d.drawString(nume, x+size*12/20, y-size*4/12);
			
		}
		
		if(poarta.getNrIn() == 2)
		{
		g.drawLine(x-size*35/20, y-size/2, x-size*24/20, y-size/2);
		g.drawLine(x-size*35/20, y+size/2, x-size*24/20, y+size/2);
		
		poarta.in[0] = new Coordonate(x-size*35/20, y-size/2);
		poarta.in[1] = new Coordonate(x-size*35/20, y+size/2);
		
		
		}
		
		if(poarta.getNrIn() == 3)
		{
		g.drawLine(x-size*35/20, y-size/2, x-size*24/20, y-size/2);
		g.drawLine(x-size*35/20, y+size/2, x-size*24/20, y+size/2);
		
		poarta.in[0] = new Coordonate(x-size*35/20, y-size/2);
		poarta.in[2] = new Coordonate(x-size*35/20, y+size/2);
		
		g.drawLine(x-size*23/20, y, x-size*35/20, y);
		
		poarta.in[1] = new Coordonate(x-size*35/20, y);
		
		}
		
		if(poarta.getNrIn() == 4)
		{
		g.drawLine(x-size*35/20, y-size*4/5, x-size*26/20, y-size*4/5);
		g.drawLine(x-size*35/20, y+size*4/5, x-size*26/20, y+size*4/5);
		
		poarta.in[0] = new Coordonate(x-size*35/20, y-size*4/5);
		poarta.in[1] = new Coordonate(x-size*35/20, y+size*4/5);
		
		g.drawLine(x-size*35/20, y-size*2/5, x-size*23/20, y-size*2/5);
		g.drawLine(x-size*35/20, y+size*2/5, x-size*23/20, y+size*2/5);
		
		poarta.in[2] = new Coordonate(x-size*35/20, y-size*2/5);
		poarta.in[3] = new Coordonate(x-size*35/20, y+size*2/5);
		}
		
		
		if(poarta.getNrIn() == 5)
		{
		g.drawLine(x-size*35/20, y-size*4/5, x-size*26/20, y-size*4/5);
		g.drawLine(x-size*35/20, y+size*4/5, x-size*26/20, y+size*4/5);
		
		poarta.in[0] = new Coordonate(x-size*35/20, y-size*4/5);
		poarta.in[1] = new Coordonate(x-size*35/20, y+size*4/5);
		
		g.drawLine(x-size*23/20, y, x-size*35/20, y);
		
		poarta.in[2] = new Coordonate(x-size*35/20, y);
		
		g.drawLine(x-size*35/20, y-size*2/5, x-size*23/20, y-size*2/5);
		g.drawLine(x-size*35/20, y+size*2/5, x-size*23/20, y+size*2/5);
		
		poarta.in[3] = new Coordonate(x-size*35/20, y-size*2/5);
		poarta.in[4] = new Coordonate(x-size*35/20, y+size*2/5);
		}
		
		}
		
		
		if(poarta.getOrientare().equals( "stanga"))
		{
			
		Coordonate c = poarta.getCoord();
		int size = - poarta.getSize();

		
		g.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);
		
		g.drawOval(x+size*4/5, y+size*3/20, -size*6/20,  -size*6/20);
		
		g.drawLine(x+size*16/20, y, x+size*22/20, y);
		poarta.setOut(new Coordonate(x+size*22/20, y));
		

		
		GeneralPath path = new GeneralPath();
		path.moveTo(x-size*28/20, y-size);
		path.curveTo(x-size*28/20, y-size, x-size*15/20, y, x-size*28/20, y+size);
		g2d.draw(path);
		
		
		
		path.moveTo(x-size*28/20, y-size);
		path.curveTo(x-size*28/20, y-size, x+size*8/20, y-size*18/20, x+size/2, y);
		g2d.draw(path);
		
		
		path.moveTo(x-size*28/20, y+size);
		path.curveTo(x-size*28/20, y+size, x+size*8/20, y+size*18/20, x+size/2, y);
		g2d.draw(path);
		
		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
			g2d.drawString(nume, x+size*12/20, y-size*4/12);
		
		}
		
		if(poarta.getNrIn() == 2)
		{
		g.drawLine(x-size*35/20, y-size/2, x-size*24/20, y-size/2);
		g.drawLine(x-size*35/20, y+size/2, x-size*24/20, y+size/2);
		
		poarta.in[0] = new Coordonate(x-size*35/20, y-size/2);
		poarta.in[1] = new Coordonate(x-size*35/20, y+size/2);
		
		
		}
		
		if(poarta.getNrIn() == 3)
		{
		g.drawLine(x-size*35/20, y-size/2, x-size*24/20, y-size/2);
		g.drawLine(x-size*35/20, y+size/2, x-size*24/20, y+size/2);
		
		poarta.in[0] = new Coordonate(x-size*35/20, y-size/2);
		poarta.in[2] = new Coordonate(x-size*35/20, y+size/2);
		
		g.drawLine(x-size*23/20, y, x-size*35/20, y);
		
		poarta.in[1] = new Coordonate(x-size*35/20, y);
		
		}
		
		if(poarta.getNrIn() == 4)
		{
		g.drawLine(x-size*35/20, y-size*4/5, x-size*26/20, y-size*4/5);
		g.drawLine(x-size*35/20, y+size*4/5, x-size*26/20, y+size*4/5);
		
		poarta.in[0] = new Coordonate(x-size*35/20, y-size*4/5);
		poarta.in[1] = new Coordonate(x-size*35/20, y+size*4/5);
		
		g.drawLine(x-size*35/20, y-size*2/5, x-size*23/20, y-size*2/5);
		g.drawLine(x-size*35/20, y+size*2/5, x-size*23/20, y+size*2/5);
		
		poarta.in[2] = new Coordonate(x-size*35/20, y-size*2/5);
		poarta.in[3] = new Coordonate(x-size*35/20, y+size*2/5);
		}
		
		
		if(poarta.getNrIn() == 5)
		{
		g.drawLine(x-size*35/20, y-size*4/5, x-size*26/20, y-size*4/5);
		g.drawLine(x-size*35/20, y+size*4/5, x-size*26/20, y+size*4/5);
		
		poarta.in[0] = new Coordonate(x-size*35/20, y-size*4/5);
		poarta.in[1] = new Coordonate(x-size*35/20, y+size*4/5);
		
		g.drawLine(x-size*23/20, y, x-size*35/20, y);
		
		poarta.in[2] = new Coordonate(x-size*35/20, y);
		
		g.drawLine(x-size*35/20, y-size*2/5, x-size*23/20, y-size*2/5);
		g.drawLine(x-size*35/20, y+size*2/5, x-size*23/20, y+size*2/5);
		
		poarta.in[3] = new Coordonate(x-size*35/20, y-size*2/5);
		poarta.in[4] = new Coordonate(x-size*35/20, y+size*2/5);
		}
		
		}
		
		
		if(poarta.getOrientare().equals("jos") )
		{
			
		Coordonate c = poarta.getCoord();
		int size = poarta.getSize();
		
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);
	
		
		g.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		
		g.drawOval(x-size*3/20, y+size/2, size*6/20,  size*6/20);
		
		g.drawLine(x, y+size*16/20, x, y+size*22/20);
		poarta.setOut(new Coordonate(x, y+size*22/20));
	
		

		
		GeneralPath path = new GeneralPath();
		path.moveTo(x-size, y-size*28/20);
		path.curveTo(x-size, y-size*28/20, x, y-size*15/20, x+size, y-size*28/20);
		g2d.draw(path);
		
		
		
		path.moveTo(x-size, y-size*28/20);
		path.curveTo(x-size, y-size*28/20, x-size*18/20, y+size*8/20, x, y+size/2);
		g2d.draw(path);
		
		
		path.moveTo(x+size, y-size*28/20);
		path.curveTo(x+size, y-size*28/20, x+size*18/20, y+size*8/20, x, y+size/2);
		g2d.draw(path);
		

		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			
			g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
			Font font = g2d.getFont();
			AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(90));
			g2d.setFont(font.deriveFont(at));
			g2d.drawString(nume, x+size*10/12, y+size*5/20);
			
			
		}
		
		if(poarta.getNrIn() == 2)
		{
		g.drawLine(x-size/2, y-size*35/20, x-size/2, y-size*24/20);
		g.drawLine(x+size/2, y-size*35/20, x+size/2, y-size*24/20);
		
		poarta.in[0] = new Coordonate(x-size/2, y-size*35/20);
		poarta.in[1] = new Coordonate(x+size/2, y-size*35/20);
		
		}
		
		if(poarta.getNrIn() == 3)
		{
		g.drawLine(x-size/2, y-size*35/20, x-size/2, y-size*24/20);
		g.drawLine(x+size/2, y-size*35/20, x+size/2, y-size*24/20);
		
		poarta.in[0] = new Coordonate(x-size/2, y-size*35/20);
		poarta.in[2] = new Coordonate(x+size/2, y-size*35/20);
		
		g.drawLine(x, y-size*23/20, x, y-size*35/20);
		
		poarta.in[1] = new Coordonate(x, y-size*35/20);
		}
		
		if(poarta.getNrIn() == 4)
		{
		g.drawLine(x-size*4/5, y-size*35/20, x-size*4/5, y-size*26/20);
		g.drawLine(x+size*4/5, y-size*35/20, x+size*4/5, y-size*26/20);
		
		poarta.in[0] = new Coordonate(x-size*4/5, y-size*35/20);
		poarta.in[1] = new Coordonate(x+size*4/5, y-size*35/20);
		
		g.drawLine(x-size*2/5, y-size*35/20, x-size*2/5, y-size*23/20);
		g.drawLine(x+size*2/5, y-size*35/20, x+size*2/5, y-size*23/20);
		
		poarta.in[2] = new Coordonate(x-size*2/5, y-size*35/20);
		poarta.in[3] = new Coordonate(x+size*2/5, y-size*35/20);
		
		
		}
		
		
		if(poarta.getNrIn() == 5)
		{
		g.drawLine(x-size*4/5, y-size*35/20, x-size*4/5, y-size*26/20);
		g.drawLine(x+size*4/5, y-size*35/20, x+size*4/5, y-size*26/20);
		
		poarta.in[0] = new Coordonate(x-size*4/5, y-size*35/20);
		poarta.in[1] = new Coordonate(x+size*4/5, y-size*35/20);
		
		g.drawLine(x, y-size*23/20, x, y-size*35/20);
		
		poarta.in[2] = new Coordonate(x, y-size*35/20);
		
		g.drawLine(x-size*2/5, y-size*35/20, x-size*2/5, y-size*23/20);
		g.drawLine(x+size*2/5, y-size*35/20, x+size*2/5, y-size*23/20);
		
		poarta.in[3] = new Coordonate(x-size*2/5, y-size*35/20);
		poarta.in[4] = new Coordonate(x+size*2/5, y-size*35/20);
		}
		}
		
		
		
		
		
		
		if(poarta.getOrientare().equals("sus"))
		{
			
		Coordonate c = poarta.getCoord();
		int size = - poarta.getSize();
	
		
		g.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);
	
		
		
		g.drawOval(x+size*3/20, y+size*4/5, -size*6/20,  -size*6/20);
		g.drawLine(x, y+size*16/20, x, y+size*22/20);
		poarta.setOut(new Coordonate(x, y+size*22/20));
		

		
		GeneralPath path = new GeneralPath();
		path.moveTo(x-size, y-size*28/20);
		path.curveTo(x-size, y-size*28/20, x, y-size*15/20, x+size, y-size*28/20);
		g2d.draw(path);
		
		
		
		path.moveTo(x-size, y-size*28/20);
		path.curveTo(x-size, y-size*28/20, x-size*18/20, y+size*8/20, x, y+size/2);
		g2d.draw(path);
		
		
		path.moveTo(x+size, y-size*28/20);
		path.curveTo(x+size, y-size*28/20, x+size*18/20, y+size*8/20, x, y+size/2);
		g2d.draw(path);
		
		

		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			
			g2d.setFont(new Font("Arial", Font.BOLD, -size*10/12));
			Font font = g2d.getFont();
			AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(90));
			g2d.setFont(font.deriveFont(at));
			g2d.drawString(nume, x+size*12/20, y+size*18/12);
			
		}
		
		if(poarta.getNrIn() == 2)
		{
		g.drawLine(x-size/2, y-size*35/20, x-size/2, y-size*24/20);
		g.drawLine(x+size/2, y-size*35/20, x+size/2, y-size*24/20);
		
		poarta.in[0] = new Coordonate(x-size/2, y-size*35/20);
		poarta.in[1] = new Coordonate(x+size/2, y-size*35/20);
		
		}
		
		if(poarta.getNrIn() == 3)
		{
		g.drawLine(x-size/2, y-size*35/20, x-size/2, y-size*24/20);
		g.drawLine(x+size/2, y-size*35/20, x+size/2, y-size*24/20);
		
		poarta.in[0] = new Coordonate(x-size/2, y-size*35/20);
		poarta.in[2] = new Coordonate(x+size/2, y-size*35/20);
		
		g.drawLine(x, y-size*23/20, x, y-size*35/20);
		
		poarta.in[1] = new Coordonate(x, y-size*35/20);
		}
		
		if(poarta.getNrIn() == 4)
		{
		g.drawLine(x-size*4/5, y-size*35/20, x-size*4/5, y-size*26/20);
		g.drawLine(x+size*4/5, y-size*35/20, x+size*4/5, y-size*26/20);
		
		poarta.in[0] = new Coordonate(x-size*4/5, y-size*35/20);
		poarta.in[1] = new Coordonate(x+size*4/5, y-size*35/20);
		
		g.drawLine(x-size*2/5, y-size*35/20, x-size*2/5, y-size*23/20);
		g.drawLine(x+size*2/5, y-size*35/20, x+size*2/5, y-size*23/20);
		
		poarta.in[2] = new Coordonate(x-size*2/5, y-size*35/20);
		poarta.in[3] = new Coordonate(x+size*2/5, y-size*35/20);
		
		
		}
		
		
		if(poarta.getNrIn() == 5)
		{
		g.drawLine(x-size*4/5, y-size*35/20, x-size*4/5, y-size*26/20);
		g.drawLine(x+size*4/5, y-size*35/20, x+size*4/5, y-size*26/20);
		
		poarta.in[0] = new Coordonate(x-size*4/5, y-size*35/20);
		poarta.in[1] = new Coordonate(x+size*4/5, y-size*35/20);
		
		g.drawLine(x, y-size*23/20, x, y-size*35/20);
		
		poarta.in[2] = new Coordonate(x, y-size*35/20);
		
		g.drawLine(x-size*2/5, y-size*35/20, x-size*2/5, y-size*23/20);
		g.drawLine(x+size*2/5, y-size*35/20, x+size*2/5, y-size*23/20);
		
		poarta.in[3] = new Coordonate(x-size*2/5, y-size*35/20);
		poarta.in[4] = new Coordonate(x+size*2/5, y-size*35/20);
		}
		}
		
	}
	
	
	public static void desenareXOR(PoartaLogica poarta, Graphics g)
	{
		if(poarta.getOrientare().equals("dreapta") )
		{
			
		Coordonate c = poarta.getCoord();
		int size = poarta.getSize();
		
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);

		
		g2d.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		g2d.drawLine(x+size/2, y, x+size*16/20, y);
		poarta.setOut(new Coordonate(x+size*16/20, y));
		
		
   
		
		GeneralPath path = new GeneralPath();
		path.moveTo(x-size*28/20, y-size);
		path.curveTo(x-size*28/20, y-size, x-size*3/4, y, x-size*28/20, y+size);
		g2d.draw(path);
		
		path.moveTo(x-size*32/20, y-size);
		path.curveTo(x-size*32/20, y-size, x-size*19/20, y, x-size*32/20, y+size);
		g2d.draw(path);
		
		path.moveTo(x-size*28/20, y-size);
		path.curveTo(x-size*28/20, y-size, x+size*8/20, y-size*18/20, x+size/2, y);
		g2d.draw(path);
		
		
		path.moveTo(x-size*28/20, y+size);
		path.curveTo(x-size*28/20, y+size, x+size*8/20, y+size*18/20, x+size/2, y);
		g2d.draw(path);
		
		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
			g2d.drawString(nume, x+size*12/20, y-size*4/12);
			
		}
		
		
		if(poarta.getNrIn() == 2)
		{
		g.drawLine(x-size*39/20, y-size/2, x-size*28/20, y-size/2);
		g.drawLine(x-size*39/20, y+size/2, x-size*28/20, y+size/2);
		
		poarta.in[0] = new Coordonate(x-size*39/20, y-size/2);
		poarta.in[1] = new Coordonate(x-size*39/20, y+size/2);
		}
		
		if(poarta.getNrIn() == 3)
		{
		g.drawLine(x-size*39/20, y-size/2, x-size*28/20, y-size/2);
		g.drawLine(x-size*39/20, y+size/2, x-size*28/20, y+size/2);
		
		poarta.in[0] = new Coordonate(x-size*39/20, y-size/2);
		poarta.in[2] = new Coordonate(x-size*39/20, y+size/2);
		
		g.drawLine(x-size*27/20, y, x-size*38/20, y);
		
		poarta.in[1] = new Coordonate(x-size*38/20, y);
		}
		
		if(poarta.getNrIn() == 4)
		{
		g.drawLine(x-size*39/20, y-size*4/5, x-size*30/20, y-size*4/5);
		g.drawLine(x-size*39/20, y+size*4/5, x-size*30/20, y+size*4/5);
		
		poarta.in[0] = new Coordonate(x-size*39/20, y-size*4/5);
		poarta.in[1] = new Coordonate(x-size*39/20, y+size*4/5);
		
		g.drawLine(x-size*39/20, y-size*2/5, x-size*28/20, y-size*2/5);
		g.drawLine(x-size*39/20, y+size*2/5, x-size*28/20, y+size*2/5);
		
		poarta.in[2] = new Coordonate(x-size*39/20, y-size*2/5);
		poarta.in[3] = new Coordonate(x-size*39/20, y+size*2/5);
		}
		
		if(poarta.getNrIn() == 5)
		{
		g.drawLine(x-size*38/20, y-size*4/5, x-size*30/20, y-size*4/5);
		g.drawLine(x-size*38/20, y+size*4/5, x-size*30/20, y+size*4/5);
		
		poarta.in[0] = new Coordonate(x-size*39/20, y-size*4/5);
		poarta.in[1] = new Coordonate(x-size*39/20, y+size*4/5);
		
		g.drawLine(x-size*27/20, y, x-size*38/20, y);
		
		poarta.in[2] = new Coordonate(x-size*38/20, y);
		
		g.drawLine(x-size*38/20, y-size*2/5, x-size*28/20, y-size*2/5);
		g.drawLine(x-size*38/20, y+size*2/5, x-size*28/20, y+size*2/5);
		
		poarta.in[3] = new Coordonate(x-size*39/20, y-size*2/5);
		poarta.in[4] = new Coordonate(x-size*39/20, y+size*2/5);
		}
		
		
		}
		
		
		if(poarta.getOrientare().equals("stanga") )
		{
			
		Coordonate c = poarta.getCoord();
		int size = - poarta.getSize();
		
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);

	
		
		g2d.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		g2d.drawLine(x+size/2, y, x+size*16/20, y);
		poarta.setOut(new Coordonate(x+size*16/20, y));
		
		
		
		GeneralPath path = new GeneralPath();
		path.moveTo(x-size*28/20, y-size);
		path.curveTo(x-size*28/20, y-size, x-size*3/4, y, x-size*28/20, y+size);
		g2d.draw(path);
		
		path.moveTo(x-size*32/20, y-size);
		path.curveTo(x-size*32/20, y-size, x-size*19/20, y, x-size*32/20, y+size);
		g2d.draw(path);
		
		path.moveTo(x-size*28/20, y-size);
		path.curveTo(x-size*28/20, y-size, x+size*8/20, y-size*18/20, x+size/2, y);
		g2d.draw(path);
		
		
		path.moveTo(x-size*28/20, y+size);
		path.curveTo(x-size*28/20, y+size, x+size*8/20, y+size*18/20, x+size/2, y);
		g2d.draw(path);
		
		
		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			
			g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
			
			g2d.drawString(nume, x+size*15/20, y-size*4/12);
			
			
		}
		
		if(poarta.getNrIn() == 2)
		{
		g.drawLine(x-size*39/20, y-size/2, x-size*28/20, y-size/2);
		g.drawLine(x-size*39/20, y+size/2, x-size*28/20, y+size/2);
		
		poarta.in[0] = new Coordonate(x-size*39/20, y-size/2);
		poarta.in[1] = new Coordonate(x-size*39/20, y+size/2);
		}
		
		if(poarta.getNrIn() == 3)
		{
		g.drawLine(x-size*39/20, y-size/2, x-size*28/20, y-size/2);
		g.drawLine(x-size*39/20, y+size/2, x-size*28/20, y+size/2);
		
		poarta.in[0] = new Coordonate(x-size*39/20, y-size/2);
		poarta.in[2] = new Coordonate(x-size*39/20, y+size/2);
		
		g.drawLine(x-size*27/20, y, x-size*38/20, y);
		
		poarta.in[1] = new Coordonate(x-size*38/20, y);
		}
		
		if(poarta.getNrIn() == 4)
		{
		g.drawLine(x-size*39/20, y-size*4/5, x-size*30/20, y-size*4/5);
		g.drawLine(x-size*39/20, y+size*4/5, x-size*30/20, y+size*4/5);
		
		poarta.in[0] = new Coordonate(x-size*39/20, y-size*4/5);
		poarta.in[1] = new Coordonate(x-size*39/20, y+size*4/5);
		
		g.drawLine(x-size*39/20, y-size*2/5, x-size*28/20, y-size*2/5);
		g.drawLine(x-size*39/20, y+size*2/5, x-size*28/20, y+size*2/5);
		
		poarta.in[2] = new Coordonate(x-size*39/20, y-size*2/5);
		poarta.in[3] = new Coordonate(x-size*39/20, y+size*2/5);
		}
		
		if(poarta.getNrIn() == 5)
		{
		g.drawLine(x-size*38/20, y-size*4/5, x-size*30/20, y-size*4/5);
		g.drawLine(x-size*38/20, y+size*4/5, x-size*30/20, y+size*4/5);
		
		poarta.in[0] = new Coordonate(x-size*39/20, y-size*4/5);
		poarta.in[1] = new Coordonate(x-size*39/20, y+size*4/5);
		
		g.drawLine(x-size*27/20, y, x-size*38/20, y);
		
		poarta.in[2] = new Coordonate(x-size*38/20, y);
		
		g.drawLine(x-size*38/20, y-size*2/5, x-size*28/20, y-size*2/5);
		g.drawLine(x-size*38/20, y+size*2/5, x-size*28/20, y+size*2/5);
		
		poarta.in[3] = new Coordonate(x-size*39/20, y-size*2/5);
		poarta.in[4] = new Coordonate(x-size*39/20, y+size*2/5);
		}
		}
		
		
		if(poarta.getOrientare().equals("jos"))
		{
			
		Coordonate c = poarta.getCoord();
		int size = poarta.getSize();

		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);
		
		g.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		g.drawLine(x, y+size/2, x, y+size*16/20);
		poarta.setOut(new Coordonate(x, y+size*16/20));
		
		

		
		GeneralPath path = new GeneralPath();
		path.moveTo(x-size, y-size*28/20);
		path.curveTo(x-size, y-size*28/20, x, y-size*3/4, x+size, y-size*28/20);
		g2d.draw(path);
		
		path.moveTo(x-size, y-size*32/20);
		path.curveTo(x-size, y-size*32/20, x, y-size*19/20, x+size, y-size*32/20);
		g2d.draw(path);
		
		path.moveTo(x-size, y-size*28/20);
		path.curveTo(x-size, y-size*28/20, x-size*18/20, y+size*8/20, x, y+size/2);
		g2d.draw(path);
		
		
		path.moveTo(x+size, y-size*28/20);
		path.curveTo(x+size, y-size*28/20, x+size*18/20, y+size*8/20, x, y+size/2);
		g2d.draw(path);
		
		if(poarta.getNrIn() == 2)
		{
		g.drawLine(x-size/2, y-size*39/20, x-size/2, y-size*28/20);
		g.drawLine(x+size/2, y-size*39/20, x+size/2, y-size*28/20);
		
		poarta.in[0] = new Coordonate(x-size/2, y-size*39/20);
		poarta.in[1] = new Coordonate(x+size/2, y-size*39/20);
		
		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			
			g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
			Font font = g2d.getFont();
			AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(90));
			g2d.setFont(font.deriveFont(at));
			g2d.drawString(nume, x+size*10/12, y+size*5/20);
			
			
		}
		
		}
		
		if(poarta.getNrIn() == 3)
		{
		g.drawLine(x-size/2, y-size*39/20, x-size/2, y-size*28/20);
		g.drawLine(x+size/2, y-size*39/20, x+size/2, y-size*28/20);
		
		poarta.in[0] = new Coordonate(x-size/2, y-size*39/20);
		poarta.in[2] = new Coordonate(x+size/2, y-size*39/20);
		
		g.drawLine(x, y-size*27/20, x, y-size*38/20);
		poarta.in[1] = new Coordonate(x, y-size*38/20);
		
		}
		
		if(poarta.getNrIn() == 4)
		{
		g.drawLine(x-size*4/5, y-size*39/20, x-size*4/5, y-size*30/20);
		g.drawLine(x+size*4/5, y-size*39/20, x+size*4/5, y-size*30/20);
		
		poarta.in[0] = new Coordonate(x-size*4/5, y-size*39/20);
		poarta.in[1] = new Coordonate(x+size*4/5, y-size*39/20);
		
		
		g.drawLine(x-size*2/5, y-size*39/20, x-size*2/5, y-size*28/20);
		g.drawLine(x+size*2/5, y-size*39/20, x+size*2/5, y-size*28/20);
		
		poarta.in[2] = new Coordonate(x-size*2/5, y-size*39/20);
		poarta.in[3] = new Coordonate(x+size*2/5, y-size*39/20);
		
		}
		
		if(poarta.getNrIn() == 5)
		{
		g.drawLine(x-size*4/5, y-size*39/20, x-size*4/5, y-size*30/20);
		g.drawLine(x+size*4/5, y-size*39/20, x+size*4/5, y-size*30/20);
		
		poarta.in[0] = new Coordonate(x-size*4/5, y-size*39/20);
		poarta.in[1] = new Coordonate(x+size*4/5, y-size*39/20);
		
		g.drawLine(x, y-size*27/20, x, y-size*38/20);
		poarta.in[2] = new Coordonate(x, y-size*38/20);
		
		g.drawLine(x-size*2/5, y-size*39/20, x-size*2/5, y-size*28/20);
		g.drawLine(x+size*2/5, y-size*39/20, x+size*2/5, y-size*28/20);
		
		poarta.in[3] = new Coordonate(x-size*2/5, y-size*39/20);
		poarta.in[4] = new Coordonate(x+size*2/5, y-size*39/20);
		}
		
		}
		
		
		if(poarta.getOrientare().equals("sus") )
		{
			
		Coordonate c = poarta.getCoord();
		int size = - poarta.getSize();
		

		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);

		
		g2d.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		g2d.drawLine(x, y+size/2, x, y+size*16/20);
		poarta.setOut(new Coordonate(x, y+size*16/20));
		
		

		
		GeneralPath path = new GeneralPath();
		path.moveTo(x-size, y-size*28/20);
		path.curveTo(x-size, y-size*28/20, x, y-size*3/4, x+size, y-size*28/20);
		g2d.draw(path);
		
		path.moveTo(x-size, y-size*32/20);
		path.curveTo(x-size, y-size*32/20, x, y-size*19/20, x+size, y-size*32/20);
		g2d.draw(path);
		
		path.moveTo(x-size, y-size*28/20);
		path.curveTo(x-size, y-size*28/20, x-size*18/20, y+size*8/20, x, y+size/2);
		g2d.draw(path);
		
		
		path.moveTo(x+size, y-size*28/20);
		path.curveTo(x+size, y-size*28/20, x+size*18/20, y+size*8/20, x, y+size/2);
		g2d.draw(path);
		

		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			
			g2d.setFont(new Font("Arial", Font.BOLD, -size*10/12));
			Font font = g2d.getFont();
			AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(90));
			g2d.setFont(font.deriveFont(at));
			g2d.drawString(nume, x+size*12/20, y+size*18/12);
			
			
		}
		
		if(poarta.getNrIn() == 2)
		{
		g.drawLine(x-size/2, y-size*39/20, x-size/2, y-size*28/20);
		g.drawLine(x+size/2, y-size*39/20, x+size/2, y-size*28/20);
		
		poarta.in[0] = new Coordonate(x-size/2, y-size*39/20);
		poarta.in[1] = new Coordonate(x+size/2, y-size*39/20);
		
		}
		
		if(poarta.getNrIn() == 3)
		{
		g.drawLine(x-size/2, y-size*39/20, x-size/2, y-size*28/20);
		g.drawLine(x+size/2, y-size*39/20, x+size/2, y-size*28/20);
		
		poarta.in[0] = new Coordonate(x-size/2, y-size*39/20);
		poarta.in[2] = new Coordonate(x+size/2, y-size*39/20);
		
		g.drawLine(x, y-size*27/20, x, y-size*38/20);
		poarta.in[1] = new Coordonate(x, y-size*38/20);
		
		}
		
		if(poarta.getNrIn() == 4)
		{
		g.drawLine(x-size*4/5, y-size*39/20, x-size*4/5, y-size*30/20);
		g.drawLine(x+size*4/5, y-size*39/20, x+size*4/5, y-size*30/20);
		
		poarta.in[0] = new Coordonate(x-size*4/5, y-size*39/20);
		poarta.in[1] = new Coordonate(x+size*4/5, y-size*39/20);
		
		
		g.drawLine(x-size*2/5, y-size*39/20, x-size*2/5, y-size*28/20);
		g.drawLine(x+size*2/5, y-size*39/20, x+size*2/5, y-size*28/20);
		
		poarta.in[2] = new Coordonate(x-size*2/5, y-size*39/20);
		poarta.in[3] = new Coordonate(x+size*2/5, y-size*39/20);
		
		}
		
		if(poarta.getNrIn() == 5)
		{
		g.drawLine(x-size*4/5, y-size*39/20, x-size*4/5, y-size*30/20);
		g.drawLine(x+size*4/5, y-size*39/20, x+size*4/5, y-size*30/20);
		
		poarta.in[0] = new Coordonate(x-size*4/5, y-size*39/20);
		poarta.in[1] = new Coordonate(x+size*4/5, y-size*39/20);
		
		g.drawLine(x, y-size*27/20, x, y-size*38/20);
		poarta.in[2] = new Coordonate(x, y-size*38/20);
		
		g.drawLine(x-size*2/5, y-size*39/20, x-size*2/5, y-size*28/20);
		g.drawLine(x+size*2/5, y-size*39/20, x+size*2/5, y-size*28/20);
		
		poarta.in[3] = new Coordonate(x-size*2/5, y-size*39/20);
		poarta.in[4] = new Coordonate(x+size*2/5, y-size*39/20);
		}
		}
		
		
		
		
		
		
		
	}
	
	
	
	public static void desenareNXOR(PoartaLogica poarta, Graphics g)
	{
		if(poarta.getOrientare().equals("dreapta"))
		{
		Coordonate c = poarta.getCoord();
		int size = poarta.getSize();
		
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);
	
		
		
		g2d.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		
		g2d.drawOval(x+size/2, y-size*3/20, size*6/20,  size*6/20);
		
		g.drawLine(x+size*16/20, y, x+size*22/20, y);
		poarta.setOut(new Coordonate(x+size*22/20, y));
		
		
		GeneralPath path = new GeneralPath();
		path.moveTo(x-size*28/20, y-size);
		path.curveTo(x-size*28/20, y-size, x-size*15/20, y, x-size*28/20, y+size);
		g2d.draw(path);
		
		path.moveTo(x-size*32/20, y-size);
		path.curveTo(x-size*32/20, y-size, x-size*19/20, y, x-size*32/20, y+size);
		g2d.draw(path);
		
		
		
		path.moveTo(x-size*28/20, y-size);
		path.curveTo(x-size*28/20, y-size, x+size*8/20, y-size*18/20, x+size/2, y);
		g2d.draw(path);
		
		
		path.moveTo(x-size*28/20, y+size);
		path.curveTo(x-size*28/20, y+size, x+size*8/20, y+size*18/20, x+size/2, y);
		g2d.draw(path);
		
		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
			g2d.drawString(nume, x+size*12/20, y-size*4/12);
			
		}
		
		if(poarta.getNrIn() == 2)
		{
		g.drawLine(x-size*39/20, y-size/2, x-size*28/20, y-size/2);
		g.drawLine(x-size*39/20, y+size/2, x-size*28/20, y+size/2);
		
		poarta.in[0] = new Coordonate(x-size*39/20, y-size/2);
		poarta.in[1] = new Coordonate(x-size*39/20, y+size/2);
		}
		
		if(poarta.getNrIn() == 3)
		{
		g.drawLine(x-size*39/20, y-size/2, x-size*28/20, y-size/2);
		g.drawLine(x-size*39/20, y+size/2, x-size*28/20, y+size/2);
		
		poarta.in[0] = new Coordonate(x-size*39/20, y-size/2);
		poarta.in[2] = new Coordonate(x-size*39/20, y+size/2);
		
		g.drawLine(x-size*27/20, y, x-size*38/20, y);
		
		poarta.in[1] = new Coordonate(x-size*38/20, y);
		}
		
		if(poarta.getNrIn() == 4)
		{
		g.drawLine(x-size*39/20, y-size*4/5, x-size*30/20, y-size*4/5);
		g.drawLine(x-size*39/20, y+size*4/5, x-size*30/20, y+size*4/5);
		
		poarta.in[0] = new Coordonate(x-size*39/20, y-size*4/5);
		poarta.in[1] = new Coordonate(x-size*39/20, y+size*4/5);
		
		g.drawLine(x-size*39/20, y-size*2/5, x-size*28/20, y-size*2/5);
		g.drawLine(x-size*39/20, y+size*2/5, x-size*28/20, y+size*2/5);
		
		poarta.in[2] = new Coordonate(x-size*39/20, y-size*2/5);
		poarta.in[3] = new Coordonate(x-size*39/20, y+size*2/5);
		}
		
		if(poarta.getNrIn() == 5)
		{
		g.drawLine(x-size*38/20, y-size*4/5, x-size*30/20, y-size*4/5);
		g.drawLine(x-size*38/20, y+size*4/5, x-size*30/20, y+size*4/5);
		
		poarta.in[0] = new Coordonate(x-size*39/20, y-size*4/5);
		poarta.in[1] = new Coordonate(x-size*39/20, y+size*4/5);
		
		g.drawLine(x-size*27/20, y, x-size*38/20, y);
		
		poarta.in[2] = new Coordonate(x-size*38/20, y);
		
		g.drawLine(x-size*38/20, y-size*2/5, x-size*28/20, y-size*2/5);
		g.drawLine(x-size*38/20, y+size*2/5, x-size*28/20, y+size*2/5);
		
		poarta.in[3] = new Coordonate(x-size*39/20, y-size*2/5);
		poarta.in[4] = new Coordonate(x-size*39/20, y+size*2/5);
		}
		
		}
		
		
		if(poarta.getOrientare().equals("stanga") )
		{
		Coordonate c = poarta.getCoord();
		int size = - poarta.getSize();
	
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);
	
		
		
		g.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		
		g.drawOval(x+size*16/20, y+size*3/20, -size*6/20,  -size*6/20);
		g.drawLine(x+size*16/20, y, x+size*22/20, y);
		poarta.setOut(new Coordonate(x+size*22/20, y));
		
		
	
		
		GeneralPath path = new GeneralPath();
		path.moveTo(x-size*28/20, y-size);
		path.curveTo(x-size*28/20, y-size, x-size*15/20, y, x-size*28/20, y+size);
		g2d.draw(path);
		
		path.moveTo(x-size*32/20, y-size);
		path.curveTo(x-size*32/20, y-size, x-size*19/20, y, x-size*32/20, y+size);
		g2d.draw(path);
		
		
		
		path.moveTo(x-size*28/20, y-size);
		path.curveTo(x-size*28/20, y-size, x+size*8/20, y-size*18/20, x+size/2, y);
		g2d.draw(path);
		
		
		path.moveTo(x-size*28/20, y+size);
		path.curveTo(x-size*28/20, y+size, x+size*8/20, y+size*18/20, x+size/2, y);
		g2d.draw(path);
		

		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			
			g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
			
			g2d.drawString(nume, x+size*15/20, y-size*4/12);
			
			
		}

		
		if(poarta.getNrIn() == 2)
		{
		g.drawLine(x-size*39/20, y-size/2, x-size*28/20, y-size/2);
		g.drawLine(x-size*39/20, y+size/2, x-size*28/20, y+size/2);
		
		poarta.in[0] = new Coordonate(x-size*39/20, y-size/2);
		poarta.in[1] = new Coordonate(x-size*39/20, y+size/2);
		}
		
		if(poarta.getNrIn() == 3)
		{
		g.drawLine(x-size*39/20, y-size/2, x-size*28/20, y-size/2);
		g.drawLine(x-size*39/20, y+size/2, x-size*28/20, y+size/2);
		
		poarta.in[0] = new Coordonate(x-size*39/20, y-size/2);
		poarta.in[2] = new Coordonate(x-size*39/20, y+size/2);
		
		g.drawLine(x-size*27/20, y, x-size*38/20, y);
		
		poarta.in[1] = new Coordonate(x-size*38/20, y);
		}
		
		if(poarta.getNrIn() == 4)
		{
		g.drawLine(x-size*39/20, y-size*4/5, x-size*30/20, y-size*4/5);
		g.drawLine(x-size*39/20, y+size*4/5, x-size*30/20, y+size*4/5);
		
		poarta.in[0] = new Coordonate(x-size*39/20, y-size*4/5);
		poarta.in[1] = new Coordonate(x-size*39/20, y+size*4/5);
		
		g.drawLine(x-size*39/20, y-size*2/5, x-size*28/20, y-size*2/5);
		g.drawLine(x-size*39/20, y+size*2/5, x-size*28/20, y+size*2/5);
		
		poarta.in[2] = new Coordonate(x-size*39/20, y-size*2/5);
		poarta.in[3] = new Coordonate(x-size*39/20, y+size*2/5);
		}
		
		if(poarta.getNrIn() == 5)
		{
		g.drawLine(x-size*38/20, y-size*4/5, x-size*30/20, y-size*4/5);
		g.drawLine(x-size*38/20, y+size*4/5, x-size*30/20, y+size*4/5);
		
		poarta.in[0] = new Coordonate(x-size*39/20, y-size*4/5);
		poarta.in[1] = new Coordonate(x-size*39/20, y+size*4/5);
		
		g.drawLine(x-size*27/20, y, x-size*38/20, y);
		
		poarta.in[2] = new Coordonate(x-size*38/20, y);
		
		g.drawLine(x-size*38/20, y-size*2/5, x-size*28/20, y-size*2/5);
		g.drawLine(x-size*38/20, y+size*2/5, x-size*28/20, y+size*2/5);
		
		poarta.in[3] = new Coordonate(x-size*39/20, y-size*2/5);
		poarta.in[4] = new Coordonate(x-size*39/20, y+size*2/5);
		}
		
		}
		
		
		if(poarta.getOrientare().equals("jos") )
		{
		Coordonate c = poarta.getCoord();
		int size = poarta.getSize();
		
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);
	
		
		
		
		g.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		
		g.drawOval(x-size*3/20, y+size/2, size*6/20,  size*6/20);
		g.drawLine(x, y+size*16/20, x, y+size*22/20);
		poarta.setOut(new Coordonate(x, y+size*22/20));
		

		GeneralPath path = new GeneralPath();
		path.moveTo(x-size, y-size*28/20);
		path.curveTo(x-size, y-size*28/20, x, y-size*15/20, x+size, y-size*28/20);
		g2d.draw(path);
		
		path.moveTo(x-size, y-size*32/20);
		path.curveTo(x-size, y-size*32/20, x, y-size*19/20, x+size, y-size*32/20);
		g2d.draw(path);
		
		
		
		path.moveTo(x-size, y-size*28/20);
		path.curveTo(x-size, y-size*28/20, x-size*18/20, y+size*8/20, x, y+size/2);
		g2d.draw(path);
		
		
		path.moveTo(x+size, y-size*28/20);
		path.curveTo(x+size, y-size*28/20, x+size*18/20, y+size*8/20, x, y+size/2);
		g2d.draw(path);
		
		

		if(poarta.getNume()!=null)
			{
				String nume = poarta.getNume();
				if(poarta.getValue()==0 || poarta.getValue() == 1)
				{
					nume = nume+ "="+String.valueOf(poarta.getValue());
				}
				
				g2d.setFont(new Font("Arial", Font.BOLD, size*10/12));
				Font font = g2d.getFont();
				AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(90));
				g2d.setFont(font.deriveFont(at));
				g2d.drawString(nume, x+size*10/12, y+size*5/20);
				
				
			}
		
		if(poarta.getNrIn() == 2)
		{
		g.drawLine(x-size/2, y-size*39/20, x-size/2, y-size*28/20);
		g.drawLine(x+size/2, y-size*39/20, x+size/2, y-size*28/20);
		
		poarta.in[0] = new Coordonate(x-size/2, y-size*39/20);
		poarta.in[1] = new Coordonate(x+size/2, y-size*39/20);
		
		}
		
		if(poarta.getNrIn() == 3)
		{
		g.drawLine(x-size/2, y-size*39/20, x-size/2, y-size*28/20);
		g.drawLine(x+size/2, y-size*39/20, x+size/2, y-size*28/20);
		
		poarta.in[0] = new Coordonate(x-size/2, y-size*39/20);
		poarta.in[2] = new Coordonate(x+size/2, y-size*39/20);
		
		g.drawLine(x, y-size*27/20, x, y-size*38/20);
		poarta.in[1] = new Coordonate(x, y-size*38/20);
		
		}
		
		if(poarta.getNrIn() == 4)
		{
		g.drawLine(x-size*4/5, y-size*39/20, x-size*4/5, y-size*30/20);
		g.drawLine(x+size*4/5, y-size*39/20, x+size*4/5, y-size*30/20);
		
		poarta.in[0] = new Coordonate(x-size*4/5, y-size*39/20);
		poarta.in[1] = new Coordonate(x+size*4/5, y-size*39/20);
		
		
		g.drawLine(x-size*2/5, y-size*39/20, x-size*2/5, y-size*28/20);
		g.drawLine(x+size*2/5, y-size*39/20, x+size*2/5, y-size*28/20);
		
		poarta.in[2] = new Coordonate(x-size*2/5, y-size*39/20);
		poarta.in[3] = new Coordonate(x+size*2/5, y-size*39/20);
		
		}
		
		if(poarta.getNrIn() == 5)
		{
		g.drawLine(x-size*4/5, y-size*39/20, x-size*4/5, y-size*30/20);
		g.drawLine(x+size*4/5, y-size*39/20, x+size*4/5, y-size*30/20);
		
		poarta.in[0] = new Coordonate(x-size*4/5, y-size*39/20);
		poarta.in[1] = new Coordonate(x+size*4/5, y-size*39/20);
		
		g.drawLine(x, y-size*27/20, x, y-size*38/20);
		poarta.in[2] = new Coordonate(x, y-size*38/20);
		
		g.drawLine(x-size*2/5, y-size*39/20, x-size*2/5, y-size*28/20);
		g.drawLine(x+size*2/5, y-size*39/20, x+size*2/5, y-size*28/20);
		
		poarta.in[3] = new Coordonate(x-size*2/5, y-size*39/20);
		poarta.in[4] = new Coordonate(x+size*2/5, y-size*39/20);
		}
		
		}
		
		
		if(poarta.getOrientare().equals("sus") )
		{
		Coordonate c = poarta.getCoord();
		int size = - poarta.getSize();
		
		Graphics2D g2d = (Graphics2D) g;
		Stroke drawingStroke = new BasicStroke(2);
		g2d.setStroke(drawingStroke);
	

		
		
		g.setColor(poarta.getCuloare());
		int x = c.getX();
		int y = c.getY();
		
		
		g.drawOval(x+size*3/20, y+size*16/20, -size*6/20,  -size*6/20);
		g.drawLine(x, y+size*16/20, x, y+size*22/20);
		poarta.setOut(new Coordonate(x, y+size*22/20));
		
		

		
		GeneralPath path = new GeneralPath();
		path.moveTo(x-size, y-size*28/20);
		path.curveTo(x-size, y-size*28/20, x, y-size*15/20, x+size, y-size*28/20);
		g2d.draw(path);
		
		path.moveTo(x-size, y-size*32/20);
		path.curveTo(x-size, y-size*32/20, x, y-size*19/20, x+size, y-size*32/20);
		g2d.draw(path);
		
		
		
		path.moveTo(x-size, y-size*28/20);
		path.curveTo(x-size, y-size*28/20, x-size*18/20, y+size*8/20, x, y+size/2);
		g2d.draw(path);
		
		
		path.moveTo(x+size, y-size*28/20);
		path.curveTo(x+size, y-size*28/20, x+size*18/20, y+size*8/20, x, y+size/2);
		g2d.draw(path);
		

		if(poarta.getNume()!=null)
		{
			String nume = poarta.getNume();
			if(poarta.getValue()==0 || poarta.getValue() == 1)
			{
				nume = nume+ "="+String.valueOf(poarta.getValue());
			}
			
			g2d.setFont(new Font("Arial", Font.BOLD, -size*10/12));
			Font font = g2d.getFont();
			AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(90));
			g2d.setFont(font.deriveFont(at));
			g2d.drawString(nume, x+size*12/20, y+size*18/12);
			
			
		}

		
		if(poarta.getNrIn() == 2)
		{
		g.drawLine(x-size/2, y-size*39/20, x-size/2, y-size*28/20);
		g.drawLine(x+size/2, y-size*39/20, x+size/2, y-size*28/20);
		
		poarta.in[0] = new Coordonate(x-size/2, y-size*39/20);
		poarta.in[1] = new Coordonate(x+size/2, y-size*39/20);
		
		}
		
		if(poarta.getNrIn() == 3)
		{
		g.drawLine(x-size/2, y-size*39/20, x-size/2, y-size*28/20);
		g.drawLine(x+size/2, y-size*39/20, x+size/2, y-size*28/20);
		
		poarta.in[0] = new Coordonate(x-size/2, y-size*39/20);
		poarta.in[2] = new Coordonate(x+size/2, y-size*39/20);
		
		g.drawLine(x, y-size*27/20, x, y-size*38/20);
		poarta.in[1] = new Coordonate(x, y-size*38/20);
		
		}
		
		if(poarta.getNrIn() == 4)
		{
		g.drawLine(x-size*4/5, y-size*39/20, x-size*4/5, y-size*30/20);
		g.drawLine(x+size*4/5, y-size*39/20, x+size*4/5, y-size*30/20);
		
		poarta.in[0] = new Coordonate(x-size*4/5, y-size*39/20);
		poarta.in[1] = new Coordonate(x+size*4/5, y-size*39/20);
		
		
		g.drawLine(x-size*2/5, y-size*39/20, x-size*2/5, y-size*28/20);
		g.drawLine(x+size*2/5, y-size*39/20, x+size*2/5, y-size*28/20);
		
		poarta.in[2] = new Coordonate(x-size*2/5, y-size*39/20);
		poarta.in[3] = new Coordonate(x+size*2/5, y-size*39/20);
		
		}
		
		if(poarta.getNrIn() == 5)
		{
		
		g.drawLine(x-size*4/5, y-size*39/20, x-size*4/5, y-size*30/20);
		g.drawLine(x+size*4/5, y-size*39/20, x+size*4/5, y-size*30/20);
		
		poarta.in[0] = new Coordonate(x-size*4/5, y-size*39/20);
		poarta.in[1] = new Coordonate(x+size*4/5, y-size*39/20);
		
		g.drawLine(x, y-size*27/20, x, y-size*38/20);
		poarta.in[2] = new Coordonate(x, y-size*38/20);
		
		g.drawLine(x-size*2/5, y-size*39/20, x-size*2/5, y-size*28/20);
		g.drawLine(x+size*2/5, y-size*39/20, x+size*2/5, y-size*28/20);
		
		poarta.in[3] = new Coordonate(x-size*2/5, y-size*39/20);
		poarta.in[4] = new Coordonate(x+size*2/5, y-size*39/20);
		}
		}
	}
	
		public static void desenareConexiune(Coordonate c1, Coordonate c2, Graphics g, Conexiune con)
		{
		
	
			g.setColor(con.getCuloare());
			Line2D line1 = new Line2D.Double (c1.x,c1.y,c2.x, c1.y);
			Line2D line2 = new Line2D.Double (c2.x, c1.y, c2.x, c2.y);
			Stroke drawingStroke = new BasicStroke(2);
			
			Graphics2D graph = (Graphics2D)g;
			  graph.setStroke(drawingStroke);
			  graph.setPaint(con.getCuloare());
			  graph.draw(line1);
			  
			  graph.draw(line2);
			  
			  con.setLine1(line1);
			  con.setLine2(line2);
		
		
	
		}
		
		public static void desenareNod(Nod nod, Graphics g)
		{
			g.setColor(nod.getCuloare());
			g.fillOval(nod.getCoord().getX()-4, nod.getCoord().getY()-4, 8, 8);
		
			
		}
	
	
	
}
