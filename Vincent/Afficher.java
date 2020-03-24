
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

public Afficher( FenetreJeu f , String s) {
	super(new FlowLayout() );
//im = Toolkit.getDefaultToolkit().getImage(s);
fen = f;
initComponents();

try{
im = ImageIO.read(new File(s));


  } catch (IOException ex) {
                ex.printStackTrace();
            }
addMouseListener(this);
 
}

public Afficher( Shape f){
	

	
	
}
///*
public void paintComponent(Graphics g) {
int h = getHeight()/20;
int w = getWidth()/25;

			g.drawImage(im, 0, 0, getWidth(), getHeight(), this);
			g.setColor(Color.GREEN);
                
				g.fillPolygon(Mexico);
                g.fillPolygon(Africa);
                g.fillPolygon(Brazil);
                g.fillPolygon(CAmerica);
                g.fillPolygon(USA);
  
   if(compteurPeintre>1){
                g.fillPolygon(PeintureEnCours);
			}
			
           g.setColor(Color.BLACK);
           
				g.drawPolygon(Mexico);
                g.drawPolygon(Africa);
                g.drawPolygon(Brazil);
                g.drawPolygon(CAmerica);
                g.drawPolygon(USA);
                

}//*/
/* @Override
           protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GREEN);
                
				g.fillPolygon(Mexico);
                g.fillPolygon(Africa);
                g.fillPolygon(SAmerica);
                g.fillPolygon(CAmerica);
            }*/
   private void initComponents() {

		int xBrazil [] = {383,380,380,380,379,368,360,347,337,344,347,354,376,382,387,391,394,401,406,408,410,406,407,411,414,416,419,421,424,427,430,435,436,441,447,453,455,460,467,472,474,475,475,472,467,461,461,461,459,455,452,447,440,432,422,422,420,416,411,404,397};
		int yBrazil [] = {630,611,593,581,566,553,539,538,530,513,500,490,461,458,458,457,458,462,467,471,476,482,488,490,490,488,487,487,485,487,488,490,494,493,493,493,496,498,502,505,510,514,516,521,529,541,546,553,558,566,577,578,575,585,592,596,603,610,617,628,635};
        int xArgentina [] = {384,385,387,388,390,392,388,386,378,371,370,364,362,361,362,362,359,358,357,362,365,362,359,357,357,357,352,346,340,339,336,333,333,332,332,332,332,326,325,328,323,326,327,327,332,337,346,351,360,366,371,376,379};
        int yArgentina [] = {630,634,636,638,643,643,651,652,655,655,666,663,667,672,675,682,687,692,696,700,699,705,712,716,722,727,734,732,729,726,719,713,703,697,689,681,670,667,663,657,652,646,640,559,538,531,538,539,540,550,556,566,567};
        int xAfrica[] = {705, 678, 681, 667, 658, 594, 555, 556, 604, 670, 764, 816, 848, 798, 804, 775, 762, 714};
        int yAfrica[] = {631, 558, 509, 488, 466, 464, 425, 394, 332, 325, 354, 441, 435, 503, 550, 571, 608, 632};
		int xSAmerica [] = {293,297,300,303,305,308,313,318,317,319,327,334,340,345,356,362,365,368,371,375,380,387,389,396,401,405,410,409,408,402,414,419,423,433,436,452,460,465,470,473,474,474,471,465,463,461,460,461,460,457,452,452,448,440,432,428,425,423,402,395,388,385,392,390,384,375,372,366,363,363,367,364,365,359,364,367,359,357,352,348,342,337,335,333,330,328,331,331,331,329,325,326,323,323,326,327,308,300,294,285,280,278,275,282,285,287,293,293,293,291,293};
		int ySAmerica [] = {447,447,444,441,438,434,433,431,438,438,434,437,439,439,438,438,442,445,448,452,455,459,457,459,461,465,474,477,481,489,489,484,486,490,493,494,498,502,502,505,510,518,526,530,536,539,542,550,555,562,573,577,579,580,584,587,589,604,631,635,632,632,646,651,654,654,665,665,665,672,673,677,685,690,700,702,713,730,731,734,731,721,716,710,702,698,694,690,681,673,669,661,653,647,643,561,550,539,529,514,507,507,502,499,496,474,468,462,456,449,448};
		int xCAmerica [] = {293,285,277,269,269,269,261,249,251,242,237,238,233,233,245,252,254,261,268,275,281,286,291};
		int yCAmerica [] = {447,441,444,436,427,418,413,414,403,406,406,412,414,420,425,425,432,440,447,450,452,446,447};
		int xMexico [] = {232,222,215,181,176,178,162,152,149,145,145,148,149,151,155,157,160,160,151,149,143,144,141,138,136,134,126,125,248,237,227,221,217,214,215,213,212,211,212,214,217,221,227,231,235,239,242,244,247,256,254,251,250,243,236,236,233,232};
		int yMexico [] = {420,414,417,400,395,390,363,347,338,337,347,350,354,359,367,373,376,382,374,364,359,352,350,341,330,326,321,322,354,351,352,356,358,362,369,374,379,386,393,397,402,404,404,403,400,397,395,392,389,391,395,399,403,407,406,411,417,419};
        int xUSA [] = {128,124,124,123,121,119,120,119,117,116,118,119,119,121,125,126,128,129,137,342,334,331,326,325,319,315,312,311,306,299,293,290,288,287,290,288,283,280,281,279,276,273,268,259,255,252,249,241,238,128};
        int yUSA [] = {323,323,318,315,312,307,303,300,295,289,286,280,274,269,265,256,249,242,244,295,295,295,298,303,306,312,321,327,331,333,336,341,345,358,366,371,373,368,362,356,353,353,350,348,349,352,354,353,353,323};
        Africa = new Polygon(xAfrica, yAfrica, yAfrica.length);
        SAmerica = new Polygon(xSAmerica, ySAmerica, ySAmerica.length);
       CAmerica = new Polygon(xCAmerica, yCAmerica, yCAmerica.length);
       Mexico = new Polygon(xMexico, yMexico, xMexico.length);
       USA = new Polygon(xUSA, yUSA, xUSA.length);
       Brazil = new Polygon(xBrazil, yBrazil, xBrazil.length);

    }


public void mousePressed(MouseEvent e) {
      
    }
     
    public void mouseReleased(MouseEvent e) {
       
    }
     
    public void mouseEntered(MouseEvent e) {
     
    }
     
    public void mouseExited(MouseEvent e) {
     
    }
     
    public void mouseClicked(MouseEvent e) {
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
    if(Brazil.contains(me)){
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
		
		
	}
    }

}

