import java.util.*;

public class Simulation {
	
	private static Virus maladie;
	//private static Region zone;
	protected static int tempsSimu;
	
	private static long delay = 1000; 
	private static Timer temps = new Timer();
	private static TimerJeu jeu = new TimerJeu();
	
	
	//TODO : main va se charger de créer le virus, de créer la région, d'infecter les premières zones et d'instancier le JFrame de la simulation
	public static void main (String[] args) {
		tempsSimu = 0;
		changerVit(1000);
		
	}
	
	
	public static void pauseTimer() {
		
		
	}
	
	public static void changerVit(int v) { 
		delay = v;
		temps.cancel();
		temps.purge();
		
		temps = new Timer();
		jeu = new TimerJeu();
		temps.scheduleAtFixedRate(jeu, 0, delay);
		
		
	}
	
	
	/* Une fois un certan temps écoulé avec les timers,
	 * la simu va incrémenter de 1 la variable de temps, et a exécuter les évènements
	 * liés à l'avancement dans le temps (infection, deplacements, mutation)
	 */
	 
	public static void jourSuivant() {
		tempsSimu++;
		System.out.println(tempsSimu);
		
	} 
	
}

