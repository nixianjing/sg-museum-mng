package com.example.museum.common.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * 数据字典枚举
 *
 * @author xianjing.n
 * @date 2019-12-04 20:22
 **/
@Getter
public enum DictTypeEnum {

    DICT_IMG_TYPE("IMG_TYPE", "图片分类", "文物扫描图片的类型"),
    DICT_YEARS("YEARS", "年代", "文物所属年代"),
    DICT_SOURCE("SOURCE", "来源", "文物的来源"),
    DICT_GRADE("GRADE", "等级", "文物所属等级"),
    DICT_RELIC_TYPE("RELIC_TYPE", "种类", "文物所属种类"),
    DICT_TEXTURE("TEXTURE", "质地", "文物所属质地"),
    DICT_MANUFACTURE("MANUFACTURE", "制造工艺", "文物的制造工艺"),
    DICT_TEXTILE("TEXTILE", "织物组织", "文物所属的织物组织"),
    ;

    private String code;

    private String name;

    private String message;

    DictTypeEnum(String code, String name, String message) {
        this.code = code;
        this.name = name;
        this.message = message;
    }

    private static DictTypeEnum getByCode(String code) {
        for (DictTypeEnum dictTypeEnum : DictTypeEnum.values()) {
            if (Objects.equals(dictTypeEnum.code, code)) {
                return dictTypeEnum;
            }
        }
        return null;
    }
}
