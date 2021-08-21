package com.MIBlog.config.jwt;


import com.MIBlog.config.exception.ServiceException;
import com.MIBlog.dataobject.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * jwt工具类
 */
public class JWTUtils {

    public static String token="token";

    //密钥
    public static String jwt_secret="MIBlog@rua@shun08474450.";
    //过期时间
    public static  long  jwt_expr= 3600*24*1000 ;
    //1、生成token
    public static String sign(User user){
        // 1、指定签名的时候使用的签名算法
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;
        //2、生成签发时间
        long nowMillis=System.currentTimeMillis();
        Date date = new Date(nowMillis);
        //3、创建playLoad的私有声明
        Map<String,Object> claim=new HashMap<>();

        claim.put("id",user.getUserId());
        claim.put("usrName",user.getUserName());

        System.out.println("生成时的数据===>"+claim);
        //4、生成签发人
        String subject=user.getUserName();

        JwtBuilder builder= Jwts.builder()
                .setClaims(claim)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(date)
                .setSubject(subject)
                .signWith(signatureAlgorithm,jwt_secret);
        //设置过期时间
        Date exprDate = new Date(nowMillis + jwt_expr);
        builder.setExpiration(exprDate);
        return builder.compact();
    }


    //2、验证token
    public static boolean verify(String token){
        System.out.println("token===>"+token);
        try {
            if (StringUtils.isEmpty(token)){
                return false;
            }
            Jwts.parser().setSigningKey(jwt_secret).parseClaimsJws(token).getBody();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    //3、获取用户信息
    public static User getUser(String token){
        try {
            if (StringUtils.isEmpty(token)) {
                throw new ServiceException("token不能为空");
            }
            if (verify(token)){
                Claims claims= Jwts.parser().setSigningKey(jwt_secret).parseClaimsJws(token).getBody();
                User user=new User();
                user.setUserId(claims.get("id")+"");
                user.setUserName(claims.get("userName")+"");

                System.out.println("user==>");
                return user;
            }else {
                throw new ServiceException("请您先登录再试,没有找到token");
            }
        } catch (Exception e) {
            e.printStackTrace();

            throw new ServiceException("请您先登录再试,没有找到token");
        }
    }


}
