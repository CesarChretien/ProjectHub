/**
 * 
 */
function Relocate(direction, canWidth, canHeight) {
	for(var i = 0; i < this.length; i++) {
		if(direction === "left") {
			this[i].relocate(this[i].radius + Math.max(Math.random() * (canWidth - 2*this[i].radius) - 100, 0), this[i].radius + Math.random() * (canHeight - 2*this[i].radius));
		}
		else if(direction === "right") {
			this[i].relocate(this[i].radius + Math.min(Math.random() * (canWidth - 2*this[i].radius) + 100, canWidth - 2*this[i].radius), this[i].radius + Math.random() * (canHeight - 2*this[i].radius));
		}
		else if(direction === "up") {
			this[i].relocate(this[i].radius + Math.random() * (canWidth - 2*this[i].radius), this[i].radius + Math.max(Math.random() * (canHeight - 2*this[i].radius) - 70, 0));
		}
		else if(direction === "down") {
			this[i].relocate(this[i].radius + Math.random() * (canWidth - 2*this[i].radius), this[i].radius + Math.min(Math.random() * (canHeight - 2*this[i].radius) + 70, canHeight - 2*this[i].radius));
		}
	}
}