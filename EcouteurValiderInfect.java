import java.awt.event.*;
import java.util.*;

public class EcouteurValiderInfect implements ActionListener{
    private Simulation fen;
    
    public EcouteurValiderInfect(Simulation f){
        this.fen=f;
    }
    public void actionPerformed(ActionEvent e){
		fen.validerInfection();
    }
}
