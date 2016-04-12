package mainPack;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

public class PrimireFisier extends Thread

{
	InetAddress adresa = null;
	FereastraPrincipala fp;
	String url;
	int filesize;
	
	
	PrimireFisier(FereastraPrincipala fp, String url, int filesize)
	{
		this.fp = fp;
		this.url = url;
		this.filesize = filesize;
	}
	
	
	public void run()
	{
		
		try
		{
		
			
			filesize = 6022386;

		    long start = System.currentTimeMillis();
		    int bytesRead;
		    int current = 0;
		    // localhost for testing
		    Socket sock = new Socket(fp.adress,8221);
		    System.out.println("Connecting...");
		    System.out.println("Socket primire: "+sock);
		    // receive file
	
		    byte [] mybytearray  = new byte [filesize];

		    InputStream is = sock.getInputStream();
		    FileOutputStream fos = new FileOutputStream(url);
		    BufferedOutputStream bos = new BufferedOutputStream(fos);
		    bytesRead = is.read(mybytearray,0,mybytearray.length);
		    current = bytesRead;
		    
		    System.out.println("current: " + current);
		    System.out.println("mybytearray.length: " + mybytearray.length );
		
		    
		       do {
		    	  
		    	   bytesRead = is.read(mybytearray, current, (mybytearray.length-current));
		    	   if(bytesRead >= 0) current += bytesRead;
		       		} while(bytesRead > -1);

		    bos.write(mybytearray, 0 , current);
		    bos.flush();
		    long end = System.currentTimeMillis();
		    System.out.println(end-start);
		    bos.close();
		    sock.close();
			System.out.println("Fisierul primit");
		
		}

	    catch (Exception e)
	    {
	 
	    	e.printStackTrace();
	    	
	    }
		
			
			
			
		
	}
	

}
