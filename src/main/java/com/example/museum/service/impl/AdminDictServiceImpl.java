package com.example.museum.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.museum.common.enums.DataFlagEnum;
import com.example.museum.common.enums.DictTypeEnum;
import com.example.museum.common.enums.StatusEnum;
import com.example.museum.common.utils.ListObjConverter;
import com.example.museum.common.utils.ObjConverter;
import com.example.museum.dao.AdminDictFieldMapper;
import com.example.museum.dao.AdminDictTypeMapper;
import com.example.museum.dto.AdminDictFieldDTO;
import com.example.museum.dto.AdminDictTypeDTO;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;
import com.example.museum.dto.base.Response;
import com.example.museum.po.AdminDictFieldPO;
import com.example.museum.po.AdminDictTypePO;
import com.example.museum.service.AdminDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 数据字典逻辑业务
 *
 * @author xianjing.n
 * @date 2019-11-05 01:08
 **/
@Service("adminDictService")
public class AdminDictServiceImpl implements AdminDictService {

    private static final Logger LOG = LoggerFactory.getLogger(AdminDictServiceImpl.class);

    @Resource
    private AdminDictTypeMapper adminDictTypeMapper;

    @Resource
    private AdminDictFieldMapper adminDictFieldMapper;

    @Override
    public List<AdminDictTypeDTO> getDictTypeList() {
        try {
            List<AdminDictTypePO> infoList = adminDictTypeMapper.dictTypeList();
            if (infoList != null) {
                return ListObjConverter.convert(infoList, AdminDictTypeDTO.class);
            }
        } catch (Exception e) {
            LOG.error("查询数据字典分类返回数据异常.param={}", e);
        }
        return null;
    }

    @Override
    public PageResult<AdminDictFieldDTO> getDictFieldPage(String dictTypeCode, String dictName, PageInfo<Object> pageInfo) {
        try {
            List<AdminDictFieldDTO> dtoList = null;
            int count = adminDictFieldMapper.pageCount(dictTypeCode, dictName);
            pageInfo.setCountNumbers(count);
            if (pageInfo.isQuery()) {
                List<AdminDictFieldPO> infoList = adminDictFieldMapper.pageList(dictTypeCode, dictName, pageInfo);
                dtoList = ListObjConverter.convert(infoList, AdminDictFieldDTO.class);
            }
            return new PageResult<>((int) (pageInfo.getQueryPage() + 1), pageInfo.getPageSize(), count, dtoList);
        } catch (Exception e) {
            LOG.error("查询数据字典分类明细返回数据异常.param={},{}", JSONObject.toJSONString(pageInfo), e);
        }
        return null;
    }

    @Override
    public Long saveField(AdminDictFieldDTO adminDictFieldDTO) {
        adminDictFieldDTO.setCreateTime(new Date());
        adminDictFieldDTO.setUpdateTime(new Date());
        adminDictFieldDTO.setStatus(StatusEnum.OPEN.getCode());
        adminDictFieldDTO.setDataFlag(DataFlagEnum.NORMAL.getCode());
        AdminDictFieldPO adminDictFieldPO = ObjConverter.convert(adminDictFieldDTO, AdminDictFieldPO.class);
        return adminDictFieldMapper.insert(adminDictFieldPO);
    }

    @Override
    public Response<Integer> updateField(AdminDictFieldDTO adminDictFieldDTO) {
        adminDictFieldDTO.setUpdateTime(new Date());
        AdminDictFieldPO adminDictFieldPO = ObjConverter.convert(adminDictFieldDTO, AdminDictFieldPO.class);
        return new Response<>(adminDictFieldMapper.update(adminDictFieldPO));
    }

    @Override
    public AdminDictFieldDTO getDictFieldById(Long id) {
        AdminDictFieldPO adminDictFieldPO = adminDictFieldMapper.getById(id);
        if (Objects.isNull(adminDictFieldPO)) {
            return null;
        }
        return ObjConverter.convert(adminDictFieldPO, AdminDictFieldDTO.class);
    }

    @Override
    public List<AdminDictFieldDTO> getDictFieldList(String dictTypeCode) {
        List<AdminDictFieldPO> infoList = adminDictFieldMapper.list(dictTypeCode, null);
        if (infoList == null) {
            return null;
        }
        return ListObjConverter.convert(infoList, AdminDictFieldDTO.class);
    }

    @Override
    public Boolean saveFieldCode(String dictTypeCode, String dictName) {
        List<AdminDictFieldPO> list = adminDictFieldMapper.list(dictTypeCode, dictName);
        if (list != null && list.size() > 0) {
            return true;
        } else {
            AdminDictFieldPO dictFieldPO = new AdminDictFieldPO();
            dictFieldPO.setDictTypeCode(dictTypeCode);
            dictFieldPO.setDictName(dictName);
            dictFieldPO.setSort(1);
            adminDictFieldMapper.insert(dictFieldPO);
        }
        return true;
    }

    @Override
    public Boolean setUpDict(DictTypeEnum dictTypeEnum, String name, Long updateUserId) {
        AdminDictTypePO adminDictTypePO = adminDictTypeMapper.getByCode(dictTypeEnum.getCode());
        if (Objects.isNull(adminDictTypePO)) {
            adminDictTypePO = new AdminDictTypePO();
            adminDictTypePO.setCode(dictTypeEnum.getCode());
            adminDictTypePO.setName(dictTypeEnum.getName());
            adminDictTypePO.setNote(dictTypeEnum.getMessage());
            adminDictTypePO.setStatus(StatusEnum.OPEN.getCode());
            adminDictTypePO.setDataFlag(DataFlagEnum.NORMAL.getCode());
            adminDictTypePO.setCreateUserId(updateUserId);
            adminDictTypePO.setUpdateUserId(updateUserId);
            adminDictTypePO.setCreateTime(new Date());
            adminDictTypePO.setUpdateTime(new Date());
            adminDictTypeMapper.insert(adminDictTypePO);
        }
        List<AdminDictFieldPO> dictFieldList = adminDictFieldMapper.list(dictTypeEnum.getCode(), name);
        if (Objects.isNull(dictFieldList) || dictFieldList.size() == 0) {
            Integer sort = 1;
            List<AdminDictFieldPO> dictFieldPOList = adminDictFieldMapper.list(dictTypeEnum.getCode(), null);
            if (Objects.nonNull(dictFieldPOList) && dictFieldPOList.size() > 0) {
                sort = dictFieldPOList.size() + 1;
            }
            AdminDictFieldPO adminDictFieldPO = new AdminDictFieldPO();
            adminDictFieldPO.setDictTypeCode(dictTypeEnum.getCode());
            adminDictFieldPO.setDictTypeName(dictTypeEnum.getName());
            adminDictFieldPO.setDictName(name);
            adminDictFieldPO.setSort(sort);
            adminDictFieldPO.setStatus(StatusEnum.OPEN.getCode());
            adminDictFieldPO.setDataFlag(DataFlagEnum.NORMAL.getCode());
            adminDictFieldPO.setCreateUserId(updateUserId);
            adminDictFieldPO.setUpdateUserId(updateUserId);
            adminDictFieldPO.setCreateTime(new Date());
            adminDictFieldPO.setUpdateTime(new Date());
            adminDictFieldMapper.insert(adminDictFieldPO);
        }
        return Boolean.TRUE;
    }
}
