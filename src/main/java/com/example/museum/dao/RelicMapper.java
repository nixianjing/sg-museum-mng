package com.example.museum.dao;

import com.example.museum.dto.base.PageInfo;
import com.example.museum.po.RelicPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文物信息Mapper
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Repository
public interface RelicMapper {

    /**
     * 新增
     *
     * @param record
     * @return
     */
    Long insertSelective(RelicPO record);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    RelicPO selectByPrimaryKey(@Param("id")Long id);

    /**
     * 根据名称查询
     *
     * @param relicName
     * @return
     */
    RelicPO selectByRelicName(@Param("relicName") String relicName);

    /**
     * 编辑信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(RelicPO record);

    /**
     * 设置报损状态
     * @param id
     * @param repairStatus
     * @return
     */
    int setRepairStatus(@Param("id") Long id,@Param("repairStatus")Integer repairStatus);

    /**
     * 设置出入库状态
     * @param id
     * @param stockStatus
     * @return
     */
    int setStockStatus(@Param("id")Long id,@Param("stockStatus")Integer stockStatus);


    /**
     * 查询总记录
     *
     * @param relicPO
     * @return
     */
    int listCount(@Param("param") RelicPO relicPO);

    /**
     * 分页查询
     *
     * @param relicPO
     * @param pageInfo
     * @return
     */
    List<RelicPO> pageList(@Param("param") RelicPO relicPO, @Param("page") PageInfo pageInfo);
}