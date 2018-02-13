document.write('<script type="text/javascript" src="/js/shape/rect.js"></script>');

function Piece(data){
	this.g2d = {};
	this.lineHeight = 20;

	this.data = data;
	this.lines = Object.keys( data ).length - 1;
	
	this.width = 220;
	var newHeight = (this.lines + 2) * this.lineHeight; // top + bottom;
	this.area = new Rect(100, 100 , this.width, newHeight);
};
Piece.prototype.top = {"color" : "#88dd88"};
Piece.prototype.bottom = {"color" : "#bbbbee"};
Piece.prototype.background = {"color" : "#000000"};
Piece.prototype.rowColor = ["#cccccc", "#eeeeee"];
Piece.prototype.strokeStyle = "gray";
Piece.prototype.set = function(rect){
	this.area = rect;
};
Piece.prototype.set = function(x, y, width, height){
	this.area.set(x, y, width, height);
};
Piece.prototype.draw = function(){
	this.g2d.strokeStyle = this.strokeStyle;
	
	//draw background --> line 으로 대체
	this.g2d.strokeStyle = "#222222";
	this.g2d.lineWidth = 3;
	this.g2d.beginPath();
	this.g2d.moveTo(this.area.l + 3, this.area.b + 2);
	this.g2d.lineTo(this.area.r + 2, this.area.b + 2);
	this.g2d.lineTo(this.area.r + 2, this.area.t + 3);
	this.g2d.stroke();
	
	//draw top
	this.g2d.fillStyle = this.top.color;
	this.g2d.fillRect(this.area.x, this.area.y, this.area.width, this.lineHeight);
	
	//draw lines
	for(var i = 1; i <= this.lines; i++){
		this.g2d.fillStyle = this.rowColor[i%2];
		this.g2d.fillRect(this.area.x, this.area.y + this.lineHeight * i, this.area.width, this.lineHeight);
	}
	
	// draw bottom
	this.g2d.fillStyle = this.bottom.color;
	this.g2d.fillRect(this.area.x, this.area.y + this.lineHeight * (this.lines + 1), this.area.width, this.lineHeight);

	var margin = 5;
	// draw data
	this.g2d.font = "14px Arial Black";
	this.g2d.fillStyle = "black";
	
	this.g2d.textAlign = "left";
	this.g2d.fillText(this.data["skinId"],this.area.l + margin, this.area.t + this.lineHeight-2);
	
	this.g2d.font = "12px Arial";
	var idx = 1;
	for(var key in this.data){
//		console.log(key + " " + this.data[key]);
		if(key == "skinId") continue;
		idx++;
		this.g2d.textAlign = "left";
		this.g2d.fillText(key, this.area.l + margin, this.area.t + this.lineHeight * idx - 2);
		this.g2d.textAlign = "right";
		this.g2d.fillText(this.data[key], this.area.r - margin, this.area.t + this.lineHeight * idx - 2);
 	}
	
	this.g2d.strokeStyle = "#aaaaaa";
	this.g2d.lineWidth = 1;
	this.g2d.strokeRect(this.area.x, this.area.y, this.area.width, this.area.height);
};
Piece.prototype.move = function(dx, dy){
	this.area.set(this.area.x + dx, this.area.y + dy, this.area.width, this.area.height);
};
Piece.prototype.containsPoint = function(x,y){
	return this.area.containsPoint(x,y);
};
Piece.prototype.containsRect = function(rect){
	return this.area.containsRect(rect);
};
Piece.prototype.isSelectingPoint = function(x,y){
	if(this.area.x <= x && x <= this.area.x + this.area.width / 2 && this.area.y <= y && y <= this.area.y + this.area.height) return true;
	if(this.area.x <= x && x <= this.area.x + this.area.width){
		if(this.area.y <= y && y <= this.area.y + this.lineHeight) return true;
		if( (this.area.y + this.area.height - this.lineHight) <= y && y <= this.area.y + this.area.height) return true;
	}
	return false;
}
Piece.prototype.isEditingPoint = function(x,y){
	if((this.area.x + this.area.width)/2 <= x && x <= this.area.x + this.area.width && this.area.t + this.lineHeight <= y && y <= this.area.b - this.lineHeight) return true;
	return false;
}
Piece.prototype.getText = function(x,y){
	var i = parseInt(parseInt(y - this.area.y) / this.lineHeight);
	var idx = 0;
	for(var key in this.data){
		if(key == "skinId") continue;
		idx++;
		if(idx == i){
			console.log("getText = " + this.data[key]);
			return this.data[key];
		}
 	}
	return "";
}
Piece.prototype.getField = function(x,y){
	var i = parseInt(parseInt(y - this.area.y) / this.lineHeight);
	var idx = 0;
	for(var key in this.data){
		if(key == "skinId") continue;
		idx++;
		if(idx == i){
			console.log("getField = " + key);
			return key;
		}
 	}
	return "";
}
Piece.prototype.getEditRect = function(x,y){
	var i = parseInt(parseInt(y - this.area.y) / this.lineHeight);
	return new Rect(this.area.x + this.area.width/2, this.area.y + this.lineHeight * i, this.area.width/2, this.lineHeight);
}
Piece.prototype.applyEdit = function(field, text){
	this.data[field] = text;
}