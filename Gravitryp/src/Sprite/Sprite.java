package Sprite;

public class Sprite {


	private Points p; //set of points describing the sprite
	private double angle; //angle of the sprite
	private String shape; //shape of the hitbox of the sprite
	private boolean col; //checks if this sprite collides with another sprite
	
	//possible shapes
	protected static final String CIRCLE = "circle";
	protected static final String SQUARE = "square";
	protected static final String TRIANGLE = "triangle";
	
	//callable constructors
	public Sprite(double xloc, double yloc, double xwidth, double ywidth) {
		this(new Points(new Coordinate(xloc,yloc), SQUARE, xwidth, ywidth), 0);
	}
	public Sprite(double xloc, double yloc, String shape, double xwidth, double ywidth) {
		this(new Points(new Coordinate(xloc,yloc), shape, xwidth, ywidth), 0);
	}
	public Sprite(double xloc, double yloc, double xwidth, double ywidth, double angle) {
		this(new Points(new Coordinate(xloc,yloc), SQUARE, xwidth, ywidth), angle);
	}
	public Sprite(double xloc, double yloc, String shape, double xwidth, double ywidth, double angle) {
		this(new Points(new Coordinate(xloc,yloc), shape, xwidth, ywidth), angle);
	}
	
	//actual constructor
	private Sprite(Points p, double angle) {
		this.setPoints(p);
		this.setAngle(angle);
	}
	
	//public boolean hasCollision(Sprite spr) {	}
	public void rotateLeft() {
		for(Point points: this.getPoints()) {
			points.setAngle((points.getAngle() + 5) % 360);
		}
	}
	
	public void rotateRight() {
		for(Point points: this.getPoints()) {
			points.setAngle((points.getAngle() + 355) % 360);
		}
	}
	
	//setters
	private void setPoints(Points p) { this.p = p; }
	private void setAngle(double angle) { this.angle = angle; }
	
	//getters
	public Point[] getPoints(){ return this.p.getPoints(); }
	public double getAngle(){ return this.angle; }
	public String getShape(){ return this.shape; }
	
	//class Coordinate describing (x,y) coordinates
	static class Coordinate {
		private double x;
		private double y;
		
		private Coordinate(double x, double y) {
			setX(this.x);
			setY(this.y);
		}
		private void setX(double x){ this.x = x; }
		private void setY(double y){ this.y = y; }
	}
	
	//A point depending on a centre Coordinate, distance from that Coordinate and it's angle
	static class Point {
		private Coordinate coord;
		private double angle;
		private double distance;
		
		private Point(Coordinate coord, double angle, double distance) {
			this.setCoord(coord);
			this.setAngle(angle);
			this.setDistance(distance);
		}
		
		private void setCoord(Coordinate coord) {
			this.coord = coord;
		}
		private void setAngle(double angle) {
			this.angle = angle;
		}
		private void setDistance(double distance) {
			this.distance = distance;
		}
		protected double getAngle() {
			return this.angle;
		}
		
	}
	
	//class Points which is basically a list of Coordinates
	static class Points {
		private Point[] points;
		private double radius = 0; //only not 0 if shape is a circle
		
		protected static final String CIRCLE = "circle";
		protected static final String SQUARE = "square";
		protected static final String TRIANGLE = "triangle";
		
		private Points(Coordinate coord, String shape, double xwidth, double ywidth) {
			if(shape.equals(CIRCLE)) {
				setRadius(xwidth, ywidth);
				points = new Point[1];
				points[0] = new Point(coord, 0, 0);
			}
			else if (shape.equals(SQUARE)) {
				double r = 0.5*Math.sqrt(Math.pow(xwidth, 2) + Math.pow(ywidth, 2));
				points = new Point[4];
				points[0] = new Point(coord, 45, r);
				points[1] = new Point(coord, 135, r);
				points[2] = new Point(coord, 225, r);
				points[3] = new Point(coord, 315, r);
			}
			else if (shape.equals(TRIANGLE)) {
				double r = 0.5*Math.sqrt(Math.pow(xwidth, 2) + Math.pow(ywidth, 2));
				points = new Point[3];
				points[0] = new Point(coord, 0, r);
				points[1] = new Point(coord, 135, r);
				points[2] = new Point(coord, 225, r);
			}
		}
		
		public Point[] getPoints() {
			return this.points;
		}
		private void setRadius(double xwidth, double ywidth) {
			this.radius = 0.5*Math.sqrt(Math.pow(xwidth, 2) + Math.pow(ywidth, 2));
		}
		public double getRadius() {
			return this.radius;
		}
	}
}