import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Simulation extends JFrame implements ActionListener {
	
	private Virus maladie;
	private Monde zone;
	protected int jourSimu;
	
	private boolean timerOn;
	private int[] delais = {20,50,100,200,500,1000,2000,5000};
	private int posActuelle;
	private int delaiRef;
	private Timer temps;
	
	private JPanel pCommande, pBoutonsCommande;
	private JPanel pCreationVirus, pSliders, pPresets, pLancerSimu;
	private JPanel pStatistiques;
	private JPanel pCarte;
	
	private JButton bPause, bAcc, bRal;
	private JButton bCorona, bGrippeEspa, bPesteNoire;
	private JButton bCreerVirus;
	
	private JSlider sVirulence,sDuree,sLethalite;
	
	private TextField afficheurVit, afficheurDate;
	private TextField nomDuVirus;
	
	private Label valVirulence, valDuree, valLethalite;
	private Label titSliders, titPresets;
	private Label rensNom;
	private Label todoCarte;
	private Label statistiques;
	
	
	public Simulation(int x, int y) {
		super("Simulation d'epidemie");
		
		posActuelle = delais.length-3;
		temps = new Timer(1, this); //1 correspond ici au délai par défaut pour les changement de vitesse, et non le délai initial
		delaiRef = delais[posActuelle];
		temps.setDelay(delaiRef);	
		timerOn = false;
		jourSimu = 0;
		zone = new Monde();
		
		//Panel de commande
		pCommande = new JPanel(new BorderLayout());
		pBoutonsCommande = new JPanel();
		afficheurVit = new TextField();
		afficheurVit.setEditable(false);
		afficherVitesse();
		afficheurDate = new TextField();
		afficheurDate.setEditable(false);
		afficherDate();
		
		bPause = new JButton("Start");
		bAcc = new JButton("Accelerer");
		bRal = new JButton("Ralentir");
		bPause.addActionListener(new EcouteurVitesse(this, "pause"));
		bAcc.addActionListener(new EcouteurVitesse(this,"acc"));
		bRal.addActionListener(new EcouteurVitesse(this,"ral"));
		
		pBoutonsCommande.add(bRal);
		pBoutonsCommande.add(bPause);
		pBoutonsCommande.add(bAcc);
		pCommande.add(pBoutonsCommande, BorderLayout.CENTER);
		pCommande.add(afficheurVit, BorderLayout.WEST);
		pCommande.add(afficheurDate, BorderLayout.EAST);
		
		//Panel de la carte de la simu (TODO)
		pCarte = new JPanel();
		todoCarte = new Label("ici la carte");
		pCarte.add(todoCarte);
		
		//Panel des statistiques en temps réel
		pStatistiques = new JPanel();
		statistiques = new Label(zone.getStats());
		pStatistiques.add(statistiques);
		
		//Panel de création du virus :
		pCreationVirus = new JPanel(new GridLayout(1,2));
		
		
		//Sous panel des sliders :
		pSliders = new JPanel(new GridLayout(9,1));
		titSliders = new Label("Definition des parametres du virus");

		sVirulence = new JSlider(0,100,50);
		valVirulence = new Label("Indice de virulence : "+sVirulence.getValue()/100.0);
		sVirulence.setPaintTicks(true);
		sVirulence.setMinorTickSpacing(10);
		sVirulence.setMajorTickSpacing(20);
		sVirulence.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				double viru =((JSlider)event.getSource()).getValue()/100.0;
				valVirulence.setText("Incidce de virulence : "+viru);
			}
		});
		
		sDuree = new JSlider(1,30,10);
		valDuree = new Label("Duree de la maladie : "+sDuree.getValue()+" jour(s)");
		sDuree.setPaintTicks(true);
		sDuree.setMinorTickSpacing(5);
		sDuree.setMajorTickSpacing(10);
		sDuree.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				valDuree.setText("Duree de la maladie : "+((JSlider)event.getSource()).getValue()+" jour(s)");
			}
		});
		
		sLethalite = new JSlider(0,1000,5);
		valLethalite = new Label("Taux de lethalite : "+sLethalite.getValue()/10.0+"%");
		sLethalite.setPaintTicks(true);
		sLethalite.setMinorTickSpacing(50);
		sLethalite.setMajorTickSpacing(100);
		sLethalite.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				valLethalite.setText("Taux de lethalite : "+((JSlider)event.getSource()).getValue()/10.0+"%");
			}
		});
		
		rensNom = new Label("Nom du virus :");
		nomDuVirus = new TextField("");
		
		pSliders.add(titSliders);
		pSliders.add(valVirulence);		
		pSliders.add(sVirulence);
		pSliders.add(valDuree);
		pSliders.add(sDuree);
		pSliders.add(valLethalite);
		pSliders.add(sLethalite);
		pSliders.add(rensNom);
		pSliders.add(nomDuVirus);
		pSliders.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//Sous-panel des presets :
		
		pPresets = new JPanel();
		pPresets.setLayout(new BoxLayout(pPresets, BoxLayout.Y_AXIS));
		
		titPresets = new Label("Choix d'un virus deja existant");
		bCorona = new JButton("Covid-19");
		bGrippeEspa = new JButton("Grippe espagnole");
		bPesteNoire = new JButton("Peste bubonique");
		
		bCorona.addActionListener(new EcouteurPresets(this,"Coronavirus"));
		bGrippeEspa.addActionListener(new EcouteurPresets(this,"Grippe espagnole"));
		bPesteNoire.addActionListener(new EcouteurPresets(this,"Peste bubonique"));
		
		pPresets.add(titPresets);
		pPresets.add(Box.createVerticalGlue());
		pPresets.add(bCorona);
		pPresets.add(Box.createVerticalGlue());
		pPresets.add(bGrippeEspa);
		pPresets.add(Box.createVerticalGlue());
		pPresets.add(bPesteNoire);
		pPresets.add(Box.createVerticalGlue());		
		
		bCorona.setAlignmentX(Component.CENTER_ALIGNMENT);
		bGrippeEspa.setAlignmentX(Component.CENTER_ALIGNMENT);
		bPesteNoire.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		pPresets.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//Ajout des sous-panels au panel de création : 
		pCreationVirus.add(pSliders);
		pCreationVirus.add(pPresets);
		
		//Panel de lancement de la simu
		pLancerSimu = new JPanel();
		bCreerVirus = new JButton("Creer le virus !");
		bCreerVirus.addActionListener(new EcouteurCreation(this));
		pLancerSimu.setBorder(BorderFactory.createLineBorder(Color.black));
		
		pLancerSimu.add(bCreerVirus);
		
		//Ajout des panels initiaux
		this.add(pCreationVirus, BorderLayout.CENTER);
		this.add(pLancerSimu, BorderLayout.SOUTH);
		
		this.setSize(x,y);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent ae) { //Chaque incrémentation fait passer au jour suivant.
		jourSimu++;
		afficherDate();
		zone.majPropaPays(maladie);
		zone.deplacements();
		zone.AfficheMonde();
		afficherStatistiques();
		
	}
	
	/* Crée le virus avec les paramètres renseignés et c'est parti !
	 * Cela va lancer la simu en remplaçant les JPanels */
	public void creerVirus() {
		maladie = new Virus(this.getVirulence(),this.getDuree(),this.getLethaliteJournaliere(),this.getNomVirus());
		System.out.println(maladie);
		this.remove(pCreationVirus);
		this.remove(pLancerSimu);
		this.add(pCommande, BorderLayout.NORTH);
		this.add(pCarte, BorderLayout.CENTER);
		this.add(pStatistiques, BorderLayout.SOUTH);
		this.setSize(1000,600);
		this.validate();
		this.repaint();
	}
	
	public void infectionPays(Pays aTrouver, int aInfecter) {
		ArrayList<Pays> listePays = zone.getPays();
		String nomATrouver = aTrouver.getNomPays();
		int i=0;
		while(i<listePays.size() && nomATrouver!=listePays.get(i).getNomPays()) {
			i++;
		}
		
		
	} 
	
	public void pauseTimer() {
		if(timerOn) {
			temps.stop();
			bPause.setText("Start");
		} else {
			temps.start();
			bPause.setText("Stop");
		}
		timerOn = !timerOn;
		this.validate();
		this.repaint();
	}
	
	public void changerVit(int commande) { //Accélérer envoie -1, ralentir envoie +1
		if(!(commande==1 && posActuelle==delais.length-1) && !(commande==-1 && posActuelle==0)) { //Pour éviter les outOfBounds
			posActuelle+=commande;
			temps.setDelay(delais[posActuelle]);
			if(timerOn) {
				temps.start();
			}
			afficherVitesse();
		}
	}
	
	/* Rapport des vitesse = 1/rapport des delais = delai de reference/delai actuel
	 * Donc vitesse x2 veut dire délai 2 fois plus faible que celui de référence
	 */
	
	public void afficherVitesse() {
		double rapportVitesse = ((double) (delaiRef))/delais[posActuelle];
		afficheurVit.setText("Vitesse actuelle : x"+rapportVitesse);
		this.validate();
		this.repaint();
	}
	
	public void afficherDate() {
		afficheurDate.setText("Jour "+jourSimu);
		this.validate();
		this.repaint();
		
	}
	
	public void afficherStatistiques() {
		statistiques.setText(zone.getStats());
		this.validate();
		this.repaint();
	}
	
	public String getNomVirus() {
		return nomDuVirus.getText();
	}
	
	public void setNomVirus(String s) {
		nomDuVirus.setText(s);
		this.validate();
		this.repaint();
	}
	
	public double getVirulence() {
		return (double)(sVirulence.getValue())/100.0;
	}
	
	public int getDuree() {
		return sDuree.getValue();
	}
	
	/*Petit ajout ici : on demande de renseigner la léthalité "totale" du virus (càd la chance de mourir sur toute la durée de la maladie)
	 *Or le modèle SIR utilise la léthalité journalière (chance chaque jour de mourir du virus)
	 *On réalise donc une conversion µ = 1 - (1-lTot)^(1/duree)
	*/ 
	public double getLethaliteJournaliere() {
		double lTot = sLethalite.getValue()/1000.0;
		return (1.0-Math.pow(1-lTot,1.0/sDuree.getValue()));
	}
	
	public void setVirulence(double v) {
		sVirulence.setValue((int) (v*100));
	}
	
	public void setDuree(int d) {
		sDuree.setValue(d);
	}
	
	public void setLethalite(double l) {
		sLethalite.setValue((int) (l*1000));
	}
	
	public static void main (String[] args) {
		
		Simulation simu = new Simulation(450,350);
		
	}
	
}

