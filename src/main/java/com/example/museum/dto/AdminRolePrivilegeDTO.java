package com.example.museum.dto;

import com.example.museum.common.enums.DataFlagEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author xianjing.n
 * @date 2019-10-30 21:53
 **/
@Data
public class AdminRolePrivilegeDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 角色编号
     */
    private String roleCode;

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
