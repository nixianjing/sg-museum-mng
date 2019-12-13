package com.example.museum.dao;

import com.example.museum.dto.base.PageInfo;
import com.example.museum.po.RelicScanningPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RelicScanningMapper {

    /**
     * 新增
     *
     * @param record
     * @return
     */
    Long insertSelective(RelicScanningPO record);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    RelicScanningPO selectByPrimaryKey(Long id);

    /**
     * 根据项目名称和项目时间查询
     *
     * @param projectName
     * @param projectTime
     * @return
     */
    RelicScanningPO getRelicScanningByProjectNameAndTime(@Param("relicId") Long relicId, @Param("projectName") String projectName, @Param("projectTime") String projectTime);

    /**
     * 编辑信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(RelicScanningPO record);


    /**
     * 查询总记录
     *
     * @param relicScanningPO
     * @return
     */
    int listCount(@Param("param") RelicScanningPO relicScanningPO);

    /**
     * 分页查询
     *
     * @param relicScanningPO
     * @param pageInfo
     * @return
     */
    List<RelicScanningPO> pageList(@Param("param") RelicScanningPO relicScanningPO, @Param("page") PageInfo pageInfo);
}