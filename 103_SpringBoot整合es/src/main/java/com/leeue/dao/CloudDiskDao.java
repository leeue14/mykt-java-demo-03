package com.leeue.dao;

import com.leeue.entity.CloudDiskEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CloudDiskDao extends ElasticsearchRepository<CloudDiskEntity, String> {

}