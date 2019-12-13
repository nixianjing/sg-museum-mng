package com.example.museum.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.museum.common.utils.ListObjConverter;
import com.example.museum.common.utils.ObjConverter;
import com.example.museum.dao.RelicReportLossMapper;
import com.example.museum.dto.RelicReportLossDTO;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;
import com.example.museum.po.RelicReportLossPO;
import com.example.museum.service.RelicReportLossService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author xianjing.n
 * @date 2019-12-10 20:40
 **/
@Slf4j
@Service
public class RelicReportLossServiceImpl implements RelicReportLossService {

    private static final Logger LOG = LoggerFactory.getLogger(RelicReportLossServiceImpl.class);


    @Resource
    private RelicReportLossMapper relicReportLossMapper;

    @Override
    public Boolean createRelicReportLoss(RelicReportLossDTO reportLossDTO) {
        RelicReportLossPO relicReportLossPO = ObjConverter.convert(reportLossDTO, RelicReportLossPO.class);
        relicReportLossPO.setCreateTime(new Date());
        return relicReportLossMapper.save(relicReportLossPO) > 0;
    }

    @Override
    public Boolean setReimburseStatus(Long id) {
        return relicReportLossMapper.setReimburseStatus(id, 1) > 0;
    }

    @Override
    public Boolean setRepairStatus(Long id, Long repairUserId, String repairUserName, String repairTime, String repairReason) {
        return relicReportLossMapper.setRepairStatus(id, 2, repairUserId, repairUserName, repairTime, repairReason) > 0;
    }

    @Override
    public RelicReportLossDTO getById(Long id) {
        RelicReportLossPO relicReportLossPO = relicReportLossMapper.getById(id);
        if (Objects.isNull(relicReportLossPO)) {
            return null;
        }
        return ObjConverter.convert(relicReportLossPO, RelicReportLossDTO.class);
    }

    @Override
    public PageResult<RelicReportLossDTO> pageList(PageInfo<RelicReportLossDTO> pageInfo) {
        try {
            RelicReportLossPO relicReportLossPO = ObjConverter.convert(pageInfo.getData(), RelicReportLossPO.class);
            int count = relicReportLossMapper.listCount(relicReportLossPO);
            pageInfo.setCountNumbers(count);
            List<RelicReportLossDTO> dtoList = null;
            if (pageInfo.isQuery()) {
                List<RelicReportLossPO> infoList = relicReportLossMapper.pageList(relicReportLossPO, pageInfo);
                dtoList = ListObjConverter.convert(infoList, RelicReportLossDTO.class);
            }
            return new PageResult<>((int) (pageInfo.getQueryPage() + 1), pageInfo.getPageSize(), count, dtoList);
        } catch (Exception e) {
            LOG.error("查询文物报损信息异常.param={},{}", JSONObject.toJSONString(pageInfo), e);
        }
        return null;
    }


    @Override
    public List<RelicReportLossDTO> lossByIdsList(List<Long> ids) {
        List<RelicReportLossPO> poList = relicReportLossMapper.lossByIdsList(ids);
        if (Objects.isNull(poList)) {
            return null;
        }
        return ListObjConverter.convert(poList, RelicReportLossDTO.class);
    }

    @Override
    public List<RelicReportLossDTO> lossByRelicIdList(Long relicId) {
        List<RelicReportLossPO> poList = relicReportLossMapper.lossByRelicIdList(relicId);
        if (Objects.isNull(poList)) {
            return null;
        }
        return ListObjConverter.convert(poList, RelicReportLossDTO.class);
    }
}
