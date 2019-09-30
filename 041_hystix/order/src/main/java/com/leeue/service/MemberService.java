/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.leeue.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.leeue.utils.HttpClientUtils;

/**
 * Order服务 远程调用MemberService服务
 * @author liyue
 * @date 2019-05-23 16:31
 */
@Service
public class MemberService {

	public JSONObject getMember() {

		JSONObject result = HttpClientUtils.httpGet("http://127.0.0.1:8081/member/memberIndex");
		return result;
	}

}
