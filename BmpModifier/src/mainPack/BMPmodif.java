package mainPack;

import java.io.*;

public class BMPmodif

{
	 
	 public static void BMPmod(String in,String out, double bplus, double bori, int bmodulo, double gplus, double gori, int gmodulo, double rplus, double rori, int rmodulo) throws IOException
	{

		
		int B;
		int G;
		int R;
		
		
	
		BufferedInputStream fisier = new BufferedInputStream(new FileInputStream(in));
		BufferedOutputStream  fisier2 = new BufferedOutputStream (new FileOutputStream(out));
		
		for (int i =0; i<54; i++)
		{
			fisier2.write(fisier.read());
		}
		
		while (true)
		{
			B = fisier.read();
			G = fisier.read();
			R = fisier.read();
			
			fisier2.write((int)((B+bplus)*bori)%bmodulo);
			if (B==-1)
				break;
			fisier2.write((int)((G+bplus)*gori)%gmodulo);
			fisier2.write((int)((R+bplus)*rori)%rmodulo);
		
			
			
		}
		
		
		fisier.close();
		fisier2.close();
		
		}
		public static void albNegru(String in, String out) throws IOException
		{
			int B;
			int G;
			int R;
			
			
		
			BufferedInputStream fisier = new BufferedInputStream(new FileInputStream(in));
			BufferedOutputStream  fisier2 = new BufferedOutputStream (new FileOutputStream(out));
			
			for (int i =0; i<54; i++)
			{
				fisier2.write(fisier.read());
			}
			
			while (true)
			{
				B = fisier.read();
				G = fisier.read();
				R = fisier.read();
				
				fisier2.write(G);
				if (B==-1)
					break;
				fisier2.write(G);
				fisier2.write(G);
				
				
			}
			
			
			fisier.close();
			fisier2.close();
			
			
			
		}

		
		
	

}
