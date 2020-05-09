import java.awt.event.*;
import java.util.*;

public class EcouteurNombreInfect implements ActionListener{
    private FenetreInfection fen;
    private Pays pays;
    
    /*Ecouteur utilisé pour infecter une région cliqué : il contient le pays en question et la fenêtre d'infection.
     *Quand on va valider le nombre d'infectés dans la fenêtre d'infection, l'écouteur va infecter le pays en récupérant
     *le nombre renseigné dans le fenetre d'inction, puis va fermer cette dernière*/
     
    public EcouteurNombreInfect(FenetreInfection f, Pays p){
		fen=f;
		pays=p;
    }
    public void actionPerformed(ActionEvent e){
		pays.infectionInitale(fen.getValeurInfect());
		fen.dispose();
    }
}
