
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.geom.Rectangle2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Toolkit;

public class Afficher extends JPanel implements MouseListener{
private BufferedImage im;

private FenetreJeu fen;

private int compteurPeintre = 0;
private int [] xPeintureEnCours = new int [1000];
private int [] yPeintureEnCours = new int [1000];
private Polygon PeintureEnCours;
private Polygon SAmerica;
private Polygon Africa;
private Polygon CAmerica;
private Polygon Mexico;
private Polygon USA;
private Polygon Brazil;
private Polygon Argentina;
private Polygon Madagascar;
private Polygon Equateur;
private Polygon Canada;
private int largeurEcran =(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
private int hauteurEcran = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
private int onAClique =-1;												// Ce compteur permet de savoir à quelle étape on est de l'affichage -1 signifie qu'on est à l'initialisation


public Afficher( FenetreJeu f , String s) {
	super(new FlowLayout() );
//im = Toolkit.getDefaultToolkit().getImage(s);
fen = f;
initComponents();

System.out.println(largeurEcran);
try{
im = ImageIO.read(new File(s));


  } catch (IOException ex) {
                ex.printStackTrace();
            }
addMouseListener(this);
 //tailleFen = this.getSize();
}

///*
public void paintComponent(Graphics g) {
int h = getHeight()/20;
int w = getWidth()/25;
if(onAClique == -1){
			g.drawImage(im, 0, 0, getWidth(), getHeight(), this);
			g.setColor(Color.GREEN);

				g.fillPolygon(Mexico);
                g.fillPolygon(Africa);
                g.fillPolygon(Brazil);
                g.fillPolygon(CAmerica);
                g.fillPolygon(USA);
                g.fillPolygon(Argentina);
				g.fillPolygon(Madagascar);
				g.fillPolygon(Equateur);
				g.fillPolygon(Canada);

   if(compteurPeintre>1){
               // g.fillPolygon(PeintureEnCours);
			}

           g.setColor(Color.YELLOW);
				g.drawPolygon(Equateur);
				g.drawPolygon(Argentina);
				g.drawPolygon(Madagascar);
				g.drawPolygon(Mexico);
                g.drawPolygon(Africa);
                g.drawPolygon(Brazil);
                g.drawPolygon(CAmerica);
                g.drawPolygon(USA);
                g.drawPolygon(Canada);
}else if(onAClique == 0){
	g.setColor(Color.BLUE);
	g.fillPolygon(USA);

}else if ( onAClique == 1 ){
	g.setColor(Color.RED);
	g.fillPolygon(Brazil);
} else if( onAClique == 2){
	g.setColor(Color.PINK);
	g.fillPolygon(Argentina);
} else if ( onAClique == 3){
	g.setColor(Color.ORANGE);
	g.fillPolygon(Mexico);
}

}
   private void initComponents() {                                       // initialisation des différents polygones qui vont composer la carte

		int xBrazil [] = {383,380,380,380,379,368,360,347,337,344,347,354,376,382,387,391,394,401,406,408,410,406,407,411,414,416,419,421,424,427,430,435,436,441,447,453,455,460,467,472,474,475,475,472,467,461,461,461,459,455,452,447,440,432,422,422,420,416,411,404,397};
		diviserTableauX( xBrazil);
		int yBrazil [] = {630,611,593,581,566,553,539,538,530,513,500,490,461,458,458,457,458,462,467,471,476,482,488,490,490,488,487,487,485,487,488,490,494,493,493,493,496,498,502,505,510,514,516,521,529,541,546,553,558,566,577,578,575,585,592,596,603,610,617,628,635};
        diviserTableauY( yBrazil);
        int xArgentina [] = {384,385,387,388,390,392,388,386,378,371,370,364,362,361,362,362,359,358,357,362,365,362,359,357,357,357,352,346,340,339,336,333,333,332,332,332,332,326,325,328,323,326,327,327,332,337,346,351,360,366,371,376,379};
        diviserTableauX( xArgentina);
        int yArgentina [] = {630,634,636,638,643,643,651,652,655,655,666,663,667,672,675,682,687,692,696,700,699,705,712,716,722,727,734,732,729,726,719,713,703,697,689,681,670,667,663,657,652,646,640,559,538,531,538,539,540,550,556,566,567};
        diviserTableauY( yArgentina);
        int xAfrica[] = {705, 678, 681, 667, 658, 594, 555, 556, 604, 670, 764, 816, 848, 798, 804, 775, 762, 714};
        diviserTableauX( xAfrica);
        int yAfrica[] = {631, 558, 509, 488, 466, 464, 425, 394, 332, 325, 354, 441, 435, 503, 550, 571, 608, 632};
		diviserTableauY( yAfrica);
		int xCAmerica [] = {293,285,277,269,269,269,261,249,251,242,237,238,233,233,245,252,254,261,268,275,281,286,291};
		diviserTableauX( xCAmerica);
		int yCAmerica [] = {447,441,444,436,427,418,413,414,403,406,406,412,414,420,425,425,432,440,447,450,452,446,447};
		diviserTableauY( yCAmerica);
		int xMexico [] = {232,228,223,214,205,195,189,183,180,176,179,176,170,161,159,154,152,148,145,144,148,150,157,159,159,152,151,150,149,146,146,144,139,140,139,138,135,129,190,193,199,203,206,212,207,210,211,213,219,224,232,237,240,242,244,246,256,254,254,252,245,242,237,233,232};
		diviserTableauX( xMexico);
		int yMexico [] = {419,417,414,415,412,407,404,402,400,396,390,384,373,362,355,350,339,338,336,341,350,355,371,374,379,374,368,364,361,360,356,352,348,343,340,332,327,323,342,351,353,361,369,374,382,389,394,399,403,405,403,401,397,395,392,390,390,394,398,402,404,405,412,413,418};
        diviserTableauY( yMexico);
        int xUSA [] = {137,251,266,260,264,272,276,283,285,279,273,272,272,273,276,276,277,280,283,285,286,290,292,289,288,294,305,317,329,341,353,362,367,367,368,371,357,351,343,342,341,335,331,326,324,319,313,312,313,309,307,302,296,294,289,287,286,288,289,287,285,282,282,280,278,278,277,274,272,268,265,259,254,249,250,246,235,228,220,217,214,215,213,212,209,205,204,202,200,197,196,193,192,190,186,179,129,125,124,122,121,121,118,118,116,120,119,120,129,131,137};
        diviserTableauX( xUSA);
        int yUSA [] = {245,248,256,262,263,262,264,265,266,269,273,279,288,291,292,288,282,275,273,273,270,272,274,280,282,283,277,277,272,268,263,263,265,267,271,273,278,281,287,290,294,296,296,299,304,307,307,316,320,324,329,332,334,336,343,348,352,360,366,371,374,372,366,362,357,355,351,351,351,349,349,349,348,350,354,354,351,352,356,358,361,366,372,374,371,367,362,358,354,351,352,350,346,341,340,338,322,321,315,311,305,299,296,289,289,284,281,272,253,242,244};
        diviserTableauY(yUSA);
        int xMadagascar[] = {821,825,828,830,832,833,836,838,840,840,844,844,844,842,843,841,837,834,831,827,825,821,819,817,819,816,814,813,815,814,819};
        diviserTableauX( xMadagascar);
        int yMadagascar[] = {595,592,588,584,577,572,566,560,554,551,553,550,547,544,540,535,540,545,550,552,553,553,554,558,569,573,578,581,587,591,593};
        diviserTableauY( yMadagascar);
        int xEquateur[] = {326,330,331,333,336,338,339,343,347,348,356,363,367,372,376,381,378,377,375,376,374,371,368,366,364,360,357,355,350,350,343,338,334,331,327,323,322,320,316,317,317,317,312,307,304,302,298,295,292,291,295,295,293,297,292,287,285,282,279,279,284,281,278,277,277,283,286,291,294,299,301,304,308,308,314,323,326};
        diviserTableauX( xEquateur);
        int yEquateur[] = {559,548,541,537,532,530,526,514,504,499,487,479,474,466,460,457,455,454,453,451,451,449,446,446,441,441,440,439,438,439,439,439,439,435,435,436,440,445,445,442,440,434,432,435,439,442,446,448,448,452,456,457,460,462,465,472,478,480,486,490,494,495,498,500,505,509,515,522,528,536,540,545,547,548,551,556,557};
        diviserTableauY( yEquateur);
        int xCanada [] = {134,137,252,264,272,277,282,287,291,290,291,296,305,308,319,320,348,351,357,364,374,381,386,393,397,406,413,410,405,401,395,398,402,404,405,398,397,395,394,390,385,381,375,372,369,368,371,374,374,366,364,361,357,353,347,345,345,341,342,345,341,342,339,339,340,332,326,320,318,318,315,314,308,307,309,310,312,311,305,300,296,294,293,292,289,285,283,278,278,277,277,274,272,275,274,278,281,285,286,288,291,294,298,298,293,295,301,305,308,313,315,318,313,311,318,320,325,325,338,343,346,347,354,352,349,357,363,366,368,371,375,378,371,368,364,358,356,351,357,364,370,369,370,373,378,384,385,387,384,383,382,382,389,392,395,396,397,394,389,391,395,401,403,405,405,409,413,416,409,405,400,399,401,400,396,391,387,384,385,385,379,376,373,370,372,372,370,365,360,361,354,348,353,347,350,355,355,349,344,339,339,339,339,343,342,347,345,336,329,326,325,325,329,327,319,319,319,321,321,323,321,331,336,339,343,333,329,322,318,309,308,305,299,299,292,293,290,282,275,271,271,268,259,252,248,237,231,237,235,226,221,216,212,206,203,202,197,191,176,167,159,155,132,122,117,113,108,96,80,71,66,60,54,50,45,37,36,44,50,49,47,42,37,25,21,17,15,22,19,14,19,24,21,25,32,18,12,1,6,16,22,27,33,38,45,46,52,59,65,64,68,74,80,81,88,95,100,107,112,119,121,124,124,121,126,127,126};
        diviserTableauX( xCanada);
        int yCanada [] = {242,245,248,254,255,250,250,254,256,261,265,266,272,275,276,276,264,259,254,250,250,249,251,251,247,241,237,231,230,233,233,231,228,227,224,221,217,207,199,187,193,202,201,198,195,192,188,184,181,179,175,170,170,168,166,169,178,178,185,188,191,199,202,208,215,214,217,223,228,234,237,239,233,230,224,219,217,214,213,210,209,208,204,202,201,198,197,197,195,190,188,185,182,180,172,169,166,164,164,161,161,155,155,151,149,147,150,149,148,148,146,142,137,134,137,140,137,131,133,133,129,123,120,117,112,113,114,113,120,123,126,132,140,146,147,147,143,148,154,154,158,163,166,166,170,174,175,176,172,167,165,162,168,170,168,165,162,155,149,149,142,149,154,153,148,147,146,143,138,134,132,127,122,114,109,106,106,100,96,93,94,97,97,97,92,88,85,85,88,93,97,97,97,96,91,87,81,81,88,90,96,100,102,106,111,112,118,123,125,127,123,117,116,112,114,110,106,103,100,95,91,86,84,82,81,75,75,82,86,99,106,107,103,103,111,117,116,115,115,112,109,105,106,114,109,105,103,98,98,94,87,84,84,84,82,76,77,77,78,79,77,72,54,49,44,39,38,39,45,48,50,51,73,72,73,78,83,87,88,92,95,95,96,101,107,110,116,118,120,121,128,128,133,136,148,155,158,164,167,163,158,158,155,154,151,143,143,143,145,141,139,135,134,130,135,140,140,147,158,163,170,181,189,197,208,217,222};
		diviserTableauY(yCanada );





       Africa = new Polygon(xAfrica, yAfrica, yAfrica.length);
       Argentina = new Polygon(xArgentina,yArgentina, xArgentina.length);
       CAmerica = new Polygon(xCAmerica, yCAmerica, yCAmerica.length);
       Canada = new Polygon(xCanada, yCanada, yCanada.length);
       Mexico = new Polygon(xMexico, yMexico, xMexico.length);
       USA = new Polygon(xUSA, yUSA, xUSA.length);
       Brazil = new Polygon(xBrazil, yBrazil, xBrazil.length);
       Madagascar = new Polygon(xMadagascar, yMadagascar, yMadagascar.length);
       Equateur = new Polygon(xEquateur, yEquateur, yEquateur.length);

    }

    public void diviserTableauX( int [] t){                             //permet d'adapter le dessin des polygones à la taille de l'écran utilisé
		int width =1366;


		for( int i = 0 ; i<t.length;i++){
			t[i] = (int)(t[i]*largeurEcran/width);

		}

	}
	public void diviserTableauY( int [] t){                             //permet d'adapter le dessin des polygones à la taille de l'écran utilisé

		int height=768;

		for( int i = 0 ; i<t.length;i++){
			t[i] = (int) (t[i]*hauteurEcran/height);

		}

	}

public void mousePressed(MouseEvent e) {
      Point me = e.getPoint();
      if( Brazil.contains(me)){
		  onAClique = 1;
	  } else if ( USA.contains(me)){
		  onAClique=0;
	  }else if(Argentina.contains(me)){
		  onAClique = 2;
	  } else if (Mexico.contains(me)){
		  onAClique = 3;
	  }
	  repaint();

    }

    public void mouseReleased(MouseEvent e) {
       onAClique =-1;
       repaint();
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {							//Permet d'effectuer une action au clic de la souris sur un objet
    Point me =e.getPoint();
    fen.setCoordonateX(me.getX());
    fen.setCoordonateY(me.getY());
    xPeintureEnCours[compteurPeintre] = (int)me.getX();
    yPeintureEnCours[compteurPeintre] = (int)me.getY();
    PeintureEnCours = new Polygon( xPeintureEnCours, yPeintureEnCours, compteurPeintre+1);
    compteurPeintre++;
    System.out.println(	me);
    System.out.println(	compteurPeintre);
    validate();
    this.repaint();
    fen.repeindre();
   /* if(Brazil.contains(me)){
		System.out.println("Je suis là");
		JFrame J = new JFrame("corona");
			//J.removeAll();
			J.setVisible(true);
			J.setExtendedState(Frame.MAXIMIZED_BOTH);
			J.repaint();
			JPanel p = new JPanel(new FlowLayout());

			J.add(new JButton("Bouh"));
			//J.add(p);
			J.validate();
			J.repaint();


	}*/
    }

}
