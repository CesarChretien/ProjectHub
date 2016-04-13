package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import Sprite.Spaceship;
import Sprite.Sprite;

public class Space extends JFrame implements KeyListener{

	private static final long serialVersionUID = 1L;
	//Spaceship(xloc, yloc, SHAPE, xwidth, ywidth, angle, fuel, travelangle)
    Spaceship ship = new Spaceship(300, 300, Sprite.TRIANGLE, 32, 32, 0, 100, 0);
	
	public Space() {
		this.setLocation(100, 100);
		this.setSize(800, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addKeyListener(this);
		
	}
	
	public void paint(Graphics g) {
	    g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.red);
	    g.fillOval(20, 50, 100, 100);
	    g.setColor(Color.green);
	    System.out.println(ship.getXlist()[0] + " " +  ship.getXlist()[1] + " " + ship.getXlist()[2]);
	    System.out.println(ship.getYlist()[0] + " " +  ship.getYlist()[1] + " " + ship.getYlist()[2]);
	    System.out.println("travelangle: " + ship.getTravelangle());
	    System.out.println("shipangle: " + ship.getAngle());
	    g.fillPolygon(ship.getXlist(), ship.getYlist(), ship.getYlist().length);
	}

	@Override
	public void keyPressed(KeyEvent e) {
	    int keyCode = e.getKeyCode();
	    switch( keyCode ) { 
	        case KeyEvent.VK_UP: ship.move(); this.repaint();
	        break; 
	        case KeyEvent.VK_DOWN: ship.slow(); this.repaint();
	        break; 
	        case KeyEvent.VK_LEFT: ship.rotateLeft(); this.repaint();
		    break;
	        case KeyEvent.VK_RIGHT : ship.rotateRight(); this.repaint();
	        break;
	     }
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}