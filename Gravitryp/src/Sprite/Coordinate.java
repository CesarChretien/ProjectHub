package Sprite;

public class Coordinate {
	private double x;
	private double y;
	
	Coordinate(double x, double y) {
		setX(x);
		setY(y);
	}
	private void setX(double x){ this.x = x; }
	private void setY(double y){ this.y = y; }
	
	public double getX(){ return this.x; }
	public double getY(){ return this.y; }
}