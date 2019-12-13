package com.example.museum.common.enums;

import lombok.Getter;

/**
 * @author xianjing.n
 * @date 2019-11-28 13:01
 **/
@Getter
public enum ImgTypeEnum {

    IMG_TYPE_YES(0, "图片"),
    IMG_TYPE_NO(1, "非图片"),
    ;

    private Integer code;

    private String message;

    ImgTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
