package com.example.museum.web;

import com.example.museum.common.BaseWebController;
import com.example.museum.common.Constants;
import com.example.museum.dto.AdminUserDTO;
import com.example.museum.dto.BusinessUpdateLogsDTO;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;
import com.example.museum.service.BusinessUpdateLogService;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 业务日志管理
 *
 * @author xianjing.n
 * @date 2019-10-27 01:03
 **/
@Controller
@RequestMapping("businessLog")
public class BusinessLogWebController extends BaseWebController {

    @Resource
    private BusinessUpdateLogService businessUpdateLogService;

    /**
     * 列表视图
     *
     * @param mv
     * @return
     */
    @RequestMapping("openList")
    public ModelAndView list(ModelAndView mv) {
        mv.setViewName("businessLog/list");
        return mv;
    }

    /**
     * 分页列表
     *
     * @param businessUpdateLogsDTO
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("pageList")
    @ResponseBody
    public PageResult adminList(BusinessUpdateLogsDTO businessUpdateLogsDTO,
                                @RequestParam(defaultValue = "1") Long curPage,
                                @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {
        PageInfo<BusinessUpdateLogsDTO> pageInfo = new PageInfo<>(curPage, pageSize);
        pageInfo.setData(businessUpdateLogsDTO);
        PageResult<BusinessUpdateLogsDTO> pageList = businessUpdateLogService.pageList(pageInfo);
        return pageList;
    }

    /**
     * 详情视图
     *
     * @param mv
     * @return
     */
    @RequestMapping("openEdit")
    public ModelAndView openEdit(ModelAndView mv, Long id) {
        BusinessUpdateLogsDTO businessUpdateLogsDTO = businessUpdateLogService.getById(id);
        mv.addObject("businessDTO", businessUpdateLogsDTO);
        mv.setViewName("businessLog/info");
        return mv;
    }
}
