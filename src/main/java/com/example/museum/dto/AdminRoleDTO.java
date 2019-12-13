package com.example.museum.dto;

import com.example.museum.common.enums.DataFlagEnum;
import com.example.museum.common.enums.StatusEnum;
import lombok.Data;

import java.util.Date;

/**
 * 角色信息
 *
 * @author xianjing.n
 * @date 2019-10-24 01:56
 **/
@Data
public class AdminRoleDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 角色编号
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 状态 {@link StatusEnum}
     */
    private Integer status;

    /**
     * 数据标志 {@link DataFlagEnum}
     */
    private Integer dataFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人姓名
     */
    private String createUserName;

    /**
     * 修改人姓名
     */
    private String updateUserName;

    /**
     * 创建人姓名
     */
    private Long createUserId;

    /**
     * 修改人姓名
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
