package com.example.museum.dao;

import com.example.museum.dto.base.PageInfo;
import com.example.museum.po.AdminUserPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 管理员Mapper
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Repository
public interface AdminUserMapper {

    /**
     * 新增
     *
     * @param record 对象
     * @return
     */
    Long insert(AdminUserPO record);

    /**
     * 新增
     *
     * @param record 对象
     * @return
     */
    int insertSelective(AdminUserPO record);

    /**
     * 根据ID查询
     *
     * @param id 主键
     * @return
     */
    AdminUserPO getById(Long id);

    /**
     * 根据登录账号查询
     *
     * @param userCode
     * @return
     */
    AdminUserPO getByUserCode(@Param("userCode") String userCode);

    /**
     * 根据手机号码查询
     *
     * @param mobile
     * @return
     */
    AdminUserPO getByMobile(@Param("mobile") String mobile);

    /**
     * 编辑信息
     *
     * @param record
     * @return
     */
    int update(AdminUserPO record);

    /**
     * 批量编辑状态
     *
     * @param ids
     * @param status
     * @return
     */
    int batchUpdateStatusById(@Param("ids") List<Long> ids, @Param("status") Integer status,
                              @Param("updateUserId") Long updateUserId);

    /**
     * 批量删除
     *
     * @param ids
     * @param dataFlag
     * @param updateUserId
     * @return
     */
    int batchDeleteById(@Param("ids") List<Long> ids, @Param("dataFlag") Integer dataFlag,
                        @Param("updateUserId") Long updateUserId);

    /**
     * 重置密码
     *
     * @param id
     * @param updateUserId
     * @param password
     * @return
     */
    int updatePasswordById(@Param("id") Long id, @Param("updateUserId") Long updateUserId, @Param("password") String password);

    /**
     * 查询总记录
     *
     * @param adminUserPO
     * @return
     */
    int listCount(@Param("param") AdminUserPO adminUserPO);

    /**
     * 分页查询
     *
     * @param adminUserPO
     * @param pageInfo
     * @return
     */
    List<AdminUserPO> pageList(@Param("param") AdminUserPO adminUserPO, @Param("page") PageInfo pageInfo);


}