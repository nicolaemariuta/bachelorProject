package claseUtile;

public class Coordonate
{
	int x;
	int y;
	
	public Coordonate (int x, int y)
	{
		this.x =x;
		this.y = y;
	}
	
	public static int distanta(Coordonate c1, Coordonate c2)
	{
		return Math.abs(c1.x - c2.x) + Math.abs(c1.y - c2.y);
	}

}
