package com.quantization.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * 7、token管理策略配置：jdbc/redis/jwt
 */
@Configuration
public class TokenConfig {

    /**
     * jwt方式管理令牌，另外还有redis、jdbc方式
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }
    
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 采用非对称加密jwt：第1个参数就是密钥证书文件，第2个参数 密钥库口令, 私钥进行签名
        KeyStoreKeyFactory factory = new KeyStoreKeyFactory(
                new ClassPathResource("oauth2.jks"), "oauth2".toCharArray());
        converter.setKeyPair(factory.getKeyPair("oauth2"));
        return converter;
    }

}
