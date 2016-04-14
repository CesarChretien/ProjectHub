package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Sprite.Spaceship;
import Sprite.Sprite;

public class Gravitryp extends JFrame implements KeyListener {
	
	 /**
	  * 
	  */
	
	BufferedImage img= null;{
	try {
		    img = ImageIO.read(new File("./img/SpaceShipStill.png"));
	}
	catch (IOException ie) {
		
	}}
	
	private static final long serialVersionUID = 1L;
	//Spaceship(xloc, yloc, SHAPE, xwidth, ywidth, angle, fuel, travelangle)
    Spaceship ship = new Spaceship(300, 300, Sprite.TRIANGLE, img.getWidth(), img.getHeight(), 0, 100, 0);
    boolean game_runs = false;
    int FPS = 60;
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    AffineTransform identity = new AffineTransform();
    
	public Gravitryp () {
	    Space space = new Space();
	    this.setTitle("Gravitryp");
		this.add(space);
		
		this.pack();
		this.setLocation(100, 100);
		this.setSize(800, 600);
		this.setVisible(true);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addKeyListener(this);
		
		game_runs = true;
		while( game_runs ) {
			try {
				if(up) {
					ship.move();
				}
				if(down) {
					ship.slow();
				}
				if(left) {
					ship.rotateLeft();
				}
				if(right) {
					ship.rotateRight();
				}
				ship.updateLocation();
				this.repaint();
				Thread.sleep(Math.round(1000.0/FPS));
			}
			catch (InterruptedException ie) {
				game_runs = false;
			}
		}	
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
	    int keyCode = e.getKeyCode();
	    switch (keyCode) { 
		    case KeyEvent.VK_UP: up = true; break;
		    case KeyEvent.VK_DOWN: down = true; break;
		    case KeyEvent.VK_LEFT: left = true; break;
		    case KeyEvent.VK_RIGHT: right = true; break;
	    }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) { 
	    	case KeyEvent.VK_UP: up = false; break;
	    	case KeyEvent.VK_DOWN: down = false; break;
	    	case KeyEvent.VK_LEFT: left = false; break;
	    	case KeyEvent.VK_RIGHT: right = false; break;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	class Space extends JPanel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void paint(Graphics g) {
			g.setColor(Color.green);
	    	g.fillPolygon(ship.getXlist(), ship.getYlist(), ship.getYlist().length);
	    	//g.drawImage(img, (int)(Math.round(ship.getPoints()[0].getCoord().getX()) - 0.5*img.getWidth()), (int)(Math.round(ship.getPoints()[0].getCoord().getY()) - 0.5*img.getHeight()), null);
	    	Graphics2D g2d = (Graphics2D)g;
	    	AffineTransform trans = new AffineTransform();
	    	trans.setTransform(identity);
	    	trans.setToTranslation((ship.getPoints()[0].getCoord().getX() - 0.5*img.getWidth()), (ship.getPoints()[0].getCoord().getY() - 0.5*img.getHeight()));
	    	trans.rotate( -Math.toRadians(ship.getAngle()), 0.5*img.getWidth(), 0.5*img.getHeight() );
	    	//g2d.drawImage(img, (int)(Math.round(ship.getPoints()[0].getCoord().getX()) - 0.5*img.getWidth()), (int)(Math.round(ship.getPoints()[0].getCoord().getY()) - 0.5*img.getHeight()), null);
	    	g2d.drawImage(img, trans, this);
		}
	}
}