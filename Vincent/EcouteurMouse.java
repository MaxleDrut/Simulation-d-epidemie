
import java.awt.event.*;
import java.util.*;

public abstract class EcouteurMouse implements MouseListener{
	private FenetreJeu fen;
	private int action;
	
	public EcouteurMouse(FenetreJeu f ){
		
		fen=f;
		
	}
    //actionPerformed indique les instructions a executer au clic du bouton
    public void mouseClicked(MouseEvent e){
	
	if(e.getComponent().getClass().getName().equals("boutonLancement")){
		
		System.out.println("ccccc");
		
	}

		
		
	}
    }
