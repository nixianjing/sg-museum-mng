package com.example.museum.service;

import com.example.museum.common.enums.DataFlagEnum;
import com.example.museum.common.enums.DictTypeEnum;
import com.example.museum.common.enums.StatusEnum;
import com.example.museum.dto.AdminDictFieldDTO;
import com.example.museum.dto.AdminDictTypeDTO;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;
import com.example.museum.dto.base.Response;

import java.util.List;

/**
 * @author xianjing.n
 * @date 2019-11-05 01:08
 **/
public interface AdminDictService {

    /**
     * 数据字典类型集合
     *
     * @return
     */
    List<AdminDictTypeDTO> getDictTypeList();

    /**
     * 数据字典明细分类
     *
     * @param dictTypeCode
     * @param dictName
     * @param pageInfo
     * @return
     */
    PageResult<AdminDictFieldDTO> getDictFieldPage(String dictTypeCode, String dictName, PageInfo<Object> pageInfo);

    /**
     * 新增数据字典明细
     *
     * @param adminDictFieldDTO
     * @return
     */
    Long saveField(AdminDictFieldDTO adminDictFieldDTO);

    /**
     * 修改数据字典明细
     *
     * @param adminDictFieldDTO
     * @return
     */
    Response<Integer> updateField(AdminDictFieldDTO adminDictFieldDTO);


    /**
     * 根据ID获取数据字典明细对象
     *
     * @param id
     * @return
     */
    AdminDictFieldDTO getDictFieldById(Long id);

    /**
     * 根据分类编码获取数据字典集明细合
     *
     * @param dictTypeCode
     * @return
     */
    List<AdminDictFieldDTO> getDictFieldList(String dictTypeCode);

    /**
     * @param
     * @return
     */
    Boolean saveFieldCode(String dictTypeCode, String dictName);

    /**
     * 设置字典信息
     * @param dictTypeEnum
     * @param name
     * @return
     */
    Boolean setUpDict(DictTypeEnum dictTypeEnum,String name,Long updateUserId);
}
