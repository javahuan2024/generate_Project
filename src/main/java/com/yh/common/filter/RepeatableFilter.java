//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yh.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.yh.common.filter.RepeatedlyRequestWrapper;
import org.springframework.util.StringUtils;

public class RepeatableFilter implements Filter {
    public static String APPLICATION_JSON_VALUE = "application/json";

    public RepeatableFilter() {
    }

    public void init(FilterConfig filterConfig) {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        if (request instanceof HttpServletRequest && StringUtils.startsWithIgnoreCase(request.getContentType(), APPLICATION_JSON_VALUE)) {
            requestWrapper = new RepeatedlyRequestWrapper((HttpServletRequest)request, response);
        }

        if (null == requestWrapper) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapper, response);
        }

    }

    public void destroy() {
    }
}
