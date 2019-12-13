package com.example.museum.common.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * @author xianjing.n
 * @date 2019-12-12 15:36
 **/
@Getter
public enum RepairStatusEnum {

    REPAIR_STATUS_STAY(0, "待修复"),
    REPAIR_STATUS_IN(1, "修复中"),
    REPAIR_STATUS_COMPLETE(2, "已修复"),
    ;

    private Integer code;

    private String message;

    RepairStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private static RepairStatusEnum getByCode(String code) {
        for (RepairStatusEnum repairStatusEnum : RepairStatusEnum.values()) {
            if (Objects.equals(repairStatusEnum.code, code)) {
                return repairStatusEnum;
            }
        }
        return null;
    }
}
