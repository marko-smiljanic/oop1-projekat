package zad09_korisnik;

import java.text.ParseException;
import java.util.ArrayList;

import aplikacija.Aplikacija;
import zad01_restoran.Restoran;
import zad07_korpa.Korpa;
import zad10_porudzbina.Porudzbina;

public abstract class Korisnik {

	protected String ime;
	protected String prezime;
	protected String korisnickoIme;
	protected String lozinka;
	
	public Korisnik() {
		super();
	}
	
	
	public Korisnik(String ime, String prezime, String korisnickoIme, String lozinka) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
	
	public String uCSV() {
		return this.ime + "," + this.prezime + "," + this.korisnickoIme + "," + this.lozinka + ",";  //treba dodati zarez na kraj?? zbog problema u aplikaciji i splitovanja
	}
	
	public static Korisnik izCSV(String csv) throws ParseException {
		String[] razbijeni = csv.split(",");
		
		switch(razbijeni[0]) {
			case "Administrator":
				Administrator noviAd = new Administrator(razbijeni[1], razbijeni[2], razbijeni[3], razbijeni[4]);
				return noviAd;
			case "VlasnikRestorana":
				// veze izmedju restorana i vlasnika se prave naknadno
				VlasnikRestorana noviVl = new VlasnikRestorana(razbijeni[1], razbijeni[2], razbijeni[3], razbijeni[4]);
				return noviVl;
			case "Kupac":
				Kupac noviKu = new Kupac(razbijeni[1], razbijeni[2], razbijeni[3], razbijeni[4], Double.parseDouble(razbijeni[5]), razbijeni[6]);
				return noviKu;
			default:
				return null;
		}
	}
	
	public abstract int meni() throws ParseException;
	

	
}
