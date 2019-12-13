package com.example.museum.web;

import com.example.museum.common.BaseWebController;
import com.example.museum.dto.AdminUserDTO;
import com.example.museum.dto.RelicStockDTO;
import com.example.museum.dto.RelicStockDetailedDTO;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;
import com.example.museum.service.RelicStockDetailedService;
import com.example.museum.service.RelicStockService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 出入库汇总
 *
 * @author xianjing.n
 * @date 2019-12-11 13:45
 **/
@Controller
@RequestMapping("relicStock")
public class RelicStockController extends BaseWebController {

    @Resource
    private RelicStockService relicStockService;

    @Resource
    private RelicStockDetailedService relicStockDetailedService;

    /**
     * 打开出入库汇总列表视图
     *
     * @param mv
     * @return
     */
    @RequestMapping("openStockList")
    public ModelAndView openStockList(ModelAndView mv) {
        mv.setViewName("relicStock/stockList");
        return mv;
    }

    /**
     * 出入库汇总分页列表
     *
     * @param relicStockDTO
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("pageStockList")
    @ResponseBody
    public PageResult pageStockList(RelicStockDTO relicStockDTO,
                                    @RequestParam(defaultValue = "1") Long curPage,
                                    @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {
        AdminUserDTO userToken = this.getUserToken();
        PageInfo<RelicStockDTO> pageInfo = new PageInfo<>(curPage, pageSize);
        pageInfo.setData(relicStockDTO);
        PageResult<RelicStockDTO> pageList = relicStockService.pageList(pageInfo);
        return pageList;
    }

    /**
     * 打开出入库名称列表视图
     *
     * @param mv
     * @return
     */
    @RequestMapping("openStockDetailedList")
    public ModelAndView openStockDetailedList(ModelAndView mv) {
        mv.setViewName("relicStock/stockDetailedList");
        return mv;
    }

    /**
     * 出入库明细分页列表
     *
     * @param relicStockDetailedDTO
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("pageStockDetailedList")
    @ResponseBody
    public PageResult pageStockDetailedList(RelicStockDetailedDTO relicStockDetailedDTO,
                                            @RequestParam(defaultValue = "1") Long curPage,
                                            @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {
        AdminUserDTO userToken = this.getUserToken();
        PageInfo<RelicStockDetailedDTO> pageInfo = new PageInfo<>(curPage, pageSize);
        pageInfo.setData(relicStockDetailedDTO);
        PageResult<RelicStockDetailedDTO> pageList = relicStockDetailedService.pageList(pageInfo);
        return pageList;
    }


}
