<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/css/layout.css">
</head>

<body>
	<header>
    <h1>Canvas</h1>
	</header>
	<%@ include file="canvasNav.jsp"%>

	<section style="height:100%; width:100%; overflow: scroll;">
		<div id="messageButtons" style="width: 210px;float: left;"></div>
		<div
			style="width: 1024; height: 800; float: left;">
			<canvas id="objectCanvas" width="1024" height="800"></canvas>
 			<script type="text/javascript" src="js/shape/rect.js"></script>
 			<script type="text/javascript" src="js/piece/face.js"></script>
			<script type="text/javascript" src="js/piece/piece.js"></script>
			<script type="text/javascript" src="js/piece/pieces.js"></script>
			<script type="text/javascript" src="js/piece/context.js"></script>
			<script type="text/javascript" src="js/piece/canvas.js"></script>
		</div>
	</section>

	<script>
		getSkinIdMap();

		function getSkinIdMap() {
			$.ajax({
				url : "/skinnerMap",
				type : "POST",
				dataType : "json",
				contentType : 'application/json, charset=utf-8',
				mimeType : 'application/json',
				success : function(map) {
					skinTypeMap = map;
					var content = "";
					for ( var key in map) {
						content += "<input class=\"button\" type=\"objectButton\" value=\"";
						content += key;
						content += "\"  onclick=";
						content += "onMessageButtonClicked(this.value)>";
					}

					$("#messageButtons").html(content);
				}
			});
		}

		function onMessageButtonClicked(skinType) {
			requestSkinAll(skinType);
		}

		function requestSkinAll(skinType) {
			$.ajax({
				url : "/canvas/skinizedAll",
				type : "POST",
				data : {
					"skinType" : skinType
				},
				success : function(result) {
					var list = JSON.parse(result);
					pieces.clear();
					var x = 10;
					var y = 10;
					for ( var i in list) {
						var data = list[i];
						var piece = new Piece(data, skinTypeMap[data["skinType"]], x, y);
						pieces.add(piece);

						x = x + 240;
						if (x > 4 * 240) {
							x = 10;
							y = y + (Object.keys(list[i]).length + 2) * 20 + 20;
						}
					}
				}
			});
		}
	</script>

	<%@ include file="../footer.jsp"%>

</body>
</html>
