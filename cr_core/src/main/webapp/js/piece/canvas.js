document.write('<script type="text/javascript" src="js/piece/context.js"></script>');
var skinTypeMap;
var canvas = document.getElementById("objectCanvas");
var ctx = canvas.getContext("2d");
$('body').on('contextmenu', '#objectCanvas', function(e){ return false; });

const canvasContext = new CanvasContext();
var pieces = new Pieces();
canvasContext.addListener(pieces);
  
for(var i = 0; i < 10; i++){
	pieces.add(new Piece({skinType:"Unknown",name:"no name",value:"<null>"}, {skinType:"Unknown",cdate:"201801234567"}, 100, 100));
}

pieces.draw(ctx);

var isEmpty = function(value){ if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){ return true }else{ return false } };

function getMousePos(canvas, event) {
    var rect = canvas.getBoundingClientRect();
    return {
      x: event.clientX - rect.left,
      y: event.clientY - rect.top
    };
}

canvas.addEventListener('mousedown', function(event) {
	lastDownTarget = event.target;
	  const p = getMousePos(canvas, event);
	  console.log(p.x + ", " + p.y);
	  canvasContext.mouseDown(p.x, p.y);
	  canvasContext.draw(ctx);
	}, false);

	canvas.addEventListener('mousemove', function(event) {
	  const p = getMousePos(canvas, event);
	  //console.log(p.x + ", " + p.y);
	  canvasContext.mouseMoveTo(p.x, p.y);
	  canvasContext.draw(ctx);
	}, false);

	canvas.addEventListener('mouseup', function(event) {
	  const p = getMousePos(canvas, event);
	  canvasContext.mouseUp(p.x, p.y);
	  canvasContext.draw(ctx);
	}, false);
	
	var lastDownTarget;
	window.onload = function() {
	    document.addEventListener('keyup', function(event) {
	        if(lastDownTarget == canvas) {
	            console.log(event.keyCode + " down");
	        }
	    }, false);

	    document.addEventListener('keydown', function(event) {
	        if(lastDownTarget == canvas) {
	        	console.log(event.keyCode + " up");
	        }
	    }, false);
	}