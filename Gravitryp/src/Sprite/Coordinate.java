package Sprite;

class Coordinate {
	private int x;
	private int y;
	
	Coordinate(int x, int y) {
		setX(x);
		setY(y);
	}
	private void setX(int x){ this.x = x; }
	private void setY(int y){ this.y = y; }
	
	int getX(){ return this.x; }
	int getY(){ return this.y; }
}