package com.example.museum.po;

import lombok.Data;
import com.example.museum.common.enums.*;

import java.util.Date;

/**
 * 角色对应权限对象
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Data
public class AdminRolePrivilegePO {
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