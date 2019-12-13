package com.example.museum.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author xianjing.n
 * @date 2019-12-10 20:43
 **/
@Data
public class RelicStockDTO {

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

    private String startTime;

    private String endTime;
}
