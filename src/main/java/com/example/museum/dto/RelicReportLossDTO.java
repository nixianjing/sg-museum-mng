package com.example.museum.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author xianjing.n
 * @date 2019-12-10 21:18
 **/
@Data
public class RelicReportLossDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 文物ID
     */
    private Long relicId;

    /**
     * 文物名称
     */
    private String relicName;

    /**
     * 报损时间
     */
    private String reimburseTime;

    /**
     * 报损原因
     */
    private String reason;

    /**
     * 报损状态 0待修复 1修复中 2已修复
     */
    private Integer status;

    /**
     * 报损人ID
     */
    private Long userId;

    /**
     * 报损人姓名
     */
    private String userName;

    /**
     * 修复完成说明
     */
    private String repairReason;

    /**
     * 修复完成操作人ID
     */
    private Long repairUserId;

    /**
     * 修复完成操作人姓名
     */
    private String repairUserName;

    /**
     * 修复完成时间
     */
    private String repairTime;

    /**
     * 创建时间
     */
    private Date createTime;
}
