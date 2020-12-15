package com.ruoyi.framework.configure;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JWTUtil {

    private static final String SECRET = "cielitt";
    private static final String ISSUER = "leyuyingchai";
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtil.class);

    /**24小时的毫秒数*/
    private final static int EXPIRES_SECOND = 1000 * 60 * 2;
    /**JsonWebToken头部信息*/
    private static Map<String,Object> HEADER_PARAMS = Maps.newHashMap();
    static {
        HEADER_PARAMS.put("alg","HS256");
        HEADER_PARAMS.put("typ","JWT");
    }

    /**
     * 校验JWT数据，返回自定义的数据
     * @param jwt
     * @return
     */
    public static StudentSession parseJWT(String jwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                    .parseClaimsJws(jwt).getBody();
            return JSON.parseObject(String.valueOf(claims.get("StudentSession")),StudentSession.class);
        }catch (Exception e){
            LOGGER.info("解析登录Cookie异常：" + e.getMessage());
        }
        return null;
    }

    /**
     * 创建JWT
     * @param studentSession
     * @return
     */
    public static String createJWT(StudentSession studentSession) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParams(HEADER_PARAMS)
                .setIssuer(ISSUER)
                .claim("StudentSession", JSON.toJSONString(studentSession))
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (EXPIRES_SECOND >= 0) {
            long expMillis = nowMillis + EXPIRES_SECOND;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }
        //生成token
        return builder.compact();
    }

}
