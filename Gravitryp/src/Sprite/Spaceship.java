package Sprite;

public class Spaceship extends Sprite{

	private int speed;
	private double travelangle;
	private int fuel;
	private final double rotation = 0.01;
	private final int accel = 1;
	
	//callable constructors
	public Spaceship(int xloc, int yloc, int xwidth, int ywidth, int fuel, double travelangle) {
		super(xloc, yloc, xwidth, ywidth);
		setSpeed(0);
		setFuel(fuel);
		setTravelangle(travelangle);
	}
	
	public Spaceship(int xloc, int yloc, String shape, int xwidth, int ywidth, int fuel, double travelangle) {
		super(xloc, yloc, shape, xwidth, ywidth);
		setSpeed(0);
		setFuel(fuel);
		setTravelangle(travelangle);
	}
	
	public Spaceship(int xloc, int yloc, int xwidth, int ywidth, double angle, int fuel, double travelangle) {
		super(xloc, yloc, xwidth, ywidth, angle);
		setSpeed(0);
		setFuel(fuel);
		setTravelangle(travelangle);
	}
	
	public Spaceship(int xloc, int yloc, String shape, int xwidth, int ywidth, double angle, int fuel, double travelangle) {
		super(xloc, yloc, shape, xwidth, ywidth, angle);
		setSpeed(0);
		setFuel(fuel);
		setTravelangle(travelangle);
	}
	
	//movement
	public void move() {
		if(this.getSpeed() == 0) {
			this.setTravelangle(this.getAngle());
			this.setSpeed(this.getSpeed() + accel);
		}
		else {
			if(Math.abs(this.getTravelangle() - this.getAngle()) <= 90) {
				if(this.getTravelangle() == this.getAngle()) {
					this.setSpeed(this.getSpeed() + accel);
				}
				else {
					if(this.getTravelangle() > this.getAngle()) {
						this.setTravelangle(this.getTravelangle() - rotation);
					}
					else if(this.getTravelangle() < this.getAngle()) {
						this.setTravelangle(this.getTravelangle() + rotation);
					}
				}
			}
			else {
				if(this.getTravelangle() > this.getAngle()) {
					this.setSpeed(this.getSpeed() - accel);
					this.setTravelangle(this.getTravelangle() - rotation);
				}
				else if(this.getTravelangle() < this.getAngle()) {
					this.setSpeed(this.getSpeed() - accel);
					this.setTravelangle(this.getTravelangle() + rotation);
				}	
			}
		}
	}
	public void slow() {
		this.setSpeed((this.getSpeed() - accel > 0) ? this.getSpeed() - accel : 0);
	}
	
	//setters and getters
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getFuel() {
		return fuel;
	}
	public void setFuel(int fuel) {
		this.fuel = fuel;
	}
	public double getTravelangle() {
		return travelangle;
	}
	public void setTravelangle(double travelangle) {
		this.travelangle = travelangle;
	}

}
