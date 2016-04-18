package objects;

public class Point {

	private double x;
	private double y;
	
	public Point(double x, double y) {
		this.setX(x);
		this.setY(y);
	}
	
	//rotates a point deg degrees around Point centre
	public void rotate(Point centre, double deg) {
		double th = Math.toRadians(deg);
		double x = this.getX() - centre.getX();
		double y = this.getY() - centre.getY();
		double ol = new Point(x, y).length();
		double dx = Math.cos(th)*x - Math.sin(th)*y;
		double dy = Math.sin(th)*x + Math.cos(th)*y;
		double nl = new Point(dx, dy).length();
		this.setX(centre.getX() + (ol/nl)*dx);
		this.setY(centre.getY() + (ol/nl)*dy);
	}
	//rotates a point deg degrees around Point (0,0)
	public void rotate(double deg) {
		this.rotate(new Point(0, 0), deg);
	}
	
	//adds a point to another point
	public void add(Point p) {
		this.setX(this.getX() + p.getX());
		this.setY(this.getY() + p.getY());
	}
	
	public void scale(double scale) {
		setX(scale * this.getX());
		setY(scale * this.getY());
	}
	public double length() {
		return Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2));
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	
}
