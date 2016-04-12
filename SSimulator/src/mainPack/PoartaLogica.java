package mainPack;

import java.awt.Color;
import java.io.Serializable;
import java.util.Stack;

import claseUtile.Coordonate;

public class PoartaLogica implements Serializable
{
	String type;
	Coordonate coord;
	Color culoare;
	Color defColor;
	int nrIn;
	
	int[] valor = null;
	
	
	


	boolean important = false;
	

	String nume = "";
	int value = 3;

	public Stack<PoartaLogica> intrari = new Stack();
	public Coordonate[] in = new Coordonate[5];
	public int[] intr = {0,0,0,0,0};
	boolean nod = false;
	PoartaLogica iesire;

	String iiesire;
	
	boolean oout = false;




	Coordonate out;


	int size;
	String orientare;
	public PoartaLogica(Coordonate coord, String orientare, int size)
	{
		this.coord = coord;
		this.orientare = orientare;
		this.size = size;
	}
	
	public PoartaLogica(Coordonate coord, String orientare, int size, Color culoare)
	{
		this.coord = coord;
		this.orientare = orientare;
		this.size = size;
		this.culoare = culoare;
	}
	


	public PoartaLogica(String type, Coordonate coord,Color culoare, int size)
	{
		this.type = type;
		this.coord = coord;
		this.size = size;
		this.orientare = "dreapta";
		this.culoare = culoare;
		this.setDefColor(culoare);
		
		this.setNrIn(2);

		iesire = null;
		
		out = null;
		
		for (int i = 0; i<in.length; i++)
		{
			in[i] = null;
		}
		
		if(type == "AMPL")
		{
			this.setNrIn(1);
			
		}
		else
		if(type == "NON")
		{
			this.setNrIn(1);
			
		}
		else
		if(type == "IN")
		{
			this.setNrIn(1);
			
		}
		else
		if(type == "OUT")
		{
			this.setNrIn(1);
			
		}
		else
		{
			this.setNrIn(2);
			
		}
		
	}
	
	
	public Color getDefColor() {
		return defColor;
	}


	public void setDefColor(Color defColor) {
		this.defColor = defColor;
	}

	
	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public Coordonate getCoord() {
		return coord;
	}



	public void setCoord(Coordonate coord) {
		this.coord = coord;
	}



	public Color getCuloare() {
		return culoare;
	}



	public void setCuloare(Color culoare) {
		this.culoare = culoare;
	}



	public int getSize() {
		return size;
	}



	public void setSize(int size) {
		this.size = size;
	}

	public String getOrientare() {
		return orientare;
	}


	public void setOrientare(String orientare) {
		this.orientare = orientare;
	}
	
	public int getNrIn() {
		return nrIn;
	}


	public void setNrIn(int nrIn) {
		this.nrIn = nrIn;
	}
	
	public boolean isNod() {
		return nod;
	}


	public void setNod(boolean nod) {
		this.nod = nod;
	}
	
	
	public boolean isImportant() {
		return important;
	}


	public void setImportant(boolean important) {
		this.important = important;
	}


	
	public PoartaLogica getIesire() {
		return iesire;
	}


	public void setIesire(PoartaLogica iesire) {
		this.iesire = iesire;
	}

	public Coordonate getOut() {
		return out;
	}


	public void setOut(Coordonate out) {
		this.out = out;
	}
	
	
	public String getIiesire() {
		return iiesire;
	}


	public void setIiesire(String iiesire) {
		this.iiesire = iiesire;
	}

	public boolean isOout() {
		return oout;
	}


	public void setOout(boolean oout) {
		this.oout = oout;
	}
	
	public String getNume() {
		return nume;
	}


	public void setNume(String nume) {
		this.nume = nume;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}
	
	public boolean isAreNod() {
		return areNod;
	}


	public void setAreNod(boolean areNod) {
		this.areNod = areNod;
	}
	
	public int[] getValor() {
		return valor;
	}


	public void setValor(int[] valor) {
		this.valor = valor;
	}



	boolean areNod = false;
	
	
	
}
