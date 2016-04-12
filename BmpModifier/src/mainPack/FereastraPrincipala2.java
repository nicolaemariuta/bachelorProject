package mainPack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.Box.Filler;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FereastraPrincipala2 extends JFrame implements ActionListener

	

{
	JButton load = new JButton ("LOAD File");
	JTextField filein = new JTextField ("", 50);
	JButton save = new JButton ("SAVE File");
	JTextField fileout = new JTextField ("",50);
	JFormattedTextField bplus = new JFormattedTextField(new Double(0));
	JFormattedTextField bori = new JFormattedTextField(new Double(1));
	JFormattedTextField bmodulo = new JFormattedTextField(new Integer(0));
	JFormattedTextField gplus = new JFormattedTextField(new Double(0));
	JFormattedTextField gori = new JFormattedTextField(new Double(1));
	JFormattedTextField gmodulo = new JFormattedTextField(new Integer(0));
	JFormattedTextField rplus = new JFormattedTextField(new Double(0));
	JFormattedTextField rori = new JFormattedTextField(new Double(1));
	JFormattedTextField rmodulo = new JFormattedTextField(new Integer(0));
	String filename1,filename2;
	JButton activare = new JButton ("ACTIVATE");
	JButton albnegru = new JButton ("GreyScale picture");
	
	
	
	
	FereastraPrincipala2(String titlu)
	{
		super(titlu);
		NumberFormat nformat;
		nformat = NumberFormat.getNumberInstance();
		
		JLabel bpl = new JLabel("+");
		JLabel bmi = new JLabel("*");
		JLabel bmo = new JLabel("%");
		bpl.setForeground(Color.BLUE);
		bmi.setForeground(Color.BLUE);
		bmo.setForeground(Color.BLUE);
		
		JLabel gpl = new JLabel("+");
		JLabel gmi = new JLabel("*");
		JLabel gmo = new JLabel("%");
		gpl.setForeground(Color.BLUE);
		gmi.setForeground(Color.BLUE);
		gmo.setForeground(Color.BLUE);
		
		JLabel rpl = new JLabel("+");
		JLabel rmi = new JLabel("*");
		JLabel rmo = new JLabel("%");
		rpl.setForeground(Color.BLUE);
		rmi.setForeground(Color.BLUE);
		rmo.setForeground(Color.BLUE);
		
		
		JPanel panel1 = new JPanel();
		panel1.add(filein, BorderLayout.WEST);
		panel1.add(load, BorderLayout.EAST);
		
		JPanel panel2 = new JPanel();
		panel2.add(fileout, BorderLayout.WEST);
		panel2.add(save, BorderLayout.EAST);
		
		
		
	
		JPanel pblue = new JPanel();
		JLabel albastru = new JLabel ("Blue");
		albastru.setForeground(Color.blue);
		bplus.setColumns(10);
		bori.setColumns(10);
		bmodulo.setColumns(10);
		pblue.add(albastru);
		pblue.add(bpl);
		pblue.add(bplus);
		pblue.add(bmi);
		pblue.add(bori);
		pblue.add(bmo);
		pblue.add(bmodulo);
		
		
	
		JPanel pyellow = new JPanel();
		JLabel galben = new JLabel ("Yellow");
		galben.setForeground(Color.yellow);
		gplus.setColumns(10);
		gori.setColumns(10);
		gmodulo.setColumns(10);
		pyellow.add(galben);
		pyellow.add(gpl);
		pyellow.add(gplus);
		pyellow.add(gmi);
		pyellow.add(gori);
		pyellow.add(gmo);
		pyellow.add(gmodulo);
		
		
		JPanel pred = new JPanel();
		JLabel rosu = new JLabel ("Red");
		rosu.setForeground(Color.red);
		rplus.setColumns(10);
		rori.setColumns(10);
		rmodulo.setColumns(10);
		pred.add(rosu);
		pred.add(rpl);
		pred.add(rplus);
		pred.add(rmi);
		pred.add(rori);
		pred.add(rmo);
		pred.add(rmodulo);
		
		activare.setBackground(Color.red);
		JPanel activ = new JPanel();
		activ.add(activare, BorderLayout.CENTER);
		activ.add(albnegru, BorderLayout.WEST);
		
		
		JSplitPane sp1 = new JSplitPane (JSplitPane.VERTICAL_SPLIT, pblue, pyellow);
		JSplitPane sp2 = new JSplitPane (JSplitPane.VERTICAL_SPLIT, sp1, pred);
		JSplitPane sp3 = new JSplitPane (JSplitPane.VERTICAL_SPLIT, sp2, panel1);
		JSplitPane sp4 = new JSplitPane (JSplitPane.VERTICAL_SPLIT, sp3, panel2);
		JSplitPane sp5 = new JSplitPane (JSplitPane.VERTICAL_SPLIT, sp4, activ);
		
		
		getContentPane().add(sp5, BorderLayout.CENTER);
		
		bplus.addActionListener(this);
		bori.addActionListener(this);
		bmodulo.addActionListener(this);
		gplus.addActionListener(this);
		gori.addActionListener(this);
		gmodulo.addActionListener(this);
		rplus.addActionListener(this);
		rori.addActionListener(this);
		rmodulo.addActionListener(this);
		load.addActionListener(this);
		save.addActionListener(this);
		activare.addActionListener(this);
		albnegru.addActionListener(this);
		
		
		pack();
		this.setLocationRelativeTo(null);
		

	}






	public void actionPerformed(ActionEvent e) 
	
	{
		if (e.getSource()== load)
		{
			FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
			fd.setDirectory("C:/Users/npiulitza");
			fd.setFilenameFilter(new FilenameFilter()
			{
				public boolean accept(File dir, String numeFis) {
					return (numeFis.endsWith(".bmp"));
				}
				
			});
			fd.show();
			filename1 = fd.getDirectory()+fd.getFile();
			filein.setText(filename1);
		
		}
		
		if (e.getSource()== save)
		{
			FileDialog fd = new FileDialog(this, "Choose File", FileDialog.SAVE);
			fd.setDirectory("C:/Users/npiulitza");
			fd.setFilenameFilter(new FilenameFilter()
			{
				public boolean accept(File dir, String numeFis)
				{
					return (numeFis.endsWith(".bmp"));
				}
				
			});
			fd.show();
			filename2 = fd.getDirectory()+fd.getFile();
			fileout.setText(filename2);
		}
		
		if (e.getSource()== activare)
		{
			String b1 = bplus.getText();
			String b2 = bori.getText();
			String b3 = bmodulo.getText();
			
			String g1 = gplus.getText();
			String g2 = gori.getText();
			String g3 = gmodulo.getText();
			
			String r1 = rplus.getText();
			String r2 = rori.getText();
			String r3 = rmodulo.getText();
			
			
			double b10=0;
			double b20=0;
			int b30=0;
			double g10=0;
			double g20=0;
			int g30=0;
			double r10=0;
			double r20=0;
			int r30=0;
			
			
			
			
		
			
			try
			{
				b10 = Double.parseDouble(b1);
				b20 = Double.parseDouble(b2);
				b30 = Integer.parseInt(b3);
				
				g10 = Double.parseDouble(g1);
				g20 = Double.parseDouble(g2);
				g30 = Integer.parseInt(g3);
				
				r10 = Double.parseDouble(r1);
				r20 = Double.parseDouble(r2);
				r30 = Integer.parseInt(r3);
				
				
				
				if (b30==0)
				{
					b30 = 256;
				}
				
				if (g30==0)
				{
					g30 = 256;
				}
				
				if (r30==0)
				{
					r30 = 256;
				}
				
				
			}
			catch (Exception E)
			{
				JOptionPane.showMessageDialog(this,"The data you introduced is wrong", "Error",JOptionPane.ERROR_MESSAGE);				
			}
			
			
			try
			{
				
				
				BMPmodif.BMPmod(filein.getText(),fileout.getText(),  b10,  b20,  b30, g10,  g20,  g30,  r10, r20, r30);
			}
			catch (IOException i)
			{
				JOptionPane.showMessageDialog(this,"The files you chosen are wrong", "Error",JOptionPane.ERROR_MESSAGE);
			}
		
		}
		
		
		if (e.getSource()==albnegru)
		{
			try
			{
				BMPmodif.albNegru(filein.getText(), fileout.getText());
			}
			catch (IOException i)
			{
				JOptionPane.showMessageDialog(this,"The files you chosen are wrong", "Error",JOptionPane.ERROR_MESSAGE);

			}
			
		}
		
	}
	
}
