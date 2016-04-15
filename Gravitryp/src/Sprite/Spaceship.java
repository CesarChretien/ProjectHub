package Sprite;

public class Spaceship extends Sprite{

	private double speed;
	private double travelangle;
	private int fuel;
	private final double rotation = 3;
	private final double turnspeed = 4;
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
	
	public double applyXGravity(Planet[] planet) {
		double res = 0.0;
		for(Planet p: planet) {
			double xdiff = p.getXCoord() - this.getXCoord();
			double ydiff = p.getYCoord() - this.getYCoord();
			double dsq = Math.pow(xdiff, 2) +  Math.pow(ydiff,2);
			res += (dsq != 0.0) ? p.getMass()*xdiff/dsq : 0.0; 
		}
		return 1.0*res;
	}
	
	public double applyYGravity(Planet[] planet) {
		double res = 0.0;
		for(Planet p: planet) {
			double xdiff = p.getXCoord() - this.getXCoord();
			double ydiff = p.getYCoord() - this.getYCoord();
			double dsq = Math.pow(xdiff, 2) +  Math.pow(ydiff,2);
			res += (dsq != 0.0) ? p.getMass()*ydiff/dsq : 0.0; 
		}
		return 1.0*res;
	}
	
	public void updateLocation() {
		for(Point points: super.getPoints()) {
			points.setCoord(new Coordinate((1.0*points.getCoord().getX() + this.getSpeed()*Math.cos((Math.PI/180)*this.getTravelangle())),
										   (1.0*points.getCoord().getY() - this.getSpeed()*Math.sin((Math.PI/180)*this.getTravelangle()))));
		}
	}
	public void updateLocation(int x, int y, Planet[] planet) {
		Coordinate old = new Coordinate(this.getXCoord(), this.getYCoord());
		for(Point points: super.getPoints()) {
		points.setCoord(new Coordinate(((1.0*points.getCoord().getX() + this.getSpeed()*Math.cos((Math.PI/180)*this.getTravelangle())) + 1.0*x + applyXGravity(planet)) % x ,
									   ((1.0*points.getCoord().getY() - this.getSpeed()*Math.sin((Math.PI/180)*this.getTravelangle())) + 1.0*y + applyYGravity(planet)) % y));
		}
		Coordinate n = new Coordinate(this.getXCoord(), this.getYCoord());
		
		if(Math.abs(n.getY() - old.getY()) < 50 && Math.abs(n.getX() - old.getX()) < 50)
			this.setTravelangle(1.0*Math.round((360.0 - (180.0/Math.PI)*Math.atan2(n.getY() - old.getY(), n.getX() - old.getX()))%360));
		System.out.println("reishoek: " + this.getTravelangle() + ", hoek: " + this.getAngle() + ", speed: " + getSpeed());
	}
	
	//movement
	public void rotateLeft() {
		for(Point points: super.getPoints()) {
			points.setAngle((points.getAngle() + turnspeed) % 360);
		}
		this.setRealAngle((super.getAngle() + turnspeed) % 360);
		//this.updateLocation();
	}
	
	public void rotateRight() {
		for(Point points: super.getPoints()) {
			points.setAngle((points.getAngle() + (360 - turnspeed)) % 360);
		}
		super.setRealAngle((super.getAngle() + (360 - turnspeed)) % 360);
		//this.updateLocation();
	}
	
	public void move() {
		if(this.getSpeed() == 0 || this.getAngle() == this.getTravelangle()) {
			this.setTravelangle(this.getAngle());
			this.setSpeed(this.getSpeed() + accel);
		}
		else if (Math.abs(this.getAngle() - this.getTravelangle()) < rotation) {
			this.setTravelangle(this.getAngle());
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
