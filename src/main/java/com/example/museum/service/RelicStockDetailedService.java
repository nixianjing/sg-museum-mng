package com.example.museum.service;

import com.example.museum.dto.RelicStockDetailedDTO;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;

import java.util.List;

/**
 * 汇总明细
 *
 * @author xianjing.n
 * @date 2019-12-10 15:39
 **/
public interface RelicStockDetailedService {

    /**
     * 批量插入
     *
     * @param dtoList
     * @return
     */
    Boolean batchRelicStockDetailed(List<RelicStockDetailedDTO> dtoList);


    /**
     * 根据汇总设置出库
     *
     * @param relicStockId
     * @param outTime
     * @param userId
     * @param userName
     * @return
     */
    int setOutStockAll(Long relicStockId, String outTime, Long userId, String userName);


    /**
     * 设置出库
     *
     * @param ids
     * @param outTime
     * @param userId
     * @param userName
     * @return
     */
    int setOutStockStatus(List<Long> ids, String outTime, Long userId, String userName);

    /**
     * 根据汇总设置入库
     *
     * @param relicStockId
     * @param enterTime
     * @param updateUserId
     * @param updateUserName
     * @return
     */
    int setEnterStockAll(Long relicStockId, String enterTime, Long updateUserId,
                         String updateUserName, String reason, String remarks);


    /**
     * 设置入库
     *
     * @param ids
     * @param enterTime
     * @param updateUserId
     * @param updateUserName
     * @return
     */
    int setEnterStockStatus(List<Long> ids, String enterTime, Long updateUserId,
                            String updateUserName, String reason, String remarks);


    /**
     * 分页查询
     *
     * @param pageInfo
     * @return
     */
    PageResult<RelicStockDetailedDTO> pageList(PageInfo<RelicStockDetailedDTO> pageInfo);


    /**
     * 查询集合
     *
     * @param relicStockId
     * @return
     */
    List<RelicStockDetailedDTO> relicStockList(Long relicStockId);
}
