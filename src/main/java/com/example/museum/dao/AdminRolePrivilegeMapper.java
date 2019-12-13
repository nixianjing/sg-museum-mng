package com.example.museum.dao;

import com.example.museum.po.AdminRolePrivilegePO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 角色对应权限Mapper
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Repository
public interface AdminRolePrivilegeMapper {


    /**
     * 批量新增
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<AdminRolePrivilegePO> list);

    /**
     * 删除角色权限
     *
     * @param roleCode
     * @return
     */
    int deleteByRoleCode(@Param("roleCode") String roleCode, @Param("updateUserId") Long updateUserId);

    /**
     * 查询角色权限
     *
     * @param roleCode
     * @return
     */
    List<AdminRolePrivilegePO> list(@Param("roleCode") String roleCode);

    /**
     * 查询批量角色权限
     *
     * @param roleCodes
     * @return
     */
    List<AdminRolePrivilegePO> lists(@Param("roleCodes") List<String> roleCodes);
}