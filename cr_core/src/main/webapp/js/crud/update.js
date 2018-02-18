var updateTable;

function UpdateTableInit() {
	updateTable = new Update(skinTypeMap[selectedSkinType]);
	
	$.ajax({
		url : "/skinner/skinizedAll",
		type : "POST",
		dataType : "json",
		data : {
			"skinType" : selectedSkinType
		},
		success : function(result) {
			console.log(result);
			updateTable.created(result);
			updateTable.refresh();
		}
	});
}

function Update(skin){
	Crud.call(this, skin);
	this.autoText = {field:"", prefix:"", target:"", suffix:""};
}
Update.prototype = Object.create(Crud.prototype);
Update.prototype.addRows = function(n){
	for(var i = 0; i < n; i++){
		this.kids.push(new UpdateRow(this.skin, null, this.newNextId()));
	}
	this.refresh();
}
Update.prototype.onAddAbove = function(id){
	for(i in this.kids){
		if(this.kids[i].id == id){
			this.kids.splice(i, 0, new UpdateRow(this.skin, null, this.newNextId()));
			this.refresh();
			return;
		}
	}
}
Update.prototype.drawAutoText = function(){	
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
		txt += "<td class='at'><input class='at' type='text' id='updateTable.autoTextPrefix' value='"+updateTable.autoText.prefix+"'></td>";
		txt += "<td class='at'><input class='at' type='text' id='updateTable.autoTextTarget' value='"+updateTable.autoText.target+"'></td>";
		txt += "<td class='at'><input class='at' type='text' id='updateTable.autoTextSuffix' value='"+updateTable.autoText.suffix+"'></td>";
		txt += "<td class='at'><input class='at' type='button' onClick=updateTable.onAutoText() value='Apply'></td>";
	txt += "</tr>";
	
	txt += "</table></div>";
	
	return txt;
}
Update.prototype.onAutoText = function(){
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
Update.prototype.drawButtons = function(){
	var txt ="";
	txt += "<div style='clear:left; float:none;'>" +
		"<input class='ct' type='button' onClick=updateTable.onUpdate() value='Update Selected'>" +
		"<input class='ct' type='button' onClick=updateTable.onDelete() value='Delete Selected'>" +
		"</div>";
	
	return txt;
}
Update.prototype.draw = function(){
	var txt = this.drawAutoText();
	txt += this.drawButtons();
	
	txt += "<table class='ct'>";
	
	txt += "<colgroup>";
	for(var i = 0; i < this.fields.length + 4; i++){
		if((i == 0)) txt += "<col width=30px; />";
		else if((i == 1)) txt += "<col width=50px; />";
		else if(i >= this.fields.length + 2) txt += "<col width=30px; />";
		else txt += "<col width=150px; />";
	}
	txt += "</colgroup>";

	// Table Head
	txt +="<tr class='ct'>";
		txt += "<th class='ct'></th>"; // for checkbox
		txt += "<th class='ct'></th>"; // for number
	
	for(var i in this.fields){
		var field = this.fields[i];
		if(field.name == "skinType") continue;
		txt += "<th class='ct' >" +
				"<label class='ct' onClick=updateTable.sort(\""+field.name+"\")>"+field.name+"</label>";
		if(field.hideTyping == 0 && field.autoFill == 0 && 
				field.isPk == 0 && field.isFk == 0) txt += "<input class='ct' name='autoTextField' type='radio' id='radio"+field.name+"'>";
		txt += "</th>";
	}
		txt += "<th class='ct'></th>"; // for update button
		txt += "<th class='ct'></th>"; // for delete button
	
	txt +="</tr>";
	
	
	
	// Type Info
	txt +="<tr class='ctinfo'>";
		txt += "<th class='ct'><input class='ct' type='checkbox' id='updateTableSelectAll' onclick='updateTable.onCheckBox(-1,this.checked)'></th>"; // for checkbox
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
	
		txt += "<th class='ctinfo'></th>"; // for update button
		txt += "<th class='ctinfo'></th>"; // for delete button
	
	txt +="</tr>";
	
	
	for(var i in this.kids){
		txt += this.kids[i].draw(parseInt(i) + 1);
	}
	
	txt+="</table>";
	
	return txt;
}
Update.prototype.getNotNillItems = function(){
	var items = [];
	var nill = false; // null 필드가 있는지 확인하는 변수.
	
	for(var n in this.kids){
		var each = this.kids[n];
		if(each.checked == false) continue;
		var itemNill = false;
		for(i in this.fields){
			var field = this.fields[i];
			if(field.autoFill == 0 && field.nillable == 0 && each.item[field.name] == undefined){
				itemNill = true;
				break;
			}
		}
		
		if(itemNill) nill = true;
		else items.push(each.item);
	}
	
	return {items : items, nill : nill};
}
Update.prototype.onUpdate = function(id){
	var request = [];
	var nill = false; // null 필드가 있는지 확인하는 변수.
	
	if(id == undefined){
		var result = this.getNotNillItems();
		nill = result.nill;
		request = result.items;
	}
	else request = this.getItemById(id);
	
	if(nill == false || (nill == true && confirm("비어 있는 필드가 있습니다.\n비어 있는 필드가 있으면 업데이트에서 제외됩니다.\n계속하시겠습니까?") == true)){
		$.ajax({
			url : "/crud/update",
			type : "POST",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			mimeType : 'application/json',
			data : JSON.stringify(request),
			success : function(results) {
				alert("데이터가 업데이트 되었습니다 : " + JSON.stringify(results));
				console.log('success = ' + JSON.stringify(results));

				updateTable.updated(results);
				updateTable.refresh();
			},
			error : function(results) {
				console.log('error = ' + JSON.stringify(results));
			}
		});
	}
};
Update.prototype.onDelete = function(id){
	var request = [];
	var nill = false; // null 필드가 있는지 확인하는 변수.
	
	if(id == undefined){
		var result = this.getNotNillItems();
		console.log(result);
		nill = result.nill;
		request = result.items;
	}
	else request = this.getItemById(id);
		
	if(nill == false || (nill == true && confirm("키가 비어 있는 필드가 있습니다.\n비어 있는 필드가 있으면 삭제에서 제외됩니다.\n계속하시겠습니까?") == true)){
		$.ajax({
			url : "/crud/delete",
			type : "POST",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			mimeType : 'application/json',
			data : JSON.stringify(request),
			success : function(results) {
				alert("삭제가 완료 되었습니다 : " + JSON.stringify(results));
				console.log('success = ' + JSON.stringify(results));

				updateTable.deleted(results);
				updateTable.refresh();
			},
			error : function(results) {
				console.log('error = ' + JSON.stringify(results));
			}
		});
	}
};
Update.prototype.refresh = function() {
	this.keepAutoText();
	$('#update').html(this.draw());
	if(document.getElementById('radio' + this.autoText.field) != undefined){
		document.getElementById('radio' + this.autoText.field).checked = true;
	}
}
Update.prototype.updated = function(results) {
	for(var i in results){
		for(var j in this.kids){
			var item = this.kids[j].item;
			if(pkEquals(results[i], item, this.fields)){
				this.kids[j].item = results[i];
				this.kids[j].checked = false;
				break;
			}
		}
	}
}
Update.prototype.deleted = function(results) {
	for(var i in results){
		for(var j in this.kids){
			var item = this.kids[j].item;
			if(pkEquals(results[i], item, this.fields)){
				this.kids.splice(j,1);
				break;
			}
		}
	}
}

function pkEquals(a, b, fields){
	for(i in fields){
		var field = fields[i];
		if(field.isPk == 0) continue;
		if(a[field.name] == undefined || b[field.name] == undefined) return false;
		if(a[field.name] !== b[field.name]) return false;
	}
	return true;
}

Update.prototype.keepAutoText = function(){
	for(i in this.fields){
		if(document.getElementById('radio' + this.fields[i].name) == undefined) continue;
		if(document.getElementById('radio' + this.fields[i].name).checked){
			this.autoText.field = this.fields[i].name;
			break;
		}
	}
	this.autoText.prefix = document.getElementById('updateTable.autoTextPrefix') == undefined ? "" : document.getElementById('updateTable.autoTextPrefix').value;
	this.autoText.target = document.getElementById('updateTable.autoTextTarget') == undefined ? "" : document.getElementById('updateTable.autoTextTarget').value;
	this.autoText.suffix = document.getElementById('updateTable.autoTextSuffix') == undefined ? "" : document.getElementById('updateTable.autoTextSuffix').value;	
}
Update.prototype.created = function(kids){	
	for(var i in kids){
		this.kids.push(new UpdateRow(this.skin,kids[i], this.newNextId()));
	}
}
Update.prototype.onCheckBox = function(row, checked){
	console.log(row, checked);
	if(row == -1){
		$('input[name=updateTableSelect]').prop('checked', checked);
		for(var i in this.kids){
			this.kids[i].checked = checked;
		}
	}
	else{
		this.kids[parseInt(row)-1].checked = checked;
	}
}


function UpdateRow(skin, item, id){
	CrudRow.call(this, skin, item, id);
	this.checked = false;
}
UpdateRow.prototype = Object.create(CrudRow.prototype);
UpdateRow.prototype.draw = function(row){
	var txt = "<tr class='ct'>";

	txt += "<td class='ct'><input class='ct' type='checkbox' name='updateTableSelect' onclick='updateTable.onCheckBox("+row+",this.checked)' value='"+row+"'></td>";
	txt += "<td class='ct'><label class='ct'>"+row+"</label></td>"; // for number
	
	for(var i in this.fields){
		var field = this.fields[i];
		if(field.name == 'skinType') continue;
	
		txt += "<td class='ct'>";
			txt += "<input class='ct' type=";
			if (field.hideTyping)
				txt += "'password' onfocusout=updateTable.onTextInputFocusOut(\""+field.name+"\",\""+this.id+"\",this.value) value='"+this.item[field.name]+"'></div>";
			else if (field.autoFill == 0 && field.isPk == 0 && field.isFk == 0)
				txt += "'text' onfocusout=updateTable.onTextInputFocusOut(\""+field.name+"\",\""+this.id+"\",this.value) value='"+this.item[field.name]+"'></div>";
			else
				txt += "'text' readonly value='"+this.item[field.name]+"'>";
		
		txt += "</td>";
	}
	
	txt += "<td class='ct'>" +
			"<input class='ct' type='button' style='width=60px; float:none; display=inline-block;' value='[U]' onclick=updateTable.onUpdate(\""+this.id+"\")>" +
			"</td>";
	
	txt += "<td class='ct'>" +
			"<input class='ct' type='button' style='width=60px; float:left; display=inline-block;' value='[-]' onclick=updateTable.onDelete(\""+this.id+"\")>" +
			"</td>";
	
	txt +="</tr>";
	
	return txt;
}