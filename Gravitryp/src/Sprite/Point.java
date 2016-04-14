package Sprite;

import Sprite.Coordinate;

public class Point {
	private Coordinate coord;
	private double angle;
	private double distance;
	
	public Point(Coordinate coord, double angle, double distance) {
		this.setCoord(coord);
		this.setAngle(angle);
		this.setDistance(distance);
	}

	void setCoord(Coordinate coord) {
		this.coord = coord;
	}
	public Coordinate getCoord() {
		return this.coord;
	}
	void setAngle(double angle) {
		this.angle = angle;
	}
	private double getDistance() {
		return this.distance;
	}
	private void setDistance(double distance) {
		this.distance = distance;
	}
	protected double getAngle() {
		return this.angle;
	}
	double getX() {
		return ((this.getCoord().getX() + this.getDistance()*Math.cos(this.getAngle() * (Math.PI/180.0))));
	}
	double getY() {
		return ((this.getCoord().getY() - this.getDistance()*Math.sin(this.getAngle() * (Math.PI/180.0))));
	}
	
}