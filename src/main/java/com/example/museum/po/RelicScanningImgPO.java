package com.example.museum.po;

import lombok.Data;

import java.util.Date;

/**
 * 文物修复记录图片记录
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Data
public class RelicScanningImgPO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 修复记录ID
     */
    private Long relicScanningId;

    /**
     * 图片类型 数据字典类型 {@link com.example.museum.common.enums.DictTypeEnum}.DICT_IMG_TYPE
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
     * 压缩图片路径 isImg=0一定有值
     */
    private String compressImgUrl;

    /**
     * 图片URL
     */
    private String imgUrl;

    /**
     * 图片描述
     */
    private String relicExplain;

    /**
     * 是否图片 0是否 1否
     */
    private Integer isImg;

    /**
     * 状态 0有效 1无效
     */
    private Integer status;

    /**
     * 创建人
     */
    private Long createUserId;

    /**
     * 编辑人
     */
    private Long updateUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 编辑时间
     */
    private Date updateTime;
}