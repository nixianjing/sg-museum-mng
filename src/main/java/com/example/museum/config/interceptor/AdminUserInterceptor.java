package com.example.museum.config.interceptor;

import com.example.museum.common.Constants;
import com.example.museum.common.aop.PrivilegeValidateAop;
import com.example.museum.permission.PermissionEnum;
import com.example.museum.dto.AdminUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 系统管理权限控制
 *
 * @author 小明
 */
public class AdminUserInterceptor extends HandlerInterceptorAdapter {

    public Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String contextPath = request.getContextPath();
        String url = request.getServletPath();
        HttpSession session = request.getSession();
        AdminUserDTO adminUser = (AdminUserDTO) session.getAttribute(Constants.SESSION_ADMIN);
        if (adminUser == null) {
            response.sendRedirect(contextPath + "/admin.html");
            return false;
        }
        response.setCharacterEncoding("UTF-8");
        String type = request.getHeader("X-Requested-With");

        //PrivilegeValidateAop Annotation，实现方法级权限控制
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            PrivilegeValidateAop permissionValidate = method.getMethodAnnotation(PrivilegeValidateAop.class);
            //如果为空在表示该方法不需要进行权限验证
            if (permissionValidate == null) {
                return super.preHandle(request, response, handler);
            }
            //验证是否具有权限
            Map<String, String> attribute = (Map<String, String>) request.getSession().getAttribute(Constants.SESSION_PRIVILEGE_ADMIN);
            if (attribute == null || attribute.size() < 1) {
                LOG.error("没有权限。。。");
                /** 没有权限，跳转到错误页，现在这个错误页没有，要写一个 **/
                response.sendRedirect(request.getContextPath() + "/permission.html");
                return false;
            }
            for (int i = 0; i < permissionValidate.value().length; i++) {
                PermissionEnum permissionEnum = permissionValidate.value()[i];
                String eqId = permissionEnum.getCode() + "";
                if (attribute.containsKey(eqId)) {
                    return super.preHandle(request, response, handler);
                }
            }
            LOG.error("没有权限。。。");
            if (type != null && type.equalsIgnoreCase("XMLHttpRequest")) {
                String jsonObject = "异步的请求这里要处理一下";
                String contentType = "application/json";
                response.setContentType(contentType);
                PrintWriter out = response.getWriter();
                out.print(jsonObject);
                out.flush();
                out.close();
                return false;
            } else {//同步请求
                request.setAttribute("permissionMsg", "没有该操作权限!");
                String returnUrl = request.getContextPath();
                /** 没有权限，跳转到错误页，现在这个错误页没有，要写一个 **/
                response.sendRedirect(returnUrl + "/permission.html");
            }
            return false;
        } else {
            return super.preHandle(request, response, handler);
        }

    }
}
