package com.ruoyi.project.system.computer.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ruoyi.common.utils.HttpUtils;
import com.ruoyi.common.utils.ListDataUtil;
import com.ruoyi.common.utils.RequestUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.bill.domain.Bill;
import com.ruoyi.project.system.bill.service.IBillService;
import com.ruoyi.project.system.exam.domain.TExam;
import com.ruoyi.project.system.exam.service.ITExamService;
import com.ruoyi.project.system.examstudent.domain.TQuestionRecord;
import com.ruoyi.project.system.examstudent.domain.TRoomExamStudent;
import com.ruoyi.project.system.examstudent.service.ITRoomExamStudentService;
import com.ruoyi.project.system.paper.domain.TPaper;
import com.ruoyi.project.system.paper.service.ITPaperService;
import com.ruoyi.project.system.student.domain.StudentLevel;
import com.ruoyi.project.system.student.service.IStudentService;

@Controller
@RequestMapping("/student/exam")
public class ExamController  extends BaseController{
	
	private String prefix = "system/computer";

	private static final Logger log = LoggerFactory.getLogger(ExamController.class);
	@Autowired
    private ITExamService tExamService;
	
	@Autowired
	private ITRoomExamStudentService tRoomExamStudentService;
	
	@Autowired
	private ITPaperService tPaperService;

	@Autowired
	private IStudentService studentService;

	@Autowired
	private IBillService billService;
	
	@Value("${ruoyi.scratchProfile}")
	private String scratchFilePath;
	
	@GetMapping("/start/{examId}/{flag}")
    public String startExam(@PathVariable("examId") Long examId,@PathVariable("flag") String flag, ModelMap mmap) {
    	mmap.put("examId", examId);
    	mmap.put("studentId", ShiroUtils.getSysUser().getStudent().getStudentId());
    	TRoomExamStudent tes = new TRoomExamStudent();
    	tes.setExamId(examId);
    	tes.setStudentId(ShiroUtils.getSysUser().getStudent().getStudentId());
    	List<TRoomExamStudent> list = tRoomExamStudentService.selectStudentExamList(tes);
    	if(list.isEmpty()) {
    		mmap.put("judgeState", "");
    	}else {
    		mmap.put("judgeState", list.get(0).getJudgeState());
    		mmap.put("id", list.get(0).getId());
    	}
    	return prefix + "/" + flag;
    }
	
	@GetMapping("/get/{examId}")
	@ResponseBody
	public TExam get(@PathVariable("examId") Long examId) {
		TExam tExam = tExamService.selectTExamById(examId);
		List<TPaper> pplist = tPaperService.selectTPaperList(new TPaper());
		tExam.getParams().put("paperDoc", (ListDataUtil.ListFindValue(pplist,"paperId",tExam.getPaperId()+"","paperDoc").toString()));
		return tExam;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/submitexam")
	@Transactional
	@ResponseBody
	public String submitexam(HttpServletRequest request,TRoomExamStudent res) {
		String msg = "success";
		int result;
		try {
			List<TQuestionRecord> qrlist = Lists.newArrayList();
			Map<String, String> parm = RequestUtils.getRequestMap(request);
			Long paperId = Long.valueOf(parm.get("paperId"));
			Set<Entry<String, String>> set = parm.entrySet();
			int size = set.size();
			StringBuffer sb = new StringBuffer();
			sb.append("{\"answerList\":[ ");
			for(int i=1; i<=size ; i++) {
				if(parm.containsKey("questionVal"+i)) {
					String answer = parm.get("questionVal"+i);
					String questionId = parm.get("questionId"+i);
					String fg = "";
					if(i>1) {
						fg = ",";
					}
					sb.append(fg).append("{\"questionId\":\""+questionId+"\",\"answer\":\""+answer+"\"}");
					TQuestionRecord qs = new TQuestionRecord();
					qs.setStudentAnswer(answer);
					qs.setQuestionId(Long.valueOf(questionId));
					qs.setExamId(res.getExamId());
					qs.setStudentId(res.getStudentId());
					qrlist.add(qs);
				}
			}
			sb.append(" ]}");
			res.setStudentAnswerJson(sb.toString());
			tRoomExamStudentService.deleteTRoomExamByStudentIdAndExamId(res);//先删除
			result = tRoomExamStudentService.insertTRoomExamStudent(res);
			if(result==0) {
				msg = "error";
			}else {
				//处理判卷线程
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				Map<String,MultipartFile> files = multipartRequest.getFileMap();
				TPaper p = tPaperService.selectTPaperById(paperId);
				if(p.getJudgeMode().equals("1")) {//如果为系统判卷
					JSONObject json = JSONObject.parseObject(p.getExamAnswerJson());
					JSONArray array = json.getJSONArray("answerList");
					Double stuAllScore = 0.00;
					int ss = 1;
					for(Object o : array) {
						Long questionId = Long.valueOf(JSONObject.parseObject(o.toString()).getString("questionId"));
						Double score = Double.valueOf(JSONObject.parseObject(o.toString()).getString("score"));
						Long questionTypeId = Long.valueOf(JSONObject.parseObject(o.toString()).getString("questionType"));
						String answer = JSONObject.parseObject(o.toString()).getString("answer");
						for(TQuestionRecord s : qrlist) {
							if(questionId.equals(s.getQuestionId())) {
								s.setAnswer(answer);
								s.setScore(score);
								s.setQuestionTypeId(questionTypeId);
								s.setSb(null);
//								if(questionTypeId == 7) {//python编程题
//									String realAnswer = pythonRun3(s.getStudentAnswer());
//									if(realAnswer.equals("")) {
//										realAnswer = pythonRun(s.getStudentAnswer());
//									}else{
//										s.setStudentAnswer(realAnswer);
//									}
//								}else 
								if(questionTypeId == 6) {//scratch
									MultipartFile f = files.get("scratchfile"+ss);
									if(null!=f) {
										try {
											String name = f.getOriginalFilename();//直接返回文件的名字;
											File file=new File(scratchFilePath);
											if(!file.exists()){//目录不存在就创建
												file.mkdirs();
											}
											String subffix = name.substring(name.lastIndexOf(".") + 1, name.length());//我这里取得文件后缀
											if(StringUtils.isNotEmpty(subffix)) {
												String fileName = (new StringBuffer(res.getExamId().toString()).append("-").append(res.getStudentId()).append("scratchfile").append(ss)).toString();//文件保存进来，我给他重新命名，数据库保存有原本的名字，所以输出的时候再把他附上原本的名字就行了。
												File newFile = new File(file+"/"+fileName+"."+subffix);
	//													f.transferTo(newFile);
												FileUtils.copyInputStreamToFile(f.getInputStream(), newFile);
												s.setStudentAnswer(fileName+"."+subffix);
											}
										} catch (Exception e) {
											e.printStackTrace();
										}
									
									}
								}
								//如果答案一致
								if(StringUtils.trim(answer).equals(StringUtils.trim(s.getStudentAnswer()))) {
									stuAllScore += score;
									s.setStudentScore(score);
									s.setQuestionTypeId(questionTypeId);
								}else {
									s.setStudentScore(0.00);
								}
							}
						}
						ss ++;
					}
					res.setScore(stuAllScore);
					res.setJudgeState("1");
					tRoomExamStudentService.updateTRoomExamStudent(res);
					TQuestionRecord qq = new TQuestionRecord();
					qq.setExamId(res.getExamId());
					qq.setStudentId(res.getStudentId());
					tRoomExamStudentService.deleteQuestionRecord(qq);//先清除
					tRoomExamStudentService.insertQuestionRecords(qrlist);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}
	
	/**
	 * 提交实操/答辩题目
	 * @param request
	 * @param res
	 * @return
	 */
	@PostMapping("/submitflag/{flag}")
	@Transactional
	@ResponseBody
	@SuppressWarnings("unchecked")
	public String submitoperate(HttpServletRequest request,TRoomExamStudent res,@PathVariable("flag") String flag) {
		String msg = "success";
		try {
			if(flag.equals("operate")) {//已实操
				res.setJudgeState("2");
			}else {
				res.setJudgeState("3");//已答辩
			}
			tRoomExamStudentService.updateTRoomExamStudent(res);
			Map<String, String> parm = RequestUtils.getRequestMap(request);
			List<TQuestionRecord> qrlist = Lists.newArrayList();
			TQuestionRecord qr = new TQuestionRecord();
			qr.setQuestionId(Long.parseLong(parm.get("questionId").toString()));
			qr.setExamId(res.getExamId());
			qr.setStudentId(res.getStudentId());
			qr.setQuestionTypeId(Long.parseLong(parm.get("typeId").toString()));
			qr.setStudentAnswer(request.getParameter("minute"));
			qrlist.add(qr);
			tRoomExamStudentService.insertQuestionRecords(qrlist);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@GetMapping("/signup")
	public String exam(ModelMap mmap){
		try {
			mmap.put("student", getSysUser().getStudent());
		} catch (Exception e) {
			throw new RuntimeException("获取失败，请重新登录");
		}
		return prefix + "/examList";
	}

	/**
	 * 查询报名列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TExam tExam){
		//只允许查询报名中的
		tExam.setState("1");
		//查询当前学生所有科目等级
		StudentLevel slp = new StudentLevel();
		slp.setStudentId(ShiroUtils.getSysUser().getStudent().getStudentId());
		List<StudentLevel> level = studentService.selectStudentLevel(slp);
		startPage();
		List<TExam> list = tExamService.apiList(tExam);
		if(null != list && list.size() > 0){
			for (int i = 0; i < list.size(); i++){
				int examLevel = (Integer) list.get(i).getParams().get("levelValue");
				//循环科目判断用户当前考试科目等级是否达到
				for (StudentLevel sl:level){
					//等级不够不返回当前考试信息
					if(examLevel > (sl.getLevelValue() + 1) && sl.getSubjectId().equals(list.get(i).getSubjectId())){
						list.remove(i);
						i--;
						continue;
					}
				}
				Bill bill = new Bill();
				bill.setPayUserId(ShiroUtils.getSysUser().getUserId());
				bill.setBillType("1");
				bill.setPayState("1");
				bill.setBillName("1");
				bill.setRefId(list.get(i).getExamId());
				List<Bill> billList = billService.selectBillList(bill);
				if(null != billList && billList.size() > 0){
					list.get(i).setPayState("1");
				}
			}
		}
		return getDataTable(list);
	}
	

	@GetMapping("/score/{resId}")
    public String score(@PathVariable("resId") Long resId, ModelMap mmap) {
    	TRoomExamStudent res = tRoomExamStudentService.selectTRoomExamStudentById(resId);
    	mmap.put("res", res);
    	TQuestionRecord record = new TQuestionRecord();
    	record.setExamId(res.getExamId());
    	record.setStudentId(res.getStudentId());
    	mmap.put("questionList", tRoomExamStudentService.selectQuestionRecords(record));

		TExam tExam = tExamService.selectTExamById(res.getExamId());
		List<TPaper> pplist = tPaperService.selectTPaperList(new TPaper());
		tExam.getParams().put("paperDoc", (ListDataUtil.ListFindValue(pplist,"paperId",tExam.getPaperId()+"","paperDoc").toString()));
    	mmap.put("exam",tExam);
    	mmap.put("roles",getSysUser().getRoles());
    	return prefix + "/score";
    }
	
	@PostMapping("/updateScore")
	@ResponseBody
    public String updateScore(TQuestionRecord qr) {
		int r;
		try {
			r = tRoomExamStudentService.updateQuestionRecords(qr);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		if(r > 0 ) {
			return "success";
		}
    	return "error";
    }

	@Value("${python_api.method}") 
	private  String method;
	@Value("${python_api.url}") 
	private  String url;
	@Value("${python_api.parms}") 
	private  String parms;
	@Value("${python_api.values}") 
	private  String values;
	@Value("${python_api.result_key}") 
	private  String result_key;

	@Value("${python3_api.url}") 
	private  String url3;
	
	/**
	 * 编译python接口
	 * @param code
	 * @return
	 */
	public String pythonRun3(String code) {
		Map<String, String> parm = Maps.newHashMap();
		parm.put("code",code);
		String result = "";
		try {
			result = HttpUtils.URLPost(url3, parm, "UTF-8"); //"https://tool.runoob.com/compile2.php"
			System.out.println("python3编译： "+result);
			if(result!="") {
				JSONObject obj = JSONObject.parseObject(result);
				if(obj.getString("code").equals("Success")) {
					result = obj.getString("output");
					result = result.replaceAll("\n","\r\n");
					result = result.substring(0,result.length()-2);
				}else {
					result = "";
				}
			}
		} catch (Exception e) {
            log.error("python3编译接口错误",e);
		}
		return result;
	}
	
	/**
	 * 编译python接口
	 * @param code
	 * @return
	 */
	public String pythonRun(String code) {
		String result = "";
		String [] parmsArray = parms.split("##");
		String [] valuesArray = values.split("##");
		try {
			Map<String, String> parm = Maps.newHashMap();
			for(int i=0;i<parmsArray.length;i++) {
				if(valuesArray[i].equals("$code")) {
					parm.put(parmsArray[i],code);
				}else {
					parm.put(parmsArray[i],valuesArray[i].equals("empty")?"":valuesArray[i]);
				}
			}
			if(method.equals("POST")) {
				result = HttpUtils.URLPost(url, parm, "UTF-8");
			}else {
				result = HttpUtils.URLGet(url, parm, "UTF-8");
			}
			if(result!="") {
				JSONObject obj = JSONObject.parseObject(result);
				result = obj.getString(result_key);
				result = result.replaceAll("\n","\r\n");
				result = result.substring(0,result.length()-2);
			}
		}catch (Exception e) {
			log.error("python编译接口错误",e);
		}
		return result;
	}
}
