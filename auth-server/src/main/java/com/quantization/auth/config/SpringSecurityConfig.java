package com.quantization.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 4、创建安全配置类：指定要进行认证的用户的用户名和密码，这个用户名和密码是资源所有者的。
 *    该用户名和密码，与客户端id和密码不是一个东西。
 *    客户端的id和密码是应用系统的标识，每个应用系统就对应一个客户端ip和密码。
 *    这里指定的用户名和密码是客户的，就是每个应用系统的用户，即资源所有者。
 */
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

}
