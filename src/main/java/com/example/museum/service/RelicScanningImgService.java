package com.example.museum.service;

import com.example.museum.dto.RelicScanningImgDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文物修复图片
 *
 * @author xianjing.n
 * @date 2019-11-24 00:48
 **/
public interface RelicScanningImgService {

    /**
     * 新增文物修复记录图片
     *
     * @param relicScanningImgDTO
     * @return
     */
    Boolean saveRelicScanningImg(RelicScanningImgDTO relicScanningImgDTO);

    /**
     * 批量插入文物修复记录图片信息
     *
     * @param dtoList
     * @return
     */
    Boolean batchRelicScanningImg(List<RelicScanningImgDTO> dtoList, Long createUserId);

    /**
     * 编辑图片描述
     *
     * @param imgId        图片ID
     * @param relicExplain 图片描述
     * @param updateUserId 编辑人ID
     * @return
     */
    Boolean updateImgDescribe(Long imgId, String relicExplain, Long updateUserId);

    /**
     * 编辑图片
     *
     * @param imgId
     * @param imgName
     * @param imgUrl
     * @return
     */
    Boolean updateImgUrl(Long imgId, String imgName, String imgUrl, Long updateUserId);

    /**
     * 删除图片
     *
     * @param relicId      文物ID
     * @param imgId        图片ID
     * @param updateUserId 删除人ID
     * @return
     */
    Boolean deleteImg(Long relicId, Long imgId, Long updateUserId);

    /**
     * 查询文物修复记录图片集合
     *
     * @param relicScanningId 文物修复记录ID
     * @param type            图片类型
     * @return
     */
    List<RelicScanningImgDTO> getRelicScanningImgList(Long relicScanningId, String type,Integer isImg);


    RelicScanningImgDTO getRelicScanningImg(Long relicScanningId, String type, String imgName);
}
