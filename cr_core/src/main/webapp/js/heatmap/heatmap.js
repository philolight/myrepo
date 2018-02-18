var heatMap;

function HeatMapInit() {
//	$('body').on('contextmenu', '#heatMapCanvas', function(e){ return false; });
	heatMap = new HeatMap(5);
	heatMap.drawControls();
	heatMap.refresh();
}

function HeatMap(degree){
	this.canvas = document.getElementById("heatMapCanvas");
	this.ctx = this.canvas.getContext("2d");
	this.w = this.canvas.width;
	this.h = this.canvas.height;
	this.points = [];
	this.pointMap = [];
	this.degree = degree;
	this.mapDegree = 100;
	this.map = [];
	this.vMax = 0;
	this.mapArea = new Rect(0,0,this.w-100, this.h);
	this.barArea = new Rect(this.w-100,0,100, this.h);
	this.image = {};
	
	for(var i = 0; i < 100; i++){
		this.points.push(new Point(i, Math.random() * 1024, Math.random() * 768, Math.random() * 100));
	}
	this.pointProjection();
	this.drawHeatMap();
	
	document.getElementById("heatMapCanvas").addEventListener('mousemove', function(event) {
		var rect = document.getElementById("heatMapCanvas").getBoundingClientRect();
		  const p = {x:event.clientX - rect.left , y:event.clientY - rect.top};
		  heatMap.onMouseMove(p);
	}, false);
}
HeatMap.prototype.drawButtons = function(){
	// 층, 단위(day, hour, realtime), 시간, next, prev, 
	var txt ="";
	txt += "<div style='clear:left; float:none;'>" +
		"<select id='heatMapFloor' onmousedown=\"this.value='';\" onchange=\"heatMap.onFloorChanged(this)\"></select>" +
		"<select id='heatMapUnit' onmousedown=\"this.value='';\" onchange=\"heatMap.onUnitChanged(this)\">" +
			"<option value='Day'>Day</option>" +
			"<option value='Hour'>Hour</option>" +
			"<option value='Now'>Now</option>" +
		"</select>" +
		"<input type='datetime-local' id='time'>" +
		"<input type='button' onClick=heatMap.onBtnPrev() value='Prev'>" +
		"<input type='button' onClick=heatMap.onBtnNext() value='Next'>" +
		"</div>";
	
	return txt;
}
HeatMap.prototype.drawControls = function(){
	var txt = this.drawButtons();
	$('#heatMapControl').html(txt);	
}
HeatMap.prototype.pointProjection = function(){
	var ratio = 1;
	var xMax = 0, yMax = 0;
	for(i in this.points){
		if(this.points[i].x > xMax) xMax = this.points[i].x;
		if(this.points[i].y > yMax) yMax = this.points[i].y;
	}
	
	ratio = Math.min(this.mapArea.w / xMax, this.mapArea.h / yMax);
	ratio = ratio * 0.95;
	
	for(i in this.points){
		this.points[i].x = this.points[i].x * ratio;
		this.points[i].y = this.points[i].y * ratio;
	}
	
	
	
	var yRooms = parseInt(yMax / this.mapDegree) + 1;
	var xRooms = parseInt(xMax / this.mapDegree) + 1;
	this.pointMap = new Array(yRooms);
	for(var i = 0; i < yRooms; i++){
		this.pointMap[i] = new Array(xRooms);
		for(var j = 0; j < xRooms; j++) this.pointMap[i][j] = [];
	}
	
	for(var i in this.points){
		var p = this.points[i];
		this.pointMap[parseInt(p.y/this.mapDegree)][parseInt(p.x/this.mapDegree)].push(p);
	}
}
HeatMap.prototype.drawHeatMap = function(){
	this.backCanvas = document.createElement("canvas");
	this.backCanvas.width = this.w;
	this.backCanvas.height = this.h;
	this.backCtx = this.backCanvas.getContext("2d");
	
	this.makeHeatMap();
	
	this.backCtx.fillStyle = "#fff";
	this.backCtx.fillRect(0,0,this.w,this.h);
	
    this.backCtx.drawImage(img,0,0,this.mapArea.w,this.h);
	
	for(var i in this.map){
		for(var j in this.map[i]){
			this.backCtx.fillStyle = this.color(this.map[i][j]);
			this.backCtx.fillRect(j * this.degree, i * this.degree, this.degree, this.degree);
		}
	}
	
	var margin = 10;
	var barX = this.barArea.x + margin;
	var barY = this.barArea.h / 3;
	var barBottom = this.barArea.h / 3 * 2;
	var barWidth = (this.barArea.w - margin * 2) / 4;
	var barHeight = this.barArea.h / 3;
	
	var h = barHeight / pal.length;
	
	for(var i = 0; i < pal.length; i++){
		this.backCtx.fillStyle = this.color( (i / (pal.length-1)) * this.vMax);
		this.backCtx.fillRect(barX, barBottom - (i+1) * h, barWidth, h);
		
		var v = parseInt(((i+1) / (pal.length-1)) * this.vMax);
		var text = "";
		if(v > 1000000) text = parseInt(v / 100000)/10 + "M";
		else if(v > 1000) text = parseInt(v / 100)/10 + "K";
		else text = v;
		this.backCtx.fillStyle = "#000";
		this.backCtx.fillText(text, barX + barWidth + margin, barBottom - (i+1) * h);
	}
	
	this.backCtx.beginPath();
	this.backCtx.moveTo(barX + barWidth, barY);
	this.backCtx.lineTo(barX + barWidth, barBottom - h);
	for(var i = 0; i < pal.length; i++){
		this.backCtx.moveTo(barX + barWidth, barY + i * h);
		this.backCtx.lineTo(barX + barWidth + 5, barY + i * h);
	}
	this.backCtx.stroke();
	
	for(var i in this.points){
		this.points[i].draw(this.backCtx);
	}
}
HeatMap.prototype.onFloorChanged = function(){
	
}
HeatMap.prototype.onUnitChanged = function(){
	
}
HeatMap.prototype.onBtnPrev = function(){
	
}
HeatMap.prototype.onBtnNext = function(){
	
}
var img = new Image();
img.onload = function () {
	console.log("loaded");
}
img.src = "resources/images/layout.jpg";
HeatMap.prototype.refresh = function() {
	this.ctx.drawImage(this.backCanvas, 0, 0, this.w, this.h, 0, 0, this.w, this.h);
}
HeatMap.prototype.makeHeatMap = function(){
	this.map = new Array(parseInt(this.mapArea.h / this.degree) + 1); 
	for(var i = 0; i < this.map.length; i++) this.map[i] = new Array(parseInt(this.mapArea.w / this.degree) + 1);
	for(var i = 0; i < this.map.length; i++){
		for(var j = 0; j < this.map[0].length; j++){
			this.map[i][j] = 0;
		}
	}
	for(var i = 0; i < this.map.length; i++){
		for(var j = 0; j < this.map[0].length; j++){
			for(var p in this.points){
				this.map[i][j] += this.points[p].getValue(j * this.degree, i * this.degree);
			}
		}
	}
	
	this.vMax = 0;
	for(var i = 0; i < this.map.length; i++){
		for(var j = 0; j < this.map[0].length; j++){
			if(this.map[i][j] > this.vMax) this.vMax = this.map[i][j];
		}
	}
}

var pal = [
		[1,1,1],
		[0.73,0.73,1],
		[0.48,0.48,1],
		[0.2,0.2,1],
		[0,1,1],
		[0,1,0],
		[1,1,0],
		[1,0.66,0],
		[1,0.33,0],
		[1,0,0]	
		];

HeatMap.prototype.color = function(v){
	if(v > this.vMax) v = this.vMax;
	
	var value = v / (this.vMax);

	var pi = parseInt(value * (pal.length-1));
	if(pi >= (pal.length-1)) pi = (pal.length-2);

	value = value * (pal.length-1) - pi;
		
	var colorDegree = 7;
	var r = parseInt(parseInt((pal[pi][0] * (1.0-value)+ pal[pi + 1][0] * value)*colorDegree)*(255/colorDegree));
	var g = parseInt(parseInt((pal[pi][1] * (1.0-value)+ pal[pi + 1][1] * value)*colorDegree)*(255/colorDegree));
	var b = parseInt(parseInt((pal[pi][2] * (1.0-value)+ pal[pi + 1][2] * value)*colorDegree)*(255/colorDegree));
	
	return "rgba("+r+","+g+","+b+", 0.65)";
}
HeatMap.prototype.onMouseMove = function(p){
	this.refresh();
	
	var block = this.pointMap[parseInt(p.y/this.mapDegree)][parseInt(p.x/this.mapDegree)];
	var minDis = Number.MAX_SAFE_INTEGER;
	var pointIndex = -1;
	for(i in block){
		var dis = Math.sqrt((block[i].x - p.x) * (block[i].x - p.x) + (block[i].y - p.y) * (block[i].y - p.y));
		if(minDis > dis){
			minDis = dis;
			pointIndex = i;
		}
	}
	
	if(minDis < 20){
		var p = block[pointIndex];
		this.ctx.fillStyle = "#f08";
		this.ctx.fillRect(p.x-3, p.y-3, 7,7);
		
		var margin = 10;
		var infoWidth = 100;
		var infoHeight = 20;
		var infoNum = 4;
		var x = p.x > this.w/2? p.x - infoWidth - margin : p.x + margin;
		var y = p.y > this.h/2? p.y - infoHeight * infoNum - margin : p.y + margin;

		var infoColor = [ "rgba(255,255,255,0.85)", "rgba(230,230,230,0.85)" ];
		
		this.ctx.font = "14px Arial";
		
		this.ctx.fillStyle = "rgba(255,128,255, 0.85)";
		this.ctx.fillRect(x, y + infoHeight * 0, infoWidth, infoHeight);
		this.ctx.fillStyle = "#000";
		this.ctx.textAlign = "left";
		this.ctx.fillText("Name", x + margin, y + infoHeight - 5);
		this.ctx.textAlign = "right";
		this.ctx.fillText(p.name, x + infoWidth - margin, y + infoHeight - 5);
		
		this.ctx.fillStyle = infoColor[0];
		this.ctx.fillRect(x, y + infoHeight * 1, infoWidth, infoHeight);
		this.ctx.fillStyle = "#000";
		this.ctx.textAlign = "left";
		this.ctx.fillText("X", x + margin, y + infoHeight * 2 - 5);
		this.ctx.textAlign = "right";
		this.ctx.fillText(parseInt(p.x), x + infoWidth - margin, y + infoHeight * 2 - 5);
		
		this.ctx.fillStyle = infoColor[1];
		this.ctx.fillRect(x, y + infoHeight * 2, infoWidth, infoHeight);
		this.ctx.fillStyle = "#000";
		this.ctx.textAlign = "left";
		this.ctx.fillText("Y", x + margin, y + infoHeight * 3 - 5);
		this.ctx.textAlign = "right";
		this.ctx.fillText(parseInt(p.y), x + infoWidth - margin, y + infoHeight * 3 - 5);
		
		this.ctx.fillStyle = infoColor[0];
		this.ctx.fillRect(x, y + infoHeight * 3, infoWidth, infoHeight);
		this.ctx.fillStyle = "#000";
		this.ctx.textAlign = "left";
		this.ctx.fillText("Value", x + margin, y + infoHeight * 4 - 5);
		this.ctx.textAlign = "right";
		this.ctx.fillText(parseInt(p.value), x + infoWidth - margin, y + infoHeight * 4 - 5);
		
	}
}

function Point(name, x, y, value){
	this.name = name;
	this.x = x;
	this.y = y;
	this.r = 100;
	this.value = value;
}
Point.prototype.getValue = function(x, y){
	var dis = Math.sqrt((this.x-x)*(this.x-x) + (this.y-y)*(this.y-y));
	if(dis > this.r) return 0;
	return (this.r - dis) / this.r * this.value;
}
Point.prototype.draw = function(ctx){
	ctx.fillStyle = "#f0f";
	ctx.fillRect(this.x-2, this.y-2, 5,5);
	ctx.fillStyle = "#000";
	ctx.fillText(this.name, this.x+5, this.y+2);	
}