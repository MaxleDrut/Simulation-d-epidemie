
import java.awt.Polygon;
import java.util.LinkedList;


public class Pays {

	private String nom;
	private int nMorts;
	private int popTotale;
	private int sains;
	private int infectes;
	private int retablis;
	private int morts;
	private int jour;
	protected int popInit;
	private static LinkedList<Pays> listeDesPays = new LinkedList<Pays>();
	
	//STATISTIQUES 1 PAYS-
	private LinkedList<Integer> popTotaleJour=new LinkedList<Integer>();
	private LinkedList<Integer> sainsJour=new LinkedList<Integer>();
	private LinkedList<Integer> infectesJour=new LinkedList<Integer>();
	private LinkedList<Integer> retablisJour=new LinkedList<Integer>();
	private LinkedList<Integer> mortsJour=new LinkedList<Integer>();
	private LinkedList<Integer> jours=new LinkedList<Integer>();
	
	//Par défaut, une Pays crée est vierge de l'épidémie en cours
	public Pays(){ 
		
	}
	//Constructeur prenant en charge un nom et une population initiale
	public Pays(String n, int pT) {
		nom = n;
		popTotale = pT;
		popInit = pT;
		sains = pT;
		infectes = 0;
		retablis = 0;
		jour=0;
		listeDesPays.add(this);
	}
	//Getters
	public LinkedList<Integer> getPopTotaleJour() { return popTotaleJour; }
	public LinkedList<Integer> getInfectesJour()  {return infectesJour; }
	public LinkedList<Integer> getSainsJour() { return sainsJour; }
	public LinkedList<Integer> getRetablisJour() { return retablisJour; }
	public LinkedList<Integer> getMortsJour() { return mortsJour; }
	public LinkedList<Integer> getJours() { return jours; }

	public int getPop() { return popTotale; }
	public int getSains() { return sains; }
	public int getInfectes() { return infectes; }
	public int getRetablis() { return retablis; }
	public int getMorts() { return morts; }

	public long[] getStatsPays() {
		long[] stats = {sains,infectes,retablis,morts};
		return stats;
	}
		
	public String getNomPays(){ return nom; }
	
	public static LinkedList<Pays>getListePays(){return listeDesPays;}
	
	//Setters
	
	public void setSains(int s){
		sains=s;
	}

	public void setInfectes(int i){
		infectes=i;
	}

	public void setRetablis(int r){
		retablis=r;
	}

	public void setPopulation(int total){
		popTotale=total;
	}

	/* Si le nombre renseigné de personne à infecter est supérieur à la population totale
	 * Alors on va simplement infecter tout le pays !*/
	
	public void infectionInitale(int i) {
		if(i+infectes>popTotale) {
			i = popTotale-infectes;
		}
		infectes+=i;
		retablis=0;
		sains-=i;
		actualiserStats();
	}


	//Fait évoluer les paramètres de la Pays selon le modèle SIR 
	//et les propriétés du virus; actualise les données statistiques
	public void propagation(Virus v) {
		
		int nouveauxCas=(int)(v.getVirulence()*infectes*(int)sains/popTotale);
		int nouveauxRetablissements= (int)(1.0/(v.getTMaladie())*infectes);
		int nMorts=(int)(v.getLethalite()*infectes);
		sains=sains-nouveauxCas;
		infectes= infectes+nouveauxCas-nouveauxRetablissements-nMorts;
		retablis= retablis+nouveauxRetablissements;
		popTotale= popTotale-nMorts;
		morts=morts+nMorts;
		actualiserStats();
	}
	
	//Methode pour mettre à jour les linked list des statistiques

	public void actualiserStats(){
		jour++;
		popTotaleJour.add(popTotale);
		sainsJour.add(sains);
		infectesJour.add(infectes);
		retablisJour.add(retablis);
		mortsJour.add(morts);
		jours.add(jour);
	}
	
	//Renvoie les stats de la ville.
	public String toString(){
		String message= "La Pays de "+nom+" a ces statistiques : \n PopulationTotale:"+popTotale+"\n Sains:"+sains+" \n Infectés:"+infectes+"\n Retablis:"+retablis+"\n Morts:"+morts;
		return message;
	}

}
