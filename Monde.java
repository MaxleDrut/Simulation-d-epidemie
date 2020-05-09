import java.util.*;
import java.util.ArrayList;
import java.io.*;
import java.net.*;

public class Monde {

	/*Pourquoi listePays et liaisonPays sont en static ?
	 *La réponse : il est nécessaire d'accéder aux pays contenus dans listePays, ainsi qu'aux débits, depuis d'autres classes (carte et PolygonePays notamment)
	 *En passant par des statics, on s'assure que les instances sont correctement actualisées.
	 *Si l'on ne les passe pas en static, alors il faudrait actualiser manuellement chaque pays quand on l'utilise dans polygonePays :
	 *On économise donc de la puissance de calcul !*/
	protected static ArrayList<Pays> listePays = null;

	/*convention d'écriture : liaisonPayss[X][Y] => De la Pays X vers la Pays Y. donc non commutatif. Sortie négatives. Entrée positive
	 *Liaison des Payss est en pourcentage de population qui se déplace dans une autre Pays.*/
	private static int[][] liaisonPays = null;
	
	
	/* Définitions des variables utiles pour le décodage de la matrice d'adjacence des liaisons entre pays.  '
	 * 
	 * */
	private double frontFaible, frontMoy, frontForte, hubFaible, hubFort;
	
	/* Constructeurs de Monde :
	 * 	-> Création des pays 
	 * 	-> Création de la matrice d'adjacence 
	 * 	-> set des coéfs de '
	 * */
	public Monde() {
		ArrayList<Pays> listeTempo = new ArrayList<Pays>();
		
		try {
			int i = 0;
			BufferedReader br =new BufferedReader(new FileReader(new File("listepays.txt")));
			
			String l;
			/* lecture du fichier listepays.txt
			 * permet de créer la liste des pays sous forme de ArrayList<Pays>
			 * */ 
			while ((l= br.readLine()) !=null) {
				String[] splitArray = null; //tableau de chaînes
				String str = l;
				splitArray = str.split(",");
				listeTempo.add(new Pays(splitArray[0],Integer.parseInt(splitArray[1])));
				
		}br.close(); 
		} catch (IOException e) { e.printStackTrace(); }
	
		// set des pourcentage de population sortant en fonction du type de liaisons. 
		frontFaible = 0.0001;
		frontMoy = 0.0005;
		frontForte = 0.001;
		hubFaible = 0.0002;
		hubFort = 0.0006;

		
		/*Lecture du fichier matrice adj.csv
		 *Permet de créer la matrice des liaisons
		 *pour voir cette belle matrice : https://docs.google.com/spreadsheets/d/1V4MqJJkmcMB7VqllnY6_Yh6n_44gbsr8_WzuP3ZPz6s/edit#gid=0
		 *avec 1225 cases !! 
		 *La construction de cette matrice est subjective. 
		 * */
		int[][] liaisonTempo = new int[35][35];
		try {
			int i = 0;
			BufferedReader br =new BufferedReader(new FileReader(new File("matrice adj.csv")));
			
			String l;
			
			while ((l= br.readLine()) !=null) {
				String[] splitArray = null; //tableau de chaînes
				String str = l;
				splitArray = str.split(",");
				for(int j = 0; j< splitArray.length;j++){
					if(splitArray[j]!=null) {
						liaisonTempo[i][j] = Integer.parseInt(splitArray[j]);
					}
				}
				i++;
			}
			br.close(); 
		    } catch (IOException e) { e.printStackTrace(); }

		listePays = listeTempo;
		liaisonPays = liaisonTempo;
	}

	//Lorsqu'elle est exécutée, cette méthode réalise fait se propager le virus en interne dans chaque Pays
	public void majPropaPays(Virus vir) {
		//-> appel de propagation pour chaque Pays.
		for(Pays p : listePays){
				p.propagation(vir);
			}
	}

	
	/* Méthode déplacement : 
	 * cette méthode se charge de faire transiter les populations entre les pays. 
	 * Pour se faire, on dertermine la population sortant d'un pays. 
	 * On tire ensuite au sort sa composition en sains, infectés, rétablis.
	 * On traite chaque individu. 
	 * puis on modifier les populations du pays une fois avoir determiner tout les déplacement en suivant ce calcul : Popu actuel - popu sortant+ popu entrante
	 * */

	public void deplacements() {
		// tableau permettant de concerver le nombre de sains : 0, infectés : 1, rétablis : 2 entrant et sortant dans chaque pays. 
		int[][] SortiePopu = new int[listePays.size()][3];
		int[][] EntreePopu = new int[listePays.size()][3];

		// initialisation des tableaux a zero
		for(int i = 0; i < SortiePopu.length ; i++) { 
			for(int j = 0; j < SortiePopu[i].length ; j++) {
				SortiePopu[i][j] = 0;
				EntreePopu[i][j] = 0;
			}
		}
		
		
		for(int i = 0; i < liaisonPays.length ; i++) {
			int popuPays 	 = (int)(listePays.get(i).getPop());
			int popuSains 	 = (int)(listePays.get(i).getSains());
			int popuInfectes = (int)(listePays.get(i).getInfectes());
			int popuRetablis = (int)(listePays.get(i).getRetablis());
			
			//pour chaque pays on détermine le pourcentage de sains, infectés et rétablis. 
			double propSains    = popuSains/(double)popuPays;
			double propInfectes = popuInfectes/(double)popuPays;
			double propRetablis = popuRetablis/(double)popuPays;

			for(int j = 0; j < liaisonPays[0].length; j++) {
				int MvtPopu = valeurDeplacement(i,j); // ValeurDeplacement revoit le nombre de personne se déplaçant. 
				for(int k = 0; k < MvtPopu; k++) { 
					
					 /* le tirage est dans l'intervalle ]0,1[ 
					  * On compare ensuite avec les différentes proportions de sains, infectés et rétablis. 
					  * Nous avons fait le choix de mettre un random pour mettre en évidence le caractère aléatoire de l'état de l'individu sortant
					  **/				
					double tirage = Math.random();
					
					if(tirage <= propSains) { //tirage sains
						SortiePopu[i][0]++;
						EntreePopu[j][0]++;
					}
					if(tirage > propSains && tirage <= propSains + propInfectes) { //tirage infectes
						SortiePopu[i][1]++;
						EntreePopu[j][1]++;
					}
					if(tirage > propSains + propInfectes && tirage <= propSains + propInfectes + propRetablis) { //tirage retablis
						SortiePopu[i][2]++;
						EntreePopu[j][2]++;
					}
				}
			}
		}
		// Set la nouvelle population
		int i = 0;
		for(Pays p : listePays) {
			int sain = ((int)(p.getSains())   + EntreePopu[i][0] - SortiePopu[i][0]);
			int infect = ((int)(p.getInfectes()) + EntreePopu[i][1] - SortiePopu[i][1]);
			int reta = ((int)(p.getRetablis()) + EntreePopu[i][2] - SortiePopu[i][2]);
			
			// s'assuré de la positivité des populations.
			if (sain>0){
				p.setSains(sain);
			} else {
				p.setSains(0);
			}
			if (infect>0){
				p.setInfectes(infect);
			} else {
				p.setInfectes(0);
			}
			if (reta>0){
				p.setRetablis(reta);
			} else{
				p.setRetablis(0);
			}
			p.setPopulation(p.getSains() + p.getInfectes() + p.getRetablis());
			i++;
		}

	}
	
	/*On calcule le nombre de personnes qui se déplacent entre un pays A et B.
	 *Pour cela on prend la population du plus petit pays et l'on cherche le type de connexion (frontière forte, hub...)
	 *Défini dans liaisonPays. 
	 *On renvoie alors la population du plus petit pays*/
	
	private int valeurDeplacement(int i, int j) {
		Pays paysA = listePays.get(i);
		Pays paysB = listePays.get(j);
		
		if(paysA.getInfectes()==0 && paysB.getInfectes()==0 && paysA.getRetablis()==0 && paysB.getRetablis()==0) {
			/*Exception pour le calcul : si les pays n'ont ni infectés ni rétablis (que des sains et des morts)
			 *Alors c'est incohérent de quand même faire le calcul d'échange de pop.
			 *En échangeant uniquement les pays avec au moins un malade on économise en puissance de calcul*/
			return 0; 
		}
		int popMin;
		if(paysA.getPop() > paysB.getPop()) {
			popMin = paysB.getPop();
		} else {
			popMin = paysA.getPop();
		}
		
		switch(liaisonPays[i][j]) { //Pas besoin de break dans ce switch case, les return le font déjà...
			case 1 : //Frontière faible
			return (int) (popMin*frontFaible);
			case 2 : //Frontière moyenne
			return (int) (popMin*frontMoy);
			case 3 : //Frontière forte
			return (int) (popMin*frontForte);
			case 4 : //Hub faible
			return (int) (popMin*hubFaible);
			case 5 : //Hub fort
			return (int) (popMin*hubFort);
			default : //Pas de connexion entre les pays
			return 0;
		}
	}

	/* La méthode afficheMonde était utilisée pour nos premiers tests : elle imprimait l'état de chaque pays (infectés, morts...)
	 * chaque jour, ainsi que celui du monde. Elle n'est pas utilisée dans la version finale.
	 * Pourquoi utilise-t-on des long ?
	 *La réponse : on fait appel à la somme des populations de tous les pays.
	 *En 2020, la population mondiale dépasse les 7.5 milliards d'habitants. Or, la valeur max d'un int est d'environ 2.1 milliards.
	 *Il faut donc passer en long ! */
	
	public void afficheMonde() {
		long totSain = 0;
		long totPop = 0;
		long totInf = 0;
		long totRet = 0;
		long totmorts =0;
		
		for(Pays V : listePays) {
			System.out.println(V.getNomPays() + " Sains  : " + V.getSains() + " Infectes : " +  V.getInfectes() + " Rétablis : " + V.getRetablis() + " morts :" + V.getMorts()+ " Population Total : " + V.getPop());
			totSain +=  V.getSains() ;
			totPop +=  V.getPop();
			totInf += V.getInfectes();
			totRet += V.getRetablis() ;
			totmorts += V.getMorts();
		}
		System.out.println(" Sains  : " +totSain + " Infectes : " +  totInf + " Rétablis : " + totRet + " morts :" + totmorts+ " Population Total : " + totPop);
	}

	public ArrayList<Pays> getPays(){
		return listePays;
	}
		
	//Cette méthode permet de retrouver l'adresse d'un pays en l'identifiant par son nom.
	public Pays getPaysParNom(String n){
		Pays r=new Pays();
		for(Pays p:listePays){
			if (p.getNomPays().equals(n)){
			r=p;
			}
		}
		if(r.getNomPays()==null){
			System.out.println(n+" n a pas pu etre place");
		}
		return r;
	}
	
	/*Renvoie un tableau avec dans l'ordre le nombre de sains, d'infectés, de rétablis et de morts.
	 *On l'utilise pour afficher les statistiques du monde dans simulation.*/
	 
	public long[] getStatsMonde() {
			long totSain = 0;
			long totInf = 0;
			long totRet = 0;
			long totmorts =0;
		for(Pays V : listePays){
			totSain +=  V.getSains() ;
			totInf += V.getInfectes();
			totRet += V.getRetablis() ;
			totmorts += V.getMorts();
		}
		long[] tab = {totSain,totInf,totRet,totmorts};
		return tab;

	}

}
