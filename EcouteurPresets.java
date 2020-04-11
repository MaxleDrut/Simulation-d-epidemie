import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class EcouteurPresets implements ActionListener{
    private Simulation simu;
    private String nomPreset;

    public EcouteurPresets(Simulation s, String n){
        simu = s;
        nomPreset = n;
    }

    public void actionPerformed(ActionEvent e){
       double tab[];
       switch(nomPreset) {
		   case("Coronavirus") :
		   tab = this.attributsCovid();
		   break;
		   case("Rougeole") :
		   tab = this.attributsRougeole();
		   break;
		   case("Variole") :
		   tab = this.attributsVariole();
		   break;
		   case("Polio") :
		   tab = this.attributsPolio();
		   break; 
		   case("Grippe saisonniere") :
		   tab = this.attributsGrippeSaisonniere();
		   break;
		   case("Grippe espagnole") :
		   tab = this.attributsGrippeEspagnole();
		   break;
		   case("Peste de Bombay") :
		   tab = this.attributsPesteBombay();
		   break;
		   default:
		   tab = new double[3];
	   }
	   simu.setVirulence(tab[0]);
	   simu.setDuree((int) (tab[1]));
	   simu.setLethalite(tab[2]);
	   simu.setNomVirus(nomPreset);
       
    }
    
    //Dans l'ordre, les attributs sont : le facteur virulence, la durée de la maladie et la léthalité
    private double[] attributsCovid() {
		double[] param = {0.72,15,0.045};
		return param;
	}
	
	private double[] attributsRougeole() {
		double[] param = {0.8,6,0.10};
		return param;
	}
	
	private double[] attributsVariole() {
		double[] param = {0.23,13,0.30};
		return param;
	}
	
	
	/*Petite note sur la Polio : la Polio provoque une paralysie irréversible dans 5% des cas, c'est de loin son pire symptôme.
	 *C'est cette paralysie qui peut devenir léthale mais est surtout très handicapante. 
	 *Je compte ici les cas PARALYSES comme des cas morts afin d'utiliser les paramètres de la simulation,
	 *Mais retenez que la Polio est très très rarement mortelle.*/
	
	private double[] attributsPolio() {
		double[] param = {0.36,14,0.05};
		return param;
		
	}
	
	private double[] attributsGrippeSaisonniere() {
		double[] param = {0.6,6,0.002};
		return param;
	}
	
	private double[] attributsGrippeEspagnole() {
		double[] param = {0.70,8,0.15};
		return param;
	}
	
	private double[] attributsPesteBombay() {
		double[] param = {0.27,56,0.65};
		return param;
	}
}

