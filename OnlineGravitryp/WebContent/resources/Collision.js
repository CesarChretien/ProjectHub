/**
 * 
 */
function hasCollisionWith(objlist) {
	
	var thb = this.sprite.hitbox;
	for(var i = 0; i < objlist.length; i++) {
		var o = objlist[i].sprite;
		var os = o.shape;
		var ohb = o.hitbox;
		
		if(os === "circle") {
			var mid = ohb[0];
			for(var j = 0; j < thb.length; j++) {
				var sp1 = thb[j % thb.length];
				var sp2 = thb[(j+1) % thb.length];
				
				var lambda = ((mid.x - sp2.x)*(sp1.x - sp2.x) + (mid.y - sp2.y)*(sp1.y - sp2.y)) / (Math.pow(sp1.x - sp2.x, 2) + Math.pow(sp1.y - sp2.y, 2));
				if (lambda < 0) {
					var d = sp2;
				}
				else if (lambda > 1) {
					var d = sp1;
				}
				else {
					var d = new Point(sp2.x - lambda*(sp2.x - sp1.x), sp2.y - lambda*(sp2.y - sp1.y));
				}
				
				var dist = Math.sqrt((mid.x - d.x)*(mid.x - d.x) + (mid.y - d.y)*(mid.y - d.y));
				if(dist <= o.radius) {
					return true;
				}
			}
		}
	}
	return false;
}