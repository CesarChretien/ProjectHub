/**
 * 
 */
function Ship(centre, shape, xwidth, ywidth, shipdir, velocity) {
	
	this.maxspd = 5.0;
	this.rotspd = 4.0;
	this.accel = 0.1;
	this.sprite = new Sprite(centre, shape, xwidth, ywidth, shipdir);
	this.velocity = velocity;
	
	this.rotateLeft = function() {
		this.sprite.direction.rotate0(-this.rotspd);
		this.sprite.setHitbox(this.sprite.centre, this.sprite.direction);
	}
	
	this.rotateRight = function() {
		this.sprite.direction.rotate0(this.rotspd);
		this.sprite.setHitbox(this.sprite.centre, this.sprite.direction);
	}
	
	this.setVelocity = function(v) {
		if(v.length() > this.maxspd) {
			v = new Point(v.x * (this.maxspd/v.length()), v.y * (this.maxspd/v.length()));
		}
		this.velocity = v;
	}
	
	this.move = function() {
		var update = new Point(this.sprite.direction.x, this.sprite.direction.y);
		update.scale(this.accel);
		this.setVelocity(new Point(this.velocity.x + update.x, this.velocity.y + update.y));
	}
	
	this.slow = function() {
		if(this.velocity.length() > this.accel) {
			this.velocity.scale(1 - this.accel);
		}
		else {
			this.velocity = new Point(0, 0);
		}
	}
	
	this.applyGravity = function(planets) {
		var g_xsum = 0;
		var g_ysum = 0;
		
		for(var i = 0; i < planets.length; i++) {
			var dx = planets[i].sprite.centre.x - this.sprite.centre.x;
			var dy = planets[i].sprite.centre.y - this.sprite.centre.y;
			var distsq = dx*dx + dy*dy;
			g_xsum += planets[i].gc * (dx / distsq);
			g_ysum += planets[i].gc * (dy / distsq);
		}
		
		this.setVelocity(new Point(this.velocity.x + g_xsum, this.velocity.y + g_ysum));
	}
	
	this.update = function(width, height) {
		this.sprite.centre.x = (this.sprite.centre.x + this.velocity.x + width) % width;
		this.sprite.centre.y = (this.sprite.centre.y + this.velocity.y + height) % height;
		this.sprite.setHitbox(this.sprite.centre, this.sprite.direction);
	}
}