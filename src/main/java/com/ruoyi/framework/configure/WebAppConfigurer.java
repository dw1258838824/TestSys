package com.ruoyi.framework.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置拦截器
 * @author sulanyou
 */
//@Configuration
public class WebAppConfigurer  extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则,excludePathPatterns 用户排除拦截
       /* registry.addInterceptor(new com.ruoyi.framework.configure.StudentLoginInterceptor())
                .excludePathPatterns("/**")
                .addPathPatterns("/api/**");
        super.addInterceptors(registry);*/
    }

}
