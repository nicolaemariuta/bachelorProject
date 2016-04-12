package mainPack;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.Collection;
import java.util.Stack;

import claseUtile.Coordonate;
import claseUtile.FormaGeometrica;

public class SuprafataDesenare extends Canvas implements MouseListener, MouseMotionListener,KeyListener
{
	FereastraPrincipala fp;
	Dimension dim = new Dimension(435,353);

	int x1;
	int y1;
	
	int x2;
	int y2;
	
	int x3;
	int y3;
	
	int contor = 0;
	
	Stack<Integer> nx = new Stack();
	Stack<Integer> ny = new Stack();
	
	Stack<FormaGeometrica> stack = new Stack();

	SuprafataDesenare(FereastraPrincipala fp)
	{

		this.fp = fp;
		this.setSize(dim);
		this.setBackground(Color.white);
		stack.clear();
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
	}
	
	
	public void paint (Graphics g)
	{
		g.setPaintMode();
	
	
		for(int i = 0; i< stack.size(); i++)
		{
			
			FormaGeometrica fg = stack.get(i);
			if (fg.forma == "string")
			{
				g.setColor(fg.culoare);
				g.setFont(fg.font);
				g.drawString(fg.drawString, fg.x1, fg.y1);
			
			}
			
			if(fg.forma.equals("rectangle") )
			{
				g.setColor(fg.culoare);
				g.drawRect(fg.x1, fg.y1, 
						Coordonate.distanta(new Coordonate(fg.x1,fg.y1),new Coordonate(fg.x2,fg.y2)),
						Coordonate.distanta(new Coordonate(fg.x1,fg.y1), new Coordonate(fg.x3,fg.y3)));
			}
			
			if(fg.forma.equals("Rounded Rectangle") )
			{
				g.setColor(fg.culoare);
				g.drawRoundRect(fg.x1, fg.y1, 
						Coordonate.distanta(new Coordonate(fg.x1,fg.y1), new Coordonate(fg.x2,fg.y2)),
						Coordonate.distanta(new Coordonate(fg.x1,fg.y1), new Coordonate(fg.x3,fg.y3)),
						Coordonate.distanta(new Coordonate(fg.x1,fg.y1), new Coordonate(fg.x2,fg.y2))/7, 
						Coordonate.distanta(new Coordonate(fg.x1,fg.y1), new Coordonate(fg.x2,fg.y2))/7);
			}
			
			if(fg.forma == "oval")
			{
				g.setColor(fg.culoare);
				g.drawOval(fg.x1, fg.y1, Coordonate.distanta(new Coordonate(fg.x1,fg.y1), new Coordonate(fg.x2,fg.y2)),
						Coordonate.distanta(new Coordonate(fg.x1,fg.y1), new Coordonate(fg.x3,fg.y3)));
				
			}
			
			if(fg.forma == "line")
			{
				g.setColor(fg.culoare);
				g.drawLine(fg.x1, fg.y1, fg.x2, fg.y2);
			}
			
			if(fg.forma == "circle")
			{
				g.setColor(fg.culoare);
				g.drawOval(fg.x1, fg.y1, Coordonate.distanta(new Coordonate(fg.x1,fg.y1), new Coordonate(fg.x2,fg.y2)),
						Coordonate.distanta(new Coordonate(fg.x1,fg.y1), new Coordonate(fg.x2,fg.y2)));
				
			}
			
			if(fg.forma == "square")
			{
				g.setColor(fg.culoare);
				g.drawRect(fg.x1, fg.y1, Coordonate.distanta(new Coordonate(fg.x1,fg.y1), new Coordonate(fg.x2,fg.y2)),
						Coordonate.distanta(new Coordonate(fg.x1,fg.y1), new Coordonate(fg.x2,fg.y2)));
				
			}
			
			if(fg.forma == "pencil")
			{
				g.setColor(fg.culoare);
				g.drawLine(fg.x1, fg.y1, fg.x2, fg.y2);
				
			}
			
			if(fg.forma == "eraser")
			{
				
				g.setColor(this.getBackground());
				g.fillRect(fg.x1, fg.y1, 8, 8);
			}
			
			
			if(fg.forma == "polygon")
			{
				g.setColor(fg.culoare);
				g.drawPolygon(fg.x,fg.y, fg.size);
				
			}
			
			if(fg.forma == "Polyline")
			{
				g.setColor(fg.culoare);
				g.drawPolyline(fg.x,fg.y, fg.size);
				
			}
		}
		
		
	}
	
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}	
	public void mouseExited(MouseEvent e){}


	public void mousePressed(MouseEvent e)
	{
		
		if (fp.forma == "string")
		{
			x1 = e.getX();
			y1 = e.getY();
			stack.add(new FormaGeometrica(fp.forma, x1, y1, x2, y2, x3, y3, fp.drawString, fp.culoare,fp.font));
			repaint();
			fp.forma = "pencil";
	
		}
		
		
		if (fp.forma == "eraser")
		{
			x1 = e.getX();
			y1 = e.getY();
			stack.add(new FormaGeometrica(fp.forma, x1, y1, x2, y2, x3, y3, fp.drawString, fp.culoare,fp.font));
		}
		
		
		if(fp.forma== "pencil")
		{
			x1 = e.getX();
			y1 = e.getY();
		}
		
		if (fp.forma=="line")
		{
			x1 = e.getX();
			y1 = e.getY();
		}
		
		if (fp.forma == "rectangle")
		{
			if(contor == 0)
			{
				x1 = e.getX();
				y1 = e.getY();
				contor++;
			}
			
			else
			{
				if (contor == 1)
				{
					x2 = e.getX();
					y2 = e.getY();
					contor++;
				}
				else
				{
					if (contor == 2)
					{
						x3 = e.getX();
						y3 = e.getY();
						
						stack.add(new FormaGeometrica(fp.forma, x1, y1, x2, y2, x3, y3, fp.drawString, fp.culoare,fp.font));
						repaint();
						contor = 0;
					}
				}
					
			}
		}
		
		if (fp.forma == "Rounded Rectangle")
		{
			if(contor == 0)
			{
				x1 = e.getX();
				y1 = e.getY();
				contor++;
			}
			
			else
			{
				if (contor == 1)
				{
					x2 = e.getX();
					y2 = e.getY();
					contor++;
				}
				else
				{
					if (contor == 2)
					{
						stack.add(new FormaGeometrica(fp.forma, x1, y1, x2, y2, x3, y3, fp.drawString, fp.culoare,fp.font));
						repaint();
						contor = 0;
					}
				}
					
			}
			
		}
		
		if(fp.forma == "square")
		{
			x1 = e.getX();
			y1 = e.getY();
		}
		
		if (fp.forma == "circle")
		{
			x1 = e.getX();
			y1 = e.getY();
		}
		
		if (fp.forma == "polygon")
		{
			nx.add(e.getX());
			ny.add(e.getY());
			fp.setTitle("Press q key if you want to close the polygone!");
			
		}
		
		if (fp.forma == "Polyline")
		{
			nx.add(e.getX());
			ny.add(e.getY());
			fp.setTitle("Press q key if you want to close the polyline!");
			
		}
		
		if(fp.forma == "oval")
		{
			if(contor == 0)
			{
			
				x1 = e.getX();
				y1 = e.getY();
				contor++;
			}
			
			else
			{
				if (contor == 1)
				{
					x2 = e.getX();
					y2 = e.getY();
					contor++;
				}
				else
				{
					if (contor == 2)
					{
						stack.add(new FormaGeometrica(fp.forma, x1, y1, x2, y2, x3, y3, fp.drawString, fp.culoare,fp.font));
						repaint();
						contor = 0;
					}
				}
					
			}
		}
		
	}

	public void mouseReleased(MouseEvent e)
	{

		
		if (fp.forma == "line")
		{
			x2 = e.getX();
			y2 = e.getY();
			
			stack.add(new FormaGeometrica(fp.forma, x1, y1, x2, y2, x3, y3, fp.drawString, fp.culoare,fp.font));
			repaint();
			
		}
		
		if(fp.forma == "circle")
		{
			x2 = e.getX();
			y2 = e.getY();
			stack.add(new FormaGeometrica(fp.forma, x1, y1, x2, y2, x3, y3, fp.drawString, fp.culoare,fp.font));
			repaint();
		}
		if(fp.forma == "square")
		{
			x2 = e.getX();
			y2 = e.getY();
			
			repaint();
		}
		
		if (fp.forma == "eraser")
		{
			repaint();
		}
		
		if(fp.forma == "pencil")
		{
			repaint();
		}
		
		
	}

	public void mouseDragged(MouseEvent e)
	{
		if (fp.forma == "pencil")
		{
			x2 = e.getX();
			y2 = e.getY();
			
			stack.add(new FormaGeometrica(fp.forma, x1, y1, x2, y2, x3, y3, fp.drawString, fp.culoare,fp.font));
			Graphics g = this.getGraphics();
			g.setColor(fp.culoare);
			g.drawLine(x1, y1, x2, y2);
			g.dispose();
		
			x1=x2;
			y1=y2;
	

		}
		
		if (fp.forma == "eraser")
		{
			x2 = e.getX();
			y2 = e.getY();
			
			stack.add(new FormaGeometrica(fp.forma, x1, y1, x2, y2, x3, y3, fp.drawString, fp.culoare,fp.font));
			Graphics g = this.getGraphics();
			g.setColor(this.getBackground());
			g.fillRect(x1, y1, 8, 8);
			g.dispose();
			
		
			
			x1=x2;
			y1=y2;
		
			
		}
		
	}
	
	public void mouseMoved(MouseEvent e) {}
	
	public void keyPressed(KeyEvent w) 
	
	{
	
		if (w.getKeyChar()== 'q')
		{
			
			if(fp.forma == "polygon")
			{
			nx.add(nx.get(0));
			ny.add(ny.get(0));
			
			int[] x = new int[nx.size()];
			for (int k = 0; k < x.length; k++)
			{
				x[k] = nx.get(k);
			}
			
			int[] y = new int[ny.size()];
			for (int k = 0; k < y.length; k++)
			{
				y[k] = ny.get(k);
			}
			
			stack.add(new FormaGeometrica(fp.forma, x, y,   fp.culoare , x.length ));
			repaint();
			
			nx.clear();
			ny.clear();
			}
			
			
			if(fp.forma == "Polyline")
			{
		
			
			int[] x = new int[nx.size()];
			for (int k = 0; k < x.length; k++)
			{
				x[k] = nx.get(k);
			}
			
			int[] y = new int[ny.size()];
			for (int k = 0; k < y.length; k++)
			{
				y[k] = ny.get(k);
			}
			

			stack.add(new FormaGeometrica(fp.forma, x, y, fp.culoare , x.length ));
			repaint();
			
			nx.clear();
			ny.clear();
			}
			
			
		}
		
	}

	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	

}
