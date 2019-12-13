package com.example.museum.service.impl;

import com.example.museum.common.enums.DataFlagEnum;
import com.example.museum.common.utils.ListObjConverter;
import com.example.museum.dao.AdminRolePrivilegeMapper;
import com.example.museum.dto.AdminRolePrivilegeDTO;
import com.example.museum.dto.AdminUserPrivilegeDTO;
import com.example.museum.dto.base.Response;
import com.example.museum.po.AdminRolePrivilegePO;
import com.example.museum.po.AdminUserPrivilegePO;
import com.example.museum.service.AdminRolePrivilegeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xianjing.n
 * @date 2019-10-28 01:32
 **/
@Service
public class AdminRolePrivilegeServiceImpl implements AdminRolePrivilegeService {

    @Resource
    private AdminRolePrivilegeMapper adminRolePrivilegeMapper;


    @Override
    public Map<String, String> getRolePrivilege(String roleCode) {
        Map<String, String> privilegeMap = new HashMap<>();
        List<AdminRolePrivilegePO> rolePrivilegePOList = adminRolePrivilegeMapper.list(roleCode);
        privilegeMap = rolePrivilegePOList.stream().collect(Collectors.toMap(AdminRolePrivilegePO::getPrivilegeCode, AdminRolePrivilegePO::getPrivilegeCode, (a, b) -> b));
        return privilegeMap;
    }

    @Override
    public List<AdminRolePrivilegeDTO> getRolePrivilegeList(String roleCode) {
        List<AdminRolePrivilegePO> infoList = adminRolePrivilegeMapper.list(roleCode);
        return ListObjConverter.convert(infoList, AdminRolePrivilegeDTO.class);
    }

    @Override
    public Response<Boolean> saveRolePrivilege(String roleCode, List<String> privilegeList, Long updateUserId) {
        try {
            adminRolePrivilegeMapper.deleteByRoleCode(roleCode, updateUserId);
            List<AdminRolePrivilegePO> list = new ArrayList<>();
            for (String privilegeCode : privilegeList) {
                AdminRolePrivilegePO adminRolePrivilegePO = new AdminRolePrivilegePO();
                adminRolePrivilegePO.setRoleCode(roleCode);
                adminRolePrivilegePO.setPrivilegeCode(privilegeCode);
                adminRolePrivilegePO.setCreateUserId(updateUserId);
                adminRolePrivilegePO.setUpdateUserId(updateUserId);
                adminRolePrivilegePO.setCreateTime(new Date());
                adminRolePrivilegePO.setUpdateTime(new Date());
                adminRolePrivilegePO.setDataFlag(DataFlagEnum.NORMAL.getCode());
                list.add(adminRolePrivilegePO);
            }
            return new Response<>(adminRolePrivilegeMapper.batchInsert(list) > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response<>(Boolean.FALSE);
    }
}
