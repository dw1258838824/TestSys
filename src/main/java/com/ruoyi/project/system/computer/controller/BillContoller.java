package com.ruoyi.project.system.computer.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.api.entity.ClassInErrorMsg;
import com.ruoyi.project.system.api.service.ClassInService;
import com.ruoyi.project.system.bill.domain.Bill;
import com.ruoyi.project.system.bill.service.IBillService;
import com.ruoyi.project.system.exam.domain.TExam;
import com.ruoyi.project.system.exam.service.ITExamService;
import com.ruoyi.project.system.register.domain.TExamRegister;
import com.ruoyi.project.system.register.service.ITExamRegisterService;
import com.ruoyi.project.system.student.domain.Student;
import com.ruoyi.project.system.student.service.IStudentService;
import com.testsys.common.UnionPayOfWXPay;

@RestController
@RequestMapping("/student/bill")
public class BillContoller {

	private static final Logger log = LoggerFactory.getLogger(BillContoller.class);
    @Autowired
    private IBillService billService;
    @Autowired
    private ITExamService examService;

    @Autowired
    private ITExamRegisterService examRegisterService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ClassInService classInService;

    @Value("${wechat.notify_url}")
    private String notifyUrl;

    private String payIP = "47.93.241.224";

    @GetMapping("/wechat/pay")
    public AjaxResult wechatPay(Long examId, HttpServletRequest request){
        TExam exam = examService.selectTExamById(examId);
        String rignEndTime = DateUtils.parseDateToStr("yyyyMMddHHmmss", exam.getRignEndTime());
        if(null != exam && !"1".equals(exam.getState())){
            return new AjaxResult(AjaxResult.Type.ERROR,"失败，报名时间已截止");
        }
        Bill b = new Bill();
        //b.setPayUserId(ShiroUtils.getSysUser().getStudent().getStudentId());
        b.setPayUserId(ShiroUtils.getSysUser().getUserId());
        b.setBillType("1");
        b.setPayState("1");
        b.setBillName("1");
        b.setRefId(exam.getExamId());
        List<Bill> billList = billService.selectBillList(b);
        //判断当前考试是否已经报名
        if(null != billList && billList.size() > 0){
            return new AjaxResult(AjaxResult.Type.ERROR,"无法重复报名");
        }
        if(null != exam){
            String billNo = UUID.randomUUID().toString().replaceAll("-","");
            Bill bill = new Bill();
            bill.setBillNo(billNo);//订单编号
            bill.setBillType("1");//账单类型  1收入  2支出
            bill.setBillName("1");//账单名称  1报名费 2补考费
            //bill.setPayUserId(ShiroUtils.getSysUser().getStudent().getStudentId());//支付人
            bill.setPayUserId(ShiroUtils.getSysUser().getUserId());//支付人
            bill.setAmount(exam.getPrice());//价格
            if(exam.getPrice() > 0.00){
            	bill.setPayState("0");//交易状态  0处理中 1成功 2异常
            }else {
            	bill.setPayState("1");//交易状态  0处理中 1成功 2异常
            }
            bill.setRefId(examId);//关联编号
            int count = billService.insertBill(bill);
            if(count > 0){
            	if(bill.getAmount() > 0.00) {
	                Map<String,String> wechatPay = UnionPayOfWXPay.unifiedOrder(
	                    "2",
	                    "",
	                    billNo,
	                    examId.toString(),
	                    exam.getExamTitle(),
	                    String.valueOf(Double.valueOf(exam.getPrice() * 100).intValue()),
	                    payIP,
	                    notifyUrl,
	                    rignEndTime);
	                Map<String,String> re = Maps.newHashMap();
	                re.put("url",wechatPay.get("code_url"));
	                re.put("billNo",billNo);
	                return new AjaxResult(AjaxResult.Type.SUCCESS,"成功",re);
            	}else {
            		Student stu = ShiroUtils.getSysUser().getStudent();
            		TExamRegister er = new TExamRegister();
                    er.setStudentId(stu.getStudentId());
                    er.setExamId(exam.getExamId());
                    er.setState("1");
                    er.setExamCode(DateUtils.dateTimeNow("yyyyMMdd")+(ShiroUtils.getSysUser().getDeptId()==null?"000":ShiroUtils.getSysUser().getDeptId())+""+stu.getStudentId());
                    er.setCtime(new Date());
                	try {
        				Map<String, String> params = Maps.newHashMap();
        				params.put("courseId", exam.getUid()+"");;
        				params.put("className", stu.getStudentName());
        				params.put("beginTime", (exam.getBeginTime().getTime()/1000)+"");
        				params.put("endTime", (exam.getArgueEndTime().getTime()/1000)+"");
        				params.put("teacherUid", stu.getUid()+"");
        				params.put("record","1");
        				params.put("live","1");
        				params.put("replay","1");
        				String json = classInService.classInHttp("addCourseClass", params);//调用classIn加课节
        				JSONObject obj = JSONObject.parseObject(json);
        				Integer errno = obj.getJSONObject("error_info").getInteger("errno");
        				if(null!=errno && errno==1) {
        					Long uid = obj.getLong("data");//获取课节ID
        					er.setUid(uid);
        					er.setLiveurl(obj.getJSONObject("more_data").getString("live_url"));
        				}else {
        					log.error("调用classIn添加课节失败",json);
        					return new AjaxResult(AjaxResult.Type.ERROR,ClassInErrorMsg.getErrorMsgByCode(errno, "addCourseClass"));
        				}
        			} catch (Exception e) {
        				log.error("调用classIn添加课节失败",e);
        		        return new AjaxResult(AjaxResult.Type.ERROR,"失败");
        			}
        			//添加报名信息
                    examRegisterService.insertTExamRegister(er);
                    return new AjaxResult(AjaxResult.Type.SUCCESS,"成功",er.getExamCode());
            	}
                
            }
        }
        return new AjaxResult(AjaxResult.Type.ERROR,"失败");
    }

    @GetMapping("isSuccess")
    public AjaxResult billIsSuccess(HttpServletRequest request){
    	String billNo = request.getParameter("billNo");
    	String examId = request.getParameter("examId");
    	String studentId = request.getParameter("studentId");
        Bill bill = billService.selectBillByBillNo(billNo);
        if("0".equals(bill.getPayState())){
            return new AjaxResult(AjaxResult.Type.SUCCESS,"订单待支付","WAIT");
        }else if("1".equals(bill.getPayState())){
        	TExamRegister tExamRegister = new TExamRegister();
        	tExamRegister.setExamId(Long.parseLong(examId));
        	tExamRegister.setStudentId(Long.parseLong(studentId));
            return new AjaxResult(AjaxResult.Type.SUCCESS,"SUCCESS",examRegisterService.selectTExamRegisterByStudentExam(tExamRegister).getExamCode());
        }else{
            return new AjaxResult(AjaxResult.Type.SUCCESS,"订单支付异常","ERROR");
        }
    }


}
