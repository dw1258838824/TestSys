package com.ruoyi.project.system.countchart.controller;

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
import com.ruoyi.project.system.countchart.domain.ViewRoleCount;
import com.ruoyi.project.system.countchart.service.IViewRoleCountService;
import com.ruoyi.project.system.role.domain.Role;
import com.ruoyi.project.system.role.service.IRoleService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.ListDataUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 角色人员统计Controller
 * 
 * @author ruoyi
 * @date 2020-07-27
 */
@Controller
@RequestMapping("/system/countchart")
public class ViewRoleCountController extends BaseController
{
    private String prefix = "system/countchart";

    @Autowired
    private IViewRoleCountService viewRoleCountService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserService userService;

    @RequiresPermissions("system:countchart:view")
    @GetMapping()
    public String countchart(ModelMap mmap)
    {
    	mmap.put("roles", roleService.selectRoleAll());
    	User u = new User();
    	u.setRoleId(100L);
    	mmap.put("studentCount", userService.selectUserList(u).size());
    	u.setRoleId(null);
    	u.getParams().put("noroleId", "100");
    	mmap.put("rootCount", userService.selectUserList(u).size());
        return prefix + "/countchart";
    }

    /**
     * 查询角色人员统计列表
     */
    @RequiresPermissions("system:countchart:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ViewRoleCount viewRoleCount)
    {
        List<ViewRoleCount> list = viewRoleCountService.selectViewRoleCountList(viewRoleCount);
        return getDataTable(list);
    }

    /**
     * 导出角色人员统计列表
     */
    @RequiresPermissions("system:countchart:export")
    @Log(title = "角色人员统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ViewRoleCount viewRoleCount)
    {
        List<ViewRoleCount> list = viewRoleCountService.selectViewRoleCountList(viewRoleCount);
        List<Role> rlist = roleService.selectRoleAll();
        for(ViewRoleCount r : list) {
        	r.setRoleName(ListDataUtil.ListFindValue(rlist, "roleId", r.getRoleId().toString(), "roleName").toString());
        }
        ExcelUtil<ViewRoleCount> util = new ExcelUtil<ViewRoleCount>(ViewRoleCount.class);
        return util.exportExcel(list, "countchart");
    }

    /**
     * 新增角色人员统计
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存角色人员统计
     */
    @RequiresPermissions("system:countchart:add")
    @Log(title = "角色人员统计", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ViewRoleCount viewRoleCount)
    {
        return toAjax(viewRoleCountService.insertViewRoleCount(viewRoleCount));
    }

    /**
     * 修改角色人员统计
     */
    @GetMapping("/edit/{roleId}")
    public String edit(@PathVariable("roleId") Long roleId, ModelMap mmap)
    {
        ViewRoleCount viewRoleCount = viewRoleCountService.selectViewRoleCountById(roleId);
        mmap.put("viewRoleCount", viewRoleCount);
        return prefix + "/edit";
    }

    /**
     * 修改保存角色人员统计
     */
    @RequiresPermissions("system:countchart:edit")
    @Log(title = "角色人员统计", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ViewRoleCount viewRoleCount)
    {
        return toAjax(viewRoleCountService.updateViewRoleCount(viewRoleCount));
    }

    /**
     * 删除角色人员统计
     */
    @RequiresPermissions("system:countchart:remove")
    @Log(title = "角色人员统计", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(viewRoleCountService.deleteViewRoleCountByIds(ids));
    }
}
