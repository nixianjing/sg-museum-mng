package com.example.museum.web;

import com.example.museum.common.BaseWebController;
import com.example.museum.common.enums.DictTypeEnum;
import com.example.museum.common.enums.ImgTypeEnum;
import com.example.museum.dto.*;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;
import com.example.museum.service.AdminDictService;
import com.example.museum.service.RelicScanningImgService;
import com.example.museum.service.RelicScanningService;
import com.example.museum.service.RelicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 文物信息视图
 *
 * @author xianjing.n
 * @date 2019-11-20 10:39
 **/
@Controller
@RequestMapping("relic")
public class RelicWebController extends BaseWebController {


    @Resource
    private RelicService relicService;

    @Resource
    private RelicScanningService relicScanningService;

    @Resource
    private RelicScanningImgService relicScanningImgService;

    @Resource
    private AdminDictService adminDictService;


    /**
     * 打开上传word文档压缩包
     *
     * @param mv
     * @return
     */
    @RequestMapping("openUploadZipWord")
    public ModelAndView openUploadZipWord(ModelAndView mv) {
        mv.setViewName("upload/uploadZipWord");
        return mv;
    }

    /**
     * 打开上传word文档(文物)
     *
     * @param mv
     * @return
     */
    @RequestMapping("openUploadWord")
    public ModelAndView openUploadWord(ModelAndView mv) {
        mv.setViewName("upload/uploadWord");
        return mv;
    }

    /**
     * 打开上传word文档(修复记录)
     *
     * @param mv
     * @return
     */
    @RequestMapping("openScanningUploadWord")
    public ModelAndView openScanningUploadWord(ModelAndView mv, Long relicId) {
        mv.addObject("relicId", relicId);
        mv.setViewName("upload/uploadScanningWord");
        return mv;
    }


    /**
     * 打开编辑文物信息
     *
     * @param mv
     * @return
     */
    @RequestMapping("openEditRelic")
    public ModelAndView openEditRelic(ModelAndView mv, Long relicId) {
        RelicDTO relicDTO = relicService.getRelicById(relicId);
        mv.addObject("relicDTO", relicDTO);
        mv.setViewName("relic/relicEdit");
        return mv;
    }

    /**
     * 打开详情文物信息
     *
     * @param mv
     * @return
     */
    @RequestMapping("openInfoRelic")
    public ModelAndView openInfoRelic(ModelAndView mv, Long relicId) {
        RelicDTO relicDTO = relicService.getRelicById(relicId);
        mv.addObject("relicDTO", relicDTO);
        mv.setViewName("relic/relicInfo");
        return mv;
    }


    /**
     * 打开新增文物信息
     *
     * @param mv
     * @return
     */
    @RequestMapping("openRelicAdd")
    public ModelAndView openRelicAdd(ModelAndView mv, Long relicId) {
        RelicDTO relicDTO = relicService.getRelicById(relicId);
        mv.addObject("relicDTO", relicDTO);
        mv.setViewName("relic/relicAdd");
        return mv;
    }

    /**
     * 打开编辑文物修复记录信息
     *
     * @param mv
     * @return
     */
    @RequestMapping("openEditRelicScanning")
    public ModelAndView openEditRelicScanning(ModelAndView mv, Long relicScanningId) {
        /** 图片类型 **/
        List<AdminDictFieldDTO> imgTypeList = adminDictService.getDictFieldList(DictTypeEnum.DICT_IMG_TYPE.getCode());
        RelicScanningDTO relicScanningDTO = relicScanningService.getRelicScanningById(relicScanningId);
        RelicDTO relicDTO = relicService.getRelicById(relicScanningDTO.getRelicId());
        mv.addObject("imgTypeList", imgTypeList);
        mv.addObject("relicScanningDTO", relicScanningDTO);
        mv.addObject("relicDTO", relicDTO);
        mv.setViewName("relic/relicScanningEdit");
        return mv;
    }

    /**
     * 打开详情文物修复记录信息
     *
     * @param mv
     * @return
     */
    @RequestMapping("openInfoRelicScanning")
    public ModelAndView openInfoRelicScanning(ModelAndView mv, Long relicScanningId) {
        RelicScanningDTO relicScanningDTO = relicScanningService.getRelicScanningById(relicScanningId);
        RelicDTO relicDTO = relicService.getRelicById(relicScanningDTO.getRelicId());
        /**  图片信息记录 **/
        List<RelicImgDTO> imgDTOList = new ArrayList<>();
        /**  图片类型 **/
        List<AdminDictFieldDTO> dictFieldDTOList = adminDictService.getDictFieldList("IMG_TYPE");
        /** 查询图片集合 **/
        List<RelicScanningImgDTO> imgList = relicScanningImgService.getRelicScanningImgList(relicScanningDTO.getId(), null, null);
        for (AdminDictFieldDTO adminDictFieldDTO : dictFieldDTOList) {
            RelicImgDTO relicImgDTO = new RelicImgDTO();
            relicImgDTO.setImgTypeName(adminDictFieldDTO.getDictName() + "信息");
            List<RelicScanningImgDTO> relicScanningImgDTOS = new ArrayList<>();
            for (RelicScanningImgDTO relicScanningImgDTO : imgList) {
                if (Objects.equals(relicScanningImgDTO.getType(), adminDictFieldDTO.getDictName())
                        && Objects.equals(relicScanningImgDTO.getIsImg(), ImgTypeEnum.IMG_TYPE_YES.getCode())) {
                    relicScanningImgDTOS.add(relicScanningImgDTO);
                }
            }
            if (relicScanningImgDTOS.size() > 0) {
                relicImgDTO.setImgDTOList(relicScanningImgDTOS);
            }
            imgDTOList.add(relicImgDTO);
        }
        mv.addObject("relicScanningDTO", relicScanningDTO);
        mv.addObject("relicDTO", relicDTO);
        mv.addObject("imgDTOList", imgDTOList);
        mv.setViewName("relic/relicScanningInfo");
        return mv;
    }


    /**
     * 打开浏览文物视图
     *
     * @param mv
     * @return
     */
    @RequestMapping("openBrowseRelicList")
    public ModelAndView openBrowseRelicList(ModelAndView mv) {
        mv.setViewName("relic/relicBrowseList");
        return mv;
    }


    /**
     * 浏览文物分页列表
     *
     * @param relicDTO
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("pageBrowseRelicList")
    @ResponseBody
    public PageResult pageBrowseRelicList(RelicDTO relicDTO,
                                          @RequestParam(defaultValue = "1") Long page,
                                          @RequestParam(defaultValue = "1") int limit) {
        PageInfo<RelicDTO> pageInfo = new PageInfo<>(page, limit);
        pageInfo.setData(relicDTO);
        PageResult<RelicDTO> pageList = relicService.pageList(pageInfo);
        return pageList;
    }


    /**
     * 打开列表视图
     *
     * @param mv
     * @return
     */
    @RequestMapping("openRelicList")
    public ModelAndView openRelicList(ModelAndView mv) {
        /** 年代 **/
        List<AdminDictFieldDTO> yearsList = adminDictService.getDictFieldList(DictTypeEnum.DICT_YEARS.getCode());
        /** 来源 **/
        List<AdminDictFieldDTO> sourceList = adminDictService.getDictFieldList(DictTypeEnum.DICT_SOURCE.getCode());
        /** 等级 **/
        List<AdminDictFieldDTO> gradeList = adminDictService.getDictFieldList(DictTypeEnum.DICT_GRADE.getCode());
        /** 种类 **/
        List<AdminDictFieldDTO> relicTypeList = adminDictService.getDictFieldList(DictTypeEnum.DICT_RELIC_TYPE.getCode());
        /** 质地 **/
        List<AdminDictFieldDTO> textureList = adminDictService.getDictFieldList(DictTypeEnum.DICT_TEXTURE.getCode());
        /** 制造工艺 **/
        List<AdminDictFieldDTO> manufactureList = adminDictService.getDictFieldList(DictTypeEnum.DICT_MANUFACTURE.getCode());
        /** 织物组织 **/
        List<AdminDictFieldDTO> textileList = adminDictService.getDictFieldList(DictTypeEnum.DICT_TEXTILE.getCode());
        mv.addObject("yearsList", yearsList);
        mv.addObject("sourceList", sourceList);
        mv.addObject("gradeList", gradeList);
        mv.addObject("relicTypeList", relicTypeList);
        mv.addObject("textureList", textureList);
        mv.addObject("manufactureList", manufactureList);
        mv.addObject("textileList", textileList);
        mv.setViewName("relic/relicList");
        return mv;
    }

    /**
     * 分页列表
     *
     * @param relicDTO
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("pageRelicList")
    @ResponseBody
    public PageResult pageRelicList(RelicDTO relicDTO,
                                    @RequestParam(defaultValue = "1") Long page,
                                    @RequestParam(defaultValue = "10") int limit) {
        PageInfo<RelicDTO> pageInfo = new PageInfo<>(page, limit);
        pageInfo.setData(relicDTO);
        PageResult<RelicDTO> pageList = relicService.pageList(pageInfo);
        return pageList;
    }


    /**
     * 打开列表视图
     *
     * @param mv
     * @return
     */
    @RequestMapping("openRelicScanningList")
    public ModelAndView openRelicScanningList(ModelAndView mv, Long relicId) {
        mv.addObject("relicId", relicId);
        mv.setViewName("relic/relicScanningList");
        return mv;
    }

    /**
     * 分页列表
     *
     * @param relicScanningDTO
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("pageRelicScanningList")
    @ResponseBody
    public PageResult pageRelicScanningList(RelicScanningDTO relicScanningDTO,
                                            @RequestParam(defaultValue = "1") Long curPage,
                                            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<RelicScanningDTO> pageInfo = new PageInfo<>(curPage, pageSize);
        pageInfo.setData(relicScanningDTO);
        PageResult<RelicScanningDTO> pageList = relicScanningService.pageList(pageInfo);
        return pageList;
    }


}
