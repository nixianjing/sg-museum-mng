package com.example.museum.service;

import com.example.museum.common.utils.ListObjConverter;
import com.example.museum.dto.AdminRolePrivilegeDTO;
import com.example.museum.dto.AdminUserPrivilegeDTO;
import com.example.museum.dto.base.Response;
import com.example.museum.po.AdminUserPrivilegePO;

import java.util.List;
import java.util.Map;

/**
 * @author xianjing.n
 * @date 2019-10-28 01:31
 **/
public interface AdminRolePrivilegeService {

    /**
     * 角色对应权限
     *
     * @param roleCode
     * @return
     */
    Map<String, String> getRolePrivilege(String roleCode);

    /**
     * 获取角色权限集合
     *
     * @param roleCode
     * @return
     */
    List<AdminRolePrivilegeDTO> getRolePrivilegeList(String roleCode);

    /**
     * 添加角色权限
     *
     * @param roleCode
     * @param privilegeList
     * @param updateUserId
     * @return
     */
    Response<Boolean> saveRolePrivilege(String roleCode, List<String> privilegeList, Long updateUserId);
}
