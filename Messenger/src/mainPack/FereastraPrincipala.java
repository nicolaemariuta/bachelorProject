package mainPack;

import java.awt.BorderLayout;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import javax.swing.JTextPane;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JSeparator;
import javax.swing.JEditorPane;

import java.awt.FileDialog;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextField;
import javax.swing.JLabel;



public class FereastraPrincipala extends JFrame implements ActionListener
{

	private JPanel contentPane;
	JTextField textNume;
	JTextArea textMeu;
	JTextPane textPane;
	JButton btnConectare;

	public static final int PORT = 8222;

	InetAddress adress = null;
	DatagramSocket socket = null;
	DatagramPacket cerere, raspuns = null;
	String nume;
	PrimireMesaje server = new PrimireMesaje(textPane,this);

	
	JButton btnFile = new JButton("Send File");

	public FereastraPrincipala()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 556);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("Messenger");
		
		JPanel panelComun = new JPanel();
		panelComun.setBackground(Color.LIGHT_GRAY);
		panelComun.setBounds(10, 11, 457, 284);
		contentPane.add(panelComun);
		GridBagLayout gbl_panelComun = new GridBagLayout();
		gbl_panelComun.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelComun.rowHeights = new int[]{0, 0};
		gbl_panelComun.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelComun.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelComun.setLayout(gbl_panelComun);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panelComun.add(scrollPane, gbc_scrollPane);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
		
		
		JPanel panelMeu = new JPanel();
		panelMeu.setBackground(Color.LIGHT_GRAY);
		panelMeu.setBounds(10, 297, 457, 107);
		contentPane.add(panelMeu);
		panelMeu.setLayout(null);
		
		textMeu = new JTextArea();
		textMeu.setBounds(10, 11, 348, 85);
		panelMeu.add(textMeu);
		
		JButton btnSend = new JButton("SEND");	
		btnSend.setForeground(Color.RED);
		btnSend.setBackground(Color.CYAN);
		btnSend.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnSend.setBounds(358, 11, 89, 85);
		panelMeu.add(btnSend);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 403, 457, 154);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		btnFile.setFont(btnFile.getFont().deriveFont(btnFile.getFont().getStyle() | Font.BOLD));
		btnFile.setForeground(new Color(220, 20, 60));
		
		btnFile.setBounds(10, 11, 134, 32);
		panel.add(btnFile);
		
		btnConectare = new JButton("Connect");
		btnConectare.setFont(btnConectare.getFont().deriveFont(btnConectare.getFont().getStyle() | Font.BOLD));
		btnConectare.setForeground(Color.BLUE);
		
		btnConectare.setBounds(333, 11, 114, 32);
		panel.add(btnConectare);
		
		textNume = new JTextField();
		textNume.setBounds(199, 11, 124, 32);
		panel.add(textNume);
		textNume.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("  Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(146, 14, 54, 23);
		panel.add(lblNewLabel);
		
		
	
		
		btnSend.addActionListener(this);
		textNume.addActionListener(this);
		btnConectare.addActionListener(this);
		btnFile.addActionListener(this);
	
		
		server.start();
		
		
	}
	

	public void actionPerformed(ActionEvent e)
	
	
	{
		
		
		
		if (e.getSource() == btnFile)
			
		{
			if(textNume.getText().length()!=0 && adress !=null)
			{
		
		
				
			FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
			
			fd.setDirectory("C:/Users");
			fd.show();
	
			String numeFisier;
			numeFisier = fd.getDirectory()+fd.getFile();
			System.out.println(numeFisier);
			if (numeFisier.isEmpty()|| numeFisier == null)
			{
				System.out.println("EMPTY");
				JOptionPane.showMessageDialog(this,"!!!!!", "Error",JOptionPane.ERROR_MESSAGE);
			}
		
			
			try
			{
			
			String text = "\nTrimit fisierul " + numeFisier + "\n Astept acceptul celuilalt user!";
			
		
		
			Document doc = textPane.getDocument();
			doc.insertString(doc.getLength(), text, null);
			System.out.println(4);
			}
			catch(Exception eee)
			
			{
				eee.printStackTrace();
			}
			
			try
			{
			int size = (int) new File(fd.getDirectory()+fd.getFile()).length();
			new TrimitereMesaje("FISIER" +"*"+size+"~"+ fd.getFile(), adress).start();
			
			}
			catch (Exception f)
			{
				JOptionPane.showMessageDialog(this,"The connection no longer works!", "Error",JOptionPane.ERROR_MESSAGE);
				f.printStackTrace();
				
			}
			
			
			new TrimitereFisiere(this, numeFisier).start();
				
			
			}
			else
			{
				JOptionPane.showMessageDialog(this,"You cannot send the file! Check if you are connected or if you written your name!", "Error",JOptionPane.ERROR_MESSAGE);
				
			}
			
			
			
		}
		
		
		
		if (e.getSource() == btnConectare )
		{
			if(textNume.getText().length()==0)
			{
				JOptionPane.showMessageDialog(this,"You not written your name!", "Error",JOptionPane.ERROR_MESSAGE);
			}
			
			else
			{
			AflareIp ai = new AflareIp(this);
			ai.show();
		
			}
			
			
		}
		
		
		if(e.getActionCommand() == "SEND")
		{
			
			
			if(textNume.getText().length()==0)
			{
				JOptionPane.showMessageDialog(this,"You not written your name!", "Error",JOptionPane.ERROR_MESSAGE);
			}
			
			else
				if(textMeu.getText().length()!=0)
			{
				String text ="\n" +textNume.getText() +": "+ textMeu.getText();
				
				try
				{
					
					Font font = new Font("Arial", Font.PLAIN, 16);
					textPane.setFont(font);
					textPane.setForeground(Color.black);
					Document doc = textPane.getDocument();
					doc.insertString(doc.getLength(), text, null);
				}
				catch(BadLocationException EE) {}
				textMeu.setText("");
				
				if(adress !=null)
				{
					
					
					try 
					{
					
						TrimitereMesaje mesaj = new TrimitereMesaje(text, adress);
						mesaj.start();
					}
					catch (UnknownHostException e1)
					{
						JOptionPane.showMessageDialog(this,"The connection no longer works!", "Error",JOptionPane.ERROR_MESSAGE);
					}
					
				}
			
			}
			
			
		}

		
	}

}
