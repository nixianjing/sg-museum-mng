package com.example.museum.common.enums;

import lombok.Getter;

/**
 * 业务类型
 *
 * @author xianjing.n
 * @date 2019-10-15 15:56
 **/
@Getter
public enum BusinessTypeEnum {

    ADMIN_USER(1, "用户"),
    ADMIN_ROLE(2, "角色"),
    ADMIN_USER_ROLE(3, "用户对应角色"),
    ADMIN_ROLE_PRIVILEGE(4, "角色对应权限"),
    ADMIN_USER_PRIVILEGE(5, "用户对应权限"),
    ;

    private Integer code;

    private String message;

    BusinessTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BusinessTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (BusinessTypeEnum typeEnum : BusinessTypeEnum.values()) {
            if (typeEnum.getCode().equals(code)) {
                return typeEnum;
            }
        }
        return null;
    }

}
