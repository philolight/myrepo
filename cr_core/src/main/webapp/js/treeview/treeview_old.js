var gid = -1;
var tree;

function TreeHead(parent, skin, kids, skinTypeMap, refresh){
	this.id = "th" + (++gid);
	this.parent = parent; 	// 상위 트리
	this.skin = skin;
	
	this.skinTypeMap = skinTypeMap;
	if(parent == null) this.indent = 0;		// 들여쓰기
	else this.indent = parent.indent + 1;
	this.fields = skin["fields"];
		
	this.fold = true;	// 접기 펴기
	this.news = [];		// insert 대기 상태인 kid
	
	this.nodes = [];
	this.draw();
	this.refresh = refresh;
	this.receiveKids(kids);
}
TreeHead.prototype.draw = function(){
	var txt = "";
	
	txt += "<div id='test2' style='float:none; width:100%; display:inline-block;'></div>";
	
	for(var i = 0; i < this.indent; i++) txt += "<div class='indent' id = \""+this.id+"\"></div>";
	
	txt += "<div style='float:left; display:inline-block; overflow:auto;'>";
	
	txt += "<div style='float:left; display:inline-block;'>";
	
	if(this.fold) txt += "<input class='tv' type='button' value='Open' onclick='TreeHead.prototype.onOpen(\""+this.id+"\")' style='float:left; padding:1; width:80px;'>";
	else txt += "<input class='tv' type='button' value='Fold' onclick='TreeHead.prototype.onFold(\""+this.id+"\")' style='float:left; padding:1; width:80px;'>";
	
	txt += "<label class='skinType' style='float:left;'>"+this.skin["skinType"]+"</label>";
	
	txt += "<input class='tv' type='button' value='New[+]' onclick='TreeHead.prototype.newEntity(\""+this.id+"\")' style='float:left; padding:1; width:80px;'>";
	
	txt += "</div>";
	
	if(this.fold == false){
		for(var i in this.news){
			txt += this.news[i].draw();
		}
		
		for(var i in this.nodes){
			txt += this.nodes[i].draw();
		}
	}
	
	txt +="</div>";
	
	return txt;
}
TreeHead.prototype.newEntity = function(id){
	var node = find(id);
	if(node == null){
		console.log("head is null");
		return;
	}

	var item = {skinType:node.skin["skinType"]};
	
	for(var i in node.fields){
		var field = node.fields[i];
		item[field.name] = "";
	}
	
	node.news.push(new TreeNode(node, node.skin, item, node.skinTypeMap, false));
	node.fold = false;
	
	tree.refresh();
};
TreeHead.prototype.onOpen = function(id){
	var node = find(id);
	
	if(node == null){
		console.log("head is null");
		return;
	}
		
	if(node.parent != undefined && node.nodes.length == 0){
		$.ajax({
			url : "/crud/kids",
			type : "POST",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			mimeType : 'application/json',
			data : JSON.stringify({parent : node.parent.me, kidSkinType : node.skin["skinType"]}),
			success : function(result) {
				console.log('success = ' + JSON.stringify(result));
				node.receiveKids(result);
				tree.refresh();
			},
			error : function(result) {
				console.log('error' + JSON.stringify(result));
			}
		});
	}
	node.fold = !node.fold;		
	tree.refresh();
}
TreeHead.prototype.onFold = function(id){
	var node = find(id);
	if(node == null) console.log("head is null");
	else{
		node.fold = !node.fold;
		tree.refresh();
	}
}
TreeHead.prototype.find = function(id){
	if(this.id == id) return this;

	for(i in this.news){
		var ret = this.news[i].find(id);
		if(ret != null) return ret;
	}
	
	for(i in this.nodes){
		var ret = this.nodes[i].find(id);
		if(ret != null) return ret;
	}
	
	return null;
}
TreeHead.prototype.remove = function(id){
	for(i in this.news){
		if(this.news[i].id == id){
			return;
		}
	}
	
	for(i in this.nodes){
		if(this.nodes[i].id == id){
			return;
		}
	}
}
TreeHead.prototype.receiveKids = function(kids){
	for(var i in kids){
		this.nodes.push(new TreeNode(this, this.skin, kids[i], this.skinTypeMap, true));
	}
}

function TreeNode(head, skin, me, skinTypeMap, exist){
	this.id = "tn" + (++gid);
	this.head = head;
	this.skin = skin;
	this.me = me;
	this.skinTypeMap = skinTypeMap;
	this.indent = head.indent;
	this.kids = [];
	this.fields = skin["fields"];
	this.fold = true;
	
	if(exist == true){
		this.afterInsert(me);
	}
	else this.draw = TreeNode.prototype.newDraw;
};
TreeNode.prototype.drawHeader = function(){
	var txt = "";
	
	txt += "<div id='test' style='float:none; width:100%; display:inline-block;'> </div>";
	
	txt += "<div style='float:left; display:inline-block;'>";
	
	txt += "<table class='tv' id=\""+this.id+"\">";
	txt += "<th class='tv'></th>"; // open
	txt += "<th class='tv'></th>"; // update
	
	for(var i in this.fields){
		var field = this.fields[i];
		if(field.name == 'skinType') continue;
		txt += "<th class='tv'>"+field.name+"</th>";
	}
	
	txt += "<th class='tv'></th>"; // delete
	
	return txt;
};
TreeNode.prototype.existDraw = function(){
	var txt = this.drawHeader();

	txt += "<tr class='tv' id='"+this.id+"'>";

	if(this.fold) txt += "<td class='tv'><input class='tv' type='button' value='Open' onclick='TreeNode.prototype.onOpen(\""+this.id+"\")' style='width:80px;'></td>";
	else txt += "<td class='tv'><input class='tv' type='button' value='Fold' onclick='TreeNode.prototype.onFold(\""+this.id+"\")' style='width:80px;'></td>";
	
	txt +="<td class='tv'><input class='tv' type='button' value='Update' onclick='TreeNode.prototype.onUpdate(\""+this.id+"\")' style='width:80px;'></td>";
	
	for(var i in this.fields){
		var field = this.fields[i];
		if(field.name == 'skinType') continue;
		txt += "<td class='tv'><input type=";
			
		if (field.hideTyping)
			txt += "'password'";
		else if (field.editable == 1 && (field.isPk == 0 && field.isFk == 0))
			txt += "'text' onfocusout=onFocusOut(\""+field.name+"\",\""+this.id+"\",this.value)";
		else
			txt += "'text' readonly";
		txt += " value='"+this.me[field.name]+"'></td>";
	}
	
	txt +="<td class='tv'><input class='tv' type='button' value='Delete' onclick='TreeNode.prototype.onDelete(\""+this.id+"\")' style='width:80px;'></td>";
				
	txt +="</tr>";
	txt +="</table>";
	txt +="</div>";
	
	if(this.fold == false){
		for(var i in this.kids){
			txt += this.kids[i].draw();
		}
	}
	
	return txt;
};
TreeNode.prototype.newDraw = function(){
	var txt = this.drawHeader();
	
	txt += "<tr class='tv' id='"+this.id+"'>";

	txt +="<td class='tv'><input class='tv' type='button' value='Create' onclick='TreeNode.prototype.onInsert(\""+this.id+"\")' style='width:80px;'></td>";
	
	for(var i in this.fields){
		var field = this.fields[i];
		if(field.name == 'skinType') continue;
		txt += "<td class='tv'><input class='tv' type=";
		
		if (field.hideTyping)
			txt += "'password' onfocusout=onFocusOut(\""+field.name+"\",\""+this.id+"\",this.value)";
		else if (field.autoFill == 0)
			txt += "'text' onfocusout=onFocusOut(\""+field.name+"\",\""+this.id+"\",this.value)";
		else
			txt += "'text' readonly";
		txt += " value='"+this.me[field.name]+"'></td>";
	}
	
	txt +="<td class='tv'><input class='tv' type='button' value='Delete' onclick='TreeNode.prototype.onNewDelete(\""+this.id+"\")' style='width:80px;'></td>";

	txt +="</tr>";
	txt +="</table>";
	txt +="</div>";
	
	if(this.fold == false){
		for(var i in this.kids){
			txt += this.kids[i].draw();
		}
	}
	
	return txt;
};

TreeNode.prototype.onInsert = function(id){
	var node = find(id);
	if(node == null) return;
	
	console.log(JSON.stringify(node.me));
	
	$.ajax({
		url : "/crud/create",
		type : "POST",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		mimeType : 'application/json',
		data : JSON.stringify(node.me),
		success : function(result) {
			console.log('success = ' + JSON.stringify(result));
			node.afterInsert(result);
			tree.refresh();
		},
		error : function(result) {
			console.log('error');
		}
	});
};
TreeNode.prototype.afterInsert = function(me){
	this.me = me;
	this.draw = TreeNode.prototype.existDraw;
	
	for(i in this.skin.kidSkinTypes){
		var kidSkin = this.skinTypeMap[this.skin.kidSkinTypes[i]];
		this.kids.push(new TreeHead(this, kidSkin, [], this.skinTypeMap));
	}
}
TreeNode.prototype.onUpdate = function(id){
	var node = find(id);
	if(node == null) return;
	
	$.ajax({
		url : "/crud/update",
		type : "POST",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		mimeType : 'application/json',
		data : JSON.stringify(node.me),
		success : function(result) {
			console.log('success = ' + JSON.stringify(result));
			node.me = result;
			tree.refresh();
		},
		error : function(result) {
			console.log('error = ' + JSON.stringify(result));
		}
	});
};
TreeNode.prototype.onDelete = function(id){
	var node = find(id);
	if(node == null) return;
	
	$.ajax({
		url : "/crud/delete",
		type : "POST",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		mimeType : 'application/json',
		data : JSON.stringify(node.me),
		success : function(result) {
			console.log('success = ' + JSON.stringify(result));
			node.me = result;
			tree.refresh();
		},
		error : function(result) {
			console.log('error = ' + JSON.stringify(result));
		}
	});
};
TreeNode.prototype.onNewDelete = function(id){
	var node = find(id);
	if(node == null) return;
	node.parent.remove(id);
};
TreeNode.prototype.find = function(id){
	if(this.id == id) return this;
	
	for(i in this.kids){
		var ret = this.kids[i].find(id);
		if(ret != null) return ret;
	}
	
	return null;
}
TreeNode.prototype.onOpen = function(id){
	this.onFold(id);
}
TreeNode.prototype.onFold = function(id){
	var node = find(id);
	if(node == null) return;
	node.fold = !node.fold;
	tree.refresh();
}

function find(id){
	if(tree == null) return null;
	return tree.find(id);
}

function onFocusOut(fieldName,id,value){
	var node = find(id);
	if(node == null) return;
	
	node.me[fieldName] = value;
}