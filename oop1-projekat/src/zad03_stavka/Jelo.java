package zad03_stavka;

import zad03_stavka.Stavka;
import zad04_tip_jela.TipJela;
import zad06_jedinica_mere.JedinicaMere;

public class Jelo extends Stavka {
	
	private TipJela tipJela;

	
	public Jelo(String naziv, int kolicina, JedinicaMere jedinicaMere, TipJela tipJela, String nazivRest, double cena) {
		super(naziv, kolicina, jedinicaMere, nazivRest, cena);
		this.tipJela = tipJela;
		this.nazivRest = nazivRest;
	}

	public TipJela getTipJela() {
		return tipJela;
	}

	public void setTipJela(TipJela tipJela) {
		this.tipJela = tipJela;
	}
	
	public String uCSV() {
		return this.getClass().getSimpleName() + "," + super.uCSV() + this.tipJela + "," + this.nazivRest + "," + this.cena + ",";
	}
	
	
	
	
	
	
	
	
}
