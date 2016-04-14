package Sprite;

public class Spaceship extends Sprite{

	private double speed;
	private double travelangle;
	private int fuel;
	private final double rotation = 4;
	private final double accel = 0.1;
	private final double max_speed = 5;
	
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
	
	public void updateLocation() {
		for(Point points: super.getPoints()) {
			points.setCoord(new Coordinate((1.0*points.getCoord().getX() + this.getSpeed()*Math.cos((Math.PI/180)*this.getTravelangle())),
										   (1.0*points.getCoord().getY() - this.getSpeed()*Math.sin((Math.PI/180)*this.getTravelangle()))));
		}
	}
	
	//movement
	public void rotateLeft() {
		for(Point points: super.getPoints()) {
			points.setAngle((points.getAngle() + 4) % 360);
		}
		this.setRealAngle((super.getAngle() + 4) % 360);
		//this.updateLocation();
	}
	
	public void rotateRight() {
		for(Point points: super.getPoints()) {
			points.setAngle((points.getAngle() + 356) % 360);
		}
		super.setRealAngle((super.getAngle() + 356) % 360);
		//this.updateLocation();
	}
	
	public void move() {
		if(this.getSpeed() == 0 || this.getAngle() == this.getTravelangle()) {
			this.setTravelangle(this.getAngle());
			this.setSpeed(this.getSpeed() + accel);
		}
		else {
			
			if(Math.abs(this.getAngle() - this.getTravelangle()) > 90) {
				this.slow();
			}
			
			if(this.getAngle() >= 0 && this.getAngle() < 180) {
				if(this.getTravelangle() > this.getAngle() && this.getTravelangle() < this.getAngle() + 180) {
					this.setTravelangle(this.getTravelangle() - rotation);
				}
				else {
					this.setTravelangle(this.getTravelangle() + rotation);
				}
			}
			else if(this.getAngle() >= 180 && this.getAngle() < 360) {
				if(this.getTravelangle() < this.getAngle() && this.getTravelangle() > this.getAngle() - 180) {
					this.setTravelangle(this.getTravelangle() + rotation);
				}
				else {
					this.setTravelangle(this.getTravelangle() - rotation);
				}
			}
		}
		//this.updateLocation();
	}
	
	public void slow() {
		this.setSpeed(this.getSpeed() - accel);
	}
	
	//setters and getters
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = (speed > 0) ? Math.min(speed, max_speed) : 0;
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
		this.travelangle = (travelangle + 360) % 360;
	}
}
