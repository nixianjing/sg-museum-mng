package com.example.museum.service;

import com.example.museum.dto.RelicStockDTO;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;

/**
 * 汇总记录
 *
 * @author xianjing.n
 * @date 2019-12-10 15:38
 **/
public interface RelicStockService {

    /**
     * 新建出入库汇总信息
     *
     * @param stayStockNum
     * @param userId
     * @param userName
     * @return
     */
    Long createRelicStock(Integer stayStockNum, Long userId, String userName);

    /**
     * 设置出库数量
     *
     * @param id
     * @param num
     * @return
     */
    Boolean setOutStockNum(Long id, Integer num);

    /**
     * 设置入库数量
     *
     * @param id
     * @param num
     * @return
     */
    Boolean setEnterStockNum(Long id, Integer num);

    /**
     * 分页查询
     *
     * @param pageInfo
     * @return
     */
    PageResult<RelicStockDTO> pageList(PageInfo<RelicStockDTO> pageInfo);
}
