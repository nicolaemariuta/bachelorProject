package signalProcessing;

import java.util.ArrayList;
import java.util.Collections;

import javazoom.jl.decoder.JavaLayerException;

public class TestProgram {
	
	private static final int WINDOWSIZE = 131072;
	private static final int HOPSIZE =   8192;   //65536;
	
	
	public static void main(String[] args) throws JavaLayerException {
		
		String url = "decoder\\sample_converted.wav";
		

		

		double[]  autocor = null; //Functions.getBeats(url, WINDOWSIZE, HOPSIZE);
		
		for(int i =0; i<autocor.length; i++)
		{
			System.out.println(autocor[i]);
		}
		
		
		
		
		
	}
	
	
	

}
