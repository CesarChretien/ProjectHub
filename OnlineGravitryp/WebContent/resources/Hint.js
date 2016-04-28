/**
 * 
 */
function Hint(centre, xwidth, ywidth, dir) {
	this.sprite = new Sprite(centre, "circle", xwidth, ywidth, new Point(1, 0));
	this.radius = radius;
	if(dir === "right") {
		this.angle = 0;
	}
	else if(dir === "up") {
		this.angle = 90;
	}
	else if(dir === "left") {
		this.angle = 180;
	}
	else if(dir === "down") {
		this.angle = 270;
	}
	
	this.draw = function(img, ctx) {
		ctx.translate(this.sprite.centre.x, this.sprite.centre.y);
		ctx.rotate(this.angle*(Math.PI/180.0));
		ctx.drawImage(img, 0, 0, img.width, img.height, -this.sprite.xwidth/2, -this.sprite.ywidth/2, this.sprite.xwidth, this.sprite.ywidth);
		ctx.translate(-this.sprite.centre.x, -this.sprite.centre.y);
	}
}