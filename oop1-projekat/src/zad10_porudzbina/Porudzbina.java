package zad10_porudzbina;

import java.util.Collection;
import java.util.Date;

import zad07_korpa.Korpa;
import zad08_stavka_u_korpi.StavkaUKorpi;
import zad09_korisnik.Kupac;
import zad11_tip_dostave.TipDostave;

public class Porudzbina {
	
	private Kupac kupac;
	private String adresa;
	private TipDostave tipDostave;
	private Collection<StavkaUKorpi> stavkaUKorpi;  //dodeljuje se vrednost istog polja iz korpe
	private final long ID;
	
	public Porudzbina(Kupac kupac, String adresa, TipDostave tipDostave, Collection<StavkaUKorpi> stavkaUKorpi) { // kada kreiramo iz korpe
		super();
		this.kupac = kupac;
		this.adresa = adresa;
		this.tipDostave = tipDostave;
		this.stavkaUKorpi = stavkaUKorpi;
		this.ID = new Date().getTime(); // svaka porudzbina ima jedinstven ID
	}

	public Porudzbina(Kupac kupac, String adresa, TipDostave tipDostave, Collection<StavkaUKorpi> stavkaUKorpi, long ID) { // kada citamo iz fajla
		super();
		this.kupac = kupac;
		this.adresa = adresa;
		this.tipDostave = tipDostave;
		this.stavkaUKorpi = stavkaUKorpi;
		this.ID = ID;
	}
	
	public Kupac getKupac() {
		return kupac;
	}

	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public TipDostave getTipDostave() {
		return tipDostave;
	}

	public void setTipDostave(TipDostave tipDostave) {
		this.tipDostave = tipDostave;
	}

	public Collection<StavkaUKorpi> getStavkaUKorpi() {
		return stavkaUKorpi;
	}

	public void setStavkaUKorpi(Collection<StavkaUKorpi> stavkaUKorpi) {
		this.stavkaUKorpi = stavkaUKorpi;
	}
	
	
	
	
}
