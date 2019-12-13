package com.example.museum.dao;

import com.example.museum.po.RelicScanningWordRecordPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文档修复文档变更记录Mapper
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Repository
public interface RelicScanningWordRecordMapper {

    /**
     * 新增
     *
     * @param record
     * @return
     */
    Long insertSelective(RelicScanningWordRecordPO record);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    RelicScanningWordRecordPO selectByPrimaryKey(@Param("id") Long id);

    /**
     * 查询变更记录
     *
     * @param relicScanningId
     * @return
     */
    List<RelicScanningWordRecordPO> getRecordByScanningId(@Param("relicScanningId") Long relicScanningId);
}