package claseUtile;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JDialog;

import mainPack.FereastraPrincipala;

public class DialogCulori extends Dialog implements AdjustmentListener
{
	FereastraPrincipala fp;
	
	private Scrollbar rValue, gValue, bValue, aValue;
	private Culoare culoare = new Culoare();
	
	Color c = Color.black;
	
	public DialogCulori(final FereastraPrincipala fp)
	{
		super(fp);
		
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				fp.culoare = c;
				fp.setTitle("Color: "+ fp.culoare.toString() +" and shape: " + fp.forma);
				fp.labelCuloare.setBackground(c);
				dispose();
		
			}
		});

		Panel main = new Panel();
		Panel rgbValues = new Panel();
		rgbValues.setLayout(new GridLayout(4,1));
		
		rValue = new Scrollbar(Scrollbar.HORIZONTAL, 0,1,0,256);
		rValue.setBackground(Color.RED);
		
		gValue = new Scrollbar (Scrollbar.HORIZONTAL, 0, 1, 0 ,256);
		gValue.setBackground(Color.GREEN);
		
		bValue = new Scrollbar (Scrollbar.HORIZONTAL, 0 , 1, 0, 256);
		bValue.setBackground(Color.BLUE);
		
		aValue = new Scrollbar (Scrollbar.HORIZONTAL , 0 , 1 , 0,  256);
		aValue.setValue(255);
		aValue.setBackground(Color.lightGray);
		
		add(culoare, BorderLayout.CENTER);
		
		rgbValues.add(rValue);
		rgbValues.add(gValue);
		rgbValues.add(bValue);
		rgbValues.add(aValue);
		add(rgbValues, BorderLayout.SOUTH);
		setSize(200,200);
		
	

		
		
		rValue.addAdjustmentListener(this);
		gValue.addAdjustmentListener(this);
		bValue.addAdjustmentListener(this);
		aValue.addAdjustmentListener(this);
		
	}


	public void adjustmentValueChanged(AdjustmentEvent e) 
	{
		int r = rValue.getValue();
		int g = gValue.getValue();
		int b = bValue.getValue();
		int a = aValue.getValue();
		c = new Color (r, g, b, a);
		culoare.color= c;
		culoare.repaint();
		
	}

}
