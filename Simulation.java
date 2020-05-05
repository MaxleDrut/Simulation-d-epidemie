import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Simulation extends JFrame implements ActionListener {
	
	private Virus maladie;
	private Monde zone;
	private Pays paysSelectionne;
	protected int jourSimu;
	protected boolean mondeInfect;
	
	private int largeurEcran =(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int hauteurEcran = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	protected int simHeight;
	protected int simWidth;
	private boolean timerOn;
	private int[] delais = {50,100,200,500,1000,2000,5000};
	private int posActuelle;
	private int delaiRef;
	private Timer temps;
	
	private JPanel pCommande, pBoutonsCommande;
	private JPanel pCreationVirus, pSliders, pEnteteNom, pPresets, pLancerSimu;
	private JPanel pStatistiques,pChiffres;
	private JPanel pCarte;
	private JPanel barreStats;
	private JPanel pAffInfect, pValInfect;
	
	private JButton bPause, bAcc, bRal;
	private JButton bNomRandom;
	private JButton bCorona, bRougeole, bVariole, bPolio, bGrippeSaison, bGrippeEspa, bPesteBombay;
	private JButton bCreerVirus;
	private JButton bValInfect;
	
	private JSlider sVirulence,sDuree,sLethalite;
	
	private TextField afficheurVit, afficheurDate;
	private TextField nomDuVirus;
	
	private Label valVirulence, valDuree, valLethalite;
	private Label titSliders, titPresets;
	private Label rensNom;
	private Label todoCarte;
	private Label affSains, affInfectes, affRetablis, affMorts;
	private Label entetePaysStats, nomPaysStats;
	private Label[] separateurs;
	private Label infectIni;
	
	private Font gras;
	
	public Simulation(int x, int y) {
		super("Simulation d'epidemie");
		
		simWidth = 1100*largeurEcran/1366;
		simHeight = 670*hauteurEcran/768;
		posActuelle = delais.length-3;
		temps = new Timer(1, this); //1 correspond ici au délai par défaut pour les changement de vitesse, et non le délai initial
		delaiRef = delais[posActuelle];
		temps.setDelay(delaiRef);	
		timerOn = false;
		jourSimu = 0;
		zone = new Monde();
		paysSelectionne = null; // Par défaut, on ne passe le curseur sur aucun pays.
		mondeInfect = false; // Deviendra true lorsque l'on aura infecté au moins une pers dans le monde.
		
		//Panel d'affichage de l'infection initiale
		pAffInfect = new JPanel();
		infectIni = new Label("Cliquer sur la zone a infecter puis renseigner le nombre de cas initiaux");
		pAffInfect.add(infectIni);
		
		//Panel de validation de l'infection initiale
		pValInfect = new JPanel();
		bValInfect = new JButton("Commencer la simulation !");
		bValInfect.addActionListener(new EcouteurValiderInfect(this));
		
		pValInfect.add(bValInfect);
		
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
		
		//Panel de la carte de la simu
		pCarte = new Carte(this,"bleu.jpg");
		
		//Panel des statistiques en temps réel
		pStatistiques = new JPanel(new GridLayout(2,1));

		barreStats = new BarreStatistiques();
		
			//Sous panel de l'affichage des chiffres de la maladie :
			pChiffres = new JPanel();
			entetePaysStats = new Label("Region visualisee :");		
			nomPaysStats = new Label();
			gras = new Font("", Font.BOLD,12);
			affSains = new Label();
			affInfectes = new Label();
			affRetablis = new Label();
			affMorts = new Label();
			separateurs = new Label[4];
			for(int i=0;i<separateurs.length;i++) {
				separateurs[i] = new Label("|");
			}
			this.afficherStatistiques();
			
			pChiffres.add(entetePaysStats);
			pChiffres.add(nomPaysStats);
			nomPaysStats.setFont(gras);
			pChiffres.add(separateurs[0]);
			pChiffres.add(affSains);
			pChiffres.add(separateurs[1]);
			pChiffres.add(affInfectes);
			pChiffres.add(separateurs[2]);
			pChiffres.add(affRetablis);
			pChiffres.add(separateurs[3]);
			pChiffres.add(affMorts);
		
		pStatistiques.add(barreStats);		
		pStatistiques.add(pChiffres);
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
				valVirulence.setText("Incice de virulence : "+viru);
			}
		});
		
		sDuree = new JSlider(1,60,10);
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
		
			//Sous-sous panel avec le titre et le bouton pour nommer le virus
			JPanel pEnteteNom = new JPanel(new BorderLayout());
			rensNom = new Label("Nom du virus :");
			bNomRandom = new JButton("Aleatoire !");
			bNomRandom.addActionListener(new EcouteurNomRandom(this));
			pEnteteNom.add(rensNom, BorderLayout.WEST);
			pEnteteNom.add(bNomRandom, BorderLayout.EAST);
			
		nomDuVirus = new TextField("");
		
		pSliders.add(titSliders);
		pSliders.add(valVirulence);		
		pSliders.add(sVirulence);
		pSliders.add(valDuree);
		pSliders.add(sDuree);
		pSliders.add(valLethalite);
		pSliders.add(sLethalite);
		pSliders.add(pEnteteNom);
		pSliders.add(nomDuVirus);
		pSliders.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//Sous-panel des presets :
		
		pPresets = new JPanel();
		pPresets.setLayout(new BoxLayout(pPresets, BoxLayout.Y_AXIS));
		
		titPresets = new Label("Choix d'un virus deja existant");
		bCorona = new JButton("Covid-19");
		bRougeole = new JButton("Rougeole");
		bVariole = new JButton("Variole");
		bPolio = new JButton("Polio");
		bGrippeSaison = new JButton("Grippe saisonniere");
		bGrippeEspa = new JButton("Grippe espagnole");
		bPesteBombay = new JButton("Peste de Bombay");
		
		bCorona.addActionListener(new EcouteurPresets(this,"Coronavirus"));
		bRougeole.addActionListener(new EcouteurPresets(this,"Rougeole"));
		bVariole.addActionListener(new EcouteurPresets(this,"Variole"));
		bPolio.addActionListener(new EcouteurPresets(this,"Polio"));
		bGrippeSaison.addActionListener(new EcouteurPresets(this,"Grippe saisonniere"));
		bGrippeEspa.addActionListener(new EcouteurPresets(this,"Grippe espagnole"));
		bPesteBombay.addActionListener(new EcouteurPresets(this,"Peste de Bombay"));
		
		pPresets.add(titPresets);
		pPresets.add(Box.createVerticalGlue());
		pPresets.add(bCorona);
		pPresets.add(bRougeole);
		pPresets.add(bVariole);
		pPresets.add(bPolio);
		pPresets.add(bGrippeSaison);
		pPresets.add(bGrippeEspa);
		pPresets.add(bPesteBombay);
		pPresets.add(Box.createVerticalGlue());		
		
		bCorona.setAlignmentX(Component.CENTER_ALIGNMENT);
		bRougeole.setAlignmentX(Component.CENTER_ALIGNMENT);
		bVariole.setAlignmentX(Component.CENTER_ALIGNMENT);
		bPolio.setAlignmentX(Component.CENTER_ALIGNMENT);
		bGrippeSaison.setAlignmentX(Component.CENTER_ALIGNMENT);
		bGrippeEspa.setAlignmentX(Component.CENTER_ALIGNMENT);
		bPesteBombay.setAlignmentX(Component.CENTER_ALIGNMENT);
		
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
	
	//Chaque incrémentation fait passer au jour suivant, ce qui va faire se propager la maladie dans les pays et le monde.
	public void actionPerformed(ActionEvent ae) { 
		jourSimu++;
		afficherDate();
		zone.majPropaPays(maladie);
		zone.deplacements();
		afficherStatistiques();	
	}
	
	/* Crée le virus avec les paramètres renseignés et c'est parti !
	 * Cela va lancer la simu en remplaçant les JPanels */
	public void creerVirus() {
		maladie = new Virus(this.getVirulence(),this.getDuree(),this.getLethaliteJournaliere(),this.getNomVirus());
		System.out.println(maladie);		
		this.remove(pCreationVirus);
		this.remove(pLancerSimu);
		this.add(pValInfect, BorderLayout.NORTH);
		this.add(pCarte, BorderLayout.CENTER);
		this.add(pAffInfect, BorderLayout.SOUTH);
		this.setSize(simWidth,simHeight);
		this.setTitle("Simulation d'epidemie : "+maladie.getNom());
		this.validate();
		this.repaint();
	}
	
	public void validerInfection() {
		long[] stats = zone.getStatsMonde();
		if(stats[1]!=0) { //Il faut qu'il y ait au moins 1 infecté dans le monde pour lancer la simu !
			mondeInfect = true;
			this.remove(pValInfect);
			this.remove(pAffInfect);
			this.add(pCommande, BorderLayout.NORTH);
			this.add(pStatistiques, BorderLayout.SOUTH);
		} else {
			infectIni.setText("Erreur ! Il faut infecter au minimum une personne !");
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
	
	public void changerVitesse(int commande) { //Accélérer envoie -1, ralentir envoie +1
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
	/* On réactualise les proportions de la barre de statistiques. Puisque l'on fait appel à une méthode spécifique
	 * à la classe BarreStatistiques, il faut un downcast.
	 * De plus, on met aussi à jour les chiffres du label.
	 * Si l'utilisateur a sélectionné un pays dans Carte, on ira afficher ce pays.
	 * Sinon, on affiche le monde par défaut.*/
	public void afficherStatistiques() {
		long[] stats = new long[4];
		if(paysSelectionne == null) {
			stats = zone.getStatsMonde();
			nomPaysStats.setText("Monde");
		} else {
			stats = paysSelectionne.getStatsPays();
			nomPaysStats.setText(paysSelectionne.getNomPays());
		}
		((BarreStatistiques) (barreStats)).setProportions(stats);
		affSains.setText("Sains : "+arrondirValeur(stats[0]));
		affInfectes.setText("Infectes : "+arrondirValeur(stats[1]));
		affRetablis.setText("Retablis : "+arrondirValeur(stats[2]));
		affMorts.setText("Morts : "+arrondirValeur(stats[3]));
		this.validate();
		this.repaint();
	}

	/*Pour un affichage propre, on ne souhaite que les 3 premiers digits si l'on a + de 1 million de malade)
	 * Le but de l'arrondisseur sera donc de retourner un String contenant le chiffre arrondi et l'unité 
	 * (ex : 12.1 millions | 51 245)*
	 * 
	 * Ah et oui ce ne sont pas vraiment des arrondis, plutôt une troncature...*/
	 
	private String arrondirValeur(long val) {
		long valEntiere;
		long valDeci;
		if(val<pDix(3)) { //Pas d'arrondi si moins de 1000
			return Long.toString(val);
			
		} else if(val<pDix(6)) { //Si compris entre 1000 et 1 million, on sépare les digits en 2 (ex : 12 543)
			valEntiere = val/pDix(3);
			valDeci=(val-valEntiere*pDix(3));
			if(valDeci==0) { //Cas particulier où le nombre est multiple de 1000 : il faut quand même afficher les TROIS zéros derrière !
				return(valEntiere+" 000");
			} else if(valDeci<10) { //Autre cas : si les dizaines et centaines sont nulles, il faut rajouter 2 zéros devant
				return(valEntiere+" 00"+valDeci);
			} else if(valDeci<100) { //Et si les centaines sont nulles, il faut rajouter 1 zéro.
				return(valEntiere+" 0"+valDeci);
			} else {
				return(valEntiere+" "+valDeci);
			}
			
		} else if(val<2*pDix(6)) { //On différencie le cas de 1 million (sans s) et 2 millionS.
			valEntiere=1; 
			valDeci = (val-valEntiere*pDix(6))/pDix(4); //On récupère les 2 digits décimaux grâce à une division euclidienne
			if(valDeci<10) {
				return (valEntiere+".0"+valDeci+" million");
			} else {
				return(valEntiere+"."+valDeci+" million");
			}
			
		} else if(val<pDix(7)) {
			valEntiere=val/pDix(6);
			valDeci =(val-valEntiere*pDix(6))/pDix(4);
			if(valDeci<10) {
				return (valEntiere+".0"+valDeci+" millions");
			} else {
				return(valEntiere+"."+valDeci+" millions");
			}
			
		} else if(val<pDix(8)) {
			valEntiere=val/pDix(6);
			valDeci = (val-valEntiere*pDix(6))/pDix(5);
			return(valEntiere+"."+valDeci+" millions");
			
		} else if(val<pDix(9)) {
			valEntiere=val/pDix(6);
			return(valEntiere+" millions");
			
		} else if(val<2*pDix(9)){ 
			valEntiere=1;
			valDeci =(val-valEntiere*pDix(9))/pDix(7);
			if(valDeci<10) {
				return (valEntiere+".0"+valDeci+" milliard");
			} else {
				return(valEntiere+"."+valDeci+" milliard");
			}
			
		} else if(val<pDix(10)) {
			valEntiere=val/pDix(9);
			valDeci =(val-valEntiere*pDix(9))/pDix(7);
			if(valDeci<10) {
				return (valEntiere+".0"+valDeci+" milliards");
			} else {
				return(valEntiere+"."+valDeci+" milliards");
			}
			
		} else {
			return("Erreur"); //On considère que l'on ne dépassera jamais 10 milliards.
		}
	}
	
	//La fonction Math.pow renvoie des double ! Cela empêche donc la division euclidienne. Renvoyons alors des int à la place.
	private long pDix(long p) { 
		if(p>=0) {
			return (long) (Math.pow(10,p));
		} else {
			return 0;
		}
	}
	
	public void afficherInfection() {
		if(paysSelectionne == null) {
			infectIni.setText("Cliquer sur la zone a infecter puis renseigner le nombre de cas initiaux");
		} else {
			infectIni.setText("Le pays "+paysSelectionne.getNomPays()+" a actuellement "+paysSelectionne.getInfectes()+" infecte(s)");
		}
	}
	
	public void selectionPays(Pays p) {
		paysSelectionne = p;
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
	
	public void repeindre() {
		this.validate();
		this.repaint();
	}
	
	public static void main (String[] args) {

		Simulation simu = new Simulation(450,350);
		
	}
	
}

