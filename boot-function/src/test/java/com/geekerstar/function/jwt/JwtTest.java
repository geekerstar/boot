package com.geekerstar.function.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.junit.Test;
import sun.misc.BASE64Encoder;

import javax.crypto.SecretKey;

/**
 * @author geekerstar
 * @date 2023/4/5 16:52
 */
public class JwtTest {

    /**
     * 创建Token
     */
    @Test
    public void createJwt(){
        //私钥字符串
        String key = "1234567890_1234567890_1234567890";
        //1.对秘钥做BASE64编码
        String base64 = new BASE64Encoder().encode(key.getBytes());
        //2.生成秘钥对象,会根据base64长度自动选择相应的 HMAC 算法
        SecretKey secretKey = Keys.hmacShaKeyFor(base64.getBytes());
        //3.利用JJWT生成Token
        String data = "{\"userId\":123}"; //载荷数据
        String jwt = Jwts.builder().setSubject(data).signWith(secretKey).compact();
        System.out.println(jwt);
    }

    /**
     * 校验及提取JWT数据
     */
    @Test
    public void checkJwt(){
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJ1c2VySWRcIjoxMjN9In0.1p_VTN46sukRJTYFxUg93CmfR3nJZRBm99ZK0e3d9Hw";
        //私钥
        String key = "1234567890_1234567890_1234567890";
        //1.对秘钥做BASE64编码
        String base64 = new BASE64Encoder().encode(key.getBytes());
        //2.生成秘钥对象,会根据base64长度自动选择相应的 HMAC 算法
        SecretKey secretKey = Keys.hmacShaKeyFor(base64.getBytes());
        //3.验证Token
        try {
            //生成JWT解析器
            JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey).build();
            //解析JWT
            Jws<Claims> claimsJws = parser.parseClaimsJws(jwt);
            //得到载荷中的用户数据
            String subject = claimsJws.getBody().getSubject();
            System.out.println(subject);
        }catch (JwtException e){
            //所有关于Jwt校验的异常都继承自JwtException
            System.out.println("Jwt校验失败");
            e.printStackTrace();
        }
    }
}
