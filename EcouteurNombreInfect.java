import java.awt.event.*;
import java.util.*;

public class EcouteurNombreInfect implements ActionListener{
    private FenetreInfection fen;
    private Pays pays;
    
    public EcouteurNombreInfect(FenetreInfection f, Pays p){
		fen=f;
		pays=p;
    }
    public void actionPerformed(ActionEvent e){
		pays.infectionInitale(fen.getValeurInfect());
		fen.dispose();
    }
}
