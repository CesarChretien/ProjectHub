package Sprite;

public class Planet extends Sprite {

	private double mass;
	
	public Planet(int xloc, int yloc, int xwidth, int ywidth, double mass) {
		super(xloc, yloc, Sprite.CIRCLE, xwidth, ywidth);
		this.setMass(mass);
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public double getRadius() {
		return this.getPointsObj().getRadius();
	}
	
}
