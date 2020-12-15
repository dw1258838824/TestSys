package com.ruoyi.project.system.api.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.configure.StudentSession;
import com.ruoyi.project.system.api.entity.ClassInErrorMsg;
import com.ruoyi.project.system.api.entity.WeChatCallBack;
import com.ruoyi.project.system.api.entity.xml;
import com.ruoyi.project.system.api.service.ClassInService;
import com.ruoyi.project.system.bill.domain.Bill;
import com.ruoyi.project.system.bill.service.IBillService;
import com.ruoyi.project.system.exam.domain.TExam;
import com.ruoyi.project.system.exam.service.ITExamService;
import com.ruoyi.project.system.register.domain.TExamRegister;
import com.ruoyi.project.system.register.service.ITExamRegisterService;
import com.ruoyi.project.system.student.domain.Student;
import com.ruoyi.project.system.student.service.IStudentService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import com.testsys.common.UnionPayOfWXPay;
import com.testsys.utils.ApiResult;

@RestController
@RequestMapping("/api/bill")
public class BillApi {

	private static final Logger log = LoggerFactory.getLogger(BillApi.class);
    @Autowired
    private IBillService billService;
    @Autowired
    private ITExamService examService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private ITExamRegisterService examRegisterService;
    @Autowired
    private ClassInService classInService;
    @Autowired
    private IUserService userService;
    
    @Value("${wechat.notify_url}")
    private String notifyUrl;
    private String payIP = "47.93.241.224";

    @GetMapping("/wechat/pay")
    public ApiResult add(Long examId, HttpServletRequest request){
        StudentSession ss = StudentSession.getStudentSession(request);
        Long userId = null;
        if(null == ss){
            return new ApiResult(500,"未登陆");
        }else{
            userId = studentService.selectStudentById(ss.getStudentId()).getUserId();
        }
        TExam exam = examService.selectTExamById(examId);
        String rignEndTime = DateUtils.parseDateToStr("yyyyMMddHHmmss", exam.getRignEndTime());
        if(null != exam && !"1".equals(exam.getState())){
            return new ApiResult(500,"失败，报名时间已截止");
        }
        Bill b = new Bill();
        //b.setPayUserId(ss.getStudentId());
        b.setPayUserId(userId);
        b.setBillType("1");
        b.setPayState("1");
        b.setBillName("1");
        b.setRefId(exam.getExamId());
        List<Bill> billList = billService.selectBillList(b);
        //判断当前考试是否已经报名
        if(null != billList && billList.size() > 0){
            return new ApiResult(500,"无法重复报名");
        }
        if(null != exam){
            String billNo = UUID.randomUUID().toString().replaceAll("-","");
            Bill bill = new Bill();
            bill.setBillNo(billNo);//订单编号
            bill.setBillType("1");//账单类型  1收入  2支出
            bill.setBillName("1");//账单名称  1报名费 2补考费
            //bill.setPayUserId(ss.getStudentId());//支付人
            bill.setPayUserId(userId);//支付人
            bill.setAmount(exam.getPrice());//价格
            if(exam.getPrice() > 0.00){
            	bill.setPayState("0");//交易状态  0处理中 1成功 2异常
            }else {
            	bill.setPayState("1");//交易状态  0处理中 1成功 2异常
            }
            bill.setRefId(examId);//关联编号
            int count = billService.insertBill(bill);
            if(count > 0){
            	if(exam.getPrice() > 0.00) {
            		Map<String,String> wechatPay = UnionPayOfWXPay.unifiedOrder(
            				"1",
            				ss.getWechatId(),
            				billNo,
            				examId.toString(),
            				exam.getExamTitle(),
            				String.valueOf(Double.valueOf(exam.getPrice() * 100).intValue()),
            				payIP,
            				notifyUrl,
            				rignEndTime);
            		Map<String,Object> result = Maps.newHashMap();
            		result.put("timeStamp",System.currentTimeMillis() / 1000);
            		result.put("prepayId",wechatPay.get("prepay_id"));
            		return new ApiResult(result);
            	}else {
            		Student stu = studentService.selectUserById(ss.getStudentId());
            		TExamRegister er = new TExamRegister();
                    er.setStudentId(ss.getStudentId());
                    er.setExamId(exam.getExamId());
                    er.setState("1");
                    User u = userService.selectUserById(userId);
                    er.setExamCode(DateUtils.dateTimeNow("yyyyMMdd")+(u.getDeptId()==null?"000":u.getDeptId())+""+stu.getStudentId());
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
        					return new ApiResult(500, ClassInErrorMsg.getErrorMsgByCode(errno, "addCourseClass"));
        				}
        			} catch (Exception e) {
        				log.error("调用classIn添加课节失败",e);
        				return new ApiResult(500,"失败");
        			}
        			//添加报名信息
                    examRegisterService.insertTExamRegister(er);
            		Map<String,Object> result = Maps.newHashMap();
            		result.put("timeStamp",System.currentTimeMillis() / 1000);
            		result.put("prepayId","00000");
            		result.put("examCode", er.getExamCode());
            		return new ApiResult(result);
            	}
            }
        }
        return new ApiResult(500,"失败");
    }

    @PostMapping(value="/pay/callback",produces = MediaType.APPLICATION_XML_VALUE)
    public xml callBack(@RequestBody WeChatCallBack wccb){
        String state = "SUCCESS";
        //返回支付成功
        if(state.equals(wccb.getReturn_code())){
            Bill bill = new Bill();
            bill.setPayState("1");
            bill.setPayNo(wccb.getTransaction_id());
            bill.setBillNo(wccb.getOut_trade_no());
            //更新订单状态
            billService.updatePayStateByBillNo(bill);
            //查询订单信息
            Bill bl = billService.selectBillByBillNo(wccb.getOut_trade_no());
            User u = userService.selectUserById(bl.getPayUserId());
            Student stu = studentService.selectUserById(bl.getPayUserId());
            TExamRegister er = new TExamRegister();
            er.setStudentId(stu.getStudentId());
            er.setExamId(bl.getRefId());
            er.setState("1");
            er.setExamCode(DateUtils.dateTimeNow("yyyyMMdd")+(u.getDeptId()==null?"000":u.getDeptId())+""+stu.getStudentId());
            er.setCtime(new Date());
			try {
                TExam tExam = examService.selectTExamById(bl.getRefId());
				Map<String, String> params = Maps.newHashMap();
				params.put("courseId", tExam.getUid()+"");;
				params.put("className", stu.getStudentName());
				params.put("beginTime", (tExam.getBeginTime().getTime()/1000)+"");
				params.put("endTime", (tExam.getArgueEndTime().getTime()/1000)+"");
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
					return new xml("FAIL", ClassInErrorMsg.getErrorMsgByCode(errno, "addCourseClass"));
				}
			} catch (Exception e) {
				log.error("调用classIn添加课节失败",e);
				return new xml("FAIL","内部错误");
			}
			//添加报名信息
            examRegisterService.insertTExamRegister(er);
            return new xml("SUCCESS","OK");
        }else{
            return new xml("FAIL","内部错误");
        }
    }
}
