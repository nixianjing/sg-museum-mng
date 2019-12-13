package com.example.museum.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.museum.common.utils.ListObjConverter;
import com.example.museum.common.utils.ObjConverter;
import com.example.museum.dao.RelicScanningMapper;
import com.example.museum.dto.RelicScanningDTO;
import com.example.museum.dto.RelicWordDTO;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;
import com.example.museum.po.RelicScanningPO;
import com.example.museum.po.RelicScanningWordRecordPO;
import com.example.museum.service.RelicScanningService;
import com.example.museum.service.RelicWordRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author xianjing.n
 * @date 2019-11-24 17:52
 **/
@Service
public class RelicScanningServiceImpl implements RelicScanningService {

    private static final Logger LOG = LoggerFactory.getLogger(RelicScanningServiceImpl.class);

    @Resource
    private RelicScanningMapper relicScanningMapper;

    @Resource
    private RelicWordRecordService relicWordRecordService;


    @Override
    public RelicScanningDTO getRelicScanningById(Long relicScanningId) {
        RelicScanningPO relicScanningPO = relicScanningMapper.selectByPrimaryKey(relicScanningId);
        return ObjConverter.convert(relicScanningPO, RelicScanningDTO.class);
    }

    @Override
    public RelicScanningDTO getRelicScanningByProjectNameAndTime(Long relicId,String projectName, String projectTime) {
        RelicScanningPO relicScanningPO = relicScanningMapper.getRelicScanningByProjectNameAndTime(relicId,projectName, projectTime);
        if (Objects.isNull(relicScanningPO)) {
            return null;
        }
        return ObjConverter.convert(relicScanningPO, RelicScanningDTO.class);
    }

    @Override
    public Boolean updateRelicScanningWord(Long relicScanningId, RelicWordDTO relicWordDTO, RelicScanningWordRecordPO wordRecordPO, Long updateUserId) {
        RelicScanningPO relicScanningPO = ObjConverter.convert(relicWordDTO, RelicScanningPO.class);
        relicScanningPO.setId(relicScanningId);
        relicScanningPO.setUpdateUserId(updateUserId);
        relicScanningPO.setUpdateTime(new Date());
        if (relicScanningMapper.updateByPrimaryKeySelective(relicScanningPO) > 0) {
            wordRecordPO.setRelicScanningId(relicScanningId);
            wordRecordPO.setCreateUserId(updateUserId);
            return relicWordRecordService.createWordRecord(wordRecordPO);
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean saveRelicScanningWord(Long relicId, RelicWordDTO relicWordDTO, Long updateUserId) {
        RelicScanningPO relicScanningPO = ObjConverter.convert(relicWordDTO, RelicScanningPO.class);
        relicScanningPO.setRelicId(relicId);
        relicScanningPO.setCreateUserId(updateUserId);
        relicScanningPO.setUpdateUserId(updateUserId);
        relicScanningPO.setCreateTime(new Date());
        relicScanningPO.setUpdateTime(new Date());
        return relicScanningMapper.insertSelective(relicScanningPO) > 0;
    }

    @Override
    public PageResult<RelicScanningDTO> pageList(PageInfo<RelicScanningDTO> pageInfo) {
        try {
            RelicScanningPO relicScanningPO = ObjConverter.convert(pageInfo.getData(), RelicScanningPO.class);
            int count = relicScanningMapper.listCount(relicScanningPO);
            pageInfo.setCountNumbers(count);
            List<RelicScanningDTO> dtoList = new ArrayList<>();
            if (pageInfo.isQuery()) {
                List<RelicScanningPO> infoList = relicScanningMapper.pageList(relicScanningPO, pageInfo);
                dtoList = ListObjConverter.convert(infoList, RelicScanningDTO.class);
            }
            return new PageResult<>((int) (pageInfo.getQueryPage() + 1), pageInfo.getPageSize(), count, dtoList);
        } catch (Exception e) {
            LOG.error("查询文物修复记录信息异常.param={},{}", JSONObject.toJSONString(pageInfo), e);
        }
        return null;
    }
}
