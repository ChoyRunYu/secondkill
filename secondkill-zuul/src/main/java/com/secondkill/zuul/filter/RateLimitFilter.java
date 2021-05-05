package com.secondkill.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.secondkill.zuul.service.RateLimitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 限流过滤器
 * @author choy
 * @date 2021/03/25
 */
@Component
public class RateLimitFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(RateLimitFilter.class);

    @Autowired
    private RateLimitService rateLimitService;

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
        // 获取请求上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        // 获取请求
        HttpServletRequest request = ctx.getRequest();
        // 获取请求的uri
        String uri = request.getRequestURI();
        // uri地址匹配
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        // 如果是立即抢购地址，则需要限流，其他接口暂时不用
        return checkUrl(uri);
    }

    /**
     * 校验需要进行权限校验的url,白名单匹配
     * @return
     */
    public Boolean checkUrl(String url){
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String[] writeUrls = new String[]{
                "/api/order/checkStock/{id}",
                "/api/order/createOrder/{id}"
        };
        boolean flag = false;
        for (String writeUrl : writeUrls) {
            if (antPathMatcher.match(writeUrl, url)){
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse response = ctx.getResponse();
        try{
            logger.info("获取令牌");
            // 尝试获取令牌
            if (!rateLimitService.tryAcquire()){
                ctx.setSendZuulResponse(false);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=utf-8");
                ctx.setResponseBody("{\"code\":40004, \"msg\":\"哎呀，访问人数多，店小二正在处理~\"}");
                logger.info("被限流了");
            }else{
                logger.info("没有被限流");
                ctx.setSendZuulResponse(true);
                return true;
            }
        }catch (Exception e){
            ctx.setSendZuulResponse(false);
        }finally {
            return false;
        }
    }
}
