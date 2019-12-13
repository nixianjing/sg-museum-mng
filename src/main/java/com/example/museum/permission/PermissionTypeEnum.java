package com.example.museum.permission;

import lombok.Getter;

/**
 * 权限类型枚举
 *
 * @author xianjing.n
 * @since 2019-10-25 01:34
 **/
@Getter
public enum PermissionTypeEnum {

    PERMISSION(0,  "权限"),
    GROUP(1,  "组"),
    MENU(2,"菜单"),
    BUTTON(3,"按钮"),
    ;
    private Integer code;

    private String title;

    PermissionTypeEnum(Integer code, String title) {
        this.code = code;
        this.title = title;
    }
}
