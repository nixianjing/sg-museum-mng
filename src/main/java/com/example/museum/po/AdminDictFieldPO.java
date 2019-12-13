package com.example.museum.po;

import lombok.Data;

import java.util.Date;

/**
 * 数据字典明细
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Data
public class AdminDictFieldPO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 类型名称
     */
    private String dictTypeCode;

    /**
     * 类型名称
     */
    private String dictTypeName;

    /**
     * 名称
     */
    private String dictName;

    /**
     * 展示排序
     */
    private Integer sort;

    /**
     * 状态 {@link com.example.museum.common.enums.StatusEnum}
     */
    private Integer status;

    /**
     * 数据状态 {@link com.example.museum.common.enums.DataFlagEnum}
     */
    private Integer dataFlag;

    /**
     * 编辑人ID
     */
    private Long updateUserId;

    /**
     * 创建人ID
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 编辑时间
     */
    private Date updateTime;

}