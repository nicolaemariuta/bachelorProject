package signalProcessing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.musicg.math.statistics.ZeroCrossingRate;

import javazoom.jl.decoder.JavaLayerException;

public class Functions{
	
	
	
	
	
	
	//for testing the signal processing functions
	public static void main(String[] args) {
		
	     String url = "C:\\Users\\nicolae\\Desktop\\B128.wav";    //
	//	String url = "decoder\\classic1_converted.wav";
	//		  String url = "decoder/05 Fidel.mp3";
		
		
		
		ArrayList<Double> lista  = FileReader.readAllSamples(url);
		
		
		double[] samples = new double[16384];
		for(int i = 0; i< 16384; i++)
		{
			samples[i] = lista.get(i);
			
		}
		
		System.out.println(System.currentTimeMillis());
		DFT(samples);
		System.out.println(System.currentTimeMillis());
		
	/*	System.out.println("samples");
		for(double d : samples)
		{
			System.out.println(d);
		}
		
		
		System.out.println("fullWaveRectification");
		samples = fullWaveRectification(samples);
		for(double d : samples)
		{
			System.out.println(d);
		}
		
		System.out.println("lowPassFiltering");
		samples = lowPassFiltering(samples);
		for(double d : samples)
		{
			System.out.println(d);
		}
		
		System.out.println("downsampling");
		samples = downsampling(samples);
		System.out.println(samples.length);
		for(double d : samples)
		{
			System.out.println(d);
		}
		
		System.out.println("meanRemoval");
		samples = meanRemoval(samples);
		System.out.println(samples.length);
		for(double d : samples)
		{
			System.out.println(d);
		}
		
		*/
		
	//	System.out.println("suma");
	//	double suma = sum(samples);
	//	System.out.println("Suma = " + suma);
		
		
	/*	System.out.println("autocorrelation");
		samples = autocorrelation(samples);
		System.out.println(samples.length);
		for(double d : samples)
		{
			System.out.println(d);
		}
		*/
		
		
	}
	
	//Haar Wavelet
	public static double[] discreteHaarWaveletTransform(double[] input, int nrOctaves) {
	    // This function assumes that input.length=2^n, n>1
	    double[] output = new double[input.length];
	 
	    for (int length = input.length >> 1; ; length >>= 1) {
	        // length = input.length / 2^n, WITH n INCREASING to log(input.length) / log(2)
	        for (int i = 0; i < length; ++i) {
	            double sum = input[i * 2] + input[i * 2 + 1];
	            double difference = input[i * 2] - input[i * 2 + 1];
	            output[i] = sum/2;
	            output[length + i] = difference/2;
	        }
	        nrOctaves--;
	        
	        if (length == 1 || nrOctaves == 0) {
	            return output;
	        }
	        //Swap arrays to do next iteration
	        System.arraycopy(output, 0, input, 0, length << 1);
	    }
	}
	
	//inverse Haar Vavelet Transform
	public static double[] inverseDiscreteHaarWaveletTransform(double[] input, int nrOctaves) {
	    // This function assumes that input.length=2^n, n>1
	    double[] output = new double[input.length];
	    int k = input.length;
	    
	    for(int i = 0; i< nrOctaves; i++)
	    	k /= 2;
	    
	    for (int length = k; ; length *= 2 ) {
	        // length = input.length / 2^n, WITH n INCREASING to log(input.length) / log(2)
	        for (int i = 0; i < length; ++i) {
	            double sum = input[i ] + input[i + length];
	            double difference = input[i ] - input[i + length];
	            output[2*i] = sum;
	            output[2*i+1] = difference;
	        }
	        if (length == input.length/2) {
	            return output;
	        }
	 
	        //Swap arrays to do next iteration
	        System.arraycopy(output, 0, input, 0, length << 1);
	    }
	}
	
	
	//Rectification of signal - returns absolute value of samples
	public static double[] fullWaveRectification(double[] samples)
	{
		
		for(int i = 0; i < samples.length; i++)
		{
			samples[i] = Math.abs(samples[i]);
		}
		
		return samples;
	}
	
	
	
	//Low Pass Filter based on formula output[i] = (1-alpha)*input[i] + alpha*output[i-1]
	public static double[] lowPassFiltering(double[] samples)
	{
		double alpha = 0.99;
		double[] lpf = new double[samples.length];
		lpf[0] = samples[0];
		
		for(int i =1; i<samples.length; i++)
		{
			lpf[i] = (1-alpha)*samples[i] + alpha*lpf[i-1];  	
		}
		return lpf;
	}
	
	//Downsampling functions - reads every sampleRate sample and adds it to output array
	public static double[] downsampling(double[] input, int sampleRate)
	{
		double[] output = new double[input.length/sampleRate];
		
		for(int i = 0; i<output.length; i++)
		{
			output[i] = input[i*sampleRate];
		}
		return output;
	}
	
	//normalization - calculates average for all samples values 
	//and then substract the average value from each sample
	public static double[] meanRemoval(double[] samples)
	{
		double suma = 0;
		for(double d: samples)
		{
			suma = suma + d;
		}
		double mean = suma/samples.length;
	
		for(int i =0; i< samples.length; i++)
		{
			samples[i] = samples[i] - mean;
			}
		return samples;
	}
	
	
	//sums all samples - not used 
	public static double sum(double[] samples)
	{
		double sum = 0;
		
		for(int i =0; i <samples.length; i++)
		{
		//	System.out.println("Esantion " + samples[i]);
			
			
			sum = sum + samples[i];
		}
	//	System.out.println("Suma" + sum);
		return sum;
	}
	//unused function - sums values of 2 arrays, if octave is 2^n smaller than inout, the octave values are added every 2^n index values of input
	public static double[] addOctave(double[] octave, double[] input )
	{
		double[] output =input;
		int jump = input.length/octave.length;
		
		
		
		for(int i = 0; i < octave.length; i++)
		{
			output[i*jump] = output[i*jump]+ octave[i];
		}
		
		return output;
	}
	
	
	
	
	
	
	//autocorrelation calculated between indexes start and end of the input array
	public static double[] autocorrelation(double[] input, int start, int end)
	{
		int size = input.length;
		
		double[] r = new double[size];
		double sum;
		
		for(int i = start; i<end; i++)
		{
			sum = 0;
			for(int j =0; j < size-i; j++)
			{
				sum +=input[j]*input[j+i];
			}
			r[i] = sum/(size-i);
		}
		return r;
	}
	
	
	//returns the beat histogram for the list of samples
	public static double[] getBeats(ArrayList<Double> allSamples, int windowSize, int hopSize)
	{

			ArrayList<MyMapNode> beats = new ArrayList<MyMapNode>();   //used by threads to add beat values obtained from window processing
			ArrayList<Thread> threads = new ArrayList<Thread>(); //threads for window processing to parallelize the histogram processing
			

			
			double [] output = new double[300];  //output array where beat values will be added;
			Arrays.fill(output,0);
			
			int nrThreads = 0;
			
			
			int hop = 0;

			
			while(true)
			{
				//fill samples array with the values of window
				double[] samples = new double[windowSize];
				
				for(int i = 0; i<windowSize; i++)
				{
					samples[i] = allSamples.get(i+hop);
				
				}
				//start thread for processing the window
				Thread window = new BeatExtract(samples,nrThreads, beats);
				window.start();
				threads.add(window);
		

				hop = hop+hopSize;
				
				//check if end of samples list is reached
				if(hop+windowSize>allSamples.size()-4096)
					break;
			}
			//wait until all threads are stopped
			while(true)
			{
				 boolean terminat = true;
				 for(Thread thread: threads)
				 {
					 if(thread.isAlive())
						 terminat = false;
				 }
				 if(terminat)
					 break;
				 
				// System.out.println(".");
			}
			
			//add values obtained from window processing to the beat histogram 
				for(MyMapNode node : beats)
			{
				try{
				double[] bpmValues = node.getBpmValues();
				double[] peaks = node.getPeaks();
	
				
				
				for(int i =0; i<bpmValues.length; i++ )
				{
					output[(int)bpmValues[i]] += peaks[i];
				}
				} catch (Exception e) { System.out.println("exceptie~~~~~~~~~~~~~~~~~~~~~~~~~~~");}
			}
				

				
				
			//	scale the values
			for(int i =0; i < output.length; i++)
			{
				output[i] = output[i] * 15000 / threads.size();
			}
			
			return output;
			
	}
	
	
	//Discrete Fourier Transform, apply formula directly
	public static double[] DFT(double[] samples)
	{
	
		double[] output = new double[samples.length*2];   // first half is the real part, the second half is imaginary
		
		for(int k = 0; k < samples.length; k++)
		{
			double preal = 0;
			double pimag = 0;
			
			for(int t = 0; t < samples.length; t++)
			{
				double angle = 2*Math.PI*t*k/samples.length;
				preal += samples[t] * Math.cos(angle);
				pimag += -samples[t] * Math.sin(angle);
			}
			
			output[k] = preal;
			output[k+samples.length] = pimag;
			
			
		}
		
		return output;
	}
	
	
	//Discrete Cosine Transform, apply formula directly, it only has real part
	public static double[] realDCT(double[] samples)
	{
		int n = samples.length;
		
		double[] output = new double[n];
		
		
		for(int k = 0; k < n; k++)
		{
			double sumreal = 0;

			
			for(int t = 0; t < n; t++)
			{
				double angle = 2*Math.PI*t*k/n;
				sumreal += samples[t] * Math.cos(angle);
	
			}
				output[k] = sumreal;

		}
		
		return output;
	}
	
	
	
	public static double[]  complexFFT(double[] samples, boolean DIRECT)
	{
		int n = samples.length;
		double id = Math.log(n)/Math.log(2.0);
		
		int nu = (int) id;
		int n2 = n/2;
		int nu1 = nu -1;
		double[] xReal = new double[n];
		double[] xImag = new double[n];
		double tReal, tImag, p, arg, c, s;
		
		
		//DIRECT or inverse transform
		double constant;
		if(DIRECT)
			constant = -2 * Math.PI;
		else
			constant = 2 * Math.PI;
		
		
		for(int i = 0; i<n; i++)
		{
			xReal[i] = samples[i];
			xImag[i] = 0;
		}
		
		
		// calculation
		int k = 0;
		for(int l = 1; l < nu; l++)
		{
			while(k<n)
			{
				for(int i = 1; i<= n2; i++)
				{
					p = bitreverseReference(k>>nu1, nu);
					//direct fft or inverse fft
					arg = constant * p/n;
					c = Math.cos(arg);
					s = Math.sin(arg);
					tReal = xReal[k + n2]*c + xImag[k + n2] * s;
					tImag = xImag[k + n2]*c - xReal[k + n2]*s;
					xReal[k + n2] = xReal[k] - tReal;
					xImag[k + n2] = xImag[k] - tImag;
					xReal[k] += tReal;
					xImag[k] += tImag;
					k++;	
				}
				k+= n2;
			}
			k = 0;
			nu1--;
			n2 /=2;
		}
		
		// Recombination
		k = 0;
		int r;
		
		while(k<n)
		{
			r = bitreverseReference(k, nu);
			if(r>k)
			{
				tReal = xReal[k];
				tImag = xImag[k];
				xReal[k] = xReal[r];
				xImag[k] = xImag[r];
				xReal[r] = tReal;
				xImag[r] = tImag;
			}
			
			k++;
		}
		
		//mix mix xReal and xImag to have an output array
		double[] output = new double[xReal.length*2];
		double radice = 1; //Math.sqrt(n);
		
		for(int i = 0; i< output.length; i+=2)
		{
			int i2 = i/2;
			output[i] = xReal[i2] * radice;
			output[i+1] = xImag[i2] * radice;
		}
		
		
		return output;
	}
	
	public static double[] absValue(double[] samples)
	{
		double[] output = new double[samples.length/2];
		
		for(int i = 2; i <samples.length; i+=2)
		{
			output[i/2] = Math.sqrt(Math.pow(samples[i],2) + Math.pow(samples[i+1],2));
		}
		
		return output;
	}
	
	
	public static double frameCentroid(double[] input)
	{
		double centroidSup = 0;
		double centroidInf = 0;
		input =  Functions.complexFFT(input, true );
		input = Functions.absValue(input);
		
	//	input = Functions.absValue(input);
	//	input =  Functions.realDFT(input);
		double fundamentalFreq = fundamentalFrequency(input);
	//	System.out.println(fundamentalFreq);
		fundamentalFreq = (fundamentalFreq<1) ? 1 : fundamentalFreq;
		
		
		for(int i = 0; i < input.length/2; i++)
		{
			double frequency = i*44100/ input.length;
			centroidSup += frequency*input[i];
			centroidInf += input[i];
	//		System.out.println(frequency);
	//		System.out.println("freq" +input[i]);
	//		System.out.println("inf" + centroidInf);
	//		System.out.println("sup" + centroidSup);
			
	//		break;
		}
				
		double centroid = (centroidInf == 0) ? 0 :  centroidSup/centroidInf;
	//System.out.println(centroid);
		return centroid/fundamentalFreq;
	}
	
	//Spectral centroid applied over windows of 1024 samples each
	public static double centroid(double[] input)
	{
		
		int frameSize = 1024;
		int index = 0;
		double centroid = 0;
		int contor = 0;

			while(true)
		{
			double[] frame = new double[frameSize];
			for(int i = 0; i < frameSize; i++)
			{
				frame[i] = input[i+index];
				index++;
			}
			double tempCentroid = frameCentroid(frame);

			centroid += tempCentroid;

			contor++;
			
			if( (index) >= (input.length - frameSize*2))
			 break;
			
		}
		contor = contor*10;
		
		return centroid/contor;
	}
	
	//calculats energy of the audio signal by applying the equation: E = sumAll(sample[i]^2) 
	public static double energy(double[] samples)
	{
		double energy = 0;
		
		for(int i = 0; i < samples.length; i++)
		{
			energy += Math.pow(samples[i] , 2);
		}
		
		
		return 200*energy/samples.length;   //Returns averga Energy
	}
	
	
	
	
	//counts number of crossings from negative sample values to positive sample values or otherwise
	public static double zeroCrossingRate(double[] input)
	{
		double scr = 0;
		
		for(int i = 1; i < input.length; i++)
		{
			scr += Math.abs(sgn(input[i]) - sgn(input[i-1]));
		}
		
		scr  = scr/input.length;
		return scr * 50;
	}
	
	
	private static    int sgn(double n)
	{
		return (n<0) ? 0 : 1;
	}
	
	
	
	public static double fundamentalFrequency(double[] input)
	{
		double frequency = 0;
		double magnitude = 0;
		
		for(int i = 0; i < input.length/2; i++ )
		{
			if(input[i] > magnitude)
			{
				frequency = i*44100/ input.length;
				magnitude = input[i];
			}
		}
		
		return frequency;
		
	}
	
	
	 private static int bitreverseReference(int j, int nu) {
			int j2;
			int j1 = j;
			int k = 0;
			for (int i = 1; i <= nu; i++) {
			    j2 = j1 / 2;
			    k = 2 * k + j1 - 2 * j2;
			    j1 = j2;
			}
			return k;
		    }
	
	 
	 
	 public static double[] multiplePeakPicking(double[] input)
	 {
		 double previous = input[0];
		 double previousSlope = 0;
		 ArrayList<Double> peaks = new ArrayList<Double>();
		 
		 
		 double[] output = new double[input.length];
		
	

		 
		 for(int i = 0; i < input.length; i++)
		 {
			double slope = input[i] - previous;
			
	//		System.out.println(slope);
			
			if(slope*previousSlope <0 )
			{
				output[i] = previous;
				peaks.add(previous);
			}
			
			previousSlope = slope;
			previous = input[i];
			
			
				
		 }
	
		 return output;
	 }
	 
	 
	 //spectral flux
	 public static double spectralFlux(double[] input)
	 {
		 int frameSize = 1024;
		 double[] prevFrame = null;
		 
		 int contor = 0;
		 int index = 1024;
		 double flux = 0;
		 
			while(true)
			{
				double tempFlux = 0;
				
				double[] frame = new double[frameSize];
				for(int i = 0; i < frameSize; i++)
				{
				
					frame[i] = input[i+index];
					index++;
				}
				
				frame =  Functions.complexFFT(frame, true );
				frame = Functions.absValue(frame);
				frame = Functions.meanRemoval(frame);
				
				
				if(prevFrame != null)
				{
					for(int i = 0; i < frameSize; i++)
					{
						tempFlux += Math.pow(frame[i]- prevFrame[i],2);
					}
					
				}
				flux += tempFlux;
				prevFrame = frame;
				
				contor++;
				if( (index) >= (input.length - frameSize*2))
				 break;
			}
			
			contor = contor * 441;
					 
		 return flux/contor;
	}
	 
	 //calculates Spectral rolloff for a window of 1024 samples
	 public static double spectralRolloff(double[] input)
	 {
		 double rolloff = 0;
	
		 input = Functions.complexFFT(input, true);
		 input = Functions.absValue(input);
		 
		 double roll1 = 0;
		 for(int i =0; i < input.length/2; i++)
		 {
			 roll1 += input[i];
		 }
		 
		 int index = 0;
		 double roll2 = 0;
		 for(int i =0; i < input.length/2; i++)
		 {
			 roll2 += input[i];
			 
			 if(roll2 > (0.85 * roll1))
				 break;
			 
		
			 index = i;
		 }
		 
		 rolloff = index * 44100 / input.length;

		 
		 return rolloff;
	 }
	 
	 //splits the audios signal into windows to calulate rolloff and the makes an average of it
	 public static double spectralRolloffAverage(double[] input)
	 {
		 int frameSize = 1024;
	
		  int contor = 0;
		 int index = 0;
		 double rolloff = 0;
		 
			while(true)
			{
				double tempRolloff = 0;
				
				double[] frame = new double[frameSize];
				for(int i = 0; i < frameSize; i++)
				{
				
					frame[i] = input[i+index];
					index++;
				}
				
				tempRolloff =  Functions.spectralRolloff(frame);
			
				rolloff += tempRolloff;
	
				contor++;
				if( (index) >= (input.length - frameSize*2))
				 break;
			}
			
			contor = contor * 4410 * 2;
					 
		 return rolloff/contor;
		 
	 }
	 

}
