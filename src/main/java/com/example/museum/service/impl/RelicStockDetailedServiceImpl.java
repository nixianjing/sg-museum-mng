package com.example.museum.service.impl;

import com.example.museum.dao.RelicStockDetailedMapper;
import com.example.museum.dto.RelicStockDetailedDTO;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;
import com.example.museum.service.RelicStockDetailedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xianjing.n
 * @date 2019-12-10 15:39
 **/
@Slf4j
@Service
public class RelicStockDetailedServiceImpl implements RelicStockDetailedService {

    @Resource
    private RelicStockDetailedMapper relicStockDetailedMapper;

    @Override
    public Boolean batchRelicStockDetailed(List<RelicStockDetailedDTO> dtoList) {
        return null;
    }

    @Override
    public int setOutStockAll(Long relicStockId, String outTime, Long userId, String userName) {
        return 0;
    }

    @Override
    public int setOutStockStatus(List<Long> ids, String outTime, Long userId, String userName) {
        return 0;
    }

    @Override
    public int setEnterStockAll(Long relicStockId, String enterTime, Long updateUserId, String updateUserName, String reason, String remarks) {
        return 0;
    }

    @Override
    public int setEnterStockStatus(List<Long> ids, String enterTime, Long updateUserId, String updateUserName, String reason, String remarks) {
        return 0;
    }

    @Override
    public PageResult<RelicStockDetailedDTO> pageList(PageInfo<RelicStockDetailedDTO> pageInfo) {
        return null;
    }

    @Override
    public List<RelicStockDetailedDTO> relicStockList(Long relicStockId) {
        return null;
    }
}
