package com.ruoyi.project.system.reg.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.api.service.ClassInService;
import com.ruoyi.project.system.bill.domain.Bill;
import com.ruoyi.project.system.bill.service.IBillService;
import com.ruoyi.project.system.exam.domain.TExam;
import com.ruoyi.project.system.exam.service.ITExamService;
import com.ruoyi.project.system.reg.domain.TDeptReg;
import com.ruoyi.project.system.reg.mapper.TDeptRegMapper;
import com.ruoyi.project.system.reg.service.ITDeptRegService;
import com.ruoyi.project.system.register.domain.TExamRegister;
import com.ruoyi.project.system.register.service.ITExamRegisterService;

/**
 * 机构批量报名Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-31
 */
@Service
public class TDeptRegServiceImpl implements ITDeptRegService 
{
	private static final Logger log = LoggerFactory.getLogger(TDeptRegServiceImpl.class);
    @Autowired
    private TDeptRegMapper tDeptRegMapper;

    @Autowired
    private ITExamService examService;
    @Autowired
    private IBillService billService;
    
    @Autowired
    private ClassInService classInService;
    
    @Autowired
    private ITExamRegisterService examRegisterService;
    /**
     * 查询机构批量报名
     * 
     * @param deptRegId 机构批量报名ID
     * @return 机构批量报名
     */
    @Override
    public TDeptReg selectTDeptRegById(Long deptRegId)
    {
        return tDeptRegMapper.selectTDeptRegById(deptRegId);
    }

    /**
     * 查询机构批量报名列表
     * 
     * @param tDeptReg 机构批量报名
     * @return 机构批量报名
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<TDeptReg> selectTDeptRegList(TDeptReg tDeptReg)
    {
        return tDeptRegMapper.selectTDeptRegList(tDeptReg);
    }

    /**
     * 新增机构批量报名
     * 
     * @param tDeptReg 机构批量报名
     * @return 结果
     */
    @Override
    public int insertTDeptReg(TDeptReg tDeptReg)
    {
        return tDeptRegMapper.insertTDeptReg(tDeptReg);
    }

    /**
     * 修改机构批量报名
     * 
     * @param tDeptReg 机构批量报名
     * @return 结果
     */
    @Override
    @Transactional
    public int updateTDeptReg(TDeptReg tDeptReg)
    {
    	int result = 0;
    	try {
    		Long [] tDeptegIds = Convert.toLongArray(tDeptReg.getParams().get("ids")+"");
			tDeptReg.setIds(tDeptegIds);
			tDeptReg.setReplayUserId(ShiroUtils.getUserId());
			result = tDeptRegMapper.updateTDeptReg(tDeptReg);
			if(result>0 && tDeptReg.getOperateState().equals("1")) {
				TDeptReg reg = new TDeptReg();
				reg.setIds(tDeptegIds);
				List<TDeptReg> dlist = tDeptRegMapper.selectTDeptRegList(reg);
				if(null!= dlist && dlist.size()>0) {
					for(TDeptReg dr : dlist) {
						if(null != dr.getParams().get("userId")) {
							TExam t = examService.selectTExamById(dr.getExamId());
							Long userId = Long.parseLong(dr.getParams().get("userId")+"");
							Bill bill = new Bill();
							bill.setBillNo(UUID.randomUUID().toString().replaceAll("-",""));//订单编号
							bill.setBillType("1");//账单类型  1收入  2支出
							bill.setBillName("1");//账单名称  1报名费 2补考费
							//bill.setPayUserId(ss.getStudentId());//支付人
							bill.setPayUserId(userId);//支付人
							bill.setAmount(t.getPrice());//价格
							bill.setPayState("1");//交易状态  0处理中 1成功 2异常
							bill.setRefId(dr.getExamId());//关联编号
							billService.insertBill(bill); //新增账单
							TExamRegister er = new TExamRegister();
		                    er.setStudentId(dr.getStudentId());
		                    er.setExamId(dr.getExamId());
		                    er.setState("1");
		                    er.setExamCode(DateUtils.dateTimeNow("yyyyMMdd")+(dr.getDeptId()==null?"000":dr.getDeptId())+""+dr.getStudentId());
		                    er.setCtime(new Date());
		                    try {
		        				Map<String, String> params = Maps.newHashMap();
		        				params.put("courseId", t.getUid()+"");;
		        				params.put("className", dr.getParams().get("studentName")+"");
		        				params.put("beginTime", (t.getBeginTime().getTime()/1000)+"");
		        				params.put("endTime", (t.getArgueEndTime().getTime()/1000)+"");
		        				params.put("teacherUid", dr.getParams().get("uid")+"");
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
		        					//添加报名信息
		        					examRegisterService.insertTExamRegister(er);
		        				}else {
		        					log.error("调用classIn添加课节失败",json);
		        				}
		        			} catch (Exception e) {
		        				log.error("调用classIn添加课节失败",e);
		        			}
						}
					}
				}
			}
		} catch (Exception e) {
		}
    	return result;
    }

    /**
     * 删除机构批量报名对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTDeptRegByIds(String ids)
    {
        return tDeptRegMapper.deleteTDeptRegByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除机构批量报名信息
     * 
     * @param deptRegId 机构批量报名ID
     * @return 结果
     */
    @Override
    public int deleteTDeptRegById(Long deptRegId)
    {
        return tDeptRegMapper.deleteTDeptRegById(deptRegId);
    }
}
