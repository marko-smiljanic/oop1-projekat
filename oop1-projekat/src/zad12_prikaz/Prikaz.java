package zad12_prikaz;

import java.io.IOException;
import java.text.ParseException;

public interface Prikaz {
	public void jednostavanPrikazMenija() throws ParseException, IOException;
	public void detaljanPrikazMenija() throws ParseException, IOException;
	
}
