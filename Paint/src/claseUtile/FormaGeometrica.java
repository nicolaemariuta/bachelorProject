package claseUtile;

import java.awt.Color;
import java.awt.Font;

public class FormaGeometrica
{
	
	public String forma;
	public String drawString;
	public int x1;
	public int x2;
	public int x3;
	public int y1;
	public int y2;
	public int y3;
	public Color culoare;
	public Font font;
	public int[] x;
	public int[] y;
	public int size;
	
	public FormaGeometrica(String forma,int x1, int y1, int x2, int y2, int x3, int y3, String drawString, Color culoare, Font font)
	{
		this.forma = forma;
		this.x1 = x1;
		this.x2 = x2;
		this.x3 = x3;
		this.y1 = y1;
		this.y2 = y2;
		this.y3 = y3;
		this.drawString = drawString;
		this.culoare = culoare;
		this.font = font;
	}
	
	public FormaGeometrica(String forma, int[] x, int[] y, Color culoare, int size )
	{
		this.forma = forma;
		this.x = x;
		this.y = y;
		this.culoare = culoare;
		this.size = size;
		
	}
	
	

}
