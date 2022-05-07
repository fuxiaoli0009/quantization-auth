package com.quantization.gateway.filter;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginFilter  extends ZuulFilter {

	@Override
	public Object run() throws ZuulException {
		log.info("执行过滤器");
		return null;
	}
	
	@Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

}