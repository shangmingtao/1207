package cn.milo.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/******************************************************
 ****** @ClassName   : LoginInterceptor.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 12 08 14:44     
 ****** @version     : v1.0.x                      
 *******************************************************/
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String user=(String) httpServletRequest.getSession().getAttribute("user");
        String url=httpServletRequest.getRequestURI();
        System.out.println(url);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
