package com.example.museum.service.impl;

import com.example.museum.common.enums.DataFlagEnum;
import com.example.museum.common.utils.ListObjConverter;
import com.example.museum.dao.AdminRolePrivilegeMapper;
import com.example.museum.dao.AdminUserPrivilegeMapper;
import com.example.museum.dto.AdminUserPrivilegeDTO;
import com.example.museum.dto.base.Response;
import com.example.museum.po.AdminRolePrivilegePO;
import com.example.museum.po.AdminUserPrivilegePO;
import com.example.museum.service.AdminUserPrivilegeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xianjing.n
 * @date 2019-10-28 01:32
 **/
@Service
public class AdminUserPrivilegeServiceImpl implements AdminUserPrivilegeService {

    @Resource
    private AdminUserPrivilegeMapper adminUserPrivilegeMapper;

    @Override
    public Map<String, String> getUserPrivilege(Long adminUserId) {
        Map<String, String> privilegeMap = new HashMap<>();
        List<AdminUserPrivilegePO> userPrivilegePOList = adminUserPrivilegeMapper.list(adminUserId);
        privilegeMap = userPrivilegePOList.stream().collect(Collectors.toMap(AdminUserPrivilegePO::getPrivilegeCode, AdminUserPrivilegePO::getPrivilegeCode, (a, b) -> b));
        return privilegeMap;
    }

    @Override
    public List<AdminUserPrivilegeDTO> getUserPrivilegeList(Long adminUserId) {
        List<AdminUserPrivilegePO> infoList = adminUserPrivilegeMapper.list(adminUserId);
        return ListObjConverter.convert(infoList, AdminUserPrivilegeDTO.class);
    }

    @Override
    public Response<Boolean> saveUserPrivilege(Long adminUserId, List<String> privilegeList, Long updateUserId) {
        try {
            adminUserPrivilegeMapper.deleteByAdminUserId(adminUserId, updateUserId);
            List<AdminUserPrivilegePO> list = new ArrayList<>();
            for (String privilegeCode : privilegeList) {
                AdminUserPrivilegePO adminUserPrivilegePO = new AdminUserPrivilegePO();
                adminUserPrivilegePO.setAdminUserId(adminUserId);
                adminUserPrivilegePO.setPrivilegeCode(privilegeCode);
                adminUserPrivilegePO.setCreateUserId(updateUserId);
                adminUserPrivilegePO.setUpdateUserId(updateUserId);
                adminUserPrivilegePO.setCreateTime(new Date());
                adminUserPrivilegePO.setUpdateTime(new Date());
                adminUserPrivilegePO.setDataFlag(DataFlagEnum.NORMAL.getCode());
                list.add(adminUserPrivilegePO);
            }
            return new Response<>(adminUserPrivilegeMapper.batchInsert(list) > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response<>(Boolean.FALSE);
    }
}
