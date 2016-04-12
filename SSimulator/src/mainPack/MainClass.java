package mainPack;

import java.awt.EventQueue;

public class MainClass 
{
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FereastraPrincipala frame = new FereastraPrincipala();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
