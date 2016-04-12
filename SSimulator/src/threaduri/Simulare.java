package threaduri;

import java.util.Stack;

import javax.xml.ws.handler.PortInfo;

import mainPack.FereastraPrincipala;
import mainPack.PoartaLogica;

public class Simulare extends Thread
{
	FereastraPrincipala fp = null;
	public int fara = 0;
	public boolean simulare = true;
	public Stack<String> coloane = new Stack();
	public Stack<PoartaLogica> verificate = new Stack();
	
	Stack<PoartaLogica> porti = new Stack();
	
	public Simulare(FereastraPrincipala fp, PoartaLogica[] stack)
	{
		this.fp = fp;
		for(int i = 0; i <stack.length; i++)
		{
		  porti.add(stack[i]);
		}
	}
	
	public void run ()
	{
		for(int i = 0; i < porti.size(); i++)
		{
			porti.get(i).setValor(null);
		}
	
		
		for (int i = 0; i < porti.size(); i++)
		{
		
			
			if(porti.get(i).getType().equals("IN") )
			{
				if(!(porti.get(i).getValue() == 0 || porti.get(i).getValue() == 1))
				{
					fara++;
				}
			}
		}
		
		int pas = 1;
		
		for(int i = 0; i < porti.size(); i++)
		{	
			
			if(porti.get(i).getType().equals("IN")  && !(porti.get(i).getValue() == 0 || porti.get(i).getValue() == 1 ))
			{
				
				
				
				coloane.add(porti.get(i).getNume());
				verificate.add(porti.get(i));
				
				int[] valori = new int[(int) Math.pow(2, fara)];
				
				
				int contor = 0;
				
				while (contor<valori.length)
				{
					for(int k = 0; k < pas; k++ )
					{
						valori[contor] = 1;
						contor++;
					}
					for(int k = 0; k < pas; k++ )
					{
						valori[contor] = 0;
						contor++;
					}
				}
				
				porti.get(i).setValor(valori);
				pas = pas*2;
			}
			else  if(porti.get(i).getType().equals("IN")   )
			{
				coloane.add(porti.get(i).getNume());
				verificate.add(porti.get(i));
				
				int[] valori = new int[(int) Math.pow(2, fara)];
				
				for(int k = 0; k< valori.length; k++)
				{
					valori[k] = porti.get(i).getValue();
				}
				porti.get(i).setValor(valori);
			}
				
			
			
			
		}
		
	
		
		while(true)
		{
			boolean find = true;
			for(int i = 0; i < porti.size(); i++)
			{
				if(porti.get(i).getType().equals("IN") )
				{
					find = false;
					porti.remove(i);
				}
			}
			if(find == true)
			{
				break;
			}
		}
		
	
		
	
		
		while(true)
		{
			
		
		
		boolean verificare = true;	
		
	
		
		for(int i = 0; i< porti.size(); i++)
		{
				boolean conectat = true;
			
			
		
			
				
				for(int h = 0; h <porti.get(i).intrari.size(); h++)
				{
					
					if(porti.get(i).intrari.get(h).getValor() == null)
					{	
						
						conectat = false;
						break;
					}
					
				}
				
				
				
			
			
				if(!porti.get(i).getType().equals("AMPL") && !porti.get(i).getType().equals("NON") && !porti.get(i).getType().equals("IN") && !porti.get(i).getType().equals("OUT"))
				{
				
					
					if(porti.get(i).intrari.size()<2 )
					{
						verificare = true;
						simulare = false;
					
					}
				
				}
				
				
				if(porti.get(i).getType().equals("AMPL") || porti.get(i).getType().equals("NON") )
				{
					if(porti.get(i).intrari.size() ==0)
					{
					
						verificare = true;
						simulare = false;
					
					}
				
				}
				
				
		
			
				if(porti.get(i).intrari.size() !=0 && conectat == true )
				{
				
	
					
					
					if( verificare == true)
					{
				
					
						
					if(porti.get(i).getType().equals( "AMPL")  )
					{
			
						
						coloane.add(porti.get(i).getNume() + "= " + porti.get(i).intrari.get(0).getNume());
							
						
				
						int[] valori = new int[(int) Math.pow(2, fara)];
						
						for(int j = 0; j < Math.pow(2, fara); j++)
						{
							
							
							if(porti.get(i).intrari.get(0).getValor()[j]  == 0)
							{
								valori[j] = 0;
							}
							else
							{
								valori[j] = 1;
							}
			
						}
						
					
						verificare = false;
						porti.get(i).setValor(valori);
						verificate.add(porti.get(i));
					
						porti.remove(i);
				
					}
					}
					
					if( verificare == true)
					{
					if(porti.get(i).getType().equals( "OUT")  )
					{
					
					

						coloane.add(porti.get(i).getNume() + "= " + porti.get(i).intrari.get(0).getNume());
							
							
						
						
						int[] valori = new int[(int) Math.pow(2, fara)];
						
						for(int j = 0; j < Math.pow(2, fara); j++)
						{
							if(porti.get(i).intrari.get(0).getValor()[j]  == 0)
							{
								valori[j] = 0;
							}
							else
							{
								valori[j] = 1;
							}
			
						}
						
						verificare = false;
						porti.get(i).setValor(valori);
						verificate.add(porti.get(i));
						porti.remove(i);
				
					}
					}
					
					if( verificare == true)
					{
			
					if(porti.get(i).getType().equals("NON") )
						{
							
							coloane.add(porti.get(i).getNume() + "= !" + porti.get(i).intrari.get(0).getNume());
							
							
						
						
						int[] valori = new int[(int) Math.pow(2, fara)];
						
						for(int j = 0; j < Math.pow(2, fara); j++)
						{
							if(porti.get(i).intrari.get(0).getValor()[j]  == 0)
							{
								valori[j] = 1;
							}
							else
							{
								valori[j] = 0;
							}
			
						}
						
						verificare = false;
						porti.get(i).setValor(valori);
						verificate.add(porti.get(i));
						porti.remove(i);
					
						}
					}
					
		
					if( verificare == true)
					{
					
					if(porti.get(i).getType().equals("AND")  )
					{
						
				
						
						String nume = porti.get(i).getNume() + "=";
						for(int k =0; k< porti.get(i).intrari.size(); k++)
						{
							if(k == 0)
							{
								nume = nume  + porti.get(i).intrari.get(k).getNume();
							}
							else
							{
							nume = nume + "*" + porti.get(i).intrari.get(k).getNume();
							}
							
						}
						
						coloane.add(nume);
						
						boolean b = true;
						int[] valori = new int[(int) Math.pow(2, fara)];
					
						for(int j =0; j< valori.length; j++)
						{
						int m = 1;
						
						for(int k =0; k< porti.get(i).intrari.size(); k++)
						{
							
							m = m * porti.get(i).intrari.get(k).getValor()[j]; 
						
						}
						
						if(m == 1)
						{
							valori[j] = 1;
						}
						else
						{
							valori[j] = 0;
						}
						}
						
			
						
						verificare = false;
						porti.get(i).setValor(valori);
						verificate.add(porti.get(i));
						porti.remove(i);
				
					
						
					}
					
					}
					
					if( verificare == true)
					{
					if(porti.get(i).getType().equals( "NAND"))
					{
						
						String nume = porti.get(i).getNume() + "=!(";
						
					
						for(int k =0; k< porti.get(i).intrari.size(); k++)
						{
							if(k == 0)
							{
								nume = nume  + porti.get(i).intrari.get(k).getNume();
							}
							else
							{
							nume = nume + "*" + porti.get(i).intrari.get(k).getNume();
							}
							
						}
						
						
						coloane.add(nume + ")");
						
						boolean b = true;
						int[] valori = new int[(int) Math.pow(2, fara)];
						
						for(int j =0; j< valori.length; j++)
						{
						int m = 1;
						
						for(int k =0; k< porti.get(i).intrari.size(); k++)
						{
							m = m* porti.get(i).intrari.get(k).getValor()[j]; 
							
						}
						
						if(m == 1)
						{
							valori[j] = 0;
						}
						else
						{
							valori[j] = 1;
						}
						}
						verificare = false;
						porti.get(i).setValor(valori);
						verificate.add(porti.get(i));
						porti.remove(i);
				
					
						
					}
					}
					
					if( verificare == true)
					{
					
					if(porti.get(i).getType().equals("OR") )
					{
						String nume = porti.get(i).getNume() + "=";
					
						for(int k =0; k< porti.get(i).intrari.size(); k++)
						{
							if(k == 0)
							{
								nume = nume  + porti.get(i).intrari.get(k).getNume();
							}
							else
							{
							nume = nume + "+" + porti.get(i).intrari.get(k).getNume();
							}
							
						}
						
						
						coloane.add(nume);
						
						boolean b = true;
						int[] valori = new int[(int) Math.pow(2, fara)];
						
						for(int j =0; j< valori.length; j++)
						{
						int m = 0;
						
						for(int k =0; k< porti.get(i).intrari.size(); k++)
						{
							m = m + porti.get(i).intrari.get(k).getValor()[j]; 
							
						}
						
						if(m != 0)
						{
							valori[j] = 1;
						}
						else
						{
							valori[j] = 0;
						}
						}
						verificare = false;
						porti.get(i).setValor(valori);
						verificate.add(porti.get(i));
						porti.remove(i);
					
					
					}
					}
					
					if( verificare == true)
					{

					if(porti.get(i).getType().equals("NOR"))
					{
						
						String nume = porti.get(i).getNume() + "=!(";
						
						for(int k =0; k< porti.get(i).intrari.size(); k++)
						{
							if(k == 0)
							{
								nume = nume  + porti.get(i).intrari.get(k).getNume();
							}
							else
							{
							nume = nume + "+" + porti.get(i).intrari.get(k).getNume();
							}
							
						}
						
						
						coloane.add(nume+ ")");
						
						
						boolean b = true;
						int[] valori = new int[(int) Math.pow(2, fara)];
						
						for(int j =0; j< valori.length; j++)
						{
						int m = 0;
						
						for(int k =0; k< porti.get(i).intrari.size(); k++)
						{
							m = m + porti.get(i).intrari.get(k).getValor()[j]; 
							
						}
						
						if(m != 0)
						{
							valori[j] = 0;
						}
						else
						{
							valori[j] = 1;
						}
						}
						verificare = false;
						porti.get(i).setValor(valori);
						verificate.add(porti.get(i));
						porti.remove(i);
					
						
						
					}
					}
					
					if( verificare == true)
					{
					if(porti.get(i).getType().equals("XOR") )
					{
					
						String nume = porti.get(i).getNume() + "= (";
					
						for(int k =0; k< porti.get(i).intrari.size(); k++)
						{
							if(k == 0)
							{
								nume = nume  + porti.get(i).intrari.get(k).getNume();
							}
							else
							{
							nume = nume + "+" + porti.get(i).intrari.get(k).getNume();
							}
							
						}
						
						
						coloane.add(nume+")%2");
						
						
						int[] valori = new int[(int) Math.pow(2, fara)];
						
						for(int j =0; j< valori.length; j++)
						{
						int m = 0;
						
						for(int k =0; k< porti.get(i).intrari.size(); k++)
						{
						
							
							m = m+ porti.get(i).intrari.get(k).getValor()[j]; 
							
						}
						
						if(m%2 == 1)
						{
							valori[j] = 1;
						}
						else
						{
							valori[j] = 0;
						}
						}
						verificare = false;
						porti.get(i).setValor(valori);
						verificate.add(porti.get(i));
						porti.remove(i);
					
						
						
					}}
					
					if( verificare == true)
					{
					
					if(porti.get(i).getType().equals("NXOR") )
					{
					
						String nume = porti.get(i).getNume() + "= !((";
						
						for(int k =0; k< porti.get(i).intrari.size(); k++)
						{
							if(k == 0)
							{
								nume = nume  + porti.get(i).intrari.get(k).getNume();
							}
							else
							{
							nume = nume + "+" + porti.get(i).intrari.get(k).getNume();
							}
							
						}
						
						
						coloane.add(nume+")%2)");
						
						int[] valori = new int[(int) Math.pow(2, fara)];
						
						for(int j =0; j< valori.length; j++)
						{
						int m = 0;
						
						for(int k =0; k< porti.get(i).intrari.size(); k++)
						{
							m = m+ porti.get(i).intrari.get(k).getValor()[j]; 
							
						}
						
						if(m%2 == 1)
						{
							valori[j] = 0;
						}
						else
						{
							valori[j] = 1;
						}
						}
						verificare = false;
						porti.get(i).setValor(valori);
						verificate.add(porti.get(i));
						porti.remove(i);
					
						
						
					}}
					
				
				}
			
				
			
		}
	
		if(verificare == true)
		{	
		
			break;
		}
		
		
		}
		
		
	
		
		
		
	}
	
	
	
	

}
