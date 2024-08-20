//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package com.yh.common.utils;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

public class ServletUtils {
    public ServletUtils() {
    }

    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public static String renderString(HttpServletResponse response, String string, Integer status) {
        try {
            response.setStatus(null == status ? 200 : status);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return null;
    }
}
