package com.example.museum.po;

/**
 * 权限对象
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
public class PermissionPO {

    private String parentCode;

    private String code;

    private String text;

    public PermissionPO(String parentCode, String code, String text) {
        this.parentCode = parentCode;
        this.code = code;
        this.text = text;
    }

    public PermissionPO() {
    }
}
