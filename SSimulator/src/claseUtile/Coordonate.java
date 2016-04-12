package claseUtile;

import java.io.Serializable;

public class Coordonate implements Serializable
{


	int x;
	int y;
	String type;
	boolean con = false;
	



	public Coordonate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public static int distanta(Coordonate c1, Coordonate c2)
	{
		return(Math.abs(c1.x - c2.x) + Math.abs(c1.y - c2.y));
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public static boolean checkProximity(Coordonate c,Coordonate x)
	{
		if(Math.abs(distanta(c,x))<10)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean isCon() {
		return con;
	}

	public void setCon(boolean con) {
		this.con = con;
	}
}
