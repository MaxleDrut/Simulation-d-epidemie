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
		// convention d'écriture : liaisonVilles[X][Y] => De la ville X vers la ville Y. donc non commutatif. Sortie négatives. Entrée positive
 
	}
	
	//TODO : lorsqu'elle est exécutée, cette méthode réalise fait se propager le virus en interne dans chaque ville 
	public void infection(Virus virus) {
		//-> appel de propagation pour chaque villes. 
		for(Ville V : listeVilles){
				V.propagation(virus);
			}
	}
	
	//TODO : lorsqu'elle est exécutée, cette population fait transiter les populations entre les villes
	public void deplacements() {
			//-> ce que je veux : Modifier la population -> Popu actuel - population + popu entrante  
			
		for(int i = 0; i < liaisonVilles.length ; i++){
			for(int j = 0; j < liaisonVilles[0].length; j++){
					
					
				//setPopulation(NouvellePopu);
			}
		}		
	}
	
	
	//TODO : quand on fait appel dans la simu à cette méthode, on lui renseigne un nom de ville et le nombre d'infecté et ça l'infecte.
	public void infecterVille(String nom, int i) {
		
		
	}
	
}

