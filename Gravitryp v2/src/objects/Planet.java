package objects;

public class Planet extends Sprite{

	private double gc; // gravity constant
	
	public double getGc() {
		return gc;
	}

	public void setGc(double gc) {
		this.gc = gc;
	}

	public Planet(Point centre, double radius, double gc) {
		super(centre, Sprite.CIRCLE, 2*radius, 2*radius, new Point(1, 0));
		this.setGc(gc);
	}
}
