import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Simulation extends JFrame implements ActionListener {
	
	private Virus maladie;
	private Region zone;
	protected int tempsSimu;
	
	private boolean timerOn;
	private int[] delais = {50,100,200,500,1000,2000,5000};
	private int delaiActuel;
	private int delaiRef;
	private Timer temps;
	
	private JPanel pHaut,pBas;
	private JButton bPause, bAcc, bRal;
	private TextField afficheurVit;
	
	public Simulation(int x, int y) {
		super("Simulation d'epidemie");
		
		delaiActuel = delais.length-3;
		temps = new Timer(1, this); //1 correspond ici au délai par défaut pour les changement de vitesse, et non le délai initial
		delaiRef = delais[delaiActuel];
		temps.setDelay(delaiRef);
		timerOn = false;
		
		//Panel du haut
		pHaut = new JPanel();
		afficheurVit = new TextField(50);
		afficheurVit.setEditable(false);
		afficherVitesse();
		pHaut.add(afficheurVit);
		
		
		//Panel du bas
		pBas = new JPanel();
		bPause = new JButton("Start");
		bAcc = new JButton("Accelerer");
		bRal = new JButton("Ralentir");
		
		bPause.addActionListener(new EcouteurVitesse(this, "pause"));
		bAcc.addActionListener(new EcouteurVitesse(this,"acc"));
		bRal.addActionListener(new EcouteurVitesse(this,"ral"));
		
		pBas.add(bRal);
		pBas.add(bPause);
		pBas.add(bAcc);
		
		//Ajout des panels
		add(pHaut, BorderLayout.NORTH);
		add(pBas, BorderLayout.SOUTH);
		
		this.setSize(x,y);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	public void actionPerformed(ActionEvent ae) { //Chaque incrémentation fait passer au jour suivant.
		tempsSimu++;
		System.out.println(tempsSimu);
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
		if(!(commande==1 && delaiActuel==delais.length-1) && !(commande==-1 && delaiActuel==0)) { //Pour éviter les outOfBounds
			delaiActuel+=commande;
			temps.setDelay(delais[delaiActuel]);
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
		double rapportVitesse = ((double) (delaiRef))/delais[delaiActuel];
		afficheurVit.setText("Vitesse actuelle : x"+rapportVitesse);
		this.validate();
		this.repaint();
	}
	
	public static void main (String[] args) {
		
		Simulation simu = new Simulation(500,500);
		
	}
	
}

