package com.example.museum.dao;

import com.example.museum.po.AdminUserRolePO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 管理员对应角色Mapper
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Repository
public interface AdminUserRoleMapper {

    /**
     * 批量新增
     *
     * @param list 对象集合
     * @return
     */
    int batchInsert(@Param("list") List<AdminUserRolePO> list);

    /**
     * 删除管理员角色
     *
     * @param ids          主键集合
     * @param updateUserId 编辑用户ID
     * @param updateTime   编辑时间
     * @return
     */
    int batchDelete(@Param("ids") List<Long> ids, @Param("updateUserId") Long updateUserId, @Param("updateTime") Date updateTime);

    /**
     * 删除管理员角色
     *
     * @param adminUserId  管理员ID
     * @param updateUserId 编辑用户ID
     * @return
     */
    int deleteByAdminUserId(@Param("adminUserId") Long adminUserId, @Param("updateUserId") Long updateUserId);

    /**
     * 查询管理员角色集合
     *
     * @param adminUserId 管理员Id
     * @return
     */
    List<AdminUserRolePO> list(@Param("adminUserId") Long adminUserId);
}