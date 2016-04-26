/**
 * 
 */
function DrawShip(ship, up, down, left, right, animD, animLR, dAngle) {
	ctx.translate(ship.sprite.centre.x, ship.sprite.centre.y);
	if(up || down) {
		if(left) {
			ship.sprite.ywidth = 30;
			ship.sprite.setHitbox(ship.sprite.centre, ship.sprite.direction);
			this.rotate((dAngle = (360 + dAngle - ship.rotspd) % 360)*(Math.PI/180.0));
			this.drawImage(imgShipLeft,0,animLR,43,30,-(ship.sprite.xwidth/2 + 7), -ship.sprite.ywidth/2,43,30);
			this.rotate(-dAngle*(Math.PI/180.0));
		}
		else if(right) {
			ship.sprite.ywidth = 30;
			ship.sprite.setHitbox(ship.sprite.centre, ship.sprite.direction);
			this.rotate((dAngle = (360 + dAngle + ship.rotspd) % 360)*(Math.PI/180.0));
			this.drawImage(imgShipRight,0,animLR,43,30,-(ship.sprite.xwidth/2 + 7), -ship.sprite.ywidth/2,43,30);
			this.rotate(-dAngle*(Math.PI/180.0));
		}
		else {
			ship.sprite.ywidth = 39;
			ship.sprite.setHitbox(ship.sprite.centre, ship.sprite.direction);
			this.rotate(dAngle*(Math.PI/180.0));
			this.drawImage(imgShipDefault,0,animD,43,39,-(ship.sprite.xwidth/2 + 7), -ship.sprite.ywidth/2,43,39);
			this.rotate(-dAngle*(Math.PI/180.0));
		}
	}
	else if (left) {
		ship.sprite.ywidth = 30;
		ship.sprite.setHitbox(ship.sprite.centre, ship.sprite.direction);
		this.rotate((dAngle = (360 + dAngle - ship.rotspd) % 360)*(Math.PI/180.0));
		this.drawImage(imgShipLeft,0,0,43,30,-(ship.sprite.xwidth/2 + 7), -ship.sprite.ywidth/2,43,30);
		this.rotate(-dAngle*(Math.PI/180.0));
	}
	else if (right) {
		ship.sprite.ywidth = 30;
		ship.sprite.setHitbox(ship.sprite.centre, ship.sprite.direction);
		this.rotate((dAngle = (360 + dAngle + ship.rotspd) % 360)*(Math.PI/180.0));
		this.drawImage(imgShipRight,0,0,43,30,-(ship.sprite.xwidth/2 + 7), -ship.sprite.ywidth/2,43,30);
		this.rotate(-dAngle*(Math.PI/180.0));
	}
	else {
		ship.sprite.ywidth = 39;
		ship.sprite.setHitbox(ship.sprite.centre, ship.sprite.direction);
		this.rotate(dAngle*(Math.PI/180.0));
		this.drawImage(imgShipDefault,0,0,43,39,-(ship.sprite.xwidth/2 + 7), -ship.sprite.ywidth/2,43,39);
		this.rotate(-dAngle*(Math.PI/180.0));
	}
	this.translate(-(ship.sprite.centre.x), -(ship.sprite.centre.y));
	animD = animD % 78 + 39;
	animLR = animLR % 60 + 30;
	return dAngle;
}