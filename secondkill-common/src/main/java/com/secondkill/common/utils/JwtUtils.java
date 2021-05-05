package com.secondkill.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.secondkill.common.enums.ResultErrEnum;
import com.secondkill.common.expection.AppRuntimeException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;


/**
 * @author Choy
 * @date 2021/03/01
 * jwt集成工具类
 */
@Component
public class JwtUtils {

    private final static Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    /**
     * 过期时间
     */
    @Value(("${auth.expireTime:3600000}"))
    private String EXPIRE_TIME;

    /**
     * 管理员私钥
     */
    @Value("${auth.privateKey:null}")
    private String PRIVATE_KEY;

    /**
     * 管理员公钥
     */
    @Value("${auth.publicKey}")
    private String PUBLIC_KEY;


    /**
     * 根据私钥生成token
     * @param userInfo payload信息
     * @param isAdmin  是否为管理员
     * @return
     */
    public String generateToken(Object userInfo, String isAdmin){
        logger.debug("传入参数为：{} --- {}", userInfo.toString(), isAdmin);
        // 生成过期时间
        Date expireTime = new Date();
        expireTime.setTime(System.currentTimeMillis() + Long.parseLong(EXPIRE_TIME));
        // 生成token
        String userToken = Jwts.builder()
                .claim("userinfo", userInfo)
                .claim("admin", isAdmin)
                .setExpiration(expireTime)
                .signWith(SignatureAlgorithm.RS256, generatePrivateKey())
                .compact();
        logger.info("颁发的token为{}", userToken);
        return userToken;
    }

    /**
     * 解析token
     * @param token 颁发的token
     * @return
     */
    public String verityToken(String token){
        try{
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(generatePublicKey()).parseClaimsJws(token);
            return "true";
        } catch (SignatureException e){
            logger.warn("token解析出错");
            return "false";
        } catch (ExpiredJwtException e){
            logger.warn("token过期");
            return "expired";
        }
    }

    /**
     * 解析token获取用户id
     * @param token 颁发的token
     * @return
     */
    public Integer getTokenUserId(String token){
        try{
            // 解析token
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(generatePublicKey()).parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            Object userInfo = body.get("userinfo");
            // 将解析的claims转成map
            Map map = JSONObject.parseObject(JSONObject.toJSONString(userInfo), Map.class);
            // 获取用户id
            Integer id = Integer.parseInt(map.get("id").toString());
            return id;
        } catch (SignatureException e){
            logger.warn("token解析出错");
            throw new AppRuntimeException(ResultErrEnum.ERR_TOKEN);
        } catch (ExpiredJwtException e){
            logger.warn("token过期");
            throw new AppRuntimeException(ResultErrEnum.EXPIRED_TOKEN);
        }
    }

    /**
     * 获取是否为管理员标志
     * @param token
     * @return
     */
    public String checkToken(String token){
        try{
            // 解析token
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(generatePublicKey()).parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            Object userInfo = body.get("admin");
            // 获取用户id
            Boolean flag = Boolean.parseBoolean(userInfo.toString());
            return flag ? "admin" : "user";
        } catch (SignatureException e){
            logger.warn("token解析出错");
            return "error";
        } catch (ExpiredJwtException e){
            logger.warn("token过期");
            return "expired";
        }
    }


    /**
     * 生成私钥
     * @return
     */
    private PrivateKey generatePrivateKey(){
        byte[] bytes = Base64.getDecoder().decode(PRIVATE_KEY.replaceAll(" ", ""));
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);
        try{
            KeyFactory factory = KeyFactory.getInstance("RSA");
            privateKey = factory.generatePrivate(spec);
        }catch (Exception e){
            logger.warn("生成私钥失败, {}", e);
        }
        return privateKey;
    }

    /**
     * 生成公钥
     * @return
     */
    private PublicKey generatePublicKey(){
        byte[] bytes = Base64.getDecoder().decode(PUBLIC_KEY.replace(" ", ""));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
        PublicKey publicKey = null;
        try{
            KeyFactory factory = KeyFactory.getInstance("RSA");
            publicKey = factory.generatePublic(spec);
        }catch (Exception e){
            logger.warn("生成公钥失败, {}", e);
        }
        return publicKey;
    }
}
