package com.example.museum.common.aop;

import com.example.museum.permission.PermissionEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 切面
 *
 * @author xianjing.n
 * @date 2019-10-14 19:47
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PACKAGE, ElementType.TYPE})
public @interface PrivilegeValidateAop {

    /**
     * 是否必须校验
     *
     * @return boolean [返回类型说明]
     * @throws throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean needCheck() default true;

    /**
     * 标识被访问者的用户id位于spring控制器方法参数的坐标
     *
     * @return String [返回类型说明]
     * @throws throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    PermissionEnum[] value() default {};
}
