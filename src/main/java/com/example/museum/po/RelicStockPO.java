package com.example.museum.po;

import lombok.Data;

import java.util.Date;

/**
 * 文物出入库汇总信息表
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Data
public class RelicStockPO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 待出库数量
     */
    private Integer stayStockNum;

    /**
     * 已出库数量
     */
    private Integer outStockNum;

    /**
     * 入库数量
     */
    private Integer enterStockNum;

    /**
     * 操作人ID
     */
    private Long userId;

    /**
     * 操作人姓名
     */
    private String userName;

    /**
     * 创建时间
     */
    private Date createTime;
}