package com.example.museum.common.enums;

import lombok.Getter;

/**
 * 业务变更类型
 *
 * @author xianjing.n
 * @date 2019-10-15 15:56
 **/
@Getter
public enum BusinessMessageEnum {

    MESSAGE_USER_ADD("新增用户信息", "adminUserSave"),
    MESSAGE_USER_UPDATE("编辑用户信息", "adminUserUpdate"),
    MESSAGE_USER_UPDATE_STATUS("编辑用户状态信息", "adminUserUpdateStatus"),
    MESSAGE_USER_DELETE("删除用户信息", "adminUserDelete"),

    ;

    private String code;

    private String message;

    BusinessMessageEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
