//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yh.common.utils;

import com.yh.common.ServiceResult;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ResultMap {
    public ResultMap() {
    }

    public static Map<String, Object> resultMap(String key, Object value) {
        Map<String, Object> map = new HashMap();
        map.put(key, value);
        return map;
    }

    public static Map objectToMap(Object obj, boolean keepNullVal) {
        if (obj == null) {
            return null;
        } else {
            Map<String, Object> map = new HashMap();

            try {
                Field[] declaredFields = obj.getClass().getDeclaredFields();
                Field[] var4 = declaredFields;
                int var5 = declaredFields.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    Field field = var4[var6];
                    field.setAccessible(true);
                    if (keepNullVal) {
                        map.put(field.getName(), field.get(obj));
                    } else if (field.get(obj) != null && !"".equals(field.get(obj).toString())) {
                        map.put(field.getName(), field.get(obj));
                    }
                }
            } catch (Exception var8) {
                var8.printStackTrace();
            }

            return map;
        }
    }

    public static Map<String, Object> ConvertObjToMap(Object obj, boolean keepNullVal) {
        Map<String, Object> reMap = new HashMap();
        if (obj == null) {
            return null;
        } else {
            List<Field> fields = new ArrayList();
            List<String> fieldsName = new ArrayList();

            for(Class tempClass = obj.getClass(); tempClass != null; tempClass = tempClass.getSuperclass()) {
                fields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            }

            List<Field> childFields = Arrays.asList(obj.getClass().getDeclaredFields());
            Iterator var7 = childFields.iterator();

            Field field;
            while(var7.hasNext()) {
                field = (Field)var7.next();
                fieldsName.add(field.getName());
            }

            try {
                var7 = fields.iterator();

                while(var7.hasNext()) {
                    field = (Field)var7.next();

                    try {
                        Field f;
                        Object o;
                        if (fieldsName.contains(field.getName())) {
                            f = obj.getClass().getDeclaredField(field.getName());
                            f.setAccessible(true);
                            o = f.get(obj);
                            if (keepNullVal) {
                                reMap.put(field.getName(), o);
                            } else if (o != null && !"".equals(o.toString())) {
                                reMap.put(field.getName(), o);
                            }
                        } else {
                            f = obj.getClass().getSuperclass().getDeclaredField(field.getName());
                            f.setAccessible(true);
                            o = f.get(obj);
                            if (keepNullVal) {
                                reMap.put(field.getName(), o);
                            } else if (o != null && !"".equals(o.toString())) {
                                reMap.put(field.getName(), o);
                            }
                        }
                    } catch (Exception var11) {
                        var11.printStackTrace();
                    }
                }
            } catch (SecurityException var12) {
                var12.printStackTrace();
            }

            return reMap;
        }
    }

    public static <T> ServiceResult<T> successResult(T data) {
        return new ServiceResult(data);
    }

    public static <T> boolean isError(ServiceResult<T> serviceResult) {
        return serviceResult == null || !serviceResult.isSucceed();
    }

    public static <T> boolean isSuccess(ServiceResult<T> serviceResult) {
        return !isError(serviceResult);
    }

    public static <T> String getErrorMessage(ServiceResult<T> serviceResult) {
        return serviceResult == null ? "未知空异常" : serviceResult.getMsg();
    }

    public static <T, K> ServiceResult<T> failResult(ServiceResult<K> serviceResult) {
        return serviceResult != null && !serviceResult.isSucceed() ? failResult(serviceResult.getCode(), serviceResult.getMsg()) : failResult("系统转换异常, 请与管理员联系");
    }

    public static ServiceResult<Void> successResult() {
        return successResult(null);
    }

    public static <T> ServiceResult<T> nullSuccessResult(String message) {
        return new ServiceResult(true, message);
    }

    public static <T> ServiceResult<T> failResult(String message) {
        return new ServiceResult(false, message);
    }

    public static <T> ServiceResult<T> failResult(int code, String message) {
        return new ServiceResult(false, code, message);
    }
}
