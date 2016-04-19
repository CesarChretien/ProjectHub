/**
 * 
 */
function Planet(centre, radius, gc) {
	
	this.sprite = new Sprite(centre, "circle", 2*radius, 2*radius, new Point(1, 0));
	this.radius = radius;
	this.gc = gc;
}