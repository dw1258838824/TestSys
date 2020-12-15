/**
 * 通用方法封装处理
 * Copyright (c) 2019 ruoyi 
 */
$(function() {
	
	//  layer扩展皮肤
	if (window.layer !== undefined) {
		layer.config({
		    extend: 'moon/style.css',
		    skin: 'layer-ext-moon'
		});
	}
	
	// 回到顶部绑定
	if ($.fn.toTop !== undefined) {
		$('#scroll-up').toTop();
	}
	
	// select2复选框事件绑定
	if ($.fn.select2 !== undefined) {
        $.fn.select2.defaults.set( "theme", "bootstrap" );
		$("select.form-control:not(.noselect2)").each(function () {
			$(this).select2().on("change", function () {
				$(this).valid();
			})
		})
	}
	
	// iCheck单选框及复选框事件绑定
	if ($.fn.iCheck !== undefined) {
		$(".check-box:not(.noicheck),.radio-box:not(.noicheck)").each(function() {
            $(this).iCheck({
                checkboxClass: 'icheckbox-blue',
                radioClass: 'iradio-blue',
            })
        })
	}
	
	// 气泡弹出框特效（移到元素时）
	$(document).on("mouseenter", '.table [data-toggle="popover"]', function() {
		var _this = this;
		$(this).popover("show");
		$(".popover").on("mouseleave", function() {
			$(_this).popover('hide');
		});
	})

	// 气泡弹出框特效（离开元素时）
	$(document).on("mouseleave", '.table [data-toggle="popover"]', function() {
		var _this = this;
		setTimeout(function() {
			if (!$(".popover:hover").length) $(_this).popover("hide");
		}, 100);
	});
	 
	// laydate 时间控件绑定
	if ($(".select-time").length > 0) {
		layui.use('laydate', function() {
		    var laydate = layui.laydate;
		    var startDate = laydate.render({
		        elem: '#startTime',
		        max: $('#endTime').val(),
		        theme: 'molv',
		        trigger: 'click',
		        done: function(value, date) {
		            // 结束时间大于开始时间
		            if (value !== '') {
		                endDate.config.min.year = date.year;
		                endDate.config.min.month = date.month - 1;
		                endDate.config.min.date = date.date;
		            } else {
		                endDate.config.min.year = '';
		                endDate.config.min.month = '';
		                endDate.config.min.date = '';
		            }
		        }
		    });
		    var endDate = laydate.render({
		        elem: '#endTime',
		        min: $('#startTime').val(),
		        theme: 'molv',
		        trigger: 'click',
		        done: function(value, date) {
		            // 开始时间小于结束时间
		            if (value !== '') {
		                startDate.config.max.year = date.year;
		                startDate.config.max.month = date.month - 1;
		                startDate.config.max.date = date.date;
		            } else {
		                startDate.config.max.year = '2099';
		                startDate.config.max.month = '12';
		                startDate.config.max.date = '31';
		            }
		        }
		    });
		});
	}
	
	// laydate time-input 时间控件绑定
	if ($(".time-input").length > 0) {
		layui.use('laydate', function () {
			var com = layui.laydate;
			$(".time-input").each(function (index, item) {
				var time = $(item);
				// 控制控件外观
				var type = time.attr("data-type") || 'date';
				// 控制回显格式
				var format = time.attr("data-format") || 'yyyy-MM-dd';
				// 控制日期控件按钮
				var buttons = time.attr("data-btn") || 'clear|now|confirm', newBtnArr = [];
				// 日期控件选择完成后回调处理
				var callback = time.attr("data-callback") || {};
				if (buttons) {
					if (buttons.indexOf("|") > 0) {
						var btnArr = buttons.split("|"), btnLen = btnArr.length;
						for (var j = 0; j < btnLen; j++) {
							if ("clear" === btnArr[j] || "now" === btnArr[j] || "confirm" === btnArr[j]) {
								newBtnArr.push(btnArr[j]);
							}
						}
					} else {
						if ("clear" === buttons || "now" === buttons || "confirm" === buttons) {
							newBtnArr.push(buttons);
						}
					}
				} else {
					newBtnArr = ['clear', 'now', 'confirm'];
				}
				com.render({
					elem: item,
					theme: 'molv',
					trigger: 'click',
					type: type,
					format: format,
					btns: newBtnArr,
					done: function (value, data) {
						if (typeof window[callback] != 'undefined'
							&& window[callback] instanceof Function) {
							window[callback](value, data);
						}
					}
				});
			});
		});
	}
	
	// tree 关键字搜索绑定
	if ($("#keyword").length > 0) {
		$("#keyword").bind("focus", function focusKey(e) {
		    if ($("#keyword").hasClass("empty")) {
		        $("#keyword").removeClass("empty");
		    }
		}).bind("blur", function blurKey(e) {
		    if ($("#keyword").val() === "") {
		        $("#keyword").addClass("empty");
		    }
		    $.tree.searchNode(e);
		}).bind("input propertychange", $.tree.searchNode);
	}
	
	// tree表格树 展开/折叠
	var expandFlag;
	$("#expandAllBtn").click(function() {
		var dataExpand = $.common.isEmpty(table.options.expandAll) ? true : table.options.expandAll;
		expandFlag = $.common.isEmpty(expandFlag) ? dataExpand : expandFlag;
	    if (!expandFlag) {
	    	$.bttTable.bootstrapTreeTable('expandAll');
	    } else {
	    	$.bttTable.bootstrapTreeTable('collapseAll');
	    }
	    expandFlag = expandFlag ? false: true;
	})
	
	// 按下ESC按钮关闭弹层
	$('body', document).on('keyup', function(e) {
	    if (e.which === 27) {
	        $.modal.closeAll();
	    }
	});
});

(function ($) {
    'use strict';
    $.fn.toTop = function(opt) {
        var elem = this;
        var win = $(window);
        var doc = $('html, body');
        var options = $.extend({
            autohide: true,
            offset: 50,
            speed: 500,
            position: true,
            right: 15,
            bottom: 5
        }, opt);
        elem.css({
            'cursor': 'pointer'
        });
        if (options.autohide) {
            elem.css('display', 'none');
        }
        if (options.position) {
            elem.css({
                'position': 'fixed',
                'right': options.right,
                'bottom': options.bottom,
            });
        }
        elem.click(function() {
            doc.animate({
                scrollTop: 0
            }, options.speed);
        });
        win.scroll(function() {
            var scrolling = win.scrollTop();
            if (options.autohide) {
                if (scrolling > options.offset) {
                    elem.fadeIn(options.speed);
                } else elem.fadeOut(options.speed);
            }
        });
    };
})(jQuery);

/** 刷新选项卡 */
var refreshItem = function(){
    var topWindow = $(window.parent.document);
	var currentId = $('.page-tabs-content', topWindow).find('.active').attr('data-id');
	var target = $('.RuoYi_iframe[data-id="' + currentId + '"]', topWindow);
    var url = target.attr('src');
    target.attr('src', url).ready();
}

/** 关闭选项卡 */
var closeItem = function(dataId){
	var topWindow = $(window.parent.document);
	if($.common.isNotEmpty(dataId)){
		window.parent.$.modal.closeLoading();
		// 根据dataId关闭指定选项卡
		$('.menuTab[data-id="' + dataId + '"]', topWindow).remove();
		// 移除相应tab对应的内容区
		$('.mainContent .RuoYi_iframe[data-id="' + dataId + '"]', topWindow).remove();
		return;
	}
	var panelUrl = window.frameElement.getAttribute('data-panel');
	$('.page-tabs-content .active i', topWindow).click();
	if($.common.isNotEmpty(panelUrl)){
		$('.menuTab[data-id="' + panelUrl + '"]', topWindow).addClass('active').siblings('.menuTab').removeClass('active');
		$('.mainContent .RuoYi_iframe', topWindow).each(function() {
            if ($(this).data('id') == panelUrl) {
                $(this).show().siblings('.RuoYi_iframe').hide();
                return false;
            }
		});
	}
}

/** 创建选项卡 */
function createMenuItem(dataUrl, menuName) {
	var panelUrl = window.frameElement.getAttribute('data-id');
    dataIndex = $.common.random(1,100),
    flag = true;
    if (dataUrl == undefined || $.trim(dataUrl).length == 0) return false;
    var topWindow = $(window.parent.document);
    // 选项卡菜单已存在
    $('.menuTab', topWindow).each(function() {
        if ($(this).data('id') == dataUrl) {
            if (!$(this).hasClass('active')) {
                $(this).addClass('active').siblings('.menuTab').removeClass('active');
                $('.page-tabs-content').animate({ marginLeft: ""}, "fast");
                // 显示tab对应的内容区
                $('.mainContent .RuoYi_iframe', topWindow).each(function() {
                    if ($(this).data('id') == dataUrl) {
                        $(this).show().siblings('.RuoYi_iframe').hide();
                        return false;
                    }
                });
            }
            flag = false;
            return false;
        }
    });
    // 选项卡菜单不存在
    if (flag) {
        var str = '<a href="javascript:;" class="active menuTab" data-id="' + dataUrl + '" data-panel="' + panelUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
        $('.menuTab', topWindow).removeClass('active');

        // 添加选项卡对应的iframe
        var str1 = '<iframe class="RuoYi_iframe" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" data-panel="' + panelUrl + '" seamless></iframe>';
        $('.mainContent', topWindow).find('iframe.RuoYi_iframe').hide().parents('.mainContent').append(str1);
        
        window.parent.$.modal.loading("数据加载中，请稍后...");
        $('.mainContent iframe:visible', topWindow).load(function () {
        	window.parent.$.modal.closeLoading();
        });

        // 添加选项卡
        $('.menuTabs .page-tabs-content', topWindow).append(str);
    }
    return false;
}

//日志打印封装处理
var log = {
    log: function(msg) {
        console.log(msg);
    },
    info: function(msg) {
        console.info(msg);
    },
    warn: function(msg) {
        console.warn(msg);
    },
    error: function(msg) {
        console.error(msg);
    }
};

//本地缓存处理
var storage = {
    set: function(key, value) {
        window.localStorage.setItem(key, value);
    },
    get: function(key) {
        return window.localStorage.getItem(key);
    },
    remove: function(key) {
        window.localStorage.removeItem(key);
    },
    clear: function() {
        window.localStorage.clear();
    }
};

/** 设置全局ajax处理 */
$.ajaxSetup({
    complete: function(XMLHttpRequest, textStatus) {
        if (textStatus == 'timeout') {
        	$.modal.alertWarning("服务器超时，请稍后再试！");
        	$.modal.enable();
            $.modal.closeLoading();
        } else if (textStatus == "parsererror" || textStatus == "error") {
        	$.modal.alertWarning("服务器错误，请联系管理员！");
        	$.modal.enable();
            $.modal.closeLoading();
        }
    }
});
var letter = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
var titleH = ["一","二","三","四","五","六","七","八","九","十","十一","十二","十三","十四","十五"];

/*document.write("今天是" + weekday[d.getDay()])*/

function addZero(i){
	if (i<10) {
		i="0" + i;
	}
	return i;
}
//获取当前字母的下一个字母和下标
function getNextLetter(a){
	var indexLetter = new Array();
	for(var ss=0; ss<letter.length;ss++){
		if(letter[ss] == a){
			indexLetter[0] = ss+2;
			indexLetter[1] = letter[ss+1];
			return indexLetter;
		}
	}
}

var typeHtmlList =  new Array();
//加载题目类型数据 selectName指定下拉框的name choseVal选中的值
function initTypeList(selectName,chooseVal){
	 $.ajax({
        type: "POST",
        url: ctx + "system/type/getlist",
        success: function(result){
            if(result.length > 0){
            	 typeHtmlList =  new Array();
	           	 for(var i=0 ; i<result.length ; i++){
	           		 var typeId = result[i].questionTypeId;
	           		 var typeName =  result[i].questionTypeName;
	           		 typeHtmlList[i] = result[i].questionTypeHtml;
	           		 var selected = "";
	           		 if(chooseVal!='' && typeId == chooseVal){
	           			selected = "selected";
	           		 }
	           		 $("select[name='"+selectName+"']").append('<option value="'+typeId+'" lang="'+i+'" '+selected+' >'+typeName+'</option>');
	           	 }
	           	 if(chooseVal!='')
	           		 $("select[name='questionTypeId']").trigger('change');
            }
        }
    });
}
//加载等级数据 selectName指定下拉框的name
function initLevelList(selectName,chooseVal){
	 $.ajax({
      type: "POST",
      url: ctx + "system/level/getlist",
      success: function(result){
          if(result.length > 0){
	           	 for(var i=0 ; i<result.length ; i++){
	           		 var levelId = result[i].levelId;
	           		 var levelName =  result[i].levelName;
	           		 var selected = "";
	           		 if(chooseVal!='' && levelId == chooseVal){
		           			selected = "selected";
	           		 }
	           		 $("select[name='"+selectName+"']").append('<option value="'+levelId+'" lang="'+i+'"  '+selected+' >'+levelName+'</option>');
	           	 }
          }
      }
  });
}
//加载科目数据 selectName指定下拉框的name
function initSubjectList(selectName,chooseVal){
	 $.ajax({
      type: "POST",
      url: ctx + "system/subject/getlist",
      success: function(result){
          if(result.length > 0){
	           	 for(var i=0 ; i<result.length ; i++){
	           		 var subjectId = result[i].subjectId;
	           		 var subjectName =  result[i].subjectName;
	           		 var selected = "";
	           		 if(chooseVal!='' && subjectId == chooseVal){
		           			selected = "selected";
	           		 }
	           		 $("select[name='"+selectName+"']").append('<option value="'+subjectId+'" lang="'+i+'"  '+selected+' >'+subjectName+'</option>');
	           	 }
          }
      }
  });
}
//加载知识点数据 selectName指定下拉框的name
function initPointList(selectName,chooseVal){
	 $.ajax({
      type: "POST",
      url: ctx + "system/point/list",
      success: function(result){
          if(result.length > 0){
	           	 for(var i=0 ; i<result.length ; i++){
	           		 var questionPointId = result[i].questionPointId;
	           		 var questionPointName =  result[i].questionPointName;
	           		 var selected = "";
	           		 if(chooseVal!='' && questionPointId == chooseVal){
		           			selected = "selected";
	           		 }
	           		 $("select[name='"+selectName+"']").append('<option value="'+questionPointId+'" lang="'+i+'"  '+selected+' >'+questionPointName+'</option>');
	           	 }
          }
      }
  });
}
/*知识点分类-新增-选择父部门树*/
function selectPointTree() {
    var options = {
        title: '知识点分类选择',
        width: "380",
        url: ctx + "system/point/selectPointTree/" + $("#treeId").val(),
        callBack: doSubmit
    };
    $.modal.openOptions(options);
}
function doSubmit(index, layero){
    var body = layer.getChildFrame('body', index);
    $("#treeId").val(body.find('#treeId').val());
    $("#treeName").val(body.find('#treeName').val());
    layer.close(index);
}

//输入框替换字符 ele元素  str指定字符
function replaceHtml(ele,str,str2){
	$(ele).val($(ele).val().replace(new RegExp(str,"gm"),str2));
}
//全部替换 o被替换 t替换成
function replaceAllStr(str,o,t){
	if(t == " "){
		return str.replace(new RegExp(/\s/g,"gm"),t);
	}
	return str.replace(new RegExp(o,"gm"),t);
}

function sleep(n) { //n表示的毫秒数
    var start = new Date().getTime();
    while (true) if (new Date().getTime() - start > n) break;
}  

function replaceStrs(ele){
	replaceHtml(ele,">","＞");
	replaceHtml(ele,"<","＜");
	replaceHtml(ele,'"','“');
}


//xml转换为答题时的html
function xmltoShowHtml(){
	
}
function isNotEmpty(parm){
	if(parm && parm != null && parm != 'undefined' && parm != ''){
		return true;
	}
	return false;
}
function showPythonCode(textName){
	if($("textarea[name='"+textName+"']").val()){
		$.modal.open("python编译", ctx + "system/question/python?&inputName="+textName);
	}else{
		alert("请输入代码");
	}
}
function showHtml(ele){
	var littleXml = $(ele).attr('lang');
	var htmlStr = "";
	var questionTypeName = $(littleXml).find("questionType").text();//题型名称
	var questionCount = parseInt($(littleXml).find("questionCount").text());//题型数量
	var questionId = parseInt($(littleXml).find("questionId").text());//题目ID
	var answer = $(littleXml).find("answer").text(); 
	var titleFileBase64 = $(littleXml).find("titleFileBase64").text(); //标题
	var titStr = '';
	if(isNotEmpty(titleFileBase64)){
		titStr = '<img width="300px" src="'+titleFileBase64+'" />';
	}
	var typeMode = $(littleXml).find("typeMode").text();//题型模式 0实操 1笔试 2答辩
	var questionTitle = $(littleXml).find("questionTitle").text(); //标题
	questionTitle = replaceAllStr(questionTitle,'\n','<br/>');
	questionTitle = replaceAllStr(questionTitle,' ','&nbsp;');
	var typeId = $(littleXml).find("typeId").text();
	if(typeId != "8" && typeId != "12"){//笔试
		
		var ifRadio = $(littleXml).find("questionRadio").length; //单选
		var ifCheckBox = $(littleXml).find("questionCheckBox").length; //多选
		var ifFile = $(littleXml).find("questionFile").length;//答辩文件
		var options = $(littleXml).find("options").length; //选项个数
		var codes = $(littleXml).find("options").find("code");//选项编号
		var contexts = $(littleXml).find("options").find("context");//选项
		var optionimg = $(littleXml).find("options").find("optionimg");//图片
		
		var dxstr = "";
		if(questionTypeName.indexOf("多选")>-1){
			dxstr = "(选择"+answer.split(',').length+"项)";
		}
		
		htmlStr += '<div class="form-group"><p class="form-control-static">'+questionTitle+dxstr+'</p>'+titStr+'</div>';
		htmlStr += '<input type="hidden" name="questionId" value='+questionId+' />';
		if(ifRadio>0){
			htmlStr += '<div class="form-group">';
			for(var i=0; i<options ; i++){
				var img = "";
				if(isNotEmpty($(optionimg[i]).text())){
					img = "<br/><img width='120px' src='"+$(optionimg[i]).text()+"' />";
				}
				htmlStr +=
					"<div class=\"radio\"> "+
					"	<label> "+
					"		<input type=\"radio\" value=\""+$(codes[i]).text()+"\"  name=\"questionVal\"> " +$(codes[i]).text()+ "：" + $(contexts[i]).text() + img +
					"	</label> "+
					"</div> ";
			}
			htmlStr += '</div>';
			ifInput = false;
		}else if(ifCheckBox>0){
			htmlStr += '<div class="form-group">';
			for(var i=0; i<options ; i++){
				var img = "";
				if(isNotEmpty($(optionimg[i]).text())){
					img = "<br/><img width='120px' src='"+$(optionimg[i]).text()+"' />";
				}
				htmlStr += 
					"<div class=\"radio cbox\"> "+
					"	<label> "+
					"		<input  type=\"checkbox\" value=\""+$(codes[i]).text()+"\"  name=\"questionChecksno"+i+"\"> " +$(codes[i]).text()+ "：" + $(contexts[i]).text() + img +
					"	</label> "+
					"</div> ";
			}
			htmlStr += '<input type=\"hidden\" id=\"questionVal\" name=\"questionVal\" / > ';
			htmlStr += '</div>';
			ifInput = false;
		}else if(typeId == "4"){
			var count = ((questionTitle.split('(')).length-1) + ((questionTitle.split('（')).length-1);
			for(var i=1;i<=count;i++){
				htmlStr += '<div class="form-group">' +
				'		<input type=\"text\" class=\"form-control tkt\" name=\"questionValsno'+i+'\"  placeholder=\"问题'+i+'\" / > ' +
				'</div>';
			}
			htmlStr += '<input type=\"hidden\" id=\"questionVal\" name=\"questionVal\" / > ';
			
		}else if(typeId == "6"){//scratch编程题
			htmlStr +=	'<div class="form-group"> '+
								'打开scratch网页版(无须登录)<a href="https://www.scratch-cn.cn/create" target="_blank">地址1</a>   <a href="https://scratch.mit.edu/projects/editor/" target="_blank">地址2</a> 或者使用客户端<br/>'+
								'<a onclick="$(this).next().click();" id="btnTitle" ><i class="fa fa-cloud-upload"></i> 将制作好的sb3后缀文件上传</a>'+
								'<input type="file" accept=".sb3" onchange="onchangeFile(this)" name=\"questionVal\" class="hide" />'+
						'</div>';

		}else{//问答题、python编程
			htmlStr +=	'<div class="form-group">' +
			'		<textarea class="form-control" rows="14" name=\"questionVal\" placeholder=\"如果是python编程题，请务必将代码填入此处 \" ></textarea>' +
			'		<a href="javascript:showPythonCode(\'questionVal\')">运行代码</a>' +
			'</div>';
		}
	}else if(typeId == "8"){//答辩题
		htmlStr += '<div class="form-group"><p class="form-control-static">答辩题：'+questionTitle+'</p>'+titStr+'</div>';
		htmlStr += '<input type="hidden" name="questionId" value='+questionId+' />';
		htmlStr += '<a href="javascript:speekTitle()"><i class="fa fa-volume-down"></i> 重新听题(如果没有声音，请检查声音是否开启，或重新进入我的考试页面点击答辩进入此页面)</a>';
		htmlStr += '<script>function speekTitle(){const msg = new SpeechSynthesisUtterance("答辩题，请听题：'+questionTitle+',请开始答辩");msg.rate=0.4;msg.volume = 100;msg.volume = 0.5; window.speechSynthesis.speak(msg);}</script>';
	}else if(typeId == "12"){//实操题
		htmlStr += '<div class="form-group"><p class="form-control-static">实操题：'+questionTitle+'</p>'+titStr+'</div>';
		htmlStr += '<input type="hidden" name="questionId" value='+questionId+' />';
	}
	$.modal.showInfo("题目预览",htmlStr,'80%','500px');
}
function onchangeFile(ele){
	if($(ele).val()==''){
		$.modal.alert("未选择文件，请重新选择");
	}
	if($(ele).val()!=''){
		$(ele).prev().html($(ele).prev().html().replace('将制作好的sb3后缀文件上传','已选择1个文件').replace('未选择文件','已选择1个文件'));
	}else{
		$(ele).prev().html($(ele).prev().html().replace('将制作好的sb3后缀文件上传','未选择文件').replace('已选择1个文件','未选择文件'));
	}
}
function getValueByName(datajson,name){
	if(datajson){
		for(var i=0;i<datajson.length;i++){
			if(name == datajson[i].name){
				return datajson[i].value;
			}
		}
	}
	return "";
}
//fdata 已选中的缓存
function returnAllHtml(xml,fdata,tMode){
	var datajson;
	if(fdata){
		datajson = JSON.parse(fdata);
	}
	var questions = $(xml).find("question");
	var paperTitle = $(xml).find("paperTitle").text();
	var allScore = $(xml).find("allScore").text();
	var htmlStr = "<form id='examForm' action='submitexam' method='post'  autocomplete='off'  enctype='multipart/form-data'><center><h1>"+paperTitle+"</h1>总分："+allScore+"分</center>";
	var typeName = "";
	var typeCount = 0;
	var typeScore = 0;
	var titleHIdx = 0;
	for(var s=0 ;s<questions.length; s++){
		var littleXml = $(questions[s]);
		var typeId = $(littleXml).find("typeId").text();
		var questionTypeName = $(littleXml).find("questionType").text();//题型名称
		var questionCount = parseInt($(littleXml).find("questionCount").text());//题型数量
		var questionId = parseInt($(littleXml).find("questionId").text());//题目ID
		var score = parseFloat($(littleXml).find("score").text()); //题目分数
		var answer = $(littleXml).find("answer").text(); 
		var titleFileBase64 = $(littleXml).find("titleFileBase64").text(); //标题
		var titStr = '';
		if(isNotEmpty(titleFileBase64)){
			titStr = '<img width="550px" src="'+titleFileBase64+'" />';
		}
		var typeMode = $(littleXml).find("typeMode").text();//题型模式 0实操 1笔试 2答辩
		var questionTitle = $(littleXml).find("questionTitle").text(); //标题
		questionTitle = replaceAllStr(questionTitle,'\n','<br/>');
		questionTitle = replaceAllStr(questionTitle,' ','&nbsp;');
		if(typeMode == "1" && tMode == typeMode){//笔试题
			if(typeName != questionTypeName){
				typeName = questionTypeName;
				typeCount = questionCount;
				typeScore = score;
				htmlStr += "<h2>"+titleH[titleHIdx]+"、"+typeName+"（共"+typeCount+"道题，每题"+typeScore+"分）</h2>";
				titleHIdx ++;
			}
			
			var ifRadio = $(littleXml).find("questionRadio").length; //单选
			var ifCheckBox = $(littleXml).find("questionCheckBox").length; //多选
			var ifFile = $(littleXml).find("questionFile").length;//答辩文件
			var options = $(littleXml).find("options").length; //选项个数
			var codes = $(littleXml).find("options").find("code");//选项编号
			var contexts = $(littleXml).find("options").find("context");//选项
			var optionimg = $(littleXml).find("options").find("optionimg");//图片
			
			var dxstr = "";
			if(questionTypeName.indexOf("多选")>-1){
				dxstr = "(选择"+answer.split(',').length+"项)";
			}
			
			htmlStr += '<div class="form-group"><p class="form-control-static">'+(s+1)+'：'+questionTitle+dxstr+'('+score+'分)'+'</p>'+titStr+'</div>';
			htmlStr += '<input type="hidden" name="questionId'+(s+1)+'" value='+questionId+' />';
			if(ifRadio>0){
				htmlStr += '<div class="form-group">';
				for(var i=0; i<options ; i++){
					var img = "";
					if(isNotEmpty($(optionimg[i]).text())){
						img = "<br/><img width='150px' src='"+$(optionimg[i]).text()+"' />";
					}
					var choseval = getValueByName(datajson,"questionVal"+(s+1));
					var checked = "";
					if(choseval == $(codes[i]).text()){
						checked = " checked ";
					}
					htmlStr +=
						"<div class=\"radio\"> "+
						"	<label> "+
						"		<input type=\"radio\" "+checked+" value=\""+$(codes[i]).text()+"\"  name=\"questionVal"+(s+1)+"\"> " +$(codes[i]).text()+ "：" + $(contexts[i]).text() + img +
						"	</label> "+
						"</div> ";
				}
				htmlStr += '</div>';
				ifInput = false;
			}else if(ifCheckBox>0){
				htmlStr += '<div class="form-group">';
				for(var i=0; i<options ; i++){
					var img = "";
					if(isNotEmpty($(optionimg[i]).text())){
						img = "<br/><img width='150px' src='"+$(optionimg[i]).text()+"' />";
					}
					var choseval  = getValueByName(datajson,"questionChecks"+(s+1)+"no"+i);
					var checked = "";
					if(choseval == $(codes[i]).text()){
						checked = " checked ";
					}
					htmlStr += 
						"<div class=\"radio cbox\"> "+
						"	<label> "+
						"		<input  type=\"checkbox\" "+checked+" value=\""+$(codes[i]).text()+"\"  name=\"questionChecks"+(s+1)+"no"+i+"\"> " +$(codes[i]).text()+ "：" + $(contexts[i]).text() + img +
						"	</label> "+
						"</div> ";
				}
				htmlStr += '<input type=\"hidden\" id=\"questionVal'+(s+1)+'\" name=\"questionVal'+(s+1)+'\" / > ';
				htmlStr += '</div>';
				ifInput = false;
			}else if(typeId == "4"){
				var count = ((questionTitle.split('(')).length-1) + ((questionTitle.split('（')).length-1);
				for(var i=1;i<=count;i++){
					htmlStr += '<div class="form-group">' +
					'		<input type=\"text\" class=\"form-control tkt'+(s+1)+'\" name=\"questionVals'+(s+1)+'no'+i+'\" value=\"'+getValueByName(datajson,'questionVals'+(s+1)+'no'+i)+'\" placeholder=\"问题'+i+'\" / > ' +
					'</div>';
				}
				htmlStr += '<input type=\"hidden\" id=\"questionVal'+(s+1)+'\" name=\"questionVal'+(s+1)+'\" / > ';
				
			}else if(typeId == "6"){//scratch编程题
				htmlStr +=	'<div class="form-group"> '+
									'打开scratch网页版(无须登录)<a href="https://www.scratch-cn.cn/create" target="_blank">地址1</a>   <a href="https://scratch.mit.edu/projects/editor/" target="_blank">地址2</a> 或者使用客户端<br/>'+
									'<a onclick="$(this).next().click();" id="btnTitle" ><i class="fa fa-cloud-upload"></i> 将制作好的sb3后缀文件上传</a>'+
									'<input type="file" accept=".sb3" onchange="onchangeFile(this)" name=\"scratchfile'+(s+1)+'\" class="hide" />'+
							'</div>' + 
							'<input type=\"hidden\" name=\"questionVal'+(s+1)+'\" value=\"file'+(s+1)+'\"  />';

			}else{//问答题、python编程
				htmlStr +=	'<div class="form-group">' +
				'		<textarea class="form-control" rows="14" name=\"questionVal'+(s+1)+'\"  placeholder=\"如果是python编程题，请务必将代码填入此处 \">'+getValueByName(datajson,"questionVal"+(s+1))+'</textarea>' +
				'		<a href="javascript:showPythonCode(\'questionVal'+(s+1)+'\')">运行代码</a>' +
				'</div>';
			}
		}else if(typeMode == "2" && tMode == typeMode){//答辩题
			htmlStr += '<div class="form-group"><p class="form-control-static">答辩题：'+questionTitle+'('+score+'分)'+'</p>'+titStr+'</div>';
			htmlStr += '<input type="hidden"  name="questionId" value='+questionId+' />';
			htmlStr += '<input type="hidden"  name="typeId" value='+typeId+' />';
			htmlStr += '<a href="javascript:speekTitle()"><i class="fa fa-volume-down"></i> 重新听题(如果没有声音，请检查声音是否开启，或重新进入我的考试页面点击答辩进入此页面)</a>';
			htmlStr += '<script>function speekTitle(){const msg = new SpeechSynthesisUtterance("答辩题，请听题：'+questionTitle+',请开始答辩");msg.rate=0.4;msg.volume = 100; window.speechSynthesis.speak(msg);}</script>';
		}else if(typeMode == "0" && tMode == typeMode){//实操题
			htmlStr += '<div class="form-group"><p class="form-control-static">实操题：'+questionTitle+'('+score+'分)'+'</p>'+titStr+'</div>';
			htmlStr += '<input type="hidden"  name="questionId" value='+questionId+' />';
			htmlStr += '<input type="hidden"  name="typeId" value='+typeId+' />';
		}
	}
	htmlStr += "</form>";
	return htmlStr;
}
//fdata 已选中的缓存
function alertAllHtml(xml,fdata){
	var datajson;
	if(fdata){
		datajson = JSON.parse(fdata);
	}
	var questions = $(xml).find("question");
	var paperTitle = $(xml).find("paperTitle").text();
	var allScore = $(xml).find("allScore").text();
	var htmlStr = "<form id='examForm' action='submitexam' method='post'  autocomplete='off'  enctype='multipart/form-data'><center><h1>"+paperTitle+"</h1>总分："+allScore+"分</center>";
	var typeName = "";
	var typeCount = 0;
	var typeScore = 0;
	var titleHIdx = 0;
	for(var s=0 ;s<questions.length; s++){
		var littleXml = $(questions[s]);
		var typeId = $(littleXml).find("typeId").text();
		var questionTypeName = $(littleXml).find("questionType").text();//题型名称
		var questionCount = parseInt($(littleXml).find("questionCount").text());//题型数量
		var questionId = parseInt($(littleXml).find("questionId").text());//题目ID
		var score = parseFloat($(littleXml).find("score").text()); //题目分数
		var answer = $(littleXml).find("answer").text(); 
		var titleFileBase64 = $(littleXml).find("titleFileBase64").text(); //标题
		var titStr = '';
		if(isNotEmpty(titleFileBase64)){
			titStr = '<img width="550px" src="'+titleFileBase64+'" />';
		}
		var typeMode = $(littleXml).find("typeMode").text();//题型模式 0实操 1笔试 2答辩
		var questionTitle = $(littleXml).find("questionTitle").text(); //标题
		questionTitle = replaceAllStr(questionTitle,'\n','<br/>');
		questionTitle = replaceAllStr(questionTitle,' ','&nbsp;');
		if(typeMode == "1"){//笔试题
			if(typeName != questionTypeName){
				typeName = questionTypeName;
				typeCount = questionCount;
				typeScore = score;
				htmlStr += "<h2>"+titleH[titleHIdx]+"、"+typeName+"（共"+typeCount+"道题，每题"+typeScore+"分）</h2>";
				titleHIdx ++;
			}
			
			var ifRadio = $(littleXml).find("questionRadio").length; //单选
			var ifCheckBox = $(littleXml).find("questionCheckBox").length; //多选
			var ifFile = $(littleXml).find("questionFile").length;//答辩文件
			var options = $(littleXml).find("options").length; //选项个数
			var codes = $(littleXml).find("options").find("code");//选项编号
			var contexts = $(littleXml).find("options").find("context");//选项
			var optionimg = $(littleXml).find("options").find("optionimg");//图片
			
			var dxstr = "";
			if(questionTypeName.indexOf("多选")>-1){
				dxstr = "(选择"+answer.split(',').length+"项)";
			}
			
			htmlStr += '<div class="form-group"><p class="form-control-static">'+(s+1)+'：'+questionTitle+dxstr+'('+score+'分)'+'</p>'+titStr+'</div>';
			htmlStr += '<input type="hidden" name="questionId'+(s+1)+'" value='+questionId+' />';
			if(ifRadio>0){
				htmlStr += '<div class="form-group">';
				for(var i=0; i<options ; i++){
					var img = "";
					if(isNotEmpty($(optionimg[i]).text())){
						img = "<br/><img width='150px' src='"+$(optionimg[i]).text()+"' />";
					}
					var choseval = getValueByName(datajson,"questionVal"+(s+1));
					var checked = "";
					if(choseval == $(codes[i]).text()){
						checked = " checked ";
					}
					htmlStr +=
						"<div class=\"radio\"> "+
						"	<label> "+
						"		<input type=\"radio\" "+checked+" value=\""+$(codes[i]).text()+"\"  name=\"questionVal"+(s+1)+"\"> " +$(codes[i]).text()+ "：" + $(contexts[i]).text() + img +
						"	</label> "+
						"</div> ";
				}
				htmlStr += '</div>';
				ifInput = false;
			}else if(ifCheckBox>0){
				htmlStr += '<div class="form-group">';
				for(var i=0; i<options ; i++){
					var img = "";
					if(isNotEmpty($(optionimg[i]).text())){
						img = "<br/><img width='150px' src='"+$(optionimg[i]).text()+"' />";
					}
					var choseval  = getValueByName(datajson,"questionChecks"+(s+1)+"no"+i);
					var checked = "";
					if(choseval == $(codes[i]).text()){
						checked = " checked ";
					}
					htmlStr += 
						"<div class=\"radio cbox\"> "+
						"	<label> "+
						"		<input  type=\"checkbox\" "+checked+" value=\""+$(codes[i]).text()+"\"  name=\"questionChecks"+(s+1)+"no"+i+"\"> " +$(codes[i]).text()+ "：" + $(contexts[i]).text() + img +
						"	</label> "+
						"</div> ";
				}
				htmlStr += '<input type=\"hidden\" id=\"questionVal'+(s+1)+'\" name=\"questionVal'+(s+1)+'\" / > ';
				htmlStr += '</div>';
				ifInput = false;
			}else if(typeId == "4"){
				var count = ((questionTitle.split('(')).length-1) + ((questionTitle.split('（')).length-1);
				for(var i=1;i<=count;i++){
					htmlStr += '<div class="form-group">' +
					'		<input type=\"text\" class=\"form-control tkt'+(s+1)+'\" name=\"questionVals'+(s+1)+'no'+i+'\" value=\"'+getValueByName(datajson,'questionVals'+(s+1)+'no'+i)+'\" placeholder=\"问题'+i+'\" / > ' +
					'</div>';
				}
				htmlStr += '<input type=\"hidden\" id=\"questionVal'+(s+1)+'\" name=\"questionVal'+(s+1)+'\" / > ';
				
			}else if(typeId == "6"){//scratch编程题
				htmlStr +=	'<div class="form-group"> '+
									'打开scratch网页版(无须登录)<a href="https://www.scratch-cn.cn/create" target="_blank">地址1</a>   <a href="https://scratch.mit.edu/projects/editor/" target="_blank">地址2</a> 或者使用客户端<br/>'+
									'<a onclick="$(this).next().click();" id="btnTitle" ><i class="fa fa-cloud-upload"></i> 将制作好的sb3后缀文件上传</a>'+
									'<input type="file"  accept=".sb3" onchange="onchangeFile(this)" name=\"scratchfile'+(s+1)+'\" class="hide" />'+
							'</div>' + 
							'<input type=\"hidden\" name=\"questionVal'+(s+1)+'\" value=\"file'+(s+1)+'\"  />';

			}else{//问答题、python编程
				htmlStr +=	'<div class="form-group">' +
				'		<textarea class="form-control" rows="14" name=\"questionVal'+(s+1)+'\"  placeholder=\"如果是python编程题，请务必将代码填入此处 \">'+getValueByName(datajson,"questionVal"+(s+1))+'</textarea>' +
				'		<a href="javascript:showPythonCode(\'questionVal'+(s+1)+'\')">运行代码</a>' +
				'</div>';
			}
		}else if(typeMode == "0" ){//实操题
			htmlStr += '<div class="form-group"><p class="form-control-static">实操题：'+questionTitle+'('+score+'分)'+'</p>'+titStr+'</div>';
			htmlStr += '<input type="hidden"  name="questionId" value='+questionId+' />';
			htmlStr += '<input type="hidden"  name="typeId" value='+typeId+' />';
		}else if(typeMode == "2" ){//答辩题
			htmlStr += '<div class="form-group"><p class="form-control-static">答辩题：'+questionTitle+'('+score+'分)'+'</p>'+titStr+'</div>';
			htmlStr += '<input type="hidden"  name="questionId" value='+questionId+' />';
			htmlStr += '<input type="hidden"  name="typeId" value='+typeId+' />';
			htmlStr += '<a href="javascript:speekTitle()"><i class="fa fa-volume-down"></i> 重新听题(如果没有声音，请检查声音是否开启，或重新进入我的考试页面点击答辩进入此页面)</a>';
			htmlStr += '<script>function speekTitle(){const msg = new SpeechSynthesisUtterance("答辩题，请听题：'+questionTitle+',请开始答辩");msg.rate=0.4;msg.volume = 100; window.speechSynthesis.speak(msg);}</script>';
		}
	}
	htmlStr += "</form>";
	return htmlStr;
}

//笔试题
function showAllHtml(ele){
	var xml = $(ele).attr('lang');
	$.modal.showInfo("题目预览",alertAllHtml(xml,null),'80%','500px');
}

function blobToDataURL(blob,cb) {
    let reader = new FileReader();
    reader.onload = function (evt) {
      var base64 = evt.target.result
      cb(base64)
    };
    reader.readAsDataURL(blob);
}

function chooseImg(file,fileBase64Id,btnTitle){
	var img = file.files[0]
	var fileSize = (img.size / 1024).toFixed(0);
	if(fileSize <= 800){
		if(img){
		  var url = URL.createObjectURL(img);
		  var base64 = blobToDataURL(img,function(base64Url) {
			$("#"+fileBase64Id).val(base64Url);
			$("#"+btnTitle).html("<i class=\"fa fa-file-image-o\"></i> "+file.value);
		  })
		}
	}else{
		$.modal.alert("选择的图片不能大于800K");
		$(file).val("");
		$("#"+fileBase64Id).val("");
		$("#"+btnTitle).html("<i class=\"fa fa-file-image-o\"></i> 选择图片");
	}
}


/* 关闭窗口 */
function closeWindow() {
    var userAgent = navigator.userAgent;
    if (userAgent.indexOf("Firefox") != -1 || userAgent.indexOf("Chrome") != -1) {
        location.href = "about:blank";
    } else {
        window.opener = null;
        window.open('', '_self');
    }
    window.close();
}
/* 对比两个时间字符串相差多少分钟并返回 */
function dateStrCompared(aStr,bStr){
	var a = new Date(aStr);
	var b = new Date(bStr);
	var aCount = a.getTime() / 60000;
	var bCount = b.getTime() / 60000;
	return parseInt(bCount - aCount);
}
/* 对比两个时间相差多少分钟并返回 */
function dateCompared(a,b){
	var aCount = a.getTime() / 60000;
	var bCount = b.getTime() / 60000;
	return parseInt(bCount - aCount);
}
/* 对比两个时间相差多少秒并返回 */
function dateComparedSecond(a,b){
	var aCount = a.getTime() / 60000;
	var bCount = b.getTime() / 60000;
	var c = bCount - aCount;
	var s = "0.";
	if(c<0){
		s = "-0.";
	}
	var min = parseFloat(s+c.toString().split('.')[1]);
	return Math.round(min*60);
}
//查看回放
function checkReply(courseid,classid){
    var rurl = ctx + "system/examstudent/replay/"+courseid+"/"+classid;
	$.ajax({
        url: rurl,
        type: "Get",
        dataType: "text",
        data: {},
        success: function (data) {
        	if(data!="error"){
				window.open(data, "_blank");
			}else{
	            $.modal.alert("查看回放失败，请确认该学员参加过考试，并正确录像！");
			}
        }
    });
}
//回显部门数据
function getDeptNameById(datas, id) {
	var actions = [];
	var deptName = "";
	if(id!=null){
		$.each(datas, function(index, data) {
			if (data.deptId == id) {
				deptName = data.deptName;
			}
		});
	}else{
		deptName = "无";
	}
    if(deptName==""){
    	deptName = "未知机构(已删除机构)";
    }
    actions.push($.common.sprintf("<span class='%s'>%s</span>", "", deptName));
    return actions.join('');
}
//回显角色数据
function getRoleNameById(datas, id) {
	var actions = [];
    $.each(datas, function(index, data) {
        if (data.roleId == id) {
        	actions.push($.common.sprintf("<span class='%s'>%s</span>", "", data.roleName));
            return false;
        }
    });
    return actions.join('');
}