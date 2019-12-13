package com.example.museum.service;

import com.example.museum.common.enums.DataFlagEnum;
import com.example.museum.common.enums.StatusEnum;
import com.example.museum.dto.AdminUserDTO;
import com.example.museum.dto.AdminUserRoleDTO;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;
import com.example.museum.dto.base.Response;
import com.example.museum.po.AdminUserRolePO;

import java.util.List;
import java.util.Map;

/**
 * 管理员Service
 *
 * @author xianjing.n
 * @date 2019-10-21 19:34
 **/
public interface AdminUserService {

    /**
     * 根据账号查询
     *
     * @param userCode
     * @return
     */
    AdminUserDTO findByUserCode(String userCode);

    /**
     * 根据手机号码查询
     *
     * @param mobile
     * @return
     */
    AdminUserDTO findByMobile(String mobile);


    /**
     * 根据手机号码查询
     *
     * @param id
     * @return
     */
    AdminUserDTO findById(Long id);

    /**
     * 获取用户权限
     *
     * @param adminUserId
     * @return
     */
    List<String> findUserPrivilegeById(Long adminUserId);


    /**
     * 新增
     *
     * @param adminUserDTO
     * @return
     */
    Response<Long> save(AdminUserDTO adminUserDTO);


    /**
     * 编辑
     *
     * @param adminUserDTO
     * @return
     */
    Response<Boolean> update(AdminUserDTO adminUserDTO);

    /**
     * 编辑状态
     *
     * @param adminUserIds 用户ID集合
     * @param updateUserId 编辑用户ID
     * @param statusEnum   状态枚举 {@link StatusEnum}
     * @return
     */
    Boolean batchUpdateStatus(List<Long> adminUserIds, Long updateUserId, StatusEnum statusEnum);

    /**
     * 批量删除
     *
     * @param adminUserIds 用户ID集合
     * @param updateUserId 编辑用户ID
     * @param dataFlagEnum 数据状态 {@link DataFlagEnum}
     * @return
     */
    Boolean batchDelete(List<Long> adminUserIds, Long updateUserId, DataFlagEnum dataFlagEnum);

    /**
     * 重置密码
     *
     * @param adminUserId  用户ID集合
     * @param updateUserId 编辑用户ID
     * @param password     密码(明文)
     * @return
     */
    Boolean updatePasswordById(Long adminUserId, Long updateUserId, String password);

    /**
     * 分页查询
     *
     * @param pageInfo
     * @return
     */
    PageResult<AdminUserDTO> pageList(PageInfo<AdminUserDTO> pageInfo);

}
