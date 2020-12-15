package com.ruoyi.project.system.room.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.room.mapper.TRoomMapper;
import com.ruoyi.project.system.room.domain.TRoom;
import com.ruoyi.project.system.room.service.ITRoomService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 考场管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-17
 */
@Service
public class TRoomServiceImpl implements ITRoomService 
{
    @Autowired
    private TRoomMapper tRoomMapper;

    /**
     * 查询考场管理
     * 
     * @param roomId 考场管理ID
     * @return 考场管理
     */
    @Override
    public TRoom selectTRoomById(Long roomId)
    {
        return tRoomMapper.selectTRoomById(roomId);
    }

    /**
     * 查询考场管理列表
     * 
     * @param tRoom 考场管理
     * @return 考场管理
     */
    @Override
    public List<TRoom> selectTRoomList(TRoom tRoom)
    {
        return tRoomMapper.selectTRoomList(tRoom);
    }

    /**
     * 新增考场管理
     * 
     * @param tRoom 考场管理
     * @return 结果
     */
    @Override
    public int insertTRoom(TRoom tRoom)
    {
        return tRoomMapper.insertTRoom(tRoom);
    }

    /**
     * 修改考场管理
     * 
     * @param tRoom 考场管理
     * @return 结果
     */
    @Override
    public int updateTRoom(TRoom tRoom)
    {
        return tRoomMapper.updateTRoom(tRoom);
    }

    /**
     * 删除考场管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTRoomByIds(String ids)
    {
        return tRoomMapper.deleteTRoomByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除考场管理信息
     * 
     * @param roomId 考场管理ID
     * @return 结果
     */
    @Override
    public int deleteTRoomById(Long roomId)
    {
        return tRoomMapper.deleteTRoomById(roomId);
    }
}
