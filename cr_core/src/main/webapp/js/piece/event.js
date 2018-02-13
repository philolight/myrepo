function Events(){
	this.p = {x=-1,y=-1};
};
Events.prototype.keyUp(key){
	delete this.keys[key];
}
Events.prototype.keyDown(key){
	this.keys[key] = key;
}