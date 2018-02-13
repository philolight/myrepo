document.write('<script type="text/javascript" src="/js/shape/rect.js"></script>');

var drawRectangledText = function(color, text, font, rect, ctx){
	ctx.fillStyle = color;
	ctx.fillRect(rect.x, rect.y, rect.w, rect.h);
	
	// draw text
	ctx.font = font.font;
	ctx.fillStyle = font.color;
	ctx.textAlign = font.align;
	if(ctx.textAlign == "left") ctx.fillText(text, rect.l + 5, rect.b-2);
	if(ctx.textAlign == "right") ctx.fillText(text, rect.r - 5, rect.b-2);
}

function Face(){};
Face.prototype.setArea = function(rect) {
	this.area = rect;
}

function TextFace(color, text, font){
	this.color = color;
	this.text = text;
	this.font = font;	// { font, color, align }
}
TextFace.prototype = new Face;					// super type
TextFace.prototype.constructor = TextFace;		// constructor
TextFace.prototype.parent = Face;				// parent
TextFace.prototype.drawNormal = function(ctx){
//	console.log("normal");
	drawRectangledText(this.color.normal, this.text, this.font, this.area, ctx);
}
TextFace.prototype.drawFocused = function(ctx){
	//console.log("focus");
	drawRectangledText(this.color.focused, this.text, this.font, this.area, ctx);
};
TextFace.prototype.drawPicked = function(ctx){
	//console.log("picked");
	drawRectangledText(this.color.normal, this.text, this.font, this.area, ctx);
};