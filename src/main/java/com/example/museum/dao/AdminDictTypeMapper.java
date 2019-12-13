package com.example.museum.dao;

import com.example.museum.dto.base.PageInfo;
import com.example.museum.po.AdminDictTypePO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据字典类型Mapper
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Repository
public interface AdminDictTypeMapper {

    /**
     * 新增
     * @param adminDictTypePO
     * @return
     */
    int insert(AdminDictTypePO adminDictTypePO);

    /**
     * 根据ID查询对象
     * @param id
     * @return
     */
    AdminDictTypePO getById(Long id);

    /**
     * 根据编号查询
     * @param code
     * @return
     */
    AdminDictTypePO getByCode(String code);

    /**
     * 总数
     *
     * @return
     */
    int pageCount(@Param("code") String code, @Param("name") String name);

    /**
     * 分页集合
     *
     * @param code 编码
     * @param name 名称
     * @return
     */
    List<AdminDictTypePO> pageList(@Param("code") String code, @Param("name") String name, @Param("page") PageInfo page);

    /**
     * 查询所有
     * @return
     */
    List<AdminDictTypePO> dictTypeList();
}