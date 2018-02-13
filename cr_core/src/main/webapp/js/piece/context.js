document.write('<script type="text/javascript" src="/js/piece/pieces.js"></script>');

function CanvasContext(){
	this.listeners = [];
	this.mouseDowned = false;
};
CanvasContext.prototype.emptyPoint = {x:-1,y:-1};
CanvasContext.prototype.addListener = function(listener){
	this.listeners.push(listener);
};
CanvasContext.prototype.mouseDown = function(x,y){
	this.mouseDowned = true;
	for(var i in this.listeners){
		if(this.listeners[i].mouseDown(x,y) === true) return;
	}
};
CanvasContext.prototype.mouseMoveTo = function(x,y){
	if(this.mouseDowned === true){
		for(var i in this.listeners){
			if(this.listeners[i].mouseDragTo(x,y) === true) return;
		}
	}
	else{
		for(var i in this.listeners){
			if(this.listeners[i].mouseMoveTo(x,y) === true) return;
		}
	}
};
CanvasContext.prototype.mouseUp = function(x,y){
	this.mouseDowned = false;
	for(var i in this.listeners){
		if(this.listeners[i].mouseUp(x,y) === true) return;
	}
};
CanvasContext.prototype.draw = function(ctx){
	for(var i in this.listeners){
		this.listeners[i].draw(ctx);
	}
};

var min = function(a, b){
	return a > b ? b : a;
}

var max = function(a, b){
	return a > b ? a : b;
}