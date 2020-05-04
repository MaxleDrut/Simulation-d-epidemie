import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PolygonePays extends Polygon {
	
	private Pays p;
	
	public PolygonePays(int[] valX, int[] valY, int taille, Pays paysRens) {
		super(valX, valY, taille);
		p = paysRens;
	}
	//Constructeur vide pour simplement instancer
	public PolygonePays(){}
	
	public Pays getPays() {
		return p;
	}
}
