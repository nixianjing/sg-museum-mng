package com.example.museum.service;

import com.example.museum.dto.RelicScanningDTO;
import com.example.museum.dto.RelicWordDTO;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;
import com.example.museum.po.RelicScanningWordRecordPO;

/**
 * @author xianjing.n
 * @date 2019-11-24 17:52
 **/
public interface RelicScanningService {
    /**
     * 根据ID查询
     *
     * @param relicScanningId
     * @return
     */
    RelicScanningDTO getRelicScanningById(Long relicScanningId);

    /**
     * 根据项目名称和项目时间查询
     *
     * @param projectName
     * @param projectTime
     * @return
     */
    RelicScanningDTO getRelicScanningByProjectNameAndTime(Long relicId,String projectName, String projectTime);

    /**
     * 编辑文档信息
     *
     * @param relicScanningId
     * @param relicWordDTO
     * @param wordRecordPO
     * @param updateUserId
     * @return
     */
    Boolean updateRelicScanningWord(Long relicScanningId, RelicWordDTO relicWordDTO, RelicScanningWordRecordPO wordRecordPO, Long updateUserId);

    /**
     * 新增修改记录文档
     *
     * @param relicId
     * @param relicWordDTO
     * @param updateUserId
     * @return
     */
    Boolean saveRelicScanningWord(Long relicId, RelicWordDTO relicWordDTO, Long updateUserId);


    /**
     * 分页查询
     *
     * @param pageInfo
     * @return
     */
    PageResult<RelicScanningDTO> pageList(PageInfo<RelicScanningDTO> pageInfo);

}
