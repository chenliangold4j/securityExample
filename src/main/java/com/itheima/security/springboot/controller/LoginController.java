package com.itheima.security.springboot.controller;

import com.itheima.security.springboot.properties.SecurityProperties;
import com.itheima.security.springboot.support.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
public class LoginController {

    @Autowired
    SecurityProperties securityProperties;

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 身份认证
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        SavedRequest savedRequest = requestCache.getRequest(httpServletRequest,httpServletResponse);
        if(savedRequest != null){
            String target = savedRequest.getRedirectUrl();
            System.out.println("引发跳转的请求是："+target);
            if(target.endsWith(".html")){
                redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,securityProperties.getBrowser().getLoginPage());
            }
        }
        return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页面");
    }

    /**
     * 测试资源1
     * @return
     */
    @GetMapping(value = "/r/r1",produces = {"text/plain;charset=UTF-8"})
    @PreAuthorize("hasAuthority('p1')")//拥有p1权限才可以访问
    public String r1(){
        return getUsername()+" 访问资源1";
    }

    /**
     * 测试资源2
     * @return
     */
    @GetMapping(value = "/r/r2",produces = {"text/plain;charset=UTF-8"})
    @PreAuthorize("hasAuthority('p2')")//拥有p2权限才可以访问
    public String r2(){
        return getUsername()+" 访问资源2";
    }

    //获取当前用户信息
    private String getUsername(){
        String username = null;
        //当前认证通过的用户身份
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //用户身份
        Object principal = authentication.getPrincipal();
        if(principal == null){
            username = "匿名";
        }
        if(principal instanceof org.springframework.security.core.userdetails.UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            username = userDetails.getUsername();
        }else{
            username = principal.toString();
        }
        return username;
    }
}
