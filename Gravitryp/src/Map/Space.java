package Map;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Space extends JFrame {

	public Space() {
		this.setLocation(100, 100);
		this.setSize(800, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	public void paint(Graphics g) {

	    g.setColor(Color.red);
	    g.fillOval(20, 50, 100, 100);
	    g.setColor(Color.blue);
	    g.fillRect(100, 100, 100, 200);
	    g.setColor(Color.green);
	    g.fillPolygon(new int[]{100, 200, 300}, new int[]{200,100,300},3);
	}
}
