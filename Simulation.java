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
	private JPanel pCommande, pBoutons, pCreationVirus;
	private JButton bPause, bAcc, bRal;
	private JSlider sVirulence,sDuree,sLethalite;
	private TextField afficheurVit, afficheurDate;
	private Label valVirulence, valDuree, valLethalite;
	
	
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
		pBoutons = new JPanel();
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
		
		pBoutons.add(bRal);
		pBoutons.add(bPause);
		pBoutons.add(bAcc);
		pCommande.add(pBoutons, BorderLayout.CENTER);
		pCommande.add(afficheurVit, BorderLayout.WEST);
		pCommande.add(afficheurDate, BorderLayout.EAST);
		
		//Panel de création du virus :
		pCreationVirus = new JPanel(new GridLayout(6,1));
		valVirulence = new Label("Virulence : ");
		valDuree = new Label();
		valLethalite = new Label();
		sVirulence = new JSlider(0,100,50);
		sVirulence.setPaintTicks(true);
		sVirulence.setMinorTickSpacing(10);
		sVirulence.setMajorTickSpacing(20);
		sVirulence.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				double viru =((JSlider)event.getSource()).getValue()/100.0;
				valVirulence.setText("Virulence : "+viru);
			}
		});
		
		sDuree = new JSlider(1,30,10);
		sLethalite = new JSlider(0,1000,5);
		pCreationVirus.add(sVirulence);
		pCreationVirus.add(valVirulence);
		pCreationVirus.add(sDuree);
		pCreationVirus.add(sLethalite);
		
		//Ajout des panels
		add(pCommande, BorderLayout.NORTH);
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

