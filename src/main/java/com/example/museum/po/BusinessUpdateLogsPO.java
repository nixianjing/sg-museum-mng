package com.example.museum.po;

import lombok.Data;
import com.example.museum.common.enums.*;

import java.util.Date;

/**
 * 业务内容变更记录日志
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Data
public class BusinessUpdateLogsPO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 业务类型 {@link BusinessTypeEnum}
     */
    private Integer businessType;

    /**
     * 变更类型 {@link BusinessUpdateTypeEnum}
     */
    private String updateType;

    /**
     * 变更类型描述
     */
    private String updateTypeMessage;

    /**
     * 旧业务内容
     */
    private String oldContentJson;

    /**
     * 新业务内容
     */
    private String newContentJson;

    /**
     * 变更人ID
     */
    private Long updateUserId;

    /**
     * 变更人姓名
     */
    private String updateUserName;

    /**
     * 变更时间
     */
    private Date updateTime;
}