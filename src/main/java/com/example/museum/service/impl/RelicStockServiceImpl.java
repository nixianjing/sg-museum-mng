package com.example.museum.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.museum.common.utils.ListObjConverter;
import com.example.museum.dao.RelicStockMapper;
import com.example.museum.dto.RelicStockDTO;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;
import com.example.museum.po.RelicStockPO;
import com.example.museum.service.RelicStockService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author xianjing.n
 * @date 2019-12-10 15:38
 **/
@Slf4j
@Service
public class RelicStockServiceImpl implements RelicStockService {

    private static final Logger LOG = LoggerFactory.getLogger(RelicStockServiceImpl.class);

    @Resource
    private RelicStockMapper relicStockMapper;

    @Override
    public Long createRelicStock(Integer stayStockNum, Long userId, String userName) {
        RelicStockPO relicStockPO = new RelicStockPO();
        relicStockPO.setStayStockNum(stayStockNum);
        relicStockPO.setUserId(userId);
        relicStockPO.setUserName(userName);
        relicStockPO.setCreateTime(new Date());
        if (relicStockMapper.save(relicStockPO) > 0) {
            return relicStockPO.getId();
        }
        return null;
    }

    @Override
    public Boolean setOutStockNum(Long id, Integer num) {
        return relicStockMapper.setOutStockNum(id, num) > 0;
    }

    @Override
    public Boolean setEnterStockNum(Long id, Integer num) {
        return relicStockMapper.setEnterStockNum(id, num) > 0;
    }

    @Override
    public PageResult<RelicStockDTO> pageList(PageInfo<RelicStockDTO> pageInfo) {
        try {
            String startTime = pageInfo.getData().getStartTime();
            String endTime = pageInfo.getData().getEndTime();
            int count = relicStockMapper.listCount(startTime,endTime);
            pageInfo.setCountNumbers(count);
            List<RelicStockDTO> dtoList = null;
            if (pageInfo.isQuery()) {
                List<RelicStockPO> infoList = relicStockMapper.pageList(startTime,endTime, pageInfo);
                dtoList = ListObjConverter.convert(infoList, RelicStockDTO.class);
            }
            return new PageResult<>((int) (pageInfo.getQueryPage() + 1), pageInfo.getPageSize(), count, dtoList);
        } catch (Exception e) {
            LOG.error("查询文物出入库信息异常.param={},{}", JSONObject.toJSONString(pageInfo), e);
        }
        return null;
    }
}
