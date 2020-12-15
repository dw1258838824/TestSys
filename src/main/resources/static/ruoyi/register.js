
$(function() {
    validateRule();
    $('.imgcode').click(function() {
		var url = ctx + "captcha/captchaImage?type=" + captchaType + "&s=" + Math.random();
		$(".imgcode").attr("src", url);
	});
    

   
    $("#getCode").click(function(){
        var username = $.common.trim($("input[name='username']").val());
        if(username.length == 11 && time == 60) {
            var th = $(this);
        	$.get("api/captcha/code/" + username, function (data, status) {
                if(data.code == 0){
                	console.log("666");
                	th.attr("disabled", "disabled");
                    getRandomCode(th);
                }else{
                    $.modal.msgError(data.msg);
                }
            });
        }else{
            $.modal.msgError("<div style='color: black!important;'>请输入正确的手机号码</div>");
        }
    });
});

var time = 60;

function getRandomCode(ele){
	if (time === 0) { 
		time = 60;
		$(ele).removeAttr("disabled");
		$(ele).val("获取验证码");
		return;
	} else { 
	    time--;
	    $(ele).val(time);
	} 
	setTimeout(function() { 
	    getRandomCode(ele);
	},1000);
}

$.validator.setDefaults({
    submitHandler: function() {
    	register();
    }
});

function register() {
	$.modal.loading($("#btnSubmit").data("loading"));
	var username = $.common.trim($("input[name='username']").val());
    var password = $.common.trim($("input[name='password']").val());
    var phoneCode = $("input[name='phoneCode']").val();
    var deptId = $("input[name='deptId']").val();
    $.ajax({
        type: "post",
        url: ctx + "register",
        data: {
            "loginName": username,
            "password": password,
            "remark" : "学员",
            "phoneCode": phoneCode,
            "phonenumber": username,
           "student.mobile": username,
           "deptId" : deptId
        },
        success: function(r) {
            if (r.code == 0) {
            	layer.alert("<font color='red'>恭喜你，您的账号 " + username + " 注册成功！</font>", {
        	        icon: 1,
        	        title: "系统提示"
        	    },
        	    function(index) {
        	        //关闭弹窗
        	        layer.close(index);
        	        location.href = ctx + 'login';
        	    });
            } else {
            	$.modal.closeLoading();
            	$('.imgcode').click();
            	$(".code").val("");
            	$.modal.msg(r.msg);
            }
        }
    });
}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
    $("#registerForm").validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            },
            confirmPassword: {
                required: true,
                equalTo: "[name='password']"
            },
            deptId: {
                required: true
            },
            phoneCode: {
            	required: true
            }
        },
        messages: {
            username: {
                required: icon + "请输入您的手机号码"
            },
            password: {
            	required: icon + "请输入您的密码"
            },
            confirmPassword: {
                required: icon + "请再次输入您的密码",
                equalTo: icon + "两次密码输入不一致"
            },
            deptId: {
            	required: icon + "请填写机构码"
            },
            phoneCode: {
            	required: icon + "请输入验证码"
            }
        }
    })
}


