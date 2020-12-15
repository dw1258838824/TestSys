package com.ruoyi.project.system.api.service;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.framework.configure.JWTUtil;
import com.ruoyi.framework.configure.StudentSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class WeChatService {

    @Value("${wechat.appid}")
    private String appid;
    @Value("${wechat.secret}")
    private String secret;
    @Value("${wechat.redirect_uri}")
    private String redirect_uri;
    @Value("${wechat.state}")
    private String state;

    public String login(){
        StringBuilder sb = new StringBuilder("https://open.weixin.qq.com/connect/oauth2/authorize");
        sb.append("?appid=" + appid);
        sb.append("&redirect_uri=" + redirect_uri);
        sb.append("&response_type=code");
        sb.append("&scope=snsapi_userinfo");
        if(StringUtils.isNotEmpty(state)){
            sb.append("&state=" + state);
        }
        sb.append("#wechat_redirect");
        return sb.toString();
    }

    /**
     * 获取access_token信息
     * @param code 用户授权回调的code
     * @return
     */
    public Map<String,Object> getAccessToken(String code){
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        StringBuilder sb = new StringBuilder();
        sb.append("appid=" + appid);
        sb.append("&secret=" + secret);
        sb.append("&code=" + code);
        sb.append("&grant_type=authorization_code");
        Map<String,Object> map = JSON.parseObject(HttpUtils.sendGet(url,sb.toString()),Map.class);
        if(null == map.get("errcode")){
            return map;
        }
        return null;
    }

    /**
     * 获取用户信息
     * @param code 用户授权回调的code
     */
    public Map<String,Object> getWeChatUserInfo(String code){
        //先获取access_token
        Map<String,Object> tokenInfo = getAccessToken(code);
        //如果返回错误
        if(null != tokenInfo.get("errcode")){
            return null;
        }
        String url = "https://api.weixin.qq.com/sns/userinfo";
        StringBuilder sb = new StringBuilder();
        sb.append("access_token=" + tokenInfo.get("access_token"));
        sb.append("&openid=" + tokenInfo.get("openid"));
        sb.append("&lang=zh_CN");
        Map<String,Object> repMap = JSON.parseObject(HttpUtils.sendGet(url,sb.toString()),Map.class);
        //如果没有返回错误
        if(null == repMap.get("errcode")){
            return repMap;
        }
        return null;
    }
}
