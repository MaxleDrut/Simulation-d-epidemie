import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.LinkedList;

public class EcouteurNomRandom implements ActionListener{
    private Simulation simu;
	private LinkedList<String> dicoNoms;

	/*Cet écouteur permet de tirer au sort un nom pour le virus quand on clique sur le bouton aléatoire au moment de la création.
	 *Au moment de la construction, on va chercher la liste de noms dans le fichier Txt et on l'ajoute à une LinkedList de nom.
	 * 
	 *Pourquoi une LinkedList ? Parce que la méthode creerDico ajoute les Strings un par un, donc avec une Linked on a une
	 *Complexité de 1 par ajout et de n pour l'ArrayList. Certes, au moment du tirage du nom aléatoire, ces chiffres seront échangés, mais vu
	 *Qu'il y a une cinquaintaine de noms à récupérer et que l'on ne va pas effectuer 50 tirages aléatoire à chaquue exécution,
	 *La LinkedList est alors plus légère*/
	 
    public EcouteurNomRandom(Simulation s){
        simu = s;
        creerDico();
    }

	//Au moment du clic, on va tirer une position aléatoire parmi la liste de nom, puis afficher ce nom dans la Simulation.
    public void actionPerformed(ActionEvent e){
		int pos = (int) (Math.random()*dicoNoms.size());
		simu.setNomVirus(dicoNoms.get(pos));
    }
    
    /*La méthode va récupérer les noms de virus dans le fichier dictionnaire grâce à un BufferedReader.
     *Cela permet de gérer les noms beaucoup plus facilement qu'en créant la liste dans la classe.
     *Le code est librement repris de celui présent sur le moodle algo Amerinsa S4 */
    private void creerDico() {
		dicoNoms = new LinkedList<String>();
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

