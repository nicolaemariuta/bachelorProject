package claseUtile;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.io.Serializable;
import java.util.Stack;

import mainPack.PoartaLogica;

public class Conexiune implements Serializable
{
	PoartaLogica in = null;
	PoartaLogica out = null;
	
	Coordonate start = null;
	Coordonate end = null;
	Coordonate middle = null;
	
	boolean node = false;
	int index = 0;
	Color culoare = Color.black;

	boolean areNod = false;
	
	Nod nod = null;

	Line2D line1  = null;
	Line2D line2  = null;
	
	public Stack<Conexiune> conexiuni = new Stack();

	public Conexiune(PoartaLogica in, PoartaLogica out, Coordonate start, Coordonate end, int index)
	{
		this.in = in;
		this.out = out;
		
		this.start = start;
		this.end = end;
		this.index = index;
	}
	
	public PoartaLogica getIn() {
		return in;
	}
	public void setIn(PoartaLogica in) {
		this.in = in;
	}
	public PoartaLogica getOut() {
		return out;
	}
	public void setOut(PoartaLogica out) {
		this.out = out;
	}
	public Coordonate getStart() {
		return start;
	}
	public void setStart(Coordonate start) {
		this.start = start;
	}
	public Coordonate getEnd() {
		return end;
	}
	public void setEnd(Coordonate end) {
		this.end = end;
	}
	public Coordonate getMiddle() {
		return middle;
	}
	public void setMiddle(Coordonate middle) {
		this.middle = middle;
	}
	
	public boolean isNode() {
		return node;
	}

	public void setNode(boolean node) {
		this.node = node;
	}
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	

	public Color getCuloare() {
		return culoare;
	}

	public void setCuloare(Color culoare) {
		this.culoare = culoare;
	}
	
	public Line2D getLine1() {
		return line1;
	}

	public void setLine1(Line2D line1) {
		this.line1 = line1;
	}

	public Line2D getLine2() {
		return line2;
	}

	public void setLine2(Line2D line2) {
		this.line2 = line2;
	}
	
	public boolean isAreNod() {
		return areNod;
	}

	public void setAreNod(boolean areNod) {
		this.areNod = areNod;
	}

	public Nod getNod() {
		return nod;
	}

	public void setNod(Nod nod) {
		this.nod = nod;
	}


	

}
