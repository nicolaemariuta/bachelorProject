package mainPack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JTabbedPane;

import javax.swing.JScrollPane;



public class AfisareRezultat extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	int inaltime;
	int latime;
	Stack<PoartaLogica> porti = null;
	
	private JTable table;
	Stack<String> coloane;
	
	
	public AfisareRezultat(int latime, int inaltime, final Stack<PoartaLogica> porti, Stack<String> coloane)
	{
		
		this.inaltime = inaltime;
		this.latime = latime;
		this.porti = porti;
		this.coloane = coloane;
		
		
		
		setBounds(100, 100, 830, 759);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPanel.add(tabbedPane);
		
		
		
		JPanel panelTabel = new JPanel();
		tabbedPane.addTab("Table", null, panelTabel, null);
		
	
	
		
		
		
		String[][] data  = new String[inaltime][latime];
		
		for(int i = 0; i< inaltime; i++)
		{
			for(int j = 0; j< latime; j++)
			{
			
				data[i][j] = String.valueOf(porti.get(j).getValor()[i]);
			}
		}
		
		
	
		
		
		

		
	
		String[] cln = new String[coloane.size()];
		for(int i = 0; i<coloane.size(); i++)
		{
			cln[i] = coloane.get(i);
		}
		 
		 DefaultTableModel model = new DefaultTableModel(data,cln);
		
		 table = new JTable(model)
		 {
			 public Component prepareRenderer (TableCellRenderer renderer, int index_row, int index_col)
			 {
				 Component comp = super.prepareRenderer(renderer, index_row, index_col);
				 
				 if(porti.get(index_col).getType().equals("IN") )
				 {
					 comp.setBackground(new Color(255,0,0,125));
				 }
				 if(porti.get(index_col).getType().equals("OUT"))
				 {
					 comp.setBackground(new Color(0,255,0,125));
				 }
				 if(!porti.get(index_col).getType().equals("OUT")  && !porti.get(index_col).getType().equals("IN")  &&  !porti.get(index_col).isImportant())
				 {
				
					 comp.setBackground(Color.white);
				 }
				 
				 if(!porti.get(index_col).getType().equals("OUT")  && !porti.get(index_col).getType().equals("IN")  &&  porti.get(index_col).isImportant())
				 {
					 comp.setBackground(Color.LIGHT_GRAY);
				 }
				 
				 
				 return comp;
			 }
			
		 };
		 
		 
		 
		 
		 
		 
		 JTableHeader header = table.getTableHeader();
		 header.setBackground(Color.yellow);
	
		 table.setBounds(624, 663, 800, 540);
		 table.setVisible(true);
		 table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		 panelTabel.setLayout(null);
				
		 JScrollPane pane = new JScrollPane(table);
		 
		 pane.setBounds(0, 0, 800, 666);
		 panelTabel.add(pane);
		 
		
		 
		
		
		
		
	}
}
