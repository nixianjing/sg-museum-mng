package com.example.museum.web;

import com.example.museum.common.BaseWebController;
import com.example.museum.common.Constants;
import com.example.museum.common.enums.StatusEnum;
import com.example.museum.dto.AdminRoleDTO;
import com.example.museum.dto.AdminUserDTO;
import com.example.museum.dto.base.CodeTable;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;
import com.example.museum.dto.base.Response;
import com.example.museum.permission.PermissionDTO;
import com.example.museum.permission.PermissionEnum;
import com.example.museum.service.AdminRolePrivilegeService;
import com.example.museum.service.AdminRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 角色管理
 *
 * @author xianjing.n
 * @date 2019-10-24 01:37
 **/
@Controller
@RequestMapping("adminRole")
public class AdminRoleWebController extends BaseWebController {

    @Resource
    private AdminRoleService adminRoleService;

    @Resource
    private AdminRolePrivilegeService adminRolePrivilegeService;


    /**
     * 打开列表视图
     *
     * @param mv
     * @return
     */
    @RequestMapping("openList")
    public ModelAndView list(ModelAndView mv) {
        mv.setViewName("role/list");
        return mv;
    }

    /**
     * 分页列表
     *
     * @param adminRoleDTO
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("pageList")
    @ResponseBody
    public PageResult adminList(AdminRoleDTO adminRoleDTO,
                                @RequestParam(defaultValue = "1") Long curPage,
                                @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<AdminRoleDTO> pageInfo = new PageInfo<>(curPage, pageSize);
        pageInfo.setData(adminRoleDTO);
        PageResult<AdminRoleDTO> pageList = adminRoleService.pageList(pageInfo);
        return pageList;
    }

    /**
     * 获取权限配置信息
     *
     * @return
     */
    @RequestMapping(value = "getRolePermission")
    @ResponseBody
    public Response<List<PermissionDTO>> getRolePermission(String roleCode) {
        Map<String, String> map = adminRolePrivilegeService.getRolePrivilege(roleCode);
        return new Response<>(PermissionEnum.getPermissionDTO(map, Boolean.TRUE));
    }

    /**
     * 角色添加视图
     *
     * @param mv
     * @return
     */
    @RequestMapping("add")
    public ModelAndView add(ModelAndView mv) {
        mv.setViewName("role/add");
        return mv;
    }

    /**
     * 角色编辑视图
     *
     * @param id
     * @param mv
     * @return
     */
    @RequestMapping("edit")
    public ModelAndView adminEdit(Long id, ModelAndView mv) {
        AdminRoleDTO adminRoleDTO = adminRoleService.findById(id);
        mv.addObject("adminRole", adminRoleDTO);
        mv.setViewName("role/edit");
        return mv;
    }


    /**
     * 角色增删改
     *
     * @param type
     * @param adminRoleDTO
     * @param ids
     * @return
     */
    @RequestMapping("{type}")
    @ResponseBody
    public Response adminSave(@PathVariable String type, String ids, AdminRoleDTO adminRoleDTO, String permissionJson) {
        AdminUserDTO userToken = this.getUserToken();
        /** 权限编号集合 **/
        List<String> permissionCodeList = PermissionEnum.getPermissionCode(permissionJson);
        /** 新增 **/
        if (Constants.SAVE.equals(type)) {
            adminRoleDTO.setCreateUserId(userToken.getId());
            adminRoleDTO.setUpdateUserId(userToken.getId());
            Response<String> rs = adminRoleService.save(adminRoleDTO);
            if (rs.isSuccess()) {
                Response<Boolean> rolePermissionRs = adminRolePrivilegeService.saveRolePrivilege(rs.getData(), permissionCodeList, userToken.getId());
                if (rolePermissionRs.isSuccess()) {
                    return new Response<>(CodeTable.SUCCESS);
                }
            }
        }
        /** 编辑信息 **/
        if (Constants.UPDATE.equals(type)) {
            adminRoleDTO.setUpdateUserId(userToken.getId());
            Response<Boolean> rs = adminRoleService.update(adminRoleDTO);
            if (rs.isSuccess()) {
                Response<Boolean> rolePermissionRs = adminRolePrivilegeService.saveRolePrivilege(adminRoleDTO.getRoleCode(), permissionCodeList, userToken.getId());
                if (rolePermissionRs.isSuccess()) {
                    return new Response<>(CodeTable.SUCCESS);
                }
            }
        }
        /** 批量处理ID **/
        List<Long> adminUserIds = new ArrayList<>();
        if (Objects.nonNull(ids)) {
            String[] idsSplit = ids.split(",");
            for (String id : idsSplit) {
                if (StringUtils.isNotBlank(id)) {
                    adminUserIds.add(new Long(id));
                }
            }
        }
        /** 启用 禁用 **/
        if (Constants.ENABLE.equals(type) || Constants.DISABLE.equals(type)) {
            StatusEnum statusEnum = null;
            if (Constants.ENABLE.equals(type)) {
                statusEnum = StatusEnum.OPEN;
            }
            if (Constants.DISABLE.equals(type)) {
                statusEnum = StatusEnum.CLOSE;
            }
            if (adminRoleService.batchUpdateStatus(adminUserIds, statusEnum, userToken.getId())) {
                return new Response(CodeTable.SUCCESS);
            }
        }
        /** 删除 **/
        if (Constants.DEL.equals(type)) {
            if (adminRoleService.batchDelete(adminUserIds, userToken.getId())) {
                return new Response(CodeTable.SUCCESS);
            }
        }
        return new Response(CodeTable.EXCEPTION);
    }


}
