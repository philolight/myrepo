<!DOCTYPE html>
<html>
<body>

<img onmousemove="bigImg(this)" onmouseout="normalImg(this)" border="0" src="smiley.gif" alt="Smiley" width="32" height="32">

<p>The function bigImg() is triggered when the user mouse pointer is moved over the image. This function enlarges the image.</p>
<p>The function normalImg() is triggered when the mouse pointer is moved out of the image. That function sets the height and width of the image back to normal.</p>

<script>
function bigImg(x) {
    x.style.height = "64px";
    x.style.width = "64px";
}

function normalImg(x) {
    x.style.height = "32px";
    x.style.width = "32px";
}
</script>

</body>
</html>