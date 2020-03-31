public class Virus {
	
	private String nom;
	private double virulence;
	private int tMaladie;
	private double lethalite;

	public Virus(double v, int tM, double l) {
		nom="Vivi";
		virulence = v;
		tMaladie = tM;
		lethalite = l;
	}
	public Virus(double v, int tM, double l,String n) {
		virulence = v;
		tMaladie = tM;
		lethalite = l;
		nom=n;
	}	

	public double getVirulence() { return virulence; }

	public int getTMaladie() { return tMaladie; }

	public double getLethalite() { return lethalite; }



	/*TODO : ce serait une méthode qui s'exécute régulièrement, qui modifie aléatoirement
	  et légèrement les paramètres du virus.
	 */
	public void mutation() {

	}
	public String toString(){
		return "Le virus "+nom+" a les stats suivantes:\n Virulence:"+virulence+"\n Temps avant retablissement:"+tMaladie+"\n Lethalite:"+lethalite;
	}


}
