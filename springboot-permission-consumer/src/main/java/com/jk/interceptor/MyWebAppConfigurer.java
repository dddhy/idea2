package com.jk.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebAppConfigurer
        extends WebMvcConfigurerAdapter {

    @Autowired
    private UserInteceptor userInteceptor;

   @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(userInteceptor).addPathPatterns("/**").excludePathPatterns("/user/login","/user/getCode","/test/login");


    }

/*    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个，这里选择拦截所有请求地址，进入后判断是否有加注解即可
        registry.addInterceptor(userInteceptor).addPathPatterns("/**").excludePathPatterns("/user/login","/user/getCode","/test/index","/test/login");
    }*/


}
