package com.leeue.controller;

import com.leeue.dao.CloudDiskDao;
import com.leeue.entity.CloudDiskEntity;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liyue
 * @date 2019/10/29 17:41
 */
@Controller
public class PageController {

    @Autowired
    private CloudDiskDao cloudDiskDao;

    @RequestMapping("/searchPage")
    public String searchPage(String keyword, String describe,
                             @PageableDefault(page = 0, value = 2) Pageable pageable,
                             HttpServletRequest request) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        Long startTime = System.currentTimeMillis();
        if (StringUtils.isNotBlank(keyword)) {
            //模糊查询 一定要使用ik 中文插件，否则可能会有问题
             /*
    这个会进行分词进行查询
    当前分词器会拆分成: 2018, 史上，最全，SpringBoot*/
            MatchQueryBuilder matchName = QueryBuilders.matchQuery("name", keyword);
            boolQueryBuilder.must(matchName);
        }
        if (StringUtils.isNotBlank(describe)) {
            //换成精确模糊查询 不会进行分词查询
            MatchPhraseQueryBuilder matchDescribe = QueryBuilders.matchPhraseQuery("describe", describe);
            boolQueryBuilder.must(matchDescribe);
        }
        Page<CloudDiskEntity> page = cloudDiskDao.search(boolQueryBuilder, pageable);
        request.setAttribute("keyword",keyword);
        request.setAttribute("page",page);
        request.setAttribute("total",page.getTotalElements());
        //计算分页总数 page.getTotalElements() 查询的总条数
        request.setAttribute("totalPage",(page.getTotalElements()-1)/pageable.getPageSize()+1);
        request.setAttribute("time",  System.currentTimeMillis()-startTime);

        return "search";
    }
}
