package com.quantization.auth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * 1、认证服务器配置类
 */
@Configuration
@EnableAuthorizationServer //2、开启认证服务器
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired // token管理方式，在TokenConfig类中已对添加到容器中了
    private TokenStore tokenStore;
	
	@Autowired
	private JwtAccessTokenConverter jwtAccessTokenConverter;

	@Autowired
	private DataSource dataSource;

	@Autowired // 刷新令牌
	private UserDetailsService customUserDetailsService;

	@Bean
	public ClientDetailsService jdbcClientDetailsService() {
		return new JdbcClientDetailsService(dataSource);
	}
	
	@Bean // 授权码管理策略
    public AuthorizationCodeServices jdbcAuthorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

	/**
	 * 3、配置被允许访问此认证服务器的客户端信息, 没有在此配置的客户端是不允许访问的
	 * 包括：内存方式和数据库方式，如下为数据库方式。
	 * @param clients
	 * @throws Exception
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(jdbcClientDetailsService());
	}
	
	/**
	 * 7、关于认证服务器端点配置
	 * 
	 * @param endpoints
	 * @throws Exception
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 刷新令牌时需要使用
        endpoints.userDetailsService(customUserDetailsService);
        // 令牌的管理方式：将令牌管理策略作用到认证服务器端点上，这样策略就会生效。
        endpoints.tokenStore(tokenStore).accessTokenConverter(jwtAccessTokenConverter);
        // 授权码管理策略 会产生的授权码放到 oauth_code 表中，如果这个授权码已经使用了，则对应这个表中的数据就会被删除
        endpoints.authorizationCodeServices(jdbcAuthorizationCodeServices());
	}
	
	/**
     * 8、令牌端点的安全配置
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 认证后可访问/oauth/token_key,默认拒绝访问
        security.tokenKeyAccess("permitAll()");
        // 认证后可访问/oauth/check_token,默认拒绝访问
        security.checkTokenAccess("isAuthenticated()");
    }
}
