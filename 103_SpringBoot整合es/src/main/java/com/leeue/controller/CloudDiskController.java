package com.leeue.controller;

import com.google.common.collect.Lists;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * SpringBoot 整合es 增删查改
 *
 * @author liyue
 * @date 2019/10/29 14:22
 */
@RestController
public class CloudDiskController {
    @Autowired
    private CloudDiskDao cloudDiskDao;


    @RequestMapping("/findById/{id}")
    public Optional<CloudDiskEntity> findById(@PathVariable String id) {
        return cloudDiskDao.findById(id);
    }

    @RequestMapping("/save")
    public CloudDiskEntity save(@RequestBody CloudDiskEntity cloudDiskEntity) {
        return cloudDiskDao.save(cloudDiskEntity);
    }

    @RequestMapping("/update")
    public CloudDiskEntity update(@RequestBody CloudDiskEntity cloudDiskEntity) {
        return cloudDiskDao.save(cloudDiskEntity);
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        cloudDiskDao.deleteById(id);
    }

    //查询所有数据
    // 当查询 2018史上最全SpringBoot
    /*
    这个会进行分词进行查询
    当前分词器会拆分成: 2018, 史上，最全，SpringBoot
     */
    @RequestMapping("/search")
    public List<CloudDiskEntity> search(String keyWord,String describe) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (StringUtils.isNotBlank(keyWord)) {
            //模糊查询 一定要使用ik 中文插件，否则可能会有问题
             /*
    这个会进行分词进行查询
    当前分词器会拆分成: 2018, 史上，最全，SpringBoot*/

            MatchQueryBuilder matchName = QueryBuilders.matchQuery("name", keyWord);
            boolQueryBuilder.must(matchName);
        }
        if(StringUtils.isNotBlank(describe)){
            //换成精确模糊查询 不会进行分词查询
            MatchPhraseQueryBuilder matchDescribe = QueryBuilders.matchPhraseQuery("describe", describe);
            boolQueryBuilder.must(matchDescribe);
        }
        Iterable<CloudDiskEntity> search = cloudDiskDao.search(boolQueryBuilder);
        return Lists.newArrayList(search);
    }



    @RequestMapping("/search/page")
    public  Page<CloudDiskEntity>  searchPage(String keyWord, String describe, @PageableDefault(page = 0,value = 2) Pageable pageable) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (StringUtils.isNotBlank(keyWord)) {
            //模糊查询 一定要使用ik 中文插件，否则可能会有问题
             /*
    这个会进行分词进行查询
    当前分词器会拆分成: 2018, 史上，最全，SpringBoot*/

            MatchQueryBuilder matchName = QueryBuilders.matchQuery("name", keyWord);
            boolQueryBuilder.must(matchName);
        }
        if(StringUtils.isNotBlank(describe)){
            //换成精确模糊查询 不会进行分词查询
            MatchPhraseQueryBuilder matchDescribe = QueryBuilders.matchPhraseQuery("describe", describe);
            boolQueryBuilder.must(matchDescribe);
        }
        Page<CloudDiskEntity> search = cloudDiskDao.search(boolQueryBuilder, pageable);
        return search;
    }





}
