import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class EcouteurNomRandom implements ActionListener{
    private Simulation simu;
	private ArrayList<String> dicoNoms;

    public EcouteurNomRandom(Simulation s){
        simu = s;
        creerDico();
    }

    public void actionPerformed(ActionEvent e){
		int pos = (int) (Math.random()*dicoNoms.size());
		simu.setNomVirus(dicoNoms.get(pos));
    }
    
    /*La méthode va récupérer les noms de virus dans le fichier dictionnaire.
     *Cela permet de gérer les noms beaucoup plus facilement qu'en créant la liste dans la classe.
     *Le code est librement repris de celui présent sur le moodle algo Amerinsa S4 */
    private void creerDico() {
		dicoNoms = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("Dictionnaire noms virus.txt")));
			String mot;
			while((mot = br.readLine()) != null) {
				dicoNoms.add(mot);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

