function getRootPath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0, pos);
    return localhostPaht;
}
//弹框
function openPopUp(title,url,w,h){
	console.log(w)
	console.log(h);
	w = w || 50;
	h = h || 70;
	console.log("-----------" + w);
	console.log("-----------" + h);
	var index = layer.open({
        title: title,
        type: 2,
        shade: 0.2,
        maxmin:true,
        //shadeClose: true,
        area: [w + '%', h +'%'],
        content: url
    });
}
String.prototype.NoSpace = function () {
    return this.replace(/\s+/g, "");
};
//得到主页
var indexHome = function(elem) {
	   if(elem.index == 0){
		    console.log("找到了index=0")
			return elem;
	   }
	return indexHome(elem.parent);
   }

//list ---> listTree
function convert(rows) {
	function exists(rows, parentId) {
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].id == parentId)
				return true;
		}
		return false;
	}
	var nodes = [];
	for (var i = 0; i < rows.length; i++) {
		var row = rows[i];
		if (!exists(rows, row.parentId)) {
			nodes.push({
				id : row.id,
				name:row.name,
				parentId:row.parentId,
				title:row.name
			});
		}
	}
	var toDo = [];
	for (var i = 0; i < nodes.length; i++) {
		
		toDo.push(nodes[i]);
	}
	
	while (toDo.length) {
		var node = toDo.shift(); 
		for (var i = 0; i < rows.length; i++) {
			var row = rows[i];
			if (row.parentId == node.id) {//寻找子元素
				var child = {
					id : row.id,
					name:row.name,
					parentId:row.parentId,
					title:row.name
				};
				if (node.children) {
					
					node.children.push(child);
				} else {
					
					node.children = [ child ];
				}
				toDo.push(child);
			}
		}
	}
	return nodes;
}