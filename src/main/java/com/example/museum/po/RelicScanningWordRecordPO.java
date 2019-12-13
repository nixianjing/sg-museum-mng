package com.example.museum.po;

import lombok.Data;

import java.util.Date;

/**
 * 文档修复文档变更记录
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Data
public class RelicScanningWordRecordPO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 修复扫描记录ID
     */
    private Long relicScanningId;

    /**
     * 文物名称
     */
    private String relicName;

    /**
     * 文物编号
     */
    private String relicNo;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目时间
     */
    private String projectTime;

    /**
     * 文档路径
     */
    private String wordPath;

    /**
     * 文档名称
     */
    private String wordName;

    /**
     * 文档URL
     */
    private String wordUrl;

    /**
     * 创建人ID
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

}