package com.example.museum.service;

import com.example.museum.common.enums.RepairStatusEnum;
import com.example.museum.dto.*;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;
import com.example.museum.po.RelicScanningWordRecordPO;

/**
 * 文物
 *
 * @author xianjing.n
 * @date 2019-11-23 10:57
 **/
public interface RelicService {

    /**
     * 根据ID查询
     *
     * @param relicId
     * @return
     */
    RelicDTO getRelicById(Long relicId);

    /**
     * 根据文物名称查询
     *
     * @param relicName
     * @return
     */
    RelicDTO getRelicByName(String relicName);

    /**
     * 编辑信息
     *
     * @param relicScanningId
     * @param relicWordDTO
     * @param wordRecordPO
     * @param updateUserId
     * @return
     */
    Boolean updateRelicScanningWord(Long relicId, Long relicScanningId, RelicWordDTO relicWordDTO, RelicScanningWordRecordPO wordRecordPO, Long updateUserId);

    /**
     * 变更文物信息图片信息
     *
     * @param relicId
     * @param imgUrl
     * @param adminUserId
     * @return
     */
    Boolean updateRelicImg(Long relicId, String imgUrl, String compressImgUrl, Long adminUserId);

    /**
     * 新增文物信息
     *
     * @param relicWordDTO
     * @param updateUserId
     * @return
     */
    Boolean saveRelicWord(RelicWordDTO relicWordDTO, Long updateUserId);

    /**
     * 设置报损状态
     *
     * @param id
     * @param repairStatusEnum
     * @return
     */
    Boolean setRepairStatus(Long id, RepairStatusEnum repairStatusEnum);

    /**
     * 设置出入库状态
     *
     * @param id
     * @param stockStatus
     * @return
     */
    Boolean setStockStatus(Long id, Integer stockStatus);

    /**
     * 分页查询
     *
     * @param pageInfo
     * @return
     */
    PageResult<RelicDTO> pageList(PageInfo<RelicDTO> pageInfo);

}
