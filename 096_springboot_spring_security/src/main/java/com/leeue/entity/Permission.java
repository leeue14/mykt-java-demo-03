package com.leeue.entity;

import lombok.Data;

/**
 * 权限
 *
 * @author liyue
 * @date 2019/12/18 16:57
 */
@Data
public class Permission {

    private Integer id;
    /**
     * 权限名称
     */
    private String permName;

    /**
     * 权限标识
     */
    private String permTag;

    /**
     * 请求url
     */
    private String url;
}
