package mainPack;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Action;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Canvas;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import claseUtile.DialogCulori;
import claseUtile.DialogDrawString;
import claseUtile.SaveJPEG;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.DoubleBuffer;

import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JInternalFrame;

public class FereastraPrincipala extends JFrame implements ActionListener
{

	private JPanel contentPane;
	public Color culoare = Color.black;
	public String forma = "pencil";
	JButton btnCerc;
	JButton btnLine;
	JButton btnPolygon;
	JButton btnPolyline;
	JButton btnOval;
	JButton btnDreptunghi;
	JButton btnPatrat;
	JButton btnAlb;
	JButton btnGriInchis;
	JButton btnGriDeschis;
	JButton btnRosu;
	JButton btnPortocaliu;
	JButton btnGalben;
	JButton btnVerde;
	JButton btnTurcoaz;
	JButton btnIndigo;
	JButton btnViolet;
	JButton btnMaro;
	JButton btnRoundRect;
	JButton btnCreion;
	JButton btnGuma;
	JPanel panelCulori;
	public JPanel panelPlansa;
	public SuprafataDesenare desen = new SuprafataDesenare(this);
	private JButton btnBackground;
	public JLabel labelCuloare;
	private JButton btnString;
	public Font font = null;
	public String drawString = "...";
	public int dim = 14;
	


	public FereastraPrincipala()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 530, 21);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Refresh");
		mnFile.add(mntmNewMenuItem);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		// Panel forme geometrice
		JPanel panelFormeGeometrice = new JPanel();
		panelFormeGeometrice.setBounds(10, 32, 38, 392);
		contentPane.add(panelFormeGeometrice);
		panelFormeGeometrice.setLayout(null);
		
		
		btnCerc = new JButton("");
		btnCerc.setToolTipText("Draw Circle");
		btnCerc.setIcon(new ImageIcon("Icoanite\\iconitaCerc.jpg"));
		btnCerc.setBounds(0, 0, 37, 37);
		panelFormeGeometrice.add(btnCerc);
		
		btnLine = new JButton("");
		btnLine.setToolTipText("Draw line");
		btnLine.setIcon(new ImageIcon("Icoanite\\iconitaLine.jpg"));
		btnLine.setBounds(0, 38, 37, 37);
		panelFormeGeometrice.add(btnLine);
		
		btnPolygon = new JButton("");
		btnPolygon.setToolTipText("Draw Polygon");
		btnPolygon.setIcon(new ImageIcon("Icoanite\\iconitaPolygon.jpg"));
		btnPolygon.setBounds(0, 76, 37, 37);
		panelFormeGeometrice.add(btnPolygon);
		
		btnPolyline = new JButton("");
		btnPolyline.setToolTipText("Draw Poliline");
		btnPolyline.setIcon(new ImageIcon("Icoanite\\iconitaPolyline.jpg"));
		btnPolyline.setBounds(0, 114, 37, 37);
		panelFormeGeometrice.add(btnPolyline);
		
		btnOval = new JButton("");
		btnOval.setToolTipText("Draw Oval");
		btnOval.setIcon(new ImageIcon("Icoanite\\iconitaOval.jpg"));
		btnOval.setBounds(0, 152, 37, 37);
		panelFormeGeometrice.add(btnOval);
		
		btnDreptunghi = new JButton("");
		btnDreptunghi.setToolTipText("Draw Rectangle");
		btnDreptunghi.setIcon(new ImageIcon("Icoanite\\iconitaDreptunghi.jpg"));
		btnDreptunghi.setBounds(0, 190, 37, 37);
		panelFormeGeometrice.add(btnDreptunghi);
		
		btnPatrat = new JButton("");
		btnPatrat.setToolTipText("Draw square");
		btnPatrat.setIcon(new ImageIcon("Icoanite\\iconitaPatrat.jpg"));
		btnPatrat.setBounds(0, 228, 37, 37);
		panelFormeGeometrice.add(btnPatrat);
		
		btnRoundRect = new JButton("");
		btnRoundRect.setIcon(new ImageIcon("Icoanite\\iconitaRoundRect.jpg"));
		btnRoundRect.setToolTipText("Rounded Rectangle\r\n");
		btnRoundRect.setBounds(0, 264, 37, 37);
		panelFormeGeometrice.add(btnRoundRect);
		
		btnCreion = new JButton("");
		btnCreion.setBackground(Color.BLACK);
		btnCreion.setIcon(new ImageIcon("Icoanite\\iconitaCreion.jpg"));
		btnCreion.setToolTipText("Pencil");
		btnCreion.setBounds(0, 306, 37, 37);
		panelFormeGeometrice.add(btnCreion);
		
		btnGuma = new JButton("");
		btnGuma.setIcon(new ImageIcon("Icoanite\\iconitaGuma.jpg"));
		btnGuma.setToolTipText("Eraser");
		btnGuma.setBounds(0, 344, 37, 37);
		panelFormeGeometrice.add(btnGuma);
		
		panelCulori = new JPanel();
		panelCulori.setBounds(486, 27, 44, 360);
		contentPane.add(panelCulori);
		panelCulori.setLayout(null);
		
		// Panel culori
		btnAlb = new JButton("");
		btnAlb.setToolTipText("White");
		btnAlb.setIcon(new ImageIcon("IconiteCulori\\iconitaAlb.jpg"));
		btnAlb.setForeground(Color.WHITE);
		btnAlb.setBackground(Color.WHITE);
		btnAlb.setBounds(4, 0, 37, 30);
		panelCulori.add(btnAlb);
		
		btnGriInchis = new JButton("");
		btnGriInchis.setForeground(Color.WHITE);
		btnGriInchis.setIcon(new ImageIcon("IconiteCulori\\iconitaGriInchis.jpg"));
		btnGriInchis.setToolTipText("Dark Grey");
		btnGriInchis.setBackground(Color.WHITE);
		btnGriInchis.setBounds(4, 31, 37, 30);
		panelCulori.add(btnGriInchis);
		
		btnGriDeschis = new JButton("");
		btnGriDeschis.setIcon(new ImageIcon("IconiteCulori\\iconitaGriDeschis.jpg"));
		btnGriDeschis.setToolTipText("Light Grey");
		btnGriDeschis.setBackground(Color.WHITE);
		btnGriDeschis.setBounds(4, 61, 37, 30);
		panelCulori.add(btnGriDeschis);
		
		btnRosu = new JButton("");
		btnRosu.setIcon(new ImageIcon("IconiteCulori\\iconitaRosu.jpg"));
		btnRosu.setToolTipText("Red");
		btnRosu.setBackground(Color.WHITE);
		btnRosu.setBounds(4, 91, 37, 30);
		panelCulori.add(btnRosu);
		
		btnPortocaliu = new JButton("");
		btnPortocaliu.setIcon(new ImageIcon("IconiteCulori\\iconitaPortocaliu.jpg"));
		btnPortocaliu.setToolTipText("Orange");
		btnPortocaliu.setBackground(Color.WHITE);
		btnPortocaliu.setBounds(4, 122, 37, 30);
		panelCulori.add(btnPortocaliu);
		
		btnGalben = new JButton("");
		btnGalben.setIcon(new ImageIcon("IconiteCulori\\iconitaGalben.jpg"));
		btnGalben.setToolTipText("Yellow");
		btnGalben.setBackground(Color.WHITE);
		btnGalben.setBounds(4, 155, 37, 30);
		panelCulori.add(btnGalben);
		
		btnVerde = new JButton("");
		btnVerde.setIcon(new ImageIcon("IconiteCulori\\iconitaVerde.jpg"));
		btnVerde.setToolTipText("Green");
		btnVerde.setBackground(Color.WHITE);
		btnVerde.setBounds(4, 186, 37, 30);
		panelCulori.add(btnVerde);
		
		btnTurcoaz = new JButton("");
		btnTurcoaz.setIcon(new ImageIcon("IconiteCulori\\iconitaTurcoaz.jpg"));
		btnTurcoaz.setToolTipText("Turquoise");
		btnTurcoaz.setBackground(Color.WHITE);
		btnTurcoaz.setBounds(4, 213, 37, 30);
		panelCulori.add(btnTurcoaz);
		
		btnIndigo = new JButton("");
		btnIndigo.setIcon(new ImageIcon("IconiteCulori\\iconitaIndigo.jpg"));
		btnIndigo.setToolTipText("Indigo");
		btnIndigo.setBackground(Color.WHITE);
		btnIndigo.setBounds(4, 243, 37, 30);
		panelCulori.add(btnIndigo);
		
		btnViolet = new JButton("");
		btnViolet.setIcon(new ImageIcon("IconiteCulori\\iconitaViolet.jpg"));
		btnViolet.setToolTipText("Purple\r\n");
		btnViolet.setBackground(Color.WHITE);
		btnViolet.setBounds(4, 273, 37, 30);
		panelCulori.add(btnViolet);
		
		btnMaro = new JButton("");
		btnMaro.setIcon(new ImageIcon("IconiteCulori\\iconitaMaro.jpg"));
		btnMaro.setToolTipText("Brown");
		btnMaro.setBackground(Color.WHITE);
		btnMaro.setBounds(4, 304, 37, 30);
		panelCulori.add(btnMaro);
		
		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setBounds(0, 0, 55, 33);
		panelCulori.add(internalFrame);
		internalFrame.setVisible(true);
		
		JPanel panelText = new JPanel();
		panelText.setBounds(47, 394, 483, 41);
		contentPane.add(panelText);
		panelText.setLayout(null);
		
		JButton buttonCustomColor = new JButton("Custom Color");
		buttonCustomColor.setBounds(243, 0, 126, 30);
		panelText.add(buttonCustomColor);
		buttonCustomColor.setToolTipText("Choose another color!");
		
		
		
		
		
		
		//alte butoane
		buttonCustomColor.addActionListener(this);
		
		panelPlansa = new JPanel();
	
		panelPlansa.setBounds(50, 32, 435, 353);
		contentPane.add(panelPlansa);
		
		btnBackground = new JButton("Background");
		btnBackground.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBackground.setToolTipText("Change background color");
		btnBackground.setBounds(123, 1, 118, 30);
		panelText.add(btnBackground);
		
		labelCuloare = new JLabel("");
		labelCuloare.setOpaque(true);
		labelCuloare.setBackground(culoare);
		labelCuloare.setToolTipText("Current color");
		labelCuloare.setBounds(379, 0, 104, 30);
		panelText.add(labelCuloare);
		
		btnString = new JButton("Draw String");
		btnString.setToolTipText("Draw a string");
		btnString.setBounds(0, 2, 120, 28);
		panelText.add(btnString);
	
		panelPlansa.add(desen);
		
		
		
		mntmSave.addActionListener(this);
		mntmNewMenuItem.addActionListener(this);
		mntmExit.addActionListener(this);
		
		//butoane forme geometrice
		btnCerc.addActionListener(this);
		btnLine.addActionListener(this);
		btnPolygon.addActionListener(this);
		btnPolyline.addActionListener(this);
		btnOval.addActionListener(this);
		btnDreptunghi.addActionListener(this);
		btnPatrat.addActionListener(this);
		btnRoundRect.addActionListener(this);
		
		// butoane culori
		btnAlb.addActionListener(this);
		btnGriInchis.addActionListener(this);
		btnGriDeschis.addActionListener(this);
		btnRosu.addActionListener(this);
		btnPortocaliu.addActionListener(this);
		btnGalben.addActionListener(this);
		btnVerde.addActionListener(this);
		btnTurcoaz.addActionListener(this);
		btnIndigo.addActionListener(this);
		btnViolet.addActionListener(this);
		btnMaro.addActionListener(this);
		btnCreion.addActionListener(this);
		btnBackground.addActionListener(this);
		btnString.addActionListener(this);
		btnGuma.addActionListener(this);

	}


	public void actionPerformed(ActionEvent e) 
	{
		// setare butoane
		
		if(e.getActionCommand()=="Refresh")
		{
			
			
			desen.setBackground(Color.white);
			desen.stack.clear();
			desen.repaint();
			
		}
		
		
		if(e.getActionCommand() == "Exit")
		{
			System.exit(0);
		}
		
		if (e.getActionCommand()=="Custom Color" )
		{
			new DialogCulori(this).show();
		}
		
		if (e.getActionCommand() == "Save")
		{
			FileDialog fd = new FileDialog(this, "Save the painting!", FileDialog.SAVE);
			
			fd.setFilenameFilter(new FilenameFilter()
			{
				public boolean accept(File dir, String numeFis) {
					return (numeFis.endsWith(".jpg"));
				}
				
			});
			
			fd.show();
			String url = fd.getDirectory() + fd.getFile();
	
			new SaveJPEG(desen,url,this).start();
		}
		
		if(e.getSource() == btnBackground)
		{
			desen.setBackground(culoare);
			desen.repaint();
		}
		
		if (e.getSource()== btnString)
		{
			new DialogDrawString(this).show();
		}
		
		
		//Setare butoane forme geometrice
		
		if(e.getSource()== btnGuma)
		{
			forma = "eraser";
		}
		
		if(e.getSource() == btnCreion)
		{
			forma = "pencil";
		}
		
		if(e.getSource()== btnCerc)
		{
			forma = "circle";
		}
		if(e.getSource()== btnLine)
		{
			forma = "line";
		}
		if (e.getSource() == btnPolygon)
		{
			forma = "polygon";
		}
		if (e.getSource() == btnPolyline)
		{
			forma = "Polyline";
		}
		if (e.getSource() == btnOval)
		{
			forma = "oval";
		}
		if (e.getSource() == btnDreptunghi)
		{
			forma = "rectangle";
		}
		if (e.getSource() ==btnPatrat)
		{
			forma = "square";
		}
		if (e.getSource() == btnRoundRect)
		{
			forma = "Rounded Rectangle";
		}
		
		// setare butoane culori
		if (e.getSource() == btnAlb)
		{
			culoare = Color.white;
			labelCuloare.setBackground(culoare);
		}
		if (e.getSource() == btnGriInchis)
		{
			culoare = Color.DARK_GRAY;
			labelCuloare.setBackground(culoare);
		}
		if (e.getSource() == btnGriDeschis)
		{
			culoare = Color.LIGHT_GRAY;
			labelCuloare.setBackground(culoare);
		}
		if (e.getSource() == btnRosu)
		{
			culoare = new Color(230,0,0);
			labelCuloare.setBackground(culoare);
		}
		if (e.getSource() == btnPortocaliu)
		{
			culoare = new Color(236,115,0);
			labelCuloare.setBackground(culoare);
		}
		if (e.getSource() == btnGalben)
		{
			culoare = Color.yellow;
			labelCuloare.setBackground(culoare);
		}
		if (e.getSource() == btnVerde)
		{
			culoare = new Color(0,153,61);
			labelCuloare.setBackground(culoare);
		}
		if (e.getSource() == btnTurcoaz)
		{
			culoare = new Color(0,154,255,181);
			labelCuloare.setBackground(culoare);
		}
		if (e.getSource() == btnIndigo)
		{
			culoare = new Color(100,48,255,255);
			labelCuloare.setBackground(culoare);
		}
		if (e.getSource() == btnViolet)
		{
			culoare = new Color(199,0,255,255);
			labelCuloare.setBackground(culoare);
		}
		if (e.getSource() == btnMaro)
		{
			culoare = new Color(160,107,79,255);
			labelCuloare.setBackground(culoare);
		}
		
		this.setTitle("Color: "+ culoare.toString() +" and shape: " + forma);
		
	}
}



	