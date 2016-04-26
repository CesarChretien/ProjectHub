/**
 * 
 */
function DrawShip(ship, up, down, left, right, anim) {
	ctx.translate(ship.sprite.centre.x, ship.sprite.centre.y);
	if(up || down) {
		if(left) {
			ship.sprite.ywidth = 30;
			ship.sprite.setHitbox(ship.sprite.centre, ship.sprite.direction);
			this.rotate((anim.ang = (360 + anim.ang - ship.rotspd) % 360)*(Math.PI/180.0));
			this.drawImage(imgShipLeft,0,anim.LR,43,30,-(ship.sprite.xwidth/2 + 7), -ship.sprite.ywidth/2,43,30);
			this.rotate(-anim.ang*(Math.PI/180.0));
		}
		else if(right) {
			ship.sprite.ywidth = 30;
			ship.sprite.setHitbox(ship.sprite.centre, ship.sprite.direction);
			this.rotate((anim.ang = (360 + anim.ang + ship.rotspd) % 360)*(Math.PI/180.0));
			this.drawImage(imgShipRight,0,anim.LR,43,30,-(ship.sprite.xwidth/2 + 7), -ship.sprite.ywidth/2,43,30);
			this.rotate(-anim.ang*(Math.PI/180.0));
		}
		else {
			ship.sprite.ywidth = 39;
			ship.sprite.setHitbox(ship.sprite.centre, ship.sprite.direction);
			this.rotate(anim.ang*(Math.PI/180.0));
			this.drawImage(imgShipDefault,0,anim.D,43,39,-(ship.sprite.xwidth/2 + 7), -ship.sprite.ywidth/2,43,39);
			this.rotate(-anim.ang*(Math.PI/180.0));
		}
	}
	else if (left) {
		ship.sprite.ywidth = 30;
		ship.sprite.setHitbox(ship.sprite.centre, ship.sprite.direction);
		this.rotate((anim.ang = (360 + anim.ang - ship.rotspd) % 360)*(Math.PI/180.0));
		this.drawImage(imgShipLeft,0,0,43,30,-(ship.sprite.xwidth/2 + 7), -ship.sprite.ywidth/2,43,30);
		this.rotate(-anim.ang*(Math.PI/180.0));
	}
	else if (right) {
		ship.sprite.ywidth = 30;
		ship.sprite.setHitbox(ship.sprite.centre, ship.sprite.direction);
		this.rotate((anim.ang = (360 + anim.ang + ship.rotspd) % 360)*(Math.PI/180.0));
		this.drawImage(imgShipRight,0,0,43,30,-(ship.sprite.xwidth/2 + 7), -ship.sprite.ywidth/2,43,30);
		this.rotate(-anim.ang*(Math.PI/180.0));
	}
	else {
		ship.sprite.ywidth = 39;
		ship.sprite.setHitbox(ship.sprite.centre, ship.sprite.direction);
		this.rotate(anim.ang*(Math.PI/180.0));
		this.drawImage(imgShipDefault,0,0,43,39,-(ship.sprite.xwidth/2 + 7), -ship.sprite.ywidth/2,43,39);
		this.rotate(-anim.ang*(Math.PI/180.0));
	}
	this.translate(-(ship.sprite.centre.x), -(ship.sprite.centre.y));
	anim.D = anim.D % 78 + 39;
	anim.LR = anim.LR % 60 + 30;
	return anim;
}