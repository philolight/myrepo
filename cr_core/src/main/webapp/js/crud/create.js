var createTable;

function CreateTableInit() {
	createTable = new Create(skinTypeMap[selectedSkinType]);
	createTable.refresh();
}

function Create(skin){
	Crud.call(this, skin);
	this.autoText = {field:"", prefix:"", target:"", suffix:""};
}
Create.prototype = Object.create(Crud.prototype);
Create.prototype.addRows = function(n){
	for(var i = 0; i < n; i++){
		this.kids.push(new CreateRow(this.skin, null, this.newNextId()));
	}
	this.refresh();
}
Create.prototype.onAddAbove = function(id){
	for(i in this.kids){
		if(this.kids[i].id == id){
			this.kids.splice(i, 0, new CreateRow(this.skin, null, this.newNextId()));
			this.refresh();
			return;
		}
	}
}
Create.prototype.drawAutoText = function(){	
	var txt = "";
	txt += "<div style='clear:left; float:none;'>";
	txt += "</br>";
	txt += "<table class='at'>";
	txt += "<caption class='at'>Auto Text Setting</caption>";
	
	txt += "<tr class='at'>";
		txt += "<th class='at'><label class='at'>Prefix</label></th>";
		txt += "<th class='at'><label class='at'>Target</label></th>";
		txt += "<th class='at'><label class='at'>Suffix</label></th>";
		txt += "<th class='at'></th>";
	txt += "</tr>";
	

	txt += "<tr class='at'>";
		txt += "<td class='at'><input class='at' type='text' id='createTable.autoTextPrefix' value='"+createTable.autoText.prefix+"'></td>";
		txt += "<td class='at'><input class='at' type='text' id='createTable.autoTextTarget' value='"+createTable.autoText.target+"'></td>";
		txt += "<td class='at'><input class='at' type='text' id='createTable.autoTextSuffix' value='"+createTable.autoText.suffix+"'></td>";
		txt += "<td class='at'><input class='at' type='button' onClick=createTable.onAutoText() value='Apply'></td>";
	txt += "</tr>";
	
	txt += "</table></div>";
	
	return txt;
}
Create.prototype.onAutoText = function(){
	this.keepAutoText();
	
	var fieldName = this.autoText.field;
	var prefix = this.autoText.prefix;
	var target = this.autoText.target;
	var suffix = this.autoText.suffix;
	var length = target.length;	
	
	for(var i in this.kids){
		var kid = this.kids[i];
		kid.item[fieldName] = prefix + target + suffix;
		
		if(isInt(target)){
			target = (parseInt(target) + 1).toString();
			while(target.length < length){
				target = "0" + target;
			}
		}
		else if(typeof target === 'string'){
			target = stringIncrement(target);
		}
	}
	
	this.refresh();
}
Create.prototype.drawButtons = function(){
	var txt ="";
	txt += "<div style='clear:left; float:none;'>" +
		"<input type='button' onClick=createTable.addRows(1) value='Add[+1]'>" +
		"<input type='button' onClick=createTable.addRows(10) value='Add[+10]'>" +
		"<input type='button' onClick=createTable.addRows(100) value='Add[+100]'>" +
		"<input class='ctCreate' type='button' onClick=createTable.onCreate() value='Create'>" +
		"</div>";
	
	return txt;
}
Create.prototype.draw = function(){
	var txt = this.drawAutoText();
	txt += this.drawButtons();
	
	txt += "<table class='ct'>";
	
	txt += "<colgroup>";
	for(var i = 0; i < this.fields.length + 3; i++){
		if((i == 0)) txt += "<col width=50px; />";
		else if(i >= this.fields.length + 1) txt += "<col width=30px; />";
		else txt += "<col width=150px; />";
	}
	txt += "</colgroup>";

	// Table Head
	txt +="<tr class='ct'>";
		txt += "<th class='ct'></th>"; // for number
	
	for(var i in this.fields){
		var field = this.fields[i];
		if(field.name == "skinType") continue;
		txt += "<th class='ct' >" +
				"<label class='ct' onClick=createTable.sort(\""+field.name+"\")>"+field.name+"</label>";
		if(field.hideTyping == 0 && field.autoFill == 0) txt += "<input class='ct' name='autoTextField' type='radio' id='radio"+field.name+"'>";
		txt += "</th>";
	}
	
		txt += "<th class='ct'></th>"; // for Add button
		txt += "<th class='ct'></th>"; // for delete button
	
	txt +="</tr>";
	
	
	
	// Type Info
	txt +="<tr class='ctinfo'>";
		txt += "<th class='ctinfo'><label class='ct'>#</label></th>"; // for number
	
	for(var i in this.fields){
		if(this.fields[i].name == "skinType") continue;
		var content = this.fields[i].type;
		if(this.fields[i].isPk == 1) content += ",PK";
		if(this.fields[i].isFk == 1) content += ",FK";
		txt += "<th class='ctinfo'>" +
				"<label class='ctinfo'>" + content + "</label>" +
				"</th>";
	}
	
		txt += "<th class='ctinfo'></th>"; // for Add button
		txt += "<th class='ctinfo'></th>"; // for delete button
	
	txt +="</tr>";
	
	
	for(var i in this.kids){
		txt += this.kids[i].draw(parseInt(i) + 1);
	}
	
	txt+="</table>";
	
	return txt;
}
Create.prototype.onCreate = function(){ // for Create
	var request = [];
	var nill = false; // null 필드가 있는지 확인하는 변수.
	for(var n in this.kids){
		var each = this.kids[n];
		var itemNill = false;
		for(i in this.fields){
			var field = this.fields[i];
			if(field.autoFill == 0 && field.nillable == 0 && each.item[field.name] == undefined){
				itemNill = true;
				break;
			}
		}
		
		if(itemNill) nill = true;
		else request.push(each.item);
	}
		
	if(nill == false || (nill == true && confirm("비어 있는 필드가 있습니다.\n비어 있는 필드가 있으면 생성에서 제외됩니다.\n계속하시겠습니까?") == true)){
		$.ajax({
			url : "/crud/create",
			type : "POST",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			mimeType : 'application/json',
			data : JSON.stringify(request),
			success : function(result) {
				alert("데이터가 생성 되었습니다 : " + JSON.stringify(result));
				console.log('success = ' + JSON.stringify(result));

				updateTable.created(result);
				createTable.clean();
				createTable.refresh();
				updateTable.refresh();
			},
			error : function(result) {
				console.log('error = ' + JSON.stringify(result));
			}
		});
	}
};
Create.prototype.refresh = function() {
	this.keepAutoText();
	$('#create').html(this.draw());
	if(document.getElementById('radio' + this.autoText.field) != undefined){
		document.getElementById('radio' + this.autoText.field).checked = true;
	}
}
Create.prototype.keepAutoText = function(){
	for(i in this.fields){
		if(document.getElementById('radio' + this.fields[i].name) == undefined) continue;
		if(document.getElementById('radio' + this.fields[i].name).checked){
			this.autoText.field = this.fields[i].name;
			break;
		}
	}
	this.autoText.prefix = document.getElementById('createTable.autoTextPrefix') == undefined ? "" : document.getElementById('createTable.autoTextPrefix').value;
	this.autoText.target = document.getElementById('createTable.autoTextTarget') == undefined ? "" : document.getElementById('createTable.autoTextTarget').value;
	this.autoText.suffix = document.getElementById('createTable.autoTextSuffix') == undefined ? "" : document.getElementById('createTable.autoTextSuffix').value;	
}


function CreateRow(skin, item, id){
	CrudRow.call(this, skin, item, id);
}
CreateRow.prototype = Object.create(CrudRow.prototype);
CreateRow.prototype.draw = function(row){
	var txt = "<tr class='ct'>";

	txt += "<td class='ct'><label class='ct'>"+row+"</label></td>"; // for delete button
	
	for(var i in this.fields){
		var field = this.fields[i];
		if(field.name == 'skinType') continue;
	
		txt += "<td class='ct'>";
			txt += "<input class='ct' type=";
			if (field.hideTyping)
				txt += "'password' onfocusout=createTable.onTextInputFocusOut(\""+field.name+"\",\""+this.id+"\",this.value) value='"+this.item[field.name]+"'></div>";
			else if (field.autoFill == 0)
				txt += "'text' onfocusout=createTable.onTextInputFocusOut(\""+field.name+"\",\""+this.id+"\",this.value) value='"+this.item[field.name]+"'></div>";
			else
				txt += "'text' readonly value='[AutoFill]'>";
		
		txt += "</td>";
	}
	
	txt += "<td class='ct'>" +
			"<input class='ct' type='button' style='width=60px; float:none; display=inline-block;' value='[+]' onclick=createTable.onAddAbove(\""+this.id+"\")>" +
			"</td>";
	
	txt += "<td class='ct'>" +
			"<input class='ct' type='button' style='width=60px; float:left; display=inline-block;' value='[-]' onclick=createTable.onRemove(\""+this.id+"\")>" +
			"</td>";
	
	txt +="</tr>";
	
	return txt;
}