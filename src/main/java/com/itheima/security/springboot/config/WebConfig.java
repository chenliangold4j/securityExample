package com.itheima.security.springboot.config;

import com.itheima.security.springboot.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Administrator
 * @version 1.0
 **/
@Configuration//就相当于springmvc.xml文件
public class WebConfig implements WebMvcConfigurer {


    @Autowired
    SecurityProperties securityProperties;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

}
