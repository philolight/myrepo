function Rect(x, y, w, h){
	this.set(x, y, w, h);
};
Rect.prototype.containsPoint = function(x, y){
	if(this.l <= x && x <= this.r && this.t <= y && y <= this.b){
		return true;
	}
	return false;
};
Rect.prototype.containsRect = function(rect){
	if(this.l <= rect.l && rect.r <= this.r && this.t <= rect.t && rect.b <= this.b) return true;
	return false;
};
Rect.prototype.set = function(x, y, w, h){
	  this.x = x;
	  this.y = y;
	  this.w = w;
	  this.h = h;
	  this.l = x;
	  this.r = x + w;
	  this.t = y;
	  this.b = y + h;
	  this.vc = this.x + this.w/2;
	  this.hc = this.y + this.h/2;
};
Rect.prototype.moveTo = function(x,y){
	this.set(x, y, this.w, this.h);
};
Rect.prototype.moveOffset = function(dx,dy){
	this.set(this.x + dx, this.y + dy, this.w, this.h);
};
Rect.prototype.print = function(){
	console.log(this.x + " " + this.y + " " + this.w + " " + this.h);
}