/**
 * 
 */
function p (centre, shape, xwidth, ywidth, shipdir, velocity) {
	
	this.maxspd = 5.0;
	this.rotspd = 4.0;
	this.accel = 0.1;
	this.sprite = new Sprite(centre, shape, xwidth, ywidth, shipdir);
	this.velocity = velocity;
	
	this.rotateLeft = function() {
		this.direction.rotate(-this.rotspd);
		this.sprite.hitbox(sprite.centre, sprite.direction);
	}
	
	this.rotateRight = function() {
		this.direction.rotate(this.rotspd);
		this.sprite.hitbox(sprite.centre, sprite.direction);
	}
	
	this.setVelocity = function(v) {
		if(p.length() > this.maxspd) {
			v = new Point(v.x * (this.maxspd/v.length()), v.y * (this.maxspd/v.length()));
		}
		this.velocity = v;
	}
	
	this.move = function() {
		var update = new Point(sprite.direction.x, sprite.direction.y);
		update.scale(this.accel);
		this.setVelocity(new Point(this.velocity.x + update.x, this.velocity.y + update.y));
	}
	
	this.slow = function() {
		if(this.velocity > this.accel) {
			this.velocity.scale(1 - this.accel);
		}
		else {
			this.velocity = new Point(0, 0);
		}
	}
	
	this.applyGravity = function(planets) {
		var g_xsum = 0;
		var g_ysum = 0;
		
		for(var i = 0; i < planets.arguments; i++) {
			var dx = planets[i].sprite.centre.x - this.centre.x;
			var dy = planets[i].sprite.centre.y - this.centre.y;
			var distsq = dx*dx + dy*dy;
			g_xsum += planets.gc * (dx / distsq);
			g_ysum += planets.gc * (dy / distsq);
		}
		
		this.setVelocity(new Point(this.velocity.x + g_xsum, this.velocity.y + g_ysum));
	}
}