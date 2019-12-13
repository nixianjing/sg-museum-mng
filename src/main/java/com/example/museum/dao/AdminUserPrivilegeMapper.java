package com.example.museum.dao;

import com.example.museum.po.AdminUserPrivilegePO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 管理员对应权限Mapper
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Repository
public interface AdminUserPrivilegeMapper {

    /**
     * 批量新增
     *
     * @param list 管理员对应权限集合
     * @return
     */
    int batchInsert(@Param("list") List<AdminUserPrivilegePO> list);

    /**
     * 批量删除管理员权限
     *
     * @param ids
     * @param updateUserId
     * @param updateTime
     * @return
     */
    int batchDelete(@Param("ids") List<Long> ids, @Param("updateUserId") Long updateUserId, @Param("updateTime") Date updateTime);

    /**
     * 删除管理员权限集合
     *
     * @param adminUserId
     * @param updateUserId
     * @return
     */
    int deleteByAdminUserId(@Param("adminUserId") Long adminUserId, @Param("updateUserId") Long updateUserId);

    /**
     * 查询管理员权限集合
     *
     * @param adminUserId
     * @return
     */
    List<AdminUserPrivilegePO> list(@Param("adminUserId") Long adminUserId);
}