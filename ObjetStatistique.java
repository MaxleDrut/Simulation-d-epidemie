import java.util.LinkedList;
// Sert à faciliter l'affichage de statistiques dans la fenêtre de stats
public class ObjetStatistique{
	
	private LinkedList<Integer> serieStatistique;
	private LinkedList<Integer> axeJours;
	private String nomAxe;
	//Prend en constructeur la liste des jours, la donnée statistique, le nom de cette donnée.
	public ObjetStatistique(LinkedList<Integer> sS,LinkedList<Integer> aJ, String nA){
		serieStatistique=sS;
		axeJours=aJ;
		nomAxe=nA;
	}
	//Prend aussi en compte le nombre de jours qu'on souhaite afficher	
	public ObjetStatistique(LinkedList<Integer> sS,LinkedList<Integer> aJ, String nA,int t){
		nomAxe=nA;
		if(t>=aJ.size()){
			serieStatistique=sS;
			axeJours=aJ;			
		}
		else{
			LinkedList<Integer>axeJoursTronque=new LinkedList<Integer>();
			LinkedList<Integer>serieStatistiqueTronquee=new LinkedList<Integer>();
			for (int i=aJ.size()-t;i<aJ.size();i++){
				axeJoursTronque.add(aJ.get(i));
				serieStatistiqueTronquee.add(sS.get(i));
			}
			serieStatistique=serieStatistiqueTronquee;
			axeJours=axeJoursTronque;
		}
	}
	
	public LinkedList<Integer> getSerieStatistique(){ return serieStatistique;}
	public LinkedList<Integer> getJours(){ return axeJours;}
	public String getNomAxe(){ return nomAxe;}

}
