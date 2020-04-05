public class Virus {

	private double virulence;
	private int tMaladie;
	private double lethalite;
	private String nom;

	public Virus(double v, int tM, double l, String n) {
		virulence = v;
		tMaladie = tM;
		lethalite = l;
		nom = n;
	}

	public double getVirulence() { return virulence; }

	public int getTMaladie() { return tMaladie; }

	public double getLethalite() { return lethalite; }
	
	public String getNom() { return nom; }

	public String toString() {
		return ("Mon virus nomme "+nom+" a les attributs : virulence = "+virulence+", duree maladie = "+tMaladie+" et lethalite journa = "+lethalite);
	}

	/*TODO : ce serait une méthode qui s'exécute régulièrement, qui modifie aléatoirement
	  et légèrement les paramètres du virus.
	 */
	public void mutation() {

	}


}
