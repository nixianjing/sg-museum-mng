package com.example.museum.dao;

import com.example.museum.po.RelicScanningImgPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 文物修复记录图片记录Mapper
 *
 * @author xianjing.n
 * @date 2019-10-14 20:00
 **/
@Repository
public interface RelicScanningImgMapper {

    /**
     * 新增
     *
     * @param record
     * @return
     */
    Long insertSelective(RelicScanningImgPO record);

    /**
     * 批量插入
     *
     * @param list 集合
     * @return
     */
    int batchInsert(@Param("list") List<RelicScanningImgPO> list, @Param("status") Integer status, @Param("userId") Long userId, @Param("createTime") Date createTime);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    RelicScanningImgPO selectByPrimaryKey(@Param("id") Long id);

    /**
     * 编辑描述
     *
     * @param id           主键
     * @param relicExplain     描述
     * @param updateUserId 编辑人ID
     * @return
     */
    int updateDescribeById(@Param("id") Long id, @Param("relicExplain") String relicExplain, @Param("updateUserId") Long updateUserId);

    /**
     * 删除图片(逻辑删除)
     *
     * @param id           主键
     * @param updateUserId 编辑人ID
     * @return
     */
    int deleteById(@Param("id") Long id, @Param("updateUserId") Long updateUserId);

    /**
     * 查询图片集合
     *
     * @param relicScanningId 修复记录ID
     * @param type            图片类型
     * @return
     */
    List<RelicScanningImgPO> getImgByScanningId(@Param("relicScanningId") Long relicScanningId, @Param("type") String type,@Param("isImg") Integer isImg);
}