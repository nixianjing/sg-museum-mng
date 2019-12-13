package com.example.museum.dto.base;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 响应码对照表
 *
 * @author 小明
 */
public enum CodeTable implements Serializable {

    SUCCESS(0, "成功!"),
    EXCEPTION(1, "网络异常,请稍候重试!"),
    ERROR(2, "参数错误"),
    DATA_HAS_EXIST(3, "数据已存在!"),
    DATA_NONE_EXIST(4, "数据不存在!"),
    ILLEGAL(5, "非法请求"),
    FILE_ERROR(6, "上传失败"),
    WORD_ERROR(7, "解析word失败"),
    CAPTCHA_ERROR(101, "账号不存在"),
    PASSWORD_ERROR(102, "密码不正确！"),
    NO_PERMISSION(103, "权限不足");
    /**
     * 存放CODE -> Enum的Map
     */
    private static final ConcurrentMap<Integer, CodeTable> CODE_MAP = new ConcurrentHashMap<Integer, CodeTable>(
            CodeTable.values().length);

    /**
     * 填充CODE --> Enum的Map
     */
    static {
        for (CodeTable codeTable : CodeTable.values()) {
            CODE_MAP.put(codeTable.code, codeTable);
        }
    }

    /**
     * 响应码
     */
    private final int code;

    /**
     * 响应消息
     */
    private final String message;

    /**
     * 构造函数
     *
     * @param code    响应码
     * @param message 响应消息
     */
    CodeTable(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CodeTable fromCode(Integer code) {
        return CODE_MAP.get(code);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
