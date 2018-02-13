/*document.write('<script type="text/javascript" src="/js/piece/piece.js"></script>');*/

function Pieces(){
	this.list = [];			// all pieces
	this.selected = [];		// selected pieces
	this.pushPoint = {x:-1,y:-1};
	this.movedPoint = {x:-1,y:-1};
	this.editRect = {};
};
Pieces.prototype.add = function(piece){
	this.unselectAll();
	this.list.push(piece);
	this.selected.push(piece);
}
Pieces.prototype.rectSelected = function(rect){
	this.unselectAll();
	for(var i in this.list){
		const piece = this.list[i];
		if(rect.containsRect(piece.area)){
			this.selected.push(piece);
		}
	}
}

// Mouse Events ----------------------------------------------------------------------------------------------------------
Pieces.prototype.mouseDown = function(x,y){
	for(var i = this.list.length-1; i >= 0; i--){
	    var piece = this.list[i];
		if(piece.containsPoint(x,y)){
			if(piece.isSelectingPoint(x,y)){
				if(this.isSelected(piece) === false){
					this.unselectAll();
					this.selected.push(piece);
				}
				
				this.pushPoint.x = x;
				this.pushPoint.y = y;
				this.movedPoint.x = x;
				this.movedPoint.y = y;
				return true;
			}
/*			else if(piece.isEditingPoint(x,y)){
				var text = piece.getText(x,y);
				var field = piece.getField(x,y);
				var rect = piece.getEditRect(x,y);
				this.createEditBox(piece, field, text, rect);
				return;
			}*/
		}
	}
	
	// selected nothing
	this.unselectAll();
	
	this.pushPoint.x = x;
	this.pushPoint.y = y;
	
	this.movedPoint.x = x;
	this.movedPoint.y = y;
	
	return true;
}
Pieces.prototype.createEditBox = function(piece, field, text, rect){
	this.editPiece = piece;
	this.editField = field;
	this.editText = text;
	this.editRect = rect;
}
Pieces.prototype.applyEditBox = function(){
	this.editPiece.applyEdit(field, text);
	this.editPiece = {};
	this.editField = {};
	this.editText = {};
	this.editRect = {};
}
Pieces.prototype.mouseUp = function(x,y){
	if(this.pushPoint.x === x && this.pushPoint.y === y){
		this.pushPoint = {x:-1, y:-1};
		this.movedPoint = {x:-1,y:-1};
		return true;
	}
	
	if(this.selected.length == 0){	
		var rx = min(this.pushPoint.x, x);
		var ry = min(this.pushPoint.y, y);
		var rw = max(this.pushPoint.x, x) - rx;
		var rh = max(this.pushPoint.y, y) - ry;
		var rect = new Rect(rx, ry, rw, rh);
		
		this.rectSelected(rect);
	}
	
	this.pushPoint = {x:-1, y:-1};
	this.movedPoint = {x:-1, y:-1};
	
	return true;
}
Pieces.prototype.mouseDragTo = function(x,y){
	if(this.selected.length != 0){
		var dx = x - this.movedPoint.x;
		var dy = y - this.movedPoint.y;
		for(var i in this.selected){
		    const piece = this.selected[i];
		    piece.move(dx,dy);
		}
	}

	this.movedPoint.x = x;
	this.movedPoint.y = y;
	
	return true;
}
Pieces.prototype.mouseMoveTo = function(x,y){
	return true;
}
// ----------------------------------------------------------------------------------------------------------
Pieces.prototype.unselectAll = function(){
	this.selected = [];
}
Pieces.prototype.selectAll = function(){
	this.selected = [];
	for(var i in this.list) this.selected.push(this.list[i]);
}
Pieces.prototype.isSelected = function(piece){
	for(var i in this.list) if(this.selected[i] === piece) return true;
	return false;
}
Pieces.prototype.draw = function(ctx){	
	ctx.fillStyle = "white";
	ctx.fillRect(0,0,ctx.canvas.clientWidth,ctx.canvas.clientHeight);
	
	for(var i in this.list){
		var piece = this.list[i];
    	piece.draw(ctx);
	}
	
	ctx.strokeStyle = "#4444ff";
	ctx.lineWidth = 1;

	for(var i in this.selected){
		var piece = this.selected[i];
    	ctx.strokeRect(piece.area.x-1, piece.area.y-1, piece.area.w+2, piece.area.h+2);
	}
	
	if(this.editRect != {}){

		ctx.strokeStyle = "#8888ff";
		ctx.lineWidth = 2;
		ctx.fillStyle = "#ffffff";
		
		ctx.fillRect(this.editRect.x, this.editRect.y, this.editRect.w, this.editRect.h);
		ctx.strokeRect(this.editRect.x, this.editRect.y, this.editRect.w, this.editRect.h);
		
		ctx.font = "12px Arial";
		ctx.fillStyle = "#000000";
		ctx.textAlign = "left";
		ctx.fillText(this.editText, this.editRect.x + 5, this.editRect.b - 2);
	}

	if(this.pushPoint == this.emptyPoint) return;
	if(this.selected.length != 0) return;
	
	ctx.strokeStyle = "gray";
	ctx.lineWidth = 3;
	ctx.fillStyle = "rgba(255,255,255,0.2)";
	
	var rx = min(this.pushPoint.x, this.movedPoint.x);
	var ry = min(this.pushPoint.y, this.movedPoint.y);
	var rw = max(this.pushPoint.x, this.movedPoint.x) - rx;
	var rh = max(this.pushPoint.y, this.movedPoint.y) - ry;
	
	ctx.fillRect(rx, ry, rw, rh);
	ctx.strokeRect(rx, ry, rw, rh);
};
Pieces.prototype.clear = function(){
	this.list = [];
	this.selected = [];
};
/*Pieces.protype.keyStroke = function(key){
	
}*/