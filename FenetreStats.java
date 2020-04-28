import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;
import org.knowm.xchart.*;


public class FenetreStats extends JFrame {
	private JButton[] boutonsStats;
	private JPanel conteneurPrincipal;
	private XChartPanel<XYChart> panelGraphique;
	private XYChart graphique=new XYChart(0,0);
	private LinkedList<ObjetStatistique> objetsGraphe=new LinkedList<ObjetStatistique>();
	private Pays vM;
	private int T;
	private int nChoixJours=5;
	
	
	public void ajouterStats(ObjetStatistique oS){
		graphique.addSeries(oS.getNomAxe(),oS.getJours(),oS.getSerieStatistique());
	}
        public FenetreStats(Pays vM,int T) {		
		super("Statistiques de la zone "+vM.getNomPays());
		this.vM=vM;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1080,720);
        graphique.setYAxisTitle("Nombre de personnes");
        graphique.setXAxisTitle("Jours");
        JPanel conteneurPrincipal= new JPanel(new BorderLayout());
        JPanel panneauBoutons= new JPanel();
		this.T=T;
		objetsGraphe.add(new ObjetStatistique(vM.getPopTotaleJour(),vM.getJours(),"Population Totale",T));
		objetsGraphe.add(new ObjetStatistique(vM.getRetablisJour(),vM.getJours(),"Nombre de retablis",T));
		objetsGraphe.add(new ObjetStatistique(vM.getInfectesJour(),vM.getJours(),"Nombre d'infectes",T));
		objetsGraphe.add(new ObjetStatistique(vM.getMortsJour(),vM.getJours(),"Nombre de morts",T));
		objetsGraphe.add(new ObjetStatistique(vM.getSainsJour(),vM.getJours(),"Nombre de sains",T));
        boutonsStats= new JButton[nChoixJours];

        
        ajouterStats(objetsGraphe.get(0));
		ajouterStats(objetsGraphe.get(1));
		ajouterStats(objetsGraphe.get(2));
		ajouterStats(objetsGraphe.get(3));

		for(int i=1;i<nChoixJours;i++){
			boutonsStats[i]=new JButton("Jours:"+5*i);
			boutonsStats[i].addActionListener(new EcouteurStats(this,5*i));
			panneauBoutons.add(boutonsStats[i]);
		}
 
		boutonsStats[nChoixJours-1]=new JButton("Tous les jours");
		boutonsStats[nChoixJours-1].addActionListener(new EcouteurStats(this,10000));
		panneauBoutons.add(boutonsStats[nChoixJours-1]);	



		XChartPanel<XYChart> panelGraphique=new XChartPanel<XYChart>(graphique);
		conteneurPrincipal.add(panneauBoutons,BorderLayout.SOUTH);
        conteneurPrincipal.add(panelGraphique,BorderLayout.CENTER);
        this.add(conteneurPrincipal);
        this.setVisible(true);
    }

    public void setTemps(int temps){
		this.dispose();
		new FenetreStats(vM,temps);
	}


 }
