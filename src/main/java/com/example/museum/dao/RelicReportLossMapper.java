package com.example.museum.dao;

import com.example.museum.dto.base.PageInfo;
import com.example.museum.po.RelicReportLossPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 报损记录信息
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Repository
public interface RelicReportLossMapper {

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int save(RelicReportLossPO record);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    RelicReportLossPO getById(Long id);

    /**
     * 设置修复状态
     *
     * @param id
     * @param status
     * @return
     */
    int setReimburseStatus(@Param("id") Long id, @Param("status") Integer status);

    /**
     * 设置修复完成
     *
     * @param id
     * @param status
     * @param repairUserId
     * @param repairUserName
     * @param repairTime
     * @param repairReason
     * @return
     */
    int setRepairStatus(@Param("id") Long id, @Param("status") Integer status, @Param("repairUserId") Long repairUserId,
                        @Param("repairUserName") String repairUserName, @Param("repairTime") String repairTime, @Param("repairReason") String repairReason);

    /**
     * 查询总记录
     *
     * @param paramPO
     * @return
     */
    int listCount(@Param("param") RelicReportLossPO paramPO);

    /**
     * 分页查询
     *
     * @param paramPO
     * @param pageInfo
     * @return
     */
    List<RelicReportLossPO> pageList(@Param("param") RelicReportLossPO paramPO, @Param("page") PageInfo pageInfo);

    /**
     * 批量查询集合
     *
     * @param ids
     * @return
     */
    List<RelicReportLossPO> lossByIdsList(@Param("ids") List<Long> ids);

    /**
     * 文物报损记录集合
     *
     * @param relicId
     * @return
     */
    List<RelicReportLossPO> lossByRelicIdList(@Param("relicId") Long relicId);
}