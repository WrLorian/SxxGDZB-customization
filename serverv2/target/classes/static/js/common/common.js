function getRootPath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0, pos);
    return localhostPaht;
}

function openPopUp(title,url){
	var index = layer.open({
        title: title,
        type: 2,
        shade: 0.2,
        maxmin:true,
        shadeClose: true,
        area: ['50%', '70%'],
        content: url
    });
}