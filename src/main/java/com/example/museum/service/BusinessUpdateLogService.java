package com.example.museum.service;

import com.example.museum.common.enums.BusinessMessageEnum;
import com.example.museum.common.enums.BusinessTypeEnum;
import com.example.museum.common.enums.BusinessUpdateTypeEnum;
import com.example.museum.dto.AdminUserDTO;
import com.example.museum.dto.BusinessUpdateLogsDTO;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;

/**
 * 日志Service
 *
 * @author xianjing.n
 * @date 2019-10-21 20:13
 **/
public interface BusinessUpdateLogService {

    /**
     * 分页查询
     *
     * @param pageInfo
     * @return
     */
    PageResult<BusinessUpdateLogsDTO> pageList(PageInfo<BusinessUpdateLogsDTO> pageInfo);


    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    BusinessUpdateLogsDTO getById(Long id);

    /**
     * 添加操作日志
     *
     * @param typeEnum
     * @param updateTypeEnum
     * @param messageEnum
     * @param oldContentJson
     * @param newContentJson
     * @param userToken
     * @return
     */
    Boolean save(BusinessTypeEnum typeEnum, BusinessUpdateTypeEnum updateTypeEnum, BusinessMessageEnum messageEnum, String oldContentJson, String newContentJson, AdminUserDTO userToken);

}
