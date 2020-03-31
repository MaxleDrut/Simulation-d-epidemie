import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class EcouteurPresets implements ActionListener{
    private Simulation simu;
    private String nomPreset;

    public EcouteurPresets(Simulation s){
        simu = s;
    }

    public void actionPerformed(ActionEvent e){
       
       
    }
    
    //Dans l'ordre, les attributs sont : le facteur virulence, la durée de la maladie et la léthalité
    private double[] attributsCovid() {
		double[] param = {0.35,15,0.02};
		return param;
	}
	
	private double[] attributsGrippeEspagnole() {
		double[] param = {0.3,10,0.10};
		return param;
	}
}

