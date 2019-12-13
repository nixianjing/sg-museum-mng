package com.example.museum.po;

import lombok.Data;
import com.example.museum.common.enums.*;

import java.util.Date;

/**
 * 管理用户对应角色对象
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Data
public class AdminUserRolePO {
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