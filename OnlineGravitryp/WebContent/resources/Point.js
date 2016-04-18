/**
 * 
 */
function Point(x, y) {
	this.x = x;
	this.y = y;
	
	this.length = function() {
		return Math.sqrt(this.x*this.x + this.y*this.y)
	}
	
	this.rotate = function(p, deg) {
		var th = (Math.PI/180.0) * deg;
		var x1 = this.x - p.x;
		var y1 = this.y - p.y;
		var ol = new Point(x1, y1).length();
		var x2 = Math.cos(th)*x1 - Math.sin(th)*y1;
		var y2 = Math.sin(th)*x1 + Math.cos(th)*y1;
		var nl = new Point(x2, y2).length();
		this.x = p.x + (ol/nl)*x2;
		this.y = p.y + (ol/nl)*y2;
	}
	
	this.rotate0 = function(deg) {
		this.rotate(new Point(0, 0), deg);
	}
	
	this.scale = function(scale) {
		this.x = scale * this.x;
		this.y = scale * this.y;
	}
}