package com.example.museum.dto;

import com.example.museum.common.enums.DataFlagEnum;
import lombok.Data;

import java.util.Date;

/**
 * 用户对应角色
 *
 * @author xianjing.n
 * @date 2019-10-28 00:06
 **/
@Data
public class AdminUserRoleDTO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 管理用户Id
     */
    private Long adminUserId;

    /**
     * 角色编号
     */
    private String roleCode;

    /**
     * 数据标志 {@link DataFlagEnum}
     */
    private Integer dataFlag;

    /**
     * 创建人ID
     */
    private Long createUserId;

    /**
     * 修改人ID
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
