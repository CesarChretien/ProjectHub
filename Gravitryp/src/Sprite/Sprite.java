package Sprite;

import Sprite.Coordinate;
import Sprite.Point;
import Sprite.Points;

public class Sprite {


	private Points p; //set of points describing the sprite
	private double angle; //angle of the sprite
	private String shape; //shape of the hitbox of the sprite
	//private boolean col; //checks if this sprite collides with another sprite
	
	//possible shapes
	protected static final String CIRCLE = "circle";
	protected static final String SQUARE = "square";
	public static final String TRIANGLE = "triangle";
	
	//callable constructors
	public Sprite(int xloc, int yloc, int xwidth, int ywidth) {
		this(xloc, yloc, SQUARE, xwidth, ywidth);
	}
	public Sprite(int xloc, int yloc, String shape, int xwidth, int ywidth) {
		this.setPoints(new Points(new Coordinate(xloc, yloc), shape, xwidth, ywidth));
		this.setAngle(0);
	}
	public Sprite(int xloc, int yloc, int xwidth, int ywidth, double angle) {
		this(xloc, yloc, SQUARE, xwidth, ywidth, angle);
	}
	public Sprite(int xloc, int yloc, String shape, int xwidth, int ywidth, double angle) {
		this.setPoints(new Points(new Coordinate(xloc,yloc), shape, xwidth, ywidth));
		this.setAngle(angle);
	}
	
	//public boolean hasCollision(Sprite spr) {	}
	
	void setRealAngle(double angle) {
		this.angle = angle;
	}
	
	public int[] getXlist() {
		int[] x = new int[this.getPoints().length];
		for(int i = 0; i < this.getPoints().length; i++) {
			x[i] = (int) Math.round(this.getPoints()[i].getX());
		}
		return x;
	}
	
	public int[] getYlist() {
		int[] y = new int[this.getPoints().length];
		for(int i = 0; i < this.getPoints().length; i++) {
			y[i] = (int) Math.round(this.getPoints()[i].getY());
		}
		return y;
	}
	
	public double getXCoord() {
		return this.getPoints()[0].getCoord().getX();
	}
	
	public double getYCoord() {
		return this.getPoints()[0].getCoord().getY();
	}
	

	private void setAngle(double angle) { 
		for(Point points: this.getPoints()) {
			points.setAngle((points.getAngle() + angle) % 360);
		}
	}
	
	//setters
	private void setPoints(Points p) { this.p = p; }
	
	//getters
	public Point[] getPoints(){ return this.p.getPoints(); }
	public Points getPointsObj() { return this.p; }
	public double getAngle(){ return this.angle; }
	public String getShape(){ return this.shape; }
}