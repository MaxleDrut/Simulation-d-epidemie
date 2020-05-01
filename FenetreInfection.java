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
	
		titInfect = new Label("Combien de personnes infecter ? (Renseigner max pour infecter tout le pays)");
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
		if(nbInfect.getText().equals("max") || nbInfect.getText().equals("Max") || nbInfect.getText().equals("MAX")) { //Prise en compte des trois écritures possibles de max
			System.out.println("Bloup");
			return pays.getSains();
		}
		try { 
			return Integer.parseInt(nbInfect.getText());
		} catch (NumberFormatException e) { //Si la valeur renseignée n'est ni un nombre ni max, alors il s'agit d'une erreur et on ne renvoie rien !
			JOptionPane.showMessageDialog(this, "Ah ah ah ! Petit filou. Rentre un nombre Integer s'il te plait :-)");
			return 0;
		}
	}
}



