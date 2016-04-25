/**
 * 
 */
function Planet(centre, radius, gc) {
	
	this.sprite = new Sprite(centre, "circle", 2*radius, 2*radius, new Point(1, 0));
	this.radius = radius;
	this.gc = gc;
	
	this.relocate = function(newx, newy) {
		this.sprite.centre = new Point(newx, newy);
		this.sprite.setHitbox(this.sprite.centre, new Point(1, 0));
	}
}