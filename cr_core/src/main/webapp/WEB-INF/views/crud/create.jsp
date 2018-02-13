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
<link rel="stylesheet" type="text/css" href="/resources/css/input.css">
</head>

<body>
	<header>
    <h1>Create Event</h1>
	</header>
	<%@ include file="../canvas/canvasNav.jsp"%>

	<section style="height:100%; width:100%; overflow: scroll;">
		<div id="messageButtons"  style="width: 210;  float: left;"></div>
		<div id="create">
		  <div id="fieldName"       style="width : 200; height : 100; float: left;"></div>
      <div id="fieldValue"      style="width : 250; height : 100; float: left;"></div>
      <div id="fieldType"       style="width : 100; height : 100; float: left;"></div>
      <div id="fieldVisible"    style="width : 100; height : 100; float: left;"></div>
      <div id="fieldEditable"   style="width : 100; height : 100; float: left;"></div>
      <div id="fieldEncryption" style="width : 100; height : 100; float: left;"></div>
      <div id="fieldHideTyping" style="width : 150; height : 100; float: left;"></div>
      <div id="fieldNillable"   style="width : 100; height : 100; float: left;"></div>
		</div>
	</section>

	<script>
	  var skinTypeMap;
	  var selectedSkinType;
	  
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
    	  selectedSkinType = skinType;
        createInputForm(skinType);
    }

    function createInputForm(skinType) {
    	var labelStyle = "<label style=\"background-color: #00aa00; text-align:center; \">";
     	var fieldName        = labelStyle + "Name</label><br>";
    	var fieldValue       = labelStyle + "Value</label><br>";
    	var fieldConstraint  = labelStyle + "Constraint</label><br>";
    	var fieldType        = labelStyle + "Type</label><br>";
    	var fieldVisible     = labelStyle + "Visible</label><br>";
    	var fieldEditable    = labelStyle + "Editable</label><br>";
    	var fieldEncryption  = labelStyle + "Encryption</label><br>";
    	var fieldHideTyping  = labelStyle + "Hide Typing</label><br>";
    	var fieldNillable    = labelStyle + "Nillable</label><br>";
    	
    	var skin = skinTypeMap[skinType];
     	var fields = skin["fields"];
     	
    	for(i in fields){
    		  var field = fields[i];
          fieldName += "<label>"+field.name+"</label><br>";
          if(field.hideTyping)  fieldValue += "<input type=\"password\"";
          else fieldValue += "<input type=\"text\"";
          fieldValue +=  "id=\"" + field.name + "\"><br>";
    	    fieldType        += "<label>" + field.type + "</label><br>";
    	    fieldVisible     += "<label style=\" background-color:" + (field.visible == 0 ? "#eeeeee" : "#ee8888") + "\">" + (field.visible == 0 ? "False" : "True") + "</label><br>";
    	    fieldEditable    += "<label style=\" background-color:" + (field.editable == 0 ? "#eeeeee" : "#ee8888") + "\">" + (field.editable == 0 ? "False" : "True") + "</label><br>";
    	    fieldEncryption  += "<label style=\" background-color:" + (field.encryption == 0 ? "#eeeeee" : "#ee8888") + "\">" + (field.encryption == 0 ? "False" : "True") + "</label><br>";
    	    fieldHideTyping  += "<label style=\" background-color:" + (field.hideTyping == 0 ? "#eeeeee" : "#ee8888") + "\">" + (field.hideTyping == 0 ? "False" : "True") + "</label><br>";
    	    fieldNillable    += "<label style=\" background-color:" + (field.nillable == 0 ? "#eeeeee" : "#ee8888") + "\">" + (field.nillable == 0 ? "False" : "True") + "</label><br>";
    	}
    	fieldValue += "<input type=\"submit\" value=\"Create\" onclick=onCreateSubmit()>";
    	    	
    	$("#fieldName").html(fieldName);
     	$("#fieldValue").html(fieldValue);
     	$("#fieldType").html(fieldType);
     	$("#fieldVisible").html(fieldVisible);
     	$("#fieldEditable").html(fieldEditable);
     	$("#fieldEncryption").html(fieldEncryption);
     	$("#fieldHideTyping").html(fieldHideTyping);
     	$("#fieldNillable").html(fieldNillable);
    }
    
     function onCreateSubmit() {
     	  if(selectedSkinType === undefined) return;
     	  var skin = skinTypeMap[selectedSkinType];
     	  
     	  var request = {};
        
        var fields = skin["fields"];

        if(fields === undefined) return;
        for(i in fields){
        	var field = fields[i];
        	var elem = document.getElementById(field.name);
        	console.log(JSON.stringify(elem));
        	request[field.name] = document.getElementById(field.name).value;
        }
        
        console.log(JSON.stringify(request));        

        $.ajax({
            url: "/crud/create",
            type:"POST",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            mimeType : 'application/json',
            data: JSON.stringify(request),
            success: function(){
              console.log('success');
            },
            error: function(){
              console.log('error');
            }
          });
       
        fields = null;
    }
	</script>

	<%@ include file="../footer.jsp"%>

</body>
</html>
