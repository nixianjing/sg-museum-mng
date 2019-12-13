package com.example.museum.common.utils.word;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xianjing.n
 * @date 2019-11-22 11:12
 **/
public class RelicMapUtils {

    public static Map<String,String> getRelicMap() {
        Map<String,String> map = new HashMap<>();
        /**
         * 文物信息
         */
        /** 文物名称 **/
        map.put("名称","relicName");
        /** 文物编号 **/
        map.put("文物号","relicNo");
        /** 年代 **/
        map.put("年代","years");
        map.put("来源","source");
        map.put("等级","grade");
        map.put("尺寸","dimensions");
        map.put("种类","relicType");
        map.put("质地","texture");
        map.put("收藏单位","collectionCompany");
        map.put("收藏时间","collectionTime");
        map.put("制造工艺","manufacture");
        map.put("织物组织","textile");

        /**
         * 修复记录信息
         */
        map.put("方案设计单位","designCompany");
        map.put("保护修复单位","repairCompany");
        map.put("方案名称及编号","planNameCode");
        map.put("批准单位及文号","companyCode");
        map.put("提取日期","extractTime");
        map.put("提取经办人","extractUserName");
        map.put("返还日期","returnTime");
        map.put("返还经办人","returnUserName");
        map.put("备注","remarks");
        /** 织物密度-经线 **/
        map.put("织物密度","densityLng");
        /** 纱线颜色-经线 **/
        map.put("纱线颜色","colourLng");
        map.put("织物密度-纬线","densityLat");
        map.put("纱线颜色-纬线","colourLat");
        map.put("纱线细度","fineness");
        map.put("纱线捻度","twist");
        map.put("纱线捻向","twistDirection");
        /** 文物描述 **/
        map.put("文字","relicExplain");
        map.put("藏品保存环境","environment");
        map.put("原保护修复情况","protect");
        map.put("病害状况描述","disease");
        return map;
    }
}
