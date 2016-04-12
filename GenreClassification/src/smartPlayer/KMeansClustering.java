package smartPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class KMeansClustering {
	
	
	ArrayList<Song> songs = new ArrayList<Song>();
	
	ArrayList<ArrayList<Song>> clusters = new ArrayList<ArrayList<Song>>();
	
	
	KMeansClustering(ArrayList<Song> songs)
	{
		this.songs = songs;		
		int nrClusters = 6;
		int nrSteps = 150;
		
		//Step 1 :initialize centroids with random songs
		ArrayList<Song> centroids = new ArrayList<Song>();
		
		for(int i =0;  i<nrClusters; i++)
		{
			while(true)  //because we do not want same song again
			{
				int random = (int) (Math.random()*songs.size());

				if(!centroids.contains(songs.get(random)))
				{
					centroids.add(songs.get(random));
					break;
				}
			}
		}
		
	//	System.out.println(centroids.size());
		
		for(int i = 0; i < nrSteps; i++ )
		{
			//Stept 2: clusters building
			clusters.clear();
			//assign each cluster a centroid
			for(int k =0; k<centroids.size(); k++)
			{
				ArrayList<Song> cluster = new ArrayList<Song>();
				cluster.add(centroids.get(k));
				clusters.add(cluster);
			}
			
			
			//fill clusters with closest element
			for(Song song: songs)
			{
				int indexCluster = getClosestCentroid(song, centroids);
				clusters.get(indexCluster).add(song);
			}
			
			//recalculate new centroids
			centroids.clear();
			for(ArrayList<Song> cluster : clusters)
			{
				//Timbral texture features
				double centroid = 0;
				double rolloff = 0;
				double spectralFlux = 0;
				double zeroCrossings = 0;
				double energy = 0;
				
				//rhytm features; based on the beat histogram
				//first peak
				double period0 = 0;
				double amplitude0 = 0;
				double ratioPeriod1 = 0;
				
				//second peak
				double period1 = 0;
				double amplitude1 = 0;
				double ratioPeriod2 = 0;
				
				//thrid peak
				double period2 = 0;
				double amplitude2 = 0;
				double ratioPeriod3 = 0;
				
				//fourth peak
				double period3 = 0;
				double amplitude3 = 0;
				
				
				//mfcc
				double mfcc0 = 0;
				double mfcc1 = 0;
				double mfcc2 = 0;
				double mfcc3 = 0;
			//	double mfcc4 = 0;
			//	double mfcc5 = 0;
				
			
				
				for(Song song : cluster)
				{
					//Timbral texture features
					centroid += song.getCentroid();
					rolloff += song.getRolloff();
					spectralFlux += song.getSpectralFlux();
					zeroCrossings += song.getZeroCrossings();
					energy += song.getEnergy();
					
					//rhytm features; based on the beat histogram
					//first peak
					period0 += song.getPeriod0();
					amplitude0 += song.getAmplitude0();
					ratioPeriod1 += song.getRatioPeriod1();
					
					//second peak
					period1 += song.getPeriod1();
					amplitude1 += song.getAmplitude1();
					ratioPeriod2 += song.getRatioPeriod2();
					
					//thrid peak
					period2 += song.getPeriod2();
					amplitude2 += song.getAmplitude2();
					ratioPeriod3 += song.getRatioPeriod3();
					
					//fourth peak
					period3 += song.getPeriod3();
					amplitude3 += song.getAmplitude3();
					
					//mfcc
					double[] mfcc = song.getMfcc();
					mfcc0 += mfcc[0];
					mfcc1 += mfcc[1];
					mfcc2 += mfcc[2];
					mfcc3 += mfcc[3];
				//	mfcc4 += mfcc[4];
				//	mfcc5 += mfcc[5];
				
				}
				
				double[] mfcc = {mfcc0/cluster.size(), mfcc1/cluster.size(), mfcc2/cluster.size(), mfcc3/cluster.size(),};
				
				Song song1 = new Song("c", "c", centroid/cluster.size(), rolloff/cluster.size(), spectralFlux/cluster.size(), zeroCrossings/cluster.size(),energy/cluster.size(),
						period0/cluster.size(), amplitude0/cluster.size(), ratioPeriod1/cluster.size(),
						period1/cluster.size(),amplitude1/cluster.size(), ratioPeriod2/cluster.size(), 
							period2/cluster.size(),amplitude2/cluster.size(), ratioPeriod3/cluster.size(), 
								period3/cluster.size(),amplitude3/cluster.size());
				song1.setMfcc(mfcc);
				
				
				int index = getClosestCentroid(song1, songs);
				
				
				centroids.add(songs.get(index));
				
				if(i == 30)
				
				{
					
					try {
					PrintWriter write = new PrintWriter(new File("C:\\Users\\nicolae\\Desktop\\knn\\clusters.csv"));
					
					for(Song song: songs)
					{
						int find = 0;
						
						for(ArrayList<Song> search : clusters)
						{
							if(search.contains(song))
							{
								find = clusters.indexOf(search);
								break;
							}
						}
						
						
						
						String toPrint= song.getCentroid() +"," + song.getRolloff() + "," + song.getSpectralFlux() + "," + song.getZeroCrossings() + "," +song.getEnergy() + "," + song.getPeriod0() +
								"," + song.getAmplitude0() + "," + song.getPeriod1()  + "," + song.getRatioPeriod1() + "," + song.getAmplitude1() + "," +  song.getPeriod2()  + "," +song.getRatioPeriod2() + "," + song.getAmplitude2() + "," + song.getPeriod3()  + "," + song.getRatioPeriod3()
								+ "," + song.getAmplitude3() + ","  + song.getMfcc()[0] + "," + song.getMfcc()[1] + ","+ song.getMfcc()[2] + ","
										+ song.getMfcc()[3] + ","+ song.getMfcc()[4] +"," + song.getMfcc()[5] + ","+ "clusterul" + find;
						
					//	if(song.getGenre().equals("rock") || song.getGenre().equals("classical"))
								write.println(toPrint);
					}
					
					write.close();
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				}
			}
			
			
		
			
		}
		
	}
	
	
	
	
	public ArrayList<ArrayList<Song>> getClusters() {
		return clusters;
	}


	//Euclidian distance between 2 songs
	private static double getDistance(Song song1, Song song2)
	{
		double distance = 0;
		//timbral texturea features
		distance += Math.pow(song1.getCentroid() - song2.getCentroid(),2);
		distance += Math.pow(song1.getRolloff() - song2.getRolloff(),2);
		distance += Math.pow(song1.getSpectralFlux() - song2.getSpectralFlux(),2);
		distance += Math.pow(song1.getZeroCrossings() - song2.getZeroCrossings(),2);
		distance += Math.pow(song1.getEnergy() - song2.getEnergy(),2);
		//histogram peak 1
		distance += Math.pow(song1.getPeriod0() - song2.getPeriod0(),2);
		distance += Math.pow(song1.getAmplitude0() - song2.getAmplitude0(),2);
		distance += Math.pow(song1.getRatioPeriod1() - song2.getRatioPeriod1(),2);
		//histogram peak2
		distance += Math.pow(song1.getPeriod1() - song2.getPeriod1(),2);
		distance += Math.pow(song1.getAmplitude1() - song2.getAmplitude1(),2);
		distance += Math.pow(song1.getRatioPeriod2() - song2.getRatioPeriod2(),2);
		//histogram peak3
		distance += Math.pow(song1.getPeriod2() - song2.getPeriod2(),2);
		distance += Math.pow(song1.getAmplitude2() - song2.getAmplitude2(),2);
		distance += Math.pow(song1.getRatioPeriod3() - song2.getRatioPeriod3(),2);
		//histogram peak4
		distance += Math.pow(song1.getPeriod3() - song2.getPeriod3(),2);
		distance += Math.pow(song1.getAmplitude3() - song2.getAmplitude3(),2);
		
		//distance mfcc;
		double[] mfcc1 = song1.getMfcc();
		double[] mfcc2 = song2.getMfcc();
		
		
	//	System.out.println(mfcc1[0]);

		
		distance+= Math.pow(mfcc1[0] - mfcc2[0], 2);
		distance+= Math.pow(mfcc1[1] - mfcc2[1], 2);
		distance+= Math.pow(mfcc1[2] - mfcc2[2], 2);
		distance+= Math.pow(mfcc1[3] - mfcc2[3], 2);
	//	distance+= Math.pow(mfcc1[4] - mfcc2[4], 2);
	//	distance+= Math.pow(mfcc1[5] - mfcc2[5], 2);
	
		
		
		return Math.sqrt(distance);
	}
	//find closest centroid
	private static int getClosestCentroid(Song song, ArrayList<Song> centroids)
	{
	//	System.out.println(centroids.size());
		int index = 0;
		double distance = getDistance(song, centroids.get(0));
		
		for(int i = 1; i< centroids.size(); i++)
		{
			double newDistance =getDistance(song, centroids.get(i));
			
			if(newDistance < distance){
				index = i;
				distance = newDistance;
			}
		}
		
		return index;
	}
	
	//get closest neighbour in cluster
	private static int getClosestNeighbour(Song song, ArrayList<Song> cluster, ArrayList<Song> played)
	{
	//	System.out.println(centroids.size());
		int index = 0;
		double distance = getDistance(song, cluster.get(0));
		
		for(int i = 1; i< cluster.size(); i++)
		{
			double newDistance =getDistance(song, cluster.get(i));
			
			if((newDistance < distance) && (!played.contains(cluster.get(i))) ){
				index = i;
				distance = newDistance;
			}
		}
		
		return index;
	}
	
	//find nearest neighbour that is not in played list
	public Song getKnearest(Song song, ArrayList<Song> played )
	{
		if(played.size() > 5)
		{
			played.remove(0);
		}
		ArrayList<Song> clusterSearch = null;
		
		for(ArrayList<Song> cluster : clusters)
		{
			if(cluster.contains(song))
			{
				clusterSearch = cluster;
				break;
			}
		}
		
		int index = getClosestNeighbour(song, clusterSearch, played);
		
		return clusterSearch.get(index);
		
	}

}
