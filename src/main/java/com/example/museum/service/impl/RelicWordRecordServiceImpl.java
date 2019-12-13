package com.example.museum.service.impl;

import com.example.museum.common.utils.ListObjConverter;
import com.example.museum.common.utils.ObjConverter;
import com.example.museum.dao.RelicScanningWordRecordMapper;
import com.example.museum.dto.RelicWordRecordDTO;
import com.example.museum.po.RelicScanningWordRecordPO;
import com.example.museum.service.RelicWordRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author xianjing.n
 * @date 2019-11-24 17:51
 **/
@Service
public class RelicWordRecordServiceImpl implements RelicWordRecordService {

    @Resource
    private RelicScanningWordRecordMapper wordRecordMapper;

    @Override
    public Boolean createWordRecord(RelicScanningWordRecordPO relicScanningWordRecordPO) {
        relicScanningWordRecordPO.setCreateTime(new Date());
        return wordRecordMapper.insertSelective(relicScanningWordRecordPO) > 0;
    }

    @Override
    public RelicWordRecordDTO getRelicWordRecord(Long wordRecordId) {
        RelicScanningWordRecordPO relicScanningWordRecordPO = wordRecordMapper.selectByPrimaryKey(wordRecordId);
        return ObjConverter.convert(relicScanningWordRecordPO, RelicWordRecordDTO.class);
    }

    @Override
    public List<RelicWordRecordDTO> getWordRecordList(Long relicScanningId) {
        List<RelicScanningWordRecordPO> poList = wordRecordMapper.getRecordByScanningId(relicScanningId);
        return ListObjConverter.convert(poList, RelicWordRecordDTO.class);
    }
}
