package zad09_korisnik;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import aplikacija.Aplikacija;
import zad01_restoran.Restoran;
import zad03_stavka.Jelo;
import zad03_stavka.Pice;
import zad03_stavka.Stavka;
import zad04_tip_jela.TipJela;
import zad05_tip_pica.TipPica;
import zad06_jedinica_mere.JedinicaMere;
import zad07_korpa.Korpa;
import zad08_stavka_u_korpi.StavkaUKorpi;
import zad10_porudzbina.Porudzbina;
import zad11_tip_dostave.TipDostave;
import zad12_prikaz.Prikaz;

public class Kupac extends Korisnik implements Prikaz {
	
	private double raspolozivoNovcanoStanje;
	private String kucnaAdresa;
	private Korpa korpa;
	private Collection<Porudzbina> porudzbina;
	
	
	public Kupac(String ime, String prezime, String korisnickoIme, String lozinka, double raspolozivoNovcanoStanje,
			String kucnaAdresa) {
		super(ime, prezime, korisnickoIme, lozinka);
		this.raspolozivoNovcanoStanje = raspolozivoNovcanoStanje;
		this.kucnaAdresa = kucnaAdresa;
		this.korpa = new Korpa(this, new ArrayList<StavkaUKorpi>());
		this.porudzbina = new ArrayList<Porudzbina>();
	}

	public double getRaspolozivoNovcanoStanje() {
		return raspolozivoNovcanoStanje;
	}

	public void setRaspolozivoNovcanoStanje(double raspolozivoNovcanoStanje) {
		this.raspolozivoNovcanoStanje = raspolozivoNovcanoStanje;
	}

	public String getKucnaAdresa() {
		return kucnaAdresa;
	}

	public void setKucnaAdresa(String kucnaAdresa) {
		this.kucnaAdresa = kucnaAdresa;
	}

	public Korpa getKorpa() {
		return korpa;
	}

	public void setKorpa(Korpa korpa) {
		this.korpa = korpa;
	}

	public Collection<Porudzbina> getPorudzbina() {
		return porudzbina;
	}

	public void setPorudzbina(Collection<Porudzbina> porudzbina) {
		this.porudzbina = porudzbina;
	}

	@Override
	public String uCSV() {
		return this.getClass().getSimpleName() + "," + super.uCSV() + raspolozivoNovcanoStanje + "," + kucnaAdresa + ",";
	}

	@Override
	public int meni() throws ParseException {
		System.out.println("1. Odjava korisnika " + "\n"
						 + "  --prikaz menija: " + "\n"
						 + "     2. Jednostavan prikaz menija" + "\n"
						 + "     3. Detaljan priakz menija" + "\n"
						 + "4. Dodavanje stavke u korpu" + "\n"
						 + "5. Uklanjanje stavke iz korpe" + "\n"
						 + "6. Porucivanje" + "\n"
								
								
							
							+ "0. Kraj programa");
		System.out.println("Unesite broj ispred opcije u meniju: ");
		int unos = Integer.parseInt(Aplikacija.tastatura.nextLine());
		return unos;
	}

	@Override
	public void jednostavanPrikazMenija() throws ParseException, IOException {  //kod kupca je isto kao i kod vlasnika, samo sto ovde umesto rednog broja u meniju dodajem sifru proizvoda koja predstavlja indeks tog elementa u listi stavki( datom u restoranu) 
		ArrayList<Restoran> izlistaniRestorani = new ArrayList<Restoran>();
		int broj = 0;
		for(Restoran rr : Aplikacija.restorani) {
			if(rr.getMeni() != null) {
				broj++;
				System.out.println(broj + ". " + rr.getNaziv());
				izlistaniRestorani.add(rr);
			}
		}
		System.out.println("Izaberite broj ispred restorana ciji meni zelite da vidite: ");
		int unos = Integer.parseInt(Aplikacija.tastatura.nextLine());
		while(unos <= 0 || unos > izlistaniRestorani.size()) {
			System.out.println("Pogresan unos!");
			System.out.println("Izaberite broj ispred restorana ciji meni zelite da vidite: ");
			unos = Integer.parseInt(Aplikacija.tastatura.nextLine());
		}
		Restoran izabraniRestoran = izlistaniRestorani.get(unos - 1);
		izabraniRestoran.getMeni().jednostavanPrikazMenija();
	}

	@Override
	public void detaljanPrikazMenija() throws ParseException, IOException {
		ArrayList<Restoran> izlistaniRestorani = new ArrayList<Restoran>();
		int broj = 0;
		for(Restoran rr : Aplikacija.restorani) {
			if(rr.getMeni() != null) {
				broj++;
				System.out.println(broj + ". " + rr.getNaziv());
				izlistaniRestorani.add(rr);
			}
		}
		System.out.println("Izaberite broj ispred restorana ciji meni zelite da vidite: ");
		int unos = Integer.parseInt(Aplikacija.tastatura.nextLine());
		while(unos <= 0 || unos > izlistaniRestorani.size()) {
			System.out.println("Pogresan unos!");
			System.out.println("Izaberite broj ispred restorana ciji meni zelite da vidite: ");
			unos = Integer.parseInt(Aplikacija.tastatura.nextLine());
		}
		Restoran izabraniRestoran = izlistaniRestorani.get(unos - 1);
		izabraniRestoran.getMeni().detaljanPrikazMenija();
	}
	
	public void dodavanjeStavkeUKorpu() throws ParseException, IOException {
		System.out.println("1. Naziv" + "\n"
						 + "2. Iznos za besplatnu dostavu: ");
		System.out.println("Odaberite broj ispred opcije za pretragu restorana: ");
		int unos = Integer.parseInt(Aplikacija.tastatura.nextLine());
		while(unos != 1 && unos != 2) {
			System.out.println("Pogresan unos!");
			System.out.println("1. Naziv" + "\n"
							 + "2. Iznos za besplatnu dostavu: ");
			System.out.println("Odaberite broj ispred opcije za pretragu restorana: ");
		}
		
		Restoran izabraniRestoran = null;
		
		if(unos == 1) {
			//da li sam prvo trebao da odstampam sve restorane na uvid?
			System.out.println("Unesite naziv restorana: ");
			String unosNaziva = Aplikacija.tastatura.nextLine();
			for(Restoran rr : Aplikacija.restorani) {
				if(rr.getNaziv().equalsIgnoreCase(unosNaziva)) {  //mozda sam mogao i sa contains
					izabraniRestoran = rr;
					break;
				}
			}
			System.out.println("Izabrali ste restoran: " + izabraniRestoran.getNaziv());
		}else if(unos == 2) {
			ArrayList<Restoran> izabraniRestoraniLista = new ArrayList<Restoran>();  //jer istom besplatnom dostavom moze da ih bude vise, dok je naziv jedinstveni
			System.out.println("Unesite iznos za besplatnu dostavu: ");
			double unosIznosa = Double.parseDouble(Aplikacija.tastatura.nextLine());
			for(Restoran rr : Aplikacija.restorani) {
				if(rr.getIznosZaBesplatnuDostavu() <= unosIznosa) {
					izabraniRestoraniLista.add(rr);
				}
			}
			System.out.println("Ovi restorani odgovaraju vasoj pretrazi: " + "\n");
			int broj = 0;
			for(Restoran rr : izabraniRestoraniLista) {
				broj++;
				System.out.println(broj + ". " + rr.getNaziv() + ", iznos za besplatnu dostavu: " + rr.getIznosZaBesplatnuDostavu());
			}
			System.out.println("Unesite broj ispred restorana ");
			int unosKonacnogRestorana = Integer.parseInt(Aplikacija.tastatura.nextLine());
			izabraniRestoran = izabraniRestoraniLista.get(unosKonacnogRestorana - 1);
			
			System.out.println("Izabrali ste restoran: " + izabraniRestoran.getNaziv() + ", iznos za besplatnu dostavu je: " + izabraniRestoran.getIznosZaBesplatnuDostavu());
		}
		
		if(izabraniRestoran.getMeni() != null) {
			izabraniRestoran.getMeni().detaljanPrikazMenija();
			
			ArrayList<Stavka> listaStavkiIzabranogRest = izabraniRestoran.getMeni().getLiStavki();
			
			System.out.println("Unesite sifru (broj) ispred proizvoda koji zelite da dodate u korpu: ");
			int unosBrojaStavke = Integer.parseInt(Aplikacija.tastatura.nextLine());
			while(unosBrojaStavke < 0 || unosBrojaStavke > listaStavkiIzabranogRest.size()) {
				System.out.println("Pogresan unos!!");
				System.out.println("Unesite sifru (broj) ispred proizvoda koji zelite da dodate u korpu: ");
				unosBrojaStavke = Integer.parseInt(Aplikacija.tastatura.nextLine());
			}
			
			Stavka izabranaStavka = listaStavkiIzabranogRest.get(unosBrojaStavke);   //ovde ne ide unos - 1 jer su brojevi u funkciji prikaz dati direktno kao i indeksi
			
						System.out.println("1. Komad" + "\n"
								+ "2. Litar" + "\n"
								+ "3. Mililitar" + "\n"
								+ "4. Gram");
			System.out.println("Unesite broj ispred opcije za jedinicu mere: ");
			int unosJedMere = Integer.parseInt(Aplikacija.tastatura.nextLine());
			while(unosJedMere != 1 && unosJedMere != 2 && unosJedMere != 3 && unosJedMere != 4) {
				System.out.println("Pogresan unos!!");
				System.out.println("1. Komad" + "\n"
						+ "2. Litar" + "\n"
						+ "3. Mililitar" + "\n"
						+ "4. Gram");
				System.out.println("Unesite broj ispred opcije za jedinicu mere: ");
				unosJedMere = Integer.parseInt(Aplikacija.tastatura.nextLine());
			}
			
			JedinicaMere jm;
			switch(unosJedMere) {
				case 1:
					jm = JedinicaMere.KOMAD;  //mozda sam samo trebao ostaviti komad ali svakako sam ostavio da kada se porucuje da se cena samo mnozi sa kolicinom
					break;					  //ako se pivo npr. prodaje u mililitrima sta ce mu opcija za narucivanje u gramima ili litrima, narucis po komad i gotovo, znas koliko ti stize
				case 2:
					jm = JedinicaMere.LITAR;
					break;
				case 3:
					jm = JedinicaMere.MILILITAR;
					break;
				case 4:
					jm = JedinicaMere.GRAM;
					break;
				default: 
					System.out.println("Pogresan unos!");	
					return;									
			}
			
			System.out.println("Unesite kolicinu(broj): ");
			int kolicina = Integer.parseInt(Aplikacija.tastatura.nextLine());
			while(kolicina <= 0) {
				System.out.println("Pogresan unos!!");
				System.out.println("Unesite kolicinu(broj): ");
				kolicina = Integer.parseInt(Aplikacija.tastatura.nextLine());
			}
			
			double konacnaCena = izabranaStavka.getCena() * kolicina;
			
			StavkaUKorpi stavkaUK = new StavkaUKorpi(Aplikacija.korpa, izabranaStavka, kolicina, konacnaCena, jm);
			
			Aplikacija.korpa.setKupac(this);  //svaki put nek setuje kupca, nije bitno, valjda
			
			if(Aplikacija.korpa.getKolekcijaStavkiUkorpi() == null) {
				Aplikacija.korpa.setKolekcijaStavkiUkorpi(new ArrayList<StavkaUKorpi>());
				Aplikacija.korpa.getKolekcijaStavkiUkorpi().add(stavkaUK);
			}else {
				Aplikacija.korpa.getKolekcijaStavkiUkorpi().add(stavkaUK);
			}
			System.out.println("Uspesno ste dodali stavku u korpu!");
			
		}else {
			System.out.println("Restoran koji ste izabrali trenutno nema meni!" + "\n");
			return;
		}
	}
	
	public void uklanjanjeStavkeIzKorpe() {
		ArrayList<StavkaUKorpi> listaStavkiUKorpi = (ArrayList<StavkaUKorpi>) Aplikacija.korpa.getKolekcijaStavkiUkorpi();
		int broj = 0;
		
		if(Aplikacija.korpa.getKupac().equals(this)) {
			for(StavkaUKorpi st : listaStavkiUKorpi) {
				broj++;
				System.out.println(broj + ". " + st.getStavka().getNaziv() + " " + st.getStavka().getKolicina() + "-" + st.getStavka().getJedinicaMere() + ", Narucena kolicina: " + st.getKolicina() + ", cena: " + st.getCena());
			}
			
			System.out.println("Unesite broj ispred stavke koju zelite da obrisete: ");
			int unosBrojaStavke = Integer.parseInt(Aplikacija.tastatura.nextLine());
			while(unosBrojaStavke <= 0 || unosBrojaStavke > listaStavkiUKorpi.size()) {
				System.out.println("Pogresan unos!!");
				System.out.println("Unesite broj ispred stavke koju zelite da obrisete: ");
				unosBrojaStavke = Integer.parseInt(Aplikacija.tastatura.nextLine());
			}
			listaStavkiUKorpi.remove(unosBrojaStavke - 1);  //odmah brisem element na tom indeksu, bez pravljenja izabrane stavke = lsita(unos - 1).
			System.out.println("Uspesno ste obrisali stavku iz korpe!");
		
		}else {
			System.out.println("Ovaj kupac nije napravio korpu!");
			return;
		}
	}
	
	public void porucivanje() {
		ArrayList<StavkaUKorpi> listaStavkiUKorpi = (ArrayList<StavkaUKorpi>) Aplikacija.korpa.getKolekcijaStavkiUkorpi();
		
		if(Aplikacija.korpa.getKupac().equals(this)) {
			
			System.out.println("1. Kucna adresa" + "\n"
							 + "2. Druga adresa" + "\n"
							 + "3. Bez dostave");
			System.out.println("Izaberite broj ispred tipa dostave: ");
			int unosTipaDostave = Integer.parseInt(Aplikacija.tastatura.nextLine());
			while(unosTipaDostave != 1 && unosTipaDostave != 2 && unosTipaDostave != 3) {
				System.out.println("Pogresan unos!!");
				System.out.println("Izaberite broj ispred tipa dostave: ");
				unosTipaDostave = Integer.parseInt(Aplikacija.tastatura.nextLine());
			}
			TipDostave td;
			String adresa = null;
			if(unosTipaDostave == 1) {
				td = TipDostave.KUCNAADRESA;
				adresa = this.getKucnaAdresa();
			}else if(unosTipaDostave == 2) {
				td = TipDostave.DRUGAADRESA;
				System.out.println("Unesite adresu: ");
				adresa = Aplikacija.tastatura.nextLine();
			}else if(unosTipaDostave == 3) {
				td = TipDostave.BEZDOSTAVE;
			}else {
				td = null;
			}
			
			StavkaUKorpi st = listaStavkiUKorpi.get(0);
			String ss = st.getStavka().uCSV();
			String[] razbijeni = ss.split(",");
			String imeRest = razbijeni[3];
			
			Restoran izabrani = null;
			for(Restoran rr : Aplikacija.restorani) {
				if(rr.getNaziv().equals(imeRest)) {
					izabrani = rr;
					break;
				}
			}
			
			double ukupnaCena = 0;
			for(StavkaUKorpi stt : listaStavkiUKorpi) {
				ukupnaCena += stt.getCena();
			}
			
			if(td == TipDostave.BEZDOSTAVE) {
				ukupnaCena += 0; //ukupna cena ne bi trebalo da se menja ako je je bez dostave	
			}else {
				if(ukupnaCena < izabrani.getIznosZaBesplatnuDostavu()) {
					ukupnaCena += 220;
				}
			}
						//ovde mi je mozak otkazao poslusnost. (:
			
			Porudzbina por = new Porudzbina(this, adresa, td, listaStavkiUKorpi);  //sad bi trebalo da se ubaci u aplikaciju negde u kolekciju porudzbina
			Aplikacija.korpa = new Korpa();
		}
	}
	
	public void potvrdaPorudzbine() {  
		
	}
	
	
	
	
	
}
