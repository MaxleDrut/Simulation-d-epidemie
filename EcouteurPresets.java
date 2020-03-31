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
		   case("Grippe espagnole") :
		   tab = this.attributsGrippeEspagnole();
		   break;
		   case("Peste bubonique") :
		   tab = this.attributsPesteBubonique();
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
		double[] param = {0.6,15,0.02};
		return param;
	}
	
	private double[] attributsGrippeEspagnole() {
		double[] param = {0.70,8,0.10};
		return param;
	}
	
	private double[] attributsPesteBubonique() {
		double[] param = {0.26,6,0.8};
		return param;
	}
}

