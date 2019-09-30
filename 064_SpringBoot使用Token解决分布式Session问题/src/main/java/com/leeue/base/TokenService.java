/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.leeue.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年8月14日 下午8:38:30<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@Service
public class TokenService {
	@Autowired
	private RedisService redisService;

	// 1. 使用token方式替代Session功能
	// 存入
	public String put(String value) {
		// 1.判断是否为空
		if (value == null) {
			return null;
		}
		// 2.先生成对应的token (token 实际上等于 key)
		String token = getToken();
		// 3.存入在redis中
		redisService.setString(token, value);
		// 4.直接返回对应的token
		return token;
	}

	public String get(String token) {
		return redisService.getString(token);
	}

	public String getToken() {
		return UUID.randomUUID().toString();
	}

}
