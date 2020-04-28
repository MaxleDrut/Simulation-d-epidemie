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
		
		this.setSize(500,200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public int getValeurInfect() {
		return Integer.parseInt(nbInfect.getText());
	}
}



