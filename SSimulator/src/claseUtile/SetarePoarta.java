package claseUtile;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


import mainPack.FereastraPrincipala;
import mainPack.SuprafataDesenare;
import javax.swing.JTextField;

public class SetarePoarta extends JDialog implements ActionListener
{

	private final JPanel contentPanel = new JPanel();
	JButton btnOk;
	JSpinner spinner;
	JCheckBox chckbxImportant;
	

	/**
	 * Create the dialog.
	 */
	
	SuprafataDesenare fp;
	private JTextField textNume;
	private JTextField textVal;
	int intrari = 0;
	
	public SetarePoarta(final SuprafataDesenare fp)
	{
		this.fp = fp;
		this.setTitle("Setting");
		setModal(true);
		setTitle("Settings");
		setBounds(300, 300, 196, 194);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false);
		
		JLabel lblNumberOfInputs = new JLabel(" Number of inputs:");
		lblNumberOfInputs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumberOfInputs.setBounds(10, 11, 127, 26);
		if(fp.selected.getType().equals("OUT")   || fp.selected.getType().equals("IN")  || fp.selected.getType().equals( "AMPL")||fp.selected.getType().equals( "NON"))
		
		{lblNumberOfInputs.setEnabled(false);}
		contentPanel.add(lblNumberOfInputs);
		
		spinner = new JSpinner();
		
		if(fp.selected.getType().equals("OUT")   || fp.selected.getType().equals("IN")  || fp.selected.getType().equals( "AMPL")||fp.selected.getType().equals( "NON"))
		{
			spinner.setEnabled(false);
		}
		else
		{
			
			spinner.setFont(new Font("Tahoma", Font.PLAIN, 13));
			spinner.setModel(new SpinnerNumberModel(fp.selected.getNrIn(), 2, 5, 1));
			spinner.setBounds(133, 13, 37, 26);
		}
		contentPanel.add(spinner);
		
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{

				fp.selected = null;
				fp.repaint();
				dispose();
			}
		});
		
		btnOk = new JButton("OK");
		btnOk.setBounds(55, 130, 68, 25);
		contentPanel.add(btnOk);
		
		textNume = new JTextField();
		textNume.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textNume.setBounds(80, 49, 37, 20);
		contentPanel.add(textNume);
		textNume.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(20, 48, 62, 20);
		contentPanel.add(lblName);
		
		JLabel lblValue = new JLabel("Value:");
		lblValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValue.setBounds(20, 73, 62, 20);
		contentPanel.add(lblValue);
		
		textVal = new JTextField();
		textVal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textVal.setColumns(10);
		textVal.setBounds(80, 74, 37, 20);
		contentPanel.add(textVal);
		
		chckbxImportant = new JCheckBox("Important");
		chckbxImportant.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxImportant.setBounds(20, 100, 97, 23);
		contentPanel.add(chckbxImportant);
		
		if(fp.selected.getType().equals("IN" ) || fp.selected.getType().equals("OUT"))
		{
			chckbxImportant.setEnabled(false);
		}
		
		if(fp.selected.getType().equals("IN" ) || fp.selected.getType().equals("OUT"))
		{
			textVal.setEnabled(true);
		}
		else
		{
			textVal.setEnabled(false);
		}
		
		if(fp.selected.getNume()!=null)
		{
			textNume.setText(fp.selected.getNume());
		}
		
		if(fp.selected.getValue() == 0 || fp.selected.getValue() == 1)
		{
			textVal.setText(String.valueOf(fp.selected.getValue()));
		}
	
		btnOk.addActionListener(this);
		
		intrari = fp.selected.getNrIn();
	
		
	}

	
	private void addWindowListener(WindowAdapter windowAdapter)
	{
		// TODO Auto-generated method stub
		
	}


	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnOk)
		{
			//citire valoare si nume
			String nume = textNume.getText();
			if(nume.length()!=0)
			{
				if(nume.length() <3)
				{
				fp.selected.setNume(nume);
				}
				else
				{
				fp.selected.setNume(nume.substring(0, 2));
				}
			}
			
			String valoare = textVal.getText();
		
			if(valoare.length()!=0 )
			{
			
				if(valoare.equals("1"))
				{
					
					fp.selected.setValue(1);
				}
				else
				if(valoare.equals("0"))
				{
				
					fp.selected.setValue(0);
				}
				else
				{
					JOptionPane.showMessageDialog(this,"The value can only be 1 or 0!","Error",JOptionPane.INFORMATION_MESSAGE);
				}  
			}
			else
			{
				fp.selected.setValue(6);
			}
			
			//setare important
			if(chckbxImportant.isSelected())
			{
				fp.selected.setImportant(true);
			}
			else
			{
				fp.selected.setImportant(false);
			}
			
			
			
			//citire nr intrari
			if(fp.selected.getNrIn() != 1)
			{
			int size = (Integer)spinner.getValue();
			
			fp.selected.setNrIn(size);
			
			
			//stergere intrari
			if(intrari != size)
			{
				System.out.println("*");
			for(int i =0; i < fp.selected.intrari.size(); i++)
			{
				
				if(fp.selected.intr[i] !=0)
				{
					Conexiune[] con = new Conexiune[fp.conexiuni.size()];
					for(int j =0; j <con.length; j++)
					{
						con[j] = fp.conexiuni.get(j);
					}
					
					
					for (int j= 0; j <con.length; j++)
					{
					
						
						if(con[j].getIn() == fp.selected)
						{
							
							Conexiune[] con2 = new Conexiune[con[j].conexiuni.size()];
							
									for(int k = 0; k < con2.length; k++)
									{
										con2[k] = con[j].conexiuni.get(k);
									}
							
							for(int w = 0; w< con2.length; w++)
							{
								fp.noduri.remove(con2[w].getNod());
							
								
								for(int p = 0; p < fp.fp.porti.size(); p++)
								{
									if(con2[w].getIn() == fp.fp.porti.get(p))
									{
										
										fp.fp.porti.get(p).setValor(null);
										fp.fp.porti.get(p).intrari.remove(con2[w].getOut());
										fp.fp.porti.get(p).intr[con2[w].getIndex()]= 0;
									}
								}
								
								
								fp.conexiuni.remove(con2[w]);
							}
							
							
							
							con[j].getOut().setOout(false);
							fp.selected.setValor(null);
							fp.selected.intrari.remove(con[j].getOut());
							fp.selected.intr[i] = 0;
							fp.conexiuni.remove(con[j]);
							
						
						}
						
						
						
					}
					
				
					
					
					
					
				}
			
			
			}
			
			
			}
			
		
			fp.repaint();
			}
			dispose();
		}
		
	}
}
