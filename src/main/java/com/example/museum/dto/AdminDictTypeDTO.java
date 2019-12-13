package com.example.museum.dto;

import lombok.Data;

import java.util.Date;

/**
 * 数据字典类型
 *
 * @author xianjing.n
 * @date 2019-11-05 01:11
 **/
@Data
public class AdminDictTypeDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 编号
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String note;

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
