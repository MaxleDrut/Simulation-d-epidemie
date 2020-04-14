import java.util.*;
import java.util.ArrayList;
import java.io.*;
import java.net.*;

public class Monde {

	private ArrayList<Pays> listePays = new ArrayList<Pays>();
	private int TTSain, TTPop,TTInf,TTRet,TTmorts;

	// convention d'écriture : liaisonPayss[X][Y] => De la Pays X vers la Pays Y. donc non commutatif. Sortie négatives. Entrée positive
	// Liaison des Payss est en pourcentage de population qui se déplace dans une autre Pays.

	private double[][] liaisonPays = new double[35][35];

	/* = {{0     ,0.02   ,0.01  ,0.015  ,0.005 ,0.012 ,0.009 ,0.008 ,0.013 ,0.01   },
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
	*/
	//TODO : crée la région avec ses Payss et les débits de population entre chaque Pays
	public Monde() {

       listePays.add(new Pays("Brazil",200000));
       listePays.add(new Pays("Argentina",200000));
       listePays.add(new Pays("Canada",200000));
       listePays.add(new Pays("CAmerica",200000));
       listePays.add(new Pays("Mexico",200000));
       listePays.add(new Pays("USA",200000));
       listePays.add(new Pays("Equateur",200000));
       listePays.add(new Pays("Greenland",200000));
       listePays.add(new Pays("North Africa",200000));
       listePays.add(new Pays("Sahel",200000));
       listePays.add(new Pays("Ethiopia",200000));
       listePays.add(new Pays("Congo",200000));
       listePays.add(new Pays("South Africa",200000));
       listePays.add(new Pays("Madagascar",200000));
       listePays.add(new Pays("Arabia",200000));
       listePays.add(new Pays("India",200000));
       listePays.add(new Pays("Kazakstan",200000));
       listePays.add(new Pays("China",200000));
       listePays.add(new Pays("S.E Asia",200000));
       listePays.add(new Pays("Turkey",200000));
       listePays.add(new Pays("Central Asia",200000));
       listePays.add(new Pays("Japon",200000));
       listePays.add(new Pays("Indonesia",200000));
       listePays.add(new Pays("New Zealand",200000));
       listePays.add(new Pays("Australia",200000));
       listePays.add(new Pays("Independant Nation of Reunion Island",200000));
       listePays.add(new Pays("Russia",200000));
       listePays.add(new Pays("Northen Lands",200000));
       listePays.add(new Pays("Balkans",200000));
       listePays.add(new Pays("Islande",200000));
       listePays.add(new Pays("Center Europe",200000));
       listePays.add(new Pays("France",200000));
       listePays.add(new Pays("Spain",200000));
       listePays.add(new Pays("Italy",200000));
       listePays.add(new Pays("UK",200000));

		listePays.get(1).infectionInitale(5000);


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
						liaisonPays[i][j] = Double.parseDouble(splitArray[j]);
					}
				}
				i++;
			}
			br.close(); // OBLIGATOIRE
		    } catch (IOException e) { e.printStackTrace(); }

	}

	//TODO : lorsqu'elle est exécutée, cette méthode réalise fait se propager le virus en interne dans chaque Pays
	public void majPropaPays(Virus virus) {
		//-> appel de propagation pour chaque Pays.
		for(Pays V : listePays){
				V.propagation(virus);
			}
	}

	//TODO : lorsqu'elle est exécutée, cette population fait transiter les populations entre les Pays
	//Modifier la population -> Popu actuel - population + popu entrante

	public void deplacements() {

		// 0 : sains 1 : infectés 2 : rétablis
		long[][] SortiePopu = new long[listePays.size()][3];
		long[][] EntreePopu = new long[listePays.size()][3];

		for(int i = 0; i < SortiePopu.length ; i++){ // initialisation des tableaux a zero

			for(int j = 0; j < SortiePopu[i].length ; j++){ // initialisation des tableaux a zero
				SortiePopu[i][j] = 0;
				EntreePopu[i][j] = 0;
			}
		}

		//-> Comptabiliser tout les entrants et sortants de chaques Pays

		for(int i = 0; i < liaisonPays.length ; i++){

			long popuPays 	  = listePays.get(i).getPop();
			long popuSains 	  = listePays.get(i).getSains();
			long popuInfectes = listePays.get(i).getInfectes();
			long popuRetablis = listePays.get(i).getRetablis();

			//proportion pour aléatoire.
			double propSains    = popuSains/(double)popuPays;
			double propInfectes = popuInfectes/(double)popuPays;
			double propRetablis = popuRetablis/(double)popuPays;

			for(int j = 0; j < liaisonPays[0].length; j++){

				long MvtPopu = (long) ( popuPays * liaisonPays[i][j]);

				for(int k = 0; k < MvtPopu; k++){

				//gestion d'une répartion aléatoire de l'état de santé de la population sortante/entrante en correspondance avec les proportions d dans la Pays.
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
		for(Pays V : listePays){

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

	public void AfficheMonde(){

			TTSain = 0;
			TTPop = 0;
			TTInf = 0;
			TTRet = 0;
			TTmorts =0;
		for(Pays V : listePays){

			System.out.println(V.getNomPays() + " Sains  : " + V.getSains() + " Infectes : " +  V.getInfectes() + " Rétablis : " + V.getRetablis() + " morts :" + V.getMorts()+ " Population Total : " + V.getPop());

			TTSain +=  V.getSains() ;
			TTPop +=  V.getPop();
			TTInf += V.getInfectes();
			TTRet += V.getRetablis() ;
			TTmorts += V.getMorts();

		}
		System.out.println(" Sains  : " +TTSain + " Infectes : " +  TTInf + " Rétablis : " + TTRet + " morts :" + TTmorts+ " Population Total : " + TTPop);

	}

	public ArrayList<Pays> getPays(){
		return listePays;
		}
		//METHODE POUR VOIR LE PAYS CORRESPONDANT AU NOM
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




	public int[] getStats() {
			int TTSain = 0;
			int TTPop = 0;
			int TTInf = 0;
			int TTRet = 0;
			int TTmorts =0;
		for(Pays V : listePays){

			TTSain +=  V.getSains() ;
			TTPop +=  V.getPop();
			TTInf += V.getInfectes();
			TTRet += V.getRetablis() ;
			TTmorts += V.getMorts();
		}
		int[] tab = {TTSain,TTInf,TTRet,TTmorts};
		return tab;

	}

}
