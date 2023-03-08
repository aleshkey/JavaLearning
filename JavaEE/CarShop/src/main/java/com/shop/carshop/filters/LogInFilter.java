package com.shop.carshop.filters;

import org.slf4j.Logger;
;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.SingleThreadModel;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.slf4j.LoggerFactory.getLogger;

public class LogInFilter implements Filter,
        SingleThreadModel {

    private Logger logger = getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String loggerName = filterConfig.getInitParameter("loggerName");
        logger = getLogger(getClass());
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            logger.info("request: server: {}, port: {}, URI: {}", request.getServerName(), request.getServerPort(), ((HttpServletRequest) request).getRequestURI());
        }

        if (response instanceof HttpServletResponse){
            logger.info("response: status: {}, encoding: {}", ((HttpServletResponse) response).getStatus(), response.getCharacterEncoding());
        }
        chain.doFilter(request, response);
    }


}
