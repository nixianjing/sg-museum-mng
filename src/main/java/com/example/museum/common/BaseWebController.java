package com.example.museum.common;
import com.example.museum.dto.AdminUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author xianjing.n
 * @date 2019-10-14 19:23
 **/
@Controller
public class BaseWebController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpServletRequest request;

    /**
     * 获取管理员信息
     *
     * @return
     */
    public AdminUserDTO getUserToken() {
        HttpSession session = request.getSession();
        AdminUserDTO adminUserDTO = (AdminUserDTO) session.getAttribute(Constants.SESSION_ADMIN);
        return adminUserDTO;
    }
}

