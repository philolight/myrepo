function helloWorld(){
	alert('helloWorld');
	alert('another helloWorld');
}

/* Object */ function multiTable(){
	this.result;
	this.makeList = function(dan, gob){
		var list = [];
		for(var i = 1; i <= gob; i++){
			list[i-1] = "";
			for(var j = 2; j <= dan; j++){
				list[i-1] += j + " * " + i + " = " + (j*i) + " ";
			}
			list[i-1] += "\n";
		}
		this.result = list;
		return list;
	}
}

/*function message(fieldMap){
	this.fields = sortedList(fieldMap);
	this.getBit = function(byteIndex, bit){
		var bitMask = 1<<bit;
		for(var i in fields){
			if(fields[i]["byteIndex"] === byteIndex && (bitMask & fields[i]["mask"]) !== 0){
				var fieldMask = fields[i]["mask"];
				var shift = 0;
				while( (fieldMask>>shift % 2) === 0) shift++;
				return (field[i]["value"] & (fieldMask>>shift));
			}
		}
		
		return 0;
	}
	
	this.sortedList = function(map){
		var list = [];
		var index = 0;
		for(var i in map) list[index++] = map[i];
		
		for(var i = 0; i < list.length - 1; i++){
			for(var j = i + 1; j < list.length; j++){
				if(list[i]["byteIndex"] > list[j]["byteIndex"] || 
					(list[i]["byteIndex"] === list[j]["byteIndex"] && list[i]["mask"] > list[j]["mask"])
				){
					var tmp = list[i];
					list[i] = list[j];
					list[j] = tmp;
				}
			}
		}
		
		return list;
	}
};*/