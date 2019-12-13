package com.example.museum.web;

import com.example.museum.common.BaseWebController;
import com.example.museum.common.Constants;
import com.example.museum.dto.AdminUserDTO;
import com.example.museum.dto.base.CodeTable;
import com.example.museum.dto.base.Response;
import com.example.museum.permission.PermissionDTO;
import com.example.museum.permission.PermissionEnum;
import com.example.museum.service.AdminUserService;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Index
 *
 * @author xianjing.n
 * @date 2019-10-21 20:38
 **/
@Controller
public class IndexWebController extends BaseWebController {

    @Resource
    private AdminUserService adminUserService;

    /**
     * 登录视图
     *
     * @return
     */
    @RequestMapping({"/", "admin.html"})
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    /**
     * 登录验证
     *
     * @param numberCode 登录账号(userCode/mobile)
     * @param password
     * @param request
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public Response userVerify(String numberCode, String password, HttpServletRequest request) {
        if (StringUtils.isEmpty(numberCode) || StringUtils.isEmpty(password)) {
            return new Response(CodeTable.ERROR, "请输入账号或密码");
        }
        try {
            AdminUserDTO adminUserDTO = adminUserService.findByUserCode(numberCode);
            if (Objects.isNull(adminUserDTO)) {
                adminUserDTO = adminUserService.findByMobile(numberCode);
            }
            if (Objects.isNull(adminUserDTO)) {
                return new Response(CodeTable.CAPTCHA_ERROR);
            }
            String pwsMd5 = DigestUtils.md5Hex(password.toLowerCase());
            if (!Objects.equals(adminUserDTO.getPassword(), pwsMd5)) {
                return new Response(CodeTable.PASSWORD_ERROR);
            }
            /** 用户登录成功获取 按钮权限 **/
            List<String> privilegeList = adminUserService.findUserPrivilegeById(adminUserDTO.getId());
            if (privilegeList != null && privilegeList.size() > 0) {
                Map<String, String> maps = Maps.uniqueIndex(privilegeList, new Function<String, String>() {
                    @Override
                    public String apply(String str) {
                        return str;
                    }
                });
                request.getSession().setAttribute(Constants.SESSION_PRIVILEGE_ADMIN, maps);
            }
            request.getSession().setAttribute(Constants.SESSION_ADMIN, adminUserDTO);
            return new Response(CodeTable.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(CodeTable.EXCEPTION);
    }

    /**
     * 登录以后视图
     *
     * @return
     */
    @RequestMapping("index.html")
    public ModelAndView admin(ModelAndView mv) {
        AdminUserDTO adminUser = super.getUserToken();
        mv.addObject("adminUser", adminUser);
        mv.setViewName("index");
        return mv;
    }

    /**
     * 主体内容
     *
     * @return
     */
    @RequestMapping("console")
    public ModelAndView desktop() {
        return new ModelAndView("console");
    }

    /**
     * 主体内容
     *
     * @return
     */
    @RequestMapping("tree")
    public ModelAndView tree() {
        return new ModelAndView("tree");
    }

    /**
     * 退出
     *
     * @param mv
     * @return
     */
    @RequestMapping("logout")
    public ModelAndView logout(ModelAndView mv, HttpServletRequest request) {
        request.getSession().invalidate();
        mv.setViewName("redirect:admin.html");
        return mv;
    }
}
