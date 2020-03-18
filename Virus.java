public class Virus {
	
	private double infectuo;
	private int tMaladie;
	private double lethalite;
	
	public Virus(double i, int tI, int tM, double l) {
		infectuo = i;
		tMaladie = tM;
		lethalite = l;
	}
	
	public double getInfectuo() { return infectuo; }
		
	public int getTMaladie() { return tMaladie; }
	
	public double getLethalite() { return lethalite; }
	
	/*TODO : ce serait une méthode qui s'exécute régulièrement, qui modifie aléatoirement
	  et légèrement les paramètres du virus. 
	 */ 
	public void mutation() {
		
	}
		
	
}

