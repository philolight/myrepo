document.write('<script type="text/javascript" src="/js/piece/face.js"></script>');

function Piece(data, skinType, x, y){
	console.log(JSON.stringify(data));
	console.log(JSON.stringify(skinType));
	var lineHeight = 20;
	var w = 220;
	this.data = data;
	this.skinType = skinType;
	this.keyFaces = [];
	this.valueFaces = [];
	
	var rowColor = [{normal:"#cccccc",focused:"#ffffcc"}, {normal:"#eeeeee",focused:"#ffffcc"}];
	var idx = 1;
	this.lines = Object.keys( this.data ).length - 1;
	console.log("this.lines = " + this.lines);
	for(var key in this.data){
		if(key == 'skinType'){
			this.keyFaces["titleFace"] = new TextFace(
					{normal:"#88dd88", focused:"#88dd88"},	// color 
					this.data[key], 						// text
					{ font:"14px Arial Black", color:"black", align:"left" } // font
					);
			this.keyFaces["titleFace"].setArea(new Rect(x, y, w, lineHeight));
		}
		else if(key == 'length'){
			
		}
		else{
			this.keyFaces[key] = new TextFace(rowColor[idx%2], key, { font:"12px Arial", color:"black", align:"left" });
			this.keyFaces[key].setArea(new Rect(x, y + lineHeight * idx, w/2, lineHeight));
			this.valueFaces[key] = new TextFace(rowColor[idx%2], data[key], { font:"12px Arial", color:"black", align:"right" });
			this.valueFaces[key].setArea(new Rect(x + w/2, y + lineHeight * idx, w/2, lineHeight));			
			idx ++;
		}
 	}
	
	this.keyFaces["bottomFace"] = new TextFace({normal:"#bbbbee",focused:"#aaaaff"}, "", { font:"14px Arial Black", color:"black", align:"left" });
	this.keyFaces["bottomFace"].setArea(new Rect(x, y + lineHeight * idx, w, lineHeight));
	
	delete this.data['skinType'];		
	
	this.area = new Rect(x, y, w, lineHeight * (this.lines + 2));
};
Piece.prototype.draw = function(ctx){	
	for(var i in this.keyFaces) this.keyFaces[i].drawNormal(ctx);
	for(var i in this.valueFaces) this.valueFaces[i].drawNormal(ctx);
	
	ctx.strokeStyle = "#aaaaaa";
	ctx.lineWidth = 1;
	ctx.strokeRect(this.area.x, this.area.y, this.area.w, this.area.h);
};
Piece.prototype.move = function(dx, dy){
	this.area.moveOffset(dx,dy);
	for(var i in this.keyFaces)
		this.keyFaces[i].area.moveOffset(dx,dy);
	for(var i in this.valueFaces) 
		this.valueFaces[i].area.moveOffset(dx,dy);
};
Piece.prototype.containsPoint = function(x,y){
	for(var i in this.keyFaces) if(this.keyFaces[i].area.containsPoint(x,y) == true) return true;
	for(var i in this.valueFaces) if(this.valueFaces[i].area.containsPoint(x,y) == true) return true;
	return false;
};
Piece.prototype.isSelectingPoint = function(x,y){
	if(this.area.x <= x && x <= this.area.vc && this.area.y <= y && y <= this.area.b) return true;
	if(this.area.x <= x && x <= this.area.r){
		if(this.area.y <= y && y <= this.area.y + this.lineHeight) return true;
		if( this.area.b - this.lineHight <= y && y <= this.area.b) return true;
	}
	return false;
}
Piece.prototype.isEditingPoint = function(x,y){
	if(this.area.vc <= x && x <= this.area.r && this.area.t + this.lineHeight <= y && y <= this.area.b - this.lineHeight) return true;
	return false;
}
Piece.prototype.getText = function(x,y){
	var i = parseInt(parseInt(y - this.area.y) / this.lineHeight);
	var idx = 0;
	for(var key in this.data){
		if(key == "skinType") continue;
		idx++;
		if(idx == i){
			console.log("getText = " + this.data[key]);
			return this.data[key];
		}
 	}
	return "";
}
Piece.prototype.getField = function(x,y){
	var i = parseInt(parseInt(y - this.area.t) / this.lineHeight);
	var idx = 0;
	for(var key in this.data){
		if(key == "skinType") continue;
		idx++;
		if(idx == i){
			console.log("getField = " + key);
			return key;
		}
 	}
	return "";
}
Piece.prototype.getEditRect = function(x,y){
	var i = parseInt(parseInt(y - this.area.t) / this.lineHeight);
	return new Rect(this.area.vc, this.area.t + this.lineHeight * i, this.area.w/2, this.lineHeight);
}
Piece.prototype.applyEdit = function(field, text){
	this.data[field] = text;
}