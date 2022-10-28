package zad03_stavka;

import java.util.Collection;

import zad05_tip_pica.TipPica;
import zad06_jedinica_mere.JedinicaMere;

public class Pice extends Stavka {
	
	private Collection<TipPica> tipPica;

	
	public Pice(String naziv, int kolicina, JedinicaMere jedinicaMere, Collection<TipPica> tipPica, String nazivRest, double cena) {
		super(naziv, kolicina, jedinicaMere, nazivRest, cena);
		this.tipPica = tipPica;
		this.nazivRest = nazivRest;
	}

	public Collection<TipPica> getTipPica() {
		return tipPica;
	}

	public void setTipPica(Collection<TipPica> tipPica) {
		this.tipPica = tipPica;
	}
	
	public String uCSV() {
		String nesto = "";
		for(TipPica tp : this.tipPica) {
			nesto += tp + "-";
		}
		return this.getClass().getSimpleName() + "," + super.uCSV() + nesto + "," + this.nazivRest + "," + this.cena + ",";
	}
	
	
	
	
	
}
