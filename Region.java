import java.util.*;

public class Region {
	
	private ArrayList<Ville> listeVilles;
	private double[][] liaisonVilles; //double car c'est une proportion des habitans de la ville qui se déplacent. 
	
	
	//TODO : crée la région avec ses villes et les débits de population entre chaque ville
	public Region() {
		
		listeVilles.add(new Ville("Paris", 2200000));
		listeVilles.add(new Ville("Marseill", 860000));
		listeVilles.add(new Ville("Lyon", 510000));
		listeVilles.add(new Ville("Toulouse", 470000));
		listeVilles.add(new Ville("Nice", 340000));
		listeVilles.add(new Ville("Nantes", 300000));
		listeVilles.add(new Ville("Montpellier", 270000));
		listeVilles.add(new Ville("Strasbourg", 270000));
		listeVilles.add(new Ville("Bordeaux", 240000));
		listeVilles.add(new Ville("Lille", 2300000));
		
		// Liaison des villes est en pourcentage de population qui se déplace dans une autre ville.
		// convention d'écriture : liaisonVilles[X][Y] => De la ville X vers la ville Y. donc non commutatif. Sortie négatives. Entrée positive

	/*	liaisonVilles = {{0, , , , , , , , , },
						 { ,0, , , , , , , , },
						 { , ,0, , , , , , , },
						 { , , ,0, , , , , , },
						 { , , , ,0, , , , , },
						 { , , , , ,0, , , , },
						 { , , , , , ,0, , , },
						 { , , , , , , ,0, , },
						 { , , , , , , , ,0, },
						 { , , , , , , , , ,0},
						};
		*/
 
	}
	
	//TODO : lorsqu'elle est exécutée, cette méthode réalise fait se propager le virus en interne dans chaque ville 
	public void infection(Virus virus) {
		//-> appel de propagation pour chaque villes. 
		for(Ville V : listeVilles){
				V.propagation(virus);
			}
	}
	
	//TODO : lorsqu'elle est exécutée, cette population fait transiter les populations entre les villes 
	//TODO : Mouvement infecté.
	public void deplacements() {
		
		//-> ce que je veux : Modifier la population -> Popu actuel - population + popu entrante  
		
		// 0 : sains 1 : infectés 2 : rétablis 
		
		long[][] SortiePopu = new long[listeVilles.size()][3];
		long[][] EntreePopu = new long[listeVilles.size()][3];

		for(int i = 0; i < SortiePopu.length ; i++){ // initialisation des tableaux a zero
			for(int j = 0; j < SortiePopu.length ; j++){ // initialisation des tableaux a zero
				SortiePopu[i][j] = 0;
				EntreePopu[i][j] = 0;		
			}
		}
			
		//-> Comptabiliser tout les entrants et sortants de chaques villes 
		
		for(int i = 0; i < liaisonVilles.length ; i++){ 
			
			long popuSains    = listeVilles.get(i).getSains();  
			long popuInfectes = listeVilles.get(i).getInfectes();  
			long popuRetablis = listeVilles.get(i).getRetablis();  
		
			for(int j = 0; j < liaisonVilles[0].length; j++){
				
				long MvtSains = (long)    ( popuSains    * liaisonVilles[i][j]); 
				long MvtInfectes = (long) ( popuInfectes * liaisonVilles[i][j]); 
				long MvtRetablis = (long) ( popuRetablis * liaisonVilles[i][j]); 
				
				
				SortiePopu[i] = SortiePopu[i] + Mouvement; 
				EntreePopu[j] = EntreePopu[j] + Mouvement;
			}
		}
		
		//-> Set la nouvelle population
		
		int i = 0;
		for(Ville V : listeVilles){
			V.setPopulation(V.getPop() + EntreePopu[i] - SortiePopu[i]);
			i++; 
			}
	}
	
	
	//TODO : quand on fait appel dans la simu à cette méthode, on lui renseigne un nom de ville et le nombre d'infecté et ça l'infecte.
	public void infecterVille(String nom, int i) {
		
		
	}
	
}

