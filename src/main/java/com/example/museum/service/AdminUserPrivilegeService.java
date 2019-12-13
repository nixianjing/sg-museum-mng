package com.example.museum.service;

import com.example.museum.dto.AdminUserPrivilegeDTO;
import com.example.museum.dto.base.Response;

import java.util.List;
import java.util.Map;

/**
 * @author xianjing.n
 * @date 2019-10-28 01:31
 **/
public interface AdminUserPrivilegeService {

    /**
     * 会员对应权限
     *
     * @param adminUserId
     * @return
     */
    Map<String, String> getUserPrivilege(Long adminUserId);

    /**
     * 获取用户权限
     *
     * @param adminUserId
     * @return
     */
    List<AdminUserPrivilegeDTO> getUserPrivilegeList(Long adminUserId);

    /**
     * 添加用户权限
     *
     * @param adminUserId
     * @param privilegeList
     * @param updateUserId
     * @return
     */
    Response<Boolean> saveUserPrivilege(Long adminUserId, List<String> privilegeList, Long updateUserId);
}
