import java.awt.*;
import javax.swing.*;
import java.util.*;

public class FenetreInfection extends JFrame{
	private Pays pays;
	
	private Label titInfect;
	private TextField nbInfect;
	private JButton validerInfect;
	
	public FenetreInfection(Pays p) {
		super("Infecter le pays : "+p.getNomPays());
		pays = p;
	
		titInfect = new Label("Combien de personne infecter ?");
		nbInfect = new TextField();
		validerInfect = new JButton("Valider");
		validerInfect.addActionListener(new EcouteurNombreInfect(this, pays));
		
		this.add(titInfect, BorderLayout.NORTH);
		this.add(nbInfect, BorderLayout.CENTER);
		this.add(validerInfect, BorderLayout.SOUTH);
		
		this.setSize(500,150);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public int getValeurInfect() {
		try {
			return Integer.parseInt(nbInfect.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Ah ah ah ! Petit filou. Rentre un nombre Integer s'il te plait :-)");
			return 0;
		}
	}
}



