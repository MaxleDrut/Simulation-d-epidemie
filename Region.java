import java.util.*;

public class Region {
	
	private ArrayList<Ville> listeVilles;
	
	// convention d'écriture : liaisonVilles[X][Y] => De la ville X vers la ville Y. donc non commutatif. Sortie négatives. Entrée positive		
	// Liaison des villes est en pourcentage de population qui se déplace dans une autre ville.

	private double[][] liaisonVilles = {{0     ,0.02   ,0.01  ,0.015  ,0.005 ,0.012 ,0.009 ,0.008 ,0.013 ,0.01   },	
										{0.015 ,0      ,0.02  ,0.002  ,0.012 ,0.006 ,0     ,0.005 ,0.008 ,0.019  },
										{0.008 ,0.015  ,0     ,0.006  ,0.02  ,0.012 ,0.015 ,0.005 ,0.01  ,0.002  },
										{0.01  ,0.008  ,0.003 ,0      ,0.01  ,0.006 ,0.015 ,0.002 ,0     ,0.02   },
										{0.003 ,0      ,0     ,0.008  ,0     ,0.006 ,0.01  ,0.012 ,0.015 ,0.003  },
										{0.02  ,0.008  ,0.01  ,0.02   ,0.002 ,0     ,0.006 ,0.008 ,0.01  ,0.005  },
										{0.005 ,0.002  ,0.012 ,0.015  ,0.01  ,0.006 ,0     ,0.005 ,0.008 ,0.004    },
										{0.008 ,0.006  ,0.02  ,0.015  ,0.01  ,0.008 ,0.005 ,0     ,0.003 ,0.01   },
										{0.006 ,0.01   ,0.003 ,0.012  ,0.02  ,0.015 ,0.003 ,0.005 ,0     ,0.006  },
										{0     ,0.006  ,0.01  ,0.008  ,0.012 ,0.02  ,0.005 ,0.002 ,0.02  ,0      },
						};
	
	
	//TODO : crée la région avec ses villes et les débits de population entre chaque ville
	public Region() {
		
		// Todo rapprocher le graph ville et la gestion des polygones. 
		listeVilles.add(new Ville("Brazil",      2200000));
		listeVilles.add(new Ville("Argentina",  860000));
		listeVilles.add(new Ville("Africa",       510000));
		listeVilles.add(new Ville("CAmerica",   470000));
		listeVilles.add(new Ville("Mexico",       340000));
		listeVilles.add(new Ville("USA",     300000));
		listeVilles.add(new Ville("Madagascar",270000));
		listeVilles.add(new Ville("Equateur", 270000));
		listeVilles.add(new Ville("Bordeaux",   240000));
		listeVilles.add(new Ville("Canada",      230000));
	
		listeVilles.get(1).infectionInitale(5000);
		for(Ville V : listeVilles){
			System.out.println(V.getNomVille() + " Sains  : " + V.getSains() + " Infectes : " +  V.getInfectes() + " Rétablis : " + V.getRetablis() + " Population Total : " + V.getPop());
			}
		
	}
	
	//TODO : lorsqu'elle est exécutée, cette méthode réalise fait se propager le virus en interne dans chaque ville 
	public void infection(Virus virus) {
		//-> appel de propagation pour chaque villes. 
		for(Ville V : listeVilles){
				V.propagation(virus);
			}
	}
	
	//TODO : lorsqu'elle est exécutée, cette population fait transiter les populations entre les villes 
	//Modifier la population -> Popu actuel - population + popu entrante  

	public void deplacements() {

		// 0 : sains 1 : infectés 2 : rétablis 
		long[][] SortiePopu = new long[listeVilles.size()][3];
		long[][] EntreePopu = new long[listeVilles.size()][3];

		for(int i = 0; i < SortiePopu.length ; i++){ // initialisation des tableaux a zero
			for(int j = 0; j < SortiePopu[i].length ; j++){ // initialisation des tableaux a zero
				SortiePopu[i][j] = 0;
				EntreePopu[i][j] = 0;		
			}
		}
			
		//-> Comptabiliser tout les entrants et sortants de chaques villes 
		
		for(int i = 0; i < liaisonVilles.length ; i++){ 
			
			long popuVille 	  = listeVilles.get(i).getPop();  
			long popuSains 	  = listeVilles.get(i).getSains();  
			long popuInfectes = listeVilles.get(i).getInfectes();  
			long popuRetablis = listeVilles.get(i).getRetablis();  
			
			//proportion pour aléatoire. 
			double propSains    = popuSains/(double)popuVille;
			double propInfectes = popuInfectes/(double)popuVille;
			double propRetablis = popuRetablis/(double)popuVille;
		
			for(int j = 0; j < liaisonVilles[0].length; j++){
				
				long MvtPopu = (long) ( popuVille * liaisonVilles[i][j]); 
				
				for(int k = 0; k < MvtPopu; k++){ 
					
				//gestion d'une répartion aléatoire de l'état de santé de la population sortante/entrante en correspondance avec les proportions d dans la ville. 
					double tirage = Math.random();
					
					if(tirage <= propSains){ //tirage sains 
						SortiePopu[i][0]++; 
						EntreePopu[j][0]++;
					}
					if(tirage > propSains && tirage <= propSains + propInfectes){ //tirage infectes
						SortiePopu[i][1]++; 
						EntreePopu[j][1]++;
					}
					if(tirage > propSains + propInfectes && tirage <= propSains + propInfectes + propRetablis){ //tirage retablis
						SortiePopu[i][2]++; 
						EntreePopu[j][2]++;
					}
				}
			}
		}
		
		//-> Set la nouvelle population
		int i = 0;
		for(Ville V : listeVilles){
			
			V.setSains(   V.getSains()    + EntreePopu[i][0] - SortiePopu[i][0]);
			V.setInfectes(V.getInfectes() + EntreePopu[i][1] - SortiePopu[i][1]);
			V.setRetablis(V.getRetablis() + EntreePopu[i][2] - SortiePopu[i][2]);
			
			V.setPopulation(V.getSains() + V.getInfectes() + V.getRetablis());
			
			i++; 
		}
	}
	
	
	//TODO : quand on fait appel dans la simu à cette méthode, on lui renseigne un nom de ville et le nombre d'infecté et ça l'infecte.
	// pas utile
	public ArrayList<Ville> getVilles(){
		return listeVilles;
		}
	//Simu ne connait que région et veux x malades a Y 
	//> get liste ville.
}

