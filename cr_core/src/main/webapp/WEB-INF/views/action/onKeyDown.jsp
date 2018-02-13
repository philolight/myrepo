<!DOCTYPE html>
<html>
<body>

<p>A function is triggered when the user is pressing a key in the input field.</p>

<!-- <input type="text" onkeydown="onKeyDownFunction()"> -->
<input type="text" id="keyIn">

<script>
var keyIn = document.getElementById('keyIn')
	keyIn.onkeydown = function(event) {
		  alert( '키코드: ' + event.keyCode );
	}
</script>

</body>
</html>