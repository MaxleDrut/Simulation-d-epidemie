import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class FenetreCarte extends JFrame implements MouseListener{

	private JTextArea xZone = new JTextArea("ici les coordon�es en x");
	private JTextArea yZone = new JTextArea("ici les coordon�es en y");


    public FenetreCarte() {

        super("Corona Virus .INC");
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
		setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane( new Carte(this ,"bleu.jpg"));
		xZone.setBounds(20,2000,100,10);
		yZone.setBounds(20,20,100,10);
        this.setVisible(true);
    }





	public void mouseClicked(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}

	public void add( JButton j){

					this.add(j);

				}

	public void setCoordonateX( double x){
		String t = xZone.getText()+(int)x +",";
		xZone.setText(t);

	}
		public void setCoordonateY( double y){
		String t = yZone.getText() +(int)y+",";
		yZone.setText(t);

	}

	public void repeindre(){
		validate();
		repaint();
	}
	 public static void main(String[] args){
        FenetreCarte f = new FenetreCarte();
    }

}
