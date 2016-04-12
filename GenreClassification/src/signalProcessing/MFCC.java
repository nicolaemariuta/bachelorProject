package signalProcessing;

import java.util.ArrayList;

public class MFCC {
	
	double[] input;
	private int frameSize = 1024;
	private double startFreq = 300;
	private double endFreq = 8000;
	private int nrCoef= 26;
	double[] melCoef;
	
	public MFCC(double[] input)
	{
		this.input = input;
		System.out.println(input.length/2);
		melCoef = new double[nrCoef/2+1];
		
		int index = 0;
		
		//split input samples to frames
		while(true)
		{
			
			double[] frame = new double[frameSize];
			
			
			for(int i = 0; i< frameSize; i++)
			{
				frame[i] = input[i+index];
				index++;
			}
	

				calculateMFCC(frame);
	
			
			
		
			
			if( (index) >= (input.length - frameSize*2))
				 break;
			
		
			
		}
		
		
		
	//	for(int i = 0;  i < melCoef.length; i++)
	//		System.out.println(melCoef[i]);
	}
	
	
	public void calculateMFCC(double[] frame)
	{
		//calculate DFT and periodogram
		frame = Functions.complexFFT(frame, true);
		frame = Functions.absValue(frame);
		
		for(int i = 0; i < frame.length; i++)
		{
			frame[i] = Math.pow(frame[i], 2);
		}
		
		ArrayList<double[]> filterBank = new ArrayList<double[]>();
		double[] frequencys = new double[nrCoef+2];
		frequencys[0] = startFreq;
		

		//compute Mel-spaced filterbank
		
		//calculate mels and frequencies
		double[] mels = new double[nrCoef+2];
		mels[0] = 1125 * Math.log(1+startFreq/700);
		mels[nrCoef+1] = 1125 * Math.log(1+endFreq/700);
		double jump= (mels[nrCoef+1] - mels[0])/(nrCoef+1);
		

		
		for(int i = 1; i < mels.length; i++)
			mels[i] = mels[i-1] + jump;

		
		for(int i = 0; i < frequencys.length; i++)
			frequencys[i] = 700*(Math.pow(Math.E, mels[i]/1125)-1);
			
	
		
		
		//calculate points where to place filters
		double[] fresolution = new double[frequencys.length];
		
		for(int i = 0; i < fresolution.length; i++)
			fresolution[i] =  Math.round(1024*frequencys[i]/44100);
	
			
		//create the filterbanks: first filterbank will start at the first point, 
		//reach its peak at the second point, then return to zero at the 3rd point
		
		for(int i = 1; i < nrCoef+1; i++)
		{
			double[] subband = new double[frame.length];
			
			
			for(int k = 0; k < subband.length; k++)
			{
				if(k<fresolution[i-1])
					subband[k] = 0;

				
				if(k>=fresolution[i-1] && k <= fresolution[i])
					subband[k] = (k-fresolution[i-1])/(fresolution[i]-fresolution[i-1]);
				
					
				
				if(k>=fresolution[i] && k <= fresolution[i+1])
					subband[k] = (fresolution[i+1]-k)/(fresolution[i+1]-fresolution[i]);
				
				if(k>fresolution[i+1])
					subband[k] = 0;
				
				
		//		System.out.println(k + "->" + subband[k]);
		
			}
	//		System.out.println("~~~~~~~~~~~~~~~~~~");

				filterBank.add(subband);
			
		}
		
		
		//apply the filterbank over the original signal: multiply filters coefficients with the power spectrum
		//the add the coefficients to calculate filterbank energies
		double[] energies = new double[nrCoef];
		for(int i = 0; i < filterBank.size(); i++)
		{
			double coefficient = 0;
			
			for(int k =0; k < frameSize; k++)
				coefficient += filterBank.get(i)[k]*frame[k];
	
			energies[i] = coefficient;
			
		}
		
		
		//calculate log of each of energy
		for(int i = 0; i < energies.length; i++ )
			energies[i] = Math.log(energies[i]);
		
		
		//apply cosine transform to obtain the MFCC
		double[] mfcc = Functions.realDCT(energies);
		
		for(int i = 0; i< melCoef.length; i++)
			{
				if(!(Double.isNaN(mfcc[i])|| Double.isInfinite(mfcc[i])))
				{
					if(melCoef[i] == 0)
					{
						melCoef[i] = mfcc[i];
					}
					else
					{
						melCoef[i] = 0.9*melCoef[i] + 0.1*mfcc[i];
					}
				}
			}
		
	//	System.out.println("%%%%%");
		

		
		
	}
	
	
	
	
	
	public int getNrCoef() {
		return nrCoef;
	}


	public void setNrCoef(int nrCoef) {
		this.nrCoef = nrCoef;
	}


	public double[] getMelCoef() {
		return melCoef;
	}


	public void setMelCoef(double[] melCoef) {
		this.melCoef = melCoef;
	}


	public static void main(String[] args) {

		String url = "decoder\\classic1_converted.wav";
	//		  String url = "decoder/05 Fidel.mp3";
		
		
		
		ArrayList<Double> lista  = FileReader.readAllSamples(url);
		
		
		double[] samples = new double[lista.size()];
		for(int i = 0; i< lista.size(); i++)
		{
			samples[i] = lista.get(i);
			
		}
		
		
		MFCC test = new MFCC(samples);
		
		
		
	}

}
