package com.example.museum.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author xianjing.n
 * @date 2019-12-10 21:45
 **/
@Data
public class RelicStockDetailedDTO {

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
     * 文物编号
     */
    private String relicNo;

    /**
     * 文物出入库汇总ID
     */
    private Long relicStockId;

    /**
     * 出库时间
     */
    private String outTime;

    /**
     * 入库时间
     */
    private String enterTime;

    /**
     * 原因
     */
    private String reason;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 出入库状态： 0待出库 1已出库 3已入库
     */
    private Integer status;

    /**
     * 删除时间
     */
    private Byte deleteFlag;

    /**
     * 出库操作人ID
     */
    private Long userId;

    /**
     * 出库操作人姓名
     */
    private String userName;

    /**
     * 入库操作人ID
     */
    private Long updateUserId;

    /**
     * 入库出库操作人姓名
     */
    private String updateUserName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 编辑时间
     */
    private Date modifyTime;
}
