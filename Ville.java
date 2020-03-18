
public class Ville {
	
	private String nom;
	private long nMorts;
	private long popTotale;
	private long sains;
	private long infectes;
	private long retablis;
	private float[] stades;
	
	// Ici est créée la variable facteurTransmissionUrbain. Sa valeur prend une des valeurs de stades.
	// Elle est à différencier de l'infectiosité du virus dans la mesure où elle est relative à l'organisation
	// sociale faite pour éviter la propagation du virus. Elle est comprise entre 0 (tout le monde est confiné, aucune sortie, aucune contamination) et 1(vie normale,
	// l'infectiosité est celle du virus). Dans le modèle le plus simple, c'est une constante, mais on peut la faire varier en fonction de paramètres quantitatifs
	// pour recréer les différents stades de quarantaine. Il est clair que si les mesures sont adaptées dans une ville, elles doivent avoir une incidence sur les villes
	// autour (plan national). Ainsi, c'est un pays entier qui applique les mesures de quarantaine. L'idée, c'est qu'on pourrait différencier dans région plusieurs
	// pays, et dès qu'une ville atteint certains critères de seuil, toutes les villes de ce pays appliquent le même état de quarantaine.
	// Dans un premier temps, je ne ferai que définir les conditions de seuil pour une ville, et je pourrai voir le reste avec les autres ensuite.
	// On essaiera plusieurs modèles mathématiques pour décrire l'efficaticé des stades? Pour l'instant linéaire.
	private float facteurTransmissionUrbain;
	
	
	//Par défaut, une ville crée est vierge de l'épidémie en cours
	public Ville(String n, int pT,String fTU) {
		nom = n;
		popTotale = pT;
		sains = pT;
		infectes = 0;
		retablis = 0;
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
	public Ville(String n, int pT) {
		nom = n;
		popTotale = pT;
		sains = pT;
		infectes = 0;
		retablis = 0;
		stades= new float[6];
			for (int i=0;i<6;i++){
				stades[i]=1;
			}
		facteurTransmissionUrbain=stades[0];
	}
	
	//TODO : set l'état initial de la ville
	public void infectionInitale(int s, int i, int r) {
		sains=s;
		retablis=r;
		infectes=i;
	}
	
	//TODO : fait évoluer les paramètres de la ville selon le modèle SIR et les propriétés du virus
	public void propagation(Virus v) {
		
		long nouveauxCas=(long)((v.getInfectuo()*facteurTransmissionUrbain)*sains*infectes);
		long nouveauxRetablissements= (long)((1/(v.getTMaladie()))*infectes);
		long nMorts=(long)(v.getLethalite()*infectes);
		
		sains=sains-nouveauxCas;
		infectes= infectes+nouveauxCas-nouveauxRetablissements-nMorts;
		retablis= retablis+nouveauxRetablissements;
		popTotale= popTotale-nMorts;
		
		if(infectes>(popTotale/1000)){
			facteurTransmissionUrbain=stades[1];
		}
		if(infectes>(popTotale/200)){
			facteurTransmissionUrbain=stades[2];
		}
		if(infectes>(popTotale/100)){
			facteurTransmissionUrbain=stades[3];
		}
		if(infectes>(popTotale/50)){
			facteurTransmissionUrbain=stades[4];
		}
		if(infectes>(popTotale/20)){
			facteurTransmissionUrbain=stades[5];
		}
		
	}
	
}

