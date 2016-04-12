package mainPack;

import java.io.IOException;
import java.net.*;

public class TrimitereMesaje extends Thread

{
	String IP;
	int port = 8224;
	InetAddress adresa = null;
	DatagramSocket socket = null;
	DatagramPacket packet = null;
	byte[] buf;
	String mesaj;
	
	TrimitereMesaje(String mesaj, InetAddress adresa) throws UnknownHostException
	{
		this.adresa = adresa;
		this.mesaj = mesaj;
	}
	
	public void run() 
	{
		try
		{
			socket = new DatagramSocket();
			buf = mesaj.getBytes();
			packet = new DatagramPacket (buf, buf.length, adresa, port);
			socket.send(packet);
			
		}
		catch (Exception e)
		{
			 e.printStackTrace();
		}
		
		finally
		{
			if(socket!= null)
			{
				socket.close();
			}
		}
	}

}
