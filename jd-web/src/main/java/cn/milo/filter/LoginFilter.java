package cn.milo.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

/******************************************************
 ****** @ClassName   : LoginFilter.java
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 12 08 14:30     
 ****** @version     : v1.0.x                      
 *******************************************************/
public class LoginFilter implements Filter{

    Logger log = Logger.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter init 执行了。。。。");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("filter doFilter 执行了。。。。");
        log.info("request ip = " + request.getRemoteAddr());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("filter destroy 执行了。。。。");

    }
}
