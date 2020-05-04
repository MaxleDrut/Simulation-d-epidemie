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
import java.awt.event.MouseMotionListener;
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

import java.util.LinkedList;

public class Carte extends JPanel implements MouseListener, MouseMotionListener{
	private BufferedImage im;

	private Simulation fen;

	private int compteurPeintre = 0;
	private int [] xPeintureEnCours = new int [1000];
	private int [] yPeintureEnCours = new int [1000];
	private PolygonePays peintureEnCours;
	private Monde monde;

	private boolean passage;
	private int largeurEcran =(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int hauteurEcran = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private int onAClique =-1; // Ce compteur permet de savoir à quelle étape on est de l'affichage ; -1 signifie qu'on est à l'initialisation

	private double degreInfection;
	private PolygonePays polygonePaysSurbrillance;
	
	private LinkedList<PolygonePays> listePolygonePays= new LinkedList<PolygonePays>();
	
	private PolygonePays CAmerica;
	private PolygonePays Mexico;
	private PolygonePays USA;
	private PolygonePays Brazil;
	private PolygonePays Argentina;
	private PolygonePays Madagascar;
	private PolygonePays Equateur;
	private PolygonePays Canada;
	private PolygonePays Spain;
	private PolygonePays France;
	private PolygonePays UK;
	private PolygonePays Italy;
	private PolygonePays NorthAfrica;
	private PolygonePays Sahel;
	private PolygonePays Ethiopia;
	private PolygonePays Congo;
	private PolygonePays SouthAfrica;
	private PolygonePays Arabia;
	private PolygonePays SEAsia;
	private PolygonePays India;
	private PolygonePays Kazakhstan;
	private PolygonePays China;
	private PolygonePays CentralAsia;
	private PolygonePays Indonesia;
	private PolygonePays Australia;
	private PolygonePays NewZealand;
	private PolygonePays CenterEurope;
	private PolygonePays Russia;
	private PolygonePays NorthenLands;
	private PolygonePays IndependantNationOfReunionIsland;
	private PolygonePays Greenland;
	private PolygonePays Turkey;
	private PolygonePays Japan;
	private PolygonePays Iceland;
	private PolygonePays Balkans;

	
public Carte( Simulation f , String s) {
	super(new FlowLayout() );
	fen = f;
	initComponents();

	try{
	im = ImageIO.read(new File(s));


	} catch (IOException ex) {
               // ex.printStackTrace();
            }

	addMouseListener(this);
	addMouseMotionListener(this);
	
	}


public void paintComponent(Graphics g) {

	Graphics2D g1 = (Graphics2D) g;
	BasicStroke line = new BasicStroke(3.0f);
	g1.setStroke(line);

	g.drawImage(im, 0, 0, getWidth(), getHeight(), this);


	for( PolygonePays p : listePolygonePays){
		if(p!=null){
			Color couleur = new Color((int) ((double) (p.getPays().getInfectes())/p.getPays().popInit*255),230 - (int) ((double)(p.getPays().getInfectes())/(p.getPays().popInit)*230), (int)((double) (p.getPays().getMorts())/p.getPays().popInit*255));
		if(passage && p == polygonePaysSurbrillance){
			g.setColor(couleur.brighter().brighter());
		}else{
			g.setColor( couleur);
	
}
			g.fillPolygon(p);
}
}


		if(compteurPeintre>1){
			g.setColor(Color.BLACK);
			g.drawPolygon(peintureEnCours);
}
	g.setColor(Color.BLACK);

	for( PolygonePays p : listePolygonePays){
		if(p!=null){
			g.drawPolygon(p);
}
}



}
private void initComponents() {
		monde=new Monde();
		int xBrazil [] = {383,380,380,380,379,368,360,347,337,344,347,354,376,382,387,391,394,401,406,408,410,406,407,411,414,416,419,421,424,427,430,435,436,441,447,453,455,460,467,472,474,475,475,472,467,461,461,461,459,455,452,447,440,432,422,422,420,416,411,404,397};
		int yBrazil [] = {630,611,593,581,566,553,539,538,530,513,500,490,461,458,458,457,458,462,467,471,476,482,488,490,490,488,487,487,485,487,488,490,494,493,493,493,496,498,502,505,510,514,516,521,529,541,546,553,558,566,577,578,575,585,592,596,603,610,617,628,635};
        diviserTableauX(xBrazil);
        diviserTableauY(yBrazil);
        Brazil = new PolygonePays(xBrazil, yBrazil, xBrazil.length, monde.getPaysParNom("Brazil"));
        listePolygonePays.add(Brazil);


        int xArgentina [] = {384,385,387,388,390,392,388,386,378,371,370,364,362,361,362,362,359,358,357,362,365,362,359,357,357,357,352,346,340,339,336,333,333,332,332,332,332,326,325,328,323,326,327,327,332,337,346,351,360,366,371,376,379};
        int yArgentina [] = {630,634,636,638,643,643,651,652,655,655,666,663,667,672,675,682,687,692,696,700,699,705,712,716,722,727,734,732,729,726,719,713,703,697,689,681,670,667,663,657,652,646,640,559,538,531,538,539,540,550,556,566,567};
        diviserTableauX(xArgentina);
        diviserTableauY(yArgentina);
        Argentina = new PolygonePays(xArgentina,yArgentina, xArgentina.length,monde.getPaysParNom("Argentina") );
        listePolygonePays.add(Argentina);

		int xCAmerica [] = {293,285,277,269,269,269,261,249,251,242,237,238,233,233,245,252,254,261,268,275,281,286,291};
		int yCAmerica [] = {447,441,444,436,427,418,413,414,403,406,406,412,414,420,425,425,432,440,447,450,452,446,447};
		diviserTableauX(xCAmerica);
		diviserTableauY(yCAmerica);
		CAmerica = new PolygonePays(xCAmerica, yCAmerica, yCAmerica.length,monde.getPaysParNom("CAmerica"));
		
        listePolygonePays.add(CAmerica);

		int xMexico [] = {232,228,223,214,205,195,189,183,180,176,179,176,170,161,159,154,152,148,145,144,148,150,157,159,159,152,151,150,149,146,146,144,139,140,139,138,135,129,190,193,199,203,206,212,207,210,211,213,219,224,232,237,240,242,244,246,256,254,254,252,245,242,237,233,232};
		int yMexico [] = {419,417,414,415,412,407,404,402,400,396,390,384,373,362,355,350,339,338,336,341,350,355,371,374,379,374,368,364,361,360,356,352,348,343,340,332,327,323,342,351,353,361,369,374,382,389,394,399,403,405,403,401,397,395,392,390,390,394,398,402,404,405,412,413,418};
        diviserTableauX( xMexico);
        diviserTableauY( yMexico);
        Mexico = new PolygonePays(xMexico, yMexico, xMexico.length, monde.getPaysParNom("Mexico"));
        listePolygonePays.add(Mexico);

        int xUSA [] = {137,251,266,260,264,272,276,283,285,279,273,272,272,273,276,276,277,280,283,285,286,290,292,289,288,294,305,317,329,341,353,362,367,367,368,371,357,351,343,342,341,335,331,326,324,319,313,312,313,309,307,302,296,294,289,287,286,288,289,287,285,282,282,280,278,278,277,274,272,268,265,259,254,249,250,246,235,228,220,217,214,215,213,212,209,205,204,202,200,197,196,193,192,190,186,179,129,125,124,122,121,121,118,118,116,120,119,120,129,131,137};
        int yUSA [] = {245,248,256,262,263,262,264,265,266,269,273,279,288,291,292,288,282,275,273,273,270,272,274,280,282,283,277,277,272,268,263,263,265,267,271,273,278,281,287,290,294,296,296,299,304,307,307,316,320,324,329,332,334,336,343,348,352,360,366,371,374,372,366,362,357,355,351,351,351,349,349,349,348,350,354,354,351,352,356,358,361,366,372,374,371,367,362,358,354,351,352,350,346,341,340,338,322,321,315,311,305,299,296,289,289,284,281,272,253,242,244};
        diviserTableauX(xUSA);
        diviserTableauY(yUSA);
        USA = new PolygonePays(xUSA, yUSA, xUSA.length, monde.getPaysParNom("USA"));
        listePolygonePays.add(USA);

        int xMadagascar[] = {821,825,828,830,832,833,836,838,840,840,844,844,844,842,843,841,837,834,831,827,825,821,819,817,819,816,814,813,815,814,819};
        int yMadagascar[] = {595,592,588,584,577,572,566,560,554,551,553,550,547,544,540,535,540,545,550,552,553,553,554,558,569,573,578,581,587,591,593};
        diviserTableauX(xMadagascar);
        diviserTableauY(yMadagascar);
        Madagascar = new PolygonePays(xMadagascar, yMadagascar, xMadagascar.length, monde.getPaysParNom("Madagascar"));
        listePolygonePays.add(Madagascar);

        int xEquateur[] = {326,330,331,333,336,338,339,343,347,348,356,363,367,372,376,381,378,377,375,376,374,371,368,366,364,360,357,355,350,350,343,338,334,331,327,323,322,320,316,317,317,317,312,307,304,302,298,295,292,291,295,295,293,297,292,287,285,282,279,279,284,281,278,277,277,283,286,291,294,299,301,304,308,308,314,323,326};
        int yEquateur[] = {559,548,541,537,532,530,526,514,504,499,487,479,474,466,460,457,455,454,453,451,451,449,446,446,441,441,440,439,438,439,439,439,439,435,435,436,440,445,445,442,440,434,432,435,439,442,446,448,448,452,456,457,460,462,465,472,478,480,486,490,494,495,498,500,505,509,515,522,528,536,540,545,547,548,551,556,557};
        diviserTableauX(xEquateur);
        diviserTableauY(yEquateur);
        Equateur = new PolygonePays(xEquateur, yEquateur, xEquateur.length,monde.getPaysParNom("Equateur"));
        listePolygonePays.add(Equateur);

        int xCanada [] = {134,137,252,264,272,277,282,287,291,290,291,296,305,308,319,320,348,351,357,364,374,381,386,393,397,406,413,410,405,401,395,398,402,404,405,398,397,395,394,390,385,381,375,372,369,368,371,374,374,366,364,361,357,353,347,345,345,341,342,345,341,342,339,339,340,332,326,320,318,318,315,314,308,307,309,310,312,311,305,300,296,294,293,292,289,285,283,278,278,277,277,274,272,275,274,278,281,285,286,288,291,294,298,298,293,295,301,305,308,313,315,318,313,311,318,320,325,325,338,343,346,347,354,352,349,357,363,366,368,371,375,378,371,368,364,358,356,351,357,364,370,369,370,373,378,384,385,387,384,383,382,382,389,392,395,396,397,394,389,391,395,401,403,405,405,409,413,416,409,405,400,399,401,400,396,391,387,384,385,385,379,376,373,370,372,372,370,365,360,361,354,348,353,347,350,355,355,349,344,339,339,339,339,343,342,347,345,336,329,326,325,325,329,327,319,319,319,321,321,323,321,331,336,339,343,333,329,322,318,309,308,305,299,299,292,293,290,282,275,271,271,268,259,252,248,237,231,237,235,226,221,216,212,206,203,202,197,191,176,167,159,155,132,122,117,113,108,96,80,71,66,60,54,50,45,37,36,44,50,49,47,42,37,25,21,17,15,22,19,14,19,24,21,25,32,18,12,1,6,16,22,27,33,38,45,46,52,59,65,64,68,74,80,81,88,95,100,107,112,119,121,124,124,121,126,127,126};
        int yCanada [] = {242,245,248,254,255,250,250,254,256,261,265,266,272,275,276,276,264,259,254,250,250,249,251,251,247,241,237,231,230,233,233,231,228,227,224,221,217,207,199,187,193,202,201,198,195,192,188,184,181,179,175,170,170,168,166,169,178,178,185,188,191,199,202,208,215,214,217,223,228,234,237,239,233,230,224,219,217,214,213,210,209,208,204,202,201,198,197,197,195,190,188,185,182,180,172,169,166,164,164,161,161,155,155,151,149,147,150,149,148,148,146,142,137,134,137,140,137,131,133,133,129,123,120,117,112,113,114,113,120,123,126,132,140,146,147,147,143,148,154,154,158,163,166,166,170,174,175,176,172,167,165,162,168,170,168,165,162,155,149,149,142,149,154,153,148,147,146,143,138,134,132,127,122,114,109,106,106,100,96,93,94,97,97,97,92,88,85,85,88,93,97,97,97,96,91,87,81,81,88,90,96,100,102,106,111,112,118,123,125,127,123,117,116,112,114,110,106,103,100,95,91,86,84,82,81,75,75,82,86,99,106,107,103,103,111,117,116,115,115,112,109,105,106,114,109,105,103,98,98,94,87,84,84,84,82,76,77,77,78,79,77,72,54,49,44,39,38,39,45,48,50,51,73,72,73,78,83,87,88,92,95,95,96,101,107,110,116,118,120,121,128,128,133,136,148,155,158,164,167,163,158,158,155,154,151,143,143,143,145,141,139,135,134,130,135,140,140,147,158,163,170,181,189,197,208,217,222};
		diviserTableauX(xCanada);
		diviserTableauY(yCanada);
		Canada = new PolygonePays(xCanada, yCanada, xCanada.length, monde.getPaysParNom("Canada"));
	    listePolygonePays.add(Canada);

		int xSpain [] = {604,601,601,592,592,589,592,594,593,592,596,600,608,616,620,623,624,639,638,634,631,628,627,627,625,624,622,620,614,607,603};
		int ySpain [] = {329,329,326,325,317,316,311,305,303,293,293,291,294,294,295,294,289,301,306,305,307,310,313,317,319,322,322,325,325,327,327};
		diviserTableauX( xSpain);
		diviserTableauY( ySpain);
		Spain = new PolygonePays(xSpain, ySpain, xSpain.length, monde.getPaysParNom("Spain") );
	    listePolygonePays.add(Spain);

		int xFrance [] = {659,657,654,649,649,648,638,634,630,627,624,624,622,619,614,611,615,619,620,622,625,626,623,625,639,640,642,650,652,656};
        int yFrance [] = {292,283,278,279,273,266,265,262,262,265,264,261,267,267,266,268,274,274,277,280,284,286,289,290,302,296,293,293,295,292};
        diviserTableauX( xFrance);
        diviserTableauY( yFrance);
        France = new PolygonePays(xFrance, yFrance, xFrance.length,monde.getPaysParNom("France"));
	    listePolygonePays.add(France);

        int xUK [] = {610,614,617,621,631,633,634,629,627,623,620,616,616,619,619,615,615,612,608,606,606,609,609,606,599,596,594,594,594,591,590,595,598,604,608,610,609};
        int yUK [] = {256,256,255,255,252,248,244,243,237,232,228,227,223,218,217,215,211,211,213,214,220,219,222,226,230,234,237,241,245,247,249,249,246,242,236,240,247};
        diviserTableauX( xUK);
        diviserTableauY( yUK);
        UK  = new PolygonePays(xUK, yUK, xUK.length,monde.getPaysParNom("UK"));
	    listePolygonePays.add(UK);

        int xItaly [] = {659,658,658,661,672,680,682,677,678,684,684,688,690,691,692,697,699,701,702,700,697,698,697,694,691,690,686,682,679,679,682,689,692,693,694,694,691,691,683,680,680,677,676,673,670,667,662};
        int yItaly [] = {292,289,284,280,277,277,283,286,290,295,297,300,301,302,306,307,305,309,312,307,308,310,313,318,321,324,325,323,322,319,318,317,316,315,316,314,312,310,305,305,302,300,298,296,293,291,291};
        diviserTableauX( xItaly);
        diviserTableauY( yItaly);
        Italy = new PolygonePays(xItaly, yItaly, xItaly.length,monde.getPaysParNom("Italy"));
	    listePolygonePays.add(Italy);

        int xNorthAfrica [] = {565,577,593,615,631,650,671,694,708,720,746,760,773,783,785,783,781,779,770,770,765,766,771,773,773,771,762,756,753,745,734,730,725,721,716,715,713,713,712,708,702,695,692,680,672,670,672,672,671,669,664,662,657,651,645,637,631,628,624,618,611,608,604,601,601,597,594,591,590,590,587,588,581,577,573,568,567};
        int yNorthAfrica [] = {379,396,394,385,401,413,424,417,412,418,409,414,405,397,395,392,390,383,371,368,360,358,365,364,359,352,351,353,353,351,352,351,347,345,345,350,349,353,356,356,353,351,347,345,341,337,335,333,327,323,325,326,324,328,327,325,329,330,333,333,333,333,333,336,339,338,339,342,346,352,354,358,362,365,368,371,375};
        diviserTableauX( xNorthAfrica);
        diviserTableauY( yNorthAfrica);
        NorthAfrica = new PolygonePays(xNorthAfrica, yNorthAfrica, xNorthAfrica.length, monde.getPaysParNom("North Africa"));
	    listePolygonePays.add(NorthAfrica);

        int xSahel [] = {746,721,707,671,632,616,594,578,565,563,561,559,559,555,557,558,558,559,557,557,556,554,556,555,560,563,569,569,571,576,580,585,590,601,603,613,616,621,622,627,628,634,634,641,646,650,655,659,666,669,676,695,705,717,727,735,741,743,744,745,745};
        int ySahel [] = {409,419,412,424,402,386,395,396,380,382,385,389,392,393,396,400,404,411,414,418,420,420,427,431,434,439,445,449,452,453,457,461,464,463,462,463,464,464,461,459,460,458,456,458,458,462,467,464,465,468,471,471,469,464,461,456,448,439,425,416,411};
        diviserTableauX( xSahel);
        diviserTableauY( ySahel);
        Sahel = new PolygonePays(xSahel, ySahel, xSahel.length,monde.getPaysParNom("Sahel"));
	    listePolygonePays.add(Sahel);

        int xEthiopia [] = {745,745,743,744,741,735,728,751,767,776,784,794,796,798,800,801,801,804,806,809,810,811,815,818,821,824,824,827,831,833,838,839,840,841,843,844,845,848,848,847,841,836,830,824,820,816,812,813,813,804,802,799,794,795,793,789,788,786,786,761};
        int yEthiopia [] = {409,418,426,435,443,455,460,469,474,489,501,505,507,504,502,500,495,493,490,491,489,486,482,480,479,478,474,471,471,469,466,462,456,455,451,449,446,444,439,434,438,439,441,439,439,440,438,435,430,426,424,422,419,415,411,407,406,398,395,414};
        diviserTableauX( xEthiopia);
        diviserTableauY( yEthiopia);
        Ethiopia = new PolygonePays(xEthiopia, yEthiopia, xEthiopia.length,monde.getPaysParNom("Ethiopia"));
	    listePolygonePays.add(Ethiopia);

        int xCongo [] = {670,669,668,668,666,669,677,680,683,684,683,686,687,686,682,681,692,706,725,737,745,759,771,778,789,799,803,802,802,802,798,798,796,796,784,767,728,721,717,712,707,698,689,682,676};
        int yCongo [] = {469,475,481,486,489,491,501,508,510,510,517,525,533,535,538,544,547,549,552,546,542,540,540,544,547,546,545,537,530,527,526,513,512,507,501,474,461,462,465,467,469,471,470,470,470};
        diviserTableauX( xCongo);
        diviserTableauY( yCongo);
        Congo = new PolygonePays(xCongo, yCongo, xCongo.length,monde.getPaysParNom("Congo"));
	    listePolygonePays.add(Congo);

        int xSouthAfrica [] = {681,681,679,679,682,686,688,690,693,697,698,701,704,704,705,709,710,716,718,734,738,744,750,754,757,760,763,764,766,765,771,774,778,777,779,777,776,776,779,782,785,791,794,797,800,801,800,795,791,787,780,777,774,771,758,752,747,741,733,728,726};
        int ySouthAfrica [] = {544,548,551,562,565,573,581,591,600,604,609,615,618,626,630,635,632,631,630,630,628,626,620,615,611,608,606,602,597,593,590,589,588,582,580,577,572,568,566,562,560,556,554,553,550,549,545,545,548,545,544,544,541,540,540,540,542,545,548,551,553};
        diviserTableauX( xSouthAfrica);
        diviserTableauY( ySouthAfrica);
        SouthAfrica = new PolygonePays(xSouthAfrica, ySouthAfrica, xSouthAfrica.length,monde.getPaysParNom("South Africa"));
	    listePolygonePays.add(SouthAfrica);

        int xArabia [] = {773,774,773,773,779,779,782,785,786,790,791,793,794,794,799,801,807,811,812,813,813,813,820,822,831,833,836,837,842,846,849,854,857,860,864,865,868,872,874,875,875,881,881,883,878,871,868,865,862,859,857,854,851,849,847,847,844,844,841,838,834,831,830,834,838,849,846,845,838,836,830,827,822,818,813,814,809,804,790,780,779,775,773,773,774};
        int yArabia [] = {354,356,362,364,366,369,376,380,383,383,387,391,393,398,398,404,410,413,418,423,427,432,432,430,429,428,427,424,424,422,419,417,415,414,413,410,410,407,404,400,397,396,393,390,386,383,379,374,376,380,383,383,382,383,383,376,375,380,378,372,366,360,357,356,356,343,328,325,324,323,320,314,315,313,320,323,322,321,324,324,331,337,343,350,358};
        diviserTableauX( xArabia);
        diviserTableauY( yArabia);
        Arabia = new PolygonePays(xArabia, yArabia, xArabia.length,monde.getPaysParNom("Arabia"));
	    listePolygonePays.add(Arabia);

        int xSEAsia [] = {1014,1026,1041,1050,1064,1079,1088,1086,1082,1081,1082,1087,1092,1097,1099,1099,1099,1095,1089,1085,1081,1081,1079,1077,1075,1071,1067,1063,1062,1059,1058,1057,1057,1056,1058,1062,1065,1070,1075,1074,1076,1074,1069,1065,1062,1063,1059,1054,1052,1051,1053,1053,1051,1051,1050,1047,1041,1039,1034,1032,1033,1031,1029,1028,1027,1023,1023,1021,1020,1021,1019,1015,1019,1020,1021,1018};
        int ySEAsia [] = {357,361,372,383,382,388,391,394,398,404,408,412,418,422,428,434,436,439,444,447,449,445,442,442,441,434,432,431,429,428,434,436,439,443,448,457,457,459,469,472,478,477,476,472,471,463,458,455,451,447,443,436,430,425,419,414,416,417,417,416,407,405,402,398,398,398,396,394,390,387,387,386,382,377,366,361};
        diviserTableauX( xSEAsia);
        diviserTableauY( ySEAsia);
        SEAsia = new PolygonePays(xSEAsia, ySEAsia, xSEAsia.length,monde.getPaysParNom("S.E Asia"));
        listePolygonePays.add(SEAsia);

        int xIndia [] = {1020,1010,988,975,957,931,919,912,906,907,912,919,926,925,931,938,938,940,943,948,950,952,954,956,959,960,963,965,968,971,971,969,971,972,972,975,981,983,987,990,992,995,998,998,999,1000,1004,1010,1014,1013,1020};
        int yIndia [] = {365,353,348,345,345,345,348,356,363,375,379,386,390,396,397,393,402,408,415,425,431,436,437,441,447,451,450,447,445,443,442,438,435,432,419,418,415,413,408,401,401,401,401,397,395,391,393,391,391,387,382};
        diviserTableauX( xIndia);
        diviserTableauY( yIndia);
        India = new PolygonePays(xIndia, yIndia, xIndia.length, monde.getPaysParNom("India"));
        listePolygonePays.add(India);

        int xKazakhstan [] = {986,989,991,991,1001,1006,1003,1001,998,995,989,987,980,966,959,948,945,939,936,929,925,921,917,905,902,897,884,876,865,863,860,860,862,855,850,848,839,834,830,822,818,816,814,818,821,823,825,827,830,834,837,839,839,837,833,834,835,837,844,847,851,856,860,862,865,868,871,883,917,929,955,969,};
        int yKazakhstan [] = {301,289,280,271,255,245,244,241,242,242,240,244,250,250,248,247,242,239,235,230,229,229,231,224,225,225,227,227,229,231,236,245,251,252,251,250,249,249,247,248,249,255,262,268,272,275,273,272,272,272,272,275,277,281,285,288,291,293,293,292,288,288,286,285,284,286,283,291,293,289,301,295};
        diviserTableauX( xKazakhstan);
        diviserTableauY( yKazakhstan);
        Kazakhstan = new PolygonePays(xKazakhstan, yKazakhstan, xKazakhstan.length,monde.getPaysParNom("Kazakstan"));
        listePolygonePays.add(Kazakhstan);

        int xChina [] = {1008,992,991,987,981,982,983,1010,1014,1020,1025,1029,1035,1042,1049,1050,1057,1059,1066,1067,1088,1092,1099,1106,1109,1114,1118,1124,1131,1135,1137,1137,1138,1139,1141,1137,1139,1138,1137,1134,1130,1127,1125,1128,1130,1132,1132,1136,1134,1132,1130,1128,1127,1124,1123,1121,1117,1117,1116,1114,1119,1121,1123,1124,1126,1129,1132,1132,1131,1132,1140,1144,1148,1148,1157,1158,1158,1163,1164,1166,1167,1164,1163,1160,1157,1155,1155,1158,1161,1162,1162,1164,1165,1164,1161,1164,1167,1168,1169,1169,1169,1165,1163,1161,1154,1153,1151,1143,1140,1138,1133,1132,1131,1126,1122,1118,1115,1109,1105,1101,1102,1102,1102,1102,1102,1100,1099,1096,1093,1091,1082,1082,1081,1075,1072,1068,1059,1055,1050,1043,1040,1034,1030,1029,1028,1027,1025,1023,1019,1017,1015,1015,1018,1018,1014,1008,1008};
        int yChina [] = {245,268,282,301,315,336,347,354,357,358,360,363,367,373,380,383,383,381,381,383,391,392,392,392,391,387,386,385,379,375,370,366,364,360,353,348,344,339,336,332,326,327,324,323,320,317,313,312,310,308,310,311,313,312,312,309,307,310,305,303,302,299,294,292,290,290,290,294,299,301,296,294,302,306,314,320,325,323,321,320,314,309,306,301,300,298,295,293,289,285,280,277,274,270,263,262,262,251,247,242,240,239,242,244,245,241,238,234,233,233,229,227,223,219,214,212,211,210,211,214,217,221,225,229,235,239,239,239,239,238,238,237,241,244,246,245,242,240,238,240,240,240,237,235,233,233,232,232,232,232,234,239,242,245,245,245,245};
        diviserTableauX( xChina);
        diviserTableauY( yChina);
        China = new PolygonePays(xChina, yChina, xChina.length,monde.getPaysParNom("China"));
        listePolygonePays.add(China);

        int xNewZealand [] = {1345,1345,1347,1345,1341,1343,1344,1338,1332,1327,1320,1312,1310,1305,1306,1311,1315,1318,1322,1323,1324,1329,1333,1337,1339,1339,1346,1350,1354,1357,1361,1364,1365,1362,1358,1355,1353,1350,1348};
        int yNewZealand [] = {663,671,677,686,692,697,703,700,708,713,717,722,723,730,735,740,737,737,736,733,727,722,719,716,714,706,704,701,697,693,684,679,677,676,674,671,676,676,674};
        diviserTableauX( xNewZealand);
        diviserTableauY( yNewZealand);
        NewZealand = new PolygonePays(xNewZealand, yNewZealand, xNewZealand.length,monde.getPaysParNom("New Zealand"));
        listePolygonePays.add(NewZealand);

        int xAustralia [] = {1217,1207,1202,1198,1195,1190,1188,1185,1177,1174,1170,1167,1162,1160,1154,1154,1142,1132,1125,1117,1114,1112,1111,1111,1113,1112,1114,1114,1114,1109,1111,1115,1120,1124,1128,1135,1140,1142,1146,1150,1153,1158,1164,1167,1173,1179,1188,1192,1194,1195,1200,1204,1207,1207,1206,1210,1211,1211,1217,1221,1225,1230,1234,1239,1243,1249,1252,1256,1258,1263,1264,1269,1273,1277,1279,1278,1274,1271,1271,1269,1263,1255,1255,1254,1254,1248,1247,1241,1240,1237,1237,1235,1231,1219,1215,1213,1213,1213,1216};
        int yAustralia [] = {538,538,536,533,539,540,543,549,548,547,550,551,555,559,559,566,571,574,577,582,586,592,601,608,615,625,628,631,636,643,646,647,648,644,644,643,643,645,644,638,634,635,635,632,634,631,635,638,646,652,648,644,641,649,653,660,664,666,675,676,675,674,678,676,675,674,669,665,659,653,647,643,636,630,624,606,597,595,590,588,576,570,565,561,553,549,540,535,540,548,557,560,566,559,556,553,550,547,543};
        diviserTableauX( xAustralia);
        diviserTableauY( yAustralia);
        Australia = new PolygonePays(xAustralia, yAustralia, xAustralia.length,monde.getPaysParNom("Australia"));
        listePolygonePays.add(Australia);

        int xIndonesia [] = {1040,1041,1054,1064,1070,1072,1083,1087,1093,1091,1102,1109,1118,1121,1119,1116,1108,1096,1090,1086,1085,1085,1080,1076,1073,1069,1064,1054,1051,1080,1057,1067,1075,1082,1094,1099,1101,1101,1100,1098,1101,1106,1110,1111,1116,1121,1128,1130,1132,1140,1141,1138,1135,1140,1142,1147,1154,1160,1163,1155,1154,1149,1150,1154,1155,1155,1174,1178,1191,1204,1214,1219,1239,1245,1252,1256,1259,1264,1266,1270,1273,1275,1265,1261,1258,1247,1245,1242,1226,1226,1224,1223,1220,1211,1215,1214,1209,1208,1204,1202,1199,1194,1192,1184,1181,1179,1173,1171,1165,1156,1158,1158,1155,1153,1153,1150,1149,1149,1148,1145,1144,1144,1146,1146,1148,1148,1148,1147,1139,1135,1131,1130,1128,1120,1118,1114,1111,1108,1106,1105,1105,1104,1101,1101,1097,1097,1095,1091,1087,1083,1079};
        int yIndonesia [] = {462,467,478,493,499,503,511,511,513,514,520,520,520,520,519,517,515,514,511,510,503,499,492,487,484,481,476,469,466,491,470,480,487,494,496,493,491,489,485,481,479,480,479,475,473,469,464,461,459,464,465,468,470,478,482,482,479,482,483,483,483,485,491,492,496,496,502,501,498,498,498,494,499,500,504,509,512,514,520,524,528,530,530,525,523,520,525,526,518,515,511,510,507,501,500,499,497,497,497,498,497,497,498,499,500,501,500,500,498,495,502,505,506,503,499,499,499,507,510,510,505,497,492,488,486,483,480,482,483,488,493,499,504,500,498,498,498,498,497,499,495,492,491,491,494,496,496,495,494,491,491};
        diviserTableauX( xIndonesia);
        diviserTableauY( yIndonesia);
        Indonesia = new PolygonePays(xIndonesia, yIndonesia, xIndonesia.length,monde.getPaysParNom("Indonesia"));
        listePolygonePays.add(Indonesia);

        int xCenterEurope [] = {635,635,639,647,649,649,655,657,665,671,677,681,685,705,719,726,734,738,741,751,748,744,743,743,740,738,734,727,721,719,718,714,714,713,710,708,707,709,708,702,698,693,685,684,680,678,674,672,669,667,664,662,656,650,649,646,645,642,644};
        int yCenterEurope [] = {255,260,264,264,271,277,278,280,277,276,276,279,283,279,278,274,270,263,259,259,254,252,244,241,237,232,224,219,213,217,219,218,216,214,216,219,223,228,231,231,230,234,234,239,236,233,236,236,233,232,237,239,239,239,246,246,248,250,251};
        diviserTableauX( xCenterEurope);
        diviserTableauY( yCenterEurope);
        CenterEurope = new PolygonePays(xCenterEurope, yCenterEurope, xCenterEurope.length,monde.getPaysParNom("Center Europe"));
        listePolygonePays.add(CenterEurope);
        
		int xBalkans[] = {755,740,732,717,702,688,690,698,704,708,707,712,714,716,720,725,725,722,721,734,737,741,742,741,742,743,745,747,749,749,751,754,759,761,766,770,769,763,759};
        int yBalkans[] = {260,261,273,279,281,284,290,295,300,304,311,315,315,319,316,315,310,310,308,308,305,303,299,296,295,291,290,286,285,282,276,276,278,280,276,268,264,261,260};
		diviserTableauX( xBalkans);
        diviserTableauY( yBalkans);
        Balkans = new PolygonePays(xBalkans, yBalkans, yBalkans.length,monde.getPaysParNom("Balkans"));
        listePolygonePays.add(Balkans);

        int xNorthenLands [] = {766,761,753,739,732,723,715,710,707,705,708,711,717,717,713,705,704,698,693,693,691,695,698,698,692,691,690,687,683,680,675,666,660,659,659,662,668,668,670,675,678,678,675,674,674,673,673,671,671,669,666,665,663,657,654,651,649,649,653,648,650,653,651,649,651,654,660,661,667,667,668,669,669,674,674,674,678,683,685,687,693,698,701,711,719,725,735,740,746,749,754,758,766,769,770,766,752,743,746,749,749,752,754,762};
        int yNorthenLands [] = {173,181,190,200,195,198,200,195,192,186,180,173,167,163,162,163,170,179,183,187,194,200,202,208,210,217,221,224,225,228,227,228,230,226,223,223,223,224,229,228,226,224,222,220,217,216,213,212,209,207,203,207,211,212,212,212,206,202,197,200,195,195,193,190,186,184,181,179,179,176,174,170,167,164,161,158,152,149,143,139,137,134,134,129,128,130,135,136,139,140,140,142,144,147,149,155,154,156,158,163,165,170,171,172};
        diviserTableauX( xNorthenLands);
        diviserTableauY( yNorthenLands);
        NorthenLands = new PolygonePays(xNorthenLands, yNorthenLands, xNorthenLands.length,monde.getPaysParNom("Northen Lands"));
        listePolygonePays.add(NorthenLands);

        int xRussia [] = {739,735,734,733,730,724,720,716,719,724,728,730,735,738,743,745,748,750,753,757,760,763,767,769,769,768,765,763,762,762,765,768,769,771,774,776,780,785,786,786,783,781,780,779,781,783,786,805,816,818,821,823,824,821,814,816,831,834,844,850,854,860,862,860,860,859,863,902,904,917,920,921,926,929,934,937,939,943,947,948,960,964,974,979,984,986,989,991,995,999,1002,1004,1017,1016,1015,1015,1016,1028,1031,1034,1046,1050,1060,1067,1071,1078,1081,1100,1101,1101,1101,1101,1101,1106,1110,1123,1127,1131,1133,1136,1142,1148,1151,1154,1157,1163,1166,1170,1169,1169,1169,1168,1165,1161,1164,1165,1169,1178,1180,1183,1185,1186,1187,1188,1189,1188,1185,1185,1185,1186,1182,1181,1177,1173,1170,1169,1166,1162,1158,1154,1159,1162,1163,1164,1172,1179,1187,1189,1202,1208,1209,1210,1217,1220,1224,1227,1230,1233,1237,1239,1237,1235,1234,1232,1230,1229,1228,1227,1228,1231,1237,1240,1244,1248,1249,1253,1253,1250,1253,1256,1254,1253,1253,1256,1259,1255,1254,1253,1252,1247,1244,1243,1250,1253,1254,1257,1262,1267,1267,1267,1270,1271,1274,1279,1279,1287,1286,1281,1278,1274,1270,1265,1262,1269,1271,1271,1270,1267,1275,1282,1298,1298,1294,1296,1288,1278,1273,1259,1252,1243,1228,1213,1202,1192,1178,1172,1163,1158,1154,1148,1141,1136,1132,1128,1123,1119,1118,1114,1106,1104,1091,1085,1083,1081,1086,1086,1081,1076,1071,1067,1063,1067,1060,1056,1052,1047,1039,1039,1042,1040,1023,1020,1022,1021,1014,1010,1004,998,997,994,988,985,976,978,977,977,972,963,944,930,929,931,928,925,925,920,913,910,894,896,900,903,896,890,886,886,886,888,894,898,899,903,911,896,888,876,878,882,877,873,873,876,879,881,887,887,885,884,882,878,878,878,875,872,872,867,866,865,865,863,858,855,852,851,853,853,850,852,856,859,861,861,855,852,838,834,829,819,803,795,793,790,785,785,786,780,778,779,781,781,779,779,776,772,770,771,764,761,759,764,765,761,755,749,740,738};
        int yRussia [] = {202,200,203,203,205,205,205,209,214,217,220,223,227,234,244,252,254,257,258,261,261,261,263,263,269,273,278,281,282,288,289,287,282,276,275,274,272,272,274,276,276,279,282,285,286,287,287,280,283,281,280,278,276,274,263,249,247,248,249,251,252,252,250,247,241,238,229,225,224,231,229,228,229,231,233,235,238,241,244,246,248,249,250,250,247,243,238,240,240,241,240,244,244,241,238,234,231,232,237,240,239,238,242,244,244,240,236,237,233,229,223,219,216,211,211,214,219,223,228,231,234,237,237,242,244,242,239,241,245,251,257,262,262,263,267,271,275,274,270,266,260,256,249,242,237,230,224,220,214,208,204,201,197,194,196,201,201,197,193,192,175,168,162,156,153,151,150,147,147,143,131,124,120,124,127,127,123,120,123,126,129,133,138,145,149,152,156,163,169,179,185,192,200,207,211,204,200,195,193,192,189,187,182,180,179,174,170,163,151,150,138,136,133,129,125,121,124,127,122,116,110,104,101,95,94,95,92,89,85,83,77,74,74,75,70,65,60,56,61,59,68,58,55,48,42,46,45,43,48,45,43,45,44,42,47,51,45,44,43,42,45,49,50,48,48,47,45,42,43,48,45,47,48,51,57,60,59,62,62,64,63,70,69,66,65,65,64,59,58,56,55,57,61,63,65,65,63,65,68,67,65,65,63,59,54,51,46,42,45,45,51,53,53,59,59,60,64,66,73,79,82,87,88,88,90,95,95,98,98,98,100,108,114,101,101,104,106,109,109,106,107,113,122,123,123,125,129,135,140,140,133,129,123,115,110,106,108,106,103,100,99,99,99,105,109,113,113,117,122,128,130,133,130,128,127,132,132,134,137,141,143,146,146,143,141,139,143,148,152,152,152,153,153,157,161,164,166,164,168,172,173,181,188,191,199,200};
        diviserTableauX( xRussia);
        diviserTableauY( yRussia);
        Russia = new PolygonePays(xRussia, yRussia, xRussia.length,monde.getPaysParNom("Russia"));
        listePolygonePays.add(Russia);

        int xGreenland [] = {550,526,525,515,512,505,503,499,494,489,479,476,467,465,462,460,457,456,450,454,451,443,438,434,425,423,423,425,428,431,425,422,422,425,421,422,425,430,431,432,437,442,445,449,451,452,452,452,452,455,455,454,452,449,448,450,456,458,455,452,453,457,456,453,450,448,444,443,441,444,444,446,446,449,449,450,451,455,459,463,467,473,475,478,481,485,488,491,494,499,502,510,515,518,521,526,532,535,539,546,551,552,543,537,537,543,545,554,554,551,550,553,556,561,565,567,571,571,568,565,567,568,571,573,574,572,570,570,573,576,579,581,577,584,590,581,573};
        int yGreenland [] = {12,14,22,30,27,22,28,25,21,21,23,28,28,30,30,31,34,34,40,40,46,46,49,49,50,53,55,58,60,63,63,62,67,69,71,74,76,77,74,76,76,76,77,80,83,84,88,93,97,99,105,108,108,112,115,117,118,123,122,118,124,128,133,135,137,138,141,145,150,153,162,165,174,179,181,186,190,192,191,195,198,189,185,179,171,167,162,162,160,157,157,152,149,147,145,143,142,142,142,136,135,132,133,132,127,127,124,129,124,118,112,111,111,111,111,108,99,93,90,89,86,83,83,87,78,76,73,70,65,62,57,55,54,50,46,43,44};
        diviserTableauX( xGreenland);
        diviserTableauY( yGreenland);
        Greenland = new PolygonePays(xGreenland, yGreenland, xGreenland.length,monde.getPaysParNom("Greenland"));
        listePolygonePays.add(Greenland);

        int xIceland [] = {562,567,573,576,578,580,576,573,568,565,558,557,553,548,551,550,550,548,546,545,551,554,553,556,563,566,566};
        int yIceland [] = {158,158,158,158,163,169,169,172,175,175,177,176,175,176,173,171,168,167,167,167,165,163,161,159,159,159,159};
        diviserTableauX( xIceland);
        diviserTableauY( yIceland);
        Iceland = new PolygonePays(xIceland, yIceland, xIceland.length,monde.getPaysParNom("Islande"));
	    listePolygonePays.add(Iceland);

        int xJapan [] = {1209,1212,1221,1224,1221,1219,1219,1217,1207,1206,1208,1207,1204,1200,1196,1185,1182,1178,1182,1183,1187,1195,1197,1200,1201,1208,1213,1214,1214,1213,1215,1214,1217,1216,1214,1212,1206,1205,1206,1206,1206,1205,1205,1205,1208,1211};
        int yJapan [] = {259,260,264,269,269,269,276,276,274,285,293,303,308,307,317,317,322,326,326,327,325,323,329,329,323,321,316,311,308,303,301,297,295,291,288,285,278,274,270,266,265,262,260,257,260,262};
        diviserTableauX( xJapan);
        diviserTableauY( yJapan);
        Japan = new PolygonePays(xJapan, yJapan, xJapan.length,monde.getPaysParNom("Japon"));
	    listePolygonePays.add(Japan);

        int xCentralAsia [] = {845,850,839,839,843,844,847,852,856,859,864,867,868,876,879,880,909,907,906,918,920,925,929,932,937,957,972,975,984,983,982,981,985,986,970,956,930,916,909,898,883,872,869,866,862,860,855,852,849,843,842,843,846,849,850,843,842,847,849,851,851,851,848,846,850};
        int yCentralAsia [] = {326,343,357,359,360,366,365,370,372,372,369,368,372,375,375,377,377,375,365,350,347,347,346,346,344,345,346,344,346,328,323,316,308,302,296,302,289,292,292,291,291,283,285,284,284,286,287,288,291,292,296,300,297,301,303,304,308,309,313,315,319,324,325,325,343};
        diviserTableauX( xCentralAsia);
        diviserTableauY( yCentralAsia);
        CentralAsia = new PolygonePays(xCentralAsia, yCentralAsia, xCentralAsia.length,monde.getPaysParNom("Central Asia"));
        listePolygonePays.add(CentralAsia);

        int xIndependantNationOfReunionIsland [] = {845,854,861,860,854,849};
        int yIndependantNationOfReunionIsland [] = {582,588,583,576,572,574};
        diviserTableauX( xIndependantNationOfReunionIsland);
        diviserTableauY( yIndependantNationOfReunionIsland);
        IndependantNationOfReunionIsland = new PolygonePays(xIndependantNationOfReunionIsland, yIndependantNationOfReunionIsland, xIndependantNationOfReunionIsland.length,monde.getPaysParNom("Independant Nation of Reunion Island"));
	    listePolygonePays.add(IndependantNationOfReunionIsland);

        int xTurkey[] = {805,817,824,831,831,824,821,812,813,811,806,792,782,774,771,762,762,758,755,754,749,744,740,737,735,742,747,754,757,762,768,771,775,780,780,785,787,793,796,797,793,791,789,785};
		int yTurkey[] = {280,283,296,306,312,311,312,313,320,322,320,323,323,324,328,328,326,325,324,327,327,323,323,314,310,308,308,305,302,302,302,302,302,304,304,305,304,303,302,299,294,292,290,287};
        diviserTableauX( xTurkey);
        diviserTableauY( yTurkey);
        Turkey = new PolygonePays(xTurkey, yTurkey, xTurkey.length,monde.getPaysParNom("Turkey"));
	    listePolygonePays.add(Turkey);



    }


    public void diviserTableauX( int [] t){                             //permet d'adapter le dessin des PolygonePayses à la taille de l'écran utilisé
		int width =1366;
	    //int wEcran =(int)tailleFen.getWidth();

		for( int i = 0 ; i<t.length;i++){
			t[i] = (int)(t[i]*1075/width);

		}

	}
	public void diviserTableauY( int [] t){                             //permet d'adapter le dessin des PolygonePayses à la taille de l'écran utilisé

		int height=768;
		//int hEcran = (int)tailleFen.getHeight();
		for( int i = 0 ; i<t.length;i++){
			t[i] = (int) (t[i]*550/height);

		}

	}
	/*On récupère le polygone cliqué parmi les polygones du JPanel.
	 *Ensuite, soit la simulation est déjà en cours (mondeInfect est true) et donc l'on affiche la fenêtre XChart du pays,
	 *soit la simulation n'a pas encore commencé et l'on ouvre la fenêtre d'infection*/

	public void mousePressed(MouseEvent e) {
     Point me = e.getPoint();
	 PolygonePays PolygonePaysClic= new PolygonePays();
	 for(PolygonePays poly:listePolygonePays){
		 if(poly!=null && poly.contains(me)){
			  PolygonePaysClic=poly;
		  }
	}
	  Pays paysClic=PolygonePaysClic.getPays();
	  if(fen.mondeInfect == true){
		new FenetreStats(paysClic,10000);
	} else{
		new FenetreInfection(paysClic);
		}
	}

    public void mouseReleased(MouseEvent e) {
       onAClique = -1;

    }

    public void mouseEntered(MouseEvent e) {}
	
    
    public void mouseMoved(MouseEvent e){
		Point me =e.getPoint();
		
		int i = 0;
		for(PolygonePays p : listePolygonePays){
	if(p!=null && p.contains(me)){
		
		passage = true;
		polygonePaysSurbrillance = p;
		fen.selectionPays(polygonePaysSurbrillance.getPays());
		if(fen.mondeInfect==true) { //Si le monde est déjà infecté, on affiche les statistiques du monde
			fen.afficherStatistiques();
		} else { //Sinon on affiche les stats du pays à infecter
			fen.afficherInfection();
		}
			repaint();
		i++;
	}
		
	}
	if(i==0){
		passage =false;
		fen.selectionPays(null);
		if(fen.mondeInfect==true) {
			fen.afficherStatistiques();
		} else {
			fen.afficherInfection();
		}
		repaint();
	}
}
	public void mouseDragged(MouseEvent e){
		
	}

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

	}


}
