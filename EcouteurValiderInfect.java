import java.awt.event.*;
import java.util.*;

public class EcouteurValiderInfect implements ActionListener{
    private Simulation simu;
    
    public EcouteurValiderInfect(Simulation f){
        this.simu=f;
    }
    public void actionPerformed(ActionEvent e){
		simu.validerInfection();
    }
}
