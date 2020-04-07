
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
private Polygon Argentina;
private Polygon Madagascar;
private Polygon Equateur;
private Polygon Canada;
private Polygon Spain;
private Polygon France;
private Polygon UK;
private Polygon Italy;
private Polygon NAfrica;
private Polygon Sahel;
private Polygon Ethiopia;
private Polygon Congo;
private Polygon SAfrica;
private Polygon Arabia;
private Polygon CAsia;
private Polygon India;
private Polygon Kazakhstan;
private Polygon China;
private Polygon SEAsia;
private Polygon Indonesia;
private Polygon Australia;
private Polygon NewZealand;
private Polygon CenterEurope;
private Polygon Russia;

private Polygon NorthenLands;
private Polygon IndependantNationOfReunionIsland;
private Polygon Greenland;

//private Dimension taille = FenToolkit.getDefaultToolkit().getScreenSize(); 


private int largeurEcran =(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
private int hauteurEcran = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
private int onAClique =-1;												// Ce compteur permet de savoir à quelle étape on est de l'affichage -1 signifie qu'on est à l'initialisation



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
 //tailleFen = this.getSize();
}


public void paintComponent(Graphics g) {
int h = getHeight()/20;
int w = getWidth()/25;

			g.drawImage(im, 0, 0, getWidth(), getHeight(), this);
			g.setColor(Color.GREEN);
                
				g.fillPolygon(Mexico);
                g.fillPolygon(Brazil);
                g.fillPolygon(CAmerica);
                g.fillPolygon(USA);
                g.fillPolygon(Argentina);
                g.fillPolygon(Equateur);
                g.fillPolygon(Canada);
                g.fillPolygon(Spain);
				g.fillPolygon(France);
				g.fillPolygon(UK);
				g.fillPolygon(Italy);
				g.fillPolygon(NAfrica);
				g.fillPolygon(Sahel);
				g.fillPolygon(Ethiopia);
			    g.fillPolygon(Congo);
				g.fillPolygon(SAfrica);
				g.fillPolygon(Madagascar);
				g.fillPolygon(Arabia);
				g.fillPolygon(India);
				g.fillPolygon(Australia);
				g.fillPolygon(NewZealand);
				g.fillPolygon(Indonesia);
				
   if(compteurPeintre>1){
	   
                //g.fillPolygon(PeintureEnCours);
                g.setColor(Color.BLACK);
                g.drawPolygon(PeintureEnCours);
			}
			
           g.setColor(Color.BLACK);

				g.drawPolygon(Equateur);
				g.drawPolygon(Argentina);
				g.drawPolygon(Madagascar);
				g.drawPolygon(Mexico);
                g.drawPolygon(Brazil);
                g.drawPolygon(CAmerica);
                g.drawPolygon(USA);
                g.drawPolygon(Canada);
                g.drawPolygon(Spain);
				g.drawPolygon(France);
				g.drawPolygon(UK);
				g.drawPolygon(Italy);
				g.drawPolygon(NAfrica);
				g.drawPolygon(Sahel);
				g.drawPolygon(Ethiopia);
                g.drawPolygon(Congo);
                g.drawPolygon(SAfrica);
                g.drawPolygon(Arabia);
                g.drawPolygon(India);
                g.drawPolygon(Australia);
                g.drawPolygon(NewZealand);
                g.drawPolygon(Indonesia);
                
                
}
   private void initComponents() {

		int xBrazil [] = {383,380,380,380,379,368,360,347,337,344,347,354,376,382,387,391,394,401,406,408,410,406,407,411,414,416,419,421,424,427,430,435,436,441,447,453,455,460,467,472,474,475,475,472,467,461,461,461,459,455,452,447,440,432,422,422,420,416,411,404,397};
		diviserTableauX( xBrazil);
		int yBrazil [] = {630,611,593,581,566,553,539,538,530,513,500,490,461,458,458,457,458,462,467,471,476,482,488,490,490,488,487,487,485,487,488,490,494,493,493,493,496,498,502,505,510,514,516,521,529,541,546,553,558,566,577,578,575,585,592,596,603,610,617,628,635};
        diviserTableauY( yBrazil);
        int xArgentina [] = {384,385,387,388,390,392,388,386,378,371,370,364,362,361,362,362,359,358,357,362,365,362,359,357,357,357,352,346,340,339,336,333,333,332,332,332,332,326,325,328,323,326,327,327,332,337,346,351,360,366,371,376,379};
        diviserTableauX( xArgentina);
        int yArgentina [] = {630,634,636,638,643,643,651,652,655,655,666,663,667,672,675,682,687,692,696,700,699,705,712,716,722,727,734,732,729,726,719,713,703,697,689,681,670,667,663,657,652,646,640,559,538,531,538,539,540,550,556,566,567};
        diviserTableauY( yArgentina);
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
		int xSpain [] = {604,601,601,592,592,589,592,594,593,592,596,600,608,616,620,623,624,639,638,634,631,628,627,627,625,624,622,620,614,607,603};
		diviserTableauX( xSpain);
		int ySpain [] = {329,329,326,325,317,316,311,305,303,293,293,291,294,294,295,294,289,301,306,305,307,310,313,317,319,322,322,325,325,327,327};
		diviserTableauY( ySpain);
		int xFrance [] = {659,657,654,649,649,648,638,634,630,627,624,624,622,619,614,611,615,619,620,622,625,626,623,625,639,640,642,650,652,656};
        diviserTableauX( xFrance);
        int yFrance [] = {292,283,278,279,273,266,265,262,262,265,264,261,267,267,266,268,274,274,277,280,284,286,289,290,302,296,293,293,295,292};
        diviserTableauY( yFrance);
        int xUK [] = {610,614,617,621,631,633,634,629,627,623,620,616,616,619,619,615,615,612,608,606,606,609,609,606,599,596,594,594,594,591,590,595,598,604,608,610,609};
        diviserTableauX( xUK);
        int yUK [] = {256,256,255,255,252,248,244,243,237,232,228,227,223,218,217,215,211,211,213,214,220,219,222,226,230,234,237,241,245,247,249,249,246,242,236,240,247};
        diviserTableauY( yUK);
        int xItaly [] = {659,658,658,661,672,680,682,677,678,684,684,688,690,691,692,697,699,701,702,700,697,698,697,694,691,690,686,682,679,679,682,689,692,693,694,694,691,691,683,680,680,677,676,673,670,667,662};
        diviserTableauX( xItaly);
        int yItaly [] = {292,289,284,280,277,277,283,286,290,295,297,300,301,302,306,307,305,309,312,307,308,310,313,318,321,324,325,323,322,319,318,317,316,315,316,314,312,310,305,305,302,300,298,296,293,291,291};
        diviserTableauY( yItaly);
        int xNAfrica [] = {565,577,593,615,631,650,671,694,708,720,746,760,773,783,785,783,781,779,770,770,765,766,771,773,773,771,762,756,753,745,734,730,725,721,716,715,713,713,712,708,702,695,692,680,672,670,672,672,671,669,664,662,657,651,645,637,631,628,624,618,611,608,604,601,601,597,594,591,590,590,587,588,581,577,573,568,567};
        diviserTableauX( xNAfrica);
        int yNAfrica [] = {379,396,394,385,401,413,424,417,412,418,409,414,405,397,395,392,390,383,371,368,360,358,365,364,359,352,351,353,353,351,352,351,347,345,345,350,349,353,356,356,353,351,347,345,341,337,335,333,327,323,325,326,324,328,327,325,329,330,333,333,333,333,333,336,339,338,339,342,346,352,354,358,362,365,368,371,375};
        diviserTableauY( yNAfrica);
        int xSahel [] = {746,721,707,671,632,616,594,578,565,563,561,559,559,555,557,558,558,559,557,557,556,554,556,555,560,563,569,569,571,576,580,585,590,601,603,613,616,621,622,627,628,634,634,641,646,650,655,659,666,669,676,695,705,717,727,735,741,743,744,745,745};
        diviserTableauX( xSahel);
        int ySahel [] = {409,419,412,424,402,386,395,396,380,382,385,389,392,393,396,400,404,411,414,418,420,420,427,431,434,439,445,449,452,453,457,461,464,463,462,463,464,464,461,459,460,458,456,458,458,462,467,464,465,468,471,471,469,464,461,456,448,439,425,416,411};
        diviserTableauY( ySahel);
        int xEthiopia [] = {745,745,743,744,741,735,728,751,767,776,784,794,796,798,800,801,801,804,806,809,810,811,815,818,821,824,824,827,831,833,838,839,840,841,843,844,845,848,848,847,841,836,830,824,820,816,812,813,813,804,802,799,794,795,793,789,788,786,786,761};
        diviserTableauX( xEthiopia);
        int yEthiopia [] = {409,418,426,435,443,455,460,469,474,489,501,505,507,504,502,500,495,493,490,491,489,486,482,480,479,478,474,471,471,469,466,462,456,455,451,449,446,444,439,434,438,439,441,439,439,440,438,435,430,426,424,422,419,415,411,407,406,398,395,414};
        diviserTableauY( yEthiopia);
        int xCongo [] = {670,669,668,668,666,669,677,680,683,684,683,686,687,686,682,681,692,706,725,737,745,759,771,778,789,799,803,802,802,802,798,798,796,796,784,767,728,721,717,712,707,698,689,682,676};
        diviserTableauX( xCongo);
        int yCongo [] = {469,475,481,486,489,491,501,508,510,510,517,525,533,535,538,544,547,549,552,546,542,540,540,544,547,546,545,537,530,527,526,513,512,507,501,474,461,462,465,467,469,471,470,470,470};
        diviserTableauY( yCongo);
        int xSAfrica [] = {681,681,679,679,682,686,688,690,693,697,698,701,704,704,705,709,710,716,718,734,738,744,750,754,757,760,763,764,766,765,771,774,778,777,779,777,776,776,779,782,785,791,794,797,800,801,800,795,791,787,780,777,774,771,758,752,747,741,733,728,726};
        diviserTableauX( xSAfrica);
        int ySAfrica [] = {544,548,551,562,565,573,581,591,600,604,609,615,618,626,630,635,632,631,630,630,628,626,620,615,611,608,606,602,597,593,590,589,588,582,580,577,572,568,566,562,560,556,554,553,550,549,545,545,548,545,544,544,541,540,540,540,542,545,548,551,553};
        diviserTableauY( ySAfrica);
        int xArabia [] = {773,774,773,773,779,779,782,785,786,790,791,793,794,794,799,801,807,811,812,813,813,813,820,822,831,833,836,837,842,846,849,854,857,860,864,865,868,872,874,875,875,881,881,883,878,871,868,865,862,859,857,854,851,849,847,847,844,844,841,838,834,831,830,834,838,849,846,845,838,836,830,827,822,818,813,814,809,804,790,780,779,775,773,773,774};
        
        int yArabia [] = {354,356,362,364,366,369,376,380,383,383,387,391,393,398,398,404,410,413,418,423,427,432,432,430,429,428,427,424,424,422,419,417,415,414,413,410,410,407,404,400,397,396,393,390,386,383,379,374,376,380,383,383,382,383,383,376,375,380,378,372,366,360,357,356,356,343,328,325,324,323,320,314,315,313,320,323,322,321,324,324,331,337,343,350,358};
        
        int xCAsia [] = {};
        
        int yCAsia [] = {};
        
        int xIndia [] = {1020,1010,988,975,957,931,919,912,906,907,912,919,926,925,931,938,938,940,943,948,950,952,954,956,959,960,963,965,968,971,971,969,971,972,972,975,981,983,987,990,992,995,998,998,999,1000,1004,1010,1014,1013,1020};
        
        int yIndia [] = {365,353,348,345,345,345,348,356,363,375,379,386,390,396,397,393,402,408,415,425,431,436,437,441,447,451,450,447,445,443,442,438,435,432,419,418,415,413,408,401,401,401,401,397,395,391,393,391,391,387,382};
        
        int xKazakhstan [] = {};
        
        int yKazakhstan [] = {};
        
        int xChina [] = {};
        
        int yChina [] = {};
        
        int xSEAsia [] = {1077,1073,1070,1062,1062,1062,1052,1052,1052,1051,1048,1041,1035,1033,1031,1028,1024,1021,1019,1018,1014,1019,1021,1037,1048,1055,1057,1060,1064,1068,1071,1075,1081,1084,1090,1095,1098,1098,1099,1095,1087,1081,1080,1077,1072,1068,1065,1063,1061,1058,1058,1056,1055,1054,1058,1061,1061,1064,1067,1071,1073,1074,1077,1077};
        
        int ySEAsia [] = {479,479,476,470,463,457,452,445,436,429,416,418,419,410,404,399,399,395,391,389,386,382,366,369,375,384,390,393,399,401,402,404,406,411,416,420,423,431,437,440,443,448,443,441,437,432,432,431,429,429,435,437,440,442,447,451,454,457,457,463,467,472,476,479};
        
        int xNewZealand [] = {1345,1345,1347,1345,1341,1343,1344,1338,1332,1327,1320,1312,1310,1305,1306,1311,1315,1318,1322,1323,1324,1329,1333,1337,1339,1339,1346,1350,1354,1357,1361,1364,1365,1362,1358,1355,1353,1350,1348};
        
        int yNewZealand [] = {663,671,677,686,692,697,703,700,708,713,717,722,723,730,735,740,737,737,736,733,727,722,719,716,714,706,704,701,697,693,684,679,677,676,674,671,676,676,674};
        
        int xAustralia [] = {1217,1207,1202,1198,1195,1190,1188,1185,1177,1174,1170,1167,1162,1160,1154,1154,1142,1132,1125,1117,1114,1112,1111,1111,1113,1112,1114,1114,1114,1109,1111,1115,1120,1124,1128,1135,1140,1142,1146,1150,1153,1158,1164,1167,1173,1179,1188,1192,1194,1195,1200,1204,1207,1207,1206,1210,1211,1211,1217,1221,1225,1230,1234,1239,1243,1249,1252,1256,1258,1263,1264,1269,1273,1277,1279,1278,1274,1271,1271,1269,1263,1255,1255,1254,1254,1248,1247,1241,1240,1237,1237,1235,1231,1219,1215,1213,1213,1213,1216};
        
        int yAustralia [] = {538,538,536,533,539,540,543,549,548,547,550,551,555,559,559,566,571,574,577,582,586,592,601,608,615,625,628,631,636,643,646,647,648,644,644,643,643,645,644,638,634,635,635,632,634,631,635,638,646,652,648,644,641,649,653,660,664,666,675,676,675,674,678,676,675,674,669,665,659,653,647,643,636,630,624,606,597,595,590,588,576,570,565,561,553,549,540,535,540,548,557,560,566,559,556,553,550,547,543};
        
        int xIndonesia [] = {1040,1041,1054,1064,1070,1072,1083,1087,1093,1091,1102,1109,1118,1121,1119,1116,1108,1096,1090,1086,1085,1085,1080,1076,1073,1069,1064,1054,1051,1080,1057,1067,1075,1082,1094,1099,1101,1101,1100,1098,1101,1106,1110,1111,1116,1121,1128,1130,1132,1140,1141,1138,1135,1140,1142,1147,1154,1160,1163,1155,1154,1149,1150,1154,1155,1155,1174,1178,1191,1204,1214,1219,1239,1245,1252,1256,1259,1264,1266,1270,1273,1275,1265,1261,1258,1247,1245,1242,1226,1226,1224,1223,1220,1211,1215,1214,1209,1208,1204,1202,1199,1194,1192,1184,1181,1179,1173,1171,1165,1156,1158,1158,1155,1153,1153,1150,1149,1149,1148,1145,1144,1144,1146,1146,1148,1148,1148,1147,1139,1135,1131,1130,1128,1120,1118,1114,1111,1108,1106,1105,1105,1104,1101,1101,1097,1097,1095,1091,1087,1083,1079};
        
        int yIndonesia [] = {462,467,478,493,499,503,511,511,513,514,520,520,520,520,519,517,515,514,511,510,503,499,492,487,484,481,476,469,466,491,470,480,487,494,496,493,491,489,485,481,479,480,479,475,473,469,464,461,459,464,465,468,470,478,482,482,479,482,483,483,483,485,491,492,496,496,502,501,498,498,498,494,499,500,504,509,512,514,520,524,528,530,530,525,523,520,525,526,518,515,511,510,507,501,500,499,497,497,497,498,497,497,498,499,500,501,500,500,498,495,502,505,506,503,499,499,499,507,510,510,505,497,492,488,486,483,480,482,483,488,493,499,504,500,498,498,498,498,497,499,495,492,491,491,494,496,496,495,494,491,491};
        
        int xCenterEurope [] = {};
        
        int yCenterEurope [] = {};
        
        int xNorthenLands [] = {};
        
        int yNorthenLands [] = {};
        
        int xRussia [] = {};
        
        int yRussia [] = {};
        
        int xGreenland [] = {};
        
        int yGreenland [] = {};
        
        
       Argentina = new Polygon(xArgentina,yArgentina, xArgentina.length);
       CAmerica = new Polygon(xCAmerica, yCAmerica, yCAmerica.length);
       Mexico = new Polygon(xMexico, yMexico, xMexico.length);
       USA = new Polygon(xUSA, yUSA, xUSA.length);
       Brazil = new Polygon(xBrazil, yBrazil, xBrazil.length);
       Equateur = new Polygon(xEquateur, yEquateur, xEquateur.length);
       Madagascar = new Polygon(xMadagascar, yMadagascar, xMadagascar.length);
	   Canada = new Polygon(xCanada, yCanada, xCanada.length);
	   Spain = new Polygon(xSpain, ySpain, xSpain.length);
	   France = new Polygon(xFrance, yFrance, xFrance.length);
	   UK  = new Polygon(xUK, yUK, xUK.length);
	   Italy = new Polygon(xItaly, yItaly, xItaly.length);
	   NAfrica = new Polygon(xNAfrica, yNAfrica, xNAfrica.length);
	   Sahel = new Polygon(xSahel, ySahel, xSahel.length);
	   Ethiopia = new Polygon(xEthiopia, yEthiopia, xEthiopia.length);
	   Congo = new Polygon(xCongo, yCongo, xCongo.length);
	   SAfrica = new Polygon(xSAfrica, ySAfrica, xSAfrica.length);
	   Arabia = new Polygon(xArabia, yArabia, xArabia.length);
       CAsia = new Polygon(xCAsia, yCAsia, xCAsia.length);
       India = new Polygon(xIndia, yIndia, xIndia.length);
       Kazakhstan = new Polygon(xKazakhstan, yKazakhstan, xKazakhstan.length);
       China = new Polygon(xChina, yChina, xChina.length);
       SEAsia = new Polygon(xSEAsia, ySEAsia, xSEAsia.length);
       Indonesia = new Polygon(xIndonesia, yIndonesia, xIndonesia.length);
       Australia = new Polygon(xAustralia, yAustralia, xAustralia.length);
       NewZealand = new Polygon(xNewZealand, yNewZealand, xNewZealand.length);
       CenterEurope = new Polygon(xCenterEurope, yCenterEurope, xCenterEurope.length);
       Russia = new Polygon(xRussia, yRussia, xRussia.length);
       NorthenLands = new Polygon(xNorthenLands, yNorthenLands, xNorthenLands.length);
       Greenland = new Polygon(xGreenland, yGreenland, xGreenland.length);
	   
    }

    
    public void diviserTableauX( int [] t){                             //permet d'adapter le dessin des polygones à la taille de l'écran utilisé	
		int width =1366;
	    //int wEcran =(int)tailleFen.getWidth();
		
		for( int i = 0 ; i<t.length;i++){
			t[i] = (int)(t[i]*largeurEcran/width);
			
		}
		
	}
	public void diviserTableauY( int [] t){                             //permet d'adapter le dessin des polygones à la taille de l'écran utilisé	
	
		int height=768;
		//int hEcran = (int)tailleFen.getHeight();
		for( int i = 0 ; i<t.length;i++){
			t[i] = (int) (t[i]*hauteurEcran/height);
			
		}
		
	}

public void mousePressed(MouseEvent e) {
      Point me = e.getPoint();
      if( Brazil.contains(me)){
		  onAClique = 1;
		  System.out.println(hauteurEcran + "," +largeurEcran);
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
       onAClique = -1;
       
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
			
			J.add(new JButton("CA MARCHE"));
			//J.add(p);
			J.validate();
			J.repaint();
		
		
	}
    }

}

