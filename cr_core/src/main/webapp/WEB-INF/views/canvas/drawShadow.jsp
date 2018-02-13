<!DOCTYPE html>
<html>
<body>

<canvas id="myCanvas" width="1024" height="768" style="border:1px solid #d3d3d3;">
Your browser does not support the HTML5 canvas tag.</canvas>

<script>

var c = document.getElementById("myCanvas");
var ctx = c.getContext("2d");
ctx.shadowBlur = 20;
ctx.fillStyle = "red";

ctx.shadowColor = "black";
ctx.fillRect(20, 20, 100, 80);

ctx.shadowColor = "yellow";
ctx.fillRect(140, 20, 100, 80);

</script>

<p><strong>Note:</strong> The canvas tag is not supported in Internet 
Explorer 8 and earlier versions.</p>

</body>
</html>
