package com.carelinker.data.sync.ttd.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.carelinker.core.rest.HttpMethod;
import com.carelinker.core.rest.PostParams;
import com.carelinker.data.sync.ttd.model.ArticlesVO;
import com.carelinker.data.sync.ttd.service.ITTDService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liyue
 * @date 2019-08-21 16:39
 */
@Slf4j
@Service
public class TTDServiceImpl implements ITTDService {
    public static final String LOCAL_TTD_ADD_USER_URL = "http://127.0.0.1:8081/clPatient/api/nes/2/addTTDUser";

    @Override
    public Boolean addTTDUser(String patientId, String idCard, String policyNumber, String phone) {
        //  PostParams postParams = new PostParams("/clCrm/api/crm/users/user/msCards/msCard/application", HttpMethod.POST);

        PostParams params = new PostParams("/clPatient/api/nes/2/addTTDUser", HttpMethod.POST);
        params.add("patientId", patientId);
        params.add("idCard", idCard);
        params.add("policyNumber", policyNumber);
        params.add("phone", phone);
        JSONObject jsonObject = params.send();

        log.info(jsonObject.toJSONString());

        if (jsonObject.containsKey("success") && jsonObject.getBoolean("success")) {
            return true;
        }

        return false;
    }

    @Override
    public List<ArticlesVO> getRecommendArticles(String userId) {

        List<ArticlesVO> articlesList = new ArrayList<>();
        ArticlesVO articlesVO = new ArticlesVO();
        articlesVO.setId(1 + "");
        articlesVO.setTitle("好文章的标题");
        articlesVO.setImgUrl("http://carelinker/1.jpg");
        articlesList.add(articlesVO);

        ArticlesVO articlesVO1 = new ArticlesVO();
        articlesVO1.setId(2 + "");
        articlesVO1.setTitle("好文章的标题");
        articlesVO1.setImgUrl("http://carelinker/1.jpg");
        articlesList.add(articlesVO1);
        return articlesList;
    }
}
