package com.ruoyi.framework.configure;

import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentSession {
    public static final String COOKIE_NAME = "HJZR_JWT";

    private Long studentId;
    private String wechatId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public static StudentSession getStudentSession(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(null != cookies){
            for (Cookie cookie : cookies) {
                if (COOKIE_NAME.equals(cookie.getName())) {
                    String value = cookie.getValue();
                    return StringUtils.isEmpty(value) ? null: JWTUtil.parseJWT(value);
                }
            }
        }
        return null;
    }

    /**
     * 生成token cookie
     * @param response
     */
    public void createWebToken(String domainName, HttpServletResponse response){
        Cookie cookie = new Cookie(COOKIE_NAME, JWTUtil.createJWT(this));
        cookie.setDomain(domainName);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
