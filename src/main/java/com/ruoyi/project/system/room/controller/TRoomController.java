package com.ruoyi.project.system.room.controller;

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
import com.ruoyi.project.system.role.domain.Role;
import com.ruoyi.project.system.room.domain.TRoom;
import com.ruoyi.project.system.room.service.ITRoomService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 考场管理Controller
 * 
 * @author ruoyi
 * @date 2020-07-17
 */
@Controller
@RequestMapping("/system/room")
public class TRoomController extends BaseController
{
    private String prefix = "system/room";

    @Autowired
    private ITRoomService tRoomService;

    @Autowired
    private IUserService userService;
    
    @RequiresPermissions("system:room:view")
    @GetMapping()
    public String room()
    {
        return prefix + "/room";
    }

    /**
     * 查询考场管理列表
     */
    @RequiresPermissions("system:room:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TRoom tRoom)
    {
    	List<Role> roles = getSysUser().getRoles();
    	for(Role r : roles) {
    		if(r.getRoleId() == 1 || r.getRoleId() == 101) {
    			break;
    		}else if(r.getRoleId() == 102) {//如果不是管理员 且为老师
    			tRoom.setTeacherUserId(getSysUser().getUserId());
    		}
    	}
        startPage();
        List<TRoom> list = tRoomService.selectTRoomList(tRoom);
        return getDataTable(list);
    }

    /**
     * 导出考场管理列表
     */
    @RequiresPermissions("system:room:export")
    @Log(title = "考场管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TRoom tRoom)
    {
        List<TRoom> list = tRoomService.selectTRoomList(tRoom);
        ExcelUtil<TRoom> util = new ExcelUtil<TRoom>(TRoom.class);
        return util.exportExcel(list, "room");
    }

    /**
     * 新增考场管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存考场管理
     */
    @RequiresPermissions("system:room:add")
    @Log(title = "考场管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TRoom tRoom)
    {
        return toAjax(tRoomService.insertTRoom(tRoom));
    }

    /**
     * 修改考场管理
     */
    @GetMapping("/edit/{roomId}")
    public String edit(@PathVariable("roomId") Long roomId, ModelMap mmap)
    {
        TRoom tRoom = tRoomService.selectTRoomById(roomId);
        mmap.put("tRoom", tRoom);
        User user = new User();
        user.setRoleId(102L);
        mmap.put("teachers", userService.selectUserList(user));
        return prefix + "/edit";
    }

    /**
     * 修改保存考场管理
     */
    @RequiresPermissions("system:room:edit")
    @Log(title = "考场管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TRoom tRoom)
    {
        return toAjax(tRoomService.updateTRoom(tRoom));
    }

    /**
     * 删除考场管理
     */
    @RequiresPermissions("system:room:remove")
    @Log(title = "考场管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tRoomService.deleteTRoomByIds(ids));
    }
}
