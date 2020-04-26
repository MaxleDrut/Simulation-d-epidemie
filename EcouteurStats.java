import java.awt.event.*;
import java.util.*;

public class EcouteurStats implements ActionListener{
    private FenetreStats fenetre;
    private int choix;
    
    public EcouteurStats(FenetreStats f,int choix){
        this.fenetre=f;
        this.choix=choix;
    }
    public void actionPerformed(ActionEvent e){
		fenetre.setTemps(choix);
    }
}
