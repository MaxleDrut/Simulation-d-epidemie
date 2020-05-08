import java.awt.event.*;
import java.util.*;

public class EcouteurValiderInfect implements ActionListener{
    private Simulation simu;
    
    /*Du même niveau que l'écouteur Création : on clique sur Valider infection,
     *Et ça dit à Simulation de valider l'infection. yay.*/
     
    public EcouteurValiderInfect(Simulation f){
        this.simu=f;
    }
    public void actionPerformed(ActionEvent e){
		simu.validerInfection();
    }
}
