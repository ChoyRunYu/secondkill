package com.secondkill.zuul.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.secondkill.common.utils.JwtUtils;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author Choy
 * @date 2021/03/01
 * 权限网关过滤
 */
@Component
public class TokenFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = ctx.getRequest();
        String uri = httpServletRequest.getRequestURI();
        // 过滤登录接口
        if (checkUser(uri)){
            return null;
        }
        ctx.setResponseStatusCode(200);
        ctx.getResponse().setContentType("application/json;charset=UTF-8");
        // 其他接口校验是否有authorization字段，没有则不用通过网关
        String accessToken = httpServletRequest.getHeader("Authorization");
        if (accessToken == null) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseBody("{\"code\":40003, \"msg\":\"没有权限，请登录后重试\"}");
        } else{
            // 校验token正确性
            String flag = jwtUtils.checkToken(accessToken);
            if (uri.contains("admin") && "user".equals(flag)){
                ctx.setResponseBody("{\"code\":10005, \"msg\":\"权限不足\"}");
            } else if ("error".equals(flag)){
                ctx.setResponseBody("{\"code\":10003, \"msg\":\"token解析错误，请重新登录\"}");
            } else if("expired".equals(flag)){
                ctx.setResponseBody("{\"code\":10004, \"msg\":\"token已过期，请重新登录\"}");
            }else{
                ctx.addZuulRequestHeader("Authorization", accessToken);
            }
        }
        return null;
    }

    /**
     * 校验需要进行权限校验的url,白名单匹配
     * @return
     */
    public Boolean checkUser(String url){
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String[] writeUrls = new String[]{
                "/api/auth/user",
                "/api/auth/admin",
                "/api/goods/listMsGoods",
                "/api/goods/msGoods/detail/{id}",
                "/api/user/check/{username}",
                "/api/user/register",
                "/api/goods/listMsGoods/{keyword}"
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
}
