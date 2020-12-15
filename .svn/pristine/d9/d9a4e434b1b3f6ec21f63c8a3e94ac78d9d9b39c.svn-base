package com.ruoyi.framework.configure;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.Anonymous;
import com.testsys.utils.ApiResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登陆拦截器
 * @author sulanyou
 */
public class StudentLoginInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentLoginInterceptor.class);

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //打印请求参数
        LOGGER.info("请求参数：" + JSON.toJSONString(request.getParameterMap()));
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Anonymous anonymous = handlerMethod.getMethodAnnotation(Anonymous.class);
        //判断controller方法是否有免登陆注解，验证用户信息判断是否登录
        if (null == anonymous) {
            //如果用户信息为null，用户未登录
            StudentSession studentSession = StudentSession.getStudentSession(request);
            if (null == studentSession || StringUtils.isEmpty(String.valueOf(studentSession.getStudentId()))) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                try(PrintWriter out = response.getWriter()){
                    out.append(JSONObject.toJSONString(new ApiResult(403,"未登录")));
                    out.flush();
                    LOGGER.info("用户未登录，拒绝访问URL --> " + request.getRequestURL());
                }catch (IOException e){
                    e.printStackTrace();
                }
                return false;
            }
            LOGGER.info("身份验证成功，登录用户信息：" + JSON.toJSONString(studentSession));
            LOGGER.info("允许访问URL --> " + request.getRequestURI());
            return true;
        }else{
            LOGGER.info("用户免登录访问URL --> " + request.getRequestURI());
        }
        return true;
    }
}
