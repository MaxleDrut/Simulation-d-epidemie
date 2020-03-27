import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Simulation extends JFrame implements ActionListener {
	
	private Virus maladie;
	private Region zone;
	protected int jourSimu;
	
	private boolean timerOn;
	private int[] delais = {50,100,200,500,1000,2000,5000};
	private int posActuelle;
	private int delaiRef;
	private Timer temps;
	private JPanel pCommande, pBoutonsCommande;
	private JPanel pCreationVirus, pSliders, pPresets;
	private JButton bPause, bAcc, bRal;
	private JButton bCorona, bGrippeEspa, bPesteNoire;
	private JSlider sVirulence,sDuree,sLethalite;
	private TextField afficheurVit, afficheurDate;
	private Label valVirulence, valDuree, valLethalite;
	private Label titSliders, titPresets;
	
	
	public Simulation(int x, int y) {
		super("Simulation d'epidemie");
		
		posActuelle = delais.length-3;
		temps = new Timer(1, this); //1 correspond ici au délai par défaut pour les changement de vitesse, et non le délai initial
		delaiRef = delais[posActuelle];
		temps.setDelay(delaiRef);	
		timerOn = false;
		jourSimu = 0;
		
		//Panel de commande (en haut pour l'instant)
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
		
		//Panel de création du virus :
		pCreationVirus = new JPanel(new GridLayout(1,2));
		
		//Sous panel des sliders :
		pSliders = new JPanel(new GridLayout(7,1));
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
		
		pSliders.add(titSliders);
		pSliders.add(valVirulence);		
		pSliders.add(sVirulence);
		pSliders.add(valDuree);
		pSliders.add(sDuree);
		pSliders.add(valLethalite);
		pSliders.add(sLethalite);

		
		//Sous-panel des presets :
		
		pPresets = new JPanel();
		titPresets = new Label("Choix d'un virus deja existant");
		
		bCorona = new JButton("Covid-19");
		bGrippeEspa = new JButton("Grippe espagnole");
		bPesteNoire = new JButton("Peste bubonique");
		
		pPresets.add(titPresets);
		pPresets.add(Box.createVerticalGlue());
		pPresets.add(bCorona);
		pPresets.add(Box.createVerticalGlue());
		pPresets.add(bGrippeEspa);
		pPresets.add(bPesteNoire);
		
		//Ajout des sous-panels au panel de création : 
		pCreationVirus.add(pSliders);
		pCreationVirus.add(pPresets);
		
		//Ajout des panels
		//add(pCommande, BorderLayout.NORTH);
		add(pCreationVirus, BorderLayout.CENTER);
		
		this.setSize(x,y);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent ae) { //Chaque incrémentation fait passer au jour suivant.
		jourSimu++;
		afficherDate();
		
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
	public static void main (String[] args) {
		
		Simulation simu = new Simulation(500,500);
		
	}
	
}

