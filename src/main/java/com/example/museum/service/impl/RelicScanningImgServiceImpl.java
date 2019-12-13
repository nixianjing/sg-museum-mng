package com.example.museum.service.impl;

import com.example.museum.common.utils.DateUtil;
import com.example.museum.common.utils.FileUtils;
import com.example.museum.common.utils.ListObjConverter;
import com.example.museum.common.utils.ObjConverter;
import com.example.museum.dao.RelicMapper;
import com.example.museum.dao.RelicScanningImgMapper;
import com.example.museum.dto.RelicScanningImgDTO;
import com.example.museum.po.RelicPO;
import com.example.museum.po.RelicScanningImgPO;
import com.example.museum.service.RelicScanningImgService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author xianjing.n
 * @date 2019-11-24 00:48
 **/
@Service
public class RelicScanningImgServiceImpl implements RelicScanningImgService {

    @Value("${relic.del.path}")
    private String relicDelPath;

    @Resource
    private RelicScanningImgMapper relicScanningImgMapper;

    @Resource
    private RelicMapper relicMapper;

    @Override
    public Boolean saveRelicScanningImg(RelicScanningImgDTO relicScanningImgDTO) {
        RelicScanningImgPO relicScanningImgPO = ObjConverter.convert(relicScanningImgDTO, RelicScanningImgPO.class);
        relicScanningImgPO.setStatus(0);
        relicScanningImgPO.setCreateTime(new Date());
        relicScanningImgPO.setUpdateTime(new Date());
        return relicScanningImgMapper.insertSelective(relicScanningImgPO) > 0;
    }

    @Override
    public Boolean batchRelicScanningImg(List<RelicScanningImgDTO> dtoList, Long createUserId) {
        List<RelicScanningImgPO> poList = ListObjConverter.convert(dtoList, RelicScanningImgPO.class);
        return relicScanningImgMapper.batchInsert(poList, 0, createUserId, new Date()) > 0;
    }

    @Override
    public Boolean updateImgDescribe(Long imgId, String relicExplain, Long updateUserId) {
        return relicScanningImgMapper.updateDescribeById(imgId, relicExplain, updateUserId) > 0;
    }

    @Override
    public Boolean updateImgUrl(Long imgId, String imgName, String imgUrl, Long updateUserId) {
        return null;
    }

    @Override
    public Boolean deleteImg(Long relicId, Long imgId, Long updateUserId) {
        RelicScanningImgPO relicScanningImgPO = relicScanningImgMapper.selectByPrimaryKey(imgId);
        RelicPO relicPO = relicMapper.selectByPrimaryKey(relicId);
        /** 删除以后图片的存放路径 **/
        String imgPath = relicDelPath + relicPO.getRelicName() + "/" + relicScanningImgPO.getType() + "/";
        String imgName = DateUtil.dateFormat(new Date(), DateUtil.F_DATE_YYYYMMDDHHMMSS) + "_" + relicScanningImgPO.getImgName();
        String imgUrl = imgPath + imgName;
        if (FileUtils.transferFile(relicScanningImgPO.getImgUrl(), imgPath, imgName)) {
            /** 删除原有文件 **/
            FileUtils.deleteFile(relicScanningImgPO.getImgUrl());
            return relicScanningImgMapper.deleteById(imgId, updateUserId) > 0;
        }
        return Boolean.FALSE;
    }

    @Override
    public List<RelicScanningImgDTO> getRelicScanningImgList(Long relicScanningId, String type,Integer isImg) {
        List<RelicScanningImgPO> poList = relicScanningImgMapper.getImgByScanningId(relicScanningId, type,isImg);
        return ListObjConverter.convert(poList, RelicScanningImgDTO.class);
    }

    @Override
    public RelicScanningImgDTO getRelicScanningImg(Long relicScanningId, String type, String imgName) {
        return null;
    }
}
