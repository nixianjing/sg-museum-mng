package com.example.museum.po;

import lombok.Data;
import com.example.museum.common.enums.*;

import java.util.Date;

/**
 * 用户对应权限信息表
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Data
public class AdminUserPrivilegePO {

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