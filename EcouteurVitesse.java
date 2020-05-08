import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class EcouteurVitesse implements ActionListener{
    private Simulation simu;
    private String type;

	/*Il s'agit de l'écouteur des trois boutons de commande du timer.
	 *Ils sont identifiés par "acc", "ral" et "pause" au moment de l'ajout de l'ActionListener.*/
	 
    public EcouteurVitesse(Simulation s, String t){
        simu = s;
        type = t;
    }

	/*Au moment du clic, soit on a cliqué sur pause et donc on met en pause le timer,
	 *Soit on a cliqué sur accélérer et on dit alors de prendre un délai plus faible dans la table de délai (on renvoie -1),
	 *Soit on veut ralentir, donc on prend un délai plus élevé (en renvoie +1)*/
	 
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
