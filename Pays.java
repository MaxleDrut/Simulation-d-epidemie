import java.util.LinkedList;

public class Pays {

	private String nom;
	private long nMorts;
	private long popTotale;
	private long sains;
	private long infectes;
	private long retablis;
	private long morts;
	private float[] stades;
	private int nStade;
	private int jour;

	private LinkedList<Long> PopTotaleJour=new LinkedList<Long>();
	private LinkedList<Long> SainsJour=new LinkedList<Long>();
	private LinkedList<Long> InfectesJour=new LinkedList<Long>();
	private LinkedList<Long> RetablisJour=new LinkedList<Long>();
	private LinkedList<Long> MortsJour=new LinkedList<Long>();
	private LinkedList<Integer> Jours=new LinkedList<Integer>();


	// Ici est créée la variable facteurTransmissionUrbain. Sa valeur prend une des valeurs de stades.
	// Elle est à différencier de l'infectiosité du virus dans la mesure où elle est relative à l'organisation
	// sociale faite pour éviter la propagation du virus. Elle est comprise entre 0 (tout le monde est confiné, aucune sortie, aucune contamination) et 1(vie normale,
	// l'infectiosité est celle du virus). Dans le modèle le plus simple, c'est une constante, mais on peut la faire varier en fonction de paramètres quantitatifs
	// pour recréer les différents stades de quarantaine. Il est clair que si les mesures sont adaptées dans une Pays, elles doivent avoir une incidence sur les Payss
	// autour (plan national). Ainsi, c'est un pays entier qui applique les mesures de quarantaine. L'idée, c'est qu'on pourrait différencier dans région plusieurs
	// pays, et dès qu'une Pays atteint certains critères de seuil, toutes les Payss de ce pays appliquent le même état de quarantaine.
	// Dans un premier temps, je ne ferai que définir les conditions de seuil pour une Pays, et je pourrai voir le reste avec les autres ensuite.
	// On essaiera plusieurs modèles mathématiques pour décrire l'efficaticé des stades? Pour l'instant linéaire.
	private float facteurTransmissionUrbain;
  

	//Par défaut, une Pays crée est vierge de l'épidémie en cours
	public Pays(String n, int pT,String fTU) {
		nom = n;
		popTotale = pT;
		sains = pT;
		infectes = 0;
		retablis = 0;
		morts = 0;
		jour=0;
		stades= new float[6];
		if(fTU=="linear"){
			for(int i=0;i<6;i++){
				stades[i]=(float)(1.0-(0.1*i));
			}
		}
		else{
			for (int i=0;i<6;i++){
				stades[i]=1;
			}
		}
		facteurTransmissionUrbain=stades[0];
	}
	public Pays(String n, int pT) {
		nom = n;
		popTotale = pT;
		sains = pT;
		infectes = 0;
		retablis = 0;
		jour=0;
		stades= new float[6];
			for (int i=0;i<6;i++){
				stades[i]=1;
			}
		facteurTransmissionUrbain=stades[0];
	}

	public LinkedList<Long> getPopTotaleJour(){return PopTotaleJour;}
	public LinkedList<Long> getInfectesJour(){return InfectesJour;}
	public LinkedList<Long> getSainsJour(){return SainsJour;}
	public LinkedList<Long> getRetablisJour(){return RetablisJour;}
	public LinkedList<Long> getMortsJour(){return MortsJour;}
	public LinkedList<Integer> getJours(){return Jours;}


	public long getPop() { return popTotale; }
	public long getSains() { return sains; }
	public long getInfectes() { return infectes; }
	public long getRetablis() { return retablis; }
	public long getMorts() { return morts; }

	public String getNomPays(){ return nom; };

	public void setSains(long s){
		sains=s;
	}

	public void setInfectes(long i){
		infectes=i;
	}

	public void setRetablis(long r){
		retablis=r;
	}

	public void setPopulation(long total){
		popTotale=total;
	}

	//TODO : set l'état initial de la Pays
	public void infectionInitale(int s, int i, int r) {
		sains=s;
		retablis=r;
		infectes=i;
		jour++;
		actualiserStats();
	}
	public void infectionInitale(int i) {
		infectes=i;
		retablis=0;
		sains=popTotale-i;
		actualiserStats();
	}


	//TODO : fait évoluer les paramètres de la Pays selon le modèle SIR et les propriétés du virus
	public void propagation(Virus v) {
		long nouveauxCas=(long)((v.getVirulence()*facteurTransmissionUrbain)*infectes*12.0*(double)sains/popTotale);
		long nouveauxRetablissements= (long)(1.0/(v.getTMaladie())*infectes);
		long nMorts=(long)(v.getLethalite()*infectes);

		sains=sains-nouveauxCas;
		infectes= infectes+nouveauxCas-nouveauxRetablissements-nMorts;
		retablis= retablis+nouveauxRetablissements;
		popTotale= popTotale-nMorts;
		morts=morts+nMorts;
		actualiserStats();
	}
	
	public void actualiserStats(){
		jour++;
		PopTotaleJour.add(popTotale);
		SainsJour.add(sains);
		InfectesJour.add(infectes);
		RetablisJour.add(retablis);
		MortsJour.add(morts);
		Jours.add(jour);
	}

	public String toString(){
		String message= "La Pays de "+nom+" a ces statistiques là: \n PopulationTotale:"+popTotale+"\n Sains:"+sains+" \n Infectés:"+infectes+"\n Retablis:"+retablis+"\n Morts:"+morts;
		return message;
	}

}
