package signalProcessing;

import java.io.*;
import java.util.ArrayList;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import connectorsAndConverters.Converter;
import connectorsAndConverters.SingleChannelStereoConverter;
import connectorsAndConverters.WavFile;
import connectorsAndConverters.WavFileException;



import javazoom.jl.converter.Converter.ProgressListener;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.OutputChannels;
import javazoom.jl.decoder.Decoder;


public class FileReader {
	
	
	public static void main(String[] args) {
	
		String inFile = "decoder\\sample.mp3";
		String outFile = "decoder\\sample.wav";
		String outFileMono = "decoder\\sample_converted.wav";
		

	
		
		
		
		
		//WAV READER
	    try
	      {
	    	 // Open the wav file specified as the first argument
	         WavFile wavFile = WavFile.openWavFile(new File(outFile));
	    	
	         
	         
	         // Display information about the wav file
	         wavFile.display();
	    	
	       
	    	
	        // Get the number of audio channels in the wav file
	         int numChannels = wavFile.getNumChannels();
	       //  System.out.println("numChannels=" + numChannels);
	    	
	    
	         
	         
	      // Create a buffer of 100 frames for setereo
	         double[] buffer = new double[100 * numChannels];
	         
	         
	         // Create a buffer of 100 frames for mono
	     //   buffer = new double[100];
	         
	         
	         int framesRead;
	         double min = Double.MAX_VALUE;
	         double max = Double.MIN_VALUE;
	         
	         int contor = 0;
	         
         do
	         {
	            // Read frames into buffer stereo
        	framesRead = wavFile.readFrames(buffer, 100);
	            
	            
	            // Read frames into buffer mono
        //	 	framesRead = wavFile.readFramesMono(buffer, 100);
	            
	            
	            contor++;
	      //      System.out.println(contor);
	            if(contor ==300)
	               {
	            	System.out.println(buffer[30]);
	  	       		System.out.println(buffer[31]);
	  	       		System.out.println(buffer[32]);
	  	       		System.out.println(buffer[33]);
	  	       		System.out.println(buffer[34]);
	  	       		System.out.println(buffer[35]);
	  	       		System.out.println(buffer[36]);
	  	       		System.out.println(buffer[37]);
	  	       		System.out.println(buffer[38]);
	  	       		System.out.println(buffer[39]);
	  	       		System.out.println(buffer[40]);
	  	       		System.out.println(buffer[41]);
	               }
	            
	            
	            // Loop through frames and look for minimum and maximum value
	            for (int s=0 ; s<framesRead * numChannels ; s++)
	            {
	               if (buffer[s] > max) max = buffer[s];
	               if (buffer[s] < min) min = buffer[s];
	            }
	         }
	         while (framesRead != 0);
	         
	         
	         
	      
	         
	         
	         

	         // Close the wavFile
	         wavFile.close();

	         // Output the minimum and maximum value
	         System.out.printf("Min: %f, Max: %f\n", min, max);
	    	

	      }
	      catch (Exception e)
	      {
	         System.err.println(e);
	      }
		
		
	}
	
	public static long framesNumber(String url)
	{
		 try {
			WavFile wavFile = WavFile.openWavFile(new File(url));
			return wavFile.getNumFrames();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return 0;
		} catch (WavFileException e) {
			// TODO Auto-generated catch block
			return 0;
		}
		 
	}
	
	//converts mp3 files to wav format if the file is not already in wav format
	public static String fileConvert(String url)
	{
		String end = url.substring(url.length()-4);
		String out = url.substring(0, url.length()-4);
		out = "decoder\\converted.wav";       //out + ".wav";
		
		
		if(end.equals(".mp3"))
		{
			
			//MP3 to .WAV
			Converter convertor = new  Converter();
				try {
					convertor.convert(url, out);
					
					
				} catch (JavaLayerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		else if(end.equals(".wav"))
		{
			out = url;     // file is already wav
		}
		else
		{
			out = "ERROR";
		}
		
		return out;
		
	}
	
	//read all samples from audio file and add them to an arraylist
	public static ArrayList<Double> readAllSamples(String url)
	{
		ArrayList<Double> lista  = new ArrayList<Double>();
		
		
		
		//WAV READER
	    try
	      {
	    	 // Open the wav file specified as the first argument
	         WavFile wavFile = WavFile.openWavFile(new File(url));
	    	
	         
	         
	         // Display information about the wav file
	  //       wavFile.display();
	    	
	       
	    	
	        // Get the number of audio channels in the wav file
	         int numChannels = wavFile.getNumChannels();
	         String mono = "D:\\facultate\\SpecialWorkspace\\GenreClassification\\decoder\\converted_mono.wav";
	         
	         
	 /*       if(numChannels != 1)
	         {
	        	 SingleChannelStereoConverter.convert(url, mono);
	         }
	         else
	         {
	        	 mono = url;
	         }
	         
	         System.out.println("mono = " + mono);
	         
	         
	         wavFile = WavFile.openWavFile(new File(mono));*/
	         
	         
	        System.out.println("numChannels=" + wavFile.getNumChannels());
	    	System.out.println("Frecventa= " +  wavFile.getSampleRate());
	    	System.out.println("NrEsantioane= " +  wavFile.getNumFrames());
	         
	         
	      // Create a buffer of 100 frames for setereo
	         double[] buffer = new double[100 ];  //* numChannels];
	         
	         
	         // Create a buffer of 100 frames for mono
	     //   buffer = new double[100];
	         
	         
	         int framesRead;
	         double min = Double.MAX_VALUE;
	         double max = Double.MIN_VALUE;
	         
	         int contor = 0;
	         
         do
	         {
	            // Read frames into buffer stereo
        	framesRead = wavFile.readFrames(buffer, 100);
        //	System.out.println("framesRead1 =" + framesRead);
        	
       // 	int fr = wavFile.readFramesToMono(buffer, 0, numChannels);
        	//System.out.println("framesRead2 =" + fr);
	            
	            
	            // Read frames into buffer mono
      //	 	framesRead = wavFile.readFramesMono(buffer, 100);
	            
	            
	            contor++;
	 //           System.out.println(contor);
	/*            if(contor ==100)
	               {
	            	System.out.println(buffer[30]);
	  	       		System.out.println(buffer[31]);
	  	       		System.out.println(buffer[32]);
	  	       		System.out.println(buffer[33]);
	  	       		System.out.println(buffer[34]);
	  	       		System.out.println(buffer[35]);
	  	       		System.out.println(buffer[36]);
	  	       		System.out.println(buffer[37]);
	  	       		System.out.println(buffer[38]);
	  	       		System.out.println(buffer[39]);
	  	       		System.out.println(buffer[40]);
	  	       		System.out.println(buffer[41]);
	               }
	            wavFile
	            
	            // Loop through frames and look for minimum and maximum value
	            for (int s=0 ; s<framesRead * numChannels ; s++)
	            {
	               if (buffer[s] > max) max = buffer[s];
	               if (buffer[s] < min) min = buffer[s];
	            }*/
	            
	            
	            
	           
	            
	            for (int s=0 ; s<framesRead  ; s += numChannels) //* numChannels)
	            {
	            	
	            	lista.add(buffer[s]);
	            }
	            
	            
	            if(lista.size() > 15000000)
	            	break;
	            
	            
	         }
	         while (framesRead != 0);
	         
	         
	         
	      
	         
	         
	         

	         // Close the wavFile
	         wavFile.close();

	         // Output the minimum and maximum value
	  //       System.out.printf("Min: %f, Max: %f\n", min, max);
	    	

	      }
	      catch (Exception e)
	      {
	         System.err.println(e);
	      }
		
		
		
		
		return lista;
	}
	

}
