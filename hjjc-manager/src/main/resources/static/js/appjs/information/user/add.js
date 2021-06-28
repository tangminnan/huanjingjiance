$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {

	$.ajax({
		cache : true,
		type : "POST",
		url : "/information/user/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			school : {
				required : true
			},
            grade : {
                required : true
            },
            clas : {
                required : true
            }
		},
		messages : {
			school : {
				required : icon + "请输入学校"
			},
            grade : {
                required : icon + "请输入年级"
            },
            clas : {
                required : icon + "请输入班级"
            }
		}
	})
}