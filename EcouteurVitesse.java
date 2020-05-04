import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class EcouteurVitesse implements ActionListener{
    private Simulation simu;
    private String type;

    public EcouteurVitesse(Simulation s, String t){
        simu = s;
        type = t;
    }

    public void actionPerformed(ActionEvent e){
       switch(type) {
		   case ("pause"):
		   simu.pauseTimer();
		   break;
		   case ("acc"):
		   simu.changerVitesse(-1);
		   break;
		   case ("ral"):
		   simu.changerVitesse(1);
		   break;
		   default:
	   }
       
    }
}
