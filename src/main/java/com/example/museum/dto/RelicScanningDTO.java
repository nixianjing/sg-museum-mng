package com.example.museum.dto;

import lombok.Data;

import java.util.Date;

/**
 * 修复记录信息
 * @author xianjing.n
 * @date 2019-11-23 13:19
 **/
@Data
public class RelicScanningDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 文档ID
     */
    private Long relicId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目时间
     */
    private String projectTime;

    /**
     * 方案设计单位
     */
    private String designCompany;

    /**
     * 保护修复单位
     */
    private String repairCompany;

    /**
     * 方案名称及编号
     */
    private String planNameCode;

    /**
     * 批准单位及文号
     */
    private String companyCode;

    /**
     * 提取日期
     */
    private String extractTime;

    /**
     * 提取经办人
     */
    private String extractUserName;

    /**
     * 返还日期
     */
    private String returnTime;

    /**
     * 返还经办人
     */
    private String returnUserName;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 织物密度_经线
     */
    private String densityLng;

    /**
     * 织物密度_纬线
     */
    private String densityLat;

    /**
     * 纱线颜色_经线
     */
    private String colourLng;

    /**
     * 纱线颜色_纬线
     */
    private String colourLat;

    /**
     * 纱线细度
     */
    private String fineness;

    /**
     * 纱线捻度
     */
    private String twist;

    /**
     * 纱线捻向
     */
    private String twistDirection;

    /**
     * 文物描述
     */
    private String relicExplain;

    /**
     * 藏品保存环境
     */
    private String environment;

    /**
     * 原保护修复情况
     */
    private String protect;

    /**
     * 病害状况描述
     */
    private String disease;

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
     * 文档时间
     */
    private String wordDate;

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
