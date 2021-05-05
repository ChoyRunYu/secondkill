package com.secondkill.zuul.service;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

/**
 * 令牌桶限流
 * @author choy
 * @date 2021/03/25
 */
@Service
public class RateLimitService {

    /**
     * 每秒发出10个令牌
     */
    RateLimiter rateLimiter = RateLimiter.create(10.0);

    /**
     * 尝试获取令牌
     * @return
     */
    public Boolean tryAcquire(){
        return rateLimiter.tryAcquire();
    }
}
