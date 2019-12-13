package com.example.museum.web;

import com.alibaba.fastjson.JSON;
import com.example.museum.common.BaseWebController;
import com.example.museum.common.Constants;
import com.example.museum.common.enums.*;
import com.example.museum.dto.AdminRoleDTO;
import com.example.museum.dto.AdminUserDTO;
import com.example.museum.dto.AdminUserRoleDTO;
import com.example.museum.dto.base.CodeTable;
import com.example.museum.dto.base.PageInfo;
import com.example.museum.dto.base.PageResult;
import com.example.museum.dto.base.Response;
import com.example.museum.permission.PermissionDTO;
import com.example.museum.permission.PermissionEnum;
import com.example.museum.service.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 管理员
 *
 * @author xianjing.n
 * @date 2019-10-24 01:37
 **/
@Controller
@RequestMapping("adminUser")
public class AdminUserWebController extends BaseWebController {

    @Resource
    private AdminUserService adminUserService;

    @Resource
    private AdminRoleService adminRoleService;

    @Resource
    private AdminUserRoleService adminUserRoleService;

    @Resource
    private AdminUserPrivilegeService adminUserPrivilegeService;

    @Resource
    private BusinessUpdateLogService businessUpdateLogService;


    /**
     * 打开列表视图
     *
     * @param mv
     * @return
     */
    @RequestMapping("openList")
    public ModelAndView list(ModelAndView mv) {
        mv.setViewName("admin/list");
        return mv;
    }

    /**
     * 分页列表
     *
     * @param adminUserDTO
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("pageList")
    @ResponseBody
    public PageResult adminList(AdminUserDTO adminUserDTO,
                                @RequestParam(defaultValue = "1") Long curPage,
                                @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {
        AdminUserDTO userToken = this.getUserToken();
        PageInfo<AdminUserDTO> pageInfo = new PageInfo<>(curPage, pageSize);
        pageInfo.setData(adminUserDTO);
        PageResult<AdminUserDTO> pageList = adminUserService.pageList(pageInfo);
        return pageList;
    }

    /**
     * 获取用户权限
     *
     * @param adminUserId 用户Id
     * @return
     */
    @RequestMapping(value = "getUserPermission")
    @ResponseBody
    public Response<List<PermissionDTO>> getPermission(Long adminUserId) {
        Map<String, String> map = adminUserPrivilegeService.getUserPrivilege(adminUserId);
        return new Response<>(PermissionEnum.getPermissionDTO(map, Boolean.TRUE));
    }

    /**
     * 管理员添加视图
     *
     * @param mv
     * @return
     */
    @RequestMapping("add")
    public ModelAndView adminAdd(ModelAndView mv) {
        List<AdminRoleDTO> roleList = adminRoleService.getRoleList(null);
        mv.addObject("roleList", roleList);
        mv.setViewName("admin/add");
        return mv;
    }

    /**
     * 管理员编辑视图
     *
     * @param userCode
     * @param mv
     * @return
     */
    @RequestMapping("edit")
    public ModelAndView adminEdit(String userCode, ModelAndView mv) {
        /** 获取角色集合 **/
        List<AdminRoleDTO> roleList = adminRoleService.getRoleList(null);
        /** 获取用户对象 **/
        AdminUserDTO adminUser = adminUserService.findByUserCode(userCode);
        /** 获取用户角色集合 **/
        List<AdminUserRoleDTO> userRoleList = adminUserRoleService.getUserRoleList(adminUser.getId());
        mv.addObject("adminUser", adminUser);
        mv.addObject("roleList", roleList);
        mv.addObject("userRoleList", JSON.toJSONString(userRoleList));
        mv.setViewName("admin/edit");
        return mv;
    }

    /**
     * 管理增删改
     *
     * @param type
     * @param adminUserDTO
     * @param ids
     * @return
     */
    @RequestMapping("{type}")
    @ResponseBody
    public Response adminSave(@PathVariable String type, String ids, AdminUserDTO adminUserDTO, String[] roleCodes, String permissionJson) {
        AdminUserDTO userToken = this.getUserToken();
        AdminUserDTO oldAdminUserDTO = adminUserService.findById(adminUserDTO.getId());
        /** 角色集合 **/
        List<String> codeList = new ArrayList<>();
        if (roleCodes != null) {
            for (String roleCode : roleCodes) {
                codeList.add(roleCode);
            }
        }
        /** 权限编号集合 **/
        List<String> permissionCodeList = PermissionEnum.getPermissionCode(permissionJson);
        /** 新增 **/
        if (Constants.SAVE.equals(type)) {
            adminUserDTO.setCreateUserId(userToken.getId());
            adminUserDTO.setUpdateUserId(userToken.getId());
            Response<Long> rs = adminUserService.save(adminUserDTO);
            /** 操作日志 **/
            businessUpdateLogService.save(BusinessTypeEnum.ADMIN_USER, BusinessUpdateTypeEnum.CREATE_CONTENT, BusinessMessageEnum.MESSAGE_USER_ADD, null, JSON.toJSONString(adminUserDTO), userToken);
            if (rs.isSuccess()) {
                Response<Boolean> userRoleRs = adminUserRoleService.saveUserRole(rs.getData(), codeList, userToken.getId());
                Response<Boolean> userPermissionRs = adminUserPrivilegeService.saveUserPrivilege(rs.getData(), permissionCodeList, userToken.getId());
                if (userRoleRs.isSuccess() && userPermissionRs.isSuccess()) {
                    return new Response<>(CodeTable.SUCCESS);
                }
            }
        }
        /** 编辑信息 **/
        if (Constants.UPDATE.equals(type)) {
            Response<Boolean> rs = adminUserService.update(adminUserDTO);
            /** 操作日志 **/
            businessUpdateLogService.save(BusinessTypeEnum.ADMIN_USER, BusinessUpdateTypeEnum.UPDATE_CONTENT, BusinessMessageEnum.MESSAGE_USER_UPDATE, JSON.toJSONString(oldAdminUserDTO), JSON.toJSONString(adminUserDTO), userToken);
            if (rs.isSuccess()) {
                Response<Boolean> userRoleRs = adminUserRoleService.saveUserRole(adminUserDTO.getId(), codeList, userToken.getId());
                Response<Boolean> userPermissionRs = adminUserPrivilegeService.saveUserPrivilege(adminUserDTO.getId(), permissionCodeList, userToken.getId());
                if (userRoleRs.isSuccess() && userPermissionRs.isSuccess()) {
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
            /** 操作日志 **/
            businessUpdateLogService.save(BusinessTypeEnum.ADMIN_USER, BusinessUpdateTypeEnum.UPDATE_CONTENT, BusinessMessageEnum.MESSAGE_USER_UPDATE_STATUS, "批量修改用户ID：" + JSON.toJSONString(adminUserIds), "Status修改为" + statusEnum.getMessage(), userToken);
            if (adminUserService.batchUpdateStatus(adminUserIds, userToken.getId(), statusEnum)) {
                return new Response(CodeTable.SUCCESS);
            }
        }
        /** 删除 **/
        if (Constants.DEL.equals(type)) {
            /** 操作日志 **/
            businessUpdateLogService.save(BusinessTypeEnum.ADMIN_USER, BusinessUpdateTypeEnum.DELETE_CONTENT, BusinessMessageEnum.MESSAGE_USER_DELETE, "批量删除用户ID：" + JSON.toJSONString(adminUserIds), null, userToken);
            if (adminUserService.batchDelete(adminUserIds, userToken.getId(), DataFlagEnum.DELETED)) {
                return new Response(CodeTable.SUCCESS);
            }
        }
        return new Response(CodeTable.EXCEPTION);
    }

    /**
     * 修改密码
     *
     * @param mv
     * @return
     */
    @RequestMapping("openRestPwd")
    public ModelAndView openRestPwd(ModelAndView mv) {
        AdminUserDTO adminUser = super.getUserToken();
        mv.addObject("adminUser", adminUser);
        mv.setViewName("user/password");
        return mv;
    }

    /**
     * 修改密码
     *
     * @param oldPassword
     * @param password
     * @return
     */
    @RequestMapping("restPwd")
    @ResponseBody
    public Response restPwd(String oldPassword, String password) {
        AdminUserDTO adminUser = super.getUserToken();
        if (StringUtils.isBlank(oldPassword)) {
            return new Response(CodeTable.ERROR, "请输入当前密码");
        }
        if (StringUtils.isBlank(password)) {
            return new Response(CodeTable.ERROR, "请输入新密码");
        }
        if (!adminUser.getPassword().equals(DigestUtils.md5Hex(oldPassword.toLowerCase()))) {
            return new Response(CodeTable.ERROR, "当前密码错误");
        }
        AdminUserDTO adminUserDTO = new AdminUserDTO();
        adminUserDTO.setId(adminUser.getId());
        adminUserDTO.setPassword(DigestUtils.md5Hex(password.toLowerCase()));
        /** 操作日志 **/
        businessUpdateLogService.save(BusinessTypeEnum.ADMIN_USER, BusinessUpdateTypeEnum.UPDATE_CONTENT, BusinessMessageEnum.MESSAGE_USER_UPDATE, JSON.toJSONString(adminUser), JSON.toJSONString(adminUserDTO), adminUser);
        return adminUserService.update(adminUserDTO);
    }
}
