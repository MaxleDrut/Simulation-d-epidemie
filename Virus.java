public class Virus {

	private double virulence;
	private int tMaladie;
	private double lethalite;

	public Virus(double v, int tM, double l) {
		virulence = v;
		tMaladie = tM;
		lethalite = l;
	}

	public double getVirulence() { return virulence; }

	public int getTMaladie() { return tMaladie; }

	public double getLethalite() { return lethalite; }



	/*TODO : ce serait une méthode qui s'exécute régulièrement, qui modifie aléatoirement
	  et légèrement les paramètres du virus.
	 */
	public void mutation() {

	}


}
