package com.example.museum.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.museum.common.enums.DataFlagEnum;
import com.example.museum.common.enums.StatusEnum;
import com.example.museum.common.utils.ListObjConverter;
import com.example.museum.common.utils.ObjConverter;
import com.example.museum.dao.AdminRoleMapper;
import com.example.museum.dto.AdminRoleDTO;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;
import com.example.museum.dto.base.Response;
import com.example.museum.po.AdminRolePO;
import com.example.museum.service.AdminRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author xianjing.n
 * @date 2019-10-21 19:36
 **/
@Service
public class AdminRoleServiceImpl implements AdminRoleService {

    private static final Logger LOG = LoggerFactory.getLogger(AdminRoleServiceImpl.class);


    /**
     * 角色
     */
    @Resource
    private AdminRoleMapper adminRoleMapper;


    @Override
    public AdminRoleDTO findById(Long id) {
        return adminRoleMapper.findById(id);
    }

    @Override
    public Response<String> save(AdminRoleDTO adminRoleDTO) {
        String roleCode = UUID.randomUUID().toString();
        adminRoleDTO.setRoleCode(roleCode);
        adminRoleDTO.setStatus(StatusEnum.OPEN.getCode());
        adminRoleDTO.setDataFlag(DataFlagEnum.NORMAL.getCode());
        adminRoleDTO.setCreateTime(new Date());
        adminRoleDTO.setUpdateTime(new Date());
        if (adminRoleMapper.insert(ObjConverter.convert(adminRoleDTO, AdminRolePO.class)) > 0) {
            return new Response<>(roleCode);
        }
        return new Response<>();
    }

    @Override
    public Response<Boolean> update(AdminRoleDTO adminRoleDTO) {
        adminRoleDTO.setUpdateTime(new Date());
        return new Response<>(adminRoleMapper.update(ObjConverter.convert(adminRoleDTO, AdminRolePO.class)) > 0);
    }

    @Override
    public Boolean batchUpdateStatus(List<Long> ids, StatusEnum statusEnum, Long updateUserId) {
        return adminRoleMapper.batchUpdateStatus(ids, statusEnum.getCode(), updateUserId) > 0;
    }

    @Override
    public Boolean batchDelete(List<Long> ids, Long updateUserId) {
        return adminRoleMapper.batchDelete(ids, updateUserId) > 0;
    }

    @Override
    public List<AdminRoleDTO> getRoleList(List<String> roleList) {
        List<AdminRolePO> infoList = adminRoleMapper.getList(roleList);
        return ListObjConverter.convert(infoList, AdminRoleDTO.class);
    }

    @Override
    public PageResult<AdminRoleDTO> pageList(PageInfo<AdminRoleDTO> pageInfo) {
        try {
            AdminRoleDTO adminRoleDTO = pageInfo.getData();
            int count = adminRoleMapper.listCount(adminRoleDTO);
            pageInfo.setCountNumbers(count);
            List<AdminRoleDTO> dtoList = null;
            if (pageInfo.isQuery()) {
                dtoList = adminRoleMapper.pageList(adminRoleDTO, pageInfo);
            }
            return new PageResult<>((int) (pageInfo.getQueryPage() + 1), pageInfo.getPageSize(), count, dtoList);
        } catch (Exception e) {
            LOG.error("查询角色异常.param={},{}", JSONObject.toJSONString(pageInfo), e);
        }
        return null;
    }
}
