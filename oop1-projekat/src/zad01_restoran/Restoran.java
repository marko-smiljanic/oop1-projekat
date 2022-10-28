package zad01_restoran;

import java.text.ParseException;
import java.util.ArrayList;

import aplikacija.Aplikacija;
import zad02_meni.Meni;
import zad03_stavka.Stavka;
import zad09_korisnik.Korisnik;
import zad09_korisnik.VlasnikRestorana;

public class Restoran {
	
	private String naziv;
	private double iznosZaBesplatnuDostavu;  //dinara
	private Meni meni;
	private VlasnikRestorana vlasnik;
	private String korImeVlasnika; // nema ni get() ni set(), ovo privremeno stoji da bi mogao pronaci svog vlasnika; potrebno na pocetku rada
	
	
	public Restoran(String naziv, double iznosZaBesplatnuDostavu, Meni meni, String korImeVlasnika) {
		super();
		this.naziv = naziv;
		this.iznosZaBesplatnuDostavu = iznosZaBesplatnuDostavu;
		this.meni = meni;
		this.korImeVlasnika = korImeVlasnika;
	}
	
	public String getNaziv() {
		return naziv;
	}
	
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getIznosZaBesplatnuDostavu() {
		return iznosZaBesplatnuDostavu;
	}
	
	public void setIznosZaBesplatnuDostavu(double iznosZaBesplatnuDostavu) {
		this.iznosZaBesplatnuDostavu = iznosZaBesplatnuDostavu;
	}

	public Meni getMeni() {
		return meni;
	}

	public void setMeni(Meni meni) {
		this.meni = meni;
	}

	public VlasnikRestorana getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(VlasnikRestorana vlasnik) {
		this.vlasnik = vlasnik;
	}
	
	public String uCSV() {
		String nesto;
		if(this.getVlasnik() == null) {
			nesto = null;
		}else {
			nesto = this.getVlasnik().getKorisnickoIme();
		}
		String nesto2;
		if(this.getMeni() == null) {
			nesto2 = null;
		}else {
			nesto2 = "ImaMeni";
		}
		
		return this.getClass().getSimpleName() + "," + this.naziv + "," + this.iznosZaBesplatnuDostavu + "," + nesto2 + "," + nesto + ",";
	}	
	
	public static Restoran izCSV(String csv) throws ParseException {
		String[] razbijeni = csv.split(",");
		Meni m;
		if(razbijeni[3].equals("ImaMeni")) {
			m = new Meni(new ArrayList<Stavka>());
		}else {
			m = null;
		}		
		return new Restoran(razbijeni[1], Double.parseDouble(razbijeni[2]), m, razbijeni[4]);  //razbijeni[4] je korImeVlasnika, ono koje je vec dodeljeno prethodno napisanom funkcijom dodaj vlasnika. Samo je ono upisano u fajl, i preko tog podatka dodajemo i vlasniku u kolekciju taj restoran
	}
	
	public void povezivanjeSaVlasnikom() {
		if (this.vlasnik != null || this.korImeVlasnika == null) {
			return; // ako je vec povezan ili ako ne moze da se poveze
		}
		for (Korisnik kk : Aplikacija.korisnici) {
			if (kk instanceof VlasnikRestorana) {
				if (kk.getKorisnickoIme().equals(this.korImeVlasnika)) {
					((VlasnikRestorana) kk).getKolekcijaRestorana().add(this);
					this.vlasnik = (VlasnikRestorana) kk;
				}
			}
		}
	}
	
}
