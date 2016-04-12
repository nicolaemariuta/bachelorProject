package signalProcessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javazoom.jl.decoder.JavaLayerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

//plot view controller class for tests
public class DisplayPlotController implements Initializable{
	
	
	private static final int WINDOWSIZE = 131072;
	private static final int HOPSIZE = 65536;
	String path;
    final ObservableList<XYChart.Series<Double, Double>> lineChartData = FXCollections.observableArrayList();
    final LineChart.Series<Double, Double> series = new LineChart.Series<Double, Double>();
     String url = "C:\\Users\\nicolae\\Desktop\\B128.wav";    //
  //  String url = "decoder\\classic1_converted.wav";
  //  String url = "D:\\facultate\\SpecialWorkspace\\GenreClassification\\decoder\\sample.wav";
    @FXML
    private ResourceBundle resources;
    
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private URL location;

    @FXML
    private LineChart<Double, Double> beatHistogram;
    

    @FXML
    private Button fileChooser;

    @FXML
    private TextField filePath;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        assert beatHistogram != null : "fx:id=\"beatHistogram\" was not injected: check your FXML file 'DisplayPlot.fxml'.";
        assert fileChooser != null : "fx:id=\"fileChooser\" was not injected: check your FXML file 'DisplayPlot.fxml'.";
        assert filePath != null : "fx:id=\"filePath\" was not injected: check your FXML file 'DisplayPlot.fxml'.";

        beatHistogram.setTitle("Beat Histogram");
        

        
        
        
        
        
    
        
        fileChooser.setOnAction(new EventHandler<ActionEvent>()
        		{

					@Override
					public void handle(ActionEvent event) {
					/*	FileChooser fileChooser = new FileChooser();
						fileChooser.setTitle("Choose music file");
						File file = fileChooser.showOpenDialog(anchorPane.getScene().getWindow());
						
						path = file.getAbsolutePath();
						filePath.setText(path);*/
						
						
						try {
							addLine5();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
						
					}
        	
        		});
		
	}
	
	
	public void addLine1()
	{
		int jump = 65536 * 2;
		int nrOctaves = 10;
		
		
		ArrayList<Double> allSamples = FileReader.readAllSamples(url);
		System.out.println(allSamples.size());
		double[] samples = new double[65536 * 2];
		
		for(int i = 0; i< samples.length; i++)
		{
			samples[i] = allSamples.get(i);
		}
		
		
	//	samples = Functions.downsampling(samples,2);
		samples  =  Functions.discreteHaarWaveletTransform(samples, nrOctaves);
			//samples = Functions.downsampling(samples,8);
	
		
		for(int i = 0; i<samples.length; i++)
	{
		if( i< samples.length/2)
		{
			samples[i] = 0;
		
		}
	}
		
	
		
		
		
		
	samples  =  Functions.inverseDiscreteHaarWaveletTransform(samples, nrOctaves);
//	samples  =  Functions.discreteHaarWaveletTransform(samples, 3);
		
		samples = Functions.fullWaveRectification(samples);
		samples = Functions.lowPassFiltering(samples);
		samples = Functions.downsampling(samples, 32);
		samples = Functions.meanRemoval(samples);
	//	samples = Functions.realDFT(samples);
	//	samples = Functions.complexFFT(samples, true );
//		samples = Functions.absValue(samples);
		
		
	//	double sum = Functions.sum(samples);
	//	System.out.println(sum);
		 

        // add values to diagram
        for(int i =0; i<samples.length; i++)
        {
        	//System.out.println(samples[i]);
        	
     //   	System.out.println("index " + i + "= " + samples[i]);
		series.getData().add(new XYChart.Data<Double, Double>((double) i, samples[i]));
		//series.getData().add(new XYChart.Data<Double, Double>((double) i*2+1, new Double(0)));  //-Math.random()*4));    //new Double(0)));
        }
        
        
        
        
	
        
        lineChartData.add(series);
        beatHistogram.setData(lineChartData);
	}
	
	
	public void addLine2()
	{
		ArrayList<Double> allSamples = FileReader.readAllSamples(url);
		System.out.println(allSamples.size());
		
		double[] samples = new double[1024];
		
		for(int i = 0; i< samples.length; i++)
		{
			samples[i] = allSamples.get(i+65536);
		}
		
		
		samples = Functions.DFT(samples);
	//samples = Functions.complexFFT(samples, true);
//	samples = Functions.absValue(samples);
		

        // add values to diagram
        for(int i =0; i<samples.length; i++)
        {
        	System.out.println(i + "-> " +samples[i]);
		series.getData().add(new XYChart.Data<Double, Double>((double) i, samples[i]));
		//series.getData().add(new XYChart.Data<Double, Double>((double) i*2+1, new Double(0)));  //-Math.random()*4));    //new Double(0)));
        }
	
        
        lineChartData.add(series);
        beatHistogram.setData(lineChartData);
        
		
		
		
	}
	
	
	public void addLine3()
	{
		
		int jump = 65536 *1;
		
		
		ArrayList<Double> allSamples = FileReader.readAllSamples(url);
		System.out.println(allSamples.size());
		double[] samples = new double[8192];
		
		for(int i = 0; i< samples.length; i++)
		{
			samples[i] = allSamples.get(i+jump);
		}
		
		
		samples = Functions.downsampling(samples,32);
		samples  =  Functions.discreteHaarWaveletTransform(samples, 3);
	//	samples = Functions.fullWaveRectification(samples);
	//	samples = Functions.lowPassFiltering(samples);
	//  samples = Functions.downsampling(samples, 32);
	//	samples = Functions.meanRemoval(samples);
		
		
		
		double sum = Functions.sum(samples);
		System.out.println(sum);
		 

        // add values to diagram
        for(int i =0; i<samples.length; i++)
        {
        	//System.out.println(samples[i]);
		series.getData().add(new XYChart.Data<Double, Double>((double) i, samples[i]));
		//series.getData().add(new XYChart.Data<Double, Double>((double) i*2+1, new Double(0)));  //-Math.random()*4));    //new Double(0)));
        }
	
        
        lineChartData.add(series);
        beatHistogram.setData(lineChartData);
        
		
		
		
	}
	
	
	public void addLine4()
	{
		int jump = 65536 *1;
		
		
		ArrayList<Double> allSamples = FileReader.readAllSamples(url);
		System.out.println(allSamples.size());
		double[] samples = new double[65536*2];
		double[] output = new double[2048];
		
		for(int i = 0; i< samples.length; i++)
		{
			samples[i] = allSamples.get(i);
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		samples = Functions.downsampling(samples, 4);
		
		samples = Functions.discreteHaarWaveletTransform(samples, 8);
		
	
	/*	
		int size = 256;
		int contor = 255;
		while(true)
		{
			double[] octave = new double[size];
			System.out.println("size = "+octave.length);
			
			
			for(int i = 0; i < octave.length; i++)
			{
				//System.out.println(contor);
				octave[i] = samples[contor];
				contor++;
			}
			size = size*2;
			
			
			System.out.println("contor = "+contor);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			
			octave = Functions.fullWaveRectification(octave);
			octave = Functions.lowPassFiltering(octave);
			octave = Functions.downsampling(octave, 16);
			octave = Functions.meanRemoval(octave);
			
			
			Functions.addOctave(octave, output);
			
			
			
			
			if(contor == (samples.length-1))
				break;
		}*/
		 
		 
		// output = Functions.autocorrelation(output);
		 
		 /////////////////////////////////////////////////////////////////////////////////////////////////
		 
        for(int i =0; i<samples.length; i++)
        {
		series.getData().add(new XYChart.Data<Double, Double>((double) i, samples[i]));
		//series.getData().add(new XYChart.Data<Double, Double>((double) i*2+1, new Double(0)));  //-Math.random()*4));    //new Double(0)));
        }
	
        
       lineChartData.add(series);
       beatHistogram.setData(lineChartData);
 	
	}
	
	
	
	public void addLine5()
	{
		int jump = 65536 * 10;
		int nrOctaves = 6;
		
		ArrayList<Double> allSamples = FileReader.readAllSamples(url);
		System.out.println(allSamples.size());
		double[] samples = new double[(int) Math.pow(2,17)];
		double[] output = new double[samples.length/32];
		Arrays.fill(output, 0);
		
		for(int i = 0; i< samples.length; i++)
		{
			samples[i] = allSamples.get(65536+i);
		}
		
		
		samples  =  Functions.discreteHaarWaveletTransform(samples, nrOctaves);
		//samples = Functions.downsampling(samples,8);
		
		
		int minBond = 2;
		int maxBond = 1;
		
		
		while(maxBond < Math.pow(nrOctaves,2))
		{
			double[] temp = new double[samples.length];
			System.out.println(minBond);
			for(int i = 0; i < temp.length; i++)
			{
				
				
				if((i<temp.length/minBond && minBond !=  Math.pow(nrOctaves+1,2)) || i> temp.length/maxBond)
					temp[i] = 0;
				else
					temp[i] = samples[i];
			}
			
			
			
			temp = Functions.inverseDiscreteHaarWaveletTransform(temp, nrOctaves);
			temp = Functions.fullWaveRectification(temp);
			temp = Functions.lowPassFiltering(temp);
			temp = Functions.downsampling(temp, 32);
			temp = Functions.meanRemoval(temp);
			
		temp = Functions.discreteHaarWaveletTransform(temp, nrOctaves);
			
			
			for(int i = 0; i < temp.length; i++)
			{
				output[i] = output[i] + temp[i];
			}
			
			minBond *= 2;
			maxBond *= 2;
			
		}
		
		output  =  Functions.inverseDiscreteHaarWaveletTransform(output, nrOctaves);
		
		int start = (60*500)/ 200;
		int end = (60*500)/ 30;
		
		System.out.println("start=" + start);
		System.out.println("end=" + end);
		
		
		output = Functions.autocorrelation(output, 0, output.length);
		output = Functions.multiplePeakPicking(output);
		
	/*for(int i = 0; i < output.length; i++)
		{
			if(output[i] < 0)
				output[i] = 0;
		}
		
		double[] timeScaled = new double[output.length*2];
		
		for(int i = 0; i< timeScaled.length; i++)
		{
			timeScaled[i] = output[i/2];
		}
		*/
	
		
	//	output = Functions.complexFFT(output, true );
		
//		output = 	Functions.multiplePeakPicking(output);
		
	/*	try {
			PrintWriter write = new PrintWriter(new File("C:\\Users\\nicolae\\Desktop\\peaks.txt"));
			
			for(int i =0; i < output.length; i++)
			{
				if(output[i] < 0)
					output[i] = 0;
				
				write.println(output[i]);
			}
			
			write.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	//	int scaleFactor = 2;
		
//		output = Functions.autocorrelation(output);
		
		
		
		
	//	output = Functions.fullWaveRectification(output);
		
/*	samples  =  Functions.inverseDiscreteHaarWaveletTransform(samples, 4);
		
	//	samples = Functions.fullWaveRectification(samples);
	//	samples = Functions.lowPassFiltering(samples);
		samples = Functions.downsampling(samples, 32);
	//	samples = Functions.meanRemoval(samples);
	//	samples = Functions.realDFT(samples);
	//	samples = Functions.complexFFT(samples, 44100 );
		
		
	//	double sum = Functions.sum(samples);
	//	System.out.println(sum);
		 */

        // add values to diagram
		
		System.out.println("lungime finala" + output.length);
        for(int i =0; i<output.length; i++)
        {
        	//System.out.println(samples[i]);
        	
     //   	System.out.println("index " + i + "= " + samples[i]);
		series.getData().add(new XYChart.Data<Double, Double>((double) i, output[i]));
		//series.getData().add(new XYChart.Data<Double, Double>((double) i*2+1, new Double(0)));  //-Math.random()*4));    //new Double(0)));
        }
        
        
        
        
	
        
        lineChartData.add(series);
        beatHistogram.setData(lineChartData);
	}
	
	
	public void addLine6() throws JavaLayerException
	{
		String url2 = FileReader.fileConvert(url);
		ArrayList<Double> allSamples = FileReader.readAllSamples(url2);
		
		
		double[] histogram = Functions.getBeats(allSamples, 131072, 8192);
		for(int i =0; i<60; i++)
		{
			histogram[i] = 0;
		}
		
		System.out.println("lungime finala" + histogram.length);
        for(int i =0; i<histogram.length; i++)
        {
        	//System.out.println(samples[i]);
        	
     //   	System.out.println("index " + i + "= " + samples[i]);
		series.getData().add(new XYChart.Data<Double, Double>((double) i, histogram[i]));
		//series.getData().add(new XYChart.Data<Double, Double>((double) i*2+1, new Double(0)));  //-Math.random()*4));    //new Double(0)));
        }
        
        
        
        
	
 
        lineChartData.add(series);
        
        beatHistogram.setData(lineChartData);
	}

}