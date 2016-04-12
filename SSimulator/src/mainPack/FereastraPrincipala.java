package mainPack;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import java.awt.GridBagConstraints;

import javax.swing.JSplitPane;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Canvas;
import java.awt.Panel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Stack;

import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import threaduri.Simulare;

import java.awt.Font;
import java.io.BufferedOutputStream;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JMenuItem;

import claseUtile.Conexiune;
import claseUtile.Coordonate;
import claseUtile.DesenareButoane;
import claseUtile.DesenarePorti;
import claseUtile.Nod;

public class FereastraPrincipala extends JFrame implements ActionListener,KeyListener
{

	private JPanel contentPane;
	SuprafataDesenare sd = new SuprafataDesenare(this, new Dimension(1200,594));
	String desenare = "select";
	
	JButton btnNON;
	JButton btnON;
	JButton btnAND;
	JButton btnNAND;
	JButton btnOR;
	JButton btnNOR;
	JButton btnXOR;
	JButton btnNXOR;
	JButton btnIN;
	JButton btnOUT;
	JButton btnNODE;
	JButton btnLINE;
	JMenuItem mntmSaveImage;
	
	JButton btnSim;
	
	JMenuItem mntmNew;
	JMenuItem mntmSave;
	JMenuItem mntmLoad ;
	
	public Stack<PoartaLogica> porti = new Stack();


	public FereastraPrincipala()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1237, 740);
		this.setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmNew = new JMenuItem("New");
		mnFile.add(mntmNew);
		
		 mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		mntmLoad = new JMenuItem("Load");
		mnFile.add(mntmLoad);
		
		mntmSaveImage = new JMenuItem("Save Image");
		mnFile.add(mntmSaveImage);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridheight = 8;
		gbc_scrollPane.gridwidth = 9;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		JPanel panelOpt = new JPanel();
		panelOpt.setBackground(Color.GRAY);
		panelOpt.setBounds(10, 11, 1203, 51);
		panel.add(panelOpt);
		panelOpt.setLayout(null);
		
		
	
		
		
		
		
	
		btnLINE = new DesenareButoane("line");
		btnLINE.setToolTipText("MAKE CONNECTIONS");
		btnLINE.setBounds(1142, 0, 58, 49);
		panelOpt.add(btnLINE);
		
		btnSim = new JButton("RESULTS");
		btnSim.setToolTipText("Click to see the results");
		btnSim.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSim.setBounds(0, 0, 176, 49);
		panelOpt.add(btnSim);
		
		JPanel panelPorti = new JPanel();
		panelPorti.setBackground(Color.GRAY);
		panelPorti.setBounds(10, 77, 58, 594);
		panel.add(panelPorti);
		panelPorti.setLayout(null);
		
		btnNON = new DesenareButoane("non");
		btnNON.setBounds(0, 52, 58, 49);
		panelPorti.add(btnNON);
		btnNON.setToolTipText("NON");
		
		btnON = new DesenareButoane("on");
		btnON.setToolTipText("ON");
		btnON.setBounds(0, 0, 58, 49);
		panelPorti.add(btnON);
		
		btnAND = new DesenareButoane("and");
		btnAND.setToolTipText("AND");
		btnAND.setBounds(0, 104, 58, 49);
		panelPorti.add(btnAND);
		
		btnNAND = new DesenareButoane("nand");
		btnNAND.setToolTipText("NAND");
		btnNAND.setBounds(0, 155, 58, 49);
		panelPorti.add(btnNAND);
		
		btnOR = new DesenareButoane("or");
		btnOR.setToolTipText("OR");
		btnOR.setBounds(0, 206, 58, 49);
		panelPorti.add(btnOR);
		
		btnNOR = new DesenareButoane("nor");
		btnNOR.setToolTipText("NOR");
		btnNOR.setBounds(0, 258, 58, 49);
		panelPorti.add(btnNOR);
		
		btnXOR = new DesenareButoane("xor");
		btnXOR.setToolTipText("XOR");
		btnXOR.setBounds(0, 309, 58, 49);
		panelPorti.add(btnXOR);
		
		btnNXOR = new DesenareButoane("nxor");
		btnNXOR.setToolTipText("NXOR");
		btnNXOR.setBounds(0, 361, 58, 49);
		panelPorti.add(btnNXOR);
		
		btnIN = new DesenareButoane("in");
		btnIN.setToolTipText("INPUT");
		btnIN.setBounds(0, 442, 58, 49);
		panelPorti.add(btnIN);
		
		btnOUT = new DesenareButoane("out");
		btnOUT.setToolTipText("OUTPUT");
		btnOUT.setBounds(0, 492, 58, 49);
		panelPorti.add(btnOUT);
		
		btnNODE = new DesenareButoane("nod");
		btnOUT.setToolTipText("NODE");
		btnNODE.setBounds(0, 541, 58, 49);
		panelPorti.add(btnNODE);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(78, 80, 1135, 594);
		
		panel.add(scrollPane_1);
		
		JPanel panelDesen = new JPanel();
		scrollPane_1.setViewportView(panelDesen);
		panelDesen.setBackground(Color.ORANGE);
		
		panelDesen.setLayout(null);
		sd.setBounds(0, 0, 1200, 594);
		sd.setBackground(Color.white);
		panelDesen.add(sd);
		
		btnNON.addActionListener(this);
		btnON.addActionListener(this);
		
		btnAND.addActionListener(this);
		btnNAND.addActionListener(this);
		
		btnOR.addActionListener(this);
		btnNOR.addActionListener(this);
		
		btnXOR.addActionListener(this);
		btnNXOR.addActionListener(this);
		
		btnIN.addActionListener(this);
		btnOUT.addActionListener(this);
		
		btnSim.addActionListener(this);
		
		btnLINE.addActionListener(this);
		btnNODE.addActionListener(this);
		this.addKeyListener(this);
		
		mntmNew.addActionListener(this);
		mntmSave.addActionListener(this);
		mntmLoad.addActionListener(this);
		mntmSaveImage.addActionListener(this);
		
	}
	
	
	
	
	public String getDesenare()
	{
		return desenare;
	}



	public void setDesenare(String desenare)
	{
		this.desenare = desenare;
	}







	public void actionPerformed(ActionEvent e)
	{
	
	//butoane desenare porti
	
		if(e.getSource() == btnON)
		{
			sd.selected = null;
			sd.repaint();
			desenare = "AMPL";
			this.setTitle("Drawing ON");
		}
		
		if(e.getSource() == btnNON)
		{
			sd.selected = null;
			sd.repaint();
			desenare = "NON";
			this.setTitle("Drawing NON");
		}
		
		if(e.getSource() == btnNON)
		{
			sd.selected = null;
			sd.repaint();
			desenare = "OR";
			this.setTitle("Drawing OR");
		}
		
		if(e.getSource() == btnNOR)
		{
			sd.selected = null;
			sd.repaint();
			desenare = "NOR";
			this.setTitle("Drawing NOR");
		}
		
		if(e.getSource() == btnAND)
		{
			sd.selected = null;
			sd.repaint();
			desenare = "AND";
			this.setTitle("Drawing AND");
		}
		
		if(e.getSource() == btnNAND)
		{
			sd.selected = null;
			sd.repaint();
			desenare = "NAND";
			this.setTitle("Drawing NAND");
		}
		
		if(e.getSource() == btnXOR)
		{
			sd.selected = null;
			sd.repaint();
			desenare = "XOR";
			this.setTitle("Drawing XOR");
		}
		
		if(e.getSource() == btnNXOR)
		{
			sd.selected = null;
			sd.repaint();
			desenare = "NXOR";
			this.setTitle("Drawing NXOR");
		}
		
		if(e.getSource() == btnIN)
		{
			sd.selected = null;
			sd.repaint();
			desenare = "IN";
			this.setTitle("Drawing IN");
		}
		
		if(e.getSource() == btnOUT)
		{
			sd.selected = null;
			sd.repaint();
			desenare = "OUT";
			this.setTitle("Drawing OUT");
		}
		if(e.getSource() == btnLINE)
		{
			sd.selected = null;
			sd.repaint();
			desenare = "LINE";
			this.setTitle("Drawing connections");
		}
		
		if(e.getSource() == btnNODE)
		{
			sd.selected = null;
			sd.repaint();
			desenare = "NODE";
			this.setTitle("Drawing nodes");
		}
		
		// simulare
		if(e.getSource() == btnSim)
		{
	/*		for(int i =0; i <porti.size(); i ++)
			{
				System.out.println("Poarta " + porti.get(i).getType() +" are " + porti.get(i).intrari.size() +" intrari");
			}
			
			*/
			
			// generare nume
			String alfabet = "";
			char letter;
			for(letter='a'; letter <= 'z'; letter++)
			{
				alfabet = alfabet + letter;
			}
		
			
			for(int i = 0; i < porti.size(); i++)
			{
				
					
			
					
				if(porti.get(i).getNume().length() == 0)
				{
					
			
					
					int k = (int)( Math.random()*alfabet.length());
					int j = (int)( Math.random()*alfabet.length());
					
					String nume = porti.get(i).getNume() + alfabet.charAt(k) + alfabet.charAt(j);
					boolean check = true;
					
					Stack<String> names = new Stack();
					
					for(int o = 0; o< porti.size(); o++)
					{
						names.add(porti.get(o).getNume());
					}
					
						if(names.contains(nume))
						{
						
							k = (int)( Math.random()*alfabet.length());
							j = (int)( Math.random()*alfabet.length());
							nume = porti.get(i).getNume() + alfabet.charAt(k) + alfabet.charAt(j);
						}
						
					
						porti.get(i).setNume(nume);
					
				}
				
			}
			
			sd.repaint();
			// rulare simulare
			
			
			PoartaLogica[] array = new PoartaLogica[porti.size()];
			for(int i = 0; i< array.length; i ++)
			{
				array[i] = porti.get(i);
			}
			
		
			
					
			Simulare sim = new Simulare(this,array);
			sim.run();
			
			try
			{
			int inaltime = sim.verificate.get(0).getValor().length;
			int latime  = sim.verificate.size();
			
		
			
			
			
			if(sim.simulare == true)
			{
				AfisareRezultat afisare = new AfisareRezultat(latime, inaltime, sim.verificate, sim.coloane);
				afisare.setVisible(true);
				
			}
			else
			{
			
				JOptionPane.showMessageDialog(this,"You did not make all connections!","Error",JOptionPane.ERROR_MESSAGE);
			}
			}catch(ArrayIndexOutOfBoundsException m)
			{
			
				JOptionPane.showMessageDialog(this,"You did not make all connections!","Error",JOptionPane.ERROR_MESSAGE);
			}
			
			
			
		}
		
		if(e.getSource() == mntmNew)
		{
		
			sd.noduri.clear();
			sd.conexiuni.clear();
			porti.clear();
			
			
			
			sd.repaint();
		}
		
		if(e.getSource() == mntmSave)
		{
			try
			{
				FileDialog fd = new FileDialog(this, "Choose File", FileDialog.SAVE);
				fd.setFilenameFilter(new FilenameFilter()
				{
					public boolean accept(File dir, String numeFis) {
						return (numeFis.endsWith(".dwq"));
					}
					
				});
				fd.setDirectory("C:/Users");
				fd.setVisible(true);
		
				String numeFisier;
				numeFisier = fd.getDirectory()+fd.getFile();
				if(!numeFisier.endsWith(".dwq"))
				{
					numeFisier = numeFisier+ ".dwq";
				}
				
				ObjectOutputStream file = new ObjectOutputStream (new FileOutputStream(numeFisier));
				
				Nod[] noduri = new Nod[sd.noduri.size()];
				for(int i = 0; i< sd.noduri.size(); i++)
				{
					noduri[i] = sd.noduri.get(i);
				}
				
				Conexiune[] conexiuni = new Conexiune[sd.conexiuni.size()];
				for(int i = 0; i <sd.conexiuni.size(); i++)
				{
					conexiuni[i] = sd.conexiuni.get(i);
				}
				
				PoartaLogica[] ports = new PoartaLogica[porti.size()];
				
				for(int i = 0; i < porti.size(); i++)
				{
					ports[i] = porti.get(i);
				}
				
				file.writeObject(noduri);
				file.writeObject(conexiuni);
				file.writeObject(ports);
				
				file.close();
				
				
			}
			catch(Exception w) {System.out.println(w);}
			
		}
		
		if(e.getSource() == mntmLoad )
		{
			try
			{
				JFileChooser jfc = new JFileChooser("C:\\Users");
				
				jfc.setAcceptAllFileFilterUsed(false); 
			
				
				jfc.addChoosableFileFilter(new FileFilter() {  // am adaugat un nou filtru
		            public boolean accept(File f) {
		                return f.isDirectory() || f.getName().toUpperCase().endsWith(".DWQ");
		            }
		 
		            public String getDescription() {
		                return "Fisiere text (*.dwq)";
		            }
		        });
				
		        jfc.showOpenDialog(null);
				
				
			
				
				String numeFisier;
				numeFisier = jfc.getSelectedFile().getAbsolutePath() ;
				
				ObjectInputStream fisier = new ObjectInputStream ( new FileInputStream(numeFisier));
				
				Nod[] nods = (Nod[]) fisier.readObject();
				Conexiune[] conexiuni = (Conexiune[]) fisier.readObject();
				PoartaLogica[] ports = (PoartaLogica[]) fisier.readObject();
			
				
				sd.noduri.clear();
				sd.conexiuni.clear();
				porti.clear();
				
				for(int i = 0; i< nods.length; i++)
				{
					sd.noduri.add(nods[i]);
				}
				for(int i = 0; i<  conexiuni.length; i++)
				{
					sd.conexiuni.add(conexiuni[i]);
				}
				   
				for(int i = 0; i <  ports.length; i++)
				{
					porti.add(ports[i]);
					porti.get(i).setCuloare(ports[i].getDefColor());
				}
				
				sd.repaint();
				
			}
			catch(Exception w){JOptionPane.showMessageDialog(this,"Could not load that file!","Error",JOptionPane.ERROR_MESSAGE);
								}

		}
		
		if(e.getSource() == mntmSaveImage)
		{
			FileDialog fd = new FileDialog(this, "Choose File", FileDialog.SAVE);
			fd.setFilenameFilter(new FilenameFilter()
			{
				public boolean accept(File dir, String numeFis) {
					return (numeFis.endsWith(".jpg"));
				}
				
			});
			fd.setDirectory("C:/Users");
			fd.setVisible(true);
	
			String numeFisier;
			numeFisier = fd.getDirectory()+fd.getFile();
			if(!numeFisier.endsWith(".jpg"))
			{
				numeFisier = numeFisier+ ".jpg";
			}
			
			
			try
			{
			
				BufferedImage image = new BufferedImage(sd.getWidth(), sd.getHeight(), BufferedImage.TYPE_INT_RGB);
				BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(numeFisier)) ;

				Graphics2D graphics = image.createGraphics();
		
				sd.paintAll(graphics);
			
				ImageIO.write(image, "jpg", fos);
				
				fos.close();
				
			}
			catch(Exception d)
			{
				JOptionPane.showMessageDialog(this,"Could not use that file!","Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		
		
		
		
		
		
	}




	@Override
	public void keyPressed(KeyEvent e) 
	{
		//apasare tasta esc
		try
		{
				if(e.getKeyCode() == e.VK_ESCAPE)
				{
		
					if(getDesenare() == "LINE")
					{
						porti.get(sd.index2).intr[sd.index] = 0;
					}
					
					setDesenare("select");
					setTitle("Select");
					
					sd.conNod.setConectat(false);
					sd.conNod = null;
					sd.selCon = null;
					sd.selected = null;
					sd.conex = false;
					
					for(int i = 0; i< sd.noduri.size(); i ++)
					{
						sd.noduri.get(i).setCuloare(Color.black);
					}
					for(int i = 0; i< porti.size(); i++)
					{
						
						porti.get(i).setCuloare(porti.get(i).getDefColor());
					}
					
					for(int i =0; i<sd.conexiuni.size(); i++)
					{
						sd.conexiuni.get(i).setCuloare(Color.black);
					}
				
					repaint();
					
				}
				
		} catch(NullPointerException ex) {}
	}




	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
