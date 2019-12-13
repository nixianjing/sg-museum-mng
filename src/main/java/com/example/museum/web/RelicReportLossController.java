package com.example.museum.web;

import com.example.museum.common.BaseWebController;
import com.example.museum.common.enums.RepairStatusEnum;
import com.example.museum.dto.AdminUserDTO;
import com.example.museum.dto.RelicReportLossDTO;
import com.example.museum.dto.base.CodeTable;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;
import com.example.museum.dto.base.Response;
import com.example.museum.service.RelicReportLossService;
import com.example.museum.service.RelicService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 报损记录
 *
 * @author xianjing.n
 * @date 2019-12-11 13:46
 **/
@Controller
@RequestMapping("relicReportLoss")
public class RelicReportLossController extends BaseWebController {

    @Resource
    private RelicReportLossService relicReportLossService;

    @Resource
    private RelicService relicService;


    /**
     * 列表视图
     *
     * @param mv
     * @return
     */
    @RequestMapping("openReportList")
    public ModelAndView openReportList(ModelAndView mv) {
        mv.setViewName("relicReport/reportList");
        return mv;
    }

    /**
     * 分页列表
     *
     * @param relicReportLossDTO
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("pageReportList")
    @ResponseBody
    public PageResult pageReportList(RelicReportLossDTO relicReportLossDTO,
                                     @RequestParam(defaultValue = "1") Long curPage,
                                     @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {
        AdminUserDTO userToken = this.getUserToken();
        PageInfo<RelicReportLossDTO> pageInfo = new PageInfo<>(curPage, pageSize);
        pageInfo.setData(relicReportLossDTO);
        PageResult<RelicReportLossDTO> pageList = relicReportLossService.pageList(pageInfo);
        return pageList;
    }

    /**
     * 详情
     *
     * @param mv
     * @return
     */
    @RequestMapping("openInfoReport")
    public ModelAndView openInfoReport(ModelAndView mv, Long id) {
        RelicReportLossDTO relicReportLossDTO = relicReportLossService.getById(id);
        mv.addObject("relicReport", relicReportLossDTO);
        mv.setViewName("relicReport/reportInfo");
        return mv;
    }

    /**
     * 设置修复中
     *
     * @param id 主键
     * @return
     */
    @RequestMapping("setRestore")
    @ResponseBody
    public Response setRestore(Long id) {
        AdminUserDTO adminUser = super.getUserToken();
        RelicReportLossDTO relicReportLossDTO = relicReportLossService.getById(id);
        if (!Objects.equals(relicReportLossDTO.getStatus(), RepairStatusEnum.REPAIR_STATUS_STAY.getCode())) {
            return new Response(CodeTable.ERROR, "设置修复失败! 该文物不是待修复");
        }
        if (Objects.equals(relicReportLossDTO.getStatus(), RepairStatusEnum.REPAIR_STATUS_COMPLETE.getCode())) {
            return new Response(CodeTable.ERROR, "设置修复失败! 该文物已修复完成");
        }
        if (Objects.equals(relicReportLossDTO.getStatus(), RepairStatusEnum.REPAIR_STATUS_IN.getCode())) {
            return new Response(CodeTable.SUCCESS, "设置成功");
        }
        Boolean status = relicReportLossService.setReimburseStatus(id);
        Boolean repairStatus = relicService.setRepairStatus(relicReportLossDTO.getRelicId(), RepairStatusEnum.REPAIR_STATUS_IN);
        if (status && repairStatus) {
            return new Response(CodeTable.SUCCESS, "设置成功");
        }
        return new Response(CodeTable.ERROR, "设置修复失败! 请稍候重试");
    }

    /**
     * 打开修复完成视图
     *
     * @param mv
     * @return
     */
    @RequestMapping("openSetRestoreComplete")
    public ModelAndView openSetRestoreComplete(ModelAndView mv, Long id) {
        RelicReportLossDTO relicReportLossDTO = relicReportLossService.getById(id);
        mv.addObject("relicReport", relicReportLossDTO);
        mv.setViewName("relicReport/setReport");
        return mv;
    }

    /**
     * 设置修复完成
     *
     * @param id           主键
     * @param repairTime   修复完成时间
     * @param repairReason 修复完成说明
     * @return
     */
    @RequestMapping("setRestoreComplete")
    @ResponseBody
    public Response setRestoreComplete(Long id, String repairTime, String repairReason) {
        AdminUserDTO adminUser = super.getUserToken();
        if (Objects.isNull(id) || StringUtils.isBlank(repairTime)) {
            return new Response(CodeTable.ERROR, "请输入修复完成时间");
        }
        RelicReportLossDTO relicReportLossDTO = relicReportLossService.getById(id);
        if (!Objects.equals(relicReportLossDTO.getStatus(), RepairStatusEnum.REPAIR_STATUS_IN.getCode())) {
            return new Response(CodeTable.ERROR, "设置修复失败! 该文物不是修复中");
        }
        if (Objects.equals(relicReportLossDTO.getStatus(), RepairStatusEnum.REPAIR_STATUS_COMPLETE.getCode())) {
            return new Response(CodeTable.SUCCESS, "设置修复完成成功");
        }
        if (Objects.equals(relicReportLossDTO.getStatus(), RepairStatusEnum.REPAIR_STATUS_STAY.getCode())) {
            return new Response(CodeTable.ERROR, "设置修复失败! 该文物还是待修复");
        }
        Boolean status = relicReportLossService.setRepairStatus(id, adminUser.getId(), adminUser.getName(), repairTime, repairReason);
        Boolean repairStatus = relicService.setRepairStatus(relicReportLossDTO.getRelicId(), RepairStatusEnum.REPAIR_STATUS_COMPLETE);
        if (status && repairStatus) {
            return new Response(CodeTable.SUCCESS, "设置修复完成成功");
        }
        return new Response(CodeTable.ERROR, "设置修复完成失败! 请稍候重试");
    }
}
