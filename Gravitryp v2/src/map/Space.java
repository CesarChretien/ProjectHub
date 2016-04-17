package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.Planet;
import objects.Point;
import objects.Ship;
import objects.Sprite;


public class Space extends JFrame implements KeyListener {
		
	 /**
	  * 
	  */
	
	//BufferedImage img= null;{
	//try {
	//	    img = ImageIO.read(new File("./img/SpaceShipStill.png"));
	//}
	//catch (IOException ie) {
	//	
	//}}
	
	private static final long serialVersionUID = 1L;
	//Spaceship(xloc, yloc, SHAPE, xwidth, ywidth, angle, fuel, travelangle)
    Ship ship = new Ship(new Point(300, 300), Sprite.TRIANGLE, 64, 32, new Point(1, 0), new Point(0, 0));
    int[] x = new int[ship.getHitbox().length];
    int[] y = new int[ship.getHitbox().length];
    
    Planet earth = new Planet(new Point(500, 600), 64, 2.5);
    Planet mars = new Planet(new Point(800,100), 32, 1);
    Planet[] planets = {earth, mars};
    
    
    boolean game_runs = false;
    int FPS = 60;
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    AffineTransform identity = new AffineTransform();
    
    final int game_width = 1000;
    final int game_height = 800;
    
	public Space () {
	    Map map = new Map();
	    this.setTitle("Gravitryp");
		this.add(map);
		
		this.pack();
		this.setLocation(300, 50);
		this.setSize(game_width, game_height);
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
				ship.applyGravity(planets);
				ship.update(this.getWidth(), this.getHeight());
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
	
	class Map extends JPanel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void paint(Graphics g) {
			for(int i = 0; i < x.length; i++) {
		    	x[i] = (int) Math.round(ship.getHitbox()[i].getX());
		    	y[i] = (int) Math.round(ship.getHitbox()[i].getY());
		    }
			//create spaceship (hitbox)
			g.setColor(Color.green);
	    	g.fillPolygon(x, y, ship.getHitbox().length);
	    	
	    	//draw planets
	    	g.setColor(Color.red);
	    	for(Planet p : planets)
	    	g.fillOval((int)Math.round(p.getCentre().getX() - p.getRadius()), (int)Math.round(p.getCentre().getY() - p.getRadius()), (int)Math.round(2.0*p.getRadius()), (int)Math.round(2.0*p.getRadius()));
	    	
	    	//show stats
	    	g.setColor(Color.black);
	    	g.drawString("Ship X location: " + ship.getCentre().getX(), 10, 10);
	    	g.drawString("Ship Y location: " + ship.getCentre().getY(), 10, 30);
	    	g.drawString("Ship direction vector: (" + ship.getDirection().getX() + ", " + ship.getDirection().getY() + ")", 10, 50);
	    	g.drawString("Ship velocity vector: (" + ship.getVelocity().getX() + ", " + ship.getVelocity().getY() + ")", 10, 70);
	    	g.drawString("Ship direction vector length: " + ship.getDirection().length(), 10, 90);
	    	g.drawString("Ship velocity vector length: " + ship.getVelocity().length(), 10, 110);
	    	//g.fillOval((int)Math.round(earth.getXCoord() - earth.getRadius()), (int)Math.round(earth.getYCoord() - earth.getRadius()), (int)Math.round(2*earth.getRadius()), (int)Math.round(2*earth.getRadius()));
	    	
	    	//Graphics2D g2d = (Graphics2D)g;
	    	//AffineTransform trans = new AffineTransform();
	    	//trans.setTransform(identity);
	    	//trans.setToTranslation((ship.getXCoord() - 0.5*img.getWidth()), (ship.getYCoord() - 0.5*img.getHeight()));
	    	//trans.rotate( -Math.toRadians(ship.getAngle()), 0.5*img.getWidth(), 0.5*img.getHeight() );
	    	//g2d.drawImage(img, trans, this);
		}
	}
}
