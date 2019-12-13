package com.example.museum.common.enums;

import lombok.Getter;

/**
 * 业务变更类型
 *
 * @author xianjing.n
 * @date 2019-10-15 15:56
 **/
@Getter
public enum BusinessUpdateTypeEnum {

    CREATE_CONTENT("add", "新增信息"),
    UPDATE_CONTENT("update", "修改信息"),
    DELETE_CONTENT("delete", "删除信息"),
    ;

    private String code;

    private String message;

    BusinessUpdateTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BusinessUpdateTypeEnum getByCode(String code) {
        if (code == null) {
            return null;
        }
        for (BusinessUpdateTypeEnum typeEnum : BusinessUpdateTypeEnum.values()) {
            if (typeEnum.getCode().equals(code)) {
                return typeEnum;
            }
        }
        return null;
    }
}
