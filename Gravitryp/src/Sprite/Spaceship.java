package Sprite;

public class Spaceship extends Sprite{

	private double speed;
	private double fuel;
	
	public Spaceship(double xloc, double yloc, double xbox, double ybox, String shape, double speed, double fuel) {
		super(xloc, yloc, xbox, ybox, Sprite.TRIANGLE);
	}

}
