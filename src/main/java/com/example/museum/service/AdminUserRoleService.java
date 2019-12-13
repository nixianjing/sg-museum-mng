package com.example.museum.service;

import com.example.museum.dto.AdminUserRoleDTO;
import com.example.museum.dto.base.Response;

import java.util.List;

/**
 * 用户对应角色
 *
 * @author xianjing.n
 * @date 2019-10-28 00:08
 **/
public interface AdminUserRoleService {

    /**
     * 根据用户ID查询用户对应角色
     *
     * @param adminUserId
     * @return
     */
    List<AdminUserRoleDTO> getUserRoleList(Long adminUserId);

    /**
     * 新增用户对应角色
     * @param adminUserId
     * @param roleList
     * @return
     */
    Response<Boolean> saveUserRole(Long adminUserId, List<String> roleList, Long updateUserId);
}
