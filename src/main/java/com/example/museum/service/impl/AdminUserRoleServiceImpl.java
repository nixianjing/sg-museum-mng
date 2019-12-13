package com.example.museum.service.impl;

import com.example.museum.common.enums.DataFlagEnum;
import com.example.museum.common.utils.ListObjConverter;
import com.example.museum.dao.AdminUserRoleMapper;
import com.example.museum.dto.AdminUserRoleDTO;
import com.example.museum.dto.base.Response;
import com.example.museum.po.AdminUserRolePO;
import com.example.museum.service.AdminUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xianjing.n
 * @date 2019-10-28 00:09
 **/
@Service
public class AdminUserRoleServiceImpl implements AdminUserRoleService {

    @Resource
    private AdminUserRoleMapper adminUserRoleMapper;

    @Override
    public List<AdminUserRoleDTO> getUserRoleList(Long adminUserId) {
        List<AdminUserRolePO> infoList = adminUserRoleMapper.list(adminUserId);
        return ListObjConverter.convert(infoList, AdminUserRoleDTO.class);
    }

    @Override
    public Response<Boolean> saveUserRole(Long adminUserId, List<String> roleList, Long updateUserId) {
        try {
            adminUserRoleMapper.deleteByAdminUserId(adminUserId, updateUserId);
            List<AdminUserRolePO> list = new ArrayList<>();
            for (String roleCode : roleList) {
                AdminUserRolePO adminUserRolePO = new AdminUserRolePO();
                adminUserRolePO.setAdminUserId(adminUserId);
                adminUserRolePO.setRoleCode(roleCode);
                adminUserRolePO.setDataFlag(DataFlagEnum.NORMAL.getCode());
                adminUserRolePO.setCreateUserId(updateUserId);
                adminUserRolePO.setUpdateUserId(updateUserId);
                adminUserRolePO.setCreateTime(new Date());
                adminUserRolePO.setUpdateTime(new Date());
                list.add(adminUserRolePO);
            }
            return new Response<>(adminUserRoleMapper.batchInsert(list) > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response<>(Boolean.FALSE);
    }
}
