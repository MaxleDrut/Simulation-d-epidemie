import java.util.LinkedList;

public class ObjetStatistique{
	
	private LinkedList<Integer> serieStatistique;
	private LinkedList<Integer> axeJours;
	private String nomAxe;
	
	public ObjetStatistique(LinkedList<Integer> sS,LinkedList<Integer> aJ, String nA){
		serieStatistique=sS;
		axeJours=aJ;
		nomAxe=nA;
	}	
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
