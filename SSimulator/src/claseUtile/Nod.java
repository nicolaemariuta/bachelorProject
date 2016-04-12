package claseUtile;

import java.awt.Color;
import java.io.Serializable;

import mainPack.PoartaLogica;

public class Nod implements Serializable

{
	PoartaLogica poarta = null;
	Coordonate coord = null;
	Conexiune con = null;
	Color culoare = Color.black;
	boolean conectat = false;
	Conexiune nodat = null;
	
	

	

	
	public Nod(PoartaLogica poarta, Coordonate coord, Conexiune con )
	{
		this.poarta = poarta;
		this.coord = coord;
		this.con = con;
	}
	
	public PoartaLogica getPoarta() {
		return poarta;
	}

	public void setPoarta(PoartaLogica poarta) {
		this.poarta = poarta;
	}

	public Coordonate getCoord() {
		return coord;
	}

	public void setCoord(Coordonate coord) {
		this.coord = coord;
	}

	public Conexiune getCon() {
		return con;
	}

	public void setCon(Conexiune con) {
		this.con = con;
	}
	
	public Conexiune getCont(Conexiune con)
	{
		return con;
	}
	
	public Color getCuloare() {
		return culoare;
	}

	public void setCuloare(Color culoare) {
		this.culoare = culoare;
	}
	
	public boolean isConectat() {
		return conectat;
	}

	public void setConectat(boolean conectat) {
		this.conectat = conectat;
	}
	
	public Conexiune getNodat() {
		return nodat;
	}

	public void setNodat(Conexiune nodat) {
		this.nodat = nodat;
	}


}
