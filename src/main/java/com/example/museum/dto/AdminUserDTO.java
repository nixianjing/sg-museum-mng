package com.example.museum.dto;

import com.example.museum.common.enums.DataFlagEnum;
import com.example.museum.common.enums.SexEnum;
import com.example.museum.common.enums.StatusEnum;
import lombok.Data;

import java.util.Date;

/**
 * 用户管理信息
 *
 * @author xianjing.n
 * @date 2019-10-14 19:31
 **/
@Data
public class AdminUserDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 登录账号
     */
    private String userCode;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别 {@link SexEnum}
     */
    private Integer sex;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 状态 {@link StatusEnum}
     */
    private Integer status;

    /**
     * 数据状态 {@link DataFlagEnum}
     */
    private Integer dataFlag;

    /**
     * 备注
     */
    private String remark;

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
