package com.example.museum.common.enums;

import lombok.Getter;

/**
 * 状态 枚举
 *
 * @author xianjing.n
 * @date 2019-10-15 14:26
 **/
@Getter
public enum StatusEnum {

    OPEN(0, "启用"),
    CLOSE(1, "禁用"),
    ;

    private Integer code;

    private String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
