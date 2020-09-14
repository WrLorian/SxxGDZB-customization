var check_True = new Array();// 勾选
var table_Data = new Array();// 表格数据缓存

function checkRender(){
	//在缓存中找到PM_CODE ,然后设置data表格中的选中状态
	//循环所有数据，找出对应关系，设置checkbox选中状态
	for (var i = 0; i < table_Data.length; i++) {
			for (var j = 0; j < check_True.length; j++) {
				if (table_Data[i].imei == check_True[j].imei) {
					//这里才是真正的有效勾选
					table_Data[i]["LAY_CHECKED"] = 'true';
					//找到对应数据改变勾选样式，呈现出选中效果
					var index = table_Data[i]['LAY_TABLE_INDEX'];
					$('.layui-table tr[data-index=' + index + '] input[type="checkbox"]').prop('checked', true);
					$('.layui-table tr[data-index=' + index + '] input[type="checkbox"]').next().addClass('layui-form-checked');
			}
		}
	}
	var checkStatus = table.checkStatus('listTable');
	if (checkStatus.isAll) {
		$(' .layui-table-header th[data-field="0"] input[type="checkbox"]').prop('checked', true);
		$('.layui-table-header th[data-field="0"] input[type="checkbox"]').next().addClass('layui-form-checked');
	}
}

function checkboxMonitor(obj){
	if (obj.type == "one") {
		if (obj.checked == true) {
			check_True.push(obj.data);
		} else {
			for (var i = 0; i < check_True.length; i++) {
				if (check_True[i].imei == obj.data.imei) {
					check_True.remove(i);
				}
			}
		}
	} else {
		if (obj.checked == true) {
			var c = check_True.concat(table_Data), //合并成一个数组
			temp = {};//用于imei判断重复
			//relust = new Array();//最后的新数组
			check_True = new Array();
			//遍历c数组，将每个item.imei在temp中是否存在值做判断，如不存在则对应的item赋值给新数组，并将temp中item.imei对应的key赋值，下次对相同值做判断时便不会走此分支，达到判断重复值的目的；
			c.map(function(item, index) {
				if (!temp[item.imei]) {
					check_True.push(item);
					temp[item.imei] = true
				}
			})
			console.log(check_True);
		} else {
			for (var i=0;i<check_True.length;i++) {
				for(var j=0;j<table_Data.length;j++ ){
					if(check_True[i] == table_Data[j]){
						check_True.remove(i);
					}
				}
			}
		}
	}
}