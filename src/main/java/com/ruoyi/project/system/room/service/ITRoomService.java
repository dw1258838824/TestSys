package com.ruoyi.project.system.room.service;

import java.util.List;
import com.ruoyi.project.system.room.domain.TRoom;

/**
 * 考场管理Service接口
 * 
 * @author ruoyi
 * @date 2020-07-17
 */
public interface ITRoomService 
{
    /**
     * 查询考场管理
     * 
     * @param roomId 考场管理ID
     * @return 考场管理
     */
    public TRoom selectTRoomById(Long roomId);

    /**
     * 查询考场管理列表
     * 
     * @param tRoom 考场管理
     * @return 考场管理集合
     */
    public List<TRoom> selectTRoomList(TRoom tRoom);

    /**
     * 新增考场管理
     * 
     * @param tRoom 考场管理
     * @return 结果
     */
    public int insertTRoom(TRoom tRoom);

    /**
     * 修改考场管理
     * 
     * @param tRoom 考场管理
     * @return 结果
     */
    public int updateTRoom(TRoom tRoom);

    /**
     * 批量删除考场管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTRoomByIds(String ids);

    /**
     * 删除考场管理信息
     * 
     * @param roomId 考场管理ID
     * @return 结果
     */
    public int deleteTRoomById(Long roomId);
}
