package zad08_stavka_u_korpi;

import zad03_stavka.Stavka;
import zad06_jedinica_mere.JedinicaMere;
import zad07_korpa.Korpa;

public class StavkaUKorpi {

	private Korpa korpa;
	private Stavka stavka;
	private int kolicina;
	private double cena;
	private JedinicaMere jedinicaMere;
	
	
	public StavkaUKorpi(Korpa korpa, Stavka stavka, int kolicina, double cena,
			JedinicaMere jedinicaMere) {
		super();
		this.korpa = korpa;
		this.stavka = stavka;
		this.kolicina = kolicina;
		this.cena = cena;
		this.jedinicaMere = jedinicaMere;
	}


	public Korpa getKorpa() {
		return korpa;
	}

	public void setKorpa(Korpa korpa) {
		this.korpa = korpa;
	}

	public Stavka getStavka() {
		return stavka;
	}

	public void setStavka(Stavka stavka) {
		this.stavka = stavka;
	}
	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public JedinicaMere getJedinicaMere() {
		return jedinicaMere;
	}

	public void setJedinicaMere(JedinicaMere jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}
	
	
	
	
}
