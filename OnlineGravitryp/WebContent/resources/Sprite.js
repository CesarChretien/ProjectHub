/**
 * 
 */
function Sprite(centre, shape, xwidth, ywidth, direction) {
	this.centre = centre;
	this.shape = shape;
	this.xwidth = xwidth;
	this.ywidth = ywidth;
	
	p = new Point(direction.x, direction.y);
	if(this.shape === "triangle") {
		p.scale(0.5*this.xwidth);
		this.hitbox = [new Point(centre.x + p.x, centre.y + p.y)];
		p.rotate(180 - (180/Math.PI)*(Math.atan(this.ywidth/this.xwidth)));
		p.scale(Math.sqrt(this.x*this.x + this.y*this.y)/this.x);
		this.hitbox.push = new Point(centre.x + p.x, centre.y + p.y);
		p.rotate(2*(180/Math.PI)*(Math.atan(this.ywidth/this.xwidth)));
		this.hitbox.push = new Point(centre.x + p.x, centre.y + p.y);
	}
	else if (this.shape === "square") {
		p.scale(0.5*Math.sqrt(this.x*this.x + this.y*this.y));
		p.rotate(45.0);
		this.hitbox = [new Point(centre.x + p.x, centre.y + p.y)];
		p.rotate(90.0);
		this.hitbox.push = new Point(centre.x + p.x, centre.y + p.y);
		p.rotate(90.0);
		this.hitbox.push = new Point(centre.x + p.x, centre.y + p.y);
		p.rotate(90.0);
		this.hitbox.push = new Point(centre.x + p.x, centre.y + p.y);
	}
	else if (this.getShape().equals(CIRCLE)){
		this.hitbox = [new Point(centre.x, centre.y)];
		this.radius = 0.5*x;
	}
}