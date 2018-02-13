<!DOCTYPE html>
<html>
<body>

<p>A function is triggered when one of the input fields get focus. The function changes the background-color of the input field.</p>
First name: <input type="text" id="fname" onfocus="myFunction(this.id)"><br>
Last name: <input type="text" id="lname" onfocus="myFunction(this.id)">

<script>
function myFunction(x) {
    document.getElementById(x).style.background = "yellow";
}
</script>

</body>
</html>