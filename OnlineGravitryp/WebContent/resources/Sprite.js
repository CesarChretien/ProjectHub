/**
 * 
 */
function Sprite(centre, shape, xwidth, ywidth, direction) {
	this.centre = centre;
	this.shape = shape;
	this.xwidth = xwidth;
	this.ywidth = ywidth;
	this.direction = direction;
	this.hitbox = [];
	
	this.setHitbox = function(centre, direction) {
		var p = new Point(direction.x, direction.y);
		if(this.shape === "triangle") {
			p.scale(0.5*this.xwidth);
			this.hitbox = [new Point(centre.x + p.x, centre.y + p.y)];
			p.rotate0(180 - (180/Math.PI)*(Math.atan(this.ywidth/this.xwidth)));
			p.scale(Math.sqrt(this.xwidth*this.xwidth + this.ywidth*this.ywidth)/this.xwidth);
			this.hitbox.push(new Point(centre.x + p.x, centre.y + p.y));
			p.rotate0(2*(180/Math.PI)*(Math.atan(this.ywidth/this.xwidth)));
			this.hitbox.push(new Point(centre.x + p.x, centre.y + p.y));
		}
		else if (this.shape === "square") {
			p.scale(0.5*Math.sqrt(this.xwidth*this.xwidth + this.ywidth*this.ywidth));
			p.rotate0(45.0);
			this.hitbox = [new Point(centre.x + p.x, centre.y + p.y)];
			p.rotate0(90.0);
			this.hitbox.push(new Point(centre.x + p.x, centre.y + p.y));
			p.rotate0(90.0);
			this.hitbox.push(new Point(centre.x + p.x, centre.y + p.y));
			p.rotate0(90.0);
			this.hitbox.push(new Point(centre.x + p.x, centre.y + p.y));
		}
		else if (this.shape === "circle"){
			this.hitbox = [new Point(centre.x, centre.y)];
			this.radius = 0.5*this.xwidth;
		}
	}
	
	this.setHitbox(this.centre, this.direction);
}