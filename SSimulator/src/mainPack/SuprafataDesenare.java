package mainPack;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Stack;

import javax.swing.JOptionPane;



import claseUtile.Conexiune;

import claseUtile.Coordonate;
import claseUtile.DesenarePorti;
import claseUtile.Nod;
import claseUtile.SetarePoarta;

public class SuprafataDesenare extends Canvas implements MouseListener, KeyListener, MouseMotionListener
{
	public FereastraPrincipala fp;
	Dimension dim;
	public PoartaLogica selected = null;
	int orientare = 0;
	boolean conex = false;
	public Stack<Conexiune> conexiuni = new Stack(); 
	public Stack<Nod> noduri = new Stack();
	int index;
	int index2;
	public Conexiune selCon = null;
	int ind = 6;
	public Nod selNod = null;


	
	public Nod conNod = null;

	//pentru conectarea portilor
	Coordonate c1 = null;
	Coordonate c2 = null;
	PoartaLogica poarta1 = null;
	PoartaLogica poarta2 = null;
	
	
	SuprafataDesenare(FereastraPrincipala fp, Dimension dim)
	{
		this.fp = fp;
		this.dim = dim;
		this.setSize(dim);
		
	
		
		this.addMouseListener(this);
		this.addKeyListener(this);
		this.addMouseMotionListener(this);
	}
	
	
	

	public void paint (Graphics g)
	{
		
		update(g);

	}
	
	public void update(Graphics g)
	{
		
		
			Image offImage = createImage(dim.width, dim.height);
			Graphics off = offImage.getGraphics();
			
		
		
				off.setColor(Color.white);
				off.fillRect(0, 0, 1237, 740);
				
				off.setColor(Color.black);
				for(int i = 0; i < 1237; i = i+10)
				{
					for(int j= 0; j < 1237; j = j+10)
					{
						off.fillOval(i, j, 2, 2);
					}
				}
			
				for(int i = 0; i<fp.porti.size();i++)
				{
				
					
					if(fp.porti.get(i).getType().equals("AMPL"))
					{
					
						DesenarePorti.desenareAMPL(fp.porti.get(i), off);
					}
					
					if(fp.porti.get(i).getType().equals("NON") )
					{
						DesenarePorti.desenareNON(fp.porti.get(i), off);
					}
					
					if(fp.porti.get(i).getType().equals("AND"))
					{
						DesenarePorti.desenareAND(fp.porti.get(i), off);
					}
					
					
					if(fp.porti.get(i).getType().equals("NAND") )
					{
						DesenarePorti.desenareNAND(fp.porti.get(i), off);
					}
					
					if(fp.porti.get(i).getType().equals("OR") )
					{
						DesenarePorti.desenareOR(fp.porti.get(i), off);
					}
					
					if(fp.porti.get(i).getType().equals("NOR"))
					{
						DesenarePorti.desenareNOR(fp.porti.get(i), off);
					}
					
					if(fp.porti.get(i).getType().equals("XOR"))
					{
						DesenarePorti.desenareXOR(fp.porti.get(i), off);
					}
					if(fp.porti.get(i).getType().equals("NXOR"))
					{
						DesenarePorti.desenareNXOR(fp.porti.get(i), off);
					}
					
					if(fp.porti.get(i).getType().equals("OUT"))
					{
						DesenarePorti.desenareAMPL(fp.porti.get(i), off);
					}
					
					if(fp.porti.get(i).getType().equals("IN"))
					{
						DesenarePorti.desenareAMPL(fp.porti.get(i), off);
					}
					
				}
				
				//desenarea conexiunilor
			
				for(int i = 0; i<conexiuni.size(); i++)
				{
					if(!conexiuni.get(i).isAreNod())
					{
					DesenarePorti.desenareConexiune(conexiuni.get(i).getIn().in[conexiuni.get(i).getIndex()],conexiuni.get(i).getOut().getOut() , off, conexiuni.get(i));
					}
					else
					{
						DesenarePorti.desenareConexiune(conexiuni.get(i).getIn().in[conexiuni.get(i).getIndex()], conexiuni.get(i).getNod().getCoord(), off, conexiuni.get(i));
						
					}
					
				}
				
				for(int i = 0; i<noduri.size(); i++)
				{
					DesenarePorti.desenareNod(noduri.get(i), off);
				}
	
			  g.drawImage(offImage, 0, 0, this);
		
	}



	public void mouseClicked(MouseEvent e) 
	{
		//conectare porti
		if(fp.getDesenare().equals("LINE")&& conex == false )
		{
			
			int x = e.getX();
			int y = e.getY();
			Coordonate w = new Coordonate(x,y);
			
			
			for(int i = 0; i<noduri.size(); i++)
			{
				try
				{
					boolean find = false;
					if(Coordonate.checkProximity(w, noduri.get(i).getCoord()) && conex == false && find == false&& !noduri.get(i).isConectat())
					{
						noduri.get(i).setCuloare(new Color(255,130,0));
						DesenarePorti.desenareNod(noduri.get(i), this.getGraphics());
						find = true;
						conex = true;
						conNod = noduri.get(i);
						c1 = noduri.get(i).getCoord();
						c1.setType("nod");
						break;
					}
					
				}
				catch(NullPointerException ee) {}
			}
		
			
			for (int i = 0; i<fp.porti.size(); i++)
			{
				try
				{
				boolean find = false;
				if(Coordonate.checkProximity(fp.porti.get(i).getOut(), w) && find == false && conex == false)
				{
					fp.porti.get(i).setCuloare(new Color(255,130,0));
					DesenarePorti.desenare(fp.porti.get(i), this.getGraphics());
					find = true;
					conex = true;
					c1 = fp.porti.get(i).getOut();
					c1.setType("out");
					poarta1 = fp.porti.get(i);
					break;
				}
				
			
		
				for(int j = 0; j<5; j++)
				{
					
					if(Coordonate.checkProximity(fp.porti.get(i).in[j], w)&& find == false && conex == false && fp.porti.get(i).intr[j]==0 )
					{
						
						
						
						fp.porti.get(i).setCuloare(new Color(255,130,0));
						DesenarePorti.desenare(fp.porti.get(i), this.getGraphics());
						conex = true;
						find = true;
						c1= fp.porti.get(i).in[j];
						c1.setType("in");
						poarta1 = fp.porti.get(i);
						fp.porti.get(i).intr[j]=1;
						index = j;
						index2 = i;
						break;
						
					}
				}
				}
				catch(NullPointerException eee) {}
				
			}
		
			
		}
		else				//conectare porti
	
		if(fp.getDesenare() == "LINE" && conex == true)
		{
			int x = e.getX();
			int y = e.getY();
			Coordonate w = new Coordonate(x,y);
			
			for(int i = 0; i<noduri.size(); i++)
			{
				try
				{
					
					if(Coordonate.checkProximity(noduri.get(i).getCoord(), w))
					{
						
					
						if(conNod !=null || c1.getType() == "out" || noduri.get(i).isConectat()||poarta1.getType() == "IN")
						{
							
							JOptionPane.showMessageDialog(this,"You cannot connect like that!","Error",JOptionPane.ERROR_MESSAGE);
							conex = false;
							for(int f = 0; f<noduri.size(); f++)
							{
								noduri.get(f).setCuloare(Color.black);
							}
							
							for(int f = 0; f< fp.porti.size(); f++)
							{
								
								fp.porti.get(f).setCuloare(fp.porti.get(f).getDefColor());
							}
							repaint();
						}
						else
						{
							
							Conexiune conexiune = new Conexiune(poarta1, noduri.get(i).getPoarta(), c1, noduri.get(i).getCoord(), index);
						
							conexiune.setNod(noduri.get(i));
							noduri.get(i).setConectat(true);
							conexiune.setAreNod(true);
							conexiuni.add(conexiune);
							
					
					
							for(int p = 0; p< fp.porti.size(); p++)
							{
							
								if(fp.porti.get(p) == poarta1)
								{
									
									fp.porti.get(p).intrari.add(noduri.get(i).getPoarta());
									break;
								
								}
							}
							
						
						
							
							noduri.get(i).getCon().conexiuni.add(conexiune);
							
					
							for(int f = 0; f<noduri.size(); f++)
							{
								noduri.get(f).setCuloare(Color.black);
							}
							
							for(int f = 0; f< fp.porti.size(); f++)
							{
								
								fp.porti.get(f).setCuloare(fp.porti.get(f).getDefColor());
							}
							conex = false;
							fp.setDesenare("select");
							repaint();
							break;
							
							
							
						}
					}
				}
				catch(Exception W) {}
			}
			
			
			if(conex == true)
			{
			for (int i = 0; i<fp.porti.size(); i++)
			{
				try
				{
				boolean find = false;
				if(Coordonate.checkProximity(fp.porti.get(i).getOut(), w) && find == false && conex == true  )
				{
					
					fp.porti.get(i).setCuloare(new Color(148,220,150));
					DesenarePorti.desenare(fp.porti.get(i), this.getGraphics());
					find = true;
					conex = false;
					fp.setDesenare("select");
					fp.setTitle("Select");
					c2 = fp.porti.get(i).getOut();
					c2.setType("out");
					
			
					
					if(c1.getType() == c2.getType() || fp.porti.get(i).isOout() || poarta1 == fp.porti.get(i)  || c1.getType() == "nod"|| fp.porti.get(i).getType() == "OUT" || poarta1.getType() == "IN"  )
					{
						fp.porti.get(index2).intr[index]=0;
						conex = false;
						JOptionPane.showMessageDialog(this,"You cannot connect like that!","Error",JOptionPane.ERROR_MESSAGE);
						for(int f = 0; f<noduri.size(); f++)
						{
							noduri.get(f).setCuloare(Color.black);
						}
						
						for(int f = 0; f< fp.porti.size(); f++)
						{
							
							fp.porti.get(f).setCuloare(fp.porti.get(f).getDefColor());
						}
						repaint();
						
					}
					
					
						else
						{
							
						conexiuni.add(new Conexiune(poarta1,fp.porti.get(i) , c1, c2,index ));
						fp.porti.get(i).setIesire(poarta1);
			
						poarta1.intrari.add(fp.porti.get(i));
						
					
						fp.porti.get(i).setOout(true);
						
						}
					
					
					for(int f = 0; f<noduri.size(); f++)
					{
						noduri.get(f).setCuloare(Color.black);
					}
					
					for(int f = 0; f< fp.porti.size(); f++)
					{
						
						fp.porti.get(f).setCuloare(fp.porti.get(f).getDefColor());
					}
				
					repaint();
					break;
				}
				for(int j = 0; j<5; j++)
				{
					if(Coordonate.checkProximity(fp.porti.get(i).in[j], w)&& find == false && conex == true && fp.porti.get(i).intr[j]==0 )
					{
						
				
						fp.porti.get(i).setCuloare(new Color(148,220,150));
						DesenarePorti.desenare(fp.porti.get(i), this.getGraphics());
						conex = false;
						find = true;
						fp.setDesenare("select");
						fp.setTitle("Select");
						c2= fp.porti.get(i).in[j];
						c2.setType("in");
						
						
						if(fp.porti.get(i).getType() == "IN")
						{
							JOptionPane.showMessageDialog(this,"You cannot connect like that","Error",JOptionPane.ERROR_MESSAGE);	
							conex = false;
							for(int f = 0; f<noduri.size(); f++)
							{
								noduri.get(f).setCuloare(Color.black);
							}
							
							for(int f = 0; f< fp.porti.size(); f++)
							{
								
								fp.porti.get(f).setCuloare(fp.porti.get(f).getDefColor());
							}
							repaint();
						}
						else
						if(c1.getType() == "nod")
						{
					
							fp.porti.get(i).intr[j]=1;
							fp.porti.get(i).intrari.add(conNod.getPoarta());
						
							Conexiune conexiune = new Conexiune(fp.porti.get(i), conNod.getPoarta(), c1, c2,  j);
							
							conexiune.setNod(conNod);
							conexiune.setAreNod(true);
							conexiuni.add(conexiune);
							conNod.setConectat(true);
							conNod.getCon().conexiuni.add(conexiune);
							fp.setDesenare("select");
							conNod = null;
							
						}
						else
					
						if(c1.getType() == c2.getType() || poarta1.isOout() || poarta1 == fp.porti.get(i) || fp.porti.get(i).getType() == "IN" || poarta1.getType() == "OUT")
						{
							JOptionPane.showMessageDialog(this,"You cannot connect like that","Error",JOptionPane.ERROR_MESSAGE);
							conex = false;
							for(int f = 0; f<noduri.size(); f++)
							{
								noduri.get(f).setCuloare(Color.black);
							}
							
							for(int f = 0; f< fp.porti.size(); f++)
							{
								
								fp.porti.get(f).setCuloare(fp.porti.get(f).getDefColor());
							}
							repaint();
						}
						 
							
					
						else
						{
							fp.porti.get(i).intr[j]=1;
							conexiuni.add(new Conexiune(fp.porti.get(i),poarta1 , c1, c2, j));
							fp.porti.get(i).intrari.add(poarta1);
					
							poarta1.setOout(true);
							poarta1.setIesire(fp.porti.get(i));
							
						
						}
						for(int f = 0; f<noduri.size(); f++)
						{
							noduri.get(f).setCuloare(Color.black);
						}
						
						for(int f = 0; f< fp.porti.size(); f++)
						{
							
							fp.porti.get(f).setCuloare(fp.porti.get(f).getDefColor());
						}
					
						repaint();
						break;
						
						
					}
				}
				}
				catch(NullPointerException eee) {}
				
			}
			}
		}
			
		
	}

	
	public void mouseEntered(MouseEvent e)
	{
		
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
				
			
		// setare porti
				
				if(selected != null)
				{
					if(e.getClickCount() == 2 )
					{
						int x = e.getX();
						int y = e.getY();
						
						if(Math.abs (x-selected.getCoord().getX())<(selected.getSize()/2 )
								   &&
								    Math.abs(y-selected.getCoord().getY())<(selected.getSize()/2 ))
						{
							
							new SetarePoarta(this).setVisible(true);
							
						}
					
						
					
					}
					
					for (int i = 0; i < 5; i++)
					{
						selected.intr[i] = 0;
						
					}
				}
				
				
				
				
				//selectare conexiuni , porti, noduri
				if(fp.getDesenare().equals("select" ))
				{
					for(int i =0; i<conexiuni.size(); i++)
					{
						conexiuni.get(i).setCuloare(Color.black);
					}
					
					for(int i = 0; i<noduri.size(); i++)
					{
						noduri.get(i).setCuloare(Color.black);
					}
					
					for(int i =0; i<fp.porti.size(); i++)
					{
						fp.porti.get(i).setCuloare(fp.porti.get(i).getDefColor());
					}
					
					
					selected = null;
					selCon = null;
					selNod = null;
					repaint();
					
					boolean bool = false;
					
					

					for(int i =0; i < noduri.size(); i++)
					{
						if(Math.abs(e.getX()-noduri.get(i).getCoord().getX())<4 && Math.abs(e.getY()-noduri.get(i).getCoord().getY())<4)
						{
							noduri.get(i).setCuloare(Color.blue);
							selNod = noduri.get(i);
							repaint();
							bool = true;
							break;
						}
					}
					
					
					if(bool == false)
					{
					
					for(int i = 0; i<conexiuni.size(); i++)
					{
						Line2D line1 = conexiuni.get(i).getLine1();
						Line2D line2 = conexiuni.get(i).getLine2();
						
						
						Line2D orizontal = null;
						Line2D vertical = null;
						
						if(line1.getX1() == line1.getX2())
						{
							vertical = line1;
							orizontal = line2;
						}
								
						
						if(line1.getY1() == line1.getY2())
						{
							vertical = line2;
							orizontal = line1;
						}
						
						
				
						if((Math.abs(e.getX() - vertical.getX1()) < 4  &&
								e.getY() < Math.max(vertical.getY1(), vertical.getY2()) &&
								e.getY() > Math.min(vertical.getY1(), vertical.getY2()) )
							||
							(Math.abs(e.getY() - orizontal.getY1()) <4  &&
									e.getX() < Math.max(orizontal.getX1(), orizontal.getX2()) &&
									e.getX() > Math.min(orizontal.getX1(), orizontal.getX2()) )	)
						{
							selCon = conexiuni.get(i);
							selCon.setCuloare(Color.blue);
							bool = true;
							repaint();
							break;
							
							
						}
							
					}
					}
					
					
					if(bool == false)
					{
						for(int i =0; i<fp.porti.size(); i++)
						{
							int x = e.getX();
							int y = e.getY();
							
							if( Math.abs (x-fp.porti.get(i).getCoord().getX())<(fp.porti.get(i).getSize()*3/4 )
							   &&
							    Math.abs(y-fp.porti.get(i).getCoord().getY())<(fp.porti.get(i).getSize()*3/4)
							    && selected == null && selCon==null)
							{
							
								fp.porti.get(i).setCuloare(Color.blue);
								DesenarePorti.desenare(fp.porti.get(i), this.getGraphics());
								selected = fp.porti.get(i);
							
								
								if(selected.getOrientare() == "sus")
								{
									orientare = 1;
								}
								
								if(selected.getOrientare() == "stanga")
								{
									orientare = 2;
								}
								
								
								if(selected.getOrientare() == "jos")
								{
									orientare = 3;
								}
								
								
								if(selected.getOrientare() == "dreapta")
								{
									orientare = 0;
								}
								
								
								break;
							}
							
						}
						
					}
				}
				
				

		
	}

	
	public void mouseReleased(MouseEvent e) 
	{
	
	
		
		
		// desenare porti
			
				if(fp.getDesenare()== "AMPL")
				{
					
					if(selected!=null)
					{
					selected.setCuloare(selected.getDefColor());
					repaint();
					selected = null;
					}
				
					
					
					int x = e.getX();
					int y = e.getY();
					fp.porti.add(new PoartaLogica("AMPL", new Coordonate(x,y),Color.black, 20));
					fp.setDesenare("select");
					fp.setTitle("Select");
					repaint();
					
				}
				
				if(fp.getDesenare()== "IN")
				{
					
					if(selected!=null)
					{
					selected.setCuloare(selected.getDefColor());
					repaint();
					selected = null;
					}
				
					
					
					int x = e.getX();
					int y = e.getY();
					fp.porti.add(new PoartaLogica("IN", new Coordonate(x,y),Color.red, 20));
					fp.setDesenare("select");
					fp.setTitle("Select");
					repaint();
					
				}
				
				if(fp.getDesenare()== "OUT")
				{
					
					if(selected!=null)
					{
					selected.setCuloare(selected.getDefColor());
					repaint();
					selected = null;
					}
				
					
					
					int x = e.getX();
					int y = e.getY();
					fp.porti.add(new PoartaLogica("OUT", new Coordonate(x,y),new Color(61,169,58), 20));
					fp.setDesenare("select");
					fp.setTitle("Select");
					repaint();
					
				}
				
				if(fp.getDesenare()== "NON")
				{
					if(selected!=null)
					{
					selected.setCuloare(selected.getDefColor());
					repaint();
					selected = null;
					}
				
					
					
					int x = e.getX();
					int y = e.getY();
					fp.porti.add(new PoartaLogica("NON", new Coordonate(x,y),Color.black, 20));
					fp.setDesenare("select");
					fp.setTitle("Select");
					repaint();
				}
				
				
				if(fp.getDesenare()== "AND")
				{
					if(selected!=null)
					{
					selected.setCuloare(selected.getDefColor());
					repaint();
					selected = null;
					}
				
					
					
					int x = e.getX();
					int y = e.getY();
					fp.porti.add(new PoartaLogica("AND", new Coordonate(x,y),Color.black, 20));
					fp.setDesenare("select");
					fp.setTitle("Select");
					repaint();
				}
				
				if(fp.getDesenare()== "NAND")
				{
					if(selected!=null)
					{
					selected.setCuloare(selected.getDefColor());
					repaint();
					selected = null;
					}
				
					
					int x = e.getX();
					int y = e.getY();
					fp.porti.add(new PoartaLogica("NAND", new Coordonate(x,y),Color.black, 20));
					fp.setDesenare("select");
					fp.setTitle("Select");
					repaint();
				}
				
				if(fp.getDesenare()== "OR")
				{
					if(selected!=null)
					{
					selected.setCuloare(selected.getDefColor());
					repaint();
					selected = null;
					}
				
					
					int x = e.getX();
					int y = e.getY();
					fp.porti.add(new PoartaLogica("OR", new Coordonate(x,y),Color.black, 20));
					fp.setDesenare("select");
					fp.setTitle("Select");
					repaint();
				}
				
				
				if(fp.getDesenare()== "NOR")
				{
					if(selected!=null)
					{
					selected.setCuloare(selected.getDefColor());
					repaint();
					selected = null;
					}
				
					
					int x = e.getX();
					int y = e.getY();
					fp.porti.add(new PoartaLogica("NOR", new Coordonate(x,y),Color.black, 20));
					fp.setDesenare("select");
					fp.setTitle("Select");
					repaint();
				}
				
				
				if(fp.getDesenare()== "XOR")
				{
					if(selected!=null)
					{
					selected.setCuloare(selected.getDefColor());
					repaint();
					selected = null;
					}
				
					
					int x = e.getX();
					int y = e.getY();
					fp.porti.add(new PoartaLogica("XOR", new Coordonate(x,y),Color.black, 20));
					fp.setDesenare("select");
					fp.setTitle("Select");
					repaint();
				}
				
				
				if(fp.getDesenare()== "NXOR")
				{
					if(selected!=null)
					{
					selected.setCuloare(selected.getDefColor());
					repaint();
					selected = null;
					}
				
					
					int x = e.getX();
					int y = e.getY();
					fp.porti.add(new PoartaLogica("NXOR", new Coordonate(x,y),Color.black, 20));
					fp.setDesenare("select");
					fp.setTitle("Select");
					repaint();
				}
				
				
				if(fp.getDesenare() == "IN")
				{
					if(selected!=null)
					{
					selected.setCuloare(selected.getDefColor());
					repaint();
					selected = null;
					}
					
					int x = e.getX();
					int y = e.getY();
					fp.porti.add(new PoartaLogica("ON", new Coordonate(x,y),Color.red, 20));
					fp.setDesenare("select");
					fp.setTitle("Select");
					repaint();
				}
				
				if(fp.getDesenare() == "OUT")
				{
					if(selected!=null)
					{
					selected.setCuloare(selected.getDefColor());
					repaint();
					selected = null;
					}
					
					int x = e.getX();
					int y = e.getY();
					fp.porti.add(new PoartaLogica("ON", new Coordonate(x,y),Color.green, 20));
					fp.setDesenare("select");
					fp.setTitle("Select");
					repaint();
				}
				
				
				if(fp.getDesenare() == "LINE")
				{
					
				}
				
				
				// desenare noduri
				if(fp.getDesenare() == "NODE")
				
				{	
				
					for(int i = 0; i<conexiuni.size(); i++)
						{
							
					
						Line2D line1 = conexiuni.get(i).getLine1();
						Line2D line2 = conexiuni.get(i).getLine2();
						
						
						Line2D orizontal = null;
						Line2D vertical = null;
						
						if(line1.getX1() == line1.getX2())
						{
							vertical = line1;
							orizontal = line2;
						}
								
						
						if(line1.getY1() == line1.getY2())
						{
							vertical = line2;
							orizontal = line1;
						}
						
						
				
						if((Math.abs(e.getX() - vertical.getX1()) < 4  &&                 
								e.getY() < Math.max(vertical.getY1(), vertical.getY2()) &&
								e.getY() > Math.min(vertical.getY1(), vertical.getY2()) ))
						{
							
							
							noduri.add(new Nod(conexiuni.get(i).getOut(), new Coordonate((int)vertical.getX1(), e.getY()), conexiuni.get(i)));
							repaint();
							
							
							//rulare simulare
							
							break;
							
						}
							
						if	(Math.abs(e.getY() - orizontal.getY1()) <4  &&
									e.getX() < Math.max(orizontal.getX1(), orizontal.getX2()) &&
									e.getX() > Math.min(orizontal.getX1(), orizontal.getX2()) )	
						{
					
							
							noduri.add(new Nod(conexiuni.get(i).getOut(), new Coordonate(e.getX(), (int)orizontal.getY1()),conexiuni.get(i)));
							repaint();
							
							
							
							
							
							
							break;
						}
								
						}
				}
				
	}

	
	public void keyPressed(KeyEvent e)
	{

		//apasare tasta esc
		try
		{
				if(e.getKeyCode() == e.VK_ESCAPE)
				{
		
					if(fp.getDesenare() == "LINE")
					{
						fp.porti.get(index2).intr[index] = 0;
					}
					
					fp.setDesenare("select");
					fp.setTitle("Select");
					
					conNod.setConectat(false);
					conNod = null;
					selCon = null;
					selected = null;
					conex = false;
					poarta1 = null;
					
					for(int i = 0; i< noduri.size(); i ++)
					{
						noduri.get(i).setCuloare(Color.black);
					}
					for(int i = 0; i< fp.porti.size(); i++)
					{
						
						fp.porti.get(i).setCuloare(fp.porti.get(i).getDefColor());
					}
					
					for(int i =0; i<conexiuni.size(); i++)
					{
						conexiuni.get(i).setCuloare(Color.black);
					}
				
					repaint();
					
				}
				
		} catch(NullPointerException ex) {}
		
		
		//redimensionarea portilor
		if (selected!=null)
		{
	
			if(e.getKeyCode() == e.VK_MINUS && selected.getSize()>8)
			{
				Nod[] array = new Nod[noduri.size()];
				
				for (int i =0; i<array.length; i++)
				{
					array[i] = noduri.get(i);
				}
				
				
				
				for(int  i =0; i <array.length; i++)
				{
		
					
					if(array[i].getCon().getIn() == selected ||array[i].getCon().getOut() == selected )
					{
						
						
						Conexiune[] art = new Conexiune[array[i].getCon().conexiuni.size()];
						
						for(int r = 0; r< art.length; r++)
						{
							art[r] = array[i].getCon().conexiuni.get(r);
						}
						
						
						for(int j = 0; j<art.length; j++)
						{
							
							art[j].getIn().intr[art[j].getIndex()] = 0;
							art[j].getIn().setValor(null);
							art[j].getIn().intrari.remove(art[j].getNod().getPoarta());
							art[j].conexiuni.clear();
							noduri.remove(art[j].getNod());
							conexiuni.remove(art[j]);
						} 
						
						
						noduri.remove(array[i]);
					}
					
				
				}
				
				
				
				
				selected.setSize(selected.getSize()-2);
				repaint();
			}
			
			if((e.getKeyCode() == e.VK_PLUS ||e.getKeyCode() == e.VK_EQUALS ) && selected.getSize()<100)
			{
				Nod[] array = new Nod[noduri.size()];
				
				for (int i =0; i<array.length; i++)
				{
					array[i] = noduri.get(i);
				}
				
				
				
				for(int  i =0; i <array.length; i++)
				{
		
					
					if(array[i].getCon().getIn() == selected ||array[i].getCon().getOut() == selected )
					{
						
						
						Conexiune[] art = new Conexiune[array[i].getCon().conexiuni.size()];
						
						for(int r = 0; r< art.length; r++)
						{
							art[r] = array[i].getCon().conexiuni.get(r);
						}
						
						
						for(int j = 0; j<art.length; j++)
						{
							
							art[j].getIn().intr[art[j].getIndex()] = 0;
							art[j].getIn().setValor(null);
							art[j].getIn().intrari.remove(art[j].getNod().getPoarta());
							art[j].conexiuni.clear();
							noduri.remove(art[j].getNod());
							conexiuni.remove(art[j]);
						} 
						
						
						noduri.remove(array[i]);
					}
					
				
				}
				
				selected.setSize(selected.getSize()+2);
				repaint();
			}
		}
		
		//stergerea conexiunilor
		if(selCon!=null && e.getKeyCode()== e.VK_DELETE)
		{
			selCon.getOut().setIesire(null);
			selCon.getOut().setOout(false);
			
			selCon.getIn().intrari.remove(selCon.getOut());
			selCon.getIn().intr[selCon.getIndex()] = 0;
			
			if(selCon.isAreNod() == true)
			{
				noduri.remove(selCon.getNod());
			}
			
			Nod[] array = new Nod[noduri.size()];
		
			for (int i =0; i<array.length; i++)
			{
				array[i] = noduri.get(i);
			}
			
			for(int i = 0; i < array.length; i++)
			{
				
				if(array[i].getCon()== selCon)
				{
					
					for(int m = 0; m <conexiuni.size(); m++)
						
					{
						if(conexiuni.get(m).isAreNod())
						{
							if(conexiuni.get(m).getNod() == array[i])
							{
								conexiuni.get(m).getIn().intr[conexiuni.get(m).getIndex()] = 0;
								conexiuni.get(m).getIn().setValor(null);
								conexiuni.get(m).getIn().intrari.remove(conexiuni.get(m).getOut());
								
								
								Conexiune[] art = new Conexiune[conexiuni.get(m).conexiuni.size()];
								
								for(int r = 0; r< art.length; r++)
								{
									art[r] = conexiuni.get(m).conexiuni.get(r);
								}
								
								
								for(int j = 0; j<art.length; j++)
								{
									
									art[j].getIn().intr[art[j].getIndex()] = 0;
									art[j].getIn().setValor(null);
									art[j].getIn().intrari.remove(art[j].getNod().getPoarta());
									art[j].conexiuni.clear();
									noduri.remove(art[j].getNod());
									conexiuni.remove(art[j]);
								} 
								
								conexiuni.remove(m);
						
							}
						}
					}
					
					noduri.remove(array[i]);
				}
		
			}
			if(selCon.isAreNod())
			{
				for(int i = 0; i<noduri.size(); i++)
				{
					if(selCon.getNod() == noduri.get(i))
					{
					
						noduri.get(i).setConectat(false);
					}
				}
			}
			conexiuni.remove(selCon);
			repaint();
		}
		
		
		//stergerea portilor
		if (selected!=null)
		{
			if(e.getKeyCode()== e.VK_DELETE)
			{
	
				for(int i =0; i < selected.intr.length; i++)
				{
					
					if(selected.intr[i] !=0)
					{
						Conexiune intrare = null;
						
						for(int j = 0; j <conexiuni.size(); j++)
						{
							if(conexiuni.get(j).getIndex() == i && selected == conexiuni.get(j).getIn())
							{
							
								intrare = conexiuni.get(j);
								break;
							}
						}
						
						try{
						Conexiune[] con = new Conexiune[intrare.conexiuni.size()];
						for(int j = 0; j< con.length; j++)
						{
							con[j] = intrare.conexiuni.get(j);
						}
						
						for(int j =0; j<con.length; j++)
						{
							con[j].getIn().intr[con[j].getIndex()] = 0;
							con[j].getIn().setValor(null);
							con[j].getIn().intrari.remove(intrare.getOut());
							
							noduri.remove(con[j].getNod());
							conexiuni.remove(con[j]);
							intrare.conexiuni.remove(con[j]);
						}
						
						Nod[] nods = new Nod[noduri.size()];
						for(int j =0; j<nods.length; j++)
						{
							nods[j] = noduri.get(j);
						}
						
						for(int j =0; j<nods.length; j++)
						{
							if(nods[j].getCon() == intrare)
							{
								noduri.remove(nods[j]);
							}
						}
							
						intrare.getOut().setOout(false);
						intrare.getIn().setValor(null);
						intrare.getIn().intrari.remove(intrare.getOut());
						conexiuni.remove(intrare);
						repaint();
						
						}catch (NullPointerException h){}
					}
				}
				
				if(selected.getIesire()!=null)
				{
					Conexiune iesire = null;
					
					for(int i = 0; i<conexiuni.size(); i++)
					{
						if(conexiuni.get(i).getOut() == selected)
						{
							iesire = conexiuni.get(i);
							break;
						}
					}
					
					try
					{
					Conexiune[] con = new Conexiune[iesire.conexiuni.size()];
					for(int j = 0; j< con.length; j++)
					{
						con[j] = iesire.conexiuni.get(j);
					}
					
					for(int j =0; j<con.length; j++)
					{
						con[j].getIn().intr[con[j].getIndex()] = 0;
						con[j].getIn().setValor(null);
						con[j].getIn().intrari.remove(selected);
						
						noduri.remove(con[j].getNod());
						conexiuni.remove(con[j]);
						iesire.conexiuni.remove(con[j]);
					}
					
					Nod[] nods = new Nod[noduri.size()];
					for(int j =0; j<nods.length; j++)
					{
						nods[j] = noduri.get(j);
					}
					
					for(int j =0; j<nods.length; j++)
					{
						if(nods[j].getCon() == iesire)
						{
							noduri.remove(nods[j]);
						}
					}
					
					
					iesire.getIn().intr[iesire.getIndex()] = 0;
					iesire.getIn().setValor(null);
					iesire.getIn().intrari.remove(iesire.getOut());
					conexiuni.remove(iesire);
					} catch (NullPointerException y){}
				}
				
				
				
			selected.intrari.clear();	
			fp.porti.remove(selected);
			repaint();
	
			}
			
		}
		
		
		//stergerea nodurilor
		
		if(e.getKeyCode()== e.VK_DELETE && selNod!=null )
		{
			
			
			for(int i =0; i <conexiuni.size(); i++)
			{
				if(conexiuni.get(i).isAreNod())
				{
					if(conexiuni.get(i).getNod() == selNod)
					{
						
						
						Conexiune[] art = new Conexiune[conexiuni.get(i).conexiuni.size()];
						
						for(int r = 0; r< art.length; r++)
						{
							art[r] = conexiuni.get(i).conexiuni.get(r);
						}
						
						
						for(int j = 0; j<art.length; j++)
						{
							
							art[j].getIn().intr[art[j].getIndex()] = 0;
							art[j].getIn().setValor(null);
							art[j].getIn().intrari.remove(art[j].getNod().getPoarta());
							art[j].conexiuni.clear();
							noduri.remove(art[j].getNod());
							conexiuni.remove(art[j]);
						} 
						
						
						conexiuni.get(i).getIn().intr[conexiuni.get(i).getIndex()] = 0;
						conexiuni.get(i).getIn().setValor(null);
						conexiuni.get(i).getIn().intrari.remove(selNod.getPoarta());
						conexiuni.remove(i);
						break;
					}
				}
			}
			
			
			
			noduri.remove(selNod);
			repaint();
		}
		
		
		//rotirea portilor
		if (selected!=null)
		{
		
			if(e.getKeyChar() == 'r' || e.getKeyChar() == 'R')
			{
				
				Nod[] array = new Nod[noduri.size()];
				
				for (int i =0; i<array.length; i++)
				{
					array[i] = noduri.get(i);
				}
				
				
				for(int  i =0; i <array.length; i++)
				{
					
					
					
					
					if(array[i].getCon().getIn() == selected || array[i].getCon().getOut() == selected)
					{

						Conexiune[] art = new Conexiune[conexiuni.size()];
						
						for(int r = 0; r< art.length; r++)
						{
							art[r] = conexiuni.get(r);
						}
						
						for(int j = 0; j<art.length; j++)
						{
							if(art[j].isAreNod())
							{
								if(art[j].getNod() == array[i])
								{
										art[j].getIn().intr[art[j].getIndex()] = 0;
										art[j].getIn().setValor(null);
										art[j].getIn().intrari.remove(art[j].getNod().getPoarta());
										art[j].conexiuni.clear();
										noduri.remove(art[j].getNod());
										conexiuni.remove(art[j]);
								}
							}
						} 
						
						
						
						noduri.remove(array[i]);
					}	
				}
				
		
				orientare++;
				if(orientare%4 == 1)
				{
					selected.setOrientare("sus");
				}
				
				if(orientare%4 == 2)
				{
					selected.setOrientare("stanga");
				}
				if(orientare%4 == 3)
				{
					selected.setOrientare("jos");
				}
				if(orientare%4 == 0)
				{
					selected.setOrientare("dreapta");
				}
				
				repaint();
			}
		
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

			
		
	}

	
	public void mouseDragged(MouseEvent e)
	{
		
		
		//pentru miscare porti
		if(selected!=null)
		{
			
			int x = e.getX();
			int y = e.getY();
			
			

			Nod[] array = new Nod[noduri.size()];
		
			for (int i =0; i<array.length; i++)
			{
				array[i] = noduri.get(i);
			}
			
			
			
			for(int  i =0; i <array.length; i++)
			{
	
				
				if(array[i].getCon().getIn() == selected ||array[i].getCon().getOut() == selected )
				{
					
					
					Conexiune[] art = new Conexiune[array[i].getCon().conexiuni.size()];
					
					for(int r = 0; r< art.length; r++)
					{
						art[r] = array[i].getCon().conexiuni.get(r);
					}
					
					
					for(int j = 0; j<art.length; j++)
					{
						
						art[j].getIn().intr[art[j].getIndex()] = 0;
						art[j].getIn().setValor(null);
						art[j].getIn().intrari.remove(art[j].getNod().getPoarta());
						art[j].conexiuni.clear();
						noduri.remove(art[j].getNod());
						conexiuni.remove(art[j]);
					} 
					
					
					noduri.remove(array[i]);
				}
				
			
			}
			
		
			selected.setCoord(new Coordonate(x,y));
			selected.setCuloare(Color.blue);
			repaint();
			
		}
		
		
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{

	
		
	}
	
	



	
}
