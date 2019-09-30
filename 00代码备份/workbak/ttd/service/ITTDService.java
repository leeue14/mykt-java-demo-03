package com.carelinker.data.sync.ttd.service;

import com.carelinker.data.sync.ttd.model.ArticlesVO;

import java.util.List;

/**
 * @author liyue
 * @date 2019-08-21 16:38
 */
public interface ITTDService {

    Boolean addTTDUser(String patientId, String idCard, String policyNumber, String phone);

    /**
     * 获取用户首页文章推荐
     * @param userId
     * @return
     */
    List<ArticlesVO> getRecommendArticles(String userId);
}
