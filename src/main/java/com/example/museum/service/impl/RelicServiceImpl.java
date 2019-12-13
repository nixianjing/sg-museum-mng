package com.example.museum.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.museum.common.enums.DictTypeEnum;
import com.example.museum.common.enums.RepairStatusEnum;
import com.example.museum.common.utils.ListObjConverter;
import com.example.museum.common.utils.ObjConverter;
import com.example.museum.dao.RelicMapper;
import com.example.museum.dto.*;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;
import com.example.museum.po.RelicPO;
import com.example.museum.po.RelicScanningWordRecordPO;
import com.example.museum.service.AdminDictService;
import com.example.museum.service.RelicScanningService;
import com.example.museum.service.RelicService;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author xianjing.n
 * @date 2019-11-23 10:57
 **/
@Service
public class RelicServiceImpl implements RelicService {

    private static final Logger LOG = LoggerFactory.getLogger(RelicServiceImpl.class);

    @Resource
    private RelicMapper relicMapper;
    @Resource
    private RelicScanningService relicScanningService;
    @Resource
    private AdminDictService adminDictService;

    @Override
    public RelicDTO getRelicById(Long relicId) {
        RelicPO relicPO = relicMapper.selectByPrimaryKey(relicId);
        if (Objects.isNull(relicPO)) {
            return null;
        }
        return ObjConverter.convert(relicPO, RelicDTO.class);
    }

    @Override
    public RelicDTO getRelicByName(String relicName) {
        RelicPO relicPO = relicMapper.selectByRelicName(relicName);
        if (Objects.isNull(relicPO)) {
            return null;
        }
        return ObjConverter.convert(relicPO, RelicDTO.class);
    }

    @Override
    public Boolean updateRelicScanningWord(Long relicId, Long relicScanningId, RelicWordDTO relicWordDTO, RelicScanningWordRecordPO wordRecordPO, Long updateUserId) {
        RelicPO relicPO = ObjConverter.convert(relicWordDTO, RelicPO.class);
        relicPO.setId(relicId);
        relicPO.setUpdateUserId(updateUserId);
        relicPO.setUpdateTime(new Date());
        if (StringUtils.isNotBlank(relicWordDTO.getYears())) {
            adminDictService.setUpDict(DictTypeEnum.DICT_YEARS, relicWordDTO.getYears(), updateUserId);
        }
        if (StringUtils.isNotBlank(relicWordDTO.getSource())) {
            adminDictService.setUpDict(DictTypeEnum.DICT_SOURCE, relicWordDTO.getSource(), updateUserId);
        }
        if (StringUtils.isNotBlank(relicWordDTO.getGrade())) {
            adminDictService.setUpDict(DictTypeEnum.DICT_GRADE, relicWordDTO.getGrade(), updateUserId);
        }
        if (StringUtils.isNotBlank(relicWordDTO.getRelicType())) {
            adminDictService.setUpDict(DictTypeEnum.DICT_RELIC_TYPE, relicWordDTO.getRelicType(), updateUserId);
        }
        if (StringUtils.isNotBlank(relicWordDTO.getTexture())) {
            adminDictService.setUpDict(DictTypeEnum.DICT_TEXTURE, relicWordDTO.getTexture(), updateUserId);
        }
        if (StringUtils.isNotBlank(relicWordDTO.getManufacture())) {
            adminDictService.setUpDict(DictTypeEnum.DICT_MANUFACTURE, relicWordDTO.getManufacture(), updateUserId);
        }
        if (StringUtils.isNotBlank(relicWordDTO.getTextile())) {
            adminDictService.setUpDict(DictTypeEnum.DICT_TEXTILE, relicWordDTO.getTextile(), updateUserId);
        }
        if (relicMapper.updateByPrimaryKeySelective(relicPO) > 0) {
            return relicScanningService.updateRelicScanningWord(relicScanningId, relicWordDTO, wordRecordPO, updateUserId);
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean updateRelicImg(Long relicId, String imgUrl, String compressImgUrl, Long adminUserId) {
        RelicPO relicPO = new RelicPO();
        relicPO.setId(relicId);
        relicPO.setImgUrl(imgUrl);
        relicPO.setCompressImgUrl(compressImgUrl);
        relicPO.setUpdateTime(new Date());
        relicPO.setUpdateUserId(adminUserId);
        return relicMapper.updateByPrimaryKeySelective(relicPO) > 0;
    }

    @Override
    public Boolean saveRelicWord(RelicWordDTO relicWordDTO, Long updateUserId) {
        RelicPO relicPO = ObjConverter.convert(relicWordDTO, RelicPO.class);
        relicPO.setCreateUserId(updateUserId);
        relicPO.setUpdateUserId(updateUserId);
        relicPO.setCreateTime(new Date());
        relicPO.setUpdateTime(new Date());
        if (StringUtils.isNotBlank(relicWordDTO.getYears())) {
            adminDictService.setUpDict(DictTypeEnum.DICT_YEARS, relicWordDTO.getYears(), updateUserId);
        }
        if (StringUtils.isNotBlank(relicWordDTO.getSource())) {
            adminDictService.setUpDict(DictTypeEnum.DICT_SOURCE, relicWordDTO.getSource(), updateUserId);
        }
        if (StringUtils.isNotBlank(relicWordDTO.getGrade())) {
            adminDictService.setUpDict(DictTypeEnum.DICT_GRADE, relicWordDTO.getGrade(), updateUserId);
        }
        if (StringUtils.isNotBlank(relicWordDTO.getRelicType())) {
            adminDictService.setUpDict(DictTypeEnum.DICT_RELIC_TYPE, relicWordDTO.getRelicType(), updateUserId);
        }
        if (StringUtils.isNotBlank(relicWordDTO.getTexture())) {
            adminDictService.setUpDict(DictTypeEnum.DICT_TEXTURE, relicWordDTO.getTexture(), updateUserId);
        }
        if (StringUtils.isNotBlank(relicWordDTO.getManufacture())) {
            adminDictService.setUpDict(DictTypeEnum.DICT_MANUFACTURE, relicWordDTO.getManufacture(), updateUserId);
        }
        if (StringUtils.isNotBlank(relicWordDTO.getTextile())) {
            adminDictService.setUpDict(DictTypeEnum.DICT_TEXTILE, relicWordDTO.getTextile(), updateUserId);
        }
        if (relicMapper.insertSelective(relicPO) > 0) {
            if (relicScanningService.saveRelicScanningWord(relicPO.getId(), relicWordDTO, updateUserId)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean setRepairStatus(Long id, RepairStatusEnum repairStatusEnum) {
        return relicMapper.setRepairStatus(id, repairStatusEnum.getCode()) > 0;
    }

    @Override
    public Boolean setStockStatus(Long id, Integer stockStatus) {
        return relicMapper.setStockStatus(id, stockStatus) > 0;
    }

    @Override
    public PageResult<RelicDTO> pageList(PageInfo<RelicDTO> pageInfo) {
        try {
            RelicPO relicPO = ObjConverter.convert(pageInfo.getData(), RelicPO.class);
            int count = relicMapper.listCount(relicPO);
            pageInfo.setCountNumbers(count);
            List<RelicDTO> dtoList = null;
            if (pageInfo.isQuery()) {
                List<RelicPO> infoList = relicMapper.pageList(relicPO, pageInfo);
                dtoList = ListObjConverter.convert(infoList, RelicDTO.class);
            }
            return new PageResult<>((int) (pageInfo.getQueryPage() + 1), pageInfo.getPageSize(), count, dtoList);
        } catch (Exception e) {
            LOG.error("查询文物信息异常.param={},{}", JSONObject.toJSONString(pageInfo), e);
        }
        return null;
    }

}
