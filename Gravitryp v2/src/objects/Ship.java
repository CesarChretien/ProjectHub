package objects;

public class Ship extends Sprite {

	private Point velocity; //vector with a maximum length, points where ship is going and also signifies speed
	private double rotspd = 4.0; //rotation speed of ship. (could become a settable)
	private double maxspd = 5.0; //maximum speed of ship. (could become a settable)
	private double accel = 0.1; //acceleration of ship. (could become a settable)
	
	public Ship(Point centre, String shape, double xwidth, double ywidth, Point shipdir, Point velocity) {
		super(centre, shape, xwidth, ywidth, shipdir);
		this.setVelocity(velocity);
	}
	
	//rotates the ship to the left
	public void rotateLeft() {
		this.getDirection().rotate(-rotspd);
		this.setHitbox(this.getCentre(), this.getDirection());
	}
	
	//rotates the ship to the right
	public void rotateRight() {
		this.getDirection().rotate(rotspd);
		this.setHitbox(this.getCentre(), this.getDirection());
	}
	
	//updates velocity vector
	public void move() {
		Point update = new Point(this.getDirection().getX(), this.getDirection().getY());
		update.scale(accel);
		this.getVelocity().add(update);
		this.setVelocity(this.getVelocity());
	}
	
	//slows the ship down
	public void slow() {
		if (this.getVelocity().length() > accel) {
			this.getVelocity().scale(1 - accel);
		}
		else
			this.setVelocity(new Point(0, 0));
	}
	
	public void applyGravity(Planet[] planets) {
		double g_xsum = 0.0;
		double g_ysum = 0.0;
		
		for(Planet p: planets) {
			double dx = p.getCentre().getX() - this.getCentre().getX();
			double dy = p.getCentre().getY() - this.getCentre().getY();
			double distsq = Math.pow(new Point(dx, dy).length(), 2);
			g_xsum += p.getGc() * dx / distsq;
			g_ysum += p.getGc() * dy / distsq;
		}
		this.getVelocity().add(new Point(g_xsum, g_ysum));
		this.setVelocity(this.getVelocity());
	}
	
	//updates ship location
	public void update() {
		this.getCentre().add(this.getVelocity());
		this.setHitbox(this.getCentre(), this.getDirection());
	}
	
	public void update(int x, int y) {
		this.getCentre().add(this.getVelocity());
		this.setCentre(new Point((1.0*x + this.getCentre().getX()) % x, (1.0*y + this.getCentre().getY()) % y));
		this.setHitbox(this.getCentre(), this.getDirection());
	}

	public Point getVelocity() {
		return velocity;
	}

	public void setVelocity(Point velocity) {
		if(velocity.length() > this.maxspd) {
			velocity = new Point(velocity.getX() * (maxspd/velocity.length()), velocity.getY() *  (maxspd/velocity.length()));
		}
		this.velocity = velocity;
	}
}
