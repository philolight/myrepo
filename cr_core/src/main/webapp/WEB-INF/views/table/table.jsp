<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="js/treeview/treeview.js"></script>
<script type="text/javascript" src="js/crud/crud.js"></script>
<script type="text/javascript" src="js/crud/create.js"></script>
<script type="text/javascript" src="js/crud/update.js"></script>
<script type="text/javascript" src="js/shape/rect.js"></script>
<script type="text/javascript" src="js/heatmap/heatmap.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/css/layout.css">
<link rel="stylesheet" type="text/css" href="/resources/css/table.css">
<link rel="stylesheet" type="text/css" href="/resources/css/button.css">
<link rel="stylesheet" type="text/css" href="/resources/css/input.css">
<link rel="stylesheet" type="text/css" href="/resources/css/treeview.css">
<link rel="stylesheet" type="text/css" href="/resources/css/crud/create.css">
</head>

<body>
	<header>
		<h1>Canvas</h1>
	</header>
	
	<div style="width: 100%;">
    <select id="skinTypeButtons" style="width: 210px; float: left;" onmousedown="this.value='';" onchange="onSkinTypeButtonClicked(this.value)"></select>
  </div>

  <section style="width: 100%; float: left;">
	  <input type="button" onClick=onTable()             value="Entity Table">
		<input type="button" onClick=onCreate()            value="Create Event">
		<input type="button" onClick=onRelationalEditor()  value="Relational Editor">
		<input type="button" onClick=onRead()              value="Read Event">
		<input type="button" onClick=onUpdate()            value="Update Event">
		<input type="button" onClick=onDelete()            value="Delete Event">
		<input type="button" onClick=onChart()             value="Chart">
		<input type="button" onClick=InitCreateUpdateTables() value="CreateUpdate">
		<input type="button" onClick=InitHeatMap() value="HeatMap">
	</section>

	<section style="height: 100%; width: 100%; overflow: scroll;">
		<div id="view" style="display=inline-block; float: left;"></div>
	</section>

	<script>	
		var selectedEntity = null;  // 선택된 Entity
	  	var selectedSkinType = null;// 선택된 Enitity의 SkinType
		var list;                   // data list
		var skinTypeMap;            // skinType들이 정의된 list들
		var selectedEvent = "tableTab";
		var tabs = ["tableTab", "createTab", "relationalEditorTab", "readTab", "updateTab", "deleteTab", "chartTab"];
	
		function InitCreateUpdateTables(){
			$('#view').html(
					"<div id='create' style='display=inline-block; float:left;'></div>" +
					"<div id='update' style='display=inline-block; float:left;'></div>"
					);
			CreateTableInit();
			UpdateTableInit();
		}
		
		function InitHeatMap(){
			var content = "<div id='heatmap' style='display=inline-block; float:left;'>";
//			content += "<img id='layout' src='/resources/images/layout.jpg' alt='Layout' height='1024' width='768'>";
//			content += "<img id='layout' src='/resources/images/layout.jpg' style='display:none;' alt='Layout' height='1024' width='768'>";
			content += "<div id='heatMapControl'></div>";
			content += "<div><canvas id='heatMapCanvas' width='1024' height='800'></canvas></div>";
			content += "</div>";
			$('#view').html(content);
			HeatMapInit();
		}
		
		// Table 버튼이 눌렸을 때 호출되는 메소드.
		function onTable() {
			$('#view').html("<div id='tableTab' style='display=inline-block; float:left;'></div>");
	        selectedEvent = "tableTab";
		}
		
		// Create 버튼이 눌렸을 때 호출되는 메소드.
		function onCreate() {
			$('#view').html("<div id='createTab' style='display=inline-block; float:left;'></div>");
			selectedEvent = "createTab";
		  	initCreateTab();
		}
	
		// Create 버튼이 눌렸을 때 화면을 생성하는 메소드.
		function initCreateTab(){
			createInputForm(selectedSkinType);
			if(selectedEntity == undefined) return;
			
			for(key in selectedEntity){
				if(document.getElementById(key) == undefined) continue;
				if(selectedEntity[key] != undefined) document.getElementById(key).value = selectedEntity[key];
			}
		}
		
    // Relational Editor 버튼이 눌렸을 때 호출되는 메소드.
    function onRelationalEditor() {
    	$('#view').html("<div id='relationalEditorTab' style='display=inline-block; float:left;'></div>");
      	selectedEvent = "relationalEditorTab";
      	document.getElementById('relationalEditorTab').style.display = 'inline-block';
      	initRelationalEditorTab();
    }
  
    // Relation 버튼이 눌렸을 때 화면을 생성하는 메소드.
    function initRelationalEditorTab(){
      createRelationalEditorForm(selectedSkinType);
      if(selectedEntity == undefined) return;
      
      for(key in selectedEntity){
        if(document.getElementById(key) == undefined) continue;
        if(selectedEntity[key] != undefined) document.getElementById(key).value = selectedEntity[key];
      }
    }
			
		// Update 버튼이 눌렸을 때 호출되는 메소드.
		function onUpdate() {
			$('#view').html("<div id='updateTab' style='display=inline-block; float:left;'></div>");
			selectedEvent = "updateTab";
			initUpdateTab();
		}
	
		// Update 버튼이 눌렸을 때 호출되는 메소드.
		function initUpdateTab() {
			createUpdateForm(selectedSkinType);
			if (selectedEntity == undefined) return;
	
			for (key in selectedEntity) {
				if (document.getElementById(key) == undefined) continue;
				if (selectedEntity[key] != undefined) document.getElementById(key).value = selectedEntity[key];
			}
		}
	
		// Delete 버튼이 눌렸을 때 호출되는 메소드.
		function onDelete() {
			$('#view').html("<div id='deleteTab' style='display=inline-block; float:left;'></div>");
			selectedEvent = "deleteTab";
		}
		
    // Delete 버튼이 눌렸을 때 호출되는 메소드.
    function onChart() {
    	$('#view').html("<div id='chartTab' style='display=inline-block; float:left;'></div>");
      	selectedEvent = "chartTab";
      	initChartTab();
    }
    
    // Create 버튼이 눌렸을 때 화면을 생성하는 메소드.
    function initChartTab(){
      createChartForm(selectedSkinType);
    }
			
		getSkinTypeMap(); // table.jsp가 실행되었을 때 최초로 호출되는 메소드.
	
		// 서버에게 SkinTypeMap을 요청하고 받은 Map을 skinTypeMap에 저장하는 메소드.
		function getSkinTypeMap() {
			$.ajax({
				url : "/skinner/skinTypeMap",
				type : "POST",
				dataType : "json",
				contentType : 'application/json, charset=utf-8',
				mimeType : 'application/json',
				success : function(map) {
					skinTypeMap = map;
					var dropDown = "";
					
					var keyList = Object.keys(map);
					keyList.sort();
					
					for (var i in keyList) {
						dropDown += "<option value='"+keyList[i]+"'>"+keyList[i]+"</option>";
					}
					
					$("#skinTypeButtons").html(dropDown);
				}
			});
		}
	
		// SkinType Dropdown이 눌렸을 때 호출되는 메소드.
		function onSkinTypeButtonClicked(skinType) {
			selectedSkinType = skinType;
			if(selectedEvent == "tableTab") makeEntityTable(skinType);
			else if(selectedEvent == "createTab") createInputForm(skinType);
			else if(selectedEvent == "relationEditorTab") createRelationalEditorForm(skinType);
 			else if(selectedEvent == "readTab") createInputForm(skinType);
			else if(selectedEvent == "updateTab") createInputForm(skinType);
			else if(selectedEvent == "deleteTab") createInputForm(skinType);
			else if(selectedEvent == "chartTab") createChartForm(skinType);
		}
	
		// Dropdown을 통해 선택된 SkinType에 대한 데이터를 서버로 요청하고, 받은 데이터들을 테이블 형태로 화면에 표시하는 메소드.
		function makeEntityTable(skinType) {
			$.ajax({
				url : "/skinner/skinizedAll",
				type : "POST",
				data : {
					"skinType" : skinType
				},
				success : function(result) {
					var skin = skinTypeMap[selectedSkinType];
					var fields = skin["fields"];

			    var rows = 0, cols = 1;
					var content = "<table id=\"entityTable\"><tr>";
					content += "<th><input type=\"checkbox\" id=\"entityTableSelectAll\" value=\""+(rows++)+"\" onClick=\"rowClicked(this.value)\"></th>";
					for (var i in fields) content += "<th id=\""+fields[i].name+"\" onClick=\"tableSortBy("+ (cols++) +")\">" + fields[i].name + "</th>";
					content += "</tr>";

					for (var i in list) {
						var data = list[i];
						content += "<tr>";
						content += "<td value=\""+rows+"\"><input type=\"checkbox\" name=\"_selected_\" value=\""+(rows++)+"\" onClick=\"rowClicked(this.value)\"></td>";
						for (i in fields) {
							content += "<td>" + data[fields[i].name] + "</td>";
						}
						content += "</tr>";
					}
	
					content +="</table>";
	
					$("#tableTab").html(content);
				}
			});
		}
		
		// table의 Row가 선택 되었을 때 호출되는 메소드.
		function rowClicked(row){
				if(row == 0) $('input[name=_selected_]').prop('checked', document.getElementById("entityTableSelectAll").checked);
				else selectedEntity = list[row-1];
		}
	
		// table의 컬럼명이 선택되었을 때 해당 컬럼을 기준으로 테이블 데이터를 정렬하는 메소드.
		function tableSortBy(cols) {
			  var table, rows, i, dir = 0;
			  table = document.getElementById("entityTable");
			  switching = true;
			  dir = "asc";
			  
			  rows = table.getElementsByTagName("TR");
			  
			  var arr = [];			  
			  for (i = 1; i < rows.length; i++) arr[i-1] = {row : i, value : rows[i].getElementsByTagName("TD")[cols].innerHTML.toLowerCase()};
			  
			  mergeSort(arr, 0, rows.length - 2);
			  
			  var before = [];
			  for (i = 1; i < rows.length; i++) before[i-1] = rows[arr[i-1].row];
			  			  
			  if(before[0].getElementsByTagName("TD")[cols].innerHTML.toLowerCase() == rows[1].getElementsByTagName("TD")[cols].innerHTML.toLowerCase() &&
					  before[rows.length-2].getElementsByTagName("TD")[cols].innerHTML.toLowerCase() == rows[rows.length-1].getElementsByTagName("TD")[cols].innerHTML.toLowerCase()
					  ){
					  dir = "desc";
			  }
			  
	      if (dir == "asc") {
	        for (i = 1; i < (rows.length - 1); i++) rows[i].parentNode.insertBefore(before[i-1], rows[i]);
	      }
	      else{
          for (i = 1; i < (rows.length - 1); i++) rows[i].parentNode.insertBefore(before[rows.length - 1 - i], rows[i]);
	      }
		}
	
		// table 데이터를 정렬하는 합병 정렬 알고리즘.
		function mergeSort(arr, left, right) {
			if (left >= right) return;
			var mid = parseInt((left + right) / 2);
			mergeSort(arr, left, mid);
			mergeSort(arr, mid + 1, right);
			merge(arr, left, mid, right);
		}
	
		// 합병 정렬의 합병 메소드.
		function merge(arr, left, mid, right) {
			var i = left;
			var j = mid + 1;
			var count = 0;
			var narr = [];
			while (i <= mid && j <= right) {
				if (arr[i].value < arr[j].value)
					narr[count++] = arr[i++];
				else
					narr[count++] = arr[j++];
			}
	
			while (i <= mid) narr[count++] = arr[i++];
	
			for (var k = 0; k < count; k++) {
				arr[k + left] = narr[k];
			}
		}
	
		// 데이터 생성(create)을 위한 input form을 생성하는 메소드.
 		function createInputForm(skinType) {
	    	  if(skinType == undefined) return;

          var createTabInit = "<div id=\"fieldName\"></div>";
          createTabInit += "<div id=\"fieldValue\"       style=\"width: 250; height: 30; float: left;\"></div>";
          createTabInit += "<div id=\"fieldType\"        style=\"width: 70; height: 30; float: left;\"></div>";
          createTabInit += "<div id=\"fieldVisible\"     style=\"width: 70; height: 30; float: left;\"></div>";
          createTabInit += "<div id=\"fieldEditable\"    style=\"width: 70; height: 30; float: left;\"></div>";
          createTabInit += "<div id=\"fieldEncryption\"  style=\"width: 100; height: 30; float: left;\"></div>";
          createTabInit += "<div id=\"fieldHideTyping\"  style=\"width: 120; height: 30; float: left;\"></div>";
          createTabInit += "<div id=\"fieldNillable\"    style=\"width: 70; height: 30; float: left;\"></div>";
          createTabInit += "<div id=\"fieldAutoFill\"    style=\"width: 70; height: 30; float: left;\"></div>";
          createTabInit += "<div id=\"fieldIsPk\"        style=\"width: 70; height: 30; float: left;\"></div>";
          createTabInit += "<div id=\"fieldIsFk\"        style=\"width: 70; height: 30; float: left;\"></div>";
          
          $("#createTab").html(createTabInit);
	    	  
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
	        var fieldAutoFill    = labelStyle + "AutoFill</label><br>";
	        var fieldIsPk        = labelStyle + "Pk</label><br>";
	        var fieldIsFk        = labelStyle + "Fk</label><br>";
	        
	        var skin = skinTypeMap[skinType];
	        var fields = skin["fields"];
	        
	        for(i in fields){
	            var field = fields[i];
	            fieldName += "<label>"+field.name+"</label><br>";
	            if(field.hideTyping)  fieldValue += "<input type=\"password\"";
	            else if(field.autoFill) fieldValue += "<input type=\"text\" readonly";
	            else fieldValue += "<input type=\"text\"";
	            fieldValue +=  " id=\"" + field.name + "\"><br>";
	            fieldType        += "<label>" + field.type + "</label><br>";
	            fieldVisible     += "<label style=\" background-color:" + (field.visible == 0 ? "#eeeeee" : "#ee8888") + "\">" + (field.visible == 0 ? "False" : "True") + "</label><br>";
	            fieldEditable    += "<label style=\" background-color:" + (field.editable == 0 ? "#eeeeee" : "#ee8888") + "\">" + (field.editable == 0 ? "False" : "True") + "</label><br>";
	            fieldEncryption  += "<label style=\" background-color:" + (field.encryption == 0 ? "#eeeeee" : "#ee8888") + "\">" + (field.encryption == 0 ? "False" : "True") + "</label><br>";
	            fieldHideTyping  += "<label style=\" background-color:" + (field.hideTyping == 0 ? "#eeeeee" : "#ee8888") + "\">" + (field.hideTyping == 0 ? "False" : "True") + "</label><br>";
	            fieldNillable    += "<label style=\" background-color:" + (field.nillable == 0 ? "#eeeeee" : "#ee8888") + "\">" + (field.nillable == 0 ? "False" : "True") + "</label><br>";
	            fieldAutoFill    += "<label style=\" background-color:" + (field.autoFill == 0 ? "#eeeeee" : "#ee8888") + "\">" + (field.autoFill == 0 ? "False" : "True") + "</label><br>";
	            fieldIsPk        += "<label style=\" background-color:" + (field.isPk == 0 ? "#eeeeee" : "#ee8888") + "\">" + (field.isPk == 0 ? "False" : "True") + "</label><br>";
	            fieldIsFk        += "<label style=\" background-color:" + (field.isFk == 0 ? "#eeeeee" : "#ee8888") + "\">" + (field.isFk == 0 ? "False" : "True") + "</label><br>";
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
	        $("#fieldAutoFill").html(fieldAutoFill);
	        $("#fieldIsPk").html(fieldIsPk);
	        $("#fieldIsFk").html(fieldIsFk);
		}

		// Create 화면의 Submit 버튼이 눌렸을 때 호출되는 메소드.
		function onCreateSubmit() {
			if (selectedSkinType === undefined) return;
			var skin = skinTypeMap[selectedSkinType];
	
			var request = {};
	
			var fields = skin["fields"];
	
			if (fields === undefined) return;
			for (i in fields) {
				var field = fields[i];
				var elem = document.getElementById(field.name);
				request[field.name] = document.getElementById(field.name).value;
			}
	
			console.log(JSON.stringify(request));
	
			$.ajax({
				url : "/create",
				type : "POST",
				dataType : "json",
				mimeType : 'application/json charset=utf-8',
				data : request,
				success : function() {
					console.log('success');
				},
				error : function() {
					console.log('error');
				}
			});
		}
		
	    // Read 버튼이 눌렸을 때 호출되는 메소드.
	    function onRead() {
	      console.log("onRead");
	      selectedEvent = "readTab";
	      tabDisplayNone();
	      tabRemoveKids();
	      document.getElementById('readTab').style.display = 'inline-block';
	      initReadTab();
	    }
	    
	    function initReadTab(){
	    	var colField = 1;
	    	var colFrom = 2;
	      createReadForm(selectedSkinType);
	      if(selectedEntity == undefined) return;
	      
	      var table = document.getElementById("readCriteriaTable");
	      var rows = table.getElementsByTagName("TR");

	      for(var i = 1; i < rows.length; i++){
	    	  var key = rows[i].getElementsByTagName("TD")[colField].innerHTML.toString();
	    	  if(selectedEntity[key] != undefined) rows[i].getElementsByTagName("TD")[colFrom].children[0].value = selectedEntity[key];
	      }
	    }
		
	    // Read화면을 생성하는 메소드
	    function createReadForm(skinType) {
	      if (skinType == undefined) return;
	  
	      var rows = 0;
        var content = "<table id=\"readCriteriaTable\"><tr>";
        content += "<th style=\"width=50;\">Use<input type=\"checkbox\" id=\"criteriaSelectAll\" value=\""+(rows++)+"\" onClick=\"criteriaClicked(this.value)\"></th>";
        content += "<th style=\"width=150;\">Field</th>";
        content += "<th style=\"width=300;\">From</th>";
        content += "<th style=\"width=50;\"></th>";
        content += "<th style=\"width=300;\">To</th>";
        content += "<th style=\"width=200;\">Add</th>";
        content += "<th style=\"width=220;\">List(Del.on.Sel)</th>";
        content += "</tr>";
        
        var skin = skinTypeMap[skinType];
        var fields = skin["fields"];
        
        content += "<tr id=\"cdate\">";
        content += "<td value=\""+rows+"\"><input type=\"checkbox\" name=\"_criteria_selected_\" value=\""+(rows++)+"\" onClick=\"criteriaClicked(this.value)\"></td>";
        content += "<td>"+"cdate"+"</td>";
        content += "<td><input type=\"datetime-local\" id=\"fromDate\"></td>";
        content += "<td>~</td>";
        content += "<td><input type=\"datetime-local\" id=\"toDate\"></td>";
        content += "<td></td>";
        content += "<td></td>";
        content += "</tr>";
                        
        for (var i in fields) {
          var field = fields[i];
          if(field.name == "cdate") continue;
          content += "<tr id=\""+field.name+"\">";
          content += "<td value=\""+rows+"\"><input type=\"checkbox\" name=\"_criteria_selected_\" value=\""+(rows++)+"\" onClick=\"criteriaClicked(this.value)\"></td>";
          content += "<td>"+field.name+"</td>";
          content += "<td><input type=\"text\" value=''></td>";
          content += "<td>~</td>";
          content += "<td><input type=\"text\" value=''></td>";
          content += "<td><input type=\"text\" onkeypress=\"if(event.keyCode==13) {onAddEnter(this, this.value); return false;}\"></td>";
          content += "<td><select id=\""+field.name + "List\" onmousedown=\"this.value='';\" onchange=\"deleteOnSelect(this)\"></select></td>";
          content += "</tr>";
        }
        content +="</table>";
        
        content += "<label style=\"float:left;\">Limit(N) : </label>";
        content += "<input id=\"limit\" type=\"text\" value=1000 style=\"float:left;\"></br></br>";
        content += "<input type=\"submit\" value=\"Read\" onclick=onReadSubmit() style=\"float:none;\">";
        
        $("#readTab").html(content);
                
        var nineHour = 9 * 60 * 60 * 1000;
        document.getElementById('fromDate').value = new Date(new Date().getTime() + nineHour).toISOString().substring(0, 16);
        document.getElementById('toDate').value = new Date(new Date().getTime() + nineHour).toISOString().substring(0, 16);
	    }
	    
	    function criteriaClicked(row){
	        if(row == 0) $('input[name=_criteria_selected_]').prop('checked', document.getElementById("criteriaSelectAll").checked);
	    }
	    
	    function deleteOnSelect(sel){
	    	sel.remove(sel.selectedIndex);
	    }
	    
	    function onAddEnter(td, value){       
        var option = document.createElement("option");
        option.value = value;
        option.text = value;
        document.getElementById(td.closest('tr').getElementsByTagName("TD")[1].innerHTML.toString() + "List").add(option);
        
	      td.value = "";
	    }
	    
	    // Update 화면에서 Submit이 눌렸을 때 호출되는 메소드.
	    function onReadSubmit() {
	    	if (selectedSkinType === undefined) return;
	    	
	    	var request = {from : {}, to : {} , maps : {}, limit : 0};

	      var skin = skinTypeMap[selectedSkinType];
	  
	      var fields = skin["fields"];
	      
	      request.from["skinType"] = selectedSkinType;
	      request.to["skinType"] = selectedSkinType;
	      request.limit = document.getElementById("limit").value;
	  
	      if (fields === undefined) return;
	      for (i in fields) {
	        var field = fields[i];
	        var elem = document.getElementById(field.name);
	        if(elem == undefined) continue;
	        request.from[field.name] = elem.getElementsByTagName("TD")[2].children[0].value;
	        request.to[field.name] = elem.getElementsByTagName("TD")[4].children[0].value;

          var select = document.getElementById(elem.getElementsByTagName("TD")[1].innerHTML.toString() + "List");
          if(select == undefined) continue;
          var options = select.options;
          var innerList = [];
          for(var idx = 0; idx < options.length; idx++){
        	  console.log(options[idx].value);
        	  innerList.push(options[idx].value);
          }
          request.maps[field.name] = innerList;
	      }
	  
	      console.log(JSON.stringify(request));
	  
	      $.ajax({
	    	  url : "/read",
          type : "POST",
          dataType : "json",
          contentType : "application/json; charset=utf-8",
          data : JSON.stringify(request),
	        success : function() {
	          console.log('success');
	        },
	        error : function() {
	          console.log('error');
	        }
	      });
	    }
		    
      // Relational Editor 화면을 생성하는 메소드
      function createRelationalEditorForm(skinType) {
    	  if(selectedEntity != undefined && skinType == selectedEntity["skinType"]){
    	   tree = new TreeHead(null, skinTypeMap[skinType], [selectedEntity], skinTypeMap, refresh);
    	  }
    	  else tree = new TreeHead(null, skinTypeMap[skinType], [], skinTypeMap, refresh);    	  
    	  
    	  refresh();
      }
      
      function refresh(){
    	  var txt = tree.draw();
    	  document.getElementById("relationalEditorTab").innerHTML = txt;
      }
	    
		// Update화면을 생성하는 메소드
		function createUpdateForm(skinType) {
			if (skinType == undefined) return;
	
			var updateTabInit = "<div id=\"fieldName\"></div>";
			updateTabInit += "<div id=\"fieldValue\"       style=\"width: 250; height: 100; float: left;\"></div>";
			updateTabInit += "<div id=\"fieldType\"        style=\"width: 70; height: 100; float: left;\"></div>";
			updateTabInit += "<div id=\"fieldVisible\"     style=\"width: 70; height: 100; float: left;\"></div>";
			updateTabInit += "<div id=\"fieldEditable\"    style=\"width: 70; height: 100; float: left;\"></div>";
			updateTabInit += "<div id=\"fieldEncryption\"  style=\"width: 100; height: 100; float: left;\"></div>";
			updateTabInit += "<div id=\"fieldHideTyping\"  style=\"width: 120; height: 100; float: left;\"></div>";
			updateTabInit += "<div id=\"fieldNillable\"    style=\"width: 70; height: 100; float: left;\"></div>";
			updateTabInit += "<div id=\"fieldAutoFill\"    style=\"width: 70; height: 100; float: left;\"></div>";
			updateTabInit += "<div id=\"fieldIsPk\"    style=\"width: 70; height: 100; float: left;\"></div>";
			updateTabInit += "<div id=\"fieldIsFk\"    style=\"width: 70; height: 100; float: left;\"></div>";
	
			$("#updateTab").html(updateTabInit);
	
			var labelStyle = "<label style=\"background-color: #00aa00; text-align:center; \">";
			var fieldName = labelStyle + "Name</label><br>";
			var fieldValue = labelStyle + "Value</label><br>";
			var fieldConstraint = labelStyle + "Constraint</label><br>";
			var fieldType = labelStyle + "Type</label><br>";
			var fieldVisible = labelStyle + "Visible</label><br>";
			var fieldEditable = labelStyle + "Editable</label><br>";
			var fieldEncryption = labelStyle + "Encryption</label><br>";
			var fieldHideTyping = labelStyle + "Hide Typing</label><br>";
			var fieldNillable = labelStyle + "Nillable</label><br>";
			var fieldAutoFill = labelStyle + "AutoFill</label><br>";
			var fieldIsPk = labelStyle + "Pk</label><br>";
			var fieldIsFk = labelStyle + "Fk</label><br>";
	
			var skin = skinTypeMap[skinType];
			var fields = skin["fields"];
	
			for (i in fields) {
				var field = fields[i];
				fieldName += "<label>" + field.name + "</label><br>";
				if (field.hideTyping)
					fieldValue += "<input type=\"password\"";
				else if (field.editable && field.autoFill == false)
					fieldValue += "<input type=\"text\"";
				else
					fieldValue += "<input type=\"text\" readonly";
	
				fieldValue += " id=\"" + field.name + "\"><br>";
				fieldType += "<label>" + field.type + "</label><br>";
				fieldVisible += "<label style=\" background-color:" + (field.visible == 0 ? "#eeeeee" : "#ee8888") + "\">" + (field.visible == 0 ? "False" : "True") + "</label><br>";
				fieldEditable += "<label style=\" background-color:" + (field.editable == 0 ? "#eeeeee" : "#ee8888") + "\">" + (field.editable == 0 ? "False" : "True") + "</label><br>";
				fieldEncryption += "<label style=\" background-color:" + (field.encryption == 0 ? "#eeeeee" : "#ee8888") + "\">" + (field.encryption == 0 ? "False" : "True") + "</label><br>";
				fieldHideTyping += "<label style=\" background-color:" + (field.hideTyping == 0 ? "#eeeeee" : "#ee8888") + "\">" + (field.hideTyping == 0 ? "False" : "True") + "</label><br>";
				fieldNillable += "<label style=\" background-color:" + (field.nillable == 0 ? "#eeeeee" : "#ee8888") + "\">" + (field.nillable == 0 ? "False" : "True") + "</label><br>";
				fieldAutoFill += "<label style=\" background-color:" + (field.autoFill == 0 ? "#eeeeee" : "#ee8888") + "\">" + (field.autoFill == 0 ? "False" : "True") + "</label><br>";
				fieldIsPk += "<label style=\" background-color:" + (field.isPk == 0 ? "#eeeeee" : "#ee8888") + "\">" + (field.isPk == 0 ? "False" : "True") + "</label><br>";
				fieldIsFk += "<label style=\" background-color:" + (field.isFk == 0 ? "#eeeeee" : "#ee8888") + "\">" + (field.isFk == 0 ? "False" : "True") + "</label><br>";
			}
			fieldValue += "<input type=\"submit\" value=\"Update\" onclick=onUpdateSubmit()>";
	
			$("#fieldName").html(fieldName);
			$("#fieldValue").html(fieldValue);
			$("#fieldType").html(fieldType);
			$("#fieldVisible").html(fieldVisible);
			$("#fieldEditable").html(fieldEditable);
			$("#fieldEncryption").html(fieldEncryption);
			$("#fieldHideTyping").html(fieldHideTyping);
			$("#fieldNillable").html(fieldNillable);
			$("#fieldAutoFill").html(fieldAutoFill);
			$("#fieldIsPk").html(fieldIsPk);
			$("#fieldIsFk").html(fieldIsFk);
		}
	
		// Update 화면에서 Submit이 눌렸을 때 호출되는 메소드.
		function onUpdateSubmit() {
			if (selectedSkinType === undefined) return;
			var skin = skinTypeMap[selectedSkinType];
	
			var request = {};
	
			var fields = skin["fields"];
	
			if (fields === undefined) return;
			for (i in fields) {
				var field = fields[i];
				var elem = document.getElementById(field.name);
				if(field.name == "cdate") datetimeToString(document.getElementById(field.name).value);
				else request[field.name] = document.getElementById(field.name).value;
			}
	
			console.log(JSON.stringify(request));
	
			$.ajax({
				url : "/crud/update",
				type : "POST",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				mimeType : 'application/json',
				data : JSON.stringify(request),
				success : function() {
					console.log('success');
				},
				error : function() {
					console.log('error');
				}
			});
	
			fields = null;
		}
	
		// Delete 화면에서 Submit이 눌렸을 때 호출되는 메소드.
		function onDeleteSubmit() {
			if (selectedSkinType === undefined) return;
			var skin = skinTypeMap[selectedSkinType];
	
			var request = {};
	
			var fields = skin["fields"];
	
			if (fields === undefined) return;
			for (i in fields) {
				var field = fields[i];
				var elem = document.getElementById(field.name);
				console.log(JSON.stringify(elem));
				request[field.name] = document.getElementById(field.name).value;
			}
	
			console.log(JSON.stringify(request));
	
			$.ajax({
				url : "/crud/delete",
				type : "POST",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				mimeType : 'application/json',
				data : JSON.stringify(request),
				success : function() {
					console.log('success');
				},
				error : function() {
					console.log('error');
				}
			});
	
			fields = null;
		}
	</script>
	
	<!-- Chart -->
	<script src="https://d3js.org/d3.v4.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/resources/css/billboard/billboard.css">
	<script type="text/javascript" src="js/billboard/billboard.js"></script>
	<script>
		bb.generate({
		    bindto: "#chartArea",
		    data: {
		        columns: [
		            ["data1", 30, 200, 100, 170, 150, 250],
		            ["data2", 130, 100, 140, 35, 110, 50]
		        ],
		        types: {
		          data1: "line",
		          data2: "area-spline"
		        },
		        colors: {
		          data1: "red",
		          data2: "green"
		        }
		    }
		});

    // Read화면을 생성하는 메소드
    function createChartForm(skinType) {
      if (skinType == undefined) return;
      
      var content = "<div id='chartArea' style='height: 60%; float: none;'></div>";
      content += "<div style='height: auto; float: none;'>";
      content += "<div id='legend' style='float: left;'></div>";
      content += "<div id='axis' style='float: left;'></div>";
      content += "<div id='series' style='float: left;'></div>";
      content += "</div>";
      
      var skin = skinTypeMap[skinType];
      var fields = skin["fields"];
            
      var legend = "<table id='legendTable'><caption>Select Legend</caption><tr>";

      var rows = 0;
      legend += "<th style=\"width=20;\"><input type=\"radio\" name=\"_legend_selected_\" value=\""+(rows++)+"\" onClick=\"legendRadioClicked(this)\"></th>";
      legend += "<th style=\"width=150;\">Field</th>";
      legend += "<th style=\"width=150;\">Add</th>";
      legend += "<th style=\"width=220;\">List(Del.on.Sel)</th>";
      legend += "</tr>";


      for (var i in fields) {
          var field = fields[i];
          legend += "<tr id='"+field.name+"'>";
          legend += "<td value=\""+rows+"\"><input type=\"radio\" name=\"_legend_selected_\" value=\""+(rows++)+"\" onClick=\"legendRadioClicked(this, this.value)\"></td>";
          legend += "<td>"+field.name+"</td>";
          legend += "<td><input type=\"text\" onkeypress=\"if(event.keyCode==13) {onLegendAdded(this, this.value); return false;}\"></td>";
          legend += "<td><select id=\""+field.name + "List\" onmousedown=\"this.value='';\" onchange=\"deleteOnSelect(this)\"></select></td>";
          legend += "</tr>";
      }
      legend +="</table>";
      
      var axis = "<table id='axisTable'><caption>Select Bottom Axis</caption><tr>";
      rows = 0;
      
      axis += "<th style=\"width=20;\"></th>";
      axis += "<th style=\"width=150;\">Field</th>";
      axis += "<th style=\"width=300;\">From</th>";
      axis += "<th style=\"width=50;\"></th>";
      axis += "<th style=\"width=300;\">To</th>";

      axis += "<tr id=\"cdate\">";
      axis += "<td value=\""+rows+"\"><input type=\"radio\" name=\"_axis_selected_\" checked='true' value=\""+(rows++)+"\" onClick=\"axisRadioClicked(this)\"></td>";
      axis += "<td>"+"cdate"+"</td>";
      axis += "<td><input type=\"datetime-local\" id=\"fromDate\"></td>";
      axis += "<td>~</td>";
      axis += "<td><input type=\"datetime-local\" id=\"toDate\"></td>";
      axis += "</tr>";
      
      for (var i in fields) {
        var field = fields[i];
        if(field.name == "cdate") continue;
        axis += "<tr id=\""+field.name+"\">";
        axis += "<td value=\""+rows+"\"><input type=\"radio\" name=\"_axis_selected_\" value=\""+(rows++)+"\" onClick=\"axisRadioClicked(this)\"></td>";
        axis += "<td>"+field.name+"</td>";
        axis += "<td><input type=\"text\" value=''></td>";
        axis += "<td>~</td>";
        axis += "<td><input type=\"text\" value=''></td>";
        axis += "</tr>";
      }
      axis +="</table>";
      
      var series = "<table id='seriesTable'><caption>Select Series</caption><tr>";
      rows = 0;
      
      series += "<th style=\"width=20;\"><input type=\"checkbox\" name=\"_series_selected_\" value=\""+(rows++)+"\" onClick=\"seriesCheckboxClicked(this, this.value)\"></th>";
      series += "<th style=\"width=150;\">Field</th>";
      
      for (var i in fields) {
        var field = fields[i];
        series += "<tr id=\""+field.name+"\">";
        series += "<td value=\""+rows+"\"><input type=\"checkbox\" name=\"_series_selected_\" value=\""+(rows++)+"\" onClick=\"seriesCheckboxClicked(this, this.value)\"></td>";
        series += "<td>"+field.name+"</td>";
        series += "</tr>";
      }
      series +="</table>";
      
      content += "<div style='height: auto; float: none;'>";
      content += "<label style=\"float:left;\">Limit(N) : </label>";
      content += "<input id=\"limit\" type=\"text\" value=1000 style=\"float:left;\"></br></br>";
      content += "<input type=\"submit\" value=\"Chart\" onclick=onChartSubmit() style=\"float:none;\">";
      content += "</div>";
      
      $("#chartTab").html(content);
      $("#legend").html(legend);
      $("#axis").html(axis);
      $("#series").html(series);
              
      var nineHour = 9 * 60 * 60 * 1000;
      document.getElementById('fromDate').value = new Date(new Date().getTime() + nineHour).toISOString().substring(0, 16);
      document.getElementById('toDate').value = new Date(new Date().getTime() + nineHour).toISOString().substring(0, 16);
    }
    
    function legendRadioClicked(radio, row){
    	if(row == 0) $('input[name=_legend_selected_]').prop('checked', false);
    	else{
    		  $('input[name=_legend_selected_]').prop('checked', false);
    		  radio.checked = true;
    	}
    }
    
    function axisRadioClicked(radio){
      $('input[name=_axis_selected_]').prop('checked', false);
      radio.checked = true;
    }
    
    function seriesCheckboxClicked(checkbox, row){
    	if(row == 0) $('input[name=_series_selected_]').prop('checked', checkbox.checked);
    }
        
    function onLegendAdded(td, value){
      var option = document.createElement("option");
      option.value = value;
      option.text = value;
      document.getElementById(td.closest('tr').getElementsByTagName("TD")[1].innerHTML.toString() + "List").add(option);
      
      td.value = "";
    }
    
    // Chart 화면에서 Submit이 눌렸을 때 호출되는 메소드.
    function onChartSubmit() {
      if (selectedSkinType === undefined) return;
      
      var request = {skinType : selectedSkinType, legend : {}, axis : {}, series : [], limit : 0};

      var skin = skinTypeMap[selectedSkinType];
        
      request.limit = document.getElementById("limit").value;
      
      var legend = document.getElementById("legendTable");
      
      for(var i = 1; i < legend.getElementsByTagName("TR").length; i++){
    	  var tr = legend.getElementsByTagName("TR")[i];
    	  var radioChecked = tr.getElementsByTagName("TD")[0].children[0].checked;
        var fieldName = tr.getElementsByTagName("TD")[1].innerHTML.toString();
    	  if(radioChecked){ // Get all items
	    	  request.legend[fieldName] = [];
    	  }
    	  else{ // Get selected items
 	        var select = document.getElementById(tr.getElementsByTagName("TD")[1].innerHTML.toString() + "List");
 	        if(select == undefined) continue;
 	        var options = select.options;
 	        if(options.length == 0) continue;
 	        var innerList = [];
 	        for(var idx = 0; idx < options.length; idx++){
 	          innerList.push(options[idx].value);
 	        }
 	        request.legend[fieldName] = innerList;
    	  }
      }
      
      var axis = document.getElementById("axisTable");
      
      for(var i = 1; i < axis.getElementsByTagName("TR").length; i++){
    	   var tr = axis.getElementsByTagName("TR")[i];
          var radioChecked = tr.getElementsByTagName("TD")[0].children[0].checked;
          if(radioChecked == false) continue;
          
          var fieldName = tr.getElementsByTagName("TD")[1].innerHTML.toString();
          
          request.axis["fieldName"] = tr.getElementsByTagName("TD")[1].innerHTML.toString();
          if(fieldName == "cdate"){
        	  request.axis["from"] = datetimeToString(tr.getElementsByTagName("TD")[2].children[0].value);
        	  request.axis["to"] = datetimeToString(tr.getElementsByTagName("TD")[4].children[0].value);
          }
          else{
        	  request.axis["from"] = tr.getElementsByTagName("TD")[2].children[0].value;
        	  request.axis["to"] = tr.getElementsByTagName("TD")[4].children[0].value;
          }
          break;
      }
      
      var series = document.getElementById("seriesTable");
      for(var i = 1; i < series.getElementsByTagName("TR").length; i++){
         var tr = series.getElementsByTagName("TR")[i];
          var radioChecked = tr.getElementsByTagName("TD")[0].children[0].checked;
          if(radioChecked == false) continue;
          
          var fieldName = tr.getElementsByTagName("TD")[1].innerHTML.toString();
          
          request.series.push(fieldName);
      }
  
      console.log(JSON.stringify(request));
  
      $.ajax({
        url : "/chart",
        type : "POST",
        dataType : "json",
        contentType : "application/json; charset=utf-8",
        data : JSON.stringify(request),
        success : function(result) {
          console.log('success');
          
          console.log(result);

          var input = {x : "<axis>", columns :[[]], colors : {}};
          var i = 0;
          for(key in result){
            input.columns.push([]);
            input.columns[i].push(key);
            input.columns[i] = input.columns[i].concat(result[key]);
            i++;
          }
                    
          console.log(JSON.stringify(input));
          
          if(Number(input.x[1]) == NaN) input.type = "bar";
          else input.type = "line";
          
          bb.generate({
              bindto: "#chartArea",
              size: { height: 520, },
              padding: { top: 40, right: 100, bottom: 40, left: 100 },
              data: input,
              axis: { x: { type: "category", tick: { rotate: 75, multiline: false }, height: 70 } },
          });
        },
        error : function() {
          console.log('error');
        }
      });
    }
    
    var colors = [
      '#ffff00',
    	'#ff0000',
    	'#0000ff',
    	'#00ff00',
    	'#00ffff',
    	'#880000',
    	'#000088',
    	'#008800',
    	'#888800',
    	'#008888',
    	'#880088'
    	];
    
    function colorPicker(i){
    	return colors[i % colors.length];
    }
    
    function datetimeToString(value){
    	console.log(value);
    	if(value == "") return "";
    	var ten    = function(x) { return x < 10 ? '0'+x : x};
    	var datetime = new Date(value);
    	var ret = datetime.getFullYear() + ten(datetime.getMonth() + 1) + ten(datetime.getDate()) + ten( datetime.getHours() ) + ten( datetime.getMinutes() ) + "00";
    	return ret;
    }
	</script>

	<%@ include file="../footer.jsp"%>

</body>
</html>
