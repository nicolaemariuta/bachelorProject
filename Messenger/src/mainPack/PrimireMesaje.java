package mainPack;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.*;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class PrimireMesaje extends Thread

{
	FereastraPrincipala frame;
	public static final int PORT = 8223;
	InetAddress adress;
	DatagramSocket socket = null;
	DatagramPacket cerere, raspuns = null;
	String nume;
	JTextPane textPane;
	
	PrimireMesaje (JTextPane textPane, FereastraPrincipala frame)
	{
		this.textPane = textPane;
		this.frame = frame;
		
	}
	
	public void run()
	{
		
		try
		{
			socket = new DatagramSocket(PORT);
			while (true)
			{
				// declaram pachetul in care va fi receptionata cererea
				byte[] buf = new byte[1024];
				cerere = new DatagramPacket(buf, buf.length);
			

				socket.receive(cerere);
		
				
				// aflam adresa si portul de la care vine cererea
				InetAddress adresa = cerere.getAddress();
				frame.adress = adresa;
				int port = cerere.getPort();
			
			
				String mesaj = new String(cerere.getData());
				
				
				if (mesaj.contains("~cerere"))
				{

					nume = mesaj.substring(7) ;
					frame.setTitle("You talk to   "+nume);
					frame.adress = cerere.getAddress();
					
					
					String rasp = "cerere"+frame.nume;
					buf = rasp.getBytes();
					raspuns = new DatagramPacket (buf, buf.length, adresa, port);
					System.out.println("Am trimis");
					
	
				}
				else
					
					
					if(mesaj.contains("FISIER"))
					{
						try
						{
						String numeFisier = mesaj.substring(5);
						final String extensieFisier = mesaj.substring(mesaj.indexOf("."));
						
					
						
						System.out.println(mesaj);
					
						String sdimensiune =  mesaj.substring(7, mesaj.indexOf("~"));
						System.out.println("Dimensiune " + sdimensiune);
					
						int size  = Integer.parseInt(sdimensiune);
						
						
						
						Document doc = frame.textPane.getDocument();
						doc.insertString(doc.getLength(),("\n cineva vrea sa iti trimita fisierul "+ mesaj), null);
						
						
						FileDialog fd = new FileDialog(frame, "Choose File", FileDialog.SAVE);
						fd.setDirectory("C:/");
						fd.setFilenameFilter(new FilenameFilter()
						{
							public boolean accept(File dir, String numeFis)
							{
								return (numeFis.endsWith(extensieFisier));
							}
							
						});
						
						fd.show();
						new PrimireFisier(this.frame, fd.getDirectory()+fd.getFile(),size).start();
						
						
						
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				
				else
				{
					
					try
					{
						
			
		
						Document doc = frame.textPane.getDocument();
						doc.insertString(doc.getLength(), mesaj, null);
						
					}
				
			catch(Exception EE) {}
					
				}
	
				
			}
		} 
		catch ( IOException e)
		{
		
			e.printStackTrace();
		}
		
		finally
		{
			if (socket != null)
			{
				socket.close();
			}
		}
	}
	
	

}
