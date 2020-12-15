package com.ruoyi.project.system.orderschart.controller;

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
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.dept.domain.Dept;
import com.ruoyi.project.system.dept.service.IDeptService;
import com.ruoyi.project.system.orderschart.domain.ViewDeptOrders;
import com.ruoyi.project.system.orderschart.service.IViewDeptOrdersService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.ListDataUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 订单统计Controller
 * 
 * @author ruoyi
 * @date 2020-07-27
 */
@Controller
@RequestMapping("/system/orderschart")
public class ViewDeptOrdersController extends BaseController
{
    private String prefix = "system/orderschart";

    @Autowired
    private IViewDeptOrdersService viewDeptOrdersService;
    @Autowired
    private IDeptService deptService;

    @RequiresPermissions("system:orderschart:view")
    @GetMapping()
    public String orderschart(ModelMap mmap)
    {
    	mmap.put("depts", deptService.selectDeptList(new Dept()));
        return prefix + "/orderschart";
    }

    /**
     * 查询订单统计列表
     */
    @RequiresPermissions("system:orderschart:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ViewDeptOrders viewDeptOrders,ModelMap mmap)
    {
        List<ViewDeptOrders> list = viewDeptOrdersService.selectViewDeptOrdersList(viewDeptOrders);
        return getDataTable(list);
    }

    /**
     * 导出订单统计列表
     */
    @RequiresPermissions("system:orderschart:export")
    @Log(title = "订单统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ViewDeptOrders viewDeptOrders)
    {
        List<ViewDeptOrders> list = viewDeptOrdersService.selectViewDeptOrdersList(viewDeptOrders);
        List<Dept> dlist = deptService.selectDeptList(new Dept());
        for(ViewDeptOrders o : list) {
        	if(null!=o.getDeptId())
        		o.setDeptName(ListDataUtil.ListFindValue(dlist, "deptId", o.getDeptId()+"", "deptName").toString());
        	else 
        		o.setDeptName("无");
        }
        ExcelUtil<ViewDeptOrders> util = new ExcelUtil<ViewDeptOrders>(ViewDeptOrders.class);
        return util.exportExcel(list, "orderschart");
    }

    /**
     * 新增订单统计
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存订单统计
     */
    @RequiresPermissions("system:orderschart:add")
    @Log(title = "订单统计", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ViewDeptOrders viewDeptOrders)
    {
        return toAjax(viewDeptOrdersService.insertViewDeptOrders(viewDeptOrders));
    }

    /**
     * 修改订单统计
     */
    @GetMapping("/edit/{deptId}")
    public String edit(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        ViewDeptOrders viewDeptOrders = viewDeptOrdersService.selectViewDeptOrdersById(deptId);
        mmap.put("viewDeptOrders", viewDeptOrders);
        return prefix + "/edit";
    }

    /**
     * 修改保存订单统计
     */
    @RequiresPermissions("system:orderschart:edit")
    @Log(title = "订单统计", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ViewDeptOrders viewDeptOrders)
    {
        return toAjax(viewDeptOrdersService.updateViewDeptOrders(viewDeptOrders));
    }

    /**
     * 删除订单统计
     */
    @RequiresPermissions("system:orderschart:remove")
    @Log(title = "订单统计", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(viewDeptOrdersService.deleteViewDeptOrdersByIds(ids));
    }
}
