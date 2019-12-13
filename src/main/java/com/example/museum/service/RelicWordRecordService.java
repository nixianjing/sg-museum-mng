package com.example.museum.service;

import com.example.museum.dto.RelicWordRecordDTO;
import com.example.museum.po.RelicScanningWordRecordPO;

import java.util.List;

/**
 * @author xianjing.n
 * @date 2019-11-24 17:50
 **/
public interface RelicWordRecordService {

    /**
     * 创建文档变更存档记录
     *
     * @param relicScanningWordRecordPO
     * @return
     */
    Boolean createWordRecord(RelicScanningWordRecordPO relicScanningWordRecordPO);

    /**
     * 根据ID查询
     *
     * @param wordRecordId
     * @return
     */
    RelicWordRecordDTO getRelicWordRecord(Long wordRecordId);

    /**
     * 查询文档变更改记录
     *
     * @param relicScanningId
     * @return
     */
    List<RelicWordRecordDTO> getWordRecordList(Long relicScanningId);
}
