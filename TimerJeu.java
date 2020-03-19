import java.util.*;

public class TimerJeu extends TimerTask {
	
	@Override 
	public void run() {
		Simulation.jourSuivant();
	}
}

