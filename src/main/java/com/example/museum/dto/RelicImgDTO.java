package com.example.museum.dto;

import lombok.Data;

import java.util.List;

/**
 * @author xianjing.n
 * @date 2019-11-28 21:58
 **/
@Data
public class RelicImgDTO {

    private String imgTypeName;


    private List<RelicScanningImgDTO> imgDTOList;

}
