package aplikacija;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import zad01_restoran.Restoran;
import zad03_stavka.Stavka;
import zad07_korpa.Korpa;
import zad09_korisnik.Administrator;
import zad09_korisnik.Korisnik;
import zad09_korisnik.Kupac;
import zad09_korisnik.VlasnikRestorana;

public class Aplikacija {
	
	public static Scanner tastatura = new Scanner(System.in);
	
	private static Korisnik k;  //prijavljeni korisnik
	
	public static Collection<Restoran> restorani = new ArrayList<Restoran>();
	public static Collection<Korisnik> korisnici = new ArrayList<Korisnik>();
	public static Collection<Stavka> stavke = new ArrayList<Stavka>();
	
	public static Korpa korpa = new Korpa();
	
	

	private static void ucitajKorisnike() throws IOException, ParseException {
		File korisnici = new File("data/korisnici.text");
		FileInputStream fis = new FileInputStream(korisnici);
		String ucitano = new String(fis.readAllBytes());
		fis.close();
		
		System.out.println(ucitano);  ///////////// TODO: //////////////// obrisati, ovo je samo pomoc meni da bih znao sifru i kor ime zbog testiranja
		String[] redovi = ucitano.split("\n");	

		for (String red : redovi) {
			Korisnik k = Korisnik.izCSV(red);
			Aplikacija.korisnici.add(k);
		}
	}
	
	private static void azurirajKorisnike() throws IOException, ParseException {  //upis u fajl
		File kor = new File("data/korisnici.text");
		kor.createNewFile();
		FileOutputStream fos = new FileOutputStream(kor);
		for (Korisnik kk : korisnici) {
			if(kk != null) {
				fos.write(kk.uCSV().getBytes());
				fos.write("\n".getBytes());
			}
		}
		fos.close();
	}
	
	private static void prijavaKorisnika() {
		while (Aplikacija.k == null) {
			System.out.println("Unesite korisnicko ime: ");
			String korIme = tastatura.nextLine();
			System.out.println("Unesite lozinku: ");
			String lozinka = tastatura.nextLine();
			
			for(Korisnik k : korisnici) {
				if(k.getKorisnickoIme().equals(korIme)) {
					if(k.getLozinka().equals(lozinka)) {
						Aplikacija.k = k;
						System.out.println("Ulogovani korisnik: " + k.getKorisnickoIme() + " [" + k.getClass().getSimpleName() + "]");
						return;  //izadje iz cele funkcije
					}else {
						System.out.println("Pogresna sifra!");
						break;  //izadje iz bloka
					}
				}
			}
			System.out.println("Korisnicko ime ne postoji!");
		}
	}
	
	private static void odjavaKorisnika() {
		if(Aplikacija.k != null) {
			Aplikacija.k = null;
			System.out.println("Upravo ste se odjavili.");
		}else {
			System.out.println("Vec ste odjavljeni");
		}
	}
	
	private static void povezivanjeRestoranaSaVlasnicima() {  //povezemo vlasnike i restorane
		for (Restoran rr : restorani) {
			rr.povezivanjeSaVlasnikom();
		}
	}
	
	private static void ucitajRestorane() throws IOException, ParseException {
		File rest = new File("data/restorani.text");
		FileInputStream fis = new FileInputStream(rest);
		String ucitano = new String(fis.readAllBytes());
		fis.close();
		
		System.out.println(ucitano);  ////////////// TODO: /////////////// obrisati, ovo je samo pomoc meni da bih znao sifru i kor ime zbog testiranja
		String[] redovi = ucitano.split("\n");
		
		for(String red : redovi) {
			Restoran r = Restoran.izCSV(red);
			Aplikacija.restorani.add(r);
		}
		// otvaraj fajl
		// ucitava restorane u kolekciju restorani
		// vlasnk je null
	}
	
	private static void azurirajRestorane() throws IOException {  //upis u fajl
		File rest = new File("data/restorani.text");
		rest.createNewFile();
		FileOutputStream fos = new FileOutputStream(rest);
		for (Restoran rr: restorani) {
			if(rr != null) {
				fos.write(rr.uCSV().getBytes());
				fos.write("\n".getBytes());
			}
		}
		fos.close();
	}
	
	private static boolean jedinstvenNazivRestorana(Restoran r) {
		for (Restoran rr: restorani) {
			if (r.getNaziv().equals(rr.getNaziv())) {
				return false;
			}
		}
		return true;
	}
	
	private static void ucitajStavke() throws IOException, ParseException {
		File meni = new File("data/meni.text");
		FileInputStream fis = new FileInputStream(meni);
		String ucitano = new String(fis.readAllBytes());
		fis.close();
		
		System.out.println(ucitano);      ///////////////////////TODO: ///////////////////// obrisati, sluzi meni kao uvid u situaciju
		String[] redovi = ucitano.split("\n");
		for(String red : redovi) {
			Stavka s = Stavka.izCSV(red);
			Aplikacija.stavke.add(s);
		}
	}
	
	private static void azurirajStavke() throws IOException {
		File meni = new File("data/meni.text");
		meni.createNewFile();
		FileOutputStream fos = new FileOutputStream(meni);
		for(Stavka s : stavke) {
			if(s != null) {
				fos.write(s.uCSV().getBytes());
				fos.write("\n".getBytes());
			}
		}
		fos.close();
	}
	
	private static void povezivanjeStavkeSaRestoranom() {  //tj. sa menijima unutar restorana
		for(Stavka ss : stavke) {
			if(ss != null) {
				ss.povezivanjeSaRestoranom();
			}		
		}
	}
	
	
	///////////////////////
	///////////////////////
	public static void main(String[] args) {
		
		try {
			ucitajKorisnike();
			ucitajRestorane();
			povezivanjeRestoranaSaVlasnicima();
			ucitajStavke();
			povezivanjeStavkeSaRestoranom();
			//ucitajPorudzbine();                // ove stvari ne znam da uradim, nemam ideju jednostavno
			//povezivanjePorudzbinaSaKupcima();
			//ucitajStavkeIzPorudzbina();
			//povezivanjeStavkeSaPorudzbinom();

			
			
			System.out.println("Unesite broj ispred opcije: " + "\n" 
								+ "1. Prijava" + "\n" 
								+ "2. Izlazak iz aplikacije");
			int unosOpcije = Integer.parseInt(tastatura.nextLine());
			if(unosOpcije == 1) {
				prijavaKorisnika();
			}else if(unosOpcije == 2) {
				System.out.println("Kraj programa. :)");
				System.exit(1);
			}else {
				System.out.println("Pogresan unos!");
			}
			
			while (true) {  //kad god se zavrsi neka radnja iz menija ja ga vrtim u while petlji
				if (k instanceof Administrator) {
					int broj = k.meni();
					while(true) {
						if(broj == 1) {
							odjavaKorisnika();
							break;  //izlazak iz if-a i prvog while-a, preskace i ide na kraj
						}else if(broj == 2) {
							Restoran r = Administrator.dodavanjeRestorana();
							while (!jedinstvenNazivRestorana(r)) {
								System.out.println("Vec postoji restoran sa datim imenom!" + "\n" + "Pokusajte ponovo!");
								r = Administrator.dodavanjeRestorana();							
							}
							System.out.println("Uspesno ste dodali restoran!");
							restorani.add(r);
							azurirajRestorane();
						}else if(broj == 3) {
							Korisnik kor = Administrator.kreiranjeKorisnika();
							korisnici.add(kor);
							azurirajKorisnike();
						}else if(broj == 4) {
							Administrator.dodavanjeVlasnikaRestorana();
							azurirajRestorane();
							azurirajKorisnike();						
						}else if(broj == 0) {
							System.out.println("Kraj programa. :)");
							System.exit(1);
						}else {
							System.out.println("Pogresan unos!"); //posto je while petlja vraca ga dok ne unese ispravan uslov
						}
						broj = k.meni();
					}
				}
				////////////
				else if (k instanceof Kupac) {
					int broj = k.meni();
					while(true) {
						if(broj == 1) {
							odjavaKorisnika();
							break;
						}else if(broj == 2) {
							((Kupac) k).jednostavanPrikazMenija();
						}else if(broj == 3) {
							((Kupac) k).detaljanPrikazMenija();
						}else if(broj == 4) {
							((Kupac) k).dodavanjeStavkeUKorpu();
						}else if(broj == 5) {
							((Kupac) k).uklanjanjeStavkeIzKorpe();
							
							
							
							
							
						}else if(broj == 0) {
							System.out.println("Kraj programa!");
							System.exit(1);
						}	else {
							System.out.println("Pogresan unos!");
						}					
						broj = k.meni();
					}// od unutrasnje while true-a				
				}
				////////////
				else {
					k = (VlasnikRestorana) k;
					int broj = k.meni();
					while(true) {
						if(broj == 1) {
							odjavaKorisnika();
							break;
						}else if(broj == 2) {
							((VlasnikRestorana) k).kreiranjeMenija();
							azurirajRestorane();
							azurirajKorisnike();
						}else if(broj == 3) {
							((VlasnikRestorana) k).dodavanjeStavkeUMeni();
							azurirajStavke();
							azurirajRestorane();
							azurirajKorisnike();		
						}else if(broj == 4) {
							((VlasnikRestorana) k).brisanjeStavke();
							azurirajStavke();
							azurirajRestorane();
							azurirajKorisnike();
						}else if(broj == 5) {
							((VlasnikRestorana) k).jednostavanPrikazMenija();
						}else if(broj == 6) {
							((VlasnikRestorana) k).detaljanPrikazMenija();	
						}else if(broj == 7) {
							((VlasnikRestorana) k).izmenaCeneStavke();
							azurirajStavke();
							azurirajRestorane();
							azurirajKorisnike();	
						}else if(broj == 0) {
								System.out.println("Kraj programa!");
								System.exit(1);
						}else {
								System.out.println("Pogresan unos!");
						}
						broj = k.meni();  //da nisam stavio ponovno biranje broja, upao bih u beskonacnu petlju gde bi taj broj non stop bila jedna vrednost i stalno bi se vrtela jedna funkcija
					}// od unutrasnjeg while-true-a
				}
				/////////// radi dalje u spoljasnjoj while-true petlji, kada prodju sve odabrane radnje korisnika, tj. kad se odjavi
				System.out.println("Unesite broj ispred opcije: " + "\n" 
									+ "1. Prijava" + "\n" 
									+ "2. Izlazak iz aplikacije");
				unosOpcije = Integer.parseInt(tastatura.nextLine());
				if(unosOpcije == 1) {
					prijavaKorisnika();
				}else if(unosOpcije == 2) {
					System.out.println("Kraj programa. :)");
					System.exit(1);
				}else {
					System.out.println("Pogresan unos!");
				}
			} // od while-true glavnog
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("Greska u radu sa fajlom!");
		}catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Greska u parsiranju!");
		}catch(NumberFormatException e) {
			System.out.println("Unosite brojeve tamo gde se trazi da unesete brojeve!!!");
		}
		catch (Exception e) {
			System.out.println("Nepoznata greska u radu!");
		}
		
		
		// javlja mi se problem kod prijave, nece uvek da se prijavi, jer ne prepoznaje koje je polje sifra... ali kada odem u fajlove i stavim zarez na kraj onda prepoznaje
		// zbog toga su mi zarezi na kraju svakog reda
		// problem citanja poslednjeg elementa iz fajla koji nema na kraju zarez, javilo mi se u prijavi, kada nije htelo da procita i prijavi administratora
		
		// Sve bi trebalo da radi osim stavki: 13 i 14. Nisam znao kako da se snadjem sa njima i sa fajlovima, upisom, citanjem a pogotovo sa povezivanjem.
		// Mozda bih i mogao da odradim da imam jos vremena, ali cisto sumnjam
		
		
	}

}
