package zad09_korisnik;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import aplikacija.Aplikacija;
import zad01_restoran.Restoran;
import zad02_meni.Meni;
import zad10_porudzbina.Porudzbina;

public class Administrator extends Korisnik {
	
	
	public Administrator(String ime, String prezime, String korisnickoIme, String lozinka) {
		super(ime, prezime, korisnickoIme, lozinka);
	}

	@Override
	public String uCSV() {
		return this.getClass().getSimpleName() + "," + super.uCSV();
	}

	@Override
	public int meni() throws ParseException {		
		System.out.println("1. Odjava korisnika" + "\n"
						+ "2. Dodavanje restorana" + "\n" 
						+ "3. Kreiranje korisnika" + "\n"
						+ "4. Dodavanje vlasnika restorana" + "\n"
						
						+ "0. Izlazak iz programa");
		System.out.println("Unesite broj ispred opcije u meniju: ");
		int unos = Integer.parseInt(Aplikacija.tastatura.nextLine());
		return unos;
	}
	
	public static Restoran dodavanjeRestorana() throws IOException {
		System.out.println("Unesite naziv restorana: ");
		String naziv = Aplikacija.tastatura.nextLine();
		System.out.println("Unesite iznos preko koga je besplatna dostava: ");
		double iznos = Double.parseDouble(Aplikacija.tastatura.nextLine());
		
		Restoran r = new Restoran(naziv, iznos, null, null);
		return r;
	}
	
	
	public static Korisnik kreiranjeKorisnika() throws IOException {
		System.out.println("Izaberite broj ispred korisnika kojeg zelite da kreirate: " + "\n"
							+ "1. Administrator" + "\n"
							+ "2. Vlasnik restorana" + "\n"
							+ "3. Kupac");
		int broj = Integer.parseInt(Aplikacija.tastatura.nextLine());
		
		while(broj != 1 && broj != 2 && broj != 3) {
			System.out.println("Uneli ste pogresno broj!");
			System.out.println("Izaberite broj ispred korisnika kojeg zelite da kreirate: " + "\n"
					+ "1. Administrator" + "\n"
					+ "2. Vlasnik restorana" + "\n"
					+ "3. Kupac");
			broj = Integer.parseInt(Aplikacija.tastatura.nextLine());
		}
		
		System.out.println("Unesite ime: ");
		String ime = Aplikacija.tastatura.nextLine();
		System.out.println("Unesite prezime: ");
		String prezime = Aplikacija.tastatura.nextLine();
		System.out.println("Unesite korisnicko ime: ");
		String korIme = Aplikacija.tastatura.nextLine();
		System.out.println("Unesite lozinku: ");
		String lozinka = Aplikacija.tastatura.nextLine();
		
		if(broj == 1) {			
			System.out.println("Uspesno ste kreirali novog Administratora!");
			return new Administrator(ime, prezime, korIme, lozinka);
		}else if(broj == 2) {			
			System.out.println("Uspesno ste kreirali novog Vlasnika restorana!");
			return new VlasnikRestorana(ime, prezime, korIme, lozinka);
		}else if(broj == 3) {
			System.out.println("Raspolozivo novcano stanje za nove kupce je generisano na 10 000");
			double stanje = 10000;
			System.out.println("Unesite adresu: ");
			String adresa = Aplikacija.tastatura.nextLine();			
			System.out.println("Uspesno ste kreirali novog Kupca!");
			return new Kupac(ime, prezime, korIme, lozinka, stanje, adresa);
		}
		return null;		
	}
	
	public static void dodavanjeVlasnikaRestorana() throws IOException {
		int broj = 0;
		ArrayList<Korisnik> izlistaniKorisnici = new ArrayList<Korisnik>();
		for(Korisnik kk : Aplikacija.korisnici) {
			if(kk instanceof VlasnikRestorana) {
				broj++;
				System.out.println(broj + ". " + kk.getIme() + " " +  kk.getPrezime() + " " + "[" + kk.getKorisnickoIme() + "]");
				izlistaniKorisnici.add(kk);
			}
		}		
		System.out.println("Izaberite broj ispred korisnika kojeg zelite da postavite za vlasnika nekog restorana: ");
		int unos = Integer.parseInt(Aplikacija.tastatura.nextLine());
		while(unos <= 0 || unos > izlistaniKorisnici.size()) {
			System.out.println("Pogresan unos!!");
			System.out.println("Izaberite broj ispred korisnika kojeg zelite da postavite za vlasnika nekog restorana: ");
			unos = Integer.parseInt(Aplikacija.tastatura.nextLine());
		}
		
		VlasnikRestorana izabraniVlasnik = (VlasnikRestorana) izlistaniKorisnici.get(unos - 1);	
		
		int broj2 = 0;   //mogao sam ponovo postaviti vec koriscneu promenljivu broj na 0, ali mi je lepse ovako
		ArrayList<Restoran> izlistaniRestorani = new ArrayList<Restoran>();
		for(Restoran rr : Aplikacija.restorani) {
			broj2++;
			System.out.println(broj2 + ". " + rr.getNaziv());
			izlistaniRestorani.add(rr);
		}		
		System.out.println("Unesite broj ispred restorana kojem zelite da dodate vlasnika: " + "[" + izabraniVlasnik.getKorisnickoIme() + "]: ");
		int unos2 = Integer.parseInt(Aplikacija.tastatura.nextLine());
		while(unos2 <= 0 || unos2 > izlistaniRestorani.size()) {
			System.out.println("Pogresan unos!!");
			System.out.println("Unesite broj ispred restorana kojem zelite da dodate vlasnika: " + "[" + izabraniVlasnik.getKorisnickoIme() + "]: ");
			unos2 = Integer.parseInt(Aplikacija.tastatura.nextLine());
		}
		
		Restoran izabraniRestoran = izlistaniRestorani.get(unos2 - 1);   //ne moram da vracam rezultat u aplikaciju jer java radi sa referencama
		
		izabraniRestoran.setVlasnik(izabraniVlasnik);
		System.out.println("Uspesno ste dodali vlasnika!");	
		
		izabraniVlasnik.getKolekcijaRestorana().add(izabraniRestoran);   //dodamo i vlasniku restoran u kolekciju
		System.out.println("Uspesno ste vlasniku dodali restoran!");	
		// na kraju rezultat ne mora da se vraca u polja u aplikaciji jer java radi sa referencama u memoriji, odnosno, uvek se radi sa istim objektima
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
