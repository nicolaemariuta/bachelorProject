package claseUtile;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import mainPack.FereastraPrincipala;
import mainPack.SuprafataDesenare;

public class SaveJPEG extends Thread
{
	SuprafataDesenare plansa;    
	String url;                
	FereastraPrincipala fp;     
	
	public SaveJPEG(SuprafataDesenare plansa, String url,FereastraPrincipala fp)
	{
		this.plansa = plansa;
		this.url = url;
		this.fp = fp;
	}
	
	public void run()
	{
		
		try
		{
			
			BufferedImage image = new BufferedImage(plansa.getWidth(), plansa.getHeight(), BufferedImage.TYPE_INT_RGB);
			BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(url)) ;

			Graphics2D graphics = image.createGraphics();
	
			fp.panelPlansa.paintAll(graphics);
		
			ImageIO.write(image, "jpg", fos);
			
			fos.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}
	

}
