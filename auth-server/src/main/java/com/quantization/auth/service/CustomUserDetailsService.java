package com.quantization.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.quantization.auth.entities.SysUser;
import lombok.extern.slf4j.Slf4j;

/**
 * 5、创建 CustomUserDetailsService：查询数据库中的用户信息
 * Spring Security身份认证之UserDetailsService利用UserDetailsService的loadUserByUsername(String userName)方法
 * 查询数据库，得到User，进行填充UserDetails对象，返回UserDetails对象，
 * 然后由框架进行校验（客户端输入的用户名密码和数据库存储的进行校验）。
 */
@Component
@Slf4j
public class CustomUserDetailsService extends AbstractUserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    SysUser findSysUser(String usernameOrMobile){
    	
        log.info("请求认证的用户名：" + usernameOrMobile);
        return sysUserService.findByUsername(usernameOrMobile);
    }

}