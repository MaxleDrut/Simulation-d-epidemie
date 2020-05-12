import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;

class AfficheImage extends JPanel
{
Image im;

AfficheImage(String s)
{
im = getToolkit().getImage(s);
}

public void paintComponent(Graphics g)
{
super.paintComponent(g);
g.drawImage(eau, 0, 0, getWidth(), getHeight(), this);
}
}

