package zad03_stavka;

import java.util.ArrayList;

import aplikacija.Aplikacija;
import zad01_restoran.Restoran;
import zad04_tip_jela.TipJela;
import zad05_tip_pica.TipPica;
import zad06_jedinica_mere.JedinicaMere;

public abstract class Stavka {
	
	protected String naziv;
	protected int kolicina;
	protected JedinicaMere jedinicaMere;
	protected String nazivRest; //privremeno, sluzi za povezivanje objekata
	protected double cena;
	
	
	public Stavka(String naziv, int kolicina, JedinicaMere jedinicaMere, String nazivRest, double cena) {
		super();
		this.naziv = naziv;
		this.kolicina = kolicina;
		this.jedinicaMere = jedinicaMere;
		this.nazivRest = nazivRest;
		this.cena = cena;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public JedinicaMere getJedinicaMere() {
		return jedinicaMere;
	}

	public void setJedinicaMere(JedinicaMere jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}
	
	
	public String uCSV() {
		return this.naziv + "," + this.kolicina + "," + this.jedinicaMere + ",";
	}
	
	public static Stavka izCSV(String csv) {   //treba dodati polje naziv restorana pa ga ispraviti u funkcijama ucsv i izcsv-a
		String[] razbijeni = csv.split(",");
		
		switch(razbijeni[0]) {
			case "Jelo":
				JedinicaMere jedM = null;
				for(JedinicaMere jm : JedinicaMere.values()) {
					if(jm.toString().equals(razbijeni[3])) {
						jedM = jm;
						break;
					}
				}
				TipJela tipJ = null;
				for(TipJela tj : TipJela.values()) {
					if(tj.toString().equals(razbijeni[4])) {
						tipJ = tj;
						break;
					}
				}
				Jelo novoJelo = new Jelo(razbijeni[1], Integer.parseInt(razbijeni[2]), jedM, tipJ, razbijeni[5], Double.parseDouble(razbijeni[6]));  //mogao sam odma return new Jelo(...)
				return novoJelo;
			case "Pice":
				JedinicaMere jedMere = null;
				for(JedinicaMere jm : JedinicaMere.values()) {
					if(jm.toString().equals(razbijeni[3])) {
						jedMere = jm;
					}
				}
				ArrayList<TipPica> listaTipova = new ArrayList<TipPica>();
				String[] tipovi = razbijeni[4].split("-");
				for(int i = 0; i < tipovi.length; i++) {
					for(TipPica tp : TipPica.values()) {
						if(tp.toString().equals(tipovi[i])) {
							listaTipova.add(tp); 		 //mozda i ovde dodati break?
						}
					}
				}
				Pice novoPice = new Pice(razbijeni[1], Integer.parseInt(razbijeni[2]), jedMere, listaTipova, razbijeni[5], Double.parseDouble(razbijeni[6]));
				return novoPice;
			default:
				return null;
		}
	}
	
	public void povezivanjeSaRestoranom() {
		if(this.nazivRest == null) {
			return;
		}
		for(Restoran rr : Aplikacija.restorani) {
			if(rr.getNaziv().equals(this.nazivRest)) {
				if(rr.getMeni().getLiStavki() != null) {
					rr.getMeni().getLiStavki().add(this);
					if (rr.getMeni().getRestoran() == null) { // samo prvi put, kada se doda prva stavka u meni
						rr.getMeni().setRestoran(rr);
					}
				}
			}
		}
	}
	
	
	
	
	
}
