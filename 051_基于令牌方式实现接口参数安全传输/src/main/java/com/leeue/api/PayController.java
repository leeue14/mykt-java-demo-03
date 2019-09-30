package com.leeue.api;

import com.leeue.base.BaseApiService;
import com.leeue.utils.BaseRedisService;
import com.leeue.utils.Constants;
import com.leeue.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyue
 * @date 2019-08-13 18:19
 */
@RestController
public class PayController extends BaseApiService {


    @Autowired
    BaseRedisService baseRedisService;

    @GetMapping("/getPayToken")
    public String getPayToken(String userId, Double money) {

        //生成令牌
        String payToken = TokenUtils.getAccessToken();
        baseRedisService.setString(payToken, userId + "----" + money, Constants.PAY_TOKEN_MEMBER_TIME);

        return payToken;
    }


    @GetMapping("/pay")
    public String pay(String payToken) {

        //生成令牌
        String userInfo = (String) baseRedisService.getString(payToken);
        return userInfo;//这里应该是跳页面的
    }
}
