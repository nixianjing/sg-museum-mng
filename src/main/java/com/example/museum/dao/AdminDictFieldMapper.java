package com.example.museum.dao;

import com.example.museum.dto.base.PageInfo;
import com.example.museum.po.AdminDictFieldPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 数据字典明细Mapper
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Repository
public interface AdminDictFieldMapper {

    /**
     * 新增
     *
     * @param record
     * @return
     */
    Long insert(AdminDictFieldPO record);

    /**
     * 根据ID查询对象
     *
     * @param id
     * @return
     */
    AdminDictFieldPO getById(Long id);

    /**
     * 修改对象
     *
     * @param record
     * @return
     */
    int update(@Param("param") AdminDictFieldPO record);

    /**
     * 总数
     *
     * @return
     */
    int pageCount(@Param("dictTypeCode") String dictTypeCode, @Param("dictName") String dictName);

    /**
     * 分页集合
     *
     * @param dictTypeCode 编码
     * @param dictName     名称
     * @return
     */
    List<AdminDictFieldPO> pageList(@Param("dictTypeCode") String dictTypeCode, @Param("dictName") String dictName, @Param("page") PageInfo page);

    /**
     * 查询所有集合
     *
     * @return
     */
    List<AdminDictFieldPO> list(@Param("dictTypeCode") String dictTypeCode, @Param("dictName") String dictName);

}