package com.example.museum.common.utils;

import com.example.museum.po.RelicPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 小明
 * @Type ObjConverter
 * @Desc 两个对象之间必须有对称的get和set方法，不适用于复杂类型
 * @date 2015年07月17日
 */
public class ObjConverter {

    private static Logger LOG = LoggerFactory.getLogger(ObjConverter.class);

    public static <T> T convert(Object source, Class<T> clazz, ForceMatch... fMatchs) {
        T target = null;
        try {
            if (source == null) {
                return target;
            }
            target = clazz.newInstance();
            convert(source, target, fMatchs);
        } catch (Exception e) {
            if (LOG.isErrorEnabled()) LOG.error("对象转换异常", e, source, target);
            throw new RuntimeException(e);
        }
        return target;
    }

    public static <T> void convert(Object source, T target, ForceMatch... fMatchs) {
        try {
            if (source == null || target == null) {
                return;
            }
            Method[] methods = target.getClass().getMethods();
            for (Method tarMethod : methods) {
                String tarMethodName = tarMethod.getName();
                Method souMethod = getSourceMethod(tarMethodName, source.getClass(), fMatchs);
                if (souMethod != null) {
                    try {
                        tarMethod.invoke(target, souMethod.invoke(source));
                    } catch (Exception e) {
                        if (LOG.isErrorEnabled())
                            LOG.error("对象转换异常", e, source, target, souMethod.getName(), tarMethod.getName());
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (Exception e) {
            if (LOG.isErrorEnabled()) LOG.error("对象转换异常", e, source, target);
            throw new RuntimeException(e);
        }
    }

    private static Method getSourceMethod(String tarMethodName, Class<?> souClazz, ForceMatch... fMatchs)
            throws Exception {
        Method souMethod = null;
        if (tarMethodName.startsWith("set")) {
            try {
                souMethod = souClazz.getMethod(tarMethodName.replaceFirst("s", "g"));
            } catch (NoSuchMethodException e) {
                // 强制匹配
                String tarFieldName = getFieldName(tarMethodName);
                for (ForceMatch fMatch : fMatchs) {
                    String souMethodName = null;
                    if (tarFieldName.equals(fMatch.getFieldx())) {
                        souMethodName = getGetMethodName(fMatch.getFieldy());
                    } else if (tarFieldName.equals(fMatch.getFieldy())) {
                        souMethodName = getGetMethodName(fMatch.getFieldx());
                    }
                    if (souMethodName != null) {
                        try {
                            souMethod = souClazz.getMethod(souMethodName);
                        } catch (NoSuchMethodException e1) {
                            if (LOG.isErrorEnabled()) LOG.error("对象转换失败，强制匹配参数异常", e, tarMethodName, souClazz, fMatchs);
                            throw e;
                        }
                        break;
                    }
                }
            }
        }
        return souMethod;
    }

    /**
     * 根据字段名字获取get方法名
     *
     * @param fieldName
     * @return
     */
    private static String getGetMethodName(String fieldName) {
        fieldName = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
        return "get" + fieldName;
    }

    /**
     * 根据方法名返回字段名
     *
     * @param methodName
     * @return
     */
    private static String getFieldName(String methodName) {
        String fieldName = methodName.substring(3, methodName.length());
        return Character.toLowerCase(fieldName.charAt(0)) + fieldName.substring(1);
    }

    public static class ForceMatch {

        private String fieldx;
        private String fieldy;

        public ForceMatch(String fieldx, String fieldy) {
            this.fieldx = fieldx;
            this.fieldy = fieldy;
        }

        public String getFieldx() {
            return fieldx;
        }

        public void setFieldx(String fieldx) {
            this.fieldx = fieldx;
        }

        public String getFieldy() {
            return fieldy;
        }

        public void setFieldy(String fieldy) {
            this.fieldy = fieldy;
        }

    }

    // Map 键值对转换
    public static <T> T getMapConvertRelicWordDTO(Map<String, String> map, T t, Class<T> clazz) {
        Field[] f = clazz.getDeclaredFields();
        //给test对象赋值
        for (int i = 0; i < f.length; i++) {
            String type = f[i].getGenericType().getTypeName();
            String attributeName = f[i].getName();
            //将属性名的首字母变为大写，为执行set/get方法做准备
            String methodName = attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
            try {
                //获取Test类当前属性的setXXX方法（私有和公有方法）
                /*Method setMethod=Test.class.getDeclaredMethod("set"+methodName);*/
                //获取Test类当前属性的setXXX方法（只能获取公有方法）
                Method setMethod = null;
                if (Objects.equals("java.lang.String", type)) {
                    setMethod = clazz.getMethod("set" + methodName, String.class);
                }
                if (Objects.equals("java.lang.Long", type)) {
                    setMethod = clazz.getMethod("set" + methodName, Long.class);
                }
                if (Objects.equals("java.lang.Integer", type)) {
                    setMethod = clazz.getMethod("set" + methodName, Integer.class);
                }
                if (Objects.equals("int", type)) {
                    setMethod = clazz.getMethod("set" + methodName, int.class);
                }
                if (Objects.equals("java.util.Date", type)) {
                    setMethod = clazz.getMethod("set" + methodName, Date.class);
                }
                //执行该set方法
                Object objValue = map.get(attributeName);
                if (Objects.nonNull(objValue)) {
                    setMethod.invoke(t, objValue);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return t;
    }
}
