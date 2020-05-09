import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class EcouteurCreation implements ActionListener{
    private Simulation simu;

	/*Un écouteur banal : il demande à la simu de lancer sa méthode creerVirus() quand
	 *On clique sur le bouton "Créer le virus"...*/
	 
    public EcouteurCreation(Simulation s){
        simu = s;
    }

    public void actionPerformed(ActionEvent e){
       simu.creerVirus();
   }
}

