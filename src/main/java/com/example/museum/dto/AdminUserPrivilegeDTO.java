package com.example.museum.dto;

import com.example.museum.common.enums.DataFlagEnum;
import lombok.Data;

import java.util.Date;

/**
 * 用户对应权限
 *
 * @author xianjing.n
 * @date 2019-10-28 01:33
 **/
@Data
public class AdminUserPrivilegeDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long adminUserId;

    /**
     * 权限编号
     */
    private String privilegeCode;

    /**
     * 数据标志 {@link DataFlagEnum}
     */
    private Integer dataFlag;

    /**
     * 创建人ID
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 编辑人ID
     */
    private Long updateUserId;

    /**
     * 编辑时间
     */
    private Date updateTime;
}
