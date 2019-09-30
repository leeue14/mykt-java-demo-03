/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.leeue.entity;

import lombok.Data;

/**
 * 微信实体类接受
 */
@Data
public class AppEntity {

    private long id;
    private String appId;
    private String appName;
    private String appSecret;
    private String accessToken;
    private String redirectUri;
    private int isFlag;
}
