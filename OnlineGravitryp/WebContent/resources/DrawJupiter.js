/**
 * 
 */
function DrawJupiter(planet, animP, planetslow) {
	this.translate(planet.sprite.centre.x, planet.sprite.centre.y);
	this.drawImage(imgPlanetJupiter, animP.x, animP.y, 55, 55, -planet.sprite.xwidth/2 , -planet.sprite.ywidth/2 , planet.sprite.xwidth, planet.sprite.ywidth);
	this.translate(-(planet.sprite.centre.x), -(planet.sprite.centre.y));

	if(!pause) {
		if(++animP.slow % planetslow == 0) {
			animP.slow = 0;
			if(animP.y == 165 && animP.x == 165) {
				animP.x = 0;
				animP.y = 0;
			}
			else if(animP.x == 220) {
				animP.x = 0;
				animP.y += 55;	
			}
			else {
				animP.x += 55;
			}
		}
	}
}