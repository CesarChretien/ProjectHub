package Sprite;

public class Spaceship extends Sprite{

	private double speed;
	private double fuel;
	
	//callable constructors
	public Spaceship(double xloc, double yloc, double xwidth, double ywidth, double fuel) {
		super(xloc, yloc, xwidth, ywidth);
		setSpeed(0);
		setFuel(fuel);
	}
	public Spaceship(double xloc, double yloc, String shape, double xwidth, double ywidth, double fuel) {
		super(xloc, yloc, shape, xwidth, ywidth);
		setSpeed(0);
		setFuel(fuel);
	}
	public Spaceship(double xloc, double yloc, double xwidth, double ywidth, double angle, double fuel) {
		super(xloc, yloc, xwidth, ywidth, angle);
		setSpeed(0);
		setFuel(fuel);
	}
	public Spaceship(double xloc, double yloc, String shape, double xwidth, double ywidth, double angle, double fuel) {
		super(xloc, yloc, shape, xwidth, ywidth, angle);
		setSpeed(0);
		setFuel(fuel);
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getFuel() {
		return fuel;
	}
	public void setFuel(double fuel) {
		this.fuel = fuel;
	}

}
