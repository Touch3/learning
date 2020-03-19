package com.imooc.apigetway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/** 使用geogle 提供的限流工具
 * @Description: 令牌限流器
 * @Author: siteFounder
 */
@Component
public class RateFilter extends ZuulFilter {

    /**每秒向桶中放多少个令牌，拿到令牌的请求才能执行过滤操作*/
    private static final RateLimiter RATE_LIMITER=RateLimiter.create(100);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 限流是等级最高的
     * @return
     */
    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //拿不到令牌则抛异常
        if(!RATE_LIMITER.tryAcquire()){
            try {
                throw new Exception("拿不到令牌！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
