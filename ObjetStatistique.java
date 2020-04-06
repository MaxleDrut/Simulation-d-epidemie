import java.util.LinkedList;

public class ObjetStatistique{
	
	private LinkedList<Long>serieStatistique;
	private LinkedList<Integer>axeJours;
	private String nomAxe;
	
	public ObjetStatistique(LinkedList<Long> sS,LinkedList<Integer> aJ, String nA){
		serieStatistique=sS;
		axeJours=aJ;
		nomAxe=nA;
	}	
	public ObjetStatistique(LinkedList<Long> sS,LinkedList<Integer> aJ, String nA,int T){
		nomAxe=nA;
		if(T>=aJ.size()){
			serieStatistique=sS;
			axeJours=aJ;			
		}
		else{
			LinkedList<Integer>axeJoursTronque=new LinkedList<Integer>();
			LinkedList<Long>serieStatistiqueTronquee=new LinkedList<Long>();
			for (int i=aJ.size()-T;i<aJ.size();i++){
				axeJoursTronque.add(aJ.get(i));
				serieStatistiqueTronquee.add(sS.get(i));
			}
			serieStatistique=serieStatistiqueTronquee;
			axeJours=axeJoursTronque;
		}
	}
	public LinkedList<Long> getSerieStatistique(){return serieStatistique;}
	public LinkedList<Integer> getJours(){return axeJours;}
	public String getNomAxe(){return nomAxe;}

}
