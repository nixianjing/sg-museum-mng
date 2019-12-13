package com.example.museum.common.enums;

import lombok.Getter;

/**
 * 数据标志 枚举
 *
 * @author xianjing.n
 * @date 2019-10-15 14:26
 **/
@Getter
public enum DataFlagEnum {

    NORMAL(0, "正常"),
    DELETED(1, "删除"),
    ;

    private Integer code;

    private String message;

    DataFlagEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
