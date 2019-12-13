package com.example.museum.service;

import com.example.museum.common.enums.StatusEnum;
import com.example.museum.dto.AdminRoleDTO;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;
import com.example.museum.dto.base.Response;

import java.util.List;

/**
 * 角色Service
 *
 * @author xianjing.n
 * @date 2019-10-21 19:36
 **/
public interface AdminRoleService {

    /**
     * 查询角色
     *
     * @param id
     * @return
     */
    AdminRoleDTO findById(Long id);

    /**
     * 新增
     *
     * @param adminRoleDTO
     * @return
     */
    Response<String> save(AdminRoleDTO adminRoleDTO);


    /**
     * 编辑
     *
     * @param adminRoleDTO
     * @return
     */
    Response<Boolean> update(AdminRoleDTO adminRoleDTO);

    /**
     * 批量修改状态
     *
     * @param ids
     * @param statusEnum
     * @param updateUserId
     * @return
     */
    Boolean batchUpdateStatus(List<Long> ids, StatusEnum statusEnum, Long updateUserId);

    /**
     * 批量删除
     *
     * @param ids
     * @param updateUserId
     * @return
     */
    Boolean batchDelete(List<Long> ids, Long updateUserId);


    /**
     * 获取所有角色
     *
     * @param roleList
     * @return
     */
    List<AdminRoleDTO> getRoleList(List<String> roleList);


    /**
     * 分页查询
     *
     * @param pageInfo
     * @return
     */
    PageResult<AdminRoleDTO> pageList(PageInfo<AdminRoleDTO> pageInfo);
}
