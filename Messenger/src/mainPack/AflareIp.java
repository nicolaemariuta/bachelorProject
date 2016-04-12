package mainPack;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AflareIp extends javax.swing.JDialog implements ActionListener
{
	
	
	private JTextField textField;
	String adresa;
	JButton btnOk;
	InetAddress adress;
	FereastraPrincipala fp;
	/**
	 * Create the dialog.
	 */
	public AflareIp(FereastraPrincipala fp) 
	{
		this.fp = fp;
		this.setModal(true);
		setBounds(100, 100, 407, 113);
		getContentPane().setLayout(null);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(98, 41, 89, 23);
		getContentPane().add(btnOk);
		
		JLabel lblWriteTheIp = new JLabel("Write the IP Address:");
		lblWriteTheIp.setBounds(21, 12, 139, 18);
		getContentPane().add(lblWriteTheIp);
		
		textField = new JTextField();
		textField.setBounds(170, 10, 192, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		
		btnOk.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand() == "OK")
		{
			adresa = textField.getText();
			
			try
			{
				
				adress= InetAddress.getByName(adresa);
				fp.adress = adress;
	
				
				
			}
			
			
			catch (UnknownHostException j)
			{
				JOptionPane.showMessageDialog(this,"The IP address is wrong!", "Error",JOptionPane.ERROR_MESSAGE);
			}
			
	
			
			
			try 
			{
				String text = "~cerere" + fp.textNume.getText();	
			
		
				TrimitereMesaje mesaj = new TrimitereMesaje(text, adress);
				mesaj.start();
		
		
			}
			catch (UnknownHostException e1)
			{
				JOptionPane.showMessageDialog(this,"The connection no longer works!", "Error",JOptionPane.ERROR_MESSAGE);
			}
			
			
			
			this.dispose();
		}
		
	}
}
