import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class Carte extends JFrame implements MouseListener{

    //Declaration des attributs de la fenêtre
	private JButton boutonLancement = new JButton(" Jouer ");
	private JButton boutonParametre = new JButton(" Paramètres ");
	
	
	private JPanel panoTitre = new JPanel( new FlowLayout());
    private JTextArea titre;
    
    private JLabel img1 = new JLabel("\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n");
    private JPanel imgS = new JPanel( new FlowLayout());
    private JLabel img2 = new JLabel("");
    private JPanel imgN = new JPanel( new FlowLayout());
    private JLabel img3 = new JLabel("");
    private JPanel imgE = new JPanel( new FlowLayout());
    private JLabel img4 = new JLabel("");
    private JPanel imgW = new JPanel( new FlowLayout());
	private int compteur = 0;

	private JTextArea xZone = new JTextArea("ici les coordonées en x");
	private JTextArea yZone = new JTextArea("ici les coordonées en y");
	
    /**
     * Constructeur de la classe UneFenetre
     */
    public Carte() {

        //on definit le nom de la fenetre
        super("Corona Virus .INC");

        //Dimensions de la fenetre graphique, fermeture et fond
        //this.setSize(lg,larg);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        //this.setResizable(false);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane( new Afficher(this ,"bleu.jpg"));
		
        //Création des conteneurs
    
    JPanel conteneurPrincipal = new JPanel(new BorderLayout());
		conteneurPrincipal.setOpaque(false);
	JPanel conteneurCentral = new JPanel(new BorderLayout());
		conteneurCentral.setOpaque(false);


	
        //Création des panels
        
        JPanel bandeBouton = new JPanel(new FlowLayout());
        bandeBouton.setOpaque(false);
		titre = new JTextArea("\n"+" Corona Virus The Game "+"\n"+"\n");
		titre.setFont(new Font("Castellar", Font.BOLD,28));
		titre.setForeground(Color.BLACK);

		titre.setEditable(false);
		panoTitre.setOpaque(false);
		titre.setOpaque(false);

		
		

		


		imgS.setOpaque(false);

        GridLayout coordonees =  new GridLayout(20,20);


        //add(xZone);
       // add(yZone);
	
		xZone.setBounds(20,2000,100,10);
		yZone.setBounds(20,20,100,10);
    

        
        //boutonLancement.addActionListener( new EcouteurClic(this, 1));
		boutonLancement.addMouseListener(this);
		titre.addMouseListener(this);
		titre.setHighlighter(null);
        //ajout du conteneur principal dans la fenetre
        //this.add(conteneurCentral);
        
        this.setVisible(true);
    }


public void changeFenetre(){
	
	
}	

  public void mouseClicked(MouseEvent e){
	
	
		
		System.out.println("ccccc");
		if( e.getSource().equals(titre) && compteur == 0){
			titre.setForeground(Color.RED);
			repaint();
			compteur =1;
		} else if ( e.getSource().equals(titre)){
			
			titre.setForeground(Color.BLACK);
			repaint();
			compteur =0;
		}
		
		System.out.println(	e.getLocationOnScreen()+"    "+e.getLocationOnScreen().getX());
		
	
}
			public void mousePressed(MouseEvent e){
				
				boutonLancement.setForeground(Color.RED);
				repaint();
				}
            public void mouseEntered(MouseEvent e){}
            public void mouseExited(MouseEvent e){}
            public void mouseReleased(MouseEvent e){
				boutonLancement.setForeground(Color.BLACK);
				repaint();
				}
				
				public void add( JButton j){
					
					this.add(j);
					
				}
				
	public void setCoordonateX( double x){
		String t = xZone.getText()+(int)x +",";
		xZone.setText(t);
		
	}
		public void setCoordonateY( double y){
		String t = yZone.getText() +(int)y+",";
		yZone.setText(t);
		
	}
	
	public void repeindre(){
		validate();
		repaint();
	}
	 public static void main(String[] args){
        Carte f = new Carte();
    }
		
}



     


