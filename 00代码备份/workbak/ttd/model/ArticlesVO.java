package com.carelinker.data.sync.ttd.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TTD 用户首页文章推荐
 *
 * @author liyue
 * @date 2019-08-21 17:44
 */
@Data
public class ArticlesVO {

    @ApiModelProperty("文章id")
    private String id;

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("文章url")
    private String imgUrl;

}
