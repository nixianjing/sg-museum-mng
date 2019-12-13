package com.example.museum.dao;

import com.example.museum.dto.base.PageInfo;
import com.example.museum.po.RelicStockPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 出入库汇总信息
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Repository
public interface RelicStockMapper {

    /**
     * 新增
     *
     * @param record
     * @return
     */
    Long save(RelicStockPO record);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    RelicStockPO getById(Long id);

    /**
     * 设置待出库数量
     *
     * @param id
     * @param num
     * @return
     */
    int setStayStockNum(@Param("id") Long id, @Param("num") Integer num);

    /**
     * 设置已出库数量
     *
     * @param id
     * @param num
     * @return
     */
    int setOutStockNum(@Param("id") Long id, @Param("num") Integer num);

    /**
     * 设置已入库数量
     *
     * @param id
     * @param num
     * @return
     */
    int setEnterStockNum(@Param("id") Long id, @Param("num") Integer num);

    /**
     * 查询总记录
     *
     * @param startTime
     * @param endTime
     * @return
     */
    int listCount(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 分页查询
     *
     * @param startTime
     * @param endTime
     * @param pageInfo
     * @return
     */
    List<RelicStockPO> pageList(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("page") PageInfo pageInfo);

}