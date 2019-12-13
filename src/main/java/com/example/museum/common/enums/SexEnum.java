package com.example.museum.common.enums;

import lombok.Getter;

/**
 * 性别 枚举
 *
 * @author xianjing.n
 * @date 2019-10-15 14:26
 **/
@Getter
public enum SexEnum {

    BOY(1, "男"),
    GIRL(2, "女"),
    ;

    private Integer code;

    private String message;

    SexEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
