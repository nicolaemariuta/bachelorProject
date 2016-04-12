package smartPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import javazoom.jl.decoder.JavaLayerException;
import signalProcessing.FileReader;
import signalProcessing.Functions;
import signalProcessing.MFCC;
import connectorsAndConverters.HsqldbConnection;

public class Song implements Serializable{
	
	
	//name and song location
	String name;     //file name
	String filePath; // full path to the location of file
	String genre;    // music genre; in case we know it from before; mostly to check the efficiency of classification algorithm
	
	//AUDIO PROCESSING parameters
	
	//Timbral texture features
	double centroid;
	double rolloff;
	double spectralFlux;
	double zeroCrossings;
	double energy;
	
	//rhytm features; based on the beat histogram
	//first peak
	double period0;
	double amplitude0;
	double ratioPeriod1;
	
	//second peak
	double period1;
	double amplitude1;
	double ratioPeriod2;
	
	//thrid peak
	double period2;
	double amplitude2;
	double ratioPeriod3;
	
	//fourth peak
	double period3;
	double amplitude3;
	
	
	//histogram 
	double[] histogram;
	
	//mel-frequency cepstral coefficients
	double[] mfcc;
	
	
	//contructor 
	public Song(String name, String filePath)
	{
		this.name = name;
		this.filePath = filePath;
	}
	
	//another constructor
	public Song(String name, String filePath, 
				double centroid, double rolloff, double spectralFlux, double zeroCrossings, double energy,
				double period0, double amplitude0, double period1, double ratioPeriod1, double amplitude1, 
				double period2, double ratioPeriod2, double amplitude2, double period3,double ratioPeriod3, double amplitude3)
	{
		this.name = name;
		this.filePath = filePath;
		
		this.centroid = centroid;
		this.rolloff = rolloff;
		this.spectralFlux = spectralFlux;
		this.zeroCrossings = zeroCrossings;
		this.energy = energy;
		
		this.period0 = period0;
		this.amplitude0 = amplitude0;
		this.ratioPeriod1 = ratioPeriod1;
		
		this.period1 = period1;
		this.amplitude1 = amplitude1;
		this.ratioPeriod2 = ratioPeriod2;
		
		this.period2 = period2;
		this.amplitude2 = amplitude2;
		this.ratioPeriod3 = ratioPeriod3;
		
		this.period3 = period3;
		this.amplitude3 = amplitude3;
		
	

	}

	
	//setters and getters
	
	
	public String getName() {
		return name;
	}

	public double getPeriod1() {
		return period1;
	}

	public void setPeriod1(double period1) {
		this.period1 = period1;
	}

	public double getPeriod2() {
		return period2;
	}

	public void setPeriod2(double period2) {
		this.period2 = period2;
	}

	public double getPeriod3() {
		return period3;
	}

	public void setPeriod3(double period3) {
		this.period3 = period3;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getCentroid() {
		return centroid;
	}

	public void setCentroid(double centroid) {
		this.centroid = centroid;
	}

	public double getRolloff() {
		return rolloff;
	}

	public void setRolloff(double rolloff) {
		this.rolloff = rolloff;
	}

	public double getSpectralFlux() {
		return spectralFlux;
	}

	public void setSpectralFlux(double spectralFlux) {
		this.spectralFlux = spectralFlux;
	}

	public double getZeroCrossings() {
		return zeroCrossings;
	}

	public void setZeroCrossings(double zeroCrossings) {
		this.zeroCrossings = zeroCrossings;
	}

	public double getPeriod0() {
		return period0;
	}

	public void setPeriod0(double period0) {
		this.period0 = period0;
	}

	public double getAmplitude0() {
		return amplitude0;
	}

	public void setAmplitude0(double amplitude0) {
		this.amplitude0 = amplitude0;
	}

	public double getRatioPeriod1() {
		return ratioPeriod1;
	}

	public void setRatioPeriod1(double ratioPeriod1) {
		this.ratioPeriod1 = ratioPeriod1;
	}

	public double getAmplitude1() {
		return amplitude1;
	}

	public void setAmplitude1(double amplitude1) {
		this.amplitude1 = amplitude1;
	}

	public double getRatioPeriod2() {
		return ratioPeriod2;
	}

	public void setRatioPeriod2(double ratioPeriod2) {
		this.ratioPeriod2 = ratioPeriod2;
	}

	public double getAmplitude2() {
		return amplitude2;
	}

	public void setAmplitude2(double amplitude2) {
		this.amplitude2 = amplitude2;
	}

	public double getRatioPeriod3() {
		return ratioPeriod3;
	}

	public void setRatioPeriod3(double ratioPeriod3) {
		this.ratioPeriod3 = ratioPeriod3;
	}

	public double getAmplitude3() {
		return amplitude3;
	}

	public void setAmplitude3(double amplitude3) {
		this.amplitude3 = amplitude3;
	}

	public double[] getMfcc() {
		return mfcc;
	}

	public void setMfcc(double[] mfcc) {
		this.mfcc = mfcc;
	}

	public double[] getHistogram() {
		return histogram;
	}

	public void setHistogram(double[] histogram) {
		this.histogram = histogram;
	}
	
	//toString method
	public String toString()
	{
		return this.getName();
	}
	

	/*
	//database insert,updates
	
	public static HsqldbConnection createTable(String tableName)
	{

		 HsqldbConnection db = null;

	        try {
	            db = new HsqldbConnection("songs_list");
	        } catch (Exception ex1) {
	            ex1.printStackTrace();    // could not start db

	            return null;                   // bye bye
	        }
	        // querry to create the table
	        String querry =  "CREATE TABLE " + tableName + " ( id INTEGER IDENTITY, name VARCHAR(256), file_path VARCHAR(256), genre VARCHAR(256),"
	        		+ "centroid DOUBLE, rolloff DOUBLE, spectralFlux DOUBLE,  zeroCrossings DOUBLE,"
	        		+ "period0 DOUBLE, amplitude0 DOUBLE, ratioPeriod1 DOUBLE,"
	        		+ "amplitude1 DOUBLE, ratioPeriod2 DOUBLE,"
	        		+ "amplitude2 DOUBLE, ratioPeriod3,"
	        		+ "amplitude3 DOUBLE, ratioPeriod4)";
	        
		try{
	          db.update(querry);
	       
	          
	          } 
		
		
		catch (SQLException ex2) {
	        	  
	          }
		 
		return db;

	}
	
	
	//insert song to database
	public static void insertSongDb(Song song, String tableName, HsqldbConnection db)
	{
	
		
		 
	
		 
		 try
		 {
		
			 String querry;
			 
			 
		if(song.getGenre() == null)
		{
			querry = "INSERT INTO "+tableName+"(name,file_path, centroid, rolloff, spectralFlux, zeroCrossings,"
					+ "period0, amplitude0, ratioPeriod1, amplitude1, ratioPeriod2, amplitude2, ratioPeriod3, amplitude3, ratioPeriod4)"
					+ "VALUES('"+song.getName()+"','"+song.getFilePath()+"',"+song.getCentroid()+", "+song.getRolloff()+", "+song.getSpectralFlux()+","+song.getZeroCrossings()+" "
							+ ","+song.getPeriod0()+", "+song.getAmplitude0()+", "+song.getRatioPeriod1()+" , "+song.getAmplitude1()+", "+song.getRatioPeriod2()+","
									+ ", "+song.getAmplitude2()+", "+song.getRatioPeriod3()+", " +song.getAmplitude3()+","+song.getRatioPeriod4()+") ";
			
		}
		
		else
		{
			
			querry = "INSERT INTO "+tableName+"(name,file_path, genre,centroid, rolloff, spectralFlux, zeroCrossings,"
					+ "period0, amplitude0, ratioPeriod1, amplitude1, ratioPeriod2, amplitude2, ratioPeriod3, amplitude3, ratioPeriod4)"
					+ "VALUES('"+song.getName()+"','"+song.getFilePath()+"','"+song.getGenre()+"',"+song.getCentroid()+", "+song.getRolloff()+", "+song.getSpectralFlux()+","+song.getZeroCrossings()+" "
							+ ","+song.getPeriod0()+", "+song.getAmplitude0()+", "+song.getRatioPeriod1()+" , "+song.getAmplitude1()+", "+song.getRatioPeriod2()+","
									+ ", "+song.getAmplitude2()+", "+song.getRatioPeriod3()+", " +song.getAmplitude3()+","+song.getRatioPeriod4()+") ";
		}
		System.out.println(querry);
		db.update(querry);
		 
		 // do a query
		//      db.query("SELECT * FROM "+tableName);
		 
		 }
		 catch (SQLException ex3) {
            ex3.printStackTrace();
        }
		 
		
		
		
	}
	
	*/
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Song> readSongsFile(String url)
	{
		ArrayList<Song> songs = null;

		try{
			FileInputStream fileIn = new FileInputStream(url);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Object o = in.readObject(); 
			
			if(o instanceof ArrayList)
			{
				songs = (ArrayList<Song>) o;
			}
			
			
			in.close();
			fileIn.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();}
		catch(IOException e){
			e.printStackTrace();}
		catch (ClassNotFoundException e) {
			e.printStackTrace();} 
	
		
		return songs;
	}
	
	
	public static void  writeSongsFile(String url, ArrayList<Song> songs)
	{
		try{
			FileOutputStream fileOut = new FileOutputStream(url);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(songs);
			out.close();
			fileOut.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();}
		catch(IOException e){
			e.printStackTrace();}
	}
	
	//create song with parameters using the url of music file
	public static Song getSongFromFile(String fileName, String filePath)
	{
		Song song = new Song(fileName, filePath);
		
		String fileProcess = FileReader.fileConvert(filePath);
		
		ArrayList<Double> lista  = FileReader.readAllSamples(fileProcess);
		
		int size = lista.size() >15000000 ? 15000000 : lista.size();
		double[] samples = new double [size];
		
		for(int i =0; i <size; i++)
		{
			samples[i] = lista.get(i);
		}
		
		System.out.println("%%%");
		song.setCentroid(0.1*Functions.centroid(samples));
		song.setRolloff(0.1*Functions.spectralRolloffAverage(samples));
		song.setSpectralFlux(0.1*Functions.spectralFlux(samples)) ;
		song.setZeroCrossings(0.1*Functions.zeroCrossingRate(samples));
		song.setEnergy(0.1*Functions.energy(samples));
		
		System.out.println("%");
		double[] beatHistogram = Functions.getBeats(lista, 131072, 8192);
		System.out.println("%%");
		for(int i =0; i < 50; i++)
		{
			beatHistogram[i] = 0;
		}
		
		
		song.setHistogram(beatHistogram);
		
		//sum amplitudes
		double sumAmplitudes = 0;
		
		for(int i =0; i < beatHistogram.length; i++)
		{
			sumAmplitudes += beatHistogram[i];
		}
		
		//first peak
		double period0 = 0;
		double amplitude0 = 0;
		
		for(int i = 0; i < beatHistogram.length; i++ )
		{
			if(amplitude0< beatHistogram[i])
			{
				period0 = i;
				amplitude0 = beatHistogram[i];
			}
				
		}
	//	System.out.println("period0 = " + period0);
		song.setPeriod0(period0/1000);
		beatHistogram[(int) period0] = 0;
		song.setAmplitude0(amplitude0/sumAmplitudes);
		
		//second peak
		double period1 = 0;
		double amplitude1 = 0;
		
		for(int i = 0; i < beatHistogram.length; i++ )
		{
			if(amplitude1< beatHistogram[i] && period0 != period1)
			{
				period1 = i;
				amplitude1 = beatHistogram[i];
			}
				
		}
		beatHistogram[(int) period1] = 0;
		song.setRatioPeriod1(period1/period0);
		song.setPeriod1(period1/1000);
	//	System.out.println("ratioperiod1 = " + song.getRatioPeriod1());
	//	System.out.println("period1 = " + period1);
		song.setAmplitude1(amplitude1/sumAmplitudes);
		
		
		
		//third peak
		double period2 = 0;
		double amplitude2 = 0;
		
		for(int i = 0; i < beatHistogram.length; i++ )
		{
			if(amplitude2< beatHistogram[i])
			{
				period2 = i;
				amplitude2 = beatHistogram[i];
			}
				
		}
		beatHistogram[(int) period2] = 0;
		song.setRatioPeriod2(period2/period1);
		song.setPeriod2(period2/1000);
	//	System.out.println("ratioperiod2 = " + song.getRatioPeriod2());
	//	System.out.println("period2 = " + period2);
		song.setAmplitude2(amplitude2/sumAmplitudes);
		
		
		//third peak
		double period3 = 0;
		double amplitude3 = 0;
				
		for(int i = 0; i < beatHistogram.length; i++ )
			{
			if(amplitude3< beatHistogram[i])
				{
					period3 = i;
					amplitude3 = beatHistogram[i];
				}
						
			}
		song.setPeriod3(period3/1000);
		beatHistogram[(int) period3] = 0;
		song.setRatioPeriod3(period3/period2);
	//	System.out.println("ratioperiod3 = " + song.getRatioPeriod3());
	//	System.out.println("period3 = " + period3);
		song.setAmplitude3(amplitude3/sumAmplitudes);
		System.out.println("%%%%%");
	//mfcc for the song
		MFCC coefficients = new MFCC(samples);
		double[] mfcc = coefficients.getMelCoef();
		song.setMfcc(mfcc);
		
		
		
		return song;
	}
	
	
	
	public boolean equals(Object o)
	{
		if(o instanceof Song)
			return this.getFilePath().equals(((Song) o).getFilePath()) && this.getName().equals(((Song) o).getName());
		else
			return false;
	}
	
	
	
	
	//functions testing
	public static void main(String[] args) {
		
	/*	Song song1 = new Song("song1", "path1");
		Song song2 = new Song("song2", "path2");
		
		ArrayList<Song> songs = new ArrayList<Song>();
		songs.add(song1);
		songs.add(song2);
		
		writeSongsFile("test_serialization", songs);
		
		ArrayList<Song> songs2 = readSongsFile("test_serialization");
		System.out.println(songs2.size());*/
		
		
		
		ArrayList<Song> songs =readSongsFile("lista_melodii_procesate");
		
		/*
		for(Song song: songs)
		{
			String fileProcess = FileReader.fileConvert(song.getFilePath());
			
			ArrayList<Double> lista  = FileReader.readAllSamples(fileProcess);
			
			int size = lista.size() >15000000 ? 15000000 : lista.size();
			double[] samples = new double [size];
			
			for(int i =0; i <size; i++)
			{
				samples[i] = lista.get(i);
			}
			
			MFCC coefficients = new MFCC(samples);
			double[] mfcc = coefficients.getMelCoef();
			song.setMfcc(mfcc);
			
			System.out.println("Ajuns la " + songs.indexOf(song) + " din " + songs.size());
			
		}
		
		writeSongsFile("lista_melodii_procesate", songs); */
		
	/*	String url = "C:\\Users\\nicolae\\Desktop\\melodii\\rock";
		
		File folder = new File(url);
		File[] listOfFiles = folder.listFiles();
		
		int index = 0;
		
		try{
		for(File file: listOfFiles)
		{
			if(file.isFile())
			{
				String fileName = file.getName();
				String filePath = file.getPath();
				System.out.println(fileName);
				Song newSong = new Song(fileName, filePath);
				
				
				if(!songs.contains(newSong))
				{
					Song addSong = Song.getSongFromFile(fileName, filePath);
					addSong.setGenre("rock");
					songs.add(addSong);
					index++;
				}
				
				
			}
			
			System.out.println("Ajuns la" + songs.size());
			
			if(index == 50)
				break;
		}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}
		System.out.println("Terminat la" + songs.size());
		writeSongsFile("lista_melodii_procesate", songs);  
	
		
		
//		System.out.println(songs.size());*/
		
	
		
		
	for(Song song : songs)
		{
//	System.out.println(song.getMfcc()[0]);
		
	//		song.setCentroid(song.getCentroid()*0);
	//		song.setRolloff(song.getRolloff()*0);
	//		song.setSpectralFlux(song.getSpectralFlux()*0) ;
	//		song.setZeroCrossings(song.getZeroCrossings()*0);
	//		song.setEnergy(song.getEnergy()*0);
		
		/*	song.setPeriod0(song.getPeriod0());
			song.setPeriod1(song.getPeriod1());
			song.setPeriod2(song.getPeriod2());
			song.setPeriod3(song.getPeriod3()*0);
			song.setRatioPeriod1(song.getRatioPeriod1());
			song.setRatioPeriod2(song.getRatioPeriod2());
			song.setRatioPeriod3(song.getRatioPeriod3());
			song.setAmplitude0(song.getAmplitude0());
			song.setAmplitude1(song.getAmplitude1());
			song.setAmplitude2(song.getAmplitude2());
			song.setAmplitude3(song.getAmplitude3());*/
			
	/*		
			song.setCentroid(0);
			song.setRolloff(0);
			song.setSpectralFlux(0) ;
			song.setZeroCrossings(0);
			song.setEnergy(0);
		
			song.setPeriod0(0);
			song.setPeriod1(0);
			song.setPeriod2(0);
			song.setPeriod3(0);
			song.setRatioPeriod1(0);
			song.setRatioPeriod2(0);
			song.setRatioPeriod3(0);
			song.setAmplitude1(0);
			song.setAmplitude2(0);
			song.setAmplitude3(0);*/
			
			
	
		
			double[] mfcc = song.getMfcc();
		//	mfcc[0] /= 2;
		//	mfcc[1] *= 10;
			
			
		/*	mfcc[2] *= 8.5;
			mfcc[3] *= 8.5;
			mfcc[4] *= 8.5;
			mfcc[5] *= 8.5;
			
			
			
		/*	mfcc[5] *= 0;
			mfcc[4] *= 0;
			mfcc[3] *= 0;
			mfcc[2] *= 0;
			mfcc[1] *= 0;
	//		mfcc[0] *= 0;*/
		/*	mfcc[0]   /= 170;
			mfcc[1]  /= 100;
			mfcc[2]  /= 100;
			mfcc[3] /= 100;
			mfcc[4] /= 100;
			mfcc[5] /= 100;
			song.setMfcc(mfcc);
		*/
		
	//	System.out.println(song.getPeriod0());
		}
		
		
		
		
		
		
		KMeansClustering clustering = new KMeansClustering(songs);
		
		ArrayList<ArrayList<Song>> clustere = clustering.getClusters();
		
		
		
		for(ArrayList<Song> cluster: clustere)
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			int classical = 0;
			int country = 0;
			int disco = 0;
			int hiphop = 0;
			int jazz = 0;
			int rock = 0;
			
			for(Song song: cluster)
			{
				if(song.getGenre().equals("classical"))
					classical++;
				
				if(song.getGenre().equals("country"))
					country++;
				
				if(song.getGenre().equals("disco"))
					disco++;
				
				if(song.getGenre().equals("hiphop"))
					hiphop++;
				
				if(song.getGenre().equals("jazz"))
					jazz++;
				
				if(song.getGenre().equals("rock"))
					rock++;
				
				
			}
			
			
			System.out.println("Classical " + classical);
			System.out.println("country " + country);
			System.out.println("disco " + disco);
			System.out.println("hiphop " + hiphop);
			System.out.println("jazz " + jazz);
			System.out.println("rock " + rock);
			
		}
	
	
	try {
		PrintWriter write = new PrintWriter(new File("C:\\Users\\nicolae\\Desktop\\songs.csv"));
		
			for(Song song: songs)
		{
			String toPrint= song.getCentroid() +"," + song.getRolloff() + "," + song.getSpectralFlux() + "," + song.getZeroCrossings() + "," +song.getEnergy() + "," + song.getPeriod0() +
					"," + song.getAmplitude0() + "," + song.getPeriod1()  + "," + song.getRatioPeriod1() + "," + song.getAmplitude1() + "," +  song.getPeriod2()  + "," +song.getRatioPeriod2() + "," + song.getAmplitude2() + "," + song.getPeriod3()  + "," + song.getRatioPeriod3()
					+ "," + song.getAmplitude3() + ","  + song.getMfcc()[0] + "," + song.getMfcc()[1] + ","+ song.getMfcc()[2] + ","
							+ song.getMfcc()[3] + ","+ song.getMfcc()[4] +"," + song.getMfcc()[5] + ","+ song.getGenre();
			
		//	if(song.getGenre().equals("rock") || song.getGenre().equals("classical"))
					write.println(toPrint);
		}
		
		
	//	write.println( "Genre" + " ; "+ "ZeroCrossings" + " ; " +"Energy");
	/*	
		int rockContor = 0;
		int classicalContor = 0;
		
		
		//write.println("coef0" + " ; "+ "coef1" + " ; " + "coef2"  + " ; "+ "coef3" + " ; "+ "coef4" + " ; "+ "coef5" + " ; "+ "genre");
	
	for(Song song: songs)
		{
		
			
			
			if(song.getGenre().equals("rock") && rockContor<70)
			{
			
				String toPrint= song.getMfcc()[0] + ";" + song.getMfcc()[1] + ";"+ song.getMfcc()[2] + ";"
						+ song.getMfcc()[3] + ";"+ song.getMfcc()[4] +";" + song.getMfcc()[5] + ";"+ song.getGenre();
				write.println(toPrint);
				rockContor++;
			}
			
			
			
			

			if(song.getGenre().equals("classical")&& classicalContor<70)
			{
			
				String toPrint= song.getMfcc()[0] + ";" + song.getMfcc()[1] + ";"+ song.getMfcc()[2] + ";"
						+ song.getMfcc()[3] + ";"+ song.getMfcc()[4] +";" + song.getMfcc()[5] + ";"+ song.getGenre();
				write.println(toPrint);
				classicalContor++;
			}*/
	
	//	}
		
		
		
	write.close();
		
	} catch (FileNotFoundException e) {
	//	 TODO Auto-generated catch block
		e.printStackTrace();
	}
	//writeSongsFile("lista_melodii_procesate", songs);
		
		
	}
	
}
