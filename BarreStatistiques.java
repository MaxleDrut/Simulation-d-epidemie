import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
 
public class BarreStatistiques extends JPanel {
	
	/*Proportions est un tableau de double dont la somme des 4 composantes fait 1.
	 *Cela représente dans l'ordre la part de sains, infectés, rétablis et morts de
	 *La zone visualisée */
	double[] proportions = {1,0,0,0};
	
	/*On va créer 4 rectangles qui se suivent, dont la longueur va dépendre de leur proportion
	 *Ainsi, on multiplie la longueur de l'écran par la proportion de sains/infectés/... et ça donne
	 *La longueur du rectangle en question.
	 *SeuilInf correspond au début du rectangle, et sup à la fin du rectangle.
	 *Ainsi, on utilise seuilInf pour sauvegarder où s'est terminé le rectangle précédent
	 * (ex : pour tracer le rectangle vert sur 120 pixels, seuilInf = 0 et seuilSup = 120.
	 * (donc pour tracer le rouge sur 90 pixels, seuilInf = 120 et seuilSup = 120 + 90)*/
	 
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
	
	public void setProportions(long[] vals) {
		long total=0;
		for(int i=0;i<proportions.length;i++) {
			total+=vals[i];
		}
		for(int i=0;i<proportions.length;i++) {
			proportions[i]=(double)(vals[i])/total;
		}
	}
                 
}
