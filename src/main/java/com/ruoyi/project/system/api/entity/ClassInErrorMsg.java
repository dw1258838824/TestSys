package com.ruoyi.project.system.api.entity;

public class ClassInErrorMsg {
	
	/**
	 * classIn返回说明
	 */
   public static String getErrorMsgByCode(Integer errno,String apiName) {
	   if(apiName.equals("addCourseClass")) {
		   switch (errno) {
			   case 1: return "成功执行";
			   case 100: return "参数不全或错误";
			   case 102: return "无权限（安全验证没通过）";
			   case 104: return "操作失败（未知错误）";
			   case 114: return "服务器异常";
			   case 119: return "结束时间须晚于开课时间";
			   case 120: return "开课时间至少一分钟以后";
			   case 136: return "机构下面没有该老师，请在机构下添加该老师";
			   case 144: return "机构下无此课程";
			   case 147: return "没有此课程信息";
			   case 149: return "课程已删除";
			   case 153: return "课程已过期";
			   case 160: return "机构下无此云盘目录";
			   case 165: return "单节课不能少于15分钟且不能超过24小时";
			   case 172: return "课程下的学生不能添加为老师";
			   case 173: return "课程下的旁听不能添加为老师";
			   case 259: return "上台人数设置超出最大限制";
			   case 268: return "课节开始时间超出允许范围（课节开始时间须在3年以内）";
			   case 280: return "课节创建成功，录课参数设置错误，录课开启失败";
			   case 281: return "课节创建成功，视频服务有问题，录课开启失败";
			   case 318: return "助教不是本机构老师";
			   case 319: return "课程下的学生不能添加为助教";
			   case 320: return "课程下的旁听不能添加为助教";
			   case 322: return "课节老师不能添加为助教";
			   case 323: return "助教购买课节失败";
			   case 324: return "课节老师加入教师列表失败";
			   case 326: return "课节助教加入教师列表失败";
			   case 330: return "助教账号格式不正确";
			   case 368: return "当前学生上台数不支持高清";
			   case 369: return "该课程/课节类型暂不支持该操作";
			   case 387: return "老师已被停用";
			   case 388: return "助教已被停用";
			   case 398: return "数据已经存在（唯一标识已存在）";
			   case 400: return "请求数据不合法";
			   case 800: return "老师被停用中";
			   case 804: return "助教被停用中";
			   case 824: return "课节添加成功，课节简介设置失败";
			   default:
				   break;
		   }
	   }
	   return "";
   }
}
