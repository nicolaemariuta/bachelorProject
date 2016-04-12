package claseUtile;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mainPack.FereastraPrincipala;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.Font;
import javax.swing.SpinnerNumberModel;
import javax.swing.JList;
import java.awt.Choice;

public class DialogDrawString extends JDialog implements ActionListener, ChangeListener, ItemListener
{

	private final JPanel contentPanel = new JPanel();
	FereastraPrincipala fp;
	JButton btnBold;
	JButton okButton;
	JButton cancelButton;
	JButton btnItalic;
	JButton btnPlain;
	Choice choiceFont;
	JSpinner sizeSpinner;
	Font font = null;
	int size = 14;
	String fontStyle = "Arial";
	String f = "plain";
	JTextArea textArea =  new JTextArea();


	
	public DialogDrawString(FereastraPrincipala fp)
		{
		super(fp);
		this.fp = fp;
		setTitle("Write the string you want to draw, then select the font.");
		
		setBounds(100, 100, 561, 165);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(10, 86, 525, 45);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				okButton = new JButton("OK");
				okButton.setBounds(353, 2, 65, 37);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setBounds(428, 2, 97, 37);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			
			btnBold = new JButton("");
			btnBold.setToolTipText("Bold text");
			btnBold.setIcon(new ImageIcon("D:\\SpecialWorkspace\\Paint\\Icoanite\\iconitaBold.jpg"));
			btnBold.setBounds(306, 2, 37, 37);
			buttonPane.add(btnBold);
			
			btnItalic = new JButton("");
			btnItalic.setToolTipText("Italic text");
			btnItalic.setIcon(new ImageIcon("D:\\SpecialWorkspace\\Paint\\Icoanite\\iconitaItalic.jpg"));
			btnItalic.setBounds(265, 2, 37, 37);
			buttonPane.add(btnItalic);
			
			sizeSpinner = new JSpinner();
			sizeSpinner.setModel(new SpinnerNumberModel(14, 3, 72, 1));
			sizeSpinner.setToolTipText("Choose the size");
			sizeSpinner.setFont(new Font("Times New Roman", Font.PLAIN, 24));
			sizeSpinner.setBounds(169, 2, 50, 37);
			buttonPane.add(sizeSpinner);
			
			btnPlain = new JButton("");
			btnPlain.setBounds(225, 2, 37, 37);
			buttonPane.add(btnPlain);
			btnPlain.setToolTipText("Plain text");
			btnPlain.setIcon(new ImageIcon("D:\\SpecialWorkspace\\Paint\\Icoanite\\iconitaPlain.jpg"));
			
			choiceFont = new Choice();
			choiceFont.setBounds(0, 10, 163, 33);
			buttonPane.add(choiceFont);
			
			String[] fonturi =  GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
			for (int i = 0; i < fonturi.length; i++)
			{
				choiceFont.add(fonturi[i]);
			}
			
			
			
			okButton.addActionListener(this);
			cancelButton.addActionListener(this);
			btnBold.addActionListener(this);
			btnItalic.addActionListener(this);
			btnPlain.addActionListener(this);
			sizeSpinner.addChangeListener(this);
			choiceFont.addItemListener(this);
			
			
			
			
			
		}
		
	
		textArea.setBounds(10, 4, 525, 78);
		contentPanel.add(textArea);
	}


	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==btnBold)
		{
			f = "bold";
		}
		if (e.getSource() == btnItalic)
		{
			f = "italic";
		}
		if (e.getSource() == btnPlain)
		{
			f = "plain";
		}
		if (e.getSource() == cancelButton)
		{
			dispose();
		}
		if (e.getSource() == okButton)
		{
			try
			{
			String string;
			string = sizeSpinner.getValue().toString();
			Integer i = Integer.parseInt(string);
			size = i;
			}
			catch(NumberFormatException w)
			{
				JOptionPane.showMessageDialog(this,"You written a wrong value!", "Error",JOptionPane.ERROR_MESSAGE);	
			}
			
			
			
	
			if (f == "bold")
			{
				System.out.println("dimensiunea:" + size);
				fp.drawString = textArea.getText();
				fp.font = new Font(fontStyle, Font.BOLD, size);
				
			}
			if (f == "italic")
			{
				fp.drawString = textArea.getText();
				fp.font = new Font(fontStyle, Font.ITALIC, size);
			}
			if (f == "plain")
			{
				fp.drawString = textArea.getText();
				fp.font = new Font(fontStyle,Font.PLAIN,size);
			
			}
			fp.forma = "string";
			dispose();
		}
	}



	public void stateChanged(ChangeEvent e)
	{
	}

	public void itemStateChanged(ItemEvent e)
	{
		fontStyle = choiceFont.getSelectedItem();
	}
}
