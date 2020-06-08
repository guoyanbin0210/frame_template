package com.lt.base.util;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static com.lt.base.util.DateUtils.parseDate;


public class BeanRefUtil {

    /**
     * set属性的值到Bean
     *
     * @param bean
     * @param valMap
     */
    public static Object setFieldValue(Object bean, Map<String, Object> valMap) {
        Class<?> cls = bean.getClass();
        // 取出bean里的所有方法
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();

        for (Field field : fields) {
            try {
                String fieldSetName = parSetName(field.getName());
                if (checkSetMet(methods, fieldSetName)) {
                    continue;
                }
                Method fieldSetMet = cls.getMethod(fieldSetName,
                        field.getType());
                String fieldKeyName = field.getName();
                String value = valMap.get(fieldKeyName) + "";
                if (!"".equals(value)) {
                    String fieldType = field.getType().getSimpleName();
                    if ("String".equals(fieldType)) {
                        fieldSetMet.invoke(bean, value);
                    } else if ("Date".equals(fieldType)) {
                        Date temp = DateUtils.parseDate(value);
                        fieldSetMet.invoke(bean, temp);
                    } else if ("Integer".equals(fieldType)
                            || "int".equals(fieldType)) {
                        Integer intval = Integer.parseInt(value);
                        fieldSetMet.invoke(bean, intval);
                    } else if ("Long".equalsIgnoreCase(fieldType)) {
                        Long temp = Long.parseLong(value);
                        fieldSetMet.invoke(bean, temp);
                    } else if ("Double".equalsIgnoreCase(fieldType)) {
                        Double temp = Double.parseDouble(value);
                        fieldSetMet.invoke(bean, temp);
                    } else if ("Boolean".equalsIgnoreCase(fieldType)) {
                        Boolean temp = Boolean.parseBoolean(value);
                        fieldSetMet.invoke(bean, temp);
                    } else {
                        System.out.println("not supper type" + fieldType);
                    }
                }
            } catch (Exception ignored) {
            }
        }
        return bean;
    }

    /////////////////////////////start 应用于 HashMap集合 转换 Object集合

    /**
     * BeanRefUtil.getInstanceByRequest()
     * 根据  HttpServletRequest request ， Object bean 初始化实例对象
     *
     * @param request
     * @param bean          等待操作的对象
     * @param includeFather 是否包含父类属性
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void getInstanceByRequest(HttpServletRequest request, Object bean, boolean includeFather) {
        HashMap<String, String> map = new HashMap<>();
        Class<?> cls = bean.getClass();
        //不含父类属性
        Field[] fields = cls.getDeclaredFields();
        Method[] methods = cls.getDeclaredMethods();
        foreachFields(request, bean, map, cls, fields, methods);
        //含父类属性
        if (includeFather) {
            fields = cls.getSuperclass().getDeclaredFields();
            methods = cls.getSuperclass().getDeclaredMethods();
            foreachFields(request, bean, map, cls, fields, methods);
        }
    }



    private static void foreachFields(HttpServletRequest request, Object bean, HashMap<String, String> map, Class<?> cls, Field[] fields, Method[] methods) {
        //遍历属性
        String name;
        String parameter;
        for (Field field : fields) {
            name = field.getName();
            parameter = request.getParameter(name);
            map.put(name, parameter);
        }
        //赋值
        String fieldSetName;
        String fieldKeyName;
        Method fieldSetMet;
        String value;
        String fieldType;
        Date dateTemp;
        Integer integerTemp;
        Long longTemp;
        Double doubleTemp;
        Boolean booleanTemp;
        for (Field field : fields) {
            fieldSetName = parSetName(field.getName());
            if (checkSetMet(methods, fieldSetName)) {
                continue;
            }
            fieldKeyName = field.getName();
            try {

                fieldSetMet = cls.getMethod(fieldSetName, field.getType());
                value = map.get(fieldKeyName);
                if (null != value && !"".equals(value)) {
                    fieldType = field.getType().getSimpleName();
                    if ("String".equals(fieldType)) {
                        fieldSetMet.invoke(bean, value);
                    } else if ("Date".equals(fieldType)) {
                        dateTemp = parseDate(value);
                        fieldSetMet.invoke(bean, dateTemp);
                    } else if ("Integer".equals(fieldType) || "int".equals(fieldType)) {
                        integerTemp = Integer.parseInt(value);
                        fieldSetMet.invoke(bean, integerTemp);
                    } else if ("Long".equalsIgnoreCase(fieldType)) {
                        longTemp = Long.parseLong(value);
                        fieldSetMet.invoke(bean, longTemp);
                    } else if ("Double".equalsIgnoreCase(fieldType)) {
                        doubleTemp = Double.parseDouble(value);
                        fieldSetMet.invoke(bean, doubleTemp);
                    } else if ("Boolean".equalsIgnoreCase(fieldType)) {
                        booleanTemp = Boolean.parseBoolean(value);
                        fieldSetMet.invoke(bean, booleanTemp);
                    } else {
                        System.out.println("not supper type" + fieldType);
                    }
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 判断是否存在某属性的 set方法
     *
     * @param methods
     * @param fieldSetMet
     * @return boolean
     */
    public static boolean checkSetMet(Method[] methods, String fieldSetMet) {
        for (Method met : methods) {
            String name = met.getName();

            if (fieldSetMet.equals(name)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 拼接某属性的 get方法
     *
     * @param fieldName
     * @return String
     */
    public static String parGetName(String fieldName) {
        return parSetOrGetName(fieldName, "get");
    }

    /**
     * 拼接在某属性的 set方法
     *
     * @param fieldName
     * @return String
     */
    public static String parSetName(String fieldName) {
        return parSetOrGetName(fieldName, "set");
    }

    public static String parSetOrGetName(String fieldName, String type) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_')
            startIndex = 1;
        return type + fieldName.substring(startIndex, startIndex + 1).toUpperCase() + fieldName.substring(startIndex + 1);
    }

}  