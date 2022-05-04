package com.quantization.auth.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quantization.auth.entities.SysUser;

/**
 * 查询数据库中的用户信息
 */
@Component("customUserDetailsService")
public class CustomUserDetailsService extends AbstractUserDetailsService {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysUserService sysUserService;

    @Override
    SysUser findSysUser(String usernameOrMobile){
        logger.info("请求认证的用户名：" + usernameOrMobile);
        return sysUserService.findByUsername(usernameOrMobile);
    }

}