package Map;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

import Sprite.*;

public class Space extends JFrame {

	private static final long serialVersionUID = 1L;

	public Space() {
		this.setLocation(100, 100);
		this.setSize(800, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	public void paint(Graphics g) {

	    g.setColor(Color.red);
	    g.fillOval(20, 50, 100, 100);
	   
	    g.setColor(Color.green);
	    Spaceship ship = new Spaceship(300, 300, Sprite.TRIANGLE, 32, 32, 100, 100, 0);
	    System.out.println(ship.getXlist()[0] + " " +  ship.getXlist()[1] + " " + ship.getXlist()[2]);
	    System.out.println(ship.getYlist()[0] + " " +  ship.getYlist()[1] + " " + ship.getYlist()[2]);
	    g.fillPolygon(ship.getXlist(), ship.getYlist(), ship.getYlist().length);
	}
}
