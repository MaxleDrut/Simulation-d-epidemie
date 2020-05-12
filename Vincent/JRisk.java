import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.Toolkit;
import java.util.*;
public class JRisk {

    private JFrame mainMap;
    private Polygon poly;

    public JRisk() {

        initComponents();

    }

    private void initComponents() {

        mainMap = new JFrame();
       // mainMap.setResizable(false);
       
       
		//mainMap.setSize( );
        mainMap.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int xAfrica[] = {705, 678, 681, 667, 658, 594, 555, 556, 604, 670, 764, 816, 848, 798, 804, 775, 762, 714};
        int yAfrica[] = {631, 558, 509, 488, 466, 464, 425, 394, 332, 325, 354, 441, 435, 503, 550, 571, 608, 632};
		
		int xSAmerica [] = {351, 332, 328, 327, 307, 275, 322, 363, 398, 474, 467, 451, 398, 366, 355 };
		int ySAmerica [] = {733, 711, 671, 558, 549, 502, 432, 438, 459, 508, 533, 577, 635, 666, 730};
        poly = new Polygon(xAfrica, yAfrica, xAfrica.length);
        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GREEN);
                
				
                g.drawPolygon(poly);
                g.fillPolygon(poly);
            }

       
        };
        mainMap.add(p);
        mainMap.pack(); 
    
        mainMap.setExtendedState(Frame.MAXIMIZED_BOTH);

        mainMap.setVisible(true);

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JRisk();
            }
        });
    }
}
