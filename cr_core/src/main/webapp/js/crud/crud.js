function Crud(skin){
	this.skin = skin;
	this.fields = skin["fields"];
	this.kids = [];	// insert 대기 상태인 kid
	this.asc = {}; // 오름차순 정렬 여부
	for(i in this.fields){
		var field = this.fields[i];
		this.asc[field.name] = false;
	}
	this.gid = -1;
}
Crud.prototype.newNextId = function(){ return (++gid); }
Crud.prototype.onRemove = function(id){
	for(i in this.kids){
		if(this.kids[i].id == id){
			this.kids.splice(i, 1);
			this.refresh();
			return;
		}
	}
	
	return null;
};
Crud.prototype.find = function(id){
	for(i in this.kids){
		if(this.kids[i].id == id) return this.kids[i];
	}
	
	return null;
}
Crud.prototype.sort = function(fieldName) {
	this.asc[fieldName] = !this.asc[fieldName];
	this.mergeSort(this.kids, 0, this.kids.length - 1, fieldName);
	if(this.asc[fieldName] == false) this.kids.reverse();
	this.refresh();
}
Crud.prototype.mergeSort = function(arr, left, right, fieldName) {
	if (left >= right) return;
	var mid = parseInt((left + right) / 2);
	this.mergeSort(arr, left, mid, fieldName);
	this.mergeSort(arr, mid + 1, right, fieldName);
	this.merge(arr, left, mid, right, fieldName);
}
Crud.prototype.merge = function(arr, left, mid, right, fieldName) {
	var i = left;
	var j = mid + 1;
	var count = 0;
	var narr = [];
	while (i <= mid && j <= right) {
		if (arr[i].item[fieldName] < arr[j].item[fieldName])
			narr[count++] = arr[i++];
		else
			narr[count++] = arr[j++];
	}

	while (i <= mid) narr[count++] = arr[i++];

	for (var k = 0; k < count; k++) {
		arr[k + left] = narr[k];
	}
}
Crud.prototype.onTextInputFocusOut = function(fieldName,id,value){
	var node = this.find(id);
	if(node == null) return;
	node.item[fieldName] = value;
}
Crud.prototype.getItemById = function(id){
	var items = [];
	for(var i in this.kids){
		if(this.kids[i].id == id){
			items.push(this.kids[i].item);
			break;
		}
	}
	return items;
}
Crud.prototype.clean = function(){
	this.kids = [];
}


function CrudRow(skin, item, id){
	this.id = id;
	this.skin = skin;
	this.fields = skin["fields"];
	if(item != undefined) this.item = item;
	else{
		var item = {skinType:skin["skinType"]};
		
		for(var i in this.fields){
			var field = this.fields[i];
			item[field.name] = "";
		}
		this.item = item;
	}
}

function isInt(value) {
	if (isNaN(value)) {
		return false;
	}
	var x = parseFloat(value);
	return (x | 0) === x;
}

function stringIncrement(str){
	var length = str.length;
	var value = 0;
    var base = str.charAt(0) >= 'a' ? 97 : 65;
    
	var i = 0;
	while(i < str.length){
		value = value * 26 + (str.charCodeAt(i) - base);
		i++;
	}
	
	value += 1;
	
	var ret = "";
	for(var i = 0; i < length; i++){
		ret = String.fromCharCode( (value % 26) + base) + ret;
		value = parseInt(value / 26);
	}

	return ret;
}