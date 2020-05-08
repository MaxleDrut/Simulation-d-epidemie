import java.awt.event.*;
import java.util.*;

public class EcouteurStats implements ActionListener{
    private FenetreStats fenetre;
    private int choix;
    
    //Cet écouteur permet de cliquer sur les boutons pour choisir l'intervalle de temps affiché dans la fenêtre stats. 
    public EcouteurStats(FenetreStats f,int choix){
        this.fenetre=f;
        this.choix=choix;
    }
    
    //Au clic, on va définir un intervalle de temps pour la fenêtre. Si on essaie de cliquer au jour 1 (donc rien à afficher), on affiche un message d'erreur
    public void actionPerformed(ActionEvent e){
		try{
		fenetre.setTemps(choix);
		}
		catch(NoSuchElementException r){
			System.out.println("Rien a afficher pour l'instant");
		}	
    }
}
