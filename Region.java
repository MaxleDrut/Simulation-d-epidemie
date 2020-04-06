import java.util.*;
import java.util.ArrayList;

public class Region {
	
	private ArrayList<Ville> listeVilles = new ArrayList<Ville>();
	private int TTSain, TTPop,TTInf,TTRet,TTmorts;

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
		
		/*// Todo rapprocher le graph ville et la gestion des polygones. 
		// Amerique 
		listeVilles.add(new Ville("Brazil",      2200000));
		listeVilles.add(new Ville("Argentina",  860000));
		listeVilles.add(new Ville("Canada",  860000));
		listeVilles.add(new Ville("Amerique central",   470000));
		listeVilles.add(new Ville("Mexico",       340000));
		listeVilles.add(new Ville("USA",     300000));
		listeVilles.add(new Ville("Equateur", 270000));
		listeVilles.add(new Ville("Canada",      230000));

		//Afrique
		listeVilles.add(new Ville("North Africa",       510000));
		listeVilles.add(new Ville("Sahel",       510000));
		listeVilles.add(new Ville("Ethiopia",       510000));
		listeVilles.add(new Ville("Congo",       510000));
		listeVilles.add(new Ville("South Africa",       510000));
		listeVilles.add(new Ville("Madagascar",270000));

		//Asie 
		listeVilles.add(new Ville("Arabia",       510000));
		listeVilles.add(new Ville("India",       510000));
		listeVilles.add(new Ville("Kazakstan",       510000));
		listeVilles.add(new Ville("China",       510000));
		listeVilles.add(new Ville("S.E Asia",       510000));
		
		//oceanie
		listeVilles.add(new Ville("Indonesia",       510000));
		listeVilles.add(new Ville("New Zealand",       510000));
		listeVilles.add(new Ville("Australia",       510000));
		
		// Europe
		listeVilles.add(new Ville("Independant Nation of Reunion Island",  510000));
		listeVilles.add(new Ville("Russia",       510000));
		listeVilles.add(new Ville("Northen Lands",       510000));
		listeVilles.add(new Ville("Center Europe",       510000));
		listeVilles.add(new Ville("France",       510000));
		listeVilles.add(new Ville("Spain",       510000));
		listeVilles.add(new Ville("Italy",       510000));
		listeVilles.add(new Ville("UK",       510000));*/
		
		listeVilles.add(new Ville("Paris",      2200000));
		listeVilles.add(new Ville("Marseille",  860000));
		listeVilles.add(new Ville("Lyon",       510000));
		listeVilles.add(new Ville("Toulouse",   470000));
		listeVilles.add(new Ville("Nice",       340000));
		listeVilles.add(new Ville("Nantes",     300000));
		listeVilles.add(new Ville("Montpellier",270000));
		listeVilles.add(new Ville("Strasbourg", 270000));
		listeVilles.add(new Ville("Lille",      230000));
		listeVilles.add(new Ville("Italy",       510000));

		listeVilles.get(1).infectionInitale(5000);

		
	}
	
	//TODO : lorsqu'elle est exécutée, cette méthode réalise fait se propager le virus en interne dans chaque ville 
	public void majPropaVille(Virus virus) {
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
			
			long S = (V.getSains()    + EntreePopu[i][0] - SortiePopu[i][0]);
			long I = (V.getInfectes() + EntreePopu[i][1] - SortiePopu[i][1]);
			long R = (V.getRetablis() + EntreePopu[i][2] - SortiePopu[i][2]);
			
			if ( S > 0){
				V.setSains(S);
				}
			else{
				V.setSains(0);
				}
			
			if ( I > 0){
				V.setInfectes(I);
				}
			else{
				V.setInfectes(0);
				}

			if ( R > 0){
				V.setRetablis(R);
				}
			else{
				V.setRetablis(0);
				}

			
			V.setPopulation(V.getSains() + V.getInfectes() + V.getRetablis());

			i++; 
		}

	}
	
	public void ToString(){
		
			TTSain = 0;
			TTPop = 0;
			TTInf = 0;
			TTRet = 0;
			TTmorts =0;
		int i = 0;
		for(Ville V : listeVilles){
			
			System.out.println(V.getNomVille() + " Sains  : " + V.getSains() + " Infectes : " +  V.getInfectes() + " Rétablis : " + V.getRetablis() + " morts :" + V.getMorts()+ " Population Total : " + V.getPop());
			
			TTSain +=  V.getSains() ;
			TTPop +=  V.getPop();
			TTInf += V.getInfectes();
			TTRet += V.getRetablis() ;
			TTmorts += V.getMorts();
	
			i++; 
		}		
		System.out.println(" Sains  : " +TTSain + " Infectes : " +  TTInf + " Rétablis : " + TTRet + " morts :" + TTmorts+ " Population Total : " + TTPop);

	}
	
	public ArrayList<Ville> getVilles(){
		return listeVilles;
		}
		

}

