package Sprite;

import Sprite.Coordinate;
import Sprite.Point;

class Points {
	private Point[] points;
	private int radius = 0; //only not 0 if shape is a circle
	
	protected static final String CIRCLE = "circle";
	protected static final String SQUARE = "square";
	protected static final String TRIANGLE = "triangle";
	
	Points(Coordinate coord, String shape, int xwidth, int ywidth) {
		System.out.println("xcoord: " + coord.getX());
		double th = Math.atan(ywidth/xwidth);
		if(shape.equals(CIRCLE)) {
			setRadius(xwidth, ywidth);
			points = new Point[1];
			points[0] = new Point(coord, 0.0, 0.0);
		}
		else if (shape.equals(SQUARE)) {
			double r = ((0.5*Math.sqrt(Math.pow(xwidth, 2) + Math.pow(ywidth, 2))));
			this.points = new Point[4];
			this.points[0] = new Point(coord, th, r);
			this.points[1] = new Point(coord, (180/Math.PI) * (Math.PI - th), r);
			this.points[2] = new Point(coord, (180/Math.PI) * (Math.PI + th), r);
			this.points[3] = new Point(coord, (180/Math.PI) * (2*Math.PI - th), r);
		}
		else if (shape.equals(TRIANGLE)) {
			int r = (int)(0.5*Math.sqrt(Math.pow(xwidth, 2) + Math.pow(ywidth, 2)));
			this.points = new Point[3];
			this.points[0] = new Point(coord, 0, 2*r);
			this.points[1] = new Point(coord, (int) Math.round((180/Math.PI) * (Math.PI - th)), r);
			this.points[2] = new Point(coord, (int) Math.round((180/Math.PI) * (Math.PI + th)), r);
		}
	}
	
	public Point[] getPoints() {
		return this.points;
	}
	private void setRadius(int xwidth, int ywidth) {
		this.radius = (int) Math.round(0.5*Math.sqrt(Math.pow(xwidth, 2) + Math.pow(ywidth, 2)));
	}
	public int getRadius() {
		return this.radius;
	}
}