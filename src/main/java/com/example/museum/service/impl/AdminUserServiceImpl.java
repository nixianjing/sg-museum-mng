package com.example.museum.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.museum.common.enums.DataFlagEnum;
import com.example.museum.common.enums.StatusEnum;
import com.example.museum.common.utils.ListObjConverter;
import com.example.museum.common.utils.ObjConverter;
import com.example.museum.dao.AdminRolePrivilegeMapper;
import com.example.museum.dao.AdminUserMapper;
import com.example.museum.dao.AdminUserPrivilegeMapper;
import com.example.museum.dao.AdminUserRoleMapper;
import com.example.museum.dto.AdminUserDTO;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;
import com.example.museum.dto.base.Response;
import com.example.museum.po.AdminRolePrivilegePO;
import com.example.museum.po.AdminUserPO;
import com.example.museum.po.AdminUserPrivilegePO;
import com.example.museum.po.AdminUserRolePO;
import com.example.museum.service.AdminUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xianjing.n
 * @date 2019-10-21 19:35
 **/
@Service
public class AdminUserServiceImpl implements AdminUserService {

    private static final Logger LOG = LoggerFactory.getLogger(AdminUserServiceImpl.class);

    /**
     * 管理员
     */
    @Resource
    private AdminUserMapper adminUserMapper;

    /**
     * 管理员对应权限
     */
    @Resource
    private AdminUserPrivilegeMapper adminUserPrivilegeMapper;

    /**
     * 管理员对应角色
     */
    @Resource
    private AdminUserRoleMapper adminUserRoleMapper;

    /**
     * 角色对应权限
     */
    @Resource
    private AdminRolePrivilegeMapper adminRolePrivilegeMapper;


    @Override
    public AdminUserDTO findByUserCode(String userCode) {
        AdminUserPO adminUserPO = adminUserMapper.getByUserCode(userCode);
        if (Objects.isNull(adminUserPO)) {
            return null;
        }
        AdminUserDTO adminUserDTO = ObjConverter.convert(adminUserPO, AdminUserDTO.class);
        return adminUserDTO;
    }

    @Override
    public AdminUserDTO findByMobile(String mobile) {
        AdminUserPO adminUserPO = adminUserMapper.getByMobile(mobile);
        if (Objects.isNull(adminUserPO)) {
            return null;
        }
        AdminUserDTO adminUserDTO = ObjConverter.convert(adminUserPO, AdminUserDTO.class);
        return adminUserDTO;
    }

    @Override
    public AdminUserDTO findById(Long id) {
        AdminUserPO adminUserPO = adminUserMapper.getById(id);
        if (Objects.isNull(adminUserPO)) {
            return null;
        }
        AdminUserDTO adminUserDTO = ObjConverter.convert(adminUserPO, AdminUserDTO.class);
        return adminUserDTO;
    }

    @Override
    public List<String> findUserPrivilegeById(Long adminUserId) {
        /** 权限集合 **/
        List<String> privilegeList = new ArrayList<>();
        /** 查询用户对应角色 **/
        List<AdminUserRolePO> userRolePOList = adminUserRoleMapper.list(adminUserId);
        if (userRolePOList != null && userRolePOList.size() > 0) {
            /** 角色集合 **/
            List<String> roles = userRolePOList.stream().map(AdminUserRolePO::getRoleCode).collect(Collectors.toList());
            /** 角色对应权限 **/
            List<AdminRolePrivilegePO> rolePrivilegePOList = adminRolePrivilegeMapper.lists(roles);
            privilegeList = rolePrivilegePOList.stream().map(AdminRolePrivilegePO::getPrivilegeCode).collect(Collectors.toList());
        }
        /** 查询用户对应权限 **/
        List<AdminUserPrivilegePO> userPrivilegePOList = adminUserPrivilegeMapper.list(adminUserId);
        if (userPrivilegePOList != null && userPrivilegePOList.size() > 0) {
            privilegeList.addAll(userPrivilegePOList.stream().map(AdminUserPrivilegePO::getPrivilegeCode).collect(Collectors.toList()));
        }
        return privilegeList;
    }

    @Override
    public Response<Long> save(AdminUserDTO adminUserDTO) {
        adminUserDTO.setStatus(StatusEnum.OPEN.getCode());
        adminUserDTO.setDataFlag(DataFlagEnum.NORMAL.getCode());
        adminUserDTO.setCreateTime(new Date());
        adminUserDTO.setUpdateTime(new Date());
        adminUserDTO.setPassword(DigestUtils.md5Hex(adminUserDTO.getPassword()).toLowerCase());
        return new Response<>(adminUserMapper.insert(ObjConverter.convert(adminUserDTO, AdminUserPO.class)));
    }

    @Override
    public Response<Boolean> update(AdminUserDTO adminUserDTO) {
        adminUserDTO.setUpdateTime(new Date());
        return new Response<>(adminUserMapper.update(ObjConverter.convert(adminUserDTO, AdminUserPO.class)) > 0);
    }

    @Override
    public Boolean batchUpdateStatus(List<Long> adminUserIds, Long updateUserId, StatusEnum statusEnum) {
        return adminUserMapper.batchUpdateStatusById(adminUserIds, statusEnum.getCode(), updateUserId) > 0;
    }

    @Override
    public Boolean batchDelete(List<Long> adminUserIds, Long updateUserId, DataFlagEnum dataFlagEnum) {
        return adminUserMapper.batchDeleteById(adminUserIds, dataFlagEnum.getCode(), updateUserId) > 0;
    }

    @Override
    public Boolean updatePasswordById(Long adminUserId, Long updateUserId, String password) {
        if(StringUtils.isBlank(password)) {
            password = "000000";
        }
        password = DigestUtils.md5Hex(password).toLowerCase();
        return adminUserMapper.updatePasswordById(adminUserId, updateUserId, password) > 0;
    }

    @Override
    public PageResult<AdminUserDTO> pageList(PageInfo<AdminUserDTO> pageInfo) {
        try {
            AdminUserPO adminUserPO = ObjConverter.convert(pageInfo.getData(), AdminUserPO.class);
            int count = adminUserMapper.listCount(adminUserPO);
            pageInfo.setCountNumbers(count);
            List<AdminUserDTO> dtoList = null;
            if (pageInfo.isQuery()) {
                List<AdminUserPO> infoList = adminUserMapper.pageList(adminUserPO, pageInfo);
                dtoList = ListObjConverter.convert(infoList, AdminUserDTO.class);
            }
            return new PageResult<>((int) (pageInfo.getQueryPage() + 1), pageInfo.getPageSize(), count, dtoList);
        } catch (Exception e) {
            LOG.error("查询企业异常.param={},{}", JSONObject.toJSONString(pageInfo), e);
        }
        return null;
    }
}
