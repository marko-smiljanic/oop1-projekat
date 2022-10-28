package zad07_korpa;

import java.util.Collection;

import zad06_jedinica_mere.JedinicaMere;
import zad08_stavka_u_korpi.StavkaUKorpi;
import zad09_korisnik.Kupac;

public class Korpa {
	
	private Kupac kupac;
	private Collection<StavkaUKorpi> kolekcijaStavkiUkorpi;

	public Korpa() {
		super();
	}
	
	public Korpa(Kupac kupac, Collection<StavkaUKorpi> kolekcijaStavkiUkorpi) {
		super();
		this.kupac = kupac;
		this.kolekcijaStavkiUkorpi = kolekcijaStavkiUkorpi;
	}

	public Kupac getKupac() {
		return kupac;
	}

	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}

	public Collection<StavkaUKorpi> getKolekcijaStavkiUkorpi() {
		return kolekcijaStavkiUkorpi;
	}

	public void setKolekcijaStavkiUkorpi(Collection<StavkaUKorpi> kolekcijaStavkiUkorpi) {
		this.kolekcijaStavkiUkorpi = kolekcijaStavkiUkorpi;
	}
	
	
	
	
	
	
	
}
