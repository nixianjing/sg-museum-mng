package com.example.museum.service;

import com.example.museum.dto.RelicReportLossDTO;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;

import java.util.List;

/**
 * @author xianjing.n
 * @date 2019-12-10 20:40
 **/
public interface RelicReportLossService {

    /**
     * 创建报损记录
     *
     * @param reportLossDTO
     * @return
     */
    Boolean createRelicReportLoss(RelicReportLossDTO reportLossDTO);

    /**
     * 设置为修复中
     *
     * @param id
     * @return
     */
    Boolean setReimburseStatus(Long id);

    /**
     * 设置修复完成
     *
     * @param id             主键
     * @param repairUserId   操作人ID
     * @param repairUserName 操作人姓名
     * @param repairTime     修复完成时间
     * @param repairReason   修复完成备注说明
     * @return
     */
    Boolean setRepairStatus(Long id, Long repairUserId, String repairUserName, String repairTime, String repairReason);

    /**
     * 查询详情
     * @param id
     * @return
     */
    RelicReportLossDTO getById(Long id);

    /**
     * 分页查询
     *
     * @param pageInfo
     * @return
     */
    PageResult<RelicReportLossDTO> pageList(PageInfo<RelicReportLossDTO> pageInfo);


    /**
     * 批量查询集合
     *
     * @param ids
     * @return
     */
    List<RelicReportLossDTO> lossByIdsList(List<Long> ids);

    /**
     * 文物报损记录集合
     *
     * @param relicId
     * @return
     */
    List<RelicReportLossDTO> lossByRelicIdList(Long relicId);
}
