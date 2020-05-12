
public class Population {
	
	private long taille;
	private long nbInfectes;
	private long nbMort =0;
	private int resilience;
	private long[] habitat = new long [4]; // une zone d'habitation est caractérisé par des coordonnées
	
	public Population ( long t, int r, long x, long y, long w, long h ){
		taille = t;
		resilience =r;
		habitat[0] = x;
		habitat[1] = y;
		habitat[2] = w;
		habitat[3] = h;
	}
	
	public void death() {
		
		taille--;
		
	}
	
	public void contagion( long n ){
		
		nbInfectes = nbInfectes + n * resilience;
		
	}	
		
	

}

