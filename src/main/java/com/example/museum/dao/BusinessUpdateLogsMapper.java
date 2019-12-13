package com.example.museum.dao;

import com.example.museum.dto.base.PageInfo;
import com.example.museum.po.BusinessUpdateLogsPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 日志记录Mapper
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Repository
public interface BusinessUpdateLogsMapper {

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insert(BusinessUpdateLogsPO record);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    BusinessUpdateLogsPO getById(Long id);

    /**
     * 查询总记录
     *
     * @param businessUpdateLogsPO
     * @return
     */
    int listCount(@Param("param") BusinessUpdateLogsPO businessUpdateLogsPO);

    /**
     * 分页查询
     *
     * @param businessUpdateLogsPO
     * @param pageInfo
     * @return
     */
    List<BusinessUpdateLogsPO> pageList(@Param("param") BusinessUpdateLogsPO businessUpdateLogsPO, @Param("page") PageInfo pageInfo);
}