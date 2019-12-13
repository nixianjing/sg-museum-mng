package com.example.museum.dto;

import lombok.Data;

import java.util.Date;

/**
 * 文物信息
 *
 * @author xianjing.n
 * @date 2019-11-23 13:17
 **/
@Data
public class RelicDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 文物名称
     */
    private String relicName;

    /**
     * 文物编号
     */
    private String relicNo;

    /**
     * 年代
     */
    private String years;

    /**
     * 来源
     */
    private String source;

    /**
     * 等级
     */
    private String grade;

    /**
     * 尺寸
     */
    private String dimensions;

    /**
     * 文物种类
     */
    private String relicType;

    /**
     * 质地
     */
    private String texture;

    /**
     * 收藏单位
     */
    private String collectionCompany;

    /**
     * 收藏时间
     */
    private String collectionTime;

    /**
     * 制造工艺
     */
    private String manufacture;

    /**
     * 织物组织
     */
    private String textile;

    /**
     * 文物图片URL
     */
    private String imgUrl;

    /**
     * 压缩图片路径
     */
    private String compressImgUrl;

    /**
     * 报损状态 0正常 1待修复 2修复中
     */
    private Integer repairStatus;

    /**
     * 出入库状态: 0在库 1待出库 2已出库
     */
    private Integer stockStatus;

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
