package com.example.museum.common.tag;

import com.example.museum.common.Constants;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.jsoup.helper.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @author xianjing.n
 * @date 2019-10-14 20:06
 **/
@Component("privilegeTag")
public class PrivilegeTag implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] model, TemplateDirectiveBody body)
            throws TemplateException, IOException {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();

        Map<String, String> roleObj = (Map<String, String>) request.getSession().getAttribute(Constants.SESSION_PRIVILEGE_ADMIN);
        Object menuObj = params.get("token");
        String menuId = (menuObj == null ? "" : menuObj.toString());
        if (null == roleObj) {
            //没有权限信息，不展示按钮
            env.getOut().write("");
            return;
        }

        //是否有此菜单权限
        if (menuId.indexOf(",") != -1) {
            String menuIds[] = menuId.split(",");
            for (String str : menuIds) {
                if (!StringUtil.isBlank(str) && roleObj.containsKey(str)) {
                    if (body != null) {
                        body.render(env.getOut());
                        break;
                    }
                } else {
                    env.getOut().write("");
                }
            }
        }
    }
}
