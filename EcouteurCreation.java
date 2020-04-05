import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class EcouteurCreation implements ActionListener{
    private Simulation simu;

    public EcouteurCreation(Simulation s){
        simu = s;
    }

    public void actionPerformed(ActionEvent e){
       simu.creerVirus();
   }
}

