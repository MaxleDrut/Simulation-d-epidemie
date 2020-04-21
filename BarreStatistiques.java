import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
 
public class BarreStatistiques extends JPanel {
	
	double[] proportions = {1,0,0,0};
	
	
	public void paintComponent(Graphics g){
		int i=0;
		int seuilInf = 0;
		int seuilSup = (int) (this.getWidth()*proportions[i]);
		g.setColor(Color.GREEN);
		g.fillRect(seuilInf,0,seuilSup,this.getHeight());
		
		i++;
		seuilInf = seuilSup;
		seuilSup+=(int) (this.getWidth()*proportions[i]);
		g.setColor(Color.RED);
		g.fillRect(seuilInf,0,seuilSup,this.getHeight());
		
		i++;
		seuilInf = seuilSup;
		seuilSup+=(int) (this.getWidth()*proportions[i]);
		g.setColor(Color.BLUE);
		g.fillRect(seuilInf,0,seuilSup,this.getHeight());
		
		i++;
		seuilInf = seuilSup;
		seuilSup+=(int) (this.getWidth()*proportions[i]);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(seuilInf,0,seuilSup,this.getHeight());
	}
	
	public void setProportions(int[] vals) {
		int total=0;
		for(int i=0;i<proportions.length;i++) {
			total+=vals[i];
		}
		for(int i=0;i<proportions.length;i++) {
			proportions[i]=(double)(vals[i])/total;
		}
	}
                 
}
