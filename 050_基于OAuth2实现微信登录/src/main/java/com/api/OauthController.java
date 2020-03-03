package com.api;

import com.alibaba.fastjson.JSONObject;
import com.utils.HttpClientUtils;
import com.utils.WeiXinUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liyue
 * @date 2019-08-13 10:30
 */
@Controller
public class OauthController {


    @Autowired
    WeiXinUtils weiXinUtils;

    // 获取授权链接
    @GetMapping("/authorizedUrl")
    public String getOauthOpen() {

        return "redirect:" + weiXinUtils.getAuthorizedUrl();
    }

    /**
     * 这个必须要这么写 必须是callback函数
     *
     * @param request
     * @param code
     * @return
     */
    @GetMapping("/callback")
    public void callback(HttpServletRequest request, String code) {

        String accessTokenUrl = weiXinUtils.getAccessTokenUrl(code);

        JSONObject resultAccessToken = HttpClientUtils.httpGet(accessTokenUrl);

        //1.看请求json是否正确返回
        if (resultAccessToken.containsKey("errcode")) {
            System.out.println("JSON请求错误");
            return;
        }
        //2.获取access_token
        String accessToken = resultAccessToken.getString("access_token");
        if (StringUtils.isEmpty(accessToken)) {
            System.out.println("获取accessToken错误");
            return;
        }
        String openId = resultAccessToken.getString("openid");

        //根据accessToken和OpenId来获取调用用户信息的地址
        String userInfoUrl = weiXinUtils.getUserInfoUrl(accessToken, openId);

        //httpclient获取微信信息
        JSONObject userInfoResult = HttpClientUtils.httpGet(userInfoUrl);

        System.out.println(userInfoResult);
    }

}
