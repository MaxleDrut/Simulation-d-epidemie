import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
 
public class BarreStatistiques extends JPanel {
	
	double[] proportions;
	
	public void paintComponent(Graphics g){
		if(proportions==null) {
			double[] proportions = {1.0,0.0,0.0,0.0};
		}
		g.setColor(Color.GREEN);
		g.fillRect(0,0,(int) (this.getWidth()*proportions[0]),this.getHeight());
		g.setColor(Color.BLUE);
		g.fillRect(this.getWidth()/2,0,this.getWidth(),this.getHeight());
	}
	
	public void setProportions(double[] vals) {
		proportions = vals;
	}
                 
}
