package signalProcessing;

import java.util.ArrayList;

public class BeatExtract extends Thread{
	
	double[] samples;
	
	int index;
	ArrayList<MyMapNode> beats;
	
	
	BeatExtract (double[] samples, int index, ArrayList<MyMapNode> beats )
	{
		this.samples = samples;
		this.index = index;
		this.beats = beats;
		
	//	System.out.println("beatextract:" + index);
	}
	
	
	public void run() 
	{

		samples = Functions.downsampling(samples, 2);
		int nrOctaves = 10;
		
		
		// apply discrete wavelet transform
		samples  =  Functions.discreteHaarWaveletTransform(samples, nrOctaves);
		//samples = Functions.downsampling(samples,8);
		double[] summedOctaves = new double[samples.length/32];
		
		
		
	
		int minBond = 2;
		int maxBond = 1;
		
		//split signal to octaves
		while(maxBond < Math.pow(nrOctaves,2))
		{
			double[] temp = new double[samples.length];
		//	System.out.println(minBond);
			for(int i = 0; i < temp.length; i++)
			{
				if((i<temp.length/minBond && minBond !=  Math.pow(nrOctaves+1,2)) || i> temp.length/maxBond)
					temp[i] = 0;
				else
					temp[i] = samples[i];
			}
			
			
			//envelope extraction
			temp = Functions.inverseDiscreteHaarWaveletTransform(temp, nrOctaves);
			temp = Functions.fullWaveRectification(temp);
			temp = Functions.lowPassFiltering(temp);
			temp = Functions.downsampling(temp, 32);
			temp = Functions.meanRemoval(temp);
			
		temp = Functions.discreteHaarWaveletTransform(temp, nrOctaves);
			
			//octaves sum
			for(int i = 0; i < temp.length; i++)
			{
				summedOctaves[i] = summedOctaves[i] + temp[i];
			}
			
			minBond *= 2;
			maxBond *= 2;
			
		}
		
		summedOctaves  =  Functions.inverseDiscreteHaarWaveletTransform(summedOctaves, nrOctaves);
		
		
		//autocorellation start and end point calculation
		int decimate =500;
		
		int start = (60*decimate)/ 200;
		int end = (60*decimate)/ 60;
		
		
		double[] autocorrelation =  Functions.autocorrelation(summedOctaves,  start,  end);
		samples =  Functions.multiplePeakPicking(autocorrelation);
	//	samples = Functions.fullWaveRectification(samples);
		
	//	System.out.println("contor"+contor);
		//take first 5 peaks and their amplitude
		int nrPeaks =5;
		double[] bpmValues = new double[nrPeaks];
		double[] peaks = new double[nrPeaks];
		
		for(int l = 0; l< nrPeaks; l++ )
		{
			int index = 0;
			
			for(int i = 0; i < end; i++)
			{
				if(samples[i] > samples[index])
					index = i;
			
			}
			
			peaks[l] = samples[index];
			
			//convert to bpm
			try{
			bpmValues[l] = 60 * decimate / index;
			}
			catch(ArithmeticException e)
			{
				bpmValues[l] = 0;
			}
			
			samples[index] = 0;
		}
		
		try
		{
		beats.add(new MyMapNode(index, bpmValues, peaks));    // cauzeaza exceptie uneori !!!!!!
		}catch (Exception e) {}
		
	}
	
	
	
	
	

}
