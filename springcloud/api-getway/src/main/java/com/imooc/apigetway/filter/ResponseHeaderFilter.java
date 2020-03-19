package com.imooc.apigetway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

import java.util.UUID;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;

@Component
public class ResponseHeaderFilter extends ZuulFilter {

    /**
     * 后置过滤器
     * @return
     */
    @Override
    public String filterType() {
        return POST_TYPE;
    }

    /**
     * 过滤器等级一般比默认的相对应的过滤器等级高一级
     * @return
     */
    @Override
    public int filterOrder() {
        return SEND_RESPONSE_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 向返回结果头中增加信息
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext=RequestContext.getCurrentContext();
        HttpServletResponse servletResponse=requestContext.getResponse();
        servletResponse.setHeader("X-Foo", UUID.randomUUID().toString());
        return null;
    }
}
