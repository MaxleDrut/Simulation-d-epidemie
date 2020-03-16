
public class Ville {
	
	private String nom;
	private long popTotale;
	private long sains;
	private long contamines;
	private long infectes;
	private long retablis;
	
	
	//Par défaut, une ville crée est vierge de l'épidémie en cours
	public Ville(String n, int pT) {
		nom = n;
		popTotale = pT;
		sains = pT;
		contamines = 0;
		infectes = 0;
		retablis = 0;
		
	}
	
	//TODO : set l'état initial de la ville
	public void infectionInitale(int s, int c, int i, int r) {
		
	}
	
	//TODO : fait évoluer les paramètres de la ville selon le modèle SIR et les propriétés du virus
	public void propagation(Virus v) {
		
	}
	
}

