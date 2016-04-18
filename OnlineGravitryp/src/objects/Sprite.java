package objects;

public class Sprite {
	
	private Point centre;
	private Point direction; //unity vector, points where nose of ship points
	private Point[] hitbox;
	private String shape;
	private double xwidth;
	private double ywidth;
	private double radius = 0.0; //only set if hitbox is a circle

	public static final String CIRCLE = "circle";
	public static final String TRIANGLE = "triangle";
	public static final String SQUARE = "square";
	
	public Sprite(Point centre, String shape, double xwidth, double ywidth, Point direction) {
		this.setCentre(centre);
		this.setShape(shape);
		this.setDirection(direction);
		this.setXwidth(xwidth);
		this.setYwidth(ywidth);
		this.setHitbox(centre, direction);
	}
	
	public boolean hasCollision(Sprite spr) {
		return false;
	}
	
	public Point getDirection() {
		return direction;
	}

	public void setDirection(Point direction) {
		this.direction = direction;
	}
	
	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}

	public Point[] getHitbox() {
		return hitbox;
	}
	
	public double getXwidth() {
		return xwidth;
	}

	public void setXwidth(double xwidth) {
		this.xwidth = xwidth;
	}

	public double getYwidth() {
		return ywidth;
	}

	public void setYwidth(double ywidth) {
		this.ywidth = ywidth;
	}

	public void setHitbox(Point centre, Point direction) {
		Point p = new Point(direction.getX(), direction.getY());
		if(this.getShape().equals(TRIANGLE)) {
			this.hitbox = new Point[3];
			p.scale(0.5*this.getXwidth());
			this.hitbox[0] = new Point(centre.getX() + p.getX(), centre.getY() + p.getY());
			p.rotate(180 - Math.toDegrees(Math.atan(this.getYwidth()/this.getXwidth())));
			p.scale(Math.sqrt(Math.pow(this.getXwidth(), 2) + Math.pow(this.getYwidth(), 2))/this.getXwidth());
			this.hitbox[1] = new Point(centre.getX() + p.getX(), centre.getY() + p.getY());
			p.rotate(2*Math.toDegrees(Math.atan(this.getYwidth()/this.getXwidth())));
			this.hitbox[2] = new Point(centre.getX() + p.getX(), centre.getY() + p.getY());
		}
		else if (this.getShape().equals(SQUARE)) {
			this.hitbox = new Point[4];
			p.scale(0.5*Math.sqrt(Math.pow(this.getXwidth(), 2) + Math.pow(this.getYwidth(), 2)));
			p.rotate(45.0);
			this.hitbox[0] = new Point(centre.getX() + p.getX(), centre.getY() + p.getY());
			p.rotate(90.0);
			this.hitbox[1] = new Point(centre.getX() + p.getX(), centre.getY() + p.getY());
			p.rotate(90.0);
			this.hitbox[2] = new Point(centre.getX() + p.getX(), centre.getY() + p.getY());
			p.rotate(90.0);
			this.hitbox[3] = new Point(centre.getX() + p.getX(), centre.getY() + p.getY());
		}
		else if (this.getShape().equals(CIRCLE)){
			this.hitbox = new Point[1];
			this.hitbox[0] = new Point(centre.getX(), centre.getY());
			this.radius = 0.5*this.getXwidth();
		}
	}
	
	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}
	
	public double getRadius() {
		return this.radius;
	}
}
