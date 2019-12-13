package com.example.museum.dao;

import com.example.museum.dto.AdminRoleDTO;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.po.AdminRolePO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 角色Mapper
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Repository
public interface AdminRoleMapper {

    /**
     * 新增
     *
     * @param record 对象
     * @return
     */
    int insert(AdminRolePO record);

    /**
     * 新增
     *
     * @param record 对象
     * @return
     */
    int insertSelective(AdminRolePO record);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    AdminRolePO getById(Long id);

    /**
     * @param id
     * @return
     */
    AdminRoleDTO findById(Long id);

    /**
     * 根据角色编号查询
     *
     * @param roleCode
     * @return
     */
    AdminRolePO getByRoleCode(String roleCode);

    /**
     * 批量删除
     *
     * @param ids
     * @param updateUserId
     * @return
     */
    int batchDelete(@Param("ids") List<Long> ids, @Param("updateUserId") Long updateUserId);

    /**
     * 批量编辑状态
     *
     * @param ids
     * @param status
     * @param updateUserId
     * @return
     */
    int batchUpdateStatus(@Param("ids") List<Long> ids, @Param("status") Integer status, @Param("updateUserId") Long updateUserId);

    /**
     * 编辑对象
     *
     * @param record
     * @return
     */
    int update(@Param("param") AdminRolePO record);

    /**
     * 批量查询角色
     *
     * @param roleCodes 角色编号集合
     * @return
     */
    List<AdminRolePO> getList(@Param("roleCodes") List<String> roleCodes);

    /**
     * 查询总记录
     *
     * @param adminRoleDTO
     * @return
     */
    int listCount(@Param("param") AdminRoleDTO adminRoleDTO);

    /**
     * 分页查询
     *
     * @param adminRoleDTO
     * @param pageInfo
     * @return
     */
    List<AdminRoleDTO> pageList(@Param("param") AdminRoleDTO adminRoleDTO, @Param("page") PageInfo pageInfo);
}