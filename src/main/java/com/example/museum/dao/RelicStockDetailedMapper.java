package com.example.museum.dao;

import com.example.museum.dto.base.PageInfo;
import com.example.museum.po.RelicStockDetailedPO;
import com.example.museum.po.RelicStockPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 出入库明细信息
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Repository
public interface RelicStockDetailedMapper {

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int save(RelicStockDetailedPO record);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    RelicStockDetailedPO getById(Long id);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 根据汇总设置出库
     *
     * @param relicStockId
     * @param outTime
     * @param userId
     * @param userName
     * @return
     */
    int setOutStockAll(@Param("relicStockId") Long relicStockId, @Param("outTime") String outTime, @Param("userId") Long userId, @Param("userName") String userName, @Param("status") Integer status);


    /**
     * 设置出库
     *
     * @param ids
     * @param outTime
     * @param userId
     * @param userName
     * @return
     */
    int setOutStockStatus(@Param("ids") List<Long> ids, @Param("outTime") String outTime, @Param("userId") Long userId, @Param("userName") String userName, @Param("status") Integer status);

    /**
     * 根据汇总设置入库
     *
     * @param relicStockId
     * @param enterTime
     * @param updateUserId
     * @param updateUserName
     * @return
     */
    int setEnterStockAll(@Param("relicStockId") Long relicStockId, @Param("enterTime") String enterTime, @Param("updateUserId") Long updateUserId, @Param("updateUserName") String updateUserName,
                         @Param("status") Integer status, @Param("reason") String reason, @Param("remarks") String remarks);


    /**
     * 设置入库
     *
     * @param ids
     * @param enterTime
     * @param updateUserId
     * @param updateUserName
     * @return
     */
    int setEnterStockStatus(@Param("ids") List<Long> ids, @Param("enterTime") String enterTime, @Param("updateUserId") Long updateUserId, @Param("updateUserName") String updateUserName,
                            @Param("status") Integer status, @Param("reason") String reason, @Param("remarks") String remarks);

    /**
     * 查询总记录
     *
     * @param paramPO
     * @return
     */
    int listCount(@Param("param") RelicStockDetailedPO paramPO);

    /**
     * 分页查询
     *
     * @param paramPO
     * @param pageInfo
     * @return
     */
    List<RelicStockPO> pageList(@Param("param") RelicStockDetailedPO paramPO, @Param("page") PageInfo pageInfo);

    /**
     * 查询集合
     * @param relicStockId
     * @return
     */
    List<RelicStockPO> relicStockList(@Param("relicStockId") Long relicStockId);
}