package com.example.museum.dto;

import com.example.museum.common.enums.BusinessTypeEnum;
import com.example.museum.common.enums.BusinessUpdateTypeEnum;
import com.example.museum.common.utils.DateUtil;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * @author xianjing.n
 * @date 2019-12-01 15:41
 **/
@Data
public class BusinessUpdateLogsDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 业务类型 {@link BusinessTypeEnum}
     */
    private Integer businessType;

    /**
     * 变更类型 {@link com.example.museum.common.enums.BusinessUpdateTypeEnum}
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


    private String businessTypeName;

    private String updateTypeName;

    private String updateTimeStr;

    public String getBusinessTypeName() {
        if (Objects.isNull(this.businessType)) {
            return null;
        }
        BusinessTypeEnum businessTypeEnum = BusinessTypeEnum.getByCode(this.businessType);
        if (Objects.isNull(businessTypeEnum)) {
            return null;
        }
        return businessTypeEnum.getMessage();
    }

    public String getUpdateTypeName() {
        if (Objects.isNull(this.updateType)) {
            return null;
        }
        BusinessUpdateTypeEnum businessUpdateTypeEnum = BusinessUpdateTypeEnum.getByCode(this.updateType);
        if (Objects.isNull(businessUpdateTypeEnum)) {
            return null;
        }
        return businessUpdateTypeEnum.getMessage();
    }

    public String getUpdateTimeStr() {
        if (Objects.isNull(this.updateTime)) {
            return null;
        }
        return DateUtil.dateFormat(this.updateTime, DateUtil.F_DATETIME);
    }
}
