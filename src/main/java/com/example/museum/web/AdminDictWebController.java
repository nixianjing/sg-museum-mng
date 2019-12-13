package com.example.museum.web;

import com.example.museum.common.BaseWebController;
import com.example.museum.common.Constants;
import com.example.museum.dto.AdminDictFieldDTO;
import com.example.museum.dto.AdminDictTypeDTO;
import com.example.museum.dto.AdminUserDTO;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;
import com.example.museum.dto.base.Response;
import com.example.museum.service.AdminDictService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xianjing.n
 * @date 2019-11-05 01:07
 **/
@Controller
@RequestMapping("adminDict")
public class AdminDictWebController extends BaseWebController {

    @Resource
    private AdminDictService adminDictService;

    @RequestMapping("openList")
    public ModelAndView list(ModelAndView mv) {
        List<AdminDictTypeDTO> typeList = adminDictService.getDictTypeList();
        mv.addObject("typeList", typeList);
        mv.setViewName("dict/list");
        return mv;
    }

    @RequestMapping("pageList")
    @ResponseBody
    public PageResult pageList(AdminDictFieldDTO adminDictFieldDTO,
                               @RequestParam(defaultValue = "1") Long page, @RequestParam(defaultValue = "10") int limit) {
        PageInfo<Object> pageInfo = new PageInfo<>(page, limit);
        PageResult<AdminDictFieldDTO> pageList = adminDictService.getDictFieldPage(adminDictFieldDTO.getDictTypeCode(), adminDictFieldDTO.getDictName(), pageInfo);
        return pageList;
    }

    @RequestMapping("add")
    public ModelAndView add(ModelAndView mv) {
        List<AdminDictTypeDTO> typeList = adminDictService.getDictTypeList();
        mv.addObject("typeList", typeList);
        mv.setViewName("dict/add");
        return mv;
    }

    @RequestMapping("edit")
    public ModelAndView edit(Long id, ModelAndView mv) {
        AdminDictFieldDTO adminDictFieldDTO = adminDictService.getDictFieldById(id);
        List<AdminDictTypeDTO> typeList = adminDictService.getDictTypeList();
        mv.addObject("typeList", typeList);
        mv.addObject("dictFieldPO", adminDictFieldDTO);
        mv.setViewName("dict/edit");
        return mv;
    }

    @RequestMapping("{type}")
    @ResponseBody
    public Response save(@PathVariable String type, AdminDictFieldDTO adminDictFieldDTO) {
        AdminUserDTO userToken = this.getUserToken();
        if (Constants.SAVE.equals(type)) {
            adminDictFieldDTO.setUpdateUserId(userToken.getId());
            adminDictFieldDTO.setCreateUserId(userToken.getId());
            Long id = adminDictService.saveField(adminDictFieldDTO);
            if (id > 0) {
                return new Response(true);
            }
        }
        if (Constants.UPDATE.equals(type)) {
            adminDictFieldDTO.setUpdateUserId(userToken.getId());
            Response<Integer> rs = adminDictService.updateField(adminDictFieldDTO);
            if (rs.isSuccess()) {
                return new Response(true);
            }
        }
        return new Response(false);
    }
}
