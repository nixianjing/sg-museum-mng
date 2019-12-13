package com.example.museum.dto;

import lombok.Data;

/**
 * @author xianjing.n
 * @date 2019-11-24 00:50
 **/
@Data
public class RelicScanningImgDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 修复记录ID
     */
    private Long relicScanningId;

    /**
     * 图片类型
     */
    private String type;

    /**
     * 图片路径
     */
    private String imgPath;

    /**
     * 图片名称
     */
    private String imgName;

    /**
     * 图片URL
     */
    private String imgUrl;

    /**
     * 压缩图片路径 isImg=0一定有值
     */
    private String compressImgUrl;

    /**
     * 图片描述
     */
    private String relicExplain;

    /**
     * 是否图片
     */
    private Integer isImg;

    /**
     * 创建人
     */
    private Long createUserId;

    /**
     * 编辑人
     */
    private Long updateUserId;

}
