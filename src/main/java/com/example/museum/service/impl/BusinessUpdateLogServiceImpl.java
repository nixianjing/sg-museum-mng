package com.example.museum.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.museum.common.enums.BusinessMessageEnum;
import com.example.museum.common.enums.BusinessTypeEnum;
import com.example.museum.common.enums.BusinessUpdateTypeEnum;
import com.example.museum.common.utils.ListObjConverter;
import com.example.museum.common.utils.ObjConverter;
import com.example.museum.dao.BusinessUpdateLogsMapper;
import com.example.museum.dto.AdminUserDTO;
import com.example.museum.dto.BusinessUpdateLogsDTO;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;
import com.example.museum.po.AdminUserPO;
import com.example.museum.po.BusinessUpdateLogsPO;
import com.example.museum.service.BusinessUpdateLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author xianjing.n
 * @date 2019-10-21 20:13
 **/
@Service
public class BusinessUpdateLogServiceImpl implements BusinessUpdateLogService {

    private static final Logger LOG = LoggerFactory.getLogger(AdminUserServiceImpl.class);

    /**
     * 变更日志
     */
    @Resource
    private BusinessUpdateLogsMapper businessUpdateLogsMapper;


    @Override
    public PageResult<BusinessUpdateLogsDTO> pageList(PageInfo<BusinessUpdateLogsDTO> pageInfo) {
        try {
            BusinessUpdateLogsPO businessUpdateLogsPO = ObjConverter.convert(pageInfo.getData(), BusinessUpdateLogsPO.class);
            int count = businessUpdateLogsMapper.listCount(businessUpdateLogsPO);
            pageInfo.setCountNumbers(count);
            List<BusinessUpdateLogsDTO> dtoList = null;
            if (pageInfo.isQuery()) {
                List<BusinessUpdateLogsPO> infoList = businessUpdateLogsMapper.pageList(businessUpdateLogsPO, pageInfo);
                dtoList = ListObjConverter.convert(infoList, BusinessUpdateLogsDTO.class);
            }
            return new PageResult<>((int) (pageInfo.getQueryPage() + 1), pageInfo.getPageSize(), count, dtoList);
        } catch (Exception e) {
            LOG.error("查询变更日志异常.param={},{}", JSONObject.toJSONString(pageInfo), e);
        }
        return null;
    }

    @Override
    public BusinessUpdateLogsDTO getById(Long id) {
        BusinessUpdateLogsPO businessUpdateLogsPO = businessUpdateLogsMapper.getById(id);
        if (Objects.isNull(businessUpdateLogsPO)) {
            return null;
        }
        return ObjConverter.convert(businessUpdateLogsPO, BusinessUpdateLogsDTO.class);
    }

    @Override
    public Boolean save(BusinessTypeEnum typeEnum, BusinessUpdateTypeEnum updateTypeEnum, BusinessMessageEnum messageEnum, String oldContentJson, String newContentJson, AdminUserDTO userToken) {
        BusinessUpdateLogsPO logsPO = new BusinessUpdateLogsPO();
        logsPO.setBusinessType(typeEnum.getCode());
        logsPO.setUpdateType(updateTypeEnum.getCode());
        logsPO.setUpdateTypeMessage(messageEnum.getCode());
        logsPO.setOldContentJson(oldContentJson);
        logsPO.setNewContentJson(newContentJson);
        logsPO.setUpdateUserId(userToken.getId());
        logsPO.setUpdateUserName(userToken.getName());
        logsPO.setUpdateTime(new Date());
        return businessUpdateLogsMapper.insert(logsPO) > 0;
    }
}
