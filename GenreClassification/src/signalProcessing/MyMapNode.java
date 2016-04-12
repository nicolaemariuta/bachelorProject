package signalProcessing;
//own map containing key for sorting and 2 arrays of doubles
public class MyMapNode implements Comparable<MyMapNode>{
	
	int key;
	double[] bpmValues;
	double[] peaks;
	
	MyMapNode(int key, double[] bmpValues, double[] peaks)
	{
		this.key = key;
		this.bpmValues = bmpValues;
		this.peaks = peaks;
	}

	

	@Override
	public int compareTo(MyMapNode o) {
		if(key == o.key)
			return 0;
		
		else if(key <o.key)
			return -1;
		else return 1;
			
	}
	
	public String toString()
	{
		return "(key="+key; // + ",value="+value+")";
	}



	public int getKey() {
		return key;
	}



	public void setKey(int key) {
		this.key = key;
	}



	public double[] getBpmValues() {
		return bpmValues;
	}



	public void setBpmValues(double[] bmpValues) {
		this.bpmValues = bmpValues;
	}



	public double[] getPeaks() {
		return peaks;
	}



	public void setPeaks(double[] peaks) {
		this.peaks = peaks;
	}




}
