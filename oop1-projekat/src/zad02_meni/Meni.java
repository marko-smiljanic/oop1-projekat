package zad02_meni;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import aplikacija.Aplikacija;
import zad01_restoran.Restoran;
import zad03_stavka.Jelo;
import zad03_stavka.Pice;
import zad03_stavka.Stavka;
import zad04_tip_jela.TipJela;
import zad05_tip_pica.TipPica;
import zad12_prikaz.Prikaz;

public class Meni implements Prikaz {

	private ArrayList<Stavka> liStavki;
	private Restoran restoran;

	public Meni() {
		super();
	}
	
	public Meni(ArrayList<Stavka> liStavki) {
		super();
		this.liStavki = liStavki;
	}

	public ArrayList<Stavka> getLiStavki() {
		return liStavki;
	}

	public void setLiStavki(ArrayList<Stavka> liStavki) {
		this.liStavki = liStavki;
	}
	
	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	@Override
	public void jednostavanPrikazMenija() throws ParseException, IOException {
		
		//////// POZIVANJE FUKNCIJE //////// treba za copy-paste
		
//		ArrayList<Restoran> izlistaniRestorani = new ArrayList<Restoran>();
//		int broj = 0;
//		for(Restoran rr : Aplikacija.restorani) {
//			if(rr.getMeni() != null) {
//				broj++;
//				System.out.println(broj + ". " + rr.getNaziv());
//				izlistaniRestorani.add(rr);
//			}
//		}
//		System.out.println("Izaberite broj ispred restorana ciji meni zelite da vidite: ");
//		int unos = Integer.parseInt(Aplikacija.tastatura.nextLine());
//		while(unos <= 0 || unos > izlistaniRestorani.size()) {
//			System.out.println("Pogresan unos!");
//			System.out.println("Izaberite broj ispred restorana ciji meni zelite da vidite: ");
//			unos = Integer.parseInt(Aplikacija.tastatura.nextLine());
//		}
//		Restoran izabraniRestoran = izlistaniRestorani.get(unos - 1);
//		izabraniRestoran.getMeni().jednostavanPrikazMenija();  // ili detaljni
		
		
		System.out.println("*****************************" + "\n" 
				 		+ "*****************************");
		System.out.println(" ###  Restoran " + this.restoran.getNaziv() + "  ###");
		System.out.println("        Dobrodosli!" + "\n");
		System.out.println("********** Meni ***********" + "\n");
		
		System.out.println("-Sifra porizvoda--Naziv--Cena-" + "\n");
		
		System.out.println("*****PICE: " + "\n");
		
		System.out.println("**Bezalkoholno: ");
		for(Stavka s : liStavki) {
			if(s instanceof Pice) {
				ArrayList<TipPica> tpLista = (ArrayList<TipPica>) ((Pice) s).getTipPica();
				for(TipPica t : tpLista) {
					if(t == TipPica.BEZALKOHOLNO) {
						System.out.println(liStavki.indexOf(s) + "  " + s.getNaziv() + " " + s.getCena());
					}
				}
			}
		}
		System.out.println("**Alkoholno: ");
		for(Stavka s : liStavki) {
			if(s instanceof Pice) {
				ArrayList<TipPica> tpLista = (ArrayList<TipPica>) ((Pice) s).getTipPica();
				for(TipPica t : tpLista) {
					if(t == TipPica.ALKOHOLNO) {
						System.out.println(liStavki.indexOf(s) + "  " + s.getNaziv() + " " + s.getCena());
					}
				}
			}
		}
		System.out.println("\n");
		
		System.out.println("*****JELO: " + "\n");
		
		System.out.println("**Glavno jelo: ");
		for(Stavka s : liStavki) {
			if(s instanceof Jelo) {
				if(((Jelo) s).getTipJela() == TipJela.GLAVNOJELO) {
					System.out.println(liStavki.indexOf(s) + "  " + s.getNaziv() + " " + s.getCena());
				}
			}
		}
		System.out.println("**Predjelo: ");
		for(Stavka s : liStavki) {
			if(s instanceof Jelo) {
				if(((Jelo) s).getTipJela() == TipJela.PREDJELO) {
					System.out.println(liStavki.indexOf(s) + ". " + s.getNaziv() + " " + s.getCena());
				}
			}
		}
		System.out.println("**Salata: ");
		for(Stavka s : liStavki) {
			if(s instanceof Jelo) {
				if(((Jelo) s).getTipJela() == TipJela.SALATA) {
					System.out.println(liStavki.indexOf(s) + "  " + s.getNaziv() + " " + s.getCena());
				}
			}
		}
		System.out.println("**Desert: ");
		for(Stavka s : liStavki) {
			if(s instanceof Jelo) {
				if(((Jelo) s).getTipJela() == TipJela.DESERT) {
					System.out.println(liStavki.indexOf(s) + "  " + s.getNaziv() + " " + s.getCena());
				}
			}
		}	
		System.out.println("*****************************" + "\n" 
						 + "*****************************" + "\n");	
		
	}

	@Override
	public void detaljanPrikazMenija() throws ParseException, IOException {
		
		System.out.println("*****************************" + "\n" 
				 		+ "*****************************");
		System.out.println(" ###  Restoran " + this.restoran.getNaziv() + "  ###");
		System.out.println("        Dobrodosli!" + "\n");
		System.out.println("********** Meni ***********" + "\n");
		
		System.out.println("-Sifra porizvoda--Naziv--Kolicina u jedinici mere--Cena-" + "\n");
		
		System.out.println("*****PICE: " + "\n");
		
		System.out.println("**Bezalkoholno: " + "\n");
		System.out.println("*Gazirano: ");
		for(Stavka s : liStavki) {
			if(s instanceof Pice) {
				ArrayList<TipPica> tpLista = (ArrayList<TipPica>) ((Pice) s).getTipPica();
				for(TipPica t : tpLista) {
					if(t == TipPica.GAZIRANO) {  //u ovom momentu sam skontao da uopste nisam morao da pisem for petlju nego sam mogao da gadjam indeksima jednostavno, jer znam na kojem indeksu je jedan od parova gazirano/negazirano... alkoholno/bezalkoholno...itd
						TipPica t2 = tpLista.get(1);  //zao mi je sto sam ovo tek sada skontao, ali nema veze, ostavicu tako da ne gubim vreme
						if(t2 == TipPica.BEZALKOHOLNO) {
							System.out.println(liStavki.indexOf(s) + "  " + s.getNaziv() + " " + s.getKolicina() + " " + s.getJedinicaMere() + " " + s.getCena());
						}
					}
				}
			}
		}
		System.out.println("*Negazirano: ");
		for(Stavka s : liStavki) {
			if(s instanceof Pice) {
				ArrayList<TipPica> tpLista = (ArrayList<TipPica>) ((Pice) s).getTipPica();
				for(TipPica t : tpLista) {
					if(t == TipPica.NEGAZIRANO) {
						TipPica t2 = tpLista.get(1);
						if(t2 == TipPica.BEZALKOHOLNO) {
							System.out.println(liStavki.indexOf(s) + "  " + s.getNaziv() + " " + s.getKolicina() + " " + s.getJedinicaMere() + " " +  s.getCena());
						}
					}
				}
			}
		}
		System.out.println("\n");
		System.out.println("**Alkoholno: " + "\n");
		for(Stavka s : liStavki) {
			if(s instanceof Pice) {
				ArrayList<TipPica> tp = (ArrayList<TipPica>) ((Pice) s).getTipPica();
				for(TipPica t : tp) {
					if(t == TipPica.ALKOHOLNO) {
						System.out.println(liStavki.indexOf(s) + "  " + s.getNaziv() + " " + s.getKolicina() + " " + s.getJedinicaMere() + " " +  s.getCena());
					}
				}
			}
		}
		System.out.println("\n");
		
		System.out.println("*****JELO: " + "\n");
		
		System.out.println("**Glavno jelo: ");
		for(Stavka s : liStavki) {
			if(s instanceof Jelo) {
				if(((Jelo) s).getTipJela() == TipJela.GLAVNOJELO) {
					System.out.println(liStavki.indexOf(s) + "  " + s.getNaziv() + " " + s.getKolicina() + " " + s.getJedinicaMere() +  " " + s.getCena());
				}
			}
		}
		System.out.println("**Predjelo: ");
		for(Stavka s : liStavki) {
			if(s instanceof Jelo) {
				if(((Jelo) s).getTipJela() == TipJela.PREDJELO) {
					System.out.println(liStavki.indexOf(s) + "  " + s.getNaziv() + " " + s.getKolicina() + " " + s.getJedinicaMere() + " " +  s.getCena());
				}
			}
		}
		System.out.println("**Salata: ");
		for(Stavka s : liStavki) {
			if(s instanceof Jelo) {
				if(((Jelo) s).getTipJela() == TipJela.SALATA) {
					System.out.println(liStavki.indexOf(s) + "  " + s.getNaziv() + " " + s.getKolicina() + " " + s.getJedinicaMere() + " " + s.getCena());
				}
			}
		}
		System.out.println("**Desert: ");
		for(Stavka s : liStavki) {
			if(s instanceof Jelo) {
				if(((Jelo) s).getTipJela() == TipJela.DESERT) {
					System.out.println(liStavki.indexOf(s) + "  " + s.getNaziv() + " " + s.getKolicina() + " " + s.getJedinicaMere() + " " + s.getCena());
				}
			}
		}	
		System.out.println("*****************************" + "\n" 
						 + "*****************************" + "\n");		
	}

	
	
	
	
}
