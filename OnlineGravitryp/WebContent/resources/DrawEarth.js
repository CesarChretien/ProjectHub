/**
 * 
 */
function DrawEarth(planet, animP, planetslow) {
	this.translate(planet.sprite.centre.x, planet.sprite.centre.y);
	this.drawImage(imgPlanetEarth, animP.x, animP.y, 49, 49, -planet.sprite.xwidth/2 , -planet.sprite.ywidth/2 , planet.sprite.xwidth, planet.sprite.ywidth);
	this.translate(-(planet.sprite.centre.x), -(planet.sprite.centre.y));

	if(!pause) {
		if(++animP.slow % planetslow == 0) {
			animP.slow = 0;
			if(animP.y == 147 && animP.x == 147) {
				animP.x = 0;
				animP.y = 0;
			}
			else if(animP.x == 196) {
				animP.x = 0;
				animP.y += 49;	
			}
			else {
				animP.x += 49;
			}
		}
	}
}