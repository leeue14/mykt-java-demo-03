/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.leeue.api;

import com.alibaba.fastjson.JSONObject;
import com.leeue.base.BaseApiService;
import com.leeue.base.ResponseBase;
import com.leeue.entity.AppEntity;
import com.leeue.mapper.AppMapper;
import com.leeue.utils.BaseRedisService;
import com.leeue.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 创建获取getAccessToken
@RestController
@RequestMapping(value = "/auth")
public class AuthController extends BaseApiService {
    @Autowired
    private BaseRedisService baseRedisService;
    private long timeToken = 60 * 60 * 2;
    @Autowired
    private AppMapper appMapper;

    // 使用appId+appSecret 生成AccessToke
    @RequestMapping("/getAccessToken")
    public ResponseBase getAccessToken(AppEntity appEntity) {
        AppEntity appResult = appMapper.findApp(appEntity);
        if (appResult == null) {
            return setResultError("没有对应机构的认证信息");
        }
        int isFlag = appResult.getIsFlag();
        if (isFlag == 1) {
            return setResultError("您现在没有权限生成对应的AccessToken");
        }
        // ### 获取新的accessToken 之前删除之前老的accessToken
        // 从redis中删除之前的accessToken
        String accessToken = appResult.getAccessToken();
        baseRedisService.delKey(accessToken);
        // 生成的新的accessToken
        String newAccessToken = newAccessToken(appResult.getAppId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accessToken", newAccessToken);
        return setResultSuccessData(jsonObject);
    }

    private String newAccessToken(String appId) {
        // 使用appid+appsecret 生成对应的AccessToken 保存两个小时
        String accessToken = TokenUtils.getAccessToken();
        // 保证在同一个事物redis 事物中
        // 生成最新的token key为accessToken value 为 appid
        baseRedisService.setString(accessToken, appId, timeToken);
        // 表中保存当前accessToken
        appMapper.updateAccessToken(accessToken, appId);
        return accessToken;
    }
}
