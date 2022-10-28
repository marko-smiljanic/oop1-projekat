package zad09_korisnik;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

import aplikacija.Aplikacija;
import zad01_restoran.Restoran;
import zad02_meni.Meni;
import zad03_stavka.Jelo;
import zad03_stavka.Pice;
import zad03_stavka.Stavka;
import zad04_tip_jela.TipJela;
import zad05_tip_pica.TipPica;
import zad06_jedinica_mere.JedinicaMere;
import zad12_prikaz.Prikaz;

public class VlasnikRestorana extends Korisnik implements Prikaz {
	
	private Collection<Restoran> kolekcijaRestorana;

	public VlasnikRestorana() {
		super();
	}
	
	public VlasnikRestorana(String ime, String prezime, String korisnickoIme, String lozinka) {
		super(ime, prezime, korisnickoIme, lozinka);
		this.kolekcijaRestorana = new ArrayList<Restoran>();
	}

	public Collection<Restoran> getKolekcijaRestorana() {
		return kolekcijaRestorana;
	}

	public void setKolekcijaRestorana(Collection<Restoran> kolekcijaRestorana) {
		this.kolekcijaRestorana = kolekcijaRestorana;
	}
	
	@Override
	public String uCSV() {
		return this.getClass().getSimpleName() + "," + super.uCSV();  //nema potrebe da pise kojim restoranima je on vlasnik
	}

	@Override
	public int meni() throws ParseException {		
		System.out.println("1. Odjava korisnika" + "\n"         
						+ "2. Kreiranje menija" + "\n"
						+ "3. Dodavanje stavke u meni" + "\n"
						+ "4. Brisanje stavke iz menija" + "\n"
						+ "  --prikaz menija: " + "\n"
						+ "     5. Jednostavni" + "\n"
						+ "     6. Detaljni" + "\n"
						+ "7. Izmena cene stavke iz menija" + "\n"
						
						+ "0. Izlazak iz programa");		
		System.out.println("Unesite broj ispred opcije u meniju: ");
		int unos = Integer.parseInt(Aplikacija.tastatura.nextLine());
		return unos;
	}
	
	public void kreiranjeMenija() throws IOException, ParseException {
		ArrayList<Restoran> izlistaniRestorani = new ArrayList<Restoran>();
		int broj = 0;
		for(Restoran rr : this.getKolekcijaRestorana()) {
			broj++;
			System.out.println(broj + ". " + rr.getNaziv());
			izlistaniRestorani.add(rr);
		}
		System.out.println("Izaberite broj ispred restorana u kojem zelite da napravite meni: ");
		int unos = Integer.parseInt(Aplikacija.tastatura.nextLine());
		while(unos <= 0 || unos > izlistaniRestorani.size()) {
			System.out.println("Pogresan unos!");
			System.out.println("Izaberite broj ispred restorana u kojem zelite da napravite meni: ");
			unos = Integer.parseInt(Aplikacija.tastatura.nextLine());
		}
		Restoran izabrani = izlistaniRestorani.get(unos - 1);
		
		if(izabrani.getMeni() != null) {
			System.out.println("Ovaj restoran vec ima meni!");
			return;
		}else {
			izabrani.setMeni(new Meni(new ArrayList<Stavka>()));
			System.out.println("Uspesno ste kreirali meni!");
		}
	}
	
	public void dodavanjeStavkeUMeni() throws IOException, ParseException {
		ArrayList<Restoran> izlistaniRestorani = new ArrayList<Restoran>();
		int broj = 0;
		for(Restoran rr : this.getKolekcijaRestorana()) {
			broj++;
			System.out.println(broj + ". " + rr.getNaziv());
			izlistaniRestorani.add(rr);
		}
		System.out.println("Izaberite broj ispred restorana u ciji meni zelite da dodate stavku: ");
		int unos = Integer.parseInt(Aplikacija.tastatura.nextLine());
		while(unos <= 0 || unos > izlistaniRestorani.size()) {
			System.out.println("Pogresan unos!");
			System.out.println("Izaberite broj ispred restorana u ciji meni zelite da dodate stavku: ");
			unos = Integer.parseInt(Aplikacija.tastatura.nextLine());
		}
		Restoran izabrani = izlistaniRestorani.get(unos - 1);
		
		if(izabrani.getMeni() == null) {
			System.out.println("U izabranom restoranu nemate meni! Morate prvo napraviti meni!");
			return;
		}else {
			izabrani.getMeni().setLiStavki(new ArrayList<Stavka>());  //napravim listu stavki odma na pocetku jer je nemam napravljenu, nigde mi do sada nije trebala!
			
			System.out.println("1. Jelo" + "\n"
		                       + "2. Pice");
			System.out.println("Unesite ispred opcije za vrstu stavke koje zelite da dodate: ");
			int unosTipaStavke = Integer.parseInt(Aplikacija.tastatura.nextLine());
			while(unosTipaStavke != 1 && unosTipaStavke != 2) {
				System.out.println("Pogresan unos!. Unesite pravilno!");
				System.out.println("1. Jelo" + "\n"
	                       			+ "2. Pice");
				System.out.println("Unesite ispred opcije za vrstu stavke koje zelite da dodate: ");
				unosTipaStavke = Integer.parseInt(Aplikacija.tastatura.nextLine());
			}

			System.out.println("Unesite naziv: ");
			String naziv = Aplikacija.tastatura.nextLine();
			System.out.println("Unesite kolicinu(broj): ");
			int kolicina = Integer.parseInt(Aplikacija.tastatura.nextLine());
			while(kolicina <= 0) {
				System.out.println("Pogresan unos!!");
				System.out.println("Unesite kolicinu(broj): ");
				kolicina = Integer.parseInt(Aplikacija.tastatura.nextLine());
			}
			
			System.out.println("1. Komad" + "\n"
								+ "2. Litar" + "\n"
								+ "3. Mililitar" + "\n"
								+ "4. Gram");
			System.out.println("Unesite broj ispred opcije za jedinicu mere: ");
			int opcija = Integer.parseInt(Aplikacija.tastatura.nextLine());
			while(opcija != 1 && opcija != 2 && opcija != 3 && opcija != 4) {
				System.out.println("Pogresan unos!!");
				System.out.println("1. Komad" + "\n"
						+ "2. Litar" + "\n"
						+ "3. Mililitar" + "\n"
						+ "4. Gram");
				System.out.println("Unesite broj ispred opcije za jedinicu mere: ");
				opcija = Integer.parseInt(Aplikacija.tastatura.nextLine());
			}
			
			JedinicaMere jm;
			switch(opcija) {
				case 1:
					jm = JedinicaMere.KOMAD;
					break;
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
					System.out.println("Pogresan unos!");  // ovo pisem samo da mi ne bi izbacivalo da mozda nije promenljiva inicijalizovana		
					return;									// mogao sam i promenljive staviti na null, svejedno totalno
			}
			
			Stavka s;
			if(unosTipaStavke == 1) {
				TipJela tj;
				System.out.println("1. Glavno jelo" + "\n"
									+ "2. Predjelo" + "\n"
									+ "3. Salata" + "\n"
									+ "4. Desert");
				System.out.println("Unesite broj ispred tipa jela: ");
				int opcijaJelo = Integer.parseInt(Aplikacija.tastatura.nextLine());
				while(opcijaJelo != 1 && opcijaJelo != 2 && opcijaJelo != 3 && opcijaJelo != 4) {
					System.out.println("Pogresan unos!!");
					System.out.println("1. Glavno jelo" + "\n"
							+ "2. Predjelo" + "\n"
							+ "3. Salata" + "\n"
							+ "4. Desert");
					System.out.println("Unesite broj ispred tipa jela: ");
					opcijaJelo = Integer.parseInt(Aplikacija.tastatura.nextLine());
				}
				
				switch(opcijaJelo) {
					case 1:
						tj = TipJela.GLAVNOJELO;
						break;
					case 2:
						tj = TipJela.PREDJELO;
						break;
					case 3:
						tj = TipJela.SALATA;
						break;
					case 4:
						tj = TipJela.DESERT;
						break;
					default:
						System.out.println("Pogresan unos!");
						return;
				}
				System.out.println("Unesite cenu jela (u dinaraima): ");
				double unosCene2 = Double.parseDouble(Aplikacija.tastatura.nextLine());
				while(unosCene2 < 0 ) {
					System.out.println("Pogresan unos!!");
					System.out.println("Unesite cneu jela: ");
					unosCene2 = Double.parseDouble(Aplikacija.tastatura.nextLine());
				}
				s = new Jelo(naziv, kolicina, jm, tj, izabrani.getNaziv(), unosCene2);	
				
				//Aplikacija.stavke.add(s);
				
				izabrani.getMeni().getLiStavki().add(s);
				Aplikacija.stavke.add(s);
				System.out.println("Uspesno ste dodali stavku u meni!");
				return;
			
			}else if(unosTipaStavke == 2) {
				ArrayList<TipPica> listaTipovaPica = new ArrayList<TipPica>(); 
				
				System.out.println("1. Gazirano" + "\n"
			                       + "2. Negazirano" + "\n");
				System.out.println("Unesite broj ispred tipa opcije: ");
				int gaziranoNegazirano = Integer.parseInt(Aplikacija.tastatura.nextLine());
				while(gaziranoNegazirano != 1 && gaziranoNegazirano != 2) {
					System.out.println("Pogresan unos!!");
					System.out.println("1. Gazirano" + "\n"
		                       + "2. Negazirano" + "\n");
					System.out.println("Unesite broj ispred tipa opcije: ");
					gaziranoNegazirano = Integer.parseInt(Aplikacija.tastatura.nextLine());
				}
				
				TipPica t1;
				switch(gaziranoNegazirano) {
					case 1:
						t1 = TipPica.GAZIRANO;
						break;
					case 2:
						t1 = TipPica.NEGAZIRANO;
						break;
					default:
						System.out.println("Pogresan unos!");
						return;
				}
				TipPica t2;
				System.out.println("1. Alkoholno" + "\n"
									+ "2. Bezalkoholno");
				System.out.println("Unesite broj ispred opcije: ");
				int alkoholnoBezalkoholno = Integer.parseInt(Aplikacija.tastatura.nextLine());
				while(alkoholnoBezalkoholno != 1 && alkoholnoBezalkoholno != 2) {
					System.out.println("Pogresan unos!!");
					System.out.println("1. Alkoholno" + "\n"
							+ "2. Bezalkoholno");
					System.out.println("Unesite broj ispred opcije: ");
					alkoholnoBezalkoholno = Integer.parseInt(Aplikacija.tastatura.nextLine());
				}
				
				switch(alkoholnoBezalkoholno) {
					case 1:
						t2 = TipPica.ALKOHOLNO;
						break;
					case 2:
						t2 = TipPica.BEZALKOHOLNO;
						break;
					default:
						System.out.println("Pogresno ste uneli!");
						return;
				}
				TipPica t3;
				System.out.println("1. Hladjeno" + "\n"
									+ "2. Nehladjeno");
				System.out.println("Unesite broj ispred opcije: ");
				int hladjenoNehladjeno = Integer.parseInt(Aplikacija.tastatura.nextLine());
				while(hladjenoNehladjeno != 1 && hladjenoNehladjeno != 2) {
					System.out.println("Pogresan unos!!");
					System.out.println("1. Hladjeno" + "\n"
							+ "2. Nehladjeno");
					System.out.println("Unesite broj ispred opcije: ");
					hladjenoNehladjeno = Integer.parseInt(Aplikacija.tastatura.nextLine());
				}
				
				switch(hladjenoNehladjeno) {
					case 1: {
						t3 = TipPica.HLADJENO;
						break;
					}
					case 2: {
						t3 = TipPica.NEHLADJENO;
						break;
					}default: {
						System.out.println("Pogresan unos!");
						return;
					}
				}
				System.out.println("Unesite cenu pica (u dinaraima): ");
				double unosCene = Double.parseDouble(Aplikacija.tastatura.nextLine());
				while(unosCene < 0 ) {
					System.out.println("Pogresan unos!!");
					System.out.println("Unesite cneu pica: ");
					unosCene = Double.parseDouble(Aplikacija.tastatura.nextLine());
				}
				listaTipovaPica.add(t1);
				listaTipovaPica.add(t2);
				listaTipovaPica.add(t3);
				
				s = new Pice(naziv, kolicina, jm, listaTipovaPica, izabrani.getNaziv(), unosCene);
				
				Aplikacija.stavke.add(s);  //ovo je bio kljuc, zato nije radilo! ! !
				
				izabrani.getMeni().getLiStavki().add(s);
				System.out.println("Uspesno ste dodali stavku u meni!");
				return;
			}
		}
	}

	public void brisanjeStavke() throws IOException, ParseException {
		ArrayList<Restoran> izlistaniRestorani = new ArrayList<Restoran>();
		int broj = 0;
		for(Restoran rr : this.getKolekcijaRestorana()) {
			if(rr.getMeni() != null) {
				broj++;
				System.out.println(broj + ". " + rr.getNaziv());
				izlistaniRestorani.add(rr);
			}
		}
		System.out.println("Izaberite broj ispred restorana iz cijeg menija zelite da uklonite stavku iz menija: ");
		int unos = Integer.parseInt(Aplikacija.tastatura.nextLine());
		while(unos <= 0 || unos > izlistaniRestorani.size()) {
			System.out.println("Pogresan unos!");
			System.out.println("Izaberite broj ispred restorana iz cijeg menija zelite da uklonite stavku iz menija: ");
			unos = Integer.parseInt(Aplikacija.tastatura.nextLine());
		}
		Restoran izabraniRestoran = izlistaniRestorani.get(unos - 1);
		ArrayList<Stavka> liStavki = izabraniRestoran.getMeni().getLiStavki();
		
		int broj2 = 0;
		for(Stavka s : liStavki) {
			broj2++;
			System.out.println(broj2 + ". " + s.getNaziv());
		}
		System.out.println("Unesite broj ispred stavke koju zelite da obrisete iz menija: ");
		int unos2 = Integer.parseInt(Aplikacija.tastatura.nextLine());
		while(unos2 <= 0 || unos2 > liStavki.size()) {
			System.out.println("Pogresan unos!");
			System.out.println("Unesite broj ispred stavke koju zelite da obrisete iz menija: ");
			unos2 = Integer.parseInt(Aplikacija.tastatura.nextLine());
		}
		
		
		Stavka izabranaStavka = liStavki.get(unos2 - 1);
		izabraniRestoran.getMeni().getLiStavki().remove(izabranaStavka);
		Aplikacija.stavke.remove(izabranaStavka);
		
		System.out.println("Uspesno ste obrisali stavku iz menija!");
	}

	@Override
	public void jednostavanPrikazMenija() throws ParseException, IOException {
		ArrayList<Restoran> izlistaniRestorani = new ArrayList<Restoran>();
		int broj = 0;
		for(Restoran rr : this.getKolekcijaRestorana()) {
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
	public void detaljanPrikazMenija() throws ParseException, IOException {  // ne znam zasto sam stavio ove izuzetke kada do njih ne moze doci...
		ArrayList<Restoran> izlistaniRestorani = new ArrayList<Restoran>();
		int broj = 0;
		for(Restoran rr : this.getKolekcijaRestorana()) {
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
	
	public void izmenaCeneStavke() throws ParseException, IOException {  //mogao sam kao i kod kupca da u prikaz menija dodam indekse, pa onda da pozovem priakz menija i da ih iz menija unosi i tako bira stavku da menja cenu, ali bi funkcije bile bas iste u kupcu i vlasniku, pa zato nisam, nego kad hoce da menja cenu prikazuje mu se grubo ovako
		ArrayList<Restoran> izlistaniRestorani = new ArrayList<Restoran>();
		int broj = 0;
		for(Restoran rr : this.getKolekcijaRestorana()) {
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
		ArrayList<Stavka> liStavki = izabraniRestoran.getMeni().getLiStavki();
		
		int broj2 = 0;
		for(Stavka s : liStavki) {
			broj2++;
			System.out.println(broj2+ ". " + s.getNaziv() + " " + s.getKolicina() + " " + s.getJedinicaMere() + " " + s.getCena());
		}
		System.out.println("Unesite broj ispred stavke kojoj zelite da promenite cenu: ");
		int unosBr = Integer.parseInt(Aplikacija.tastatura.nextLine());
		while(unosBr <= 0 || unosBr > liStavki.size()) {
			System.out.println("Pogresan unos!");
			System.out.println("Unesite broj ispred stavke kojoj zelite da promenite cenu: ");
			unosBr = Integer.parseInt(Aplikacija.tastatura.nextLine());
		}
		Stavka izabranaStavka = liStavki.get(unosBr - 1);
		
		System.out.println("Unesite novu cenu: ");
		double novaCena = Double.parseDouble(Aplikacija.tastatura.nextLine());
		while(novaCena <= 0) {
			System.out.println("Pogresan unos!!");
			System.out.println("Unesite novu cenu: ");
			novaCena = Double.parseDouble(Aplikacija.tastatura.nextLine());
		}
		
		izabranaStavka.setCena(novaCena);
		System.out.println("Uspesno ste promenili cenu!");
	}
	
	
	
	
	
	
	
	
}
