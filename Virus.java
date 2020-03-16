


public class Virus {
	
	private double infectuo;
	private int tIncubation;
	private int tMaladie;
	private double lethalite;
	
	public Virus(double i, int tI, int tM, double l) {
		infectuo = i;
		tIncubation = tI;
		tMaladie = tM;
		lethalite = l;
	}
	
	public double getInfectuo() { return infectuo; }
	
	public int getIncubation() { return tIncubation; }
	
	public int getMaladie() { return tMaladie; }
	
	public double lethalite() { return lethalite; }
	
	/*TODO : ce serait une méthode qui s'exécute régulièrement, qui modifie aléatoirement
	  et légèrement les paramètres du virus. 
	 */ 
	public void mutation() {
		
	}
		
	
}

