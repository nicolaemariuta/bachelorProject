package claseUtile;

import java.awt.*;

public class Culoare extends Canvas 
{
	public Color color = new Color(0,0,0,255);
	Dimension canvasSize = new Dimension (150,50);
	
	public void paint(Graphics g)
	{
		
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, 12));
		String text = "";
		text += " R= " + color.getRed();
		text += " G= " + color.getGreen();
		text += " B= " + color.getBlue();
		text += " A= " + color.getAlpha();
		g.drawString(text, 0, 90);
		
		g.setColor(color);
		g.fillRect(0,0, 200, 70);
		
		
		
	}

}
