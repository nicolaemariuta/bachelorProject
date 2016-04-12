package mainPack;
import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;



public class TrimitereFisiere extends Thread

{
	
	
	FereastraPrincipala fp;
	String url;
	
	public TrimitereFisiere(FereastraPrincipala fp,String url ) 
	{
		this.fp = fp;
		this.url =url;
	
	}
	
	
	public void run ()
	{
		

	
		try
		{
			  ServerSocket servsock = new ServerSocket(8221);
			    while (true) 
			    {
			      System.out.println("Waiting...");

			      Socket sock = servsock.accept();
			      System.out.println("Accepted connection : " + sock);

			      // sendfile
			      File myFile = new File (url);
			      byte [] mybytearray  = new byte [(int)myFile.length()];
			      System.out.println("Dimensiunea fisierului la trimitere:" + (int)myFile.length());
			      FileInputStream fis = new FileInputStream(myFile);
			      BufferedInputStream bis = new BufferedInputStream(fis);
			      bis.read(mybytearray,0,mybytearray.length);
			      OutputStream os = sock.getOutputStream();
		
			      System.out.println("Dimensiunea array la trimitere: " + mybytearray.length);
			      os.write(mybytearray,0,mybytearray.length);
		
			      os.flush();
			      sock.close();
			      break;
		
			    }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

			}

}

