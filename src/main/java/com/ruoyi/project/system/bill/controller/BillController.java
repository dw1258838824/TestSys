package com.ruoyi.project.system.bill.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.utils.ListDataUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.bill.domain.Bill;
import com.ruoyi.project.system.bill.service.IBillService;
import com.ruoyi.project.system.dict.utils.DictUtils;
import com.ruoyi.project.system.exam.domain.TExam;
import com.ruoyi.project.system.exam.service.ITExamService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;

/**
 * 账单Controller
 * 
 * @author ruoyi
 * @date 2020-06-22
 */
@Controller
@RequestMapping("/system/bill")
public class BillController extends BaseController
{
    private String prefix = "system/bill";

    @Autowired
    private IBillService billService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ITExamService examService;

    @RequiresPermissions("system:bill:view")
    @GetMapping()
    public String bill(ModelMap mmap)
    {
        return prefix + "/bill";
    }

    /**
     * 查询账单列表
     */
    @RequiresPermissions("system:bill:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Bill bill)
    {
    	bill.setPayState("1");
        startPage();
        List<Bill> list = billService.selectBillList(bill);
        return getDataTable(list);
    }

    /**
     * 导出账单列表
     */
    @RequiresPermissions("system:bill:export")
    @Log(title = "账单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Bill bill)
    {
    	bill.setPayState("1");
        List<Bill> list = billService.selectBillList(bill);
        if(list.size()>0) {
	        List<User> ulist = userService.selectUserList(new User());
	        List<TExam> tlist = examService.selectTExamList(new TExam());
			for (Bill b : list) {
				b.setBillType(DictUtils.getDictCache("sys_bill_type",b.getBillType()));
				b.setBillName(DictUtils.getDictCache("sys_bill_name",b.getBillName()));
				b.setPayState(DictUtils.getDictCache("sys_pay_state",b.getPayState()));
				if(null!=b.getPayUserId())
					b.setPayUserName(ListDataUtil.ListFindValue(ulist, "userId", b.getPayUserId().toString(), "userName").toString());
				if(null!=b.getTakeUserId())
					b.setTakeUserName(ListDataUtil.ListFindValue(ulist, "userId", b.getTakeUserId().toString(), "userName").toString());
				if(null!=b.getRefId()) {
					b.setRefName(ListDataUtil.ListFindValue(tlist, "examId", b.getRefId().toString(), "examTitle").toString());
				}
				
			}
        }
        ExcelUtil<Bill> util = new ExcelUtil<Bill>(Bill.class);
        return util.exportExcel(list, "bill");
    }

    /**
     * 新增账单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存账单
     */
    @RequiresPermissions("system:bill:add")
    @Log(title = "账单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Bill bill)
    {
        return toAjax(billService.insertBill(bill));
    }

    /**
     * 修改账单
     */
    @GetMapping("/edit/{billId}")
    public String edit(@PathVariable("billId") Long billId, ModelMap mmap)
    {
        Bill bill = billService.selectBillById(billId);
        mmap.put("bill", bill);
        return prefix + "/edit";
    }

    /**
     * 修改保存账单
     */
    @RequiresPermissions("system:bill:edit")
    @Log(title = "账单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Bill bill)
    {
        return toAjax(billService.updateBill(bill));
    }

    /**
     * 删除账单
     */
    @RequiresPermissions("system:bill:remove")
    @Log(title = "账单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(billService.deleteBillByIds(ids));
    }
}
