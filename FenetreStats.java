import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;
import org.knowm.xchart.*;

public class FenetreStats extends JFrame {
	
	private JButton[] boutonsStats;
	private JPanel conteneurPrincipal;
	private XChartPanel<XYChart> panelGraphique;
	private XYChart graphique;
	private LinkedList<ObjetStatistique> objetsGraphe=new LinkedList<ObjetStatistique>();
	private Pays vM;
	private int t;
	private int nChoixJours;
	
    public FenetreStats(Pays vM,int t) {		
		super("Statistiques de la zone "+vM.getNomPays());
		this.vM=vM;
		
        graphique=new XYChart(0,0);
        graphique.setYAxisTitle("Nombre de personnes");
        graphique.setXAxisTitle("Jours");
        JPanel conteneurPrincipal= new JPanel(new BorderLayout());
        JPanel panneauBoutons= new JPanel();
		this.t=t;
		//Ajouts des objets statistiques à la liste des stats souhaitées
		objetsGraphe.add(new ObjetStatistique(vM.getPopTotaleJour(),vM.getJours(),"Population Totale",t));
		objetsGraphe.add(new ObjetStatistique(vM.getRetablisJour(),vM.getJours(),"Nombre de retablis",t));
		objetsGraphe.add(new ObjetStatistique(vM.getInfectesJour(),vM.getJours(),"Nombre d'infectes",t));
		objetsGraphe.add(new ObjetStatistique(vM.getMortsJour(),vM.getJours(),"Nombre de morts",t));
		objetsGraphe.add(new ObjetStatistique(vM.getSainsJour(),vM.getJours(),"Nombre de sains",t));
		
        nChoixJours = 5;		
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
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1080,720);
        this.add(conteneurPrincipal);
        this.setVisible(true);
    }
	//Ajout d'un objet statistique sur le graphe
	public void ajouterStats(ObjetStatistique oS) {
		graphique.addSeries(oS.getNomAxe(),oS.getJours(),oS.getSerieStatistique());
	}
	//Définition du temps d'affichage souhaité, pour zoomer ou dézoomer sur le graphe
    public void setTemps(int temps){
		this.dispose();
		new FenetreStats(vM,temps);
	}


 }
