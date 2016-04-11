package Sprite;

public class Sprite {

	private double xloc; //x-coord of centre of sprite
	private double xbox; //width of sprite
	private double yloc; //y-coord of centre of sprite
	private double ybox; //height of the sprite
	private Points p;
	private double angle; //angle of the sprite
	private String shape; //shape of the hitbox of the sprite
	private boolean col; //checks if this sprite collides with another sprite
	
	//possible shapes
	protected static final String CIRCLE = "circle";
	protected static final String SQUARE = "square";
	protected static final String TRIANGLE = "triangle";
	
	//constructors
	private Sprite(double xloc, double yloc, double xwidth, double ywidth) {
		this(new Points(new Coordinate(xloc,yloc), SQUARE, xwidth, ywidth), 0);
	}
	private Sprite(Coordinate coord, double xwidth, double ywidth) {
		
	}
	
	private Sprite(Points p, double angle) {
		this.setXbox(xbox);
		this.setYbox(ybox);
		this.setAngle(angle);
		this.setShape(shape);
	}
	
	private Sprite(Points p) {
		this(p, 0);
	}
	
	public boolean hasCollision(Sprite spr) {
		if(this.getShape().equals(CIRCLE) && spr.getShape().equals(CIRCLE))
			return true;
		return false;
	}
	
	//setters
	public void setXloc(double xloc){ this.xloc = xloc;	}
	public void setYloc(double yloc){ this.yloc = yloc;	}
	public void setXbox(double xbox){ this.xbox = xbox;	}
	public void setYbox(double ybox){ this.ybox = ybox;	}
	public void setAngle(double angle) { this.angle = angle; }
	public void setShape(String shape) { this.shape = shape; }
	
	//getters
	public double getXloc(){ return this.xloc; }
	public double getYloc(){ return this.yloc; }
	public double getXbox(){ return this.xbox; }
	public double getYbox(){ return this.ybox; }
	public double getangle(){ return this.angle; }
	public String getShape(){ return this.shape; }
	
	static class Coordinate {
		private double x;
		private double y;
		
		private Coordinate(double x, double y) {
			setX(this.x);
			setY(this.y);
		}
		protected void setX(double x){ this.x = x; }
		private void setY(double y){ this.y = y; }
		
		private double getX(){ return this.x; }
		private double getY(){ return this.y; }
	}
	
	static class Points {
		private Coordinate[] coords;
		private double radius;
		
		protected static final String CIRCLE = "circle";
		protected static final String SQUARE = "square";
		protected static final String TRIANGLE = "triangle";
		
		private Points(Coordinate coord, String shape, double xwidth, double ywidth) {
			if(shape.equals(CIRCLE)) {
				setRadius(xwidth, ywidth);
				coords = new Coordinate[1];
				coords[0].setX(coord.x); coords[0].setY(coord.y);
			}
			else if (shape.equals(SQUARE)) {
				coords = new Coordinate[4];
				coords[0].setX(coord.x - xwidth/2); coords[0].setY(coord.y + ywidth/2);
				coords[1].setX(coord.x + xwidth/2); coords[1].setY(coord.y + ywidth/2);
				coords[2].setX(coord.x + xwidth/2); coords[2].setY(coord.y - ywidth/2);
				coords[3].setX(coord.x - xwidth/2); coords[3].setY(coord.y - ywidth/2);
			}
			else if (shape.equals(TRIANGLE)) {
				coords = new Coordinate[3];
				coords[0].setX(coord.x - xwidth/2); coords[0].setY(coord.y + ywidth/2);
				coords[1].setX(coord.x + xwidth/2); coords[1].setY(coord.y + ywidth/2);
				coords[2].setX(coord.x); coords[2].setY(coord.y - ywidth/2);
			}
		}
		
		private void setRadius(double xwidth, double ywidth) {
			this.radius = 0.5*Math.sqrt(Math.pow(xwidth, 2) + Math.pow(ywidth, 2));
		}
	}
	
}
